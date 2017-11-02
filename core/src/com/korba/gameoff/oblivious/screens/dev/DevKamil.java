package com.korba.gameoff.oblivious.screens.dev;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.*;
import com.korba.gameoff.oblivious.*;
import com.korba.gameoff.oblivious.ui.inside.*;

public class DevKamil extends BasicScreen {

    private Table screensTable;

    Screen insideUiDevScreen;

    public DevKamil(SpriteBatch batch, ObscurityGame game) {
        super(batch,game);

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
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    private void additionalScreen(Screen devScreen, String buttonText) {
        TextButton btnChangeScreen = new TextButton(buttonText, new Skin(Gdx.files.internal("skins/default_ui_skin.json")));
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




//=======
//import com.korba.gameoff.oblivious.*;
//>>>>>>> 605c0d8104d6bdc21b78a2a88d2acf768e703950
//
//public class DevKamil extends BasicScreen{
//
//    public DevKamil(SpriteBatch batch, ObscurityGame game) {
//<<<<<<< HEAD
//        this.batch = batch;
//        this.game = game;
//
//        camera = new OrthographicCamera();
//        viewport = new FitViewport(LauncherConfig.WIDTH, LauncherConfig.HEIGHT, camera);
//        stage = new Stage(viewport, batch);
//        screensTable = new Table();
//        screensTable.left().top();
//        screensTable.padLeft(5).padTop(20);
//
//        insideUiDevScreen = new InsideUiDevScreen(batch, game);
//        additionalScreen(insideUiDevScreen, "Inside UI");
//
//        screensTable.setFillParent(true);
//        stage.addActor(screensTable);
//    }
//
//    @Override
//    public void show() {
//        Gdx.input.setInputProcessor(stage);
//    }
//
//    @Override
//    public void render(float delta) {
//        clearScreen();
//        draw();
//    }
//
//    private void additionalScreen(Screen devScreen, String buttonText) {
//        final Label btnChangeScreen = new Label(buttonText, new Skin());
//        final Screen screen = devScreen;
//
//        btnChangeScreen.addListener(new InputListener() {
//            @Override
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                game.setScreen(screen);
//                return super.touchDown(event, x, y, pointer, button);
//            }
//        });
//
//        screensTable.add(btnChangeScreen).pad(5, 5, 5, 5);
//    }
//
//    private void clearScreen() {
//        Gdx.gl.glClearColor(0, 0, 0, 1);
//        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
//    }
//
//    private void draw() {
//        camera.update();
//        batch.setProjectionMatrix(camera.combined);
//        batch.begin();
//        batch.end();
//        stage.draw();
//    }
//
//    @Override
//    public void resize(int width, int height) {
//        viewport.update(width, height);
//    }
//
//    @Override
//    public void pause() {
//
//    }
//
//    @Override
//    public void resume() {
//
//    }
//
//    @Override
//    public void hide() {
//
//    }
//
//    @Override
//    public void dispose() {
//        stage.dispose();
//        batch.dispose();
//        game.dispose();
//=======
//        super(batch,game);
//>>>>>>> 605c0d8104d6bdc21b78a2a88d2acf768e703950
//    }
}
