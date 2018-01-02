import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Rectangle;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class _
{
    private Image Y;
    private int Z;
    private Rectangle T;
    private Vector _a;
    private Vector aa;
    
    public _(final Image y, final Rectangle t) {
        this.Y = y;
        this.T = t;
        this.reset();
    }
    
    public void reset() {
        this.Z = 0;
        this._a = new Vector();
        this.aa = new Vector();
    }
    
    public void b() {
        ++this.Z;
        this._a.addElement(new Integer((int)(Math.random() * (this.T.width - 28)) + this.T.x + 4));
        this.aa.addElement(new Integer((int)(Math.random() * (this.T.height - 28)) + this.T.y + 4));
    }
    
    public int b() {
        return this.Z;
    }
    
    public void _(final Graphics graphics) {
        for (int i = 0; i < this.Z; ++i) {
            graphics.drawImage(this.Y, (int)this._a.elementAt(i), (int)this.aa.elementAt(i), null);
        }
    }
}
