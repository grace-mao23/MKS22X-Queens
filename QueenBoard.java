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
      if (addQueen(i,col) && solveH(col+1)) { // if queen addable and this position plays out
        return true;
      }
      // if addQueen was false, no queen is here and removeQueen returns false, not doing anything to the barod
      // if solveH was false (meaning addQueen true), queen removed
      removeQueen(i,col);
    }
    return false; // no positions in the column yielded a solution
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
      return 1; // you've reached end, this is a solution
    }
    int result = 0;
    for (int i = 0; i < board.length; i++) {
      if (addQueen(i,col)) { // if you can add a queen...
        result += countH(col+1); // check if this place yields solutions
      }
      removeQueen(i,col); // reset for next loop
    }
    return result;
  }

  public static void main(String[] args) {
    QueenBoard none = new QueenBoard(0);
    QueenBoard one = new QueenBoard(1);
    QueenBoard two = new QueenBoard(2);
    QueenBoard three = new QueenBoard(3);
    QueenBoard four = new QueenBoard(4);
    QueenBoard five = new QueenBoard(5);
    QueenBoard six = new QueenBoard(6);
    QueenBoard seven = new QueenBoard(7);
    QueenBoard eight = new QueenBoard(8);
    QueenBoard nine = new QueenBoard(9);
    QueenBoard ten = new QueenBoard(10);
    QueenBoard[] q = new QueenBoard[] {
      none, one, two, three, four, five, six, seven, eight, nine, ten
    };
    for (QueenBoard e : q) {
      System.out.println("Board: \n" + e.toString());
      System.out.println("Solveable? " + e.solve());
      e.reset();
      System.out.println("Solutions: " + e.countSolutions());
      System.out.println("--------------------------");
    }
  }



}
