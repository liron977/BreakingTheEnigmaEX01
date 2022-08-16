package engineManager;

import engine.theEnigmaEngine.*;
import engine.validator.*;
import historyAndStatistics.HistoryOfProcess;
import historyAndStatistics.MachineHistoryAndStatistics;
import schemaGenerated.CTEEnigma;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.*;

import machineDTO.*;

import static java.lang.Character.toUpperCase;


public class EngineManager implements EngineManagerInterface,Serializable {

    private ListOfExceptionsDTO listOfExceptionsDTO;
    private CodeDescriptionDTO codeDescriptionDTO;
    private TheMachineEngine theMachineEngine;
    private MachineHistoryAndStatistics machineHistoryAndStatistics=new MachineHistoryAndStatistics();
    private  CTEEnigma cteEnigma;
    private SchemaGenerated schemaGenerated;
    boolean isCodeConfigurationSet=false;
    private int amountOfProcessedMessages=0;
    List<Rotor> listOfRotors;
    String reflectorId;
    private MenuValidator menuValidator=new MenuValidator();
    private final String JAXB_XML_GAME_PACKAGE_NAME = "schemaGenerated";

    @Override
    public ListOfExceptionsDTO load(String filePath) throws Exception {
        CTEEnigma cteEnigma =readFromXmlFile(filePath);
        Validator xmlReflectorValidator =new XmlReflectorValidator(cteEnigma);
        Validator xmlRotorValidator=new XmlRotorValidator((cteEnigma));
        Validator xmlKeyboardValidator=new XmlKeyboardValidator(cteEnigma);
        List<Validator> validators=new ArrayList<>();
        validators.add(xmlKeyboardValidator);
        validators.add(xmlReflectorValidator);
        validators.add((xmlRotorValidator));
        ValidatorRunner validatorRunner=new ValidatorRunner(validators);
        List<Exception> exceptions=validatorRunner.run();
        if(exceptions.size() == 0){
            menuValidator.reset();
            isCodeConfigurationSet=false;
            amountOfProcessedMessages=0;
            machineHistoryAndStatistics=new MachineHistoryAndStatistics();
            menuValidator.setTrueValueToIsMachineDefined();
            theMachineEngine=buildTheMachineEngine();
       }
          listOfExceptionsDTO =new ListOfExceptionsDTO(exceptions);
          return listOfExceptionsDTO;
    }
    public List<String> getNotchList(){
        return theMachineEngine.getListOfNotch();
    }
    public TheMachineEngine buildTheMachineEngine(){
         schemaGenerated=new SchemaGenerated(cteEnigma);
        TheMachineEngine theMachineEngine= new TheMachineEngine(schemaGenerated.createRotorsSet(),schemaGenerated.createReflectorsSet(),schemaGenerated.createKeyboard(),schemaGenerated.getAmountOfUsedRotors());
        return theMachineEngine;

    }
    public boolean getIsCodeConfigurationSet(){
        return isCodeConfigurationSet;
    }

