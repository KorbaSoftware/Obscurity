package com.korba.gameoff.oblivious.config;


import com.badlogic.gdx.graphics.Color;
import static com.badlogic.gdx.graphics.Color.NAVY;

public class GameConfig {

    private GameConfig(){}

    //general config vars
    public static final int PPM = 16; //pixels per meter
    public static final int TILE_SIZE = 32;
    public static final boolean IS_DEVMODE = false;
    public static final float OUTDOOR_VELOCITY = 12f;
    public static final float INDOOR_VELOCITY = 6f;

    //collision bits
    public static final short DEFAULT_BIT = 1;
    public static final short PLAYER_BIT = 2;
    public static final short STATIC_OBJECT_BIT = 4;
    public static final short DOOR_BIT = 8;
    public static final short SPAWN_POINT_BIT = 16;
    public static final short STATION_CHANGE_BIT = 32;

    //ambient light
    public static final float AMBIENT_LIGHT_STRENGTH = 0.1f;
    public static final Color AMBIENT_LIGHT_COLOR = NAVY;

}
