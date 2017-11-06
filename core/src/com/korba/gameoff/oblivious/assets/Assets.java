package com.korba.gameoff.oblivious.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {

    public static final AssetManager manager = new AssetManager();

    // Textures
    public static final String LOAD_GAME    = "buttons/load_game.png";
    public static final String NEW_GAME     = "buttons/new_game.png";
    public static final String EXIT         = "buttons/exit.png";
    public static final String OPTIONS      = "buttons/options.png";
    public static final String BACKGROUND   = "menu_background.png";
    public static final String KORBA_LOGO   = "korba_logo.png";
    public static final String GAME_LOGO    = "game_logo.png";
    public static final String PLAYER       = "characters/player/lysy64.png";
    public static final String DEV_FRUK     = "buttons/dev_fruk.png";
    public static final String DEV_KAMIL    = "buttons/dev_kamil.png";
    public static final String DEV_KUBA     = "buttons/dev_kuba.png";

    //Pixmaps
    public static final String CURSOR       = "custom_cursor.png";

    //Skins
    public static final String DEFAULT_UI   ="skins/default_ui_skin.json";

    public static void loadInitialAssets() {
        loadCursor();
        loadLoadingAssets();

        manager.finishLoading();
    }

    public static void loadRemainingAssets() {
        loadGameAssets();

        manager.finishLoading();
    }

    private static void loadGameAssets(){
        manager.load(LOAD_GAME, Texture.class);
        manager.load(NEW_GAME, Texture.class);
        manager.load(EXIT, Texture.class);
        manager.load(OPTIONS, Texture.class);
        manager.load(BACKGROUND, Texture.class);
        manager.load(PLAYER, Texture.class);
        manager.load(DEV_FRUK, Texture.class);
        manager.load(DEV_KAMIL, Texture.class);
        manager.load(DEV_KUBA, Texture.class);
    }

    private static void loadLoadingAssets(){
        manager.load(KORBA_LOGO, Texture.class);
        manager.load(GAME_LOGO, Texture.class);
    }

    private static void loadCursor(){
        manager.load(CURSOR, Pixmap.class);
    }

    public static Texture getTexture(String name){
        return manager.get(name, Texture.class);
    }

    public static Pixmap getPixmap(String name){
        return manager.get(name, Pixmap.class);
    }

    public static TextureAtlas getAtlas(String name){
        return manager.get(name, TextureAtlas.class);
    }

    //not working
    public static Skin getSkin(String name){
        return manager.get(name, Skin.class);
    }
}
