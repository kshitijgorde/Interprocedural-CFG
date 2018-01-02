import java.awt.image.ImageObserver;
import java.applet.AudioClip;
import java.awt.Canvas;
import java.awt.Image;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class Rock extends SpaceObject implements Runnable
{
    private int x;
    private int y;
    private int i;
    private Graphics screen;
    private Image[] frames;
    private Canvas theScreen;
    private AudioClip[] sounds;
    private boolean[] cities;
    private int[] cityPos;
    private int t;
    private int hit;
    private int timeout;
    
    Rock(final Image[] frames, final AudioClip[] sounds, final boolean[] cities, final int[] cityPos, final Canvas theScreen) {
        this.t = 1;
        this.x = (int)(Math.random() * Rocks.screen_area);
        this.y = -10;
        if (Math.random() <= 0.5) {
            this.i = (int)(Math.random() * 5.0);
        }
        else {
            this.i = -(int)(Math.random() * 5.0);
        }
        this.t = -(int)(Math.random() * 20.0);
        this.frames = frames;
        this.sounds = sounds;
        this.cities = cities;
        this.cityPos = cityPos;
        this.theScreen = theScreen;
    }
    
    public void paint(final Graphics graphics) {
        if (super.alive) {
            graphics.fillOval(this.x, this.y, 10, 10);
            return;
        }
        if (this.timeout % 1000000000 == 0 && this.hit < Rocks.no_of_rock_frames) {
            graphics.drawImage(this.frames[this.hit++], this.x - 25, this.y - 25, this.theScreen);
        }
        ++this.timeout;
    }
    
    public void isAHit(final int n, final int n2) {
        if (Math.abs(n - this.x) < 30 && Math.abs(n2 - this.y) < 30) {
            super.alive = false;
            this.sounds[0].play();
        }
    }
    
    public void run() {
        try {
            while (super.alive) {
                if (super.move) {
                    this.x += this.i;
                    if (this.x <= 0 || this.x >= Rocks.screen_area) {
                        this.i = -this.i;
                    }
                    this.y = (int)Math.pow(this.t, 2.0);
                    if (this.y > 390) {
                        super.alive = false;
                        this.y -= 10;
                        for (int i = 0; i < Rocks.no_of_cities; ++i) {
                            if (Math.abs(this.x - this.cityPos[i]) <= 20 && this.cities[i]) {
                                this.cities[i] = false;
                                this.sounds[1].play();
                            }
                            else {
                                this.sounds[0].play();
                            }
                        }
                    }
                    ++this.t;
                }
                Thread.currentThread();
                Thread.sleep(120L);
            }
        }
        catch (InterruptedException ex) {}
    }
}
