package com.korba.gameoff.oblivious.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.korba.gameoff.oblivious.ObscurityGame;
import com.korba.gameoff.oblivious.config.LauncherConfig;
import com.korba.gameoff.oblivious.tools.AssetUtils;
import com.korba.gameoff.oblivious.tools.TextLoader;

public class TapeScene implements Disposable {

    private final Viewport viewport;
    public Stage stage;
    private final ObscurityGame game;
    private int iterator = 1;
    private final Label textLabel;
    private final TextLoader txtLoader;
    private final Image textBackgroundImage;
    private final Image backgroundImage;

    public TapeScene(SpriteBatch sb, ObscurityGame game){
        this.game = game;
        viewport = new FitViewport(LauncherConfig.WIDTH,LauncherConfig.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        txtLoader = new TextLoader(1);
        txtLoader.loadTexts();
        textLabel = new Label(txtLoader.getTextById(0), AssetUtils.DEFAULT_SKIN);
        textBackgroundImage = new Image(AssetUtils.getTexture(AssetUtils.TEXT_BG));
        backgroundImage = new Image(AssetUtils.getTexture(AssetUtils.TAPE_SCREEN));
        textLabel.setFontScale(2,2);

        stage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                nextText();
            }
        });

        Stack textStack = new Stack();
        backgroundImage.setSize(LauncherConfig.WIDTH, LauncherConfig.HEIGHT*0.75f);
        textLabel.setAlignment(1);
        textStack.add(textBackgroundImage);
        textStack.add(textLabel);

        Table table = new Table();
        table.center().top();
        table.add(backgroundImage).width(LauncherConfig.WIDTH).height(LauncherConfig.HEIGHT*0.75f);
        table.row();
        table.add(textStack).width(LauncherConfig.WIDTH).height(LauncherConfig.HEIGHT*0.25f);
        table.setFillParent(true);
        stage.addActor(table);
    }

    public void nextText(){
        if(txtLoader.getTextById(iterator)!= null) {
            textLabel.setText(txtLoader.getTextById(iterator++));
        }
        else {
            clearScreen();
            textLabel.setText(txtLoader.getFirst());
            iterator = 1;
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
        Gdx.gl.glClearColor(0.55f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
    }
}
