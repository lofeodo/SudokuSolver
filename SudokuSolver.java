import java.util.List;

/**
 * SudokuSolver is an implementation of GameSolver
 *
 * @author Daniel Lofeodo, Arielle Gazzé
 * 
 * @version 1.1
 * Make the code work with any size sudoku
 * @since 1.0
 */

public class SudokuSolver implements GameSolver {
    private IntegerBoard board;
    private IntegerBoard solution;
    private SudokuTree tree;
    private final int subsquareSize;

    public SudokuSolver(GameBoard<Integer> board) {
        if (!(board instanceof IntegerBoard)) {
            throw new IllegalArgumentException("Invalid board type. The board must be of type IntegerBoard.");
        }

        this.board = (IntegerBoard) board;
        this.solution = null;
        this.tree = new SudokuTree(this.board);
        this.subsquareSize = this.board.getSubsquareSize();
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
        var value = board.getCell(row, col);

        // Check rows
        for (int i = 0; i < board.getHeight(); i++) {
            if (i != col && board.getCell(row, i) == value) {
                return false;
            }
        }

        // Check columns
        for (int i = 0; i < board.getWidth(); i++) {
            if (i != row && board.getCell(i, col) == value) {
                return false;
            }
        }

        // Check 3x3 subsquare
        int subsquareRowStart = row - row % subsquareSize;
        int subsquareColStart = col - col % subsquareSize;
        for (int i = subsquareRowStart; i < subsquareRowStart + subsquareSize; i++) {
            for (int j = subsquareColStart; j < subsquareColStart + subsquareSize; j++)
                if (i != row && j != col && board.getCell(i, j) == value) {
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

        int row = indices[0];
        int col = indices[1];
        List<BoardPosition> children = tree.generateChildren(p, row, col);

        for (BoardPosition child : children) {
            if (isValidPlacement(row, col, child.getElement()) && solveBoard(child)) {
                return true;
            }
        }

        // All possible child boards have been tried - there is no solution for this
        // board
        return false;
    }
}
