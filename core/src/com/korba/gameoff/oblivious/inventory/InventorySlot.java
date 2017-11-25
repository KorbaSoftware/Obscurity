package com.korba.gameoff.oblivious.inventory;

import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.*;
import com.korba.gameoff.oblivious.inventory.items.*;
import com.korba.gameoff.oblivious.tools.*;

public class InventorySlot extends Stack {

    private Image customBackground;
    private Stack defaultBackground;

    public InventorySlot() {
        customBackground = new Image();
        defaultBackground = new Stack();
        Image image = new Image(AssetUtils.getTexture(AssetUtils.ITEM_PLACEHOLDER));

        defaultBackground.add(image);
        this.add(defaultBackground);
    }

    public InventorySlot(Image  customBackground) {
        this();
        this.customBackground = customBackground;
        defaultBackground.add(customBackground);
    }

    public static void swapSlots(InventorySlot slotSource, InventorySlot slotTarget, Item dragItem) {
        slotSource.add(slotTarget.removeItem());
        slotTarget.add(dragItem);
    }

    @Override
    public void add(Actor actor) {
        super.add(actor);
    }

    public Item getItem() {
        Item item = null;
        if(hasItem()) {
            SnapshotArray<Actor> arrayChildren = this.getChildren();
            if(arrayChildren.size > 1)
            item = (Item) arrayChildren.peek();
        }

        return item;
    }

    public int itemsInSlot () {
        return getChildren().size - 1;
    }

    public Item removeItem() {

        Array<Item> items = new Array<>();
        if(hasItem()) {
            SnapshotArray<Actor> arrayChildren = this.getChildren();
            items.add((Item) arrayChildren.pop());

            return items.get(0);
        }

        return null;
    }

    public boolean hasItem() {
        if(hasChildren()) {
            SnapshotArray<Actor> items = this.getChildren();
            if(items.size > 1)
                return true;
        }
        return false;
    }

    public String getItemDescription() {
        if(hasChildren()) {
            SnapshotArray<Actor> items = this.getChildren();
            if(items.size > 1) {
                Item item = (Item) items.peek();
                return item.getDescription();
            }
        }

        return "";
    }

}
