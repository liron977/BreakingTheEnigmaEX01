package EngineManager;

import Engine.TheEnigmaEngine.TheMachineEngine;
import MachineDTO.*;

import java.util.Currency;
import java.util.List;

public interface EngineManagerInterface {

    ListOfExceptionsDTO load(String filePath) throws Exception; //throws XmlException;
    ListOfExceptionsDTO getAllErrorsRelatedToFilePath(String filePath);
   // public ListOfExceptionsDTO getAllErrorsRelatedToInitCodeManuallyInputStructure(String str);
    public ListOfExceptionsDTO getAllErrorsRelatedToChosenManuallyRotors(String str);
    public ListOfExceptionsDTO getAllErrorsRelatedToChosenManuallyStartingPosition(String str);
    public ListOfExceptionsDTO getAllErrorsRelatedToChosenManuallyReflectorId(String str);
    public ListOfExceptionsDTO getAllErrorsRelatedToChosenManuallyPlagBoard(String str);
    public CurrentCodeDescriptionDTO initCodeAutomatically();
    public TheMachineSettingsDTO getTheMachineSettingsDTO();
    public boolean isChooseToExit(String userInput);
   public ListOfExceptionsDTO getAllErrorsConvertingInputProcess(String str);
   public ConvertedStringDTO getConvertedString(String str);
    public void resetCurrentCode();
    public boolean getIsCodeConfigurationSet();
    public List<MachineHistoryAndStatisticsDTO> getHistoryAndStatisticsDTO();
    public void chooseManuallyStartingPosition(String userInput);
    public void chooseManuallyPlugBoard(String userInput);
    public void DefineIsCodeConfigurationSetValueToTrue();
    public TheMachineEngine buildTheMachineEngine();
    public ListOfExceptionsDTO getAllErrorsRelatedToUserDefinePlugBoard(String userInput);
    public ListOfExceptionsDTO getAllErrorsRelatedToMachineMenuValidator();
    public void updateExceptionListMenuValidator();
    public ListOfExceptionsDTO getAllErrorsRelatedToInitCodeMenuValidator();
    }