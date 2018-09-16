package com.korba.gameoff.oblivious.gameplay.player;

import javafx.scene.PointLight;

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

    private PlayerSprite playerSprite;
    private PointLight playerLight;
    private PlayerPhysics playerPhysics;

    public Player (PlayerSprite playerSprite, PlayerPhysics playerPhysics, PointLight playerLight) {
        this.playerSprite = playerSprite;
        this.playerPhysics = playerPhysics;
        this.playerLight = playerLight;
    }

    public PlayerSprite getPlayerSprite() {
        return playerSprite;
    }

    public void setPlayerSprite(PlayerSprite playerSprite) {
        this.playerSprite = playerSprite;
    }

    public PointLight getPlayerLight() {
        return playerLight;
    }

    public void setPlayerLight(PointLight playerLight) {
        this.playerLight = playerLight;
    }

    public PlayerPhysics getPlayerPhysics() {
        return playerPhysics;
    }

    public void setPlayerPhysics(PlayerPhysics playerPhysics) {
        this.playerPhysics = playerPhysics;
    }
}
