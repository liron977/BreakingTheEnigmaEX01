public class Keyboard {
    private String keyboard;
   private int keyboardAmount;
    public Keyboard (String keyboard){
        this.keyboard=keyboard;
        this.keyboardAmount=keyboard.length();
    }
    public int getIndexFromKeyboard(char ch){
        return keyboard.indexOf(ch);
    }
    public char getCharacterFromKeyboardByIndex(int index){
        return keyboard.charAt(index);
    }
    public int getKeyboardAmount(){
        return keyboardAmount;
    }
}
