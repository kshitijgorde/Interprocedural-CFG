import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class suiChoiceitem
{
    String title;
    Image image;
    boolean select;
    
    suiChoiceitem(final String title, final Image image) {
        this.select = false;
        this.title = title;
        this.image = image;
    }
    
    suiChoiceitem(final String title) {
        this.select = false;
        this.title = title;
    }
}
