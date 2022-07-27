import java.util.ArrayList;
import java.util.List;

public class Rotor {
  private   List<Pair> rotorStructure=new ArrayList<>();
  private String rotorId;
  private int startingPosition;
  private int notchPosition;
  private int entriesAmount;

Rotor(String rotorId, char startingPosition, char notchPosition, int entriesAmount, List<Pair> rotorStructure)
{
    this.rotorId=rotorId;
    this.startingPosition=startingPosition-'A'+1;
    this.notchPosition=(int)notchPosition;
    this.entriesAmount=entriesAmount;
    this.rotorStructure=rotorStructure;
}
public void initRotorStructure()
{
    int arraySize=this.rotorStructure.size();
    List<Pair> tmpRotorStructure=new ArrayList<>();
   tmpRotorStructure.addAll(rotorStructure);
    int newPosition;
    for(int i=0;i<arraySize;i++)
    {
        newPosition=i-startingPosition+1;
        if(newPosition<0)
        {
            newPosition=newPosition+entriesAmount;
        }
        tmpRotorStructure.set(newPosition, rotorStructure.get(i));
    }
    this.rotorStructure=tmpRotorStructure;
}
public List<Pair> getRotorStructure()
    {
        return rotorStructure;
    }


}
