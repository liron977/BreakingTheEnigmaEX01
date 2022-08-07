package Console;

import Menus.*;

import java.util.Scanner;

public class UiMenu {

    private final int FIRST_OPTION = 1;
    private final int EXIT_OPTION = 8;
    final int LAST_OPTION = 7;
    UserConsole userConsole;

    public UiMenu(UserConsole userConsole) {
        this.userConsole = userConsole;
    }

    public void printMenu() {
        System.out.println("-------------------------------------------------");
        System.out.println("Select option (press number " + FIRST_OPTION + "-" + EXIT_OPTION + "):");
        System.out.println("1. Load new file");
        System.out.println("2. Get general information about the machine specifications");
        System.out.println("3. Choose an initial code configuration (manually)");
        System.out.println("4. Choose an initial code configuration (automatically)");
        System.out.println("5. Input processing");
        System.out.println("6. Resetting the current code");
        System.out.println("7. History and statistics");
        System.out.println("8. Exit");
        System.out.println("-------------------------------------------------");
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int userChoice = 0;
        do {
            printMenu();
            try {
                userChoice = scanner.nextInt();
                if (userChoice <= LAST_OPTION && userChoice >= FIRST_OPTION) {
                    executeMenu(userChoice);
                } else if (userChoice != EXIT_OPTION) {
                    System.out.println("please select a valid option,between [" + FIRST_OPTION + "-" + EXIT_OPTION + "]");
                }
            }catch (Exception e){
                System.out.println("Insert only numbers between [" + FIRST_OPTION + "-" + LAST_OPTION + "]");
                scanner.nextLine();
            }
        } while (userChoice != EXIT_OPTION);
        System.out.println("Thanks,bye");
    }

    public void executeMenu(int userChoice) {
        switch (userChoice) {
            case 1:
                MenuManager loadNewFile = new LoadNewFile(userConsole);
                loadNewFile.execution();
                break;
       case 2:
           MenuManager displayMachineConfiguration=new DisplayMachineConfiguration(userConsole);
           displayMachineConfiguration.execution();
           break;
        case 3:
            MenuManager initCodeConfigurationManually  =new InitCodeConfigurationManually(userConsole);
            initCodeConfigurationManually.execution();
            break;
      case 4:
          MenuManager initCodeConfigurationAutomatically=new InitCodeConfigurationAutomatically(userConsole);
          initCodeConfigurationAutomatically.execution();
            break;
          case 5:
              MenuManager inputProcess=new InputProcess(userConsole);
              inputProcess.execution();
              break;
       case 6:
           MenuManager resetCurrentCode=new ResetCurrentCode(userConsole);
           resetCurrentCode.execution();
           break;
        case 7:
            MenuManager historyAndStatistics=new DisplayHistoryAndStatistics(userConsole);
            historyAndStatistics.execution();
            break;

        }

        //public void Case1_LoadNewFile() {
      /*  Scanner scanner = new Scanner(System.in);
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
            }*/
        //}

    }
}