package cpsc2150.extendedConnectX.tests;
import static org.junit.Assert.*;

import cpsc2150.extendedConnectX.models.GameBoard;
import cpsc2150.extendedConnectX.models.BoardPosition;
import org.junit.Test;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
  Abigail Clanton (alclant)
  Cassandra Phillips (Cassie488)
  Ricardo Varona (ricky643)
  Kaki Newsome (kakmonk)
 */
public class TestGameBoard {
    public String blankBoard(int row, int col) {
        int num = 0;
        String toString = "|";

        // implement toString as if it were a default function
        while(num < col) {
            if(num < 10){
                String prnt = Integer.toString(num);
                toString += " " + prnt + "|";
                num++;}
            else {
                String prnt = Integer.toString(num);
                toString += prnt + "|";
                num++;}
        }
        toString += "\n";

        row--;
        for(; row >= 0; row--) {
            for(int c = 0; c < col ; c++){
                toString += ("|" + " " + " ");}
            toString += "|\n";
        }
        return toString;
    }

    //factory to create a gamebaord with the tests
    private GameBoard gameBoardFactory(int row, int col, int numToWin) {
        return new GameBoard(row, col, numToWin);
    }
    //factory to create a boardPositon for the tests
    private BoardPosition BoardPositionFactory(int row, int col) {
        return new BoardPosition(row, col);
    }

