// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.image;

import java.awt.Dimension;
import java.awt.Image;

public class $OKB
{
    Object source;
    Image image;
    int width;
    int height;
    
    public boolean $PKB() {
        return this.source != null && this.width > 0 && this.height > 0;
    }
    
    public $OKB(final Object source, final Image image, final int width, final int height) {
        this.source = source;
        this.image = image;
        if (image == null) {
            this.width = -1;
            this.height = -1;
        }
        else {
            this.width = width;
            this.height = height;
        }
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public Image getImage() {
        return this.image;
    }
    
    public Dimension getSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Object getSource() {
        return this.source;
    }
    
    public int getWidth() {
        return this.width;
    }
}
