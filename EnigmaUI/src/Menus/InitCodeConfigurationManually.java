package Menus;

public class InitCodeConfigurationManually implements RunTheMenuInterface{
    @Override
    public void execution(int userChoice) {
        printDescription();
    }
    public void printDescription() {
        String str="Please enter the requested details :\n" +"#1 -The selected rotors ID numbers + order between them (for " +
                "example:if you choose 2 rotors with id:7,3 and the rotor(7) appears on the far right, insert <3,7>) \n"
                +"#2 -Initial position for each rotor ,series of valid characters from the ABC of the machine without separation" +
                " between them (for example <AO>,A position is for rotor(7) abf O for rotor(3))\n"
                +"#3 -Choosing a reflector -Roman numerals only: I, II, III, IV, V (for example <II>) \n"
                +"#4 -Pairs of characters from the ABS of the machine that will be used as plugs (for example A and Z will be replaced,insrt <A|Z> \n"
                +"TO SUM UP,your input should be in thids structure according the example <3,7><AO><II><A|Z>";

        System.out.println(str);
    }
}
