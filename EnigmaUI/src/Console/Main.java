package Console;

import Console.UiMenu;
import EngineManager.EngineManager;

public class Main {

    public static void main(String[] args) {
        EngineManager engineManager = new EngineManager();
        UserConsole userConsole = new UserConsole(engineManager);
        UiMenu uiMenu=new UiMenu(userConsole);
        uiMenu.start();
    }
}
