package com.korba.gameoff.oblivious.gameplay.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.korba.gameoff.oblivious.config.GameConfig;
import com.korba.gameoff.oblivious.gameplay.components.BodyComponent;
import com.korba.gameoff.oblivious.gameplay.components.PositionComponent;
import com.korba.gameoff.oblivious.gameplay.components.RenderableComponent;
import com.korba.gameoff.oblivious.gameplay.components.SpriteComponent;

public class RenderSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;
    private SpriteBatch batch;

    public RenderSystem(SpriteBatch batch) {
        this.batch = batch;
    }

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(RenderableComponent.class, SpriteComponent.class, PositionComponent.class, BodyComponent.class).get());
    }

    public void update(float deltaTime) {
        for (Entity entity : entities) {
            SpriteComponent spriteComponent = entity.getComponent(SpriteComponent.class);
            PositionComponent positionComponent = entity.getComponent(PositionComponent.class);
            spriteComponent.sprite.setBounds(spriteComponent.sprite.getX(), spriteComponent.sprite.getY(),
                    32 / GameConfig.PPM, spriteComponent.sprite.getRegionHeight() / GameConfig.PPM);
            spriteComponent.sprite.setPosition(positionComponent.x - spriteComponent.sprite.getWidth() / 2,
                    positionComponent.y + spriteComponent.offset / GameConfig.PPM - spriteComponent.sprite.getHeight() / 2);
            spriteComponent.sprite.draw(batch);
        }
    }
}
