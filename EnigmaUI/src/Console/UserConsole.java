package Console;

import MachineDTO.CurrentCodeDescriptionDTO;
import MachineDTO.ListOfExceptionsDTO;
import EngineManager.EngineManagerInterface;
import MachineDTO.MachineHistoryAndStatisticsDTO;
import MachineDTO.TheMachineSettingsDTO;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UserConsole {

    EngineManagerInterface engineManager;

public UserConsole (EngineManagerInterface engineManager){
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
    public boolean isInitCodeManuallyStructureIsValid(String str){
    ListOfExceptionsDTO listOfExceptionsDTO= engineManager.getAllErrorsRelatedToInitCodeManuallyInputStructure(str);
        if (listOfExceptionsDTO.getListOfException().size() == 0) {
            return true;
        } else {
            printListOfException(listOfExceptionsDTO.getListOfException());
            return false;
        }
    }
    public boolean isRotorsIDinInitCodeManuallyIsValid(String str){
        ListOfExceptionsDTO listOfExceptionsDTO= engineManager.getAllErrorsRelatedToChosenManuallyRotors(str);
        if (listOfExceptionsDTO.getListOfException().size() == 0) {
            return true;
        } else {
            printListOfException(listOfExceptionsDTO.getListOfException());
            return false;
        }
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
    public boolean isPlagBoardinInitCodeManuallyIsValid(String str){
        ListOfExceptionsDTO listOfExceptionsDTO= engineManager.getAllErrorsRelatedToChosenManuallyPlagBoard(str);
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
/*    public boolean userInputValidation(String str) {

        MachineDTO machineDTO = engineManager.getAllErrorsRelatedToinitCodeManuallyInputStructure(str);
        if (machineDTO.getListOfException().size() == 0) {
            return true;
        } else {
            printListOfException(machineDTO.getListOfException());
            return false;
        }
    }*/
    public void initCodeConfigurationAutomatically(){
      //  FileDTO fileDTO=engineManager.initCodeAutomatically();
        engineManager.initCodeAutomatically();
        System.out.println("4. Selection of initial code configuration (automatically) performed successfully");


    }
    public void  initStartingPositionConfigurationManually(String userInput){
        engineManager.chooseManuallyStartingPosition(userInput);
    }
    public void  initPlugBoardConfigurationManually(String userInput){
        engineManager.chooseManuallyPlugBoard(userInput);
    }
    public void resetCurrentCode(){
        engineManager.resetCurrentCode();
        System.out.println("The reset code succeeded");
    }

    //The second way
    public String getCurrentCodeConfigurations(){
        int numberOfConfigurations=1;
        TheMachineSettingsDTO theMachineSettingsDTO=engineManager.getTheMachineSettingsDTO();
        String currentCodeConfigurations="Current Code Configurations:\n";
        currentCodeConfigurations=currentCodeConfigurations+"1.Amount of wheels in use out of possible amount of wheels: "+theMachineSettingsDTO.getAmountOfUsedRotors()+"\\"+theMachineSettingsDTO.getMaxAmountOfRotors()+"\n";
        currentCodeConfigurations=currentCodeConfigurations+"2.The notch positions for each rotor: \n"+getNotchPositionByPairs(theMachineSettingsDTO.getNotchPosition());
        currentCodeConfigurations=currentCodeConfigurations+"3.The amount of reflectors is: "+theMachineSettingsDTO.getAmountOfReflectors()+"\n";
        currentCodeConfigurations=currentCodeConfigurations+"4.The current amount of proceeded messages: "+theMachineSettingsDTO.getAmountOfProcessedMessages()+"\n";
        if(engineManager.getIsCodeConfigurationSet()) {
            currentCodeConfigurations = currentCodeConfigurations + "5.The current code description: \n" + getCurrentCodeDescription(theMachineSettingsDTO.getCurrentCodeDescriptionDTO()) + "\n";
        }
        return currentCodeConfigurations;

    }


    public String getCurrentCodeDescription(CurrentCodeDescriptionDTO currentCodeDescriptionDTO){
        String currentCodeDescription="";
        currentCodeDescription=currentCodeDescription+"<"+getUsedRotorsId(currentCodeDescriptionDTO.getUsedRotorsId())+">";
        currentCodeDescription=currentCodeDescription+"<"+currentCodeDescriptionDTO.getChosenStartingPosition()+">";
        currentCodeDescription=currentCodeDescription+"<"+currentCodeDescriptionDTO.getReflectorId()+">";
        List<String> pairsOfSwappingCharacter =currentCodeDescriptionDTO.getPairsOfSwappingCharacter();
        if((pairsOfSwappingCharacter!=null)&&(pairsOfSwappingCharacter.size()!=0)) {
            currentCodeDescription=currentCodeDescription+"<" + getPairsOfSwappingCharacter(currentCodeDescriptionDTO.getPairsOfSwappingCharacter()) + ">";
        }
        return currentCodeDescription;


    }
    public void getHistoryAndStatistics(){
       List<MachineHistoryAndStatisticsDTO> listOfMachineHistory= engineManager.getHistoryAndStatisticsDTO();
        for (MachineHistoryAndStatisticsDTO machineHistory:listOfMachineHistory) {
            if(machineHistory.getCurrentCodeDescriptionDTO()!=null) {
                System.out.printf("The strings that proceeded for "+getCurrentCodeDescription(machineHistory.getCurrentCodeDescriptionDTO()) + " are :\n");
                String[] userInput = machineHistory.getHistoryAndStatisticsDTO().getUserInput();
                String[] convertedStrings = machineHistory.getHistoryAndStatisticsDTO().getConvertedString();
                String[] timeToProcess = machineHistory.getHistoryAndStatisticsDTO().getTimeToProcess();
                if(userInput.length==0) {
                    System.out.println("none");
                }
            else {
                for (int i = 0; i < userInput.length; i++) {
                    System.out.println("<" + userInput[i] + "> --> <" + convertedStrings[i] + "> (" + timeToProcess[i] + " nano-seconds)");
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

    public String getUsedRotorsId(String[] usedRotorsId) {
        String usedRotors="";
        //Collections.reverse(Arrays.asList(usedRotorsId));
        for (String RotorId:usedRotorsId) {

            usedRotors=usedRotors+RotorId+",";
        }
        usedRotors=usedRotors.substring(0,usedRotors.length()-1);

        StringBuffer userReversedRotors = new StringBuffer(usedRotors);
        // To reverse the string
        userReversedRotors.reverse();
        return userReversedRotors.toString();

    }

        public String getNotchPositionByPairs(List<String> notchPositions){
        String notchPositionByPairs="";
        for (String notchPair:notchPositions) {
            notchPositionByPairs=notchPositionByPairs+"The notch position for rotor id " +notchPair.charAt(0)+" is : "+notchPair.substring(1,notchPair.length());

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

}