import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.awt.Component;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Graphics;
import java.applet.AudioClip;
import java.awt.MediaTracker;
import java.io.InputStream;
import java.awt.Image;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class pool extends Applet implements Runnable, MouseListener, MouseMotionListener, KeyListener
{
    boolean loaded;
    final short tableARC = 30;
    final short BAGARC = 12;
    final Font smallfont;
    final Dimension screen;
    final Rectangle table;
    final Rectangle area;
    final short numofballs = 15;
    final short R = 9;
    final short D = 18;
    final Rectangle restArea;
    final short heavenstart;
    final short heavenend;
    final Rectangle controlbutton;
    final Point shootcenter;
    final Rectangle powerbutton;
    final Point balloff;
    final Point ballpoint;
    final Point cueballpos;
    final short glinelen;
    final Color BKCOLOR;
    final Color tableCOLOR;
    final Color BCOLOR1;
    final Color BCOLOR2;
    final Color AREACOLOR;
    final double ANGLEI = 0.017453292519943295;
    final short MAXDISTANT = 72;
    final short MAXSPEED = 100;
    final double timeMax = 0.72;
    final double speedMin = 1.3888888888888888;
    final double timeMin = 0.04;
    poolball[] balls;
    cueball cue;
    poolball lowBalls;
    poolball highBalls;
    Image bigCue;
    Image shadeImage;
    byte movingcue;
    double glineangle;
    Point gStart;
    Point gEnd;
    volatile boolean bdrawgl;
    boolean bdrawpower;
    boolean shooting;
    boolean banglechanged;
    boolean ballRunning;
    boolean ongame;
    boolean bstarted;
    boolean bmousepower;
    boolean bi;
    boolean bDraw;
    boolean bDrawAll;
    int timeCounter;
    short shotpower;
    Point shootpos;
    int potentialCollideX;
    int potentialCollideY;
    short heavenpos;
    byte teamColored;
    byte credit;
    byte RballsOnTable;
    byte BballsOnTable;
    byte currentPlayer;
    byte winner;
    final Point Apos;
    final Point Bpos;
    final Font bigFont;
    InputStream iss;
    Sphere sphere;
    Image ii;
    Image tableImage;
    MediaTracker tracker;
    AudioClip hitSound;
    AudioClip dropSound;
    Graphics goff;
    Thread theThread;
    private final int[] ballcolors;
    final int[] BAGS;
    final Color[] powerColors;
    int farStick;
    int stickPos;
    
    public pool() {
        this.smallfont = new Font("Arial", 1, 10);
        this.screen = new Dimension(640, 360);
        this.table = new Rectangle(10, 10, 540, 299);
        this.area = new Rectangle(this.table.x + 19, this.table.y + 24, 500, 250);
        this.restArea = new Rectangle(this.table.x + 100, this.table.y + this.table.height + 12, this.table.width - 200, 22);
        this.heavenstart = (short)(this.restArea.x + 18);
        this.heavenend = (short)(this.restArea.x + this.restArea.width - 18 - 2);
        this.controlbutton = new Rectangle(this.table.x + this.table.width + 10, 10, 60, 28);
        this.shootcenter = new Point(this.controlbutton.x + this.controlbutton.width / 2, this.controlbutton.y + this.controlbutton.height + 50);
        this.powerbutton = new Rectangle(this.controlbutton.x + 10, this.restArea.y + this.restArea.height - 212, 36, 202);
        this.balloff = new Point(this.area.x - 9, this.area.y - 9);
        this.ballpoint = new Point(this.area.width - 100, (this.area.height - 90) / 2 + 9);
        this.cueballpos = new Point(100, this.area.height / 2);
        this.glinelen = (short)this.area.width;
        this.BKCOLOR = new Color(192, 192, 192);
        this.tableCOLOR = new Color(204, 153, 0);
        this.BCOLOR1 = Color.blue;
        this.BCOLOR2 = Color.red;
        this.AREACOLOR = new Color(102, 255, 51);
        this.shootpos = new Point(this.shootcenter.x, this.shootcenter.y);
        this.potentialCollideX = 0;
        this.potentialCollideY = 0;
        this.Apos = new Point(this.table.x + 40, this.table.y + this.table.height + 21);
        this.Bpos = new Point(this.table.x + this.table.width - 40, this.table.y + this.table.height + 21);
        this.bigFont = new Font("Arial", 1, 30);
        this.ballcolors = new int[] { -3093453, -16777063, -3145728, -10079386, -16760768, -16723968, -10092544, -16777216, -6711040, -13421616, -3407872, -3394612, -16744320, -16764160, -6750208 };
        this.BAGS = new int[] { 5, 5, this.area.width - 5, 5, 5, this.area.height - 5, this.area.width - 5, this.area.height - 5, this.area.width / 2, 0, this.area.width / 2, this.area.height };
        this.powerColors = new Color[] { new Color(39168), new Color(10092441), new Color(16763955), new Color(16764108), Color.red };
        this.farStick = 0;
        this.stickPos = 3;
    }
    
    public String getAppletInfo() {
        return "Billiards/pool game - by Fengming Yang, www.freeshopusa.com, version 2.8";
    }
    
    public void init() {
        this.balls = new poolball[16];
        for (int i = 0; i < 15; ++i) {
            this.balls[i] = new poolball();
            this.balls[i].color = this.ballcolors[i];
            this.balls[i].angle = 0;
        }
        this.cue = new cueball();
        for (int j = 0; j < 324; ++j) {
            if (poolball.D18[j] != 0) {
                final int n = j / 18 - 9;
                final int n2 = 165 + (9 - (int)Math.sqrt(n * n + (j % 18 - 9) * (j % 18 - 9))) * 10;
                this.cue.picData[j] = (0xFF000000 | n2 << 16 | n2 << 8 | n2);
            }
        }
        this.balls[15] = this.cue;
        for (int k = 0; k <= 15; ++k) {
            this.balls[k].num = (byte)k;
            (this.balls[k].source = new MemoryImageSource(18, 18, this.balls[k].picData, 0, 18)).setAnimated(true);
            this.balls[k].bd = this.createImage(this.balls[k].source);
        }
        final int[] array = new int[256];
        for (int l = 0; l < 195; ++l) {
            if (poolball.shade[l] == 1) {
                array[l] = -16777216;
            }
            else {
                array[l] = 0;
            }
        }
        this.shadeImage = this.createImage(new MemoryImageSource(13, 15, array, 0, 13));
        this.gStart = new Point();
        this.gEnd = new Point();
        this.resize(this.screen.width, this.screen.height);
        this.ii = this.createImage(this.screen.width, this.screen.height);
        this.goff = this.ii.getGraphics();
        this.bDrawAll = true;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        try {
            this.hitSound = this.getAudioClip(this.getCodeBase(), "hit.au");
            this.dropSound = this.getAudioClip(this.getCodeBase(), "drop.au");
            this.tableImage = this.getImage(this.getDocumentBase(), "table.gif");
            (this.tracker = new MediaTracker(this)).addImage(this.tableImage, 0);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        this.sphere = new Sphere(9.0, 32.0);
        this.iss = null;
        try {
            this.iss = new URL(this.getDocumentBase(), "bd.fsu").openStream();
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        this.gameInit();
        this.loaded = false;
    }
    
    public void destroy() {
        this.getInputContext().removeNotify(this);
        this.removeMouseMotionListener(this);
        this.removeMouseListener(this);
        this.removeKeyListener(this);
    }
    
    public void resetGame() {
        this.ongame = false;
        this.bstarted = false;
        this.gameInit();
        this.drawGround();
    }
    
    private void drawGround() {
        this.goff.setColor(this.BKCOLOR);
        this.goff.fillRect(0, 0, this.screen.width, this.screen.height);
        this.goff.drawImage(this.tableImage, this.table.x, this.table.y, this.table.width, this.table.height, this);
        this.drawPlayer();
        this.drawPowerBar();
        this.goff.setColor(new Color(255, 255, 102));
        this.goff.fillRoundRect(this.controlbutton.x, this.controlbutton.y, this.controlbutton.width, this.controlbutton.height, 20, 20);
        this.goff.setColor(new Color(153, 0, 0));
        this.goff.drawRoundRect(this.controlbutton.x, this.controlbutton.y, this.controlbutton.width, this.controlbutton.height, 20, 20);
        this.goff.setColor(new Color(153, 51, 102));
        this.goff.setFont(this.smallfont);
        String s;
        if (this.bstarted) {
            s = "Reset";
        }
        else {
            s = "Start";
        }
        this.goff.drawString(s, this.controlbutton.x + 16, this.controlbutton.y + 20);
        this.drawBigCue();
        this.goff.setColor(Color.yellow);
        this.goff.drawString("Power", this.powerbutton.x, this.powerbutton.y - 4);
        this.goff.drawString("Cue Ball", this.shootcenter.x - 30, this.shootcenter.y - 30);
    }
    
    private void drawPowerBar() {
        this.goff.setColor(new Color(118, 118, 118));
        this.goff.drawRect(this.powerbutton.x, this.powerbutton.y, this.powerbutton.width, this.powerbutton.height);
        final int n = this.powerbutton.height / 10;
        for (int i = 1; i < 10; ++i) {
            if (i % 2 == 0) {
                this.goff.drawLine(this.powerbutton.x, this.powerbutton.y + i * n, this.powerbutton.x + this.powerbutton.width / 4, this.powerbutton.y + i * n);
            }
            else {
                this.goff.drawLine(this.powerbutton.x, this.powerbutton.y + i * n, this.powerbutton.x + this.powerbutton.width / 8, this.powerbutton.y + i * n);
            }
        }
        final int n2 = this.powerbutton.height / 5;
        final short n3 = (short)(this.shotpower * 2);
        final short n4 = (short)(n3 / n2);
        final short n5 = (short)(n3 % n2);
        this.goff.setColor(this.powerColors[0]);
        switch (n4) {
            case 0: {
                this.goff.fillRect(this.powerbutton.x + this.powerbutton.width / 4 + 1, this.powerbutton.y + this.powerbutton.height - 1 - this.shotpower * 2, this.powerbutton.width / 2 - 1, this.shotpower * 2);
                break;
            }
            case 1: {
                this.goff.fillRect(this.powerbutton.x + this.powerbutton.width / 4 + 1, this.powerbutton.y + this.powerbutton.height - 1 - n2, this.powerbutton.width / 2 - 1, n2);
                this.goff.setColor(this.powerColors[1]);
                this.goff.fillRect(this.powerbutton.x + this.powerbutton.width / 4 + 1, this.powerbutton.y + this.powerbutton.height - 1 - n2 - n5, this.powerbutton.width / 2 - 1, n5);
                break;
            }
            case 2: {
                this.goff.fillRect(this.powerbutton.x + this.powerbutton.width / 4 + 1, this.powerbutton.y + this.powerbutton.height - 1 - n2, this.powerbutton.width / 2 - 1, n2);
                this.goff.setColor(this.powerColors[1]);
                this.goff.fillRect(this.powerbutton.x + this.powerbutton.width / 4 + 1, this.powerbutton.y + this.powerbutton.height - 1 - 2 * n2, this.powerbutton.width / 2 - 1, n2);
                this.goff.setColor(this.powerColors[2]);
                this.goff.fillRect(this.powerbutton.x + this.powerbutton.width / 4 + 1, this.powerbutton.y + this.powerbutton.height - 1 - 2 * n2 - n5, this.powerbutton.width / 2 - 1, n5);
                break;
            }
            case 3: {
                this.goff.fillRect(this.powerbutton.x + this.powerbutton.width / 4 + 1, this.powerbutton.y + this.powerbutton.height - 1 - n2, this.powerbutton.width / 2 - 1, n2);
                this.goff.setColor(this.powerColors[1]);
                this.goff.fillRect(this.powerbutton.x + this.powerbutton.width / 4 + 1, this.powerbutton.y + this.powerbutton.height - 1 - 2 * n2, this.powerbutton.width / 2 - 1, n2);
                this.goff.setColor(this.powerColors[2]);
                this.goff.fillRect(this.powerbutton.x + this.powerbutton.width / 4 + 1, this.powerbutton.y + this.powerbutton.height - 1 - 3 * n2, this.powerbutton.width / 2 - 1, n2);
                this.goff.setColor(this.powerColors[3]);
                this.goff.fillRect(this.powerbutton.x + this.powerbutton.width / 4 + 1, this.powerbutton.y + this.powerbutton.height - 1 - 3 * n2 - n5, this.powerbutton.width / 2 - 1, n5);
                break;
            }
            case 4: {
                this.goff.fillRect(this.powerbutton.x + this.powerbutton.width / 4 + 1, this.powerbutton.y + this.powerbutton.height - 1 - n2, this.powerbutton.width / 2 - 1, n2);
                this.goff.setColor(this.powerColors[1]);
                this.goff.fillRect(this.powerbutton.x + this.powerbutton.width / 4 + 1, this.powerbutton.y + this.powerbutton.height - 1 - 2 * n2, this.powerbutton.width / 2 - 1, n2);
                this.goff.setColor(this.powerColors[2]);
                this.goff.fillRect(this.powerbutton.x + this.powerbutton.width / 4 + 1, this.powerbutton.y + this.powerbutton.height - 1 - 3 * n2, this.powerbutton.width / 2 - 1, n2);
                this.goff.setColor(this.powerColors[3]);
                this.goff.fillRect(this.powerbutton.x + this.powerbutton.width / 4 + 1, this.powerbutton.y + this.powerbutton.height - 1 - 4 * n2, this.powerbutton.width / 2 - 1, n2);
                this.goff.setColor(this.powerColors[4]);
                this.goff.fillRect(this.powerbutton.x + this.powerbutton.width / 4 + 1, this.powerbutton.y + this.powerbutton.height - 1 - 4 * n2 - n5, this.powerbutton.width / 2 - 1, n5);
                break;
            }
            case 5: {
                this.goff.fillRect(this.powerbutton.x + this.powerbutton.width / 4 + 1, this.powerbutton.y + this.powerbutton.height - 1 - n2, this.powerbutton.width / 2 - 1, n2);
                this.goff.setColor(this.powerColors[1]);
                this.goff.fillRect(this.powerbutton.x + this.powerbutton.width / 4 + 1, this.powerbutton.y + this.powerbutton.height - 1 - 2 * n2, this.powerbutton.width / 2 - 1, n2);
                this.goff.setColor(this.powerColors[2]);
                this.goff.fillRect(this.powerbutton.x + this.powerbutton.width / 4 + 1, this.powerbutton.y + this.powerbutton.height - 1 - 3 * n2, this.powerbutton.width / 2 - 1, n2);
                this.goff.setColor(this.powerColors[3]);
                this.goff.fillRect(this.powerbutton.x + this.powerbutton.width / 4 + 1, this.powerbutton.y + this.powerbutton.height - 1 - 4 * n2, this.powerbutton.width / 2 - 1, n2);
                this.goff.setColor(this.powerColors[4]);
                this.goff.fillRect(this.powerbutton.x + this.powerbutton.width / 4 + 1, this.powerbutton.y + this.powerbutton.height - 1 - 5 * n2, this.powerbutton.width / 2 - 1, n2);
                break;
            }
        }
    }
    
    public void startGame() {
        this.ongame = true;
        this.bstarted = true;
        this.bdrawgl = true;
        this.currentPlayer = 1;
        this.bDrawAll = true;
    }
    
    public void prepareData(final int n) {
        final byte[] array = new byte[322];
        final byte[] array2 = new byte[1280];
        this.balls[n].allocateMem();
        int n2;
        if (n < 8) {
            n2 = 128;
        }
        else {
            n2 = 320;
        }
        try {
            this.iss.read(array, 0, n2);
        }
        catch (IOException ex) {
            return;
        }
        for (int i = 0; i < n2; ++i) {
            byte b = array[i];
            for (int j = 0; j < 4; ++j) {
                byte b2 = (byte)((byte)(b & 0xFFFFFFC0) >> 6);
                if (b2 == -2) {
                    b2 = 2;
                }
                array2[i * 4 + j] = b2;
                b <<= 2;
            }
        }
        this.sphere.produceData(array2, n);
        for (int k = 0; k < 11664; ++k) {
            this.balls[n].A0data[k] = this.sphere.A0data[k];
            this.balls[n].A45data[k] = this.sphere.A45data[k];
            this.balls[n].A90data[k] = this.sphere.A90data[k];
            this.balls[n].A135data[k] = this.sphere.A135data[k];
        }
        if (n == 14) {
            try {
                if (this.iss != null) {
                    this.iss.close();
                }
            }
            catch (Exception ex2) {}
        }
    }
    
    public void gameInit() {
        final byte[] array = new byte[15];
        this.shotpower = 60;
        this.credit = 0;
        this.teamColored = 0;
        final boolean b = false;
        this.bdrawpower = b;
        this.bdrawgl = b;
        this.bstarted = b;
        this.ongame = b;
        this.shooting = false;
        this.bi = true;
        this.heavenpos = this.heavenend;
        final byte b2 = 7;
        this.BballsOnTable = b2;
        this.RballsOnTable = b2;
        this.currentPlayer = 0;
        this.movingcue = 10;
        for (int i = 0; i < 15; ++i) {
            array[i] = 0;
        }
        this.cue.x = 1.0 * this.cueballpos.x;
        this.cue.y = 1.0 * this.cueballpos.y;
        this.cue.spinning = false;
        int n2;
        int n = n2 = 5;
        double x = this.ballpoint.x;
        double y;
        double n3 = y = this.ballpoint.y;
        int j = 0;
        while (j < 15) {
            int n4 = (int)(Math.random() * 15.0);
            if (n4 == 15) {
                --n4;
            }
            if (array[n4] == 0) {
                array[n4] = 1;
                ++j;
                this.balls[n4].x = x;
                this.balls[n4].y = y;
                y += 18.0;
                if (--n2 != 0) {
                    continue;
                }
                n3 = (y = n3 + 9.0);
                n = (n2 = n - 1);
                x -= 18.0;
            }
        }
        double x2;
        double y2;
        int n5;
        for (x2 = this.ballpoint.x - 36.0, y2 = this.ballpoint.y + 36.0, n5 = 0; n5 < 15 && (this.balls[n5].x != x2 || this.balls[n5].y != y2); ++n5) {}
        if (n5 < 15) {
            this.balls[n5].x = this.balls[7].x;
            this.balls[n5].y = this.balls[7].y;
            this.balls[7].x = x2;
            this.balls[7].y = y2;
        }
        for (int k = 0; k <= 15; ++k) {
            this.balls[k].vx = 0.0;
            this.balls[k].vy = 0.0;
            this.balls[k].state = 1;
            this.balls[k].collided = 0;
            this.balls[k].moved = true;
        }
        this.glineangle = 0.0;
        this.banglechanged = true;
    }
    
    private synchronized void drawPic() {
        if (!this.bDraw && !this.bDrawAll) {
            return;
        }
        if (this.bDraw && this.bdrawgl) {
            this.bDrawAll = true;
        }
        if (this.bDrawAll) {
            this.drawGround();
        }
        else {
            this.goff.drawImage(this.tableImage, this.table.x, this.table.y, this.table.width, this.table.height, this);
        }
        this.goff.setColor(new Color(118, 118, 118));
        this.goff.fillRoundRect(this.restArea.x, this.restArea.y, this.restArea.width, this.restArea.height, 30, 30);
        this.goff.setColor(Color.black);
        this.goff.fillOval(this.restArea.x + 4, this.restArea.y + 2, 18, 18);
        this.drawBalls();
        if (this.bdrawgl) {
            if (this.bstarted && !this.ongame) {
                this.showWinner();
            }
            else {
                this.drawGuideline();
            }
        }
        this.repaint();
        this.bDrawAll = false;
        this.bDraw = false;
    }
    
    private void drawBalls() {
        this.drawTableBalls();
        this.drawRestBalls();
    }
    
    private synchronized void drawTableBalls() {
        for (int i = 0; i <= 15; ++i) {
            if (this.balls[i].state < 4) {
                this.goff.drawImage(this.shadeImage, (int)(this.balls[i].x + this.balloff.x + 8.0), (int)(this.balls[i].y + this.balloff.y + 6.0), 15, 13, this);
            }
        }
        for (int j = 0; j < 15; ++j) {
            if (this.balls[j].state < 4) {
                if (!this.bdrawgl && this.balls[j].moved) {
                    this.balls[j].moved = false;
                    for (int k = 0; k < 324; ++k) {
                        if (poolball.D18[k] != 0) {
                            switch (this.balls[j].currentDirection[this.balls[j].rotate * 18 * 18 + k]) {
                                case 1: {
                                    this.balls[j].picData[k] = -3092272;
                                    break;
                                }
                                case 0: {
                                    this.balls[j].picData[k] = -16777216;
                                    break;
                                }
                                case 2:
                                case 3: {
                                    this.balls[j].picData[k] = this.balls[j].color;
                                    break;
                                }
                                default: {
                                    System.out.println("Error data happened");
                                    break;
                                }
                            }
                            final byte b = poolball.light18[k];
                            final int n = b << 16 | b << 8 | b;
                            final int[] picData = this.balls[j].picData;
                            final int n2 = k;
                            picData[n2] += n;
                        }
                    }
                    this.balls[j].source.newPixels();
                }
                this.goff.drawImage(this.balls[j].bd, (int)(this.balls[j].x + 0.5 + this.balloff.x), (int)(this.balls[j].y + 0.5 + this.balloff.y), 18, 18, this);
                if (this.balls[j].state >= 2) {
                    final poolball poolball = this.balls[j];
                    ++poolball.state;
                }
                if (this.balls[j].state == 4) {
                    this.balls[j].x = this.heavenstart;
                }
            }
        }
        if (this.cue.state < 4) {
            this.goff.drawImage(this.cue.bd, (int)(this.cue.x + 0.5 + this.balloff.x), (int)(this.cue.y + 0.5 + this.balloff.y), 18, 18, this);
            if (this.cue.state >= 2) {
                final cueball cue = this.cue;
                ++cue.state;
            }
            if (this.cue.state == 4) {
                this.cue.x = this.heavenstart;
            }
        }
    }
    
    private void drawRestBalls() {
        for (int i = 0; i < 15; ++i) {
            if (this.balls[i].state >= 4) {
                if (this.balls[i].moved) {
                    this.balls[i].moved = false;
                    for (int j = 0; j < 324; ++j) {
                        if (poolball.D18[j] != 0) {
                            switch (this.balls[i].currentDirection[this.balls[i].rotate * 18 * 18 + j]) {
                                case 1: {
                                    this.balls[i].picData[j] = -3092272;
                                    break;
                                }
                                case 0: {
                                    this.balls[i].picData[j] = -16777216;
                                    break;
                                }
                                case 2: {
                                    this.balls[i].picData[j] = this.balls[i].color;
                                    break;
                                }
                                case 3: {
                                    this.balls[i].picData[j] = this.balls[i].color;
                                    break;
                                }
                                default: {
                                    System.out.println("Error data happened");
                                    break;
                                }
                            }
                            final byte b = poolball.light18[j];
                            final int n = b << 16 | b << 8 | b;
                            final int[] picData = this.balls[i].picData;
                            final int n2 = j;
                            picData[n2] += n;
                        }
                    }
                    this.balls[i].source.newPixels();
                }
                this.goff.drawImage(this.balls[i].bd, (int)this.balls[i].x, this.restArea.y + 3, 18, 18, this);
            }
        }
        if (this.cue.state >= 4) {
            this.goff.drawImage(this.cue.bd, (int)this.cue.x, this.restArea.y + 3, 18, 18, this);
        }
    }
    
    private void drawBigCue() {
        this.goff.drawImage(this.bigCue, this.shootcenter.x - 25, this.shootcenter.y - 25, 50, 50, this);
        this.goff.setColor(Color.gray);
        this.goff.drawOval(this.shootcenter.x - 15, this.shootcenter.y - 15, 30, 30);
        this.goff.setColor(Color.blue);
        this.goff.drawLine(this.shootpos.x - 2, this.shootpos.y, this.shootpos.x + 2, this.shootpos.y);
        this.goff.drawLine(this.shootpos.x, this.shootpos.y - 2, this.shootpos.x, this.shootpos.y + 2);
    }
    
    private void drawPlayer() {
        final Color color = new Color(128, 0, 0);
        this.goff.setColor(Color.white);
        if (this.bstarted) {
            if (this.currentPlayer == 1) {
                this.goff.fillRect(this.Apos.x + 20, this.Apos.y + 15, 10, 2);
                this.goff.setColor(color);
                this.goff.fillRect(this.Apos.x - 20, this.Apos.y + 14, 40, 4);
            }
            else {
                this.goff.fillRect(this.Bpos.x + 20, this.Bpos.y + 15, 10, 2);
                this.goff.setColor(color);
                this.goff.fillRect(this.Bpos.x - 20, this.Bpos.y + 14, 40, 4);
            }
        }
        if (this.teamColored == 0) {
            this.goff.setColor(Color.darkGray);
            this.goff.drawImage(this.cue.bd, this.Apos.x - 10, this.Apos.y - 10, 18, 18, this);
            this.goff.drawImage(this.cue.bd, this.Bpos.x - 10, this.Bpos.y - 10, 18, 18, this);
        }
        else if (this.teamColored == 1) {
            this.goff.drawImage(this.lowBalls.bd, this.Apos.x - 10, this.Apos.y - 10, 18, 18, this);
            this.goff.drawImage(this.highBalls.bd, this.Bpos.x - 10, this.Bpos.y - 10, 18, 18, this);
        }
        else {
            this.goff.drawImage(this.lowBalls.bd, this.Bpos.x - 10, this.Bpos.y - 10, 18, 18, this);
            this.goff.drawImage(this.highBalls.bd, this.Apos.x - 10, this.Apos.y - 10, 18, 18, this);
        }
    }
    
    private void drawStick() {
        if (this.shooting) {
            if (this.farStick % 5 == 0) {
                if (this.stickPos == 20) {
                    this.stickPos = 3;
                }
                else {
                    this.stickPos = 20;
                }
            }
            ++this.farStick;
        }
        else {
            this.stickPos = 3;
        }
        final double n = this.glineangle - 1.5707963267948966;
        final int[] array = new int[4];
        final int[] array2 = new int[4];
        final double n2 = (9 + this.stickPos) * Math.cos(this.glineangle);
        final double n3 = -(9 + this.stickPos) * Math.sin(this.glineangle);
        final double n4 = this.cue.x - n2;
        final double n5 = this.cue.y - n3;
        final double n6 = 2.0 * Math.cos(n);
        final double n7 = -2.0 * Math.sin(n);
        array[0] = (int)(n4 + n6);
        array2[0] = (int)(n5 + n7);
        array[1] = (int)(n4 - n6);
        array2[1] = (int)(n5 - n7);
        final double n8 = n4 - 8.0 * Math.cos(this.glineangle);
        final double n9 = n5 + 8.0 * Math.sin(this.glineangle);
        array[2] = (int)(n8 - n6);
        array2[2] = (int)(n9 - n7);
        array[3] = (int)(n8 + n6);
        array2[3] = (int)(n9 + n7);
        for (int i = 0; i < 4; ++i) {
            final int[] array3 = array;
            final int n10 = i;
            array3[n10] += this.area.x;
            final int[] array4 = array2;
            final int n11 = i;
            array4[n11] += this.area.y;
        }
        this.goff.setColor(Color.white);
        this.goff.fillPolygon(array, array2, 4);
        array[0] = array[2];
        array2[0] = array2[2];
        array[1] = array[3];
        array2[1] = array2[3];
        final double n12 = n8 - 120.0 * Math.cos(this.glineangle);
        final double n13 = n9 + 120.0 * Math.sin(this.glineangle);
        final double n14 = 3.0 * Math.cos(n);
        final double n15 = -3.0 * Math.sin(n);
        array[2] = (int)(n12 + n14);
        array2[2] = (int)(n13 + n15);
        array[3] = (int)(n12 - n14);
        array2[3] = (int)(n13 - n15);
        for (int j = 2; j < 4; ++j) {
            final int[] array5 = array;
            final int n16 = j;
            array5[n16] += this.area.x;
            final int[] array6 = array2;
            final int n17 = j;
            array6[n17] += this.area.y;
        }
        this.goff.setColor(new Color(128, 0, 0));
        this.goff.fillPolygon(array, array2, 4);
    }
    
    private double potentialCollision(final int n, final int n2, final poolball poolball) {
        final double n3 = n - poolball.x;
        final double n4 = 50.0 * Math.cos(this.glineangle);
        final double n5 = n2 - poolball.y;
        final double n6 = -50.0 * Math.sin(this.glineangle);
        final double n7 = n4 * n4 + n6 * n6;
        if (n7 <= 1.0E-4) {
            return -1.0;
        }
        final double n8 = n3 * n3 + n5 * n5 - 324.0;
        final double n9 = 2.0 * (n3 * n4 + n5 * n6);
        final double n10 = n9 * n9 - 4.0 * n7 * n8;
        if (n10 < 0.0) {
            return -2.0;
        }
        final double sqrt = Math.sqrt(n10);
        final double n11 = (-n9 + sqrt) / (2.0 * n7);
        final double n12 = (-n9 - sqrt) / (2.0 * n7);
        if (Math.abs(n11 - n12) < 1.0E-10) {
            return -3.0;
        }
        final double min = Math.min(n11, n12);
        if (min >= 0.0) {
            return min;
        }
        if (n8 >= 0.0) {
            return -6.0;
        }
        if (n11 + n12 > 0.04) {
            return 0.0;
        }
        return -4.0;
    }
    
    private void drawGuideline() {
        this.drawStick();
        if (this.banglechanged) {
            int n = 100;
            final int n2 = (int)this.cue.x;
            final int n3 = (int)this.cue.y;
            double n4 = 1000.0;
            for (int i = 0; i < 15; ++i) {
                if (this.balls[i].state == 1) {
                    final double potentialCollision = this.potentialCollision(n2, n3, this.balls[i]);
                    if (potentialCollision >= 0.0) {
                        if (potentialCollision < n4) {
                            n4 = potentialCollision;
                            n = i;
                        }
                    }
                }
            }
            if (n < 100) {
                this.potentialCollideX = (int)(this.cue.x + 50.0 * Math.cos(this.glineangle) * n4 + 0.5);
                this.potentialCollideY = (int)(this.cue.y - 50.0 * Math.sin(this.glineangle) * n4 + 0.5);
                final double atan2 = Math.atan2((int)this.balls[n].y - this.potentialCollideY, (int)this.balls[n].x - this.potentialCollideX);
                final int abs = Math.abs((int)(11.0 * Math.cos(atan2)));
                final int abs2 = Math.abs((int)(11.0 * Math.sin(atan2)));
                if (this.balls[n].x > this.potentialCollideX) {
                    this.gStart.x = abs + (int)this.balls[n].x;
                }
                else {
                    this.gStart.x = (int)this.balls[n].x - abs;
                }
                if (this.balls[n].y > this.potentialCollideY) {
                    this.gStart.y = abs2 + (int)this.balls[n].y;
                }
                else {
                    this.gStart.y = (int)this.balls[n].y - abs2;
                }
                final int abs3 = Math.abs((int)(17.0 * Math.cos(atan2)));
                final int abs4 = Math.abs((int)(17.0 * Math.sin(atan2)));
                if (this.balls[n].x > this.potentialCollideX) {
                    this.gEnd.x = abs3 + (int)this.balls[n].x;
                }
                else {
                    this.gEnd.x = (int)this.balls[n].x - abs3;
                }
                if (this.balls[n].y > this.potentialCollideY) {
                    this.gEnd.y = abs4 + (int)this.balls[n].y;
                }
                else {
                    this.gEnd.y = (int)this.balls[n].y - abs4;
                }
            }
            else {
                this.potentialCollideX = 0;
                this.potentialCollideY = 0;
            }
        }
        if (this.potentialCollideX != 0) {
            this.goff.setColor(Color.pink);
            this.goff.drawOval(this.potentialCollideX + this.balloff.x, this.potentialCollideY + this.balloff.y, 18, 18);
            this.goff.drawLine(this.balloff.x + 9 + this.gStart.x, this.balloff.y + 9 + this.gStart.y, this.balloff.x + 9 + this.gEnd.x, this.balloff.y + 9 + this.gEnd.y);
        }
        this.banglechanged = false;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public synchronized void paint(final Graphics graphics) {
        graphics.drawImage(this.ii, 0, 0, this);
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        final int keyCode = keyEvent.getKeyCode();
        this.bDrawAll = true;
        Label_0756: {
            switch (keyCode) {
                case 37:
                case 52: {
                    if (!this.bdrawgl) {
                        break;
                    }
                    if (this.movingcue != 20) {
                        this.glineangle += 0.017453292519943295;
                        this.banglechanged = true;
                        break;
                    }
                    if (keyEvent.isControlDown()) {
                        this.placeCue((int)this.cue.x - 1, (int)this.cue.y);
                        break;
                    }
                    break;
                }
                case 39:
                case 54: {
                    if (!this.bdrawgl) {
                        break;
                    }
                    if (this.movingcue != 20) {
                        this.glineangle -= 0.017453292519943295;
                        this.banglechanged = true;
                        break;
                    }
                    if (keyEvent.isControlDown()) {
                        this.placeCue((int)this.cue.x + 1, (int)this.cue.y);
                        break;
                    }
                    break;
                }
                case 38:
                case 56: {
                    if (!this.bdrawgl) {
                        break;
                    }
                    switch (this.movingcue) {
                        case 20: {
                            if (keyEvent.isControlDown()) {
                                this.placeCue((int)this.cue.x, (int)this.cue.y - 2);
                                break Label_0756;
                            }
                            if (keyEvent.isShiftDown()) {
                                this.placeCue(this.cueballpos.x, (int)this.cue.y - 2);
                                break Label_0756;
                            }
                            this.glineangle += 0.017453292519943295;
                            this.banglechanged = true;
                            break Label_0756;
                        }
                        case 10: {
                            if (keyEvent.isShiftDown()) {
                                this.placeCue(this.cueballpos.x, (int)this.cue.y - 2);
                                break Label_0756;
                            }
                            this.glineangle += 0.017453292519943295;
                            this.banglechanged = true;
                            break Label_0756;
                        }
                        default: {
                            this.glineangle += 0.017453292519943295;
                            this.banglechanged = true;
                            break Label_0756;
                        }
                    }
                    break;
                }
                case 40:
                case 50:
                case 53: {
                    if (!this.bdrawgl) {
                        break;
                    }
                    switch (this.movingcue) {
                        case 20: {
                            if (keyEvent.isControlDown()) {
                                this.placeCue((int)this.cue.x, (int)this.cue.y + 2);
                                break Label_0756;
                            }
                            if (keyEvent.isShiftDown()) {
                                this.placeCue(this.cueballpos.x, (int)this.cue.y + 2);
                                break Label_0756;
                            }
                            this.glineangle -= 0.017453292519943295;
                            this.banglechanged = true;
                            break Label_0756;
                        }
                        case 10: {
                            if (keyEvent.isShiftDown()) {
                                this.placeCue(this.cueballpos.x, (int)this.cue.y + 2);
                                break Label_0756;
                            }
                            this.glineangle -= 0.017453292519943295;
                            this.banglechanged = true;
                            break Label_0756;
                        }
                        default: {
                            this.glineangle -= 0.017453292519943295;
                            this.banglechanged = true;
                            break Label_0756;
                        }
                    }
                    break;
                }
                case 34: {
                    if (this.bdrawgl) {
                        this.glineangle -= 0.3490658503988659;
                        this.banglechanged = true;
                        break;
                    }
                    break;
                }
                case 33: {
                    if (this.bdrawgl) {
                        this.glineangle += 0.3490658503988659;
                        this.banglechanged = true;
                        break;
                    }
                    break;
                }
                case 27:
                case 82:
                case 119: {
                    this.resetGame();
                    break;
                }
                case 83:
                case 118: {
                    this.startGame();
                    break;
                }
                case 32: {
                    if (this.bdrawgl) {
                        this.controlPower();
                        break;
                    }
                    break;
                }
            }
        }
        if (this.glineangle > 6.283185307179586) {
            this.glineangle -= 6.283185307179586;
        }
        if (this.glineangle < -6.283185307179586) {
            this.glineangle += 6.283185307179586;
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 32: {
                if (this.bdrawgl) {
                    this.shoot();
                    break;
                }
                break;
            }
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public synchronized void mousePressed(final MouseEvent mouseEvent) {
        if (mouseEvent.isMetaDown()) {
            return;
        }
        this.bDrawAll = true;
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (this.area.contains(x, y)) {
            if (this.movingcue > 0) {
                final int n = (int)this.cue.x - (x - this.area.x);
                if (n * n + ((int)this.cue.y - (y - this.area.y)) * ((int)this.cue.y - (y - this.area.y)) < 81) {
                    if (this.movingcue >= 20) {
                        if (mouseEvent.isControlDown()) {
                            this.movingcue = 22;
                        }
                        else {
                            this.movingcue = 21;
                        }
                    }
                    else {
                        this.movingcue = 11;
                    }
                }
                else {
                    this.mouseAim(x, y);
                }
            }
            else {
                this.mouseAim(x, y);
            }
        }
        else if (this.powerbutton.contains(x, y)) {
            if (this.bdrawgl) {
                this.bmousepower = true;
            }
        }
        else if (this.controlbutton.contains(x, y)) {
            if (this.bstarted) {
                this.resetGame();
            }
            else {
                this.startGame();
            }
        }
        else if (this.bdrawgl) {
            final int n2 = this.shootcenter.x - x;
            if (n2 * n2 + (this.shootcenter.y - y) * (this.shootcenter.y - y) < 225) {
                this.shootpos.x = x;
                this.shootpos.y = y;
            }
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public synchronized void mouseDragged(final MouseEvent mouseEvent) {
        if (mouseEvent.isMetaDown()) {
            return;
        }
        this.bDrawAll = true;
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (this.area.contains(x, y)) {
            if (this.movingcue > 0) {
                switch (this.movingcue) {
                    case 22: {
                        if (mouseEvent.isControlDown()) {
                            this.placeCue(x - this.area.x, y - this.area.y);
                            break;
                        }
                        this.movingcue = 21;
                        break;
                    }
                    case 21: {
                        if (!mouseEvent.isControlDown()) {
                            this.placeCue(this.cueballpos.x, y - this.area.y);
                            break;
                        }
                        this.movingcue = 22;
                        break;
                    }
                    case 11: {
                        this.placeCue(this.cueballpos.x, y - this.area.y);
                        break;
                    }
                    default: {
                        this.mouseAim(x, y);
                        break;
                    }
                }
            }
            else {
                this.mouseAim(x, y);
            }
        }
        else if (this.bdrawgl) {
            final int n = this.shootcenter.x - x;
            if (n * n + (this.shootcenter.y - y) * (this.shootcenter.y - y) < 225) {
                this.shootpos.x = x;
                this.shootpos.y = y;
            }
        }
    }
    
    private void mouseAim(final int n, final int n2) {
        if (!this.bdrawgl) {
            return;
        }
        this.glineangle = Math.atan2(-(n2 - this.area.y - this.cue.y), n - this.area.x - this.cue.x);
        this.banglechanged = true;
    }
    
    private void controlPower() {
        if (this.bi) {
            this.shotpower += 4;
        }
        else {
            this.shotpower -= 4;
        }
        if (this.shotpower > 100) {
            this.bi = false;
            this.shotpower = 100;
        }
        if (this.shotpower <= 4) {
            this.bi = true;
            this.shotpower = 4;
        }
        this.bdrawpower = true;
        this.shooting = true;
    }
    
    public synchronized void mouseReleased(final MouseEvent mouseEvent) {
        if (mouseEvent.isMetaDown()) {
            return;
        }
        this.bDrawAll = true;
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.bmousepower = false;
        if (this.movingcue == 22 || this.movingcue == 21) {
            this.movingcue = 20;
            this.bdrawgl = true;
            this.banglechanged = true;
        }
        else if (this.movingcue == 11) {
            this.movingcue = 10;
            this.bdrawgl = true;
            this.banglechanged = true;
        }
        if (this.powerbutton.contains(x, y) && this.bdrawgl) {
            this.shoot();
        }
    }
    
    private void shoot() {
        if (this.shotpower <= 1) {
            return;
        }
        this.movingcue = 0;
        this.bdrawpower = false;
        this.bdrawgl = false;
        this.ballRunning = true;
        this.credit = 0;
        this.cue.vx = 1.0 * this.shotpower * Math.cos(this.glineangle);
        this.cue.vy = -1.0 * this.shotpower * Math.sin(this.glineangle);
        final double n = 1.5707963267948966 + this.glineangle - Math.atan2(this.shootpos.y - this.shootcenter.y, this.shootpos.x - this.shootcenter.x);
        this.cue.svx = -this.shotpower * 0.4 * Math.cos(n);
        this.cue.svy = this.shotpower * 0.4 * Math.sin(n);
        final double n2 = this.shootpos.y - this.shootcenter.y;
        final double sqrt = Math.sqrt(n2 * n2 + (this.shootpos.x - this.shootcenter.x) * (this.shootpos.x - this.shootcenter.x));
        if (sqrt > 1.0) {
            this.cue.spinning = true;
        }
        final double n3 = sqrt / 15.0;
        final cueball cue = this.cue;
        cue.svx *= n3;
        final cueball cue2 = this.cue;
        cue2.svy *= n3;
        this.bDrawAll = true;
        this.shooting = false;
    }
    
    private void placeCue(int n, int n2) {
        int n4;
        int n3 = n4 = 1;
        int n5 = 0;
        do {
            int i;
            for (i = 0; i < 15; ++i) {
                final int n6 = (int)this.balls[i].x - n;
                final int n7 = n6 * n6;
                final int n8 = (int)this.balls[i].y - n2;
                if (n8 * n8 + n7 < 324) {
                    break;
                }
            }
            if (i == 15 && n >= 9 && n < this.area.width - 9 && n2 >= 9 && n2 < this.area.height - 9) {
                this.cue.x = n;
                this.cue.y = n2;
                return;
            }
            if (this.movingcue == 11 || this.movingcue == 21 || this.movingcue == 22) {
                if (n4 != 0) {
                    if (++n2 > this.area.height - 9 - 1) {
                        n4 = 0;
                        n2 -= 2;
                    }
                }
                else if (--n2 < 9) {
                    n4 = 1;
                    n2 += 2;
                }
            }
            if (this.movingcue != 22) {
                continue;
            }
            if (n3 != 0) {
                if (++n <= this.area.width - 9 - 1) {
                    continue;
                }
                n3 = 0;
                n -= 2;
            }
            else {
                if (--n >= 9) {
                    continue;
                }
                n3 = 1;
                n += 2;
            }
        } while (++n5 < 200);
    }
    
    private void fullCollision(final poolball poolball) {
        this.collision(this.cue, poolball);
        final cueball cue = this.cue;
        cue.vx += this.cue.svx;
        final cueball cue2 = this.cue;
        cue2.vy += this.cue.svy;
        this.cue.svx = 0.0;
        this.cue.svy = 0.0;
        this.cue.spinning = false;
    }
    
    private void collision(final poolball poolball, final poolball poolball2) {
        final double atan2 = Math.atan2(poolball.vy, poolball.vx);
        final double atan3 = Math.atan2(poolball2.vy, poolball2.vx);
        final double n = poolball2.x - poolball.x;
        final double n2 = poolball2.y - poolball.y;
        double atan4;
        if (n == 0.0) {
            if (n2 > 0.0) {
                atan4 = 1.5707963267948966;
            }
            else if (n2 < 0.0) {
                atan4 = -1.5707963267948966;
            }
            else {
                atan4 = 0.0;
            }
        }
        else {
            atan4 = Math.atan(n2 / n);
        }
        final double n3 = atan2 - atan4;
        final double n4 = atan3 - atan4;
        final double sqrt = Math.sqrt(poolball.vx * poolball.vx + poolball.vy * poolball.vy);
        final double sqrt2 = Math.sqrt(poolball2.vx * poolball2.vx + poolball2.vy * poolball2.vy);
        final double n5 = sqrt2 * Math.cos(n4);
        final double n6 = sqrt * Math.sin(n3);
        final double sqrt3 = Math.sqrt(n5 * n5 + n6 * n6);
        final double n7 = Math.atan2(n6, n5) + atan4;
        poolball.vx = sqrt3 * Math.cos(n7);
        poolball.vy = sqrt3 * Math.sin(n7);
        final double n8 = sqrt2 * Math.sin(n4);
        final double n9 = sqrt * Math.cos(n3);
        final double sqrt4 = Math.sqrt(n8 * n8 + n9 * n9);
        final double n10 = Math.atan2(n8, n9) + atan4;
        poolball2.vx = sqrt4 * Math.cos(n10);
        poolball2.vy = sqrt4 * Math.sin(n10);
    }
    
    public double dist(final poolball poolball, final poolball poolball2) {
        final double n = poolball.x - poolball2.x;
        final double n2 = n * n;
        final double n3 = poolball.y - poolball2.y;
        return Math.sqrt(n2 + n3 * n3);
    }
    
    public double detectCollision(final poolball poolball, final poolball poolball2) {
        final double n = poolball.x - poolball2.x;
        final double n2 = poolball.vx - poolball2.vx;
        final double n3 = poolball.y - poolball2.y;
        final double n4 = poolball.vy - poolball2.vy;
        final double n5 = n2 * n2 + n4 * n4;
        if (n5 <= 1.0E-4) {
            return -1.0;
        }
        final double n6 = n * n + n3 * n3 - 324.0;
        final double n7 = 2.0 * (n * n2 + n3 * n4);
        final double n8 = n7 * n7 - 4.0 * n5 * n6;
        if (n8 < 0.0) {
            return -2.0;
        }
        final double sqrt = Math.sqrt(n8);
        final double n9 = (-n7 + sqrt) / (2.0 * n5);
        final double n10 = (-n7 - sqrt) / (2.0 * n5);
        if (Math.abs(n9 - n10) < 1.0E-10) {
            return -3.0;
        }
        final double min = Math.min(n9, n10);
        if (min < 0.0) {
            if (n6 >= 0.0) {
                return -6.0;
            }
            if (n9 + n10 > 0.04) {
                return 0.0;
            }
            return -4.0;
        }
        else {
            if (min > 0.72) {
                return -5.0;
            }
            return min;
        }
    }
    
    public void decidePosition() {
        int n = 0;
        for (int i = 0; i <= 15; ++i) {
            this.balls[i].collided = 0;
        }
        boolean b;
        do {
            b = false;
            for (int j = 15; j >= 0; --j) {
                if (this.balls[j].state == 1) {
                    final double abs = Math.abs(this.balls[j].vx);
                    final double abs2 = Math.abs(this.balls[j].vy);
                    if (abs >= 1.2888888888888888 || abs2 >= 1.2888888888888888) {
                        int n3;
                        int n2 = n3 = 100;
                        double n4 = 100.0;
                        double n5 = 100.0;
                        for (int k = 15; k >= 0; --k) {
                            if (this.balls[k].state == 1) {
                                if (k != j) {
                                    final double detectCollision = this.detectCollision(this.balls[j], this.balls[k]);
                                    if (detectCollision >= 0.0) {
                                        b = true;
                                        if (n4 > detectCollision) {
                                            n4 = detectCollision;
                                            n3 = k;
                                        }
                                        else if (Math.abs(n4 - detectCollision) < 1.0E-8) {
                                            n5 = detectCollision;
                                            n2 = k;
                                        }
                                    }
                                }
                            }
                        }
                        if (n3 < 100) {
                            if (n == 0) {
                                this.hitSound.play();
                                n = 1;
                            }
                            if (this.balls[j].collided == 0) {
                                this.moveForward(this.balls[j], n4);
                            }
                            if (this.balls[n3].collided == 0) {
                                this.moveForward(this.balls[n3], n4);
                            }
                            if (Math.abs(n4 - n5) < 1.0E-8 && n2 < 90) {
                                if (this.balls[n2].collided == 0) {
                                    this.moveForward(this.balls[n2], n4);
                                }
                                final double vx = this.balls[j].vx;
                                final double vy = this.balls[j].vy;
                                if (j == 15 && this.cue.spinning) {
                                    this.fullCollision(this.balls[n3]);
                                }
                                else {
                                    this.collision(this.balls[j], this.balls[n3]);
                                }
                                final poolball poolball = this.balls[n3];
                                poolball.vx /= 2.0;
                                final poolball poolball2 = this.balls[n3];
                                poolball2.vy /= 2.0;
                                final poolball poolball3 = this.balls[n3];
                                ++poolball3.collided;
                                final double vx2 = this.balls[j].vx;
                                final double vy2 = this.balls[j].vy;
                                this.balls[j].vx = vx;
                                this.balls[j].vy = vy;
                                if (j == 15 && this.cue.spinning) {
                                    this.fullCollision(this.balls[n2]);
                                }
                                else {
                                    this.collision(this.balls[j], this.balls[n2]);
                                }
                                final poolball poolball4 = this.balls[n2];
                                poolball4.vx /= 2.0;
                                final poolball poolball5 = this.balls[n2];
                                poolball5.vy /= 2.0;
                                final poolball poolball6 = this.balls[n2];
                                ++poolball6.collided;
                                this.balls[j].vx = (this.balls[j].vx + vx2) / 4.0;
                                this.balls[j].vy = (this.balls[j].vy + vy2) / 4.0;
                                this.decideDrawDirection(this.balls[n3]);
                                this.decideDrawDirection(this.balls[j]);
                                this.decideDrawDirection(this.balls[n2]);
                            }
                            else {
                                if (j == 15 && this.cue.spinning) {
                                    this.fullCollision(this.balls[n3]);
                                }
                                else {
                                    this.collision(this.balls[j], this.balls[n3]);
                                }
                                final poolball poolball7 = this.balls[n3];
                                ++poolball7.collided;
                                this.decideDrawDirection(this.balls[n3]);
                                this.decideDrawDirection(this.balls[j]);
                            }
                        }
                        else if (this.balls[j].collided == 0) {
                            this.moveForward(this.balls[j], 0.72);
                        }
                        final poolball poolball8 = this.balls[j];
                        ++poolball8.collided;
                    }
                }
            }
        } while (b);
        this.wearSpeed();
    }
    
    private void moveForward(final poolball poolball, final double n) {
        if (n <= 0.0) {
            return;
        }
        poolball.rotate = (poolball.rotate + 1) % 36;
        poolball.moved = true;
        double min = n;
        double n2 = n;
        boolean b = false;
        boolean b2 = false;
        final double x = poolball.x + poolball.vx * n;
        if (x < 9.0) {
            min = (9.0 - poolball.x) / poolball.vx;
            b = true;
        }
        else if (x > this.area.width - 9 - 1.0) {
            min = (this.area.width - 9 - 1.0 - poolball.x) / poolball.vx;
            b = true;
        }
        final double y = poolball.y + poolball.vy * n;
        if (y < 9.0) {
            b2 = true;
            n2 = (9.0 - poolball.y) / poolball.vy;
        }
        else if (y > this.area.height - 9 - 1.0) {
            b2 = true;
            n2 = (this.area.height - 9 - 1.0 - poolball.y) / poolball.vy;
        }
        if (!b && !b2) {
            poolball.x = x;
            poolball.y = y;
        }
        else {
            min = Math.min(min, n2);
            poolball.x += min * poolball.vx;
            poolball.y += min * poolball.vy;
            if (b && b2) {
                if (min == n2) {
                    poolball.vy = -poolball.vy;
                }
                else {
                    poolball.vx = -poolball.vx;
                }
            }
            else {
                if (b) {
                    poolball.vx = -poolball.vx;
                }
                if (b2) {
                    poolball.vy = -poolball.vy;
                }
            }
        }
        this.checkBags(poolball);
        final double n3 = 1.0 - min / 14.399999999999999;
        poolball.vx *= n3;
        poolball.vy *= n3;
    }
    
    private void checkBags(final poolball poolball) {
        int n = 0;
        for (int i = 0; i < 4; ++i) {
            final int n2 = (int)poolball.x - this.BAGS[i * 2];
            final int n3 = (int)poolball.y - this.BAGS[i * 2 + 1];
            if (n2 * n2 + n3 * n3 <= 144) {
                n = 1;
                break;
            }
        }
        if (n == 0) {
            for (int j = 4; j < 6; ++j) {
                if (Math.abs((int)poolball.x - this.BAGS[j * 2]) <= 12) {
                    if (Math.abs((int)poolball.y - this.BAGS[j * 2 + 1]) <= 10) {
                        if (j == 4) {
                            if (poolball.vy > 0.0) {
                                n = 1;
                                break;
                            }
                        }
                        else if (poolball.vy < 0.0) {
                            n = 1;
                        }
                    }
                }
            }
        }
        if (n != 0) {
            this.dropSound.play();
            poolball.state = 2;
            this.updateCredit(poolball);
        }
    }
    
    private void updateCredit(final poolball poolball) {
        if (poolball.num == 15) {
            this.credit -= 20;
        }
        else if (poolball.num == 7) {
            this.credit -= 40;
        }
        else {
            if (poolball.num < 7) {
                --this.BballsOnTable;
            }
            else {
                --this.RballsOnTable;
            }
            if (this.teamColored == 0) {
                ++this.credit;
                if (poolball.num < 7) {
                    if (this.currentPlayer == 1) {
                        this.teamColored = 1;
                    }
                    else {
                        this.teamColored = 2;
                    }
                }
                else if (this.currentPlayer == 1) {
                    this.teamColored = 2;
                }
                else {
                    this.teamColored = 1;
                }
            }
            else if (poolball.num < 7) {
                if (this.currentPlayer == this.teamColored) {
                    ++this.credit;
                }
            }
            else if (this.currentPlayer != this.teamColored) {
                ++this.credit;
            }
        }
    }
    
    private void wearSpeed() {
        boolean b = false;
        for (int i = 0; i <= 15; ++i) {
            if (this.balls[i].state == 1) {
                if (Math.abs(this.balls[i].vx) <= 1.3888888888888888 && Math.abs(this.balls[i].vy) <= 1.3888888888888888) {
                    this.balls[i].vx = 0.0;
                    this.balls[i].vy = 0.0;
                }
                else {
                    b = true;
                }
            }
        }
        final boolean moveDropped = this.moveDropped();
        if (!b && moveDropped) {
            this.shootpos.x = this.shootcenter.x;
            this.shootpos.y = this.shootcenter.y;
            this.ballRunning = false;
            if (this.cue.state != 1) {
                this.timeCounter = 20;
            }
            else {
                this.bdrawgl = true;
                this.banglechanged = true;
                this.decideNextPlayer();
            }
        }
    }
    
    private synchronized void restoreCue() {
        this.heavenpos = (short)this.cue.x;
        this.cue.vx = 0.0;
        this.cue.vy = 0.0;
        this.cue.spinning = false;
        this.updateDropped();
        this.movingcue = 20;
        this.placeCue(this.cueballpos.x, this.cueballpos.y);
        this.cue.state = 1;
        this.bdrawgl = true;
        this.decideNextPlayer();
    }
    
    private void decideDrawDirection(final poolball poolball) {
        if (poolball.vx == 0.0) {
            poolball.currentDirection = poolball.A0data;
            return;
        }
        final double abs = Math.abs(poolball.vy / poolball.vx);
        if (abs > 2.0) {
            poolball.currentDirection = poolball.A0data;
            return;
        }
        if (abs <= 0.5) {
            poolball.currentDirection = poolball.A90data;
            return;
        }
        if (poolball.vx > 0.0) {
            if (poolball.vy <= 0.0) {
                poolball.currentDirection = poolball.A45data;
            }
            else {
                poolball.currentDirection = poolball.A135data;
            }
            return;
        }
        if (poolball.vy <= 0.0) {
            poolball.currentDirection = poolball.A135data;
        }
        else {
            poolball.currentDirection = poolball.A45data;
        }
    }
    
    private void decideNextPlayer() {
        if (this.credit < -50) {
            this.ongame = false;
            if (this.currentPlayer == 1) {
                this.winner = 2;
            }
            else {
                this.winner = 1;
            }
        }
        else if (this.credit < -30) {
            this.ongame = false;
            if (this.currentPlayer == 1) {
                if (this.teamColored == 1) {
                    if (this.BballsOnTable == 0) {
                        this.winner = 1;
                    }
                    else {
                        this.winner = 2;
                    }
                }
                else if (this.RballsOnTable == 0) {
                    this.winner = 1;
                }
                else {
                    this.winner = 2;
                }
            }
            else if (this.teamColored == 1) {
                if (this.RballsOnTable == 0) {
                    this.winner = 2;
                }
                else {
                    this.winner = 1;
                }
            }
            else if (this.BballsOnTable == 0) {
                this.winner = 2;
            }
            else {
                this.winner = 1;
            }
        }
        else if (this.credit <= 0) {
            if (this.currentPlayer == 1) {
                this.currentPlayer = 2;
            }
            else {
                this.currentPlayer = 1;
            }
        }
    }
    
    private void showWinner() {
        this.goff.setFont(this.bigFont);
        String s;
        if (this.winner == 1) {
            if (this.teamColored == 1) {
                this.goff.setColor(Color.blue);
            }
            else {
                this.goff.setColor(Color.red);
            }
            s = "Left Player won the game !";
        }
        else {
            if (this.teamColored == 1) {
                this.goff.setColor(Color.red);
            }
            else {
                this.goff.setColor(Color.blue);
            }
            s = "Right Player won the game!";
        }
        this.goff.drawString(s, this.table.x + this.table.width / 2 - 200, this.table.y + this.table.height / 2);
    }
    
    public boolean moveDropped() {
        boolean b = true;
        for (int i = 0; i <= 15; ++i) {
            if (this.balls[i].state >= 2 && this.balls[i].state < 5) {
                b = false;
            }
            if (this.balls[i].state == 4) {
                final poolball poolball = this.balls[i];
                poolball.x += 18.0;
                this.balls[i].rotate = (this.balls[i].rotate + 1) % 36;
                this.balls[i].moved = true;
                if (this.balls[i].x >= this.heavenpos) {
                    this.balls[i].state = 5;
                    this.balls[i].x = this.heavenpos;
                    this.heavenpos -= 18;
                }
            }
        }
        return b;
    }
    
    private void updateDropped() {
        int n = 0;
        for (int i = 0; i < 15; ++i) {
            if (this.balls[i].state == 5 && this.balls[i].x < this.heavenpos) {
                final poolball poolball = this.balls[i];
                poolball.x += 18.0;
                ++n;
            }
        }
        this.heavenpos -= (short)(n * 18);
    }
    
    public synchronized void playGame() {
        if (!this.ongame) {
            return;
        }
        if (!this.bdrawgl) {
            this.bDraw = true;
            if (this.ballRunning) {
                this.decidePosition();
            }
            else if (this.cue.state != 1) {
                --this.timeCounter;
                if (this.timeCounter <= 0) {
                    this.restoreCue();
                }
            }
        }
        if (this.bmousepower) {
            this.bDrawAll = true;
            this.controlPower();
        }
    }
    
    private void loadData() {
        int i = 0;
        this.showLoading(i);
        try {
            this.tracker.waitForAll();
        }
        catch (InterruptedException ex) {
            System.out.println("Load image failed!");
        }
        do {
            this.prepareData(i);
            ++i;
            this.showLoading(i);
        } while (i < 15);
        this.prepareFlagball();
        this.drawGround();
        this.dropSound.play();
        this.hitSound.play();
        this.loaded = true;
    }
    
    private void prepareFlagball() {
        this.lowBalls = new poolball();
        this.highBalls = new poolball();
        for (int i = 0; i < 324; ++i) {
            if (poolball.D18[i] != 0) {
                switch (this.balls[0].A0data[i]) {
                    case 1: {
                        this.lowBalls.picData[i] = -3092272;
                        break;
                    }
                    case 0: {
                        this.lowBalls.picData[i] = -16777216;
                        break;
                    }
                    case 2:
                    case 3: {
                        this.lowBalls.picData[i] = this.balls[0].color;
                        break;
                    }
                    default: {
                        System.out.println("Error data happened");
                        break;
                    }
                }
                final byte b = poolball.light18[i];
                final int n = b << 16 | b << 8 | b;
                final int[] picData = this.lowBalls.picData;
                final int n2 = i;
                picData[n2] += n;
                switch (this.balls[10].A0data[i]) {
                    case 1: {
                        this.highBalls.picData[i] = -3092272;
                        break;
                    }
                    case 0: {
                        this.highBalls.picData[i] = -16777216;
                        break;
                    }
                    case 2:
                    case 3: {
                        this.highBalls.picData[i] = this.balls[10].color;
                        break;
                    }
                    default: {
                        System.out.println("Error data happened");
                        break;
                    }
                }
                final int[] picData2 = this.highBalls.picData;
                final int n3 = i;
                picData2[n3] += n;
            }
        }
        this.lowBalls.source = new MemoryImageSource(18, 18, this.lowBalls.picData, 0, 18);
        this.highBalls.source = new MemoryImageSource(18, 18, this.highBalls.picData, 0, 18);
        this.lowBalls.bd = this.createImage(this.lowBalls.source);
        this.highBalls.bd = this.createImage(this.highBalls.source);
        final int[] array = new int[2500];
        for (int j = 0; j < 50; ++j) {
            for (int k = 0; k < 50; ++k) {
                final int n4 = (j - 25) * (j - 25) + (k - 25) * (k - 25);
                if (n4 > 625) {
                    array[j * 50 + k] = 0;
                }
                else {
                    final int n5 = 165 + (25 - (int)Math.sqrt(n4)) * 3;
                    array[j * 50 + k] = (0xFF000000 | n5 << 16 | n5 << 8 | n5);
                }
            }
        }
        this.bigCue = this.createImage(new MemoryImageSource(50, 50, array, 0, 50));
    }
    
    private void showLoading(final int n) {
        final Graphics graphics = this.getGraphics();
        if (n == 0 || n == 1) {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, this.screen.width, this.screen.height);
        }
        if (n == 0) {
            graphics.setColor(Color.yellow);
            graphics.drawString("loading picture, please wait...", 200, 200);
            return;
        }
        graphics.setColor(Color.yellow);
        graphics.drawString("Preparing game, please wait...", 200, 200);
        graphics.setColor(Color.orange);
        graphics.drawRect(100, 240, 300, 20);
        graphics.setColor(Color.green);
        graphics.fillRect(100, 240, n * 20, 20);
    }
    
    public void run() {
        if (!this.loaded) {
            this.loadData();
        }
        final Thread currentThread = Thread.currentThread();
        Thread.currentThread().setPriority(10);
        while (this.theThread == currentThread) {
            final long currentTimeMillis = System.currentTimeMillis();
            try {
                this.playGame();
                this.drawPic();
                Thread.sleep(Math.max(0L, currentTimeMillis + 50L - System.currentTimeMillis()));
            }
            catch (InterruptedException ex) {
                break;
            }
        }
    }
    
    public void start() {
        if (this.theThread == null) {
            (this.theThread = new Thread(this)).start();
        }
    }
}
