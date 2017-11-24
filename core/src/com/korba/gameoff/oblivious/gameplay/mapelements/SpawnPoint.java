package com.korba.gameoff.oblivious.gameplay.mapelements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.korba.gameoff.oblivious.config.GameConfig;
import com.korba.gameoff.oblivious.gameplay.managers.MapManager;

public class SpawnPoint extends MapObject {
    MapManager mapManager;

    public SpawnPoint(World world, TiledMap map, Rectangle bounds, MapManager mapManager) {
        super(world, map, bounds);
        this.mapManager = mapManager;
        setCategoryFilter(GameConfig.SPAWN_POINT_BIT);
        fixture.setSensor(true);
        fixture.setUserData(this);
    }

    @Override
    public void onContact() {
        mapManager.getWorldLevelManager().setLastSpawnPoint(this.body.getPosition());
    }
}