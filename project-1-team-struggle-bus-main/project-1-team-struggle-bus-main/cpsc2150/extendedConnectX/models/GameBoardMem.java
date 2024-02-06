package cpsc2150.extendedConnectX.models;
import static java.util.Map.entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
  Abigail Clanton (alclant)
  Cassandra Phillips (Cassie488)
  Ricardo Varona (ricky643)
  Kaki Newsome (kakmonk)
 */

/**
 * GameBoardMem class holds the methods that keeps track of the game board in a memory efficient way
 * 
 * @invariant - (MIN_ROW <= enteredRow <= MAX_ROW) AND (MIN_ROW <= entColumn <= MAX_COL) AND
 *              (MIN_NUM_TO_WIN <= amtToWin <= MAX_NUM_TO_WIN) AND (amtToWin <= enteredRow) AND
 *              (amtToWin <= entColumn) AND
 *              [There can never be an empty space bewtween any two player tokens]
 *
 * @corresponds (self = hashMap<Character, List<BoardPosition>>()) AND (enteredRow = entRow) AND
 *              (enteredColumn = entColumn) AND (amountToWin = amtWin)
 */
public class GameBoardMem extends AbsGameBoard implements IGameBoard {
    public static final int MAX_ROW = 100, MAX_COL = 100;
    public static final int MIN_ROW = 3, MIN_COL = 3, MIN_NUM_TO_WIN = 3;
    public static final int MAX_NUM_TO_WIN = 25;
    public static final int MAX_PLAYERS = 10;
    public static final int MIN_PLAYERS = 2;

    private Map<Character, List<BoardPosition>>gameBoardForMem = new HashMap<Character, List<BoardPosition>>();
    //private varibles for sizing and arrays
    private int enteredRow, enteredColumn, enteredATW;

   /**
     * @param - entRow - The amount of rows entered by the player that they would like the gameboard to have.
     *          entColumn - The amount of columns entered by the player that they would like the gameboard to have.
     *          amtToWin - The amount of the same tokens in a row to win the game selected by the player.
     *
     * @pre - (MIN_ROW <= enteredRow <= MAX_ROW) AND (MIN_ROW <= entColumn <= MAX_COL) AND
     *        (MIN_NUM_TO_WIN <= amtToWin <= MAX_NUM_TO_WIN) AND (amtToWin <= enteredRow) AND
     *        (amtToWin <= entColumn)
     *
     * @post - (enteredRow = entRow) AND (enteredColumn = entColumn) AND (amountToWin = amtWin)
    */
    public GameBoardMem(int entRow, int entColumn, int amtToWin)
    {
        //assigning the entered values
        enteredRow = entRow;
        enteredColumn = entColumn;
        enteredATW = amtToWin;
    }
    
    /**
     * Places the token in the lowest avaliable slot of the selected column.
     * @param token - The token which is being placed.
     * @param column - The column number for the player piece to be inserted at.
     *
     * @pre
     * (column >= 0) AND (column < enteredColumn) AND [checkIfFree states there is space in column]
     *
     * @post
     * [self = #self except character token is added to the lowest avaialable row in column column
     * AND the map contains a pair value location with the token being the key and the location of
     * the token placed being added to the list in the pair. If the token does not previously have a 
     * key within the map, then dropToken creates a key value pair with an empty list and then adds the 
     * location of the dropped token to the list.]
     */
    public void dropToken(char token, int column)
    {
        Boolean isSpaceOccupied = false;
        int row = enteredRow;
        row--;

        //for loop checking each row in the column to see if it is empty
        for(; row >= 0; row--){
            //create a boardposition with the new row and the column.
            BoardPosition droppingToken = new BoardPosition(row, column);
            //iterate through the gameboard map entries
            for (Map.Entry<Character, List<BoardPosition>> entry : gameBoardForMem.entrySet()){
                //create a list with the corresponding value for the key.
                List<BoardPosition> ids = entry.getValue();
                //iterate through each entry within the list.
                for (BoardPosition id : ids){
                    //if the positon in the list equals the location we are trying to put a token
                    if(id.equals(droppingToken)){
                        //make isSpaceOccupied true
                        isSpaceOccupied = true;
                    }
                    //if the space is occupied
                    if(isSpaceOccupied == true){
                        //increase the row as the row up will not be occupied
                        row++;
                        //if the row is above the amount of rows in the gameboard then return as 
                        //you can not drop a token in a full column
                        if(row >= enteredRow)
                            return;
                        //if the token is already a key just add the position to the list
                        if(gameBoardForMem.containsKey(token)){
                            BoardPosition droppingTokenFix = new BoardPosition(row, column);
                            gameBoardForMem.get(token).add(droppingTokenFix);
                        } else {
                            //if the token is not a key add the token as a key with a value of an empty list
                            //then insert the position into the list
                            List<BoardPosition> boardList = new ArrayList<BoardPosition>();
                            BoardPosition droppingTokenFix2 = new BoardPosition(row, column);
                            gameBoardForMem.put(token, boardList);
                            gameBoardForMem.get(token).add(droppingTokenFix2);
                        }
                        return;
                    }
                }
            }
            //if the space is not occupied at the bottom of the column
            if(isSpaceOccupied == false && row == 0){
                //if the token is already a key just add the position to the list
                if(gameBoardForMem.containsKey(token)){
                    gameBoardForMem.get(token).add(droppingToken);
                } else {
                    //if the token is not a key add the token as a key with a value of an empty list
                    //then insert the position into the list
                    List<BoardPosition> boardList = new ArrayList<BoardPosition>();
                    gameBoardForMem.put(token, boardList);
                    gameBoardForMem.get(token).add(droppingToken);
                }
                return;
            }
        }
        // if the gameBoard is completely empty assign row to 0 and add the token as
        //a key with a value of an empty list and then insert the position into the list
        if(gameBoardForMem.isEmpty()){
            row = 0;
            BoardPosition droppingTokenEmptyMap = new BoardPosition(row, column);
            List<BoardPosition> listEmptyMap = new ArrayList<BoardPosition>();
            gameBoardForMem.put(token, listEmptyMap);
            gameBoardForMem.get(token).add(droppingTokenEmptyMap);
        }
    }

