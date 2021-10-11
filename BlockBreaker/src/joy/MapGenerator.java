package joy;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
public int map[][];//contains all the bricks
public  int brickWidth;
public int brickHeight;


 public MapGenerator(int row, int col ) {
	map =new int[row][col];
	
	for(int i = 0; i<map.length; i++) {
		for(int j = 0; j< map[0].length; j++) {
			map[i][j]= 1;//1 shows brick can't intercept ball
		}
	}
	//set brick
	brickWidth = 540/col;
	brickHeight = 150/row;
 }
 public void paint(Graphics2D g) {

		for(int i = 0; i<map.length; i++) {
			for(int j = 0; j<map[1].length ; j++) {
		if(map[i][j]>0) {
			g.setColor(Color.white);
			g.fillRect(j*brickWidth+80,i*brickHeight + 50,brickWidth,brickHeight);
			//setting the inner boxes 
			g.setStroke(new BasicStroke(3));
			g.setColor(Color. black);
			g.drawRect(j*brickWidth +80, i*brickHeight +50, brickWidth, brickHeight);
			
		}
			}
		}
 }
 //intersection between ball and bricks
 public void setBrickValue(int value, int row,int col) {
	 map[row][col] = value;
 }
 
 
}
