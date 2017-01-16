

public class Tiles implements Equalizer{
    //~~~~~~~~~~~~~~~~~~~~~Instance Variables~~~~~~~~~~~~~~~~~~~
    private int intValue;

    //~~~~~~~~~~~~~~~~~~~~Default Constuctor~~~~~~~~~~~~~~~~~~~~
    //When a tile spawns, has the value of 2^1 or 2^2
    public Tiles(){
	int randomPow = (int) (Math.random() * 2) + 1 ;
	intValue =(int) Math.pow(2,randomPow);
    }

    //~~~~~~~~~~~~~~~~~~~~~~Methods~~~~~~~~~~~~~~~~~~~~~~~~~~

    //returns a String representation of the instance
    public String toString(){
	if (intValue < 10)//if it's a single digit
	    return "   " + intValue;
	else if (intValue < 100)//if it's two-digit
	    return "  " + intValue;
	else if (intValue < 100)//if it's three-digit
	    return " " + intValue;
	else // > 3 digits
	    return intValue;
    }
   
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
    
    //~~~~~~~~~~~~~~~~~Main Method~~~~~~~~~~~~~~~~~~~~~
    public static void main (String[] args){
	Tiles tester = new Tiles();
	tester.doubleTile();
	System.out.println(tester);
    }
}
