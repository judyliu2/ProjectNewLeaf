package board;

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

    /*isEmpty() returns a boolean denoting whether a space has been taken up*/
    public boolean isEmpty(int row, int column){
	return (userGrid[row][column].equals(nullValue));
    }

    /*leftJustify() should move all significant values as left as they can go
      uses a bubbling method to push all non-nullValues to that direction
      vanilla version, not exit early*/
    public boolean leftJustify(){
	boolean movement = false; // Indicates if a swap has occurred
	//go by row
	for (int row = 0; row < userGrid.length; row ++){
	    //do n - 1 passes
	    for (int pass = 0; pass < userGrid[0].length - 1; pass ++){
		//for each column in that row
		for (int column = userGrid[0].length - 1; column > 0; column--){
		    //check for presence of nullValue to the left
		    if (get(row,column -1).equals(nullValue) &&
			!(get(row, column).equals(nullValue))){
			//if so, swap
			swap(row, column, row, column - 1);
			movement = true; // Swap has occurred
		    }
		}	    
	    }
	}
	return movement; // Passes result to swipe methods
    }
    
    /*rightJustify() should move all significant values as right as they can go
      uses a bubbling method to push all non-nullValues to that direction
      vanilla version, not exit early
     */
    public boolean rightJustify(){
	boolean movement = false;
	for (int row = 0; row < userGrid.length; row ++){
	    //do n - 1 passes
	    for (int pass = 0; pass < userGrid[0].length - 1; pass ++){
		//for each column in that row
		for (int column = 0; column < userGrid[0].length - 1; column ++){
		    //check for presence of nullValue to the right
		    if (get(row,column +1).equals(nullValue)&&
			!(get(row, column).equals(nullValue))){
			//if so, swap
			swap(row, column, row, column + 1);
			movement = true;
		    }
		}	    
	    }
	}
	return movement;
    }
    /*upJustify() should move all significant values as up as they can go
      uses a bubbling method to push all non-nullValues to that direction
      vanilla version, not exit early*/
    public boolean upJustify(){
	boolean movement = false;
	//go by column
	for (int column = 0; column < userGrid[0].length; column ++){
	    //do n - 1 passes
	    for (int pass = 0; pass < userGrid.length - 1; pass ++){
		//for each row in that column
		for (int row = userGrid.length - 1; row > 0; row --){
		    //check for nullValue presence above
		    if (get(row -1,column).equals(nullValue)&&
			!(get(row, column).equals(nullValue))){
			//if so, swap
			swap(row, column, row -1, column);
			movement = true;
		    }
		}	    
	    }
	}
	return movement;
    }

    /*downJustify() should move all significant values as down as they can go
      uses a bubbling method to push all non-nullValues to that direction
      vanilla version, not exit early*/
    public boolean downJustify(){
	boolean movement = false;
	//go by column
	for (int column = 0; column < userGrid[0].length; column ++){
	    //do n -1 passes
	    for (int pass = 0; pass < userGrid.length - 1; pass ++){
		//for each row in that column
		for (int row = 0; row < userGrid.length - 1; row ++){
		    //check for nullValue presence below
		    if (get(row + 1,column).equals(nullValue)&&
			!(get(row, column).equals(nullValue))){
			//if so, swap
			swap(row, column, row + 1, column);
			movement = true;
		    }
		}	    
	    }
	}
	return movement;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    //~~~~~~~~~~~~main~~~~~~~testin~phase~~~~~~~~~~
    public static void main(String[] args){
	Grid test = new Grid<Integer>(4, 4, 0);
	System.out.println(test);
	test.set(1 , 1 , 4);
	test.set(1 , 2 , 5);
	System.out.println(test);
	System.out.println(test.isEmpty(0,0));
	/*test.rightJustify();
	System.out.println(test);
	test.upJustify();
	System.out.println(test);
	test.downJustify();
	System.out.println(test);
	*/
	test.leftJustify();
	System.out.println(test);
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
}
