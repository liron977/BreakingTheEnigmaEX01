import java.util.Collections;

public class TheMachineEngine {
    private RotorsSet rotorsSet;
    private Reflector reflector;
    private Keyboard keyboard;

    public TheMachineEngine(RotorsSet rotorsSet,Reflector reflector,Keyboard keyboard){
        this.rotorsSet=rotorsSet;
        this.reflector=reflector;
        this.keyboard=keyboard;

    }

    public char manageDecode(char signal){
        char entryValue='C';
        int indexOfSignal;
        Rotor tmpRotor=null;

        rotorsSet.manageSpins();
         indexOfSignal= keyboard.getIndexFromKeyboard(signal);
        for (Rotor rotor: rotorsSet.getListOfRotors()) {
            entryValue=rotor.getEntryValueFromRotorByIndex(indexOfSignal);
            indexOfSignal=rotor.getIndexFromRotorByEntryValue(entryValue);
        }
        indexOfSignal=reflector.getExitIndexFromTheReflector(indexOfSignal);
        Collections.reverse(rotorsSet.getListOfRotors());

        for (Rotor rotor:rotorsSet.getListOfRotors()) {
            entryValue=rotor.getExitValueFromRotorByIndex(indexOfSignal);
            indexOfSignal=rotor.getIndexFromRotorByExitValue(entryValue);
            tmpRotor=rotor;
        }
        Collections.reverse(rotorsSet.getListOfRotors());
        if(tmpRotor!=null){
            indexOfSignal=tmpRotor.getIndexFromRotorByExitValue(entryValue);
            entryValue=keyboard.getCharacterFromKeyboardByIndex(indexOfSignal);

        }


        return  entryValue;



    }


}
