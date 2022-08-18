package engine.theEnigmaEngine;

import java.io.Serializable;
import java.util.*;

import schemaGenerated.*;

public class SchemaGenerated implements Serializable {
    private CTEEnigma enigmaDescriptor;
    private String keyboardInput;
    private List<String> romanNumeralsList = new ArrayList<>();

    public SchemaGenerated(CTEEnigma enigmaDescriptor) {
        this.enigmaDescriptor = enigmaDescriptor;
        this.keyboardInput = enigmaDescriptor.getCTEMachine().getABC().trim();
    }
    public RotorsSet createRotorsSet(){
        List<Rotor> rotors =new ArrayList<>();
        List<CTERotor> cteRotors=enigmaDescriptor.getCTEMachine().getCTERotors().getCTERotor();
        for (CTERotor cteRotor:cteRotors) {
           rotors.add(createNewRotor(cteRotor));
        }
        RotorsSet rotorsSet=new RotorsSet(rotors);
        return rotorsSet;

    }
    private Pair createNewPair(CTEPositioning positing){
        String left=positing.getLeft().toUpperCase();
        String right=positing.getRight().toUpperCase();
        Pair pair=new Pair(right,left);

        return pair;
    }
    private Rotor createNewRotor(CTERotor cteRotor){
        List<Pair> rotorStructure=new ArrayList<>();
        int entriesAmount=0;
        int rotorId=cteRotor.getId();
        String rotorIdString=String.valueOf(rotorId);
        int notchPosition=cteRotor.getNotch();
        List<CTEPositioning> ctePositioning=cteRotor.getCTEPositioning();
        for (CTEPositioning positing:ctePositioning) {
            rotorStructure.add(createNewPair(positing));
            entriesAmount++;
        }
        Rotor newRoter=new Rotor(rotorIdString,entriesAmount,rotorStructure,notchPosition);
        return newRoter;
    }
    public ReflectorsSet createReflectorsSet(){
        List<Reflector> reflectorsList=new ArrayList<>();
        List<CTEReflector> cteReflectorList=enigmaDescriptor.getCTEMachine().getCTEReflectors().getCTEReflector();
        String reflectorId;
        Reflector reflector;

        for (CTEReflector cteReflector: cteReflectorList) {
            List<CTEReflect> cteReflect=cteReflector.getCTEReflect();
            reflectorId=cteReflector.getId();
            reflector=new Reflector(reflectorId,crateNewReflector(cteReflect));
            reflectorsList.add(reflector);
        }
        ReflectorsSet reflectorsSet=new ReflectorsSet(reflectorsList);
        return reflectorsSet;
    }
    private List<Pair> crateNewReflector(List<CTEReflect> cteReflect) {
        String left,right;
        Pair pair;
        List<Pair> reflectorPairStructure=new ArrayList<>();
        for (CTEReflect reflect:cteReflect) {
            left=Integer.toString(reflect.getInput());
            right=Integer.toString(reflect.getOutput());
            pair=new Pair(left,right);
            reflectorPairStructure.add(pair);
        }
        return  reflectorPairStructure;
    }
    public int getAmountOfUsedRotors(){
        return enigmaDescriptor.getCTEMachine().getRotorsCount();
    }
    public Keyboard createKeyboard()
    {
       return new Keyboard(enigmaDescriptor.getCTEMachine().getABC().trim());
    }
}