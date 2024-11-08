import java.util.ArrayList;
import java.util.List;

public class GameTree<T> extends AbstractTree<GameBoard<T>> {
    private BoardPosition<T> root;

    public GameTree(GameBoard<T> board) {
        this.root = new BoardPosition<>(board, null);
    }

    @Override
    public BoardPosition<T> root() {
        return root;
    }

    @Override
    public int size() {
        // TODO
        return -1;
    }

    @Override
    public BoardPosition<T> parent(Position<GameBoard<T>> p) throws IllegalArgumentException {
        if (p instanceof BoardPosition) {
            return ((BoardPosition<T>) p).getParent();
        }

        throw new IllegalArgumentException("Position must be an instance of BoardPosition<T>");
    }

    @Override
    public Iterable<Position<GameBoard<T>>> children(Position<GameBoard<T>> p) throws IllegalArgumentException {
        List<BoardPosition<T>> children = new ArrayList<>();
        GameBoard<T> board = p.getElement();

        for (GameBoard<T> newBoard : )
    }
}
