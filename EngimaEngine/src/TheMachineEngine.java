import java.util.Collections;

public class TheMachineEngine {
    private RotorsSet rotorsSet;
    private Reflector reflector;
    private Keyboard keyboard;
    private PlugsBoard plugsBoard;

    public TheMachineEngine(RotorsSet rotorsSet,Reflector reflector,Keyboard keyboard,PlugsBoard plugsBoard){
        this.rotorsSet=rotorsSet;
        this.reflector=reflector;
        this.keyboard=keyboard;
        this.plugsBoard=plugsBoard;

    }

    public char manageDecode(char signal){
        Rotor tmpRotor=null;

        char entryValue=plugsBoard.getSwappedCharacter(signal);
        rotorsSet.manageSpins();
        int indexOfSignal= keyboard.getIndexFromKeyboard(entryValue);
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
            entryValue=plugsBoard.getSwappedCharacter(entryValue);
        }
        return  entryValue;



    }


}
