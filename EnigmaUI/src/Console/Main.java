package Console;

import Console.UiMenu;
import EngineManager.EngineManager;

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

        EngineManager engineManager = new EngineManager();
        UserConsole userConsole = new UserConsole(engineManager);
        UiMenu uiMenu=new UiMenu(userConsole);
        uiMenu.start();

    }
}
