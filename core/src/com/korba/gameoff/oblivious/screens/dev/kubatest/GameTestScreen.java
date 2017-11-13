package com.korba.gameoff.oblivious.screens.dev.kubatest;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.korba.gameoff.oblivious.*;
import com.korba.gameoff.oblivious.config.GameConfig;
import com.korba.gameoff.oblivious.config.LauncherConfig;
import com.korba.gameoff.oblivious.gameplay.components.*;
import com.korba.gameoff.oblivious.gameplay.managers.EntityManager;
import com.korba.gameoff.oblivious.gameplay.managers.MapManager;
import com.korba.gameoff.oblivious.gameplay.managers.MapType;
import com.korba.gameoff.oblivious.gameplay.managers.PlayerManager;
import com.korba.gameoff.oblivious.gameplay.systems.KeyboardInputSys;
import com.korba.gameoff.oblivious.screens.dev.BasicScreen;
import com.korba.gameoff.oblivious.tools.AssetUtils;

public class GameTestScreen extends BasicScreen {

    private PlayerManager player;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;
    private MapManager mapManager;
    private Entity playerEntity;
    private Entity cameraEntity;
    private MapType type;

    public GameTestScreen(SpriteBatch batch, ObscurityGame game, MapType type) {
        super(batch, game);
        this.type = type;
        camera = new OrthographicCamera();
        viewport = new FitViewport(LauncherConfig.WIDTH / GameConfig.PPM / 2,
                LauncherConfig.HEIGHT / GameConfig.PPM / 2, this.camera);
        cameraEntity = new Entity();
        cameraEntity.add(new CameraComponent(camera));
        game.getEntityManager().getEngine().addEntity(cameraEntity);

    }

    private void createPlayerEntity(){
        player.getPhysics().setBodyPosition(mapManager.getLevelManager().getPlayerPosition());
        playerEntity = new Entity();
        playerEntity.add(new VelocityComponent(mapManager.getMapVelocity()))
                .add(new PositionComponent(mapManager.getLevelManager().getPlayerPosition().x, mapManager.getLevelManager().getPlayerPosition().y))
                .add(new SpriteComponent(new TextureRegion(new TextureRegion(new Texture(AssetUtils.PLAYER)), 0, 0, 32, 64)))
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

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        mapManager.getMapRenderer().render();
        debugRenderer.render(world, camera.combined);
        this.batch.setProjectionMatrix(camera.combined);
        this.batch.begin();
        game.getEntityManager().update(delta);
        this.batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        world.dispose();
        debugRenderer.dispose();
    }

    @Override
    public void show() {
        //TODO gettery do systemow
        if(type == MapType.ROOM) game.getEntityManager().getEngine().addSystem(game.getEntityManager().mouseInputSystem);
        if(type == MapType.OPEN) game.getEntityManager().getEngine().addSystem(game.getEntityManager().keyboardInputSys);
        world = game.getWorld();
        debugRenderer = new Box2DDebugRenderer();
        setPhysicsVisibility();
        mapManager = new MapManager(type, game, world);
        player = game.getEntityManager().getPlayer();
        createPlayerEntity();
    }
    private void setPhysicsVisibility(){
        debugRenderer.setDrawContacts(game.isDevMode());
        debugRenderer.setDrawInactiveBodies(game.isDevMode());
        debugRenderer.setDrawJoints(game.isDevMode());
        debugRenderer.setDrawVelocities(game.isDevMode());
        debugRenderer.setDrawAABBs(game.isDevMode());
        debugRenderer.setDrawBodies(game.isDevMode());
    }
}