    /**
     * Determines the value of a given position on the gameboard.
     * @param position - The chosen board position
     *
     * @return - The key at the given location
     *
     * @pre - None
     *
     * @post
     * (self = #self)
     * whatsAtPos = [returns the key at the chosen board position.]
     */
    public char whatsAtPos(BoardPosition position){
        char keyAtPos = ' ';

        //iterates through each entry and checks if that entry contains the position in the list
        for (Map.Entry<Character, List<BoardPosition>> entry : gameBoardForMem.entrySet()){
            if (entry.getValue().contains(position)) {
                //if the entry contains that position return the key (token)
                keyAtPos = entry.getKey();}}
        return keyAtPos;
    }

    /**
     * Verifies whether or not the player is at a specific position on the
     * board.
     * @param position - the location of position being check
     *        player - the identifier used to see what the player's position is
     *
     * @return - True, if the player is at a location, or false otherwise.
     *
     * @pre
     * (player == a char chosen by one of the players)
     *
     * @post
     * (self = #self)
     * isPlayerAtPos = true IF [gameBoardFroMem map contains the position within the
     * list for the key value pair of the entered player token] False OW
     */
    @Override public boolean isPlayerAtPos(BoardPosition position, char player) {
        //if the map contains the key with the value of the player's token
        //meaning the player has dropped a token
        if(gameBoardForMem.containsKey(player)){
            //then check if the player's entry contains the positon and if it does
            //return true otherwise return false.
            if (gameBoardForMem.get(player).contains(position))
                return true;}
        return false;
    }

    /**
     * @return an integer which represents the max number of players
     * that can participate in a game.
     *
     * pre - None
     *
     * @post getMaxPlayers() = #MAX_PLAYERS AND (self = #self)
     */
    public int getMaxPlayers() {
        return MAX_PLAYERS;
    }

    /**
     * @return an integer which represents the minimum number 
     * of players that can participate in a game.
     *
     * pre - None
     *
     * @post getMinPlayers() = #MIN_PLAYERS AND (self = #self)
     */
    public int getMinPlayers() {
        return MIN_PLAYERS;
    }

    /**
     * @return an integer which represents number of rows
     *
     * pre - None
     *
     * @post getNumRows() = enteredRows AND (self = #self)
     */
    public int getNumRows() {
        return enteredRow;
    }

    /**
     * @return an integer which represents the max number of 
     * rows that can be chosen to play with.
     *
     * pre - None
     *
     * @post getMaxRow() = #MAX_ROW AND (self = #self)
     */
    public int getMaxRow() {
        return MAX_ROW;
    }

    /**
     * @return an integer which represents the minimum number of 
     * rows that can be chosen to play with.
     *
     * pre - None
     *
     * @post getMinRow() = #MIN_ROW AND (self = #self)
     */
    public int getMinRow() {
        return MIN_ROW;
    }

    /**
     * @return an integer which represents number of columns
     *
     * pre - None
     *
     * @post getNumColumns() = enteredColumn AND (self = #self)
     */
    public int getNumColumns() {
        return enteredColumn;
    }

    /**
     * @return an integer which represents the max number of 
     * columns that can be chosen to play with.
     *
     * pre - None
     *
     * @post getMaxCol() = #MAX_COL AND (self = #self)
     */
    public int getMaxCol() {
        return MAX_COL;
    }

    /**
     * @return an integer which represents the minimum number of 
     * columns that can be chosen to play with.
     *
     * pre - None
     *
     * @post getMinCol() = #MIN_COL AND (self = #self)
     */
    public int getMinCol() {
        return MIN_COL;
    }

    /**
     * @return an integer which represents number of tokens in a row to win
     *
     * pre - None
     *
     * @post getNumToWin() = enteredATW AND (self = #self)
     */
    public int getNumToWin() {
        return enteredATW;
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