package com.korba.gameoff.oblivious.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.korba.gameoff.oblivious.ObscurityGame;
import com.korba.gameoff.oblivious.screens.dev.AlertWindow;
import com.korba.gameoff.oblivious.tools.AssetUtils;

import java.util.LinkedList;

public class PauseOverlay extends Dialog {

    private Image resumeImage,optionsImage, saveImage, loadImage, exitImage;
    private Stage stage;
    private PauseOverlay thisOverlay;
    private LinkedList<Image> imageList;

    public PauseOverlay(Stage stage) {
        super("", AssetUtils.DEFAULT_SKIN, "dialog");
        this.stage = stage;
        thisOverlay = this;
        imageList = new LinkedList<>();
        createView();
    }

    private void createView() {
        Table buttonTable = thisOverlay.getButtonTable();
        //optionImage is in different table so it cannot be added to List
        optionsImage = new Image(AssetUtils.getTexture(AssetUtils.OPTIONS));

        imageList.add(resumeImage = new Image(AssetUtils.getTexture(AssetUtils.RESUME)));
        imageList.add(saveImage = new Image(AssetUtils.getTexture(AssetUtils.SAVE_GAME)));
        imageList.add(loadImage = new Image(AssetUtils.getTexture(AssetUtils.LOAD_GAME)));
        imageList.add(exitImage = new Image(AssetUtils.getTexture(AssetUtils.EXIT)));



        resumeImage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                ObscurityGame.setGameState(ObscurityGame.GameState.RUNNING);
                thisOverlay.remove();
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

        thisOverlay.getContentTable().add(optionsImage).padBottom(10);

        for(Image image : imageList){
            buttonTable.add(image).width(optionsImage.getWidth()*0.9f).height(optionsImage.getHeight()*0.9f).padBottom(10);
            buttonTable.row();
        }

        thisOverlay.show(stage);
    }

    public void doThings(){
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(Gdx.input.getInputProcessor());
        multiplexer.addProcessor(this.stage);
        Gdx.input.setInputProcessor(multiplexer);
        stage.draw();
    }
}
