package MachineDTO;

import java.util.List;

public class MachineDTO {

    private List<Exception> errors;
   // private TheMachineEngine theMachineEngine;


    public MachineDTO(List<Exception> errors){
        this.errors=errors;

    }
    public List<Exception> getListOfException(){
        return errors;
    }
}
