package Engine.TheEnigmaEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.Serializable;

public class RotorsSet implements Serializable {
   private List<Rotor> rotors =new ArrayList<>();
    private int rotorsAmount;
    public RotorsSet(List<Rotor> rotors){
        this.rotors=rotors;
        this.rotorsAmount=rotors.size();
    }

    public Rotor getRotorById(String rotorId){
        for (Rotor rotor:rotors) {
            if(rotor.getRotorId().equals(rotorId)){
                return rotor;
            }
        }
        return null;
    }
    public List<Rotor> getListOfRotors() {
       return rotors;
    }

    public String getRotorsStartingPositions(){
        String rotorsStartingPositions="";
        for (Rotor rotor:rotors) {

            rotorsStartingPositions=rotorsStartingPositions+rotor.getStartingPosition();
        }
        return rotorsStartingPositions;
    }
    public String[] getAllRotorsId(){
        int i=0;
        String[] rotorsId=new String[rotors.size()];
        for (Rotor rotor:rotors) {
            rotorsId[i]=rotor.getRotorId();
              i++;
        }
        return rotorsId;
    }
    public void reverseRotors(){
        Collections.reverse(rotors);
    }
    public void manageSpins(){

        for(int i=0;i<rotorsAmount-1;i++) {
           if(i==0){
               rotors.get(0).spinRotor();
              if(rotors.get(0).isNotchLocatedInWindow()){
                  rotors.get(1).spinRotor();
              }
           }
           else{
               if(rotors.get(i).isNotchLocatedInWindow()){
                   rotors.get(i+1).spinRotor();
               }
           }
        }
    }
    public int getMaxAmountOfRotors() {
return rotors.size();
    }

    public List<String> getNotchList(){
        List<String> notchList=new ArrayList<>();
        for (Rotor rotor:rotors) {
            notchList.add(String.valueOf(rotor.getNotchPosition()));

        }
       return notchList;
    }
    public List<String> getOriginalNotchPositionList(){
        List<String> notchList=new ArrayList<>();
        for (Rotor rotor:rotors) {
            notchList.add(String.valueOf(rotor.getOriginalNotchPosition()));
        }
        return notchList;
    }

    public boolean isRotorsIdExists(String id){
        for (Rotor rotor:rotors){
            if(rotor.getRotorId().equals(id)){
                return true;
            }
        }
        return false;
    }
    public void resetCurrentRotorsCode(){
        for (Rotor rotor:rotors) {
            rotor.initRotorStructure();

        }
    }

}