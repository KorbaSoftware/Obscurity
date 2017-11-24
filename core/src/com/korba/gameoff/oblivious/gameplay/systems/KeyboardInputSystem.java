package com.korba.gameoff.oblivious.gameplay.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.korba.gameoff.oblivious.config.GameState;

public class KeyboardInputSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;


    public KeyboardInputSystem(){

    }

    public void addedToEngine(Engine engine){

    }

    public void update(float deltaTime){
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
           // if(GameState.getCurrentState() == GameState.State.RUNNING)
            GameState.setCurrentState(GameState.State.PAUSED);
           //else if(GameState.getCurrentState() == GameState.State.PAUSED)
            //    GameState.setCurrentState(GameState.State.RUNNING);
        }
    }

}
