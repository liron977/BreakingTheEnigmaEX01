import java.util.ArrayList;
import java.util.List;

public class Rotor {
    private List<Pair> rotorStructure = new ArrayList<>();
    private String rotorId;
    private int startingPosition;
    private Pair notchPair;
    private int entriesAmount;

    Rotor(String rotorId, char startingPosition, String  notchPosition, int entriesAmount, List<Pair> rotorStructure) {
        this.rotorId = rotorId;
        this.startingPosition = startingPosition - 'A' + 1;
        this.entriesAmount = entriesAmount;
        this.rotorStructure = rotorStructure;
        this.notchPair = this.rotorStructure.get(Integer.parseInt(notchPosition) -1);
        initRotorStructure();

    }

    private void initRotorStructure() {
        List<Pair> tmpRotorStructure = new ArrayList<>();
        tmpRotorStructure.addAll(rotorStructure);
        int newPosition;
        for (int i = 0; i < entriesAmount; i++) {
            newPosition = i - startingPosition + 1;
            if (newPosition < 0) {
                newPosition = newPosition + entriesAmount;
            }
            tmpRotorStructure.set(newPosition, rotorStructure.get(i));
        }
        this.rotorStructure = tmpRotorStructure;
    }

    public List<Pair> getRotorStructure() {
        return rotorStructure;
    }

    public void spinRotor() {
        int newPosition;
        List<Pair> tmpRotorStructure = new ArrayList<>();
        tmpRotorStructure.addAll(rotorStructure);
        for (int i = 0; i < entriesAmount; i++) {
            newPosition = i - 1;
            if (newPosition < 0) {
                newPosition = newPosition + entriesAmount;
            }
            tmpRotorStructure.set(newPosition, rotorStructure.get(i));
        }
        rotorStructure = tmpRotorStructure;
    }

    public boolean isNotchLocatedInWindow() {
        return rotorStructure.get(0).equals(notchPair);
    }
}
