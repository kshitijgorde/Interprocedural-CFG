// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.axis;

import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;
import org.jfree.text.TextBlockAnchor;
import org.jfree.ui.RectangleAnchor;
import java.io.Serializable;

public class CategoryLabelPositions implements Serializable
{
    public static final CategoryLabelPositions STANDARD;
    public static final CategoryLabelPositions UP_90;
    public static final CategoryLabelPositions DOWN_90;
    public static final CategoryLabelPositions UP_45;
    public static final CategoryLabelPositions DOWN_45;
    private CategoryLabelPosition positionForAxisAtTop;
    private CategoryLabelPosition positionForAxisAtBottom;
    private CategoryLabelPosition positionForAxisAtLeft;
    private CategoryLabelPosition positionForAxisAtRight;
    
    public static CategoryLabelPositions createUpRotationLabelPositions(final double angle) {
        return new CategoryLabelPositions(new CategoryLabelPosition(RectangleAnchor.BOTTOM, TextBlockAnchor.BOTTOM_LEFT, TextAnchor.BOTTOM_LEFT, -angle, CategoryLabelWidthType.RANGE, 0.5f), new CategoryLabelPosition(RectangleAnchor.TOP, TextBlockAnchor.TOP_RIGHT, TextAnchor.TOP_RIGHT, -angle, CategoryLabelWidthType.RANGE, 0.5f), new CategoryLabelPosition(RectangleAnchor.RIGHT, TextBlockAnchor.BOTTOM_RIGHT, TextAnchor.BOTTOM_RIGHT, -angle, CategoryLabelWidthType.RANGE, 0.5f), new CategoryLabelPosition(RectangleAnchor.LEFT, TextBlockAnchor.TOP_LEFT, TextAnchor.TOP_LEFT, -angle, CategoryLabelWidthType.RANGE, 0.5f));
    }
    
    public static CategoryLabelPositions createDownRotationLabelPositions(final double angle) {
        return new CategoryLabelPositions(new CategoryLabelPosition(RectangleAnchor.BOTTOM, TextBlockAnchor.BOTTOM_RIGHT, TextAnchor.BOTTOM_RIGHT, angle, CategoryLabelWidthType.RANGE, 0.5f), new CategoryLabelPosition(RectangleAnchor.TOP, TextBlockAnchor.TOP_LEFT, TextAnchor.TOP_LEFT, angle, CategoryLabelWidthType.RANGE, 0.5f), new CategoryLabelPosition(RectangleAnchor.RIGHT, TextBlockAnchor.TOP_RIGHT, TextAnchor.TOP_RIGHT, angle, CategoryLabelWidthType.RANGE, 0.5f), new CategoryLabelPosition(RectangleAnchor.LEFT, TextBlockAnchor.BOTTOM_LEFT, TextAnchor.BOTTOM_LEFT, angle, CategoryLabelWidthType.RANGE, 0.5f));
    }
    
    public CategoryLabelPositions() {
        this.positionForAxisAtTop = new CategoryLabelPosition();
        this.positionForAxisAtBottom = new CategoryLabelPosition();
        this.positionForAxisAtLeft = new CategoryLabelPosition();
        this.positionForAxisAtRight = new CategoryLabelPosition();
    }
    
    public CategoryLabelPositions(final CategoryLabelPosition top, final CategoryLabelPosition bottom, final CategoryLabelPosition left, final CategoryLabelPosition right) {
        if (top == null) {
            throw new IllegalArgumentException("Null 'top' argument.");
        }
        if (bottom == null) {
            throw new IllegalArgumentException("Null 'bottom' argument.");
        }
        if (left == null) {
            throw new IllegalArgumentException("Null 'left' argument.");
        }
        if (right == null) {
            throw new IllegalArgumentException("Null 'right' argument.");
        }
        this.positionForAxisAtTop = top;
        this.positionForAxisAtBottom = bottom;
        this.positionForAxisAtLeft = left;
        this.positionForAxisAtRight = right;
    }
    
    public CategoryLabelPosition getLabelPosition(final RectangleEdge edge) {
        CategoryLabelPosition result = null;
        if (edge == RectangleEdge.TOP) {
            result = this.positionForAxisAtTop;
        }
        else if (edge == RectangleEdge.BOTTOM) {
            result = this.positionForAxisAtBottom;
        }
        else if (edge == RectangleEdge.LEFT) {
            result = this.positionForAxisAtLeft;
        }
        else if (edge == RectangleEdge.RIGHT) {
            result = this.positionForAxisAtRight;
        }
        return result;
    }
    
    public static CategoryLabelPositions replaceTopPosition(final CategoryLabelPositions base, final CategoryLabelPosition top) {
        if (base == null) {
            throw new IllegalArgumentException("Null 'base' argument.");
        }
        if (top == null) {
            throw new IllegalArgumentException("Null 'top' argument.");
        }
        return new CategoryLabelPositions(top, base.getLabelPosition(RectangleEdge.BOTTOM), base.getLabelPosition(RectangleEdge.LEFT), base.getLabelPosition(RectangleEdge.RIGHT));
    }
    
