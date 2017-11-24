package com.korba.gameoff.oblivious.tools;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.korba.gameoff.oblivious.ObscurityGame;
import com.korba.gameoff.oblivious.config.GameState;

public class GameInputProcessor implements InputProcessor {
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch(keycode){
            case Input.Keys.ESCAPE:
                switch(GameState.getCurrentState()){
                    case RUNNING:
                        GameState.setCurrentState(GameState.State.PAUSED);
                        break;
                    case PAUSED:
                        GameState.setCurrentState(GameState.State.RUNNING);
                        break;
                }
                break;
        }
        return true;
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
