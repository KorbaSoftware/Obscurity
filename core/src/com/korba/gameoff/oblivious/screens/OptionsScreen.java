package com.korba.gameoff.oblivious.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.google.gwt.i18n.client.*;
import com.korba.gameoff.oblivious.ObscurityGame;
import com.korba.gameoff.oblivious.config.LauncherConfig;
import com.korba.gameoff.oblivious.config.GamePreferences;
import com.korba.gameoff.oblivious.screens.dev.BasicScreen;
import com.korba.gameoff.oblivious.tools.AssetUtils;

public class OptionsScreen extends BasicScreen{

    private Slider musicVolumeSlider, soundVolumeSlider;
    private Table table;
    private Image optionsLogo, changeProfile, currentUser;
    private Label soundVolumeLabel, musicVolumeLabel;
    private Texture background;
    private GamePreferences preferences;
    private CheckBox muteCheckBox;

    public OptionsScreen(SpriteBatch batch, ObscurityGame game){
        super(batch,game);
        preferences = game.getPreferences();
        prepareElements();
        createViewTable();
    }

    private void prepareElements() {
        musicVolumeSlider = new Slider(0.0f, 1.0f, 0.1f, false, AssetUtils.DEFAULT_SKIN); //values to adjust in the future
        soundVolumeSlider = new Slider(0.0f, 1.0f, 0.1f, false, AssetUtils.DEFAULT_SKIN); //values to adjust in the future
        optionsLogo = new Image(AssetUtils.getTexture(AssetUtils.OPTIONS));
        changeProfile = new Image(AssetUtils.getTexture(AssetUtils.PROFILE));
        currentUser = new Image(getCurrentUser());
        currentUser.setSize(32, 32);
        muteCheckBox = new CheckBox("MUTE", AssetUtils.DEFAULT_SKIN);

        musicVolumeSlider.setValue(preferences.getMusicVolume());
        soundVolumeSlider.setValue(preferences.getSoundVolume());
        muteCheckBox.setChecked(preferences.isMuted());


        musicVolumeLabel = new Label((int)(1 * preferences.getMusicVolume() *100) + "%", AssetUtils.DEFAULT_SKIN);
        soundVolumeLabel = new Label((int)(1 * preferences.getSoundVolume() *100) + "%", AssetUtils.DEFAULT_SKIN);
    }

    private Texture getCurrentUser() {
        switch(preferences.getProfile()){
            case FIRST: {
                return AssetUtils.getTexture(AssetUtils.PROFILE_ONE);
            }
            case SECOND: {
                return AssetUtils.getTexture(AssetUtils.PROFILE_TWO);
            }
            case THIRD: {
                return AssetUtils.getTexture(AssetUtils.PROFILE_THREE);
            }
        }
        return null;
    }

    private void createViewTable(){
        table = new Table();
        table.center().top();
        table.add(optionsLogo).width(optionsLogo.getWidth()).pad(20);
        table.row();
        table.add(new Label("MUSIC VOLUME", AssetUtils.DEFAULT_SKIN)).pad(20);
        table.row();
        table.add(musicVolumeSlider).width(optionsLogo.getWidth()/2);
        table.row();
        table.add(musicVolumeLabel).pad(20);
        table.row();
        table.add(new Label("SOUND VOLUME", AssetUtils.DEFAULT_SKIN)).pad(20);
        table.row();
        table.add(soundVolumeSlider).width(optionsLogo.getWidth()/2);
        table.row();
        table.add(soundVolumeLabel).pad(20);
        table.row();
        table.add(muteCheckBox).pad(20);
        table.row();
        table.add(new Label("CURRENT PROFILE:", AssetUtils.DEFAULT_SKIN));
        table.row();
        table.add(currentUser).width(64).height(64).pad(20);
        table.row();
        table.add(changeProfile);

        musicVolumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                preferences.setMusicVolume(musicVolumeSlider.getValue());
                musicVolumeLabel.setText((int)(preferences.getMusicVolume() *100) + "%"); //value to adjust in the future
            }
        });
        soundVolumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                preferences.setSoundVolume(soundVolumeSlider.getValue());
                soundVolumeLabel.setText((int)(1 * preferences.getSoundVolume() *100) + "%"); //value to adjust in the future
            }
        });
        muteCheckBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                preferences.setMuted(muteCheckBox.isChecked());
            }
        });

        changeProfile.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new ProfileScreen(batch, game));
            }
        });

        table.setFillParent(true);
        stage.addActor(table);
        background = AssetUtils.getTexture(AssetUtils.BACKGROUND);
    }

    @Override
    protected void draw(){
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0, LauncherConfig.WIDTH, LauncherConfig.HEIGHT);
        table.draw(batch, 1f);
        batch.end();
        stage.draw();
    }
}
