package com.korba.gameoff.oblivious.tools;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.*;
import com.badlogic.gdx.assets.loaders.*;
import com.badlogic.gdx.assets.loaders.resolvers.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AssetUtils {

    public static final AssetManager assetManager = new AssetManager();
    private static final InternalFileHandleResolver fileResolver = new InternalFileHandleResolver();

    // Characters
    public static final String PLAYER_32_SPRITES = "characters/player/lysy_32_frames.png";
    public static final String PLAYER_64_SPRITES = "characters/player/lysy_64_frames.png";

    // Maps
    public static final String MAP_TEST0 = "maps/tmx/opentest.tmx";
    public static final String MAP_TEST1= "maps/tmx/newmap.tmx";
    public static final String MAP_TEST2= "maps/tmx/newmap1.tmx";
    public static final String MAP_TEST3= "maps/tmx/newmap2.tmx";
    public static final String MAP_METRO= "maps/tmx/metro.tmx";
    public static final String MAP_METRO3= "maps/tmx/metro3.tmx";
    public static final String MAP_METRO2= "maps/tmx/metro2.tmx";

    // Items
    public static final String ITEM_PLACEHOLDER = "items/item_placeholder.png";
    public static final String ITEM_RADIO = "items/item_radio.png";
    public static final String ITEM_PINDOL = "items/item_pindol.png";
    public static final String ITEM_MECHA_PINDOL = "items/item_mecha_pindol.png";

    // Skins
    private static final String DEFAULT_SKIN_ATLAS_PATH = "skins/flat/skin.atlas";
    private static final String DEFAULT_SKIN_PATH = "skins/flat/skin.json";

    // Other textures
    public static final String DEV_KAMIL = "buttons/dev_kamil.png";
    public static final String DEV_KUBA = "buttons/dev_kuba.png";
    public static final String DEV_FRUK = "buttons/dev_fruk.png";
    public static final String LOAD_GAME = "buttons/load_game.png";
    public static final String NEW_GAME = "buttons/new_game.png";
    public static final String EXIT = "buttons/exit.png";
    public static final String OPTIONS = "buttons/options.png";
    public static final String BACKGROUND = "menu_background.png";
    public static final String KORBA_LOGO = "korba_logo.png";
    public static final String GAME_LOGO = "game_logo.png";
    public static final String PLAYER_64 = "characters/player/lysy64.png";
    public static final String PLAYER_32 = "characters/player/lysy32.png";
    public static final String CURSOR = "custom_cursor.png";
    public static final String PROFILE = "buttons/profile.png";
    public static final String PROFILE_THREE = "buttons/profile_three.png";
    public static final String PROFILE_TWO = "buttons/profile_two.png";
    public static final String PROFILE_ONE = "buttons/profile_one.png";
    public static final String RESUME = "buttons/resume.png";
    public static final String SAVE_GAME ="buttons/save_game.png";
    public static final String TAPE_SCREEN = "buttons/tape.png";
    public static final String TEXT_BG = "buttons/text_bg.png";

    public static final TextureAtlas DEFAULT_SKIN_TEXTURE_ATLAS = new TextureAtlas(Gdx.files.internal(DEFAULT_SKIN_ATLAS_PATH));
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
        loadTexture(PLAYER_64);
        loadTexture(PLAYER_32);
        loadTexture(PLAYER_32_SPRITES);
        loadTexture(PLAYER_64_SPRITES);
    }

    public static void loadRemainingAssets() {
        loadTexture(NEW_GAME);
        loadTexture(LOAD_GAME);
        loadTexture(SAVE_GAME);
        loadTexture(EXIT);
        loadTexture(OPTIONS);
        loadTexture(BACKGROUND);
        loadTexture(PROFILE);
        loadTexture(PROFILE_ONE);
        loadTexture(PROFILE_TWO);
        loadTexture(PROFILE_THREE);
        loadTexture(RESUME);
        loadTexture(ITEM_RADIO);
        loadTexture(ITEM_PINDOL);
        loadTexture(ITEM_PLACEHOLDER);
        loadTexture(ITEM_MECHA_PINDOL);
        loadTexture(TAPE_SCREEN);
        loadTexture(TEXT_BG);

        loadMap(MAP_TEST0);
        loadMap(MAP_TEST1);
        loadMap(MAP_TEST2);
        loadMap(MAP_TEST3);
        loadMap(MAP_METRO);
        loadMap(MAP_METRO2);
        loadMap(MAP_METRO3);

        loadTexture(DEV_KAMIL);
        loadTexture(DEV_KUBA);
        loadTexture(DEV_FRUK);
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
