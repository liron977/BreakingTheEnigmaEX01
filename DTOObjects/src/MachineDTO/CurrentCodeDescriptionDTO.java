package MachineDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CurrentCodeDescriptionDTO implements Serializable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentCodeDescriptionDTO that = (CurrentCodeDescriptionDTO) o;
        return Arrays.equals(usedRotorsId, that.usedRotorsId) && Objects.equals(chosenStartingPosition, that.chosenStartingPosition) && Objects.equals(reflectorId, that.reflectorId) && Objects.equals(pairsOfSwappingCharacter, that.pairsOfSwappingCharacter);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(chosenStartingPosition, reflectorId, pairsOfSwappingCharacter);
        result = 31 * result + Arrays.hashCode(usedRotorsId);
        return result;
    }
}