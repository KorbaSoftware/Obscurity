package com.korba.gameoff.oblivious.screens.dev.kubatest.components;

import com.badlogic.ashley.core.Component;

public class VelocityComponent implements Component {
    public float x = 0;
    public float y = 0;
    public VelocityComponent(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
