/**
 * IntegerBoard is an implementation of GameBoard using
 * Integers as entries
 *
 * @author Daniel Lofeodo, Arielle Gazzé
 * 
 * @version 1.0
 * @since 1.0
 */

public class IntegerBoard implements GameBoard<Integer> {
    private final int width;
    private final int height;
    private final int subsquareSize;
    private final Integer[][] board;

    public IntegerBoard(Integer[][] board) {
        height = board.length;
        width = board[0].length;
        subsquareSize = getSubsquareSize(height);
        if (!isBoardSizeValid(width, height, subsquareSize)) {
            throw new IllegalArgumentException(
                    "Illegal dimensions. Board's height and width must be equal and be square-rootable.");
        } else {
            this.board = board;
        }
    }

    public Integer[][] getBoardCopy() {
        Integer[][] copy = new Integer[board.length][];
        for (int i = 0; i < board.length; i++) {
            copy[i] = board[i].clone();
        }
        return copy;
    }

    public int[] getFirstZeroIndices() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (getCell(i, j) == 0) {
                    return new int[] { i, j };
                }
            }
        }

        // Board is "Full"
        return null;
    }

    @Override
    public Integer getCell(int row, int col) throws IndexOutOfBoundsException {
        checkBounds(row, col);
        return board[row][col];
    }

    @Override
    public void setCell(int row, int col, Integer val) throws IndexOutOfBoundsException {
        checkBounds(row, col);
        board[row][col] = val;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public int getSubsquareSize() {
        return subsquareSize;
    }

    @Override
    public void display() {
        int numSubSquares = width / subsquareSize;
        int lineLength = 2 * 2 + 3 * (numSubSquares - 1) + 2 * width - numSubSquares;
        String line = "-".repeat(lineLength);

        System.out.print(line);
        System.out.println();
        for (int row = 0; row < height; row++) {
            System.out.print("| ");
            for (int col = 0; col < width; col++) {
                if ((col + 1) % subsquareSize == 0) {
                    System.out.print(board[row][col] + " | ");
                } else {
                    System.out.print(board[row][col] + " ");
                }
            }
            System.out.println();
            if ((row + 1) % subsquareSize == 0) {
                System.out.print(line);
                System.out.println();
            }
        }
    }

    private void checkBounds(int row, int col) {
        if (row < 0 || row >= width || col < 0 || col >= height) {
            throw new IndexOutOfBoundsException("Cell position out of bounds.");
        }
    }

    private int getSubsquareSize(int boardSize) {
        return (int) Math.sqrt(boardSize);
    }

    private boolean isBoardSizeValid(int boardWidth, int boardHeight, int subsquareSize) {
        // Board must be square.
        if (boardWidth != boardHeight)
            return false;
        // Board size must be squareroot-able
        if (subsquareSize * subsquareSize != boardWidth)
            return false;

        return true;
    }
}