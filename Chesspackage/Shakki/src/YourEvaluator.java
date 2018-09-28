public class YourEvaluator extends Evaluator {

    // Implement your heuristic evaluation function here!
    // The below default function prefers:
    //  * not to lose (white king is worth at lot)
    //  * to win (black not having a king is worth a lot)
    //  * to have more white pieces (+1) and less black pieces (-1)

	public double eval(Position p) {
		double ret = 0;
		for(int x = 0; x < p.board.length; ++x) {
			for(int y = 0; y < p.board[x].length; ++y) {
				if(p.board[x][y] == Position.Empty) continue;
				if(p.board[x][y] == Position.WKing) ret += 1e9+((x>0&&x<6)?10:0)+((y>0&&y<6)?10:0);
				if(p.board[x][y] == Position.WQueen) ret += 200+((x>1&&x<5&&y>1&&y<5)?3:0)+((x>2&&x<4&&y>2&&y<4)?10:0);
				if(p.board[x][y] == Position.WRook) ret += 100+((x>1&&x<5&&y>1&&y<5)?5:0);
				if(p.board[x][y] == Position.WBishop) ret += 60+((x>1&&x<5&&y>1&&y<5)?5:0);
				if(p.board[x][y] == Position.WKnight) ret += 60+((x>1&&x<5&&y>1&&y<5)?5:0);
				if(p.board[x][y] == Position.WPawn) ret += 15*(y)+((x>1&&x<5)?1:0);
				if(p.board[x][y] == Position.BKing) ret -= 1e9+((x>0&&x<6)?10:0)+((y>0&&y<6)?10:0);
				if(p.board[x][y] == Position.BQueen) ret -= 200+((x>1&&x<5&&y>1&&y<5)?3:0)+((x>2&&x<4&&y>2&&y<4)?10:0);
				if(p.board[x][y] == Position.BRook) ret -= 100+((x>1&&x<5&&y>1&&y<5)?5:0);
				if(p.board[x][y] == Position.BBishop) ret -= 60+((x>1&&x<5&&y>1&&y<5)?5:0);
				if(p.board[x][y] == Position.BKnight) ret -= 60+((x>1&&x<5&&y>1&&y<5)?5:0);
				if(p.board[x][y] == Position.BPawn) ret -= 15*(6-y)+((x>1&&x<5)?1:0);
			}
		}
		return ret;
	}
}
