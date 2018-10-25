package com.korba.gameoff.oblivious.gameplay.player;

public class Player {

    public enum STATE {
        IDLE,
        WALKING
    }

    public enum DIRECTION {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public enum SIZE {
        SMALL,
        BIG
    }

    private PlayerSprite sprite32;
    private PlayerSprite sprite64;
    private PlayerPhysics physics;

    public Player (PlayerPhysics physics, PlayerSprite sprite32, PlayerSprite sprite64) {
        this.sprite32 = sprite32;
        this.sprite64 = sprite64;
        this.physics = physics;
    }

    public PlayerPhysics getPhysics() {
        return physics;
    }

    public void setPhysics(PlayerPhysics physics) {
        this.physics = physics;
    }

    public PlayerSprite getSprite32() {
        return sprite32;
    }

    public PlayerSprite getSprite64() {
        return sprite64;
    }


}
