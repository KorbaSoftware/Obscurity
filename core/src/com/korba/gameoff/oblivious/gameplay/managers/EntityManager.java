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
    public MouseInputSystem mouseInputSystem;
    public KeyboardInputSys keyboardInputSys;

    public EntityManager(Engine engine, SpriteBatch spriteBatch, World world){
        this.engine = engine;
        this.world = world;
        mouseInputSystem = new MouseInputSystem();
        keyboardInputSys = new KeyboardInputSys();
        CollisionSystem collisionSystem = new CollisionSystem(world);
        PositionSystem positionSystem = new PositionSystem();
        RenderSystem renderSystem = new RenderSystem(spriteBatch);
        engine.addSystem(collisionSystem);
        engine.addSystem(positionSystem);
        engine.addSystem(renderSystem);
        createPlayer();
    }

    private void createPlayer(){
        PlayerManager player = new PlayerManager(world);
        this.player = player;
    }

    public void setKeyboardInput() {
        engine.removeSystem(mouseInputSystem);
        engine.addSystem(keyboardInputSys);
    }

    public void setMouseInput() {
        engine.removeSystem(keyboardInputSys);
        engine.addSystem(mouseInputSystem);
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