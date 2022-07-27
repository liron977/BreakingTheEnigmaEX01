import java.util.ArrayList;
import java.util.List;

public class RotorsSet {
   private List<Rotor> rotors =new ArrayList<>();
    private int rotorsAmount;
    public RotorsSet(List<Rotor> rotors){
        this.rotors=rotors;
        this.rotorsAmount=rotors.size();
    }
    public void manageSpins(){

        for(int i=0;i<rotorsAmount-1;i++) {
           if(i==0){
               rotors.get(0).spinRotor();
              if(rotors.get(0).isNotchLocatedInWindow()){
                  rotors.get(1).spinRotor();
              }
           }
           else{
               if(rotors.get(i).isNotchLocatedInWindow()){
                   rotors.get(i+1).spinRotor();
               }
           }
        }
    }

}


