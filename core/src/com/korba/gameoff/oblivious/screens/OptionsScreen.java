package com.korba.gameoff.oblivious.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.korba.gameoff.oblivious.ObscurityGame;
import com.korba.gameoff.oblivious.config.LauncherConfig;
import com.korba.gameoff.oblivious.config.GamePreferences;
import com.korba.gameoff.oblivious.screens.dev.BasicScreen;
import com.korba.gameoff.oblivious.tools.AssetUtils;

public class OptionsScreen extends BasicScreen{

    private Slider musicVolumeSlider, soundVolumeSlider;
    private Table table;
    private Image optionsLogo;
    private Label soundVolumeLabel, musicVolumeLabel;
    private Texture background;
    private GamePreferences preferences;

    public OptionsScreen(SpriteBatch batch, ObscurityGame game){
        super(batch,game);
        preferences = game.getPreferences();
        prepareElements();
        createView();
    }

    private void prepareElements() {
        musicVolumeSlider = new Slider(0.0f, 1.0f, 0.1f, false, AssetUtils.DEFAULT_SKIN); //values to adjust in the future
        soundVolumeSlider = new Slider(0.0f, 1.0f, 0.1f, false, AssetUtils.DEFAULT_SKIN); //values to adjust in the future
        optionsLogo = new Image(AssetUtils.getTexture(AssetUtils.OPTIONS));
        musicVolumeSlider.setValue(preferences.getMusicVolume());
        soundVolumeSlider.setValue(preferences.getSoundVolume());
        musicVolumeLabel = new Label(String.format(java.util.Locale.US, "%.0f" + "%%",
                preferences.getMusicVolume()*100), AssetUtils.DEFAULT_SKIN); //value to adjust in the future
        soundVolumeLabel = new Label(String.format(java.util.Locale.US, "%.0f" + "%%",
                preferences.getSoundVolume()*100), AssetUtils.DEFAULT_SKIN); //value to adjust in the future
    }

    private void createView(){
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


        musicVolumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                preferences.setMusicVolume(musicVolumeSlider.getValue());
                musicVolumeLabel.setText(String.format(java.util.Locale.US, "%.0f" + "%%",
                        preferences.getMusicVolume()*100)); //value to adjust in the future
            }
        });
        soundVolumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                preferences.setSoundVolume(soundVolumeSlider.getValue());
                soundVolumeLabel.setText(String.format(java.util.Locale.US, "%.0f" + "%%",
                        preferences.getSoundVolume()*100)); //value to adjust in the future
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
