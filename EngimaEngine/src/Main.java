import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Pair> rotorStructure=new ArrayList<>();
        int amount=6;
        rotorStructure.add(new Pair('A','F'));
        rotorStructure.add(new Pair('B','E'));
        rotorStructure.add( new Pair('C','D'));
        rotorStructure.add(new Pair('D','C'));
        rotorStructure.add(new Pair('E','B'));
        rotorStructure.add(new Pair('F','A'));
        Rotor rotor=new Rotor("1",'C',"4",6,rotorStructure);
        rotor.spinRotor();
        rotor.spinRotor();
        rotor.spinRotor();
        rotor.spinRotor();
        rotor.spinRotor();
        rotor.spinRotor();
        rotor.spinRotor();


        System.out.println(rotor.isNotchLocatedInWindow());

        System.out.println(rotor.getRotorStructure().toString());


    }
}
