public class Empty{
    //~~~~~~~~~~~~~~~~~~Instance Variables~~~~~~~~~~~~~~~~~~
    private int intValue;

    //~~~~~~~~~~~~~~~~~Default Constructor~~~~~~~~~~~~~
    //
    public Empty(){
	intValue = 0;
    }

    //~~~~~~~~~~~~~~~~~~Methods~~~~~~~~~~~~~~~~~~~
    
    //returns intValue
    public int getIntVal(){
	return intValue;
    }
    
    public boolean isEqual(Equalizer other){
	return (intValue == other.getIntValue());
    }
    

    public String toString(){
	String retStr = "";
	retStr += intValue;
	return retStr;
    }
    
    //~~~~~~~~~~~~~~~~~~~Main Method~~~~~~~~~~~~~~~~~
    public static void main(String[] args){
	Empty tester = new Empty();
	System.out.println(tester);
    }
}
