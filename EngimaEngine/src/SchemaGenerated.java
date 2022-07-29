import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import schemaGenerated.*;

public class SchemaGenerated /*implements Serializable*/ {

    public SchemaGenerated(CTEEnigma enigmaDescriptor){
          Keyboard keyboard= new Keyboard(enigmaDescriptor.getCTEMachine().getABC().trim());
        RotorsSet rotorsSet= createRotorsSet(enigmaDescriptor);
        List<Rotor> rotorsFinal=new ArrayList<>();
        rotorsFinal.add(rotorsSet.getRotorById("1"));
        rotorsFinal.add(rotorsSet.getRotorById("2"));
        //לשנות הכל פשוט לייצר רשימה של רוטרים ואז לבחור את הרוטור המתאים לפי ה-id שהתקבך
         rotorsSet=new RotorsSet(rotorsFinal);
        ReflectorsSet reflectorsSet=createReflectorsSet(enigmaDescriptor);
        Reflector reflector=reflectorsSet.searchReflectorById("I");

        List<Pair> plugs=new ArrayList<>();
       // plugs.add(new Pair('C','B'));
        plugs.add(new Pair('A','F'));
        PlugsBoard plugsBoard=new PlugsBoard(keyboard,plugs);

        TheMachineEngine theMachineEngine=new TheMachineEngine(rotorsSet,reflector,keyboard,plugsBoard);
        Scanner scanner = new Scanner(System.in);

        char userIntegerInput = 0;
        boolean validInput=true;
        do {
            System.out.print("Enter an integer: ");
            userIntegerInput = scanner.next().charAt(0);
            System.out.println(theMachineEngine.manageDecode(userIntegerInput));
        } while (validInput == true);

    }
    private ReflectorsSet createReflectorsSet(CTEEnigma enigmaDescriptor){
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
       char left,right;
       Pair pair;
       List<Pair> reflectorPairStructure=new ArrayList<>();
         for (CTEReflect reflect:cteReflect) {
                left=(char)(reflect.getInput()+'0');
                right=(char)(reflect.getOutput()+'0');
                pair=new Pair(left,right);
                reflectorPairStructure.add(pair);
            }
         return  reflectorPairStructure;
   }
    private RotorsSet createRotorsSet(CTEEnigma enigmaDescriptor){
        List<Rotor> rotors =new ArrayList<>();
        List<CTERotor> cteRotors=enigmaDescriptor.getCTEMachine().getCTERotors().getCTERotor();
        for (CTERotor cteRotor:cteRotors) {
           rotors.add(createNewRotor(cteRotor));
        }
        RotorsSet rotorsSet=new RotorsSet(rotors);
        return rotorsSet;

    }

    private Pair createNewPair(CTEPositioning positing){
        String left=positing.getLeft();
        String right=positing.getRight();
        char charLeft=left.charAt(0);
        char charRight=right.charAt(0);
        Pair pair=new Pair(charLeft,charRight);

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
        Rotor newRoter=new Rotor(rotorIdString,notchPosition,entriesAmount,rotorStructure);
        System.out.print("Enter an Char for position: ");
        Scanner scanner = new Scanner(System.in);
        char userIntegerInput = scanner.next().charAt(0);
       int startingPosition = userIntegerInput - 'A' + 1;
       newRoter.initRotorStructure(startingPosition);
        return newRoter;
    }
}
