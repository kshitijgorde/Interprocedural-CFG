// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets.draw;

import java.awt.Graphics;
import java.awt.Color;

public class DrawDigitalLine extends DrawLine
{
    public DrawDigitalLine() {
    }
    
    public DrawDigitalLine(final int stroke, final int gap, final int thickness, final Color color) {
        super(stroke, gap, thickness, color);
    }
    
    public DrawDigitalLine(final PatternMaker pm, final Color color) {
        super(pm, color);
    }
    
    public void drawLine(final Graphics graphics, final int x1, final int y1, final int x2, final int y2) {
        if (y1 == y2) {
            super.drawLine(graphics, x1, y1, x2, y2);
        }
        else {
            super.drawLine(graphics, x1, y1, x2, y1);
            super.drawLine(graphics, x2, y1, x2, y2);
        }
    }
    
    public LargePoint getDataPointYOnLineAtX(final double x, final LargePoint start, final LargePoint end) {
        if (x <= start.x) {
            return new LargePoint(start);
        }
        if (x >= end.x) {
            return new LargePoint(end);
        }
        return new LargePoint(x, start.y);
    }
    
    public boolean clipDataToExtents(final LargeRectangle clipRect, final LargePoint startPoint, final LargePoint endPoint) {
        final double clipBottom = clipRect.y;
        final double clipTop = clipRect.y + clipRect.height;
        final double clipLeft = clipRect.x;
        final double clipRight = clipRect.x + clipRect.width;
        if (startPoint.y > clipTop && endPoint.y > clipTop) {
            return false;
        }
        if (startPoint.y < clipBottom && endPoint.y < clipBottom) {
            return false;
        }
        if (startPoint.x < clipLeft && endPoint.x < clipLeft) {
            return false;
        }
        if (startPoint.x > clipRight && endPoint.x > clipRight) {
            return false;
        }
        if (endPoint.x > clipRight) {
            if (startPoint.y < clipBottom || startPoint.y > clipTop) {
                return false;
            }
            endPoint.x = clipRight;
            endPoint.y = startPoint.y;
        }
        if (startPoint.x < clipLeft) {
            startPoint.x = clipRect.x;
        }
        if (startPoint.y > clipTop) {
            startPoint.y = clipTop;
            startPoint.x = endPoint.x;
        }
        else if (startPoint.y < clipBottom) {
            startPoint.y = clipBottom;
            startPoint.x = endPoint.x;
        }
        if (endPoint.y > clipTop) {
            endPoint.y = clipTop;
        }
        else if (endPoint.y < clipBottom) {
            endPoint.y = clipBottom;
        }
        return true;
    }
}
