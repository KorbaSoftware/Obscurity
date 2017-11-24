package com.korba.gameoff.oblivious.gameplay.managers.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.korba.gameoff.oblivious.ObscurityGame;
import com.korba.gameoff.oblivious.config.GameConfig;
import com.korba.gameoff.oblivious.gameplay.managers.MapManager;
import com.korba.gameoff.oblivious.gameplay.managers.MapType;
import com.korba.gameoff.oblivious.gameplay.mapelements.Door;
import com.korba.gameoff.oblivious.gameplay.mapelements.StationChanger;

public class MetroStationManager extends IndoorLevelManager{
    private final int PREVIOUS_STATION = 7;
    private final int NEXT_STATION = 6;
    private final Vector2 afterTravelSpawnPoint;
    private Array<StationChanger> stationChangers;


    public MetroStationManager (ObscurityGame game, World world, TiledMap map, MapManager mapManager, MapType mapType) {
        super(game, world, map, mapManager, mapType);
        afterTravelSpawnPoint = super.spawnPoints.peek();
        stationChangers = new Array<>();
        for (MapObject object : map.getLayers().get(PREVIOUS_STATION).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            stationChangers.add( new StationChanger(world, map, rect, mapManager,-1));
        }
        for (MapObject object : map.getLayers().get(NEXT_STATION).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            stationChangers.add( new StationChanger(world, map, rect, mapManager,1));
        }

    }

    @Override
    public void setInactive(){
        super.setInactive();
        stationChangers.forEach(stationChanger -> stationChanger.setCategoryFilter(GameConfig.DEFAULT_BIT));
    }

    @Override
    public void setActive(){
        super.setActive();
        stationChangers.forEach(stationChanger -> stationChanger.setCategoryFilter(GameConfig.STATION_CHANGE_BIT));
    }



    public Vector2 getAfterTravelSpawnPoint() {
        return afterTravelSpawnPoint;
    }
}
