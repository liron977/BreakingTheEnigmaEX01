package Engine.TheEnigmaEngine;

import Engine.TheEnigmaEngine.*;

import java.util.Collections;
import java.util.List;

public class TheMachineEngine {
    private RotorsSet rotorsSet;
    private RotorsSet usedRotors;
    private Reflector reflector;
    private ReflectorsSet reflectorsSet;
    private Keyboard keyboard;
    private PlugsBoard plugsBoard;
    private int amountOfUsedRotors;

    public TheMachineEngine(RotorsSet rotorsSet, ReflectorsSet reflectorsSet, Keyboard keyboard,int amountOfUsedRotors){
        this.rotorsSet=rotorsSet;
        //this.reflector=reflector;
        this.keyboard=keyboard;
        this.reflectorsSet=reflectorsSet;
        this.amountOfUsedRotors=amountOfUsedRotors;
        //this.plugsBoard=plugsBoard;
    }
    public void addPlugsBoardTOTheMachine(PlugsBoard plugsBoard){
        this.plugsBoard=plugsBoard;
    }

    public void  addSelectedReflector(String reflectorId){

        this.reflector=reflectorsSet.getReflectorById(reflectorId);
    }
    public ReflectorsSet getReflectorsSet(){
        return reflectorsSet;
    }
    public String[] getRotorsId(){
        String[] rotorsId=new String[rotorsSet.getListOfRotors().size()];
        int i=0;
        for (Rotor rotor: rotorsSet.getListOfRotors()) {
               rotorsId[i]=rotor.getRotorId();
               i++;
        }
        return rotorsId;
    }
    public String[] getReflectorId(){
        String[] reflectorsId=new String[]{};
        int i=0;
        for (Reflector reflector: reflectorsSet.getListOfReflectors()) {
            reflectorsId[i]=reflector.getReflectorId();
            i++;
        }
        return reflectorsId;
    }
    public String getKeyboard(){
        return keyboard.getKeyboard();
    }
    public RotorsSet getRotorsSet(){
        return rotorsSet;
    }
    public void setUsedRotors(Rotor usedRotor){
        this.usedRotors=usedRotors;
    }
    public int getAmountOfUsedRotors(){
        return amountOfUsedRotors;
    }
    public void createUsedRotorsSet(List<Rotor> listOfRotors) {
         this.usedRotors=new RotorsSet(listOfRotors);
    }
    public RotorsSet getUsedRotors(){
        return usedRotors;
    }



/*    public char manageDecode(char signal){
        Engine.TheEnigmaEngine.Rotor tmpRotor=null;

        char entryValue=plugsBoard.getSwappedCharacter(signal);
        rotorsSet.manageSpins();
        int indexOfSignal= keyboard.getIndexFromKeyboard(entryValue);
        for (Engine.TheEnigmaEngine.Rotor rotor: rotorsSet.getListOfRotors()) {
            entryValue=rotor.getEntryValueFromRotorByIndex(indexOfSignal);

            indexOfSignal=rotor.getIndexFromRotorByEntryValue(entryValue);
        }
        indexOfSignal=reflector.getExitIndexFromTheReflector(indexOfSignal);
        Collections.reverse(rotorsSet.getListOfRotors());

        for (Engine.TheEnigmaEngine.Rotor rotor:rotorsSet.getListOfRotors()) {
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
            indexOfSignal=rotor.getEntryIndexFromRotorByValue(entryValue);
            tmpRotor=rotor;
        }
        Collections.reverse(rotorsSet.getListOfRotors());
        if(tmpRotor!=null){
            indexOfSignal=tmpRotor.getEntryIndexFromRotorByValue(entryValue);
            entryValue=keyboard.getCharacterFromKeyboardByIndex(indexOfSignal);
            entryValue=plugsBoard.getSwappedCharacter(entryValue);
        }

        return  entryValue;
    }




}