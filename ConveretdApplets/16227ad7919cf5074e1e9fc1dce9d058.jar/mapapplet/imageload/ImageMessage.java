// 
// Decompiled by Procyon v0.5.30
// 

package mapapplet.imageload;

import java.awt.Image;

public class ImageMessage
{
    public Image image;
    public String ID;
    
    public ImageMessage(final Image im, final String strID) {
        this.image = im;
        this.ID = strID;
    }
}
