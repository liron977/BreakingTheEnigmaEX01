package machineDTO;

public class MachineHistoryAndStatisticsDTO {
    private CodeDescriptionDTO codeDescriptionDTO;
    private HistoryAndStatisticsDTO historyAndStatisticsDTO;
    public MachineHistoryAndStatisticsDTO(CodeDescriptionDTO codeDescriptionDTO, HistoryAndStatisticsDTO historyAndStatisticsDTO){
        this.historyAndStatisticsDTO=historyAndStatisticsDTO;
        this.codeDescriptionDTO = codeDescriptionDTO;

    }

    public HistoryAndStatisticsDTO getHistoryAndStatisticsDTO() {
        return historyAndStatisticsDTO;
    }

    public CodeDescriptionDTO getCurrentCodeDescriptionDTO() {
        return codeDescriptionDTO;
    }
}