import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class suiListitem
{
    String title;
    Image image;
    boolean select;
    
    suiListitem(final String title, final Image image) {
        this.select = false;
        this.title = title;
        this.image = image;
    }
    
    suiListitem(final String title) {
        this.select = false;
        this.title = title;
    }
}
