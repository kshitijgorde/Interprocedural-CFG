import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class Trap extends Sprite
{
    Sprite action;
    
    public Trap(final Image img) {
        super(img);
        this.setId(5);
    }
    
    public Trap(final Image img, final Sprite a) {
        this(img);
        this.setAction(a);
    }
    
    public void setAction(final Sprite action) {
        this.action = action;
    }
    
    public Sprite getAction() {
        return this.action;
    }
}
