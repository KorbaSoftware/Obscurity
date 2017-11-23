package com.korba.gameoff.oblivious.gameplay.player;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.korba.gameoff.oblivious.config.GameConfig;
import com.korba.gameoff.oblivious.tools.*;

public class PlayerPhysics{

    public World world;

    public Body getBody() {
        return body;
    }
    private Body body;

    public PlayerPhysics(World world, Vector2 position){
        this.world = world;
        definePlayer(position);
    }

    private void definePlayer(Vector2 position) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(position);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.filter.categoryBits = GameConfig.PLAYER_BIT;
        fixtureDef.filter.maskBits = GameConfig.STATIC_OBJECT_BIT |
                                     GameConfig.DOOR_BIT |
                                     GameConfig.SPAWN_POINT_BIT;
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(16 / GameConfig.PPM, 16 / GameConfig.PPM);
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef).setUserData("player");
    }

    public void setBodyPosition(Vector2 position){
        body.setTransform(position, 0);
    }

}