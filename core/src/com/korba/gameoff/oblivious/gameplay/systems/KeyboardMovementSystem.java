package com.korba.gameoff.oblivious.gameplay.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.korba.gameoff.oblivious.gameplay.components.*;
import com.korba.gameoff.oblivious.gameplay.managers.*;
import com.korba.gameoff.oblivious.gameplay.player.Player;

public class KeyboardMovementSystem extends EntitySystem {

    private final PlayerManager player;
    private ImmutableArray<Entity> entities;

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
            if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
                bodyCom.body.setLinearVelocity(0, velocityCom.velocity);
                if(player != null) {
                    player.setState(Player.STATE.WALKING);
                    player.setDirection(Player.DIRECTION.UP);
                }
            } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
                bodyCom.body.setLinearVelocity(0, -velocityCom.velocity);
                if(player != null) {
                    player.setState(Player.STATE.WALKING);
                    player.setDirection(Player.DIRECTION.DOWN);
                }
            } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
                bodyCom.body.setLinearVelocity(-velocityCom.velocity, 0);
                if(player != null) {
                    player.setState(Player.STATE.WALKING);
                    player.setDirection(Player.DIRECTION.LEFT);
                }
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
                bodyCom.body.setLinearVelocity(velocityCom.velocity, 0);
                if(player != null) {
                    player.setState(Player.STATE.WALKING);
                    player.setDirection(Player.DIRECTION.RIGHT);
                }
            } else {
                bodyCom.body.setLinearVelocity(0, 0);
                if(player != null) {
                    player.setState(Player.STATE.IDLE);
                }
            }
        }

    }
}
