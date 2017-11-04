package com.korba.gameoff.oblivious.ui.inside;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.korba.gameoff.oblivious.*;
import com.korba.gameoff.oblivious.config.*;

public class InsideUiDevScreen implements Screen{

    private SpriteBatch batch;
    private ObscurityGame game;
    private Camera camera;
    private Viewport viewport;
    private Stage stage;

    private InsideUI insideUI;

    public InsideUiDevScreen(SpriteBatch batch, ObscurityGame game) {
        this.batch = batch;
        this.game = game;

        camera = new OrthographicCamera();
        viewport = new FitViewport(LauncherConfig.WIDTH, LauncherConfig.HEIGHT, camera);
        stage = new Stage(viewport, batch);

        insideUI = new InsideUI(game, batch, stage);
    }

    @Override
    public void show() {

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
