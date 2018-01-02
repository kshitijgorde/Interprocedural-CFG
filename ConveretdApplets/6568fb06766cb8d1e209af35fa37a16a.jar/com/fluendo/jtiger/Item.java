// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jtiger;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Component;
import com.fluendo.utils.Debug;
import com.fluendo.jkate.Event;
import java.awt.Font;
import com.fluendo.jkate.Tracker;

public class Item
{
    private Tracker kin;
    private boolean alive;
    private Font font;
    private int font_size;
    private String text;
    private TigerBitmap background_image;
    private int width;
    private int height;
    private float region_x;
    private float region_y;
    private float region_w;
    private float region_h;
    
    public Item(final Event event) {
        this.kin = null;
        this.alive = false;
        this.font = null;
        this.font_size = 0;
        this.text = null;
        this.background_image = null;
        this.width = -1;
        this.height = -1;
        this.kin = new Tracker(event);
        this.text = null;
        if (event.text != null && event.text.length > 0) {
            try {
                this.text = new String(event.text, "UTF8");
            }
            catch (Exception ex) {
                Debug.warning("Failed to convert text from UTF-8 - text will not display");
                this.text = null;
            }
        }
    }
    
    protected void createFont(final Component component, final Image image) {
        this.font_size = image.getWidth(null) / 32;
        if (this.font_size < 12) {
            this.font_size = 12;
        }
        this.font = new Font("sansserif", 1, this.font_size);
    }
    
    protected void updateCachedData(final Component component, final Image image) {
        final int width = image.getWidth(null);
        final int height = image.getHeight(null);
        if (width == this.width && height == this.height) {
            return;
        }
        this.createFont(component, image);
        this.width = width;
        this.height = height;
    }
    
    public boolean update(final Component component, final Image image, final double n) {
        final Event ev = this.kin.ev;
        if (ev == null) {
            return false;
        }
        if (n >= ev.end_time) {
            return false;
        }
        if (n < ev.start_time) {
            this.alive = false;
        }
        else {
            this.alive = true;
        }
        final Dimension dimension = new Dimension(image.getWidth(null), image.getHeight(null));
        return this.kin.update(n - ev.start_time, dimension, dimension);
    }
    
    public void setupRegion(final Component component, final Image image) {
        if (this.kin.has[0]) {
            this.region_x = this.kin.region_x;
            this.region_y = this.kin.region_y;
            this.region_w = this.kin.region_w;
            this.region_h = this.kin.region_h;
        }
        else {
            final Dimension dimension = new Dimension(image.getWidth(null), image.getHeight(null));
            this.region_x = dimension.width * 0.1f;
            this.region_y = dimension.height * 0.8f;
            this.region_w = dimension.width * 0.8f;
            this.region_h = dimension.height * 0.1f;
        }
    }
    
    public void render(final Component component, final Image image) {
        if (!this.alive) {
            return;
        }
        this.updateCachedData(component, image);
        this.setupRegion(component, image);
        this.renderBackground(component, image);
        this.renderText(image);
    }
    
    public void renderBackground(final Component component, final Image image) {
        if (this.kin.ev.bitmap != null) {
            if (this.background_image == null) {
                this.background_image = new TigerBitmap(component, this.kin.ev.bitmap, this.kin.ev.palette);
            }
            final Graphics graphics = image.getGraphics();
            graphics.drawImage(this.background_image.getScaled((int)(this.region_w + 0.5), (int)(this.region_h + 0.5)), (int)(this.region_x + 0.5), (int)(this.region_y + 0.5), null);
            graphics.dispose();
        }
    }
    
    public void renderText(final Image image) {
        if (this.text == null) {
            return;
        }
        final Graphics graphics = image.getGraphics();
        graphics.setFont(this.font);
        final float n = graphics.getFontMetrics().stringWidth(this.text);
        final float n2 = 0.0f;
        final float n3 = this.font_size * 0.05f;
        final float n4 = this.font_size * 0.05f;
        graphics.setColor(Color.black);
        graphics.drawString(this.text, (int)(this.region_x + (this.region_w - n) / 2.0f + n3 + 0.5f), (int)(this.region_y + n2 + n4 + 0.5f));
        graphics.drawString(this.text, (int)(this.region_x + (this.region_w - n) / 2.0f - n3 + 0.5f), (int)(this.region_y + n2 - n4 + 0.5f));
        graphics.drawString(this.text, (int)(this.region_x + (this.region_w - n) / 2.0f + n3 + 0.5f), (int)(this.region_y + n2 - n4 + 0.5f));
        graphics.drawString(this.text, (int)(this.region_x + (this.region_w - n) / 2.0f - n3 + 0.5f), (int)(this.region_y + n2 + n4 + 0.5f));
        graphics.setColor(Color.white);
        graphics.drawString(this.text, (int)(this.region_x + (this.region_w - n) / 2.0f + 0.5f), (int)(this.region_y + n2 + 0.5f));
        graphics.dispose();
    }
}
