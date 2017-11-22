package com.korba.gameoff.oblivious.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.korba.gameoff.oblivious.ObscurityGame;
import com.korba.gameoff.oblivious.config.GamePreferences;
import com.korba.gameoff.oblivious.config.LauncherConfig;
import com.korba.gameoff.oblivious.screens.dev.BasicScreen;
import com.korba.gameoff.oblivious.tools.AssetUtils;

public class ProfileScreen extends BasicScreen {

    private Table profileTable;
    private Image profile1, profile2, profile3;
    private Label choose;
    private Texture background;

    public ProfileScreen(SpriteBatch batch, ObscurityGame game){
        super(batch, game);
        prepareElements();
    }

    public void prepareElements(){
        profileTable = new Table();
        profileTable.center().top();
        choose = new Label("Choose Profile", AssetUtils.DEFAULT_SKIN);
        profile1 = new Image(AssetUtils.getTexture(AssetUtils.PROFILE_ONE));
        profile2 = new Image(AssetUtils.getTexture(AssetUtils.PROFILE_TWO));
        profile3 = new Image(AssetUtils.getTexture(AssetUtils.PROFILE_THREE));

        profile1.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.getPreferences().setProfile(GamePreferences.SelectedProfile.FIRST);
                game.setScreen(new MenuScreen(batch, game));
            }
        });

        profile2.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.getPreferences().setProfile(GamePreferences.SelectedProfile.SECOND);
                game.setScreen(new MenuScreen(batch, game));
            }
        });

        profile3.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.getPreferences().setProfile(GamePreferences.SelectedProfile.THIRD);
                game.setScreen(new MenuScreen(batch, game));
            }
        });
        profileTable.add();
        profileTable.add(choose).height(80);
        profileTable.row();
        profileTable.add(profile1).padRight(10);
        profileTable.add(profile2).padRight(10);
        profileTable.add(profile3);
        profileTable.setFillParent(true);
        stage.addActor(profileTable);
        background = AssetUtils.getTexture(AssetUtils.BACKGROUND);
    }

    @Override
    protected void draw(){
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0, LauncherConfig.WIDTH, LauncherConfig.HEIGHT);
        profileTable.draw(batch, 1f);
        batch.end();
        stage.draw();
    }
}
