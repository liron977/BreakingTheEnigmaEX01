package Console;

import EngineManager.EngineManager;
import MachineDTO.FileDTO;
import MachineDTO.MachineDTO;

import java.util.List;

public class UserConsole {

EngineManager engineManager;

public UserConsole (EngineManager engineManager){
    this.engineManager=engineManager;
}

    public boolean fileNameValidation(String str) {

        FileDTO fileDTO = engineManager.getAllErrorsRelatedToFilePath(str);
        if (fileDTO.getListOfException().size() == 0) {
            return true;
        } else {
            printListOfException(fileDTO.getListOfException());
            return false;
        }
    }
    public boolean isFileLoadSuccessfully(String str) throws Exception {
        FileDTO fileDTO = engineManager.load(str);
        if (fileDTO.getListOfException().size() == 0) {
            return true;
        } else {
            printListOfException(fileDTO.getListOfException());
              return false;
        }

    }
    private void printListOfException(List<Exception> errors){
        for (Exception exception : errors) {
            System.out.println(exception.getMessage());
            System.out.println("******************");
        }
    }
    public boolean isInputStructure(String str){
    MachineDTO machineDTO= engineManager.getAllErrorsRelatedToinitCodeConfigurationManually(str);
        if (machineDTO.getListOfException().size() == 0) {
            return true;
        } else {
            printListOfException(machineDTO.getListOfException());
            return false;
        }
    }
    public boolean userInputValidation(String str) {

        MachineDTO machineDTO = engineManager.getAllErrorsRelatedToinitCodeConfigurationManually(str);
        if (machineDTO.getListOfException().size() == 0) {
            return true;
        } else {
            printListOfException(machineDTO.getListOfException());
            return false;
        }
    }
  /*  public boolean isInputValid(MachineDTO machineDTO){


    }*/
}

