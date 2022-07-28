public class Keyboard {
    private String keyboard;

    public Keyboard (String keyboard){
        this.keyboard=keyboard;
    }
    public int getIndexFromKeyboard(char ch){
        return keyboard.indexOf(ch);
    }
}
