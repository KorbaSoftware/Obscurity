package com.korba.gameoff.oblivious.gameplay.player;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.Body;

public class PlayerLight extends PointLight {

    private Color color;
    private float strength;

    public PlayerLight(RayHandler rayHandler, int rays, Body body, Color color, float strength, float radius) {
        super(rayHandler, rays);
        this.color = color;
        attachToBody(body);
        setStrength(strength);
        setColor(this.color);
        setDistance(radius);
        setContactFilter((short) 0, (short) 4, (short) 4); //randomowe wartosci

    }

    @Override
    public void update() {
        super.update();
    }

    public void setStrength(float strength) {
        this.strength = strength;
        this.color.a = strength;
    }
}
