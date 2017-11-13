package com.korba.gameoff.oblivious.equipment.items;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class Item extends Image {

    public enum ItemID {
        RADIO, RADIOANTENNA, BATTERY, WORKING_RADIO, PENCIL, PAPER, FAKE_DRIVE_LICENSE
    }

    private ItemID itemID;
    private boolean combineable;
    private String description;
    private List<Item> itemsNeedToCombine;

    public Item(Item item) {
        super();
    }

    public Item(TextureRegion textureRegion, ItemID itemID, boolean combineable, String description,List<Item> itemsNeedToCombine) {
        super(textureRegion);

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

    public Image getImage() {
        return new Image(this.getDrawable());
    }


}
