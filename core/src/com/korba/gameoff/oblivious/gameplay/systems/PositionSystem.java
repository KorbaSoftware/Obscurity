package com.korba.gameoff.oblivious.gameplay.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.korba.gameoff.oblivious.config.GameConfig;
import com.korba.gameoff.oblivious.gameplay.components.BodyComponent;
import com.korba.gameoff.oblivious.gameplay.components.PositionComponent;
import com.korba.gameoff.oblivious.gameplay.components.VelocityComponent;


public class PositionSystem extends IteratingSystem {
    private ComponentMapper<PositionComponent> positionMap = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<BodyComponent> bodyMap = ComponentMapper.getFor(BodyComponent.class);

    public PositionSystem() {
        super(Family.all(PositionComponent.class, VelocityComponent.class, BodyComponent.class).get());
    }

    public void processEntity(Entity entity, float deltaTime) {
        PositionComponent positionCom = positionMap.get(entity);
        BodyComponent bodyCom = bodyMap.get(entity);

        if (bodyCom != null) {
            positionCom.x = bodyCom.body.getPosition().x;
            positionCom.y = bodyCom.body.getPosition().y;
        }
        }
    }
