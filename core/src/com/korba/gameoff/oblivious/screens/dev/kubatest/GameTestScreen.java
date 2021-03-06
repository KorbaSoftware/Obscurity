package com.korba.gameoff.oblivious.screens.dev.kubatest;

import box2dLight.RayHandler;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.korba.gameoff.oblivious.*;
import com.korba.gameoff.oblivious.config.GameConfig;
import com.korba.gameoff.oblivious.config.LauncherConfig;
import com.korba.gameoff.oblivious.gameplay.components.*;
import com.korba.gameoff.oblivious.gameplay.managers.MapManager;
import com.korba.gameoff.oblivious.gameplay.managers.MapType;
import com.korba.gameoff.oblivious.gameplay.managers.PlayerManager;
import com.korba.gameoff.oblivious.screens.dev.BasicScreen;

public class GameTestScreen extends BasicScreen {

    private PlayerManager player;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;
    private MapManager mapManager;
    private Entity playerEntity;
    private Entity cameraEntity;
    private RayHandler rayHandler;

    public GameTestScreen(SpriteBatch batch, ObscurityGame game) {
        super(batch, game);
    }

    @Override
    public void show() {
        setViewportAndCamera();
        world = game.getWorld();
        mapManager = new MapManager(MapType.OPEN, game, world);
        game.getEntityManager().getEngine().addSystem(game.getEntityManager().getKeyboardMovementSystem());
        debugRenderer = new Box2DDebugRenderer();
        setPhysicsVisibility(GameConfig.IS_DEVMODE);
        createPlayerEntity();
        setLights();
    }

    private void setViewportAndCamera(){
        camera = new OrthographicCamera();
        viewport = new FitViewport(LauncherConfig.WIDTH / GameConfig.PPM / 2,
                LauncherConfig.HEIGHT / GameConfig.PPM / 2, this.camera);
        cameraEntity = new Entity();
        cameraEntity.add(new CameraComponent(camera));
        game.getEntityManager().getEngine().addEntity(cameraEntity);
    }

    private void createPlayerEntity(){
        player = game.getEntityManager().getPlayer();
        player.setSpriteType(mapManager.getType());
        player.getPhysics().setBodyPosition(mapManager.getWorldLevelManager().getPlayerPosition());
        playerEntity = new Entity();
        playerEntity.add(new VelocityComponent(mapManager.getMapVelocity()))
                .add(new PositionComponent(mapManager.getWorldLevelManager().getPlayerPosition().x, mapManager.getWorldLevelManager().getPlayerPosition().y))
                .add(new SpriteComponent(player.getSprite().getTextureRegion()))
                .add(new RenderableComponent())
                .add(new PlayerComponent())
                .add(new BodyComponent(player.getPhysics().getBody()));
        game.getEntityManager().getEngine().addEntity(playerEntity);
    }

    public void update(float delta){
        world.step(1 / 60f, 6, 2);
        mapManager.positionCamera(camera, player.getPhysics());
        camera.update();
        mapManager.getMapRenderer().setView(camera);
    }

    private void setLights(){
        rayHandler = new RayHandler(game.getWorld());
        Color ambientColor = GameConfig.AMBIENT_LIGHT_COLOR;
        ambientColor.a = GameConfig.AMBIENT_LIGHT_STRENGTH;
        player.createLight(rayHandler);
        rayHandler.setAmbientLight(ambientColor);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        if(mapManager.isMapToChange()){
            MapType type = mapManager.getType();
            mapManager.setMapToChange(false);
            player.getPhysics().setBodyPosition(mapManager.getPosition());
            player.setSpriteType(mapManager.getType());
            Gdx.app.debug("render",mapManager.getType().name());
            playerEntity.remove(SpriteComponent.class);
            playerEntity.add(new SpriteComponent(player.getSprite().getTextureRegion()));
            playerEntity.getComponent(VelocityComponent.class).velocity = mapManager.getMapVelocity();
            mapManager.positionCamera(camera, player.getPhysics());

        }
        mapManager.getMapRenderer().render();
        debugRenderer.render(world, camera.combined);
        this.batch.setProjectionMatrix(camera.combined);
        this.batch.begin();
        game.getEntityManager().update(delta);
        this.batch.end();
        rayHandler.updateAndRender();
        rayHandler.setCombinedMatrix(camera);
    }

    private void setPhysicsVisibility(boolean value){
        debugRenderer.setDrawVelocities(value);
        debugRenderer.setDrawAABBs(value);
        debugRenderer.setDrawBodies(value);
        debugRenderer.setDrawContacts(value);
        debugRenderer.setDrawInactiveBodies(value);
        debugRenderer.setDrawJoints(value);
    }

    @Override
    public void dispose() {
        super.dispose();
        world.dispose();
        debugRenderer.dispose();
        rayHandler.dispose();
    }
}