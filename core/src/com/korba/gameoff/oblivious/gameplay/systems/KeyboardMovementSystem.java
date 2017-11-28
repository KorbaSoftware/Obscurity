package com.korba.gameoff.oblivious.gameplay.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.korba.gameoff.oblivious.gameplay.components.*;
import com.korba.gameoff.oblivious.gameplay.managers.*;

public class KeyboardMovementSystem extends EntitySystem {

    private PlayerManager player;

    private ImmutableArray<Entity> entities;
    private ComponentMapper<BodyComponent> bodyMap = ComponentMapper.getFor(BodyComponent.class);
    private ComponentMapper<VelocityComponent> velocityMap = ComponentMapper.getFor(VelocityComponent.class);

    public KeyboardMovementSystem() {

    }

    public KeyboardMovementSystem(PlayerManager player) {
        this.player = player;
    }

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(BodyComponent.class, VelocityComponent.class, PlayerComponent.class).get());
    }

    public void update(float deltaTime) {
        for (Entity entity : entities) {
            BodyComponent bodyCom = entity.getComponent(BodyComponent.class);
            VelocityComponent velocityCom = entity.getComponent(VelocityComponent.class);
            //Gdx.app.debug("Keyboard input", "key pressed");
            if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
                //  Gdx.app.debug("Keyboard input", "up pressed");
                bodyCom.body.setLinearVelocity(0, velocityCom.velocity);
                if(player != null) {
                    player.setState(PlayerManager.PLAYER_STATE.WALKING);
                    player.setDirection(PlayerManager.PLAYER_DIRECTION.UP);
                }
            } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
                // Gdx.app.debug("Keyboard input", "down pressed");
                bodyCom.body.setLinearVelocity(0, -velocityCom.velocity);
                if(player != null) {
                    player.setState(PlayerManager.PLAYER_STATE.WALKING);
                    player.setDirection(PlayerManager.PLAYER_DIRECTION.DOWN);
                }
            } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
                //  Gdx.app.debug("Keyboard input", "left pressed");
                bodyCom.body.setLinearVelocity(-velocityCom.velocity, 0);
                if(player != null) {
                    player.setState(PlayerManager.PLAYER_STATE.WALKING);
                    player.setDirection(PlayerManager.PLAYER_DIRECTION.LEFT);
                }
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
                // Gdx.app.debug("Keyboard input", "right pressed");
                bodyCom.body.setLinearVelocity(velocityCom.velocity, 0);
                if(player != null) {
                    player.setState(PlayerManager.PLAYER_STATE.WALKING);
                    player.setDirection(PlayerManager.PLAYER_DIRECTION.RIGHT);
                }
            } else {
                bodyCom.body.setLinearVelocity(0, 0);
                if(player != null) {
                    player.setState(PlayerManager.PLAYER_STATE.IDLE);
                }
            }
        }

    }
}
