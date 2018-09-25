
public class Solver{

	private static int length = 5;
	private static int width = 5;
	private static int mines = 2;

	private static int[][] indexes = new int[mines][2];

	private static Minesweeper game;

	public static void main(String[] args) {
		game = new Minesweeper(length, width, mines);
		clickAroundOne(2, 2, 2, 1);
	}

	public static void checkOnes(){

	/*	for (int y=0; y<length; y++){ //checks each button in the grid
			for (int x=0; x<width; x++){
				if(x.getNumber(j, i) == 1){ //if the number checked equals one
					if(y+1<length && containsBomb(x, y+1)) clickAround(x, y+1);
					if(y-1>=0 && board[y-1][x].getSquare()== false) reveal(y-1, x);
					if(x+1<board[0].length && y+1<board.length && board[y+1][x+1].getSquare()== false) reveal(y+1, x+1);
					if(x+1<board[0].length && board[y][x+1].getSquare()== false) reveal(y, x+1);
					if(x+1<board[0].length && y-1>=0 && board[y-1][x+1].getSquare()== false) reveal(y-1, x+1);
					if(x-1>=0 && y+1<board.length && board[y+1][x-1].getSquare()== false) reveal(y+1, x-1);
					if(x-1>=0 && board[y][x-1].getSquare()== false) reveal(y, x-1);
					if(x-1>=0 && y-1>=0 && board[y-1][x-1].getSquare() == false) reveal(y-1, x-1);
				}
			}
		}*/
	}

	public static boolean containsBomb(int x, int y){
		for(int i=0; i<indexes.length; i++){
			if (indexes[i][0]==x && indexes[i][1]==y)
				return true;
		}
		return false;
	}

	//given index x,y for location of "1", x2,y2 for location of bomb, click around everywhere else
	public static void clickAroundOne(int x, int y, int x2, int y2){
		if (x-1>=0 && y+1<length && (x-1!=x2 || y+1!=y2)) game.clickBoard(x-1, y+1);
		if (x-1>=0 && (x-1!=x2 || y!=y2)) game.clickBoard(x-1, y);
		if (x-1>=0 && y-1>=0 && (x-1!=x2 || y-1!=y2)) game.clickBoard(x-1, y-1);
		if (y+1<length && y+1!=y2) game.clickBoard(x, y+1);
		if (y-1>=0 && y-1!=y2) game.clickBoard(x, y-1);
		if (x+1<width && y+1<length && (x-1!=x2 || y+1!=y2)) game.clickBoard(x+1, y+1);
		if (x+1<width && (x+1!=x2 || y!=y2)) game.clickBoard(x+1, y);
		if (x+1<width && y-1>=0 && (x+1!=x2 || y-1!=y2)) game.clickBoard(x+1, y-1);
	}
}