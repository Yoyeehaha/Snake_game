
import java.awt.Color;
import java.awt.Graphics;

public class Snake {
    private int bodyParts;
    private char direction;
    private final int x[];
	private final int y[];

    public Snake() {
        bodyParts = 6;
        direction = 'R';
        x = new int[GamePanel.GAME_UNITS];
        y = new int[GamePanel.GAME_UNITS];
        y[0] = GamePanel.UNIT_SIZE;
    }

    public void draw(Graphics g){
        for(int i=0;i<bodyParts;i++) {
            if(i%7==0) {
                g.setColor(Color.red);
                g.fillRect(x[i],y[i], GamePanel.UNIT_SIZE, GamePanel.UNIT_SIZE);
            } else if(i%7==1) {
                g.setColor(Color.orange);
                g.fillRect(x[i],y[i], GamePanel.UNIT_SIZE, GamePanel.UNIT_SIZE);
            } else if(i%7==2) {
                g.setColor(Color.yellow);
                g.fillRect(x[i],y[i], GamePanel.UNIT_SIZE, GamePanel.UNIT_SIZE);
            } else if(i%7==3) {
                g.setColor(Color.green);
                g.fillRect(x[i],y[i], GamePanel.UNIT_SIZE, GamePanel.UNIT_SIZE);
            } else if(i%7==4) {
                g.setColor(Color.cyan);
                g.fillRect(x[i],y[i], GamePanel.UNIT_SIZE, GamePanel.UNIT_SIZE);
            } else if(i%7==5) {
                g.setColor(Color.blue);
                g.fillRect(x[i],y[i], GamePanel.UNIT_SIZE, GamePanel.UNIT_SIZE);
            } else if(i%7==6) {
                g.setColor(Color.pink);
                g.fillRect(x[i],y[i], GamePanel.UNIT_SIZE, GamePanel.UNIT_SIZE);
            }
        }
    }

    //public void update(){}

    public void move(char direction) {
		for(int i=bodyParts;i>0;i--) { 		
			x[i]=x[i-1];
			y[i]=y[i-1];
		}
		switch(direction) {
            case 'U':
                y[0] = y[0] - GamePanel.UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + GamePanel.UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - GamePanel.UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + GamePanel.UNIT_SIZE;
                break;
		}
	}

    public boolean isOnTheSnake(int itemX, int itemY) {
        for(int i=0; i<bodyParts; i++) {
            if(itemX== x[i] && itemY==y[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean willBeTouchedBySnake(int itemX, int itemY) {
        int nextX = x[0];
        int nextY = y[0];
        switch(direction) {
            case 'U':
                nextY = y[0] - GamePanel.UNIT_SIZE;
                break;
            case 'D':
                nextY = y[0] + GamePanel.UNIT_SIZE;
                break;
            case 'L':
                nextX = x[0] - GamePanel.UNIT_SIZE;
                break;
            case 'R':
                nextX = x[0] + GamePanel.UNIT_SIZE;
                break;
		}
        if(itemX==nextX && itemY==nextY) {
            return true;
        }
        return false;
    }

    public boolean checkCollisions() {
        //checks if head collides with body
		for(int i = bodyParts;i>0;i--) {
			if((x[0] == x[i])&& (y[0] == y[i])) {
				return false;
			}
		}	
		//check if head touches left border
		if(x[0]<0) {
			return false;
		}
		//check if head touches right border
		if(x[0]> GamePanel.SCREEN_WIDTH-1) {
			return false;
		}
		//check if head touches top border
		if(y[0]<GamePanel.UNIT_SIZE) {
			return false;
		}
		//check if head touches bottom border
		if(y[0]> GamePanel.SCREEN_HEIGHT-1) {
			return false;
		}
        return true;
    }

    public char getDirection() { return direction; }
    public void setDirection(char direction) { this.direction = direction; }
    public int getBodyParts() { return bodyParts; }
    public void setBodyParts(int i) { bodyParts += i; }
    public int getSnakeX(int index) { return x[index]; }
    public int getSnakeY(int index) { return y[index]; }
    
}