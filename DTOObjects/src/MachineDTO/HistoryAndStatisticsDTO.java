package MachineDTO;

public class HistoryAndStatisticsDTO {
   String[] userInput;
    String[] convertedString;
    String[] timeToProcess;
public HistoryAndStatisticsDTO(String[] userInput,String[] convertedString,String[] timeToProcess){
this.userInput=userInput;
this.convertedString=convertedString;
this.timeToProcess=timeToProcess;
}
    public String[] getTimeToProcess() {
        return timeToProcess;
    }

    public String[] getConvertedString() {
        return convertedString;
    }

    public String[] getUserInput() {
        return userInput;
    }
}