package Console;

import MachineDTO.ListOfExceptionsDTO;
import EngineManager.EngineManagerInterface;

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
    public void InitCodeConfigurationAutomatically(){
      //  FileDTO fileDTO=engineManager.initCodeAutomatically();
        engineManager.initCodeAutomatically();
        System.out.println("VALID");


    }
    public void resetCurrentCode(){
        engineManager.resetCurrentCode();
        System.out.println("The reset code succeeded");
    }

}