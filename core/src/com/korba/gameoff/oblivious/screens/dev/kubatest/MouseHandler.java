package com.korba.gameoff.oblivious.screens.dev.kubatest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MouseHandler implements InputProcessor{

    private PlayerPhysics player;
    private float velocity;
    private OrthographicCamera camera;

    public MouseHandler(PlayerPhysics player, float velocity, OrthographicCamera camera){
        this.player = player;
        this.velocity = velocity;
        this.camera = camera;
    }

    public void handleInput(){
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) || Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
            System.out.println(player.getBody().getWorldPoint(player.getBody().localPoint2).x / 2);
            System.out.println(player.getBody().getWorldPoint(player.getBody().localPoint2).y / 2);
            System.out.println(camera.position.x);
            System.out.println(camera.position.y);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println(screenX);
        System.out.println(screenY);
        System.out.println(pointer);
        System.out.println(button);
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
