package Engine.validator;

import Engine.TheEnigmaEngine.Pair;
import Engine.TheEnigmaEngine.TheMachineEngine;
import schemaGenerated.CTEEnigma;

import java.util.ArrayList;
import java.util.List;

public class UserInputReflectorValidator implements Validator{
    private List<Exception> listOfException;
    private char[] userInput;
    private CTEEnigma cteEnigma;

    private TheMachineEngine theMachineEngine;
    private String reflectorId;

    public UserInputReflectorValidator(String userInput, CTEEnigma cteEnigma, TheMachineEngine theMachineEngine) {
        this.cteEnigma = cteEnigma;
        this.listOfException = new ArrayList<>();
        this.userInput = new char[userInput.length()];
        for (int i = 0; i < userInput.length(); i++) {
            this.userInput[i] = userInput.charAt(i);
        }
        this.theMachineEngine = theMachineEngine;

    }
    private void isReflectorIdValid(){

        if(!theMachineEngine.getReflectorsSet().searchReflectorById(reflectorId)){
            listOfException.add(new Exception("The reflector ID is not exist in the file"));
        }
        else {
            char[] tmp=reflectorId.trim().toCharArray();
            for(int i=0;i<reflectorId.trim().length();i++){
                if((tmp[i]!='I')&&(tmp[i]!='V')){
                    listOfException.add(new Exception("The reflector ID is not legal,you need to enter one option of {I,II,III,IV,V}"));
                }
            }

        }
    }

    private char[] deepCopy() {
        char[] tmp = new char[userInput.length];
        int countOfOpener = 0, countOfBrackets = 0;
        for (int i = 0; i < userInput.length; i++) {
            tmp[i] = userInput[i];
        }
        return tmp;
    }
    @Override
    public void validate() {
        splitTheUserInput();
        isReflectorIdValid();
    }

    @Override
    public List<Exception> getListOfException() {
        return listOfException;
    }
    private void splitTheUserInput() {
        char[] tmp = deepCopy();
        char[] sub = new char[tmp.length];
        int counterOfOpenBrackets = 0;
        int j = 0;
        boolean flag = false;
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] == '<') {
                counterOfOpenBrackets ++;
                i++;
                 if(counterOfOpenBrackets==3){
                    char[]  reflectorIdTmp = new char[tmp.length];
                    j=0;
                    while (tmp[i] != '>') {
                        reflectorIdTmp[j] = tmp[i];
                        j++;
                        i++;
                    }
                    reflectorId=String.valueOf(reflectorIdTmp).trim().toUpperCase();
                }
            }
        }
    }
    public String getReflectorId(){
        return reflectorId;
    }
}
