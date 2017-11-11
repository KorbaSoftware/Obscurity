package com.korba.gameoff.oblivious.screens.dev.kubatest.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;
import com.korba.gameoff.oblivious.config.GameConfig;

public class BodyComponent implements Component {
    public Body body;
    public BodyComponent(PositionComponent positionCom, Body body) {
        setBodyAndPosition(positionCom, body);
    }
    public void setBodyAndPosition(PositionComponent position, Body body) {
        this.body = body;
        this.body.setTransform(position.x / GameConfig.PPM, position.y / GameConfig.PPM, 0);
    }

}
