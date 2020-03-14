package neuro;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame{
	private static final long serialVersionUID = 1L;
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();

	public Main(){
		frame.setTitle("ME-E-E-E-EH");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);

		frame.setSize(600,600);
		frame.setLocation(700,300);

		Painter painter = new Painter();
		painter.setBounds(0, 0, 600, 600);

		frame.add(painter);
		frame.setVisible(true);
	}

	public static void main(String[] args){
		new Main();
	}
}
