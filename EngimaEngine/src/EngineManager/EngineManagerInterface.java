package EngineManager;

import MachineDTO.ListOfExceptionsDTO;
import MachineDTO.MachineDTO;

public interface EngineManagerInterface {

    ListOfExceptionsDTO load(String filePath) throws Exception; //throws XmlException;
    ListOfExceptionsDTO getAllErrorsRelatedToFilePath(String filePath);
    public ListOfExceptionsDTO getAllErrorsRelatedToinitCodeManuallyInputStructure(String str);
    public ListOfExceptionsDTO getAllErrorsRelatedToChosenManuallyRotors(String str);
    public void initCodeAutomatically();

}
