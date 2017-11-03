package com.korba.gameoff.oblivious.screens.dev.kubatest;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.korba.gameoff.oblivious.ObscurityGame;
import com.korba.gameoff.oblivious.screens.dev.DevKuba;

public class WorldCreator {
    private ObscurityGame game;

    private final int WALL_LAYER = 1;

    public WorldCreator(ObscurityGame game, World world, TiledMap map){
            this.game = game;

            // ground layer
            for (MapObject object : map.getLayers().get(WALL_LAYER).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();
                new StaticObject(world, map, rect);
            }

        }
}
