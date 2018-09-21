
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String emptyBoard = "???" + "???" + "???";
        TicTacToe r = new TicTacToe(emptyBoard, false);
        System.out.println(r);
        play(r);
    }

    private static void play(TicTacToe r) {
        Scanner s = new Scanner(System.in);
        AlphaBeta ab = new AlphaBeta();
        System.out.println("Best move: "+ab.bestMove(r));
        while(true){
            while(!r.play(Integer.parseInt(s.next())));
            System.out.println(r);
            if(r.isEndState()) break;
            System.out.println("Best move: "+ab.bestMove(r));
        }
        if(r.won('x')){
            System.out.println("x voitti");
        }else if(r.won('o')){
            System.out.println("o voitti");
        }else{
            System.out.println("tasapeli");
        }
    }
}
