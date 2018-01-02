// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$UHB;

import java.awt.Dimension;
import java.awt.Rectangle;

public class $QXB
{
    public static Rectangle $RXB(final Rectangle rectangle, final Dimension dimension) {
        if (dimension == null) {
            throw new Error("size=null");
        }
        Rectangle rectangle2;
        if (rectangle == null) {
            rectangle2 = new Rectangle(0, 0, dimension.width, dimension.height);
        }
        else {
            rectangle2 = new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            if (rectangle2.x < 0) {
                rectangle2.width += rectangle2.x;
                rectangle2.x = 0;
            }
            if (rectangle2.y < 0) {
                rectangle2.height += rectangle2.y;
                rectangle2.y = 0;
            }
            rectangle2.width = Math.min(rectangle2.width, dimension.width - rectangle2.x);
            rectangle2.height = Math.min(rectangle2.height, dimension.height - rectangle2.y);
        }
        if (rectangle2.width < 1 || rectangle2.height < 1) {
            return null;
        }
        return rectangle2;
    }
    
    public static Rectangle $SXB(final Rectangle rectangle, final Rectangle rectangle2) {
        if (rectangle == null || rectangle2 == null) {
            return rectangle;
        }
        final Rectangle intersection = rectangle.intersection(rectangle2);
        if (intersection == null) {
            return rectangle;
        }
        if (rectangle.width == rectangle2.width && rectangle.x == rectangle2.x) {
            if (intersection.height == rectangle.height) {
                return null;
            }
            if (intersection.height == rectangle2.height) {
                return rectangle;
            }
            if (rectangle2.y > rectangle.y) {
                return new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height - intersection.height);
            }
            return new Rectangle(rectangle.x, rectangle.y + intersection.height, rectangle.width, rectangle.height - intersection.height);
        }
        else {
            if (rectangle.height != rectangle2.height || rectangle.y != rectangle2.y) {
                return rectangle;
            }
            if (intersection.width == rectangle.width) {
                return null;
            }
            if (intersection.width == rectangle2.width) {
                return rectangle;
            }
            if (rectangle2.x > rectangle.x) {
                return new Rectangle(rectangle.x, rectangle.y, rectangle.width - intersection.width, rectangle.height);
            }
            return new Rectangle(rectangle.x + intersection.width, rectangle.y, rectangle.width - intersection.width, rectangle.height);
        }
    }
}
