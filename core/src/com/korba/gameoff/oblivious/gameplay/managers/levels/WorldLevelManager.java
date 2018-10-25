package com.korba.gameoff.oblivious.gameplay.managers.levels;

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
import com.korba.gameoff.oblivious.gameplay.mapelements.SpawnPoint;

public class WorldLevelManager extends LevelManager {

    private final int POSITION_LAYER = 6;
    private final int ROOM1_LAYER = 7;
    private final int ROOM2_LAYER = 8;
    private final int ROOM3_LAYER = 9;
    private final int METRO_ENTRANCE_1_LAYER = 10;
    private final int METRO_ENTRANCE_2_LAYER = 11;
    private final int METRO_ENTRANCE_3_LAYER = 12;
    private final int METRO_EXIT_1_LAYER = 13;
    private final int METRO_EXIT_2_LAYER = 14;
    private final int METRO_EXIT_3_LAYER = 15;

    private final Array<SpawnPoint> positions;
    private Vector2 metroExit1;
    private Vector2 metroExit2;
    private Vector2 metroExit3;

    public WorldLevelManager (ObscurityGame game, World world, TiledMap map, MapManager mapManager){
        super(game, world, map, mapManager);
        mapType = MapType.OPEN;
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
        for (MapObject object : map.getLayers().get(METRO_ENTRANCE_1_LAYER).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            doors.add(new Door(world, map, rect, mapManager, MapType.METROSTATION1));
        }
        for (MapObject object : map.getLayers().get(METRO_ENTRANCE_2_LAYER).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            doors.add(new Door(world, map, rect, mapManager, MapType.METROSTATION2));
        }
        for (MapObject object : map.getLayers().get(METRO_ENTRANCE_3_LAYER).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            doors.add(new Door(world, map, rect, mapManager, MapType.METROSTATION3));
        }
        for (MapObject object : map.getLayers().get(METRO_EXIT_1_LAYER).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            metroExit1 = new Vector2((rect.getX() + 16) / GameConfig.PPM, (rect.getY() + 16) / GameConfig.PPM);
        }
        for (MapObject object : map.getLayers().get(METRO_EXIT_2_LAYER).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            metroExit2 = new Vector2((rect.getX() + 16) / GameConfig.PPM, (rect.getY() + 16) / GameConfig.PPM);
        }
        for (MapObject object : map.getLayers().get(METRO_EXIT_3_LAYER).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            metroExit3 = new Vector2((rect.getX() + 16) / GameConfig.PPM, (rect.getY() + 16) / GameConfig.PPM);
        }
    }

    public Vector2 getExit(MapType type){
        switch(type){
            case METROSTATION1:
                return metroExit1;
            case METROSTATION2:
                return metroExit2;
            case METROSTATION3:
                return metroExit3;
            default:
                return new Vector2(0,0);
        }
    }

}
