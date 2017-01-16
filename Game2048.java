
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
    }

    //simulates swiping to the right
    public void swipeRight(){
	_game.rightJustify();
    }

    //simulates swiping up
    public void swipeUp(){
	_game.upJustify();
    }

    //simulates swiping down
    public void swipeDown(){
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

    }

    //main method
    public static void main(String args){

    }


}//end class Game2048

