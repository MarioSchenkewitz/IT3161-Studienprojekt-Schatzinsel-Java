package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

public class UI {
	GamePanel gp;
	Graphics2D g2;
	Font arial_40;
	public boolean messageOn = false;
	public String message = "";
	boolean gameFinished = false;
	public int commandNum = 0;

	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");

	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Arial", Font.PLAIN, 40);

	}

	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}

	public void draw(Graphics2D g2) {

		this.g2 = g2;

		g2.setColor(Color.black);

		// Title State
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		
		if(gp.gameState == gp.helpState) {
			drawHelpScreen();
		}
		
		// Play State
		if (gp.gameState == gp.playState) {
			if (gameFinished == true) {
				if (messageOn == true) {
					g2.setFont(arial_40);
					g2.drawString("Schatz gefunden!", 50, 530 + arial_40.getSize());
				}
			} else {
				playTime += (double) 1 / 60;
			}
		}

		// Pause State
		if (gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		
		
		if (gp.gameState ==  gp.pauseState || gp.gameState == gp.playState) {
			g2.setFont(arial_40);
			g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 11, arial_40.getSize());
		}
		
	}

	public void drawHelpScreen() {
		g2.setColor(new Color(0, 0, 0));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		// Title Name
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
		String text = "Hilfe";
		int x = getXForCenteredText(text);
		int y = gp.tileSize*2;
		
		// shadow
		g2.setColor(Color.gray);
		g2.drawString(text, x+5, y+5);
		// main color
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,20));
		text = "Dieses Programm wurde als Studienprojekt der TIT20B entwickeln."
				+ "\nEs Handelt sich um den Prototypen des Spiels in der Sprache Java."
				+ "\n"
				+ "\nIn diesem Spiel landen 3 Piraten zufällig auf einer Insel,"
				+ "\nauf der ein Schatz versteckt ist."
				+ "\nSie suchen zufällig und wenn einer von ihnen den Schatz findet, "
				+ "\ndann ruft derjenige die anderen herbei."
				+ "\n"
				+ "\nTastenbelegung:"
				+ "\nEscape - Spiel beenden"
				+ "\nP - Pause";
		x = gp.tileSize;
		y = gp.tileSize*3;
		for(String line : text.split("\n")) {
			g2.drawString(line, x, y);
			y+= 30;
		}
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
		text = "Zurück";
		x = getXForCenteredText(text);
		y = gp.tileSize*10;
		g2.drawString(text, x, y);
		if (commandNum == 0) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
		text = "Beenden";
		x = getXForCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if (commandNum == 1) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
	}

	public void drawTitleScreen() {
		g2.setColor(new Color(0, 0, 0));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		// Title Name
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
		String text = "Schatzinsel";
		int x = getXForCenteredText(text);
		int y = gp.tileSize*3;
		
		
		// shadow
		g2.setColor(Color.gray);
		g2.drawString(text, x+5, y+5);
		// main color
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		x = gp.screenWidth/2 - (gp.tileSize*2)/2;
		y += gp.tileSize;
		
		g2.drawImage(gp.defaultPirate.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
		g2.drawImage(gp.treasure.down1, x, y+gp.tileSize, gp.tileSize*2, gp.tileSize*2, null);
		
		// Menu
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
		text = "Neues Spiel";
		x = getXForCenteredText(text);
		y += gp.tileSize*4;
		g2.drawString(text, x, y);
		if (commandNum == 0) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		if(gp.treasure.visible == true) {
			text = "Schatz Sichtbarkeit: An";
		} else if (gp.treasure.visible == false) {
			text = "Schatz Sichtbarkeit: Aus";
		}
		x = getXForCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if (commandNum == 1) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		text = "Hilfe";
		x = getXForCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if (commandNum == 2) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		text = "Beenden";
		x = getXForCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if (commandNum == 3) {
			g2.drawString(">", x-gp.tileSize, y);
		}
	}

	public void drawPauseScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		String text = "PAUSED";
		int x = getXForCenteredText(text);
		int y = gp.screenHeight / 2;

		g2.drawString(text, x, y);
	}

	public int getXForCenteredText(String text) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth / 2 - length / 2;
		return x;
	}

}
