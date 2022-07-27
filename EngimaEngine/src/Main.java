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


        List<Pair> rotorStructure2=new ArrayList<>();
        rotorStructure2.add(new Pair('A','E'));
        rotorStructure2.add(new Pair('B','B'));
        rotorStructure2.add( new Pair('C','D'));
        rotorStructure2.add(new Pair('D','F'));
        rotorStructure2.add(new Pair('E','C'));
        rotorStructure2.add(new Pair('F','A'));
        Rotor rotor2=new Rotor("1",'C',"1",6,rotorStructure2);

        System.out.println(rotor.getRotorStructure().toString()+"Rotor 1");
        System.out.println(rotor2.getRotorStructure().toString()+"Rotor 2");
        System.out.println("AFTER");
        List<Rotor> arrRotors=new ArrayList<>();
        arrRotors.add(rotor);
        arrRotors.add((rotor2));

        RotorsSet rotorsSet=new RotorsSet(arrRotors);


        List<Pair> reflectorLisr=new ArrayList<>();
        reflectorLisr.add(new Pair('1','4'));
        reflectorLisr.add(new Pair('2','5'));
        reflectorLisr.add( new Pair('3','6'));
        Reflector  reflector=new Reflector("2",reflectorLisr);


        List<Pair> reflectorLisr2=new ArrayList<>();
        reflectorLisr2.add(new Pair('1','2'));
        reflectorLisr2.add(new Pair('3','4'));
        reflectorLisr2.add(new Pair('5','6'));
        Reflector  reflector2=new Reflector("2",reflectorLisr2);

        System.out.println(reflector2.getReflectorStructure() +"Reflector");
  /*      rotorsSet.manageSpins();


        System.out.println(rotor.getRotorStructure().toString()+"Rotor 1");
        System.out.println(rotor2.getRotorStructure().toString()+"Rotor 2");
        System.out.println("AFTER TWO SPINS");
        rotorsSet.manageSpins();
        System.out.println(rotor.getRotorStructure().toString()+"Rotor 1");
        System.out.println(rotor2.getRotorStructure().toString()+"Rotor 2");

        System.out.println("AFTER 3 SPINS");
        rotorsSet.manageSpins();
        System.out.println(rotor.getRotorStructure().toString()+"Rotor 1");
        System.out.println(rotor2.getRotorStructure().toString()+"Rotor 2");*/
    }
}
