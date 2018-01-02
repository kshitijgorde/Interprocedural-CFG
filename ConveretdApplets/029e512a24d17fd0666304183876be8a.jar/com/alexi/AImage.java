// 
// Decompiled by Procyon v0.5.30
// 

package com.alexi;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class AImage extends IGraphicObject implements ImageObserver
{
    protected Image m_displayImage;
    protected int m_imageWidth;
    protected int m_imageHeight;
    
    public AImage(final Image displayImage, final int imageWidth, final int imageHeight, final String theUrl, final String linkTarget) {
        this.m_displayImage = null;
        this.m_imageWidth = 0;
        this.m_imageHeight = 0;
        this.m_displayImage = displayImage;
        this.m_imageWidth = imageWidth;
        this.m_imageHeight = imageHeight;
        super.boundingRectangle = new Rectangle();
        super.theUrl = theUrl;
        super.linkTarget = linkTarget;
    }
    
    public void drawGraphicObject(final Graphics graphics) {
        if (this.m_displayImage != null) {
            graphics.drawImage(this.m_displayImage, super.boundingRectangle.x, super.boundingRectangle.y + super.boundingRectangle.height - this.m_imageHeight, this);
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        return true;
    }
}
