import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Dimension;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Sabotage extends Applet implements Runnable
{
    Dimension d;
    Font largefont;
    Font medfont;
    Font smallfont;
    FontMetrics fmsmall;
    FontMetrics fmlarge;
    FontMetrics fmmed;
    Graphics goff;
    Image ii;
    Thread thethread;
    MediaTracker thetracker;
    Image chopper_left_1;
    Image chopper_left_2;
    Image chopper_right_1;
    Image chopper_right_2;
    Image paratrooper1;
    Image paratrooper2;
    boolean steerablebullets;
    boolean ingame;
    boolean showtitle;
    final int screendelay = 250;
    int count;
    final Color backgnd;
    final Color turretbasecolor;
    final Color turretcolor;
    final Color guncolor;
    final Color gndcolor;
    final Color bulletcolor;
    final double pi = 3.141592653589793;
    final double rotdelta = 0.039269908169872414;
    double dangle;
    final double gunminangle = 0.07853981633974483;
    int[] xcoord;
    int[] ycoord;
    final int polycoords = 4;
    final double[] gunpoly_x;
    final double[] gunpoly_y;
    final double gunsize = 20.0;
    double gunangle;
    int turretbase_x;
    int turretbase_y;
    int turret_x;
    int turret_y;
    int gun_x;
    int gun_y;
    int gnd_y;
    final int turretbase_w = 40;
    final int turretbase_h = 30;
    final int turret_w = 20;
    final int turret_h = 15;
    int landed;
    double[] bullet_x;
    double[] bullet_y;
    double[] bullet_angle;
    boolean[] bullet_on;
    final double bulletspeed = 4.0;
    final int maxbullets = 10;
    boolean chopperanim;
    final int animdelay = 3;
    final int chopper_speed = 3;
    int animcount;
    final int min_chopper_delay = 40;
    final int max_chopper_delay = 150;
    int chopper_delay;
    int chopper_count;
    int[] chopper1_x;
    int[] chopper2_x;
    boolean[] chopper1_v;
    boolean[] chopper2_v;
    final int max_choppers = 6;
    final int max_paras = 20;
    final int min_paras = 6;
    int num_paras;
    int[] para_x;
    int[] para_y;
    int[] para_freefall;
    boolean[] para_v;
    int freefall_min;
    int freefall_max;
    int freefall;
    final int paraspeed_slow = 1;
    final int paraspeed_fast = 4;
    final int para_delay_max = 200;
    final int para_delay_min = 50;
    int para_delay;
    int para_count;
    int score;
    final int exp_dots = 10;
    double[] exp_x;
    double[] exp_y;
    double[] exp_vx;
    double[] exp_vy;
    final double max_vx = 7.0;
    final double max_vy = 4.0;
    final double exp_g = 0.3;
    double exp_color;
    final double dexp_color = 0.01;
    boolean explosion;
    final int diffdelay = 500;
    int diffcount;
    
    public Sabotage() {
        this.largefont = new Font("Helvetica", 1, 48);
        this.medfont = new Font("Helvetica", 1, 20);
        this.smallfont = new Font("Helvetica", 1, 14);
        this.thetracker = null;
        this.steerablebullets = true;
        this.ingame = false;
        this.showtitle = true;
        this.count = 250;
        this.backgnd = new Color(0, 0, 64);
        this.turretbasecolor = new Color(224, 224, 224);
        this.turretcolor = new Color(0, 200, 200);
        this.guncolor = Color.white;
        this.gndcolor = new Color(64, 255, 0);
        this.bulletcolor = new Color(255, 255, 0);
        this.gunpoly_x = new double[] { -3.0, 3.0, 3.0, -3.0 };
        this.gunpoly_y = new double[] { 0.0, 0.0, -20.0, -20.0 };
        this.gunangle = 1.5707963267948966;
        this.turretbase_x = 180;
        this.turretbase_y = 300;
        this.turret_x = 190;
        this.turret_y = 285;
        this.gun_x = 200;
        this.gun_y = 293;
        this.gnd_y = 330;
        this.chopperanim = false;
        this.animcount = 3;
    }
    
    public String getAppletInfo() {
        return "Sabotage - (C)2003 by Brian Postma";
    }
    
    public void init() {
        this.d = this.size();
        final Graphics graphics = this.getGraphics();
        graphics.setFont(this.smallfont);
        this.fmsmall = graphics.getFontMetrics();
        graphics.setFont(this.largefont);
        this.fmlarge = graphics.getFontMetrics();
        graphics.setFont(this.medfont);
        this.fmmed = graphics.getFontMetrics();
        this.xcoord = new int[4];
        this.ycoord = new int[4];
        this.chopper_left_1 = this.getImage(this.getDocumentBase(), "chopper_left_1.gif");
        this.chopper_left_2 = this.getImage(this.getDocumentBase(), "chopper_left_2.gif");
        this.chopper_right_1 = this.getImage(this.getDocumentBase(), "chopper_right_1.gif");
        this.chopper_right_2 = this.getImage(this.getDocumentBase(), "chopper_right_2.gif");
        this.paratrooper1 = this.getImage(this.getDocumentBase(), "paratrooper1.gif");
        this.paratrooper2 = this.getImage(this.getDocumentBase(), "paratrooper2.gif");
        (this.thetracker = new MediaTracker(this)).addImage(this.chopper_left_1, 0);
        this.thetracker.addImage(this.chopper_left_2, 0);
        this.thetracker.addImage(this.chopper_right_1, 0);
        this.thetracker.addImage(this.chopper_right_2, 0);
        try {
            this.thetracker.waitForAll();
        }
        catch (InterruptedException ex) {
            return;
        }
        this.bullet_x = new double[10];
        this.bullet_y = new double[10];
        this.bullet_angle = new double[10];
        this.bullet_on = new boolean[10];
        this.chopper1_x = new int[6];
        this.chopper2_x = new int[6];
        this.chopper1_v = new boolean[6];
        this.chopper2_v = new boolean[6];
        this.para_x = new int[20];
        this.para_y = new int[20];
        this.para_v = new boolean[20];
        this.para_freefall = new int[20];
        this.turretbase_x = (this.d.width - 40) / 2;
        this.turretbase_y = this.d.height - 30 - 30;
        this.turret_x = (this.d.width - 20) / 2;
        this.turret_y = this.turretbase_y - 15;
        this.gun_x = this.d.width / 2;
        this.gun_y = this.turret_y + 8;
        this.gnd_y = this.d.height - 30;
        this.exp_x = new double[10];
        this.exp_y = new double[10];
        this.exp_vx = new double[10];
        this.exp_vy = new double[10];
        this.freefall_max = this.d.height - 30 - 30 - this.paratrooper2.getHeight(null);
        this.freefall_min = 2 * this.chopper_left_2.getHeight(null) + 14;
        this.GameInit();
    }
    
    public void GameInit() {
        for (int i = 0; i < 10; ++i) {
            this.bullet_on[i] = false;
        }
        for (int j = 0; j < 6; ++j) {
            this.chopper1_x[j] = this.d.width;
            this.chopper1_v[j] = false;
            this.chopper2_x[j] = -this.chopper_left_1.getWidth(this);
            this.chopper2_v[j] = false;
        }
        this.chopper_delay = 150;
        this.chopper_count = this.chopper_delay;
        this.num_paras = 6;
        this.para_delay = 200;
        this.para_count = this.para_delay;
        this.freefall = this.freefall_min;
        for (int k = 0; k < 20; ++k) {
            this.para_v[k] = false;
            this.para_freefall[k] = (int)(Math.random() * (this.freefall - this.freefall_min)) + this.freefall_min;
        }
        this.landed = 0;
        this.score = 0;
        this.explosion = false;
        this.diffcount = 500;
    }
    
    public boolean keyDown(final Event event, final int n) {
        switch (n) {
            case 1006: {
                this.dangle = 0.039269908169872414;
                break;
            }
            case 1007: {
                this.dangle = -0.039269908169872414;
                break;
            }
            case 49: {
                if (!this.ingame) {
                    this.GameInit();
                    this.ingame = true;
                    this.steerablebullets = true;
                    break;
                }
                break;
            }
            case 50: {
                if (!this.ingame) {
                    this.GameInit();
                    this.ingame = true;
                    this.steerablebullets = false;
                    break;
                }
                break;
            }
            case 27: {
                this.ingame = false;
                break;
            }
        }
        return true;
    }
    
    public boolean keyUp(final Event event, final int n) {
        if (n == 1006 || n == 1007) {
            this.dangle = 0.0;
        }
        if (n == 32 && this.ingame) {
            this.FireBullet();
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.goff == null && this.d.width > 0 && this.d.height > 0) {
            this.ii = this.createImage(this.d.width, this.d.height);
            this.goff = this.ii.getGraphics();
        }
        if (this.goff == null || this.ii == null) {
            return;
        }
        this.goff.setColor(this.backgnd);
        this.goff.fillRect(0, 0, this.d.width, this.d.height);
        this.DrawGun();
        this.DrawPlayField();
        this.DrawScore();
        this.DoChoppers();
        this.CheckNewChopper();
        this.DrawExplosion();
        if (this.ingame) {
            this.DoBullets();
            this.MoveParas();
            this.DrawParas();
            this.CheckParas();
            this.AdvanceDifficulty();
        }
        else {
            this.ShowIntroScreen();
        }
        graphics.drawImage(this.ii, 0, 0, this);
    }
    
    public void AdvanceDifficulty() {
        --this.diffcount;
        if (this.diffcount > 0) {
            return;
        }
        this.diffcount = 500;
        if (this.freefall < this.freefall_max) {
            ++this.freefall;
        }
        if (this.para_delay > 50) {
            this.para_delay -= 10;
        }
        else {
            this.para_delay = 50;
        }
        if (this.chopper_delay > 40) {
            this.chopper_delay -= 10;
        }
        else {
            this.chopper_delay = 40;
        }
        if (this.chopper_delay == 40 && this.num_paras < 20) {
            ++this.num_paras;
        }
    }
    
    public void DrawExplosion() {
        if (!this.explosion) {
            return;
        }
        this.exp_color -= 0.01;
        if (this.exp_color < 0.0) {
            this.explosion = false;
            return;
        }
        this.goff.setColor(new Color((int)(this.exp_color * 255.0), (int)(this.exp_color * 255.0), (int)(this.exp_color * 255.0)));
        for (int i = 0; i < 10; ++i) {
            final double[] exp_x = this.exp_x;
            final int n = i;
            exp_x[n] += this.exp_vx[i];
            final double[] exp_y = this.exp_y;
            final int n2 = i;
            exp_y[n2] += this.exp_vy[i];
            final double[] exp_vy = this.exp_vy;
            final int n3 = i;
            exp_vy[n3] += 0.3;
            this.goff.fillRect((int)this.exp_x[i], (int)this.exp_y[i], 2, 2);
        }
    }
    
    public void NewExplosion(final int n, final int n2) {
        this.explosion = true;
        for (int i = 0; i < 10; ++i) {
            this.exp_vx[i] = Math.random() * 2.0 * 7.0 - 7.0;
            this.exp_vy[i] = Math.random() * 2.0 * 4.0 - 4.0;
            this.exp_x[i] = n + 5.0 * this.exp_vx[i];
            this.exp_y[i] = n2 + 5.0 * this.exp_vy[i];
            this.exp_color = 1.0;
        }
    }
    
    public void CheckParas() {
        boolean b = true;
        if (this.para_count > 0) {
            --this.para_count;
            return;
        }
        boolean b2 = false;
        for (int i = 0; i < 6; ++i) {
            b2 |= (this.chopper1_v[i] | this.chopper2_v[i]);
        }
        if (!b2) {
            return;
        }
        int n;
        for (n = 0; n < this.num_paras && this.para_v[n]; ++n) {}
        if (n >= this.num_paras) {
            return;
        }
        this.para_count = this.para_delay + (int)(Math.random() * 20.0);
        int j = (int)(Math.random() * 6.0) + 1;
        int n2 = -1;
        while (j > 0) {
            --j;
            boolean b3 = false;
            while (!b3) {
                if (++n2 >= 6) {
                    n2 = 0;
                    b = !b;
                }
                if (b) {
                    b3 = this.chopper1_v[n2];
                }
                else {
                    b3 = this.chopper2_v[n2];
                }
            }
        }
        this.para_v[n] = true;
        if (b) {
            this.para_x[n] = this.chopper1_x[n2] + this.chopper_left_1.getWidth(this) / 2;
            this.para_y[n] = this.freefall_min;
            this.para_freefall[n2] = (int)(Math.random() * (this.freefall - this.freefall_min)) + this.freefall_min;
        }
        else {
            this.para_x[n] = this.chopper2_x[n2] + this.chopper_left_1.getWidth(this) / 2;
            this.para_y[n] = this.freefall_min;
            this.para_freefall[n2] = (int)(Math.random() * (this.freefall - this.freefall_min)) + this.freefall_min;
        }
        if (this.para_x[n] < 0) {
            this.para_x[n] = 0;
        }
        if (this.para_x[n] > this.d.width - this.paratrooper2.getWidth(null)) {
            this.para_x[n] = this.d.width - this.paratrooper2.getWidth(null);
        }
    }
    
    public void MoveParas() {
        for (int i = 0; i < 20; ++i) {
            if (this.para_v[i]) {
                if (this.para_y[i] < this.gnd_y - this.paratrooper2.getHeight(null)) {
                    if (this.para_y[i] < this.para_freefall[i]) {
                        final int[] para_y = this.para_y;
                        final int n = i;
                        para_y[n] += 4;
                    }
                    else {
                        final int[] para_y2 = this.para_y;
                        final int n2 = i;
                        ++para_y2[n2];
                    }
                }
                else {
                    this.para_y[i] = this.gnd_y - this.paratrooper2.getHeight(null);
                    if (this.para_x[i] < this.d.width / 2) {
                        final int[] para_x = this.para_x;
                        final int n3 = i;
                        ++para_x[n3];
                    }
                    else {
                        final int[] para_x2 = this.para_x;
                        final int n4 = i;
                        --para_x2[n4];
                    }
                    if (this.para_x[i] > (this.d.width - 20) / 2 - this.paratrooper2.getWidth(null) && this.para_x[i] < (this.d.width + 20) / 2) {
                        this.para_v[i] = false;
                        ++this.landed;
                        if (this.landed >= 4) {
                            this.ingame = false;
                        }
                    }
                }
            }
        }
    }
    
    public void DrawParas() {
        for (int i = 0; i < 20; ++i) {
            if (this.para_v[i]) {
                if (this.para_y[i] < this.gnd_y - this.paratrooper2.getHeight(null)) {
                    if (this.para_y[i] < this.para_freefall[i]) {
                        this.goff.drawImage(this.paratrooper2, this.para_x[i], this.para_y[i], null);
                    }
                    else {
                        this.goff.drawImage(this.paratrooper1, this.para_x[i], this.para_y[i], null);
                    }
                }
                else {
                    this.goff.drawImage(this.paratrooper2, this.para_x[i], this.para_y[i], null);
                }
            }
        }
    }
    
    public void FireBullet() {
        int n;
        for (n = 0; n < 10 && this.bullet_on[n]; ++n) {}
        if (n != 10) {
            this.bullet_x[n] = this.gun_x + 20.0 * Math.cos(this.gunangle);
            this.bullet_y[n] = this.gun_y - 20.0 * Math.sin(this.gunangle);
            this.bullet_angle[n] = this.gunangle;
            this.bullet_on[n] = true;
            if (this.score > 0) {
                --this.score;
            }
        }
    }
    
    public boolean NewChopper() {
        boolean b = false;
        if (Math.random() > 0.5) {
            b = true;
        }
        for (int i = 0; i < 6; ++i) {
            if (b) {
                if (!this.chopper1_v[i]) {
                    return this.chopper1_v[i] = true;
                }
            }
            else if (!this.chopper2_v[i]) {
                return this.chopper2_v[i] = true;
            }
        }
        return false;
    }
    
    public void CheckNewChopper() {
        if (this.chopper_count > 0) {
            --this.chopper_count;
        }
        else if (Math.random() < 0.1 && this.NewChopper()) {
            this.chopper_count = this.chopper_delay;
        }
    }
    
    public void DoChoppers() {
        --this.animcount;
        if (this.animcount <= 0) {
            this.chopperanim = !this.chopperanim;
            this.animcount = 3;
        }
        for (int i = 0; i < 6; ++i) {
            if (this.chopper1_v[i]) {
                final int[] chopper1_x = this.chopper1_x;
                final int n = i;
                chopper1_x[n] -= 3;
                if (this.chopper1_x[i] < -this.chopper_left_1.getWidth(this)) {
                    this.chopper1_v[i] = false;
                    this.chopper1_x[i] = this.d.width;
                }
                if (this.chopperanim) {
                    this.goff.drawImage(this.chopper_left_1, this.chopper1_x[i], 5, null);
                }
                else {
                    this.goff.drawImage(this.chopper_left_2, this.chopper1_x[i], 5, null);
                }
            }
            if (this.chopper2_v[i]) {
                final int[] chopper2_x = this.chopper2_x;
                final int n2 = i;
                chopper2_x[n2] += 3;
                if (this.chopper2_x[i] > this.d.width) {
                    this.chopper2_v[i] = false;
                    this.chopper2_x[i] = -this.chopper_left_1.getWidth(this);
                }
                if (this.chopperanim) {
                    this.goff.drawImage(this.chopper_right_1, this.chopper2_x[i], this.chopper_left_1.getHeight(null) + 10, null);
                }
                else {
                    this.goff.drawImage(this.chopper_right_2, this.chopper2_x[i], this.chopper_left_2.getHeight(null) + 10, null);
                }
            }
        }
    }
    
    public boolean CheckChopperHit(final int n, final int n2, final int n3, final int n4) {
        return n + 1 > n3 & n - 1 < n3 + this.chopper_left_2.getWidth(null) & n2 + 1 > n4 & n2 - 1 < n4 + this.chopper_left_2.getHeight(null);
    }
    
    public boolean CheckBullet(final int n, final int n2) {
        for (int i = 0; i < this.num_paras; ++i) {
            if (this.para_v[i] && n + 1 > this.para_x[i] && n - 1 < this.para_x[i] + this.paratrooper2.getWidth(null) && n2 + 1 > this.para_y[i] + 6 && n2 - 1 < this.para_y[i] + this.paratrooper2.getHeight(null)) {
                this.score += 10;
                this.para_v[i] = false;
                return true;
            }
        }
        for (int j = 0; j < 6; ++j) {
            if (this.chopper1_v[j] && this.CheckChopperHit(n, n2, this.chopper1_x[j], 5)) {
                this.NewExplosion(this.chopper1_x[j] + this.chopper_left_2.getWidth(null) / 2, 5 + this.chopper_left_2.getHeight(null) / 2);
                this.chopper1_v[j] = false;
                this.score += 50;
                this.chopper1_x[j] = this.d.width;
                return true;
            }
            if (this.chopper2_v[j] && this.CheckChopperHit(n, n2, this.chopper2_x[j], this.chopper_left_2.getHeight(null) + 10)) {
                this.NewExplosion(this.chopper2_x[j] + this.chopper_left_2.getWidth(null) / 2, 10 + this.chopper_left_2.getHeight(null) + this.chopper_left_2.getHeight(null) / 2);
                this.chopper2_v[j] = false;
                this.score += 50;
                this.chopper2_x[j] = -this.chopper_left_1.getWidth(this);
                return true;
            }
        }
        return false;
    }
    
    public void DoBullets() {
        this.goff.setColor(this.bulletcolor);
        for (int i = 0; i < 10; ++i) {
            if (this.bullet_on[i]) {
                this.goff.fillRect((int)this.bullet_x[i] - 1, (int)this.bullet_y[i] - 1, 3, 3);
                if (this.CheckBullet((int)this.bullet_x[i], (int)this.bullet_y[i] - 1)) {
                    this.bullet_on[i] = false;
                }
                if (this.steerablebullets) {
                    final double[] bullet_x = this.bullet_x;
                    final int n = i;
                    bullet_x[n] += 4.0 * Math.cos(this.gunangle);
                    final double[] bullet_y = this.bullet_y;
                    final int n2 = i;
                    bullet_y[n2] -= 4.0 * Math.sin(this.gunangle);
                }
                else {
                    final double[] bullet_x2 = this.bullet_x;
                    final int n3 = i;
                    bullet_x2[n3] += 4.0 * Math.cos(this.bullet_angle[i]);
                    final double[] bullet_y2 = this.bullet_y;
                    final int n4 = i;
                    bullet_y2[n4] -= 4.0 * Math.sin(this.bullet_angle[i]);
                }
                if (this.bullet_x[i] < 0.0 || this.bullet_x[i] > this.d.width || this.bullet_y[i] < 0.0) {
                    this.bullet_on[i] = false;
                }
            }
        }
    }
    
    public void DrawGun() {
        for (int i = 0; i < 4; ++i) {
            this.xcoord[i] = (int)(this.gun_x + (this.gunpoly_x[i] * Math.sin(this.gunangle) - this.gunpoly_y[i] * Math.cos(this.gunangle)));
            this.ycoord[i] = (int)(this.gun_y + (this.gunpoly_x[i] * Math.cos(this.gunangle) + this.gunpoly_y[i] * Math.sin(this.gunangle)));
        }
        this.goff.setColor(this.guncolor);
        this.goff.fillPolygon(this.xcoord, this.ycoord, 4);
        this.gunangle += this.dangle;
        if (this.gunangle < 0.07853981633974483) {
            this.gunangle = 0.07853981633974483;
        }
        if (this.gunangle > 3.0630528372500483) {
            this.gunangle = 3.0630528372500483;
        }
    }
    
    public void DrawPlayField() {
        this.goff.setColor(this.turretbasecolor);
        this.goff.fillRect(this.turretbase_x, this.turretbase_y, 40, 30);
        this.goff.setColor(this.turretcolor);
        this.goff.fillArc(this.turret_x, this.turret_y, 20, 30, 0, 180);
        this.goff.setColor(this.gndcolor);
        this.goff.fillRect(0, this.gnd_y, this.d.width, 8);
    }
    
    public void DrawScore() {
        this.goff.setFont(this.smallfont);
        this.goff.setColor(new Color(96, 128, 255));
        this.goff.drawString("Score: " + this.score, 16, this.d.height - 8);
        this.goff.drawString("Infiltrated: ", this.d.width - 140, this.d.height - 8);
        for (int i = 0; i < this.landed; ++i) {
            this.goff.drawImage(this.paratrooper2, this.d.width - 65 + 15 * i, this.d.height - 24, null);
        }
    }
    
    public void ShowIntroScreen() {
        final String s = "PARATROOPERS";
        this.goff.setFont(this.largefont);
        this.goff.setColor(Color.white);
        this.goff.drawString(s, (this.d.width - this.fmlarge.stringWidth(s)) / 2 + 2, this.d.height / 2 - 45 + 2);
        this.goff.setColor(new Color(96, 128, 255));
        this.goff.drawString(s, (this.d.width - this.fmlarge.stringWidth(s)) / 2, this.d.height / 2 - 45);
        this.goff.setFont(this.medfont);
        this.goff.setColor(new Color(255, 160, 64));
        if (this.showtitle) {
            final String s2 = "(c)2004 by Brian Postma";
            this.goff.drawString(s2, (this.d.width - this.fmmed.stringWidth(s2)) / 2, this.d.height / 2 - 5);
            final String s3 = "b.postma@hetnet.nl";
            this.goff.drawString(s3, (this.d.width - this.fmmed.stringWidth(s3)) / 2, this.d.height / 2 + 20);
        }
        else {
            final String s4 = "'1' for steerable bullets, '2' for non-steerable";
            this.goff.drawString(s4, (this.d.width - this.fmmed.stringWidth(s4)) / 2, this.d.height / 2 - 5);
            final String s5 = "Use cursor left/right and space to fire";
            this.goff.drawString(s5, (this.d.width - this.fmmed.stringWidth(s5)) / 2, this.d.height / 2 + 20);
        }
        this.goff.setColor(new Color(96, 128, 255));
        final String s6 = "http://www.brianpostma.com";
        this.goff.drawString(s6, (this.d.width - this.fmmed.stringWidth(s6)) / 2, this.d.height / 2 + 50);
        --this.count;
        if (this.count <= 0) {
            this.count = 250;
            this.showtitle = !this.showtitle;
        }
    }
    
    public void run() {
        Thread.currentThread().setPriority(10);
        final Graphics graphics = this.getGraphics();
        while (true) {
            final long currentTimeMillis = System.currentTimeMillis();
            try {
                this.paint(graphics);
                Thread.sleep(Math.max(0L, currentTimeMillis + 20L - System.currentTimeMillis()));
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void start() {
        if (this.thethread == null) {
            (this.thethread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.thethread != null) {
            this.thethread.stop();
            this.thethread = null;
        }
    }
}
