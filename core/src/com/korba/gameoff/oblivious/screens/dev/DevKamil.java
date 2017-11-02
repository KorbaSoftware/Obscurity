package com.korba.gameoff.oblivious.screens.dev;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.*;
import com.korba.gameoff.oblivious.*;
import com.korba.gameoff.oblivious.config.LauncherConfig;
import com.korba.gameoff.oblivious.ui.*;
import com.korba.gameoff.oblivious.ui.inside.*;

public class DevKamil implements Screen{

    private SpriteBatch batch;
    private ObscurityGame game;
    private Stage stage;
    private Camera camera;
    private Viewport viewport;
    private Table screensTable;

    Screen insideUiDevScreen;


    public DevKamil(SpriteBatch batch, ObscurityGame game) {
        this.batch = batch;
        this.game = game;

        camera = new OrthographicCamera();
        viewport = new FitViewport(LauncherConfig.WIDTH, LauncherConfig.HEIGHT, camera);
        stage = new Stage(viewport, batch);
        screensTable = new Table();
        screensTable.left().top();
        screensTable.padLeft(5).padTop(20);

        insideUiDevScreen = new InsideUiDevScreen(batch, game);
        additionalScreen(insideUiDevScreen, "Inside UI");

        screensTable.setFillParent(true);
        stage.addActor(screensTable);
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

    private void additionalScreen(Screen devScreen, String buttonText) {
        final Label btnChangeScreen = new Label(buttonText, new Skin());
        final Screen screen = devScreen;

        btnChangeScreen.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(screen);
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        screensTable.add(btnChangeScreen).pad(5, 5, 5, 5);
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
