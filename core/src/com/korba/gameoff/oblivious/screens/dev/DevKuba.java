package com.korba.gameoff.oblivious.screens.dev;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.*;
import com.korba.gameoff.oblivious.*;
import com.korba.gameoff.oblivious.screens.dev.kubatest.GameTestScreen;
import com.korba.gameoff.oblivious.screens.dev.kubatest.MapType;
import com.korba.gameoff.oblivious.ui.inside.*;

public class DevKuba extends BasicScreen {

    private Table screensTable;

    Screen openTestScreen;
    Screen roomTestScreen;
    public DevKuba(SpriteBatch batch, ObscurityGame game) {
        super(batch,game);

        screensTable = new Table();
        screensTable.left().top();
        screensTable.padLeft(20).padTop(20);

        openTestScreen = new GameTestScreen(batch, game, MapType.OPEN);
        additionalScreen(openTestScreen, "Open World");
        screensTable.row().padTop(10);
        roomTestScreen = new GameTestScreen(batch, game, MapType.ROOM);
        additionalScreen(roomTestScreen, "Room");

        screensTable.setFillParent(true);
        stage.addActor(screensTable);
    }

    private void additionalScreen(Screen devScreen, String buttonText) {
        TextButton btnChangeScreen = new TextButton(buttonText, new Skin(Gdx.files.internal("skins/default_ui_skin.json")));
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