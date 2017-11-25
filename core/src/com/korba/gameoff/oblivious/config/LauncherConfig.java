package com.korba.gameoff.oblivious.config;

public class LauncherConfig {

    public static final String APP_NAME = "Obscurity(pre-alpha)";
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static boolean vSync = false;
    public static final int FOREGROUND_FPS = 60;
    public static final int BACKGROUND_FPS = 10;
    public static final boolean FULLSCREEN = false;
    public static final boolean RESIZABLE = false;
    public static final float LOADING_SCREEN_LOGO_LIFETIME = (GameConfig.IS_DEVMODE) ? 0f : 3f;

}
