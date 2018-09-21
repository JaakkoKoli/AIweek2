import java.util.ArrayList;

public class TicTacToe implements AlphaBetaNode {
    boolean crossesTurn;
    private String state;

    /**
     * Tic-tac-toe implementation
     * @param state String that represents the state of the board. May only contain characters: 'o', 'x' or '?'.
     */
    public TicTacToe(String state, boolean crossesTurn) {
        this.state = state;
        this.crossesTurn = crossesTurn;
    }

    public boolean play(int x){
        if(x<0||x>8){
            return false;
        }
        if(state.charAt(x)!='?'){
            return false;
        }
        char[] c=this.state.toCharArray();
        c[x]=this.crossesTurn ? 'x' : 'o';
        this.state=new String(c);
        this.crossesTurn=!this.crossesTurn;
        return true;
    }
    
    @Override
    public int value() {
        if(this.won('x')){
            return 1;
        }else if(this.won('o')){
            return -1;
        }
        return 0;
    }

    @Override
    public ArrayList<AlphaBetaNode> generateChildren() {
        ArrayList<AlphaBetaNode> children = new ArrayList<>();
        for(int i=0;i<state.length();i++){
            if(state.charAt(i)=='?'){
                char[] newState = this.state.toCharArray();
                newState[i]=this.crossesTurn ? 'x' : 'o';
                children.add(new TicTacToe(new String(newState), !this.crossesTurn));
            }
        }
        return children;
    }
    
    public String getState(){
        return this.state;
    }

    @Override
    public boolean isEndState() {
        return state.indexOf('?') < 0 || won('x') || won('o');
    }

    public boolean won(char c) {
        String s = "" + c + c + c;
        return (state.substring(0, 3).equals(s)
                || state.substring(3, 6).equals(s)
                || state.substring(6, 9).equals(s)
                || ("" + state.charAt(0) + state.charAt(3) + state.charAt(6)).equals(s)
                || ("" + state.charAt(1) + state.charAt(4) + state.charAt(7)).equals(s)
                || ("" + state.charAt(2) + state.charAt(5) + state.charAt(8)).equals(s)
                || ("" + state.charAt(0) + state.charAt(4) + state.charAt(8)).equals(s)
                || ("" + state.charAt(6) + state.charAt(4) + state.charAt(2)).equals(s));
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        char[] arr = state.toCharArray();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ret.append("|").append(arr[3 * i + j]);
            }
            ret.append("|\n");
        }
        return ret.toString();
    }

    @Override
    public boolean isMaxNode() {
        return crossesTurn;
    }
}
