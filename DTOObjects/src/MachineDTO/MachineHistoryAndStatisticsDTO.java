package MachineDTO;

public class MachineHistoryAndStatisticsDTO {
    private CurrentCodeDescriptionDTO currentCodeDescriptionDTO;
    private HistoryAndStatisticsDTO historyAndStatisticsDTO;
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