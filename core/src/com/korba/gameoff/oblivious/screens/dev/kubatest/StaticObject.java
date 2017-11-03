package com.korba.gameoff.oblivious.screens.dev.kubatest;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.korba.gameoff.oblivious.config.GameConfig;

public class StaticObject extends MapObject{
    public StaticObject(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        setCategoryFilter(GameConfig.STATIC_OBJECT_BIT);
        fixture.setUserData("static");
    }

    @Override
    public void onHit() {

    }
}
