package ca.ajweeks.meteormasher.state;

import java.awt.Graphics;

import ca.ajweeks.meteormasher.Game;
import ca.ajweeks.meteormasher.input.Input;
import ca.ajweeks.meteormasher.utils.Button;
import ca.ajweeks.meteormasher.utils.Colour;

public class MainMenuState extends BasicState {
	
	private Button playGame = new Button((540 / 2) - (110 / 2), 300, 110, 45, "Play");
	private Button quit = new Button((540 / 2) - (110 / 2), 365, 110, 45, "Quit");
	private Colour colour = new Colour();
	
	@Override
	public void update(Input input) {
		playGame.hover = playGame.hovering(input.x, input.y);
		if (playGame.hover && (input.leftMouseDown || input.rightMouseDown)) {
			Game.stateManager.changeState(StateManager.GAME_STATE);
		}
		
		quit.hover = quit.hovering(input.x, input.y);
		if (quit.hover && (input.leftMouseDown || input.rightMouseDown)) {
			System.exit(0);
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(Game.starryBackground, 0, 0, null);
		
		g.setColor(colour.title);
		
		playGame.render(g);
		quit.render(g);
	}
	
}
