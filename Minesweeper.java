import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.UIManager;


public class Minesweeper implements ActionListener{
	private JFrame frame = new JFrame("Minesweeper");
	private JButton reset = new JButton("Reset");
	//private JButton flag = new JButton("Flag Mode");
	//private JButton normal = new JButton("Normal Mode");
	private static JButton[][] buttons; 
	private Container grid = new Container();
	private static Piece[][] board;
	
	private static int length;
	private static int width;
	private static int mines = 1;
	private int clicks = 0;
	
	public static void main(String[] args){
		new Minesweeper(20, 20, 100);
	}
	
	public Minesweeper(int len, int wid, int min){

		//create standard format (helps solve issue with mac buttons)
		try {
    		UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
 		} catch (Exception e) {
            e.printStackTrace();
		}

		length = len; width = wid; mines = min; //sets confinements
		
		//defines array sizes based on argument
		buttons = new JButton[length][width]; 
		board = new Piece[length][width];
		
		//sets frame size and other standard stuff
		frame.setSize(1000, 1000);
		frame.setLayout(new BorderLayout());
		frame.add(reset, BorderLayout.NORTH);
		reset.addActionListener(this);
		
		//creates a grid of the buttons in a container
		grid.setLayout(new GridLayout(length, width));
		for(int i=0; i<length; i++){
			for (int j=0; j<width; j++){
				buttons[i][j] = new JButton();
				buttons[i][j].addActionListener(this);
				buttons[i][j].setBackground(Color.BLACK);
				buttons[i][j].setForeground(Color.BLACK);
				grid.add(buttons[i][j]);
			}
		}
		
		frame.add(grid, BorderLayout.CENTER);
		makeBoard(); //assigns bombs and values to the arrays
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	//assigns bombs and values to the arrays
	public static void makeBoard(){
		int xcoord, ycoord;
		
		//set the specified number of bombs
		for (int i=0; i<mines; i++){
			//generate a random index
			xcoord = (int)(Math.random()*width);
			ycoord = (int)(Math.random()*length);
			
			//if there is no Piece at the generated index, set a bomb there
			if(board[ycoord][xcoord]==null){
				board[ycoord][xcoord] = new Piece(xcoord, ycoord, -1, true);
				buttons[ycoord][xcoord].setText("x");
				buttons[ycoord][xcoord].setFont(new Font("Segoe UI Light", Font.PLAIN, 24));
			}

			//if there is already a Piece at the index, regenerate another index
			else{
				i--;
				continue;
			}
		}
		
		//create an empty piece in all the spaces without a bomb (so there are no more null pointer exceptions)
		for(int j = 0; j < length; j++){
			for (int k=0; k < width; k++){
				if(board[j][k]==null)
					board[j][k] = new Piece(j,k);
			}
		}

		//assign values to the squares that are around the mines
		for(int a = 0; a < length; a++){
			for (int b = 0; b < width; b++){
				if(!board[a][b].getBomb()){
					board[a][b].setNumber(assign(a,b));
					buttons[a][b].setText(board[a][b].getNumber() + "");
					buttons[a][b].setFont(new Font("Segoe UI Light", Font.PLAIN, 24));
				}
			}
		}
	}
	
	//returns a number that should be assigned to a cell, based on the number of bombs that are around that space
	public static int assign(int y, int x){
		int counter = 0;
		
		//if any of the pieces around the given index have a bomb, increase the counter
		if (x-1>=0 && y+1<board.length && board[y+1][x-1].getBomb()) counter++;
		if(x-1>=0 && board[y][x-1].getBomb()) counter++;
		if(x-1>=0 && y-1>=0 && board[y-1][x-1].getBomb()) counter++;
		if(y+1<board.length && board[y+1][x].getBomb()) counter++;
		if(y-1>=0 && board[y-1][x].getBomb()) counter++;
		if(x+1<board[0].length && y+1<board.length && board[y+1][x+1].getBomb()) counter++;
		if(x+1<board[0].length && board[y][x+1].getBomb()) counter++;
		if(x+1<board[0].length && y-1>=0 && board[y-1][x+1].getBomb()) counter++;
		
		return counter;
	}	

	//If a button is pressed, reveal the square, and if the button is 0, reveal all adjacent non-bomb squares
	public static void reveal(int y, int x){

		//if the space has a nonzero number, make only that space white
		if(board[y][x].getNumber()!=0){
			buttons[y][x].setBackground(Color.WHITE);
			board[y][x].setSquare(true);
		}
		
		//if the space has a zero number, recursively check the spaces around it
		else{
			//buttons[y][x].setText("");
			buttons[y][x].setBackground(Color.WHITE);
			board[y][x].setSquare(true);
			if(y+1<board.length) reveal(y+1, x);
			if(y-1>=0 && board[y-1][x].getSquare()== false) reveal(y-1, x);
			if(x+1<board[0].length && y+1<board.length && board[y+1][x+1].getSquare()== false) reveal(y+1, x+1);
			if(x+1<board[0].length && board[y][x+1].getSquare()== false) reveal(y, x+1);
			if(x+1<board[0].length && y-1>=0 && board[y-1][x+1].getSquare()== false) reveal(y-1, x+1);
			if(x-1>=0 && y+1<board.length && board[y+1][x-1].getSquare()== false) reveal(y+1, x-1);
			if(x-1>=0 && board[y][x-1].getSquare()== false) reveal(y, x-1);
			if(x-1>=0 && y-1>=0 && board[y-1][x-1].getSquare() == false) reveal(y-1, x-1);
		}
	}
	
	//allows the frame visibility to be set from another class
	public void setFrame(boolean x){
		frame.setVisible(x);
	}

	//shuffles the bombs around the remaining unopened squares
	public static void shuffle(){
		//remove current bombs
		for(int a=0; a<length; a++){
			for(int b=0; b<width; b++){
				if(board[a][b].getSquare() == false)
					board[a][b].setNumber(0);
					board[a][b].setBomb(false);
					board[a][b].setCheck(false);
			}
		}

		int xcoord, ycoord;

		for (int i=0; i<mines; i++){
			//generate a random index
			xcoord = (int)(Math.random()*width);
			ycoord = (int)(Math.random()*length);
			
			//if the sqyare is not clicked, add in x for bombs
			if(board[ycoord][xcoord].getSquare()==false && board[ycoord][xcoord].getCheck() == false){
				board[ycoord][xcoord] = new Piece(xcoord, ycoord, -1, true);
				board[ycoord][xcoord].setCheck(true);
				buttons[ycoord][xcoord].setText("x");
				buttons[ycoord][xcoord].setFont(new Font("Segoe UI Light", Font.PLAIN, 24));
			}

			
			else{
				i--;
				continue;
			}
		}

		//
		for(int a = 0; a < length; a++){
			for (int b = 0; b < width; b++){
				if(!board[a][b].getBomb()){
					board[a][b].setNumber(assign(a,b));
					buttons[a][b].setText(board[a][b].getNumber() + "");
					buttons[a][b].setFont(new Font("Segoe UI Light", Font.PLAIN, 24));
				}
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(reset)){
			setFrame(false);
			new Startup();
		}
		else{
			for (int i=0; i<board.length; i++){
				for (int j=0; j<board[0].length; j++){
					if(event.getSource().equals(buttons[i][j])){
						//if user clicks a bomb, turn all the squares white
						if(buttons[i][j].getText().equals("x")){
							for(int a=0; a<length; a++){
								for(int b=0; b<width; b++){
									buttons[a][b].setBackground(Color.WHITE);
									board[a][b].setSquare(true);
									if(buttons[a][b].getText().equals("x")){
										buttons[a][b].setText("");
										buttons[a][b].setVisible(true);
										buttons[a][b].setIcon(new ImageIcon("images.png"));
									}
								}
							}
						}
						
						//if the user clicks a space with no bombs around it, implement reveal method
						else if(buttons[i][j].getText().equals("0")){
							reveal(i, j);
							checkWin();
							clicks++;
							//if (clicks % 3 == 0 )
						//		shuffle();
						}
						
						//otherwise, record the square as clicked
						else{
							clicks++;
							buttons[i][j].setBackground(Color.WHITE);
							board[i][j].setSquare(true);
							if (clicks % 3 == 0 )
								shuffle();
							checkWin();
						}
					}
				}
			}
		}		
	}

	//check if the user won the game
	public void checkWin(){
		int num = 0;
		
		//count the remaining number of squares
		for(int a = 0; a<length; a++ ){
			for (int b = 0; b<width; b++){
				if(board[a][b].getSquare() == false)
					num++;
			}
		}

		//if the remaining number of squares = the number of mines, cue the win video
		if (num == mines){
			setFrame(false);
			new Vid();
		}
	}

	//---------------------------------------------------
	public int clickBoard(int x, int y){
		board[y][x].setSquare(true);
		buttons[y][x].setBackground(Color.RED);
		buttons[y][x].doClick();
		return board[y][x].getNumber();
	}

	public boolean getClicked(int x, int y){
		return board[y][x].getSquare();
	}

	public int getNumber(int x, int y){
		return board[y][x].getNumber();
	}
}
