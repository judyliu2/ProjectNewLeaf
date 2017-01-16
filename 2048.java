/*Driver Class 2048
contains a user interactive game that will utilize all the files located
in the folder, 2048*/

//~important imports~
import 2048.Grid;
import 2048.Empty;
import 2048.Tiles;
import cs1.Keyboard;
//~~~~~~~~~~~~~~~~~~~

public class 2048{

    //~~~~~~~~~~~~instance vars~~~~~~~~~~~~~~~~
    private Grid<Equalizer> _game;
    private int _placesFilled;
    private int _score;
    private int highest;
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    //~~~~~~~~~~~~constructor~~~~~~~~~~~~~~~~~~
    public 2048(){

    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //~~~~~~~~~~~~methods~~~~~~~~~~~~~~~~~~~~~~
    
    //prints the current grid
    public void printGrid(){

    }
    
    //spawns a new tile at a random place
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

    }

    //simulates swiping to the right
    public void swipeRight(){

    }

    //simulates swiping up
    public void swipeUp(){

    }

    //simulates swiping down
    public void swipeDown(){

    }

    //simulates a turn after accepting user input
    public void turn(String userInput){

    }

    //uses above methods to play a game of 2048
    public void game(){

    }

    //main method
    public static void main(String args){

    }


}//end class 2048
