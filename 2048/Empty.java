public class Empty implements Equalizer{
    //~~~~~~~~~~~~~~~~~~Instance Variables~~~~~~~~~~~~~~~~~~
    private int intValue;

    //~~~~~~~~~~~~~~~~~Default Constructor~~~~~~~~~~~~~
    //
    public Empty(){
	intValue = 0;
    }

    //~~~~~~~~~~~~~~~~~~Methods~~~~~~~~~~~~~~~~~~~
    public String toString(){
	return "    ";
    }

    
    //returns intValue
    public int getIntVal(){
	return intValue;
    }
    
    /*
    public boolean isEqual(Equalizer other){
	return (intValue == other.getIntValue());
    }
    */
    
    //~~~~~~~~~~~~~~~~~~~Main Method~~~~~~~~~~~~~~~~~
    public static void main(String[] args){
	Empty tester = new Empty();
	System.out.println(tester);
    }
}