    //Rename suggestion test case: construct_GameBoard_With_Minimum_Inputs_Test()
    //A small gameboard could also be 4,4,3, 4,4,4, 3,3,4, 3,3,7, etc, etc
    //"What are the minimum inputs I could do?"
    //Rule of Thumb: Test case name gives the information needed as if there were no comments/contracts explaining it
    //Fine to have wordy test case names
    //case for testing GameBoard(int entRow, intEntColumn, int amtToWin) in the case where the board is made from single
    //digit numbers
    @Test
    public void construct_Small_Board_Test()
    {
        GameBoard smallBoardAct = gameBoardFactory(3, 3, 3);
        char compareArr[][] = new char [3][3];
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                compareArr[i][j] = ' ';
            }
        }

        // calls blankboard and passes in row and value from array, then sets that to String expected
        String expected = blankBoard(compareArr.length, compareArr[0].length);
        // compared created string to gameBoard constructor as a string
        assertEquals(expected, smallBoardAct.toString());

    }

    //case for testing GameBoard(int entRow, intEntColumn, int amtToWin) in the case where the board is made from single
    //digit numbers of equal size
    
    //Not a unique test; construct_Small_Board_Test() also has col = row (square board), but at a smaller size
    //Now a unique test after changing construct_Small_Board_Test()
    @Test
    public void construct_Square_Board_Test()
    {
        GameBoard squareBoardAct = gameBoardFactory(8, 8, 5);
        char compareArr[][] = new char [8][8];
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                compareArr[i][j] = ' ';
            }
        }

        //creates board for comparison to the one created
        String expected = blankBoard(compareArr.length, compareArr[0].length);
        //asserts whether the board is as expected
        assertEquals(expected, squareBoardAct.toString());
    }

    //Test case can be changed to construct_GameBoard_With_Maximum_Inputs_Test() instead to test if it can truly hit the max
    //Rows, Columns, and Tokens to win effectively without problems
    //case for testing GameBoard(int entRow, intEntColumn, int amtToWin) in the case where the board is made from double
    //digit numbers
    @Test
    public void construct_Large_Board_Test()
    {
        GameBoard largeBoardAct = gameBoardFactory(15, 12, 11);
        char compareArr[][] = new char [15][12];
        for(int i = 0; i <  15; i++) {
            for (int j = 0; j < 12; j++) {
                compareArr[i][j] = ' ';
            }
        }
        //creates board for comparison to the one created
        String expected = blankBoard(compareArr.length, compareArr[0].length);
        //asserts whether the board is as expected
        assertEquals(expected, largeBoardAct.toString());
    }

    //Suggested name change could be: checkIfFree_Column_Is_Full_Test
    //case for testing checkIfFree(int col) in the case where the column has no free spots
    @Test
    public void checkIfFree_Full_Test()
    {
        //creates gameboard and adds values to it
        GameBoard fullRowBoard = gameBoardFactory(4, 4, 3);
        fullRowBoard.dropToken('X', 2);
        fullRowBoard.dropToken('O', 2);
        fullRowBoard.dropToken('X', 2);
        fullRowBoard.dropToken('O', 2);

        //asserts that the column is not free
        assertTrue(fullRowBoard.checkIfFree(2) == false);
    }

    //Suggested name change could be: checkIfFree_Column_Is_Empty() or checkIfFree_Game_Board_Is_Empty()
    //case for testing checkIfFree(int col) in the case where the column is completely free
    @Test
    public void checkIfFree_Empty_Test()
    {
        //creates gameboard
        GameBoard emptyRowBoard = gameBoardFactory(4, 4, 3);

        //asserts that the column is free
        assertTrue(emptyRowBoard.checkIfFree(1) == true);
    }

    //case for testing checkIfFree(int col) in the case where the column is partially free
    @Test
    public void checkIfFree_Partial_Test()
    {
        //creates and populates gameboard
        GameBoard partialRowBoard = gameBoardFactory(4, 4, 3);
        partialRowBoard.dropToken('X', 1);

        //asserts that the column is free
        assertTrue(partialRowBoard.checkIfFree(1) == true);
    }

    //Suggested name change could be: checkHorizWin_Lowest_Row_Of_Game_Board_Test
    //case for testing checkHorizWin(BoardPosition pos, char token) in the case where the wining piece is placed on the
    //lowest row
    @Test
    public void checkHorizWin_Low_Test()
    {
        //creates and populates gameboard
        GameBoard horizLowBoard = gameBoardFactory(4, 4, 4);
        horizLowBoard.dropToken('X', 0);
        horizLowBoard.dropToken('O', 0);
        horizLowBoard.dropToken('X', 1);
        horizLowBoard.dropToken('O', 1);
        horizLowBoard.dropToken('X', 2);
        horizLowBoard.dropToken('O', 2);
        horizLowBoard.dropToken('X', 3);

        //creates the board position for comparison
        BoardPosition newPos = BoardPositionFactory(0, 3);

        //asserts that there is a horizontal win
        assertTrue(horizLowBoard.checkHorizWin(newPos, 'X') == true);
    }

    //Suggested name change could be: checkHorizWin_Highest_Row_Of_Game_Board_Test
    //case for testing checkHorizWin(BoardPosition pos, char token) in the case where the wining piece is placed on the
    //highest row
    @Test
    public void checkHorizWin_High_Test()
    {
        //creates and populates gameboard
        GameBoard horizHighBoard = gameBoardFactory(4, 4, 4);
        horizHighBoard.dropToken('O', 2);
        horizHighBoard.dropToken('X', 2);
        horizHighBoard.dropToken('O', 2);
        horizHighBoard.dropToken('X', 2);
        horizHighBoard.dropToken('O', 1);
        horizHighBoard.dropToken('X', 3);
        horizHighBoard.dropToken('O', 3);
        horizHighBoard.dropToken('X', 3);
        horizHighBoard.dropToken('O', 1);
        horizHighBoard.dropToken('X', 3);
        horizHighBoard.dropToken('O', 1);
        horizHighBoard.dropToken('X', 1);
        horizHighBoard.dropToken('O', 0);
        horizHighBoard.dropToken('X', 0);
        horizHighBoard.dropToken('O', 0);
        horizHighBoard.dropToken('X', 0);

        //creates the board position for comparison
        BoardPosition newPos = BoardPositionFactory(3, 0);

        //asserts that there is a horizontal win
        assertTrue(horizHighBoard.checkHorizWin(newPos, 'X') == true);
    }
    //Suggested name change could be: checkHorizWin_Middle_Row_Of_Game_Board_Test
    //case for testing checkHorizWin(BoardPosition pos, char token) in the case where the wining piece is placed on a
    //middle row
    @Test
    public void checkHorizWin_Middle_Test()
    {
        //creates and populates gameboard
        GameBoard horizMidBoard = gameBoardFactory(4, 4, 4);
        horizMidBoard.dropToken('O', 0);
        horizMidBoard.dropToken('X', 0);
        horizMidBoard.dropToken('O', 1);
        horizMidBoard.dropToken('X', 1);
        horizMidBoard.dropToken('O', 1);
        horizMidBoard.dropToken('X', 2);
        horizMidBoard.dropToken('O', 3);
        horizMidBoard.dropToken('X', 3);
        horizMidBoard.dropToken('O', 3);
        horizMidBoard.dropToken('X', 2);

        //creates the board position for comparison
        BoardPosition newPos = BoardPositionFactory(1, 2);

        //asserts that there is a horizontal win
        assertTrue(horizMidBoard.checkHorizWin(newPos, 'X') == true);
    }

    //case for testing checkHorizWin(BoardPosition pos, char token) in the case where there is no wining piece
    @Test
    public void checkHorizWin_False_Test()
    {
        //creates and populates gameboard
        GameBoard horizFalseBoard = gameBoardFactory(4, 4, 4);
        horizFalseBoard.dropToken('O', 0);
        horizFalseBoard.dropToken('X', 0);
        horizFalseBoard.dropToken('O', 1);
        horizFalseBoard.dropToken('X', 1);
        horizFalseBoard.dropToken('O', 2);
        horizFalseBoard.dropToken('X', 2);
        horizFalseBoard.dropToken('O', 1);
        horizFalseBoard.dropToken('X', 3);
        horizFalseBoard.dropToken('O', 3);
        horizFalseBoard.dropToken('X', 0);
        horizFalseBoard.dropToken('O', 2);

        //creates the board position for comparison
        BoardPosition newPos = BoardPositionFactory(2, 2);

        //asserts that there is not a horizontal win
        assertTrue(horizFalseBoard.checkHorizWin(newPos, 'X') == false);
    }

    //case for testing checkVertWin(BoardPosition pos, char token) in the case where the winning piece is placed in
    // the furthest left column
    @Test
    public void checkVertWin_Left_Test()
    {
        //creates and populates gameboard
        GameBoard leftVertBoard = gameBoardFactory(4, 4, 4);
        leftVertBoard.dropToken('X', 0);
        leftVertBoard.dropToken('O', 1);
        leftVertBoard.dropToken('X', 0);
        leftVertBoard.dropToken('O', 1);
        leftVertBoard.dropToken('X', 0);
        leftVertBoard.dropToken('O', 1);
        leftVertBoard.dropToken('X', 0);

        //creates the board position for comparison
        BoardPosition newPos = BoardPositionFactory(3, 0);

        //asserts that there is a vertical win
        assertTrue(leftVertBoard.checkVertWin(newPos, 'X') == true);
    }


    //case for testing checkVertWin(BoardPosition pos, char token) in the case where the winning piece is placed in
    // the furthest right column
    @Test
    public void checkVertWin_Right_Test()
    {
        //creates and populates gameboard
        GameBoard rightVertBoard = gameBoardFactory(4, 4, 4);
        rightVertBoard.dropToken('X', 3);
        rightVertBoard.dropToken('O', 2);
        rightVertBoard.dropToken('X', 3);
        rightVertBoard.dropToken('O', 2);
        rightVertBoard.dropToken('X', 3);
        rightVertBoard.dropToken('O', 2);
        rightVertBoard.dropToken('X', 3);

        //creates the board position for comparison
        BoardPosition newPos = BoardPositionFactory(3, 3);

        //asserts that there is a vertical win
        assertTrue(rightVertBoard.checkVertWin(newPos, 'X') == true);
    }

    //case for testing checkVertWin(BoardPosition pos, char token) in the case where the winning piece is placed in
    // a middle column
    @Test
    public void checkVertWin_Mid_Test()
    {
        //creates and populates gameboard
        GameBoard midVertBoard = gameBoardFactory(4, 4, 3);
        midVertBoard.dropToken('X', 2);
        midVertBoard.dropToken('O', 1);
        midVertBoard.dropToken('X', 2);
        midVertBoard.dropToken('O', 1);
        midVertBoard.dropToken('X', 2);

        //creates the board position for comparison
        BoardPosition newPos = BoardPositionFactory(2, 2);

        //asserts that there is a vertical win
        assertTrue(midVertBoard.checkVertWin(newPos, 'X') == true);
    }

    //case for testing checkVertWin(BoardPosition pos, char token) in the case where there is no winning piece
    @Test
    public void checkVertWin_False_Test()
    {
        //creates and populates gameboard
        GameBoard falseVertBoard = gameBoardFactory(4, 4, 3);
        falseVertBoard.dropToken('X', 2);
        falseVertBoard.dropToken('O', 1);
        falseVertBoard.dropToken('X', 2);
        falseVertBoard.dropToken('O', 1);
        falseVertBoard.dropToken('X', 1);
        falseVertBoard.dropToken('O', 2);

        //creates the board position for comparison
        BoardPosition newPos = BoardPositionFactory(2, 2);

        //asserts that there is a not vertical win
        assertTrue(falseVertBoard.checkVertWin(newPos, 'O') == false);
    }

    //Suggested name change could be: checkDiagWin_Last_Token_Placed_Bottom_Left_Test()
    //case for testing checkDiagWin(BoardPosition pos, char token) in the case where the winning piece is placed at the
    // bottom left
    @Test
    public void checkDiagWin_BottomL_Test()
    {
        //creates and populates gameboard
        GameBoard diagBLBoard = gameBoardFactory(4, 4, 3);
        diagBLBoard.dropToken('O', 3);
        diagBLBoard.dropToken('X', 1);
        diagBLBoard.dropToken('O', 1);
        diagBLBoard.dropToken('X', 2);
        diagBLBoard.dropToken('O', 3);
        diagBLBoard.dropToken('X', 2);
        diagBLBoard.dropToken('O', 2);
        diagBLBoard.dropToken('X', 1);
        diagBLBoard.dropToken('O', 0);

        //creates the board position for comparison
        BoardPosition newPos = BoardPositionFactory(0, 0);

        //asserts that there is a diagonal win
        assertTrue(diagBLBoard.checkDiagWin(newPos, 'O') == true);
    }
    //Suggested name change could be: checkDiagWin_Last_Token_Placed_Top_Right_Test()
    //case for testing checkDiagWin(BoardPosition pos, char token) in the case where the winning piece is placed at the
    // top right
    @Test
    public void checkDiagWin_TopR_Test()
    {
        //creates and populates gameboard
        GameBoard diagTRBoard = gameBoardFactory(4, 4, 3);
        diagTRBoard.dropToken('X', 2);
        diagTRBoard.dropToken('O', 1);
        diagTRBoard.dropToken('X', 2);
        diagTRBoard.dropToken('O', 3);
        diagTRBoard.dropToken('X', 3);
        diagTRBoard.dropToken('O', 1);
        diagTRBoard.dropToken('X', 3);
        diagTRBoard.dropToken('O', 2);
        diagTRBoard.dropToken('X', 1);
        diagTRBoard.dropToken('O', 3);

        //creates the board position for comparison
        BoardPosition newPos = BoardPositionFactory(3, 3);

        //asserts that there is a diagonal win
        assertTrue(diagTRBoard.checkDiagWin(newPos, 'O') == true);
    }

    //Suggested name change could be: checkHorizWin_Lowest_Row_Of_Game_Board_Test
    //case for testing checkDiagWin(BoardPosition pos, char token) in the case where the winning piece is placed at the
    // top left
    @Test
    public void checkDiagWin_TopL_Test()
    {
        //creates and populates gameboard
        GameBoard diagTLBoard = gameBoardFactory(4, 4, 3);
        diagTLBoard.dropToken('X', 2);
        diagTLBoard.dropToken('O', 1);
        diagTLBoard.dropToken('X', 2);
        diagTLBoard.dropToken('O', 1);
        diagTLBoard.dropToken('X', 1);
        diagTLBoard.dropToken('O', 0);
        diagTLBoard.dropToken('X', 0);
        diagTLBoard.dropToken('O', 0);
        diagTLBoard.dropToken('X', 0);

        //creates the board position for comparison
        BoardPosition newPos = BoardPositionFactory(3, 0);

        //asserts that there is a diagonal win
        assertTrue(diagTLBoard.checkDiagWin(newPos, 'X') == true);
    }

    //case for testing checkDiagWin(BoardPosition pos, char token) in the case where the winning piece is placed at the
    //bottom left
    @Test
    public void checkDiagWin_BottomR_Test()
    {
        //creates and populates gameboard
        GameBoard diagBRBoard = gameBoardFactory(4, 4, 3);
        diagBRBoard.dropToken('X', 2);
        diagBRBoard.dropToken('O', 1);
        diagBRBoard.dropToken('X', 2);
        diagBRBoard.dropToken('O', 1);
        diagBRBoard.dropToken('X', 1);
        diagBRBoard.dropToken('O', 2);
        diagBRBoard.dropToken('X', 3);

        //creates the board position for comparison
        BoardPosition newPos = BoardPositionFactory(0, 3);

        //asserts that there is a diagonal win
        assertTrue(diagBRBoard.checkDiagWin(newPos, 'X') == true);
    }

    //case for testing checkDiagWin(BoardPosition pos, char token) in the case where the winning piece is placed in the
    //middle of a bottom left to top right diagonal
    @Test
    public void checkDiagWin_BottomLToTopR_MiddleLast_Test()
    {
        //creates and populates gameboard
        GameBoard diagBLTRMBoard = gameBoardFactory(4, 4, 3);
        diagBLTRMBoard.dropToken('O', 1);
        diagBLTRMBoard.dropToken('X', 2);
        diagBLTRMBoard.dropToken('O', 0);
        diagBLTRMBoard.dropToken('X', 2);
        diagBLTRMBoard.dropToken('O', 2);
        diagBLTRMBoard.dropToken('X', 3);
        diagBLTRMBoard.dropToken('O', 1);

        //creates the board position for comparison
        BoardPosition newPos = BoardPositionFactory(1, 1);

        //asserts that there is a diagonal win
        assertTrue(diagBLTRMBoard.checkDiagWin(newPos, 'O') == true);
    }

    //case for testing checkDiagWin(BoardPosition pos, char token) in the case where the winning piece is placed in the
    //middle of a bottom right to top left diagonal

    //Marking test;
    //Wrong output for this test. Doesn't match with project report
    @Test
    public void checkDiagWin_BottomRToTopL_MiddleLast_Test()
    {
        //creates and populates gameboard
        GameBoard diagBRTLMBoard = gameBoardFactory(4, 4, 3);
        diagBRTLMBoard.dropToken('X', 3);
        diagBRTLMBoard.dropToken('O', 1);
        diagBRTLMBoard.dropToken('X', 3);
        diagBRTLMBoard.dropToken('O', 1);
        diagBRTLMBoard.dropToken('X', 2);
        diagBRTLMBoard.dropToken('O', 3);
        diagBRTLMBoard.dropToken('X', 2);
        diagBRTLMBoard.dropToken('O', 3);
        diagBRTLMBoard.dropToken('X', 0);
        diagBRTLMBoard.dropToken('O', 2);

        //creates the board position for comparison
        BoardPosition newPos = BoardPositionFactory(2, 2);

        //asserts that there is a diagonal win
        assertTrue(diagBRTLMBoard.checkDiagWin(newPos, 'O') == true);
    }

    //case for testing checkDiagWin(BoardPosition pos, char token) in the case where there is no winning piece
    @Test
    public void checkDiagWin_Empty_Test()
    {
        //creates and populates gameboard
        GameBoard diagEmptyBoard = gameBoardFactory(4, 4, 3);

        //creates the board position for comparison
        BoardPosition newPos = BoardPositionFactory(0, 0);

        //asserts that there is not a diagonal win
        assertTrue(diagEmptyBoard.checkDiagWin(newPos, 'O') == false);
    }

    //case for testing checkTie() in the case where the board is checked for a tie when completely full
    @Test
    public void checkTieWin_Full_Test()
    {
        //creates and populates gameboard
        GameBoard tieFullBoard = gameBoardFactory(4, 4, 4);
        tieFullBoard.dropToken('O', 1);
        tieFullBoard.dropToken('X', 0);
        tieFullBoard.dropToken('O', 0);
        tieFullBoard.dropToken('X', 0);
        tieFullBoard.dropToken('O', 0);
        tieFullBoard.dropToken('X', 1);
        tieFullBoard.dropToken('O', 1);
        tieFullBoard.dropToken('X', 2);
        tieFullBoard.dropToken('O', 2);
        tieFullBoard.dropToken('X', 2);
        tieFullBoard.dropToken('O', 2);
        tieFullBoard.dropToken('X', 3);
        tieFullBoard.dropToken('O', 3);
        tieFullBoard.dropToken('X', 3);
        tieFullBoard.dropToken('O', 3);
        tieFullBoard.dropToken('X', 1);

        //asserts that the board is a tie
        assertTrue(tieFullBoard.checkTie() == true);
    }

    //case for testing checkTie() in the case where the board is checked for a tie when empty
    @Test
    public void checkTieWin_Empty_Test()
    {
        //creates and populates gameboard
        GameBoard tieEmptyBoard = gameBoardFactory(4, 4, 3);

        //asserts that the board is a tie
        assertTrue(tieEmptyBoard.checkTie() == false);
    }

    //case for testing checkTie() in the case where the board is checked for a tie with only one piece in it
    @Test
    public void checkTieWin_OnlyOne_Test()
    {
        //creates and populates gameboard
        GameBoard tieOnlyOneBoard = gameBoardFactory(4, 4, 3);
        tieOnlyOneBoard.dropToken('X', 1);

        //asserts that the board is a tie
        assertTrue(tieOnlyOneBoard.checkTie() == false);
    }

    //case for testing checkTie() in the case where the board is checked for a tie when the final piece maks it a win
    @Test
    public void checkTieWin_finalTokenWin_Test()
    {
        //creates and populates gameboard
        GameBoard tieAllButOneBoard = gameBoardFactory(4, 4, 4);
        tieAllButOneBoard.dropToken('X', 1);
        tieAllButOneBoard.dropToken('O', 0);
        tieAllButOneBoard.dropToken('X', 3);
        tieAllButOneBoard.dropToken('O', 2);
        tieAllButOneBoard.dropToken('X', 0);
        tieAllButOneBoard.dropToken('O', 1);
        tieAllButOneBoard.dropToken('X', 2);
        tieAllButOneBoard.dropToken('O', 3);
        tieAllButOneBoard.dropToken('X', 3);
        tieAllButOneBoard.dropToken('O', 2);
        tieAllButOneBoard.dropToken('X', 1);
        tieAllButOneBoard.dropToken('O', 0);
        tieAllButOneBoard.dropToken('X', 1);
        tieAllButOneBoard.dropToken('O', 0);
        tieAllButOneBoard.dropToken('X', 2);
        tieAllButOneBoard.dropToken('O', 3);

        //creates the board position for comparison
        BoardPosition newPos = BoardPositionFactory(3, 3);

        //asserts that the board is not a tie
        if(tieAllButOneBoard.checkDiagWin(newPos,'O') == true)
            assertTrue(tieAllButOneBoard.checkTie() == false);

    }

    //case for testing whatsAtPos(BoardPosition pos) in the case where the position is checked for a piece when none is there
    @Test
    public void whatsAtPos_Empty_Test()
    {
        //creates and populates gameboard
        GameBoard whatsAtPosEmptyBoard = gameBoardFactory(4, 4, 3);

        //creates the board position for comparison
        BoardPosition newPos = BoardPositionFactory(0, 0);

        //asserts that the board has the correct item
        assertEquals(whatsAtPosEmptyBoard.whatsAtPos(newPos), ' ');
    }

    //case for testing whatsAtPos(BoardPosition pos) in the case where the position is checked for a piece that it has
    //and the board is full
    @Test
    public void whatsAtPos_Full_Test()
    {
        //creates and populates gameboard
        GameBoard whatsAtPosFullBoard = gameBoardFactory(4, 4, 3);
        whatsAtPosFullBoard.dropToken('X', 0);
        whatsAtPosFullBoard.dropToken('O', 0);
        whatsAtPosFullBoard.dropToken('X', 0);
        whatsAtPosFullBoard.dropToken('O', 0);
        whatsAtPosFullBoard.dropToken('X', 2);
        whatsAtPosFullBoard.dropToken('O', 2);
        whatsAtPosFullBoard.dropToken('X', 2);
        whatsAtPosFullBoard.dropToken('O', 2);
        whatsAtPosFullBoard.dropToken('O', 1);
        whatsAtPosFullBoard.dropToken('X', 1);
        whatsAtPosFullBoard.dropToken('O', 1);
        whatsAtPosFullBoard.dropToken('X', 1);
        whatsAtPosFullBoard.dropToken('X', 3);
        whatsAtPosFullBoard.dropToken('O', 3);
        whatsAtPosFullBoard.dropToken('X', 3);
        whatsAtPosFullBoard.dropToken('O', 3);

        //creates the board position for comparison
        BoardPosition newPos = BoardPositionFactory(2, 2);

        //asserts that the board has the correct item
        assertEquals(whatsAtPosFullBoard.whatsAtPos(newPos), 'X');
    }

    //case for testing whatsAtPos(BoardPosition pos) in the case where the position is checked for a piece that does not
    //have
    @Test
    public void whatsAtPos_SemiFull_NoPlayer_Test()
    {
        //creates and populates gameboard
        GameBoard whatsAtPosSemiFullBoardNoPlayer = gameBoardFactory(4, 4, 3);
        whatsAtPosSemiFullBoardNoPlayer.dropToken('X', 0);
        whatsAtPosSemiFullBoardNoPlayer.dropToken('O', 0);
        whatsAtPosSemiFullBoardNoPlayer.dropToken('X', 2);
        whatsAtPosSemiFullBoardNoPlayer.dropToken('O', 2);
        whatsAtPosSemiFullBoardNoPlayer.dropToken('X', 0);
        whatsAtPosSemiFullBoardNoPlayer.dropToken('O', 3);
        whatsAtPosSemiFullBoardNoPlayer.dropToken('X', 3);
        whatsAtPosSemiFullBoardNoPlayer.dropToken('O', 1);

        //creates the board position for comparison
        BoardPosition newPos = BoardPositionFactory(3, 0);

        //asserts that the board has the correct item
        assertEquals(whatsAtPosSemiFullBoardNoPlayer.whatsAtPos(newPos), ' ');
    }

    //case for testing whatsAtPos(BoardPosition pos) in the case where the position is checked for a piece that it does
    //have
    @Test
    public void whatsAtPos_SemiFull_Occupied_Test()
    {
        //creates and populates gameboard
        GameBoard whatsAtPosSemiFullBoardOccupied = gameBoardFactory(4, 4, 3);
        whatsAtPosSemiFullBoardOccupied.dropToken('X', 0);
        whatsAtPosSemiFullBoardOccupied.dropToken('O', 0);
        whatsAtPosSemiFullBoardOccupied.dropToken('X', 2);
        whatsAtPosSemiFullBoardOccupied.dropToken('O', 1);
        whatsAtPosSemiFullBoardOccupied.dropToken('X', 2);
        whatsAtPosSemiFullBoardOccupied.dropToken('O', 3);
        whatsAtPosSemiFullBoardOccupied.dropToken('X', 3);
        whatsAtPosSemiFullBoardOccupied.dropToken('O', 3);

        //creates the board position for comparison
        BoardPosition newPos = BoardPositionFactory(1, 2);

        //asserts that the board has the correct item
        assertEquals(whatsAtPosSemiFullBoardOccupied.whatsAtPos(newPos), 'X');
    }

    //case for testing whatsAtPos(BoardPosition pos) in the case where the position is checked for a piece that it does
    //have and is surrounded by others
    @Test
    public void whatsAtPos_SemiFull_Surrounded_Test()
    {
        //creates and populates gameboard
        GameBoard whatsAtPosSemiFullBoardSurrounded = gameBoardFactory (4, 4, 3);
        whatsAtPosSemiFullBoardSurrounded.dropToken('X', 0);
        whatsAtPosSemiFullBoardSurrounded.dropToken('O', 0);
        whatsAtPosSemiFullBoardSurrounded.dropToken('X', 0);
        whatsAtPosSemiFullBoardSurrounded.dropToken('O', 0);
        whatsAtPosSemiFullBoardSurrounded.dropToken('X', 2);
        whatsAtPosSemiFullBoardSurrounded.dropToken('O', 1);
        whatsAtPosSemiFullBoardSurrounded.dropToken('X', 2);
        whatsAtPosSemiFullBoardSurrounded.dropToken('O', 1);
        whatsAtPosSemiFullBoardSurrounded.dropToken('X', 1);
        whatsAtPosSemiFullBoardSurrounded.dropToken('O', 3);
        whatsAtPosSemiFullBoardSurrounded.dropToken('X', 3);
        whatsAtPosSemiFullBoardSurrounded.dropToken('O', 3);
        whatsAtPosSemiFullBoardSurrounded.dropToken('X', 3);

        //creates the board position for comparison
        BoardPosition newPos = BoardPositionFactory(3, 1);

        //asserts that the board has the correct item
        assertEquals(whatsAtPosSemiFullBoardSurrounded.whatsAtPos(newPos), ' ');
    }

    //case for testing isPlayerAtPos(BoardPosition pos, char player) in the case where the position is checked for a piece that it does
    //have and is the only one
    @Test
    public void isPlayerAtPos_IsAt_Test()
    {
        //creates and populates gameboard
        GameBoard posIsAtBoard = gameBoardFactory(4, 4, 3);
        posIsAtBoard.dropToken('X', 2);

        //creates the board position for comparison
        BoardPosition newPos = BoardPositionFactory(0, 2);

        //asserts that the character is at the position
        assertTrue(posIsAtBoard.isPlayerAtPos(newPos, 'X') == true);
    }

    //case for testing isPlayerAtPos(BoardPosition pos, char player) in the case where the position is checked for a piece that it does
    //not have and is the only one
    @Test
    public void isPlayerAtPos_IsNotAt_Test()
    {
        //creates and populates gameboard
        GameBoard posIsNotAtBoard = gameBoardFactory(4, 4, 3);
        posIsNotAtBoard.dropToken('O', 1);

        //creates the board position for comparison
        BoardPosition newPos = BoardPositionFactory(0, 1);

        //asserts that the character is not at the position
        assertTrue(posIsNotAtBoard.isPlayerAtPos(newPos, 'X') == false);
    }

    //case for testing isPlayerAtPos(BoardPosition pos, char player) in the case where the position is checked for a piece that it does
    //not have and is empty
    @Test
    public void isPlayerAtPos_NoPlayer_Test()
    {
        //creates and populates gameboard
        GameBoard posNoPlayerBoard = gameBoardFactory(4, 4, 3);
        posNoPlayerBoard.dropToken('O', 1);
        posNoPlayerBoard.dropToken('X', 3);
        posNoPlayerBoard.dropToken('O', 0);

        //creates the board position for comparison
        BoardPosition newPos = BoardPositionFactory(0, 2);

        //asserts that the character is not at the position
        assertTrue(posNoPlayerBoard.isPlayerAtPos(newPos, 'X') == false);
    }

    //case for testing isPlayerAtPos(BoardPosition pos, char player) in the case where the position is checked for a piece that is
    //at the top right
    @Test
    public void isPlayerAtPos_topRight_Test()
    {
        //creates and populates gameboard
        GameBoard posNoPlayerBoard = gameBoardFactory(4, 4, 3);
        posNoPlayerBoard.dropToken('X', 3);
        posNoPlayerBoard.dropToken('O', 3);
        posNoPlayerBoard.dropToken('X', 3);
        posNoPlayerBoard.dropToken('O', 3);

        //creates the board position for comparison
        BoardPosition newPos = BoardPositionFactory(3, 3);

        //asserts that the character is at the position
        assertTrue(posNoPlayerBoard.isPlayerAtPos(newPos, 'O') == true);
    }

    //case for testing isPlayerAtPos(BoardPosition pos, char player) in the case where the position is not in the position
    //in a full board
    @Test
    public void isPlayerAtPos_Full_Test()
    {
        //creates and populates gameboard
        GameBoard posFullBoard = gameBoardFactory(4, 4, 3);
        posFullBoard.dropToken('X', 0);
        posFullBoard.dropToken('O', 0);
        posFullBoard.dropToken('X', 0);
        posFullBoard.dropToken('O', 0);
        posFullBoard.dropToken('X', 2);
        posFullBoard.dropToken('O', 2);
        posFullBoard.dropToken('X', 2);
        posFullBoard.dropToken('O', 2);
        posFullBoard.dropToken('X', 1);
        posFullBoard.dropToken('O', 3);
        posFullBoard.dropToken('X', 3);
        posFullBoard.dropToken('O', 1);
        posFullBoard.dropToken('X', 1);
        posFullBoard.dropToken('O', 3);
        posFullBoard.dropToken('X', 3);
        posFullBoard.dropToken('O', 1);

        //creates the board position for comparison
        BoardPosition newPos = BoardPositionFactory(3, 1);

        //asserts that the character is not at the position
        assertTrue(posFullBoard.isPlayerAtPos(newPos, 'X') == false);
    }

    //case for testing dropToken(char player, int column) in the case where the token is placed in the bottom left
    @Test
    public void dropToken_Left_Bottom_Test()
    {
        //creates and populates gameboard
        GameBoard dropTokenEmptyCol = gameBoardFactory(4, 4, 3);
        dropTokenEmptyCol.dropToken('X', 0);

        //creates the board position for comparison
        BoardPosition newPos = BoardPositionFactory(0, 0);

        //asserts that the character dropped correctly
        assertEquals(dropTokenEmptyCol.whatsAtPos(newPos), 'X');
    }

    //case for testing dropToken(char player, int column) in the case where the token is placed in the top left
    @Test
    public void dropToken_Left_Top_Test()
    {
        //creates and populates gameboard
        GameBoard dropTokenFillingCol = gameBoardFactory(4, 4, 3);
        dropTokenFillingCol.dropToken('X', 0);
        dropTokenFillingCol.dropToken('O', 0);
        dropTokenFillingCol.dropToken('X', 0);
        dropTokenFillingCol.dropToken('O', 0);

        //creates the board position for comparison
        BoardPosition newPos = BoardPositionFactory(3, 0);

        //asserts that the character dropped correctly
        assertEquals(dropTokenFillingCol.whatsAtPos(newPos), 'O');
    }

    //case for testing dropToken(char player, int column) in the case where the token is placed in the middle
    @Test
    public void dropToken_Middle_of_Board_Test()
    {
        //creates and populates gameboard
        GameBoard dropTokenOnSameTokenType = gameBoardFactory(4, 4, 3);
        dropTokenOnSameTokenType.dropToken('X', 0);
        dropTokenOnSameTokenType.dropToken('O', 1);
        dropTokenOnSameTokenType.dropToken('X', 1);
        dropTokenOnSameTokenType.dropToken('O', 2);
        dropTokenOnSameTokenType.dropToken('X', 0);

        //creates the board position for comparison
        BoardPosition newPos = BoardPositionFactory(1, 1);

        //asserts that the character dropped correctly
        assertEquals(dropTokenOnSameTokenType.whatsAtPos(newPos), 'X');
    }

    //case for testing dropToken(char player, int column) in the case where the token is placed in the bottom right
    @Test
    public void dropToken_Right_Bottom_Test()
    {
        //creates and populates gameboard
        GameBoard dropTokenEmptyCol = gameBoardFactory(4, 4, 3);
        dropTokenEmptyCol.dropToken('X', 3);

        //creates the board position for comparison
        BoardPosition newPos = BoardPositionFactory(0, 3);

        //asserts that the character dropped correctly
        assertEquals(dropTokenEmptyCol.whatsAtPos(newPos), 'X');
    }

    //case for testing dropToken(char player, int column) in the case where the token is placed in the top right
    @Test
    public void dropToken_Right_Top_Test()
    {
        //creates and populates gameboard
        GameBoard dropTokenFillingCol = gameBoardFactory(4, 4, 3);
        dropTokenFillingCol.dropToken('X', 3);
        dropTokenFillingCol.dropToken('O', 3);
        dropTokenFillingCol.dropToken('X', 3);
        dropTokenFillingCol.dropToken('O', 3);

        //creates the board position for comparison
        BoardPosition newPos = BoardPositionFactory(3, 3);

        //asserts that the character dropped correctly
        assertEquals(dropTokenFillingCol.whatsAtPos(newPos), 'O');
    }
}