package com.korba.gameoff.oblivious.ui.inside;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.viewport.*;
import com.korba.gameoff.oblivious.equipment.*;
import com.korba.gameoff.oblivious.tools.*;

public class InsideUI implements Screen{

    private Stage stage;
    private Camera camera;
    private Viewport viewport;

    private EqUI eqUI;
    private InventoryUI inventoryUI;

    public InsideUI(Camera camera) {
        this.camera = camera;
        viewport = new ScreenViewport(camera);
        stage = new Stage(viewport);
        stage.setDebugAll(true);

        eqUI = new EqUI(AssetUtils.DEFAULT_SKIN);
        eqUI.setKeepWithinStage(false);
        eqUI.setMovable(false);
        eqUI.setVisible(false);
        eqUI.setPosition(stage.getWidth() / 2 - 64 * 3, stage.getHeight() / 2 - 64*3);

        inventoryUI = new InventoryUI();
        inventoryUI.setKeepWithinStage(false);
        inventoryUI.setMovable(false);
        inventoryUI.setVisible(false);
        inventoryUI.setPosition(stage.getWidth() / 2 - 64 * 3, stage.getHeight() / 2 - 64*3);

        stage.addActor(eqUI);
        stage.addActor(inventoryUI);
        eqUI.validate();
        inventoryUI.validate();

        Array<Actor> eqActors = eqUI.getEqActors();
        for(Actor actor : eqActors)
            stage.addActor(actor);

        Array<Actor> invActors = inventoryUI.getInventoryActors();
        for(Actor actor : invActors)
            stage.addActor(actor);

        TextButton btnEQ = new TextButton("Old EQ", AssetUtils.DEFAULT_SKIN);
        btnEQ.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                eqUI.setVisible(eqUI.isVisible() ? false : true);
            }
        });
        btnEQ.setBounds(20, 20, 50 ,50);

        TextButton invEQ = new TextButton("New EQ", AssetUtils.DEFAULT_SKIN);
        invEQ.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                inventoryUI.setVisible(inventoryUI.isVisible() ? false : true);
            }
        });
        invEQ.setBounds(80, 20, 50, 50);

        stage.addActor(btnEQ);
        stage.addActor(invEQ);
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
