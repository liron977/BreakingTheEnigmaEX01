package Menus;

import Console.UserConsole;

public class InitCodeConfigurationAutomatically implements MenuManager {

    private UserConsole userConsole;
    public InitCodeConfigurationAutomatically (UserConsole userConsole){
        this.userConsole = userConsole;
    }
    @Override
    public void execution() {

        userConsole.initCodeConfigurationAutomatically();

    }
}
