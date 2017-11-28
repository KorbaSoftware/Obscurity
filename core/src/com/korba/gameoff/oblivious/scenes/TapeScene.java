package com.korba.gameoff.oblivious.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.korba.gameoff.oblivious.ObscurityGame;
import com.korba.gameoff.oblivious.config.LauncherConfig;
import com.korba.gameoff.oblivious.screens.ProfileScreen;
import com.korba.gameoff.oblivious.tools.AssetUtils;
import com.korba.gameoff.oblivious.tools.TextLoader;

public class TapeScene implements Disposable {

    private Viewport viewport;
    public Stage stage;
    private ObscurityGame game;
    private int iterator = 1;
    private Label textLabel;
    private TextLoader txtLoader;
    private Image testImage;

    public TapeScene(SpriteBatch sb, ObscurityGame game){
        this.game = game;
        viewport = new FitViewport(LauncherConfig.WIDTH,LauncherConfig.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        txtLoader = new TextLoader(1);
        txtLoader.loadTexts();
        textLabel = new Label(txtLoader.getTextById(0), AssetUtils.DEFAULT_SKIN);
        testImage = new Image(AssetUtils.getTexture(AssetUtils.OPTIONS));

        testImage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("TEST KURWA");
                nextText();
            }
        });

        Table table = new Table();
        table.center().top();
        table.add(testImage).width(LauncherConfig.WIDTH).height(LauncherConfig.HEIGHT*0.75f);;
        table.row();
        table.add(textLabel);
        table.setFillParent(true);
        stage.addActor(table);
    }

    public void nextText(){

        if(txtLoader.getTextById(iterator)!=null) {
            textLabel.setText(txtLoader.getTextById(iterator++));
        }
        else {
            clearScreen();
            textLabel.setText(txtLoader.getFirst());
            iterator = 1;
            Gdx.input.setInputProcessor(null);
            ObscurityGame.setGameState(ObscurityGame.GameState.RUNNING);
        }
    }

    public void setInput(){
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
    }
}
