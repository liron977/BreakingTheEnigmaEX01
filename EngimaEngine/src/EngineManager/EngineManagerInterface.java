package EngineManager;

import MachineDTO.ConvertedStringDTO;
import MachineDTO.ListOfExceptionsDTO;
import MachineDTO.TheMachineSettingsDTO;

public interface EngineManagerInterface {

    ListOfExceptionsDTO load(String filePath) throws Exception; //throws XmlException;
    ListOfExceptionsDTO getAllErrorsRelatedToFilePath(String filePath);
    public ListOfExceptionsDTO getAllErrorsRelatedToInitCodeManuallyInputStructure(String str);
    public ListOfExceptionsDTO getAllErrorsRelatedToChosenManuallyRotors(String str);
    public void initCodeAutomatically();
    public TheMachineSettingsDTO getTheMachineSettingsDTO();

   public ListOfExceptionsDTO getAllErrorsConvertingInputProcess(String str);
   public ConvertedStringDTO getConvertedString(String str);
    public void resetCurrentCode();
}