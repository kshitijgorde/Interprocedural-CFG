import java.util.StringTokenizer;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.net.URLConnection;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class JungleCanvas extends Canvas implements Runnable
{
    Image backdrop1;
    Image backdrop2;
    Image osImage;
    Graphics osGraphics;
    JungleRunner parent;
    Font noticeFont;
    boolean ready;
    int framenum;
    int personX;
    int personY;
    Image[][] guy;
    Image[] logs;
    Image[] treasures;
    boolean up;
    boolean down;
    boolean left;
    boolean right;
    boolean jump;
    int guyframe;
    int guydir;
    int jumpdir;
    int jumpup;
    int ladderAt;
    Color brown;
    int puddle;
    int puddlepause;
    int puddlecolor;
    int puddlesize;
    int vine;
    int vinepause;
    int[] vinex;
    int[] viney;
    int swinging;
    int floor;
    int drowning;
    int climbing;
    Image[][] snake;
    int snakeAt;
    int snakepos;
    int snakeZ;
    int frozen;
    int[] loglocs;
    int[] logframes;
    int[] pits;
    int logsmoving;
    Image[] gators;
    int gatorpause;
    int logct;
    Image[] fires;
    int fire;
    int fireY;
    int fireframe;
    Image[][] scorpions;
    int scorpion;
    int scorpframe;
    Image wall;
    int wallpt;
    int lives;
    Font dataFont;
    int score;
    Color myBlue;
    int treasureX;
    int treasureY;
    int treasureType;
    Image title;
    boolean havetitle;
    boolean gamerunning;
    int numFrames;
    int[][] board;
    int[][] origboard;
    boolean dieoff;
    
    public JungleCanvas(final JungleRunner parent) {
        this.parent = parent;
        this.loglocs = new int[4];
        this.logframes = new int[4];
        this.pits = new int[4];
        this.brown = new Color(128, 64, 64);
        this.noticeFont = new Font("Serif", 1, 48);
        this.dataFont = new Font("Serif", 1, 20);
        this.myBlue = new Color(0, 0, 196);
    }
    
    public void run() {
        this.requestFocus();
        this.osImage = this.createImage(600, 400);
        this.osGraphics = this.osImage.getGraphics();
        this.title = this.parent.getImage(this.parent.getCodeBase(), "logo.jpg");
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(this.title, 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (Exception ex) {}
        this.havetitle = true;
        this.repaint();
        this.backdrop1 = this.parent.getImage(this.parent.getCodeBase(), "base1.jpg");
        this.backdrop2 = this.parent.getImage(this.parent.getCodeBase(), "base2.jpg");
        final Image image = this.parent.getImage(this.parent.getCodeBase(), "guy.gif");
        final Image image2 = this.parent.getImage(this.parent.getCodeBase(), "logs.gif");
        final Image image3 = this.parent.getImage(this.parent.getCodeBase(), "snake.gif");
        final Image image4 = this.parent.getImage(this.parent.getCodeBase(), "gators.gif");
        final Image image5 = this.parent.getImage(this.parent.getCodeBase(), "fire.gif");
        final Image image6 = this.parent.getImage(this.parent.getCodeBase(), "scorpions.gif");
        final Image image7 = this.parent.getImage(this.parent.getCodeBase(), "treasures.gif");
        this.wall = this.parent.getImage(this.parent.getCodeBase(), "wall.gif");
        final MediaTracker mediaTracker2 = new MediaTracker(this);
        mediaTracker2.addImage(this.backdrop1, 0);
        mediaTracker2.addImage(this.backdrop2, 0);
        mediaTracker2.addImage(image, 0);
        mediaTracker2.addImage(image3, 0);
        mediaTracker2.addImage(image2, 0);
        mediaTracker2.addImage(image4, 0);
        mediaTracker2.addImage(image5, 0);
        mediaTracker2.addImage(image6, 0);
        mediaTracker2.addImage(image7, 0);
        mediaTracker2.addImage(this.wall, 0);
        this.vinex = new int[20];
        this.viney = new int[20];
        for (int i = 0; i < 20; ++i) {
            final int n = i - 10;
            this.vinex[i] = 300 + (int)(100.0 * Math.sin(n / 6.0));
            this.viney[i] = 120 + (int)(40.0 * Math.cos(n / 6.0));
        }
        try {
            mediaTracker2.waitForAll();
        }
        catch (Exception ex2) {}
        this.guy = new Image[15][2];
        final croppedImage croppedImage = new croppedImage(image);
        for (int j = 0; j < 15; ++j) {
            this.guy[j][0] = this.createImage(croppedImage.getCrop(j * 75, 0, 75, 75));
        }
        this.treasures = new Image[5];
        final croppedImage croppedImage2 = new croppedImage(image7);
        for (int k = 0; k < 5; ++k) {
            this.treasures[k] = this.createImage(croppedImage2.getCrop(k * 50, 0, 50, 50));
        }
        final croppedImage croppedImage3 = new croppedImage(image);
        croppedImage3.invertImage();
        for (int l = 0; l < 15; ++l) {
            this.guy[14 - l][1] = this.createImage(croppedImage3.getCrop(l * 75, 0, 75, 75));
        }
        this.fires = new Image[4];
        final croppedImage croppedImage4 = new croppedImage(image5);
        for (int n2 = 0; n2 < 4; ++n2) {
            this.fires[n2] = this.createImage(croppedImage4.getCrop(n2 * 50, 0, 50, 50));
        }
        this.gators = new Image[2];
        final croppedImage croppedImage5 = new croppedImage(image4);
        this.gators[0] = this.createImage(croppedImage5.getCrop(0, 0, 30, 20));
        this.gators[1] = this.createImage(croppedImage5.getCrop(30, 0, 30, 20));
        this.scorpions = new Image[2][2];
        final croppedImage croppedImage6 = new croppedImage(image6);
        this.scorpions[0][0] = this.createImage(croppedImage6.getCrop(0, 0, 50, 50));
        this.scorpions[1][0] = this.createImage(croppedImage6.getCrop(50, 0, 50, 50));
        final croppedImage croppedImage7 = new croppedImage(image6);
        croppedImage7.invertImage();
        this.scorpions[0][1] = this.createImage(croppedImage7.getCrop(50, 0, 50, 50));
        this.scorpions[1][1] = this.createImage(croppedImage7.getCrop(0, 0, 50, 50));
        this.snake = new Image[4][2];
        final croppedImage croppedImage8 = new croppedImage(image3);
        for (int n3 = 0; n3 < 4; ++n3) {
            this.snake[n3][0] = this.createImage(croppedImage8.getCrop(n3 * 50, 0, 50, 50));
        }
        final croppedImage croppedImage9 = new croppedImage(image3);
        croppedImage9.invertImage();
        for (int n4 = 0; n4 < 4; ++n4) {
            this.snake[3 - n4][1] = this.createImage(croppedImage9.getCrop(n4 * 50, 0, 50, 50));
        }
        final croppedImage croppedImage10 = new croppedImage(image2);
        this.logs = new Image[4];
        for (int n5 = 0; n5 < 4; ++n5) {
            this.logs[n5] = this.createImage(croppedImage10.getCrop(n5 * 27, 0, 27, 58));
        }
        this.loadLayout();
        this.ready = true;
        this.repaint();
        do {
            if (this.gamerunning) {
                this.updateGame();
                this.drawFrame();
                this.repaint();
                try {
                    Thread.sleep(80L);
                }
                catch (Exception ex3) {}
            }
        } while (!this.dieoff);
    }
    
    public void updateGame() {
        if (this.puddle == 3) {
            ++this.gatorpause;
            if (this.gatorpause == 30) {
                this.gatorpause = 0;
            }
        }
        if (this.drowning > 0) {
            return;
        }
        if (this.swinging == 1) {
            if (!this.down) {
                return;
            }
            this.swinging = 2;
            this.jumpup = 8;
            this.guyframe = 1;
            if (this.vinepause < 20) {
                this.guydir = 0;
                this.jumpdir = 1;
            }
            else {
                this.guydir = 1;
                this.jumpdir = -1;
            }
        }
        if (this.jump) {
            if (this.swinging != 1) {
                this.personY -= this.jumpup;
                this.jumpup -= 4;
                boolean b = false;
                if (this.personY < 160 || this.wallpt == -1 || this.personX > this.wallpt + 40 || this.personX < this.wallpt - 40) {
                    b = true;
                }
                boolean b2 = false;
                if (this.personY < 160 || this.wallpt == -1 || this.personX < this.wallpt - 50 || this.personX > this.wallpt) {
                    b2 = true;
                }
                if ((this.jumpdir == -1 && b) || (this.jumpdir == 1 && b2)) {
                    this.personX += this.jumpdir * 10;
                }
                if (this.jumpup < 0 && this.personY > this.floor) {
                    this.jump = false;
                    this.swinging = 0;
                    this.personY = this.floor;
                    this.guyframe = 0;
                }
                if (this.vine == 1 && this.swinging == 0) {
                    int vinepause = this.vinepause;
                    if (vinepause > 19) {
                        vinepause = 38 - vinepause;
                    }
                    final int n = this.vinex[vinepause];
                    final int n2 = this.viney[vinepause];
                    final int n3 = this.personX + 15;
                    final int personY = this.personY;
                    final int n4 = this.personX + 60;
                    final int n5 = this.personY + 40;
                    if (n > n3 && n < n4 && n2 > personY && n2 < n5) {
                        this.swinging = 1;
                    }
                }
            }
        }
        else {
            if (this.frozen > 0) {
                return;
            }
            boolean b3 = false;
            if (this.personY < 160 || this.wallpt == -1 || this.personX > this.wallpt + 40 || this.personX < this.wallpt - 40) {
                b3 = true;
            }
            boolean b4 = false;
            if (this.personY < 160 || this.wallpt == -1 || this.personX < this.wallpt - 50 || this.personX > this.wallpt) {
                b4 = true;
            }
            if (this.guyframe == 0 || this.guyframe > 4) {
                if (this.left && b3) {
                    this.personX -= 10;
                    ++this.guyframe;
                }
                if (this.right && b4) {
                    this.personX += 10;
                    ++this.guyframe;
                }
                if (this.guyframe == 1) {
                    this.guyframe = 5;
                }
                if (this.guyframe == 15) {
                    this.guyframe = 5;
                }
            }
            if (this.guyframe == 2) {
                if (this.left && b3) {
                    this.personX -= 5;
                    this.guyframe = 0;
                }
                if (this.right && b4) {
                    this.personX += 5;
                    this.guyframe = 0;
                }
            }
        }
        if (this.personX < -20) {
            if (this.framenum > 0) {
                --this.framenum;
                this.personX = 550;
                this.getFrame();
            }
            else {
                this.framenum = this.numFrames - 1;
                this.personX = 550;
                this.getFrame();
            }
        }
        if (this.personX > 550) {
            if (this.framenum < this.numFrames - 1) {
                ++this.framenum;
                this.personX = -20;
                this.getFrame();
            }
            else {
                this.personX = -20;
                this.framenum = 0;
                this.getFrame();
            }
        }
        if (this.guyframe > 4 && !this.left && !this.right && !this.jump) {
            this.guyframe = 0;
        }
        if (this.ladderAt > -1 && !this.jump) {
            if (this.personX > this.ladderAt - 35 && this.personX < this.ladderAt + 10) {
                if (this.personY == 155) {
                    this.personX = this.ladderAt - 10;
                    this.score -= 100;
                    this.jump = true;
                    this.jumpup = -5;
                    this.jumpdir = 0;
                    this.floor = 310;
                    this.guyframe = 4;
                }
                if (this.personY == 310 && this.climbing == 0 && this.up) {
                    this.climbing = 1;
                    this.guyframe = 4;
                    this.personX = this.ladderAt - 10;
                }
                if (this.climbing == 1) {
                    if (this.up && this.personY > 190) {
                        this.personY -= 10;
                        this.guydir = 1 - this.guydir;
                    }
                    if (this.down && this.personY < 310) {
                        this.personY += 10;
                        this.guydir = 1 - this.guydir;
                        if (this.personY > 309) {
                            this.personY = 310;
                            this.climbing = 0;
                            this.guyframe = 0;
                        }
                    }
                }
            }
            else if (this.personY == 155) {
                if (this.personX > this.ladderAt - 50 && this.personX < this.ladderAt - 34 && this.down) {
                    this.personX = this.ladderAt - 10;
                    this.personY += 50;
                    this.climbing = 1;
                    this.guyframe = 4;
                }
                if (this.personX > this.ladderAt + 9 && this.personX < this.ladderAt + 25 && this.down) {
                    this.personX = this.ladderAt - 10;
                    this.personY += 50;
                    this.climbing = 1;
                    this.guyframe = 4;
                }
            }
        }
        if (this.personY == 155) {
            for (int i = 0; i < 4; ++i) {
                if (this.pits[i] > -1 && this.personX > this.pits[i] - 35 && this.personX < this.pits[i] + 35) {
                    this.score -= 100;
                    this.jump = true;
                    this.jumpup = -5;
                    this.jumpdir = 0;
                    this.floor = 310;
                    this.personX = this.pits[i];
                    this.guyframe = 4;
                }
            }
        }
        if (this.treasureType > -1 && this.personX > this.treasureX - 35 && this.personX < this.treasureX + 20 && this.personY > this.treasureY - 50 && this.personY < this.treasureY - 30) {
            switch (this.treasureType) {
                case 0: {
                    this.score += 500;
                    break;
                }
                case 1: {
                    this.score += 1000;
                    break;
                }
                case 2: {
                    this.score += 2000;
                    break;
                }
                case 3: {
                    this.score += 5000;
                    break;
                }
                case 4: {
                    this.score += 10000;
                    break;
                }
            }
            this.treasureType = -1;
            this.board[this.framenum][19] = -1;
        }
        if (this.snakeAt > -1 && this.personX > this.snakeAt - 25 && this.personX < this.snakeAt + 10 && this.personY > this.snakeZ - 60 && this.personY < this.snakeZ - 30) {
            this.frozen = 1;
            if (this.personY < this.floor) {
                this.jump = true;
                this.jumpup = -5;
                this.jumpdir = 0;
                this.guyframe = 0;
            }
        }
        if (this.scorpion > -1 && this.personX > this.scorpion - 45 && this.personX < this.scorpion + 20 && this.personY > 300) {
            this.frozen = 1;
            if (this.personY < this.floor) {
                this.jump = true;
                this.jumpup = -5;
                this.jumpdir = 0;
                this.guyframe = 0;
            }
        }
        if (this.fire > -1 && this.personX > this.fire - 30 && this.personX < this.fire + 10 && this.personY > this.fireY - 45 && this.personY < this.fireY - 30) {
            this.frozen = 1;
            if (this.personY < this.floor) {
                this.jump = true;
                this.jumpup = -5;
                this.jumpdir = 0;
                this.guyframe = 0;
            }
        }
        int n6 = 0;
        for (int j = 0; j < 4; ++j) {
            if (this.loglocs[j] > -999 && this.personY == 155 && this.personX > this.loglocs[j] - 45 && this.personX < this.loglocs[j] + 10) {
                n6 = 1;
            }
        }
        if (n6 == 1) {
            this.score -= 5;
            this.guyframe = 2;
            return;
        }
        if (this.guyframe == 2) {
            this.guyframe = 0;
        }
    }
    
    public void paint(final Graphics graphics) {
        if (!this.ready) {
            if (!this.havetitle) {
                graphics.setColor(Color.black);
                graphics.fillRect(0, 0, 600, 400);
            }
            else {
                graphics.drawImage(this.title, 0, 0, null);
            }
            graphics.setFont(this.noticeFont);
            graphics.setColor(Color.white);
            graphics.drawString("Loading Graphics...", 50, 380);
            return;
        }
        if (this.gamerunning) {
            graphics.drawImage(this.osImage, 0, 0, null);
            return;
        }
        graphics.drawImage(this.title, 0, 0, null);
        graphics.setFont(this.noticeFont);
        graphics.setColor(Color.white);
        graphics.drawString("Press any key to begin!", 50, 380);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void drawFrame() {
        if (this.framenum / 2 == this.framenum / 2.0f) {
            this.osGraphics.drawImage(this.backdrop1, 0, 0, null);
        }
        else {
            this.osGraphics.drawImage(this.backdrop2, 0, 0, null);
        }
        if (this.ladderAt > -1) {
            this.osGraphics.setColor(Color.black);
            this.osGraphics.fillRect(this.ladderAt, 220, 50, 20);
            this.osGraphics.setColor(this.brown);
            this.osGraphics.fillRect(this.ladderAt + 5, 225, 5, 15);
            this.osGraphics.fillRect(this.ladderAt + 40, 225, 5, 15);
            this.osGraphics.fillRect(this.ladderAt + 5, 260, 5, 120);
            this.osGraphics.fillRect(this.ladderAt + 40, 260, 5, 120);
            for (int i = 0; i < 5; ++i) {
                this.osGraphics.fillRect(this.ladderAt + 5, 275 + i * 20, 40, 5);
            }
        }
        this.osGraphics.setColor(Color.black);
        for (int j = 0; j < 4; ++j) {
            if (this.pits[j] > -1) {
                this.osGraphics.fillRect(this.pits[j], 220, 70, 20);
            }
        }
        if (this.puddle > 0) {
            if (this.puddlecolor == 1) {
                this.osGraphics.setColor(this.myBlue);
            }
            else {
                this.osGraphics.setColor(Color.black);
            }
            if (this.drowning == 0) {
                ++this.puddlepause;
            }
            if (this.puddlepause == 60) {
                this.puddlepause = 0;
            }
            if (this.puddle == 2) {
                if (this.puddlepause < 8) {
                    this.puddlesize = this.puddlepause;
                }
                if (this.puddlepause > 7 && this.puddlepause < 30) {
                    this.puddlesize = 8;
                }
                if (this.puddlepause > 29 && this.puddlepause < 38) {
                    this.puddlesize = 38 - this.puddlepause;
                }
                if (this.puddlepause > 37) {
                    this.puddlesize = 0;
                }
            }
            else {
                this.puddlesize = 8;
            }
            this.osGraphics.fillOval(300 - this.puddlesize * 18, 225 - this.puddlesize, this.puddlesize * 36, this.puddlesize * 3);
            if (this.personY == 155 && this.drowning == 0 && this.personX > 265 - this.puddlesize * 18 && this.personX < 255 + this.puddlesize * 18) {
                int n = 1;
                if (this.puddle == 3) {
                    if (this.gatorpause < 16) {
                        if (this.personX > 156 && this.personX < 193) {
                            n = 0;
                        }
                        if (this.personX > 246 && this.personX < 283) {
                            n = 0;
                        }
                        if (this.personX > 336 && this.personX < 373) {
                            n = 0;
                        }
                    }
                    else {
                        if (this.personX > 176 && this.personX < 193) {
                            n = 0;
                        }
                        if (this.personX > 266 && this.personX < 283) {
                            n = 0;
                        }
                        if (this.personX > 356 && this.personX < 373) {
                            n = 0;
                        }
                    }
                }
                if (n == 1) {
                    this.drowning = 1;
                    this.guyframe = 0;
                }
            }
            if (this.vine == 1) {
                ++this.vinepause;
                if (this.vinepause == 38) {
                    this.vinepause = 0;
                }
                int vinepause = this.vinepause;
                if (vinepause > 19) {
                    vinepause = 38 - vinepause;
                }
                this.osGraphics.setColor(Color.black);
                this.osGraphics.drawLine(300, 25, this.vinex[vinepause], this.viney[vinepause]);
                if (this.swinging == 1) {
                    this.personX = this.vinex[vinepause] - 38;
                    this.personY = this.viney[vinepause] - 10;
                    this.guyframe = 3;
                }
            }
            if (this.puddle == 3) {
                int n2 = 0;
                if (this.gatorpause > 15) {
                    n2 = 1;
                }
                this.osGraphics.drawImage(this.gators[n2], 195, 210, null);
                this.osGraphics.drawImage(this.gators[n2], 285, 210, null);
                this.osGraphics.drawImage(this.gators[n2], 375, 210, null);
            }
        }
        if (this.logsmoving == 1) {
            ++this.logct;
            if (this.logct == 4) {
                this.logct = 0;
            }
        }
        else {
            this.logct = 0;
        }
        for (int k = 0; k < 4; ++k) {
            if (this.loglocs[k] > -999) {
                if (this.logsmoving == 1) {
                    final int[] loglocs = this.loglocs;
                    final int n3 = k;
                    loglocs[n3] -= 10;
                    if (this.loglocs[k] == -50) {
                        this.loglocs[k] = 650;
                    }
                    if (this.personY > 160 && this.personY < 240 && this.personX > this.loglocs[k] - 45 && this.personX < this.loglocs[k] + 10) {
                        this.score -= 25;
                        this.personY = 240;
                    }
                }
                this.osGraphics.drawImage(this.logs[this.logct], this.loglocs[k], 190, null);
            }
        }
        if (this.personY < 160) {
            if (this.drowning > 0) {
                this.osGraphics.setClip(this.personX, this.personY, 75, 75);
            }
            this.osGraphics.drawImage(this.guy[this.guyframe][this.guydir], this.personX, this.personY + this.drowning, null);
            if (this.drowning > 0) {
                this.osGraphics.setClip(0, 0, 600, 400);
                ++this.drowning;
                if (this.drowning == 20) {
                    this.drowning = 0;
                    this.guydies();
                }
            }
        }
        else if (this.personY > 250) {
            this.osGraphics.drawImage(this.guy[this.guyframe][this.guydir], this.personX, this.personY, null);
        }
        else {
            this.osGraphics.setClip(0, 0, 600, 240);
            this.osGraphics.drawImage(this.guy[this.guyframe][this.guydir], this.personX, this.personY, null);
            this.osGraphics.setClip(0, 260, 600, 240);
            this.osGraphics.drawImage(this.guy[this.guyframe][this.guydir], this.personX, this.personY, null);
            this.osGraphics.setClip(0, 0, 600, 400);
        }
        if (this.snakeAt > -1) {
            ++this.snakepos;
            if (this.snakepos == 20) {
                this.snakepos = 0;
            }
            int snakepos = this.snakepos;
            if (snakepos > 3) {
                snakepos = 6 - snakepos;
            }
            if (this.snakepos > 6) {
                snakepos = 0;
            }
            int n4 = 0;
            if (this.personX > this.snakeAt - 15) {
                n4 = 1;
            }
            this.osGraphics.drawImage(this.snake[snakepos][n4], this.snakeAt, this.snakeZ, null);
        }
        if (this.fire > -1) {
            ++this.fireframe;
            if (this.fireframe == 4) {
                this.fireframe = 0;
            }
            this.osGraphics.drawImage(this.fires[this.fireframe], this.fire, this.fireY, null);
        }
        if (this.scorpion > -1) {
            this.scorpframe = 1 - this.scorpframe;
            int n5 = 1;
            if (this.scorpion > this.personX + 15) {
                n5 = 0;
                if ((this.wallpt == -1 || this.scorpion > this.wallpt + 60 || this.scorpion < this.wallpt - 30) && this.scorpion > this.personX + 18) {
                    this.scorpion -= 3;
                }
            }
            else if ((this.wallpt == -1 || this.scorpion < this.wallpt - 50 || this.scorpion > this.wallpt) && this.scorpion < this.personX + 13) {
                this.scorpion += 3;
            }
            this.osGraphics.drawImage(this.scorpions[this.scorpframe][n5], this.scorpion, 350, null);
        }
        if (this.treasureType > -1) {
            this.osGraphics.drawImage(this.treasures[this.treasureType], this.treasureX, this.treasureY, null);
        }
        if (this.frozen > 0) {
            ++this.frozen;
            if (this.frozen == 20) {
                this.guydies();
            }
        }
        if (this.wallpt > -1) {
            this.osGraphics.drawImage(this.wall, this.wallpt, 260, null);
        }
        this.osGraphics.setColor(Color.white);
        this.osGraphics.setFont(this.dataFont);
        if (this.score < 0) {
            this.score = 0;
        }
        this.osGraphics.drawString("Lives:" + this.lives, 10, 16);
        this.osGraphics.drawString("Score:" + this.score, 450, 16);
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (this.drowning > 0 || this.frozen > 0) {
            return true;
        }
        if (n == 1006) {
            this.left = true;
            this.right = false;
        }
        else if (n == 1007) {
            this.right = true;
            this.left = false;
        }
        else if (n == 1004) {
            this.up = true;
        }
        else if (n == 1005) {
            this.down = true;
        }
        else if (n == 32) {
            this.hitJump();
        }
        if (this.left) {
            this.guydir = 1;
        }
        else if (this.right) {
            this.guydir = 0;
        }
        if (this.climbing == 1 && (n == 1006 || n == 1007)) {
            this.hitJump();
        }
        return true;
    }
    
    public void hitJump() {
        if (this.climbing == 1 && this.personY > 190 && this.personY < 280) {
            return;
        }
        if (this.drowning > 0 || this.frozen > 0) {
            return;
        }
        if (!this.jump) {
            if (this.personY < 230) {
                this.floor = 155;
            }
            else {
                this.floor = 310;
            }
            this.jump = true;
            if (this.left) {
                this.jumpdir = -1;
            }
            else if (this.right) {
                this.jumpdir = 1;
            }
            else {
                this.jumpdir = 0;
            }
            this.jumpup = 15;
            this.climbing = 0;
            this.guyframe = 1;
        }
    }
    
    public boolean keyUp(final Event event, final int n) {
        if (!this.gamerunning) {
            this.newGame();
            return true;
        }
        if (n == 1006) {
            this.left = false;
        }
        else if (n == 1007) {
            this.right = false;
        }
        else if (n == 1004) {
            this.up = false;
        }
        else if (n == 1005) {
            this.down = false;
        }
        return true;
    }
    
    public void guydies() {
        if (this.lives > 0) {
            --this.lives;
            this.personX = 20;
            this.personY = 50;
            this.jump = true;
            this.jumpdir = 0;
            this.jumpup = -4;
            this.climbing = 0;
            this.guyframe = 1;
            this.guydir = 0;
            this.frozen = 0;
            this.drowning = 0;
            this.floor = 155;
            return;
        }
        this.osGraphics.setFont(new Font("Serif", 1, 48));
        this.osGraphics.setColor(Color.white);
        this.osGraphics.drawString("Game Over...", 180, 200);
        this.osGraphics.drawString("Final Score: " + this.score, 150, 250);
        this.repaint();
        try {
            final URLConnection openConnection = new URL(String.valueOf(this.parent.getCodeBase()) + "/scores.php?score=" + this.score).openConnection();
            openConnection.setDoInput(true);
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
            String line;
            do {
                line = bufferedReader.readLine();
                System.out.println(line);
            } while (!line.equals(null));
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        try {
            Thread.sleep(5000L);
        }
        catch (Exception ex2) {}
        this.gamerunning = false;
    }
    
    public void newGame() {
        this.board = new int[this.numFrames][22];
        for (int i = 0; i < this.numFrames; ++i) {
            for (int j = 0; j < 22; ++j) {
                this.board[i][j] = this.origboard[i][j];
            }
        }
        this.gamerunning = true;
        this.framenum = 0;
        this.getFrame();
        this.lives = 3;
        this.score = 1000;
        this.guydies();
    }
    
    public void getFrame() {
        System.out.println("Frame " + this.framenum);
        for (int i = 0; i < 4; ++i) {
            this.loglocs[i] = this.board[this.framenum][i];
            this.pits[i] = this.board[this.framenum][i + 5];
        }
        this.logsmoving = this.board[this.framenum][4];
        this.ladderAt = this.board[this.framenum][9];
        this.snakeAt = this.board[this.framenum][10];
        this.snakeZ = this.board[this.framenum][11];
        this.fire = this.board[this.framenum][12];
        this.fireY = this.board[this.framenum][13];
        this.scorpion = this.board[this.framenum][14];
        this.puddle = this.board[this.framenum][15];
        this.puddlecolor = this.board[this.framenum][16];
        this.vine = this.board[this.framenum][17];
        this.wallpt = this.board[this.framenum][18];
        this.treasureType = this.board[this.framenum][19];
        this.treasureX = this.board[this.framenum][20];
        this.treasureY = this.board[this.framenum][21];
    }
    
    public void loadLayout() {
        try {
            final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new URL(this.parent.getCodeBase(), "layout.txt").openConnection().getInputStream()));
            this.numFrames = new Integer(dataInputStream.readLine());
            dataInputStream.readLine();
            this.origboard = new int[this.numFrames][22];
            for (int i = 0; i < this.numFrames; ++i) {
                final StringTokenizer stringTokenizer = new StringTokenizer(dataInputStream.readLine());
                for (int j = 0; j < 22; ++j) {
                    this.origboard[i][j] = new Integer(stringTokenizer.nextToken());
                }
            }
        }
        catch (Exception ex) {}
    }
}
