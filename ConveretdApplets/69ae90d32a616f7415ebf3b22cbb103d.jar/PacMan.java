import java.awt.Event;
import java.awt.Component;
import java.awt.image.ImageObserver;
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

public class PacMan extends Applet implements Runnable
{
    Dimension d;
    Font largefont;
    Font smallfont;
    FontMetrics fmsmall;
    FontMetrics fmlarge;
    Graphics goff;
    Image ii;
    Thread thethread;
    MediaTracker thetracker;
    Color dotcolor;
    int bigdotcolor;
    int dbigdotcolor;
    Color mazecolor;
    boolean ingame;
    boolean showtitle;
    boolean scared;
    boolean dying;
    final int screendelay = 120;
    final int blocksize = 24;
    final int nrofblocks = 15;
    final int scrsize = 360;
    final int animdelay = 8;
    final int pacanimdelay = 2;
    final int ghostanimcount = 2;
    final int pacmananimcount = 4;
    final int maxghosts = 12;
    final int pacmanspeed = 6;
    int animcount;
    int pacanimcount;
    int pacanimdir;
    int count;
    int ghostanimpos;
    int pacmananimpos;
    int nrofghosts;
    int pacsleft;
    int score;
    int deathcounter;
    int[] dx;
    int[] dy;
    int[] ghostx;
    int[] ghosty;
    int[] ghostdx;
    int[] ghostdy;
    int[] ghostspeed;
    Image ghost1;
    Image ghost2;
    Image ghostscared1;
    Image ghostscared2;
    Image pacman1;
    Image pacman2up;
    Image pacman2left;
    Image pacman2right;
    Image pacman2down;
    Image pacman3up;
    Image pacman3down;
    Image pacman3left;
    Image pacman3right;
    Image pacman4up;
    Image pacman4down;
    Image pacman4left;
    Image pacman4right;
    int pacmanx;
    int pacmany;
    int pacmandx;
    int pacmandy;
    int reqdx;
    int reqdy;
    int viewdx;
    int viewdy;
    int scaredcount;
    int scaredtime;
    final int maxscaredtime = 120;
    final int minscaredtime = 20;
    final short[] level1data;
    final int[] validspeeds;
    final int maxspeed = 6;
    int currentspeed;
    short[] screendata;
    
    public PacMan() {
        this.largefont = new Font("Helvetica", 1, 24);
        this.smallfont = new Font("Helvetica", 1, 14);
        this.thetracker = null;
        this.dotcolor = new Color(192, 192, 0);
        this.bigdotcolor = 192;
        this.dbigdotcolor = -2;
        this.ingame = false;
        this.showtitle = true;
        this.scared = false;
        this.dying = false;
        this.animcount = 8;
        this.pacanimcount = 2;
        this.pacanimdir = 1;
        this.count = 120;
        this.ghostanimpos = 0;
        this.pacmananimpos = 0;
        this.nrofghosts = 6;
        this.level1data = new short[] { 19, 26, 26, 22, 9, 12, 19, 26, 22, 9, 12, 19, 26, 26, 22, 37, 11, 14, 17, 26, 26, 20, 15, 17, 26, 26, 20, 11, 14, 37, 17, 26, 26, 20, 11, 6, 17, 26, 20, 3, 14, 17, 26, 26, 20, 21, 3, 6, 25, 22, 5, 21, 7, 21, 5, 19, 28, 3, 6, 21, 21, 9, 8, 14, 21, 13, 21, 5, 21, 13, 21, 11, 8, 12, 21, 25, 18, 26, 18, 24, 18, 28, 5, 25, 18, 24, 18, 26, 18, 28, 6, 21, 7, 21, 7, 21, 11, 8, 14, 21, 7, 21, 7, 21, 3, 4, 21, 5, 21, 5, 21, 11, 10, 14, 21, 5, 21, 5, 21, 1, 12, 21, 13, 21, 13, 21, 11, 10, 14, 21, 13, 21, 13, 21, 9, 19, 24, 26, 24, 26, 16, 26, 18, 26, 16, 26, 24, 26, 24, 22, 21, 3, 2, 2, 6, 21, 15, 21, 15, 21, 3, 2, 2, 6, 21, 21, 9, 8, 8, 4, 17, 26, 8, 26, 20, 1, 8, 8, 12, 21, 17, 26, 26, 22, 13, 21, 11, 2, 14, 21, 13, 19, 26, 26, 20, 37, 11, 14, 17, 26, 24, 22, 13, 19, 24, 26, 20, 11, 14, 37, 25, 26, 26, 28, 3, 6, 25, 26, 28, 3, 6, 25, 26, 26, 28 };
        this.validspeeds = new int[] { 1, 2, 3, 4, 6, 8 };
        this.currentspeed = 3;
    }
    
