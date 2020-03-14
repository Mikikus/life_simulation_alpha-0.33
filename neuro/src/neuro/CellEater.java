package neuro;

import java.util.Random;

public class CellEater {
	private int eaterX;
	private int eaterY;
	private int dot = 30;
	private boolean live = false;
	private int eaterHungerPoints = 40;

	public CellEater(){
		if(!live){
			createEater();
		}
	}

	public void createEater(){
		live = true;
		int ix = new Random().nextInt(19) + 1;
		int iy = new Random().nextInt(19) + 1;
		eaterX = ix*dot;
		eaterY = iy*dot;
	}

	public void randomMove(){
		int moveCase = new Random().nextInt(4);
		switch(moveCase){
			//up
			case 0:
				eaterY -= dot;
				break;
			//left
			case 1:
				eaterX -= dot;
				break;
			//down
			case 2:
				eaterY += dot;
				break;
			//right
			case 3:
				eaterX += dot;
				break;
		}
	}

	public int getEaterX() {
		return eaterX;
	}

	public void setEaterX(int eaterX) {
		this.eaterX = eaterX;
	}

	public int getEaterY() {
		return eaterY;
	}

	public void setEaterY(int eaterY) {
		this.eaterY = eaterY;
	}

	public int getDot() {
		return dot;
	}

	public void setDot(int dot) {
		this.dot = dot;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public int getEaterHungerPoints() {
		return eaterHungerPoints;
	}

	public void setEaterHungerPoints(int eaterHungerPoints) {
		this.eaterHungerPoints = eaterHungerPoints;
	}
}
