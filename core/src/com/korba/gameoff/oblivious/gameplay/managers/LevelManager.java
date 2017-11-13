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
import com.korba.gameoff.oblivious.gameplay.mapelements.StaticObject;

public class LevelManager {
    private ObscurityGame game;
    private TiledMap map;
    private Array<Vector2> spawnPoints;

    private final int WALL_LAYER = 3;
    private final int SPAWN_POINTS = 4;

    public LevelManager(ObscurityGame game, World world, TiledMap map){
            this.game = game;

            for (MapObject object : map.getLayers().get(WALL_LAYER).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();
                new StaticObject(world, map, rect);
            }
        spawnPoints = new Array<Vector2>();
        for (MapObject object : map.getLayers().get(SPAWN_POINTS).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            spawnPoints.add(new Vector2((rect.getX() + 16) / GameConfig.PPM, (rect.getY() + 16) / GameConfig.PPM));
        }
    }

        public Vector2 getPlayerPosition(){
        return spawnPoints.first();
        }

}
