import java.util.ArrayList;
import java.util.List;
import java.lang.IllegalArgumentException;

public class GameTree<T> extends AbstractTree<GameBoard<T>> {

    private BoardPosition<T> root;

    public GameTree(GameBoard<T> initialBoard) {
        this.root = new BoardPosition<>(initialBoard, null);
    }

    @Override
    public BoardPosition<T> root() {
        return this.root;
    }

    @Override
    public BoardPosition<T> parent(Position<GameBoard<T>> p) throws IllegalArgumentException {
        return validate(p).getParent();
    }

    @Override
    public Iterable<Position<GameBoard<T>>> children(Position<GameBoard<T>> p) throws IllegalArgumentException {
        // Collect children in a list
        List<Position<GameBoard<T>>> children = new ArrayList<>();
        for (BoardPosition<T> child : generateChildren((BoardPosition<T>) p)) {
            children.add(child);
        }
        return children;
    }

    @Override
    public int numChildren(Position<GameBoard<T>> p) throws IllegalArgumentException {
        return ((List<?>) generateChildren((BoardPosition<T>) p)).size();
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Position<GameBoard<T>> p) {
        int count = 1;
        for (Position<GameBoard<T>> child : children(p)) {
            count += size(child);
        }
        return count;
    }

    // Generate possible children based on game logic
    private List<BoardPosition<T>> generateChildren(BoardPosition<T> p) {
        List<BoardPosition<T>> children = new ArrayList<>();
        GameBoard<T> board = p.getElement();

        // Sudoku-specific child generation logic goes here.
        // For example, find next empty cell, try possible entries, and create child
        // nodes.

        return children;
    }

    // Helper to validate Position type
    private BoardPosition<T> validate(Position<GameBoard<T>> p) throws IllegalArgumentException {
        if (!(p instanceof BoardPosition))
            throw new IllegalArgumentException("Invalid position type");
        return (BoardPosition<T>) p;
    }
}
