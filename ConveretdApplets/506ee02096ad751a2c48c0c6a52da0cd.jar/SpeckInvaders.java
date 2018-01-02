import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Dimension;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SpeckInvaders extends Applet implements Runnable
{
    Dimension d;
    Font largefont;
    Font smallfont;
    FontMetrics fmsmall;
    FontMetrics fmlarge;
    Graphics goff;
    Image ii;
    Thread thethread;
    final int rowsofenemies = 6;
    final int enemiesperrow = 10;
    final int maxbombs = 10;
    final int enemyxdelta = 8;
    final int enemyydelta = 6;
    final int enemyxoffset = 80;
    final int groundheight = 5;
    final int rotsteps = 32;
    final int turretmarge = 24;
    final int bombspeed = 3;
    final Color turretcolor;
    final Color enemycolor1;
    final Color enemycolor2;
    final Color enemycolor3;
    final Color enemycolor4;
    final Color enemycolor5;
    final Color enemycolor6;
    final Color bulletcolor;
    final Color bombcolor;
    final Color groundcolor;
    final Color backgnd;
    final Color textcolor1;
    final Color textcolor2;
    final Color textcolor3;
    Color[] enemycolors;
    int[] bombx;
    int[] bomby;
    boolean[] drawbomb;
    boolean ingame;
    int bulletx;
    int bullety;
    int enemyx;
    int enemyy;
    int turretx;
    int enemydx;
    int enemyspeed;
    boolean[] drawenemy;
    int enemyradius;
    int score;
    int[] rotx;
    int[] roty;
    int animpos;
    int animdpos;
    int enemycount;
    int enemydelay;
    int speckcount;
    int bombcount;
    int curmaxbombs;
    int turretdx;
    int[] polyx;
    int[] polyy;
    int lives;
    int groundy;
    boolean dying;
    final double pi = 3.141592653589793;
    
    public SpeckInvaders() {
        this.largefont = new Font("Helvetica", 1, 24);
        this.smallfont = new Font("Helvetica", 1, 14);
        this.turretcolor = new Color(0, 200, 200);
        this.enemycolor1 = new Color(255, 255, 0);
        this.enemycolor2 = new Color(255, 200, 20);
        this.enemycolor3 = new Color(255, 150, 40);
        this.enemycolor4 = new Color(255, 100, 60);
        this.enemycolor5 = new Color(255, 50, 80);
        this.enemycolor6 = new Color(255, 0, 100);
        this.bulletcolor = new Color(255, 255, 255);
        this.bombcolor = new Color(255, 255, 255);
        this.groundcolor = new Color(0, 255, 0);
        this.backgnd = new Color(0, 0, 32);
        this.textcolor1 = new Color(255, 160, 64);
        this.textcolor2 = new Color(96, 128, 255);
        this.textcolor3 = new Color(255, 96, 128);
        this.enemycolors = new Color[] { this.enemycolor1, this.enemycolor2, this.enemycolor3, this.enemycolor4, this.enemycolor5, this.enemycolor6 };
        this.ingame = false;
        this.turretdx = 0;
        this.lives = 4;
        this.groundy = 210;
    }
    
    public String getAppletInfo() {
        return "SpeckInvaders - (C)2003 by Brian Postma";
    }
    
    public void init() {
        this.d = this.size();
        final Graphics graphics = this.getGraphics();
        graphics.setFont(this.smallfont);
        this.fmsmall = graphics.getFontMetrics();
        graphics.setFont(this.largefont);
        this.fmlarge = graphics.getFontMetrics();
        this.drawbomb = new boolean[10];
        this.bombx = new int[10];
        this.bomby = new int[10];
        this.drawenemy = new boolean[60];
        this.rotx = new int[32];
        this.roty = new int[32];
        this.polyx = new int[4];
        this.polyy = new int[4];
        this.groundy = this.d.height - 30;
        this.enemyradius = (this.d.width - 80) / 10 - 8;
        for (int i = 0; i < 32; ++i) {
            this.rotx[i] = (int)(this.enemyradius / 2 * Math.sin(6.283185307179586 * i / 32.0));
            this.roty[i] = (int)(this.enemyradius / 2 * Math.cos(6.283185307179586 * i / 32.0));
        }
        this.GameInit();
        this.ingame = false;
    }
    
    public void GameInit() {
        this.score = 0;
        this.LevelInit();
        this.ingame = true;
        this.curmaxbombs = 4;
        this.lives = 3;
    }
    
    public void LevelInit() {
        this.enemyx = this.enemyradius;
        this.enemyy = this.enemyradius;
        this.enemydx = 1;
        this.animpos = 0;
        this.animdpos = -1;
        this.enemycount = 0;
        this.enemydelay = 6;
        this.speckcount = 60;
        for (int i = 0; i < 60; ++i) {
            this.drawenemy[i] = true;
        }
        this.enemyspeed = 1;
        this.dying = false;
        this.LevelContinue();
    }
    
    public void LevelContinue() {
        this.bulletx = -1;
        this.bullety = -1;
        for (int i = 0; i < 10; ++i) {
            this.drawbomb[i] = false;
        }
        this.bombcount = 0;
        this.turretx = this.d.width / 2;
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (this.ingame) {
            switch (n) {
                case 1006: {
                    this.turretdx = -2;
                    break;
                }
                case 1007: {
                    this.turretdx = 2;
                    break;
                }
                case 27: {
                    this.ingame = false;
                    break;
                }
            }
        }
        else if (n == 115 || n == 83) {
            this.GameInit();
        }
        return true;
    }
    
    public boolean keyUp(final Event event, final int n) {
        if (n == 1006 || n == 1007) {
            this.turretdx = 0;
        }
        if (n == 32 && this.bullety < 0) {
            this.bullety = this.groundy - 7;
            this.bulletx = this.turretx;
        }
        return true;
    }
    
    public void DrawSpecks() {
        final int n = (this.animpos + 4) % 32;
        final int n2 = (this.animpos + 16) % 32;
        final int n3 = (this.animpos + 16 + 4) % 32;
        int enemyy = this.enemyy;
        final int n4 = (int)(0.4 * this.enemyradius);
        for (int i = 0; i < 6; ++i) {
            this.goff.setColor(this.enemycolors[i]);
            int enemyx = this.enemyx;
            for (int j = 0; j < 10; ++j) {
                if (this.drawenemy[i * 10 + j]) {
                    this.polyx[0] = enemyx + this.rotx[this.animpos];
                    this.polyx[1] = enemyx + this.rotx[n];
                    this.polyx[2] = enemyx + this.rotx[n3];
                    this.polyx[3] = enemyx + this.rotx[n2];
                    this.polyy[0] = enemyy + this.roty[this.animpos];
                    this.polyy[1] = enemyy + this.roty[n];
                    this.polyy[2] = enemyy + this.roty[n3];
                    this.polyy[3] = enemyy + this.roty[n2];
                    this.goff.drawPolygon(this.polyx, this.polyy, 4);
                    if (this.bulletx > enemyx - n4 && this.bulletx < enemyx + n4 && this.bullety > enemyy - n4 && this.bullety < enemyy + n4) {
                        this.score += (6 - i) * 10;
                        this.drawenemy[i * 10 + j] = false;
                        this.bullety = -1;
                    }
                }
                enemyx += this.enemyradius + 8;
            }
            enemyy += this.enemyradius + 6;
        }
        this.animpos += this.animdpos;
        if (this.animpos < 0) {
            this.animpos = 31;
        }
        if (this.animpos >= 32) {
            this.animpos = 0;
        }
    }
    
    public void MoveSpecks() {
        int n = 30000;
        int n2 = -1;
        int n3 = -1;
        int enemyy = this.enemyy;
        this.speckcount = 0;
        ++this.enemycount;
        if (this.enemycount >= this.enemydelay) {
            this.enemyx += this.enemydx * this.enemyspeed;
        }
        for (int i = 0; i < 6; ++i) {
            int enemyx = this.enemyx;
            for (int j = 0; j < 10; ++j) {
                if (this.drawenemy[i * 10 + j]) {
                    ++this.speckcount;
                    if (enemyx < n) {
                        n = enemyx;
                    }
                    if (enemyx > n2) {
                        n2 = enemyx;
                    }
                    if (enemyy > n3) {
                        n3 = enemyy;
                    }
                }
                enemyx += this.enemyradius + 8;
            }
            enemyy += this.enemyradius + 6;
        }
        if (this.enemycount >= this.enemydelay) {
            this.enemycount = 0;
            if (n <= this.enemyradius || n2 >= this.d.width - this.enemyradius) {
                this.enemyy += 6;
                this.enemydx = -this.enemydx;
                this.animdpos = -this.animdpos;
            }
        }
        if (n3 >= this.groundy - this.enemyradius) {
            this.ingame = false;
        }
        if (this.speckcount <= 32) {
            this.enemydelay = 5;
        }
        if (this.speckcount <= 16) {
            this.enemydelay = 4;
        }
        if (this.speckcount <= 8) {
            this.enemydelay = 3;
        }
        if (this.speckcount <= 4) {
            this.enemydelay = 2;
        }
        if (this.speckcount <= 2) {
            this.enemydelay = 1;
        }
        if (this.speckcount == 1) {
            this.enemyspeed = 4;
        }
        if (this.speckcount == 0) {
            ++this.curmaxbombs;
            if (this.curmaxbombs >= 10) {
                this.curmaxbombs = 10;
            }
            this.LevelInit();
        }
    }
    
    public void DoTurret() {
        int n = this.groundy - 1;
        if (!this.dying) {
            this.turretx += this.turretdx;
            if (this.turretx >= this.d.width - 24) {
                this.turretx = this.d.width - 24;
            }
            if (this.turretx <= 24) {
                this.turretx = 24;
            }
        }
        else {
            this.turretx += 6;
            if (this.turretx >= this.d.width) {
                this.dying = false;
                --this.lives;
                if (this.lives <= 0) {
                    this.ingame = false;
                }
                else {
                    this.LevelContinue();
                }
            }
        }
        this.goff.setColor(this.turretcolor);
        for (int i = 6; i >= 0; --i) {
            this.goff.drawLine(this.turretx - i, n, this.turretx + i, n);
            --n;
        }
    }
    
    public void DoBullet() {
        if (this.bullety >= 0) {
            this.bullety -= 3;
            this.goff.setColor(this.bulletcolor);
            this.goff.drawLine(this.bulletx, this.bullety - 1, this.bulletx, this.bullety + 1);
        }
    }
    
    public void DoBombs() {
        this.goff.setColor(this.bombcolor);
        for (int i = 0; i < this.curmaxbombs; ++i) {
            if (this.drawbomb[i]) {
                this.goff.fillRect(this.bombx[i], this.bomby[i], 2, 3);
                final int[] bomby = this.bomby;
                final int n = i;
                bomby[n] += 3;
                if (this.bombx[i] > this.turretx - 6 && this.bombx[i] < this.turretx + 5 && this.bomby[i] > this.groundy - 5) {
                    this.dying = true;
                }
                if (this.bomby[i] > this.groundy - 3) {
                    this.drawbomb[i] = false;
                    --this.bombcount;
                }
            }
        }
    }
    
    public void CheckBombs() {
        if (Math.random() > 0.1 || this.bombcount >= this.curmaxbombs) {
            return;
        }
        int n;
        for (n = 0; this.drawbomb[n] && n < this.curmaxbombs; ++n) {}
        if (n >= this.curmaxbombs) {
            return;
        }
        int i = (int)(this.speckcount * Math.random());
        if (i >= this.speckcount) {
            i = this.speckcount - 1;
        }
        int j = -1;
        while (i >= 0) {
            ++j;
            while (j < 60 && !this.drawenemy[j]) {
                ++j;
            }
            --i;
        }
        if (j >= 60) {
            return;
        }
        int n2 = j;
        while (j < 60) {
            if (this.drawenemy[j]) {
                n2 = j;
            }
            j += 10;
        }
        this.bombx[n] = this.enemyx + n2 % 10 * (this.enemyradius + 8);
        this.bomby[n] = this.enemyy + n2 / 10 * (this.enemyradius + 6);
        this.drawbomb[n] = true;
        ++this.bombcount;
    }
    
    public void ShowScore() {
        this.goff.setFont(this.smallfont);
        this.goff.setColor(new Color(96, 128, 255));
        this.goff.drawString("Score: " + this.score, 24, this.d.height - 8);
        this.goff.setColor(this.turretcolor);
        for (int i = 1; i < this.lives; ++i) {
            int n = this.d.height - 8;
            for (int j = 6; j >= 0; --j) {
                this.goff.drawLine(this.d.width / 2 + i * 20 - j, n, this.d.width / 2 + i * 20 + j, n);
                --n;
            }
        }
    }
    
    public void ShowIntroScreen() {
        this.goff.setFont(this.largefont);
        this.goff.setColor(this.textcolor1);
        final String s = "SPECK INVADERS";
        this.goff.setColor(Color.white);
        this.goff.drawString(s, (this.d.width - this.fmlarge.stringWidth(s)) / 2 + 2, this.d.height / 2 - 38);
        this.goff.setColor(this.textcolor1);
        this.goff.drawString(s, (this.d.width - this.fmlarge.stringWidth(s)) / 2, this.d.height / 2 - 40);
        this.goff.setFont(this.smallfont);
        this.goff.setColor(Color.white);
        final String s2 = "(c)2003 by Brian Postma";
        this.goff.drawString(s2, (this.d.width - this.fmsmall.stringWidth(s2)) / 2 + 1, this.d.height / 2 - 9);
        this.goff.setColor(this.textcolor3);
        this.goff.drawString(s2, (this.d.width - this.fmsmall.stringWidth(s2)) / 2, this.d.height / 2 - 10);
        this.goff.setColor(Color.white);
        final String s3 = "B.Postma@HetNet.nl - www.brianpostma.com";
        this.goff.drawString(s3, (this.d.width - this.fmsmall.stringWidth(s3)) / 2 + 1, this.d.height / 2 + 11);
        this.goff.setColor(this.textcolor3);
        this.goff.drawString(s3, (this.d.width - this.fmsmall.stringWidth(s3)) / 2, this.d.height / 2 + 10);
        this.goff.setColor(Color.white);
        final String s4 = "'S' to start game";
        this.goff.drawString(s4, (this.d.width - this.fmsmall.stringWidth(s4)) / 2 + 1, this.d.height / 2 + 31);
        this.goff.setColor(this.textcolor2);
        this.goff.drawString(s4, (this.d.width - this.fmsmall.stringWidth(s4)) / 2, this.d.height / 2 + 30);
        this.goff.setColor(Color.white);
        final String s5 = "Use cursor keys to move, space to shoot";
        this.goff.drawString(s5, (this.d.width - this.fmsmall.stringWidth(s5)) / 2 + 1, this.d.height / 2 + 51);
        this.goff.setColor(this.textcolor2);
        this.goff.drawString(s5, (this.d.width - this.fmsmall.stringWidth(s5)) / 2, this.d.height / 2 + 50);
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
        this.goff.setColor(this.groundcolor);
        this.goff.fillRect(0, this.groundy, this.d.width, 5);
        if (this.ingame) {
            if (!this.dying) {
                this.MoveSpecks();
                this.DoBullet();
                this.CheckBombs();
                this.DoBombs();
            }
            this.DoTurret();
        }
        this.DrawSpecks();
        if (!this.ingame) {
            this.ShowIntroScreen();
        }
        this.ShowScore();
        graphics.drawImage(this.ii, 0, 0, this);
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
