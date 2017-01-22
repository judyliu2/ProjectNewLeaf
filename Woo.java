
/*Driver Class 2048
contains a user interactive game that will utilize all the files located
in the folder, 2048*/

//~important imports~
import board.Grid;
import cs1.Keyboard;
//~~~~~~~~~~~~~~~~~~~

public class Woo{

    //~~~~~~~~~~~~instance vars~~~~~~~~~~~~~~~~
    private Grid<Equalizer> _game;
    private int _placesFilled;
    private int _score;
    private int highest;
    private int rows;
    private int columns;
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    //~~~~~~~~~~~~constructor~~~~~~~~~~~~~~~~~~
    public Woo(){
	Empty nullVal = new Empty();
	rows = 4;
	columns = 4;
	_game = new Grid<Equalizer>(columns,rows,nullVal);
	_placesFilled = 0;
	_score = 0;
	highest = 0;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //~~~~Overloaded constructor~~~~~~~~~~~~~~~~~~
    public Woo(int x, int y){
	Empty nullVal = new Empty();
	_game = new Grid<Equalizer>(y,x,nullVal);
	_placesFilled = 0;
	_score = 0;
	highest = 0;
	rows = y;
	columns = x;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //~~~~~~~~~~~~methods~~~~~~~~~~~~~~~~~~~~~~
    
    //prints the current grid
    public void printGrid(){
	System.out.println("Score: " + _score);
	System.out.println(_game);
    }

    //spawns a new tile at a random empty place
    public void spawn(){
	if (_placesFilled < 16){
	    boolean didSpawn = false;
	    while (didSpawn == false){
		int r = (int)(Math.random() * rows);
		int c = (int)(Math.random() * columns);
		if( _game.isEmpty(r,c)){
		    Equalizer input = new Tiles();
		    _game.set(r,c,input);
		    _score += input.getIntVal();
		    _placesFilled += 1;
		    didSpawn = true;
		}
	    }
	}
    }
    
    //doubles the value of one tile and removes the other
    //input are the indices for each tile
    public void merge(int row1, int column1, int row2, int column2){
	_game.get(row1,column1).doubleTile();
	_game.remove(row2,column2);
	int temp = _game.get(row1,column1).getIntVal();
	_score += temp;
	if (temp > highest){
	    highest = temp;
	}
	_placesFilled -= 1;
    }

    //simulates swiping to the left
    public boolean swipeLeft(){
	boolean move = _game.leftJustify();
	boolean merges = false;

	//for each row
	for (int r = 0; r < rows; r ++){
	    //for each instance in that row
	    for (int c = 0; c < columns - 1; c ++){
		//so long as it's not a nullValue
		if (!(_game.isEmpty(r,c))){
		    //check if it's the same as it's neighbor
		    if(_game.get(r,c).isEqual(_game.get(r,c+1))){
			merge(r,c,r,c+1);//if so, merge
			merges = true;
			c+=1;//don't know if this works
		    }
		}
	    }
	}
	if (merges){
	    _game.leftJustify();
	}
	return move || merges;
    }

    //simulates swiping to the right
    public boolean swipeRight(){
	boolean move =  _game.rightJustify();;
	boolean merges = false;
	//for each row
	for (int r = 0; r < rows; r ++){
	    //for each instance in that row
	    for (int c = columns - 1; c > 0; c --){
		//so long as it's not a nullValue
		if (!(_game.isEmpty(r,c))){
		    //check if it's the same as it's neighbor
		    if(_game.get(r,c).isEqual(_game.get(r,c-1))){
			merge(r,c,r,c-1);//if so, merge
			merges = true;
			c-=1;//don't know if this works
		    }
		}
	    }
	}
	if (merges){
	    _game.rightJustify();
	}
	return move || merges;
    }

    //simulates swiping up
    public boolean swipeUp(){
	boolean move = _game.upJustify();		
	boolean merges = false;		
	//for each column
	for (int c = 0; c < columns; c ++){
	    //for each instance in that row
	    for (int r = 0; r < rows - 1; r ++){
		//so long as it's not a nullValue
		if (!(_game.isEmpty(r,c))){
		    //check if it's the same as it's neighbor
		    if(_game.get(r,c).isEqual(_game.get(r+1,c))){
			merge(r,c,r+1,c);//if so, merge
			merges = true;
			r+=1;//don't know if this works
		    }
		}
	    }
	}
	if (merges){
	    _game.upJustify();
	}
	return move || merges;
    }

    //simulates swiping down
    public boolean swipeDown(){
	boolean move =  _game.downJustify();
	boolean merges = false;
	//for each column
	for (int c = 0; c < columns; c ++){
	    //for each instance in that row
	    for (int r = rows - 1; r > 0; r --){
		//so long as it's not a nullValue
		if (!(_game.isEmpty(r,c))){
		    //check if it's the same as it's neighbor
		    if(_game.get(r,c).isEqual(_game.get(r-1,c))){
			merge(r,c,r-1,c);//if so, merge
			merges = true;
			r-=1;//don't know if this works
		    }
		}
	    }
	}
	if (merges){
	    _game.downJustify();
	}
	return move || merges;
    }

    //Player wins if highest = 2048
    public void win(){
	if (highest >= 2048){
	    System.out.println("Congratulations, comrade. The holy God of 2048 commends you for your journey and dubs you a Knight of 2048.");
	    System.out.println("May you protect the holiness for legends to come.");
	}
    }
    //checks for any mergeable tile on _game()
    public boolean checker(){
	boolean hasMerge = false;
	//for each row
	for (int row = 0; row < rows; row ++){
	    //and each column
	    for (int column = 0; column < columns; column ++){
		if (row < (rows - 1)){//if it's not in the last row
		    hasMerge = hasMerge || _game.get(row, column).isEqual(_game.get(row + 1, column));
		    //is it equal to the one under it?
		}
		if (column < (columns - 1)){//if it's not in the last column
		    hasMerge = hasMerge || _game.get (row, column).isEqual(_game.get(row, column + 1));
		    //is it equal to the one right of it?
		}
		if (hasMerge){//if it can merge
		    return hasMerge;//exit this method
		}
	    }
	}
	return hasMerge;
    }
		
		
	    

    //checks for any mergeable tile based on given coordinates
    public boolean checker(int x, int y){
	boolean matches = false;
	//case for inner tiles, where tiles can be compared to neighboring tiles in all 4 directions
	if ((x > 0 && x < rows-1) && (y > 0 && y < columns-1)){
	    matches = (_game.get(x,y).isEqual(_game.get(x-1,y)) ||
		       _game.get(x,y).isEqual(_game.get(x+1,y)) ||
		       _game.get(x,y).isEqual(_game.get(x,y-1)) ||
		       _game.get(x,y).isEqual(_game.get(x,y+1)));
	}

	//cases for "corners" and "sides" of the grid
	//top side of the grid
	if (x == 0 ){
	    if (y == 0){
		matches = (_game.get(x,y).isEqual(_game.get(x+1,y)) ||
		       _game.get(x,y).isEqual(_game.get(x,y+1)));
	    }
	    else if ( y == columns-1){
	    matches = (_game.get(x,y).isEqual(_game.get(x+1,y)) ||
		       _game.get(x,y).isEqual(_game.get(x,y-1)));
	    } else {
		matches = (_game.get(x,y).isEqual(_game.get(x,y-1)) ||
			   _game.get(x,y).isEqual(_game.get(x+1,y)) ||
			   _game.get(x,y).isEqual(_game.get(x,y+1)));
	    }
	}
	
	//left side of grid
	else if(y == 0){
	    if ( x == rows -1){
		matches = (_game.get(x,y).isEqual(_game.get(x-1,y)) ||
		       _game.get(x,y).isEqual(_game.get(x,y+1)));
	    } else {
		matches = (_game.get(x,y).isEqual(_game.get(x-1,y)) ||
		       _game.get(x,y).isEqual(_game.get(x,y+1)) ||
		       _game.get(x,y).isEqual(_game.get(x+1,y)));
	    }
	}

	//bottom side of grid
	else if (x == rows -1){
	    if (y == columns -1){
		matches = (_game.get(x,y).isEqual(_game.get(x-1,y)) ||
			   _game.get(x,y).isEqual(_game.get(x,y-1)));
	    } else {
		matches = (_game.get(x,y).isEqual(_game.get(x,y-1)) ||
			   _game.get(x,y).isEqual(_game.get(x-1,y)) ||
		       _game.get(x,y).isEqual(_game.get(x,y+1)));
	    }
	}

	//right side of grid
	else {
	    matches = (_game.get(x,y).isEqual(_game.get(x-1,y)) ||
		       _game.get(x,y).isEqual(_game.get(x,y-1)) ||
		       _game.get(x,y).isEqual(_game.get(x+1,y)));
	}
	return matches;
    }
    
    //Player loses if all tiles are filled (temporary)
    public boolean lose(){
	boolean chance = false;
	//checks if about half the tiles in the board is mergeable 
	for ( int x = rows % 2; x < rows; x+=2){
	    for( int y = columns % 2; y < columns; y+=2){
		//check if any of these tiles are mergeable with their neighbors
		chance = chance || checker(x,y);
		if ( chance == true){break;}
	    }
	    if ( chance == true){break;}
	}
	for ( int x = rows % 2 + 1; x < rows; x+=2){
	    for( int y = columns % 2 + 1; y < columns; y+=2){
		//check if any of these tiles are mergable with their neighbors
		chance = chance || checker(x,y);
		if ( chance == true){break;}
	    }
	    if ( chance == true){break;}
	}
	
	if(_placesFilled == (rows * columns)){
	    if (chance == false){
	    System.out.println("Adieu, comrade. You have made it thus far, but have fallen on the battlefield. The holy God of 2048 commends you for your efforts.");
	    System.out.println("May you meet the holiness in another reincarnation.");
	    }
	}
	return chance;
    }


    //simulates a turn after accepting user input
    public void turn(){
	boolean merge = false; // Merge Indicators - passed from Justify methods
	System.out.println("Use the keys A,D,W,S to swipe left, right, up, and down, respectively. ");
	System.out.println();
	
	System.out.println("What shall be your next move?");

	String userInput = Keyboard.readString().trim().toUpperCase();

	if (userInput.equals("A")){
	    merge = swipeLeft(); 
	    //System.out.println(merge); // Debugging Code
	    if (merge == true){
		spawn();
	    } else {
		System.out.println("Do you take me for a fool? That doesn't do anything! Try another direction!");
	    }
	} else if (userInput.equals("D")){
	    merge = swipeRight();
	    //System.out.println(merge); // Debugging Code
	    if (merge == true){
		spawn();
	    } else {
		System.out.println("Do you take me for a fool? That doesn't do anything! Try another direction!");
	    }
	} else if (userInput.equals("W")){
	    merge = swipeUp();
	    //System.out.println(merge); // Debugging Code
	    if (merge == true){
		spawn();
	    } else {
		System.out.println("Do you take me for a fool? That doesn't do anything! Try another direction!");
	    }
	} else if (userInput.equals("S")){
	    merge = swipeDown();
	    //System.out.println(merge); // Debugging Code
	    if (merge == true){
		spawn();
	    } else {
		System.out.println("Do you take me for a fool? That doesn't do anything! Try another direction!");
	    }
	} else {
	    System.out.println("You have disobeyed the laws of the land. Try again if you wish to ever meet the lord.");
	    turn();
	}
    }

    //uses above methods to play a game of 2048
    public void game(){
	System.out.println("Now, you may embark on your journey. Best of luck to you...");
	System.out.println();
	spawn();
	printGrid();
	turn();
	
	while(highest < 2048){
	    System.out.println("Your journey continues. Stay strong!");
	    System.out.println();
	    printGrid();
	    turn();
	    if (_placesFilled == rows * columns){
		if (lose() == false){
		    return;
		}
	    }
	}
	win();
	printGrid();
    }

    //main method
    public static void main(String[] args){
	//Asks user if they want to customize their grids. 1 for regular sized grid and 2 for customization
	System.out.println("Welcome player. Here, you will begin your journey to meet the holy God of 2048...");
	System.out.println();
	System.out.println("What would you like the size of your grid to be? \n Press 1 for normal grid. \n Press 2 to customize size.");
	System.out.println();

	// Read user input
	int userInput1 = Keyboard.readInt();
	while (!(userInput1 == 1 || userInput1 == 2)){
	    System.out.print("\nYou must make a proper decision. The world is at a standstill.\n");
	    userInput1 = Keyboard.readInt();
	}
	if (userInput1 == 1){
	    Woo bob = new Woo();
	    bob.game();
	}
	//if user wants to customize grid size, it only takes an integer from 2~10, inclusive; otherwise, the user has to try another input.
	else if (userInput1 == 2){
	    System.out.println("\nHow wide would you like your grid to be? Our selections range from 2 to 10");
	    int userInputx = Keyboard.readInt();
	    while(userInputx == -1 || (userInputx < 2 || userInputx > 10)){
		 System.out.print("\nYou must make a proper decision. The world is at a standstill.\n");
		 userInputx = Keyboard.readInt();
	    }
	    System.out.println("\nHow tall would you like your grid to be? Our selections range from 2 to 10");
	    int userInputy = Keyboard.readInt();
	    while(userInputy == -1 || (userInputy < 2 || userInputy > 10)){
		 System.out.print("\nYou must make a proper decision. The world is at a standstill.\n");
		 userInputy = Keyboard.readInt();
	    }
 
	    Woo bob = new Woo(userInputx, userInputy);
	    bob.game();
	}
	
    }
    
}//end class Woo2
