package com.korba.gameoff.oblivious.screens.dev;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.korba.gameoff.oblivious.ObscurityGame;
import com.korba.gameoff.oblivious.config.LauncherConfig;
import com.korba.gameoff.oblivious.tools.AssetUtils;


public class AreYouSureOverlay extends Window {

    private ObscurityGame game;
    private TextButton okButton, cancelButton;
    private Screen screen = null;
    private AreYouSureOverlay thisOverlay;


    public AreYouSureOverlay(Screen screen, ObscurityGame game){
        super("Are You Sure", AssetUtils.DEFAULT_SKIN);
        this.game = game;
        this.screen = screen;
        doThings();
    }

    public AreYouSureOverlay(){
        super("", AssetUtils.DEFAULT_SKIN);
        doThings();
    }

    public void doThings() {
        thisOverlay = this;

        thisOverlay.setX(LauncherConfig.WIDTH/2-thisOverlay.getWidth()/2);
        thisOverlay.setY(LauncherConfig.HEIGHT/2-thisOverlay.getHeight()/2);

        okButton = new TextButton("OK", AssetUtils.DEFAULT_SKIN);
        cancelButton = new TextButton("CANCEL", AssetUtils.DEFAULT_SKIN);

        okButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(screen == null){
                    Gdx.app.exit();
                }else{
                    game.setScreen(screen);
                }
            }
        });

        cancelButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                thisOverlay.remove();
            }
        });
        thisOverlay.add(new Label("ARE YOU SURE?",AssetUtils.DEFAULT_SKIN)).pad(20);
        thisOverlay.row();
        Table group = new Table();
        group.add(okButton).padRight(10);
        group.add(cancelButton);
        thisOverlay.add(group);


    }

}
