import java.awt.Graphics;
import java.awt.Color;
import java.applet.AudioClip;

// 
// Decompiled by Procyon v0.5.30
// 

class Ship
{
    Shape[] s;
    Shooter rounds;
    AudioClip[] sounds;
    int width;
    int height;
    int hull;
    int hc;
    int nc;
    int type;
    int nextshow;
    int showtime;
    double fade;
    double[] anglespeed;
    double[] x_speed;
    double[] y_speed;
    int[] red;
    int[] green;
    int[] blue;
    int damage;
    boolean th;
    boolean alive;
    boolean stop;
    boolean sound_on;
    double[][][] px;
    double[][][] py;
    Color[][] col;
    
    public void checkKey(final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        this.rounds.trigger(b, (double)this.s[0].poly_x[0], (double)this.s[0].poly_y[0], this.s[0].angle, this.sound_on);
        if (b2) {
            if (this.s[0].v_speed < 0.0) {
                this.s[0].v_speed = 0.0;
            }
            final Shape shape = this.s[0];
            shape.v_speed += 0.01;
            if (this.s[0].v_speed > 0.15) {
                this.s[0].v_speed = 0.15;
            }
        }
        if (b3) {
            if (this.s[0].v_speed > 0.0) {
                this.s[0].v_speed = 0.0;
            }
            final Shape shape2 = this.s[0];
            shape2.v_speed -= 0.01;
            if (this.s[0].v_speed < -0.15) {
                this.s[0].v_speed = -0.15;
            }
        }
        if (b2 == b3) {
            this.s[0].v_speed = 0.0;
        }
        if (b4) {
            this.s[0].xySpeed(0.25);
            this.th = true;
            return;
        }
        this.th = false;
    }
    
    public void paint(final Graphics graphics) {
        if (this.fade > 0.1) {
            this.s[1].paint(graphics);
            this.s[0].paint(graphics);
            this.s[2].paint(graphics);
            this.s[3].paint(graphics);
            this.rounds.paint(graphics);
        }
    }
    
    public void solid(final boolean filled) {
        for (int i = 0; i < this.s.length; ++i) {
            this.s[i].filled = filled;
        }
        this.rounds.solid(filled);
    }
    
