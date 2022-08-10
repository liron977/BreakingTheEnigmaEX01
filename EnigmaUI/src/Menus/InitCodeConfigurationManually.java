package Menus;

import Console.Mediator;

import java.util.Scanner;

public class InitCodeConfigurationManually implements MenuManager {
    public Mediator mediator;
    private Scanner scanner;

    public InitCodeConfigurationManually(Mediator mediator) {
         scanner = new Scanner(System.in);
        this.mediator = mediator;
    }

    @Override
    public void execution() {
        initCodeConfiguration();
    }

    public void printRotorDescription() {
        String str = "Please enter the rotors ID numbers + order between them (for " +
                "example:if you choose 2 rotors with id:7,3 and the rotor(7) appears on the far right, insert <3,7>) \n";
        System.out.println(str);
    }

    public void printStartPositionDescriptionDescription() {
        String str = "Please enter the initial position for each rotor ,series of valid characters from the ABC of the machine without separation" +
                " between them (for example <AO>,A position is for rotor(7) abf O for rotor(3))\n";

        System.out.println(str);
    }

    public void printPlugBoardDescription() {
        String str = "Please enter pairs of characters from the ABS of the machine that will be replaced (for example if you insert AZ ,A and Z will be replaced) \n";
        System.out.println(str);
    }

    public void printReflectorIdDescription() {
        String str = "Please choose a reflector:\n 1)I \n 2)II \n 3)III \n 4)IV \n 5)V \n";
        System.out.println(str);
    }


    private void initCodeConfiguration() {
        boolean isUserInputRotorsIsValid = false;
        boolean isUserInputReflectIdIsValid = false;
        boolean isStartingPositionAreValid = false;
        boolean isPlagBoardIsValid = false;
        if (mediator.isMachineWasDefined()) {
            printRotorDescription();
            String loadStart = scanner.nextLine();
    while (!isUserInputRotorsIsValid) {
        if (mediator.isRotorsIDinInitCodeManuallyIsValid(loadStart)) {
            isUserInputRotorsIsValid = true;
            System.out.println("Rotors id updated successfully  \n");
            printStartPositionDescriptionDescription();
            loadStart = scanner.nextLine();
            while (!isStartingPositionAreValid) {
                if (mediator.isStartingPositionInitCodeManuallyIsValid(loadStart)) {
                    isStartingPositionAreValid = true;
                    mediator.initStartingPositionConfigurationManually(loadStart);
                    System.out.println("Starting position for each rotor updated successfully \n");
                    printReflectorIdDescription();
                    loadStart = scanner.nextLine();
                    while (!isUserInputReflectIdIsValid) {
                        if (mediator.isReflectoIDinInitCodeManuallyIsValid(loadStart)) {
                            isUserInputReflectIdIsValid = true;
                            System.out.println("Reflector id updated successfully  \n");
                        } else {
                            System.out.println("Please try again  \n");
                            loadStart = scanner.nextLine();
                        }
                    }
                    if (isPlayerWantsPlugBoard()) {
                        printPlugBoardDescription();
                        loadStart = scanner.nextLine();
                        while (!isPlagBoardIsValid) {
                            if (mediator.isPlagBoardinInitCodeManuallyIsValid(loadStart)&&!mediator.isChooseToExit(loadStart)) {
                                isPlagBoardIsValid = true;
                                mediator.initPlugBoardConfigurationManually(loadStart);
                                System.out.println("Plug board updated successfully  \n");
                            } else {
                                System.out.println("Please try again");
                                loadStart = scanner.nextLine();
                            }
                        }
                    }
                } else {
                    System.out.println("Please try again");
                    loadStart = scanner.nextLine();
                }
            }
        } else {
            System.out.println("Please try again");
            loadStart = scanner.nextLine();
        }
    }
    mediator.setIsCodeConfigurationWasdefine();
}

    }
    private boolean isPlayerWantsPlugBoard() {
        boolean isUserInputIsValid = false;
        System.out.println("Do you want to define a plug board \n 1)Yes \n 2)No");
        String userInput = scanner.nextLine();
        while (!isUserInputIsValid) {
            isUserInputIsValid = mediator.isPlayerDefinePlugBoardIsValid(userInput);
            if (isUserInputIsValid) {
                if (userInput.equals("1")) {
                    return true;
                } else {
                    return false;
                }
            } else {
                System.out.println("Please try again");
                userInput = scanner.nextLine();
            }
        }
        return false;
    }
}