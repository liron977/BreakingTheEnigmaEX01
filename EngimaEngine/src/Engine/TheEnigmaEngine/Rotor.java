package Engine.TheEnigmaEngine;

import Engine.TheEnigmaEngine.Pair;

import java.util.ArrayList;
import java.util.List;

public class Rotor {
    private List<Pair> rotorStructure = new ArrayList<>();
    private String rotorId;
    private int startingPosition;
    private Pair notchPair;
    private int entriesAmount;

    Rotor(String rotorId, int entriesAmount, List<Pair> rotorStructure) {
        this.rotorId = rotorId;
        this.entriesAmount = entriesAmount;
        this.rotorStructure = rotorStructure;

    }
    public void updateNitchPosition(int notchPosition){
        this.notchPair = this.rotorStructure.get((notchPosition) -1);
    }
public void setRotorStartingPosition(char startingPosition){
         this.startingPosition = startingPosition - 'A' + 1;
        this.startingPosition=startingPosition;
        initRotorStructure(startingPosition);
}
    public void initRotorStructure(int startingPosition) {
        List<Pair> tmpRotorStructure = new ArrayList<>();
        tmpRotorStructure.addAll(rotorStructure);
        int newPosition;
        for (int i = 0; i < entriesAmount; i++) {
            newPosition = i - startingPosition + 1;
            if (newPosition < 0) {
                newPosition = newPosition + entriesAmount;
            }
            tmpRotorStructure.set(newPosition, rotorStructure.get(i));
        }
        this.rotorStructure = tmpRotorStructure;
    }

    public List<Pair> getRotorStructure() {
        return rotorStructure;
    }
     public String getRotorId (){
        return rotorId;
     }
    public void spinRotor() {
        int newPosition;
        List<Pair> tmpRotorStructure = new ArrayList<>();
        tmpRotorStructure.addAll(rotorStructure);
        for (int i = 0; i < entriesAmount; i++) {
            newPosition = i - 1;
            if (newPosition < 0) {
                newPosition = newPosition + entriesAmount;
            }
            tmpRotorStructure.set(newPosition, rotorStructure.get(i));
        }
        rotorStructure = tmpRotorStructure;
    }
    public boolean isNotchLocatedInWindow() {
        return rotorStructure.get(0).equals(notchPair);
    }
    public String getEntryValueFromRotorByIndex(int index){
      return rotorStructure.get(index).getEntry();
    }
    public String getExitValueFromRotorByIndex(int index){
        return rotorStructure.get(index).getExit();
    }
    public int getIndexFromRotorByEntryValue(String str){
        for(int i=0;i<rotorStructure.size();i++){
            if(rotorStructure.get(i).getExit().equals(str)){
                return i;
            }
        }
        return -1;
    }
    public int getIndexFromRotorByExitValue(String str){
        for(int i=0;i<rotorStructure.size();i++){
            if(rotorStructure.get(i).getEntry().equals(str)){
                return i;
            }
        }
        return -1;
    }

}
