package Menus;

import Console.Mediator;

public class ReadCurrentStateFromFile implements  MenuManager{

    private Mediator mediator;

    public ReadCurrentStateFromFile(Mediator mediator){
        this.mediator=mediator;
    }
    @Override
    public void execution() {
     mediator.readCurrentStateFromFile();

    }
}
