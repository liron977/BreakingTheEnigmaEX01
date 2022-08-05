package MachineDTO;

import java.util.ArrayList;
import java.util.List;

public class CurrentCodeDescriptionDTO {
    private String[] usedRotorsId;
    private String chosenStartingPosition;
    private String reflectorId;
    private List<String> pairsOfSwappingCharacter=new ArrayList<>();

    public CurrentCodeDescriptionDTO(List<String> pairsOfSwappingCharacter,String reflectorId,String chosenStartingPosition,String[] usedRotorsId){
this.pairsOfSwappingCharacter=pairsOfSwappingCharacter;
this.reflectorId=reflectorId;
this.chosenStartingPosition=chosenStartingPosition;
this.usedRotorsId=usedRotorsId;
    }

    public List<String> getPairsOfSwappingCharacter() {
        return pairsOfSwappingCharacter;
    }

    public String getChosenStartingPosition() {
        return chosenStartingPosition;
    }

    public String getReflectorId() {
        return reflectorId;
    }

    public String[] getUsedRotorsId() {
        return usedRotorsId;
    }
}