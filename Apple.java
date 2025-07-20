
import java.awt.Graphics;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import java.io.File;

import java.awt.image.BufferedImage;

public class Apple {
	private int appleX;
	private int appleY;
	private BufferedImage appleImage;
    private int applesEaten =0;
    private Snake snake;
    private Bomb bomb;
    private Random random;

    public Apple(Snake snake, Bomb bomb) {
        random = new Random();
        this.snake = snake;
        this.bomb = bomb;
        try {
			appleImage = ImageIO.read(new File("picture\\apple.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void draw(Graphics g) {
        g.drawImage(appleImage, appleX, appleY, null);
    }

    public void checkApple() {
		if((snake.getSnakeX(0)==appleX)&&(snake.getSnakeY(0)==appleY)) {
			snake.setBodyParts(1);;
			applesEaten++;
			newApple();
		}
	}

    public void newApple() {
        bomb.newBomb();
        appleX = random.nextInt(GamePanel.SCREEN_WIDTH/GamePanel.UNIT_SIZE) * GamePanel.UNIT_SIZE;
        appleY = random.nextInt(GamePanel.SCREEN_HEIGHT/GamePanel.UNIT_SIZE-1) * GamePanel.UNIT_SIZE + GamePanel.UNIT_SIZE;

        if(snake.isOnTheSnake(appleX, appleY)) {
            newApple();
        }

        if(bomb.getBombX() == appleX && bomb.getBombY() == appleY) {
            newApple();
        }
    }

    public int getApplesEaten() {
        return applesEaten;
    }
}