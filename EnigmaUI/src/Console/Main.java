package Console;

import EngineManager.EngineManager;
import EngineManager.EngineManagerInterface;

public class Main {

    public static void main(String[] args) {

        String str ="<45,27,94><AO!><III><A|Z,D|E>";
        String[] out=str.split("<");
        for (int i=0;i< out.length;i++){

            String[] out3=out[i].split(">");
            for (int j=0;j< out3.length;j++)
            {
                System.out.println(out3[j]);

            }


        }
       EngineManagerInterface engineManager=new EngineManager();

       // EngineManager engineManager = new EngineManagerInterface();
        UserConsole userConsole = new UserConsole(engineManager);
        UiMenu uiMenu=new UiMenu(userConsole);
        uiMenu.start();

    }
}
