package com.korba.gameoff.oblivious.ui.inside;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.viewport.*;
import com.korba.gameoff.oblivious.config.*;
import com.korba.gameoff.oblivious.inventory.items.*;
import com.korba.gameoff.oblivious.tools.*;

public class InsideUI implements Screen {

    private final Stage stage;
    private final Viewport viewport;

    private final InventoryUI inventoryUI;

     InsideUI(Camera camera) {
        viewport = new ScreenViewport(camera);
        stage = new Stage(viewport);
        stage.setDebugAll(true);

        inventoryUI = new InventoryUI();
        inventoryUI.setKeepWithinStage(false);
        inventoryUI.setMovable(false);
        inventoryUI.setVisible(false);
        inventoryUI.setPosition(stage.getWidth() / 2 - 64 * 2, stage.getHeight() / 2 - 64*2);

        stage.addActor(inventoryUI);
        inventoryUI.addItemToInventory(new Item(AssetUtils.getTexture(AssetUtils.ITEM_RADIO), "Broken radio", true, ItemsIds.BROKEN_RADIO));
        inventoryUI.addItemToInventory(new Item(AssetUtils.getTexture(AssetUtils.ITEM_PINDOL), "Pindol", true, ItemsIds.PINDOL));
        inventoryUI.validate();


        Array<Actor> invActors = inventoryUI.getInventoryActors();
        for(Actor actor : invActors)
            stage.addActor(actor);


        TextButton invEQ = new TextButton("New EQ", AssetUtils.DEFAULT_SKIN);
        invEQ.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                inventoryUI.setVisible(!inventoryUI.isVisible());
            }
        });
        invEQ.setBounds(20, 20, 50, 50);

        stage.addActor(invEQ);

        setAllDebug(!GameConfig.IS_DEVMODE);
    }

    public Stage getStage() {
        return stage;
    }

    private void setAllDebug(boolean debug) {
        stage.setDebugAll(debug);
    }

    @Override
    public void show() {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

    @Override
    public void resume() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void hide() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
