
/*Driver Class 2048
contains a user interactive game that will utilize all the files located
in the folder, 2048*/

//~important imports~
import Game.Grid;
import Game.Empty;
import Game.Tiles;
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
	Empty nullVal = new Empty()
	Grid _game = new Grid(4,4,nullVal);

	_score = 0;
	highest = 2048;
	
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //~~~~~~~~~~~~methods~~~~~~~~~~~~~~~~~~~~~~
    
    //prints the current grid
    public void printGrid(){
	System.out.println(_game);
    }
    
    //spawns a new tile at a random empty place
    public void spawn(){
	int row = (int)(Math.random() * 4);
	int column = (int)(Math.random() * 4);
	if( _game.isEmpty(row,column)){
	    Tile input = new Tile();
	    game.set(row,column,input);
	}
	else{
	    spawn();
	}
    }
    
    //doubles the value of one tile and removes the other
    //input are the indices for each tile
    public void merge(int row1, int column1, int row2, int column2){
	_game.get(row1,column1).double();
	_game.remove(row2,column2);
    }

    //simulates swiping to the left
    public void swipeLeft(){
	_game.leftJustify();

	//for each row
	for (int r = 0; r < 4; r ++){
	    //for each instance in that row
	    for (int c = 0; c < 3; c ++){
		//check if it's the same as it's neighbor
		if(_game.get(r,c).isEqual(_game.get(r,c+1))){
		    merge(r,c,r,c+1);//if so, merge
		    c+=1;//don't know if this works
		}
	    }
	}
	_game.leftJustify();
    }

    //simulates swiping to the right
    public void swipeRight(){
	_game.rightJustify();
	//for each row
	for (int r = 0; r < 4; r ++){
	    //for each instance in that row
	    for (int c = 3; c > 0; c --){
		//check if it's the same as it's neighbor
		if(_game.get(r,c).isEqual(_game.get(r,c-1))){
		    merge(r,c,r,c-1);//if so, merge
		    c-=1;//don't know if this works
		}
	    }
	}
	_game.rightJustify();
    }

    //simulates swiping up
    public void swipeUp(){
	_game.upJustify();
	//for each column
	for (int c = 0; c < 4; c ++){
	    //for each instance in that row
	    for (int r = 0; r < 3; r ++){
		//check if it's the same as it's neighbor
		if(_game.get(r,c).isEqual(_game.get(r,c+1))){
		    merge(r,c,r+1,c);//if so, merge
		    r+=1;//don't know if this works
		}
	    }
	}
	_game.upJustify();
    }

    //simulates swiping down
    public void swipeDown(){
	_game.downJustify();

	//for each column
	for (int c = 0; c < 4; c ++){
	    //for each instance in that row
	    for (int r = 3; r > 0; r --){
		//check if it's the same as it's neighbor
		if(_game.get(r,c).isEqual(_game.get(r-1,c))){
		    merge(r,c,r-1,c);//if so, merge
		    r-=1;//don't know if this works
		}
	    }
	}
	_game.downJustify();
    }

    //simulates a turn after accepting user input
    public void turn(String userInput){
	System.out.println("Which direction would you like the tiles to move toward? \n A for Left \n S for Down \n D for Right \n W for up");
	
	userInput = Keyboard.readString();
	
	if ((userInput.compareTo("a") == 0) ||
	    (userInput.compareTo("A")== 0)){
	    swipeLeft();
	}
	
	else if ((userInput.compareTo("s") == 0) ||
		 (userInput.compareTo("S")== 0)){
	    swipeDown();
	}
	
	else if ((userInput.compareTo("d") == 0) ||
		 (userInput.compareTo("D")== 0)){
	    swipeRight();
	}
	
	else if ((userInput.compareTo("w") == 0) ||
		 (userInput.compareTo("W")== 0)){
	    swipeUp();
	}
	
	else{
	    System.out.println("Sorry, your input is not a valid direction.");
	}
    }

    //uses above methods to play a game of 2048
    public void game(){
	System.out.println("Welcome player. Here, you will begin your journey to meet the holy God of 2048...");
	System.out.println();
	
	spawn();
	printGrid();
    }

    //main method
    public static void main(String args){

    }

}//end class Game2048
