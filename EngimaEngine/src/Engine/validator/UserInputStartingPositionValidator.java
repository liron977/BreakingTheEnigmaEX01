package Engine.validator;
import Engine.TheEnigmaEngine.Pair;
import Engine.TheEnigmaEngine.TheMachineEngine;
import java.util.ArrayList;
import java.util.List;

public class UserInputStartingPositionValidator implements Validator{

    private List<Exception> listOfException;
    private TheMachineEngine theMachineEngine;
    private String rotorsPosition;
    private List<Pair> pairsOfSwappingCharacter = new ArrayList<>();
    public UserInputStartingPositionValidator(String rotorsPosition,TheMachineEngine theMachineEngine) {
        this.listOfException = new ArrayList<>();
        this.theMachineEngine = theMachineEngine;
        this.rotorsPosition=rotorsPosition.toUpperCase();

    }
    public void isStartingPositionStructureIsValid() {
        char signal;
        for (int i = 0; i < rotorsPosition.length(); i++) {
            signal=rotorsPosition.charAt(i);
            if(!theMachineEngine.getKeyboard().contains(String.valueOf(signal))){
                listOfException.add(new Exception("The rotors position must be signal from the machine keyboard,the signal ["+signal+"] is not valid"));
            }
        }
    }
    private void isRototsPositionAmountIsValid(){
        int amountOfUsedRotors= theMachineEngine.getAmountOfUsedRotors();
        if(rotorsPosition.length()>amountOfUsedRotors){
            listOfException.add(new Exception("You have too much signal for stating position,you need to have ["+amountOfUsedRotors+"] signals"));
        }
        else if(rotorsPosition.length()<amountOfUsedRotors){
            listOfException.add(new Exception("You need to insert amount of starting position as the amount of rotors,you need to have ["+amountOfUsedRotors+"] signals"));
        }
    }
    @Override
    public void validate() {
        isStartingPositionStructureIsValid();
        isRototsPositionAmountIsValid();
    }
    @Override
    public List<Exception> getListOfException() {
        return listOfException;
    }
}
