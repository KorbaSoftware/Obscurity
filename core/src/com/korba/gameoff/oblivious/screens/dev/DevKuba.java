package com.korba.gameoff.oblivious.screens.dev;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.korba.gameoff.oblivious.*;
import com.korba.gameoff.oblivious.config.GameConfig;
import com.korba.gameoff.oblivious.config.LauncherConfig;
import com.korba.gameoff.oblivious.screens.dev.kubatest.CustomInputProcessor;
import com.korba.gameoff.oblivious.screens.dev.kubatest.Player;
import com.korba.gameoff.oblivious.screens.dev.kubatest.WorldCreator;

public class DevKuba extends BasicScreen {

    private ObscurityGame game;
    private TmxMapLoader mapLoader;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer mapRenderer;
    private Player player;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;
    private WorldCreator worldCreator;
    private CustomInputProcessor inputProcessor;


    public DevKuba(SpriteBatch batch, ObscurityGame game){
        super(batch, game);
    }

    public DevKuba(SpriteBatch batch, ObscurityGame game, String path) {
        super(batch, game);
        camera = new OrthographicCamera();
        viewport = new FitViewport(LauncherConfig.WIDTH / GameConfig.PPM / 2,
                LauncherConfig.HEIGHT / GameConfig.PPM / 2, this.camera);
        mapLoader = new TmxMapLoader();
        tiledMap = mapLoader.load(path);
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1f / GameConfig.PPM);
        world = new World(new Vector2(0, 0), true);
        debugRenderer = new Box2DDebugRenderer();
        worldCreator = new WorldCreator(game, world, tiledMap);
        player = new Player(world, worldCreator.getPlayerPosition());
        inputProcessor = new CustomInputProcessor(player);

    }

    public void update(float delta){
        world.step(1 / 60f, 6, 2);
        camera.position.x = player.body.getPosition().x;
        camera.position.y = player.body.getPosition().y;
        player.update(delta);
        camera.update();
        mapRenderer.setView(camera);
    }

    @Override
    public void render(float delta) {
    update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        mapRenderer.render();
        debugRenderer.render(world, camera.combined);
        this.batch.setProjectionMatrix(camera.combined);
        this.batch.begin();
        player.draw(batch);
        this.batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        world.dispose();
        mapRenderer.dispose();
        tiledMap.dispose();
        debugRenderer.dispose();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputProcessor);
    }
}