    public void CheckMaze() {
        int n;
        boolean b;
        for (n = 0, b = true; n < 225 && b; n = (short)(n + 1)) {
            if ((this.screendata[n] & 0x30) != 0x0) {
                b = false;
            }
        }
        if (b) {
            this.score += 50;
            this.DrawScore();
            try {
                Thread.sleep(3000L);
            }
            catch (InterruptedException ex) {}
            if (this.nrofghosts < 12) {
                ++this.nrofghosts;
            }
            if (this.currentspeed < 6) {
                ++this.currentspeed;
            }
            this.scaredtime -= 20;
            if (this.scaredtime < 20) {
                this.scaredtime = 20;
            }
            this.LevelInit();
        }
    }
    
    public void CheckScared() {
        --this.scaredcount;
        if (this.scaredcount <= 0) {
            this.scared = false;
        }
        if (this.scared && this.scaredcount >= 30) {
            this.mazecolor = new Color(192, 32, 255);
        }
        else {
            this.mazecolor = new Color(32, 192, 255);
        }
        if (this.scared) {
            this.screendata[111] = 11;
            this.screendata[113] = 14;
        }
        else {
            this.screendata[111] = 10;
            this.screendata[113] = 10;
        }
    }
    
    public void Death() {
        --this.deathcounter;
        switch ((this.deathcounter & 0xF) / 4) {
            case 0: {
                this.goff.drawImage(this.pacman4up, this.pacmanx + 1, this.pacmany + 1, this);
                break;
            }
            case 1: {
                this.goff.drawImage(this.pacman4right, this.pacmanx + 1, this.pacmany + 1, this);
                break;
            }
            case 2: {
                this.goff.drawImage(this.pacman4down, this.pacmanx + 1, this.pacmany + 1, this);
                break;
            }
            default: {
                this.goff.drawImage(this.pacman4left, this.pacmanx + 1, this.pacmany + 1, this);
                break;
            }
        }
        if (this.deathcounter == 0) {
            --this.pacsleft;
            if (this.pacsleft == 0) {
                this.ingame = false;
            }
            this.LevelContinue();
        }
    }
    
    public void DoAnim() {
        --this.animcount;
        if (this.animcount <= 0) {
            this.animcount = 8;
            ++this.ghostanimpos;
            if (this.ghostanimpos >= 2) {
                this.ghostanimpos = 0;
            }
        }
        --this.pacanimcount;
        if (this.pacanimcount <= 0) {
            this.pacanimcount = 2;
            this.pacmananimpos += this.pacanimdir;
            if (this.pacmananimpos == 3 || this.pacmananimpos == 0) {
                this.pacanimdir = -this.pacanimdir;
            }
        }
    }
    
    public void DrawGhost(final int n, final int n2) {
        if (this.ghostanimpos == 0 && !this.scared) {
            this.goff.drawImage(this.ghost1, n, n2, this);
        }
        else if (this.ghostanimpos == 1 && !this.scared) {
            this.goff.drawImage(this.ghost2, n, n2, this);
        }
        else if (this.ghostanimpos == 0 && this.scared) {
            this.goff.drawImage(this.ghostscared1, n, n2, this);
        }
        else if (this.ghostanimpos == 1 && this.scared) {
            this.goff.drawImage(this.ghostscared2, n, n2, this);
        }
    }
    
