package com.korba.gameoff.oblivious.gameplay.managers;

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
import com.korba.gameoff.oblivious.gameplay.mapelements.Door;
import com.korba.gameoff.oblivious.gameplay.mapelements.SpawnPoint;
import com.korba.gameoff.oblivious.gameplay.mapelements.StaticObject;

public class WorldLevelManager extends LevelManager {

    private final int POSITION_LAYER = 6;
    private final int ROOM1_LAYER = 7;
    private final int ROOM2_LAYER = 8;
    private final int ROOM3_LAYER = 9;
    private final int METRO_ENTRANCE_LAYER = 10;

    private Array<SpawnPoint> positions;

    public WorldLevelManager (ObscurityGame game, World world, TiledMap map, MapManager mapManager){
        super(game, world, map, mapManager);
        positions = new Array<>();
        for (MapObject object : map.getLayers().get(POSITION_LAYER).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            positions.add(new SpawnPoint(world, map, rect, mapManager));
        }
        lastSpawnPoint = super.getPlayerPosition();


        for (MapObject object : map.getLayers().get(ROOM1_LAYER).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
             doors.add(new Door(world, map, rect, mapManager, MapType.MOTELROOM1));
        }
        for (MapObject object : map.getLayers().get(ROOM2_LAYER).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            doors.add(new Door(world, map, rect, mapManager, MapType.MOTELROOM2));
        }
        for (MapObject object : map.getLayers().get(ROOM3_LAYER).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            doors.add(new Door(world, map, rect, mapManager, MapType.MOTELROOM3));
        }
        for (MapObject object : map.getLayers().get(METRO_ENTRANCE_LAYER).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            doors.add(new Door(world, map, rect, mapManager, MapType.METROSTATION));
        }


        Gdx.app.debug("WorldLevelManager", "constructor");

        setInactive();
    }

}
