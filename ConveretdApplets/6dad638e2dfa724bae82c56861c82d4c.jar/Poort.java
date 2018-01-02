import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class Poort extends Verbinding
{
    private Punt a;
    private Punt b;
    private Punt c;
    private int soort;
    private boolean powera;
    private boolean powerb;
    private boolean powerc;
    private boolean actiefa;
    private boolean actiefb;
    
    public Poort(final Punt a, final Punt b, final Punt c, final int soort) {
        this.soort = 0;
        this.powera = false;
        this.powerb = false;
        this.powerc = false;
        this.actiefa = false;
        this.actiefb = false;
        this.a = a;
        this.b = b;
        this.c = c;
        this.soort = soort;
    }
    
    public void paint(final Graphics graphics, final ImageObserver imageObserver, final Punt punt) {
        if (punt == this.a) {
            this.update(graphics, imageObserver);
        }
    }
    
    public void update(final Graphics graphics, final ImageObserver imageObserver) {
        graphics.setColor(Color.black);
        graphics.drawLine(this.a.x, this.a.y, this.a.x + 10, this.a.y);
        graphics.drawLine(this.b.x, this.b.y, this.b.x + 10, this.b.y);
        graphics.drawLine(this.c.x - 10, this.c.y, this.c.x, this.c.y);
        if (super.actief) {
            graphics.setColor(Color.red);
            if (this.powera) {
                graphics.drawLine(this.a.x, this.a.y - 1, this.a.x + 10, this.a.y - 1);
                graphics.drawLine(this.a.x, this.a.y + 1, this.a.x + 10, this.a.y + 1);
            }
            if (this.powerb) {
                graphics.drawLine(this.b.x, this.b.y - 1, this.b.x + 10, this.b.y - 1);
                graphics.drawLine(this.b.x, this.b.y + 1, this.b.x + 10, this.b.y + 1);
            }
            if (this.powerc) {
                graphics.drawLine(this.c.x - 10, this.c.y - 1, this.c.x, this.c.y - 1);
                graphics.drawLine(this.c.x - 10, this.c.y + 1, this.c.x, this.c.y + 1);
            }
        }
        graphics.drawImage(Verbinding.foto, this.a.x + 5, this.a.y - 5, this.c.x - 5, this.c.y + 15, this.soort * 30, super.actief ? (this.powera ? (this.powerb ? 120 : 90) : (this.powerb ? 60 : 30)) : 0, (this.soort + 1) * 30, super.actief ? (this.powera ? (this.powerb ? 150 : 120) : (this.powerb ? 90 : 60)) : 30, imageObserver);
    }
    
    public void activeer(final boolean b, final Punt punt) {
        if ((this.a == punt && !this.actiefa) || (this.b == punt && !this.actiefb) || (this.a == punt && this.powera != b) || (this.b == punt && this.powerb != b)) {
            if (this.a == punt) {
                this.actiefa = true;
                this.powera = b;
            }
            if (this.b == punt) {
                this.actiefb = true;
                this.powerb = b;
            }
            if (this.actiefa && this.actiefb) {
                super.actief = true;
                switch (this.soort) {
                    case 0: {
                        this.powerc = (this.powera && this.powerb);
                        break;
                    }
                    case 1: {
                        this.powerc = (!this.powera || !this.powerb);
                        break;
                    }
                    case 2: {
                        this.powerc = (this.powera || this.powerb);
                        break;
                    }
                    case 3: {
                        this.powerc = (!this.powera && !this.powerb);
                        break;
                    }
                    case 4: {
                        this.powerc = (this.powera ^ this.powerb);
                        break;
                    }
                    case 5: {
                        this.powerc = !(this.powera ^ this.powerb);
                        break;
                    }
                }
                this.c.activeer(this.powerc);
            }
        }
    }
    
    public void deActiveer() {
        if (this.actiefa || this.actiefb) {
            this.actiefa = false;
            this.actiefb = false;
            this.powera = false;
            this.powerb = false;
            if (super.actief) {
                super.actief = false;
                this.powerc = false;
                this.c.deActiveer();
            }
        }
    }
    
    public boolean lusControle(final Verbinding verbinding) {
        return verbinding == this || this.a.lusControle(verbinding) || this.b.lusControle(verbinding);
    }
}
