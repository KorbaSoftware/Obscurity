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
    private MetroStationManager lastStationEntered;
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
            stations = new Array<>();
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
                    MetroStationManager temporaryStationObject = new MetroStationManager(game, world, maps.get(types.indexOf(type, true)), this , type);
                    temporaryStationObject.setInactive();
                    stations.add(temporaryStationObject);
                }

                    IndoorLevelManager temporaryIndoorLevelObject = new IndoorLevelManager(game, world, maps.get(types.indexOf(type, true)), this , type);
                    temporaryIndoorLevelObject.setInactive();
                    levels.add(temporaryIndoorLevelObject);

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

        if(type == MapType.METROSTATION1 || type == MapType.METROSTATION2 || type == MapType.METROSTATION3){
            switch (type){
                case METROSTATION1:
                    lastStationEntered = stations.get(0);
                    break;
                case METROSTATION2:
                    lastStationEntered = stations.get(1);
                    break;
                case METROSTATION3:
                    lastStationEntered = stations.get(2);
                    break;
            }
            for (MetroStationManager station : stations){
                if(station.getMapType() == type){
                    if(currentLevel != worldLevelManager)
                    setupRoom(station, station.getAfterTravelSpawnPoint());
                    else setupRoom(station);
                }
            }
        }
        else {
            for (IndoorLevelManager levelManager : levels){
                if(levelManager.getMapType() == type){
                    setupRoom(levelManager);
                }
            }
        }
        mapRenderer.setMap(currentMap);
        mapToChange = true;

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

    public void previousStation() {
        changeStation(-1);
    }

    public void nextStation() {
        changeStation(1);
    }

    public void changeStation(int direction){
        for(MetroStationManager station : stations){
            if(lastStationEntered.getMapType() == station.getMapType()){
                int index = stations.indexOf(station, false) + direction;
                worldLevelManager.setLastSpawnPoint(worldLevelManager.getExit(stations.get(index).getMapType()));
                lastStationEntered = stations.get(index);
                changeMap(lastStationEntered.getMapType());
                game.getEntityManager().getMouseMovementSystem().nullify();
                break;
            }
        }
    }

    private void setupRoom(IndoorLevelManager level){
        currentMap = level.getMap();
        currentLevel.setInactive();
        currentLevel = level;
        currentLevel.setActive();
        position = level.getPlayerPosition();
        game.getEntityManager().setMouseInput();
        this.type = level.getMapType();
        System.out.println(this.type.toString());
        mapVelocity = 6;
        Gdx.app.debug("MapManago","changed to ROOM");
    }

    private void setupRoom(IndoorLevelManager level, Vector2 position){
        setupRoom(level);
        this.position = position;
    }
}
