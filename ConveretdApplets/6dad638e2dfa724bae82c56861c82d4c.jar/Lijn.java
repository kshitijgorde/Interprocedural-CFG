import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class Lijn extends Verbinding
{
    private Punt a;
    private Punt c;
    private boolean power;
    
    public Lijn(final Punt a, final Punt c) {
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
        if (super.actief && this.power) {
            graphics.setColor(Color.red);
            if (this.a.x == this.c.x) {
                graphics.drawLine(this.a.x - 1, this.a.y, this.c.x - 1, this.c.y);
                graphics.drawLine(this.a.x + 1, this.a.y, this.c.x + 1, this.c.y);
            }
            else {
                graphics.drawLine(this.a.x, this.a.y - 1, this.c.x, this.c.y - 1);
                graphics.drawLine(this.a.x, this.a.y + 1, this.c.x, this.c.y + 1);
            }
        }
        graphics.setColor(Color.black);
        if (this.a.x == this.c.x) {
            if (this.c.y > this.a.y) {
                graphics.drawLine(this.a.x - 2, this.a.y / 2 + this.c.y / 2 - 1, this.a.x, this.a.y / 2 + this.c.y / 2 + 1);
                graphics.drawLine(this.a.x + 2, this.a.y / 2 + this.c.y / 2 - 1, this.a.x, this.a.y / 2 + this.c.y / 2 + 1);
            }
            else {
                graphics.drawLine(this.a.x - 2, this.a.y / 2 + this.c.y / 2 + 1, this.a.x, this.a.y / 2 + this.c.y / 2 - 1);
                graphics.drawLine(this.a.x + 2, this.a.y / 2 + this.c.y / 2 + 1, this.a.x, this.a.y / 2 + this.c.y / 2 - 1);
            }
        }
        else if (this.c.x > this.a.x) {
            graphics.drawLine(this.a.x / 2 + this.c.x / 2 - 1, this.c.y - 2, this.a.x / 2 + this.c.x / 2 + 1, this.c.y);
            graphics.drawLine(this.a.x / 2 + this.c.x / 2 - 1, this.c.y + 2, this.a.x / 2 + this.c.x / 2 + 1, this.c.y);
        }
        else {
            graphics.drawLine(this.a.x / 2 + this.c.x / 2 + 1, this.c.y - 2, this.a.x / 2 + this.c.x / 2 - 1, this.c.y);
            graphics.drawLine(this.a.x / 2 + this.c.x / 2 + 1, this.c.y + 2, this.a.x / 2 + this.c.x / 2 - 1, this.c.y);
        }
    }
    
    public void activeer(final boolean power, final Punt punt) {
        if (!super.actief || this.power != power) {
            super.actief = true;
            this.power = power;
            this.c.activeer(power);
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
