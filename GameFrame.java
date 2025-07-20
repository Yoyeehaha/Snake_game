import javax.swing.JFrame;

public class GameFrame extends JFrame{
	GamePanel gamePanel;

	GameFrame(){
		//this.add(new GamePanel(this));
		this.add(new GamePanel(this));
		this.setTitle("貪吃蛇");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}