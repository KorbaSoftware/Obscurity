package com.korba.gameoff.oblivious;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.korba.gameoff.oblivious.screens.MenuScreen;

public class ObscurityMain extends Game {
	private SpriteBatch batch;
    public static final int ACTUAL_WIDTH = 1280;
    public static final int ACTUAL_HEIGHT = 720;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
       	setScreen(new MenuScreen(batch, this));
	}

	@Override
	public void render () {
	    super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
