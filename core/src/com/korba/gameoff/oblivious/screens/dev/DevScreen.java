package com.korba.gameoff.oblivious.screens.dev;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.korba.gameoff.oblivious.ObscurityGame;
import com.korba.gameoff.oblivious.config.LauncherConfig;
import com.korba.gameoff.oblivious.screens.*;
import com.korba.gameoff.oblivious.tools.*;

public class DevScreen implements Screen {

    public enum Dev {
        FRUK,
        KAMIL,
        KUBA
    }

    private final SpriteBatch batch;
    private final ObscurityGame game;
    private final MenuScreen menu;
    private final Camera camera;
    private final Viewport viewport;
    private final Stage stage;
    private final Table devButtons;

    public DevScreen(SpriteBatch batch, ObscurityGame game, MenuScreen menu){
        this.batch = batch;
        this.game = game;
        this.menu = menu;
        camera = new OrthographicCamera();
        viewport = new FitViewport(LauncherConfig.WIDTH, LauncherConfig.HEIGHT, camera);
        stage =  new Stage(viewport, batch);
        stage.addActor(setBackButton());
        devButtons = new Table();
        devButtons.center().top();
        devButtons.padLeft(5).padTop(20);

        addDeveloperButton(AssetUtils.getTexture(AssetUtils.DEV_FRUK), Dev.FRUK);
        addDeveloperButton(AssetUtils.getTexture(AssetUtils.DEV_KAMIL), Dev.KAMIL);
        addDeveloperButton(AssetUtils.getTexture(AssetUtils.DEV_KUBA), Dev.KUBA);

        devButtons.setFillParent(true);
        stage.addActor(devButtons);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this.stage);
    }

    @Override
    public void render(float delta) {
        clearScreen();
        draw();
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

    protected void clearScreen(){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
    }

    protected void draw(){
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.end();
        stage.draw();
    }

    public Table setBackButton(){
        Table table = new Table();
        table.left().bottom().padBottom(20).padLeft(20);
        Image logo = new Image(AssetUtils.getTexture(AssetUtils.KORBA_LOGO));
            logo.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    game.setScreen(menu);
                }
            });
        table.add(logo);
        table.setFillParent(true);
        return table;
    }

    private void addDeveloperButton(Texture texture, Dev dev){
        Image newButton = new Image(texture);
        final Dev devType = dev;
        newButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(chooseScreen(devType));
            }
        });
        this.devButtons.add(newButton).pad(5,5,5,5);
    }

    private Screen chooseScreen(Dev dev) {

        if(Dev.FRUK.equals(dev)) {
            return new DevFruk(batch, game);
        } else if(Dev.KAMIL.equals(dev)) {
            return new DevKamil(batch, game);
        } else {
            return new DevKuba(batch, game);
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
        game.dispose();
    }
}
