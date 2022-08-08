package EngineManager;

import Engine.TheEnigmaEngine.*;
import Engine.validator.*;
import HistoryAndStatistics.HistoryOfProcess;
import HistoryAndStatistics.MachineHistoryAndStatistics;
import schemaGenerated.CTEEnigma;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.*;

import MachineDTO.*;

import static java.lang.Character.toUpperCase;


public class EngineManager implements EngineManagerInterface {

    private ListOfExceptionsDTO listOfExceptionsDTO;
    private CurrentCodeDescriptionDTO currentCodeDescriptionDTO;
    private MachineDTO machineDTO;
    private TheMachineEngine theMachineEngine;
    private MachineHistoryAndStatistics machineHistoryAndStatistics=new MachineHistoryAndStatistics();
    private  CTEEnigma cteEnigma;
    SchemaGenerated schemaGenerated;
    boolean isCodeConfigurationSet=false;
    private int amountOfProcessedMessages=0;
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
    public boolean getIsCodeConfigurationSet(){
        return isCodeConfigurationSet;
    }
    public ListOfExceptionsDTO getAllErrorsRelatedToInitCodeManuallyInputStructure(String str){
         theMachineEngine=buildTheMachineEngine();
        UserInputValidator2 userInputValidator=new UserInputValidator2(str,cteEnigma,theMachineEngine);
        userInputValidator.validate();
        List<Exception> exceptions=  userInputValidator.getListOfException();
        ListOfExceptionsDTO  listOfExceptionsDTO =new ListOfExceptionsDTO(exceptions);
        isCodeConfigurationSet=true;
        return listOfExceptionsDTO;
    }
    public void DefineIsCodeConfigurationSetValueToTrue(){
        this.isCodeConfigurationSet=true;
        reverseUsedRotors(theMachineEngine);
        machineHistoryAndStatistics.addNewMachineSettings(createCurrentCodeDescriptionDTO());
    }
    private void reverseUsedRotors(TheMachineEngine theMachineEngine){
        theMachineEngine.reverseUsedRotors();

    }
    public ListOfExceptionsDTO getAllErrorsRelatedToChosenManuallyRotors(String str){
         theMachineEngine=buildTheMachineEngine();
        UserInputRotorsValidator userInputRotorsValidator=new UserInputRotorsValidator(str,theMachineEngine);
        userInputRotorsValidator.validate();
        List<Rotor> listOfRotors=new ArrayList<>();
        String[] rotorsId=userInputRotorsValidator.getFilteredUserInput();
          if(rotorsId!=null) {
            for (String rotorId : rotorsId) {
                listOfRotors.add(theMachineEngine.getRotorsSet().getRotorById(rotorId));
            }
            chooseManuallyRotors(listOfRotors);
        }
        List<Exception> exceptions=  userInputRotorsValidator.getListOfException();
        ListOfExceptionsDTO  listOfExceptionsDTO =new ListOfExceptionsDTO(exceptions);
        return listOfExceptionsDTO;
    }
    public ListOfExceptionsDTO getAllErrorsRelatedToChosenManuallyStartingPosition(String str){
        Validator userInputStartingPositionValidator=new UserInputStartingPositionValidator(str,theMachineEngine);
        userInputStartingPositionValidator.validate();
        List<Exception> exceptions= userInputStartingPositionValidator.getListOfException();
        ListOfExceptionsDTO  listOfExceptionsDTO =new ListOfExceptionsDTO(exceptions);
        return listOfExceptionsDTO;
    }
    public ListOfExceptionsDTO getAllErrorsRelatedToChosenManuallyReflectorId(String str){
        UserInputReflectorValidator userInputReflectorValidator=new UserInputReflectorValidator(str,theMachineEngine);
        userInputReflectorValidator.validate();
        String reflectorId=userInputReflectorValidator.getReflectorId();
        chooseManuallyReflect(reflectorId);
        List<Exception> exceptions= userInputReflectorValidator.getListOfException();
        ListOfExceptionsDTO  listOfExceptionsDTO =new ListOfExceptionsDTO(exceptions);
        return listOfExceptionsDTO;
    }
    public ListOfExceptionsDTO getAllErrorsRelatedToChosenManuallyPlagBoard(String str){
        Validator userInputPlugBoardValidator=new UserInputPlugBoardValidator(str,theMachineEngine);
        userInputPlugBoardValidator.validate();
        List<Exception> exceptions= userInputPlugBoardValidator.getListOfException();
        ListOfExceptionsDTO  listOfExceptionsDTO =new ListOfExceptionsDTO(exceptions);
        return listOfExceptionsDTO;
    }
    public void chooseManuallyRotors(List<Rotor> rotors){
        if(rotors.size()>0){
            theMachineEngine.createUsedRotorsSet(rotors);
        }
    }
    public void chooseManuallyStartingPosition(String userInput){
        userInput=userInput.toUpperCase();
        String startingPosition;
        int i=0;

        List<Rotor> rotorsSet=theMachineEngine.getUsedRotors().getListOfRotors();
        for (Rotor rotor: rotorsSet) {
            startingPosition=String.valueOf(userInput.charAt(i));
            rotor.setRotorStartingPosition(startingPosition);
            i++;
        }
    }
    public void chooseManuallyReflect(String reflectorId){
       if(reflectorId!="") {
           theMachineEngine.addSelectedReflector(reflectorId);
       }
    }
    public void chooseManuallyPlugBoard(String userInput){
        userInput=userInput.toUpperCase();
        List<Pair> pairsOfSwappingLetters=new ArrayList<>();
        String firstSignal,secondSignal;
        int amountOfSwappingPairs=userInput.length()/2;
        if(amountOfSwappingPairs>0) {
            for (int i = 0; i < userInput.length()-1; i++) {
                firstSignal = String.valueOf(userInput.charAt(i));
                secondSignal = String.valueOf(userInput.charAt(i+1));
                Pair pair = new Pair(firstSignal, secondSignal);
                pairsOfSwappingLetters.add(pair);
            }
        }
        PlugsBoard plugsBoard=new PlugsBoard(theMachineEngine.getKeyboard(),pairsOfSwappingLetters);
        theMachineEngine.addPlugsBoardTOTheMachine(plugsBoard);

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
        isCodeConfigurationSet=true;
        reverseUsedRotors(theMachineEngine);
        machineHistoryAndStatistics.addNewMachineSettings(createCurrentCodeDescriptionDTO());


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
    }