    public void DefineIsCodeConfigurationSetValueToTrue(){
        this.isCodeConfigurationSet=true;
        menuValidator.setTrueValueToUpdateIsCodeDefined();
        reverseUsedRotors(theMachineEngine);
        createCurrentCodeDescriptionDTO();
        machineHistoryAndStatistics.addNewMachineSettings(codeDescriptionDTO);
    }
    private void reverseUsedRotors(TheMachineEngine theMachineEngine){
        theMachineEngine.reverseUsedRotors();

    }
    public ListOfExceptionsDTO getAllErrorsRelatedToChosenManuallyRotors(String str){
        UserInputRotorsValidator userInputRotorsValidator=new UserInputRotorsValidator(str,theMachineEngine);
        userInputRotorsValidator.validate();
         listOfRotors=new ArrayList<>();
        String[] rotorsId=userInputRotorsValidator.getFilteredUserInput();
          if(rotorsId!=null) {
            for (String rotorId : rotorsId) {
                listOfRotors.add(theMachineEngine.getRotorsSet().getRotorById(rotorId));
            }

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
        reflectorId=userInputReflectorValidator.getReflectorId();
       // chooseManuallyReflect(reflectorId);
        List<Exception> exceptions= userInputReflectorValidator.getListOfException();
        ListOfExceptionsDTO  listOfExceptionsDTO =new ListOfExceptionsDTO(exceptions);
        return listOfExceptionsDTO;
    }
    public ListOfExceptionsDTO getAllErrorsRelatedToChosenManuallyPlugBoard(String str){
        Validator userInputPlugBoardValidator=new UserInputPlugBoardValidator(str,theMachineEngine);
        userInputPlugBoardValidator.validate();
        List<Exception> exceptions= userInputPlugBoardValidator.getListOfException();
        ListOfExceptionsDTO  listOfExceptionsDTO =new ListOfExceptionsDTO(exceptions);
        return listOfExceptionsDTO;
    }
    public void chooseManuallyRotors(){
        if(listOfRotors.size()>0){
            theMachineEngine.createUsedRotorsSet(listOfRotors);
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
    public void chooseManuallyReflect(){
       if(reflectorId!="") {
           theMachineEngine.addSelectedReflector(reflectorId);
       }
    }
    public void chooseManuallyPlugBoard(String userInput){
        if(!userInput.equals("")) {
            userInput = userInput.toUpperCase();
            List<Pair> pairsOfSwappingLetters = new ArrayList<>();
            String firstSignal, secondSignal;
            int amountOfSwappingPairs = userInput.length() / 2;
            if (amountOfSwappingPairs > 0) {
                for (int i = 0; i < userInput.length() - 1; i += 2) {
                    firstSignal = String.valueOf(userInput.charAt(i));
                    secondSignal = String.valueOf(userInput.charAt(i + 1));
                    Pair pair = new Pair(firstSignal, secondSignal);
                    pairsOfSwappingLetters.add(pair);
                }
            }
            PlugsBoard plugsBoard = new PlugsBoard(theMachineEngine.getKeyboard(), pairsOfSwappingLetters);
            theMachineEngine.addPlugsBoardTOTheMachine(plugsBoard);
        }

    }
public void resetPlugBoard(){
    List<Pair> pairsOfSwappingLetters=new ArrayList<>();
    PlugsBoard plugsBoard=new PlugsBoard(theMachineEngine.getKeyboard(),pairsOfSwappingLetters);
    theMachineEngine.addPlugsBoardTOTheMachine(plugsBoard);
}
    public CodeDescriptionDTO getCodeDescriptionDTO() {
        return codeDescriptionDTO;
    }

    public int getRotorsAmount(){
        return cteEnigma.getCTEMachine().getRotorsCount();
    }
    public CodeDescriptionDTO initCodeAutomatically(){
        theMachineEngine = buildTheMachineEngine();
        chooseAutomaticallyRotors(theMachineEngine);
        initRotorsPositionAutomatically(theMachineEngine);
        chooseAutomaticallyReflector(theMachineEngine);
        choosePlugBoardSettings(theMachineEngine);
        isCodeConfigurationSet=true;
        menuValidator.setTrueValueToUpdateIsCodeDefined();
        reverseUsedRotors(theMachineEngine);
        createCurrentCodeDescriptionDTO();
        machineHistoryAndStatistics.addNewMachineSettings(codeDescriptionDTO);
        return codeDescriptionDTO;


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
     public boolean isChooseToExit(String userInput){
        if(userInput.equals("")){
            return true;

        }
        return false;
    }

    public ConvertedStringDTO getConvertedString(String userInputString){
        long begin = System.nanoTime();

        String convertedString="";
        userInputString=userInputString.toUpperCase();
        for (int i=0;i<userInputString.length();i++){
            String userInputByString =String.valueOf(userInputString.charAt(i));
            String convertedCharByString=theMachineEngine.manageDecode(userInputByString);
            convertedString=convertedString.concat(convertedCharByString);
        }
        long end = System.nanoTime();
        amountOfProcessedMessages++;
        ConvertedStringDTO convertedStringDTO=new ConvertedStringDTO(convertedString);
        createCurrentCodeDescriptionDTO();
        machineHistoryAndStatistics.addNewProcess(codeDescriptionDTO,new HistoryOfProcess(userInputString,convertedString,end-begin));

        return convertedStringDTO;
    }
    public List<MachineHistoryAndStatisticsDTO> getHistoryAndStatisticsDTO() {
        List<MachineHistoryAndStatisticsDTO> listOfMachineHistoryAndStatisticsDTO=new ArrayList<>();
        Map<CodeDescriptionDTO, List<HistoryOfProcess>> machineHistory= machineHistoryAndStatistics.getMachineHistory();
         for (Map.Entry<CodeDescriptionDTO,List<HistoryOfProcess>> entry : machineHistory.entrySet()) {
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
    public ListOfExceptionsDTO getAllErrorsRelatedToUserDefinePlugBoard(String userInput){
        menuValidator.setTrueValueToUpdateIsCodeDefined();
        UserInputPlugBoardValidator userInputPlugBoardValidator=new UserInputPlugBoardValidator(userInput,theMachineEngine);
         userInputPlugBoardValidator.isUserChosenInputToDefineAPlugBoardIsValid();
        List<Exception> exceptions= userInputPlugBoardValidator.getListOfException();
        listOfExceptionsDTO =new ListOfExceptionsDTO(exceptions);
        return listOfExceptionsDTO;
    }
        public void resetCurrentCode(){
        theMachineEngine.resetCurrentRotorSetCode();
    }
    public ListOfExceptionsDTO getAllErrorsRelatedToMachineMenuValidator(){
        menuValidator.isXmlLoaded();
     return new ListOfExceptionsDTO(menuValidator.getListOfException());
    }
    public ListOfExceptionsDTO getAllErrorsRelatedToInitCodeMenuValidator(){
        menuValidator.isCodeConfigurationWasDefined();
        return new ListOfExceptionsDTO(menuValidator.getListOfException());
    }
    public TheMachineSettingsDTO getTheMachineSettingsDTO(){
        if(theMachineEngine==null){
            theMachineEngine=buildTheMachineEngine();
        }
        List<String> reflectorsId=theMachineEngine.getReflectorsId();
        int amountOfUsedRotors=theMachineEngine.getAmountOfUsedRotors();
        int maxAmountOfRotors=theMachineEngine.getMaxAmountOfRotors();
       int amountOfReflectors= theMachineEngine.getReflectorsAmount();
       createCurrentCodeDescriptionDTO();
        TheMachineSettingsDTO theMachineSettingsDTO=new TheMachineSettingsDTO(amountOfUsedRotors,maxAmountOfRotors,amountOfReflectors,amountOfProcessedMessages, codeDescriptionDTO,reflectorsId);
        return theMachineSettingsDTO;
    }
    public  void  createCurrentCodeDescriptionDTO(){
        CodeDescriptionDTO codeDescriptionDTO =null;
        if(isCodeConfigurationSet) {
            String[] usedRotorsId = theMachineEngine.getArrayOfRotorsId();
            String currentStartingPosition = theMachineEngine.getUsedRotors().getCurrentRotorsStartingPositions();

            String chosenStartingPosition = theMachineEngine.getUsedRotors().getRotorsOriginalStartingPositions();
            String reflectorId = theMachineEngine.getReflector().getReflectorId();
            List<String> notchPosition=theMachineEngine.getListOfNotch();
            List<String> originalNotchPosition=theMachineEngine.getOriginalNotchPositionList();
            List<String> pairsOfSwappingCharacter = theMachineEngine.getStringPairsOfSwappingCharacter();
          codeDescriptionDTO = new CodeDescriptionDTO(pairsOfSwappingCharacter, reflectorId, chosenStartingPosition,currentStartingPosition, usedRotorsId,originalNotchPosition,notchPosition);
        }
        this.codeDescriptionDTO = codeDescriptionDTO;


    }
    public void updateExceptionListMenuValidator(){
        menuValidator.updateExceptionList();
    }

    public void writeToFile(String FileName) throws IOException {
        ArrayList<EngineManager> meds = new ArrayList();
        meds.add(this);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FileName));
        Throwable var = null;

        try {
            out.writeObject(meds);
            out.flush();
        } catch (Throwable error) {
            var = error;
            throw error;
        } finally {
            if (out != null) {
                if (var != null) {
                    try {
                        out.close();
                    } catch (Throwable error2) {
                        var.addSuppressed(error2);
                    }
                } else {
                    out.close();
                }
            }

        }

    }
    public int getAmountOfUsedRotors(){
        return theMachineEngine.getAmountOfUsedRotors();
    }

    public void readFromFile(String FileName) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(FileName));
        Throwable var = null;
        try {
            ArrayList<EngineManager> meds = (ArrayList)in.readObject();
            this.listOfExceptionsDTO = ((EngineManager)meds.get(0)).listOfExceptionsDTO;
            this.codeDescriptionDTO = ((EngineManager)meds.get(0)).codeDescriptionDTO;
            this.theMachineEngine = ((EngineManager)meds.get(0)).theMachineEngine;
            this.machineHistoryAndStatistics = ((EngineManager)meds.get(0)).machineHistoryAndStatistics;
            this.cteEnigma = ((EngineManager)meds.get(0)).cteEnigma;
            this.schemaGenerated = ((EngineManager)meds.get(0)).schemaGenerated;
            this.isCodeConfigurationSet = ((EngineManager)meds.get(0)).isCodeConfigurationSet;
            this.amountOfProcessedMessages = ((EngineManager)meds.get(0)).amountOfProcessedMessages;
            this.menuValidator = ((EngineManager)meds.get(0)).menuValidator;
            this.listOfRotors=((EngineManager)meds.get(0)).listOfRotors;
            this.reflectorId=((EngineManager)meds.get(0)).reflectorId;

        } catch (Throwable error) {
            var = error;
            throw error;
        } finally {
            if (in != null) {
                if (var != null) {
                    try {
                        in.close();
                    } catch (Throwable error2) {
                        var.addSuppressed(error2);
                    }
                } else {
                    in.close();
                }
            }

        }

    }

}