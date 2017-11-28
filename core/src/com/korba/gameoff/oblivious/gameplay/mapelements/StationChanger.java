package com.korba.gameoff.oblivious.gameplay.mapelements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.korba.gameoff.oblivious.config.GameConfig;
import com.korba.gameoff.oblivious.gameplay.managers.MapManager;
import com.korba.gameoff.oblivious.gameplay.managers.MapType;

public class StationChanger extends MapObject{

    private MapManager mapManager;
    private int direction;

    public StationChanger(World world, TiledMap map, Rectangle bounds, MapManager mapManager, int direction){
        super(world, map, bounds);
        this.mapManager = mapManager;
        this.direction = direction;
        setCategoryFilter(GameConfig.STATION_CHANGE_BIT);
        fixture.setUserData(this);
        fixture.setSensor(true);
    }

    @Override
    public void onContact() {
        mapManager.changeStation(direction);

    }
}