    public Ship(final int width, final int height, final int n, final int type, final AudioClip[] sounds) {
        this.anglespeed = new double[4];
        this.x_speed = new double[4];
        this.y_speed = new double[4];
        this.red = new int[4];
        this.green = new int[4];
        this.blue = new int[4];
        this.alive = true;
        this.px = new double[][][] { { { -9.0, -9.0, -8.0, -6.0, -4.0, -2.0, -1.0, -1.0, -2.0, -4.0, -6.0, -8.0, -9.0 }, { -1.0, 2.0, 0.0, 2.0, 4.0, 6.0, 7.0, 6.0, 4.0, 2.0, 0.0, 2.0, -1.0 }, { -1.0, -2.0, 6.0, 7.0 }, { -1.0, -2.0, 6.0, 7.0 } }, { { -11.0, -11.0, -10.0, -8.0, -6.0, -6.0, -8.0, -10.0, -11.0 }, { -6.0, 0.0, 0.0, 5.0, 8.0, 6.0, 6.0, 8.0, 5.0, 0.0, 0.0, -6.0 }, { 5.0, 5.0, 11.0, 11.0 }, { 5.0, 5.0, 11.0, 11.0 } }, { { -5.0, -5.0, -4.0, -2.0, 0.0, 7.0, 7.0, 0.0, -2.0, -4.0, -5.0 }, { -2.0, -4.0, -1.0, 4.0, 4.0, -1.0, -4.0, -2.0 }, { -6.0, -6.0, 4.0, 4.0 }, { -6.0, -6.0, 4.0, 4.0 } }, { { -9.0, -5.0, -5.0, 0.0, 0.0, 4.0, 4.0, 0.0, 0.0, -1.0, -1.0, -5.0, -5.0, -9.0 }, { -5.0, 3.0, 3.0, 8.0, 8.0, 0.0, 0.0, 4.0, 4.0, 0.0, 0.0, -5.0 }, { -9.0, -5.0, -5.0, -1.0, -1.0, -9.0 }, { 3.0, 9.0, 9.0, 0.0, 0.0, 8.0, 8.0, 3.0 } } };
        this.py = new double[][][] { { { 0.0, -1.0, -3.0, -4.0, -4.0, -3.0, -1.0, 1.0, 3.0, 4.0, 4.0, 3.0, 1.0 }, { -1.0, -1.0, -3.0, -3.0, -1.0, -1.0, 0.0, 1.0, 1.0, 3.0, 3.0, 1.0, 1.0 }, { -3.0, -4.0, -4.0, -3.0 }, { 3.0, 4.0, 4.0, 3.0 } }, { { 0.0, -1.0, -3.0, -3.0, -1.0, 1.0, 3.0, 3.0, 1.0 }, { -1.0, -1.0, -4.0, -6.0, -6.0, -2.0, 2.0, 6.0, 6.0, 4.0, 1.0, 1.0 }, { -6.0, -7.0, -7.0, -6.0 }, { 6.0, 7.0, 7.0, 6.0 } }, { { 0.0, -1.0, -3.0, -4.0, -4.0, -3.0, 3.0, 4.0, 4.0, 3.0, 1.0 }, { -4.0, -8.0, -8.0, -3.0, 3.0, 8.0, 8.0, 4.0 }, { -8.0, -9.0, -9.0, -8.0 }, { 8.0, 9.0, 9.0, 8.0 } }, { { -9.0, -9.0, -5.0, -5.0, -1.0, -1.0, 4.0, 4.0, 9.0, 9.0, 4.0, 4.0, -2.0, -2.0 }, { -9.0, -9.0, -4.0, -4.0, 7.0, 7.0, 4.0, 4.0, -1.0, -1.0, -5.0, -5.0 }, { -2.0, -2.0, 4.0, 4.0, 9.0, 9.0 }, { -9.0, -9.0, 9.0, 9.0, 7.0, 7.0, -4.0, -4.0 } } };
        this.col = new Color[][] { { new Color(240, 240, 240), new Color(190, 190, 190), new Color(0, 255, 255), new Color(0, 255, 255) }, { new Color(200, 90, 0), new Color(150, 50, 0), new Color(255, 100, 40), new Color(255, 100, 40) }, { new Color(50, 230, 40), new Color(50, 150, 30), new Color(0, 255, 50), new Color(0, 255, 50) }, { new Color(75, 75, 75), new Color(120, 120, 120), new Color(210, 210, 210), new Color(210, 210, 210) } };
        final int n2 = width / 2;
        final int n3 = height / 2;
        this.width = width;
        this.height = height;
        this.hull = 100;
        this.hc = 255;
        this.type = type;
        this.sounds = sounds;
        this.s = new Shape[this.px.length];
        for (int i = 0; i < this.px.length; ++i) {
            for (int j = 0; j < this.px[this.type][i].length; ++j) {
                this.px[this.type][i][j] *= n;
                this.py[this.type][i][j] *= n;
            }
            this.s[i] = new Shape(n2, n3, this.px[this.type][i], this.py[this.type][i], true, width, height, this.col[this.type][i]);
        }
        if (this.type != 0) {
            final double n4 = Math.random() * 6.283185307179586;
            final double n5 = Math.random() * 25.0 + 200.0;
            this.s[0].x = (int)(Math.cos(n4) * n5);
            this.s[0].y = (int)(Math.sin(n4) * n5);
            this.s[0].x_speed = (Math.random() - 0.5) * 8.0;
            this.s[0].y_speed = (Math.random() - 0.5) * 8.0;
            this.alive = false;
        }
        switch (this.type) {
            case 0: {
                this.rounds = new Shooter(this.width, this.height, 100, sounds, new Color(0, 100, 255));
                this.damage = 3;
                break;
            }
            case 1: {
                this.rounds = new Shooter(this.width, this.height, 100, sounds, new Color(255, 0, 50));
                this.damage = 40;
                break;
            }
            case 2: {
                this.rounds = new Shooter(this.width, this.height, 100, sounds, new Color(50, 255, 50));
                this.damage = 40;
                break;
            }
            case 3: {
                this.rounds = new Shooter(this.width, this.height, 100, sounds, new Color(255, 255, 250));
                this.damage = 40;
                break;
            }
        }
        this.nextshow = (int)(Math.random() * 2000.0) + 1000;
    }
    
    public void newlevel() {
        if (this.type == 0) {
            this.s[0].x = this.width / 2;
            this.s[0].y = this.height / 2;
            this.s[0].x_speed = 0.0;
            this.s[0].y_speed = 0.0;
            this.s[0].v_speed = 0.0;
            this.s[0].angle = 0.0;
        }
        this.rounds.ammo = 100;
        for (int i = 0; i < this.rounds.s.length; ++i) {
            this.rounds.s[i].alive = false;
        }
    }
    
    public void checkAuto(final Ship ship) {
        if (this.alive) {
            this.s[0].v_speed = (Math.atan2(this.s[0].y - ship.s[0].y, this.s[0].x - ship.s[0].x) - this.s[0].angle) / 16.0;
            if (this.showtime % 12 == 0) {
                this.rounds.trigger(true, (double)this.s[0].poly_x[0], (double)this.s[0].poly_y[0], this.s[0].angle, this.sound_on);
                this.rounds.trigger(false, 0.0, 0.0, 0.0, false);
            }
            if (this.showtime > 500) {
                this.alive = false;
            }
        }
        else if (this.showtime > this.nextshow) {
            this.nextshow = (int)(Math.random() * 2000.0) + 1000;
            this.showtime = 0;
            final double n = Math.random() * 6.283185307179586;
            final double n2 = Math.random() * 25.0 + 200.0;
            this.s[0].x = (int)(Math.cos(n) * n2);
            this.s[0].y = (int)(Math.sin(n) * n2);
            this.s[0].x_speed = (Math.random() - 0.5) * 8.0;
            this.s[0].y_speed = (Math.random() - 0.5) * 8.0;
            this.s[0].angle = Math.atan2(this.s[0].y - ship.s[0].y, this.s[0].x - ship.s[0].x);
            this.alive = true;
            this.hull = 100;
        }
        ++this.showtime;
    }
    
