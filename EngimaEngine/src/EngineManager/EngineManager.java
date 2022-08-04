package EngineManager;

import Engine.TheEnigmaEngine.*;
import Engine.validator.*;
import schemaGenerated.CTEEnigma;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import MachineDTO.*;

import static java.lang.Character.toUpperCase;


public class EngineManager implements EngineManagerInterface {

    private FileDTO fileDTO;
    private MachineDTO machineDTO;

    private  CTEEnigma cteEnigma;
    SchemaGenerated schemaGenerated;

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
        List<Exception> exceptions=  validatorRunner.run();
          fileDTO =new FileDTO(exceptions);
          return fileDTO;
    }
    public TheMachineEngine buildTheMachineEngine(){

         schemaGenerated=new SchemaGenerated(cteEnigma);
        TheMachineEngine theMachineEngine= new TheMachineEngine(schemaGenerated.createRotorsSet(),schemaGenerated.createReflectorsSet(),schemaGenerated.createKeyboard(),schemaGenerated.getAmountOfUsedRotors());
        //machineDTO =new MachineDTO(new ArrayList<>(),cteEnigma.getCTEMachine().getRotorsCount(),theMachineEngine.getRotorsId(),theMachineEngine.getReflectorId(),theMachineEngine.getKeyboard(),0,"");
        //return machineDTO;
        return theMachineEngine;

    }
    public MachineDTO getAllErrorsRelatedToinitCodeConfigurationManually(String str){
        TheMachineEngine theMachineEngine=buildTheMachineEngine();
        UserInputValidator userInputValidator=new UserInputValidator(str,cteEnigma,theMachineEngine);
        List<Validator> validators=new ArrayList<>();
        validators.add(userInputValidator);
        ValidatorRunner validatorRunner=new ValidatorRunner(validators);
        List<Exception> exceptions=  validatorRunner.run();
        machineDTO =new MachineDTO(exceptions,0, new String[]{" "},new String[]{" "},"",0,"");
       return machineDTO;
    }
    public int getRotorsAmount(){
        return cteEnigma.getCTEMachine().getRotorsCount();
    }
    public void initCodeAutomatically(){

        TheMachineEngine theMachineEngine = buildTheMachineEngine();
        createAutomaticallyRotors(theMachineEngine);
        initRotorsPositionAutomatically(theMachineEngine);

    }
    private void createAutomaticallyRotors(TheMachineEngine theMachineEngine){
        List<Rotor> listOfRotors = theMachineEngine.getRotorsSet().getListOfRotors();
        Random randomGenerator = new Random();
        List<Rotor> listOfRandomRotors = new ArrayList<>();
        HashMap<String,Integer> rotorsHashMap=new HashMap<>();
        String rotorId;
        Rotor randomSelectedRotor;
        for (int i = 0; i < theMachineEngine.getAmountOfUsedRotors(); i++) {
            randomSelectedRotor = listOfRotors.get((randomGenerator.nextInt(listOfRotors.size())));
            rotorId=randomSelectedRotor.getRotorId();
            while (((rotorsHashMap.get(rotorId))!=null)&&(rotorsHashMap.get(rotorId)>=1)){
                randomSelectedRotor = listOfRotors.get((randomGenerator.nextInt(listOfRotors.size())));
            }
            rotorsHashMap.put(rotorId,1);
            listOfRandomRotors.add(randomSelectedRotor);
        }
        theMachineEngine.createUsedRotorsSet(listOfRotors);
    }
    private void initRotorsPositionAutomatically(TheMachineEngine theMachineEngine){
      String keyboard=theMachineEngine.getKeyboard();
        Random randomGenerator = new Random();
         String randomSelectedPosition = String.valueOf(keyboard.charAt((toUpperCase(randomGenerator.nextInt(keyboard.length())))));
         List<Rotor> rotorsSet=theMachineEngine.getUsedRotors().getListOfRotors();
        for (Rotor rotor: rotorsSet) {
            rotor.setRotorStartingPosition(randomSelectedPosition);
        }


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