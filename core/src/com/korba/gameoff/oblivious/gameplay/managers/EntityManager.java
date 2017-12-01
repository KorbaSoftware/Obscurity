package com.korba.gameoff.oblivious.gameplay.managers;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.korba.gameoff.oblivious.gameplay.systems.*;

public class EntityManager {
    public Engine getEngine() {
        return engine;
    }

    private Engine engine;
    private World world;
    private PlayerManager player;
    private static Array<Entity> entities = new Array<Entity>();
    private MouseMovementSystem mouseMovementSystem;
    private KeyboardMovementSystem keyboardMovementSystem;

    public EntityManager(Engine engine, SpriteBatch spriteBatch, World world) {
        this.engine = engine;
        this.world = world;
        createPlayer();
        mouseMovementSystem = new MouseMovementSystem(player);
        keyboardMovementSystem = new KeyboardMovementSystem(player);
        CollisionSystem collisionSystem = new CollisionSystem(world);
        PositionSystem positionSystem = new PositionSystem();
        RenderSystem renderSystem = new RenderSystem(spriteBatch, player);
        engine.addSystem(collisionSystem);
        engine.addSystem(positionSystem);
        engine.addSystem(renderSystem);
    }

    private void createPlayer() {
        PlayerManager player = new PlayerManager(world);
        this.player = player;
    }

    public void setKeyboardInput() {
        engine.removeSystem(mouseMovementSystem);
        engine.addSystem(keyboardMovementSystem);
        mouseMovementSystem.nullify();
    }

    public void setMouseInput() {
        engine.removeSystem(keyboardMovementSystem);
        engine.addSystem(mouseMovementSystem);
    }

    public void update(float delta) {
        engine.update(delta);
    }

    public static void add(Entity entity) {
        entities.add(entity);
    }

    public PlayerManager getPlayer() {
        return player;
    }

    public KeyboardMovementSystem getKeyboardMovementSystem() {
        return keyboardMovementSystem;
    }

    public MouseMovementSystem getMouseMovementSystem() {
        return mouseMovementSystem;
    }
}