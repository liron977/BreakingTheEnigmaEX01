package HistoryAndStatistics;

import MachineDTO.CurrentCodeDescriptionDTO;

import java.util.*;

public class MachineHistoryAndStatistics {


    Map<CurrentCodeDescriptionDTO, List<HistoryOfProcess>> machineHistory = new HashMap<>();

    public void addNewMachineSettings(CurrentCodeDescriptionDTO currentCodeDescriptionDTO) {

        List<HistoryOfProcess> historyOfProcesses = new ArrayList<HistoryOfProcess>();
        machineHistory.put(currentCodeDescriptionDTO, historyOfProcesses);
    }

    public Map<CurrentCodeDescriptionDTO, List<HistoryOfProcess>> getMachineHistory() {
        return machineHistory;
    }

    public void addNewProcess(CurrentCodeDescriptionDTO currentCodeDescriptionDTO, HistoryOfProcess newProcess) {
        List<HistoryOfProcess> historyOfProcesses = machineHistory.get(currentCodeDescriptionDTO);
        if (historyOfProcesses == null) {
            historyOfProcesses = new ArrayList<HistoryOfProcess>();
        }
        historyOfProcesses.add(newProcess);
        machineHistory.put(currentCodeDescriptionDTO, historyOfProcesses);
    }

}