import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Font;
import java.text.DecimalFormat;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class gotcha extends Applet implements Runnable, MouseListener, MouseMotionListener
{
    final int MAIN_MENU = 0;
    final int INIT = 1;
    final int GAMEON = 2;
    final int GOTCHA = 3;
    final int COUNTSCORE = 4;
    final int TIMESUP = 5;
    final int GAMEOVER = 6;
    final int COMPLETE = 7;
    int boxtx;
    int boxty;
    int boxbx;
    int boxby;
    int boxc1;
    int boxc2;
    int boxw;
    int boxh;
    int xp;
    int yp;
    int dx;
    int dy;
    int txd;
    int bxd;
    int tyd;
    int byd;
    int[][] ballpos;
    int ballSpeed;
    int speed;
    int maxball;
    int winn;
    int winw;
    int wine;
    int wins;
    int boxfw;
    int boxfh;
    int level;
    int life;
    int mainscore;
    int[] score;
    int[] gccatch;
    int gameState;
    int gcx;
    int gcy;
    static final int ballw = 20;
    static final int boxb = 4;
    static final int maxlevel = 5;
    boolean boxin;
    boolean t;
    boolean b;
    boolean l;
    boolean r;
    boolean tl;
    boolean tr;
    boolean bl;
    boolean br;
    boolean hsplit;
    boolean vsplit;
    boolean counting;
    boolean t1;
    boolean t2;
    boolean t3;
    boolean t4;
    Image ball1;
    Image ball2;
    Image ball3;
    Image[] ball;
    Image gotcha;
    Image gotchabg;
    long start;
    long now;
    long end;
    long time;
    long gcstart;
    long gcnow;
    DecimalFormat clock;
    DecimalFormat scoref;
    boolean startwatch;
    Font SansSerif12;
    Font SansSerif36;
    MediaTracker tracker;
    Thread runner;
    Image workspace;
    Graphics offscreen;
    
    public gotcha() {
        this.boxtx = 100;
        this.boxty = 100;
        this.boxbx = 200;
        this.boxby = 200;
        this.boxc1 = 255;
        this.boxc2 = 0;
        this.speed = 50;
        this.maxball = 14;
        this.winn = 6;
        this.winw = 6;
        this.wine = 294;
        this.wins = 254;
        this.gcx = 70;
        this.gcy = 350;
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void pause(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner = null;
        }
    }
    
    public void init() {
        this.tracker = new MediaTracker(this);
        this.ball1 = this.getImage(this.getCodeBase(), "ball1.gif");
        this.tracker.addImage(this.ball1, 0);
        this.ball2 = this.getImage(this.getCodeBase(), "ball2.gif");
        this.tracker.addImage(this.ball2, 0);
        this.ball3 = this.getImage(this.getCodeBase(), "ball3.gif");
        this.tracker.addImage(this.ball3, 0);
        this.gotcha = this.getImage(this.getCodeBase(), "gotcha.jpg");
        this.tracker.addImage(this.gotcha, 0);
        this.gotchabg = this.getImage(this.getCodeBase(), "gotchabg.jpg");
        this.tracker.addImage(this.gotchabg, 0);
        try {
            this.tracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
        this.ball = new Image[this.maxball];
        this.ballpos = new int[this.maxball][4];
        this.score = new int[2];
        this.gccatch = new int[2];
        for (int i = 0; i < 2; ++i) {
            this.score[i] = 0;
            this.gccatch[i] = 0;
        }
        this.clock = new DecimalFormat("00");
        this.scoref = new DecimalFormat("000000");
        this.SansSerif12 = new Font("SansSerif", 0, 10);
        this.SansSerif36 = new Font("SansSerif", 1, 36);
        this.gameState = 0;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.workspace = this.createImage(this.getSize().width, this.getSize().height);
        this.offscreen = this.workspace.getGraphics();
    }
    
    public void run() {
        while (this.runner == Thread.currentThread()) {
            switch (this.gameState) {
                case 0: {
                    this.level = 1;
                    this.life = 5;
                    this.mainscore = 0;
                    break;
                }
                case 1: {
                    this.levelsetup(this.level);
                    this.gameState = 2;
                    break;
                }
                case 2: {
                    if (this.startwatch) {
                        this.start = System.currentTimeMillis();
                        this.startwatch = false;
                    }
                    this.now = System.currentTimeMillis();
                    int maxball = this.maxball;
                    while (--maxball >= 0) {
                        final int[] array = this.ballpos[maxball];
                        final int n = 0;
                        array[n] += this.ballpos[maxball][2];
                        final int[] array2 = this.ballpos[maxball];
                        final int n2 = 1;
                        array2[n2] += this.ballpos[maxball][3];
                        this.collidehandler(maxball);
                    }
                    this.time = this.end - (this.now - this.start);
                    if (this.time < 1L) {
                        if (this.life > 1) {
                            this.gameState = 5;
                            this.startwatch = true;
                        }
                        else {
                            this.gameState = 6;
                            this.startwatch = true;
                        }
                    }
                    if (this.gotchayet()) {
                        this.gameState = 3;
                        this.startwatch = true;
                        break;
                    }
                    break;
                }
                case 3: {
                    --this.gcy;
                    if (this.gcy <= 100) {
                        this.gcy = 100;
                        this.speed = 50;
                        if (this.startwatch) {
                            this.gcstart = System.currentTimeMillis();
                            this.startwatch = false;
                        }
                        this.gcnow = System.currentTimeMillis();
                    }
                    if ((this.gcnow - this.gcstart) / 1000L % 60L > 3L) {
                        this.gameState = 4;
                    }
                    if (this.counting) {
                        if (this.hsplit && !this.vsplit) {
                            int maxball2 = this.maxball;
                            while (--maxball2 >= 0) {
                                if (this.ballpos[maxball2][1] <= this.boxty) {
                                    this.doscore(maxball2, 0);
                                    if (maxball2 > this.level) {
                                        this.t1 = true;
                                    }
                                }
                                if (this.ballpos[maxball2][1] >= this.boxby - 20) {
                                    this.doscore(maxball2, 1);
                                    if (maxball2 <= this.level) {
                                        continue;
                                    }
                                    this.t2 = true;
                                }
                            }
                            if (this.gccatch[0] < this.level) {
                                this.t1 = true;
                            }
                            if (this.gccatch[1] < this.level) {
                                this.t2 = true;
                            }
                            if (!this.t1) {
                                this.mainscore += this.score[0] + (int)(this.end - (this.now - this.start)) / 10;
                            }
                            if (!this.t2) {
                                this.mainscore += this.score[1] + (int)(this.end - (this.now - this.start)) / 10;
                            }
                        }
                        if (!this.hsplit && this.vsplit) {
                            int maxball3 = this.maxball;
                            while (--maxball3 >= 0) {
                                if (this.ballpos[maxball3][0] <= this.boxtx) {
                                    this.doscore(maxball3, 0);
                                    if (maxball3 > this.level) {
                                        this.t3 = true;
                                    }
                                }
                                if (this.ballpos[maxball3][0] >= this.boxbx - 20) {
                                    this.doscore(maxball3, 1);
                                    if (maxball3 <= this.level) {
                                        continue;
                                    }
                                    this.t4 = true;
                                }
                            }
                            if (this.gccatch[0] < this.level) {
                                this.t3 = true;
                            }
                            if (this.gccatch[1] < this.level) {
                                this.t4 = true;
                            }
                            if (!this.t3) {
                                this.mainscore += this.score[0] + (int)(this.end - (this.now - this.start)) / 10;
                            }
                            if (!this.t4) {
                                this.mainscore += this.score[1] + (int)(this.end - (this.now - this.start)) / 10;
                            }
                        }
                        this.counting = false;
                        break;
                    }
                    break;
                }
                case 5: {
                    if (this.startwatch) {
                        this.gcstart = System.currentTimeMillis();
                        --this.life;
                        this.startwatch = false;
                    }
                    this.gcnow = System.currentTimeMillis();
                    if ((this.gcnow - this.gcstart) / 1000L % 60L > 5L) {
                        this.gameState = 1;
                        break;
                    }
                    break;
                }
                case 6: {
                    if (this.startwatch) {
                        this.gcstart = System.currentTimeMillis();
                        this.startwatch = false;
                    }
                    this.gcnow = System.currentTimeMillis();
                    if ((this.gcnow - this.gcstart) / 1000L % 60L > 5L) {
                        this.gameState = 0;
                        break;
                    }
                    break;
                }
                case 7: {
                    if (this.startwatch) {
                        this.gcstart = System.currentTimeMillis();
                        this.startwatch = false;
                    }
                    this.gcnow = System.currentTimeMillis();
                    if ((this.gcnow - this.gcstart) / 1000L % 60L > 5L) {
                        this.gameState = 0;
                        break;
                    }
                    break;
                }
            }
            this.pause(this.speed);
            this.repaint();
        }
    }
    
    public void doscore(final int n, final int n2) {
        if (n < this.level) {
            final int[] score = this.score;
            score[n2] += 10;
            final int[] gccatch = this.gccatch;
            ++gccatch[n2];
        }
        else if (n == this.level) {
            final int[] score2 = this.score;
            score2[n2] += 5000;
        }
    }
    
    public boolean gotchayet() {
        if (this.boxtx == this.winw && this.boxbx == this.wine) {
            return this.hsplit = true;
        }
        return this.boxty == this.winn && this.boxby == this.wins && (this.vsplit = true);
    }
    
    public void collidehandler(final int n) {
        int maxball = this.maxball;
        while (--maxball >= 0) {
            final int n2 = this.ballpos[n][0] + 10 - (this.ballpos[maxball][0] + 10);
            final int n3 = this.ballpos[n][1] + 10 - (this.ballpos[maxball][1] + 10);
            final double sqrt = Math.sqrt(n2 * n2 + n3 * n3);
            if (sqrt <= 20.0 && sqrt > 0.0) {
                final int rnd = this.rnd(this.ballSpeed);
                if (this.ballpos[maxball][3] > 0) {
                    this.ballpos[n][2] = rnd;
                }
                else {
                    this.ballpos[n][2] = rnd * -1;
                }
                this.ballpos[n][3] = this.ballpos[maxball][2];
                if ((this.ballpos[n][2] > 0 && n2 < 0) || (this.ballpos[n][2] < 0 && n2 > 0)) {
                    final int[] array = this.ballpos[n];
                    final int n4 = 2;
                    array[n4] *= -1;
                }
                if ((this.ballpos[n][3] <= 0 || n3 >= 0) && (this.ballpos[n][3] >= 0 || n3 <= 0)) {
                    continue;
                }
                final int[] array2 = this.ballpos[n];
                final int n5 = 3;
                array2[n5] *= -1;
            }
        }
        if (this.ballpos[n][2] > 0 && this.ballpos[n][0] + 20 >= this.boxtx && this.ballpos[n][0] < this.boxtx && this.ballpos[n][1] >= this.boxty - 20 && this.ballpos[n][1] <= this.boxby + 20) {
            final int[] array3 = this.ballpos[n];
            final int n6 = 2;
            array3[n6] *= -1;
            final int[] array4 = this.ballpos[n];
            final int n7 = 0;
            array4[n7] += this.ballpos[n][2];
        }
        if (this.ballpos[n][2] < 0 && this.ballpos[n][0] <= this.boxbx && this.ballpos[n][0] + 20 > this.boxbx && this.ballpos[n][1] >= this.boxty - 20 && this.ballpos[n][1] <= this.boxby + 20) {
            final int[] array5 = this.ballpos[n];
            final int n8 = 2;
            array5[n8] *= -1;
            final int[] array6 = this.ballpos[n];
            final int n9 = 0;
            array6[n9] += this.ballpos[n][2];
        }
        if (this.ballpos[n][3] > 0 && this.ballpos[n][1] + 20 >= this.boxty && this.ballpos[n][1] < this.boxty && this.ballpos[n][0] >= this.boxtx - 20 && this.ballpos[n][0] <= this.boxbx + 20) {
            final int[] array7 = this.ballpos[n];
            final int n10 = 3;
            array7[n10] *= -1;
            final int[] array8 = this.ballpos[n];
            final int n11 = 1;
            array8[n11] += this.ballpos[n][3];
        }
        if (this.ballpos[n][3] < 0 && this.ballpos[n][1] <= this.boxby && this.ballpos[n][1] + 20 > this.boxby && this.ballpos[n][0] >= this.boxtx - 20 && this.ballpos[n][0] <= this.boxbx + 20) {
            final int[] array9 = this.ballpos[n];
            final int n12 = 3;
            array9[n12] *= -1;
            final int[] array10 = this.ballpos[n];
            final int n13 = 1;
            array10[n13] += this.ballpos[n][3];
        }
        if (this.ballpos[n][0] < this.winw && this.ballpos[n][2] < 0) {
            final int[] array11 = this.ballpos[n];
            final int n14 = 2;
            array11[n14] *= -1;
            final int[] array12 = this.ballpos[n];
            final int n15 = 0;
            array12[n15] += this.ballpos[n][2];
        }
        if (this.ballpos[n][0] + 20 > this.wine && this.ballpos[n][2] > 0) {
            final int[] array13 = this.ballpos[n];
            final int n16 = 2;
            array13[n16] *= -1;
            final int[] array14 = this.ballpos[n];
            final int n17 = 0;
            array14[n17] += this.ballpos[n][2];
        }
        if (this.ballpos[n][1] < this.winn && this.ballpos[n][3] < 0) {
            final int[] array15 = this.ballpos[n];
            final int n18 = 3;
            array15[n18] *= -1;
            final int[] array16 = this.ballpos[n];
            final int n19 = 1;
            array16[n19] += this.ballpos[n][3];
        }
        if (this.ballpos[n][1] + 20 > this.wins && this.ballpos[n][3] > 0) {
            final int[] array17 = this.ballpos[n];
            final int n20 = 3;
            array17[n20] *= -1;
            final int[] array18 = this.ballpos[n];
            final int n21 = 1;
            array18[n21] += this.ballpos[n][3];
        }
    }
    
    public void drawOjects(final Graphics graphics) {
        this.shadowLine(this.offscreen, this.boxtx, this.boxty, this.boxbx, this.boxby, this.boxc1, this.boxc2);
        int maxball = this.maxball;
        while (--maxball >= 0) {
            graphics.drawImage(this.ball[maxball], this.ballpos[maxball][0], this.ballpos[maxball][1], this);
        }
    }
    
    public void drawGame(final Graphics graphics) {
        this.shadowLine(this.offscreen, this.winw, this.winn, this.wine, this.wins, this.boxc2, this.boxc1);
        graphics.drawImage(this.gotchabg, 7, 7, this);
        this.shadowLine(this.offscreen, 5, 257, 50, 276, this.boxc2, this.boxc1);
        this.shadowLine(this.offscreen, 5, 278, 50, 297, this.boxc2, this.boxc1);
        this.shadowLine(this.offscreen, 52, 257, 72, 276, this.boxc2, this.boxc1);
        this.shadowLine(this.offscreen, 52, 278, 72, 297, this.boxc2, this.boxc1);
        this.shadowLine(this.offscreen, 74, 257, 228, 297, this.boxc2, this.boxc1);
        this.shadowLine(this.offscreen, 230, 257, 294, 276, this.boxc2, this.boxc1);
        this.shadowLine(this.offscreen, 230, 278, 294, 297, this.boxc2, this.boxc1);
        graphics.setColor(new Color(230, 229, 255));
        graphics.fillRect(6, 258, 44, 18);
        graphics.fillRect(6, 279, 44, 18);
        graphics.fillRect(231, 258, 62, 18);
        graphics.setColor(new Color(0, 0, 64));
        graphics.fillRect(75, 258, 153, 39);
        graphics.setFont(this.SansSerif12);
        graphics.setColor(Color.black);
        graphics.drawString("LEVEL", 12, 271);
        graphics.drawString("LIFE", 17, 292);
        graphics.drawString("SCORE", 244, 271);
        graphics.drawString(String.valueOf(this.level), 60, 271);
        graphics.drawString(String.valueOf(this.life), 60, 292);
        graphics.drawString(String.valueOf(this.scoref.format(this.mainscore)), 245, 292);
        graphics.setColor(Color.green);
        graphics.drawString(String.valueOf(this.clock.format(this.time / 10L % 100L)), 170, 280);
        graphics.drawString(String.valueOf(this.clock.format(this.time / 1000L % 60L)) + ":", 145, 280);
        graphics.drawString(String.valueOf(this.clock.format(this.time / 1000L / 60L % 60L)) + ":", 120, 280);
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        this.offscreen.setColor(new Color(215, 214, 255));
        this.offscreen.fillRect(0, 0, this.getSize().width, this.getSize().height);
        switch (this.gameState) {
            case 0: {
                this.offscreen.drawImage(this.gotcha, 0, 0, this);
                break;
            }
            case 2: {
                this.drawGame(this.offscreen);
                this.drawOjects(this.offscreen);
                break;
            }
            case 3: {
                this.drawGame(this.offscreen);
                this.drawOjects(this.offscreen);
                this.offscreen.setFont(this.SansSerif36);
                this.offscreen.setColor(Color.black);
                this.offscreen.drawString("GOTCHA!", this.gcx, this.gcy + 2);
                this.offscreen.setColor(new Color(215, 214, 255));
                this.offscreen.drawString("GOTCHA!", this.gcx, this.gcy);
                break;
            }
            case 5: {
                this.drawGame(this.offscreen);
                this.offscreen.setColor(Color.green);
                this.offscreen.setFont(this.SansSerif12);
                this.offscreen.drawString("HEY!! TIMES UP...TRY AGAIN!", 74, 150);
                break;
            }
            case 4: {
                this.drawGame(this.offscreen);
                this.offscreen.setColor(Color.black);
                this.offscreen.setFont(this.SansSerif12);
                this.offscreen.drawString("LEVEL " + this.level + " RESULT:", 12, 20);
                this.shadowLine(this.offscreen, 9, 28, 163, 76, this.boxc1, this.boxc2);
                this.shadowLine(this.offscreen, 9, 78, 163, 126, this.boxc1, this.boxc2);
                this.offscreen.setColor(Color.green);
                this.offscreen.drawString("[CLICK ME BABY ONE MORE TIME...]", 60, 225);
                this.offscreen.setColor(Color.black);
                if (this.hsplit && !this.vsplit) {
                    int n = 0;
                    int n2 = 0;
                    int n3 = 30;
                    int n4 = 80;
                    int maxball = this.maxball;
                    while (--maxball >= 0) {
                        if (this.ballpos[maxball][1] <= this.boxty) {
                            if (n >= 7) {
                                n3 = 55;
                            }
                            this.offscreen.drawImage(this.ball[maxball], 10 + n % 7 * 22, n3, this);
                            ++n;
                        }
                        if (this.ballpos[maxball][1] >= this.boxby - 20) {
                            if (n2 >= 7) {
                                n4 = 105;
                            }
                            this.offscreen.drawImage(this.ball[maxball], 10 + n2 % 7 * 22, n4, this);
                            ++n2;
                        }
                    }
                    if (this.t1) {
                        this.offscreen.drawString("GRRRR.....", 170, 45);
                    }
                    else {
                        this.offscreen.drawString("GOTCHA!", 170, 45);
                        this.offscreen.drawString("= " + this.score[0] + " POINTS", 170, 65);
                    }
                    if (this.t2) {
                        this.offscreen.drawString("GRRRR.....", 170, 95);
                    }
                    else {
                        this.offscreen.drawString("GOTCHA!", 170, 95);
                        this.offscreen.drawString("= " + this.score[1] + " POINTS", 170, 115);
                    }
                    if (!this.t1 || !this.t2) {
                        this.offscreen.drawString("OVERALL OBJECTIVE: [PASS]", 12, 145);
                        if (!this.t1) {
                            this.offscreen.drawString("ADDING TO THE SCORE = " + this.score[0] + " + " + (this.end - (this.now - this.start)) / 10L + " POINTS", 12, 165);
                        }
                        if (!this.t2) {
                            this.offscreen.drawString("ADDING TO THE SCORE = " + this.score[1] + " + " + (this.end - (this.now - this.start)) / 10L + " POINTS", 12, 165);
                        }
                    }
                    else {
                        this.offscreen.drawString("OVERALL OBJECTIVE: [FAIL]", 12, 145);
                    }
                }
                if (this.hsplit || !this.vsplit) {
                    break;
                }
                int n5 = 0;
                int n6 = 0;
                int n7 = 30;
                int n8 = 80;
                int maxball2 = this.maxball;
                while (--maxball2 >= 0) {
                    if (this.ballpos[maxball2][0] <= this.boxtx) {
                        if (n5 >= 7) {
                            n7 = 55;
                        }
                        this.offscreen.drawImage(this.ball[maxball2], 10 + n5 % 7 * 22, n7, this);
                        ++n5;
                    }
                    if (this.ballpos[maxball2][0] >= this.boxbx - 20) {
                        if (n6 >= 7) {
                            n8 = 105;
                        }
                        this.offscreen.drawImage(this.ball[maxball2], 10 + n6 % 7 * 22, n8, this);
                        ++n6;
                    }
                }
                if (this.t3) {
                    this.offscreen.drawString("GRRRR.....", 170, 45);
                }
                else {
                    this.offscreen.drawString("GOTCHA!", 170, 45);
                    this.offscreen.drawString("= " + this.score[0] + " POINTS", 170, 65);
                }
                if (this.t4) {
                    this.offscreen.drawString("GRRRR.....", 170, 95);
                }
                else {
                    this.offscreen.drawString("GOTCHA!", 170, 95);
                    this.offscreen.drawString("= " + this.score[1] + " POINTS", 170, 115);
                }
                if (this.t3 && this.t4) {
                    this.offscreen.drawString("OVERALL OBJECTIVE: [FAIL]", 12, 145);
                    break;
                }
                this.offscreen.drawString("OVERALL OBJECTIVE: [PASS]", 12, 145);
                if (!this.t3) {
                    this.offscreen.drawString("ADDING TO THE SCORE = " + this.score[0] + " + " + (this.end - (this.now - this.start)) / 10L + " POINTS", 12, 165);
                }
                if (!this.t4) {
                    this.offscreen.drawString("ADDING TO THE SCORE = " + this.score[1] + " + " + (this.end - (this.now - this.start)) / 10L + " POINTS", 12, 165);
                    break;
                }
                break;
            }
            case 6: {
                this.drawGame(this.offscreen);
                this.offscreen.setColor(Color.green);
                this.offscreen.drawString("GAME OVER", 120, 150);
                break;
            }
            case 7: {
                this.drawGame(this.offscreen);
                this.offscreen.setColor(Color.green);
                this.offscreen.drawString("ALL LEVEL COMPLETE", 90, 120);
                this.offscreen.drawString("THANKS FOR PLAYING!", 90, 160);
                this.offscreen.drawString("GOTCHA V1.0 ~ JW 10/2002", 80, 200);
                break;
            }
        }
        graphics.drawImage(this.workspace, 0, 0, this);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        switch (this.gameState) {
            case 2: {
                this.boxin = false;
                this.t = false;
                this.b = false;
                this.l = false;
                this.r = false;
                this.tl = false;
                this.tr = false;
                this.bl = false;
                this.br = false;
                break;
            }
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        switch (this.gameState) {
            case 0: {
                final int x = mouseEvent.getX();
                final int y = mouseEvent.getY();
                if (x > 100 && x < 200 && y > 230 && y < 265) {
                    this.gameState = 1;
                    break;
                }
                break;
            }
            case 2: {
                this.xp = mouseEvent.getX();
                this.yp = mouseEvent.getY();
                if (this.xp > this.boxtx + 4 && this.xp < this.boxbx - 4 && this.yp > this.boxty + 4 && this.yp < this.boxby - 4) {
                    this.txd = this.xp - this.boxtx;
                    this.tyd = this.yp - this.boxty;
                    this.bxd = this.xp - this.boxbx;
                    this.byd = this.yp - this.boxby;
                    this.boxin = true;
                }
                if (this.xp > this.boxtx + 4 && this.xp < this.boxbx - 4 && this.yp >= this.boxty && this.yp <= this.boxty + 4) {
                    this.t = true;
                }
                if (this.xp > this.boxtx + 4 && this.xp < this.boxbx - 4 && this.yp <= this.boxby && this.yp >= this.boxby - 4) {
                    this.b = true;
                }
                if (this.xp >= this.boxtx && this.xp <= this.boxtx + 4 && this.yp > this.boxty + 4 && this.yp < this.boxby - 4) {
                    this.l = true;
                }
                if (this.xp <= this.boxbx && this.xp >= this.boxbx - 4 && this.yp > this.boxty + 4 && this.yp < this.boxby - 4) {
                    this.r = true;
                }
                if (this.xp >= this.boxtx && this.xp < this.boxtx + 4 && this.yp >= this.boxty && this.yp < this.boxty + 4) {
                    this.tl = true;
                }
                if (this.xp >= this.boxtx && this.xp < this.boxtx + 4 && this.yp <= this.boxby && this.yp > this.boxby - 4) {
                    this.bl = true;
                }
                if (this.xp <= this.boxbx && this.xp > this.boxbx - 4 && this.yp >= this.boxty && this.yp < this.boxty + 4) {
                    this.tr = true;
                }
                if (this.xp <= this.boxbx && this.xp > this.boxbx - 4 && this.yp <= this.boxby && this.yp > this.boxby - 4) {
                    this.br = true;
                    break;
                }
                break;
            }
            case 4: {
                if (this.hsplit && !this.vsplit) {
                    if (!this.t1 || !this.t2) {
                        ++this.level;
                    }
                    if (this.t1 && this.t2) {
                        --this.life;
                    }
                }
                if (!this.hsplit && this.vsplit) {
                    if (!this.t3 || !this.t4) {
                        ++this.level;
                    }
                    if (this.t3 && this.t4) {
                        --this.life;
                    }
                }
                if (this.level > 5) {
                    this.gameState = 7;
                    this.startwatch = true;
                    break;
                }
                if (this.life < 1) {
                    this.gameState = 6;
                    this.startwatch = true;
                    break;
                }
                this.gameState = 1;
                break;
            }
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        switch (this.gameState) {
            case 0: {
                if (x > 100 && x < 200 && y > 230 && y < 265) {
                    this.setCursor(new Cursor(12));
                    break;
                }
                this.setCursor(new Cursor(0));
                break;
            }
            case 2: {
                if (x > this.boxtx + 4 && x < this.boxbx - 4 && y > this.boxty + 4 && y < this.boxby - 4) {
                    this.setCursor(new Cursor(13));
                    break;
                }
                if ((x > this.boxtx + 4 && x < this.boxbx - 4 && y >= this.boxty && y <= this.boxty + 4) || (x > this.boxtx + 4 && x < this.boxbx - 4 && y <= this.boxby && y >= this.boxby - 4)) {
                    this.setCursor(new Cursor(8));
                    break;
                }
                if ((x >= this.boxtx && x <= this.boxtx + 4 && y > this.boxty + 4 && y < this.boxby - 4) || (x <= this.boxbx && x >= this.boxbx - 4 && y > this.boxty + 4 && y < this.boxby - 4)) {
                    this.setCursor(new Cursor(10));
                    break;
                }
                if ((x >= this.boxtx && x < this.boxtx + 4 && y >= this.boxty && y < this.boxty + 4) || (x <= this.boxbx && x > this.boxbx - 4 && y <= this.boxby && y > this.boxby - 4)) {
                    this.setCursor(new Cursor(6));
                    break;
                }
                if ((x >= this.boxtx && x < this.boxtx + 4 && y <= this.boxby && y > this.boxby - 4) || (x <= this.boxbx && x > this.boxbx - 4 && y >= this.boxty && y < this.boxty + 4)) {
                    this.setCursor(new Cursor(4));
                    break;
                }
                this.setCursor(new Cursor(0));
                break;
            }
            case 3: {
                this.setCursor(new Cursor(0));
                break;
            }
            case 5: {
                this.setCursor(new Cursor(0));
                break;
            }
            case 6: {
                this.setCursor(new Cursor(0));
                break;
            }
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        switch (this.gameState) {
            case 2: {
                this.dx = mouseEvent.getX();
                this.dy = mouseEvent.getY();
                this.boxw = this.boxbx - this.boxtx;
                this.boxh = this.boxby - this.boxty;
                if (this.boxin) {
                    if (this.dx < this.winw + this.txd) {
                        this.dx = this.winw + this.txd;
                    }
                    if (this.dx > this.wine + this.bxd) {
                        this.dx = this.wine + this.bxd;
                    }
                    if (this.dy < this.winn + this.tyd) {
                        this.dy = this.winn + this.tyd;
                    }
                    if (this.dy > this.wins + this.byd) {
                        this.dy = this.wins + this.byd;
                    }
                    this.boxtx = this.dx - this.txd;
                    this.boxty = this.dy - this.tyd;
                    this.boxbx = this.dx - this.bxd;
                    this.boxby = this.dy - this.byd;
                }
                if (this.t) {
                    this.boxty = this.boxlimit1(this.boxh, this.boxfh, this.dy, this.boxby, this.winn);
                }
                if (this.b) {
                    this.boxby = this.boxlimit2(this.boxh, this.boxfh, this.dy, this.boxty, this.wins);
                }
                if (this.l) {
                    this.boxtx = this.boxlimit1(this.boxw, this.boxfw, this.dx, this.boxbx, this.winw);
                }
                if (this.r) {
                    this.boxbx = this.boxlimit2(this.boxw, this.boxfw, this.dx, this.boxtx, this.wine);
                }
                if (this.tl) {
                    this.boxty = this.boxlimit1(this.boxh, this.boxfh, this.dy, this.boxby, this.winn);
                    this.boxtx = this.boxlimit1(this.boxw, this.boxfw, this.dx, this.boxbx, this.winw);
                }
                if (this.tr) {
                    this.boxty = this.boxlimit1(this.boxh, this.boxfh, this.dy, this.boxby, this.winn);
                    this.boxbx = this.boxlimit2(this.boxw, this.boxfw, this.dx, this.boxtx, this.wine);
                }
                if (this.bl) {
                    this.boxby = this.boxlimit2(this.boxh, this.boxfh, this.dy, this.boxty, this.wins);
                    this.boxtx = this.boxlimit1(this.boxw, this.boxfw, this.dx, this.boxbx, this.winw);
                }
                if (this.br) {
                    this.boxby = this.boxlimit2(this.boxh, this.boxfh, this.dy, this.boxty, this.wins);
                    this.boxbx = this.boxlimit2(this.boxw, this.boxfw, this.dx, this.boxtx, this.wine);
                    break;
                }
                break;
            }
        }
    }
    
    public int boxlimit1(final int n, final int n2, final int n3, final int n4, final int n5) {
        int n6;
        if (n > n2 && n3 > n5) {
            n6 = n3;
        }
        else {
            n6 = n4 - n2;
            if (n3 < n6) {
                n6 = n3;
            }
            if (n3 < n5) {
                n6 = n5;
            }
        }
        return n6;
    }
    
    public int boxlimit2(final int n, final int n2, final int n3, final int n4, final int n5) {
        int n6;
        if (n > n2 && n3 < n5) {
            n6 = n3;
        }
        else {
            n6 = n4 + n2;
            if (n3 > n6) {
                n6 = n3;
            }
            if (n3 > n5) {
                n6 = n5;
            }
        }
        return n6;
    }
    
    public void shadowLine(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        graphics.setColor(new Color(n5, n5, n5));
        graphics.drawLine(n, n2, n3, n2);
        graphics.drawLine(n, n2, n, n4);
        graphics.setColor(new Color(n6, n6, n6));
        graphics.drawLine(n3, n4, n3, n2);
        graphics.drawLine(n3, n4, n, n4);
    }
    
    public int rnd(final int n) {
        return (int)(Math.random() * n) + 1;
    }
    
    public void levelsetup(final int n) {
        this.startwatch = true;
        this.hsplit = false;
        this.vsplit = false;
        this.counting = true;
        this.t1 = false;
        this.t2 = false;
        this.t3 = false;
        this.t4 = false;
        this.boxtx = 100;
        this.boxty = 100;
        this.boxbx = 200;
        this.boxby = 200;
        this.gcy = 300;
        this.gcnow = 0L;
        this.gcstart = 0L;
        this.xp = 0;
        this.yp = 0;
        this.dx = 0;
        this.dy = 0;
        this.boxin = false;
        this.t = false;
        this.b = false;
        this.l = false;
        this.r = false;
        this.tl = false;
        this.tr = false;
        this.bl = false;
        this.br = false;
        for (int i = 0; i < this.maxball; ++i) {
            if (i < n) {
                this.ball[i] = this.ball1;
            }
            else if (i == n) {
                this.ball[i] = this.ball3;
            }
            else {
                this.ball[i] = this.ball2;
            }
        }
        for (int j = 0; j < this.maxball; ++j) {
            if (j > this.maxball / 2) {
                this.ballpos[j][0] = 5 + j % (this.maxball / 2) * 30;
                this.ballpos[j][1] = 210;
            }
            else {
                this.ballpos[j][0] = 5 + j * 30;
                this.ballpos[j][1] = 10;
            }
            this.ballpos[j][2] = 1;
            this.ballpos[j][3] = -1;
        }
        for (int k = 0; k < 2; ++k) {
            this.score[k] = 0;
            this.gccatch[k] = 0;
        }
        if (n == 1) {
            this.speed = 10;
            this.ballSpeed = 2;
            this.maxball = 10;
            this.boxfw = 20;
            this.boxfh = 20;
            this.end = 30000L;
        }
        else if (n == 2) {
            this.speed = 9;
            this.ballSpeed = 3;
            this.maxball = 11;
            this.boxfw = 30;
            this.boxfh = 30;
            this.end = 60000L;
        }
        else if (n == 3) {
            this.speed = 8;
            this.ballSpeed = 4;
            this.maxball = 12;
            this.boxfw = 40;
            this.boxfh = 40;
            this.end = 90000L;
        }
        else if (n == 4) {
            this.speed = 6;
            this.ballSpeed = 5;
            this.maxball = 13;
            this.boxfw = 50;
            this.boxfh = 50;
            this.end = 120000L;
        }
        else if (n == 5) {
            this.speed = 4;
            this.ballSpeed = 6;
            this.maxball = 14;
            this.boxfw = 60;
            this.boxfh = 60;
            this.end = 150000L;
        }
    }
}
