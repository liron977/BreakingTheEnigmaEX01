package Menus;

import Console.UserConsole;

import java.util.Scanner;

public class InitCodeConfigurationManually implements MenuManager {
    public UserConsole userConsole;

    public InitCodeConfigurationManually(UserConsole userConsole){
        this.userConsole=userConsole;
    }
    @Override
    public void execution() {
        printDescription();
        initCodeConfiguration();
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
    private void initCodeConfiguration(){
        Scanner scanner = new Scanner(System.in);
        String loadStart = scanner.nextLine();
        boolean isUserInputDisplayIsValid=false;
        boolean isRotorsIdAreValid=false;
        //userConsole.isInputValid(loadStart);

        while (!isUserInputDisplayIsValid){
            if (userConsole.isInitCodeManuallyStructureIsValid(loadStart)) {
                isUserInputDisplayIsValid = true;
                ///Create tne machine with the user data
                while (!isRotorsIdAreValid) {
                        if (userConsole.isRotorsIDinInitCodeManuallyIsValid(loadStart)) {
                            isUserInputDisplayIsValid = true;
                            break;
                        }
                        else {
                            System.out.println("Please insert updated requested details");
                            loadStart = scanner.nextLine();
                        }
                }
            }
            else {
                System.out.println("Please insert updated requested details");
                loadStart = scanner.nextLine();
            }
        }

    }


}
   /* private boolean isStrValid(String str){
        char[] ch = new char[str.length()];
        int countOfOpener=0,countOfBrackets=0;
        for (int i = 0; i < str.length(); i++) {
            ch[i] = str.charAt(i);
        }
        for (int i=0;i<ch.length;i++){
          if(ch[i]=='<'){
              countOfOpener++;
          }
         else if(ch[i]=='>'){
             countOfBrackets++;
          }
        }
        if(countOfOpener!=countOfBrackets){
            return false;
        }
        return true;
    }
    */
