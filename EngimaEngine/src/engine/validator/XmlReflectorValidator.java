package engine.validator;

import schemaGenerated.CTEEnigma;
import schemaGenerated.CTEReflect;
import schemaGenerated.CTEReflector;

import java.util.*;

public class XmlReflectorValidator implements Validator {
    private List<String> romanNumeralsList;
    private CTEEnigma enigmaDescriptor;
    private List<Exception> errors;
    public XmlReflectorValidator(CTEEnigma enigmaDescriptor) {
        this.enigmaDescriptor = enigmaDescriptor;
        errors=new ArrayList<>();
        romanNumeralsList=new ArrayList<>();
    }
    private void initRomanNumeralsList() {
        romanNumeralsList.add("I");
        romanNumeralsList.add("II");
        romanNumeralsList.add("III");
        romanNumeralsList.add("IV");
        romanNumeralsList.add("V");
    }

    @Override
    public void validate(){
        isReflectorIdIsRomanNumerals();
        isEachReflectorHasUniqId();
        isSignalMappedToItselfInReflector();
        isReflectorhasDoubleMapping();
        amountOfReflectorMapped();
    }
    @Override
    public List<Exception> getListOfException(){
        return errors;
    }
    private void isReflectorIdIsRomanNumerals() {
        initRomanNumeralsList();
        List<CTEReflector> cteReflectorList = enigmaDescriptor.getCTEMachine().getCTEReflectors().getCTEReflector();
        String reflectorId;
        for (CTEReflector cteReflector : cteReflectorList) {
            reflectorId = cteReflector.getId().toUpperCase();
            if (!romanNumeralsList.contains(reflectorId)) {
                errors.add(new Exception("The reflector id :[" +reflectorId + "] is not valid." +
                        "reflector id can be only a roman char {I,II,III,IV,V}" +
                        "Please update the file "));
            }
        }
    }
    private void isEachReflectorHasUniqId() {
        HashMap<String, Integer> idHashMap = new HashMap<>();
        List<CTEReflector> cteReflectorList = enigmaDescriptor.getCTEMachine().getCTEReflectors().getCTEReflector();
        String reflectorId;

        for (CTEReflector cteReflector : cteReflectorList) {
            List<CTEReflect> cteReflects = cteReflector.getCTEReflect();
            reflectorId = cteReflector.getId().toUpperCase();
            if ((idHashMap.get(reflectorId) != null) && (idHashMap.get(reflectorId) > 0)) {
                errors.add(new Exception("Each reflector id must be uniq," +
                        "the id ["+ reflectorId +"] appears more than one time"));
            }
            idHashMap.put(reflectorId, 1);
        }

        isReflectorsIdAreOrder(idHashMap);
    }
    private void isReflectorsIdAreOrder(HashMap<String, Integer> idHashMap)
    {
        String previousId = "I";
        SortedSet<String> keys = new TreeSet<>(idHashMap.keySet());
        for (String key : keys) {
            switch (key) {
                case "II":
                    if(!previousId.equals("I")){
                        errors.add(new Exception("The different reflector`s id should be a running counter starting from I," +
                                "it can be unorganized."+"you have id ["+previousId +"] and after II " ));
                    }
                    break;
                case "III":
                    if(!previousId.equals("II")){
                        errors.add(new Exception("The different reflector`s id should be a running counter starting from I," +
                                "it can be unorganized."+"you have id ["+previousId +"] and after III " ));
                    }
                    break;
                case "IV":
                    if(!previousId.equals("III")){
                        errors.add(new Exception("The different reflector`s id should be a running counter starting from I," +
                                "it can be unorganized."+"you have id ["+previousId +"] and after IV " ));
                    }
                    break;
                case "V":
                    if(!previousId.equals("IV")){
                        errors.add(new Exception("The different reflector`s id should be a running counter starting from I," +
                                "it can be unorganized."+"you have id ["+previousId +"] and after V " ));
                    }
                    break;
            }
            previousId = key;
        }
    }
    private void isSignalMappedToItselfInReflector(){
        char left,right;
        List<CTEReflector> cteReflectorList = enigmaDescriptor.getCTEMachine().getCTEReflectors().getCTEReflector();
        for (CTEReflector cteReflector : cteReflectorList) {
            List<CTEReflect> cteReflects = cteReflector.getCTEReflect();
            String reflectorId=cteReflector.getId().toUpperCase();
            for (CTEReflect reflect:cteReflects) {
                left=(char)(reflect.getInput()+'0');
                right=(char)(reflect.getOutput()+'0');
                if(left==right){
                    errors.add(new Exception("The reflector ["+reflectorId+"] is illegal,signal can not be mapped to itself" ));
                }
            }
        }
    }


    private void isReflectorhasDoubleMapping(){
        String left,right;
        List<CTEReflector> cteReflectorList = enigmaDescriptor.getCTEMachine().getCTEReflectors().getCTEReflector();
        for (CTEReflector cteReflector : cteReflectorList) {
            HashMap<String, Integer> leftSignalMap = new HashMap<>();
            HashMap<String, Integer> rightSignalMap = new HashMap<>();
            List<CTEReflect> cteReflects = cteReflector.getCTEReflect();
            String reflectorId=cteReflector.getId().toUpperCase();
            for (CTEReflect reflect:cteReflects) {
                left=String.valueOf(reflect.getInput());
                right=String.valueOf(reflect.getOutput());
                if((leftSignalMap.get(left)!=null)&&(leftSignalMap.get(left)!=0)) {
                    errors.add(new Exception("The reflector ["+reflectorId+"] is illegal,reflector can not have double mapping the left signal "+left +" is already mapped" ));
                }
                leftSignalMap.put(left,1);
                if((rightSignalMap.get(right)!=null)&&(rightSignalMap.get(right)!=0)) {
                    errors.add(new Exception("The reflector ["+reflectorId+"] is illegal,reflector can not have double mapping the right signal "+right +" is already mapped" ));
                }
                rightSignalMap.put(right,1);
            }
        }
    }
    private void amountOfReflectorMapped() {
        List<CTEReflector> cteReflectorList = enigmaDescriptor.getCTEMachine().getCTEReflectors().getCTEReflector();
        int counter;
        for (CTEReflector cteReflector : cteReflectorList) {
            counter=0;
            String reflectorId=cteReflector.getId().toUpperCase();
            List<CTEReflect> cteReflects = cteReflector.getCTEReflect();
            for (CTEReflect reflect : cteReflects) {
                counter++;
            }
            if(counter*2!=enigmaDescriptor.getCTEMachine().getABC().trim().length()){
                errors.add(new Exception("The reflector ["+reflectorId+"] is illegal,the amount of mappings should be half of ABC" ));
            }
        }
    }
}