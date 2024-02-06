#Remade Makefile; Could never compile correctly; missing seperator error
#Reasoning; Every TAB was equivalent to 5 spaces instead of just 1 regular indentation
#This breaks the makefile as spaces for tabbing will NEVER be read from
#This is also shown throughout entire repository when editing

JAVAFILES = cpsc2150/extendedConnects/GameScreen.java cpsc2150/extendedConnectX/models/*.java
TESTFILES = cpsc2150/extendedConnectX/tests/*.java
CLASSFILES = cpsc2150/extendedConnects/GameScreen.class cpsc2150/extendedConnectX/models/*.class cpsc2150/extendedConnectX/tests/*.class

default:
	javac -Xlint:unchecked cpsc2150/extendedConnects/GameScreen.java cpsc2150/extendedConnectX/models/AbsGameBoard.java cpsc2150/extendedConnectX/models/BoardPosition.java cpsc2150/extendedConnectX/models/GameBoard.java cpsc2150/extendedConnectX/models/IGameBoard.java

run:
	java cpsc2150/extendedConnects/GameScreen

test: $(JAVAFILES) $(TESTFILES)
	javac -Xlint:unchecked -cp .:/usr/share/java/junit4.jar $(JAVAFILES) $(TESTFILES)

testGB: $(CLASSFILES)
	java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.extendedConnectX.tests.TestGameBoard

testGBMem: $(CLASSFILES)
	java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.extendedConnectX.tests.TestGameBoardMem

clean:
	rm -f $(CLASSFILES)