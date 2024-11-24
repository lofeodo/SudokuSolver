import java.util.ArrayList;
import java.util.List;

/**
 * interface for grid-oriented game boards storing generic type values
 *
 * @author Daniel Leofodo et Arielle Gazzé
 * 
 * @since 1.0 (8 November 2024)
 */

public class BoardPosition implements Position<IntegerBoard> {
    private final IntegerBoard board;
    private final BoardPosition parent;
    private List<BoardPosition> children;

    public BoardPosition(IntegerBoard board, BoardPosition parent, List<BoardPosition> children) {
        this.board = board;
        this.parent = parent;
        this.children = children;
    }

    @Override
    public IntegerBoard getElement() {
        return board;
    }

    public BoardPosition getParent() {
        return parent;
    }

    public void addChild(BoardPosition p) {
        if (children == null) {
            children = new ArrayList<BoardPosition>();
        }
        children.add(p);
    }

    public List<BoardPosition> getChildren() {
        return children;
    }
}
