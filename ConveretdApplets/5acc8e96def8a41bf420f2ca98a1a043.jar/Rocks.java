import java.awt.Graphics;
import java.applet.AudioClip;

// 
// Decompiled by Procyon v0.5.30
// 

class Rocks
{
    Rock[][] s;
    AudioClip[] sounds;
    int width;
    int height;
    int n;
    int scale;
    int p;
    int damage;
    int remain;
    boolean filled;
    boolean sound_on;
    
    public Rocks(final int width, final int height, final int n, final AudioClip[] sounds) {
        this.width = width;
        this.height = height;
        this.n = n;
        this.p = 0;
        this.remain = 1;
        this.sounds = sounds;
        this.s = new Rock[n][35];
        final double[] array = { 8.0, 5.0, 5.0, 2.0, 2.0, 2.0, 2.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
        for (int i = 0; i < this.n; ++i) {
            final double n2 = Math.random() * 6.283185307179586;
            final double n3 = Math.random() * 25.0 + 200.0;
            final int n4 = (int)(Math.cos(n2) * n3);
            final int n5 = (int)(Math.sin(n2) * n3);
            int n6 = 0;
            do {
                if (n6 > 6) {
                    array[n6] = Math.random() + 0.1;
                }
                this.s[i][n6] = new Rock(n4 + this.width / 2, n5 + this.height / 2, this.width, this.height, array[n6], this.filled);
                if (n6 == 0) {
                    this.s[i][n6].alive = true;
                }
            } while (++n6 < 35);
        }
    }
    
    public void paint(final Graphics graphics) {
        for (int i = 0; i < this.n; ++i) {
            int n = 0;
            do {
                this.s[i][n].paint(graphics);
            } while (++n < 34);
        }
    }
    
    public void solid(final boolean filled) {
        for (int i = 0; i < this.s.length; ++i) {
            for (int j = 0; j < this.s[i].length; ++j) {
                this.s[i][j].s.filled = filled;
            }
        }
    }
    
    public boolean check(final Shape shape) {
        int i = 0;
    Label_1339:
        while (i < this.n) {
            int n = 0;
            while (!this.s[i][n].alive || !this.s[i][n].check(shape)) {
                if (++n >= 7) {
                    ++i;
                    continue Label_1339;
                }
            }
            switch (n) {
                case 0: {
                    this.s[i][n + 1].s.x = this.s[i][n].s.x;
                    this.s[i][n + 1].s.y = this.s[i][n].s.y;
                    this.s[i][n + 2].s.x = this.s[i][n].s.x;
                    this.s[i][n + 2].s.y = this.s[i][n].s.y;
                    this.s[i][n].alive = false;
                    this.s[i][n + 1].revive = true;
                    this.s[i][n + 2].revive = true;
                    this.damage = 17;
                    int n2 = 7;
                    do {
                        this.s[i][n2].explode = true;
                        this.s[i][n2].s.x = this.s[i][n].s.x;
                        this.s[i][n2].s.y = this.s[i][n].s.y;
                    } while (++n2 < 14);
                    this.p = 100;
                    break;
                }
                case 1: {
                    this.s[i][n + 2].s.x = this.s[i][n].s.x;
                    this.s[i][n + 2].s.y = this.s[i][n].s.y;
                    this.s[i][n + 3].s.x = this.s[i][n].s.x;
                    this.s[i][n + 3].s.y = this.s[i][n].s.y;
                    this.s[i][n].alive = false;
                    this.s[i][n + 2].revive = true;
                    this.s[i][n + 3].revive = true;
                    this.damage = 7;
                    int n3 = 14;
                    do {
                        this.s[i][n3].explode = true;
                        this.s[i][n3].s.x = this.s[i][n].s.x;
                        this.s[i][n3].s.y = this.s[i][n].s.y;
                    } while (++n3 < 18);
                    this.p = 200;
                    break;
                }
                case 2: {
                    this.s[i][n + 3].s.x = this.s[i][n].s.x;
                    this.s[i][n + 3].s.y = this.s[i][n].s.y;
                    this.s[i][n + 4].s.x = this.s[i][n].s.x;
                    this.s[i][n + 4].s.y = this.s[i][n].s.y;
                    this.s[i][n].alive = false;
                    this.s[i][n + 3].revive = true;
                    this.s[i][n + 4].revive = true;
                    this.damage = 7;
                    int n4 = 18;
                    do {
                        this.s[i][n4].explode = true;
                        this.s[i][n4].s.x = this.s[i][n].s.x;
                        this.s[i][n4].s.y = this.s[i][n].s.y;
                    } while (++n4 < 22);
                    this.p = 200;
                    break;
                }
                case 3: {
                    this.damage = 3;
                    this.s[i][n].alive = false;
                    int n5 = 22;
                    do {
                        this.s[i][n5].explode = true;
                        this.s[i][n5].s.x = this.s[i][n].s.x;
                        this.s[i][n5].s.y = this.s[i][n].s.y;
                    } while (++n5 < 25);
                    this.p = 350;
                    break;
                }
                case 4: {
                    this.damage = 3;
                    this.s[i][n].alive = false;
                    int n6 = 25;
                    do {
                        this.s[i][n6].explode = true;
                        this.s[i][n6].s.x = this.s[i][n].s.x;
                        this.s[i][n6].s.y = this.s[i][n].s.y;
                    } while (++n6 < 28);
                    this.p = 350;
                    break;
                }
                case 5: {
                    this.damage = 3;
                    this.s[i][n].alive = false;
                    int n7 = 28;
                    do {
                        this.s[i][n7].explode = true;
                        this.s[i][n7].s.x = this.s[i][n].s.x;
                        this.s[i][n7].s.y = this.s[i][n].s.y;
                    } while (++n7 < 31);
                    this.p = 350;
                    break;
                }
                case 6: {
                    this.damage = 3;
                    this.s[i][n].alive = false;
                    int n8 = 31;
                    do {
                        this.s[i][n8].explode = true;
                        this.s[i][n8].s.x = this.s[i][n].s.x;
                        this.s[i][n8].s.y = this.s[i][n].s.y;
                    } while (++n8 < 34);
                    this.p = 350;
                    break;
                }
            }
            if (this.sound_on) {
                this.sounds[2].play();
            }
            return true;
        }
        return false;
    }
    
    public void move() {
        int remain = 0;
        for (int i = 0; i < this.n; ++i) {
            int n = 0;
            do {
                this.s[i][n].move();
                if (this.s[i][n].explode != this.s[i][n].alive) {
                    ++remain;
                }
            } while (++n < 34);
        }
        this.remain = remain;
    }
}
