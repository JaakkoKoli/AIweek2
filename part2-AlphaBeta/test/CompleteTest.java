import org.junit.Test;
import org.junit.Rule;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("AlphaBeta")
public class CompleteTest {

    @Rule
    public MockStdio io = new MockStdio();

    @Test
    public void testEmptyBoard() {
        String emptyBoard = "???" + "???" + "???";
        TicTacToe r = new TicTacToe(emptyBoard, true);
        assertEquals("AlphaBetaValue was not correct with an empty board", 0, AlphaBeta.alphaBetaValue(r));
    }

    @Test
    public void testXAdvantage() {
        String emptyBoard = "xx?" + "o??" + "o??";
        TicTacToe r = new TicTacToe(emptyBoard, true);
        assertEquals("Wrong alphaBetaValue with board: xx? o?? o??", 1, AlphaBeta.alphaBetaValue(r));
    }

    @Test
    public void testOAdvantage() {
        String emptyBoard = "oo?" + "x??" + "x??";
        TicTacToe r = new TicTacToe(emptyBoard, true);
        assertEquals("Wrong alphaBetaValue with board: oo? x?? x??", -1, AlphaBeta.alphaBetaValue(r));
    }
}
