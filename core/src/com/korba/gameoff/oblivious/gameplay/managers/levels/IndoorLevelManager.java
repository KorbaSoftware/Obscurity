package com.korba.gameoff.oblivious.gameplay.managers.levels;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.korba.gameoff.oblivious.ObscurityGame;
import com.korba.gameoff.oblivious.gameplay.managers.MapManager;
import com.korba.gameoff.oblivious.gameplay.managers.MapType;

public class IndoorLevelManager extends LevelManager {


    public IndoorLevelManager (ObscurityGame game, World world, TiledMap map, MapManager mapManager, MapType mapType) {
        super(game, world, map, mapManager);
        this.mapType = mapType;
        //setInactive();
    }
}