    public void DrawMaze() {
        int n = 0;
        this.bigdotcolor += this.dbigdotcolor;
        if (this.bigdotcolor <= 64 || this.bigdotcolor >= 192) {
            this.dbigdotcolor = -this.dbigdotcolor;
        }
        for (int i = 0; i < 360; i += 24) {
            for (int j = 0; j < 360; j += 24) {
                this.goff.setColor(this.mazecolor);
                if ((this.screendata[n] & 0x1) != 0x0) {
                    this.goff.drawLine(j, i, j, i + 24 - 1);
                }
                if ((this.screendata[n] & 0x2) != 0x0) {
                    this.goff.drawLine(j, i, j + 24 - 1, i);
                }
                if ((this.screendata[n] & 0x4) != 0x0) {
                    this.goff.drawLine(j + 24 - 1, i, j + 24 - 1, i + 24 - 1);
                }
                if ((this.screendata[n] & 0x8) != 0x0) {
                    this.goff.drawLine(j, i + 24 - 1, j + 24 - 1, i + 24 - 1);
                }
                if ((this.screendata[n] & 0x10) != 0x0) {
                    this.goff.setColor(this.dotcolor);
                    this.goff.fillRect(j + 11, i + 11, 2, 2);
                }
                if ((this.screendata[n] & 0x20) != 0x0) {
                    this.goff.setColor(new Color(224, 224 - this.bigdotcolor, this.bigdotcolor));
                    this.goff.fillRect(j + 8, i + 8, 8, 8);
                }
                n = (short)(n + 1);
            }
        }
    }
    
    public void DrawPacMan() {
        if (this.viewdx == -1) {
            this.DrawPacManLeft();
        }
        else if (this.viewdx == 1) {
            this.DrawPacManRight();
        }
        else if (this.viewdy == -1) {
            this.DrawPacManUp();
        }
        else {
            this.DrawPacManDown();
        }
    }
    
    public void DrawPacManDown() {
        switch (this.pacmananimpos) {
            case 1: {
                this.goff.drawImage(this.pacman2down, this.pacmanx + 1, this.pacmany + 1, this);
                break;
            }
            case 2: {
                this.goff.drawImage(this.pacman3down, this.pacmanx + 1, this.pacmany + 1, this);
                break;
            }
            case 3: {
                this.goff.drawImage(this.pacman4down, this.pacmanx + 1, this.pacmany + 1, this);
                break;
            }
            default: {
                this.goff.drawImage(this.pacman1, this.pacmanx + 1, this.pacmany + 1, this);
                break;
            }
        }
    }
    
    public void DrawPacManLeft() {
        switch (this.pacmananimpos) {
            case 1: {
                this.goff.drawImage(this.pacman2left, this.pacmanx + 1, this.pacmany + 1, this);
                break;
            }
            case 2: {
                this.goff.drawImage(this.pacman3left, this.pacmanx + 1, this.pacmany + 1, this);
                break;
            }
            case 3: {
                this.goff.drawImage(this.pacman4left, this.pacmanx + 1, this.pacmany + 1, this);
                break;
            }
            default: {
                this.goff.drawImage(this.pacman1, this.pacmanx + 1, this.pacmany + 1, this);
                break;
            }
        }
    }
    
    public void DrawPacManRight() {
        switch (this.pacmananimpos) {
            case 1: {
                this.goff.drawImage(this.pacman2right, this.pacmanx + 1, this.pacmany + 1, this);
                break;
            }
            case 2: {
                this.goff.drawImage(this.pacman3right, this.pacmanx + 1, this.pacmany + 1, this);
                break;
            }
            case 3: {
                this.goff.drawImage(this.pacman4right, this.pacmanx + 1, this.pacmany + 1, this);
                break;
            }
            default: {
                this.goff.drawImage(this.pacman1, this.pacmanx + 1, this.pacmany + 1, this);
                break;
            }
        }
    }
    
    public void DrawPacManUp() {
        switch (this.pacmananimpos) {
            case 1: {
                this.goff.drawImage(this.pacman2up, this.pacmanx + 1, this.pacmany + 1, this);
                break;
            }
            case 2: {
                this.goff.drawImage(this.pacman3up, this.pacmanx + 1, this.pacmany + 1, this);
                break;
            }
            case 3: {
                this.goff.drawImage(this.pacman4up, this.pacmanx + 1, this.pacmany + 1, this);
                break;
            }
            default: {
                this.goff.drawImage(this.pacman1, this.pacmanx + 1, this.pacmany + 1, this);
                break;
            }
        }
    }
    
    public void DrawScore() {
        this.goff.setFont(this.smallfont);
        this.goff.setColor(new Color(96, 128, 255));
        this.goff.drawString("Score: " + this.score, 276, 376);
        for (int i = 0; i < this.pacsleft; ++i) {
            this.goff.drawImage(this.pacman3left, i * 28 + 8, 361, this);
        }
    }
    
    public void GameInit() {
        this.pacsleft = 3;
        this.score = 0;
        this.scaredtime = 120;
        this.LevelInit();
        this.nrofghosts = 6;
        this.currentspeed = 3;
        this.scaredtime = 120;
    }
    
