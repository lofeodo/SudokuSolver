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
    public SudokuSolver(GameBoard<Integer> board) {
        if (!(board instanceof IntegerBoard)) {
            throw new IllegalArgumentException("Invalid board type. The board must be of type IntegerBoard.");
        }

        this.board = (IntegerBoard) board;
        this.solution = null;
        this.tree = new SudokuTree(this.board);
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
    public boolean isValidPlacement(int row, int col, IntegerBoard board) {
        // Check rows
        var value = board.getCell(row, col);
        for (int i = 0; i < board.getHeight(); i++) {
            if (i == col) {
                // Do not compare cell with itself.
                continue;
            } else if (board.getCell(row, i) == value) {
                return false;
            }
        }

        // Check columns
        for (int i = 0; i < board.getWidth(); i++) {
            if (i == row) {
                // Do not compare cell with itself.
                continue;
            }
            if (board.getCell(i, col) == value) {
                return false;
            }
        }

        // Check 3x3 subsquare
        int rowStart = row - row % 3;
        int colStart = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                if (i + rowStart == row && j + colStart == col) {
                    // Do not compare cell with itself.
                    continue;
                } else if (board.getCell(i + rowStart, j + colStart) == value) {
                    return false;
                }
        }
        return true;
    }

    // actual solver
    private boolean solveBoard(BoardPosition p) {
        IntegerBoard newBoard = p.getElement();
        int[] indices = newBoard.getFirstZeroIndices();

        // If no zero (empty) cells are found, that means the board is full and
        // therefore solved.
        if (indices == null) {
            solution = newBoard;
            return true;
        }

        var x = indices[0];
        var y = indices[1];
        List<BoardPosition> children = tree.generateChildren(p, indices[0], indices[1]);

        // // For debugging - remove after
        // newBoard.display();
        // System.out.println();

        for (BoardPosition child : children) {
            if (isValidPlacement(x, y, child.getElement())) {
                if (solveBoard(child)) {
                    return true;
                }
            }
        }

        // All possible boards have been tried - there is no solution
        return false;
    }
}
