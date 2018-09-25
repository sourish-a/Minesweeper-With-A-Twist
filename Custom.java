import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class Custom implements ActionListener{
	
	public static void main(String[] args){
		new Custom();
	}

	JFrame input = new JFrame("Custom");
	JLabel one = new JLabel("Length");
	JLabel two = new JLabel("Width");
	JLabel three = new JLabel("# Mines");
	JLabel error = new JLabel("");
	JTextField length = new JTextField();
	JTextField width = new JTextField();
	JTextField mines = new JTextField();
	JButton play = new JButton("Play");
	
	public Custom(){

		//create standard format (helps solve issue with mac buttons)
		try {
    		UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
 		} catch (Exception e) {
            e.printStackTrace();
		}

		input.getContentPane().setBackground(Color.BLACK);
		input.setBounds(100, 100, 450, 300);
		input.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		input.getContentPane().setLayout(null);
		
		length.setFont(new Font("Segoe UI Light", Font.PLAIN, 28));
		length.setBounds(20, 80, 123, 29);
		length.setForeground(Color.BLACK);
		length.setBackground(Color.WHITE);
		input.getContentPane().add(length);
		
		width.setFont(new Font("Segoe UI Light", Font.PLAIN, 28));
		width.setBounds(155, 80, 123, 29);
		width.setForeground(Color.BLACK);
		width.setBackground(Color.WHITE);
		input.getContentPane().add(width);
		
		mines.setFont(new Font("Segoe UI Light", Font.PLAIN, 28));
		mines.setBounds(290, 80, 123, 29);
		mines.setForeground(Color.BLACK);
		mines.setBackground(Color.WHITE);
		input.getContentPane().add(mines);

		one.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
		one.setBounds(45, 50, 123, 29);
		one.setForeground(Color.WHITE);
		input.getContentPane().add(one);

		two.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
		two.setBounds(188, 50, 123, 29);
		two.setForeground(Color.WHITE);
		input.getContentPane().add(two);

		three.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
		three.setBounds(310, 50, 123, 29);
		three.setForeground(Color.WHITE);
		input.getContentPane().add(three);
		
		play.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		play.setForeground(Color.BLACK);
		play.setBackground(Color.WHITE);
		play.setBounds(155, 130, 123, 29);
		play.addActionListener(this);
		input.getContentPane().add(play);

		error.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
		error.setBounds(145, 180, 180, 29);
		error.setForeground(Color.WHITE);
		input.getContentPane().add(error);
		
		input.setVisible(true);
	}
	
	public void setFrame(boolean x){
		input.setVisible(x);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int len = Integer.parseInt(length.getText());
		int wid = Integer.parseInt(width.getText());
		int min = Integer.parseInt(mines.getText());
		
		if(len>0 && wid>0 && min<=len*wid){
			setFrame(false);
			new Minesweeper(len, wid, min);
		}
		else{
			error.setText("Enter Valid Range");
		}
	}
	
}
