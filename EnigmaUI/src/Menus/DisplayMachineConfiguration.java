package Menus;

import Console.UserConsole;
import MachineDTO.TheMachineSettingsDTO;

import java.util.List;

public class DisplayMachineConfiguration implements MenuManager {

    private UserConsole userConsole;

    public DisplayMachineConfiguration(UserConsole userConsole) {
        this.userConsole = userConsole;
    }

    @Override
    public void execution() {

        String output = userConsole.getCurrentCodeConfigurations();
        if (output != null) {
            System.out.println(output);
        }
    }
}