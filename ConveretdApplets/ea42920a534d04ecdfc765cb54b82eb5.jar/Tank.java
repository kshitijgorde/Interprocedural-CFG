import java.awt.Color;
import java.awt.Graphics;
import java.applet.AudioClip;
import java.awt.Rectangle;
import gamelib.ActionBuffer;
import gamelib.AVEntry;
import gamelib.OffComponent;

// 
// Decompiled by Procyon v0.5.30
// 

class Tank extends OffComponent
{
    Player owner;
    float a;
    float v;
    float h;
    int x;
    int y;
    private static AVEntry snd;
    
    static {
        Tank.snd = null;
    }
    
    Tank(final ActionBuffer actionBuffer, final Player owner) {
        super(actionBuffer, new Rectangle(-100, -100, 26, 26));
        this.a = 0.0f;
        this.v = 100.0f;
        this.h = 100.0f;
        this.owner = owner;
        if (Tank.snd == null) {
            Tank.snd = new AVEntry("Tank.au", 1);
        }
        this.setVisible(true);
    }
    
    void activate() {
        ((Field)super.buffer).tc.block = false;
        final Tank actt = ((Field)super.buffer).actt;
        (((Field)super.buffer).actt = this).repaint();
        if (actt != null) {
            actt.repaint();
        }
    }
    
    void addDamage(float n) {
        if (n < 0.0f) {
            n = 0.0f;
        }
        final int y = this.y;
        this.setPosition(this.x);
        if (this.y - y > 0) {
            n += (this.y - y) * 3.0f;
        }
        n = (float)Math.floor(n);
        this.h -= n;
        if (this.h < 0.0f) {
            if (this.h + n > 0.0f) {
                this.h = 0.0f;
                for (int i = 0; i < 4; ++i) {
                    new Explosive(super.buffer, 25).ignite(this.x + TankApplet.gen.nextInt() % 16, this.y - Math.abs(TankApplet.gen.nextInt() % 16));
                }
                final AudioClip clip = Tank.snd.getClip();
                if (clip != null) {
                    clip.play();
                }
                this.repaint();
            }
            else {
                this.h = 0.0f;
            }
        }
    }
    
    void impactAt(final int n) {
    }
    
    protected void paint(final Graphics graphics) {
        final int n = this.x + (int)(12.0 * Math.sin(this.a * 2.0 * 3.141592653589793 / 360.0));
        final int n2 = this.y - (int)(12.0 * Math.cos(this.a * 2.0 * 3.141592653589793 / 360.0));
        if (this.h > 0.0f) {
            if (((Field)super.buffer).actt == this) {
                graphics.setColor(Color.white);
            }
            else {
                graphics.setColor(Color.black);
            }
            graphics.fillOval(this.x - 5, this.y - 5, 11, 11);
            graphics.drawLine(this.x - 1, this.y, n - 1, n2);
            graphics.drawLine(this.x + 1, this.y, n + 1, n2);
            graphics.drawLine(this.x, this.y - 1, n, n2 - 1);
            graphics.drawLine(this.x, this.y + 1, n, n2 + 1);
            graphics.setColor(this.owner.color);
        }
        else {
            graphics.setColor(Color.black);
        }
        graphics.fillOval(this.x - 4, this.y - 4, 9, 9);
        graphics.drawLine(this.x, this.y, n, n2);
    }
    
    void setPosition(final int x) {
        this.x = x;
        this.y = ((Field)super.buffer).ground.getHorizon(this.x);
        this.setPosition(this.x - 13, this.y - 13);
    }
    
    void shoot() {
        ((Field)super.buffer).tc.block = true;
        new Missile(super.buffer, this.x + (int)(12.0 * Math.sin(this.a * 2.0 * 3.141592653589793 / 360.0)), this.y - (int)(12.0 * Math.cos(this.a * 2.0 * 3.141592653589793 / 360.0)), this.a, this.v, new Explosive(super.buffer, 33));
        ((Field)super.buffer).next();
    }
}
