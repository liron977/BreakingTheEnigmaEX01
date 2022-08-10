package Menus;

import Console.Mediator;

import java.util.Scanner;

public class InputProcess implements MenuManager{
    private String userInput;
    private Mediator mediator;
    public InputProcess(Mediator mediator){
        this.mediator = mediator;

    }
    @Override
    public void execution() {
        boolean isValidInputProcess=false;
        boolean isMachineWasDefined= mediator.isMachineWasDefined();
        boolean isCodeWasDefined= mediator.isCodeWasDefined();
        if(isMachineWasDefined&&isCodeWasDefined) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter the string to process");
            userInput = scanner.nextLine();
            while (!isValidInputProcess) {

                isValidInputProcess = mediator.isUserStringToProcessIsValid(userInput);
                if (isValidInputProcess) {
                    System.out.println("The converted string is: " + mediator.getConvertedString(userInput));

                } else {
                    userInput = scanner.nextLine();
                }

            }
        }
    }
}