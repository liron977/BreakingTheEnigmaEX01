import java.util.*;

import Enums.RomanNumerals;
import schemaGenerated.*;

public class SchemaGenerated /*implements Serializable*/ {
    /* private Keyboard keyboard;
    private RotorsSet rotorsSet;
    private ReflectorsSet reflectorsSet;
   private PlugsBoard plugsBoard;
   private  TheMachineEngine theMachineEngine;*/
    private CTEEnigma enigmaDescriptor;
    private String keyboardInput;

    private List<String> romanNumeralsList = new ArrayList<>();

    public SchemaGenerated(CTEEnigma enigmaDescriptor) {
        this.enigmaDescriptor = enigmaDescriptor;
        this.keyboardInput = enigmaDescriptor.getCTEMachine().getABC().trim();
        initRomanNumeralsList();
     /*  //   Keyboard keyboard= new Keyboard(enigmaDescriptor.getCTEMachine().getABC().trim());
        RotorsSet rotorsSet= createRotorsSet(enigmaDescriptor);
        List<Rotor> rotorsFinal=new ArrayList<>();
        rotorsFinal.add(rotorsSet.getRotorById("1"));
        rotorsFinal.add(rotorsSet.getRotorById("2"));
        //לשנות הכל פשוט לייצר רשימה של רוטרים ואז לבחור את הרוטור המתאים לפי ה-id שהתקבך
         rotorsSet=new RotorsSet(rotorsFinal);
        ReflectorsSet reflectorsSet=createReflectorsSet(enigmaDescriptor);
        Reflector reflector=reflectorsSet.searchReflectorById("I");

        List<Pair> plugs=new ArrayList<>();
       // plugs.add(new Pair('C','B'));
        plugs.add(new Pair('A','F'));
        PlugsBoard plugsBoard=new PlugsBoard(keyboard,plugs);

        TheMachineEngine theMachineEngine=new TheMachineEngine(rotorsSet,reflector,keyboard,plugsBoard);
        Scanner scanner = new Scanner(System.in);

        char userIntegerInput = 0;
        boolean validInput=true;
        do {
            System.out.print("Enter an integer: ");
            userIntegerInput = scanner.next().charAt(0);
            System.out.println(theMachineEngine.manageDecode(userIntegerInput));
        } while (validInput == true);
*/
    }

    public int isXmlValid() {

        int isKeyboardSizeIsEven = isKeyboardSizeIsEven();
        if (isKeyboardSizeIsEven == -2) {
            return -2;
        }
        int isKeyboardIsNotEmpty = isKeyboardIsNotEmpty();
        if (isKeyboardIsNotEmpty == -10) {
            return -10;
        }
        int isRotorsCountEqualsToExistsRotorsAmount = isRotorsCountEqualsToExistsRotorsAmount();
        if (isRotorsCountEqualsToExistsRotorsAmount == -3) {
            return -3;
        }
        int isRotorsAmountIslegal = isRotorsAmountIslegal();
        if (isRotorsAmountIslegal == -4) {
            return -4;
        }
        int isEachRotorHasUniqId = isEachRotorHasUniqId();
        if (isEachRotorHasUniqId == -5) {
            return -5;
        }
        int isRotorsMappedIslegal = isRotorsMappedIslegal();
        if (isRotorsMappedIslegal < 0) {
            return -6;
        }
        int isRotorsSignalslAreValid = isRotorsSignalslAreValid();
        if (isRotorsSignalslAreValid == -11) {
            return -11;
        }
        int isRotorsAmountOfSignalIsLegal = isRotorsAmountOfSignalIsLegal();
        if (isRotorsAmountOfSignalIsLegal == -12) {
            return -12;
        }
        int isNotchPositionLegal = isNotchPositionLegal();
        if (isNotchPositionLegal == -7) {
            return -7;
        }

        return 100;
    }

    private void initRomanNumeralsList() {
        romanNumeralsList.add("I");
        romanNumeralsList.add("II");
        romanNumeralsList.add("III");
        romanNumeralsList.add("IV");
        romanNumeralsList.add("V");
    }

