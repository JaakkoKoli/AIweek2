public class AlphaBeta {

    public static int alphaBetaValue(AlphaBetaNode node) {
        return node.isMaxNode() ? maxValue(node, -100, 100) : minValue(node, -100, 100);
    }
    
    private static int maxValue(AlphaBetaNode node, int alpha, int beta) {
        if(node.isEndState()){
            return node.value();
        }
        int v = -1000;
        for(AlphaBetaNode n:node.generateChildren()){
            v = Math.max(v, minValue(n, alpha, beta));
            alpha = Math.max(alpha, v);
            if(alpha >= beta){
                return v;
            }
        }
        return v;
    }

    private static int minValue(AlphaBetaNode node, int alpha, int beta) {
        if(node.isEndState()){
            return node.value();
        }
        int v = 1000;
        for(AlphaBetaNode n:node.generateChildren()){
            v = Math.min(v, maxValue(n, alpha, beta));
            alpha = Math.min(beta, v);
            if(alpha >= beta){
                return v;
            }
        }
        return v;
    }
    
//    public static int alphaBetaValue(AlphaBetaNode node) {
//        return node.isMaxNode() ? maxValue(node) : minValue(node);
//    }
//    
//    private static int maxValue(AlphaBetaNode node) {
//        if(node.isEndState()){
//            return node.value();
//        }
//        int v = Integer.MIN_VALUE;
//        for(AlphaBetaNode n:node.generateChildren()){
//            v = Math.max(v, minValue(n));
//        }
//        return v;
//    }
//
//    private static int minValue(AlphaBetaNode node) {
//        if(node.isEndState()){
//            return node.value();
//        }
//        int v = Integer.MAX_VALUE;
//        for(AlphaBetaNode n:node.generateChildren()){
//            v = Math.min(v, maxValue(n));
//        }
//        return v;
//    }
    
    public int bestMove(AlphaBetaNode node){
        AlphaBetaNode n = node.generateChildren().get(0);
        if(node.isMaxNode()){
            for(AlphaBetaNode a:node.generateChildren()){
                if(alphaBetaValue(a)>alphaBetaValue(n)){
                    n = a;
                }
            }
        }else{
            for(AlphaBetaNode a:node.generateChildren()){
                if(alphaBetaValue(a)<alphaBetaValue(n)){
                    n = a;
                }
            }
        }
        return parseMove((TicTacToe) node, (TicTacToe) n);
    }
    
    private static int parseMove(TicTacToe n1, TicTacToe n2){
        for(int i=0;i<n1.getState().toCharArray().length;i++){
            if(n1.getState().toCharArray()[i]!=n2.getState().toCharArray()[i]){
                return i;
            }
        }
        return -1;
    }
}
