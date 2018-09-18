package com.korba.gameoff.oblivious.gameplay.player;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.korba.gameoff.oblivious.config.GameConfig;


public class PlayerPhysics {

    private World world;
    private Body body;

    public PlayerPhysics(World world, Vector2 position) {
        this.world = world;
        definePlayer(position);
    }

    private void definePlayer(Vector2 position) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(position);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);
        body.createFixture(createFixtureDef()).setUserData("player");
    }

    private FixtureDef createFixtureDef() {
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.filter.categoryBits = GameConfig.PLAYER_BIT;
        fixtureDef.filter.maskBits = prepareCategoryBits();
        fixtureDef.shape = prepareShape();
        return fixtureDef;
    }

    private short prepareCategoryBits() {
        return GameConfig.STATIC_OBJECT_BIT |
                GameConfig.DOOR_BIT |
                GameConfig.SPAWN_POINT_BIT |
                GameConfig.STATION_CHANGE_BIT;
    }

    private PolygonShape prepareShape() {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(16 / GameConfig.PPM, 16 / GameConfig.PPM);
        return shape;
    }

    public void setBodyPosition(Vector2 position) {
        body.setTransform(position, 0);
    }

    public Body getBody() {
        return body;
    }
}