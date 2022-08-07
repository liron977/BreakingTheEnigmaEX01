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
        initCodeConfiguration();
    }
    public void printRotorDescription() {
        String str="Please enter therotors ID numbers + order between them (for " +
                "example:if you choose 2 rotors with id:7,3 and the rotor(7) appears on the far right, insert <3,7>) \n";
        System.out.println(str);
    }
    public void printStartPositionDescriptionDescription() {
        String str="Please enter the initial position for each rotor ,series of valid characters from the ABC of the machine without separation" +
                " between them (for example <AO>,A position is for rotor(7) abf O for rotor(3))\n";

        System.out.println(str);
    }

    public void printPlagBoardDescription() {
        String str="Please enter pairs of characters from the ABS of the machine that will be replaced (for example if you insert AZ ,A and Z will be replaced) \n";
        System.out.println(str);
    }
    public void printReflectorIdDescription() {
        String str="Please choose a reflector:\n 1)I \n 2)II \n 3)III \n 4)IV \n 5)V \n";
        System.out.println(str);
    }


    private void initCodeConfiguration(){
        Scanner scanner = new Scanner(System.in);
        boolean isUserInputRotorsIsValid=false;
        boolean isUserInputReflectIdIsValid=false;
        boolean isStartingPositionAreValid=false;
        boolean isPlagBoardIsValid=false;
        printRotorDescription();
        String loadStart = scanner.nextLine();
        while (!isUserInputRotorsIsValid){
            if (userConsole.isRotorsIDinInitCodeManuallyIsValid(loadStart)) {
                isUserInputRotorsIsValid = true;
                System.out.println("Rotors id updated successfully  \n");
                printStartPositionDescriptionDescription();
                loadStart = scanner.nextLine();
                while (!isStartingPositionAreValid) {
                        if (userConsole.isStartingPositionInitCodeManuallyIsValid(loadStart)) {
                            isStartingPositionAreValid = true;
                            userConsole.initStartingPositionConfigurationManually(loadStart);
                            System.out.println("Starting position for each rotor updated successfully \n");
                             printReflectorIdDescription();
                              loadStart = scanner.nextLine();
                              while (!isUserInputReflectIdIsValid){
                               if(userConsole.isReflectoIDinInitCodeManuallyIsValid(loadStart)){
                                   isUserInputReflectIdIsValid=true;
                                   System.out.println("Reflector id updated successfully  \n");
                               }
                               else {
                                   System.out.println("Please insert updated requested details  \n");
                                   loadStart = scanner.nextLine();
                               }
                           }
                            printPlagBoardDescription();
                            loadStart = scanner.nextLine();
                           while (!isPlagBoardIsValid){
                               if(userConsole.isPlagBoardinInitCodeManuallyIsValid(loadStart)){
                                   isPlagBoardIsValid=true;
                                   userConsole.initPlugBoardConfigurationManually(loadStart);
                                   System.out.println("Plug board updated successfully  \n");
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
            else {
                System.out.println("Please insert updated requested details");
                loadStart = scanner.nextLine();
            }
        }
       userConsole.setIsCodeConfigurationWasdefine();

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
