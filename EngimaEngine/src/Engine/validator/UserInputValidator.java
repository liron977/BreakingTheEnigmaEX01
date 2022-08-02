package Engine.validator;

import Engine.TheEnigmaEngine.Pair;
import Engine.TheEnigmaEngine.Rotor;
import Engine.TheEnigmaEngine.TheMachineEngine;
import schemaGenerated.CTEEnigma;

import java.util.ArrayList;
import java.util.List;

public class UserInputValidator implements Validator {

    private char[] userInput;
    private List<Exception> listOfException;
    private CTEEnigma cteEnigma;

    private TheMachineEngine theMachineEngine;
    private String[] rotorsId;
    private char[] rotorsPosition;
    private String reflectorId;
    private List<Pair> pairsOfSwappingCharacter = new ArrayList<>();
    public UserInputValidator(String userInput, CTEEnigma cteEnigma,TheMachineEngine theMachineEngine) {
        this.cteEnigma = cteEnigma;
        this.listOfException = new ArrayList<>();
        this.userInput = new char[userInput.length()];
        int countOfOpener = 0, countOfBrackets = 0;
        for (int i = 0; i < userInput.length(); i++) {
            this.userInput[i] = userInput.charAt(i);
        }
        this.theMachineEngine=theMachineEngine;

    }

    @Override
    public void validate() {
        if (isStrFormatValid()) {
            splitTheUserInput();
            isRotorsIdFromUserInputIsValid();
            isRotorIDIsUniq();
            isReflectorIdValid();
        }
    }
    public void isRotorIDIsUniq(){
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
    @Override
    public List<Exception> getListOfException() {
        return listOfException;
    }

    public Boolean isStrFormatValid() {
        int countOfOpener = 0, countOfBrackets = 0;
        for (int i = 0; i < userInput.length; i++) {
            if (userInput[i] == '<') {
                countOfOpener++;
            } else if (userInput[i] == '>') {
                countOfBrackets++;
            }
        }
        if (countOfOpener != countOfBrackets) {
            listOfException.add(new Exception("The input is not valid,the amount of < is not equal to the amount of >"));
            return false;
        } else if ((countOfOpener < 3) || (countOfOpener > 5)) {
            listOfException.add(new Exception("The input is not valid,you need to have between 4 to 5 <>,you insert [" + countOfBrackets + "]"));
            return false;
        }
        return true;
    }
    private char[] deepCopy() {
        char[] tmp = new char[userInput.length];
        int countOfOpener = 0, countOfBrackets = 0;
        for (int i = 0; i < userInput.length; i++) {
            tmp[i] = userInput[i];
        }
        return tmp;
    }
    private void isRotorsIdFromUserInputIsValid(){
        int numberOfRotorsFromTheFile=cteEnigma.getCTEMachine().getRotorsCount();
      if(rotorsId.length>numberOfRotorsFromTheFile){
          listOfException.add(new Exception("The number of rotors you enter is more of the rotors amount exist in the machine,you can insert ["+numberOfRotorsFromTheFile+"] for maximum"));
      }
      else if(rotorsId.length==0){
          listOfException.add(new Exception("You didn`t enter rotors id,you can insert for maximum ["+numberOfRotorsFromTheFile+"]"));
      }
        for (String rotorsId: rotorsId) {
          if(!theMachineEngine.getRotorsSet().isRotorsIdExists(rotorsId)){
              listOfException.add(new Exception("The rotor id ["+rotorsId+"] does not exists in the machine"));
          }
        }
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




    private void splitTheUserInput() {
        char[] tmp = deepCopy();
        char[] sub = new char[tmp.length];
        int count = 0;
        int counterOfOpenBrackets = 0;
        int j = 0;
        boolean flag = false;
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] == '<') {
                count = 0;
                counterOfOpenBrackets++;
                i++;
                if (counterOfOpenBrackets == 1) {
                    while (tmp[i] != '>') {
                        count++;
                        if ((tmp[i] > '9') && (tmp[i] != ',')) {
                            listOfException.add(new Exception("The input is not valid,in the first <> you need to enter numbers separated by a comma"));
                            flag = true;
                            break;
                        } else {
                            sub[j] = tmp[i];
                            j++;

                        }
                        i++;
                    }
                    if (!flag) {
                        String subString = String.valueOf(sub);
                        rotorsId = (subString.split(","));
                        for (int ix = 0; ix < rotorsId.length; ix++) {
                            rotorsId[ix] = rotorsId[ix].trim();
                        }
                    }
                } else if (counterOfOpenBrackets == 2) {
                char[]  rotorsPositionTmp = new char[tmp.length];
                    j=0;
                    while (tmp[i] != '>') {
                        count++;
                        rotorsPositionTmp[j] = tmp[i];
                        j++;
                        i++;
                    }
                    rotorsPosition=new char[count];
                    for(int ix=0;ix<count;ix++){
                        rotorsPosition[ix]=rotorsPositionTmp[ix];
                    }
                }
                else if(counterOfOpenBrackets==3){
                    char[]  reflectorIdTmp = new char[tmp.length];
                    j=0;
                    while (tmp[i] != '>') {
                        count++;
                        reflectorIdTmp[j] = tmp[i];
                        j++;
                        i++;
                    }
                  reflectorId=String.valueOf(reflectorIdTmp);
                }
                else if(counterOfOpenBrackets==4){
                    char[]  plugsTmp = new char[tmp.length];
                    j=0;
                    while (tmp[i] != '>') {
                        count++;
                        plugsTmp[j] = tmp[i];
                        j++;
                        i++;
                    }
                    String subString = String.valueOf(plugsTmp);
                    String[] plugs = (subString.split(","));
                    for (int ix = 0; ix < plugs.length; ix++) {
                        String tmpSub=plugs[ix].trim();
                        for (int jx=0;jx<tmpSub.length()-2;jx++)
                        {
                            Pair pair=new Pair(String.valueOf(tmpSub.charAt(jx)),(String.valueOf(tmpSub.charAt(jx+2))));
                            pairsOfSwappingCharacter.add(pair);
                        }
                    }
                }
            }
        }

    }
}