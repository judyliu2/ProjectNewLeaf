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

    /*remove() substitutes the value at listed index with a null value
      to render it insignificant
      also returns "removed" value*/
    public t remove(int row, int column){
	t temp = (t)userGrid[row][column];
	userGrid[row][column] = nullValue;
	return temp;
    }
    
    /*set() substitutes the value at listed index with the input
      also returns original value*/
    public t set(int row, int column, t object){
	t temp = (t)userGrid[row][column];
	userGrid[row][column] = object;
	return temp;
    }
    
    /*get() acquires the value at the given index and returns it*/
    public t get(int row, int column){
	return (t)userGrid[row][column];
    }

    /*swap() switches the values at the given indices */
    public void swap(int row1, int column1, int row2, int column2){
        set(row1,column1,(set(row2,column2,get(row1,column1))));
    }

    /*leftJustify() should move all significant values as left as they can go
      uses a bubbling method to push all non-nullValues to that direction
      vanilla version, not exit early*/
    public void leftJustify(){
	//go by row
	for (int row = 0; row < userGrid.length; row ++){
	    //do n - 1 passes
	    for (int pass = 0; pass < userGrid[0].length - 1; pass ++){
		//for each column in that row
		for (int column = userGrid[0].length - 1; column > 0; column --){
		    //check for presence of nullValue to the left
		    if (get(row,column -1).equals(nullValue)){
			//if so, swap
			swap(row, column, row, column - 1);
		    }
		}	    
	    }
	}
    }

    public void rightJustify(){
	for (int y = 0; y < userGrid.length; y++){
	    for(int x = userGrid[y].length - 1; x > 0; x--){
		    set(x, y, get(x - 1, y));
	    }
	}
    }
    /*upJustify() should move all significant values as up as they can go
      uses a bubbling method to push all non-nullValues to that direction
      vanilla version, not exit early*/
    public void upJustify(){
	//go by column
	for (int column = 0; column < userGrid[0].length; column ++){
	    //
	    for (int pass = 0; pass < userGrid.length - 1; pass ++){
		for (int row = userGrid.length - 1; row > 0; row --){
		    if (get(row -1,column).equals(nullValue)){
			swap(row, column, row -1, column);
		    }
		}	    
	    }
	}
    }

    /*downJustify() should move all significant values as down as they can go
      uses a bubbling method to push all non-nullValues to that direction
      vanilla version, not exit early*/
    public void downJustify(){
	for (int column = 0; column < userGrid[0].length; column ++){
	    for (int pass = 0; pass < userGrid.length - 1; pass ++){
		for (int row = 0; row < userGrid.length - 1; row ++){
		    if (get(row + 1,column).equals(nullValue)){
			swap(row, column, row + 1, column);
		    }
		}	    
	    }
	}
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    //~~~~~~~~~~~~main~~~~~~~testin~phase~~~~~~~~~~
    public static void main(String[] args){
	Grid test = new Grid<Integer>(3, 3, 0);
	System.out.println(test);
	test.set(1 , 1 , 4);
	test.set(2 , 2 , 5);
	System.out.println(test);
	test.downJustify();
	System.out.println(test);
	test.leftJustify();
	System.out.println(test);
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
}
