package Console;

import EngineManager.EngineManager;
import MachineDTO.MachineDTO;

import java.util.List;
import java.util.Scanner;

public class UserConsole {

EngineManager engineManager;

public UserConsole (EngineManager engineManager){
    this.engineManager=engineManager;
}

    public boolean fileNameValidation(String str) {

        MachineDTO machineDTO = engineManager.getAllErrorsRelatedToFilePath(str);
        if (machineDTO.getListOfException().size() == 0) {
            return true;
        } else {
            printListOfException(machineDTO.getListOfException());
            return false;
        }
    }
    public boolean isFileLoadSuccessfully(String str) throws Exception {
        MachineDTO machineDTO = engineManager.load(str);
        if (machineDTO.getListOfException().size() == 0) {
            return true;
        } else {
            printListOfException(machineDTO.getListOfException());
              return false;
        }

    }
    private void printListOfException(List<Exception> errors){
        for (Exception exception : errors) {
            System.out.println(exception.getMessage());
            System.out.println("******************");
        }
    }
}