    public static CategoryLabelPositions replaceBottomPosition(final CategoryLabelPositions base, final CategoryLabelPosition bottom) {
        if (base == null) {
            throw new IllegalArgumentException("Null 'base' argument.");
        }
        if (bottom == null) {
            throw new IllegalArgumentException("Null 'bottom' argument.");
        }
        return new CategoryLabelPositions(base.getLabelPosition(RectangleEdge.TOP), bottom, base.getLabelPosition(RectangleEdge.LEFT), base.getLabelPosition(RectangleEdge.RIGHT));
    }
    
    public static CategoryLabelPositions replaceLeftPosition(final CategoryLabelPositions base, final CategoryLabelPosition left) {
        if (base == null) {
            throw new IllegalArgumentException("Null 'base' argument.");
        }
        if (left == null) {
            throw new IllegalArgumentException("Null 'left' argument.");
        }
        return new CategoryLabelPositions(base.getLabelPosition(RectangleEdge.TOP), base.getLabelPosition(RectangleEdge.BOTTOM), left, base.getLabelPosition(RectangleEdge.RIGHT));
    }
    
    public static CategoryLabelPositions replaceRightPosition(final CategoryLabelPositions base, final CategoryLabelPosition right) {
        if (base == null) {
            throw new IllegalArgumentException("Null 'base' argument.");
        }
        if (right == null) {
            throw new IllegalArgumentException("Null 'right' argument.");
        }
        return new CategoryLabelPositions(base.getLabelPosition(RectangleEdge.TOP), base.getLabelPosition(RectangleEdge.BOTTOM), base.getLabelPosition(RectangleEdge.LEFT), right);
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CategoryLabelPositions)) {
            return false;
        }
        final CategoryLabelPositions p = (CategoryLabelPositions)o;
        final boolean b0 = this.positionForAxisAtTop.equals(p.positionForAxisAtTop);
        final boolean b2 = this.positionForAxisAtBottom.equals(p.positionForAxisAtBottom);
        final boolean b3 = this.positionForAxisAtLeft.equals(p.positionForAxisAtLeft);
        final boolean b4 = this.positionForAxisAtRight.equals(p.positionForAxisAtRight);
        return b0 && b2 && b3 && b4;
    }
    
    static {
        STANDARD = new CategoryLabelPositions(new CategoryLabelPosition(RectangleAnchor.BOTTOM, TextBlockAnchor.BOTTOM_CENTER), new CategoryLabelPosition(RectangleAnchor.TOP, TextBlockAnchor.TOP_CENTER), new CategoryLabelPosition(RectangleAnchor.RIGHT, TextBlockAnchor.CENTER_RIGHT, CategoryLabelWidthType.RANGE, 0.3f), new CategoryLabelPosition(RectangleAnchor.LEFT, TextBlockAnchor.CENTER_LEFT, CategoryLabelWidthType.RANGE, 0.3f));
        UP_90 = new CategoryLabelPositions(new CategoryLabelPosition(RectangleAnchor.BOTTOM, TextBlockAnchor.CENTER_LEFT, TextAnchor.CENTER_LEFT, -1.5707963267948966, CategoryLabelWidthType.RANGE, 1.0f), new CategoryLabelPosition(RectangleAnchor.TOP, TextBlockAnchor.CENTER_RIGHT, TextAnchor.CENTER_RIGHT, -1.5707963267948966, CategoryLabelWidthType.RANGE, 1.0f), new CategoryLabelPosition(RectangleAnchor.RIGHT, TextBlockAnchor.BOTTOM_CENTER, TextAnchor.BOTTOM_CENTER, -1.5707963267948966, CategoryLabelWidthType.CATEGORY, 0.9f), new CategoryLabelPosition(RectangleAnchor.LEFT, TextBlockAnchor.TOP_CENTER, TextAnchor.TOP_CENTER, -1.5707963267948966, CategoryLabelWidthType.CATEGORY, 0.9f));
        DOWN_90 = new CategoryLabelPositions(new CategoryLabelPosition(RectangleAnchor.BOTTOM, TextBlockAnchor.CENTER_RIGHT, TextAnchor.CENTER_RIGHT, 1.5707963267948966, CategoryLabelWidthType.RANGE, 0.3f), new CategoryLabelPosition(RectangleAnchor.TOP, TextBlockAnchor.CENTER_LEFT, TextAnchor.CENTER_LEFT, 1.5707963267948966, CategoryLabelWidthType.RANGE, 0.3f), new CategoryLabelPosition(RectangleAnchor.RIGHT, TextBlockAnchor.TOP_CENTER, TextAnchor.TOP_CENTER, 1.5707963267948966, CategoryLabelWidthType.CATEGORY, 0.9f), new CategoryLabelPosition(RectangleAnchor.LEFT, TextBlockAnchor.BOTTOM_CENTER, TextAnchor.BOTTOM_CENTER, 1.5707963267948966, CategoryLabelWidthType.CATEGORY, 0.9f));
        UP_45 = createUpRotationLabelPositions(0.7853981633974483);
        DOWN_45 = createDownRotationLabelPositions(0.7853981633974483);
    }
}
