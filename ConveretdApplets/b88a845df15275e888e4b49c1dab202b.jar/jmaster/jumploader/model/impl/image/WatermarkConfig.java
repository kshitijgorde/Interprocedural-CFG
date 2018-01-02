// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.impl.image;

import java.net.URL;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class WatermarkConfig
{
    public static final String VALIGN_TOP = "top";
    public static final String VALIGN_MIDDLE = "middle";
    public static final String VALIGN_BOTTOM = "bottom";
    public static final String HALIGN_LEFT = "left";
    public static final String HALIGN_CENTER = "center";
    public static final String HALIGN_RIGHT = "right";
    protected String D;
    protected String C;
    protected int B;
    protected String E;
    protected ImageIcon A;
    
    public String getHalign() {
        return this.C;
    }
    
    public void setHalign(final String c) {
        this.C = c;
    }
    
    public ImageIcon getImage() {
        return this.A;
    }
    
    public void setImage(final ImageIcon a) {
        this.A = a;
    }
    
    public String getImageUrl() {
        return this.E;
    }
    
    public void setImageUrl(final String e) {
        this.E = e;
    }
    
    public int getOpacityPercent() {
        return this.B;
    }
    
    public void setOpacityPercent(final int b) {
        this.B = b;
    }
    
    public String getValign() {
        return this.D;
    }
    
    public void setValign(final String d) {
        this.D = d;
    }
    
    public ImageIcon getOrLoadImage() {
        return this.getOrLoadImage(null);
    }
    
    public ImageIcon getOrLoadImage(final BufferedImage bufferedImage) {
        if (this.A == null) {
            try {
                this.A = new ImageIcon(new URL(this.E));
            }
            catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return this.A;
    }
}
