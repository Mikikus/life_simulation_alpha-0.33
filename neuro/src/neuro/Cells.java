package neuro;

import java.util.Random;

public class Cells{
	private int dot = 30;
	private int cellX, cellY;
	private boolean live = false;
	//private boolean hunger = false;
	private int hungerPoints = 40;

	public Cells(){
		if(!live){
			createCell();
		}
	}

	public void randomMove(){
		int moveCase = new Random().nextInt(4);
		switch(moveCase){
			//up
			case 0:
				cellY -= dot;
				break;
			//left
			case 1:
				cellX -= dot;
				break;
			//down
			case 2:
				cellY += dot;
				break;
			//right
			case 3:
				cellX += dot;
				break;
		}
	}

	public void createCell(){
		live = true;
		int ix = new Random().nextInt(19) + 1;
		int iy = new Random().nextInt(19) + 1;
		cellX = ix*dot;
		cellY = iy*dot;
	}

	public int getCellX() {
		return cellX;
	}

	public void setCellX(int cellX) {
		this.cellX = cellX;
	}

	public int getCellY() {
		return cellY;
	}

	public void setCellY(int cellY) {
		this.cellY = cellY;
	}

	public boolean isLive(){
		return live;
	}
	
	public void setLive(boolean live){
		this.live = live;
	}

	public int getHungerPoints() {
		return hungerPoints;
	}

	public void setHungerPoints(int hungerPoints) {
		this.hungerPoints = hungerPoints;
	}
}
