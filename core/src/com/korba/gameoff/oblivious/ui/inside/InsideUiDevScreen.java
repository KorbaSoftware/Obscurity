package com.korba.gameoff.oblivious.ui.inside;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.korba.gameoff.oblivious.*;
import com.korba.gameoff.oblivious.config.*;

public class InsideUiDevScreen implements Screen {

    OrthographicCamera camera;
    OrthographicCamera uiCamera;

    ObscurityGame game;
    InsideUI insideUI;


    public InsideUiDevScreen(ObscurityGame game) {
        this.game = game;
        game.setGameState(ObscurityGame.GameState.INSIDE);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, LauncherConfig.WIDTH, LauncherConfig.HEIGHT);

        uiCamera = new OrthographicCamera();
        uiCamera.setToOrtho(false, camera.viewportWidth, camera.viewportHeight);
        insideUI = new InsideUI(uiCamera);

        Gdx.input.setInputProcessor(insideUI.getStage());
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(insideUI.getStage());


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        insideUI.render(delta);
    }

    @Override
    public void resize(int width, int height) {

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
        insideUI.dispose();
        game.dispose();
    }
}
