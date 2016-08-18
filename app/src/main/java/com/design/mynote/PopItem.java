package com.design.mynote;

/**
 * Created by 杰‘z on 2016/8/13.
 */
public class PopItem {

    public String itemText;
    public int itemIcon;

    public PopItem(String itemText, int itemIcon) {
        this.itemText = itemText;
        this.itemIcon = itemIcon;
    }

    public String getItemText() {
        return itemText;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }

    public int getItemIcon() {
        return itemIcon;
    }

    public void setItemIcon(int itemIcon) {
        this.itemIcon = itemIcon;
    }
}
