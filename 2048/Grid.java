public class Grid<t>{

    //~~~~~~~~~~~~~~Instance Vars~~~~~~~~~~~~~~~~~~~~~
    private Object[][] userGrid;
    private int placesFilled;
    private int maxPlaces;
    private t nullValue;
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //~~~~~~~~~~~~~~~constructor~~~~~~~~~~~~~~~~~~~~~~
    public Grid(int row, int column, t nullVal){
	userGrid = new Object[row][column];
	nullValue = nullVal;
	for (int x = 0; x < row; x ++){
	    for (int y = 0; y < column; y ++){
		userGrid[x][y] = nullValue;
	    }
	}
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //~~~~~~~~~~~~~~~~~methods~~~~~~~~~~~~~~~~~~~~~~~
    public String toString(){
	String retStr = "";
	for (Object[] row: userGrid){
	    for (Object column: row){
		retStr += column + "|";
	    }
	    retStr += "\n";
	}
	return retStr;
    }

    public t remove(int row, int column){
	t temp = (t)userGrid[row][column];
	userGrid[row][column] = nullValue;
	return temp;
    }

    public t set(int row, int column, t object){
	t temp = (t)userGrid[row][column];
	userGrid[row][column] = object;
	return temp;
    }

    public t get(int row, int column){
	return (t)userGrid[row][column];
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
	for (int y = 0; y < userGrid.length; y++){
	    if ( userGrid[y].length != 4) {
		for(int x = userGrid[y].length - 1; x > 0; x--){
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


    //~~~~~~~~~~~~main~~~~~~~testin~phase~~~~~~~~~~
    public static void main(String[] args){
	Grid test = new Grid<Integer>(3, 3, 0);
	System.out.println(test);
	test.set(1 , 1 , 4);
	System.out.println(test);
	test.leftJustify();
	System.out.println(test);
	test.rightJustify();
	System.out.println(test);
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
}
