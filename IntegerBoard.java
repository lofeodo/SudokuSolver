
/**
 * IntegerBoard is an implementation of GameBoard using
 * Integers as entries
 *
 * @author Daniel Lofeodo, Arielle Gazzé
 * @version 1.0
 * @since 1.0
 */

public class IntegerBoard implements GameBoard<Integer> {
    private final int width = 9;
    private final int height = 9;
    private final Integer[][] board;
    private boolean valid = true;

    public IntegerBoard(Integer[][] board) {
        if (board.length != height || board[0].length != width) {
            System.out.println("Illegal dimensions.");
            valid = false;
            this.board = null;
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
        if (!valid)
            return null;

        checkBounds(row, col);

        return board[row][col];
    }

    @Override
    public void setCell(int row, int col, Integer val) throws IndexOutOfBoundsException {
        if (!valid)
            return;

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

    @Override
    public void display() {
        if (!valid) {
            System.out.println("Board is invalid - cannot be displayed.");
            return;
        }

        System.out.print("-------------------------");
        System.out.println();
        for (int row = 0; row < height; row++) {
            System.out.print("| ");
            for (int col = 0; col < width; col++) {
                if ((col + 1) % 3 == 0) {
                    System.out.print(board[row][col] + " | ");
                } else {
                    System.out.print(board[row][col] + " ");
                }
            }
            System.out.println();
            if ((row + 1) % 3 == 0) {
                System.out.print("-------------------------");
                System.out.println();
            }
        }
    }

    private void checkBounds(int row, int col) {
        if (row < 0 || row >= width || col < 0 || col >= height) {
            throw new IndexOutOfBoundsException("Cell position out of bounds.");
        }
    }
}