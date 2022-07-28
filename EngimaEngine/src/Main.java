import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        rotorStructure2.add(new Pair('A','B'));
        rotorStructure2.add(new Pair('B','A'));
        rotorStructure2.add( new Pair('C','D'));
        rotorStructure2.add(new Pair('D','C'));
        rotorStructure2.add(new Pair('E','F'));
        rotorStructure2.add(new Pair('F','E'));
        Rotor rotor2=new Rotor("1",'C',"1",6,rotorStructure2);

        List<Pair> rotorStructur3=new ArrayList<>();
        rotorStructur3.add(new Pair('A','E'));
        rotorStructur3.add(new Pair('B','B'));
        rotorStructur3.add( new Pair('C','D'));
        rotorStructur3.add(new Pair('D','F'));
        rotorStructur3.add(new Pair('E','C'));
        rotorStructur3.add(new Pair('F','A'));
        Rotor rotor3=new Rotor("13",'C',"1",6,rotorStructur3);

        System.out.println(rotor.getRotorStructure().toString()+"Rotor 1");
        System.out.println(rotor2.getRotorStructure().toString()+"Rotor 2");
        System.out.println("AFTER");
        List<Rotor> arrRotors=new ArrayList<>();
        arrRotors.add(rotor);
        //arrRotors.add((rotor2));
        arrRotors.add((rotor3));
        RotorsSet rotorsSet=new RotorsSet(arrRotors);


        List<Pair> reflectorLisr=new ArrayList<>();
        reflectorLisr.add(new Pair('1','4'));
        reflectorLisr.add(new Pair('2','5'));
        reflectorLisr.add( new Pair('3','6'));
        Reflector  reflector=new Reflector("2",reflectorLisr);
        System.out.println(reflector.getReflectorStructure() +"Reflector");

       //System.out.println(reflector.getExitIndexFromTheReflector(6)+ "ExitIndexFromTheReflector");

        List<Pair> reflectorLisr2=new ArrayList<>();
        reflectorLisr2.add(new Pair('1','2'));
        reflectorLisr2.add(new Pair('3','4'));
        reflectorLisr2.add(new Pair('5','6'));
        Reflector  reflector2=new Reflector("2",reflectorLisr2);

        Keyboard keyboard=new Keyboard("ABCDEF");

        int amountToSwap=1;
        List<Pair> plugs=new ArrayList<>();
       // plugs.add(new Pair('C','B'));
        plugs.add(new Pair('A','F'));
        PlugsBoard plugsBoard=new PlugsBoard(keyboard,plugs,amountToSwap);
       // PlugsBoard plugsBoard=new PlugsBoard(keyboard,plugs,amountToSwap);



        TheMachineEngine theMachineEngine=new TheMachineEngine(rotorsSet,reflector,keyboard,plugsBoard);
        Scanner scanner = new Scanner(System.in);

        char userIntegerInput = 0;
        boolean validInput=true;
        do {
            System.out.print("Enter an integer: ");

                userIntegerInput = scanner.next().charAt(0);
              /*  if((userIntegerInput=='\t')||(userIntegerInput=='\r\n')||(userIntegerInput=='\r')){
                    validInput = false;
                }*/
                //else {
            System.out.println(theMachineEngine.manageDecode(userIntegerInput));

               // }


        } while (validInput == true);
        //Scanner sc = new Scanner(System.in);
        //char c = (char) System.in.read();
//        int i=0;
//        while ((c!='\t')&&(c!='\n')&&(c!='\r')) {
//            c = sc.next().charAt(i);
//        }

      //  System.out.println(theMachineEngine.manageDecode('F'));


        //rotor.spinRotor();
       // System.out.println(rotor.getValueFromRotorByIndex(keyboard.getIndexFromKeyboard('B')));
        //System.out.println(keyboard.getIndexFromKeyboard('C'));

        //System.out.println(reflector2.getReflectorStructure() +"Reflector");

        //System.out.println(reflector2.getExitIndexFromTheReflector(6)+ "ExitIndexFromTheReflector");

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
