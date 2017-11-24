package com.korba.gameoff.oblivious.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.korba.gameoff.oblivious.config.GameState;
import com.korba.gameoff.oblivious.config.LauncherConfig;
import com.korba.gameoff.oblivious.screens.dev.AlertWindow;
import com.korba.gameoff.oblivious.tools.AssetUtils;

public class PauseOverlay extends Window {

    private Image resumeImage,optionsImage, saveImage, loadImage, exitImage;
    private Stage stage;
    private PauseOverlay thisOverlay;

    public PauseOverlay(Stage stage) {
        super("", AssetUtils.DEFAULT_SKIN);
        this.stage = stage;
        thisOverlay = this;
        createView();
    }

    private void createView() {
        thisOverlay.setWidth(300);
        thisOverlay.setHeight(500);
        thisOverlay.setX(LauncherConfig.WIDTH/2-thisOverlay.getWidth()/2);
        thisOverlay.setY(LauncherConfig.HEIGHT/2-thisOverlay.getHeight()/2);

        optionsImage = new Image(AssetUtils.getTexture(AssetUtils.OPTIONS));
        resumeImage = new Image(AssetUtils.getTexture(AssetUtils.RESUME));
        saveImage = new Image(AssetUtils.getTexture(AssetUtils.SAVE_GAME));
        loadImage = new Image(AssetUtils.getTexture(AssetUtils.LOAD_GAME));
        exitImage = new Image(AssetUtils.getTexture(AssetUtils.EXIT));

        resumeImage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                GameState.setCurrentState(GameState.State.RUNNING);
                thisOverlay.setVisible(false);
            }
        });

        exitImage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                new AlertWindow(stage);
            }
        });

        thisOverlay.add(optionsImage).padBottom(20);
        thisOverlay.row();
        thisOverlay.add(resumeImage).pad(20).width(256).height(64);
        thisOverlay.row();
        thisOverlay.add(saveImage).pad(20).width(256).height(64);
        thisOverlay.row();
        thisOverlay.add(loadImage).pad(20).width(256).height(64);
        thisOverlay.row();
        thisOverlay.add(exitImage).pad(20).width(256).height(64);;
        //thisOverlay.add(new Label("Hello World", AssetUtils.DEFAULT_SKIN)).pad(20);
        stage.addActor(this);


    }

    public void doThings(){
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(Gdx.input.getInputProcessor());
        multiplexer.addProcessor(this.stage);
        Gdx.input.setInputProcessor(multiplexer);
        stage.draw();
    }
}
