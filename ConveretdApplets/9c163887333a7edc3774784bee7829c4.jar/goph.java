import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Component;
import java.applet.AudioClip;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class goph extends Applet implements Runnable
{
    String appsig;
    String check;
    Font stats;
    Font gameOver;
    Font stuffLoading;
    Image hole;
    Image[] gopherPics;
    Image[] gopherHurt;
    Image currentImg;
    Image startStop;
    MediaTracker tracker;
    AudioClip ouch;
    AudioClip bang;
    AudioClip gup;
    AudioClip gdown;
    AudioClip laugh;
    int x;
    int y;
    int rx;
    int ry;
    int maxX;
    int maxY;
    int width;
    int height;
    int score;
    int level;
    int tries;
    int gopherPoints;
    int maxScorePerLevel;
    int maxLevel;
    int delay;
    int frameRate;
    boolean isHit;
    boolean keepGoing;
    boolean gameStart;
    boolean imagesLoaded;
    Thread runner;
    ganim GopherThread;
    Thread gt;
    
    public void init() {
        this.check = this.getParameter("check");
        if (this.check == null) {
            this.check = "BS";
        }
        if (this.check.equals(this.appsig)) {
            this.tracker = new MediaTracker(this);
            this.hole = this.getImage(this.getCodeBase(), "hole.gif");
            this.tracker.addImage(this.hole, 0);
            this.gopherPics[0] = this.getImage(this.getCodeBase(), "hole.gif");
            this.tracker.addImage(this.gopherPics[0], 1);
            this.gopherPics[1] = this.getImage(this.getCodeBase(), "gopher1.gif");
            this.tracker.addImage(this.gopherPics[1], 2);
            this.gopherPics[2] = this.getImage(this.getCodeBase(), "gopher2.gif");
            this.tracker.addImage(this.gopherPics[2], 3);
            this.gopherPics[3] = this.getImage(this.getCodeBase(), "gopher3.gif");
            this.tracker.addImage(this.gopherPics[3], 4);
            this.gopherHurt[0] = this.getImage(this.getCodeBase(), "hurt1.gif");
            this.tracker.addImage(this.gopherHurt[0], 5);
            this.gopherHurt[1] = this.getImage(this.getCodeBase(), "hurt2.gif");
            this.tracker.addImage(this.gopherHurt[1], 6);
            this.gopherHurt[2] = this.getImage(this.getCodeBase(), "hurt3.gif");
            this.tracker.addImage(this.gopherHurt[2], 7);
            this.gopherHurt[3] = this.getImage(this.getCodeBase(), "hole.gif");
            this.tracker.addImage(this.gopherHurt[3], 8);
            this.startStop = this.getImage(this.getCodeBase(), "ss.jpg");
            this.tracker.addImage(this.startStop, 9);
            this.ouch = this.getAudioClip(this.getCodeBase(), "ow.au");
            this.bang = this.getAudioClip(this.getCodeBase(), "bang.au");
            this.gup = this.getAudioClip(this.getCodeBase(), "gup.au");
            this.gdown = this.getAudioClip(this.getCodeBase(), "gdown.au");
            this.laugh = this.getAudioClip(this.getCodeBase(), "laugh.au");
        }
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.gt != null) {
            this.gt.stop();
            this.gt = null;
        }
        if (this.runner != null) {
            this.runner.stop();
            this.runner = null;
        }
    }
    
    public void run() {
        try {
            for (int i = 0; i < 9; ++i) {
                this.patience(false);
                this.tracker.waitForID(i);
                if ((this.tracker.statusID(i, true) & 0x4) != 0x0) {
                    this.patience(true);
                    this.patience(false);
                }
            }
            this.imagesLoaded = true;
        }
        catch (InterruptedException ex) {
            return;
        }
        if (this.imagesLoaded) {
            this.repaint();
        }
        if (!this.keepGoing) {
            this.gt = null;
        }
    }
    
    public void patience(final boolean badImg) {
        final Graphics g = this.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, 400, 430);
        g.setFont(this.stuffLoading);
        g.setColor(Color.black);
        if (badImg) {
            g.setColor(Color.red);
            g.drawString("ERROR IN IMAGE LOAD", 5, 400);
            g.drawString("PLEASE CONTACT THIS PAGE'S OWNER", 5, 422);
            this.pause(1500);
        }
        g.drawString("Loading images...", 5, 422);
    }
    
    public void paint(final Graphics g) {
        int paintX = 0;
        int paintY = 0;
        this.currentImg = this.hole;
        this.width = 100;
        this.height = 100;
        this.maxX = this.width * 4;
        this.maxY = this.height * 4;
        for (int j = 0; j < this.maxY; j += this.height) {
            for (int i = 0; i < this.maxX; i += this.width) {
                g.drawImage(this.currentImg, paintX, paintY, this);
                paintX += this.width;
            }
            paintY += this.height;
            paintX = 0;
        }
        this.gameStats(this.score, this.level, this.tries);
    }
    
    public void gameStats(final int s, final int l, final int t) {
        final Graphics g = this.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 400, 400, 430);
        g.setFont(this.stats);
        g.setColor(Color.black);
        g.drawString("Score " + s + "   Level " + l + "   Tries " + t, 10, 420);
        g.drawImage(this.startStop, 300, 400, this);
    }
    
    public void gopherUp(final int upX, final int upY) {
        final Graphics g = this.getGraphics();
        for (int i = 0; i < 3; ++i) {
            g.drawImage(this.gopherPics[i], upX, upY, this);
            this.pause(this.frameRate);
            if (this.isHit) {
                return;
            }
        }
        g.drawImage(this.gopherPics[3], upX, upY, this);
        if (this.isHit) {
            return;
        }
        this.pause(this.delay);
    }
    
    public void gopherDown(final int downX, final int downY) {
        if (this.isHit) {
            return;
        }
        final Graphics g = this.getGraphics();
        g.drawImage(this.gopherPics[2], downX, downY, this);
        this.pause(this.frameRate);
        if (this.isHit) {
            return;
        }
        g.drawImage(this.gopherPics[1], downX, downY, this);
        this.pause(this.frameRate);
        if (this.isHit) {
            return;
        }
        g.drawImage(this.gopherPics[0], downX, downY, this);
        this.isHit = false;
        this.badHit();
    }
    
    public void gopherHit(final int hurtX, final int hurtY) {
        final Graphics g = this.getGraphics();
        for (int i = 0; i < 4; ++i) {
            g.drawImage(this.gopherHurt[i], hurtX, hurtY, this);
            this.pause(150);
        }
    }
    
    public int randomX() {
        final int randomNumber = (int)(Math.random() * 40000.0 / 10000.0);
        return randomNumber * 100;
    }
    
    public int randomY() {
        final int randomNumber = (int)(Math.random() * 40000.0 / 10000.0);
        return randomNumber * 100;
    }
    
    public void pause(final int time) {
        try {
            Thread.sleep(time);
        }
        catch (InterruptedException ex) {}
    }
    
    public boolean mouseDown(final Event e, final int mouseX, final int mouseY) {
        if (mouseX >= 300 && mouseX <= 350 && mouseY >= 400 && mouseY <= 430 && this.imagesLoaded && !this.gameStart) {
            this.resetGame();
            this.repaint();
            this.gameStart = true;
            this.pause(1000);
            final Thread gt = new Thread(this.GopherThread);
            gt.start();
        }
        if (mouseX >= 350 && mouseX <= 400 && mouseY >= 400 && mouseY <= 430 && this.gameStart) {
            this.gameStart = false;
            this.keepGoing = false;
        }
        if (this.gameStart && !this.isHit && mouseX >= this.rx + 13 && mouseX <= this.rx + 87 && mouseY >= this.ry + 9 && mouseY <= this.ry + 73) {
            this.isHit = true;
        }
        return true;
    }
    
    public void goodHit() {
        this.gameStats(this.score += this.gopherPoints, this.level, this.tries);
        this.gopherHit(this.rx, this.ry);
        if (this.score == this.maxScorePerLevel) {
            this.maxScorePerLevel += 100;
            ++this.level;
            if (this.level > this.maxLevel) {
                this.youWin();
            }
            if (this.level > 4) {
                this.delay = 350;
            }
            else {
                this.delay -= 200;
            }
            this.frameRate -= 5;
            this.gameStats(this.score, this.level, this.tries);
        }
        this.isHit = false;
    }
    
    public void badHit() {
        --this.tries;
        if (this.tries == 0) {
            this.gameOver();
        }
        this.gameStats(this.score, this.level, this.tries);
        this.isHit = false;
    }
    
    public void gameOver() {
        final Graphics g = this.getGraphics();
        this.laugh.play();
        for (int i = 0; i < 4; ++i) {
            g.drawImage(this.gopherPics[i], 0, 0, this);
            g.drawImage(this.gopherPics[i], 300, 0, this);
            g.drawImage(this.gopherPics[i], 0, 300, this);
            g.drawImage(this.gopherPics[i], 300, 300, this);
            g.drawImage(this.gopherPics[i], 100, 100, this);
            g.drawImage(this.gopherPics[i], 100, 200, this);
            g.drawImage(this.gopherPics[i], 200, 100, this);
            g.drawImage(this.gopherPics[i], 200, 200, this);
            this.pause(50);
        }
        g.setFont(this.gameOver);
        g.setColor(Color.yellow);
        g.drawString("THE GOPHERS RUINED", 20, 150);
        g.drawString("YOUR YARD!", 100, 200);
        g.drawString("GAME OVER", 100, 250);
        this.gameStats(this.score, this.level, this.tries);
        this.gameStart = false;
        this.keepGoing = false;
    }
    
    public void youWin() {
        int i = 4;
        final Graphics g = this.getGraphics();
        g.setFont(this.gameOver);
        while (i > 0) {
            --i;
            g.drawImage(this.gopherHurt[i], 0, 0, this);
            g.drawImage(this.gopherHurt[i], 300, 0, this);
            g.drawImage(this.gopherHurt[i], 0, 300, this);
            g.drawImage(this.gopherHurt[i], 300, 300, this);
            g.drawImage(this.gopherHurt[i], 100, 100, this);
            g.drawImage(this.gopherHurt[i], 100, 200, this);
            g.drawImage(this.gopherHurt[i], 200, 100, this);
            g.drawImage(this.gopherHurt[i], 200, 200, this);
            this.pause(50);
        }
        g.setColor(Color.red);
        g.drawString("YOU WIN!", 130, 210);
        this.gameStats(this.score, this.level - 1, this.tries);
        this.gameStart = false;
        this.keepGoing = false;
    }
    
    public void resetGame() {
        this.x = 0;
        this.y = 0;
        this.rx = 0;
        this.ry = 0;
        this.score = 0;
        this.level = 1;
        this.maxScorePerLevel = 100;
        this.tries = 5;
        this.delay = 1300;
        this.frameRate = 50;
        this.isHit = false;
        this.keepGoing = true;
        this.gameStart = false;
    }
    
    public goph() {
        this.appsig = "Gopher Bash! by Pat Friedl Copyright 1998";
        this.stats = new Font("TimesRoman", 1, 16);
        this.gameOver = new Font("TimesRoman", 1, 30);
        this.stuffLoading = new Font("TimesRoman", 0, 16);
        this.gopherPics = new Image[4];
        this.gopherHurt = new Image[4];
        this.level = 1;
        this.tries = 5;
        this.gopherPoints = 10;
        this.maxScorePerLevel = 100;
        this.maxLevel = 6;
        this.delay = 1200;
        this.frameRate = 50;
        this.isHit = false;
        this.keepGoing = true;
        this.gameStart = false;
        this.imagesLoaded = false;
        this.GopherThread = new ganim();
    }
    
    class ganim extends Thread
    {
        public void run() {
            while (goph.this.keepGoing) {
                goph.this.rx = goph.this.randomX();
                goph.this.ry = goph.this.randomY();
                goph.this.gopherUp(goph.this.rx, goph.this.ry);
                goph.this.gopherDown(goph.this.rx, goph.this.ry);
                if (goph.this.isHit) {
                    goph.this.goodHit();
                }
                if (goph.this.level > 3) {
                    goph.this.pause(375);
                }
            }
        }
    }
}
