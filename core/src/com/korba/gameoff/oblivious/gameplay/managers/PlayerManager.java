package com.korba.gameoff.oblivious.gameplay.managers;

import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.*;
import com.korba.gameoff.oblivious.gameplay.player.Player;
import com.korba.gameoff.oblivious.gameplay.player.PlayerLight;
import com.korba.gameoff.oblivious.gameplay.player.PlayerPhysics;
import com.korba.gameoff.oblivious.gameplay.player.PlayerSprite;
import com.korba.gameoff.oblivious.tools.*;

import java.util.*;

public class PlayerManager {

    private Player.STATE state;
    private Player.DIRECTION direction;
    private Player.SIZE size;

    private Player player;
    private PlayerSprite currentSprite;
    private final World world;

    private Map<String, Animation> animations;

    PlayerManager(final World world) {
        this.world = world;
        state = Player.STATE.IDLE;
        direction = Player.DIRECTION.DOWN;
        this.player = createPlayer();
        this.currentSprite = player.getSprite32();
        defineAnimation();
    }

    private Player createPlayer() {
        PlayerPhysics physics = definePhysics(world);
        PlayerSprite sprite64 = new PlayerSprite(new TextureRegion(AssetUtils.getTexture(AssetUtils.PLAYER_64), 0, 0, 32, 64));
        PlayerSprite sprite32 = new PlayerSprite(new TextureRegion(AssetUtils.getTexture(AssetUtils.PLAYER_32), 0, 0, 32, 32));
        player = new Player(physics, sprite32, sprite64);
        return player;
    }

    private PlayerPhysics definePhysics(World world) {
        return new PlayerPhysics(world, new Vector2(0, 0));
    }

    public void createLight(RayHandler rayHandler) {
        PlayerLight.createLight(rayHandler, 500, player.getPhysics().getBody(), Color.BLACK, 12);
    }

    public PlayerPhysics getPhysics() {
        return player.getPhysics();
    }

    public PlayerSprite getSprite() {
        return currentSprite;
    }

    public Map<String, Animation> getAnimations() {
        return animations;
    }

    public void setSpriteType(MapType type) {
        if (type.equals(MapType.OPEN)) {
            currentSprite = player.getSprite32();
            setSize(Player.SIZE.SMALL);
        } else {
            currentSprite = player.getSprite64();
            setSize(Player.SIZE.BIG);
        }
    }

    public Player.STATE getState() {
        return state;
    }

    public void setState(Player.STATE state) {
        this.state = state;
    }

    public Player.DIRECTION getDirection() {
        return direction;
    }

    public void setDirection(Player.DIRECTION direction) {
        this.direction = direction;
    }

    public void setSize(Player.SIZE size) {
        this.size = size;
    }

    public Player.SIZE getSize() {
        return size;
    }

    private void defineAnimation() {
        animations = new HashMap<>();
        if(AssetUtils.isAssetLoaded(AssetUtils.PLAYER_32_SPRITES) && AssetUtils.isAssetLoaded(AssetUtils.PLAYER_64_SPRITES)) {
            TextureRegion[][] frames32 = TextureRegion.split(AssetUtils.getTexture(AssetUtils.PLAYER_32_SPRITES), 32, 32);
            TextureRegion[][] frames64 = TextureRegion.split(AssetUtils.getTexture(AssetUtils.PLAYER_64_SPRITES), 32, 64);
            Array<TextureRegion> downFrames32 = new Array<>();
            Array<TextureRegion> upFrames32 = new Array<>();
            Array<TextureRegion> leftFrames32 = new Array<>();
            Array<TextureRegion> rightFrames32 = new Array<>();
            downFrames32.addAll(frames32[0][0], frames32[0][1], frames32[0][2], frames32[0][3]);
            upFrames32.addAll(frames32[1][0], frames32[1][1], frames32[1][2], frames32[1][3]);
            leftFrames32.addAll(frames32[2][0], frames32[2][1], frames32[2][2], frames32[2][3]);
            rightFrames32.addAll(frames32[3][0], frames32[3][1], frames32[3][2], frames32[3][3]);

            animations.put("WALK_DOWN_32", new Animation(1f / 8f, downFrames32));
            animations.put("WALK_UP_32", new Animation(1f / 8f, upFrames32));
            animations.put("WALK_LEFT_32", new Animation(1f / 8f, leftFrames32));
            animations.put("WALK_RIGHT_32", new Animation(1f / 8f, rightFrames32));

            Array<TextureRegion> downFrames64 = new Array<>();
            Array<TextureRegion> upFrames64 = new Array<>();
            Array<TextureRegion> sideFrames64 = new Array<>();

            upFrames64.addAll(frames64[0][0], frames64[0][1], frames64[0][2], frames64[0][3], frames64[0][4], frames64[0][5], frames64[0][6]);
            downFrames64.addAll(frames64[1][0], frames64[1][1], frames64[1][2], frames64[1][3], frames64[1][4], frames64[1][5], frames64[1][6]);
            sideFrames64.addAll(frames64[2][0], frames64[2][1], frames64[2][2], frames64[2][3], frames64[2][1], frames64[2][5], frames64[2][6], frames64[2][7], frames64[2][8]);

            animations.put("WALK_DOWN_64", new Animation(1f / 10f, downFrames64));
            animations.put("WALK_UP_64", new Animation(1f / 10f, upFrames64));
            animations.put("WALK_SIDE_64", new Animation(1f / 10f, sideFrames64));

            animations.forEach((k, v) -> v.setPlayMode(Animation.PlayMode.LOOP));
        }
    }
}
