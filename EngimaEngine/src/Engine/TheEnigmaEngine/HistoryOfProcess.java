package Engine.TheEnigmaEngine;

public class HistoryOfProcess {


    String userInput;
    String convertedInput;
    long timeToProcess;

    public HistoryOfProcess(String userInput, String convertedInput, long timeToProcess){
        this.userInput=userInput;
        this.convertedInput=convertedInput;
        this.timeToProcess=timeToProcess;

    }

    public long getTimeToProcess() {
        return timeToProcess;
    }

    public String getConvertedInput() {
        return convertedInput;
    }

    public String getUserInput() {
        return userInput;
    }
}