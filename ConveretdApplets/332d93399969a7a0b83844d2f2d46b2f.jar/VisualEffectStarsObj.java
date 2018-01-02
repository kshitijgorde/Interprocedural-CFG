import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class VisualEffectStarsObj
{
    public int w;
    public int h;
    public int x;
    public int y;
    public int maxspeed;
    public int dir;
    public int sx;
    public int sy;
    public int size;
    public int maxsize;
    private float bright;
    private int r;
    private int g;
    private int b;
    private Color basecolor;
    private Color color;
    public int slen;
    
    public VisualEffectStarsObj(final int w, final int h, final int maxspeed, final int dir, final int maxsize) {
        this.w = 0;
        this.h = 0;
        this.x = 0;
        this.y = 0;
        this.maxspeed = 5;
        this.dir = 1;
        this.sx = 0;
        this.sy = 0;
        this.size = 1;
        this.maxsize = 2;
        this.bright = 1.0f;
        this.r = 255;
        this.g = 255;
        this.b = 255;
        this.basecolor = Color.white;
        this.color = Color.white;
        this.slen = 10;
        this.w = w;
        this.h = h;
        this.maxspeed = maxspeed;
        this.maxsize = maxsize;
        this.dir = dir;
        this.x = (int)(Math.random() * w);
        this.x = ((this.x < 5) ? 5 : ((this.x > w - 5) ? (w - 5) : this.x));
        this.y = (int)(Math.random() * h);
        this.y = ((this.y < 5) ? 5 : ((this.y > h - 5) ? (h - 5) : this.y));
        this.r = (int)(Math.random() * 76.0) + 180;
        this.g = (int)(Math.random() * 76.0) + 180;
        this.b = (int)(Math.random() * 76.0) + 180;
        this.basecolor = new Color(this.r, this.g, this.b);
        this.size = 1 + (int)(Math.random() * this.maxsize + 0.5);
    }
    
    public Color getBaseColor() {
        return this.basecolor;
    }
    
    public Color getNewColor() {
        this.bright = (float)Math.random() * 0.6f + 0.7f;
        int n = (int)(this.bright * this.r);
        int n2 = (int)(this.bright * this.g);
        int n3 = (int)(this.bright * this.b);
        if (n > 255) {
            n = 255;
        }
        if (n2 > 255) {
            n2 = 255;
        }
        if (n3 > 255) {
            n3 = 255;
        }
        return this.color = new Color(n, n2, n3);
    }
    
    public void move() {
        this.x += this.dir * (int)(Math.random() * (this.maxspeed + 1));
        if (this.x < 0) {
            this.x = this.w - 1;
        }
        else if (this.x > this.w - 1) {
            this.x = 0;
        }
    }
}
