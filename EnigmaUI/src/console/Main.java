package console;

import engineManager.EngineManager;
import engineManager.EngineManagerInterface;

public class Main {
    public static void main(String[] args){
       EngineManagerInterface engineManager=new EngineManager();
       Mediator mediator = new Mediator(engineManager);
       Menu menu =new Menu(mediator);
       menu.start();

    }
}