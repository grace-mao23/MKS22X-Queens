public class QueenBoard {
  private int[][] board;

  public QueenBoard(int size){
    board = new int[size][size];
  }

  private boolean addQueen(int r, int c) {
    if (board[r][c] != 0) {
      return false;
    }
    board[r][c] = -1;
    for (int i = 0; i < board.length; i++) {
      for (int x = c + 1; x < board.length; x++) {
        if (i == r || Math.abs(r-i) == Math.abs(c-x)) {
          board[i][x] += 1;
        }
      }
    }
    return true;
  }

  private boolean removeQueen(int r, int c) {
    if (board[r][c] != -1) {
      return false;
    }
    board[r][c] = 0;
    for (int i = 0; i < board.length; i++) {
      for (int x = c + 1; x < board.length; x++) {
        if (i == r || Math.abs(r-i) == Math.abs(c-x)) {
          board[i][x] -= 1;
        }
      }
    }
    return true;
  }


  /**
  *@return The output string formatted as follows:
  *All numbers that represent queens are replaced with 'Q'
  *all others are displayed as underscores '_'
  *There are spaces between each symbol:
  *"""_ _ Q _
  *   Q _ _ _
  *   _ _ _ Q
  *   _ Q _ _"""
  *(pythonic string notation for clarity,
  *excludes the character up to the *)
  */
  public String toString(){
    String result = "";
    for (int i = 0; i < board.length; i++) {
      for (int x = 0; x < board.length; x++) {
        if (board[i][x] == -1) {
          result += "Q ";
        } else {
          result += "_ "; // board[i][x] + " " if want to see numbers
        }
      }
      result += "\n";
    }
    return result;
  }



  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;
  *        true when the board is solveable, and leaves the board in a solved state
  *@throws IllegalStateException when the board starts with any non-zero value

  */
  public boolean solve(){
    return solveH(0,0);
  }

  private boolean solveH(int col, int row) {
  //  System.out.println(row + ", " + col);
    if (col < 0) { // if we've backtracked all the way, it's unsolveable
      return false;
    }
    if (col == board.length) { // if we've gone all the way, it's solved
      return true;
    }
    if (row == board.length) { // if we've gone all the way down
      if (col == 0) { // if we're at the first column, it's unsolveable
        return false;
      }
      int r = 0;
      while (board[r][col - 1] != -1) {
        r++;
      }
      removeQueen(r, col-1); // locate and remove queen in column before
      return solveH(col - 1, r+1); // backtrack
    }
    if (board[row][col] != 0) { // if the position we're looking at is not 0
      return solveH(col,row+1); // move to next spot in column
    }
    // else...
    //System.out.println("Made it");
    addQueen(row, col); // add queen at this position
    return solveH(col + 1, 0); // move to top of next column
  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){
    return -1;
  }

  public static void main(String[] args) {
    QueenBoard q = new QueenBoard(8);
    System.out.println(q.toString());
    System.out.println(q.solve());
    System.out.println(q.toString());
    /*
    System.out.println(q.toString());
    q.addQueen(0,0);
    System.out.println(q.toString());
    q.addQueen(1,1);
    System.out.println(q.toString());
    q.addQueen(2,1);
    System.out.println(q.toString());
    q.removeQueen(0,0);
    System.out.println(q.toString()); */
  }



}
