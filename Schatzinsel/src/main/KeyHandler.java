package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		
		/*
		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}

		if(code == KeyEvent.VK_S) {
			downPressed = true;
		}

		if(code == KeyEvent.VK_A) {
			leftPressed = true;
		}

		if(code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		*/
		
		if(gp.gameState == gp.titleState) {
			if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 3;
				}
			}

			if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 3) {
					gp.ui.commandNum = 0;
				}
			}
			
			if(code == KeyEvent.VK_ENTER) {
				switch(gp.ui.commandNum) {
				case 0:
					if (gp.gameState == gp.titleState) {
						gp.gameState = gp.playState;
					}					
					break;
				case 1:
					if(gp.treasure.visible == true) {
						gp.treasure.visible = false;
					} else if (gp.treasure.visible == false) {
						gp.treasure.visible = true;
					}
					break;
				case 2:
					gp.gameState = gp.helpState;
					break;
				case 3:
					System.exit(0);
					break;
				}
			}
		}
		
		if(gp.gameState == gp.helpState) {
			if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 1;
				}
			}

			if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 1) {
					gp.ui.commandNum = 0;
				}
			}
			
			if(code == KeyEvent.VK_ENTER) {
				switch(gp.ui.commandNum) {
				case 0:
					gp.gameState = gp.titleState;
					break;
				case 1:
					System.exit(0);
					break;
				}
			}
		}
		
		/*
		 if(gp.gameState == gp.playState) {
			if(code == KeyEvent.VK_P) {
				gp.gameState = gp.pauseState;
			}
		}else if(gp.gameState == gp.pauseState) {
			if(code == KeyEvent.VK_P) {
				gp.gameState = gp.playState;
			}
		}
		 */
		
		if(code == KeyEvent.VK_P) {
			if(gp.gameState == gp.playState) {
				gp.gameState = gp.pauseState;
			}else if(gp.gameState == gp.pauseState) {
				gp.gameState = gp.playState;
			}
		}
		

		if(code == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		/*
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}

		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}

		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}

		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		
		*/
		if(gp.gameState == gp.helpState) {
			if(code == KeyEvent.VK_ENTER) {
				gp.ui.commandNum = 0;
			}
		}
		
	}

}
