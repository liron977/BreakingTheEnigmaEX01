package Menus;

import Console.UserConsole;

public class DisplayHistoryAndStatistics implements MenuManager{
    private UserConsole userConsole;
    public DisplayHistoryAndStatistics(UserConsole userConsole){
        this.userConsole=userConsole;
    }
    @Override
    public void execution() {
        userConsole.getHistoryAndStatistics();
    }
}