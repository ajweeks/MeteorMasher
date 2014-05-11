package ca.ajweeks.meteormasher.state;

import java.awt.Graphics;

import ca.ajweeks.meteormasher.input.Input;

public abstract class BasicState {
	
	public abstract void render(Graphics g);
	
	public abstract void update(Input input);
	
}
