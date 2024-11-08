public class BoardPosition<T> implements Position<GameBoard<T>> {
    private GameBoard<T> board;
    private Position<GameBoard<T>> parent;

    public BoardPosition(GameBoard<T> board, Position<GameBoard<T>> parent) {
        this.board = board;
        this.parent = parent;
    }

    @Override
    public GameBoard<T> getElement() {
        return board;
    }

    public Position<GameBoard<T>> getParent() {
        return parent;
    }
}
