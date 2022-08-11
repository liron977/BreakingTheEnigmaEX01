package Engine.validator;

import Engine.TheEnigmaEngine.TheMachineEngine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuValidator implements Validator, Serializable {

    private List<Exception> exceptionList;
    private Boolean isMachineDefined;
    private Boolean isCodeDefined;

    public MenuValidator(){
        exceptionList=new ArrayList<>();
        isCodeDefined=false;
        isMachineDefined=false;

    }
    public void isXmlLoaded(){
        if(!isMachineDefined){
            exceptionList.add(new Exception("The machine is not defined yet,please complete Step 1 first"));
        }
    }
    public void isCodeConfigurationWasDefined(){
        if(!isCodeDefined){
            exceptionList.add(new Exception("The code configuration is not defined yet,please complete step 3 or 4 first"));
        }
    }

    public void setTrueValueToIsMachineDefined(){
        isMachineDefined=true;
    }
    public void setTrueValueToUpdateIsCodeDefined(){
        isCodeDefined=true;
    }
    public void updateExceptionList(){
        exceptionList=new ArrayList<>();
    }
    public void reset(){
        updateExceptionList();
        isCodeDefined=false;
        isMachineDefined=false;
    }
    @Override
    public void validate() {

    }

    @Override
    public List<Exception> getListOfException() {
        return exceptionList;
    }
}