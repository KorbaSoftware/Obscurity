package com.korba.gameoff.oblivious.config;

public class GameState {

    public enum State {
        RUNNING,
        PAUSED
    }
    private static State currentState; {

    }

    public static State getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(State state) {
        currentState = state;
    }
}
