package Engine.validator;

import Engine.TheEnigmaEngine.Pair;
import Engine.TheEnigmaEngine.TheMachineEngine;
import schemaGenerated.CTEEnigma;

import java.util.ArrayList;
import java.util.List;

public class UserInputRotorsValidator implements Validator {
    private char[] userInput;
    private List<Exception> listOfException;
    private CTEEnigma cteEnigma;
    private TheMachineEngine theMachineEngine;
    private String[] rotorsId;
    private List<Pair> pairsOfSwappingCharacter = new ArrayList<>();

    public UserInputRotorsValidator(String userInput, CTEEnigma cteEnigma, TheMachineEngine theMachineEngine) {
        this.cteEnigma = cteEnigma;
        this.listOfException = new ArrayList<>();
        this.userInput = new char[userInput.length()];
        for (int i = 0; i < userInput.length(); i++) {
            this.userInput[i] = userInput.charAt(i);
        }
        this.theMachineEngine = theMachineEngine;

    }
    private char[] deepCopy() {
        char[] tmp = new char[userInput.length];
        int countOfOpener = 0, countOfBrackets = 0;
        for (int i = 0; i < userInput.length; i++) {
            tmp[i] = userInput[i];
        }
        return tmp;
    }

    public void splitTheUserInputToGetTheRotorsId(){
    char[] tmp = deepCopy();
    char[] sub = new char[tmp.length];
    int j = 0;
    int i=0;
    boolean flag = false;
      while (i<tmp.length) {
        if (tmp[i] == '<') {
            i++;
             {
                 while (tmp[i] != '>') {
                    if ((!Character.isDigit(tmp[i])) && (tmp[i] != ',')) {
                        listOfException.add(new Exception("The input is not valid,in the first <> you need to enter numbers separated by a comma"));
                    }
                    sub[j] = tmp[i];
                    j++;
                    i++;
                }
                 String subString = String.valueOf(sub);
                 rotorsId = (subString.split(","));
                 for (int ix = 0; ix < rotorsId.length; ix++) {
                     rotorsId[ix] = rotorsId[ix].trim().toUpperCase();
                 }
                 break;
            }
        }
    }
}
    private boolean isRotorsIdFromUserInputIsValid(){
        int numberOfRotorsFromTheFile=cteEnigma.getCTEMachine().getRotorsCount();
        if(rotorsId.length>numberOfRotorsFromTheFile){
            listOfException.add(new Exception("The number of rotors you enter is more of the rotors amount exist in the machine,you can insert ["+numberOfRotorsFromTheFile+"] for maximum"));
            return false;
        }
        else if(rotorsId.length==0){
            listOfException.add(new Exception("You didn`t enter rotors id,you can insert for maximum ["+numberOfRotorsFromTheFile+"]"));
            return false;
        }
        else {
            for (String rotorsId : rotorsId) {
                if (!theMachineEngine.getRotorsSet().isRotorsIdExists(rotorsId)) {
                    listOfException.add(new Exception("The rotor id [" + rotorsId + "] does not exists in the machine"));
                    return false;
                }
            }
        }
        return true;
    }
    private void isRotorsIdIsANumber(){
        boolean isNumeric;
        for (String id:rotorsId) {
            isNumeric = id.chars().allMatch( Character::isDigit );
            if(!isNumeric){
                listOfException.add(new Exception("The rotors id can be numbers only,the rotor id: [" +id +"] is not valid"));
            }
        }
    }
    private void isRotorIDIsUniq(){
        for(int i=0;i<rotorsId.length;i++) {
            String str=rotorsId[i];
            for(int j=i+1;j<rotorsId.length;j++){
                if(str.equals(rotorsId[j])){
                    listOfException.add(new Exception("You insert the same rotors id ["+str.toString()+"]"));
                    break;
                }
            }
        }
    }
    public  String[] getFilteredUserInput(){
        return rotorsId;
    }
    @Override
    public void validate() {
        splitTheUserInputToGetTheRotorsId();
        isRotorsIdFromUserInputIsValid();
        isRotorIDIsUniq();
        isRotorsIdIsANumber();
    }

    @Override
    public List<Exception> getListOfException() {
        return listOfException;
    }
}
