import java.awt.*;
import javax.swing.*;

public class GameOverState {
    
    JButton restartButton = new JButton("RESTART");
    JButton exitButton    = new JButton("EXIT");
    GamePanel gamePanel;

    public GameOverState(GamePanel gamePanel, GameFrame gameFrame) {
        this.gamePanel = gamePanel;
        ButtonControl(restartButton);
        ButtonControl(exitButton);

        restartButton.addActionListener( e -> {
            GamePanel newGamePanel = new GamePanel(gameFrame);
            gameFrame.getContentPane().remove(gamePanel); // remove old GamePanel
            gameFrame.getContentPane().add(newGamePanel); // add a new GamePanel
            gameFrame.pack();
            gameFrame.setVisible(true);
            newGamePanel.requestFocusInWindow();
        });
		exitButton.addActionListener(e -> { System.exit(0); });
    }

    public void GameOver(Graphics g, int applesEaten, int flag) {
        //Score
        g.setColor(Color.red);
        g.setFont(new Font("Segoe Print", Font.BOLD, 40));
        FontMetrics metrics1 = g.getFontMetrics();
        g.drawString("Your Final Score is: " + applesEaten, (GamePanel.SCREEN_WIDTH - metrics1.stringWidth("Your Final Score: " + applesEaten)) / 2, g.getFont().getSize());

        if(flag > 0){
            g.setColor(Color.red);
            g.setFont(new Font("Segoe Print", Font.BOLD, 40));
            FontMetrics metrics2 = g.getFontMetrics();
            g.drawString("Highest Score!!", (GamePanel.SCREEN_WIDTH - metrics2.stringWidth("HighestScore!!")) / 2, g.getFont().getSize()*2);    
        }

        //Game Over text
        g.setColor(Color.red);
        g.setFont(new Font("Segoe Print", Font.BOLD, 95));
        FontMetrics metrics3 = g.getFontMetrics(); 
        g.drawString("GameOver", (GamePanel.SCREEN_WIDTH - metrics3.stringWidth("GameOver")) / 2, GamePanel.SCREEN_HEIGHT / 2);
        if (applesEaten <= 10) {

            TextDisplay(g, "Try Harder!!");

        } else if (applesEaten <= 20) {

            TextDisplay(g, "Good!!");
            
        } else if (applesEaten <= 40) {

            TextDisplay(g, "Nice Job!!");

        } else {

            TextDisplay(g, "UNBELIVABLE!!");

        }
           
        gamePanel.setHighestScore(applesEaten);
        restartButton.setBounds((GamePanel.SCREEN_WIDTH - 250) / 2, GamePanel.SCREEN_HEIGHT / 2 + 100, 250, 70);
        exitButton.setBounds((GamePanel.SCREEN_WIDTH - 250) / 2, GamePanel.SCREEN_HEIGHT / 2 + 200, 250, 50);
    }   
    
    public void ButtonControl(JButton button){
        button.setForeground(Color.red);
        button.setBackground(Color.black);
        button.setFont(new Font("Segoe Print", Font.BOLD, 40));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void TextDisplay(Graphics g, String text){
        g.setColor(Color.red);
        g.setFont(new Font("Segoe Print", Font.BOLD, 75));
        FontMetrics metrics4 = g.getFontMetrics();
        g.drawString(text, (GamePanel.SCREEN_WIDTH - metrics4.stringWidth(text)) / 2, GamePanel.SCREEN_HEIGHT / 3);
    }
}