package cpsc2150.extendedConnectX.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import cpsc2150.extendedConnectX.models.BoardPosition;
import cpsc2150.extendedConnectX.models.GameBoardMem;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
  Abigail Clanton (alclant)
  Cassandra Phillips (Cassie488)
  Ricardo Varona (ricky643)
  Kaki Newsome (kakmonk)
 */
public class TestGameBoardMem {

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

    //factory to create a gamebaordMem with the tests
    private GameBoardMem gameBoardFactoryMem(int row, int col, int numToWin) {
        return new GameBoardMem(row, col, numToWin);
    }

    //factory to create a boardPosition for the tests
    private BoardPosition BoardPositionFactory(int row, int col) {
        return new BoardPosition(row, col);
    }

    //Since TestGameBoardMem has a different implementation of the game board, it is technically it's own method
    //Apart from TestGameBoard
    //Therefore, simply adding in that detail allows it to be its own test case
    //As well as include the test case report for it as well
    //case for testing GameBoardMem(int entRow, intEntColumn, int amtToWin) in the case where the board is made from single
    //digit numbers
    @Test
    public void construct_Small_Board_Test()
    {
        GameBoardMem smallBoard = gameBoardFactoryMem(3, 3, 4);
        char compareArr[][] = new char [3][3];
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                compareArr[i][j] = ' ';
            }
        }

        // calls blankboard and passes in row and value from array, then sets that to String expected
        String expected = blankBoard(compareArr.length, compareArr[0].length);
        // compared created string to gameBoard constructor as a string
        assertEquals(expected, smallBoard.toString());
    }

    //case for testing GameBoardMem(int entRow, intEntColumn, int amtToWin) in the case where the board is made from single
    //digit numbers of equal size
    @Test
    public void construct_Square_Board_Test()
    {
        GameBoardMem squareBoard =  gameBoardFactoryMem(8, 8, 5);
        char compareArr[][] = new char [8][8];
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                compareArr[i][j] = ' ';
            }
        }

        // calls blankboard and passes in row and value from array, then sets that to String expected
        String expected = blankBoard(compareArr.length, compareArr[0].length);
        // compared created string to gameBoard constructor as a string
        assertEquals(expected, squareBoard.toString());
    }

    //case for testing GameBoardMem(int entRow, intEntColumn, int amtToWin) in the case where the board is made from double
    //digit numbers
    @Test
    public void construct_Large_Board_Test()
    {
        GameBoardMem largeBoard = gameBoardFactoryMem(100, 100, 11);
        char compareArr[][] = new char [100][100];
        for(int i = 0; i <  100; i++) {
            for (int j = 0; j < 100; j++) {
                compareArr[i][j] = ' ';
            }
        }

        //creates board for comparison to the one created
        String expected = blankBoard(compareArr.length, compareArr[0].length);
        //asserts whether the board is as expected
        assertEquals(expected, largeBoard.toString());
    }

    // case for testing checkIfFree on a full column
    @Test
    public void checkIfFree_Full_Test()
    {
        // creates gameBoardMem and drop tokens into a column
        GameBoardMem fullRowBoard = gameBoardFactoryMem(4, 4, 3);
        fullRowBoard.dropToken('X', 2);
        fullRowBoard.dropToken('O', 2);
        fullRowBoard.dropToken('X', 2);
        fullRowBoard.dropToken('O', 2);

        // asserts column is not free
        assertTrue(fullRowBoard.checkIfFree(2) == false);
    }

    // case for testing checkIfFree on an empty column
    @Test
    public void checkIfFree_Empty_Test()
    {   
        // creating gameBoardMem empty
        GameBoardMem emptyRowBoard = gameBoardFactoryMem(4, 4, 3);
        // asserting column has space
        assertTrue(emptyRowBoard.checkIfFree(1) == true);
    }

    // case for testing checkIfFree on a partially filled column
    @Test
    public void checkIfFree_Partial_Test()
    {
        // creating gameBoardMem and dropping one token
        GameBoardMem partialRowBoard = gameBoardFactoryMem(4, 4, 3);
        partialRowBoard.dropToken('X', 1);
        
        // asserting column still has free spaces
        assertTrue(partialRowBoard.checkIfFree(1) == true);
    }

    // case for testing checkHorizWin on bottom row
    @Test
    public void checkHorizWin_Low_Test()
    {   
        // creating gameBoardMem and dropping tokens
        GameBoardMem horizLowBoard = gameBoardFactoryMem(4, 4, 4);
        horizLowBoard.dropToken('X', 0);
        horizLowBoard.dropToken('O', 0);
        horizLowBoard.dropToken('X', 1);
        horizLowBoard.dropToken('O', 1);
        horizLowBoard.dropToken('X', 2);
        horizLowBoard.dropToken('O', 2);
        horizLowBoard.dropToken('X', 3);

        BoardPosition newPos = BoardPositionFactory(0, 3);

        // asserting that horizontal win is true for bottom row win
        assertTrue(horizLowBoard.checkHorizWin(newPos, 'X') == true);
    }

    // case for testing checkHorizWin on top row
    @Test
    public void checkHorizWin_High_Test()
    {   
        // creating gameBoardMem and dropping token
        GameBoardMem horizHighBoard = gameBoardFactoryMem(4, 4, 4);
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

        BoardPosition newPos = BoardPositionFactory(3, 0);

        // asserting that the horizontal win is true for top row
        assertTrue(horizHighBoard.checkHorizWin(newPos, 'X') == true);
    }

    // case for testing checkHorizWin when piece is dropped in the middle to win
    @Test
    public void checkHorizWin_Middle_Test()
    {   
        // creating gameBoardMem and dropping tokens
        GameBoardMem horizMidBoard = gameBoardFactoryMem(4, 4, 4);
        horizMidBoard.dropToken('O', 0);
        horizMidBoard.dropToken('X', 0);
        horizMidBoard.dropToken('O', 1);
        horizMidBoard.dropToken('X', 1);

        horizMidBoard.dropToken('O', 1);
        horizMidBoard.dropToken('O', 3);
        horizMidBoard.dropToken('X', 3);
        
        horizMidBoard.dropToken('X', 2);
        horizMidBoard.dropToken('X', 2);

        BoardPosition newPos = BoardPositionFactory(1, 2);

        // asserting that horizontal win is true
        assertTrue(horizMidBoard.checkHorizWin(newPos, 'X') == true);
    }

    // case for testing checkHorizWin where it fails
    @Test
    public void checkHorizWin_False_Test()
    {
        // creating gameBoardMem and dropping tokens
        GameBoardMem horizFalseBoard = gameBoardFactoryMem(4, 4, 4);
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

        BoardPosition newPos = BoardPositionFactory(1, 2);
        // asserting the horizontal win is false
        assertTrue(horizFalseBoard.checkHorizWin(newPos, 'X') == false);
    }

    // case for testing checkVertWin in left column
    @Test
    public void checkVertWin_Left_Test()
    {
        // creating gameBoardMem and dropping tokens in left column
        GameBoardMem leftVertBoard = gameBoardFactoryMem(4, 4, 4);
        leftVertBoard.dropToken('X', 0);
        leftVertBoard.dropToken('O', 1);
        leftVertBoard.dropToken('X', 0);
        leftVertBoard.dropToken('O', 1);
        leftVertBoard.dropToken('X', 0);
        leftVertBoard.dropToken('O', 1);
        leftVertBoard.dropToken('X', 0);

        BoardPosition newPos = BoardPositionFactory(3, 0);
        // asserting that vertical win is true
        assertTrue(leftVertBoard.checkVertWin(newPos, 'X') == true);
    }

    // case for testing checkVertWin in most right column
    @Test
    public void checkVertWin_Right_Test()
    {   
        // creating gameBoardMem and dropping tokens in the right column
        GameBoardMem rightVertBoard = gameBoardFactoryMem(4, 4, 4);
        rightVertBoard.dropToken('X', 3);
        rightVertBoard.dropToken('O', 2);
        rightVertBoard.dropToken('X', 3);
        rightVertBoard.dropToken('O', 2);
        rightVertBoard.dropToken('X', 3);
        rightVertBoard.dropToken('O', 2);
        rightVertBoard.dropToken('X', 3);

        BoardPosition newPos = BoardPositionFactory(3, 3);

        // asserting vertical win is true
        assertTrue(rightVertBoard.checkVertWin(newPos, 'X') == true);
    }

    // test for checkVertWin in middle of board
    @Test
    public void checkVertWin_Mid_Test()
    {
        // creating gameBoardMem and dropping tokens in middle of board
        GameBoardMem midVertBoard = gameBoardFactoryMem(4, 4, 3);
        midVertBoard.dropToken('X', 2);
        midVertBoard.dropToken('O', 1);
        midVertBoard.dropToken('X', 2);
        midVertBoard.dropToken('O', 1);
        midVertBoard.dropToken('X', 2);

        BoardPosition newPos = BoardPositionFactory(2, 2);
        // asserting vertical win is true
        assertTrue(midVertBoard.checkVertWin(newPos, 'X') == true);
    }

    // test for checkVertWin when it is false
    @Test
    public void checkVertWin_False_Test()
    {
        // creating gameBoardMem and dropping tokens
        GameBoardMem falseVertBoard = gameBoardFactoryMem(4, 4, 3);
        falseVertBoard.dropToken('X', 2);
        falseVertBoard.dropToken('O', 1);
        falseVertBoard.dropToken('X', 2);
        falseVertBoard.dropToken('O', 1);
        falseVertBoard.dropToken('X', 1);
        falseVertBoard.dropToken('O', 2);

        BoardPosition newPos = BoardPositionFactory(2, 2);

        // asserting vertical win is false
        assertTrue(falseVertBoard.checkVertWin(newPos, 'O') == false);
    }

    // case for testing checkDiagWin in bottom left corner
    @Test
    public void checkDiagWin_BottomL_Test()
    {
        // creating gameBoardMem and dropping tokens
        GameBoardMem diagBLBoard = gameBoardFactoryMem(4, 4, 3);
        diagBLBoard.dropToken('O', 3);
        diagBLBoard.dropToken('X', 1);
        diagBLBoard.dropToken('O', 1);
        diagBLBoard.dropToken('X', 2);
        diagBLBoard.dropToken('O', 3);
        diagBLBoard.dropToken('X', 2);
        diagBLBoard.dropToken('O', 2);
        diagBLBoard.dropToken('X', 1);
        diagBLBoard.dropToken('O', 0);

        BoardPosition newPos = BoardPositionFactory(0, 0);
        // asserting diagonal win is true
        assertTrue(diagBLBoard.checkDiagWin(newPos, 'O') == true);
    }
    //This test case name doesn't match with the one in TestGameBoard
    //Copy-Paste TestGameBoard test cases, only changing GameBoard to GameBoardMem
    //Main essence of using the Factory Method Design
    // case for testing checkDiagWin in top right
    @Test
    public void checkDiagWin_TopR_Test()
    {
        // creating gameBoardMem and dropping tokens
        GameBoardMem diagTRBoard = gameBoardFactoryMem(4, 4, 3);
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

        BoardPosition newPos = BoardPositionFactory(3, 3);
        // asserting diagonal win is true
        assertTrue(diagTRBoard.checkDiagWin(newPos, 'O') == true);
    }

    //This test case name doesn't match with the one in TestGameBoard
    //Copy-Paste TestGameBoard test cases, only changing GameBoard to GameBoardMem
    //Main essence of using the Factory Method Design
    // case for testing checkDiagWin in top left
    @Test
    public void checkDiagWin_TopL_Test()
    {
        // creating gameBoardMem and dropping tokens
        GameBoardMem diagTLBoard = gameBoardFactoryMem(4, 4, 3);
        diagTLBoard.dropToken('X', 2);
        diagTLBoard.dropToken('O', 1);
        diagTLBoard.dropToken('X', 2);
        diagTLBoard.dropToken('O', 1);
        diagTLBoard.dropToken('X', 1);
        diagTLBoard.dropToken('O', 0);
        diagTLBoard.dropToken('X', 0);
        diagTLBoard.dropToken('O', 0);
        diagTLBoard.dropToken('X', 0);
        BoardPosition newPos = BoardPositionFactory(3, 0);
        // asserting diagonal win is true
        assertTrue(diagTLBoard.checkDiagWin(newPos, 'X') == true);
    }

    //This test case name doesn't match with the one in TestGameBoard
    //Copy-Paste TestGameBoard test cases, only changing GameBoard to GameBoardMem
    //Main essence of using the Factory Method Design
    // case for testing checkDiagWin in bottom right
    @Test
    public void checkDiagWin_BottomR_Test()
    {   
        // creating gameBoardMem and dropping tokens
        GameBoardMem diagBRBoard = gameBoardFactoryMem(4, 4, 3);
        diagBRBoard.dropToken('X', 2);
        diagBRBoard.dropToken('O', 1);
        diagBRBoard.dropToken('X', 2);
        diagBRBoard.dropToken('O', 1);
        diagBRBoard.dropToken('X', 1);
        diagBRBoard.dropToken('O', 2);
        diagBRBoard.dropToken('X', 3);

        BoardPosition newPos = BoardPositionFactory(0, 3);
        // asserting diagonal win is true
        assertTrue(diagBRBoard.checkDiagWin(newPos, 'X') == true);
    }

    // case for testing checkDiagWin bottom left to top right when winning piece is placed in the middle
    @Test
    public void checkDiagWin_BottomLToTopR_MiddleLast_Test()
    {
        // creating gameBoardMem and dropping tokens
        GameBoardMem diagBLTRMBoard = gameBoardFactoryMem(4, 4, 3);
        diagBLTRMBoard.dropToken('O', 1);
        diagBLTRMBoard.dropToken('X', 2);
        diagBLTRMBoard.dropToken('O', 0);
        diagBLTRMBoard.dropToken('X', 2);
        diagBLTRMBoard.dropToken('O', 2);
        diagBLTRMBoard.dropToken('X', 3);
        diagBLTRMBoard.dropToken('O', 1);
    
        BoardPosition newPos = BoardPositionFactory(1, 1);
        // asserting diag win is true
        assertTrue(diagBLTRMBoard.checkDiagWin(newPos, 'O') == true);
    }
    
    // case for testing checkDiagWin bottom right to top left when winning piece is placed in middle
    @Test
    public void checkDiagWin_BottomRToTopL_MiddleLast_Test()
    {
        // creating gameBoardMem and dropping tokens
        GameBoardMem diagBRTLMBoard = gameBoardFactoryMem(4, 4, 3);
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

        BoardPosition newPos = BoardPositionFactory(2, 2);
        // asserting diagonal win is true
        assertTrue(diagBRTLMBoard.checkDiagWin(newPos, 'O') == true);
    }

    // case for testing checkDiagWin when its empty
    @Test
    public void checkDiagWin_Empty_Test()
    {
        // creating gameBoardMem and keeping it empty
        GameBoardMem diagEmptyBoard = gameBoardFactoryMem(4, 4, 3);
        BoardPosition newPos = BoardPositionFactory(0, 0);
        // asserting diagonal win is false
        assertTrue(diagEmptyBoard.checkDiagWin(newPos, 'O') == false);
    }

    // case for testing checkTie when board is full
    @Test
    public void checkTieWin_Full_Test()
    {
        // creating gameBoardMem and dropping tokens
        GameBoardMem tieFullBoard = gameBoardFactoryMem(4, 4, 4);
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
        // asserting tie is true
        assertTrue(tieFullBoard.checkTie() == true);
    }

    // case for testing checkTie when board is empty
    @Test
    public void checkTieWin_Empty_Test()
    {
        // creating gameBoardMem and keeping it empty
        GameBoardMem tieEmptyBoard = gameBoardFactoryMem(4, 4, 3);
        // asserting tie is false
        assertTrue(tieEmptyBoard.checkTie() == false);
    }

    // case for testing checkTie when there is only one token in board
    @Test
    public void checkTieWin_OnlyOne_Test()
    {
        // creating gameBoardMem and dropping one(1) token
        GameBoardMem tieOnlyOneBoard = gameBoardFactoryMem(4, 4, 3);
        tieOnlyOneBoard.dropToken('X', 1);
        // asserting tie is false
        assertTrue(tieOnlyOneBoard.checkTie() == false);
    }

    // case for testing checkTie where all but one slot in board is filled
    @Test
    public void checkTieWin_AllButOne_Test()
    {
        // creating gameBoardMem and dropping tokens
        GameBoardMem tieAllButOneBoard = gameBoardFactoryMem(4, 4, 3);
        tieAllButOneBoard.dropToken('O', 1);
        tieAllButOneBoard.dropToken('X', 0);
        tieAllButOneBoard.dropToken('O', 0);
        tieAllButOneBoard.dropToken('X', 0);
        tieAllButOneBoard.dropToken('O', 0);
        tieAllButOneBoard.dropToken('X', 1);
        tieAllButOneBoard.dropToken('O', 1);
        tieAllButOneBoard.dropToken('X', 2);
        tieAllButOneBoard.dropToken('O', 2);
        tieAllButOneBoard.dropToken('X', 2);
        tieAllButOneBoard.dropToken('O', 2);
        tieAllButOneBoard.dropToken('X', 3);
        tieAllButOneBoard.dropToken('O', 3);
        tieAllButOneBoard.dropToken('X', 3);
        tieAllButOneBoard.dropToken('O', 1);
        // asserting tie is false
        assertTrue(tieAllButOneBoard.checkTie() == false);
    }

    // case for testing whatsAtPos when board is empty
    @Test
    public void testWhatsAtPos_Empty_Gameboard()
    {
        // creating gameBoardMem and keeping it empty
        GameBoardMem whatsAtPosEmptyBoard = gameBoardFactoryMem(4, 4, 3);
        BoardPosition newPos = BoardPositionFactory(0, 0);
        // asserting whatsAtPos is empty space
        assertEquals(whatsAtPosEmptyBoard.whatsAtPos(newPos), ' ');
    }

    // case for testing whatsAtPos when board is full
    @Test
    public void testWhatsAtPos_Full_GameBoard()
    {
        // creating gameBoardMem and dropping tokens
        GameBoardMem whatsAtPosFullBoard = gameBoardFactoryMem(4, 4, 3);
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

        BoardPosition newPos = BoardPositionFactory(2, 2);
        // asserting whatsAtPos is X for the given boardPosition
        assertEquals(whatsAtPosFullBoard.whatsAtPos(newPos), 'X');

    }

    // case for testing whatsAtPos in position with no player and board is semi full
    @Test
    public void testWhatsAtPos_SemiFuLL_GameBoard_No_Player()
    {
        // creating gameBoardMem and dropping tokens
        GameBoardMem whatsAtPosSemiFullBoardNoPlayer = gameBoardFactoryMem(4, 4, 3);
        whatsAtPosSemiFullBoardNoPlayer.dropToken('X', 0);
        whatsAtPosSemiFullBoardNoPlayer.dropToken('O', 0);
        whatsAtPosSemiFullBoardNoPlayer.dropToken('X', 2);
        whatsAtPosSemiFullBoardNoPlayer.dropToken('O', 2);
        whatsAtPosSemiFullBoardNoPlayer.dropToken('X', 0);
        whatsAtPosSemiFullBoardNoPlayer.dropToken('O', 3);
        whatsAtPosSemiFullBoardNoPlayer.dropToken('X', 3);
        whatsAtPosSemiFullBoardNoPlayer.dropToken('O', 1);

        BoardPosition newPos = BoardPositionFactory(3, 0);
        // asserting whatsAtPos(newPos) is empty space
        assertEquals(whatsAtPosSemiFullBoardNoPlayer.whatsAtPos(newPos), ' ');
    }

    // case for testing whatsAtPos in position filled on semi full board
    @Test
    public void testWhatsAtPos_SemiFuLL_GameBoard_Occupied()
    {
        // creating gameBoardMem and dropping tokens
        GameBoardMem whatsAtPosSemiFullBoardOccupied = gameBoardFactoryMem(4, 4, 3);
        whatsAtPosSemiFullBoardOccupied.dropToken('X', 0);
        whatsAtPosSemiFullBoardOccupied.dropToken('O', 0);
        whatsAtPosSemiFullBoardOccupied.dropToken('X', 2);
        whatsAtPosSemiFullBoardOccupied.dropToken('O', 1);
        whatsAtPosSemiFullBoardOccupied.dropToken('X', 2);
        whatsAtPosSemiFullBoardOccupied.dropToken('O', 3);
        whatsAtPosSemiFullBoardOccupied.dropToken('X', 3);
        whatsAtPosSemiFullBoardOccupied.dropToken('O', 3);

        BoardPosition newPos = BoardPositionFactory(1, 2);
        // asserting whatsAtPos(newPos) is X
        assertEquals(whatsAtPosSemiFullBoardOccupied.whatsAtPos(newPos), 'X');
    }

    // case for testing whatsAtPos of an empty position surrounded by filled positions
    @Test
    public void testWhatsAtPos_SemiFuLL_GameBoard_Surrounded()
    {
        // creating gameBoardMem and dropping tokens
        GameBoardMem whatsAtPosSemiFullBoardSurrounded = gameBoardFactoryMem(4, 4, 3);
        whatsAtPosSemiFullBoardSurrounded.dropToken('X', 0);
        whatsAtPosSemiFullBoardSurrounded.dropToken('O', 0);
        whatsAtPosSemiFullBoardSurrounded.dropToken('X', 0);
        whatsAtPosSemiFullBoardSurrounded.dropToken('O', 0);
        whatsAtPosSemiFullBoardSurrounded.dropToken('X', 2);
        whatsAtPosSemiFullBoardSurrounded.dropToken('O', 1);
        whatsAtPosSemiFullBoardSurrounded.dropToken('X', 2);
        whatsAtPosSemiFullBoardSurrounded.dropToken('O', 2);
        whatsAtPosSemiFullBoardSurrounded.dropToken('O', 2);
        whatsAtPosSemiFullBoardSurrounded.dropToken('O', 1);
        whatsAtPosSemiFullBoardSurrounded.dropToken('X', 1);
        whatsAtPosSemiFullBoardSurrounded.dropToken('O', 3);
        whatsAtPosSemiFullBoardSurrounded.dropToken('X', 3);
        whatsAtPosSemiFullBoardSurrounded.dropToken('O', 3);
        whatsAtPosSemiFullBoardSurrounded.dropToken('X', 3);

        BoardPosition newPos = BoardPositionFactory(3, 1);
        // asserting whatsAtPos(newPos) is empty
        assertEquals(whatsAtPosSemiFullBoardSurrounded.whatsAtPos(newPos), ' ');
    }   

    // case for testing isPlayerAtPos when certain player is at a position
    @Test
    public void isPlayerAtPos_IsAt_Test()
    {
        // creating gameBoardMem and dropping token
        GameBoardMem posIsAtBoard = gameBoardFactoryMem(4, 4, 3);
        posIsAtBoard.dropToken('X', 2);

        BoardPosition newPos = BoardPositionFactory(0, 2);
        // asserting the player X is at newPos position
        assertTrue(posIsAtBoard.isPlayerAtPos(newPos, 'X') == true);
    }

    // case for testing isPlayerAtPos when player is not at position
    @Test
    public void isPlayerAtPos_IsNotAt_Test()
    {
        // creating gameBoardMem and dropping token
        GameBoardMem posIsNotAtBoard = gameBoardFactoryMem(4, 4, 3);
        posIsNotAtBoard.dropToken('O', 1);

        BoardPosition newPos = BoardPositionFactory(0, 1);
        // asserting player X is not at newPos position
        assertTrue(posIsNotAtBoard.isPlayerAtPos(newPos, 'X') == false);
    }

    // case for testing isPlayerAtPos when there is no player at position
    @Test
    public void isPlayerAtPos_NoPlayer_Test()
    {
        // creating gameBoardMem and dropping tokens
        GameBoardMem posNoPlayerBoard = gameBoardFactoryMem(4, 4, 3);
        posNoPlayerBoard.dropToken('O', 1);
        posNoPlayerBoard.dropToken('X', 3);
        posNoPlayerBoard.dropToken('O', 0);

        BoardPosition newPos = BoardPositionFactory(0, 2);
        // asserting player X is not at newPos position
        assertTrue(posNoPlayerBoard.isPlayerAtPos(newPos, 'X') == false);
    }

    // case for testing isPlayerAtPos when board is empty
    @Test
    public void isPlayerAtPos_Empty_Test()
    {
        // creating gameBoardMem and keeping it empty
        GameBoardMem posEmptyBoard = gameBoardFactoryMem(4, 4, 3);

        BoardPosition newPos = BoardPositionFactory(0, 1);
        // asserting player X is not at newPos
        assertTrue(posEmptyBoard.isPlayerAtPos(newPos, 'X') == false);
    }

    // case for testing isPlayerAtPos when board is full
    @Test
    public void isPlayerAtPos_Full_Test()
    {
        // creating gameBoardMem and dropping tokens
        GameBoardMem posFullBoard = gameBoardFactoryMem(4, 4, 3);
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

        BoardPosition newPos = BoardPositionFactory(3, 1);
        // asserting that X is not at the newPos position
        assertTrue(posFullBoard.isPlayerAtPos(newPos, 'X') == false);
    }

    // case for testing dropToken in bottom left of board
    @Test
    public void dropToken_Left_Bottom_Test()
    {
        // creating gameBoardMem
        GameBoardMem dropTokenEmptyCol = gameBoardFactoryMem(4, 4, 3);
        // dropping token in bottom left
        dropTokenEmptyCol.dropToken('X', 0);

        BoardPosition newPos = BoardPositionFactory(0, 0);
        // asserting bottom left position is X
        assertEquals(dropTokenEmptyCol.whatsAtPos(newPos), 'X');
    }

    // case for testing dropToken in top left of board
    @Test
    public void dropToken_Left_Top_Test()
    {
        // creating game board and dropping tokens
        GameBoardMem dropTokenFillingCol = gameBoardFactoryMem(4, 4, 3);

        dropTokenFillingCol.dropToken('X', 0);
        dropTokenFillingCol.dropToken('O', 0);
        dropTokenFillingCol.dropToken('X', 0);
        // dropping token in top left
        dropTokenFillingCol.dropToken('O', 0);

        BoardPosition newPos = BoardPositionFactory(3, 0);
        // asserting top left position is O
        assertEquals(dropTokenFillingCol.whatsAtPos(newPos), 'O');
    }

    // case for testing dropToken in the middle of the board
    @Test
    public void dropToken_Middle_of_Board_Test()
    {
        // creating gameBoardMem and dropping tokens
        GameBoardMem dropTokenOnSameTokenType = gameBoardFactoryMem(4, 4, 3);

        dropTokenOnSameTokenType.dropToken('X', 0);
        dropTokenOnSameTokenType.dropToken('O', 1);
        dropTokenOnSameTokenType.dropToken('X', 1);
        dropTokenOnSameTokenType.dropToken('O', 2);
        dropTokenOnSameTokenType.dropToken('X', 0);

        BoardPosition newPos = BoardPositionFactory(1, 1);
        // asserting newPos is X
        assertEquals(dropTokenOnSameTokenType.whatsAtPos(newPos), 'X');
    }

    // case for testing drop Token in bottom Right
    @Test
    public void dropToken_Right_Bottom_Test()
    {
        // creating gameBoardMem
        GameBoardMem dropTokenEmptyCol = gameBoardFactoryMem(4, 4, 3);
        // dropping token in bottom right
        dropTokenEmptyCol.dropToken('X', 3);

        BoardPosition newPos = BoardPositionFactory(0, 3);
        // asserting bottom right position is X
        assertEquals(dropTokenEmptyCol.whatsAtPos(newPos), 'X');
    }

    // case for testing dropToken in top right
    @Test
    public void dropToken_Right_Top_Test()
    {
        // creating gameBoardMem and dropping tokens
        GameBoardMem dropTokenFillingCol = gameBoardFactoryMem(4, 4, 3);

        dropTokenFillingCol.dropToken('X', 3);
        dropTokenFillingCol.dropToken('O', 3);
        dropTokenFillingCol.dropToken('X', 3);
        dropTokenFillingCol.dropToken('O', 3);

        BoardPosition newPos = BoardPositionFactory(3, 3);
        // asserting top right position is O
        assertEquals(dropTokenFillingCol.whatsAtPos(newPos), 'O');
    }
}