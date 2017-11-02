package com.korba.gameoff.oblivious.screens.dev;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.korba.gameoff.oblivious.*;

public class DevKamil implements Screen{

    private SpriteBatch batch;
    private ObscurityGame game;
    private Stage stage;
    private Camera camera;
    private Viewport viewport;


    public DevKamil(SpriteBatch batch, ObscurityGame game) {
        this.batch = batch;
        this.game = game;

        camera = new OrthographicCamera();
        viewport = new FitViewport(game.ACTUAL_WIDTH, game.ACTUAL_HEIGHT, camera);
        stage = new Stage(viewport, batch);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        clearScreen();
        draw();
    }


    private void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
    }

    private void draw() {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.end();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
        game.dispose();
    }
}
