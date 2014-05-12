package ca.ajweeks.meteormasher.state;

import java.awt.Graphics;
import java.util.ArrayList;

import ca.ajweeks.meteormasher.Game;
import ca.ajweeks.meteormasher.Meteor;
import ca.ajweeks.meteormasher.input.Input;

public class GameState extends BasicState {
	
	public static int ARCADE = 0;
	public static int TIME_TRIAL = 1;
	public static int FREE_PLAY = 2;
	//public static int MULTIPLAYER = 3;
	
	public static ArrayList<Meteor> meteors;
	public static int GAME_MODE = 0;
	
	public GameState() {
		meteors = new ArrayList<>();
		randoSmallPlacement(100, 100);
	}
	
	public static void nuke() { //BOOM!
		for (int i = 0; i < meteors.size(); i++) {
			//OPTIMIZATION: lag occurs when removing meteoroids with a nuke
			meteors.get(i).toBeDestroyed = true;
		}
	}
	
	public void randoSmallPlacement(double x, double y) {
		double xV = Math.random() * 10 - 5;
		if (xV > -1 && xV < 1) xV += Math.random() > 0.5 ? 2 : -2;
		double yV = Math.random() * 10 - 5;
		if (yV > -1 && yV < 1) yV += Math.random() > 0.5 ? 2 : -2;
		int rand = (int) (Math.random() * 100);
		int type;
		if (rand >= 10) type = Meteor.EXPLODEABLE;
		else if (rand >= 5) type = Meteor.PWRUP_EXPLODE;
		else type = Meteor.PWRUP_NUKE;
		meteors.add(new Meteor(x, y, xV, yV, Meteor.LARGE, type));
	}
	
	@Override
	public void update(Input input) {
		if (input.escape) Game.stateManager.changeState(StateManager.LEVEL_SELECT_STATE);
		if (input.rightMouseDown) randoSmallPlacement(input.x - 30, input.y - 30);
		
		for (int i = 0; i < meteors.size(); i++) {
			//System.out.println("xV: " + meteors.get(i).xVelocity + " yV: " + meteors.get(i).yVelocity);
			meteors.get(i).update(input);
			
			if (meteors.get(i).toBeDestroyed) {
				meteors.remove(i);
				break;
			}
			
			meteors.get(i).checkCollisions(meteors, i); //Check if this meteor is colliding with any other meteor
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(Game.starryBackground, 0, 0, null);
		
		for (Meteor m : meteors) {
			m.render(g);
		}
	}
	
}
