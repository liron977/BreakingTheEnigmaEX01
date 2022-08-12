package machineDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CodeDescriptionDTO implements Serializable {
    private String[] usedRotorsId;
    private String chosenStartingPosition;
    private String reflectorId;
    private List<String> pairsOfSwappingCharacter=new ArrayList<>();
    private List<String> notchPosition=new ArrayList<>();
    private  List<String> originalNotchPosition=new ArrayList<>();


    public CodeDescriptionDTO(List<String> pairsOfSwappingCharacter, String reflectorId, String chosenStartingPosition, String[] usedRotorsId, List<String> originalNotchPosition, List<String> notchPosition){
this.pairsOfSwappingCharacter=pairsOfSwappingCharacter;
this.reflectorId=reflectorId;
this.chosenStartingPosition=chosenStartingPosition;
this.usedRotorsId=usedRotorsId;
this.originalNotchPosition=originalNotchPosition;
this.notchPosition=notchPosition;
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
    public List<String> getNotchPosition() {
        return notchPosition;
    }

    public List<String> getOriginalNotchPosition(){return originalNotchPosition;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodeDescriptionDTO that = (CodeDescriptionDTO) o;
        return Arrays.equals(usedRotorsId, that.usedRotorsId) && Objects.equals(chosenStartingPosition, that.chosenStartingPosition) && Objects.equals(reflectorId, that.reflectorId) && Objects.equals(pairsOfSwappingCharacter, that.pairsOfSwappingCharacter) && Objects.equals(notchPosition, that.notchPosition) && Objects.equals(originalNotchPosition, that.originalNotchPosition);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(chosenStartingPosition, reflectorId, pairsOfSwappingCharacter);
        result = 31 * result + Arrays.hashCode(usedRotorsId);
        return result;
    }
}