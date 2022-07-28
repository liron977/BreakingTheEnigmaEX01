import java.util.ArrayList;
import java.util.List;

public class PlugsBoard {
 private Keyboard keybord;
 private int amountOfSwappingPairs;
 private List<Pair> pairsOfSwappingCharacter = new ArrayList<>();

 public PlugsBoard(Keyboard keybord,List<Pair> pairsOfSwappingLetters){
  this.keybord=keybord;
  amountOfSwappingPairs=keybord.getKeyboardAmount();
  this.pairsOfSwappingCharacter=pairsOfSwappingLetters;
  this.amountOfSwappingPairs=amountOfSwappingPairs;
 }
 public char getSwappedCharacter(char character) {
  for (Pair pair: pairsOfSwappingCharacter) {
       if(pair.getEntry()==character){
        return pair.getExit();
       }
       else if(pair.getExit()==character){
        return pair.getEntry();
   }
  }
  return character;
 }
}
