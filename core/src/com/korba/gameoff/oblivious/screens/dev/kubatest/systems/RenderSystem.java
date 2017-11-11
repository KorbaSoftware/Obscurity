package com.korba.gameoff.oblivious.screens.dev.kubatest.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.korba.gameoff.oblivious.config.GameConfig;
import com.korba.gameoff.oblivious.screens.dev.kubatest.components.BodyComponent;
import com.korba.gameoff.oblivious.screens.dev.kubatest.components.PositionComponent;
import com.korba.gameoff.oblivious.screens.dev.kubatest.components.RenderableComponent;
import com.korba.gameoff.oblivious.screens.dev.kubatest.components.SpriteComponent;

public class RenderSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;
    private SpriteBatch batch;

    private ComponentMapper<PositionComponent> positionMap = ComponentMapper.getFor(PositionComponent.class);

    public RenderSystem(SpriteBatch batch){
        this.batch = batch;
    }

    public void addedToEngine(Engine engine){
        entities = engine.getEntitiesFor(Family.all(RenderableComponent.class, SpriteComponent.class, PositionComponent.class, BodyComponent.class).get());
    }

    public void update(float deltaTime){
        for (Entity entity : entities){
            BodyComponent bodyComponent = entity.getComponent(BodyComponent.class);
            SpriteComponent spriteComponent = entity.getComponent(SpriteComponent.class);
            PositionComponent positionComponent = entity.getComponent(PositionComponent.class);
            spriteComponent.sprite.setBounds(spriteComponent.sprite.getX(), spriteComponent.sprite.getY(), 32 / GameConfig.PPM, 64 / GameConfig.PPM);
            spriteComponent.sprite.setPosition(positionComponent.x - spriteComponent.sprite.getWidth() / 2,
                    positionComponent.y - spriteComponent.sprite.getHeight() / 2);
            //batch.draw(spriteComponent.sprite, positionComponent.x, positionComponent.y);
           spriteComponent.sprite.draw(batch);
        }
    }
}
