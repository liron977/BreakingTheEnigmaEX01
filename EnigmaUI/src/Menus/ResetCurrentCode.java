package Menus;

import Console.Mediator;

public class ResetCurrentCode implements MenuManager{

    public Mediator mediator;
    public ResetCurrentCode(Mediator mediator){
        this.mediator = mediator;

    }
    @Override
    public void execution() {
        boolean isMachineWasDefined= mediator.isMachineWasDefined();
        boolean isCodeWasDefined= mediator.isCodeWasDefined();
        if(isMachineWasDefined&&isCodeWasDefined) {
            mediator.resetCurrentCode();
        }
    }
}