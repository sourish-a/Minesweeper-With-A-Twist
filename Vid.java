import java.io.File;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Vid implements ActionListener{
	private static String arg;
	private static JFrame f = new JFrame();	
	private JPanel p = new JPanel();
	private JLabel l = new JLabel();
	private JLabel msg = new JLabel("You Win!"); 
	private JButton reset = new JButton("Reset");

	public Vid(){
		arg = "Kiggity.gif";
		
		ImageIcon icon = new ImageIcon(arg);  

		msg.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
		msg.setBounds(900, 20, 123, 29);

		f.setLayout(new BorderLayout());
		f.setSize(288, 512);
		f.setVisible(true);
		l.setIcon(icon);
		p.add(l);
		f.add(p, BorderLayout.CENTER);
		f.add(msg, BorderLayout.NORTH);
		f.getContentPane().add(p);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		reset.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		reset.setForeground(Color.BLACK);
		reset.setBackground(Color.WHITE);
		reset.addActionListener(this);
		f.add(reset, BorderLayout.SOUTH);
	}

	public static void stop(){
		f.setVisible(false);
	}

	public static void main(String[] args) {
		new Vid();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		new Startup();
		stop();
	}
}