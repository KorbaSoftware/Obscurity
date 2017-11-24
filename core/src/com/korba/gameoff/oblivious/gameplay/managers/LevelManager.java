package com.korba.gameoff.oblivious.gameplay.managers;

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
import com.korba.gameoff.oblivious.gameplay.mapelements.StaticObject;

public class LevelManager {
    private ObscurityGame game;
    private TiledMap map;
    protected Array<Vector2> spawnPoints;
    private Array<StaticObject> walls;
    protected Array<Door> doors;
    protected Vector2 lastSpawnPoint;
    private final int WALL_LAYER = 3;
    private final int SPAWN_POINTS = 4;
    private final int DOOR_LAYER = 5;




    public LevelManager(ObscurityGame game, World world, TiledMap map, MapManager mapManager){
            this.map = map;
            this.game = game;

        walls = new Array<>();
        for (MapObject object : map.getLayers().get(WALL_LAYER).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();
               walls.add( new StaticObject(world, map, rect));
            }

        spawnPoints = new Array<>();
        for (MapObject object : map.getLayers().get(SPAWN_POINTS).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            spawnPoints.add(new Vector2((rect.getX() + 16) / GameConfig.PPM, (rect.getY() + 16) / GameConfig.PPM));
        }

        doors = new Array<>();
        for (MapObject object : map.getLayers().get(DOOR_LAYER).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            doors.add(new Door(world, map, rect, mapManager));
        }

    }


        public void setActive(){
            for (StaticObject wall : walls){
                //wall.getBody().setActive(true);
                wall.setCategoryFilter(GameConfig.STATIC_OBJECT_BIT);
            }
            for (Door door : doors){
                //door.getBody().setActive(true);
                door.setCategoryFilter(GameConfig.DOOR_BIT);
            }

        }

        public void setInactive(){
            for (StaticObject wall : walls){
            //wall.getBody().setActive(false);
                wall.setCategoryFilter(GameConfig.DEFAULT_BIT);
             }
            for (Door door : doors){
               // door.getBody().setActive(false);
                door.setCategoryFilter(GameConfig.DEFAULT_BIT);
            }
        }
        public Vector2 getPlayerPosition(){
        return spawnPoints.first();
        }
        public TiledMap getMap() {return map;}

    public void setLastSpawnPoint(Vector2 lastSpawnPoint) {
        this.lastSpawnPoint = lastSpawnPoint;
    }

    public Vector2 getLastSpawnPoint() {
        return lastSpawnPoint;
    }
}
