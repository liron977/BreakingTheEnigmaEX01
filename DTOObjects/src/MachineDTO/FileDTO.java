package MachineDTO;

import java.util.List;

public class FileDTO {

    private List<Exception> errors;


    public FileDTO(List<Exception> errors){
        this.errors=errors;

    }
    public List<Exception> getListOfException(){
        return errors;
    }

}
