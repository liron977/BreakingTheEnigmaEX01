package Menus;

import Console.Mediator;

import java.util.Scanner;

//public UserConsole userConsole;

public class LoadNewFile implements MenuManager {

    private Mediator mediator;
   private boolean isFileLoadSuccessfully;
   private boolean isFileNameValid;
    public LoadNewFile(Mediator mediator){
        this.mediator = mediator;
        isFileLoadSuccessfully=false;
        isFileNameValid=false;
    }

    @Override
    public void execution() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please insert full xml path \n");
        String loadStart = scanner.nextLine();
        //loadStart="C:\\Users\\97254\\IdeaProjects\\BreakingTheEnigma\\EngimaEngine\\src\\resources\\ex1-sanity-small.xml";
        //loadStart="C:\\Users\\chen3\\IdeaProjects\\BreakingTheEnigma\\EngimaEngine\\src\\resources\\small.xml";
        //loadStart="C:\\Users\\97254\\IdeaProjects\\BreakingTheEnigma\\EngimaEngine\\src\\resources\\ex1-error-3.xml";
        while (!isFileNameValid){
            if (mediator.fileNameValidation(loadStart)) {
                isFileNameValid = true;
                while (!isFileLoadSuccessfully) {
                    try {
                        if (mediator.isFileLoadSuccessfully(loadStart)) {
                            isFileLoadSuccessfully = true;
                            System.out.println("The xml was uploaded successfully");
                            break;
                        }
                        else {
                            System.out.println("Please insert updated xml path,if you want to exit please press ENTER");
                            loadStart = scanner.nextLine();
                            exitFromLoadNewFile(loadStart);

                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage()+"if you want to exit please press ENTER");
                        loadStart = scanner.nextLine();
                        exitFromLoadNewFile(loadStart);

                    }
                }
            }
            else {
                System.out.println("Please insert full xml path,if you want to exit please press ENTER");
                loadStart = scanner.nextLine();
                exitFromLoadNewFile(loadStart);
            }
        }

    }
    private void exitFromLoadNewFile(String loadStart){
        if(loadStart.equals("")){
            isFileLoadSuccessfully=true;
            isFileNameValid=true;
        }
    }

}