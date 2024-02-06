#makefile
# Default target for compiling the main program
default:    cpsc2150/extendedConnects/GameScreen.java cpsc2150/extendedConnectX/models/AbsGameBoard.java cpsc2150/extendedConnectX/models/BoardPosition.java cpsc2150/extendedConnectX/models/GameBoard.java cpsc2150/extendedConnectX/models/IGameBoard.java
    javac cpsc2150/extendedConnects/GameScreen.java cpsc2150/extendedConnectX/models/AbsGameBoard.java cpsc2150/extendedConnectX/models/BoardPosition.java cpsc2150/extendedConnectX/models/GameBoard.java cpsc2150/extendedConnectX/models/IGameBoard.java

# Target to run the main program
run:    cpsc2150/extendedConnects/GameScreen.class cpsc2150/extendedConnectX/models/AbsGameBoard.class cpsc2150/extendedConnectX/models/BoardPosition.class cpsc2150/extendedConnectX/models/GameBoard.class cpsc2150/extendedConnectX/models/IGameBoard.class
    java cpsc2150/extendedConnects/GameScreen

# Target to run all tests
test:   cpsc2150/extendedConnects/GameScreen.java cpsc2150/extendedConnectX/models/AbsGameBoard.java cpsc2150/extendedConnectX/models/BoardPosition.java cpsc2150/extendedConnectX/models/GameBoard.java cpsc2150/extendedConnectX/models/IGameBoard.java
    javac -cp .:/usr/share/java/junit4.jar cpsc2150/extendedConnects/GameScreen.java cpsc2150/extendedConnectX/models/AbsGameBoard.java cpsc2150/extendedConnectX/models/BoardPosition.java cpsc2150/extendedConnectX/models/GameBoard.java cpsc2150/extendedConnectX/models/IGameBoard.java

# Target to compile and run tests for GameBoard
testGB: cpsc2150/extendedConnects/GameScreen.class cpsc2150/extendedConnectX/models/AbsGameBoard.class cpsc2150/extendedConnectX/models/BoardPosition.class cpsc2150/extendedConnectX/models/GameBoard.class cpsc2150/extendedConnectX/models/IGameBoard.class
    java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.extendedConnectX.tests.TestGameBoard

# Target to compile and run tests for GameBoardMem
testGBMem: cpsc2150/extendedConnects/GameScreen.class cpsc2150/extendedConnectX/models/AbsGameBoard.class cpsc2150/extendedConnectX/models/BoardPosition.class cpsc2150/extendedConnectX/models/GameBoard.class cpsc2150/extendedConnectX/models/IGameBoard.class
    java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.extendedConnectX.tests.TestGameBoardMem

# Clean-up command
clean:
    rm -f cpsc2150/extendedConnects/*.class
    rm -f cpsc2150/extendedConnectX/models/*.class
    rm -f cpsc2150/extendedConnectX/models/tests/*.class