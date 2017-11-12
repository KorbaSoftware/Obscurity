package com.korba.gameoff.oblivious;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.korba.gameoff.oblivious.assets.Assets;
import com.korba.gameoff.oblivious.config.*;
import com.korba.gameoff.oblivious.screens.LoadingScreen;
import com.korba.gameoff.oblivious.gameplay.managers.EntityManager;
import com.korba.gameoff.oblivious.tools.*;

public class ObscurityGame extends Game {

	public final static LoggerDev devLOG = new LoggerDev();

	private SpriteBatch batch;
    private Cursor customCursor;
    private World world;
	private EntityManager entityManager;
    private boolean showLogger = false;

	@Override
	public void create () {
		Engine engine = new Engine();
		batch = new SpriteBatch();
		Assets.loadInitialAssets();
		world = new World(new Vector2(0, 0), true);
		entityManager = new EntityManager(engine, batch, world);
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
		customCursor = Gdx.graphics.newCursor(Assets.getPixmap(Assets.CURSOR), 0, 0);
		Gdx.graphics.setCursor(customCursor);
	}
	public EntityManager getEntityManager() {
		return entityManager;
	}
	public World getWorld() {return this.world;}

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
