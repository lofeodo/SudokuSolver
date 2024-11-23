import java.util.List;

/**
 * SudokuSolver is an implementation of GameSolver
 *
 * @author Daniel Lofeodo, Arielle Gazzé
 * @version 1.0
 * @since 1.0
 */

public class SudokuSolver implements GameSolver {
    private IntegerBoard board;
    private IntegerBoard solution;
    private SudokuTree tree;

    public SudokuSolver(GameBoard<Integer> board) {
        if (!(board instanceof IntegerBoard)) {
            throw new IllegalArgumentException("Invalid board type. The board must be of type IntegerBoard.");
        }

        this.board = (IntegerBoard) board;
        this.solution = null;
        this.tree = new SudokuTree(this.board);
    }

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

    // Validate a cell value in the board
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

    // Recursive solver
    private boolean solveBoard(BoardPosition p) {
        IntegerBoard newBoard = p.getElement();
        int[] indices = newBoard.getFirstZeroIndices();

        // If no zero (empty) cells are found, that means the board is full and
        // therefore solved.
        if (indices == null) {
            solution = newBoard;
            return true;
        }

        var row = indices[0];
        var col = indices[1];
        List<BoardPosition> children = tree.generateChildren(p, row, col);

        for (BoardPosition child : children) {
            if (isValidPlacement(row, col, child.getElement())) {
                if (solveBoard(child)) {
                    return true;
                }
            }
        }

        // All possible child boards have been tried - there is no solution for this
        // board
        return false;
    }
}
