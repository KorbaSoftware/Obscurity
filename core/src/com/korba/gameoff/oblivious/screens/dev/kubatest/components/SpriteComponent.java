package com.korba.gameoff.oblivious.screens.dev.kubatest.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteComponent implements Component {
    public Sprite sprite;
    public SpriteComponent(TextureRegion textureRegion){
        this.sprite = new Sprite(textureRegion);
    }
}
