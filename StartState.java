import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartState extends JPanel{
    	
	public static final int HARD_DELAY   = 100;
	public static final int NORMAL_DELAY = 250;
	public static final int EASY_DELAY   = 400;
    private static final int SCREEN_WIDTH = GamePanel.SCREEN_WIDTH;
	private static final int SCREEN_HEIGHT = GamePanel.SCREEN_HEIGHT;
    private int mouseOnTheButton = 0;
    
    JButton hardButton   = new JButton("HARD");
    JButton normalButton = new JButton("NORMAL");
    JButton easyButton   = new JButton("EASY");
    //JLabel highestScorLabel = new JLabel("");

    StartState(GamePanel gamePanel, GameFrame gameFrame){
        ButtonControl(hardButton);
        ButtonControl(normalButton);
        ButtonControl(easyButton);

        //highestScorLabel.setFont(new Font("Ink Free", Font.BOLD, SCREEN_WIDTH/20));
        //highestScorLabel.setForeground(Color.red);

        hardButton.addActionListener(e -> { 
            gamePanel.startGame(HARD_DELAY);
            gamePanel.setGameState(GamePanel.GAME_PLAY_STATE);
        });
        hardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mouseOnTheButton = 1;
                gamePanel.repaint();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                mouseOnTheButton = 0;
                gamePanel.repaint();
            }
        });
		normalButton.addActionListener(e -> { 
            gamePanel.startGame(NORMAL_DELAY);
            gamePanel.setGameState(GamePanel.GAME_PLAY_STATE); 
        });
        normalButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mouseOnTheButton = 2;
                gamePanel.repaint();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                mouseOnTheButton = 0;
                gamePanel.repaint();
            }
        });
		easyButton.addActionListener(e -> { 
            gamePanel.startGame(EASY_DELAY);
            gamePanel.setGameState(GamePanel.GAME_PLAY_STATE); 
        });
        easyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mouseOnTheButton = 3;
                gamePanel.repaint();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                mouseOnTheButton = 0;
                gamePanel.repaint();
            }
        });
	
	}

    public void draw(Graphics g) {

        g.setColor(Color.red);
        g.setFont( new Font("Segoe Print",Font.PLAIN, 80));
        FontMetrics metrics5 = getFontMetrics(g.getFont());
        g.drawString("Snake Game", (SCREEN_WIDTH - metrics5.stringWidth("Snake Game"))/2, SCREEN_HEIGHT/4);

        g.setColor(Color.red);
        g.setFont( new Font("Segoe Print",Font.BOLD, 60));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Please Select Difficulty", (SCREEN_WIDTH - metrics.stringWidth("Please Select Difficulty"))/2, SCREEN_HEIGHT/5*2);
        
        g.setColor(Color.red);
        g.setFont( new Font("Segoe Print",Font.BOLD, SCREEN_WIDTH/25));
        FontMetrics metrics4 = getFontMetrics(g.getFont());
        if(mouseOnTheButton==1) {
            g.drawString("Highest Score: " + GamePanel.highestScoreHard, (SCREEN_WIDTH - metrics4.stringWidth("Highest Score: "))/2, SCREEN_HEIGHT/3*2);
        } else if(mouseOnTheButton==2) {
            g.drawString("Highest Score: " + GamePanel.highestScoreNormal, (SCREEN_WIDTH - metrics4.stringWidth("Highest Score: "))/2, SCREEN_HEIGHT/3*2);
        } else if(mouseOnTheButton==3) {
            g.drawString("Highest Score: " + GamePanel.highestScoreEasy, (SCREEN_WIDTH - metrics4.stringWidth("Highest Score: "))/2, SCREEN_HEIGHT/3*2);
        } 
            
    }

    public void ButtonControl(JButton button) {
        button.setForeground(Color.red);
        button.setBackground(Color.black);
        button.setFont(new Font("Cambria", Font.BOLD, SCREEN_WIDTH/20));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
/*
    public JLabel descriptionLabel() {
    JLabel label = new JLabel("Please Select Difficulty");
    label.setForeground(Color.RED);
    Font font = new Font("Ink Free", Font.BOLD, SCREEN_WIDTH / 15);
    label.setFont(font);

    FontMetrics metrics = label.getFontMetrics(font);
    int x = (SCREEN_WIDTH - metrics.stringWidth("Please Select Difficulty")) / 2;
    int y = SCREEN_HEIGHT / 3;
    label.setBounds(x, y, metrics.stringWidth("Please Select Difficulty"), metrics.getHeight());

    return label;
}
    */