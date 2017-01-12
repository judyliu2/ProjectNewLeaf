public class Grid<t>{

    //~~~~~~~~~~~~~~Instance Vars~~~~~~~~~~~~~~~~~~~~~
    private t[][] userGrid;
    private int placesFilled;
    private int maxPlaces;
    private t nullValue;
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //~~~~~~~~~~~~~~~constructor~~~~~~~~~~~~~~~~~~~~~~
    public Grid(int row, int column, t nullVal){

    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //~~~~~~~~~~~~~~~~~methods~~~~~~~~~~~~~~~~~~~~~~~
    public String toString(){
	String retStr = "";
	for (t[] row: userGrid){
	    for (t column: row){
		retStr += column + "|";
	    }
	    retStr += "\n";
	}
	return retStr;
    }

    public void remove(int i, int j){
	
    }

    public t set(int i, int j, t object){

    }

    public void leftJustify(){

    }

    public void rightJustify(){
	for (int y = 0; y < userGrid.length; y++){
	    if ( userGrid[y].length != 4) {
		for(int x = user.length - 1; x > 0; x--){
		    set(x, y, get(x - 1, y));
		}
	    }
	    
	}
    }

    public void upJustify(){

    }

    public void downJustify(){

    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    //~~~~~~~~~~~~main~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static void main(String[] args){

    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
}
