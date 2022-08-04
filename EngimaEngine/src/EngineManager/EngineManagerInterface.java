package EngineManager;

import Engine.TheEnigmaEngine.TheMachineEngine;
import MachineDTO.FileDTO;
import MachineDTO.MachineDTO;

public interface EngineManagerInterface {

    FileDTO load(String filePath) throws Exception; //throws XmlException;
    FileDTO getAllErrorsRelatedToFilePath(String filePath);
    public MachineDTO getAllErrorsRelatedToinitCodeConfigurationManually(String str);
    public void initCodeAutomatically();

}
