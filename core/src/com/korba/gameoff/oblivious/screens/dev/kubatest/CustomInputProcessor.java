package com.korba.gameoff.oblivious.screens.dev.kubatest;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class CustomInputProcessor implements InputProcessor {
    private Player player;
    public CustomInputProcessor(Player player){
        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch(keycode){
            case Input.Keys.UP:
                player.body.setLinearVelocity(player.body.getLinearVelocity().x, 10);
                break;
            case Input.Keys.DOWN:
                player.body.setLinearVelocity(player.body.getLinearVelocity().x, -10);
                break;
            case Input.Keys.RIGHT:
                player.body.setLinearVelocity(10, player.body.getLinearVelocity().y);
                break;
            case Input.Keys.LEFT:
                player.body.setLinearVelocity(-10, player.body.getLinearVelocity().y);
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch(keycode){
            case Input.Keys.UP:
                player.body.setLinearVelocity(player.body.getLinearVelocity().x, 0);
                break;
            case Input.Keys.DOWN:
                player.body.setLinearVelocity(player.body.getLinearVelocity().x, 0);
                break;
            case Input.Keys.RIGHT:
                player.body.setLinearVelocity(0, player.body.getLinearVelocity().y);
                break;
            case Input.Keys.LEFT:
                player.body.setLinearVelocity(0, player.body.getLinearVelocity().y);
                break;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
