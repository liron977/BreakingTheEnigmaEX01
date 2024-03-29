package console;

import machineDTO.CodeDescriptionDTO;
import machineDTO.ListOfExceptionsDTO;
import engineManager.EngineManagerInterface;
import machineDTO.MachineHistoryAndStatisticsDTO;
import machineDTO.TheMachineSettingsDTO;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Mediator {
    private EngineManagerInterface engineManager;

public Mediator(EngineManagerInterface engineManager){
    this.engineManager=engineManager;
}

    public boolean fileNameValidation(String str) {

        ListOfExceptionsDTO listOfExceptionsDTO = engineManager.getAllErrorsRelatedToFilePath(str);
        if (listOfExceptionsDTO.getListOfException().size() == 0) {
            return true;
        } else {
            printListOfException(listOfExceptionsDTO.getListOfException());
            return false;
        }
    }
    public boolean isFileLoadSuccessfully(String str) throws Exception {
        ListOfExceptionsDTO listOfExceptionsDTO = engineManager.load(str);
        if (listOfExceptionsDTO.getListOfException().size() == 0) {
            return true;
        } else {
            printListOfException(listOfExceptionsDTO.getListOfException());
              return false;
        }

    }
    private void printListOfException(List<Exception> errors){
        for (Exception exception : errors) {
            System.out.println(exception.getMessage());
            System.out.println("******************");
        }
    }
    public boolean isRotorsIDinInitCodeManuallyIsValid(String str){
            ListOfExceptionsDTO listOfExceptionsDTO = engineManager.getAllErrorsRelatedToChosenManuallyRotors(str);
            if (listOfExceptionsDTO.getListOfException().size() == 0) {
                return true;
            } else {
                printListOfException(listOfExceptionsDTO.getListOfException());
                return false;
            }
    }
    public boolean isChooseToExit(String str) {
    return engineManager.isChooseToExit(str);
    }


        public boolean isStartingPositionInitCodeManuallyIsValid(String str){
        ListOfExceptionsDTO listOfExceptionsDTO= engineManager.getAllErrorsRelatedToChosenManuallyStartingPosition(str);
        if (listOfExceptionsDTO.getListOfException().size() == 0) {
            return true;
        } else {
            printListOfException(listOfExceptionsDTO.getListOfException());
            return false;
        }
    }
    public boolean isReflectoIDinInitCodeManuallyIsValid(String str){
        ListOfExceptionsDTO listOfExceptionsDTO= engineManager.getAllErrorsRelatedToChosenManuallyReflectorId(str);
        if (listOfExceptionsDTO.getListOfException().size() == 0) {
            return true;
        } else {
            printListOfException(listOfExceptionsDTO.getListOfException());
            return false;
        }
    }
    public void saveRotors(){
    engineManager.chooseManuallyRotors();
    }
    public void saveReflector(){
        engineManager.chooseManuallyReflect();
    }
    public boolean isPlagBoardinInitCodeManuallyIsValid(String str){
        ListOfExceptionsDTO listOfExceptionsDTO= engineManager.getAllErrorsRelatedToChosenManuallyPlugBoard(str);
        if (listOfExceptionsDTO.getListOfException().size() == 0) {
            return true;
        } else {
            printListOfException(listOfExceptionsDTO.getListOfException());
            return false;
        }
    }

    public boolean isUserStringToProcessIsValid(String str){
        ListOfExceptionsDTO listOfExceptionsDTO= engineManager.getAllErrorsConvertingInputProcess(str);
        if (listOfExceptionsDTO.getListOfException().size() == 0) {
            return true;
        } else {
            printListOfException(listOfExceptionsDTO.getListOfException());
            return false;
        }
    }
    public String getConvertedString(String userInput){

        return engineManager.getConvertedString(userInput).getConvertedString();

    }
    public void initCodeConfigurationAutomatically(){
       CodeDescriptionDTO codeDescriptionDTO =engineManager.initCodeAutomatically();
       List<String> notchList=engineManager.getNotchList();
        System.out.println("Selection of initial code configuration (automatically) performed successfully");
        System.out.println("The current code configuration is: " +getCurrentCodeDescription(codeDescriptionDTO,notchList,engineManager.getCodeDescriptionDTO().getCurrentStartingPosition()));
    }

    public String getAvailableReflectorsId() throws Exception {
    String availableReflectorsId="";
    List<String> availableReflectorsIdList= engineManager.getTheMachineSettingsDTO().getReflectorsId();
    int index=1;
        for (int i=0;i<availableReflectorsIdList.size();i++){
            index=getReflectorIdIndex(availableReflectorsIdList.get(i));
            availableReflectorsId=availableReflectorsId+index+") "+availableReflectorsIdList.get(i)+"\n";
        }
        return availableReflectorsId;

    }
    private int getReflectorIdIndex(String availableReflectorsId){
          int index=1;
        switch (availableReflectorsId) {
            case "I":
                index = 1;
                break;
            case "II":
                index = 2;
                break;
            case "III":
                index = 3;
                break;
            case "IV":
                index = 4;
                break;
            case "V":
                index = 5;
                break;
        }
        return index;
    }
    public void  initStartingPositionConfigurationManually(String userInput){
        engineManager.chooseManuallyStartingPosition(userInput);
    }
    public void  initPlugBoardConfigurationManually(String userInput){
        engineManager.chooseManuallyPlugBoard(userInput);

    }
public void resetPlugBoard(){
    engineManager.resetPlugBoard();
}
    public void resetCurrentCode(){
        engineManager.resetCurrentCode();
        System.out.println("The reset code succeeded");
        printCodeConfiguration();
    }
    public Boolean isMachineWasDefined(){
        ListOfExceptionsDTO listOfExceptionsDTO=engineManager.getAllErrorsRelatedToMachineMenuValidator();
        if(listOfExceptionsDTO.getListOfException().size()==0){
            return true;
        }
        else {
            printListOfException(listOfExceptionsDTO.getListOfException());
            engineManager.updateExceptionListMenuValidator();
            return false;
        }
    }
    public Boolean isCodeWasDefined(){
        ListOfExceptionsDTO listOfExceptionsDTO=engineManager.getAllErrorsRelatedToInitCodeMenuValidator();
        if(listOfExceptionsDTO.getListOfException().size()==0){
            return true;
        }
        else {
            printListOfException(listOfExceptionsDTO.getListOfException());
            engineManager.updateExceptionListMenuValidator();
            return false;
        }
    }
    public String getMachineConfiguration() throws Exception{
        engineManager.createCurrentCodeDescriptionDTO();
        if(isMachineWasDefined()) {
                TheMachineSettingsDTO theMachineSettingsDTO = engineManager.getTheMachineSettingsDTO();
                String machineConfiguration = "Current machine Configurations:\n";
                machineConfiguration = machineConfiguration + "1.Amount of wheels in use out of possible amount of wheels: " + theMachineSettingsDTO.getAmountOfUsedRotors() + "\\" + theMachineSettingsDTO.getMaxAmountOfRotors() + "\n";
                machineConfiguration = machineConfiguration + "2.The amount of reflectors is: " + theMachineSettingsDTO.getAmountOfReflectors() + "\n";
                machineConfiguration = machineConfiguration + "3.The current amount of proceeded messages: " + theMachineSettingsDTO.getAmountOfProcessedMessages() + "\n";
                if (engineManager.getIsCodeConfigurationSet()) {
                    machineConfiguration = machineConfiguration + "4.The original code description: \n" + getCurrentCodeDescription(theMachineSettingsDTO.getCurrentCodeDescriptionDTO(), theMachineSettingsDTO.getCurrentCodeDescriptionDTO().getOriginalNotchPosition(), engineManager.getCodeDescriptionDTO().getChosenStartingPosition()) + "\n";
                    machineConfiguration = machineConfiguration + "5.The current code description: \n" + getCurrentCodeDescription(theMachineSettingsDTO.getCurrentCodeDescriptionDTO(), theMachineSettingsDTO.getCurrentCodeDescriptionDTO().getNotchPosition(), engineManager.getCodeDescriptionDTO().getCurrentStartingPosition()) + "\n";
                }
                return machineConfiguration;
            }


        return null;
    }

public void printCodeConfiguration(){
    List<String> notchList=engineManager.getNotchList();
    engineManager.createCurrentCodeDescriptionDTO();
    System.out.println("The current code configuration is: " +getCurrentCodeDescription(engineManager.getCodeDescriptionDTO(),notchList,engineManager.getCodeDescriptionDTO().getCurrentStartingPosition()));

}
    public String getCurrentCodeDescription(CodeDescriptionDTO codeDescriptionDTO, List<String> notchPosition,String startingPosition){
        String currentCodeDescription="";
        currentCodeDescription=currentCodeDescription+"<"+getRotorsInfo(codeDescriptionDTO.getUsedRotorsId())+">";
        StringBuilder startingPositionRevers = new StringBuilder();
        startingPositionRevers.append(startingPosition);
        startingPositionRevers.reverse();
        currentCodeDescription=currentCodeDescription+"<"+ getWindowInfoId(startingPositionRevers,notchPosition)+">";
        currentCodeDescription=currentCodeDescription+"<"+ codeDescriptionDTO.getReflectorId()+">";
        List<String> pairsOfSwappingCharacter = codeDescriptionDTO.getPairsOfSwappingCharacter();
        if((pairsOfSwappingCharacter!=null)&&(pairsOfSwappingCharacter.size()!=0)) {
            currentCodeDescription=currentCodeDescription+"<" + getPairsOfSwappingCharacter(codeDescriptionDTO.getPairsOfSwappingCharacter()) + ">";
        }
        return currentCodeDescription;

    }
    public void getHistoryAndStatistics(){
       List<MachineHistoryAndStatisticsDTO> listOfMachineHistory= engineManager.getHistoryAndStatisticsDTO();
        for (MachineHistoryAndStatisticsDTO machineHistory:listOfMachineHistory) {
            if(machineHistory.getCurrentCodeDescriptionDTO()!=null) {
                System.out.printf("The strings that proceeded for "+getCurrentCodeDescription(machineHistory.getCurrentCodeDescriptionDTO(),machineHistory.getCurrentCodeDescriptionDTO().getOriginalNotchPosition(),machineHistory.getCurrentCodeDescriptionDTO().getChosenStartingPosition()) + " are :\n");
                String[] userInput = machineHistory.getHistoryAndStatisticsDTO().getUserInput();
                String[] convertedStrings = machineHistory.getHistoryAndStatisticsDTO().getConvertedString();
                String[] timeToProcess = machineHistory.getHistoryAndStatisticsDTO().getTimeToProcess();
                if(userInput.length==0) {
                    System.out.println("none");
                }
            else {
                for (int i = 0; i < userInput.length; i++) {
                    System.out.println((i+1)+".<" + userInput[i] + "> --> <" + convertedStrings[i] + "> (" + timeToProcess[i] + " nano-seconds)");
                }
            }
            }
        }
    }
    public String getPairsOfSwappingCharacter(List<String> pairsOfSwappingCharacter){
        String seperatedPairsOfSwappingCharacter="";
        for (String pairOfSwappingCharacter:pairsOfSwappingCharacter) {
            seperatedPairsOfSwappingCharacter=seperatedPairsOfSwappingCharacter+pairOfSwappingCharacter.charAt(3)+"|"+pairOfSwappingCharacter.charAt(1);
            seperatedPairsOfSwappingCharacter=seperatedPairsOfSwappingCharacter+",";
        }
        seperatedPairsOfSwappingCharacter=seperatedPairsOfSwappingCharacter.substring(0,seperatedPairsOfSwappingCharacter.length()-1);
        return seperatedPairsOfSwappingCharacter;


    }

    public String getWindowInfoId(StringBuilder startingPositionRevers, List<String> notchPosition) {
        String usedRotors="";
        String windowInfo="";
        int index=0;
       Collections.reverse(notchPosition);
        for (int i=0;i<startingPositionRevers.length();i++){
            windowInfo=windowInfo+startingPositionRevers.charAt(i)+"("+notchPosition.get(index)+")";

            index++;
        }

        Collections.reverse(notchPosition);
        return windowInfo;

    }
    public String getRotorsInfo(String[] rotors){
    /*    List<String> rotorsInfo= getUsedRotorsId(usedRotorsId,notchPosition);*/
    Collections.reverse(Arrays.asList(rotors));
        String rotor="";
        for (int i=0;i<rotors.length;i++) {

                rotor=rotor+rotors[i]+",";

        }
        rotor=rotor.substring(0,rotor.length()-1);
        Collections.reverse(Arrays.asList(rotors));
        return rotor;

    }

        public String getNotchPositionByIndex(List<String> notchPositions){
        String notchPositionByPairs="";
        for (String notchPair:notchPositions) {
            notchPositionByPairs=notchPositionByPairs+"The notch position for rotor id " +notchPair.charAt(0)+" is : "+notchPair.substring(1,notchPair.length()) +"\n";

        }
        return notchPositionByPairs;
    }

    public void setIsCodeConfigurationWasdefine() {
        this.engineManager.DefineIsCodeConfigurationSetValueToTrue();
    }

    public Boolean isPlayerDefinePlugBoardIsValid(String str){
        ListOfExceptionsDTO listOfExceptionsDTO= engineManager.getAllErrorsRelatedToUserDefinePlugBoard(str);
        if (listOfExceptionsDTO.getListOfException().size() == 0) {
            return true;
        } else {
            printListOfException(listOfExceptionsDTO.getListOfException());
            return false;
        }
    }
    public void writeCurrentStateToFile(String fileName){
        try {
            engineManager.writeToFile(fileName);
        } catch (IOException ignore) {
            System.out.println("Error! can not write to the file\n");
            return;
        }
        System.out.println("Saved successfully!\n");
    }
    public void readCurrentStateFromFile(String fileName) {
            File f = new File(fileName);
            if (!f.exists()) {
                System.out.println("\nthe file full path name does not exist!\nPlease try again\n");
                return;
            }
            try {
                engineManager.readFromFile(fileName);
            } catch (IOException | ClassNotFoundException ignore) {
                System.out.println("Error! can not load from a file\n");
                return;
            }
            System.out.println("Load successfully!\n");
    }
    public int getAmountOfUsedRotors(){
    return engineManager.getAmountOfUsedRotors();
    }

}