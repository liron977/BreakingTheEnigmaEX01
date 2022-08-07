package Menus;

import Console.UserConsole;

import java.util.Scanner;

//public UserConsole userConsole;

public class LoadNewFile implements MenuManager {

    public UserConsole userConsole;
    public LoadNewFile(UserConsole userConsole){
        this.userConsole=userConsole;
    }

    @Override
    public void execution() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please insert full xml path \n");
        String loadStart = scanner.nextLine();
        loadStart="C:\\Users\\97254\\IdeaProjects\\BreakingTheEnigma\\EngimaEngine\\src\\resources\\ex1-sanity-small.xml";
        //loadStart="C:\\Users\\chen3\\IdeaProjects\\BreakingTheEnigma\\EngimaEngine\\src\\resources\\small.xml";
        //loadStart="C:\\Users\\97254\\IdeaProjects\\BreakingTheEnigma\\EngimaEngine\\src\\resources\\ex1-error-3.xml";
        boolean isFileNameValid=false;
        boolean isFileLoadSuccessfully=false;
        while (!isFileNameValid){
            if (userConsole.fileNameValidation(loadStart)) {
                isFileNameValid = true;
                while (!isFileLoadSuccessfully) {
                    try {
                        if (userConsole.isFileLoadSuccessfully(loadStart)) {
                            isFileLoadSuccessfully = true;
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