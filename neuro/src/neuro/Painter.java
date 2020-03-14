package neuro;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Painter extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Timer timer;
	int dot = 30;
	Food food = new Food();
	//Cells cell = new Cells();
	private Image foodImage;
	private Image cellImage;
	private Image eaterImage;
	ArrayList<Cells> cellList = new ArrayList<>();
	ArrayList<CellEater> eaterList = new ArrayList<>();

	public Painter(){
		cellList.add(new Cells());
		eaterList.add(new CellEater());
		timer = new Timer(200, this);
		timer.start();
		loadImages();
	}

	public void loadImages(){
		foodImage = new ImageIcon("C:\\Users\\Mikel\\workspace\\neuro\\src\\neuro\\food.png").getImage();
		cellImage = new ImageIcon("C:\\Users\\Mikel\\workspace\\neuro\\src\\neuro\\yellow.png").getImage();
		eaterImage = new ImageIcon("C:\\Users\\Mikel\\workspace\\neuro\\src\\neuro\\eater.png").getImage();
	}

	public void isCellLive(){
		for(int i = 0; i < cellList.size(); i++){
			if(cellList.get(i).getCellX() > 590 || cellList.get(i).getCellY() > 590){
				cellList.get(i).setLive(false);
			}
			if(cellList.get(i).getHungerPoints() <= 0){
				cellList.get(i).setLive(false);
				System.out.println("OH");
			}
			if(cellList.get(i).getHungerPoints() > 60){
				cellList.get(i).setHungerPoints(cellList.get(i).getHungerPoints() - 50);
				cellList.add(new Cells());
			}
		}
	}

	public void cellWithFoodCollision(){
		for(int i = 0; i < cellList.size(); i++){
			int cx = cellList.get(i).getCellX(); int cy = cellList.get(i).getCellY();
			int fx = food.getFoodX(); int fy = food.getFoodY();
			if(cx == fx && cy == fy){
				//System.out.println("OH");
				cellList.get(i).setHungerPoints(cellList.get(i).getHungerPoints() + 40);
				food.setExistance(false);
				food.createFood();
			}
			repaint();
		}
	}

	public void isEaterLive(){
		for(int i = 0; i < eaterList.size(); i++){
		//if(eaterList.get(i).getEaterX() > 590 || eaterList.get(i).getEaterY() > 590){
			//eaterList.get(i).setLive(false);}

			//if(eaterList.get(i).getEaterHungerPoints() <= 0){
				//eaterList.get(i).setLive(false);
			//}
			if(eaterList.get(i).getEaterHungerPoints() > 45){
				eaterList.get(i).setEaterHungerPoints(eaterList.get(i).getEaterHungerPoints() - 4);
				//eaterList.add(new CellEater());
			}
		}
	}

	public void eaterWithCellCollision(){
		for(int i = 0; i < eaterList.size(); i++){
			for(int h = 0; h < cellList.size(); h++){
				int cx = cellList.get(h).getCellX()  ; int cy = cellList.get(h).getCellY();
				int ex = eaterList.get(i).getEaterX(); int ey = eaterList.get(i).getEaterY();
				if(cx == ex && cy == ey){
					cellList.get(h).setLive(false);
					eaterList.get(i).setEaterHungerPoints(eaterList.get(i).getEaterHungerPoints() + 30);
				}
			}
		}
	}

	public void installCellEyes(){
		for(int j = 0; j < cellList.size(); j++){
			int viewFieldBeginX; int viewFieldEndX;
			int viewFieldBeginY; int viewFieldEndY;
			int i = new Random().nextInt(2);
			viewFieldBeginX = cellList.get(j).getCellX() - 15*dot;
			viewFieldEndX = cellList.get(j).getCellX() + 15*dot;
			viewFieldBeginY = cellList.get(j).getCellY() - 15*dot;
			viewFieldEndY = cellList.get(j).getCellY() + 15*dot;
			//move up or right
			if(food.getFoodX() > (cellList.get(j).getCellX() + dot/2) && food.getFoodX() < viewFieldEndX &&
			   food.getFoodY() < (cellList.get(j).getCellY() + dot/2) && food.getFoodY() > viewFieldBeginY){
				switch(i){
				case 0:
					cellList.get(j).setCellY(cellList.get(j).getCellY() - dot);
					break;
				case 1:
					cellList.get(j).setCellX(cellList.get(j).getCellX() + dot);
					break;
				}
			} //left or up
			else if(food.getFoodX() < (cellList.get(j).getCellX() + dot/2) && food.getFoodX() > viewFieldBeginX &&
					food.getFoodY() < (cellList.get(j).getCellY() + dot/2) && food.getFoodY() > viewFieldBeginY){
				switch(i){
				case 0:
					cellList.get(j).setCellY(cellList.get(j).getCellY() - dot);
					break;
				case 1:
					cellList.get(j).setCellX(cellList.get(j).getCellX() - dot);
					break;
				}
			} //down and right
			else if(food.getFoodX() > (cellList.get(j).getCellX() + dot/2) && food.getFoodX() < viewFieldEndX &&
					food.getFoodY() > (cellList.get(j).getCellY() + dot/2) && food.getFoodY() < viewFieldEndY){
				switch(i){
				case 0:
					cellList.get(j).setCellY(cellList.get(j).getCellY() + dot);
					break;
				case 1:
					cellList.get(j).setCellX(cellList.get(j).getCellX() + dot);
					break;
					}
			} //Down and left
			else if(food.getFoodX() < (cellList.get(j).getCellX() + dot/2) && food.getFoodX() > viewFieldBeginX &&
					   food.getFoodY() > (cellList.get(j).getCellY() + dot/2) && food.getFoodY() < viewFieldEndY){
				switch(i){
				case 0:
					cellList.get(j).setCellY(cellList.get(j).getCellY() + dot);
					break;
				case 1:
					cellList.get(j).setCellX(cellList.get(j).getCellX() - dot);
					break;
				}
			} else{
				cellList.get(j).randomMove();
			}
		}
	}

	public void installEaterEyes(){
		for(int j = 0; j < eaterList.size(); j++){
			for(int h = 0; h < cellList.size(); h++){
				int viewFieldBeginX; int viewFieldEndX;
				int viewFieldBeginY; int viewFieldEndY;
				int i = new Random().nextInt(2);
				viewFieldBeginX = eaterList.get(j).getEaterX() - 20*dot;
				viewFieldEndX = eaterList.get(j).getEaterX() + 20*dot;
				viewFieldBeginY = eaterList.get(j).getEaterY() - 20*dot;
				viewFieldEndY = eaterList.get(j).getEaterY() + 20*dot;
				//move up or right
				if(cellList.get(h).getCellX() > (eaterList.get(j).getEaterX() + dot/2) && cellList.get(h).getCellX() < viewFieldEndX &&
						cellList.get(h).getCellY() < (eaterList.get(j).getEaterY() + dot/2) && cellList.get(h).getCellY() > viewFieldBeginY){
					switch(i){
					case 0:
						eaterList.get(j).setEaterY(eaterList.get(j).getEaterY() - dot);
						break;
					case 1:
						eaterList.get(j).setEaterX(eaterList.get(j).getEaterX() + dot);
						break;
					}
				} //left or up
				else if(cellList.get(h).getCellX() < (eaterList.get(j).getEaterX() + dot/2) && cellList.get(h).getCellX() > viewFieldBeginX &&
						cellList.get(h).getCellY() < (eaterList.get(j).getEaterY() + dot/2) && cellList.get(h).getCellY() > viewFieldBeginY){
					switch(i){
					case 0:
						eaterList.get(j).setEaterY(eaterList.get(j).getEaterY() - dot);
						break;
					case 1:
						eaterList.get(j).setEaterX(eaterList.get(j).getEaterX() - dot);
						break;
					}
				} //down and right
				else if(cellList.get(h).getCellX() > (eaterList.get(j).getEaterX() + dot/2) && cellList.get(h).getCellX() < viewFieldEndX &&
						cellList.get(h).getCellY() > (eaterList.get(j).getEaterY() + dot/2) && cellList.get(h).getCellY() < viewFieldEndY){
					switch(i){
					case 0:
						eaterList.get(j).setEaterY(eaterList.get(j).getEaterY() + dot);
						break;
					case 1:
						eaterList.get(j).setEaterX(eaterList.get(j).getEaterX() + dot);
						break;
						}
				} //Down and left
				else if(cellList.get(h).getCellX() < (eaterList.get(j).getEaterX() + dot/2) && cellList.get(h).getCellX() > viewFieldBeginX &&
						cellList.get(h).getCellY() > (eaterList.get(j).getEaterY() + dot/2) && cellList.get(h).getCellY() < viewFieldEndY){
					switch(i){
					case 0:
						eaterList.get(j).setEaterY(eaterList.get(j).getEaterY() + dot);
						break;
					case 1:
						eaterList.get(j).setEaterX(eaterList.get(j).getEaterX() - dot);
						break;
					}
				} else{
					eaterList.get(j).randomMove();
				}
			}
		}
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(food.isExistance()){
			g.drawImage(foodImage, food.getFoodX(), food.getFoodY(), this);
		}
		for(int i = 0; i < cellList.size(); i++){
			if(cellList.get(i).isLive()){
				g.drawImage(cellImage, cellList.get(i).getCellX(), cellList.get(i).getCellY(), this);
			}
		}
		for(int i = 0; i < eaterList.size(); i++){
			if(eaterList.get(i).isLive()){
				g.drawImage(eaterImage, eaterList.get(i).getEaterX(), eaterList.get(i).getEaterY(), this);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < cellList.size(); i++){
			if(cellList.get(i).isLive()){
				installCellEyes();
				cellWithFoodCollision();
				repaint();
				isCellLive();
				cellList.get(i).setHungerPoints(cellList.get(i).getHungerPoints() - 1);
			}
			repaint();
		}
		for(int h = 0; h < eaterList.size(); h++){
			if(eaterList.get(h).isLive()){
				isEaterLive();
				installEaterEyes();
				eaterWithCellCollision();
				//eaterList.get(h).setEaterHungerPoints(eaterList.get(h).getEaterHungerPoints() - 1);
			}
			repaint();
		}
	}
}
