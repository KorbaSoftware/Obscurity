package com.korba.gameoff.oblivious.inventory.items;

public class CombineMaszin {



    public static Item combineItems(Item... items) {
        for(Item item : items) {
            if(!item.isCombinable())
                return null;
        }

        return getItem(items);
    }

    private static Item getItem(Item[] items) {
        int howManyItems = items.length;

        switch(howManyItems) {
            case 2: {

            }
            case 3: {

            }
            default: {
                return null;
            }
        }
    }

}
