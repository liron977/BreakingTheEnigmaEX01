public class Pair {
    private char entry;
    private char exit;

    public Pair(char entry,char exit)
    {
        this.entry=entry;
        this.exit=exit;
    }
    private char getEntry()
    {
        return entry;
    }
    private char getExit()
    {
        return exit;
    }
    private void setEntry(char entry)
    {
        this.entry=entry;
    }
    private void setexit(char entry)
    {
        this.entry=entry;
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
