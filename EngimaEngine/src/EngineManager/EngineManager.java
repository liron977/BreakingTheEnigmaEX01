package EngineManager;

import Engine.SchemaGenerated;
import Engine.TheMachineEngine;
import Engine.validator.*;
import schemaGenerated.CTEEnigma;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import MachineDTO.*;


public class EngineManager implements EngineManagerInterface {

    private FileDTO fileDTO;
    private MachineDTO machineDTO;

    private  CTEEnigma cteEnigma;

    private final String JAXB_XML_GAME_PACKAGE_NAME = "schemaGenerated";
    @Override
    public FileDTO load(String filePath) throws Exception
    {
        CTEEnigma cteEnigma =readFromXmlFile(filePath);
        XmlReflectorValidator xmlReflectorValidator =new XmlReflectorValidator(cteEnigma);
        XmlRotorValidator xmlRotorValidator=new XmlRotorValidator((cteEnigma));
        XmlKeyboardValidator xmlKeyboardValidator=new XmlKeyboardValidator(cteEnigma);
        List<Validator> validators=new ArrayList<>();
        validators.add(xmlKeyboardValidator);
        validators.add(xmlReflectorValidator);
        validators.add((xmlRotorValidator));
        ValidatorRunner validatorRunner=new ValidatorRunner(validators);
        List<Exception> exceptions=  validatorRunner.run(cteEnigma);
          fileDTO =new FileDTO(exceptions);
          return fileDTO;
    }
    public TheMachineEngine buildTheMachineEngine(){

        SchemaGenerated schemaGenerated=new SchemaGenerated(cteEnigma);
        return new TheMachineEngine(schemaGenerated.createRotorsSet(),schemaGenerated.createReflectorsSet(),schemaGenerated.createKeyboard());
    }
    public MachineDTO initCodeConfigurationManually(String str){
        UserInputValidator userInputValidator=new UserInputValidator(str,cteEnigma);
        List<Validator> validators=new ArrayList<>();
        validators.add(userInputValidator);
        ValidatorRunner validatorRunner=new ValidatorRunner(validators);
        List<Exception> exceptions=  validatorRunner.run(cteEnigma);
        machineDTO =new MachineDTO(exceptions);
       return machineDTO;
    }
    public int getRotorsAmount(){
        return cteEnigma.getCTEMachine().getRotorsCount();
    }


    public FileDTO getAllErrorsRelatedToFilePath(String filePath) {
        XmlFileValidator xmlFileValidator = new XmlFileValidator(filePath);
        xmlFileValidator.validate();
        List<Exception> exceptions=  xmlFileValidator.getListOfException();
        fileDTO =new FileDTO(exceptions);
        return fileDTO;
    }
    public CTEEnigma readFromXmlFile(String filePath) throws Exception {
        File file = new File(filePath);
        try {
            InputStream inputStream = new FileInputStream(file);
            JAXBContext jaxbContext = JAXBContext.newInstance(JAXB_XML_GAME_PACKAGE_NAME);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            CTEEnigma cteEnigma = (CTEEnigma) jaxbUnmarshaller.unmarshal(inputStream);
            this.cteEnigma=cteEnigma;
            return cteEnigma;
        }
        catch (JAXBException e) {
            throw new Exception("The file is not valid,please enter other file");
        }
        catch (FileNotFoundException e) {
            throw new Exception("The file did not find in this path"+ filePath);
        }

    }
}
