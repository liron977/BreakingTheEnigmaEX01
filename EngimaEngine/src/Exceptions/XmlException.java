package Exceptions;

public class XmlException extends Exception {
    /* the function create new xml exception */
    public XmlException(String message){
        super("Error in XML file: " + message);
    }
}
