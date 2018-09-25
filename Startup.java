import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Startup implements ActionListener{
	
	public static void main(String[] args){
		new Startup();
	}
	
	JFrame start = new JFrame("Welcome");
	JButton beginner = new JButton("Beginner");
	JButton intermediate = new JButton("Intermediate");
	JButton expert = new JButton("Advanced");
	JButton custom = new JButton("Custom");
	JLabel welcome = new JLabel("Welcome to Minesweeper!");
	
	public Startup(){
		start.getContentPane().setBackground(Color.BLACK);
		start.setBounds(100, 100, 450, 300);
		start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		start.getContentPane().setLayout(null);
		
		welcome.setFont(new Font("Segoe UI Light", Font.PLAIN, 28));
		welcome.setForeground(Color.WHITE);
		welcome.setBounds(56, 16, 313, 38);
		start.getContentPane().add(welcome);
		
		beginner.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		beginner.setForeground(Color.BLACK);
		beginner.setBackground(Color.WHITE);
		beginner.setBounds(144, 62, 123, 29);
		beginner.addActionListener(this);
		start.getContentPane().add(beginner);
		
		intermediate.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		intermediate.setForeground(Color.BLACK);
		intermediate.setBackground(Color.WHITE);
		intermediate.setBounds(144, 107, 123, 29);
		intermediate.addActionListener(this);
		start.getContentPane().add(intermediate);
		
		expert.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		expert.setBackground(Color.WHITE);
		expert.setForeground(Color.BLACK);
		expert.setBounds(144, 149, 123, 29);
		expert.addActionListener(this);
		start.getContentPane().add(expert);
		
		custom.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		custom.setForeground(Color.BLACK);
		custom.setBackground(Color.WHITE);
		custom.setBounds(144, 194, 123, 29);
		custom.addActionListener(this);
		start.getContentPane().add(custom);
		
		start.setVisible(true);
	}
	
	public void setStart(boolean x){
		start.setVisible(x);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(beginner)){
			setStart(false);
			new Minesweeper(9, 9, 10);
			Vid.stop();
		}
		
		if(e.getSource().equals(intermediate)){
			new Minesweeper(16, 16, 40);
			setStart(false);
			Vid.stop();
		}
		
		if (e.getSource().equals(expert)){
			new Minesweeper(24, 24, 99);
			setStart(false);
			Vid.stop();
		}
		
		if (e.getSource().equals(custom)){
			new Custom();
			setStart(false);
			Vid.stop();
		}
	}
}
