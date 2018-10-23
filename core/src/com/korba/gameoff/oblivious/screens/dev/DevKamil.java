package com.korba.gameoff.oblivious.screens.dev;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.*;
import com.korba.gameoff.oblivious.*;
import com.korba.gameoff.oblivious.tools.*;
import com.korba.gameoff.oblivious.ui.inside.*;

public class DevKamil extends BasicScreen {

    private final Table screensTable;

    Screen insideUiDevScreen;

    public DevKamil(SpriteBatch batch, ObscurityGame game) {
        super(batch,game);

        screensTable = new Table();
        screensTable.left().top();
        screensTable.padLeft(20).padTop(20);

        insideUiDevScreen = new InsideUiDevScreen(game);
        additionalScreen(insideUiDevScreen, "Inside UI");

        screensTable.setFillParent(true);
        stage.addActor(screensTable);
    }

    private void additionalScreen(Screen devScreen, String buttonText) {
        TextButton btnChangeScreen = new TextButton(buttonText, AssetUtils.DEFAULT_SKIN);
        final Screen screen = devScreen;

        btnChangeScreen.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(screen);
            }
        });

        screensTable.add(btnChangeScreen).pad(5, 5, 5, 5);
    }

}
