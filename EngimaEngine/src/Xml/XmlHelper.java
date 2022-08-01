package Xml;

import Engine.validator.XmlFileValidator;
import Exceptions.XmlException;
import schemaGenerated.CTEEnigma;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

public class XmlHelper {

    private final static String JAXB_XML_GAME_PACKAGE_NAME = "schemaGenerated";


    private boolean isFileNameIsValid(String filePath) {
        XmlFileValidator xmlFileValidator = new XmlFileValidator(filePath);
        List<Exception> errors = xmlFileValidator.getListOfException();
        if (errors.size() == 0) {
            return false;

        }
        return true;
    }
    public CTEEnigma readFromXmlFile(String filePath) throws FileNotFoundException, JAXBException {
        File file = new File(filePath);
        try {
            InputStream inputStream = new FileInputStream(file);
            JAXBContext jaxbContext = JAXBContext.newInstance(JAXB_XML_GAME_PACKAGE_NAME);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            CTEEnigma cteEnigma = (CTEEnigma) jaxbUnmarshaller.unmarshal(inputStream);
            return cteEnigma;
        }
        catch (JAXBException e) {
            throw  new JAXBException(e);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

  /*  public static CTEEnigma readFromXml(String filePath) throws XmlException {
        String error = null;
        try {
            int len = filePath.length();
            if (len < 4) {
                System.out.println("File full name is too short!");
            } else if (!filePath.endsWith(".xml")) {
                //throw new XmlException("the file must be xml file");
                System.out.println("This is not a full path of a xml file!");
            } else {
                File file = new File(filePath);

                if (!file.exists()) {
                    throw new XmlException("can not find the file [" + filePath + "]");
                } else {

                    InputStream inputStream = new FileInputStream(file);
                    JAXBContext jaxbContext = JAXBContext.newInstance(JAXB_XML_GAME_PACKAGE_NAME);
                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    CTEEnigma cteEnigma = (CTEEnigma) jaxbUnmarshaller.unmarshal(inputStream);
                    return cteEnigma;
                }
            }
            return null;
        } catch (JAXBException e) {
            return null;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        }
    }*/


}


