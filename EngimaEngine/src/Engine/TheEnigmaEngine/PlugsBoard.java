package Engine.TheEnigmaEngine;

import Engine.TheEnigmaEngine.Keyboard;
import Engine.TheEnigmaEngine.Pair;

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
 public String getSwappedCharacter(String str) {
  for (Pair pair: pairsOfSwappingCharacter) {
       if(pair.getEntry().equals(str)){
        return pair.getExit();
       }
       else if(pair.getExit().equals(str)){
        return pair.getEntry();
   }
  }
  return str;
 }
}
