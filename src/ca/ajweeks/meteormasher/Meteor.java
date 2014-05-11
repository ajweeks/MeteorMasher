package ca.ajweeks.meteormasher;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import ca.ajweeks.meteormasher.input.Input;
import ca.ajweeks.meteormasher.state.GameState;

public class Meteor {
	//Types
	public final static int EXPLODEABLE = 0;
	public final static int DRAGGABLE = 1;
	public final static int PWRUP_NUKE = 2; //Clears screen
	public final static int PWRUP_EXPLODE = 3;
	
	//Sizes
	public static final int LARGE = 234;
	public static final int MEDIUM = (LARGE / 3) * 2;
	public static final int SMALL = (LARGE / 3) * 1;
	
	public final int type;
	public int size;
	public double x, y, xd, yd;
	public boolean toBeDestroyed = false; //Set to true when this object has been exploded
	
	//private double friction = 0.006; //amount to slow down every update
	
	public void render(Graphics g) {
		switch (type) {
		case EXPLODEABLE:
		case DRAGGABLE:
			switch (size) {
			case LARGE:
				g.drawImage(Game.meteorL, (int) x, (int) y, null);
				break;
			case MEDIUM:
				g.drawImage(Game.meteorM, (int) x, (int) y, null);
				break;
			case SMALL:
				g.drawImage(Game.meteorS, (int) x, (int) y, null);
				break;
			}
			break;
		case PWRUP_EXPLODE:
			g.drawImage(Game.meteorExplode, (int) x, (int) y, null);
			break;
		case PWRUP_NUKE:
			g.drawImage(Game.meteorNuke, (int) x, (int) y, null);
			break;
		}
	}
	
	public void update(Input input) {
		x += xd;
		y += yd;
		
		//Border collisions
		if (x + size > Game.SIZE.width) {
			xd = -xd;
			x = Game.SIZE.width - size;
		}
		if (x < 0) {
			xd = -xd;
			x = 0;
		}
		if (y + size > Game.SIZE.height) {
			yd = -yd;
			y = Game.SIZE.height - size;
		}
		if (y < 0) {
			yd = -yd;
			y = 0;
		}
		
		//xVelocity -= friction;
		//yVelocity -= friction;
		
		switch (type) {
		case EXPLODEABLE:
			if (mouseIsInMeteor(input) && input.leftMouseDown) {
				explode();
			}
			break;
		case DRAGGABLE:
			//TODO: add draggable meteors
			break;
		case PWRUP_NUKE:
			if (mouseIsInMeteor(input) && input.leftMouseDown) {
				GameState.nuke();
			}
			break;
		case PWRUP_EXPLODE:
			if (mouseIsInMeteor(input) && input.leftMouseDown) {
				toBeDestroyed = true;
			}
			break;
		}
	}
	
	public void checkCollisions(ArrayList<Meteor> meteors, int thisIndex) {
		Rectangle me = new Rectangle((int) x, (int) y, size, size); //Rectangles are close enough for now
		for (int i = 0; i < meteors.size(); i++) {
			if (i != thisIndex) { //Don't check against itself
				Meteor m = meteors.get(i);
				Rectangle other = new Rectangle((int) m.x, (int) m.y, m.size, m.size);
				
				if (me.intersects(other)) {
					meteors.get(i).xd = -meteors.get(i).xd;
					meteors.get(i).yd = -meteors.get(i).yd;
					
					xd = -xd;
					yd = -yd;
				}
			}
		}
	}
	
	private boolean mouseIsInMeteor(Input input) {
		return input.x >= this.x && input.x <= this.x + this.size && input.y >= this.y && input.y <= this.y + this.size;
	}
	
	public void explode() {
		if (type != EXPLODEABLE) return;
		switch (size) {
		case LARGE:
			x += size / 4; //Center
			y += size / 4;
			size = MEDIUM;
			//Game.sound.play();
			//GameState.addMeteor(new Meteor(x, y, size, size, MEDIUM, type));
			//GameState.addMeteor(new Meteor(x, y, size, size, MEDIUM, type));
			break;
		case MEDIUM:
			x += size / 4;
			y += size / 4;
			size = SMALL;
			break;
		case SMALL:
			toBeDestroyed = true;
			break;
		}
	}
	
	public Meteor(double x, double y, double xd, double yd, int size, int type) {
		this.x = x;
		this.y = y;
		this.xd = xd;
		this.yd = yd;
		this.size = size;
		this.type = type;
	}
}
