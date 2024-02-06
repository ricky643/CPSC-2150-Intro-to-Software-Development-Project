package cpsc2150.extendedConnectX.models;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
  Abigail Clanton (alclant)
  Cassandra Phillips (Cassie488)
  Ricardo Varona (ricky643)
  Kaki Newsome (kakmonk)
 */


/**IGameBorard Contract
 * Initalizes necessary functions to create connect four board
 *
 *@initialization Ensures: The gameboard for connect four will be intialized with all blank character values
 *
 *@defines: userEnteredRow = Z
 *          userEnteredColumn = Z
 *          setAmountToWin = Z
 *
 *@constraints: [never going to have an instance where you have a space between two tokens, an x or o above a blank space because of how gravity/dropToken works]
 */
public interface IGameBoard
{

    /**
     * @return an integer which represents the max number of
     * rows that can be chosen to play with.
     *
     * pre - None
     *
     * @post getMaxRow() = #MAX_ROW AND (self = #self)
     */

    //functions used in IGameBoard
    public int getNumRows();

    /**
     * @return an integer which represents the max number of
     * columns that can be chosen to play with.
     *
     * pre - None
     *
     * @post getMaxCol() = #MAX_COL AND (self = #self)
     */
    public int getNumColumns();

    /**
     * @return an integer which represents the maximum number of
     * tokens in a row in order to win that can be chosen by the player.
     *
     * pre - None
     *
     * @post getMaxNTW() = #MAX_NUM_TO_WIN AND (self = #self)
     */
    public int getNumToWin();

    /**
     * Places the token in the lowest avaliable slot of the selected column.
     * @param token - The token which is being placed
     * @param column - column number for the player piece to be inserted at
     *
     * @pre
     * (column >= 0) AND (column < userEnteredColumn) AND [checkIfFree states there is space in column]
     *
     * @post
     *[self = #self except character token is added to the lowest avaialable row in column column]
     */
    public void dropToken(char token, int column);

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
    public char whatsAtPos(BoardPosition pos);


    /**
     * Checks to see if the position the player wants to place the piece in is
     * available.
     * @param col - the column the player wants to drop a token into.
     *
     * @return - True, if a piece can be accepted into the column; returns
     * false if it cannot be.
     *
     * @pre
     * (0 <= col < getNumColumns())
     *
     * @post
     * AND self = #self
     * checkIfFree() = [For true, top space of col = '', false col != '']
     */
    default public boolean checkIfFree(int col) {
        //set number of rows
        int row = getNumRows();
        row--;

        //creates a board psotion at the column and checks if free
        BoardPosition current = new BoardPosition(row, col);
        if(whatsAtPos(current) == ' ')
            return true; // returns current position
        return false;
    }

    /**
     * Verifies whether or not the player is at a specific position on the
     * board.
     * @param pos - the location of position being check
     * @param player - the identifier used to see what the player's position is
     *
     * @return - True, if the player is at a location, or false otherwise.
     *
     * @pre
     * (player == a char chosen by one of the players)
     *
     * @post
     * (self = #self)
     * isPlayerAtPos = true IFF gameBoardArray[pos.getRow()]|[pos.getColumn] == player, False OW
     */
    default public boolean isPlayerAtPos(BoardPosition pos, char player) {
        if(whatsAtPos(pos) == player)
            return true;
        else
            return false;
    }

    /**
     * Checks the board to see if there are any free spots left.
     * @param - none
     *
     * @return - True, if the game board has no more empty spaces, false
     * otherwise.
     *
     * @pre - None
     *
     * @post
     * (self = #self)
     * checkTie = [True, highest row in at least one col = NULL, else false]
     */
    default public boolean checkTie() {
        //loop that exits upon finding an empty slot
        for(int col = 0; col < getNumColumns(); col++) {
            if(checkIfFree(col) == true || checkForWin(col) == true) // check if free returned true so there are still spots left on the board
                return false;
            }
        return true;
    }

    /**
     * Checks the row of the most recent piece to determine if there are 5
     * pieces of the same type in the row consecutively.
     * @param token - The player who placed the piece
     * @param pos - The board position of the last piece
     *
     * @return - True, if the game resulted in a win by row, false otherwise.
     *
     * @pre
     * (token == a char chosen by one of the players as their token)
     *
     * @post
     * (self = #self)
     * checkHorzWin = [Return true when there are 5 char of same type in col in gameBoard, false otherwise]
     */
    default public boolean checkHorizWin(BoardPosition pos, char token) {
        int outUpperCols = getNumColumns(), outLowerCols = 0, row = pos.getRow(), col = pos.getColumn(), num = 0;
        num++;
        outUpperCols++;
        outLowerCols--;

        //left side of horizontal
        col--;
        while(col >= 0){
            BoardPosition currentPos = new BoardPosition(row, col);
            if (isPlayerAtPos(currentPos, token)) {
                col--;
                num++;}
            else
                col = outLowerCols;
        }

        //right side of horizontal
        col = pos.getColumn();
        col++;
        while(col < getNumColumns()){
            BoardPosition currentPos = new BoardPosition(row, col);
            if (isPlayerAtPos(currentPos, token)) {
                num++;
                col++;}
            else
                col = outUpperCols;
        }

        //checks for minimum number for win
        if(num >= getNumToWin())
            return true;
        else
            return false;
    }

