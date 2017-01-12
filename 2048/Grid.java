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

    public void remove(int row, int column){
	
    }

    public t set(int row, int column, t object){

    }

    public t get(int row, int column){
	return userGrid[row][column];
    }
    
    public void swap(int row1, int column1, int row2, int column2){
        set(row1,column1,(set(row2,column2,get(row1,column1))));
    }

    public void leftJustify(){
	for (int row = 0; row < userGrid.length; row ++){
	    for (int pass = 0; pass < userGrid[0].length - 1; pass ++){
		for (int column = userGrid[0].length - 1; column > 0; column --){
		    if (get(row,column -1).equals(nullValue)){
			swap(row, column, row, column - 1);
		    }
		}	    
	    }
	}
    }

    public void rightJustify(){

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
