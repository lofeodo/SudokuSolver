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
        List<Position<IntegerBoard>> children  = new ArrayList<Position<IntegerBoard>>();
        for (BoardPosition child : validate(p).getChildren())
        {
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
    public List<BoardPosition> generateChildren(BoardPosition p, int i, int j) {
        List<BoardPosition> children = new ArrayList<>();
        IntegerBoard board = p.getElement();

        for (int k = 1; k <= 9; k++)
        {
            IntegerBoard newBoard = new IntegerBoard(board.getBoardCopy());
            newBoard.setCell(i, j, k);
            BoardPosition child = new BoardPosition(newBoard, p, null);
            p.addChild(child);
            children.add(child);
        }

        return children;
    }

    // Helper to validate Position type
    private BoardPosition validate(Position<IntegerBoard> p) throws IllegalArgumentException {
        if (!(p instanceof BoardPosition))
            throw new IllegalArgumentException("Invalid position type");
        return (BoardPosition) p;
    }
}
