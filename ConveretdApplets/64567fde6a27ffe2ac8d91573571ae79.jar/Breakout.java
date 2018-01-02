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

public class Breakout extends Applet implements Runnable
{
    Dimension d;
    Font largefont;
    Font smallfont;
    FontMetrics fmsmall;
    FontMetrics fmlarge;
    Graphics goff;
    Image ii;
    Thread thethread;
    boolean ingame;
    int player1score;
    int ballx;
    int bally;
    int batpos;
    int batdpos;
    int balldx;
    int balldy;
    int dxval;
    int ballsleft;
    int count;
    boolean showtitle;
    boolean[] showbrick;
    int bricksperline;
    final int borderwidth = 5;
    final int batwidth = 20;
    final int ballsize = 5;
    final int batheight = 5;
    final int scoreheight = 20;
    final int screendelay = 300;
    final int brickwidth = 15;
    final int brickheight = 8;
    final int brickspace = 2;
    final int backcol = 1056832;
    final int numlines = 4;
    final int startline = 32;
    
    public Breakout() {
        this.largefont = new Font("Helvetica", 1, 24);
        this.smallfont = new Font("Helvetica", 1, 14);
        this.ingame = false;
        this.batdpos = 0;
        this.balldx = 0;
        this.balldy = 0;
        this.showtitle = true;
    }
    
    public void BatDummyMove() {
        if (this.ballx < this.batpos + 2) {
            this.batpos -= 3;
        }
        else if (this.ballx > this.batpos + 20 - 3) {
            this.batpos += 3;
        }
    }
    
    public void CheckBat() {
        this.batpos += this.batdpos;
        if (this.batpos < 5) {
            this.batpos = 5;
        }
        else if (this.batpos > this.d.width - 5 - 20) {
            this.batpos = this.d.width - 5 - 20;
        }
        if (this.bally >= this.d.height - 20 - 10 - 5 && this.bally < this.d.height - 20 - 10 && this.ballx + 5 >= this.batpos && this.ballx <= this.batpos + 20) {
            this.bally = this.d.height - 20 - 5 - 10;
            this.balldy = -this.dxval;
            this.balldx = this.CheckBatBounce(this.balldx, this.ballx - this.batpos);
        }
    }
    
    public int CheckBatBounce(final int n, final int n2) {
        int n3 = -5;
        int n4 = 0;
        final int n5 = 3;
        int n6;
        if (n > 0) {
            n6 = 1;
        }
        else {
            n6 = -1;
        }
        while (n3 < 20 && n2 > n3) {
            n3 += n5;
            ++n4;
        }
        switch (n4) {
            case 0:
            case 1: {
                return -4;
            }
            case 2: {
                return -3;
            }
            case 7: {
                return 3;
            }
            case 3:
            case 6: {
                return n6 * 2;
            }
            case 4:
            case 5: {
                return n6;
            }
            default: {
                return 4;
            }
        }
    }
    
