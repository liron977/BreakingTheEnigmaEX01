package Xml;

import schemaGenerated.CTEEnigma;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class XmlHelper {

    private final static String JAXB_XML_GAME_PACKAGE_NAME = "schemaGenerated";

    public static CTEEnigma readFromXml(String filePath) //throws XmlException
    {
        try {
            int len = filePath.length();
            if (len < 4) {
                System.out.println("File full name is too short!");
            }
            else if (!filePath.endsWith(".xml")) {
                //throw new XmlException("the file must be xml file");
                System.out.println("This is not a full path of a xml file!");
            }
            else {
                File file = new File(filePath);
                if (!file.exists()) {
                    //throw new XmlException("can not find the file \"" + filePath + "\"");
                    System.out.println("can not find the file \"" + filePath + "\"");
                } else {
                    JAXBContext jaxbContext = JAXBContext.newInstance(JAXB_XML_GAME_PACKAGE_NAME);
                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    CTEEnigma cteEnigma = (CTEEnigma) jaxbUnmarshaller.unmarshal(file);
                    return cteEnigma;
                }
            }
            return null;
        }
        catch (JAXBException e)
        {
            return null;
        }
    }
}
