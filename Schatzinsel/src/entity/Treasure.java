package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Treasure extends Entity {
	Random rand = new Random();
	int randomNumber;
	
	public boolean visible;

	public Treasure(GamePanel gp) {
		super(gp);
		setDefaultValues();
		getTreasureImage();
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize + gp.tileSize * rand.nextInt(14);
		worldY = gp.tileSize + gp.tileSize * rand.nextInt(10);
		visible = true;
	}
	
	public void getTreasureImage() {
		try {
			down1 = ImageIO.read(getClass().getResourceAsStream("/treasure/treasure_down_1.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = down1;
		
		g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
	}
}
