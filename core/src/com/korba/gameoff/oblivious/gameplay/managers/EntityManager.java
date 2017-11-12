package com.korba.gameoff.oblivious.gameplay.managers;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.korba.gameoff.oblivious.gameplay.systems.ControlledMovementSystem;
import com.korba.gameoff.oblivious.gameplay.systems.PositionSystem;
import com.korba.gameoff.oblivious.gameplay.systems.RenderSystem;

public class EntityManager {
    public Engine getEngine() {
        return engine;
    }

    private Engine engine;
    private World world;
    private PlayerManager player;
    private static Array<Entity> entities = new Array<Entity>();

    public EntityManager(Engine engine, SpriteBatch spriteBatch, World world){
        this.engine = engine;
        this.world = world;
        PositionSystem positionSystem = new PositionSystem();
        RenderSystem renderSystem = new RenderSystem(spriteBatch);
        ControlledMovementSystem controlledMovementSystem = new ControlledMovementSystem();
        engine.addSystem(controlledMovementSystem);
        engine.addSystem(positionSystem);
        engine.addSystem(renderSystem);
        createPlayer();
    }

    private void createPlayer(){
        PlayerManager player = new PlayerManager(world);
        this.player = player;
    }

    public void update(float delta){
            engine.update(delta);
    }

    public static void add(Entity entity) {
        entities.add(entity);
    }

    public PlayerManager getPlayer() {
        return player;
    }
}
