public class BoardPosition implements Position<IntegerBoard> {
    private IntegerBoard board;
    private Position<IntegerBoard> parent;

    public BoardPosition(IntegerBoard board, Position<IntegerBoard> parent) {
        this.board = board;
        this.parent = parent;
    }

    @Override
    public IntegerBoard getElement() {
        return board;
    }

    public Position<IntegerBoard> getParent() {
        return parent;
    }
}
