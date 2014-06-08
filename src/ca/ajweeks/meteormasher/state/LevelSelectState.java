package ca.ajweeks.meteormasher.state;

import java.awt.Graphics;

import ca.ajweeks.meteormasher.Game;
import ca.ajweeks.meteormasher.input.Input;
import ca.ajweeks.meteormasher.utils.Button;

public class LevelSelectState extends BasicState {
	
	private Button arcade = new Button((540 / 2) - (158 / 2), 80, 158, 45, "Arcade");
	private Button timeTrial = new Button((540 / 2) - (225 / 2), 150, 225, 45, "Time Trial");
	private Button freePlay = new Button((540 / 2) - (206 / 2), 220, 206, 45, "Free Play");
	//private Button multiplayer = new Button((540/2)-(275/2), 260, 275, 45, "Multiplayer");
	private Button back = new Button((540 / 2) - (120 / 2), 600, 120, 45, "Back");
	
	@Override
	public void update(Input input) {
		arcade.hover = arcade.hovering(input.x, input.y);
		if (arcade.hover && (input.leftMouseDown || input.rightMouseDown)) {
			GameState.GAME_MODE = GameState.GameMode.ARCADE;
			Game.stateManager.changeState(StateManager.GAME_STATE);
		}
		
		timeTrial.hover = timeTrial.hovering(input.x, input.y);
		if (timeTrial.hover && (input.leftMouseDown || input.rightMouseDown)) {
			GameState.GAME_MODE = GameState.GameMode.TIME_TRIAL;
			Game.stateManager.changeState(StateManager.GAME_STATE);
		}
		
		freePlay.hover = freePlay.hovering(input.x, input.y);
		if (freePlay.hover && (input.leftMouseDown || input.rightMouseDown)) {
			GameState.GAME_MODE = GameState.GameMode.FREE_PLAY;
			Game.stateManager.changeState(StateManager.GAME_STATE);
		}
		
		//LATER: add multiplayer gamemode
		//multiplayer.hover = multiplayer.hovering(input.x, input.y);
		//if (multiplayer.hover && (input.leftMouseDown || input.rightMouseDown)) {
		//	GameState.GAME_MODE = GameState.MULTIPLAYER;
		//	Game.stateManager.changeState(StateManager.GAME_STATE);
		//}
		
		back.hover = back.hovering(input.x, input.y);
		if (back.hover && (input.leftMouseDown || input.rightMouseDown)) {
			Game.stateManager.changeState(StateManager.MAIN_MENU_STATE);
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(Game.starryBackground, 0, 0, null);
		
		arcade.render(g);
		timeTrial.render(g);
		freePlay.render(g);
		//multiplayer.render(g);
		back.render(g);
	}
}
