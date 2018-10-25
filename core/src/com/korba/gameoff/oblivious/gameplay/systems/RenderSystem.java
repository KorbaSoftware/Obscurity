package com.korba.gameoff.oblivious.gameplay.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.korba.gameoff.oblivious.gameplay.components.BodyComponent;
import com.korba.gameoff.oblivious.gameplay.components.PositionComponent;
import com.korba.gameoff.oblivious.gameplay.components.RenderableComponent;
import com.korba.gameoff.oblivious.gameplay.components.SpriteComponent;
import com.korba.gameoff.oblivious.gameplay.managers.*;

import static com.korba.gameoff.oblivious.config.GameConfig.*;
import static com.korba.gameoff.oblivious.gameplay.player.Player.*;

public class RenderSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;
    private final SpriteBatch batch;
    private final PlayerManager player;

    private float stateTime;

    public RenderSystem(SpriteBatch batch, PlayerManager player) {
        stateTime = Gdx.graphics.getFramesPerSecond();
        this.batch = batch;
        this.player = player;
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(RenderableComponent.class, SpriteComponent.class, PositionComponent.class, BodyComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        for (Entity entity : entities) {
            SpriteComponent spriteComponent = entity.getComponent(SpriteComponent.class);
            PositionComponent positionComponent = entity.getComponent(PositionComponent.class);
            spriteComponent.sprite.setBounds(spriteComponent.sprite.getX(), spriteComponent.sprite.getY(),
                    32f / PPM, (float) spriteComponent.sprite.getRegionHeight() / PPM);
            spriteComponent.sprite.setPosition(positionComponent.x - spriteComponent.sprite.getWidth() / 2,
                    positionComponent.y + (float) spriteComponent.offset / PPM - spriteComponent.sprite.getHeight() / 2);

            if (entity.equals(entities.first())) {
                stateTime += deltaTime;
                DIRECTION direction = player.getDirection();

                boolean walking = (player.getState().equals(STATE.WALKING));

                if (player.getSize().equals(SIZE.SMALL)) {
                    if (direction.equals(DIRECTION.UP)) {
                        spriteComponent.sprite.setRegion((TextureRegion) player.getAnimations().get("WALK_UP_32").getKeyFrame(walking ? stateTime : 0));
                    } else if (direction.equals(DIRECTION.DOWN)) {
                        spriteComponent.sprite.setRegion((TextureRegion) player.getAnimations().get("WALK_DOWN_32").getKeyFrame(walking ? stateTime : 0));
                    } else if (direction.equals(DIRECTION.LEFT)) {
                        spriteComponent.sprite.setRegion((TextureRegion) player.getAnimations().get("WALK_LEFT_32").getKeyFrame(walking ? stateTime : 0));
                    } else if (direction.equals(DIRECTION.RIGHT)) {
                        spriteComponent.sprite.setRegion((TextureRegion) player.getAnimations().get("WALK_RIGHT_32").getKeyFrame(walking ? stateTime : 0));
                    }
                } else {
                    if (direction.equals(DIRECTION.UP)) {
                        spriteComponent.sprite.setRegion((TextureRegion) player.getAnimations().get("WALK_UP_64").getKeyFrame(walking ? stateTime : 0));
                    } else if (direction.equals(DIRECTION.DOWN)) {
                        spriteComponent.sprite.setRegion((TextureRegion) player.getAnimations().get("WALK_DOWN_64").getKeyFrame(walking ? stateTime : 0));
                    } else if (direction.equals(DIRECTION.LEFT)) {
                        spriteComponent.sprite.setRegion((TextureRegion) player.getAnimations().get("WALK_SIDE_64").getKeyFrame(walking ? stateTime : 0));
                        spriteComponent.sprite.flip(true, false);
                    } else if (direction.equals(DIRECTION.RIGHT)) {
                        spriteComponent.sprite.setRegion((TextureRegion) player.getAnimations().get("WALK_SIDE_64").getKeyFrame(walking ? stateTime : 0));
                    }
                }
            }
            spriteComponent.sprite.draw(batch);
        }
    }
}
