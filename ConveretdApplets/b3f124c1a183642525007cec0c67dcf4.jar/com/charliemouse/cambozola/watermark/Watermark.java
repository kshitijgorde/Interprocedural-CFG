// 
// Decompiled by Procyon v0.5.30
// 

package com.charliemouse.cambozola.watermark;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.net.URL;
import java.awt.Image;

public class Watermark
{
    public static final int H_LEFT = 0;
    public static final int H_CENTER = 1;
    public static final int H_RIGHT = 2;
    public static final int V_TOP = 0;
    public static final int V_MIDDLE = 1;
    public static final int V_BOTTOM = 2;
    public static final boolean INVISIBLE = true;
    public static final boolean VISIBLE = true;
    private Image m_image;
    private int m_imgWidth;
    private int m_imgHeight;
    private int m_horiz;
    private int m_vert;
    private int m_indentx;
    private int m_indenty;
    private boolean m_visible;
    private URL m_url;
    private Rectangle m_rect;
    
    public Watermark(final Image image, final int n, final int n2, final int n3, final int n4) {
        this(image, n, n2, n3, n4, null);
    }
    
    public Watermark(final Image image, final int n, final int n2, final int n3, final int n4, final String s) {
        this(image, n, n2, n3, n4, s, true);
    }
    
    public Watermark(final Image image, final int horiz, final int vert, final int indentx, final int indenty, final String s, final boolean visible) {
        this.m_image = image;
        this.m_imgWidth = this.m_image.getWidth(null);
        this.m_imgHeight = this.m_image.getHeight(null);
        this.m_rect = new Rectangle(0, 0, 0, 0);
        this.m_horiz = horiz;
        this.m_vert = vert;
        this.m_indentx = indentx;
        this.m_indenty = indenty;
        this.m_visible = visible;
        if (s != null && !s.equals("")) {
            try {
                this.m_url = new URL(s);
            }
            catch (MalformedURLException ex) {
                System.err.println("Unable to process watermark URL - '" + s + "' - not clickable");
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.m_visible) {
            graphics.drawImage(this.m_image, this.m_rect.x, this.m_rect.y, null);
        }
    }
    
    public boolean hitsPoint(final Point point) {
        return this.m_rect.contains(point);
    }
    
    public boolean isClickable() {
        return this.m_visible && this.m_url != null;
    }
    
    public boolean isVisible() {
        return this.m_visible;
    }
    
    public URL getURL() {
        return this.m_url;
    }
    
    public void recalculateLocation(final Dimension dimension) {
        int indentx = 0;
        switch (this.m_horiz) {
            case 0: {
                indentx = this.m_indentx;
                break;
            }
            case 1: {
                indentx = (dimension.width - this.m_imgWidth) / 2;
                break;
            }
            case 2: {
                indentx = dimension.width - this.m_indentx - this.m_imgWidth;
                break;
            }
            default: {
                throw new RuntimeException("Illegal Horizontal Anchor position - " + this.m_horiz);
            }
        }
        int indenty = 0;
        switch (this.m_vert) {
            case 0: {
                indenty = this.m_indenty;
                break;
            }
            case 1: {
                indenty = (dimension.height - this.m_imgHeight) / 2;
                break;
            }
            case 2: {
                indenty = dimension.height - this.m_indenty - this.m_imgHeight;
                break;
            }
            default: {
                throw new RuntimeException("Illegal Vertical Anchor position - " + this.m_horiz);
            }
        }
        this.m_rect.setBounds(indentx, indenty, this.m_imgWidth, this.m_imgHeight);
    }
}
