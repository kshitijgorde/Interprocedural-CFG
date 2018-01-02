import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class jatris extends Applet implements Runnable
{
    Dimension d;
    Font largefont;
    Font smallfont;
    Color textcolor1;
    Color textcolor2;
    FontMetrics fmsmall;
    FontMetrics fmlarge;
    Graphics goff;
    Image ii;
    Thread thethread;
    boolean ingame;
    final short xblocks = 10;
    final short yblocks = 20;
    final int blocksize = 16;
    final int width = 160;
    final int height = 320;
    short[][] screendata;
    final short maxcolors = 6;
    Color[] blocks;
    final int barwidth = 8;
    final Color barcolor;
    final Color background;
    int score;
    short emptyline;
    int objectx;
    int objecty;
    int objectdx;
    short objecttype;
    short objectcolor;
    int objectrotation;
    int objectrotationd;
    short objectptr;
    short checkptr;
    final short itemcount = 7;
    final short itemrotlen = 8;
    final short itemlen = 32;
    short count;
    final short maxcount = 5;
    short curcount;
    boolean fast;
    final short screendelay = 40;
    short screencount;
    boolean showtitle;
    int[] items;
    int[] checks;
    
    public jatris() {
        this.largefont = new Font("Helvetica", 1, 28);
        this.smallfont = new Font("Helvetica", 1, 10);
        this.textcolor1 = new Color(96, 128, 255);
        this.textcolor2 = new Color(255, 160, 64);
        this.ingame = false;
        this.barcolor = new Color(128, 255, 64);
        this.background = new Color(0, 0, 0);
        this.objectrotationd = 0;
        this.fast = false;
        this.screencount = 40;
        this.showtitle = true;
        this.items = new int[] { 0, 0, -1, 0, 0, -1, -1, -1, 0, 0, -1, 0, 0, 1, -1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, -1, 1, -1, 0, 0, 0, -1, 0, -2, 0, -3, 0, 0, -1, 0, -2, 0, -3, 0, 0, 0, 0, 1, 0, 2, 0, 3, 0, 0, 1, 0, 2, 0, 3, 0, 0, 0, 1, 0, 0, -1, -1, -1, 0, 0, 0, -1, -1, 0, -1, 1, 0, 0, -1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, -1, 0, 0, -1, 0, 0, -1, 1, -1, 0, 0, 0, 1, -1, 0, -1, -1, 0, 0, 1, 0, 0, 1, -1, 1, 0, 0, 0, -1, 1, 0, 1, 1, 0, 0, 1, 0, -1, 0, 0, -1, 0, 0, 0, 1, 0, -1, -1, 0, 0, 0, 0, 1, -1, 0, 1, 0, 0, 0, 1, 0, 0, -1, 0, 1, 0, 0, 0, -1, 1, -1, 0, 1, 0, 0, -1, 0, -1, -1, 1, 0, 0, 0, -1, 1, 0, 1, 0, -1, 0, 0, -1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, -1, -1, -1, 0, 0, 1, 0, -1, 0, -1, 1, 0, 0, 0, -1, 0, 1, 1, 1, 0, 0, -1, 0, 1, 0, 1, -1 };
        this.checks = new int[] { -1, 1, 0, 1, -1, 1, 0, 1, -1, 2, 0, 2, -1, 2, 0, 2, 0, 2, 1, 2, 0, 2, 1, 2, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, -1, 1, -2, 1, -3, 1, 0, 4, 0, 4, 0, 4, 0, 4, 0, 1, 1, 1, 2, 1, 3, 1, 0, 1, -1, 0, 1, 1, 0, 1, 0, 1, -1, 2, 0, 1, -1, 2, 0, 2, 1, 2, -1, 2, 0, 2, 0, 2, 1, 1, 0, 2, 1, 1, -1, 1, 0, 1, 1, 0, 1, 0, -1, 1, 0, 2, 0, 2, -1, 1, -1, 2, 0, 2, 1, 1, 1, 1, 0, 1, 1, 2, 0, 1, 1, 2, -1, 1, 0, 1, 1, 1, 1, 1, -1, 1, 0, 2, 0, 2, -1, 1, -1, 1, 0, 2, 1, 1, 1, 1, 0, 2, 1, 1, 0, 2, 1, 1, 0, 2, 1, 0, 1, 0, 0, 2, -1, 1, 0, 1, 1, 1, 1, 1, -1, 2, 0, 2, 0, 2, -1, 2, -1, 1, 0, 1, 1, 2, 1, 2, -1, 0, 0, 2, 0, 2, -1, 0, -1, 2, 0, 1, 1, 1, 1, 1, 0, 2, 1, 2, 1, 2, 0, 2, -1, 1, 0, 1, 1, 1, 1, 1 };
    }
    
    public void checkFull() {
        boolean b = false;
        for (short emptyline = 19; emptyline >= 0 && !b; --emptyline) {
            b = true;
            for (int i = 0; i < 10; i = (short)(i + 1)) {
                if (this.screendata[i][emptyline] == 0) {
                    b = false;
                }
            }
            if (b) {
                this.score += 10;
                if (this.score > 800) {
                    this.curcount = 1;
                }
                else if (this.score > 600) {
                    this.curcount = 2;
                }
                else if (this.score > 400) {
                    this.curcount = 3;
                }
                else if (this.score > 200) {
                    this.curcount = 4;
                }
                for (int j = 0; j < 10; j = (short)(j + 1)) {
                    this.screendata[j][emptyline] = 0;
                }
                this.emptyline = emptyline;
            }
        }
    }
    
    public void checkRotation() {
        int n = 1;
        final int objectrotation = (this.objectrotation + this.objectrotationd) % 4;
        int objectx = this.objectx + this.objectdx;
        for (int i = 0; i < 4; i = (short)(i + 1)) {
            final int n2 = objectx + this.items[this.objectptr + i * 2 + objectrotation * 8];
            if (n2 >= 10) {
                objectx -= n2 - 10 + 1;
            }
            else if (n2 < 0) {
                objectx -= n2;
            }
        }
        for (int n3 = 0; n3 < 4 && n != 0; n3 = (short)(n3 + 1)) {
            final int n4 = objectx + this.items[this.objectptr + n3 * 2 + objectrotation * 8];
            final int n5 = this.objecty + this.items[this.objectptr + n3 * 2 + objectrotation * 8 + 1];
            if (n5 >= 0) {
                n = ((n != 0 && this.screendata[n4][n5] == 0) ? 1 : 0);
            }
            if (n5 >= 20 || n4 < 0 || n4 >= 10) {
                n = 0;
            }
        }
        if (n != 0) {
            this.objectrotation = objectrotation;
            this.objectx = objectx;
        }
    }
    
    public void drawBars() {
        this.goff.setColor(this.barcolor);
        this.goff.fillRect(0, 0, 8, 320);
        this.goff.fillRect(168, 0, 8, 320);
        this.goff.fillRect(0, 320, 176, 8);
    }
    
    public void drawBlocks() {
        for (int i = 0; i < 10; i = (short)(i + 1)) {
            for (int j = 0; j < 20; j = (short)(j + 1)) {
                this.goff.setColor(this.blocks[this.screendata[i][j]]);
                this.goff.drawRect(i * 16 + 8, j * 16, 15, 15);
                this.goff.fillRect(i * 16 + 8 + 3, j * 16 + 3, 10, 10);
            }
        }
    }
    
    public boolean drawObject() {
        boolean b = false;
        for (int i = 0; i < 4; i = (short)(i + 1)) {
            final int n = this.objectx + this.items[this.objectptr + i * 2 + this.objectrotation * 8];
            final int n2 = this.objecty + this.items[this.objectptr + i * 2 + this.objectrotation * 8 + 1];
            final int n3 = this.objectx + this.checks[this.objectptr + i * 2 + this.objectrotation * 8];
            final int n4 = this.objecty + this.checks[this.objectptr + i * 2 + this.objectrotation * 8 + 1];
            if (n2 >= 0) {
                this.screendata[n][n2] = 0;
            }
            if (this.screendata[n3][n4] != 0) {
                b = true;
            }
        }
        if (!b) {
            --this.count;
            if (this.count <= 0 || this.fast) {
                ++this.objecty;
                this.count = this.curcount;
            }
            this.checkRotation();
            this.objectdx = 0;
            this.objectrotationd = 0;
        }
        for (int j = 0; j < 4; j = (short)(j + 1)) {
            final int n5 = this.objectx + this.items[this.objectptr + j * 2 + this.objectrotation * 8];
            final int n6 = this.objecty + this.items[this.objectptr + j * 2 + this.objectrotation * 8 + 1];
            if (n6 >= 0) {
                this.screendata[n5][n6] = this.objectcolor;
            }
            if (n6 >= 19) {
                b = true;
            }
        }
        if (b) {
            ++this.score;
            this.newObject();
        }
        return b;
    }
    
    public void gameInit() {
        for (int i = 0; i < 10; i = (short)(i + 1)) {
            for (int j = 0; j < 20; j = (short)(j + 1)) {
                this.screendata[i][j] = 0;
            }
        }
        this.score = 0;
        this.emptyline = -1;
        this.newObject();
        this.fast = false;
        this.curcount = 5;
    }
    
    public String getAppletInfo() {
        return "Jatris - by Brian Postma";
    }
    
    public void init() {
        this.screendata = new short[10][20];
        (this.blocks = new Color[7])[0] = this.background;
        this.blocks[1] = new Color(255, 0, 0);
        this.blocks[2] = new Color(0, 255, 0);
        this.blocks[3] = new Color(0, 0, 255);
        this.blocks[4] = new Color(255, 255, 0);
        this.blocks[5] = new Color(255, 0, 255);
        this.blocks[6] = new Color(0, 255, 255);
        this.resize(176, 350);
        this.d = this.size();
        this.setBackground(this.background);
        final Graphics graphics = this.getGraphics();
        graphics.setFont(this.smallfont);
        this.fmsmall = graphics.getFontMetrics();
        graphics.setFont(this.largefont);
        this.fmlarge = graphics.getFontMetrics();
        this.gameInit();
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (this.ingame) {
            if (n == 1006) {
                this.objectdx = -1;
            }
            else if (n == 1007) {
                this.objectdx = 1;
            }
            else if (n == 1004) {
                this.objectrotationd = 1;
            }
            else if (n == 1005) {
                this.fast = true;
            }
            else if (n == 27) {
                this.ingame = false;
            }
        }
        else if (n == 115 || n == 83) {
            this.ingame = true;
            this.gameInit();
        }
        return true;
    }
    
    public boolean keyUp(final Event event, final int n) {
        if (n == 1005) {
            this.fast = false;
        }
        return true;
    }
    
    public void newObject() {
        this.objectx = 4;
        this.objectdx = 0;
        this.objecty = 0;
        this.objecttype = (short)(Math.random() * 7.0);
        if (this.objecttype >= 7) {
            this.objecttype = 6;
        }
        this.objectptr = (short)(this.objecttype * 32);
        this.checkptr = (short)(this.objecttype * 32);
        this.objectcolor = (short)(Math.random() * 6.0 + 1.0);
        if (this.objectcolor > 6) {
            this.objectcolor = 6;
        }
        this.objectrotation = 0;
        this.count = 5;
        for (int i = 0; i < 4; i = (short)(i + 1)) {
            final int n = this.items[this.objectptr + i * 2 + 1];
            if (n >= 0 && this.screendata[this.objectx + this.items[this.objectptr + i * 2]][n] != 0) {
                this.ingame = false;
                this.showtitle = true;
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.goff == null && this.d.width > 0 && this.d.height > 0) {
            this.ii = this.createImage(this.d.width, this.d.height);
            this.goff = this.ii.getGraphics();
        }
        if (this.goff == null || this.ii == null) {
            return;
        }
        this.goff.setColor(this.background);
        this.goff.fillRect(0, 0, this.d.width, this.d.height);
        if (this.ingame) {
            this.playGame();
        }
        else {
            this.showIntro();
        }
        this.showScore();
        graphics.drawImage(this.ii, 0, 0, this);
    }
    
    public void playGame() {
        boolean drawObject = false;
        boolean b = false;
        if (this.emptyline < 0) {
            drawObject = this.drawObject();
        }
        else {
            this.scrollDown();
            b = true;
        }
        this.drawBars();
        this.drawBlocks();
        if (b || drawObject) {
            this.checkFull();
        }
    }
    
    public void run() {
        Thread.currentThread().setPriority(10);
        final Graphics graphics = this.getGraphics();
        while (true) {
            final long currentTimeMillis = System.currentTimeMillis();
            try {
                this.paint(graphics);
                Thread.sleep(Math.max(0L, currentTimeMillis + 60L - System.currentTimeMillis()));
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void scrollDown() {
        for (short emptyline = this.emptyline; emptyline > 0; --emptyline) {
            for (int i = 0; i < 10; i = (short)(i + 1)) {
                this.screendata[i][emptyline] = this.screendata[i][emptyline - 1];
            }
        }
        for (int j = 0; j < 10; j = (short)(j + 1)) {
            this.screendata[j][0] = 0;
        }
        this.emptyline = -1;
    }
    
    public void showIntro() {
        this.drawBars();
        this.drawBlocks();
        if (this.showtitle) {
            this.goff.setFont(this.largefont);
            final String s = "JATRIS";
            this.goff.setColor(this.textcolor1);
            this.goff.drawString(s, 8 + (160 - this.fmlarge.stringWidth(s)) / 2 - 2, 138);
            this.goff.setColor(Color.white);
            this.goff.drawString(s, 8 + (160 - this.fmlarge.stringWidth(s)) / 2, 140);
            this.goff.setFont(this.smallfont);
            final String s2 = "(c)2001 by Brian Postma";
            this.goff.setColor(this.textcolor2);
            this.goff.drawString(s2, 8 + (160 - this.fmsmall.stringWidth(s2)) / 2 - 1, 169);
            this.goff.setColor(Color.white);
            this.goff.drawString(s2, 8 + (160 - this.fmsmall.stringWidth(s2)) / 2, 170);
            final String s3 = "b.postma@hetnet.nl";
            this.goff.setColor(this.textcolor2);
            this.goff.drawString(s3, 8 + (160 - this.fmsmall.stringWidth(s3)) / 2 - 1, 189);
            this.goff.setColor(Color.white);
            this.goff.drawString(s3, 8 + (160 - this.fmsmall.stringWidth(s3)) / 2, 190);
        }
        else {
            this.goff.setFont(this.smallfont);
            final String s4 = "'S' to start game";
            this.goff.setColor(this.textcolor1);
            this.goff.drawString(s4, 8 + (160 - this.fmsmall.stringWidth(s4)) / 2 - 1, 129);
            this.goff.setColor(Color.white);
            this.goff.drawString(s4, 8 + (160 - this.fmsmall.stringWidth(s4)) / 2, 130);
            final String s5 = "Use cursor left+right to move";
            this.goff.setColor(this.textcolor2);
            this.goff.drawString(s5, 8 + (160 - this.fmsmall.stringWidth(s5)) / 2 - 1, 149);
            this.goff.setColor(Color.white);
            this.goff.drawString(s5, 8 + (160 - this.fmsmall.stringWidth(s5)) / 2, 150);
            final String s6 = "Use cursor up to rotate";
            this.goff.setColor(this.textcolor2);
            this.goff.drawString(s6, 8 + (160 - this.fmsmall.stringWidth(s6)) / 2 - 1, 169);
            this.goff.setColor(Color.white);
            this.goff.drawString(s6, 8 + (160 - this.fmsmall.stringWidth(s6)) / 2, 170);
            final String s7 = "Use cursor down to drop";
            this.goff.setColor(this.textcolor2);
            this.goff.drawString(s7, 8 + (160 - this.fmsmall.stringWidth(s7)) / 2 - 1, 189);
            this.goff.setColor(Color.white);
            this.goff.drawString(s7, 8 + (160 - this.fmsmall.stringWidth(s7)) / 2, 190);
        }
        --this.screencount;
        if (this.screencount <= 0) {
            this.screencount = 40;
            this.showtitle ^= true;
        }
    }
    
    public void showScore() {
        this.goff.setFont(this.smallfont);
        this.goff.setColor(Color.white);
        this.goff.drawString("Score: " + this.score, 40, 346);
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
