package MachineDTO;

import java.security.PublicKey;
import java.util.List;

public class MachineDTO {

    private List<Exception> errors;


    public MachineDTO( List<Exception> errors){
        this.errors=errors;

    }
    public List<Exception> getListOfException(){
        return errors;
    }

}
