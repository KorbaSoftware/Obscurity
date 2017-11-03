package com.korba.gameoff.oblivious.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.korba.gameoff.oblivious.ObscurityGame;
import com.korba.gameoff.oblivious.assets.Assets;
import com.korba.gameoff.oblivious.config.LauncherConfig;

public class LoadingScreen implements Screen {

    private AssetManager manager;
    private Image gameLogo;
    private Image korbaLogo;
    private SpriteBatch batch;
    private ObscurityGame game;
    private Camera camera;
    private Viewport viewport;
    private Stage stage;
    private float timer = 0;
    private boolean isFirstTime = true;

    public LoadingScreen(SpriteBatch batch, ObscurityGame game) {
        this.batch = batch;
        this.game = game;
        manager = game.getAssetManager();
        getLoadingAssets(manager);
        camera = new OrthographicCamera();
        viewport = new FitViewport(LauncherConfig.WIDTH, LauncherConfig.HEIGHT, camera);
        stage = new Stage(viewport, batch);
        stage.addActor(korbaLogo);
    }

    private void getLoadingAssets(AssetManager manager){
        Assets.loadLoadingAssets(manager);
        manager.finishLoading();
        gameLogo = new Image(manager.get(Assets.GAME_LOGO, Texture.class));
        korbaLogo = new Image(manager.get(Assets.KORBA_LOGO, Texture.class));
    }

    private void loading(float delta){
        timer += delta;

        if(timer > LauncherConfig.LOADING_SCREEN_DELAY1){
            korbaLogo.addAction(Actions.removeActor());
            if(isFirstTime) {
                stage.addActor(gameLogo);
                gameLogo.setPosition(LauncherConfig.WIDTH/2 - gameLogo.getWidth()/2, LauncherConfig.HEIGHT/2 - gameLogo.getHeight()/2);
                isFirstTime = false;
            }
        }
        if(manager.update() && (timer > LauncherConfig.LOADING_SCREEN_DELAY2)){
            game.setScreen(new MenuScreen(batch, game));
        }
    }

    private void draw(float delta){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.end();
        stage.draw();
        stage.act(delta);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this.stage);
        korbaLogo.setPosition(LauncherConfig.WIDTH/2 - korbaLogo.getWidth()/2, LauncherConfig.HEIGHT/2 - korbaLogo.getHeight()/2);
        Assets.loadGameAssets(manager);
    }

    @Override
    public void render(float delta) {
        loading(delta);
        draw(delta);
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
        stage.dispose();
        batch.dispose();
        manager.dispose();
        game.dispose();
    }
}