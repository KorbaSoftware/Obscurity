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
import com.korba.gameoff.oblivious.gameplay.managers.levels.IndoorLevelManager;
import com.korba.gameoff.oblivious.gameplay.managers.levels.LevelManager;
import com.korba.gameoff.oblivious.gameplay.managers.levels.MetroStationManager;
import com.korba.gameoff.oblivious.gameplay.managers.levels.WorldLevelManager;
import com.korba.gameoff.oblivious.tools.*;
import com.korba.gameoff.oblivious.gameplay.player.PlayerPhysics;

public class MapManager {
    private World world;
    private ObscurityGame game;
    private TmxMapLoader mapLoader;
    private TiledMap currentMap;
    private LevelManager currentLevel;
    private Vector2 position;
    private TiledMap worldMap;
    private OrthogonalTiledMapRenderer mapRenderer;
    private WorldLevelManager worldLevelManager;
    private MapType type;
    private float mapVelocity;
    private Array<TiledMap> maps = null;
    private Array<MapType> types = null;
    private Array<MetroStationManager> stations = null;
    private Array<IndoorLevelManager> levels = null;
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
        worldLevelManager = new WorldLevelManager(game, world, currentMap, this);
        currentLevel = worldLevelManager;
        worldLevelManager.setActive();
    }


    public void positionCamera(OrthographicCamera camera, PlayerPhysics player){
        if (this.type == MapType.OPEN){

            camera.position.x = player.getBody().getPosition().x;
            camera.position.y = player.getBody().getPosition().y;
        }
        else {
            TiledMapTileLayer layer0 = (TiledMapTileLayer) mapRenderer.getMap().getLayers().get(0);
            Vector3 center = new Vector3(layer0.getWidth() * layer0.getTileWidth() / 2 / GameConfig.PPM,
                    layer0.getHeight() * layer0.getTileHeight() / 2 / GameConfig.PPM, 0);
            camera.position.set(center);
        }
    }

    public boolean isMapToChange(){
        return mapToChange;
    }

    private void loadLevels(){
            maps = new Array<>();
            types = new Array<>();
            levels = new Array<>();
            maps.add(AssetUtils.getMap(AssetUtils.MAP_METRO));
            types.add(MapType.METROSTATION1);
            maps.add(AssetUtils.getMap(AssetUtils.MAP_METRO2));
            types.add(MapType.METROSTATION2);
            maps.add(AssetUtils.getMap(AssetUtils.MAP_METRO3));
            types.add(MapType.METROSTATION3);
            maps.add(AssetUtils.getMap(AssetUtils.MAP_TEST1));
            types.add(MapType.MOTELROOM1);
            maps.add(AssetUtils.getMap(AssetUtils.MAP_TEST2));
            types.add(MapType.MOTELROOM2);
            maps.add(AssetUtils.getMap(AssetUtils.MAP_TEST3));
            types.add(MapType.MOTELROOM3);


            for (MapType type : types){
                if (type == MapType.METROSTATION1 || type == MapType.METROSTATION2 || type == MapType.METROSTATION3){
                    levels.add(new MetroStationManager(game, world, maps.get(types.indexOf(type, true)), this , type));
                }
                levels.add(new IndoorLevelManager(game, world, maps.get(types.indexOf(type, true)), this , type));
            }
    }

    public void changeMap(){

            currentMap = worldMap;
            currentLevel.setInactive();
            currentLevel = worldLevelManager;
            currentLevel.setActive();
            position = worldLevelManager.getLastSpawnPoint();
            game.getEntityManager().setKeyboardInput();
            type = MapType.OPEN;
        System.out.println(this.type.toString());
            mapVelocity = 12;
            Gdx.app.debug("MapManago", "changed to OPEN");

        mapRenderer.setMap(currentMap);
        mapToChange = true;

    }

    public void changeMap(MapType type){
        for (IndoorLevelManager levelManager : levels){
                if(levelManager.getMapType() == type){
                    currentMap = levelManager.getMap();
                    currentLevel.setInactive();
                    currentLevel = levelManager;
                    currentLevel.setActive();
                    position = levelManager.getPlayerPosition();
                    game.getEntityManager().setMouseInput();
                    this.type = levelManager.getMapType();
                    System.out.println(this.type.toString());
                    mapVelocity = 6;
                    Gdx.app.debug("MapManago","changed to ROOM");
                }
            mapRenderer.setMap(currentMap);
            mapToChange = true;
        }
    }

    public void setMapToChange(boolean mapToChange) {this.mapToChange = mapToChange;}
    public LevelManager getWorldLevelManager() {
        return worldLevelManager;
    }
    public OrthogonalTiledMapRenderer getMapRenderer() {
        return mapRenderer;
    }
    public float getMapVelocity() {
        return mapVelocity;
    }
    public MapType getType() { return type;}
    public Vector2 getPosition(){return position; }
    public LevelManager getCurrentLevel() {  return currentLevel; }

    public void previousStation() {
      //  currentLevel.setInactive();
        Gdx.app.debug("MapManager",  "poprzednia stacja");

    }

    public void nextStation() {
       // currentLevel.setInactive();
        Gdx.app.debug("MapManager",  "nastepna stacja");
    }
}
