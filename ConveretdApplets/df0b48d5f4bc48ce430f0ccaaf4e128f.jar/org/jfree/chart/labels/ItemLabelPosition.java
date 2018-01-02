// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.labels;

import org.jfree.ui.TextAnchor;
import java.io.Serializable;

public class ItemLabelPosition implements Serializable
{
    private static final long serialVersionUID = 5845390630157034499L;
    private ItemLabelAnchor itemLabelAnchor;
    private TextAnchor textAnchor;
    private TextAnchor rotationAnchor;
    private double angle;
    
    public ItemLabelPosition() {
        this(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER, TextAnchor.CENTER, 0.0);
    }
    
    public ItemLabelPosition(final ItemLabelAnchor itemLabelAnchor, final TextAnchor textAnchor) {
        this(itemLabelAnchor, textAnchor, TextAnchor.CENTER, 0.0);
    }
    
    public ItemLabelPosition(final ItemLabelAnchor itemLabelAnchor, final TextAnchor textAnchor, final TextAnchor rotationAnchor, final double angle) {
        if (itemLabelAnchor == null) {
            throw new IllegalArgumentException("Null 'itemLabelAnchor' argument.");
        }
        if (textAnchor == null) {
            throw new IllegalArgumentException("Null 'textAnchor' argument.");
        }
        if (rotationAnchor == null) {
            throw new IllegalArgumentException("Null 'rotationAnchor' argument.");
        }
        this.itemLabelAnchor = itemLabelAnchor;
        this.textAnchor = textAnchor;
        this.rotationAnchor = rotationAnchor;
        this.angle = angle;
    }
    
    public ItemLabelAnchor getItemLabelAnchor() {
        return this.itemLabelAnchor;
    }
    
    public TextAnchor getTextAnchor() {
        return this.textAnchor;
    }
    
    public TextAnchor getRotationAnchor() {
        return this.rotationAnchor;
    }
    
    public double getAngle() {
        return this.angle;
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ItemLabelPosition)) {
            return false;
        }
        final ItemLabelPosition that = (ItemLabelPosition)obj;
        return this.itemLabelAnchor.equals(that.itemLabelAnchor) && this.textAnchor.equals(that.textAnchor) && this.rotationAnchor.equals(that.rotationAnchor) && this.angle == that.angle;
    }
}
