// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.axis;

import org.jfree.ui.TextAnchor;
import org.jfree.text.TextBlockAnchor;
import org.jfree.ui.RectangleAnchor;
import java.io.Serializable;

public class CategoryLabelPosition implements Serializable
{
    private RectangleAnchor categoryAnchor;
    private TextBlockAnchor labelAnchor;
    private TextAnchor rotationAnchor;
    private double angle;
    private CategoryLabelWidthType widthType;
    private float widthRatio;
    
    public CategoryLabelPosition() {
        this(RectangleAnchor.CENTER, TextBlockAnchor.BOTTOM_CENTER, TextAnchor.CENTER, 0.0, CategoryLabelWidthType.CATEGORY, 0.9f);
    }
    
    public CategoryLabelPosition(final RectangleAnchor categoryAnchor, final TextBlockAnchor labelAnchor) {
        this(categoryAnchor, labelAnchor, TextAnchor.CENTER, 0.0, CategoryLabelWidthType.CATEGORY, 0.9f);
    }
    
    public CategoryLabelPosition(final RectangleAnchor categoryAnchor, final TextBlockAnchor labelAnchor, final CategoryLabelWidthType widthType, final float widthRatio) {
        this(categoryAnchor, labelAnchor, TextAnchor.CENTER, 0.0, widthType, widthRatio);
    }
    
    public CategoryLabelPosition(final RectangleAnchor categoryAnchor, final TextBlockAnchor labelAnchor, final TextAnchor rotationAnchor, final double angle) {
        if (categoryAnchor == null) {
            throw new IllegalArgumentException("Null 'categoryAnchor' argument.");
        }
        if (labelAnchor == null) {
            throw new IllegalArgumentException("Null 'labelAnchor' argument.");
        }
        if (rotationAnchor == null) {
            throw new IllegalArgumentException("Null 'rotationAnchor' argument.");
        }
        this.categoryAnchor = categoryAnchor;
        this.labelAnchor = labelAnchor;
        this.rotationAnchor = rotationAnchor;
        this.angle = angle;
        this.widthType = CategoryLabelWidthType.CATEGORY;
        this.widthRatio = 0.9f;
    }
    
    public CategoryLabelPosition(final RectangleAnchor categoryAnchor, final TextBlockAnchor labelAnchor, final TextAnchor rotationAnchor, final double angle, final CategoryLabelWidthType widthType, final float widthRatio) {
        if (categoryAnchor == null) {
            throw new IllegalArgumentException("Null 'categoryAnchor' argument.");
        }
        if (labelAnchor == null) {
            throw new IllegalArgumentException("Null 'labelAnchor' argument.");
        }
        if (rotationAnchor == null) {
            throw new IllegalArgumentException("Null 'rotationAnchor' argument.");
        }
        if (widthType == null) {
            throw new IllegalArgumentException("Null 'widthType' argument.");
        }
        this.categoryAnchor = categoryAnchor;
        this.labelAnchor = labelAnchor;
        this.rotationAnchor = rotationAnchor;
        this.angle = angle;
        this.widthType = widthType;
        this.widthRatio = widthRatio;
    }
    
    public RectangleAnchor getCategoryAnchor() {
        return this.categoryAnchor;
    }
    
    public TextBlockAnchor getLabelAnchor() {
        return this.labelAnchor;
    }
    
    public TextAnchor getRotationAnchor() {
        return this.rotationAnchor;
    }
    
    public double getAngle() {
        return this.angle;
    }
    
    public CategoryLabelWidthType getWidthType() {
        return this.widthType;
    }
    
    public float getWidthRatio() {
        return this.widthRatio;
    }
    
    public boolean equals(final Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (object instanceof CategoryLabelPosition) {
            final CategoryLabelPosition p = (CategoryLabelPosition)object;
            final boolean b0 = this.categoryAnchor.equals(p.categoryAnchor);
            final boolean b2 = this.labelAnchor.equals(p.labelAnchor);
            final boolean b3 = this.rotationAnchor.equals(p.rotationAnchor);
            final boolean b4 = this.angle == p.angle;
            return b0 && b2 && b3 && b4;
        }
        return false;
    }
}
