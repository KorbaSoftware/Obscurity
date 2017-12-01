package com.korba.gameoff.oblivious.tools;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.korba.gameoff.oblivious.ObscurityGame;

public class GameInputProcessor implements InputProcessor {

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.ESCAPE:
                switch (ObscurityGame.getGameState()) {
                    case RUNNING:
                        ObscurityGame.setGameState(ObscurityGame.GameState.PAUSED);
                        break;
                    case PAUSED:
                        ObscurityGame.setGameState(ObscurityGame.GameState.RUNNING);
                        break;
                    case CUTSCENE:
                        ObscurityGame.setGameState(ObscurityGame.GameState.RUNNING);
                }
                break;

            //BACKSPACE just to test new stage
            case Input.Keys.BACKSPACE:{
                switch (ObscurityGame.getGameState()){
                    case RUNNING:{
                        ObscurityGame.setGameState(ObscurityGame.GameState.CUTSCENE);
                        break;
                    }
                    case CUTSCENE:{
                        ObscurityGame.setGameState(ObscurityGame.GameState.RUNNING);
                    }
                }
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
