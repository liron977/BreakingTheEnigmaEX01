package Engine.validator;

import schemaGenerated.CTEEnigma;

import java.util.List;

public interface Validator {

    public void validate();
    public List<Exception> getListOfException();
}
