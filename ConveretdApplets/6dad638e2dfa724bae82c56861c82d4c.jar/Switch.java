import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class Switch extends Verbinding
{
    private Punt c;
    private boolean power;
    
    public Switch(final Punt c) {
        this.power = false;
        this.c = c;
    }
    
    public void paint(final Graphics graphics, final ImageObserver imageObserver, final Punt punt) {
        if (punt == this.c) {
            this.update(graphics, imageObserver);
        }
    }
    
    public void update(final Graphics graphics, final ImageObserver imageObserver) {
        graphics.setColor(Color.black);
        graphics.drawLine(this.c.x - 10, this.c.y, this.c.x, this.c.y);
        if (super.actief) {
            graphics.setColor(Color.red);
            if (this.power) {
                graphics.drawLine(this.c.x - 10, this.c.y - 1, this.c.x, this.c.y - 1);
                graphics.drawLine(this.c.x - 10, this.c.y + 1, this.c.x, this.c.y + 1);
            }
        }
        graphics.drawImage(Verbinding.foto, this.c.x - 35, this.c.y - 15, this.c.x - 5, this.c.y + 15, 180, super.actief ? (this.power ? 60 : 30) : 0, 210, super.actief ? (this.power ? 90 : 60) : 30, imageObserver);
    }
    
    public void activeer(final boolean b, final Punt punt) {
        if (!super.actief || b) {
            this.power = (b && !this.power);
            super.actief = true;
            this.c.activeer(this.power);
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
        return verbinding == this;
    }
}