    public void GetImages() {
        this.thetracker = new MediaTracker(this);
        this.ghost1 = this.getImage(this.getDocumentBase(), "pacpix/Ghost1.gif");
        this.thetracker.addImage(this.ghost1, 0);
        this.ghost2 = this.getImage(this.getDocumentBase(), "pacpix/Ghost2.gif");
        this.thetracker.addImage(this.ghost2, 0);
        this.ghostscared1 = this.getImage(this.getDocumentBase(), "pacpix/GhostScared1.gif");
        this.thetracker.addImage(this.ghostscared1, 0);
        this.ghostscared2 = this.getImage(this.getDocumentBase(), "pacpix/GhostScared2.gif");
        this.thetracker.addImage(this.ghostscared2, 0);
        this.pacman1 = this.getImage(this.getDocumentBase(), "pacpix/PacMan1.gif");
        this.thetracker.addImage(this.pacman1, 0);
        this.pacman2up = this.getImage(this.getDocumentBase(), "pacpix/PacMan2up.gif");
        this.thetracker.addImage(this.pacman2up, 0);
        this.pacman3up = this.getImage(this.getDocumentBase(), "pacpix/PacMan3up.gif");
        this.thetracker.addImage(this.pacman3up, 0);
        this.pacman4up = this.getImage(this.getDocumentBase(), "pacpix/PacMan4up.gif");
        this.thetracker.addImage(this.pacman4up, 0);
        this.pacman2down = this.getImage(this.getDocumentBase(), "pacpix/PacMan2down.gif");
        this.thetracker.addImage(this.pacman2down, 0);
        this.pacman3down = this.getImage(this.getDocumentBase(), "pacpix/PacMan3down.gif");
        this.thetracker.addImage(this.pacman3down, 0);
        this.pacman4down = this.getImage(this.getDocumentBase(), "pacpix/PacMan4down.gif");
        this.thetracker.addImage(this.pacman4down, 0);
        this.pacman2left = this.getImage(this.getDocumentBase(), "pacpix/PacMan2left.gif");
        this.thetracker.addImage(this.pacman2left, 0);
        this.pacman3left = this.getImage(this.getDocumentBase(), "pacpix/PacMan3left.gif");
        this.thetracker.addImage(this.pacman3left, 0);
        this.pacman4left = this.getImage(this.getDocumentBase(), "pacpix/PacMan4left.gif");
        this.thetracker.addImage(this.pacman4left, 0);
        this.pacman2right = this.getImage(this.getDocumentBase(), "pacpix/PacMan2right.gif");
        this.thetracker.addImage(this.pacman2right, 0);
        this.pacman3right = this.getImage(this.getDocumentBase(), "pacpix/PacMan3right.gif");
        this.thetracker.addImage(this.pacman3right, 0);
        this.pacman4right = this.getImage(this.getDocumentBase(), "pacpix/PacMan4right.gif");
        this.thetracker.addImage(this.pacman4right, 0);
        try {
            this.thetracker.waitForAll();
        }
        catch (InterruptedException ex) {}
    }
    
    public void LevelContinue() {
        int n = 1;
        for (int i = 0; i < this.nrofghosts; i = (short)(i + 1)) {
            this.ghosty[i] = 168;
            this.ghostx[i] = 168;
            this.ghostdy[i] = 0;
            this.ghostdx[i] = n;
            n = -n;
            int currentspeed = (int)(Math.random() * (this.currentspeed + 1));
            if (currentspeed > this.currentspeed) {
                currentspeed = this.currentspeed;
            }
            this.ghostspeed[i] = this.validspeeds[currentspeed];
        }
        this.screendata[111] = 10;
        this.screendata[113] = 10;
        this.pacmanx = 168;
        this.pacmany = 264;
        this.pacmandx = 0;
        this.pacmandy = 0;
        this.reqdx = 0;
        this.reqdy = 0;
        this.viewdx = -1;
        this.viewdy = 0;
        this.dying = false;
        this.scared = false;
    }
    
    public void LevelInit() {
        for (int i = 0; i < 225; ++i) {
            this.screendata[i] = this.level1data[i];
        }
        this.LevelContinue();
    }
    
