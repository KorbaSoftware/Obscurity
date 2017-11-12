package com.korba.gameoff.oblivious.gameplay.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.korba.gameoff.oblivious.gameplay.player.PlayerPhysics;
import com.korba.gameoff.oblivious.gameplay.player.PlayerSprite;
import com.korba.gameoff.oblivious.tools.*;

public class PlayerManager {
    private PlayerPhysics physics;
    private PlayerSprite sprite;
    private World world;

    public PlayerManager(World world){
        this.world = world;
        definePhysics(world);
        defineSprite();
    }

    private void definePhysics(World world){
        this.physics = new PlayerPhysics(world, new Vector2(0,0));
    }

    private void defineSprite(){
        this.sprite = new PlayerSprite(new TextureRegion(AssetUtils.getTexture(AssetUtils.PLAYER), 0, 0, 32, 64));

    }

    public PlayerPhysics getPhysics() {
        return physics;
    }
    public PlayerSprite getSprite() {
        return sprite;
    }
}
