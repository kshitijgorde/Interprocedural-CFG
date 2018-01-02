// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.labels;

import java.io.ObjectStreamException;
import java.io.Serializable;

public final class ItemLabelAnchor implements Serializable
{
    public static final ItemLabelAnchor CENTER;
    public static final ItemLabelAnchor INSIDE1;
    public static final ItemLabelAnchor INSIDE2;
    public static final ItemLabelAnchor INSIDE3;
    public static final ItemLabelAnchor INSIDE4;
    public static final ItemLabelAnchor INSIDE5;
    public static final ItemLabelAnchor INSIDE6;
    public static final ItemLabelAnchor INSIDE7;
    public static final ItemLabelAnchor INSIDE8;
    public static final ItemLabelAnchor INSIDE9;
    public static final ItemLabelAnchor INSIDE10;
    public static final ItemLabelAnchor INSIDE11;
    public static final ItemLabelAnchor INSIDE12;
    public static final ItemLabelAnchor OUTSIDE1;
    public static final ItemLabelAnchor OUTSIDE2;
    public static final ItemLabelAnchor OUTSIDE3;
    public static final ItemLabelAnchor OUTSIDE4;
    public static final ItemLabelAnchor OUTSIDE5;
    public static final ItemLabelAnchor OUTSIDE6;
    public static final ItemLabelAnchor OUTSIDE7;
    public static final ItemLabelAnchor OUTSIDE8;
    public static final ItemLabelAnchor OUTSIDE9;
    public static final ItemLabelAnchor OUTSIDE10;
    public static final ItemLabelAnchor OUTSIDE11;
    public static final ItemLabelAnchor OUTSIDE12;
    private String name;
    
    private ItemLabelAnchor(final String name) {
        this.name = name;
    }
    
