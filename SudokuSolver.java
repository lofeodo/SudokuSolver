import java.util.List;

/**
 * SudokuSolver is an implementation of GameSolver
 *
 * @author Daniel Lofeodo, Arielle Gazzé
 * @version 1.0
 * @since 1.0
 */

 public class SudokuSolver implements GameSolver {

    // attributes
    private IntegerBoard board;
    private IntegerBoard solution;
    private SudokuTree tree;

    // constructor
    public SudokuSolver( IntegerBoard board ) {
        this.board = new IntegerBoard(board.getBoardCopy());
        this.solution = null;
        this.tree = new SudokuTree(board);
    }

    // mandatory GameSolver interface methods
    public boolean solve() {
        return solveBoard(tree.root());
    }
    public void printSolution() {
        if (solution != null) {
            solution.display();
        } else {
            System.out.println("No solution found.");
        }
    }


    // validate an insertion in the board
    public boolean isValidPlacement( int row, int col, Integer value, IntegerBoard board ) {
        // Check rows
        for (int i = 0; i < board.getHeight(); i++) {
            if (board.getCell(col, row) == value) {
                return false;
            }
        }

        // Check columns
        for (int i = 0; i < board.getWidth(); i++) {
            if (board.getCell(col, row) == value) {
                return false;
            }
        }

        // Check 3x3 subsquare
        int rowStart = row - row % 3;
        int colStart = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                if (board.getCell(i + rowStart, j + colStart) == value) {
                    return false;
                }
        }
        return true;
    }

    // actual solver
    private boolean solveBoard(BoardPosition p) {
        IntegerBoard newBoard = p.getElement();
        int[] indices = newBoard.getFirstZeroIndices();
        if (indices == null) {
            solution = newBoard;
            return true;
        }
        List<BoardPosition> allChildren = tree.generateChildren(p, indices[0], indices[1]);
        int i = 0;
        for( BoardPosition child: allChildren ) {
            i++;
            if ( isValidPlacement(indices[0], indices[1], i, p.getElement()) ) {
                solveBoard(child);
            } else {
                // garbage collector ?
            }
        }
        return false;
    }
}
