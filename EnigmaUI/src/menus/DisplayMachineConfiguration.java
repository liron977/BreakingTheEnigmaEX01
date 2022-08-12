package menus;

import console.Mediator;

public class DisplayMachineConfiguration implements MenuManager {
    private Mediator mediator;
    public DisplayMachineConfiguration(Mediator mediator) {
        this.mediator = mediator;
    }
    @Override
    public void execution() {
        String output = mediator.getMachineConfiguration();
        if (output != null) {
            System.out.println(output);
        }
    }
}