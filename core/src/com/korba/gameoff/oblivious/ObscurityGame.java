package com.korba.gameoff.oblivious;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.korba.gameoff.oblivious.config.*;
import com.korba.gameoff.oblivious.screens.MenuScreen;

public class ObscurityGame extends Game {
	private SpriteBatch batch;
    private Cursor customCursor;
    private AssetManager manager;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();
		manager.load("custom_cursor.png", Pixmap.class);
		manager.finishLoading();
		createCustomCursor();
       	setScreen(new MenuScreen(batch, this));
	}

	private void createCustomCursor() {
		customCursor = Gdx.graphics.newCursor(manager.get("custom_cursor.png", Pixmap.class), 0, 0);
		Gdx.graphics.setCursor(customCursor);
	}

	@Override
	public void render () {
	    super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		customCursor.dispose();
		manager.dispose();
	}

	public boolean isDevMode(){
		return LauncherConfig.IS_DEVMODE;
	}

	public AssetManager getAssetManager(){
		return manager;
	}

}
