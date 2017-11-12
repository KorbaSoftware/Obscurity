package com.korba.gameoff.oblivious.tools;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.*;
import com.badlogic.gdx.assets.loaders.*;
import com.badlogic.gdx.assets.loaders.resolvers.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class AssetUtils {

    public static final AssetManager assetManager = new AssetManager();

    private static InternalFileHandleResolver fileResolver = new InternalFileHandleResolver();

    // Maps
    public static final String MAP_TEST0 = "maps/tmx/test.tmx";
    public static final String MAP_TEST1= "maps/tmx/test1.tmx";

    // Items
    public static final String ITEM_PLACEHOLDER = "items/item_placeholder.png";
    public static final String ITEM_RADIO = "items/item_radio.png";

    // Skins
    private static final String DEFAULT_SKIN_ATLAS_PATH = "skins/flat/skin.atlas";
    private static final String DEFAULT_SKIN_PATH = "skins/flat/skin.json";

    // Other textures
    public static final String LOAD_GAME = "buttons/load_game.png";
    public static final String NEW_GAME = "buttons/new_game.png";
    public static final String EXIT = "buttons/exit.png";
    public static final String OPTIONS = "buttons/options.png";
    public static final String BACKGROUND = "menu_background.png";
    public static final String KORBA_LOGO = "korba_logo.png";
    public static final String GAME_LOGO = "game_logo.png";
    public static final String PLAYER = "characters/player/lysy64.png";
    public static final String CURSOR = "custom_cursor.png";


    public static final TextureAtlas DEFAULT_SKIN_TEXTURE_ATLAS = new TextureAtlas(DEFAULT_SKIN_ATLAS_PATH);
    public static final Skin DEFAULT_SKIN = new Skin(Gdx.files.internal(DEFAULT_SKIN_PATH), DEFAULT_SKIN_TEXTURE_ATLAS);

    public static void dearManagerPleaseFeelFreeToRemoveThisFuckerFromYourMajesticMemory(String path) {
        if(isAssetLoaded(path))
            assetManager.unload(path);
    }

    public static boolean isAssetLoaded(String path) {
        return assetManager.isLoaded(path);
    }

    public static float getLoadingProgress() {
        return assetManager.getProgress();
    }

    public static void loadInitialAssets() {
        loadPixmap(CURSOR);
        loadTexture(KORBA_LOGO);
        loadTexture(GAME_LOGO);
    }

    public static void loadRemainingAssets() {
        loadTexture(NEW_GAME);
        loadTexture(LOAD_GAME);
        loadTexture(EXIT);
        loadTexture(OPTIONS);
        loadTexture(BACKGROUND);
        loadTexture(PLAYER);
        loadTexture(ITEM_RADIO);
        loadTexture(ITEM_PLACEHOLDER);

        loadMap(MAP_TEST0);
        loadMap(MAP_TEST1);
    }

    public static void loadTexture(String path) {
        if(!fileResolver.resolve(path).exists() || isAssetLoaded(path))
            return;

        assetManager.setLoader(Texture.class, new TextureLoader(fileResolver));
        assetManager.load(path, Texture.class);
        assetManager.finishLoadingAsset(path);
    }

    public static Texture getTexture(String path) {
        if(isAssetLoaded(path))
            return assetManager.get(path, Texture.class);
        else
            return null;
    }

    public static void loadMap(String path) {
        if(!fileResolver.resolve(path).exists())
            return;

        assetManager.setLoader(TiledMap.class, new TmxMapLoader(fileResolver));
        assetManager.load(path, TiledMap.class);
        assetManager.finishLoadingAsset(path);
    }

    public static TiledMap getMap(String path) {
        if(isAssetLoaded(path))
            return assetManager.get(path, TiledMap.class);
        else
            return null;
    }

    public static void loadPixmap(String path) {
        if(!fileResolver.resolve(path).exists())
            return;

        assetManager.setLoader(Pixmap.class, new PixmapLoader(fileResolver));
        assetManager.load(path, Pixmap.class);
        assetManager.finishLoadingAsset(path);
    }

    public static Pixmap getPixmap(String path) {
        if(isAssetLoaded(path))
            return assetManager.get(path, Pixmap.class);
        else
            return null;
    }

}