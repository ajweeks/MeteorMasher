package ca.ajweeks.meteormasher.utils;

import java.awt.Color;
import java.awt.Graphics;

import ca.ajweeks.meteormasher.Game;

public class Button {
	
	public Colour colour;
	
	public int x, y, width, height;
	public String text;
	public boolean hover;
	
	public Button(int x, int y, int width, int height, String text) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		hover = false;
		colour = new Colour();
	}
	
	public boolean hovering(int x, int y) {
		return (x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height);
	}
	
	public void render(Graphics g) {
		g.setColor(hover ? colour.buttonHover : colour.button);
		g.fillRect(this.x, this.y, this.width, this.height);
		
		g.setFont(Game.font42);
		g.setColor(Color.BLACK);
		g.drawString(this.text, this.x + 10, this.y + 36);
	}
	
	public void render(Graphics g, Color colour, Color hoverColour, Color textColour) {
		g.setColor(hover ? hoverColour : colour);
		g.fillRect(this.x, this.y, this.width, this.height);
		
		g.setFont(Game.font42);
		g.setColor(textColour);
		g.drawString(this.text, this.x + 10, this.y + 36);
	}
}