    public boolean check(final Ship ship) {
        if (ship.alive && this.alive && ship.rounds.check(this.s[0])) {
            this.hull -= this.damage;
            this.hc = 50;
            if (this.sound_on) {
                this.sounds[1].play();
            }
            return true;
        }
        return false;
    }
    
    public boolean check(final Rocks rocks) {
        for (int i = 0; i < this.rounds.s.length; ++i) {
            if (this.rounds.s[i].alive && rocks.check(this.rounds.s[i].s)) {
                this.rounds.s[i].hit();
                return true;
            }
        }
        if (rocks.check(this.s[0])) {
            this.hull -= rocks.damage;
            this.hc = 50;
            return true;
        }
        return false;
    }
    
    public void move() {
        if (this.alive) {
            if (this.hull < 0) {
                this.alive = false;
                return;
            }
            for (int i = 0; i < this.s.length; ++i) {
                this.s[i].x = this.s[0].x;
                this.s[i].y = this.s[0].y;
                this.s[i].angle = this.s[0].angle;
                this.s[i].move();
            }
            this.rounds.move();
            if (this.fade < 1.0 && !this.stop) {
                for (int j = 0; j < this.s.length; ++j) {
                    this.s[j].setCol(this.fade);
                }
                this.fade += 0.02;
                return;
            }
            if (!this.stop) {
                switch (this.type) {
                    case 0: {
                        if (this.hc < 240) {
                            this.hc += this.hull / 10 + 1;
                            this.s[0].setCol(240, this.hc, this.hc);
                        }
                        if (this.th) {
                            if ((this.nc += 33) >= 100) {
                                this.nc = 0;
                            }
                            this.s[2].setCol(0, this.nc + 156, this.nc + 156);
                            this.s[3].setCol(0, this.nc + 156, this.nc + 156);
                            return;
                        }
                        this.nc = 0;
                        this.s[2].setCol(0, 255, 255);
                        this.s[3].setCol(0, 255, 255);
                    }
                    case 1: {
                        if (this.hc < 200) {
                            this.hc += this.hull / 10 + 1;
                            this.s[0].setCol(200, 290 - this.hc, 220 - this.hc);
                        }
                        if (this.th) {
                            if ((this.nc += 33) >= 100) {
                                this.nc = 0;
                            }
                            this.s[2].setCol(this.nc + 156, this.nc, 40);
                            this.s[3].setCol(this.nc + 156, this.nc, 40);
                            return;
                        }
                        this.nc = 0;
                        this.s[2].setCol(255, 100, 40);
                        this.s[3].setCol(255, 100, 40);
                    }
                    case 2: {
                        if (this.hc < 230) {
                            this.hc += this.hull / 10 + 1;
                            this.s[0].setCol(255 - this.hc, this.hc, 40);
                        }
                        if (this.th) {
                            if ((this.nc += 33) >= 100) {
                                this.nc = 0;
                            }
                            this.s[2].setCol(0, this.nc + 156, this.nc);
                            this.s[3].setCol(0, this.nc + 156, this.nc);
                            return;
                        }
                        this.nc = 0;
                        this.s[2].setCol(0, 255, 50);
                        this.s[3].setCol(0, 255, 50);
                    }
                    case 3: {
                        if (this.hc < 75) {
                            this.hc += this.hull / 10 + 1;
                            this.s[0].setCol(125 - this.hc, this.hc, this.hc);
                            this.s[1].setCol(195 - this.hc, this.hc + 45, this.hc + 45);
                        }
                        if (this.th) {
                            if ((this.nc += 33) >= 100) {
                                this.nc = 0;
                            }
                            this.s[2].setCol(0, this.nc + 156, this.nc + 156);
                            this.s[3].setCol(0, this.nc + 156, this.nc + 156);
                            return;
                        }
                        this.nc = 0;
                        this.s[2].setCol(210, 210, 210);
                        this.s[3].setCol(210, 210, 210);
                    }
                }
            }
        }
        else {
            if (this.fade > 0.0) {
                for (int k = 0; k < this.s.length; ++k) {
                    this.s[k].setCol(this.fade);
                    if (this.hull <= 0) {
                        this.s[k].random();
                    }
                    else {
                        this.s[k].x = this.s[0].x;
                        this.s[k].y = this.s[0].y;
                        this.s[k].angle = this.s[0].angle;
                    }
                    this.s[k].move();
                    this.rounds.move();
                }
                this.fade -= 0.02;
                return;
            }
            for (int l = 0; l < this.s.length; ++l) {
                this.s[l].setNullSpeed();
            }
        }
    }
}
