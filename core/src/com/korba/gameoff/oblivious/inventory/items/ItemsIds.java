package com.korba.gameoff.oblivious.inventory.items;

public class ItemsIds {

    // normal item id power of 2
    // combine item id sum of normal ids

    //partial items
    public static final int BROKEN_RADIO = 1;
    public static final int PINDOL = 2;

    //final items
    public static final int MECHA_PINDOL = BROKEN_RADIO | PINDOL; //broken_radio + pindol
}
