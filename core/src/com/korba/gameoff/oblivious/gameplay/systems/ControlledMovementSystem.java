package com.korba.gameoff.oblivious.gameplay.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.korba.gameoff.oblivious.config.GameConfig;
import com.korba.gameoff.oblivious.gameplay.components.*;

public class ControlledMovementSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;
    private ComponentMapper<BodyComponent> bodyMap = ComponentMapper.getFor(BodyComponent.class);
    private ComponentMapper<VelocityComponent> velocityMap = ComponentMapper.getFor(VelocityComponent.class);

    public ControlledMovementSystem(){

    }

    public void addedToEngine(Engine engine){
        entities = engine.getEntitiesFor(Family.all(BodyComponent.class, VelocityComponent.class, PlayerComponent.class).get());
    }

    public void update(float deltaTime){

        for (Entity entity : entities){
            BodyComponent bodyCom = entity.getComponent(BodyComponent.class);
            VelocityComponent velocityCom = entity.getComponent(VelocityComponent.class);
            if        (Gdx.input.isKeyPressed(Input.Keys.DOWN)
                    || Gdx.input.isKeyPressed(Input.Keys.UP)
                    || Gdx.input.isKeyPressed(Input.Keys.RIGHT)
                    || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                if(Gdx.input.isKeyPressed(Input.Keys.UP)){
                    bodyCom.body.setLinearVelocity(0, velocityCom.velocity);
                }
                if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
                    bodyCom.body.setLinearVelocity(0, -velocityCom.velocity);
                }
                if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                    bodyCom.body.setLinearVelocity(-velocityCom.velocity, 0);
                }
                if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                    bodyCom.body.setLinearVelocity(velocityCom.velocity, 0);
                }
            } else {
                bodyCom.body.setLinearVelocity(0, 0);
            }
        }

    }
}
