
/*Driver Class 2048
contains a user interactive game that will utilize all the files located
in the folder, 2048*/

//~important imports~
import board.Grid;
import cs1.Keyboard;
//~~~~~~~~~~~~~~~~~~~

public class Game2048{

    //~~~~~~~~~~~~instance vars~~~~~~~~~~~~~~~~
    private Grid<Equalizer> _game;
    private int _placesFilled;
    private int _score;
    private int highest;
    private int rows;
    private int columns;
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    //~~~~~~~~~~~~constructor~~~~~~~~~~~~~~~~~~
    public Game2048(){
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
    public Game2048(int x, int y){
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
	    }
	    else {
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
	    }
	    else{
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
	    }
	    else{
		matches = (_game.get(x,y).isEqual(_game.get(x,y-1)) ||
			   _game.get(x,y).isEqual(_game.get(x-1,y)) ||
		       _game.get(x,y).isEqual(_game.get(x,y+1)));
	    }
	}

	//right side of grid
	else{
	    matches = (_game.get(x,y).isEqual(_game.get(x-1,y)) ||
		       _game.get(x,y).isEqual(_game.get(x,y-1)) ||
		       _game.get(x,y).isEqual(_game.get(x+1,y)));
	}
	return matches;
    }
    
    //Player loses if all tiles are filled (temporary)
    public void lose(){
	boolean chance = false;
	//checks if about half the tiles in the board is mergeable 
	for ( int x = rows % 2; x < rows; x+=2){
	    for( int y = columns % 2; y < columns; y+=2){
		//check if any of these tiles are mergeable with their neighbors
		chance = checker(x,y);
	    }
	}
	for ( int x = rows % 2 + 1; x < rows; x+=2){
	    for( int y = columns % 2 + 1; y < columns; y+=2){
		//check if any of these tiles are mergable with their neighbors
		chance = checker(x,y);
	    }
	}
	
	if(_placesFilled == 16){
	    if (chance == false){
	    System.out.println("Adieu, comrade. You have made it thus far, but have fallen on the battlefield. The holy God of 2048 commends you for your efforts.");
	    System.out.println("May you meet the holiness in another reincarnation.");
	    }
	}
	
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
	    }
	    else{
		System.out.println("Do you take me for a fool? That doesn't do anything! Try another direction!");
	    }
	} else if (userInput.equals("D")){
	    merge = swipeRight();
	    //System.out.println(merge); // Debugging Code

	    if (merge == true){
		spawn();
	    }
	    else{
		System.out.println("Do you take me for a fool? That doesn't do anything! Try another direction!");
	    }
		

	} else if (userInput.equals("W")){
	    merge = swipeUp();
	    //System.out.println(merge); // Debugging Code

	    if (merge == true){
		spawn();
	    }
	    else{
		System.out.println("Do you take me for a fool? That doesn't do anything! Try another direction!");
	    }
	} else if (userInput.equals("S")){
	    merge = swipeDown();
	    //System.out.println(merge); // Debugging Code

	    if (merge == true){
		spawn();
	    }
	    else{
		System.out.println("Do you take me for a fool? That doesn't do anything! Try another direction!");
	    }
	} else {
	    System.out.println("You have disobeyed the laws of the land. Try again if you wish to ever meet the lord.");
	    turn();
	}
    }

    //uses above methods to play a game of 2048
    public void game(){
	System.out.println("Welcome player. Here, you will begin your journey to meet the holy God of 2048...");
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
		lose();
	    }
	}
	win();
	printGrid();
    }
	

    //main method
    public static void main(String[] args){
	Game2048 bob = new Game2048();
	bob.game();
    }
    
}//end class Game2048
