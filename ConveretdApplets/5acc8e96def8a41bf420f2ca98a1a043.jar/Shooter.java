import java.awt.Graphics;
import java.awt.Color;
import java.applet.AudioClip;

// 
// Decompiled by Procyon v0.5.30
// 

class Shooter
{
    Shot[] s;
    AudioClip[] sounds;
    int width;
    int height;
    int ammo;
    boolean inhibit;
    boolean sound_on;
    
    public Shooter(final int width, final int height, final int ammo, final AudioClip[] sounds, final Color color) {
        this.s = new Shot[20];
        this.sound_on = true;
        this.ammo = ammo;
        this.sounds = sounds;
        int n = 0;
        do {
            this.s[n] = new Shot(0, 0, width, height, 1, color);
        } while (++n < 20);
        this.width = width;
        this.height = height;
        this.inhibit = false;
    }
    
    public void trigger(final boolean b, final double x, final double y, final double angle, final boolean b2) {
        if (b && this.ammo > 0) {
            for (int i = 0; i < this.s.length; ++i) {
                if (!this.s[i].alive && !this.inhibit) {
                    this.inhibit = true;
                    this.s[i].life = 40;
                    this.s[i].s.angle = angle;
                    this.s[i].s.x = x;
                    this.s[i].s.y = y;
                    this.s[i].s.x_speed = 0.0;
                    this.s[i].s.y_speed = 0.0;
                    this.s[i].s.xySpeed(8.0);
                    this.s[i].trigger = true;
                    --this.ammo;
                    if (b2) {
                        this.sounds[0].play();
                    }
                    return;
                }
            }
            return;
        }
        this.inhibit = false;
    }
    
    public void paint(final Graphics graphics) {
        for (int i = 0; i < this.s.length; ++i) {
            this.s[i].paint(graphics);
        }
    }
    
    public void solid(final boolean filled) {
        for (int i = 0; i < this.s.length; ++i) {
            this.s[i].s.filled = filled;
        }
    }
    
    public boolean check(final Shape shape) {
        for (int i = 0; i < this.s.length; ++i) {
            if (this.s[i].alive && this.s[i].check(shape)) {
                return true;
            }
        }
        return false;
    }
    
    public void move() {
        for (int i = 0; i < this.s.length; ++i) {
            this.s[i].move();
        }
    }
}
