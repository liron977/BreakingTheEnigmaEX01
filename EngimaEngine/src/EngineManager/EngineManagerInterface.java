package EngineManager;

import MachineDTO.FileDTO;

public interface EngineManagerInterface {

    FileDTO load(String filePath) throws Exception; //throws XmlException;
    FileDTO getAllErrorsRelatedToFilePath(String filePath);
}
