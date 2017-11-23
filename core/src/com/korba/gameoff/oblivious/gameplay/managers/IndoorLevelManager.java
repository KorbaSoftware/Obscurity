package com.korba.gameoff.oblivious.gameplay.managers;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.korba.gameoff.oblivious.ObscurityGame;

public class IndoorLevelManager extends LevelManager {

    private MapType mapType;
    public IndoorLevelManager (ObscurityGame game, World world, TiledMap map, MapManager mapManager, MapType mapType) {
        super(game, world, map, mapManager);
        this.mapType = mapType;
        setInactive();
    }
    public MapType getMapType() {
        return mapType;
    }
}
