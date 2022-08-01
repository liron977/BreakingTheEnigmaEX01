package EngineManager;
import java.io.*;
import java.util.List;

import Exceptions.XmlException;
import Engine.*;
import Engine.validator.*;
import MachineDTO.MachineDTO;

import javax.xml.bind.JAXBException;

public interface EngineManagerInterface {

    MachineDTO load(String filePath) throws Exception; //throws XmlException;
    MachineDTO getAllErrorsRelatedToFilePath(String filePath);
}