    private void initRotorsPositionAutomatically(TheMachineEngine theMachineEngine){
      String keyboard=theMachineEngine.getKeyboard();
        Random randomGenerator = new Random();
         String randomSelectedPosition;
         List<Rotor> rotorsSet=theMachineEngine.getUsedRotors().getListOfRotors();
        for (Rotor rotor: rotorsSet) {
            randomSelectedPosition = String.valueOf(keyboard.charAt((toUpperCase(randomGenerator.nextInt(keyboard.length())))));
            rotor.setRotorStartingPosition(randomSelectedPosition);
        }
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

        }
        PlugsBoard plugsBoard=new PlugsBoard(keyboard,pairsOfSwappingLetters);
        theMachineEngine.addPlugsBoardTOTheMachine(plugsBoard);


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
        Validator userInputValidator=new UserStringProcessorValidator(userInput.toUpperCase(),theMachineEngine);
        userInputValidator.validate();
        inputListOfException=userInputValidator.getListOfException();
        ListOfExceptionsDTO inputListOfExceptionDTO=new ListOfExceptionsDTO(inputListOfException);
        return inputListOfExceptionDTO;

    }
    public ConvertedStringDTO getConvertedString(String userInputString){
        long begin = System.nanoTime();

        String convertedString="";
        userInputString=userInputString.toUpperCase();
        for (int i=0;i<userInputString.length();i++){
            String userInputByString =String.valueOf(userInputString.charAt(i));
            String convertedCharByString=theMachineEngine.manageDecode(userInputByString);
            convertedString=convertedString.concat(convertedCharByString);
            //convertedString=convertedString.concat(theMachineEngine.manageDecode(String.valueOf(userInputString.charAt(i))));
        }
        long end = System.nanoTime();
        amountOfProcessedMessages++;
        ConvertedStringDTO convertedStringDTO=new ConvertedStringDTO(convertedString);
        machineHistoryAndStatistics.addNewProcess(currentCodeDescriptionDTO,new HistoryOfProcess(userInputString,convertedString,end-begin));

        return convertedStringDTO;



    }
    public List<MachineHistoryAndStatisticsDTO> getHistoryAndStatisticsDTO() {
        List<MachineHistoryAndStatisticsDTO> listOfMachineHistoryAndStatisticsDTO=new ArrayList<>();
        Map<CurrentCodeDescriptionDTO, List<HistoryOfProcess>> machineHistory= machineHistoryAndStatistics.getMachineHistory();


        int j=0;
         for (Map.Entry<CurrentCodeDescriptionDTO,List<HistoryOfProcess>> entry : machineHistory.entrySet()) {
             String[] userInput=new String[entry.getValue().size()];
             String[] convertedString=new String[entry.getValue().size()];;
             String[] timeToProcess=new String[entry.getValue().size()];;

            for (int i=0;i<entry.getValue().size();i++) {
                userInput[i]=entry.getValue().get(i).getUserInput();
                convertedString[i]=entry.getValue().get(i).getConvertedInput();
                timeToProcess[i]=String.valueOf(entry.getValue().get(i).getTimeToProcess());


            }
             HistoryAndStatisticsDTO historyAndStatisticsDTO=new HistoryAndStatisticsDTO(userInput,convertedString,timeToProcess);
             MachineHistoryAndStatisticsDTO machineHistoryAndStatisticsDTO=new MachineHistoryAndStatisticsDTO(entry.getKey(),historyAndStatisticsDTO);
             listOfMachineHistoryAndStatisticsDTO.add(machineHistoryAndStatisticsDTO);


        }
         return listOfMachineHistoryAndStatisticsDTO;


    }





        public void resetCurrentCode(){
        theMachineEngine.resetCurrentRotorSetCode();
    }
    public TheMachineSettingsDTO getTheMachineSettingsDTO(){
        int amountOfUsedRotors=theMachineEngine.getAmountOfUsedRotors();
        int maxAmountOfRotors=theMachineEngine.getMaxAmountOfRotors();
        List<String> notchPosition=theMachineEngine.getListOfNotch();
       int amountOfReflectors= theMachineEngine.getReflectorsAmount();

        CurrentCodeDescriptionDTO currentCodeDescriptionDTO=createCurrentCodeDescriptionDTO();
        TheMachineSettingsDTO theMachineSettingsDTO=new TheMachineSettingsDTO(amountOfUsedRotors,maxAmountOfRotors,notchPosition,amountOfReflectors,amountOfProcessedMessages,currentCodeDescriptionDTO);
        return theMachineSettingsDTO;
    }
    public  CurrentCodeDescriptionDTO createCurrentCodeDescriptionDTO(){
        CurrentCodeDescriptionDTO currentCodeDescriptionDTO=null;
        if(isCodeConfigurationSet) {
            String[] usedRotorsId = theMachineEngine.getArrayOfRotorsId();
            String chosenStartingPosition = theMachineEngine.getUsedRotors().getRotorsStartingPositions();
            String reflectorId = theMachineEngine.getReflector().getReflectorId();
            List<String> pairsOfSwappingCharacter = theMachineEngine.getStringPairsOfSwappingCharacter();
          currentCodeDescriptionDTO = new CurrentCodeDescriptionDTO(pairsOfSwappingCharacter, reflectorId, chosenStartingPosition, usedRotorsId);
        }
        this.currentCodeDescriptionDTO=currentCodeDescriptionDTO;

        return currentCodeDescriptionDTO;
    }

}