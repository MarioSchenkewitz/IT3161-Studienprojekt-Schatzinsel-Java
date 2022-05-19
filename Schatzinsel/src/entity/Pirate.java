package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Pirate extends Entity {
	Treasure treasure;
	Random rand = new Random();
	int randomNumber;
	public static boolean schatzGefunden = false;
	public boolean atTreasure = false;

	public Pirate(GamePanel gp, Treasure treasure, int speed) {
		super(gp);
		this.treasure = treasure;

		solidArea = new Rectangle(16, 25, gp.tileSize - 16, gp.tileSize - 25);

		setDefaultValues(speed);
		getPirateImage();
	}

	public void setDefaultValues(int speed) {
		worldX = 2*gp.tileSize + gp.tileSize * rand.nextInt(12);
		worldY = 2*gp.tileSize + gp.tileSize * rand.nextInt(8);
		this.speed = speed;
		direction = "down";
	}

	public void getPirateImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/pirate/pirate_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/pirate/pirate_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/pirate/pirate_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/pirate/pirate_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/pirate/pirate_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/pirate/pirate_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/pirate/pirate_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/pirate/pirate_right_2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void updateDirection() {
		if (schatzGefunden == false) {
			randomNumber = rand.nextInt(4);

			switch (randomNumber) {
			case 0:
				direction = "up";
				break;
			case 1:
				direction = "down";
				break;
			case 2:
				direction = "left";
				break;
			case 3:
				direction = "right";
				break;
			}
		} else {
			if (treasure.worldX + 0.5 * gp.tileSize < this.worldX) {
				direction = "left";				
			} else if (treasure.worldX - 0.5 * gp.tileSize > this.worldX) {
				direction = "right";
			}

			if (treasure.worldY  + 0.5 * gp.tileSize < this.worldY) {
				direction = "up";
			} else if (treasure.worldY - 0.5 * gp.tileSize > this.worldY) {
				direction = "down";
			}		
		}
		
		gp.collisionChecker.checkTile(this);
		while(collisionOn == true) {
			switch (direction) {
			case "up":
				direction = "right";
				break;
			case "down":
				direction = "left";
				break;
			case "left":
				direction = "up";
				break;
			case "right":
				direction = "down";
				break;
			}
			gp.collisionChecker.checkTile(this);
		}
		
	}

	public void update() {
		updateDirection();
	}

	public void move() {
		collisionOn = false;
		gp.collisionChecker.checkTile(this);
		if (collisionOn == false) {
			switch (direction) {
			case "up":
				worldY -= speed;
				break;
			case "down":
				worldY += speed;
				break;
			case "left":
				worldX -= speed;
				break;
			case "right":
				worldX += speed;
				break;
			}
		}

		spriteCounter++;
		if (spriteCounter > 12) {
			if (spriteNum == 1) {
				spriteNum = 2;
			} else if (spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}

	}

	public void draw(Graphics2D g2) {
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);

		BufferedImage image = null;

		switch (direction) {
		case "up":
			if (spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
			break;
		}

		g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
	}
}
