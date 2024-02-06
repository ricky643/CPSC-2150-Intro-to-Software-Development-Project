package cpsc2150.extendedConnectX.models;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
  Abigail Clanton (alclant)
  Cassandra Phillips (Cassie488)
  Ricardo Varona (ricky643)
  Kaki Newsome (kakmonk)
 */

/**
 * Class BoardPosition that holds functions that relate to the position
 * of a cell on the board
 * 
 * @invariant 0 <= Row < getNumRows() AND 0 <= Column < getNumColumns()
 */
public class BoardPosition
{
    private int Row, Column;

    /**
     * inits. BoardPosition object based on params
     * @param aRow - number to be set to BoardPosition's Row
     * @param aColumn - number to be set to BoardPosition's Column
     *
     * @pre 
     * (aRow < getNumRows() && aRow >= 0) AND (aColumn >= 0 && aColumn < getNumColumns())
     *
     * @post 
     * (Row = aRow) AND (Column = aColumn)
     */
    public BoardPosition(int aRow, int aColumn)
    {
        Row = aRow;
        Column = aColumn;
    }

    /**
     * returns value in Row variable
     * @param - None
     *
     * @return - the row number as an int
     *
     * @pre
     * None
     *
     * @post
     * getRow() = Row AND Row = #Row AND Column = #Column
     */
    public int getRow() {return Row;}

    /**
     * Returns value in Column variable
     * @param - None
     *
     * @return - the column number as an int
     *
     * @pre
     * None
     *
     * @post
     * getColumn() = Column AND Row = #Row AND Column = #Column
     */
    public int getColumn() {return Column;}

    /**
     * Returns true if Row and Column of object passed in are equal to current
     * object, false otherwise
     * @param obj - object to compare against current object
     *
     * @return - True, if object taken in is equal to current object based on
     * implementation, false otherwise
     * 
     * @pre - None
     *
     * @post
     * equals(Object Obj) = [Return is true if the current object's Row and Column equals passed in
     * object's Row and Column] AND Row = #Row AND Column = #Column
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof BoardPosition) {
            BoardPosition myObj = (BoardPosition)obj;
            if (this.Row == myObj.getRow() && this.Column == myObj.getColumn())
                return true;
        }
        return false;
    }

    /**
     * returns values of Row and Column as a string
     * @param - None
     *
     * @return - String that is values of Row and Column
     *
     * @pre - None
     *
     * @post 
     * [return is "<Row>,<Column>" where <Row> equals Row and <Column> equals
     * Column] AND Row = #Row AND Column = #Column
     */
    @Override
    public String toString()
    {
        return this.Row + "," +this.Column;
    }
}