package com.korba.gameoff.oblivious.equipment.slot;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.korba.gameoff.oblivious.equipment.items.*;
import com.korba.gameoff.oblivious.tools.*;

public class EqSlot extends Image {

    private Item item;

    public EqSlot() {
        item = null;
    }

    public EqSlot(Item item) {
        super(item);

        this.item = item;
    }

    public static void swapSlot(EqSlot slotSource, EqSlot slotTarget, EqSlot item) {
        EqSlot itemFromSource = slotSource.removeSlot();
        EqSlot itemFromTarget = slotTarget.removeSlot();
        slotSource.addSlot(itemFromTarget);
        slotTarget.addSlot(itemFromSource);
    }

    public Item removeItem() {
        Item temp = new Item(item);
        item = null;

        return temp;
    }

    public EqSlot removeSlot() {
        //this.setDrawable(new TextureRegionDrawable(new TextureRegion(new Item(AssetUtils.ITEM_PLACEHOLDER, Item.ItemID.PLACEHOLDER, false, "Empty slot", null))));
        item = null;
        this.setDrawable(null);
        return this;
    }

    public Item getItem() {
        return item;
    }

    public void addItem(Item item) {
        this.item = item;
        this.setDrawable(new TextureRegionDrawable(new TextureRegion(item)));
    }

    public void addSlot(EqSlot eqSlot) {
        this.item = eqSlot.getItem();
    }

    public boolean hasItem() {
        return (item != null) ? true : false;
    }
}
