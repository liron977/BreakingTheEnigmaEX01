public class Pair {
    private char entry;
    private char exit;

    public Pair(char entry,char exit)
    {
        this.entry=entry;
        this.exit=exit;
    }
    public void setEntry(char entry){
        this.entry=entry;
    }
    public void setExit(char exit){
        this.exit=exit;
    }
    public char getEntry() {
        return entry;
    }
    public char getExit()
    {
        return exit;
    }
    @Override
    public String toString()
    {
      return "(" + this.exit +"," +this.entry +")" +"\n" ;
    }
    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
       }
        Pair other=(Pair) obj;
        return ((this.entry==other.entry)&&(this.exit==other.exit));
    }

}
