package Menus;

import Console.Mediator;

public class WriteCurrentStateToFile implements  MenuManager{
    private Mediator mediator;

    public WriteCurrentStateToFile(Mediator mediator){
        this.mediator=mediator;
    }
    @Override
    public void execution() {
        mediator.writeCurrentStateToFile();
    }
}
