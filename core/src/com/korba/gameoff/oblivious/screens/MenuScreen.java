package com.korba.gameoff.oblivious.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
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
import com.korba.gameoff.oblivious.assets.Assets;
import com.korba.gameoff.oblivious.config.LauncherConfig;
import com.korba.gameoff.oblivious.screens.dev.DevScreen;

public class MenuScreen implements Screen{

    private SpriteBatch batch;
    private ObscurityGame game;
    private Camera camera;
    private Viewport viewport;
    private Stage stage;
    private Texture background;

    public MenuScreen(SpriteBatch batch, ObscurityGame game){
        this.batch = batch;
        this.game = game;
        game.setGameState(ObscurityGame.GameState.IN_MENU);
        
        camera = new OrthographicCamera();
        viewport = new FitViewport(LauncherConfig.WIDTH, LauncherConfig.HEIGHT, camera);
        stage =  new Stage(viewport, batch);
        stage.addActor(setMenuButtons());
        stage.addActor(setGameLogo());
        stage.addActor(setCompanyLogo());
        background = Assets.manager.get(Assets.BACKGROUND, Texture.class);
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

    private void clearScreen(){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
    }

    private void draw(){
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0, LauncherConfig.WIDTH, LauncherConfig.HEIGHT);
        batch.end();
        stage.draw();
    }

    private Table setMenuButtons(){
        Table table = new Table();
        table.right().bottom().padBottom(20).padRight(20);
        Image newGame = new Image(Assets.manager.get(Assets.NEW_GAME, Texture.class));
        newGame.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //TODO
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        Image loadGame = new Image(Assets.manager.get(Assets.LOAD_GAME, Texture.class));
        loadGame.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //TODO
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        Image options = new Image(Assets.manager.get(Assets.OPTIONS, Texture.class));
        options.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //TODO
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        Image exitGame = new Image(Assets.manager.get(Assets.EXIT, Texture.class));
        exitGame.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
            }
        });
        table.add(newGame);
        table.row().padTop(20);
        table.add(loadGame);
        table.row().padTop(20);
        table.add(options);
        table.row().padTop(20);
        table.add(exitGame);
        table.setFillParent(true);
        return table;
    }

    private Table setGameLogo(){
        Table table = new Table();
        table.center().top().padTop(20);
        Image logo = new Image(Assets.manager.get(Assets.GAME_LOGO, Texture.class));
        table.add(logo);
        table.setFillParent(true);
        return table;
    }

    private Table setCompanyLogo(){
        Table table = new Table();
        table.left().bottom().padBottom(20).padLeft(20);
        Image logo = new Image(Assets.manager.get(Assets.KORBA_LOGO, Texture.class));
        if(game.isDevMode()) {
            final MenuScreen menu = this;
            logo.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    game.setScreen(new DevScreen(batch, game, menu));
                }
            });
        }
        table.add(logo);
        table.setFillParent(true);
        return table;
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
        game.dispose();
        batch.dispose();
        stage.dispose();
    }
}
