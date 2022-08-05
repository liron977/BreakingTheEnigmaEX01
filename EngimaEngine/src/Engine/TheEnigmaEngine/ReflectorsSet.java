package Engine.TheEnigmaEngine;

import Engine.TheEnigmaEngine.Reflector;

import java.util.ArrayList;
import java.util.List;

public class ReflectorsSet {


    private List<Reflector> reflectors =new ArrayList<>();

    public ReflectorsSet(List<Reflector> reflectors){
        this.reflectors=reflectors;;
    }
    public List<Reflector> getListOfReflectors() {
        return reflectors;
    }
    public int getReflectorsAmount() {
        return reflectors.size();
    }
    public Reflector getReflectorById(String reflectorId){
        for (Reflector reflector:reflectors) {
            if(reflector.getReflectorId().equals(reflectorId)){
                return reflector;
            }

        }
        return null;
    }
    public boolean searchReflectorById(String reflectorId){
        for (Reflector reflector:reflectors) {
            if(reflector.getReflectorId().equals(reflectorId)){
                return true;
            }
        }
        return false;
    }

/*    @Override
    public String toString()
    {
        String str="";
        for (Engine.TheEnigmaEngine.Reflector reflector:reflectors) {
            //str+=;
            str= str+ "New reflector:"+ reflector.getReflectorStructure().toString() + "]";
        }
        return str;
    }*/
}