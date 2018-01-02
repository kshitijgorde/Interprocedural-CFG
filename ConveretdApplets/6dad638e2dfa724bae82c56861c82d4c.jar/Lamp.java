import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class Lamp extends Verbinding
{
    private Punt a;
    private boolean power;
    
    public Lamp(final Punt a) {
        this.power = false;
        this.a = a;
    }
    
    public void paint(final Graphics graphics, final ImageObserver imageObserver, final Punt punt) {
        if (punt == this.a) {
            this.update(graphics, imageObserver);
        }
    }
    
    public void update(final Graphics graphics, final ImageObserver imageObserver) {
        graphics.setColor(Color.black);
        graphics.drawLine(this.a.x, this.a.y, this.a.x + 10, this.a.y);
        if (super.actief) {
            graphics.setColor(Color.red);
            if (this.power) {
                graphics.drawLine(this.a.x, this.a.y - 1, this.a.x + 10, this.a.y - 1);
                graphics.drawLine(this.a.x, this.a.y + 1, this.a.x + 10, this.a.y + 1);
            }
        }
        graphics.drawImage(Verbinding.foto, this.a.x + 5, this.a.y - 15, this.a.x + 35, this.a.y + 15, 210, super.actief ? (this.power ? 60 : 30) : 0, 240, super.actief ? (this.power ? 90 : 60) : 30, imageObserver);
    }
    
    public void activeer(final boolean power, final Punt punt) {
        super.actief = true;
        this.power = power;
    }
    
    public void deActiveer() {
        super.actief = false;
        this.power = false;
    }
    
    public boolean lusControle(final Verbinding verbinding) {
        return verbinding == this;
    }
}
