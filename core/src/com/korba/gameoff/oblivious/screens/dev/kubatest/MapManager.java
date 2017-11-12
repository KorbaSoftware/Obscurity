package com.korba.gameoff.oblivious.screens.dev.kubatest;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.korba.gameoff.oblivious.ObscurityGame;
import com.korba.gameoff.oblivious.config.GameConfig;
import com.korba.gameoff.oblivious.tools.*;

public class MapManager {
    private TmxMapLoader mapLoader;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer mapRenderer;
    private WorldCreator worldCreator;
    private MapType type;
    private float mapVelocity;
    private String mapPath;

    public MapManager(MapType type, ObscurityGame game, World world){
        this.type = type;
        mapLoader = new TmxMapLoader();
        chooseMapProperties();
        //tiledMap = mapLoader.load(mapPath);
        tiledMap = AssetUtils.getMap(mapPath);
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1f / GameConfig.PPM);
        worldCreator = new WorldCreator(game, world, tiledMap);
    }
    private void chooseMapProperties(){
        switch (this.type){
            case OPEN:
                setMapVelocity(12);
                this.mapPath = AssetUtils.MAP_TEST0;
                break;
            case ROOM:
                setMapVelocity(6);
                this.mapPath = AssetUtils.MAP_TEST1;
                break;
        }
    }

    public void positionCamera(OrthographicCamera camera, Player player){
        switch(type){
            case OPEN:{
                camera.position.x = player.body.getPosition().x;
                camera.position.y = player.body.getPosition().y;
            }
            break;
            case ROOM:{
                TiledMapTileLayer layer0 = (TiledMapTileLayer) mapRenderer.getMap().getLayers().get(0);
                Vector3 center = new Vector3(layer0.getWidth() * layer0.getTileWidth() / 2 / GameConfig.PPM,
                                             layer0.getHeight() * layer0.getTileHeight() / 2 / GameConfig.PPM, 0);
                camera.position.set(center);
            }
            break;
        }
    }

    private void setMapVelocity(float velocity){
        this.mapVelocity = velocity;
    }

    public WorldCreator getWorldCreator() {
        return worldCreator;
    }
    public OrthogonalTiledMapRenderer getMapRenderer() {
        return mapRenderer;
    }
    public float getMapVelocity() {
        return mapVelocity;
    }

}
