package com.korba.gameoff.oblivious.gameplay.mapelements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.korba.gameoff.oblivious.config.GameConfig;
import com.korba.gameoff.oblivious.gameplay.managers.MapManager;

public class Door extends MapObject {
    MapManager mapManager;

    public Door(World world, TiledMap map, Rectangle bounds, MapManager mapManager) {
        super(world, map, bounds);
        this.mapManager = mapManager;
        setCategoryFilter(GameConfig.DOOR_BIT);
        fixture.setUserData(this);
    }

    @Override
    public void onContact() {
        Gdx.app.debug("Door Class", "onContact method");
        mapManager.changeMap();

    }
}
