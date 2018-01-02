// 
// Decompiled by Procyon v0.5.30
// 

package com.alexi;

import java.awt.Graphics;
import java.awt.Rectangle;

public class IGraphicObject
{
    public Rectangle boundingRectangle;
    public int objectType;
    int TEXT_WITH_FONT;
    public String theUrl;
    public String linkTarget;
    
    void drawGraphicObject(final Graphics graphics) {
    }
    
    void drawGraphicObject(final Graphics graphics, final int n, final int n2) {
        this.drawGraphicObject(graphics);
    }
    
    public String getURL() {
        return this.theUrl;
    }
    
    public void setRectangle(final int x, final int y, final int width, final int height) {
        this.boundingRectangle.x = x;
        this.boundingRectangle.y = y;
        this.boundingRectangle.width = width;
        this.boundingRectangle.height = height;
    }
    
    public IGraphicObject() {
        this.boundingRectangle = null;
        this.objectType = -1;
        this.TEXT_WITH_FONT = 0;
        this.theUrl = null;
        this.linkTarget = null;
    }
}
