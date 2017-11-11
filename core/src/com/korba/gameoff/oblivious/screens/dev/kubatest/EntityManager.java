package com.korba.gameoff.oblivious.screens.dev.kubatest;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.korba.gameoff.oblivious.screens.dev.kubatest.components.BodyComponent;
import com.korba.gameoff.oblivious.screens.dev.kubatest.systems.PositionSystem;
import com.korba.gameoff.oblivious.screens.dev.kubatest.systems.RenderSystem;

public class EntityManager {
    public Engine getEngine() {
        return engine;
    }

    private Engine engine;
    private World world;
    private static Array<Entity> entities = new Array<Entity>();

    public EntityManager(Engine engine, SpriteBatch spriteBatch){
        this.engine = engine;
        this.world = world;
        PositionSystem positionSystem = new PositionSystem();
        RenderSystem renderSystem = new RenderSystem(spriteBatch);
        engine.addSystem(positionSystem);
        engine.addSystem(renderSystem);
    }

    public void update(float delta){
            engine.update(delta);
    }

    public static void add(Entity entity) {
        entities.add(entity);
    }

}
