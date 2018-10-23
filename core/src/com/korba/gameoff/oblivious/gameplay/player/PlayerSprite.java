package com.korba.gameoff.oblivious.gameplay.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerSprite extends Sprite{
    //TODO
    private final TextureRegion textureRegion;

    public PlayerSprite(TextureRegion textureRegion){
        this.textureRegion = textureRegion;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }
}
