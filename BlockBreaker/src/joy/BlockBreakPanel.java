package joy;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class BlockBreakPanel extends JPanel implements KeyListener,ActionListener {
	private boolean play =false ;
	private int score = 0;
	private int totalBricks = 21;
	private Timer timer;
	private int delay = 8;
	private int playerX =310;
	private int playerY = 120;
	private int ballXdir = -1;
	private int ballYdir = -2;
	private int ballposY =350;
	private int ballposX =120;
protected MapGenerator map;
	
	public BlockBreakPanel(){
		map = new MapGenerator(3,7);
		timer = new Timer(delay, this);
		 timer.start();
		
		
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
	}
	public void paint(Graphics g) {
		//background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692,592);
		//drawing map
		map.paint((Graphics2D)g);
		
		//BORDERS	
g.setColor(Color.yellow);		
		g.fillRect(0, 0, 3,592);
		g.fillRect(0, 0, 692,3);
		g.fillRect(691, 0, 3,592);
		// score
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.PLAIN, 25));	
g.drawString(""+score,590,30);
		
		
	//the paddle		
		//background
		g.setColor(Color.green);		
		g.fillRect(playerX, 550, 100,8);		
		//BALL	
	g.setColor(Color.YELLOW);		
		g.fillOval(ballposX, ballposY, 20, 20);	
		//if we finish the game 
		
		if(totalBricks<=0) {
			play = false;
			ballYdir = 0;
			ballXdir = 0;
			g.setColor(Color.WHITE);
			g.setFont(new Font("serif", Font.PLAIN, 35));
			g.drawString("WINNER",260,300);
			
			g.setFont(new Font("serif", Font.PLAIN, 35));
			g.drawString("Press Enter to restart",230,350);
		}
		
		
		// game over if ball falls down
		if(ballposY >570) {
			play = false;
			ballYdir = 0;
			ballXdir = 0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.PLAIN, 35));
			g.drawString("GAME OVER,Scores",190,300);
			
			g.setFont(new Font("serif", Font.PLAIN, 35));
			g.drawString("Press Enter to restart",230,350);
		}
		
		g.dispose();
		
		}
	public void actionPerformed(ActionEvent e) {
timer.start();
if(play) {
	//Detecting intersection with paddle 
	//create rectangle
	if(new Rectangle(ballposX, ballposY,20,20).intersects (new Rectangle(playerX, 550,100,8))) {
		ballYdir = -ballYdir;
	}	
	//intersection between ball and bricks
	A:for(int i=0; i< map.map.length; i++) {
		for(int j= 0; j< map.map[0].length; j++) {
			if(map.map[i][j]>0 ){
				
	int brickX= j*map.brickWidth+80;
	int brickY = i*map.brickHeight+50;
	int brickWidth = map.brickWidth;
	int brickHeight = map.brickHeight;
				
	//rectangle around individual brick
Rectangle rect= new Rectangle(brickX,brickY,brickWidth,brickHeight);
Rectangle ballRect = new Rectangle(ballposX,ballposY,20,20);	
Rectangle brickRect = rect;

if(ballRect.intersects(brickRect)) {
	map.setBrickValue(0,i,j);
	totalBricks --;
	score +=5;
	// code for left and right intersection
	if(ballposX +19<=brickRect.x || ballposX + 1>= brickRect.x+brickRect.width ) {
		ballXdir = -ballXdir;
	}else {
		ballYdir =-ballYdir;
	}
	break A;	//removes inner loop to outer loop	
}
	
			
				
			}
		}
	}
	
	
	//checks  if ball is touching the top left or right
	ballposX += ballXdir;
	ballposY += ballYdir;
	if(ballposX < 0) { //LEFT border
		ballXdir = -ballXdir;
	}
	// top
	if(ballposY < 0) {
		ballYdir = -ballYdir;
	}
	//right
	if(ballposX > 670) {
		ballXdir = -ballXdir;
	}
	
}
 repaint();
	}

	

	public void keyTyped(KeyEvent e) {
	
		
	}



	public void keyReleased(KeyEvent e) {
	
		
	}

	public void keyPressed(KeyEvent e) {
	
		
        if(e.getKeyCode() == KeyEvent.VK_RIGHT ) {
        	if(playerX >=600){
        		playerX = 600;
        	}else {
        		moveRight();
        	}
		}
        if(e.getKeyCode() == KeyEvent.VK_LEFT ) {
        	if(playerX <10){
        		playerX = 10;
        	}else {
        		moveleft();
        	}
		}
       
	
		// for the enter to start game
        if(e.getKeyCode()== KeyEvent.VK_ENTER) {
        	if(!play) {
        		play = true;
        		ballposX =120;
        		ballposY = 350;
        		ballXdir = -1;
        		ballYdir = -2;
        		playerX = 310;
        		score = 0;
        		totalBricks = 21;
        		map = new MapGenerator(3,7);
        		repaint();
        	}
        }
        
	}
	
	
	public void moveRight() {
	play = true;
	playerX += 20;
	}
	public void moveleft() {
		play = true;
		playerX -= 20;
		}



}
