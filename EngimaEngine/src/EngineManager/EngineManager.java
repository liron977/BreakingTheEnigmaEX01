package EngineManager;

import Engine.validator.*;
import Exceptions.XmlException;
import Xml.XmlHelper;
import schemaGenerated.CTEEnigma;
import Engine.*;

import java.util.ArrayList;
import java.util.List;


public class EngineManager implements EngineManagerInterface {

    private XmlHelper xmlHelper;
    @Override
    public void load(String filePath) throws XmlException
    {
        CTEEnigma descriptor = (CTEEnigma) XmlHelper.readFromXml(filePath);
        MediatorforSchema mediatorforSchema= new MediatorforSchema(descriptor);
        XmlReflectorValidator xmlReflectorValidator =new XmlReflectorValidator(descriptor);
        XmlRotorValidator xmlRotorValidator=new XmlRotorValidator((descriptor));
        XmlKeyboardValidator xmlKeyboardValidator=new XmlKeyboardValidator(descriptor);
        List<Validator> validators=new ArrayList<>();
        validators.add(xmlKeyboardValidator);
        validators.add(xmlReflectorValidator);
        validators.add((xmlRotorValidator));
        ValidatorRunner validatorRunner=new ValidatorRunner(validators);
        List<Exception> exceptions=  validatorRunner.run(descriptor);
        if(exceptions.size()!=0){
            for (Exception exception:exceptions) {
                System.out.println(exception.getMessage());
                System.out.println("******************");

            }
        }

        //System.out.println(mediatorforSchema.xmlValidation()+" XML");
    }
}
