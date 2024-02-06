package cpsc2150.extendedConnectX.models;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
  Abigail Clanton (alclant)
  Cassandra Phillips (Cassie488)
  Ricardo Varona (ricky643)
  Kaki Newsome (kakmonk)
 */

public abstract class AbsGameBoard implements IGameBoard  {
    public static final int DOUBLE_DIGITS = 10;

    /**
     * Creates a string in the format of gameBoard by overriding
     * @param - none
     *
     * @return - A string in the format of gameBoard
     *
     * @pre - None
     *
     * @post
     * (self = #self)
     * toString() = [A string generated in the format of gameBoard, which is a string
     * representation of the gameboard.
     */
    @Override public String toString() {
        String toString = "|";
        int num = 0, row = getNumRows();

        // implement toString as if it were a default function
        while(num < getNumColumns()) {
            if(num < DOUBLE_DIGITS){
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
            for(int col = 0; col < getNumColumns(); col++){
                BoardPosition currentPos = new BoardPosition(row, col);
                toString += ("|" + whatsAtPos(currentPos) + " ");}
            toString += "|\n";
        }
        return toString;
    }
}
