
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
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    //~~~~~~~~~~~~constructor~~~~~~~~~~~~~~~~~~
    public Game2048(){
	Empty nullVal = new Empty();
	_game = new Grid<Equalizer>(4,4,nullVal);
	_placesFilled = 0;
	_score = 0;
	highest = 0;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //~~~~~~~~~~~~methods~~~~~~~~~~~~~~~~~~~~~~
    
    //prints the current grid
    public void printGrid(){
	System.out.println("Score: " + _score);
	System.out.println(_game);
    }
    
    /*
    // Checks if Grid has changed.
    public Boolean gridChange(){
	if (
    */

    //spawns a new tile at a random empty place
    public void spawn(){
	if (_placesFilled < 16){
	    boolean didSpawn = false;
	    while (didSpawn == false){
		int row = (int)(Math.random() * 4);
		int column = (int)(Math.random() * 4);
		if( _game.isEmpty(row,column)){
		    Equalizer input = new Tiles();
		    _game.set(row,column,input);
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
    public Boolean swipeLeft(){
	Boolean merge1 = false;
	Boolean merge2 = false;
	merge1 = _game.leftJustify();

	//for each row
	for (int r = 0; r < 4; r ++){
	    //for each instance in that row
	    for (int c = 0; c < 3; c ++){
		//so long as it's not a nullValue
		if (!(_game.isEmpty(r,c))){
		    //check if it's the same as it's neighbor
		    if(_game.get(r,c).isEqual(_game.get(r,c+1))){
			merge(r,c,r,c+1);//if so, merge
			c+=1;//don't know if this works
		    }
		}
	    }
	}
	merge2 = _game.leftJustify();
	return merge1 || merge2;
    }

    //simulates swiping to the right
    public Boolean swipeRight(){
	Boolean merge1 = false;
	Boolean merge2 = false;
	merge1 = _game.rightJustify();
	//for each row
	for (int r = 0; r < 4; r ++){
	    //for each instance in that row
	    for (int c = 3; c > 0; c --){
		//so long as it's not a nullValue
		if (!(_game.isEmpty(r,c))){
		    //check if it's the same as it's neighbor
		    if(_game.get(r,c).isEqual(_game.get(r,c-1))){
			merge(r,c,r,c-1);//if so, merge
			c-=1;//don't know if this works
		    }
		}
	    }
	}
	merge2 = _game.rightJustify();
	return merge1 || merge2;
    }

    //simulates swiping up
    public Boolean swipeUp(){
	Boolean merge1 = false;		
	Boolean merge2 = false;		
	merge1 = _game.upJustify();
	//for each column
	for (int c = 0; c < 4; c ++){
	    //for each instance in that row
	    for (int r = 0; r < 3; r ++){
		//so long as it's not a nullValue
		if (!(_game.isEmpty(r,c))){
		    //check if it's the same as it's neighbor
		    if(_game.get(r,c).isEqual(_game.get(r+1,c))){
			merge(r,c,r+1,c);//if so, merge
			r+=1;//don't know if this works
		    }
		}
	    }
	}
	merge2 = _game.upJustify();
	return merge1 || merge2;
    }

    //simulates swiping down
    public Boolean swipeDown(){
	Boolean merge1 = false;
	Boolean merge2 = false;
	merge1 = _game.downJustify();
	//for each column
	for (int c = 0; c < 4; c ++){
	    //for each instance in that row
	    for (int r = 3; r > 0; r --){
		//so long as it's not a nullValue
		if (!(_game.isEmpty(r,c))){
		    //check if it's the same as it's neighbor
		    if(_game.get(r,c).isEqual(_game.get(r-1,c))){
			merge(r,c,r-1,c);//if so, merge
			r-=1;//don't know if this works
		    }
		}
	    }
	}
	merge2 = _game.downJustify();
	return merge1 || merge2;
    }

    //Player wins if highest = 2048
    public void win(){
	if (highest >= 2048){
	    System.out.println("Congratulations, comrade. The holy God of 2048 commends you for your journey and dubs you a Knight of 2048.");
	    System.out.println("May you protect the holiness for legends to come.");
	}
    }

    //Player loses if all 16 tiles are filled (temporary)
    public void lose(){
	if(_placesFilled == 16){
	    System.out.println("Adieu, comrade. You have made it thus far, but have fallen on the battlefield. The holy God of 2048 commends you for your efforts.");
	    System.out.println("May you meet the holiness in another reincarnation.");
	}
	
    }

    //simulates a turn after accepting user input
    public void turn(){
	Boolean merge = false; // Merge Indicators - passed from Justify methods
	System.out.println("Use the keys A,D,W,S to swipe left, right, up, and down, respectively. ");
	System.out.println();
	
	System.out.println("What shall be your next move?");

	String userInput = Keyboard.readString().trim().toUpperCase();

	if (userInput.equals("A")){
	    merge = swipeLeft(); 
	    System.out.println(merge); // Debugging Code
	    if (merge == true){
		spawn();
	    }
	} else if (userInput.equals("D")){
	    merge = swipeRight();
	    System.out.println(merge); // Debugging Code

	    if (merge == true){
		spawn();
	    }
	} else if (userInput.equals("W")){
	    merge = swipeUp();
	    System.out.println(merge); // Debugging Code

	    if (merge == true){
		spawn();
	    }
	} else if (userInput.equals("S")){
	    merge = swipeDown();
	    System.out.println(merge); // Debugging Code

	    if (merge == true){
		spawn();
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
	
	while(highest < 2048 && _placesFilled < 16){
	    System.out.println("Your journey continues. Stay strong!");
	    System.out.println();
	    printGrid();
	    turn();
	}
	win();
	lose();
    }
	

    //main method
    public static void main(String[] args){
	Game2048 bob = new Game2048();
	bob.game();
    }
    
}//end class Game2048