    private int isReflectorIdIsRomanNumerals() {
        List<CTEReflector> cteReflectorList = enigmaDescriptor.getCTEMachine().getCTEReflectors().getCTEReflector();
        String reflectorId;
        for (CTEReflector cteReflector : cteReflectorList) {
            List<CTEReflect> cteReflect = cteReflector.getCTEReflect();
            reflectorId = cteReflector.getId();
            if (!romanNumeralsList.contains(reflectorId)) {
                return -8;
            }
        }
        return 8;
    }

/*    private int isEachReflectorHasUniqId() {

        HashMap<String, Integer> idHashMap = new HashMap<>();
        List<CTEReflector> cteReflectorList = enigmaDescriptor.getCTEMachine().getCTEReflectors().getCTEReflector();
        String reflectorId;
        String previousId;

        for (CTEReflector cteReflector : cteReflectorList) {
            List<CTEReflect> cteReflects = cteReflector.getCTEReflect();
            reflectorId = cteReflector.getId();
            if ((idHashMap.get(cteReflector.getId()) != null) && (idHashMap.get(cteReflector.getId()) > 0)) {
                return -13;
            }
            idHashMap.put(cteReflector.getId(), 1);

        }
          *//*  SortedSet<String> keys = new TreeSet<>(idHashMap.keySet());
            for (String key : keys) {
                if (key != previousId + 1) {
                    return -5;
                } else {
                    previousId = key;
                }
            }
            return 5;
        }*//*
    }*/
    private int isNotchPositionLegal(){
        List<CTERotor> cteRotors=enigmaDescriptor.getCTEMachine().getCTERotors().getCTERotor();
        for (CTERotor cteRotor:cteRotors) {
            int notchPosition = cteRotor.getNotch();
            if(notchPosition<1){
                return -7;
            }
            if((notchPosition>keyboardInput.length())){
                return  -7;
            }
        }
        return 7;
    }
    private int isKeyboardIsNotEmpty(){
        int result=-10;
        if(keyboardInput.length()!=0){
            result=10;
        }
        return result;
    }
    public int isRotorsMappedIslegal(){
        List<CTERotor> cteRotors=enigmaDescriptor.getCTEMachine().getCTERotors().getCTERotor();
        for (CTERotor cteRotor:cteRotors) {
             if(isRotorhasDoubleMapping(cteRotor)==-6){
                 return -6;
             }
        }
        return 6;
    }
    public int isRotorsAmountOfSignalIsLegal(){
        List<CTERotor> cteRotors=enigmaDescriptor.getCTEMachine().getCTERotors().getCTERotor();
        int amount=0;
        for (CTERotor cteRotor:cteRotors) {
            List<CTEPositioning> ctePositioning=cteRotor.getCTEPositioning();
            amount=0;
            for (CTEPositioning positing:ctePositioning) {
                amount++;
            }
            if(amount!=keyboardInput.length()){
                return -12;
            }
        }
        return 12;
    }
    public int isRotorhasDoubleMapping(CTERotor cteRotor){
        String left,right;
        HashMap<String, Integer> leftSignalMap = new HashMap<>();
        HashMap<String, Integer> rightSignalMap = new HashMap<>();
        List<CTEPositioning> ctePositioning=cteRotor.getCTEPositioning();
        for (CTEPositioning positing:ctePositioning) {
            left=positing.getLeft();
            if((leftSignalMap.get(left)!=null)&&(leftSignalMap.get(left)!=0)) {
                return -6;
            }
            leftSignalMap.put(left,1);
            right=positing.getRight();
            if((rightSignalMap.get(right)!=null)&&(rightSignalMap.get(right)!=0)) {
                return -6;
            }
            rightSignalMap.put(right,1);
        }
        return 6;
    }
    private int isRotorsSignalslAreValid(){
        List<CTERotor> cteRotors=enigmaDescriptor.getCTEMachine().getCTERotors().getCTERotor();
        for (CTERotor cteRotor:cteRotors) {
            if(isRotorSignalsAreValid(cteRotor)==-11){
                return -11;
            }
        }
        return 11;
    }
    private int isRotorSignalsAreValid(CTERotor cteRotor){
        String left,right;
        List<CTEPositioning> ctePositioning=cteRotor.getCTEPositioning();
        for (CTEPositioning positing:ctePositioning) {
            left=positing.getLeft();
            right=positing.getRight();
            if(!(keyboardInput.toUpperCase().contains(left.toUpperCase()))||(!keyboardInput.toUpperCase().contains(right.toUpperCase()))){
                return -11;
            }
        }
        return 11;
    }
    private int isSignalMappedToItself  (CTERotor cteRotor){
        String left,right;
        List<CTEPositioning> ctePositioning=cteRotor.getCTEPositioning();
        for (CTEPositioning positing:ctePositioning) {
             left=positing.getLeft();
             right=positing.getRight();
             if(left.equals(right)){
                 return -11;
             }
        }
        return 11;
    }
    private void createKeyboard(){
        Keyboard keyboard= new Keyboard(keyboardInput.toUpperCase());
    }
    private int isKeyboardSizeIsEven(){
        int result=-2;
        if((keyboardInput.length()%2)==0){
           result=2;
        }
        return result;
    }
    private int isRotorsAmountIslegal(){
        int rotorsAmountFromFile=enigmaDescriptor.getCTEMachine().getRotorsCount();
        int result=-4;
        if(rotorsAmountFromFile>=2){
            result=4;
        }
        return result;
    }
    private int isEachRotorHasUniqId(){
        HashMap<Integer, Integer> idHashMap = new HashMap<>();
        List<CTERotor> cteRotors=enigmaDescriptor.getCTEMachine().getCTERotors().getCTERotor();
        int previousId=0;
        for (CTERotor cteRotor:cteRotors) {
            if((idHashMap.get(cteRotor.getId())!=null)&&(idHashMap.get(cteRotor.getId())>0)){
              return -5;
            }
            idHashMap.put(cteRotor.getId(),1);
        }
        SortedSet<Integer> keys = new TreeSet<>(idHashMap.keySet());
        for (Integer key : keys) {
           if(key!=previousId+1){
               return -5;
           }
           else{
               previousId=key;
           }
        }
        return 5;
    }
    private int isRotorsCountEqualsToExistsRotorsAmount(){
        int rotorsAmountFromFile=enigmaDescriptor.getCTEMachine().getRotorsCount();
        int countedRotors=countRotorsFromFile();
        int result=-3;
        if(rotorsAmountFromFile<=countedRotors){
            result=3;
        }

        return  result;
    }
    private int countRotorsFromFile(){
        List<CTERotor> cteRotors=enigmaDescriptor.getCTEMachine().getCTERotors().getCTERotor();
        int count=0;
        for (CTERotor cteRotor:cteRotors) {
           count++;
        }
        return count;
    }
    private ReflectorsSet createReflectorsSet(CTEEnigma enigmaDescriptor){
        List<Reflector> reflectorsList=new ArrayList<>();
        List<CTEReflector> cteReflectorList=enigmaDescriptor.getCTEMachine().getCTEReflectors().getCTEReflector();
        String reflectorId;
        Reflector reflector;

        for (CTEReflector cteReflector: cteReflectorList) {
            List<CTEReflect> cteReflect=cteReflector.getCTEReflect();
            reflectorId=cteReflector.getId();
            reflector=new Reflector(reflectorId,crateNewReflector(cteReflect));
            reflectorsList.add(reflector);
        }
        ReflectorsSet reflectorsSet=new ReflectorsSet(reflectorsList);
        return reflectorsSet;
   }
   private List<Pair> crateNewReflector(List<CTEReflect> cteReflect) {
       char left,right;
       Pair pair;
       List<Pair> reflectorPairStructure=new ArrayList<>();
         for (CTEReflect reflect:cteReflect) {
                left=(char)(reflect.getInput()+'0');
                right=(char)(reflect.getOutput()+'0');
                pair=new Pair(left,right);
                reflectorPairStructure.add(pair);
            }
         return  reflectorPairStructure;
   }
    private RotorsSet createRotorsSet(CTEEnigma enigmaDescriptor){
        List<Rotor> rotors =new ArrayList<>();
        List<CTERotor> cteRotors=enigmaDescriptor.getCTEMachine().getCTERotors().getCTERotor();
        for (CTERotor cteRotor:cteRotors) {
           rotors.add(createNewRotor(cteRotor));
        }
        RotorsSet rotorsSet=new RotorsSet(rotors);
        return rotorsSet;

    }

    private Pair createNewPair(CTEPositioning positing){
        String left=positing.getLeft();
        String right=positing.getRight();
        char charLeft=left.charAt(0);
        char charRight=right.charAt(0);
        Pair pair=new Pair(charLeft,charRight);

        return pair;
    }
    private Rotor createNewRotor(CTERotor cteRotor){
        List<Pair> rotorStructure=new ArrayList<>();
        int entriesAmount=0;
        int rotorId=cteRotor.getId();
        String rotorIdString=String.valueOf(rotorId);
        int notchPosition=cteRotor.getNotch();
        List<CTEPositioning> ctePositioning=cteRotor.getCTEPositioning();
        for (CTEPositioning positing:ctePositioning) {
            rotorStructure.add(createNewPair(positing));
            entriesAmount++;
        }
        Rotor newRoter=new Rotor(rotorIdString,notchPosition,entriesAmount,rotorStructure);
        System.out.print("Enter an Char for position: ");
        Scanner scanner = new Scanner(System.in);
        char userIntegerInput = scanner.next().charAt(0);
       int startingPosition = userIntegerInput - 'A' + 1;
       newRoter.initRotorStructure(startingPosition);
        return newRoter;
    }
}
