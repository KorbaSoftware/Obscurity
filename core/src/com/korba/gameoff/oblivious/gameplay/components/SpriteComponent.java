package com.korba.gameoff.oblivious.gameplay.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteComponent implements Component {

    public Sprite sprite;
    public int offset = 0;

    public SpriteComponent(TextureRegion textureRegion){
        this.sprite = new Sprite(textureRegion);
        if(sprite.getRegionHeight() == 64){
            this.offset = 16;
        }
    }
}
