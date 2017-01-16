public class Tiles{
    //~~~~~~~~~~~~~~~~~~~~~Instance Variables~~~~~~~~~~~~~~~~~~~
    private int intValue;

    //~~~~~~~~~~~~~~~~~~~~Default Constuctor~~~~~~~~~~~~~~~~~~~~
    //When a tile spawns, has the value of 2^1 or 2^2
    public Tiles(){
	int randomPow = (int) (Math.random() * 2) + 1 ;
	intValue =(int) Math.pow(2,randomPow);
    }

    //~~~~~~~~~~~~~~~~~~~~~~Methods~~~~~~~~~~~~~~~~~~~~
    
    //returns intValue
    public int getIntVal(){
	return intValue;
    }

    //doubles intValue
    public void doubleTile(){
	intValue *= 2;
    }

    /*
      public boolean isEqual(Equalizer other){
	  return (intValue == other.getIntValue());
      }
*/
    
    public String toString(){
	String retStr = "";
	retStr += intValue;
	return retStr;
    }
    //~~~~~~~~~~~~~~~~~Main Method~~~~~~~~~~~~~~~~~~~~~
    public static void main (String[] args){
	Tiles tester = new Tiles();
	tester.doubleTile();
	System.out.println(tester);
    }
}
