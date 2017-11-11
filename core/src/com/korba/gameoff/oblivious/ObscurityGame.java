package com.korba.gameoff.oblivious;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.korba.gameoff.oblivious.assets.Assets;
import com.korba.gameoff.oblivious.config.*;
import com.korba.gameoff.oblivious.screens.LoadingScreen;
import com.korba.gameoff.oblivious.screens.MenuScreen;
import com.korba.gameoff.oblivious.screens.dev.kubatest.EntityManager;
import com.korba.gameoff.oblivious.tools.*;

public class ObscurityGame extends Game {

	public final static LoggerDev devLOG = new LoggerDev();

	private SpriteBatch batch;
    private Cursor customCursor;
	private EntityManager entityManager;
    private boolean showLogger = false;

	@Override
	public void create () {
		Engine engine = new Engine();
		batch = new SpriteBatch();
		entityManager = new EntityManager(engine, batch);
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
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public void dispose () {
		batch.dispose();
		customCursor.dispose();
		Assets.manager.dispose();
	}

	public boolean isDevMode(){
		return GameConfig.IS_DEVMODE;
	}

}
