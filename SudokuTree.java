import java.util.ArrayList;
import java.util.List;

public class SudokuTree extends AbstractTree<IntegerBoard> {

    private final BoardPosition root;

    public SudokuTree(IntegerBoard initialBoard) {
        this.root = new BoardPosition(initialBoard, null);
    }

    @Override
    public BoardPosition root() {
        return this.root;
    }

    @Override
    public BoardPosition parent(Position<IntegerBoard> p) throws IllegalArgumentException {
        return validate(p).getParent();
    }

    @Override
    public Iterable<Position<IntegerBoard>> children(Position<IntegerBoard> p) throws IllegalArgumentException {
        // Collect children in a list
        List<Position<IntegerBoard>> children = new ArrayList<>();
        for (BoardPosition child : generateChildren(validate(p))) {
            children.add(child);
        }
        return children;
    }

    @Override
    public int numChildren(Position<IntegerBoard> p) throws IllegalArgumentException {
        return generateChildren(validate(p)).size();
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Position<IntegerBoard> p) {
        int count = 1;
        var children = children(p);
        for (Position<IntegerBoard> child : children) {
            count += size(child);
        }
        return count;
    }

    // Generate possible children based on game logic
    private List<BoardPosition> generateChildren(BoardPosition p) {
        List<BoardPosition> children = new ArrayList<>();
        IntegerBoard board = p.getElement();

        // TODO

        return children;
    }

    // Helper to validate Position type
    private BoardPosition validate(Position<IntegerBoard> p) throws IllegalArgumentException {
        if (!(p instanceof BoardPosition))
            throw new IllegalArgumentException("Invalid position type");
        return (BoardPosition) p;
    }
}
