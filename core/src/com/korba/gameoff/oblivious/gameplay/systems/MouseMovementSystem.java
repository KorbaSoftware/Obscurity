package com.korba.gameoff.oblivious.gameplay.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import com.korba.gameoff.oblivious.gameplay.components.BodyComponent;
import com.korba.gameoff.oblivious.gameplay.components.CameraComponent;
import com.korba.gameoff.oblivious.gameplay.components.PlayerComponent;
import com.korba.gameoff.oblivious.gameplay.components.VelocityComponent;
import com.korba.gameoff.oblivious.gameplay.managers.*;
import com.korba.gameoff.oblivious.gameplay.player.Player;

public class MouseMovementSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;
    private ImmutableArray<Entity> cameraEntities;
    private BodyComponent bodyComponent;
    private VelocityComponent velocityComponent;
    private CameraComponent cameraComponent;
    private Vector3 pointerPosition;
    private Vector3 playerPosition;
    private float deltaX = 0;
    private float deltaY = 0;
    private final PlayerManager player;

    public MouseMovementSystem(PlayerManager player) {
        this.player = player;
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(BodyComponent.class, VelocityComponent.class, PlayerComponent.class).get());
        cameraEntities = engine.getEntitiesFor(Family.all(CameraComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        for (Entity cameraEntity : cameraEntities) {
            cameraComponent = cameraEntity.getComponent(CameraComponent.class);
            for (Entity entity : entities) {
                bodyComponent = entity.getComponent(BodyComponent.class);
                velocityComponent = entity.getComponent(VelocityComponent.class);
            }
        }

        playerPosition = new Vector3(bodyComponent.body.getPosition().x, bodyComponent.body.getPosition().y, 0);

        if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            int pointerX = Gdx.input.getX();
            int pointerY = Gdx.input.getY();

            pointerPosition = new Vector3(pointerX, pointerY, 0);
            cameraComponent.camera.unproject(pointerPosition);
        }
        if (pointerPosition != null) {
            deltaY = pointerPosition.y - playerPosition.y;
            deltaX = pointerPosition.x - playerPosition.x;
        }
        if (!(deltaY < 1 && deltaY > -1) || !(deltaX < 1 && deltaX > -1)) {
            if (deltaY > 1) {
                bodyComponent.body.setLinearVelocity(bodyComponent.body.getLinearVelocity().x, velocityComponent.velocity);
                player.setState(Player.STATE.WALKING);
                player.setDirection(Player.DIRECTION.UP);
            } else if (deltaY < -1) {
                bodyComponent.body.setLinearVelocity(bodyComponent.body.getLinearVelocity().x, -velocityComponent.velocity);
                player.setState(Player.STATE.WALKING);
                player.setDirection(Player.DIRECTION.DOWN);
            } else {
                bodyComponent.body.setLinearVelocity(bodyComponent.body.getLinearVelocity().x, 0);
                player.setState(Player.STATE.WALKING);
            }

            if (deltaX > 1) {
                bodyComponent.body.setLinearVelocity(velocityComponent.velocity, bodyComponent.body.getLinearVelocity().y);
                player.setState(Player.STATE.WALKING);
                player.setDirection(Player.DIRECTION.RIGHT);
            } else if (deltaX < -1) {
                bodyComponent.body.setLinearVelocity(-velocityComponent.velocity, bodyComponent.body.getLinearVelocity().y);
                player.setState(Player.STATE.WALKING);
                player.setDirection(Player.DIRECTION.LEFT);
            } else {
                bodyComponent.body.setLinearVelocity(0, bodyComponent.body.getLinearVelocity().y);
                player.setState(Player.STATE.WALKING);
            }

        } else {
            bodyComponent.body.setLinearVelocity(0, 0);
            player.setState(Player.STATE.IDLE);
        }
    }

    public void nullify() {
        bodyComponent.body.setLinearVelocity(0, 0);
        pointerPosition = null;
        this.deltaX = 0;
        this.deltaY = 0;
    }
}