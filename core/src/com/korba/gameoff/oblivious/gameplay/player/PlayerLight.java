package com.korba.gameoff.oblivious.gameplay.player;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.Body;

public class PlayerLight {

    private PlayerLight(){}

    public static void createLight(RayHandler rayHandler, int rays, Body body, Color color, float radius) {
        PointLight pointLight = new PointLight(rayHandler, rays);
        pointLight.attachToBody(body);
        pointLight.setColor(color);
        pointLight.setDistance(radius);
        pointLight.setContactFilter((short) 0, (short) 4, (short) 4); //random values
    }
}
