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

    private ListOfExceptionsDTO listOfExceptionsDTO;
    private MachineDTO machineDTO;
    private TheMachineEngine theMachineEngine;
    private  CTEEnigma cteEnigma;
    SchemaGenerated schemaGenerated;

    private final String JAXB_XML_GAME_PACKAGE_NAME = "schemaGenerated";

    @Override
    public ListOfExceptionsDTO load(String filePath) throws Exception
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
          listOfExceptionsDTO =new ListOfExceptionsDTO(exceptions);
          return listOfExceptionsDTO;
    }
    public TheMachineEngine buildTheMachineEngine(){

         schemaGenerated=new SchemaGenerated(cteEnigma);
        TheMachineEngine theMachineEngine= new TheMachineEngine(schemaGenerated.createRotorsSet(),schemaGenerated.createReflectorsSet(),schemaGenerated.createKeyboard(),schemaGenerated.getAmountOfUsedRotors());
        //machineDTO =new MachineDTO(new ArrayList<>(),cteEnigma.getCTEMachine().getRotorsCount(),theMachineEngine.getRotorsId(),theMachineEngine.getReflectorId(),theMachineEngine.getKeyboard(),0,"");
        //return machineDTO;
        return theMachineEngine;

    }
    public ListOfExceptionsDTO getAllErrorsRelatedToInitCodeManuallyInputStructure(String str){
        TheMachineEngine theMachineEngine=buildTheMachineEngine();
        UserInputValidator2 userInputValidator=new UserInputValidator2(str,cteEnigma,theMachineEngine);
        userInputValidator.validate();
        List<Exception> exceptions=  userInputValidator.getListOfException();
        ListOfExceptionsDTO  listOfExceptionsDTO =new ListOfExceptionsDTO(exceptions);
        return listOfExceptionsDTO;
    }
    public ListOfExceptionsDTO getAllErrorsRelatedToChosenManuallyRotors(String str){
        TheMachineEngine theMachineEngine=buildTheMachineEngine();
        UserInputRotorsValidator userInputRotorsValidator=new UserInputRotorsValidator(str,cteEnigma,theMachineEngine);
        userInputRotorsValidator.validate();
        List<Exception> exceptions=  userInputRotorsValidator.getListOfException();
        ListOfExceptionsDTO  listOfExceptionsDTO =new ListOfExceptionsDTO(exceptions);
        return listOfExceptionsDTO;
    }
    public int getRotorsAmount(){
        return cteEnigma.getCTEMachine().getRotorsCount();
    }
    public void initCodeAutomatically(){
        theMachineEngine = buildTheMachineEngine();
        chooseAutomaticallyRotors(theMachineEngine);
        initRotorsPositionAutomatically(theMachineEngine);
        chooseAutomaticallyReflector(theMachineEngine);
        choosePlugBoardSettings(theMachineEngine);
    }
    private void chooseAutomaticallyRotors(TheMachineEngine theMachineEngine){
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
                rotorId=randomSelectedRotor.getRotorId();


            }
            System.out.println(randomSelectedRotor.getRotorId());
            rotorsHashMap.put(rotorId,1);
            listOfRandomRotors.add(randomSelectedRotor);
        }
        theMachineEngine.createUsedRotorsSet(listOfRandomRotors);
    }
    private void chooseAutomaticallyReflector(TheMachineEngine theMachineEngine){
        List<Reflector> listOfReflectors = theMachineEngine.getReflectorsSet().getListOfReflectors();
        Random randomGenerator = new Random();
        Reflector randomSelectedReflector= listOfReflectors.get((randomGenerator.nextInt(listOfReflectors.size())));
        theMachineEngine.addSelectedReflector(randomSelectedReflector.getReflectorId());
        System.out.println(randomSelectedReflector.getReflectorId());
    }

    private void initRotorsPositionAutomatically(TheMachineEngine theMachineEngine){
      String keyboard=theMachineEngine.getKeyboard();
        Random randomGenerator = new Random();
         String randomSelectedPosition;
         List<Rotor> rotorsSet=theMachineEngine.getUsedRotors().getListOfRotors();
       /* for (Rotor rotor: rotorsSet) {
            randomSelectedPosition = String.valueOf(keyboard.charAt((toUpperCase(randomGenerator.nextInt(keyboard.length())))));
            rotor.setRotorStartingPosition(randomSelectedPosition);
        }*/
        rotorsSet.get(0).setRotorStartingPosition("F");
        rotorsSet.get(1).setRotorStartingPosition("D");

    }
    private void choosePlugBoardSettings(TheMachineEngine theMachineEngine){
        String keyboard=theMachineEngine.getKeyboard();
        Random randomGenerator = new Random();
        HashMap<String,Integer> plugBoardHashMap=new HashMap<>();
        List<Pair> pairsOfSwappingLetters=new ArrayList<>();
        int amountOfSwappingPairs=randomGenerator.nextInt(keyboard.length()/2);
        String firstSignal,secondSignal;
        if(amountOfSwappingPairs>0) {
            for (int i = 0; i < amountOfSwappingPairs; i++) {
                firstSignal = String.valueOf(keyboard.charAt(randomGenerator.nextInt(keyboard.length())));
                while (((plugBoardHashMap.get(firstSignal)) != null) && (plugBoardHashMap.get(firstSignal) == 1)) {
                    firstSignal = String.valueOf(keyboard.charAt(randomGenerator.nextInt(keyboard.length())));
                }
                plugBoardHashMap.put(firstSignal, 1);
                secondSignal = String.valueOf(keyboard.charAt(randomGenerator.nextInt(keyboard.length())));
                while (((plugBoardHashMap.get(secondSignal)) != null) && (plugBoardHashMap.get(secondSignal) == 1)) {
                    secondSignal = String.valueOf(keyboard.charAt(randomGenerator.nextInt(keyboard.length())));
                }
                plugBoardHashMap.put(secondSignal, 1);
                Pair pair = new Pair(firstSignal, secondSignal);
                pairsOfSwappingLetters.add(pair);
            }
            PlugsBoard plugsBoard=new PlugsBoard(keyboard,pairsOfSwappingLetters);
             theMachineEngine.addPlugsBoardTOTheMachine(plugsBoard);
        }
    }

    public ListOfExceptionsDTO getAllErrorsRelatedToFilePath(String filePath) {
        XmlFileValidator xmlFileValidator = new XmlFileValidator(filePath);
        xmlFileValidator.validate();
        List<Exception> exceptions=  xmlFileValidator.getListOfException();
        listOfExceptionsDTO =new ListOfExceptionsDTO(exceptions);
        return listOfExceptionsDTO;
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
    public ListOfExceptionsDTO getAllErrorsConvertingInputProcess(String userInput){
        List<Exception> inputListOfException;
        Validator userInputValidator=new UserStringProcessorValidator(userInput,theMachineEngine);
        userInputValidator.validate();
        inputListOfException=userInputValidator.getListOfException();
        ListOfExceptionsDTO inputListOfExceptionDTO=new ListOfExceptionsDTO(inputListOfException);
        return inputListOfExceptionDTO;

    }
    public ConvertedStringDTO getConvertedString(String userInputString){

        String convertedString="";
        for (int i=0;i<userInputString.length();i++){
            String userInputByString =String.valueOf(userInputString.charAt(i));
            String convertedCharByString=theMachineEngine.manageDecode(userInputByString);
            convertedString=convertedString.concat(convertedCharByString);
            //convertedString=convertedString.concat(theMachineEngine.manageDecode(String.valueOf(userInputString.charAt(i))));
        }
        ConvertedStringDTO convertedStringDTO=new ConvertedStringDTO(convertedString);
        return convertedStringDTO;


    }
}