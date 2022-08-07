package EngineManager;

import MachineDTO.ConvertedStringDTO;
import MachineDTO.ListOfExceptionsDTO;
import MachineDTO.MachineHistoryAndStatisticsDTO;
import MachineDTO.TheMachineSettingsDTO;

import java.util.List;

public interface EngineManagerInterface {

    ListOfExceptionsDTO load(String filePath) throws Exception; //throws XmlException;
    ListOfExceptionsDTO getAllErrorsRelatedToFilePath(String filePath);
    public ListOfExceptionsDTO getAllErrorsRelatedToInitCodeManuallyInputStructure(String str);
    public ListOfExceptionsDTO getAllErrorsRelatedToChosenManuallyRotors(String str);
    public ListOfExceptionsDTO getAllErrorsRelatedToChosenManuallyStartingPosition(String str);
    public ListOfExceptionsDTO getAllErrorsRelatedToChosenManuallyReflectorId(String str);
    public ListOfExceptionsDTO getAllErrorsRelatedToChosenManuallyPlagBoard(String str);
    public void initCodeAutomatically();
    public TheMachineSettingsDTO getTheMachineSettingsDTO();

   public ListOfExceptionsDTO getAllErrorsConvertingInputProcess(String str);
   public ConvertedStringDTO getConvertedString(String str);
    public void resetCurrentCode();
    public List<MachineHistoryAndStatisticsDTO> getHistoryAndStatisticsDTO();
        public void chooseManuallyStartingPosition(String userInput);
    public void chooseManuallyPlugBoard(String userInput);
    public void DefineIsCodeConfigurationSetValueToTrue();
}