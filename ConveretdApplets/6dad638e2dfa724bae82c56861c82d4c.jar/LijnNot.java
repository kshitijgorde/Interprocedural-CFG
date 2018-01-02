import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class LijnNot extends Verbinding
{
    private Punt a;
    private Punt c;
    private boolean power;
    
    public LijnNot(final Punt a, final Punt c) {
        this.power = false;
        this.a = a;
        this.c = c;
    }
    
    public void paint(final Graphics graphics, final ImageObserver imageObserver, final Punt punt) {
        if (punt == this.a) {
            this.update(graphics, imageObserver);
        }
    }
    
    public void update(final Graphics graphics, final ImageObserver imageObserver) {
        graphics.setColor(Color.black);
        graphics.drawLine(this.a.x, this.a.y, this.c.x, this.c.y);
        if (super.actief) {
            graphics.setColor(Color.red);
            if (this.power) {
                graphics.drawLine(this.a.x, this.a.y - 1, this.a.x + 10, this.c.y - 1);
                graphics.drawLine(this.a.x, this.a.y + 1, this.a.x + 10, this.c.y + 1);
            }
            else {
                graphics.drawLine(this.c.x - 10, this.a.y - 1, this.c.x, this.c.y - 1);
                graphics.drawLine(this.c.x - 10, this.a.y + 1, this.c.x, this.c.y + 1);
            }
        }
        graphics.drawImage(Verbinding.foto, this.a.x + 5, this.a.y - 5, this.c.x - 5, this.c.y + 5, 180 + (super.actief ? (this.power ? 20 : 10) : 0), 90, 190 + (super.actief ? (this.power ? 20 : 10) : 0), 100, imageObserver);
    }
    
    public void activeer(final boolean power, final Punt punt) {
        if (!super.actief || this.power != power) {
            super.actief = true;
            this.power = power;
            this.c.activeer(!power);
        }
    }
    
    public void deActiveer() {
        if (super.actief) {
            super.actief = false;
            this.power = false;
            this.c.deActiveer();
        }
    }
    
    public boolean lusControle(final Verbinding verbinding) {
        return verbinding == this || this.a.lusControle(verbinding);
    }
}
