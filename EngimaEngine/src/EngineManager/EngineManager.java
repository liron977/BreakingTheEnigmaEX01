package EngineManager;

import Exceptions.XmlException;
import Xml.XmlHelper;
import schemaGenerated.CTEEnigma;



public class EngineManager implements EngineManagerInterface {

    private XmlHelper xmlHelper;
    @Override
    public void load(String filePath) //throws XmlException
    {
        CTEEnigma descriptor = (CTEEnigma) xmlHelper.readFromXml(filePath);
        System.out.println("name of first country is: " + descriptor.getCTEMachine().getABC());

       // MediatorforSchema mediatorforSchema= new MediatorforSchema(descriptor);

    }
}
