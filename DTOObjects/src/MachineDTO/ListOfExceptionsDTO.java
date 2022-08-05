package MachineDTO;

import java.util.List;

public class ListOfExceptionsDTO {

    private List<Exception> errors;


    public ListOfExceptionsDTO(List<Exception> errors){
        this.errors=errors;

    }
    public List<Exception> getListOfException(){
        return errors;
    }

}
