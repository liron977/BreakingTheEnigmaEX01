package Engine;

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
    public Reflector searchReflectorById(String reflectorId){
        for (Reflector reflector:reflectors) {
            if(reflector.getReflectorId().equals(reflectorId)){
                return reflector;
            }

        }
        return null;
    }
/*    @Override
    public String toString()
    {
        String str="";
        for (Engine.Reflector reflector:reflectors) {
            //str+=;
            str= str+ "New reflector:"+ reflector.getReflectorStructure().toString() + "]";
        }
        return str;
    }*/
}
