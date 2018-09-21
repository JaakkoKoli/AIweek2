import java.util.List;

public interface AlphaBetaNode {

    List<AlphaBetaNode> generateChildren();

    boolean isMaxNode();

    boolean isEndState();

    int value();
}
