package com.korba.gameoff.oblivious.gameplay.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.korba.gameoff.oblivious.ObscurityGame;
import com.korba.gameoff.oblivious.config.GameConfig;
import com.korba.gameoff.oblivious.tools.*;
import com.korba.gameoff.oblivious.gameplay.player.PlayerPhysics;

public class MapManager {
    private World world;
    private ObscurityGame game;
    private TmxMapLoader mapLoader;
    private TiledMap currentMap;
    private Vector2 position;
    private TiledMap worldMap;
    private OrthogonalTiledMapRenderer mapRenderer;
    private LevelManager levelManager;
    private MapType type;
    private float mapVelocity;
    private String mapPath;
    private Array<TiledMap> maps = null;
    private Array<LevelManager> levels = null;
    private boolean mapToChange = false;

    public MapManager(MapType type, ObscurityGame game, World world){
        this.type = type;
        this.game = game;
        this.world = world;
        loadLevels();
        mapLoader = new TmxMapLoader();
        worldMap = AssetUtils.getMap(AssetUtils.MAP_TEST0);
        mapVelocity = 12;
        currentMap = worldMap;
        mapRenderer = new OrthogonalTiledMapRenderer(currentMap, 1f / GameConfig.PPM);
        levelManager = new LevelManager(game, world, currentMap, this);
    }


    public void positionCamera(OrthographicCamera camera, PlayerPhysics player){
        switch(type){
            case OPEN:{
                camera.position.x = player.getBody().getPosition().x;
                camera.position.y = player.getBody().getPosition().y;
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

    public boolean update(float delta){
        mapRenderer.render();
        return mapToChange;
    }

    private void loadLevels(){
            maps = new Array<>();
            levels = new Array<>();
            maps.add(AssetUtils.getMap(AssetUtils.MAP_TEST1));

            for (TiledMap map : maps){
                levels.add(new LevelManager(game, world, map, this ));
            }
    }

    public void changeMap(){
        if (type == MapType.OPEN){
            currentMap = levels.first().getMap();
            position = levels.first().getPlayerPosition();
            type = MapType.ROOM;
            Gdx.app.debug("MapManago","changed to ROOM");
        }
        else if (type == MapType.ROOM){
            currentMap = worldMap;
            position = levelManager.getPlayerPosition();
            type = MapType.OPEN;
            Gdx.app.debug("MapManago", "changed to OPEN");
        }
        mapRenderer.setMap(currentMap);
        mapToChange = true;

    }

    private void setMapVelocity(float velocity){
        this.mapVelocity = velocity;
    }
    public void setMapToChange(boolean mapToChange) {this.mapToChange = mapToChange;}
    public LevelManager getLevelManager() {
        return levelManager;
    }
    public OrthogonalTiledMapRenderer getMapRenderer() {
        return mapRenderer;
    }
    public float getMapVelocity() {
        return mapVelocity;
    }
    public MapType getType() { return type;}
    public Vector2 getPosition(){return position; }
}
