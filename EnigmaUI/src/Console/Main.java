package Console;

import EngineManager.EngineManager;
import EngineManager.EngineManagerInterface;

public class Main {

    public static void main(String[] args){
       EngineManagerInterface engineManager=new EngineManager();
       UserConsole userConsole = new UserConsole(engineManager);
       Menu menu =new Menu(userConsole);
       menu.start();

    }
}