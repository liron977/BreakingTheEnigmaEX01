import java.util.ArrayList;
import java.util.List;

public class Reflector {
    private String reflectorId;
    List<Pair> reflectorPairStructure=new ArrayList<>();
    List<Character> reflectorStructure=new ArrayList<>();
    public Reflector(String reflectorId,List<Pair> reflectorPairStructure ){
        this.reflectorId=reflectorId;
        this.reflectorPairStructure=reflectorPairStructure;
    }
    private void initReflector(){
        int sizeOfReflectorStructure=reflectorStructure.size();
        char newInputEntry,char inputExit;
        int newInputIndex;
        int newExitIndex;
        for(int i=0;i<sizeOfReflectorStructure/2;i++){
            newInputEntry=this.reflectorPairStructure.get(i).getEntry();
            inputExit=this.reflectorPairStructure.get(i).getExit();
            newInputIndex=Character.getNumericValue(inputExit);
            newExitIndex=Character.getNumericValue(newInputEntry);
            reflectorStructure.set(newInputIndex,newInputEntry);
            reflectorStructure.set(newExitIndex,newInputEntry);
        }
    }
}
