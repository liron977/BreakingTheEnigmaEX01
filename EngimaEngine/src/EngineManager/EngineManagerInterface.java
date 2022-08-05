package EngineManager;

import MachineDTO.ConvertedStringDTO;
import MachineDTO.ListOfExceptionsDTO;

public interface EngineManagerInterface {

    ListOfExceptionsDTO load(String filePath) throws Exception; //throws XmlException;
    ListOfExceptionsDTO getAllErrorsRelatedToFilePath(String filePath);
    public ListOfExceptionsDTO getAllErrorsRelatedToInitCodeManuallyInputStructure(String str);
    public ListOfExceptionsDTO getAllErrorsRelatedToChosenManuallyRotors(String str);
    public void initCodeAutomatically();

   public ListOfExceptionsDTO getAllErrorsConvertingInputProcess(String str);
   public ConvertedStringDTO getConvertedString(String str);
}