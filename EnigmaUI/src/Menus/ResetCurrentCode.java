package Menus;

import Console.UserConsole;

public class ResetCurrentCode implements MenuManager{

    public UserConsole userConsole;
    public ResetCurrentCode(UserConsole userConsole){
        this.userConsole=userConsole;

    }
    @Override
    public void execution() {
        userConsole.resetCurrentCode();

    }
}