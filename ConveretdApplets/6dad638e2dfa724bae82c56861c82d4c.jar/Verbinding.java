import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class Verbinding implements Serializable
{
    protected boolean actief;
    public static Image foto;
    
    public Verbinding() {
        this.actief = false;
    }
    
    public abstract void paint(final Graphics p0, final ImageObserver p1, final Punt p2);
    
    public abstract void activeer(final boolean p0, final Punt p1);
    
    public abstract void deActiveer();
    
    public abstract boolean lusControle(final Verbinding p0);
}