    public void MoveGhosts() {
        for (int i = 0; i < this.nrofghosts; i = (short)(i + 1)) {
            if (this.ghostx[i] % 24 == 0 && this.ghosty[i] % 24 == 0) {
                final int n = this.ghostx[i] / 24 + 15 * (this.ghosty[i] / 24);
                int n2 = 0;
                if ((this.screendata[n] & 0x1) == 0x0 && this.ghostdx[i] != 1) {
                    this.dx[n2] = -1;
                    this.dy[n2] = 0;
                    ++n2;
                }
                if ((this.screendata[n] & 0x2) == 0x0 && this.ghostdy[i] != 1) {
                    this.dx[n2] = 0;
                    this.dy[n2] = -1;
                    ++n2;
                }
                if ((this.screendata[n] & 0x4) == 0x0 && this.ghostdx[i] != -1) {
                    this.dx[n2] = 1;
                    this.dy[n2] = 0;
                    ++n2;
                }
                if ((this.screendata[n] & 0x8) == 0x0 && this.ghostdy[i] != -1) {
                    this.dx[n2] = 0;
                    this.dy[n2] = 1;
                    ++n2;
                }
                if (n2 == 0) {
                    if ((this.screendata[n] & 0xF) == 0xF) {
                        this.ghostdx[i] = 0;
                        this.ghostdy[i] = 0;
                    }
                    else {
                        this.ghostdx[i] = -this.ghostdx[i];
                        this.ghostdy[i] = -this.ghostdy[i];
                    }
                }
                else {
                    int n3 = (int)(Math.random() * n2);
                    if (n3 > 3) {
                        n3 = 3;
                    }
                    this.ghostdx[i] = this.dx[n3];
                    this.ghostdy[i] = this.dy[n3];
                }
            }
            this.ghostx[i] += this.ghostdx[i] * this.ghostspeed[i];
            this.ghosty[i] += this.ghostdy[i] * this.ghostspeed[i];
            this.DrawGhost(this.ghostx[i] + 1, this.ghosty[i] + 1);
            if (this.pacmanx > this.ghostx[i] - 12 && this.pacmanx < this.ghostx[i] + 12 && this.pacmany > this.ghosty[i] - 12 && this.pacmany < this.ghosty[i] + 12 && this.ingame) {
                if (this.scared) {
                    this.score += 10;
                    this.ghostx[i] = 168;
                    this.ghosty[i] = 168;
                }
                else {
                    this.dying = true;
                    this.deathcounter = 64;
                }
            }
        }
    }
    
    public void MovePacMan() {
        if (this.reqdx == -this.pacmandx && this.reqdy == -this.pacmandy) {
            this.pacmandx = this.reqdx;
            this.pacmandy = this.reqdy;
            this.viewdx = this.pacmandx;
            this.viewdy = this.pacmandy;
        }
        if (this.pacmanx % 24 == 0 && this.pacmany % 24 == 0) {
            final int n = this.pacmanx / 24 + 15 * (this.pacmany / 24);
            final short n2 = this.screendata[n];
            if ((n2 & 0x10) != 0x0) {
                this.screendata[n] = (short)(n2 & 0xF);
                ++this.score;
            }
            if ((n2 & 0x20) != 0x0) {
                this.scared = true;
                this.scaredcount = this.scaredtime;
                this.screendata[n] = (short)(n2 & 0xF);
                this.score += 5;
            }
            if ((this.reqdx != 0 || this.reqdy != 0) && (this.reqdx != -1 || this.reqdy != 0 || (n2 & 0x1) == 0x0) && (this.reqdx != 1 || this.reqdy != 0 || (n2 & 0x4) == 0x0) && (this.reqdx != 0 || this.reqdy != -1 || (n2 & 0x2) == 0x0) && (this.reqdx != 0 || this.reqdy != 1 || (n2 & 0x8) == 0x0)) {
                this.pacmandx = this.reqdx;
                this.pacmandy = this.reqdy;
                this.viewdx = this.pacmandx;
                this.viewdy = this.pacmandy;
            }
            if ((this.pacmandx == -1 && this.pacmandy == 0 && (n2 & 0x1) != 0x0) || (this.pacmandx == 1 && this.pacmandy == 0 && (n2 & 0x4) != 0x0) || (this.pacmandx == 0 && this.pacmandy == -1 && (n2 & 0x2) != 0x0) || (this.pacmandx == 0 && this.pacmandy == 1 && (n2 & 0x8) != 0x0)) {
                this.pacmandx = 0;
                this.pacmandy = 0;
            }
        }
        this.pacmanx += 6 * this.pacmandx;
        this.pacmany += 6 * this.pacmandy;
    }
    
