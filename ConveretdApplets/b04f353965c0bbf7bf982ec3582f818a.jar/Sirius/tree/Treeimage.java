// 
// Decompiled by Procyon v0.5.30
// 

package Sirius.tree;

import java.awt.Image;
import java.io.Serializable;

public class Treeimage implements Serializable
{
    private String imagename;
    private Image image;
    
    public Treeimage(final String imagename, final Image image) {
        this.imagename = imagename;
        this.image = image;
    }
    
    public String getImagename() {
        return this.imagename;
    }
    
    public Image getImage() {
        return this.image;
    }
    
    public void setImagename(final String imagename) {
        this.imagename = imagename;
    }
    
    public void setImage(final Image image) {
        this.image = image;
    }
}
