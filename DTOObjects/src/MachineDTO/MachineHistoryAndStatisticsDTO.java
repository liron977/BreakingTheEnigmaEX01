package MachineDTO;

public class MachineHistoryAndStatisticsDTO {
    CurrentCodeDescriptionDTO currentCodeDescriptionDTO;
    HistoryAndStatisticsDTO historyAndStatisticsDTO;
    public MachineHistoryAndStatisticsDTO(CurrentCodeDescriptionDTO currentCodeDescriptionDTO,HistoryAndStatisticsDTO historyAndStatisticsDTO){
        this.historyAndStatisticsDTO=historyAndStatisticsDTO;
        this.currentCodeDescriptionDTO=currentCodeDescriptionDTO;

    }

    public HistoryAndStatisticsDTO getHistoryAndStatisticsDTO() {
        return historyAndStatisticsDTO;
    }

    public CurrentCodeDescriptionDTO getCurrentCodeDescriptionDTO() {
        return currentCodeDescriptionDTO;
    }
}