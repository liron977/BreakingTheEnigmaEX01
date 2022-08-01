package Engine;

import Engine.Rotor;

import java.util.ArrayList;
import java.util.List;

public class RotorsSet {
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
 /*   @Override
    public String toString()
    {
        String str="";
        for (Engine.Rotor rotor:rotors) {
            //str+=;
           str= str+ "New rotow:"+ rotor.getRotorStructure().toString() + "]";
        }
        return str;
    }*/

}


