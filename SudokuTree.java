import java.util.ArrayList;
import java.util.List;

/**
 * interface for grid-oriented game boards storing generic type values
 *
 * @author Daniel Leofodo et Arielle Gazzé
 * 
 * @version 1.1
 * Make the code work for any size sudoku board
 * @since 1.0 (8 November 2024)
 */

public class SudokuTree extends AbstractTree<IntegerBoard> {

    private final BoardPosition root;

    public SudokuTree(IntegerBoard initialBoard) {
        this.root = new BoardPosition(initialBoard, null, null);
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
        List<Position<IntegerBoard>> children = new ArrayList<Position<IntegerBoard>>();

        for (BoardPosition child : validate(p).getChildren()) {
            children.add(child);
        }

        return children;
    }

    @Override
    public int numChildren(Position<IntegerBoard> p) throws IllegalArgumentException {
        var children = validate(p).getChildren();

        if (children == null) {
            return 0;
        }

        return children.size();
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

    public List<BoardPosition> generateChildren(BoardPosition p, int i, int j) {
        List<BoardPosition> children = new ArrayList<>();
        IntegerBoard board = p.getElement();
        int height = (int) board.getHeight();

        for (int k = 1; k <= height; k++) {
            IntegerBoard newBoard = new IntegerBoard(board.getBoardCopy());
            newBoard.setCell(i, j, k);
            BoardPosition child = new BoardPosition(newBoard, p, null);
            p.addChild(child);
            children.add(child);
        }

        return children;
    }

    private BoardPosition validate(Position<IntegerBoard> p) throws IllegalArgumentException {
        if (!(p instanceof BoardPosition))
            throw new IllegalArgumentException("Invalid position type");
        return (BoardPosition) p;
    }
}
