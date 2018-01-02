// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Color;
import java.awt.image.ImageObserver;

public class MenuItem implements ImageObserver
{
    private boolean fEnable;
    private boolean fCheck;
    private String strItem;
    private String strSuff;
    private Color clrItem;
    private Color clrBack;
    private Image imgItem;
    private Image imgInact;
    private Rectangle recItem;
    private MenuPanel mnuItem;
    
    public MenuItem(final String strItem, final Image imgItem, final MenuPanel mnuItem) {
        this.strItem = strItem;
        this.imgItem = imgItem;
        this.mnuItem = mnuItem;
        this.fCheck = false;
        this.fEnable = true;
        this.imgInact = null;
        this.strSuff = null;
        final Color color = null;
        this.clrBack = color;
        this.clrItem = color;
        this.recItem = new Rectangle();
    }
    
    public MenuItem(final String s) {
        this(s, null, null);
    }
    
    public void set(final MenuItem menuItem) {
        this.strItem = menuItem.strItem;
        this.imgItem = menuItem.imgItem;
        this.mnuItem = menuItem.mnuItem;
        this.fCheck = menuItem.fCheck;
        this.fEnable = menuItem.fEnable;
        this.imgInact = menuItem.imgInact;
        this.strSuff = menuItem.strSuff;
        this.clrItem = menuItem.clrItem;
        this.clrBack = menuItem.clrBack;
    }
    
    public void enable(final boolean fEnable) {
        this.fEnable = fEnable;
    }
    
    public void check(final boolean fCheck) {
        this.fCheck = fCheck;
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        this.recItem.setBounds(n, n2, n3, n4);
    }
    
    public void setDirect(final boolean direct) {
        if (this.mnuItem != null) {
            this.mnuItem.setDirect(direct);
        }
    }
    
    public void setStyle(final Font font, final Color color, final Color color2, final Color color3, final Color color4) {
        if (this.mnuItem != null) {
            this.mnuItem.setStyle(font, color, color2, color3, color4);
        }
    }
    
    public void setColor(final Color clrItem, final Color clrBack) {
        this.clrItem = clrItem;
        this.clrBack = clrBack;
    }
    
    public void setSuff(final String strSuff) {
        this.strSuff = strSuff;
    }
    
    public boolean isEnabled() {
        return this.fEnable;
    }
    
    public boolean isChecked() {
        return this.fCheck;
    }
    
    public String getLabel() {
        return this.strItem;
    }
    
    public String getSuff() {
        return this.strSuff;
    }
    
    public Image getImage() {
        if (this.fEnable || this.imgItem == null) {
            return this.imgItem;
        }
        if (this.imgInact == null) {
            try {
                final int width = this.imgItem.getWidth(this);
                final int height = this.imgItem.getHeight(this);
                final int n = SystemColor.controlDkShadow.getRGB() | 0xFF000000;
                final int n2 = SystemColor.controlShadow.getRGB() | 0xFF000000;
                final int n3 = SystemColor.controlLtHighlight.getRGB() | 0xFF000000;
                final int[] array;
                new PixelGrabber(this.imgItem, 0, 0, width, height, array = new int[width * height], 0, width).grabPixels();
                final int[] array2 = new int[(width + 1) * (height + 1)];
                for (int i = 0; i < array2.length; ++i) {
                    array2[i] = 0;
                }
                int n6;
                for (int j = 0; j < height; j = n6) {
                    final int n4 = j * width;
                    final int n5 = (n6 = j + 1) * width;
                    final int n7 = j * (width + 1);
                    final int n8 = n6 * (width + 1);
                    int n9;
                    for (int k = 0; k < width; k = n9) {
                        n9 = k + 1;
                        final int n10;
                        if (((n10 = array[n4 + k]) & 0xFF000000) != 0x0) {
                            array2[n7 + k] = (((n10 & 0xFFFFFF) == 0xFFFFFF) ? n2 : n);
                            if (n9 >= width || n6 >= height || (array[n5 + n9] & 0xFF000000) == 0x0) {
                                array2[n8 + n9] = n3;
                            }
                        }
                    }
                }
                this.imgInact = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width + 1, height + 1, array2, 0, width + 1));
            }
            catch (InterruptedException ex) {}
        }
        return this.imgInact;
    }
    
    public MenuPanel getMenu() {
        return this.mnuItem;
    }
    
    public Color getText() {
        return this.clrItem;
    }
    
    public Color getBack() {
        return this.clrBack;
    }
    
    public Rectangle getBounds() {
        return this.recItem;
    }
    
    public boolean contains(final int n, final int n2) {
        return this.recItem.contains(n, n2);
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        return (n & 0x1) == 0x0 || (n & 0x2) == 0x0;
    }
}
