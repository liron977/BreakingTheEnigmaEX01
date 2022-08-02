package Engine;

import Engine.Keyboard;
import Engine.PlugsBoard;
import Engine.Rotor;
import Engine.RotorsSet;

import java.util.Collections;

public class TheMachineEngine {
    private RotorsSet rotorsSet;
    private Reflector reflector;
    private ReflectorsSet reflectorsSet;
    private Keyboard keyboard;
    private PlugsBoard plugsBoard;

    public TheMachineEngine(RotorsSet rotorsSet, ReflectorsSet reflectorsSet, Keyboard keyboard){
        this.rotorsSet=rotorsSet;
        //this.reflector=reflector;
        this.keyboard=keyboard;
        this.reflectorsSet=reflectorsSet;
        //this.plugsBoard=plugsBoard;
    }
    public void addPlugsBoardTOTheMachine(PlugsBoard plugsBoard){
        this.plugsBoard=plugsBoard;
    }
    public void  addSelectedReflector(String reflectorId){

        this.reflector=reflectorsSet.searchReflectorById(reflectorId);
    }

/*    public char manageDecode(char signal){
        Engine.Rotor tmpRotor=null;

        char entryValue=plugsBoard.getSwappedCharacter(signal);
        rotorsSet.manageSpins();
        int indexOfSignal= keyboard.getIndexFromKeyboard(entryValue);
        for (Engine.Rotor rotor: rotorsSet.getListOfRotors()) {
            entryValue=rotor.getEntryValueFromRotorByIndex(indexOfSignal);

            indexOfSignal=rotor.getIndexFromRotorByEntryValue(entryValue);
        }
        indexOfSignal=reflector.getExitIndexFromTheReflector(indexOfSignal);
        Collections.reverse(rotorsSet.getListOfRotors());

        for (Engine.Rotor rotor:rotorsSet.getListOfRotors()) {
            entryValue=rotor.getExitValueFromRotorByIndex(indexOfSignal);
            indexOfSignal=rotor.getIndexFromRotorByExitValue(entryValue);
            tmpRotor=rotor;
        }
        Collections.reverse(rotorsSet.getListOfRotors());
        if(tmpRotor!=null){
            indexOfSignal=tmpRotor.getIndexFromRotorByExitValue(entryValue);
            entryValue=keyboard.getCharacterFromKeyboardByIndex(indexOfSignal);
            entryValue=plugsBoard.getSwappedCharacter(entryValue);
        }
        return  entryValue;
    }*/
public String manageDecode(String signal){
    int indexOfSignal;
    String result;

    rotorsSet.manageSpins();
    indexOfSignal=theProcessFromTheRotorsToReflecter(signal);
    result=theProcessFromTheReflectorToRotors(indexOfSignal);

    return result;
}
    private int theProcessFromTheRotorsToReflecter(String signal){
        String entryValue=plugsBoard.getSwappedCharacter(signal);
        int indexOfSignal= keyboard.getIndexFromKeyboard(entryValue);
        for (Rotor rotor: rotorsSet.getListOfRotors()) {
            entryValue=rotor.getEntryValueFromRotorByIndex(indexOfSignal);

            indexOfSignal=rotor.getIndexFromRotorByEntryValue(entryValue);
        }

        return indexOfSignal;
    }
    private String theProcessFromTheReflectorToRotors(int indexOfSignal){
        Rotor tmpRotor=null;
        String entryValue="";

        indexOfSignal=reflector.getExitIndexFromTheReflector(indexOfSignal);
        Collections.reverse(rotorsSet.getListOfRotors());
        for (Rotor rotor:rotorsSet.getListOfRotors()) {
            entryValue=rotor.getExitValueFromRotorByIndex(indexOfSignal);
            indexOfSignal=rotor.getIndexFromRotorByExitValue(entryValue);
            tmpRotor=rotor;
        }
        Collections.reverse(rotorsSet.getListOfRotors());
        if(tmpRotor!=null){
            indexOfSignal=tmpRotor.getIndexFromRotorByExitValue(entryValue);
            entryValue=keyboard.getCharacterFromKeyboardByIndex(indexOfSignal);
            entryValue=plugsBoard.getSwappedCharacter(entryValue);
        }

        return  entryValue;
    }




}
