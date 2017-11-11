package com.korba.gameoff.oblivious.screens.dev.kubatest;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.korba.gameoff.oblivious.*;
import com.korba.gameoff.oblivious.assets.Assets;
import com.korba.gameoff.oblivious.config.GameConfig;
import com.korba.gameoff.oblivious.config.LauncherConfig;
import com.korba.gameoff.oblivious.screens.dev.BasicScreen;
import com.korba.gameoff.oblivious.screens.dev.kubatest.components.*;

public class GameTestScreen extends BasicScreen {

    private PlayerPhysics player;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;
    private MapManager mapManager;
    KeyboardHandler keyboardHandler;
    MouseHandler mouseHandler;
    Entity playerEntity;

    public GameTestScreen(SpriteBatch batch, ObscurityGame game, MapType type) {
        super(batch, game);
        camera = new OrthographicCamera();
        viewport = new FitViewport(LauncherConfig.WIDTH / GameConfig.PPM / 2,
                LauncherConfig.HEIGHT / GameConfig.PPM / 2, this.camera);
        world = new World(new Vector2(0, 0), true);
        debugRenderer = new Box2DDebugRenderer();
        mapManager = new MapManager(type, game, world);
        player = new PlayerPhysics(world, mapManager.getWorldCreator().getPlayerPosition());
        playerEntity = new Entity();
        PositionComponent positionComponent =
                new PositionComponent(mapManager.getWorldCreator().getPlayerPosition().x, mapManager.getWorldCreator().getPlayerPosition().y);
        playerEntity.add(new VelocityComponent(0,0))
                .add(positionComponent)
                .add(new SpriteComponent(new TextureRegion(Assets.manager.get(Assets.PLAYER, Texture.class), 0, 0, 32, 64)))
                .add(new RenderableComponent())
                .add(new BodyComponent(positionComponent, player.getBody()));
        game.getEntityManager().getEngine().addEntity(playerEntity);
        keyboardHandler = new KeyboardHandler(player, mapManager.getMapVelocity());
        mouseHandler = new MouseHandler(player, mapManager.getMapVelocity(), camera);
    }

    public void update(float delta){
        world.step(1 / 60f, 6, 2);
        mapManager.positionCamera(camera, player);
        keyboardHandler.handleInput();
        //mouseHandler.handleInput();

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
        //player.draw(batch);
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
        Gdx.input.setInputProcessor(mouseHandler);

    }
}