    public String toString() {
        return this.name;
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemLabelAnchor)) {
            return false;
        }
        final ItemLabelAnchor order = (ItemLabelAnchor)o;
        return this.name.equals(order.toString());
    }
    
    private Object readResolve() throws ObjectStreamException {
        ItemLabelAnchor result = null;
        if (this.equals(ItemLabelAnchor.CENTER)) {
            result = ItemLabelAnchor.CENTER;
        }
        else if (this.equals(ItemLabelAnchor.INSIDE1)) {
            result = ItemLabelAnchor.INSIDE1;
        }
        else if (this.equals(ItemLabelAnchor.INSIDE2)) {
            result = ItemLabelAnchor.INSIDE2;
        }
        else if (this.equals(ItemLabelAnchor.INSIDE3)) {
            result = ItemLabelAnchor.INSIDE3;
        }
        else if (this.equals(ItemLabelAnchor.INSIDE4)) {
            result = ItemLabelAnchor.INSIDE4;
        }
        else if (this.equals(ItemLabelAnchor.INSIDE5)) {
            result = ItemLabelAnchor.INSIDE5;
        }
        else if (this.equals(ItemLabelAnchor.INSIDE6)) {
            result = ItemLabelAnchor.INSIDE6;
        }
        else if (this.equals(ItemLabelAnchor.INSIDE7)) {
            result = ItemLabelAnchor.INSIDE7;
        }
        else if (this.equals(ItemLabelAnchor.INSIDE8)) {
            result = ItemLabelAnchor.INSIDE8;
        }
        else if (this.equals(ItemLabelAnchor.INSIDE9)) {
            result = ItemLabelAnchor.INSIDE9;
        }
        else if (this.equals(ItemLabelAnchor.INSIDE10)) {
            result = ItemLabelAnchor.INSIDE10;
        }
        else if (this.equals(ItemLabelAnchor.INSIDE11)) {
            result = ItemLabelAnchor.INSIDE11;
        }
        else if (this.equals(ItemLabelAnchor.INSIDE12)) {
            result = ItemLabelAnchor.INSIDE12;
        }
        else if (this.equals(ItemLabelAnchor.OUTSIDE1)) {
            result = ItemLabelAnchor.OUTSIDE1;
        }
        else if (this.equals(ItemLabelAnchor.OUTSIDE2)) {
            result = ItemLabelAnchor.OUTSIDE2;
        }
        else if (this.equals(ItemLabelAnchor.OUTSIDE3)) {
            result = ItemLabelAnchor.OUTSIDE3;
        }
        else if (this.equals(ItemLabelAnchor.OUTSIDE4)) {
            result = ItemLabelAnchor.OUTSIDE4;
        }
        else if (this.equals(ItemLabelAnchor.OUTSIDE5)) {
            result = ItemLabelAnchor.OUTSIDE5;
        }
        else if (this.equals(ItemLabelAnchor.OUTSIDE6)) {
            result = ItemLabelAnchor.OUTSIDE6;
        }
        else if (this.equals(ItemLabelAnchor.OUTSIDE7)) {
            result = ItemLabelAnchor.OUTSIDE7;
        }
        else if (this.equals(ItemLabelAnchor.OUTSIDE8)) {
            result = ItemLabelAnchor.OUTSIDE8;
        }
        else if (this.equals(ItemLabelAnchor.OUTSIDE9)) {
            result = ItemLabelAnchor.OUTSIDE9;
        }
        else if (this.equals(ItemLabelAnchor.OUTSIDE10)) {
            result = ItemLabelAnchor.OUTSIDE10;
        }
        else if (this.equals(ItemLabelAnchor.OUTSIDE11)) {
            result = ItemLabelAnchor.OUTSIDE11;
        }
        else if (this.equals(ItemLabelAnchor.OUTSIDE12)) {
            result = ItemLabelAnchor.OUTSIDE12;
        }
        return result;
    }
    
    public static ItemLabelAnchor getHorizontalOpposite(final ItemLabelAnchor anchor) {
        if (anchor == ItemLabelAnchor.CENTER) {
            return ItemLabelAnchor.CENTER;
        }
        if (anchor == ItemLabelAnchor.INSIDE1) {
            return ItemLabelAnchor.INSIDE11;
        }
        if (anchor == ItemLabelAnchor.INSIDE2) {
            return ItemLabelAnchor.INSIDE10;
        }
        if (anchor == ItemLabelAnchor.INSIDE3) {
            return ItemLabelAnchor.INSIDE9;
        }
        if (anchor == ItemLabelAnchor.INSIDE4) {
            return ItemLabelAnchor.INSIDE8;
        }
        if (anchor == ItemLabelAnchor.INSIDE5) {
            return ItemLabelAnchor.INSIDE7;
        }
        if (anchor == ItemLabelAnchor.INSIDE6) {
            return ItemLabelAnchor.INSIDE6;
        }
        if (anchor == ItemLabelAnchor.INSIDE7) {
            return ItemLabelAnchor.INSIDE5;
        }
        if (anchor == ItemLabelAnchor.INSIDE8) {
            return ItemLabelAnchor.INSIDE4;
        }
        if (anchor == ItemLabelAnchor.INSIDE9) {
            return ItemLabelAnchor.INSIDE3;
        }
        if (anchor == ItemLabelAnchor.INSIDE10) {
            return ItemLabelAnchor.INSIDE2;
        }
        if (anchor == ItemLabelAnchor.INSIDE11) {
            return ItemLabelAnchor.INSIDE1;
        }
        if (anchor == ItemLabelAnchor.INSIDE12) {
            return ItemLabelAnchor.INSIDE12;
        }
        if (anchor == ItemLabelAnchor.OUTSIDE1) {
            return ItemLabelAnchor.OUTSIDE11;
        }
        if (anchor == ItemLabelAnchor.OUTSIDE2) {
            return ItemLabelAnchor.OUTSIDE10;
        }
        if (anchor == ItemLabelAnchor.OUTSIDE3) {
            return ItemLabelAnchor.OUTSIDE9;
        }
        if (anchor == ItemLabelAnchor.OUTSIDE4) {
            return ItemLabelAnchor.OUTSIDE8;
        }
        if (anchor == ItemLabelAnchor.OUTSIDE5) {
            return ItemLabelAnchor.OUTSIDE7;
        }
        if (anchor == ItemLabelAnchor.OUTSIDE6) {
            return ItemLabelAnchor.OUTSIDE6;
        }
        if (anchor == ItemLabelAnchor.OUTSIDE7) {
            return ItemLabelAnchor.OUTSIDE5;
        }
        if (anchor == ItemLabelAnchor.OUTSIDE8) {
            return ItemLabelAnchor.OUTSIDE4;
        }
        if (anchor == ItemLabelAnchor.OUTSIDE9) {
            return ItemLabelAnchor.OUTSIDE3;
        }
        if (anchor == ItemLabelAnchor.OUTSIDE10) {
            return ItemLabelAnchor.OUTSIDE2;
        }
        if (anchor == ItemLabelAnchor.OUTSIDE11) {
            return ItemLabelAnchor.OUTSIDE1;
        }
        if (anchor == ItemLabelAnchor.OUTSIDE12) {
            return ItemLabelAnchor.OUTSIDE12;
        }
        return null;
    }
    
    public static ItemLabelAnchor getVerticalOpposite(final ItemLabelAnchor anchor) {
        if (anchor == ItemLabelAnchor.CENTER) {
            return ItemLabelAnchor.CENTER;
        }
        if (anchor == ItemLabelAnchor.INSIDE1) {
            return ItemLabelAnchor.INSIDE5;
        }
        if (anchor == ItemLabelAnchor.INSIDE2) {
            return ItemLabelAnchor.INSIDE4;
        }
        if (anchor == ItemLabelAnchor.INSIDE3) {
            return ItemLabelAnchor.INSIDE3;
        }
        if (anchor == ItemLabelAnchor.INSIDE4) {
            return ItemLabelAnchor.INSIDE2;
        }
        if (anchor == ItemLabelAnchor.INSIDE5) {
            return ItemLabelAnchor.INSIDE1;
        }
        if (anchor == ItemLabelAnchor.INSIDE6) {
            return ItemLabelAnchor.INSIDE12;
        }
        if (anchor == ItemLabelAnchor.INSIDE7) {
            return ItemLabelAnchor.INSIDE11;
        }
        if (anchor == ItemLabelAnchor.INSIDE8) {
            return ItemLabelAnchor.INSIDE10;
        }
        if (anchor == ItemLabelAnchor.INSIDE9) {
            return ItemLabelAnchor.INSIDE9;
        }
        if (anchor == ItemLabelAnchor.INSIDE10) {
            return ItemLabelAnchor.INSIDE8;
        }
        if (anchor == ItemLabelAnchor.INSIDE11) {
            return ItemLabelAnchor.INSIDE7;
        }
        if (anchor == ItemLabelAnchor.INSIDE12) {
            return ItemLabelAnchor.INSIDE6;
        }
        if (anchor == ItemLabelAnchor.OUTSIDE1) {
            return ItemLabelAnchor.OUTSIDE5;
        }
        if (anchor == ItemLabelAnchor.OUTSIDE2) {
            return ItemLabelAnchor.OUTSIDE4;
        }
        if (anchor == ItemLabelAnchor.OUTSIDE3) {
            return ItemLabelAnchor.OUTSIDE3;
        }
        if (anchor == ItemLabelAnchor.OUTSIDE4) {
            return ItemLabelAnchor.OUTSIDE2;
        }
        if (anchor == ItemLabelAnchor.OUTSIDE5) {
            return ItemLabelAnchor.OUTSIDE1;
        }
        if (anchor == ItemLabelAnchor.OUTSIDE6) {
            return ItemLabelAnchor.OUTSIDE12;
        }
        if (anchor == ItemLabelAnchor.OUTSIDE7) {
            return ItemLabelAnchor.OUTSIDE11;
        }
        if (anchor == ItemLabelAnchor.OUTSIDE8) {
            return ItemLabelAnchor.OUTSIDE10;
        }
        if (anchor == ItemLabelAnchor.OUTSIDE9) {
            return ItemLabelAnchor.OUTSIDE9;
        }
        if (anchor == ItemLabelAnchor.OUTSIDE10) {
            return ItemLabelAnchor.OUTSIDE8;
        }
        if (anchor == ItemLabelAnchor.OUTSIDE11) {
            return ItemLabelAnchor.OUTSIDE7;
        }
        if (anchor == ItemLabelAnchor.OUTSIDE12) {
            return ItemLabelAnchor.OUTSIDE6;
        }
        return null;
    }
    
    static {
        CENTER = new ItemLabelAnchor("ItemLabelAnchor.CENTER");
        INSIDE1 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE1");
        INSIDE2 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE2");
        INSIDE3 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE3");
        INSIDE4 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE4");
        INSIDE5 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE5");
        INSIDE6 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE6");
        INSIDE7 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE7");
        INSIDE8 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE8");
        INSIDE9 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE9");
        INSIDE10 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE10");
        INSIDE11 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE11");
        INSIDE12 = new ItemLabelAnchor("ItemLabelAnchor.INSIDE12");
        OUTSIDE1 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE1");
        OUTSIDE2 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE2");
        OUTSIDE3 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE3");
        OUTSIDE4 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE4");
        OUTSIDE5 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE5");
        OUTSIDE6 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE6");
        OUTSIDE7 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE7");
        OUTSIDE8 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE8");
        OUTSIDE9 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE9");
        OUTSIDE10 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE10");
        OUTSIDE11 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE11");
        OUTSIDE12 = new ItemLabelAnchor("ItemLabelAnchor.OUTSIDE12");
    }
}
