package com.korba.gameoff.oblivious;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.korba.gameoff.oblivious.config.*;
import com.korba.gameoff.oblivious.screens.MenuScreen;
import com.korba.gameoff.oblivious.tools.*;

public class ObscurityGame extends Game {

	public final static LoggerDev devLOG = new LoggerDev();

	private SpriteBatch batch;
    private Cursor customCursor;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		createCustomCursor();
       	setScreen(new MenuScreen(batch, this));
	}

	@Override
	public void render () {
		super.render();
	}

	private void createCustomCursor() {
		customCursor = Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("custom_cursor.png")), 0, 0);
		Gdx.graphics.setCursor(customCursor);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		customCursor.dispose();
	}

	public boolean isDevMode(){
		return LauncherConfig.IS_DEVMODE;
	}
}
