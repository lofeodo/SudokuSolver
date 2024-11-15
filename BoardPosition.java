public class BoardPosition<T> implements Position<GameBoard<T>> {
    private GameBoard<T> board;
    private BoardPosition<T> parent;

    public BoardPosition(GameBoard<T> board, BoardPosition<T> parent) {
        this.board = board;
        this.parent = parent;
    }

    @Override
    public GameBoard<T> getElement() {
        return board;
    }

    public BoardPosition<T> getParent() {
        return parent;
    }
}
