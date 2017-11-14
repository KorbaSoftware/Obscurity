package com.korba.gameoff.oblivious.equipment;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.korba.gameoff.oblivious.equipment.items.*;

import java.util.*;

public class EquipmentInside extends Window implements Equipment {

    private Set<Image> items;

    private WidgetGroup itemsGroup;
    private WidgetGroup combineGroup;

    private ImageButton btnCombine;

    public EquipmentInside(String title, Skin skin) {
        super(title, skin);
        //super("Equipment", Assets.manager.get(Assets.DEFAULT_SKIN, Skin.class));
        items = new HashSet<Image>();

//        items.add(new PlaceholderItem(Assets.manager.get(Assets.ITEM_PLACEHOLDER, Texture.class)));
//        items.add(new PlaceholderItem(Assets.manager.get(Assets.ITEM_PLACEHOLDER, Texture.class)));
//        items.add(new PlaceholderItem(Assets.manager.get(Assets.ITEM_PLACEHOLDER, Texture.class)));
//        items.add(new PlaceholderItem(Assets.manager.get(Assets.ITEM_PLACEHOLDER, Texture.class)));
//        items.add(new PlaceholderItem(Assets.manager.get(Assets.ITEM_PLACEHOLDER, Texture.class)));

        itemsGroup = new WidgetGroup();
        combineGroup = new WidgetGroup();

        //btnCombine = new ImageButton(Assets.manager.get(Assets.DEFAULT_SKIN, Skin.class));
        //btnCombine.getImageCell().size(32, 32);

//        items.forEach(item
//                -> itemsGroup.addActor(item)
//        ); //NOSZ JASNY CHUJ

        for(Image item : items) {
            itemsGroup.addActor(item);
        }

        combineGroup.addActor(btnCombine);

        defaults().expand().fill();
    }

    public void putItem(Image item){
        items.add(item);
    }

    public void removeItem(Image item) {
        items.remove(item);
    }

    public void combine(Image item1, Image item2, Image item3) {
        boolean combined = false;
        //TODO combine

        Image combinedItem = null;

        if(combined) {
            removeItem(item1);
            removeItem(item2);
            removeItem(item3);
            putItem(combinedItem);
        }
    }

    @Override
    public void dispose() {

    }
}
