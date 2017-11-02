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

    private boolean showLogger = false;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		createCustomCursor();
       	setScreen(new MenuScreen(batch, this));
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
