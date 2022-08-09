package MachineDTO;

import java.util.ArrayList;
import java.util.List;

public class TheMachineSettingsDTO {
    private int amountOfUsedRotors;
    private int maxAmountOfRotors;
    private List<String> notchPosition=new ArrayList<>();
    private int amountOfReflectors;
    private int amountOfProcessedMessages;

    private CurrentCodeDescriptionDTO currentCodeDescriptionDTO;

     public TheMachineSettingsDTO(int amountOfUsedRotors,int maxAmountOfRotors, List<String> notchPosition,int amountOfReflectors,int amountOfProcessedMessages,CurrentCodeDescriptionDTO currentCodeDescriptionDTO){
this.amountOfProcessedMessages=amountOfProcessedMessages;
this.amountOfReflectors=amountOfReflectors;
this.notchPosition=notchPosition;
this.maxAmountOfRotors=maxAmountOfRotors;
this.currentCodeDescriptionDTO=currentCodeDescriptionDTO;
this.amountOfUsedRotors=amountOfUsedRotors;
     }

    public CurrentCodeDescriptionDTO getCurrentCodeDescriptionDTO() {
        return currentCodeDescriptionDTO;
    }

    public int getAmountOfProcessedMessages() {
        return amountOfProcessedMessages;
    }

    public int getAmountOfReflectors() {
        return amountOfReflectors;
    }

    public int getAmountOfUsedRotors() {
        return amountOfUsedRotors;
    }

    public int getMaxAmountOfRotors() {
        return maxAmountOfRotors;
    }

    public List<String> getNotchPosition() {
        return notchPosition;
    }


}