    /**
     * Checks the column of the most recent piece to determine if there are 5
     * pieces of the same same in the column consecutively.
     * @param token - The player who placed the piece
     * @param pos - The board position of the last piece
     *
     * @return - True, if the game resulted in a win by column, false otherwise.
     *
     * @pre
     * (token == a char chosen by one of the players as their token)
     *
     * @post
     * (self = #self)
     * checkVertWin()  =[Return true when there are 5 char of same type in gameboard row, false otherwise]
     */
     default public boolean checkVertWin(BoardPosition pos, char token) {
        int row = pos.getRow(), col = pos.getColumn(), num = 0, outLowerRows = 0;
        num++;
        outLowerRows--;
        row--;

        //starts at the top and works down
        while(row >= 0){
            BoardPosition currentPos = new BoardPosition(row, col);
            if(isPlayerAtPos(currentPos, token)) {
                num++;
                row--;}
            else
                row = outLowerRows;
        }

         //checks for minimum number for win
        if(num >= getNumToWin())
            return true;
        else
            return false;
    }

    /**
     * Checks the places diagonal to the most recent piece to determine if
     * there are 5 pieces of the same type in a diagonal consecutively.
     * @param token - The player who placed the piece
     * @param pos - The board position of the last piece
     *
     * @return - True, if the game resulted in a win diagonally, false
     * otherwise.
     *
     * @pre
     * (token == a char chosen by one of the players as their token)
     *
     * @post
     * (self = #self)
     * [Return true when there are 5 char of same type in diagonal, false otherwise]
     */
    default public boolean checkDiagWin(BoardPosition pos, char token) {
        int outUpperCols = getNumColumns(), outLowerCols = 0, row = pos.getRow(), col = pos.getColumn(), leftDiag = 0, rightDiag = 0;
        leftDiag++;
        rightDiag++;
        outUpperCols++;
        outLowerCols--;

        //top right diagonal
        col++;
        row--;
        while(col < getNumColumns() && row >= 0 && col >= 0 && row < getNumRows()){
            BoardPosition currentPos = new BoardPosition(row, col);
            if (isPlayerAtPos(currentPos, token)) {
                col++;
                row--;
                rightDiag++;}
            else
                col = outLowerCols;
        }

        //bottom right diagonal
        col = pos.getColumn();
        row = pos.getRow();
        col--;
        row++;
        while(col < getNumColumns() && row >= 0 && col >= 0 && row < getNumRows()){
            BoardPosition currentPos = new BoardPosition(row, col);
            if (isPlayerAtPos(currentPos, token)) {
                col--;
                row++;
                rightDiag++;}
            else
                col = outUpperCols;
        }

        //bottom left diagonal
        col = pos.getColumn();
        row = pos.getRow();
        col++;
        row++;
        while(col < getNumColumns() && row >= 0 && col >= 0 && row < getNumRows()){
            BoardPosition currentPos = new BoardPosition(row, col);
            if (isPlayerAtPos(currentPos, token)) {
                col++;
                row++;
                leftDiag++;}
            else
                col = outLowerCols;
        }

        //top left diagonal
        col = pos.getColumn();
        row = pos.getRow();
        col--;
        row--;
        while(col < getNumColumns() && row >= 0 && col >= 0 && row < getNumRows()){
            BoardPosition currentPos = new BoardPosition(row, col);
            if (isPlayerAtPos(currentPos, token)) {
                col--;
                row--;
                leftDiag++;}
            else
                col = outUpperCols;
        }

        //checks for minimum number for win
        if(leftDiag >= getNumToWin()|| rightDiag >= getNumToWin())
            return true;
        else
            return false;
    }

    /**
     * Checks if the newest piece placed makes a game win using the other
     * variants of the fucntions called checkHorizWin, checkVertWin, and checkDiagWin.
     * @param col - column number for the player piece
     *
     * @return - True, if the last piece placed makes a game win, false
     * otherwise.
     *
     * @pre
     * (0 <= col < getNumColumns())
     *
     * @post
     * (self = # self)
     * [true, when the placed piece makes enough of the same pieces in a row to
     * win in the vertical, horizontal or diagonal]
     */
    default public boolean checkForWin(int col) {
        int row = getNumRows();
        row--;
        char tkn;

        //checking at a specific position
        BoardPosition current = new BoardPosition(row, col);
        tkn = whatsAtPos(current);
        while(tkn == ' '){
            row--;
            current = new BoardPosition(row, col);
            tkn = whatsAtPos(current);
        }

        //if any of the win condtions are true return true
        if (checkDiagWin(current, tkn) == true || checkHorizWin(current, tkn) == true || checkVertWin(current, tkn) == true)
            return true;
        else
            return false;
    }
}
