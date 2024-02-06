package cpsc2150.extendedConnectX.models;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
  Abigail Clanton (alclant)
  Cassandra Phillips (Cassie488)
  Ricardo Varona (ricky643)
  Kaki Newsome (kakmonk)
 */

/**
 * This GameBoard class holds all functions related to keeping track of the 
 * state of the GameBoard
 *
 * @invariant: (column < enteredColumn) AND (char p == [token choice
 * enetered by the user that is a char] || char p == '')
 * (MIN_ROW <= enteredRow <= MAX_ROW) AND (MIN_ROW <= entColumn <= MAX_COL) AND
 * (MIN_NUM_TO_WIN <= amtToWin <= MAX_NUM_TO_WIN) AND (amtToWin <= enteredRow) AND
 * (amtToWin <= entColumn)
 *
 * @corresponds: self = gameBoardArray[0...--enteredRow][0...--enteredColumn]
 *
 */
public class GameBoard extends AbsGameBoard implements IGameBoard
{
     //largest size and smallest size the parameters can be
     public static final int MAX_ROW = 100, MAX_COL = 100;
     public static final int MIN_ROW = 3, MIN_COL = 3, MIN_NUM_TO_WIN = 3;
     public static final int MAX_NUM_TO_WIN = 25;
     public static final int MAX_PLAYERS = 10;
     public static final int MIN_PLAYERS = 2;

     //private varibles for sizing and arrays
     private char gameBoardArray [][];
     private int enteredRow, enteredColumn, enteredATW;

    /**
     * Function that keeps track of the how to board currently looks
     * @param - entRow - the amount of rows entered by the player they would like the gameboard to have.
     * entVolumn - the amount of columns entered by the player they would like the gameboard to have.
     * amtWin - the amount of the same tokens in a row to win the game selected by the player.
     *
     * @pre - (MIN_ROW <= enteredRow <= MAX_ROW) AND (MIN_ROW <= entColumn <= MAX_COL) AND
     *        (MIN_NUM_TO_WIN <= amtToWin <= MAX_NUM_TO_WIN) AND (amtToWin <= enteredRow) AND
     *        (amtToWin <= entColumn)
     *
     * @post [GameBoardArray is made to be the size of user choice, and each position = ''] 
     * AND enteredRow = entRow AND enteredColumn = entColumn AND enteredATW = amtWin
     */
    public GameBoard(int entRow, int entColumn, int amtToWin)
    {
        //assigning the entered values
        enteredRow = entRow;
        enteredColumn = entColumn;
        enteredATW = amtToWin;

        //creates a 2d array with blank spaces in a enteredRow by entColumn dimension gameboard
        gameBoardArray = new char[enteredRow][enteredColumn];
        for(int row = 0; row < enteredRow; row++) {
            for (int column = 0; column < enteredColumn; column++)
                gameBoardArray[row][column] = ' ';}
    }

    /**
     * Places the token in the lowest avaliable slot of the selected column.
     * @param token - The token which is being placed
     * @param column - column number for the player piece to be inserted at
     *
     * @pre
     * (column >= 0) AND (column < enteredColumn) AND [checkIfFree states there is space in column]
     *
     * @post
     *[self = #self except character token is added to the lowest avaialable row in column column]
     */
    public void dropToken(char token, int column)
    {
        //iterates throught the gameboard starting at the bottom row
        //in the column and index the row if the position already contains
        //a token, but insert the token if the position is empty.
        for(int row = 0; row < enteredRow; row++){
            if(gameBoardArray[row][column] == ' '){
                gameBoardArray[row][column] = token;
                break;}}
    }

    /**
     * Determines the value of a given position on the gameboard.
     * @param position - The chosen board position
     *
     * @return - The item at the given location, ' ' if no symbol is present.
     *
     * @pre - None
     *
     * @post
     * (self = #self)
     * whatsAtPos = [returns the char at the chosen board position.]
     */
    public char whatsAtPos(BoardPosition position)
    {
        //gets the token at the specific position.
        char token = gameBoardArray[position.getRow()][position.getColumn()];
        return token;
    }
    

    /**
     * @return an integer which represents the max number of players
     * that can participate in a game.
     *
     * pre - None
     *
     * @post getMaxPlayers() = #MAX_PLAYERS AND (self = #self)
     */
    public int getMaxPlayers() {return MAX_PLAYERS;}

    /**
     * @return an integer which represents the minimum number 
     * of players that can participate in a game.
     *
     * pre - None
     *
     * @post getMinPlayers() = #MIN_PLAYERS AND (self = #self)
     */
    public int getMinPlayers() {return MIN_PLAYERS;}
    
    /**
     * @return an integer which represents number of Rows
     *
     * pre - None
     *
     * @post getNumRows() = enteredRow AND (self = #self)
     */
    public int getNumRows() {return enteredRow;}
    
    /**
     * @return an integer which represents the max number of 
     * rows that can be chosen to play with.
     *
     * pre - None
     *
     * @post getMaxRow() = #MAX_ROW AND (self = #self)
     */
    public int getMaxRow() {return MAX_ROW;}

    /**
     * @return an integer which represents the minimum number of 
     * rows that can be chosen to play with.
     *
     * pre - None
     *
     * @post getMinRow() = #MIN_ROW AND (self = #self)
     */
    public int getMinRow() {return MIN_ROW;}
    
    /**
     * @return an integer which represents number of columns
     *
     * pre - None
     *
     * @post getNumColumns() = enteredColumn AND (self = #self)
     */
    public int getNumColumns() {return enteredColumn;}

    /**
     * @return an integer which represents the max number of 
     * columns that can be chosen to play with.
     *
     * pre - None
     *
     * @post getMaxCol() = #MAX_COL AND (self = #self)
     */
    public int getMaxCol() {return MAX_COL;}

    /**
     * @return an integer which represents the minimum number of 
     * columns that can be chosen to play with.
     *
     * pre - None
     *
     * @post getMinCol() = #MIN_COL AND (self = #self)
     */
    public int getMinCol() {return MIN_COL;}
    
    /**
     * @return an integer which represents number of tokens in a row to win
     *
     * pre - None
     *
     * @post getNumToWin() = enteredATW AND (self = #self)
     */
    public int getNumToWin() {return enteredATW;
    }
    
    /**
     * @return an integer which represents the maximum number of
     * tokens in a row in order to win that can be chosen by the player.
     *
     * pre - None
     *
     * @post getMaxNTW() = #MAX_NUM_TO_WIN AND (self = #self)
     */
    public int getMaxNTW() {
        return MAX_NUM_TO_WIN;
    }

    /**
     * @return an integer which represents the minimum number of
     * tokens in a row in order to win that can be chosen by the player.
     *
     * pre - None
     *
     * @post getMinNTW() = #MIN_NUM_TO_WIN AND (self = #self)
     */
    public int getMinNTW() {
        return MIN_NUM_TO_WIN;
    }
}


