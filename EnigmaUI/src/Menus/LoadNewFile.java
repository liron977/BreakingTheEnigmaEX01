package Menus;

import Console.UserConsole;

import java.util.Scanner;

//public UserConsole userConsole;

public class LoadNewFile implements RunTheMenuInterface {

    public UserConsole userConsole;
    public LoadNewFile(UserConsole userConsole){
        this.userConsole=userConsole;
    }

    @Override
    public void execution(int userChoice) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please insert full xml path");
        String loadStart = scanner.nextLine();
        boolean isFileNameValid=false;
        boolean isisFileLoadSuccessfully=false;
        while (!isFileNameValid){
            if (userConsole.fileNameValidation(loadStart)) {
                isFileNameValid = true;
                while (!isisFileLoadSuccessfully) {
                    try {
                        if (userConsole.isFileLoadSuccessfully(loadStart)) {
                            isisFileLoadSuccessfully = true;
                            System.out.println("The xml was uploaded successfully");
                            break;
                        }
                        else {
                            System.out.println("Please insert updated xml path");
                            loadStart = scanner.nextLine();
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            else {
                System.out.println("Please insert full xml path");
                loadStart = scanner.nextLine();
            }
        }

    }
}
