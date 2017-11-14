package com.korba.gameoff.oblivious.gameplay.managers;

import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.korba.gameoff.oblivious.gameplay.player.PlayerLight;
import com.korba.gameoff.oblivious.gameplay.player.PlayerPhysics;
import com.korba.gameoff.oblivious.gameplay.player.PlayerSprite;
import com.korba.gameoff.oblivious.tools.*;

public class PlayerManager {
    private PlayerPhysics physics;
    private PlayerSprite sprite32;
    private PlayerSprite sprite64;
    private PlayerSprite sprite;
    private World world;
    private PlayerLight light;

    public PlayerManager(World world){
        this.world = world;
        definePhysics(world);
        defineSprite();
    }

    private void definePhysics(World world){
        this.physics = new PlayerPhysics(world, new Vector2(0,0));
    }

    private void defineSprite(){
        this.sprite64 = new PlayerSprite(new TextureRegion(AssetUtils.getTexture(AssetUtils.PLAYER_64), 0, 0, 32, 64));
        this.sprite32 = new PlayerSprite(new TextureRegion(AssetUtils.getTexture(AssetUtils.PLAYER_32), 0, 0, 32, 32));
        this.sprite = sprite32;

    }

    public void createLight(RayHandler rayHandler){
        light = new PlayerLight(rayHandler, 500, physics.getBody(), Color.BLACK, 1f, 12);
    }

    public PlayerPhysics getPhysics() {
        return physics;
    }
    public PlayerSprite getSprite() {
        return sprite;
    }

    public void setSpriteType(MapType type) {
        if(type == MapType.OPEN){
            sprite = sprite32;
        }else if(type == MapType.ROOM){
            sprite = sprite64;
        }
    }

}
