package com.korba.gameoff.oblivious.screens.dev.kubatest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class KeyboardHandler{
    private Player player;
    private float velocity;
    public KeyboardHandler(Player player, float velocity){
        this.player = player;
        this.velocity = velocity;
    }
    public void handleInput(){
        if        (Gdx.input.isKeyPressed(Input.Keys.DOWN)
                || Gdx.input.isKeyPressed(Input.Keys.UP)
                || Gdx.input.isKeyPressed(Input.Keys.RIGHT)
                || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if(Gdx.input.isKeyPressed(Input.Keys.UP)){
                player.body.setLinearVelocity(0, velocity);
            }
            if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
                player.body.setLinearVelocity(0, -velocity);
            }
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                player.body.setLinearVelocity(-velocity, 0);
            }
            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                player.body.setLinearVelocity(velocity, 0);
            }
        } else {
            player.body.setLinearVelocity(0, 0);
        }

    }


}
