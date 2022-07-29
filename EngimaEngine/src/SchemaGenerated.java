import java.util.ArrayList;
import java.util.List;

import schemaGenerated.*;

public class SchemaGenerated /*implements Serializable*/ {

    public SchemaGenerated(CTEEnigma enigmaDescriptor){
          Keyboard keyboard= new Keyboard(enigmaDescriptor.getCTEMachine().getABC());
        RotorsSet rotorsSet= createRotorsSet(enigmaDescriptor);
        System.out.println(rotorsSet.toString());

    }

/*    private createReflectorsSet(CTEEnigma enigmaDescriptor){

        List<CTEReflector> cteReflectorList=enigmaDescriptor.getCTEMachine().getCTEReflectors().getCTEReflector();
        for (CTEReflector cteReflector: cteReflectorList) {

        }
   }*/
/*   private Reflector crateNewReflector()
   {


   }*/
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
        Pair pair=new Pair(charRight,charLeft);

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
        return newRoter;
    }
}
