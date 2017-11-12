package com.korba.gameoff.oblivious.ui.inside;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.korba.gameoff.oblivious.*;
import com.korba.gameoff.oblivious.ui.*;

public class InsideUI implements UI {

    ObscurityGame game;
    SpriteBatch batch;
    Stage stage;

    public InsideUI(ObscurityGame game, SpriteBatch batch, Stage stage) {
        this.game = game;
        this.batch = batch;
        this.stage = stage;

    }

    @Override
    public void dispose() {
        game.dispose();
        batch.dispose();
        stage.dispose();
    }
}
