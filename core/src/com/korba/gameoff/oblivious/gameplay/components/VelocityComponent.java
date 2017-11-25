package com.korba.gameoff.oblivious.gameplay.components;

import com.badlogic.ashley.core.Component;

public class VelocityComponent implements Component {

    public float velocity = 0;

    public VelocityComponent(float velocity) {
        this.velocity = velocity;
    }
}
