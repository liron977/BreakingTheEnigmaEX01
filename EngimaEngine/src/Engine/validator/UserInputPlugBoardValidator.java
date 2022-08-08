package Engine.validator;

import Engine.TheEnigmaEngine.TheMachineEngine;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserInputPlugBoardValidator implements Validator{
    private String userInput;
    private List<Exception> listOfException;
    private TheMachineEngine theMachineEngine;
    public UserInputPlugBoardValidator(String userInput,TheMachineEngine theMachineEngine){
        this.userInput=userInput.toUpperCase();
        this.theMachineEngine=theMachineEngine;
       this.listOfException=new ArrayList<>();
    }
    public void isUserChosenInputToDefineAPlugBoardIsValid() {
        boolean flag = false;
        for (char signal : userInput.toCharArray()) {
            if (!Character.isDigit(signal)) {
                flag = true;
                listOfException.add(new Exception("Insert only numbers between 1-2,the character [" + signal + "] is not valid!"));
            }
        }
        if (!flag) {
            if ((userInput.length() > 1) || (userInput.length() < 1)) {
                listOfException.add(new Exception("Insert only numbers between 1-2"));
            } else {
                char signal = userInput.charAt(0);
                if ((signal > '2') || (signal < '1')) {
                    listOfException.add(new Exception("Insert only numbers between 1-2"));
                }
            }
        }
    }

    private void isPlugsBoardsIsValid(){
        isPlugsBoardAmountIsValid();
        isSwappingPairsAreValid();
        isPlugsBoardHasDoubleMapping();
    }
    private void isPlugsBoardAmountIsValid(){
        int currentPlugBoardSize=userInput.length();
        int maximumPlugBoardSize=((theMachineEngine.getKeyboard().length())/2);
        if((currentPlugBoardSize)%2!=0){
            listOfException.add(new Exception("You must enter an even number of characters,you inserted ["+currentPlugBoardSize+"]"));
        }
        if((currentPlugBoardSize/2)>maximumPlugBoardSize){
            listOfException.add(new Exception("You have ["+currentPlugBoardSize+"pairs in the plugs board,but the maximum amount is ["+maximumPlugBoardSize+"]"));
        }
    }
    private void isPlugsBoardHasDoubleMapping(){
        HashMap<Character, Integer> pairsHashMap = new HashMap<>();
        for (char signal : userInput.toCharArray()) {
            if((pairsHashMap.get(signal) != null) &&(pairsHashMap.get(signal)>0)){
                listOfException.add(new Exception("The signal ["+signal+"] is already have a couple"));
            }
            pairsHashMap.put(signal,1);
        }
    }
    private void isSwappingPairsAreValid(){
        char signal;
        for (int i=0;i<userInput.length();i++) {
            signal=userInput.charAt(i);
            isSwappingCharacterPairsIsValid(signal);
        }
    }
    private void isSwappingCharacterPairsIsValid(char str) {
        if(!theMachineEngine.getKeyboard().contains(String.valueOf(str))){
            listOfException.add(new Exception("The plugs board signal can be from the machine keyboard only,the signal ["+str +"] is not valid"));
        }
    }
    @Override
    public void validate() {
        isPlugsBoardsIsValid();
    }

    @Override
    public List<Exception> getListOfException() {
        return listOfException;
    }
}
