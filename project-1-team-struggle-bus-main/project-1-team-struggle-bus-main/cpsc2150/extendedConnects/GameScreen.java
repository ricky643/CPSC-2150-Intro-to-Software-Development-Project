package cpsc2150.extendedConnects;
import cpsc2150.extendedConnectX.models.GameBoard;
import cpsc2150.extendedConnectX.models.GameBoardMem;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Vector;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
  Abigail Clanton (alclant)
  Cassandra Phillips (Cassie488)
  Ricardo Varona (ricky643)
  Kaki Newsome (kakmonk)
 */

/**
 * GameScreen enacts all the functions and classes necessary in specific cases of execution, allows for acutal play time
 * and presents the user interface
 *
 * @invariant - none
 *
 * @corresponds - none
 */
public class GameScreen {
    public static void main(String[] args) {
        //variables used throughout code
        char current, charUsedToRep, upperUsedToRep;
        boolean tokenAlreadyUsed = false, resettingToken= false, win, tie;
        int colEntered, tkn = 0, checkingTkn = 0,  playing = 0, playAgain = 0;
        String numPlayersStrng, numRowsStrng, numColsStrng, numToWinStrng, colEnteredString, stringUsedToRep, gameType;
        
        //loops as long as the player would like to play agaon
        while (playAgain == 0) {
            //re-set variables
            int numPlayers = 0, numRows = 0, numCols = 0, numToWin = 0, playerNumber = 0;
            //recreate the gameBoard temporary use to in order to scan in players, columns,
            //rows, and number of tokens in a row in order to win
            GameBoard gameToGet = new GameBoard(numRows, numCols, numToWin);

            //collects the number of characters looping until a valid entry of 2-10
            System.out.println("How many players?");
            Scanner players = new Scanner(System.in);
            numPlayersStrng = players.nextLine();
            numPlayers = Integer.parseInt(numPlayersStrng);

            while (numPlayers < gameToGet.getMinPlayers() || numPlayers > gameToGet.getMaxPlayers()) {
                if(numPlayers < gameToGet.getMinPlayers())
                    System.out.println("There must be at least 2 players.");
                else if(numPlayers > gameToGet.getMaxPlayers())
                    System.out.println("There must be less than 25 players.");
                System.out.println("How many players?");
                Scanner playersNew = new Scanner(System.in);
                numPlayersStrng = playersNew.nextLine();
                numPlayers = Integer.parseInt(numPlayersStrng);
            }

            //create an array of tokens that will be used to loop through the tokens and 
            //determine whose turn it is
            char tokensUsed[] = new char[numPlayers];
            checkingTkn = numPlayers;
            checkingTkn--;

            //loop through the amount of players asking each player to enter a token that will
            //represent them
            for (int each = 0; each < numPlayers; each++) {
                System.out.println("Enter the character to represent player " + ++playerNumber);
                Scanner scanToken = new Scanner(System.in);
                stringUsedToRep = scanToken.nextLine();
                charUsedToRep = stringUsedToRep.charAt(0);
                upperUsedToRep = Character.toUpperCase(charUsedToRep);

                //used to check if the token entered is already used by another player
                for (char testingTokens : tokensUsed) {
                    if (testingTokens == upperUsedToRep)
                        tokenAlreadyUsed = true;}

                //if the token is already used the player will be asked to repeatedly chose a new
                //token until one that is not already chosen by another player is entered.
                while (tokenAlreadyUsed == true) {
                    System.out.println(upperUsedToRep + " is already taken as a player token!");
                    tokenAlreadyUsed = false;
                    System.out.println("Enter the character to represent player " + playerNumber);
                    Scanner tokenNew = new Scanner(System.in);
                    stringUsedToRep = tokenNew.nextLine();
                    charUsedToRep = stringUsedToRep.charAt(0);
                    upperUsedToRep = Character.toUpperCase(charUsedToRep);
                    //used to check if the token entered is already used by another player
                    for (char testingTokens : tokensUsed) {
                        if (testingTokens == upperUsedToRep)
                            tokenAlreadyUsed = true;}
                }
                //insert token chosen into the array of tokens
                tokensUsed[each] = upperUsedToRep;
            }

            //collects the number of rows looping until a valid entry of 3-100
            System.out.println("How many rows in the game board?");
            Scanner scanRow = new Scanner(System.in);
            numRowsStrng = scanRow.nextLine();
            numRows = Integer.parseInt(numRowsStrng);

            while (numRows < gameToGet.getMinRow() || numRows > gameToGet.getMaxRow()) {
                if(numRows < gameToGet.getMinRow())
                    System.out.println("There must be at least 3 rows.");
                else if(numRows > gameToGet.getMaxRow())
                    System.out.println("There must be less than 100 rows.");
                System.out.println("How many rows in the game board?");
                Scanner rowNew = new Scanner(System.in);
                numRowsStrng = rowNew.nextLine();
                numRows = Integer.parseInt(numRowsStrng);
            }

            //collects the number of columns looping until a valid entry of 3-100
            System.out.println("How many columns in the game board?");
            Scanner scanCol = new Scanner(System.in);
            numColsStrng = scanCol.nextLine();
            numCols = Integer.parseInt(numColsStrng);

            while (numCols < gameToGet.getMinCol() || numCols > gameToGet.getMaxCol()) {
                if(numCols < gameToGet.getMinCol())
                    System.out.println("There must be at least 3 columns.");
                else if(numCols > gameToGet.getMaxCol())
                    System.out.println("There must be less than 100 columns.");
                System.out.println("How many columns in the game board?");
                Scanner colNew = new Scanner(System.in);
                numColsStrng = colNew.nextLine();
                numCols = Integer.parseInt(numColsStrng);
            }

            //collects the number of tokens in a row looping until a valid entry of 3-25
            System.out.println("How many pieces in a row to win?");
            Scanner scanNTW = new Scanner(System.in);
            numToWinStrng = scanNTW.nextLine();
            numToWin = Integer.parseInt(numToWinStrng);

            while (numToWin < gameToGet.getMinNTW() || numToWin > gameToGet.getMaxNTW() || numToWin > numRows || numToWin > numCols) {
                if(numToWin < gameToGet.getMinNTW())
                    System.out.println("There must be at least 3 in a row to win.");
                else if(numToWin > gameToGet.getMaxNTW())
                    System.out.println("There must be less than 25 in a row to win.");
                else if(numToWin > numRows)
                    System.out.println("The number in a row to win must be less than the number of rows.");
                else if(numToWin > numCols)
                    System.out.println("The number in a row to win must be less than the number of columns.");
                System.out.println("How many pieces in a row to win?");
                Scanner NTWNew = new Scanner(System.in);
                numToWinStrng = NTWNew.nextLine();
                numToWin = Integer.parseInt(numToWinStrng);
            }

            //asks the player to chose between a memory efficient game or fast game and loops until
            //a player chooses the correct input of f/F for fast or m/M for memory efficient.
            System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
            Scanner scanType = new Scanner(System.in);
            gameType = scanType.nextLine();
            while (!(gameType.equals("f") || gameType.equals("F") || gameType.equals("m") || gameType.equals("M"))) {
                System.out.println("Please enter F or M");
                System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
                Scanner typeNew = new Scanner(System.in);
                gameType = typeNew.nextLine();
            }

            //case if the player has chosen a fast game
            if(gameType.equals("f") || gameType.equals("F")){
                //prints game board for play
                //create a gameboard with the parameters entered by the player.
                GameBoard game = new GameBoard(numRows, numCols, numToWin);
                String fastBoard = game.toString();
                System.out.print(fastBoard.toString());
                playing = 0;

                //while the game is not over
                while (playing == 0) {
                    //takes in the users choice of column
                    System.out.println("Player " + tokensUsed[tkn] + ", what column do you want to place your marker in?");
                    Scanner scanEntryFast = new Scanner(System.in);
                    numColsStrng = scanEntryFast.nextLine();
                    colEntered = Integer.parseInt(numColsStrng);
                    
                    //checking for entry no larger than num of columns
                    while (colEntered >= game.getNumColumns() || colEntered < 0 || game.checkIfFree(colEntered) == false) {
                        if (colEntered >= game.getNumColumns())
                            System.out.println("Column cannot be greater than " + game.getNumColumns());
                        else if (colEntered < 0)
                            System.out.println("Column cannot be less than 0");
                        else if (game.checkIfFree(colEntered) == false)
                            System.out.println("Column is full");
                        System.out.println("Player " + tokensUsed[tkn] + ", what column do you want to place your marker in?");
                        Scanner entryNewFast = new Scanner(System.in);
                        numColsStrng = entryNewFast.nextLine();
                        colEntered = Integer.parseInt(numColsStrng);
                    }

                    //adds the token to the gameboard and prints out updated game
                    game.dropToken(tokensUsed[tkn], colEntered);
                    String playingBoardFast = game.toString();
                    System.out.print(playingBoardFast.toString());

                    //checks to see if the player won with the latest piece
                    win = game.checkForWin(colEntered);
                    if (win == true) {
                        System.out.println("Player " + tokensUsed[tkn] + " Won!\n");

                        //while loop incase missinput when asking if they want to play again(neither y, Y, n, or N)
                        int chk = 0;
                        while (chk == 0) {
                            System.out.println("Would you like to play again? Y/N\n");
                            Scanner scanPlayWinFast = new Scanner(System.in);
                            char play = scanPlayWinFast.next().charAt(0);

                            //if the player wished to play reset items and make it so the loop
                            //for playing will run again
                            if (play == 'Y' || play == 'y') {
                                win = false;
                                tkn = 0;
                                playing++;
                                resettingToken = true;
                                chk++;}
                            else if (play == 'N' || play == 'n') {
                                chk++;
                                playing++;
                                playAgain++;}
                        }
                    }

                    //checks for tie with each piece placed
                    tie = game.checkTie();
                    if (tie == true) {
                        System.out.println("Tie!\n");

                        //same as win while loop
                        int chk = 0;
                        while (chk == 0) {
                            System.out.println("Would you like to play again? Y/N\n");
                            Scanner scanPlayTieFast = new Scanner(System.in);
                            char play = scanPlayTieFast.next().charAt(0);

                            //if the player wished to play reset items and make it so the loop
                            //for playing will run again
                            if (play == 'Y' || play == 'y') {
                                tie = false;
                                resettingToken = true;
                                tkn = 0;
                                playing++;
                                chk++;
                            } else if (play == 'N' || play == 'n') {
                                chk++;
                                playing++;
                                playAgain++;
                            }
                        }
                    }
                    //resets the token array index if the end of it has been reached.
                    if(resettingToken == false){
                        if (tkn == checkingTkn)
                            tkn = 0;
                        else
                            tkn++;
                    }
                }
                //resets to false so the token array will itterate properly next game.
                resettingToken = false;
            } else if (gameType.equals("m") || gameType.equals("M")){
                //prints game board with memory saving for play
                GameBoardMem game = new GameBoardMem(numRows, numCols, numToWin);
                String memoryBoard = game.toString();
                System.out.print(memoryBoard.toString());
                playing = 0;

                //while the user wishes to play a game.
                while (playing == 0) {
                    //takes in the users choice of column
                    System.out.println("Player " + tokensUsed[tkn] + ", what column do you want to place your marker in?");
                    Scanner scanEntryMemory = new Scanner(System.in);
                    colEnteredString = scanEntryMemory.nextLine();
                    colEntered = Integer.parseInt(colEnteredString);

                    //checking for entry no larger than num of columns
                    while (colEntered >= game.getNumColumns() || colEntered < 0 || game.checkIfFree(colEntered) == false) {
                        if (colEntered >= game.getNumColumns())
                            System.out.println("Column cannot be greater than " + game.getNumColumns());
                        else if (colEntered < 0)
                            System.out.println("Column cannot be less than 0");
                        else if (game.checkIfFree(colEntered) == false)
                            System.out.println("Column is full");
                        System.out.println("Player " + tokensUsed[tkn] + ", what column do you want to place your marker in?");
                        Scanner entryNewMemory = new Scanner(System.in);
                        colEnteredString = entryNewMemory.nextLine();
                        colEntered = Integer.parseInt(colEnteredString);
                    }

                    //adds the token to the gameboard and prints out updated game
                    game.dropToken(tokensUsed[tkn], colEntered);
                    String playingBoardMemory = game.toString();
                    System.out.print(playingBoardMemory.toString());

                    //checks to see if the player won with the latest piece
                    win = game.checkForWin(colEntered);
                    if (win == true) {
                        System.out.println("Player " + tokensUsed[tkn] + " Won!\n");

                        //while loop incase missinput when asking if they want to play again(neither y, Y, n, or N)
                        int chk = 0;
                        while (chk == 0) {
                            System.out.println("Would you like to play again? Y/N\n");
                            Scanner scanPlayWinMemory = new Scanner(System.in);
                            char play = scanPlayWinMemory.next().charAt(0);

                            //if the player wished to play reset items and make it so the loop
                            //for playing will run again
                            if (play == 'Y' || play == 'y') {
                                win = false;
                                tkn = 0;
                                playing++;
                                resettingToken = true;
                                chk++;
                            } else if (play == 'N' || play == 'n') {
                                chk++;
                                playing++;
                                playAgain++;
                            }
                        }
                    }

                    //checks for tie with each piece placed
                    tie = game.checkTie();
                    if (tie == true) {
                        System.out.println("Tie!\n");

                        //same as win while loop
                        int chk = 0;
                        while (chk == 0) {
                            System.out.println("Would you like to play again? Y/N\n");
                            Scanner scanPlayTieMemory = new Scanner(System.in);
                            char play = scanPlayTieMemory.next().charAt(0);

                            //if the player wished to play reset items and make it so the loop
                            //for playing will run again
                            if (play == 'Y' || play == 'y') {
                                tie = false;
                                resettingToken = true;
                                tkn = 0;
                                playing++;
                                chk++;
                            } else if (play == 'N' || play == 'n') {
                                chk++;
                                playing++;
                                playAgain++;
                            }
                        }
                    }
                    //resets the token array index if the end of it has been reached.
                    if(resettingToken == false){
                        if (tkn == checkingTkn)
                            tkn = 0;
                        else
                            tkn++;
                    }
                }
                //resets to false so the token array will itterate properly next game.
                resettingToken = false;
            }
        }
    }
}