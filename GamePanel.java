import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener{

	static final int SCREEN_WIDTH = 900;
	static final int SCREEN_HEIGHT = 750;
	static final int UNIT_SIZE = 50;
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;

	static final int GAME_INITIAL_STATE = 0;
	static final int GAME_PLAY_STATE = 1;
	private int gameState = GAME_INITIAL_STATE;
    public int flag = 0;

	public static int highestScoreHard = 0;
	public static int highestScoreNormal = 0;
	public static int highestScoreEasy = 0;
	private boolean running = false;
	private Timer timer;
	public int delay;

	private Snake snake;
	private Apple apple;
	private Bomb bomb;

	StartState startState;
	GameOverState gameOverState;
	//Graphics graphics;
	

	GamePanel(GameFrame gameFrame){
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());

		snake = new Snake();
		bomb = new Bomb(snake);
		apple = new Apple(snake, bomb);
		
		startState = new StartState(this, gameFrame);
		gameOverState = new GameOverState(this, gameFrame);

        repaint();
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, SCREEN_HEIGHT/2)); // put option buttons in the middle of the screen
		this.add(startState.hardButton);
		this.add(startState.normalButton);
		this.add(startState.easyButton);
	}

	public void startGame(int delay) {
		this.delay = delay;
		this.remove(startState.hardButton);
		this.remove(startState.normalButton);
		this.remove(startState.easyButton);
		this.requestFocusInWindow();
		apple.newApple();
		running = true;
		timer = new Timer(delay,this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

	public void draw(Graphics g) {
        if(gameState == GAME_INITIAL_STATE){
			startState.draw(g);
		}
		if(gameState == GAME_PLAY_STATE){
			if(running) {	
				apple.draw(g);
				bomb.draw(g);
				snake.draw(g);
				setHighestScore(apple.getApplesEaten());
				
                g.setColor(Color.decode("#333333"));
                g.fillRect(0, 0, SCREEN_WIDTH, UNIT_SIZE);

				g.setColor(Color.white);
				g.setFont( new Font("Ink Free",Font.BOLD,40));
				//FontMetrics metrics = getFontMetrics(g.getFont());
				g.drawString("Score: "+apple.getApplesEaten(), 600, g.getFont().getSize());
				switch(delay){
					case StartState.HARD_DELAY:
						g.drawString("Best Score: "+highestScoreHard, 300, g.getFont().getSize());
						g.drawString("Mode: "+"Hard", 10, g.getFont().getSize());
						break;
					case StartState.NORMAL_DELAY:
					    g.drawString("Best Score: "+highestScoreNormal, 300, g.getFont().getSize());
						g.drawString("Mode: "+"Normal", 10, g.getFont().getSize());
						break;
					case StartState.EASY_DELAY:
					    g.drawString("Best Score: "+highestScoreEasy, 300, g.getFont().getSize());
						g.drawString("Mode: "+"Easy", 10, g.getFont().getSize());
						break;
				}
			}else {
				gameOverState.GameOver(g, apple.getApplesEaten(), flag);
				flag = 0;
				add(gameOverState.restartButton);
				add(gameOverState.exitButton);
			}
		}
	}

	public void setGameState(int state) {
		gameState = state;
	}
	
	public void checkCollisions() {
		//check the snake touches the bomb or the boundary
		running = (snake.checkCollisions() && bomb.checkCollisions());
		if(!running) {
			timer.stop();
		}
	}
	
	public void setHighestScore(int score) {
		if(delay == StartState.HARD_DELAY){
			if(score > highestScoreHard) {
				highestScoreHard = score;
				flag++;
			}
		}
		if(delay == StartState.NORMAL_DELAY){
			if(score > highestScoreNormal) {
				highestScoreNormal = score;
				flag++;
			}
		}
		if(delay == StartState.EASY_DELAY){
			if(score > highestScoreEasy) {
				highestScoreEasy = score;
				flag++;
			}
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(running) {
			snake.move(snake.getDirection());
			apple.checkApple();
			checkCollisions();
		}
		repaint();
	}
	
	public class MyKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(snake.getDirection()!= 'R') {
					snake.setDirection('L');
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(snake.getDirection()!= 'L') {
					snake.setDirection('R');
				}
				break;
			case KeyEvent.VK_UP:
				if(snake.getDirection()!= 'D') {
					snake.setDirection('U');
				}
				break;
			case KeyEvent.VK_DOWN:
				if(snake.getDirection()!= 'U') {
					snake.setDirection('D');
				}
				break;	
			}
		
		}
	}
}