package com.korba.gameoff.oblivious.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

    // Textures
    public static final String LOAD_GAME    = "buttons/load_game.png";
    public static final String NEW_GAME     = "buttons/new_game.png";
    public static final String EXIT         = "buttons/exit.png";
    public static final String OPTIONS      = "buttons/options.png";
    public static final String BACKGROUND   = "menu_background.png";
    public static final String KORBA_LOGO   = "korba_logo.png";
    public static final String GAME_LOGO    = "game_logo.png";

    //Pixmaps
    public static final String CURSOR       = "custom_cursor.png";


    public static void loadMenuTextures(AssetManager manager){
        manager.load(LOAD_GAME, Texture.class);
        manager.load(NEW_GAME, Texture.class);
        manager.load(EXIT, Texture.class);
        manager.load(OPTIONS, Texture.class);
        manager.load(BACKGROUND, Texture.class);
        manager.load(KORBA_LOGO, Texture.class);
        manager.load(GAME_LOGO, Texture.class);
    }

    public static void loadCursor(AssetManager manager){
        manager.load(CURSOR, Pixmap.class);
    }

}
