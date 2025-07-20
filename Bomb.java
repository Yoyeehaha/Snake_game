
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Bomb {
    private int bombX;
    private int bombY;
    private BufferedImage bombImage;
    private Snake snake;
    private Random random;

    public Bomb(Snake snake) {
        random = new Random();
        this.snake = snake;
        try {
			bombImage = ImageIO.read(new File("picture\\bomb.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void draw(Graphics g) {
        g.drawImage(bombImage, bombX, bombY, null);
    }

    public void newBomb() {
        bombX = random.nextInt(GamePanel.SCREEN_WIDTH/GamePanel.UNIT_SIZE) * GamePanel.UNIT_SIZE;
        bombY = random.nextInt(GamePanel.SCREEN_HEIGHT/GamePanel.UNIT_SIZE-1) * GamePanel.UNIT_SIZE + GamePanel.UNIT_SIZE;

        if(snake.isOnTheSnake(bombX, bombY) || snake.willBeTouchedBySnake(bombY, bombX)) {
            newBomb();
        }
    }

    public boolean checkCollisions() {
		//check if snake touchs the bomb
		if(snake.getSnakeX(0)==bombX && snake.getSnakeY(0)==bombY){
			return false;
		}
        return true;		
	}

    public int getBombX() { return bombX; }
    public int getBombY() { return bombY; }

}