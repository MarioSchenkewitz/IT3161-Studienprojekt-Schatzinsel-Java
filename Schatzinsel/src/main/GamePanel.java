package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import entity.Pirate;
import entity.Player;
import entity.Treasure;
import tile.Tile;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

	// Screen Settings
	final int originalTileSize = 16; // 16x16 Pixel
	final int scale = 3; // Skalierung
	public final int tileSize = originalTileSize * scale; // 48x48 Pixel Feldgröße

	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // 768 Pixel
	public final int screenHeight = tileSize * maxScreenRow; // 576 Pixel

	// World Settings
	public final int maxWorldCol = 16;// 32;
	public final int maxWorldRow = 12;// 26;
	public final int maxWorldWidth = tileSize * maxWorldCol;
	public final int maxWorldHeight = tileSize * maxWorldRow;

	// Frames Per Second
	int FPS;
	int pirateUpdateCounter = 0;

	KeyHandler keyHandler = new KeyHandler(this);
	Thread gameThread;
	public CollisionChecker collisionChecker = new CollisionChecker(this);

	TileManager tileM = new TileManager(this);

	// public Player player = new Player(this, keyHandler);
	Treasure treasure = new Treasure(this);
	int detectionRange;
	ArrayList<Pirate> pirates = new ArrayList<Pirate>();

	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);

		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}

	public void startGameThread() {
		// Settings
		pirates.add(new Pirate(this, treasure, 2));
		pirates.add(new Pirate(this, treasure, 2));
		pirates.add(new Pirate(this, treasure, 3));
			
		FPS = 60;
		treasure.visible = false;
		detectionRange = 2;
		
		
		gameThread = new Thread(this); // Diese Klasse (Spielfeld) wird als Konstruktor an den Thread uebergeben.
		gameThread.start(); // ruft automatisch run() auf
	}

	// Gameloop Sleep Method
//
//	@Override
//	public void run() {
//		
//		double drawInterval = 1000000000/FPS; // 1.000.000.000 nano seconds = 1 second => 0.01666 Hz
//		double nextDrawTime = System.nanoTime() + drawInterval;
//		
//		while(gameThread != null) {
//			
//			update();
//
//			repaint(); // calls paintComponent()
//			
//			try {
//				double remainingTime = nextDrawTime - System.nanoTime();
//				remainingTime = remainingTime/1000000;
//				
//				if(remainingTime < 0) {				
//					remainingTime = 0;
//				}
//				
//				Thread.sleep((long) remainingTime);
//				
//				nextDrawTime += drawInterval;
//				
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}	
//	}

	// Gameloop Delta Method
	@Override
	public void run() {
		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;

		while (gameThread != null) {
			currentTime = System.nanoTime();

			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;

			if (delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}

			if (timer >= 1000000000) {
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}

	public void update() {

		pirateUpdateCounter++;

		if (pirateUpdateCounter >= FPS) {
			for (Pirate pirate : pirates) {
				pirate.update();
			}
			pirateUpdateCounter = 0;
		}
		for (Pirate pirate : pirates) {
			double distance = Math.sqrt(Math.pow((pirate.worldX - treasure.worldX), 2) + 
					Math.pow((pirate.worldY - treasure.worldY), 2));
			if ( distance >= tileSize) {
				pirate.move();	
			}
			if (distance <= detectionRange*tileSize) {
				Pirate.schatzGefunden = true;
				treasure.visible = true;
			}
		}

		// player.update();

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g); // parent class (JPanel) calls paintComponent
		Graphics2D g2 = (Graphics2D) g;

		tileM.draw(g2);
		// player.draw(g2);
		if (treasure.visible == true) {
			treasure.draw(g2);
		}
		for (Pirate pirate : pirates) {
			pirate.draw(g2);
		}
		g2.dispose();
	}
}
