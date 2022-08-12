package MachineDTO;

public class TheMachineSettingsDTO {
    private int amountOfUsedRotors;
    private int maxAmountOfRotors;
   /* private List<String> notchPosition=new ArrayList<>();
    private  List<String> originalNotchPosition=new ArrayList<>();*/
    private int amountOfReflectors;
    private int amountOfProcessedMessages;

    private CodeDescriptionDTO codeDescriptionDTO;

     public TheMachineSettingsDTO(int amountOfUsedRotors, int maxAmountOfRotors, int amountOfReflectors, int amountOfProcessedMessages, CodeDescriptionDTO codeDescriptionDTO){
this.amountOfProcessedMessages=amountOfProcessedMessages;
this.amountOfReflectors=amountOfReflectors;
//this.notchPosition=notchPosition;
//this.originalNotchPosition=originalNotchPosition;
this.maxAmountOfRotors=maxAmountOfRotors;
this.codeDescriptionDTO = codeDescriptionDTO;
this.amountOfUsedRotors=amountOfUsedRotors;
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