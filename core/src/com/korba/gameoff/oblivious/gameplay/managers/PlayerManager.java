package com.korba.gameoff.oblivious.gameplay.managers;

import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.*;
import com.korba.gameoff.oblivious.gameplay.player.PlayerLight;
import com.korba.gameoff.oblivious.gameplay.player.PlayerPhysics;
import com.korba.gameoff.oblivious.gameplay.player.PlayerSprite;
import com.korba.gameoff.oblivious.tools.*;

import java.util.*;

public class PlayerManager {

    public enum PLAYER_STATE {
        IDLE,
        WALKING
    }

    public enum PLAYER_DIRECTION {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public enum PLAYER_SIZE {
        SMALL,
        BIG
    }

    private PLAYER_STATE state;
    private PLAYER_DIRECTION direction;
    private PLAYER_SIZE size;

    private PlayerPhysics physics;
    private PlayerSprite sprite32;
    private PlayerSprite sprite64;
    private PlayerSprite sprite;
    private World world;
    private PlayerLight light;

    private Map<String, Animation> animations;

    public PlayerManager(World world) {
        this.world = world;
        state = PLAYER_STATE.IDLE;
        direction = PLAYER_DIRECTION.DOWN;
        
        definePhysics(world);
        defineSprite();
        defineAnimation();
    }

    private void definePhysics(World world) {
        this.physics = new PlayerPhysics(world, new Vector2(0, 0));
    }

    private void defineSprite() {
        this.sprite64 = new PlayerSprite(new TextureRegion(AssetUtils.getTexture(AssetUtils.PLAYER_64), 0, 0, 32, 64));
        this.sprite32 = new PlayerSprite(new TextureRegion(AssetUtils.getTexture(AssetUtils.PLAYER_32), 0, 0, 32, 32));
        this.sprite = sprite32;

    }

    private void defineAnimation() {
        animations = new HashMap<>();
        if(AssetUtils.isAssetLoaded(AssetUtils.PLAYER_32_SPRITES)) {
            TextureRegion[][] frames32 = TextureRegion.split(AssetUtils.getTexture(AssetUtils.PLAYER_32_SPRITES), 32, 32);
            Array<TextureRegion> downFrames32 = new Array<>();
            Array<TextureRegion> upFrames32 = new Array<>();
            Array<TextureRegion> leftFrames32 = new Array<>();
            Array<TextureRegion> rightFrames32 = new Array<>();
            downFrames32.addAll(frames32[0][0], frames32[0][1], frames32[0][2], frames32[0][3]);
            upFrames32.addAll(frames32[1][0], frames32[1][1], frames32[1][2], frames32[1][3]);
            leftFrames32.addAll(frames32[2][0], frames32[2][1], frames32[2][2], frames32[2][3]);
            rightFrames32.addAll(frames32[3][0], frames32[3][1], frames32[3][2], frames32[3][3]);

            animations.put("WALK_DOWN_32", new Animation(1f / 6f, downFrames32));
            animations.put("WALK_UP_32", new Animation(1f / 6f, upFrames32));
            animations.put("WALK_LEFT_32", new Animation(1f / 6f, leftFrames32));
            animations.put("WALK_RIGHT_32", new Animation(1f / 6f, rightFrames32));

            animations.forEach((k, v) -> v.setPlayMode(Animation.PlayMode.LOOP));
        }
    }

    public void createLight(RayHandler rayHandler) {
        light = new PlayerLight(rayHandler, 500, physics.getBody(), Color.BLACK, 1f, 12);
    }

    public PlayerPhysics getPhysics() {
        return physics;
    }

    public PlayerSprite getSprite() {
        return sprite;
    }

    public Map<String, Animation> getAnimations() {
        return animations;
    }

    public void setSpriteType(MapType type) {
        if (type.equals(MapType.OPEN)) {
            sprite = sprite32;
            setSize(PLAYER_SIZE.SMALL);
        } else {
            sprite = sprite64;
            setSize(PLAYER_SIZE.BIG);
        }
    }

    public PLAYER_STATE getState() {
        return state;
    }

    public void setState(PLAYER_STATE state) {
        this.state = state;
    }

    public PLAYER_DIRECTION getDirection() {
        return direction;
    }

    public void setDirection(PLAYER_DIRECTION direction) {
        this.direction = direction;
    }

    public void setSize(PLAYER_SIZE size) {
        this.size = size;
    }

    public PLAYER_SIZE getSize() {
        return size;
    }
}
