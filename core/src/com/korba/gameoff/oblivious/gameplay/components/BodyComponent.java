package com.korba.gameoff.oblivious.gameplay.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;
import com.korba.gameoff.oblivious.config.GameConfig;

public class BodyComponent implements Component {

    public Body body;

    public BodyComponent(Body body) {
        this.body = body;
    }

}
