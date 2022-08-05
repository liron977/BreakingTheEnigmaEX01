package Menus;

import Console.UserConsole;

import java.util.Scanner;

public class InputProcess implements MenuManager{
    String userInput;
    public UserConsole userConsole;
    public InputProcess(UserConsole userConsole){
        this.userConsole=userConsole;

    }
    @Override
    public void execution() {
        boolean isValidInputProcess=false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the string to process");
        userInput = scanner.nextLine();
        while(!isValidInputProcess){

           isValidInputProcess=userConsole.isUserStringToProcessIsValid(userInput);
           if(isValidInputProcess) {
               System.out.println("The converted string is: "+userConsole.getConvertedString(userInput));

           }
          else{
               userInput = scanner.nextLine();
           }

        }

    }
}