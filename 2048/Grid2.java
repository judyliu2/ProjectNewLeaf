public class Grid2<Integer>{

    //~~~~~~~~~~~~~~Instance Vars~~~~~~~~~~~~~~~~~~~~~
    private int[][] userGrid = new int[4][4];
    private int placesFilled;
    private int maxPlaces;
    private int nullValue = 0;
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //~~~~~~~~~~~~~~~~~~~~Default Constructor~~~~~~~~~~~~~~~~~~~~~~~
    //Populates userGrid with insignificant values: 0
    public Grid2(){
	for (int x = 0; x < userGrid.length; x++){
	    for (int y = 0; y < userGrid[x].length; y++){
		userGrid[x][y] = 0;
	    }
	}
    }
    //~~~~~~~~~~~~~~~Overloaded constructor~~~~~~~~~~~~~~~~~~~~~~
    public Grid2(int row, int column, int x){
	userGrid[row][column] = x;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //~~~~~~~~~~~~~~~~~methods~~~~~~~~~~~~~~~~~~~~~~~
    public String toString(){
	String retStr = "";
	for (int[] row: userGrid){
	    for (int column: row){
		retStr += column + "|";
	    }
	    retStr += "\n";
	}
	return retStr;
    }

    public int remove(int row, int column){
	int oldVal = userGrid[row][column];
	userGrid[row][column] = 0;
	return oldVal;
    }

    public int set(int row, int column, int object){
	int oldVal = userGrid[row][column];
	userGrid[row][column] = object;
	return oldVal;
    }

    public int get(int row, int column){
	return userGrid[row][column];
    }
    
    public void swap(int row1, int column1, int row2, int column2){
        set(row1,column1,(set(row2,column2,get(row1,column1))));
    }

    public int meaningfulValues(int row){
	int total = 0;
	for (int x: userGrid[row]){
	    if (x != 0){
		total += 1;
	    }
	}
	return total;
    }

    public int lastIndex(int row){
	int index = -1;
	for (int x = 0; x < userGrid[row].length; x++){
	    if (get(x,row) != 0){
		index = x;
	    }
	}
	return index;
    }
    
    public void leftJustify(){
	for (int row = 0; row < userGrid.length; row ++){
	    for (int pass = 0; pass < userGrid[0].length - 1; pass ++){
		for (int column = userGrid[0].length - 1; column > 0; column --){
		    if (get(row,column -1)==(nullValue)){
			swap(row, column, row, column - 1);
		    }
		}	    
	    }
	}
    }

    public void rightJustify(){
	for (int y = 0; y < userGrid.length; y++){
	    if (meaningfulValues(y) > 0){
		for(int x = lastIndex(y); x >= 0; x--){
		    if (x != 3){
			if (get(y, x) > 0){
			    while(get(x + 1, y) == 0){
				set(x + 1, y, get(x, y));
			    }
			}
		    }
		}
	    }
	}
    }

    public void upJustify(){
	for (int column = 0; column < userGrid[0].length; column ++){
	    for (int pass = 0; pass < userGrid.length - 1; pass ++){
		for (int row = userGrid.length - 1; row > 0; row --){
		    if (get(row -1,column).equals(nullValue)){
			swap(row, column, row -1, column);
		    }
		}	    
	    }
	}
    }

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


    //~~~~~~~~~~~~main~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static void main(String[] args){
	Grid2 test = new Grid2(2,1,2);
	System.out.println(test);
	System.out.println();

	System.out.println(test.meaningfulValues(2));
	System.out.println(test.lastIndex(2));
	test.rightJustify();
	System.out.println(test);
	System.out.println();

	test.leftJustify();
	System.out.println(test);
	System.out.println();

    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
}
