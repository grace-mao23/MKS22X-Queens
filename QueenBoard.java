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
      for (int x = c; x < board.length; x++) {
        if ((i == r || Math.abs(r-i) == Math.abs(c-x) || x == c) && board[i][x] != -1) {
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
      for (int x = c; x < board.length; x++) {
        if ((i == r || Math.abs(r-i) == Math.abs(c-x) || x == c) && !(i == r && x == c)) {
          board[i][x] -= 1;
        }
      }
    }
    return true;
  }

  private void reset() {
    for (int i = 0; i < board.length; i++) {
      for (int x = 0; x < board.length; x++) {
        board[i][x] = 0;
      }
    }
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
    for (int i = 0; i < board.length; i++) {
      for (int x = 0; x < board.length; x++) {
        if (board[i][x] != 0) {
          throw new IllegalStateException("Board is not empty");
        }
      }
    }
    return solveH(0);
  }

  private boolean solveH(int col) {
    if (col == board.length) {
      return true; // puzzle solved with queen in last row
    }
    for (int i = 0; i < board.length; i++) {
    //  System.out.println("A"+col+ ", " + i + ": \n"+toString());
      if (addQueen(i,col) && solveH(col+1)) {
      //  System.out.println("B"+col+ ", " + i + ": \n"+toString());
        return true;
      }
    //  if (!addQueen(i,col)) System.out.println("No");
      removeQueen(i,col);
    //  System.out.println("C"+col+ ", " + i + ": \n"+toString());
    }
    //System.out.println("D"+col + ": \n"+toString());
    return false;
  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){
    for (int i = 0; i < board.length; i++) {
      for (int x = 0; x < board.length; x++) {
        if (board[i][x] != 0) {
          throw new IllegalStateException("Board is not empty");
        }
      }
    }
    return countH(0);
  }

  private int countH(int col) {
    if (col == board.length) {
      return 1;
    }
    int result = 0;
    for (int i = 0; i < board.length; i++) {
      if (addQueen(i,col)) {
        result += countH(col+1);
      }
      removeQueen(i,col); // reset for next loop
    }
    return result;
  }

  public static void main(String[] args) {
    QueenBoard q = new QueenBoard(8);
  //  System.out.println(q.toString());
    //System.out.println(q.solve());
  //  System.out.println(q.toString());
    System.out.println(q.countSolutions());
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
