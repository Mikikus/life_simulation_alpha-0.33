package neuro;

import java.util.Random;

public class Food{
	private boolean existance;
	private int foodX;
	private int foodY;
	private int dot = 30;

	public Food(){
		createFood();
	}

	public void createFood(){
		existance = true;
		foodX = (new Random().nextInt(18) + 1) * dot;
		foodY = (new Random().nextInt(18) + 1) * dot;
	}

	public boolean isExistance() {
		return existance;
	}

	public void setExistance(boolean existance) {
		this.existance = existance;
	}

	public int getFoodX() {
		return foodX;
	}

	public void setFoodX(int foodX) {
		this.foodX = foodX;
	}

	public int getFoodY() {
		return foodY;
	}

	public void setFoodY(int foodY) {
		this.foodY = foodY;
	}
}