    public void PlayDemo() {
        this.CheckScared();
        this.MoveGhosts();
        this.ShowIntroScreen();
    }
    
    public void PlayGame() {
        if (this.dying) {
            this.Death();
        }
        else {
            this.CheckScared();
            this.MovePacMan();
            this.DrawPacMan();
            this.MoveGhosts();
            this.CheckMaze();
        }
    }
    
    public void ShowIntroScreen() {
        this.goff.setFont(this.largefont);
        this.goff.setColor(new Color(0, 32, 48));
        this.goff.fillRect(16, 140, 328, 80);
        this.goff.setColor(Color.white);
        this.goff.drawRect(16, 140, 328, 80);
        if (this.showtitle) {
            final String s = "Java PacMan";
            this.scared = false;
            this.goff.setColor(Color.white);
            this.goff.drawString(s, (360 - this.fmlarge.stringWidth(s)) / 2 + 2, 162);
            this.goff.setColor(new Color(96, 128, 255));
            this.goff.drawString(s, (360 - this.fmlarge.stringWidth(s)) / 2, 160);
            final String s2 = "(c)2000 by Brian Postma";
            this.goff.setFont(this.smallfont);
            this.goff.setColor(new Color(255, 160, 64));
            this.goff.drawString(s2, (360 - this.fmsmall.stringWidth(s2)) / 2, 190);
            final String s3 = "b.postma@hetnet.nl";
            this.goff.setColor(new Color(255, 160, 64));
            this.goff.drawString(s3, (360 - this.fmsmall.stringWidth(s3)) / 2, 210);
        }
        else {
            this.goff.setFont(this.smallfont);
            this.goff.setColor(new Color(96, 128, 255));
            final String s4 = "'S' to start game";
            this.goff.drawString(s4, (360 - this.fmsmall.stringWidth(s4)) / 2, 170);
            this.goff.setColor(new Color(255, 160, 64));
            final String s5 = "Use cursor keys to move";
            this.goff.drawString(s5, (360 - this.fmsmall.stringWidth(s5)) / 2, 200);
            this.scared = true;
        }
        --this.count;
        if (this.count <= 0) {
            this.count = 120;
            this.showtitle ^= true;
        }
    }
    
    public String getAppletInfo() {
        return "PacMan - by Brian Postma";
    }
    
    public void init() {
        this.GetImages();
        this.screendata = new short[225];
        this.d = this.size();
        this.setBackground(Color.black);
        final Graphics graphics = this.getGraphics();
        graphics.setFont(this.smallfont);
        this.fmsmall = graphics.getFontMetrics();
        graphics.setFont(this.largefont);
        this.fmlarge = graphics.getFontMetrics();
        this.ghostx = new int[12];
        this.ghostdx = new int[12];
        this.ghosty = new int[12];
        this.ghostdy = new int[12];
        this.ghostspeed = new int[12];
        this.dx = new int[4];
        this.dy = new int[4];
        this.GameInit();
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (this.ingame) {
            if (n == 1006) {
                this.reqdx = -1;
                this.reqdy = 0;
            }
            else if (n == 1007) {
                this.reqdx = 1;
                this.reqdy = 0;
            }
            else if (n == 1004) {
                this.reqdx = 0;
                this.reqdy = -1;
            }
            else if (n == 1005) {
                this.reqdx = 0;
                this.reqdy = 1;
            }
            else if (n == 27) {
                this.ingame = false;
            }
        }
        else if (n == 115 || n == 83) {
            this.ingame = true;
            this.GameInit();
        }
        return true;
    }
    
    public boolean keyUp(final Event event, final int n) {
        if (n == 1006 || n == 1007 || n == 1004 || n == 1005) {
            this.reqdx = 0;
            this.reqdy = 0;
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
        this.goff.setColor(Color.black);
        this.goff.fillRect(0, 0, this.d.width, this.d.height);
        this.DrawMaze();
        this.DrawScore();
        this.DoAnim();
        if (this.ingame) {
            this.PlayGame();
        }
        else {
            this.PlayDemo();
        }
        graphics.drawImage(this.ii, 0, 0, this);
    }
    
    public void run() {
        Thread.currentThread().setPriority(10);
        final Graphics graphics = this.getGraphics();
        while (true) {
            final long currentTimeMillis = System.currentTimeMillis();
            try {
                this.paint(graphics);
                Thread.sleep(Math.max(0L, currentTimeMillis + 40L - System.currentTimeMillis()));
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
