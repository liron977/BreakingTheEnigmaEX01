package machineDTO;

import java.util.List;

public class TheMachineSettingsDTO {
    private int amountOfUsedRotors;
    private int maxAmountOfRotors;
   /* private List<String> notchPosition=new ArrayList<>();
    private  List<String> originalNotchPosition=new ArrayList<>();*/
    private int amountOfReflectors;
    private int amountOfProcessedMessages;
     List<String> reflectorsId;
    private CodeDescriptionDTO codeDescriptionDTO;

     public TheMachineSettingsDTO(int amountOfUsedRotors, int maxAmountOfRotors, int amountOfReflectors, int amountOfProcessedMessages, CodeDescriptionDTO codeDescriptionDTO,List<String> reflectorsId){
this.amountOfProcessedMessages=amountOfProcessedMessages;
this.amountOfReflectors=amountOfReflectors;
this.maxAmountOfRotors=maxAmountOfRotors;
this.codeDescriptionDTO = codeDescriptionDTO;
this.amountOfUsedRotors=amountOfUsedRotors;
this.reflectorsId=reflectorsId;
     }

    public List<String> getReflectorsId() {
        return reflectorsId;
    }

    public CodeDescriptionDTO getCurrentCodeDescriptionDTO() {
        return codeDescriptionDTO;
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

//    public List<String> getNotchPosition() {
//        return notchPosition;
//    }
//
//    public List<String> getOriginalNotchPosition(){return originalNotchPosition;}


}