    public void CheckBricks() {
        int balldx = this.balldx;
        if (balldx < 0) {
            balldx = -balldx;
        }
        final int balldy = this.balldy;
        if (this.bally < 27 || this.bally > 72) {
            return;
        }
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < this.bricksperline; ++j) {
                if (this.showbrick[i * this.bricksperline + j]) {
                    final int n = 32 + i * 10;
                    final int n2 = 5 + j * 17;
                    if (this.bally >= n - 5 && this.bally < n + 8 && this.ballx >= n2 - 5 && this.ballx < n2 + 15) {
                        this.showbrick[i * this.bricksperline + j] = false;
                        if (this.ingame) {
                            this.player1score += 4 - i;
                        }
                        if (this.ballx >= n2 - 5 && this.ballx <= n2 - 5 + 3) {
                            this.balldx = -balldx;
                        }
                        else if (this.ballx <= n2 + 15 - 1 && this.ballx >= n2 + 15 - 4) {
                            this.balldx = balldx;
                        }
                        this.balldy = -balldy;
                    }
                }
            }
        }
    }
    
    public void DrawBricks() {
        boolean b = true;
        final int n = 85;
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < this.bricksperline; ++j) {
                if (this.showbrick[i * this.bricksperline + j]) {
                    b = false;
                    this.goff.setColor(new Color(255, i * n, 255 - i * n));
                    this.goff.fillRect(5 + j * 17, 32 + i * 10, 15, 8);
                }
            }
        }
        if (b) {
            this.InitBricks();
            if (this.ingame) {
                this.player1score += 100;
            }
        }
    }
    
    public void DrawPlayField() {
        this.goff.setColor(Color.white);
        this.goff.fillRect(0, 0, this.d.width, 5);
        this.goff.fillRect(0, 0, 5, this.d.height);
        this.goff.fillRect(this.d.width - 5, 0, 5, this.d.height);
        this.goff.fillRect(this.batpos, this.d.height - 10 - 20, 20, 5);
        this.goff.fillRect(this.ballx, this.bally, 5, 5);
    }
    
    public void GameInit() {
        this.batpos = (this.d.width - 20) / 2;
        this.ballx = (this.d.width - 5) / 2;
        this.bally = this.d.height - 5 - 20 - 10;
        this.player1score = 0;
        this.ballsleft = 3;
        this.dxval = 2;
        if (Math.random() < 0.5) {
            this.balldx = this.dxval;
        }
        else {
            this.balldx = -this.dxval;
        }
        this.balldy = -this.dxval;
        this.count = 300;
        this.batdpos = 0;
        this.InitBricks();
    }
    
    public void InitBricks() {
        for (int i = 0; i < 4 * this.bricksperline; ++i) {
            this.showbrick[i] = true;
        }
    }
    
    public void MoveBall() {
        this.ballx += this.balldx;
        this.bally += this.balldy;
        if (this.bally <= 5) {
            this.balldy = -this.balldy;
            this.bally = 5;
        }
        if (this.bally >= this.d.height - 5 - 20) {
            if (this.ingame) {
                --this.ballsleft;
                if (this.ballsleft <= 0) {
                    this.ingame = false;
                }
            }
            this.ballx = this.batpos + 7;
            this.bally = 72;
            this.balldy = this.dxval;
            this.balldx = 0;
        }
        if (this.ballx >= this.d.width - 5 - 5) {
            this.balldx = -this.balldx;
            this.ballx = this.d.width - 5 - 5;
        }
        if (this.ballx <= 5) {
            this.balldx = -this.balldx;
            this.ballx = 5;
        }
    }
    
    public void PlayGame() {
        this.MoveBall();
        this.CheckBat();
        this.CheckBricks();
        this.DrawPlayField();
        this.DrawBricks();
        this.ShowScore();
    }
    
    public void ShowIntroScreen() {
        this.MoveBall();
        this.CheckBat();
        this.CheckBricks();
        this.BatDummyMove();
        this.DrawPlayField();
        this.DrawBricks();
        this.ShowScore();
        this.goff.setFont(this.largefont);
        this.goff.setColor(new Color(96, 128, 255));
        if (this.showtitle) {
            final String s = "Java Breakout";
            this.goff.drawString(s, (this.d.width - this.fmlarge.stringWidth(s)) / 2, (this.d.height - 20 - 5) / 2 - 20);
            final String s2 = "(c)2000 by Brian Postma";
            this.goff.setFont(this.smallfont);
            this.goff.setColor(new Color(255, 160, 64));
            this.goff.drawString(s2, (this.d.width - this.fmsmall.stringWidth(s2)) / 2, (this.d.height - 20 - 5) / 2 + 10);
            final String s3 = "b.postma@hetnet.nl";
            this.goff.drawString(s3, (this.d.width - this.fmsmall.stringWidth(s3)) / 2, (this.d.height - 20 - 5) / 2 + 30);
        }
        else {
            this.goff.setFont(this.smallfont);
            this.goff.setColor(new Color(96, 128, 255));
            final String s4 = "'S' to start game";
            this.goff.drawString(s4, (this.d.width - this.fmsmall.stringWidth(s4)) / 2, (this.d.height - 20 - 5) / 2 - 10);
            this.goff.setColor(new Color(255, 160, 64));
            final String s5 = "Use cursor left and right to move";
            this.goff.drawString(s5, (this.d.width - this.fmsmall.stringWidth(s5)) / 2, (this.d.height - 20 - 5) / 2 + 20);
        }
        --this.count;
        if (this.count <= 0) {
            this.count = 300;
            this.showtitle ^= true;
        }
    }
    
    public void ShowScore() {
        this.goff.setFont(this.smallfont);
        this.goff.setColor(Color.white);
        this.goff.drawString("Score: " + this.player1score, 40, this.d.height - 5);
        final String string = "Balls left: " + this.ballsleft;
        this.goff.drawString(string, this.d.width - 40 - this.fmsmall.stringWidth(string), this.d.height - 5);
    }
    
    public String getAppletInfo() {
        return "Breakout - by Brian Postma";
    }
    
    public void init() {
        this.d = this.size();
        this.setBackground(new Color(1056832));
        this.bricksperline = (this.d.width - 10) / 17;
        this.d.width = this.bricksperline * 17 + 10;
        final Graphics graphics = this.getGraphics();
        graphics.setFont(this.smallfont);
        this.fmsmall = graphics.getFontMetrics();
        graphics.setFont(this.largefont);
        this.fmlarge = graphics.getFontMetrics();
        this.showbrick = new boolean[this.bricksperline * 4];
        this.GameInit();
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (this.ingame) {
            if (n == 1006) {
                this.batdpos = -3;
            }
            if (n == 1007) {
                this.batdpos = 3;
            }
            if (n == 27) {
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
        System.out.println("Key: " + n);
        if (n == 1006 || n == 1007) {
            this.batdpos = 0;
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
        this.goff.setColor(new Color(1056832));
        this.goff.fillRect(0, 0, this.d.width, this.d.height);
        if (this.ingame) {
            this.PlayGame();
        }
        else {
            this.ShowIntroScreen();
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
