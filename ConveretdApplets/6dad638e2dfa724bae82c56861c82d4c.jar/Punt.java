import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public class Punt implements Serializable
{
    public Verbinding[] richting;
    public Verbinding invoer;
    public int x;
    public int y;
    public static final int SPACE = 10;
    
    public Punt(final int x, final int y) {
        this.richting = new Verbinding[4];
        this.x = x;
        this.y = y;
    }
    
    public void paint(final Graphics graphics, final ImageObserver imageObserver) {
        if (this.richting[0] == null || this.richting[0] != this.richting[1]) {
            graphics.setColor(Color.black);
            graphics.drawLine(this.x, this.y, this.x, this.y);
            for (int i = 0; i < 4; ++i) {
                if (this.richting[i] != null) {
                    this.richting[i].paint(graphics, imageObserver, this);
                }
            }
            if (this.invoer != null) {
                for (int j = 0; j < 4; ++j) {
                    if (this.richting[j] != null && this.richting[j] != this.invoer) {
                        graphics.setColor(Color.black);
                        graphics.fillRect(this.x - 1, this.y - 1, 3, 3);
                        break;
                    }
                }
            }
        }
    }
    
    public void activeer(final boolean b) {
        for (int i = 0; i < 4; ++i) {
            if (this.richting[i] != null && this.invoer != this.richting[i]) {
                this.richting[i].activeer(b, this);
            }
        }
    }
    
    public void deActiveer() {
        for (int i = 0; i < 4; ++i) {
            if (this.richting[i] != null && this.invoer != this.richting[i]) {
                this.richting[i].deActiveer();
            }
        }
    }
    
    public boolean lusControle(final Verbinding verbinding) {
        return this.invoer != null && this.invoer.lusControle(verbinding);
    }
    
    public boolean hasLine() {
        return this.richting[0] != null && this.richting[0] == this.richting[2];
    }
    
    public boolean hasLine2() {
        return this.richting[1] != null && this.richting[1] == this.richting[3];
    }
    
    public boolean hasLine3() {
        return this.richting[0] != null || this.richting[2] != null || (this.richting[1] != null ^ this.richting[3] != null) || this.richting[1] != this.richting[3];
    }
    
    public boolean hasLine4() {
        return this.richting[1] != null || this.richting[3] != null || (this.richting[0] != null ^ this.richting[2] != null) || this.richting[0] != this.richting[2];
    }
}
