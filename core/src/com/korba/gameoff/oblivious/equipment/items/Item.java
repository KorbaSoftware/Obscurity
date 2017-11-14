package com.korba.gameoff.oblivious.equipment.items;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;

public class Item extends Texture {

    public enum ItemID {
        PLACEHOLDER, RADIO, RADIOANTENNA, BATTERY, WORKING_RADIO, PENCIL, PAPER, FAKE_DRIVE_LICENSE
    }

    private String path;
    private ItemID itemID;
    private boolean combineable;
    private String description;
    private List<Item> itemsNeedToCombine;

    public Item(String path) {
        super(path);
    }

    public Item(Item item) {
        super(item.getPath());
    }

    public Item(String path, ItemID itemID, boolean combineable, String description,List<Item> itemsNeedToCombine) {
        super(path);
        //super(textureRegion);

        //.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("90.png")));
        //this.setDrawable(new TextureRegionDrawable(textureRegion));
        this.path = path;
        this.itemID = itemID;
        this.combineable = combineable;
        this.description = description;
        this.itemsNeedToCombine = itemsNeedToCombine;
    }

    public ItemID getItemID() {
        return itemID;
    }

    public boolean isCombineable() {
        return combineable;
    }

    public String getDescription() {
        return description;
    }

    public List<Item> getItemsNeedToCombine() {
        return itemsNeedToCombine;
    }

    public Texture getItem() {
        return this;
    }

    public String getPath() {
        return path;
    }
}
