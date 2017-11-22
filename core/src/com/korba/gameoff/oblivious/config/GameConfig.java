package com.korba.gameoff.oblivious.config;

import com.badlogic.gdx.graphics.Color;

public class GameConfig {

    //general config vars
    public static final int PPM = 16; //pixels per meter
    public static final int TILE_SIZE = 32;
    public static final boolean IS_DEVMODE = true;

    //colission bits
    public static final short DEFAULT_BIT = 1;
    public static final short PLAYER_BIT = 2;
    public static final short STATIC_OBJECT_BIT = 4;
    public static final short DOOR_BIT = 8;

    //ambient light
    public static final float AMBIENT_LIGHT_STRENGTH = 0.1f;
    public static final Color AMBIENT_LIGHT_COLOR = Color.NAVY;

}
