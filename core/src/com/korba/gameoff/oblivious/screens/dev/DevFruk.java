package com.korba.gameoff.oblivious.screens.dev;

import com.badlogic.gdx.graphics.g2d.*;
import com.korba.gameoff.oblivious.*;
import com.korba.gameoff.oblivious.screens.ProfileScreen;

public class DevFruk extends BasicScreen {

    public DevFruk(SpriteBatch batch, ObscurityGame game) {
       super(batch,game);

    }
    @Override
    public void show(){
        super.show();
        game.setScreen(new ProfileScreen(batch, game));
    }
}
