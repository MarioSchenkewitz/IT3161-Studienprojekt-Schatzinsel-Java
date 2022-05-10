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

		if (gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		g2.setFont(arial_40);
		g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 11, arial_40.getSize());
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
