package Engine.TheEnigmaEngine;

import MachineDTO.CurrentCodeDescriptionDTO;

import java.util.*;

public class MachineHistoryAndStatistics {

    Map<CurrentCodeDescriptionDTO, List<HistoryOfProcess>> machineHistory=new HashMap<>();
    public void addNewMachineSettings(CurrentCodeDescriptionDTO currentCodeDescriptionDTO){

        machineHistory.put(currentCodeDescriptionDTO,new ArrayList<>());
    }
public Map<CurrentCodeDescriptionDTO, List<HistoryOfProcess>> getMachineHistory(){
        return  machineHistory;
}
    public void addNewProcess(CurrentCodeDescriptionDTO currentCodeDescriptionDTO, HistoryOfProcess newProcess){
        machineHistory.get(currentCodeDescriptionDTO).add(newProcess);
    }



    }