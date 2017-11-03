package com.korba.gameoff.oblivious.screens.dev.kubatest;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.korba.gameoff.oblivious.config.GameConfig;

public class Player extends Sprite {

    public World world;
    public Body body;

    public Player(World world){
        this.world = world;
        definePlayer();
    }

    public void definePlayer() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(320 / GameConfig.PPM, 320 / GameConfig.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.filter.categoryBits = GameConfig.PLAYER_BIT;
        fixtureDef.filter.maskBits = GameConfig.STATIC_OBJECT_BIT |
                                     GameConfig.DOOR_BIT;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(16 / GameConfig.PPM, 16 / GameConfig.PPM);
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef).setUserData("player");
    }

    public void update(float delta){
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
    }
}