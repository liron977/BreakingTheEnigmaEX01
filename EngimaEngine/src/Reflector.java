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
        char newInputEntry;
        char inputExit;
        int newInputIndex;
        int newExitIndex;
        for(int i=0;i<reflectorPairStructure.size();i++){
            newInputEntry=this.reflectorPairStructure.get(i).getEntry();
            inputExit=this.reflectorPairStructure.get(i).getExit();
            newInputIndex=Character.getNumericValue(inputExit)-1;
            newExitIndex=Character.getNumericValue(newInputEntry)-1 ;
            reflectorStructure.set(newInputIndex,newInputEntry);
            reflectorStructure.set(newExitIndex,newInputEntry);
        }
    }
    private void initReflector(){
        for (int i=0;i<reflectorPairStructure.size()*2;i++) {
          Character character='1';
            reflectorStructure.add(character);

        }
    }
}
