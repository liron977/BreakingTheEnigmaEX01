package MachineDTO;

import java.util.List;

public class MachineDTO {

    private List<Exception> errors;
    int rotorsAmount;
    String[] rotorsId;
    String[] reflectorId;
    String keyboard;
    int useRotorsAmount;
    String notchPosition;

    public MachineDTO(List<Exception> errors,int rotorsAmount,String[] rotorsId,String[] reflectorId,String keyboard,int useRotorsAmount,String notchPosition){
        this.errors=errors;
        this.rotorsAmount=rotorsAmount;
        this.rotorsId=rotorsId;
        this.reflectorId=reflectorId;
        this.keyboard=keyboard;
        this.notchPosition=notchPosition;
        this.useRotorsAmount=rotorsAmount;

    }
    public List<Exception> getListOfException(){
        return errors;
    }
    public int getRotorsAmount(){
        return rotorsAmount;
    }
    public String[] getRotorsId(){
        return rotorsId;
    }
    public String[] getReflectorId(){
        return reflectorId;
    }
    public String getKeyboard(){
        return keyboard;
    }
    public String getNotchPosition(){
        return notchPosition;
    }
    public String getUseRotorsAmount(){
        return notchPosition;
    }
}
