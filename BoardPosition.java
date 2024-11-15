public class BoardPosition implements Position<IntegerBoard> {
    private final IntegerBoard board;
    private final BoardPosition parent;

    public BoardPosition(IntegerBoard board, BoardPosition parent) {
        this.board = board;
        this.parent = parent;
    }

    @Override
    public IntegerBoard getElement() {
        return board;
    }

    public BoardPosition getParent() {
        return parent;
    }
}
