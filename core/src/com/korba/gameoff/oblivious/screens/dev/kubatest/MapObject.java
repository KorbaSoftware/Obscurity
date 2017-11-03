package com.korba.gameoff.oblivious.screens.dev.kubatest;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.korba.gameoff.oblivious.config.GameConfig;

public abstract class MapObject {
        protected World world;
        protected TiledMap map;
        protected TiledMapTile tile;
        protected Rectangle bounds;
        protected Body body;
        protected Fixture fixture;

        public MapObject(World world, TiledMap map, Rectangle bounds) {
            this.world = world;
            this.map = map;
            this.bounds = bounds;

            BodyDef bodyDef = new BodyDef();
            FixtureDef fixtureDef = new FixtureDef();
            PolygonShape polyShape = new PolygonShape();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((bounds.getX() + bounds.getWidth() / 2) / GameConfig.PPM,
                    (bounds.getY() + bounds.getHeight() / 2) / GameConfig.PPM);
            body = world.createBody(bodyDef);
            polyShape.setAsBox(bounds.getWidth() / 2 / GameConfig.PPM,
                    bounds.getHeight() / 2 / GameConfig.PPM);
            fixtureDef.shape = polyShape;
            fixture = body.createFixture(fixtureDef);
        }

        public abstract void onHit();

        public void setCategoryFilter(short filterBit) {
            Filter filter = new Filter();
            filter.categoryBits = filterBit;
            fixture.setFilterData(filter);
        }

        public TiledMapTileLayer.Cell getCell() {
            TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
            int xCoord = (int) (body.getPosition().x * GameConfig.PPM / GameConfig.TILE_SIZE);
            int yCoord = (int) (body.getPosition().y * GameConfig.PPM / GameConfig.TILE_SIZE);
            return layer.getCell(xCoord, yCoord);
        }

    }

