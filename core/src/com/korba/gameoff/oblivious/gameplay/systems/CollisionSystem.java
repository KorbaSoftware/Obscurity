package com.korba.gameoff.oblivious.gameplay.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.physics.box2d.*;
import com.korba.gameoff.oblivious.config.GameConfig;
import com.korba.gameoff.oblivious.gameplay.components.BodyComponent;
import com.korba.gameoff.oblivious.gameplay.mapelements.Door;
import com.korba.gameoff.oblivious.gameplay.mapelements.SpawnPoint;
import com.korba.gameoff.oblivious.gameplay.mapelements.StationChanger;


public class CollisionSystem extends EntitySystem implements ContactListener {
    ImmutableArray<Entity> entities;

    public CollisionSystem(World world){
        super(-1);
        world.setContactListener(this);
    }


    public void addedToEngine(Engine engine){
        entities = engine.getEntitiesFor(Family.all(BodyComponent.class).get());
    }

    @Override
    public void beginContact(Contact contact) {

        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        Fixture player = contact.getFixtureA().getUserData() == "player" ? contact.getFixtureA() : contact.getFixtureB();
        Fixture object = player == contact.getFixtureA() ? contact.getFixtureB() : contact.getFixtureA();

        int collisionBits = fixtureA.getFilterData().categoryBits | fixtureB.getFilterData().categoryBits;

        switch(collisionBits){
            case GameConfig.PLAYER_BIT | GameConfig.DOOR_BIT:
                ((Door)object.getUserData()).onContact();
                break;
            case GameConfig.PLAYER_BIT | GameConfig.SPAWN_POINT_BIT:
                ((SpawnPoint)object.getUserData()).onContact();
                break;
            case GameConfig.PLAYER_BIT | GameConfig.STATION_CHANGE_BIT:
                ((StationChanger)object.getUserData()).onContact();
                break;
        }


    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
