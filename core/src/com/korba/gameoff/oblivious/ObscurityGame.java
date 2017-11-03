package com.korba.gameoff.oblivious;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.korba.gameoff.oblivious.assets.Assets;
import com.korba.gameoff.oblivious.config.*;
import com.korba.gameoff.oblivious.screens.LoadingScreen;
import com.korba.gameoff.oblivious.screens.MenuScreen;
import com.korba.gameoff.oblivious.tools.*;

public class ObscurityGame extends Game {

	public enum GameState {
		LOADING,
		IN_MENU,
		PAUSED,
		INSIDE,
		OUTSIDE,
		FIGHT,
		CUTSCENE
	}

	public final static LoggerDev devLOG = new LoggerDev();

	private SpriteBatch batch;
    private Cursor customCursor;
    private GameState gameState;

    private boolean showLogger = false;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gameState = GameState.LOADING;
		Assets.loadInitialAssets();

		createCustomCursor();
       	setScreen(new LoadingScreen(batch, this));
	}

	@Override
	public void render () {
		super.render();
		input();
		drawLogger();
	}

	private void drawLogger() {
		if(!showLogger)
			return;

		devLOG.drawLogs();
		//TODO draw logger in corner of the screen
	}

	private void input() {
		if(Gdx.input.isKeyJustPressed(Input.Keys.X))
			showLogger = !showLogger;
	}

	private void createCustomCursor() {
		customCursor = Gdx.graphics.newCursor(Assets.manager.get(Assets.CURSOR, Pixmap.class), 0, 0);
		Gdx.graphics.setCursor(customCursor);
	}

	public boolean isDevMode(){
		return GameConfig.IS_DEVMODE;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public GameState getGameState(){
		return gameState;
	}

	@Override
	public void dispose () {
		batch.dispose();
		customCursor.dispose();
		Assets.manager.dispose();
	}

}
