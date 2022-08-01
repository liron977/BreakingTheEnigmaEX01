package EngineManager;
import java.io.*;
import Exceptions.XmlException;
import Engine.*;
import Engine.validator.*;
public interface EngineManagerInterface {

    void load(String filePath) throws XmlException; //throws XmlException;
}
