import java.util.ArrayList;
import java.util.List;

public class Reflector {
    private String reflectorId;
    private List<Pair> reflectorPairStructure=new ArrayList<>();
    private List<Character> reflectorStructure=new ArrayList<>();
    public Reflector(String reflectorId,List<Pair> reflectorPairStructure ){
        this.reflectorId=reflectorId;
        this.reflectorPairStructure=reflectorPairStructure;
        initReflector();
        updateReflector();
    }
    public List<Character> getReflectorStructure() {
       return reflectorStructure;
    }
    public void updateReflector(){
        char originalInputValue;
        char originalOutputValue;
        int newInputIndex;
        int newExitIndex;
        for(int i=0;i<reflectorPairStructure.size();i++){
            originalInputValue=this.reflectorPairStructure.get(i).getEntry();
            originalOutputValue=this.reflectorPairStructure.get(i).getExit();
            newInputIndex=Character.getNumericValue(originalOutputValue)-1;
            newExitIndex=Character.getNumericValue(originalInputValue)-1 ;
            reflectorStructure.set(newInputIndex,originalInputValue);
            reflectorStructure.set(newExitIndex,originalInputValue);
        }
    }
    private void initReflector(){
        for (int i=0;i<reflectorPairStructure.size()*2;i++) {
          Character character='1';
            reflectorStructure.add(character);

        }
    }
    public int getExitIndexFromTheReflector(int entryIndex) {
        char valueOfEntryIndex=reflectorStructure.get(entryIndex);
        for(int i=0;i<reflectorStructure.size();i++) {
            if((i!=(entryIndex))&&(reflectorStructure.get(i)==valueOfEntryIndex)){
                return i;
            }
        }
        return -1;
    }
}
