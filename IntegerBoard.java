
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

    public Integer[][] getBoardCopy(){
        Integer[][] copy = new Integer[board.length][];
        for (int i = 0; i < board.length; i++) {
            copy[i] = board[i].clone();
        }
        return copy;
    }

    @Override
    public Integer getCell(int x, int y) throws IndexOutOfBoundsException {
        if (!valid)
            return null;

        checkBounds(x, y);

        return board[y][x];
    }

    @Override
    public void setCell(int x, int y, Integer val) throws IndexOutOfBoundsException {
        if (!valid)
            return;

        checkBounds(x, y);

        board[y][x] = val;
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

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void checkBounds(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IndexOutOfBoundsException("Cell position out of bounds.");
        }
    }
}