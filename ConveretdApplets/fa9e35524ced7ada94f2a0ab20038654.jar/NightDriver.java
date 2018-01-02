import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Panel;
import java.net.MalformedURLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Button;
import java.net.URL;
import java.awt.Font;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class NightDriver extends Applet implements Runnable, ActionListener
{
    private Graphics m_Graphics;
    private Image offscreenImage;
    private AudioClip sound;
    private AudioClip sound2;
    private Image cracked;
    private Image backdrop;
    private Image post;
    private Image tree;
    private Image bush;
    private Image bush2;
    private Graphics finishedGraphics;
    private Graphics offscreenGraphics;
    private Font updateFont;
    private Font startMouse;
    private Font tooSlow;
    private int[] val2;
    private int[] track;
    private int[] trackZ;
    private int time;
    private int carPosition;
    private int facing;
    private int roadFacing;
    private int trackLoc;
    private double[] zoom;
    private int driverCount;
    private int[][] roadSigns;
    private int[] roadType;
    private int[] trees;
    private int[] bushes;
    private int[] bushestwo;
    private int facingZ;
    private long sleeper;
    public int dimmer;
    private long startTime;
    private int levtime;
    private int cpoint;
    private boolean gas;
    private boolean brake;
    private boolean left;
    private boolean right;
    private Font title;
    URL url1;
    String str;
    Button b1;
    private Thread m_DUI;
    private int level;
    private int lives;
    private int speed;
    private int soundvar;
    private boolean wrecked;
    private boolean m_fAllLoaded;
    private int dn;
    public boolean autopilot;
    private Image[][] cars;
    private int[] driverY;
    private Image[] signs;
    private int[] driverCar;
    private int[] driverDir;
    private int[][] upcomingCars;
    public boolean running;
    private Color[] road;
    private Color[] darkRoad;
    private Color[] darkLines;
    private Color[] hill;
    private Color[] barricade;
    private Color[] tunnel;
    private Color[] wood;
    private int[] xvals;
    private int[] yvals;
    private int[] xm;
    private int[] ym;
    
    public void actionPerformed(final ActionEvent actionEvent) {
        try {
            this.url1 = new URL(this.str);
            this.getAppletContext().showDocument(this.url1, "_blank");
        }
        catch (MalformedURLException ex) {
            System.out.println("Error " + ex);
        }
    }
    
    private void copyRight() {
        this.offscreenGraphics.setFont(new Font("TimesNewRoman", 1, 14));
        this.offscreenGraphics.setColor(Color.white);
        this.offscreenGraphics.drawString("Original '3d Driver' by Derek Ramey", 85, 250);
        this.offscreenGraphics.drawString("Visit: http://www.4webgames.com", 90, 270);
    }
    
    public void destroy() {
    }
    
    private void dimImage() {
        this.offscreenGraphics.setColor(Color.black);
        for (int i = 0; i < 1000; ++i) {
            this.offscreenGraphics.fillRect((int)(Math.random() * 400.0), (int)(Math.random() * 300.0), 4, 4);
        }
    }
    
    public void drawBG(final Graphics graphics) {
        if (this.dn == 2) {
            graphics.setColor(new Color(0, 14, 108));
        }
        else {
            graphics.setColor(new Color(43, 69, 255));
        }
        graphics.fillRect(0, 0, 800, 600);
        if (this.dn == 2) {
            graphics.setColor(Color.white);
            for (int i = 0; i < 250; ++i) {
                final int n = (int)Math.round(Math.random() * 800.0);
                final int n2 = (int)Math.round(Math.random() * 600.0);
                graphics.drawLine(n, n2, n, n2);
            }
        }
        graphics.setColor(Color.gray);
        for (int j = 0; j < 750; ++j) {
            final int n3 = (int)Math.round(Math.random() * 800.0);
            final int n4 = (int)Math.round(Math.random() * 600.0);
            graphics.drawLine(n3, n4, n3, n4);
        }
    }
    
    private void ending() {
        this.offscreenGraphics.setFont(new Font("TimesNewRoman", 1, 18));
        this.offscreenGraphics.setColor(Color.white);
        this.offscreenGraphics.drawString("Congratulations!!! ", 145, 172);
        this.offscreenGraphics.drawString("You have won at Level " + this.level, 125, 200);
        this.offscreenGraphics.drawString("Click for next Level ", 145, 222);
        ++this.level;
        this.levtime = this.level * 10 + 40;
        this.cpoint = 100 + this.level * 50;
        this.running = false;
        if (this.level == 4) {
            this.dn = 1;
            this.init();
            this.offscreenGraphics.setFont(new Font("SansSerif", 1, 18));
            this.offscreenGraphics.setColor(Color.blue);
            this.offscreenGraphics.drawString("Soon NightDriver wont be Nightdriver", 15, 182);
            this.offscreenGraphics.drawString("anymore, because it is already morning", 15, 202);
            this.offscreenGraphics.drawString("click to go on", 55, 222);
        }
    }
    
    public String getAppletInfo() {
        return "Name: DUI\r\nAuthor: Derek Ramey\r\n";
    }
    
    public void init() {
        this.add(new Panel());
        this.setLayout(new BorderLayout());
        this.add(this.b1, "South");
        this.b1.addActionListener(this);
        this.resize(400, 300);
        this.zoom = new double[26];
        for (int i = 0; i < 25; ++i) {
            this.zoom[i] = 200.0 / (i + 1) - 8.0;
        }
        this.offscreenImage = this.createImage(400, 300);
        this.offscreenGraphics = this.offscreenImage.getGraphics();
        this.sound = this.getAudioClip(this.getCodeBase(), "music.au");
        this.sound2 = this.getAudioClip(this.getCodeBase(), "shortwah.au");
        this.cracked = this.getImage(this.getCodeBase(), "cracked.gif");
        this.cars[0][0] = this.getImage(this.getCodeBase(), "car1f.gif");
        this.cars[0][1] = this.getImage(this.getCodeBase(), "car1b.gif");
        this.cars[1][0] = this.getImage(this.getCodeBase(), "car2f.gif");
        this.cars[1][1] = this.getImage(this.getCodeBase(), "car2b.gif");
        this.cars[2][0] = this.getImage(this.getCodeBase(), "car3f.gif");
        this.cars[2][1] = this.getImage(this.getCodeBase(), "car3b.gif");
        this.signs[0] = this.getImage(this.getCodeBase(), "banner1.gif");
        this.signs[1] = this.getImage(this.getCodeBase(), "banner2.gif");
        this.signs[2] = this.getImage(this.getCodeBase(), "banner3.gif");
        this.tree = this.getImage(this.getCodeBase(), "tree1" + this.dn + ".gif");
        this.bush = this.getImage(this.getCodeBase(), "tree2" + this.dn + ".gif");
        this.bush2 = this.getImage(this.getCodeBase(), "tree3" + this.dn + ".gif");
        this.post = this.getImage(this.getCodeBase(), "post.gif");
        this.backdrop = this.createImage(800, 600);
        this.drawBG(this.backdrop.getGraphics());
        this.updateFont = new Font("TimesNewRoman", 1, 12);
        this.startMouse = new Font("TimesNewRoman", 1, 18);
        this.tooSlow = new Font("TimesNewRoman", 1, 32);
        this.title = new Font("Serif", 3, 42);
        for (int j = 0; j < 21; ++j) {
            if (this.dn == 2) {
                this.hill[j] = new Color(70, 126 - j * 5, 30);
                this.road[j] = new Color(150 - j * 4, 150 - j * 4, 150 - j * 4);
                this.barricade[j] = new Color(255 - j * 6, 255 - j * 6, 255 - j * 6);
                this.wood[j] = new Color(150 - j * 4, 100 - j * 3, 50 - j * 2);
                this.tunnel[j] = new Color(75 - j * 3, 50 - j * 2, 50 - j * 2);
                this.darkRoad[j] = new Color(75 - j * 3, 75 - j * 3, 75 - j * 3);
                this.darkLines[j] = new Color(150 - j * 4, 150 - j * 4, 0);
            }
            else {
                this.hill[j] = new Color(99, 183 - j * 5, 43);
                this.road[j] = new Color(150 - j * 4, 150 - j * 4, 150 - j * 4);
                this.barricade[j] = new Color(255 - j * 6, 255 - j * 6, 255 - j * 6);
                this.wood[j] = new Color(150 - j * 4, 100 - j * 3, 50 - j * 2);
                this.tunnel[j] = new Color(75 - j * 3, 50 - j * 2, 50 - j * 2);
                this.darkRoad[j] = new Color(75 - j * 3, 75 - j * 3, 75 - j * 3);
                this.darkLines[j] = new Color(150 - j * 4, 150 - j * 4, 0);
            }
        }
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (n == 1006) {
            return this.left = true;
        }
        if (n == 1007) {
            return this.right = true;
        }
        if (n == 97 || n == 65) {
            return this.gas = true;
        }
        if (n == 100 || n == 68) {
            this.dn = 1;
            return true;
        }
        if (n == 122 || n == 90) {
            return this.brake = true;
        }
        if (n == 115 || n == 83) {
            if (this.soundvar == 0) {
                this.sound.loop();
                this.soundvar = 1;
            }
            else {
                this.sound.stop();
                this.soundvar = 0;
            }
        }
        return false;
    }
    
    public boolean keyUp(final Event event, final int n) {
        if (n == 1006) {
            this.left = false;
        }
        else if (n == 1007) {
            this.right = false;
        }
        else if (n == 97 || n == 65) {
            this.gas = false;
        }
        else {
            if (n != 122 && n != 90) {
                return false;
            }
            this.brake = false;
        }
        return true;
    }
    
    private void lose() {
        if (this.dn == 1) {
            this.dn = 2;
            this.init();
            this.offscreenGraphics.drawString("Game Over", 145, 222);
            this.offscreenGraphics.drawString("Click to start", 145, 222);
        }
        this.offscreenGraphics.setFont(new Font("TimesNewRoman", 1, 18));
        this.offscreenGraphics.setColor(Color.white);
        this.offscreenGraphics.drawString("you have lost the game", 125, 160);
        this.offscreenGraphics.drawString("Click to restart", 145, 176);
        this.level = 1;
        this.levtime = this.level * 10 + 40;
        this.cpoint = 100 + this.level * 50;
        this.running = false;
        this.lives = 3;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.running) {
            this.newGame();
            this.running = true;
        }
        return true;
    }
    
    public void newGame() {
        this.carPosition = 100;
        this.facing = 0;
        this.roadFacing = 0;
        this.trackLoc = 0;
        this.speed = 100;
        this.wrecked = false;
        this.autopilot = false;
        this.randomizeDrivers();
        this.randomizeTrack();
        this.startTime = System.currentTimeMillis();
        this.dimmer = 0;
    }
    
    private final void nextFrame(final int n) {
        final int[] array = new int[26];
        final int[] array2 = new int[26];
        array[0] = this.track[this.trackLoc];
        int n2 = 0;
        int n3 = 0;
        int i = this.facing * 10;
        int j = this.facingZ * 10;
        while (i < 0) {
            i += 800;
        }
        while (i > 800) {
            i -= 800;
        }
        while (j < 0) {
            j += 600;
        }
        while (j > 600) {
            j -= 600;
        }
        this.offscreenGraphics.clipRect(0, 0, 400, 300);
        this.offscreenGraphics.drawImage(this.backdrop, -i, -j, this);
        this.offscreenGraphics.drawImage(this.backdrop, -i + 800, -j, this);
        this.offscreenGraphics.drawImage(this.backdrop, -i + 800, -j + 600, this);
        this.offscreenGraphics.drawImage(this.backdrop, -i, -j + 600, this);
        for (int k = 1; k < 21; ++k) {
            n2 += this.track[k + this.trackLoc];
            n3 += this.trackZ[k + this.trackLoc];
            for (int l = k; l < 25; ++l) {
                final int[] array3 = array;
                final int n4 = l;
                array3[n4] += n2;
                final int[] array4 = array2;
                final int n5 = l;
                array4[n5] += n3;
            }
        }
        for (int n6 = 0; n6 < 21; ++n6) {
            this.upcomingCars[n6][0] = 999;
            this.upcomingCars[n6][1] = 999;
        }
        for (int n7 = 0; n7 < 50; ++n7) {
            if (this.driverY[n7] >= this.trackLoc && this.driverY[n7] <= this.trackLoc + 20) {
                if (this.upcomingCars[this.driverY[n7] - this.trackLoc][0] == 999) {
                    this.upcomingCars[this.driverY[n7] - this.trackLoc][0] = n7;
                }
                else {
                    this.upcomingCars[this.driverY[n7] - this.trackLoc][1] = n7;
                }
            }
        }
        for (int n8 = 20; n8 > 0; --n8) {
            final int n9 = array[n8] - this.carPosition / (n8 + 1) + 200 - n * n8;
            final int n10 = array[n8 - 1] - this.carPosition / n8 + 200 - n * n8 + n;
            this.xvals[0] = n9 - (int)(this.zoom[n8] * 4.0);
            this.xvals[1] = n9 + (int)(this.zoom[n8] * 4.0);
            this.xvals[3] = n10 - (int)(this.zoom[n8 - 1] * 4.0);
            this.xvals[2] = n10 + (int)(this.zoom[n8 - 1] * 4.0);
            this.yvals[0] = 200 + (int)(this.zoom[n8] + array2[n8]);
            this.yvals[1] = this.yvals[0];
            this.yvals[2] = 200 + (int)(this.zoom[n8 - 1] + array2[n8 - 1]);
            this.yvals[3] = this.yvals[2];
            final int n11 = (int)(this.zoom[n8] * 4.0);
            final int n12 = (int)(this.zoom[n8 - 1] * 4.0);
            if (this.roadType[this.trackLoc + n8] == 0) {
                this.xm[0] = this.xvals[0];
                this.ym[0] = this.yvals[0];
                this.xm[1] = this.xvals[3];
                this.ym[1] = this.yvals[3];
                this.xm[2] = this.xm[1];
                this.ym[2] = 300;
                this.xm[3] = this.xm[0] - (300 - this.yvals[0]);
                this.ym[3] = 300;
                this.offscreenGraphics.setColor(this.hill[n8]);
                this.offscreenGraphics.fillPolygon(this.xm, this.ym, 4);
            }
            if (this.roadType[this.trackLoc + n8] == 1) {
                this.xm[0] = this.xvals[3];
                this.ym[0] = this.yvals[3];
                this.xm[1] = this.xvals[3];
                this.ym[1] = this.yvals[3] - (int)this.zoom[n8 - 1];
                this.xm[2] = this.xvals[0];
                this.ym[2] = this.yvals[0] - (int)this.zoom[n8];
                this.xm[3] = this.xvals[0];
                this.ym[3] = this.yvals[0];
                this.offscreenGraphics.setColor(this.barricade[n8]);
                this.offscreenGraphics.fillPolygon(this.xm, this.ym, 4);
            }
            if (this.roadType[this.trackLoc + n8] == 2) {
                this.xm[0] = this.xvals[3];
                this.ym[0] = this.yvals[3];
                this.xm[1] = this.xvals[3];
                this.ym[1] = this.yvals[3] - (int)(this.zoom[n8 - 1] * 5.0);
                this.xm[2] = this.xvals[0];
                this.ym[2] = this.yvals[0] - (int)(this.zoom[n8] * 5.0);
                this.xm[3] = this.xvals[0];
                this.ym[3] = this.yvals[0];
                this.offscreenGraphics.setColor(this.tunnel[n8]);
                this.offscreenGraphics.fillPolygon(this.xm, this.ym, 4);
            }
            if (this.yvals[2] > this.yvals[0]) {
                this.xm[0] = n9 - n11;
                this.xm[1] = n9 + n11;
                this.xm[2] = n10 + n12;
                this.xm[3] = n10 - n12;
                final float n13 = (this.trackLoc + n8) / 100.0f;
                if (n13 == Math.round(n13)) {
                    this.offscreenGraphics.setColor(new Color(255 - n8 * 2, 0, 0));
                }
                else if (this.trackLoc + n8 > 5000) {
                    this.offscreenGraphics.setColor(new Color(0, 255 - n8 * 4, 0));
                }
                else {
                    if (this.roadType[this.trackLoc + n8] == 1) {
                        this.offscreenGraphics.setColor(this.wood[n8]);
                    }
                    if (this.roadType[this.trackLoc + n8] == 2) {
                        this.offscreenGraphics.setColor(this.darkRoad[n8]);
                    }
                    if (this.roadType[this.trackLoc + n8] == 0) {
                        this.offscreenGraphics.setColor(this.road[n8]);
                    }
                }
                this.offscreenGraphics.fillPolygon(this.xm, this.yvals, 4);
                final int n14 = this.trackLoc + n8;
                if (n14 / 2.0f == n14 / 2) {
                    this.xm[0] = n9 - (int)(this.zoom[n8] / 8.0) - 1;
                    this.xm[1] = n9 + (int)(this.zoom[n8] / 8.0) + 1;
                    this.xm[3] = n10 - (int)(this.zoom[n8 - 1] / 8.0) - 1;
                    this.xm[2] = n10 + (int)(this.zoom[n8 - 1] / 8.0) + 1;
                    this.ym[0] = this.yvals[0];
                    this.ym[1] = this.yvals[0];
                    this.ym[2] = this.yvals[2];
                    this.ym[3] = this.yvals[2];
                    if (this.roadType[this.trackLoc + n8] == 2) {
                        this.offscreenGraphics.setColor(this.darkLines[n8]);
                    }
                    else {
                        this.offscreenGraphics.setColor(Color.yellow);
                    }
                    this.offscreenGraphics.fillPolygon(this.xm, this.ym, 4);
                }
            }
            if (this.roadType[this.trackLoc + n8] == 0) {
                this.xm[0] = this.xvals[1];
                this.ym[0] = this.yvals[1];
                this.xm[1] = this.xm[0] + this.yvals[1];
                this.ym[1] = 0;
                this.xm[2] = 400;
                this.ym[2] = 0;
                this.xm[3] = 400;
                this.ym[3] = 300;
                this.xm[4] = this.xvals[2];
                this.ym[4] = 300;
                this.xm[5] = this.xvals[2];
                this.ym[5] = this.yvals[2];
                this.offscreenGraphics.setColor(this.hill[n8]);
                this.offscreenGraphics.fillPolygon(this.xm, this.ym, 6);
            }
            if (this.roadType[this.trackLoc + n8] == 1) {
                this.xm[0] = this.xvals[2];
                this.ym[0] = this.yvals[2];
                this.xm[1] = this.xvals[2];
                this.ym[1] = this.yvals[2] - (int)this.zoom[n8 - 1];
                this.xm[2] = this.xvals[1];
                this.ym[2] = this.yvals[1] - (int)this.zoom[n8];
                this.xm[3] = this.xvals[1];
                this.ym[3] = this.yvals[1];
                this.offscreenGraphics.setColor(this.barricade[n8]);
                this.offscreenGraphics.fillPolygon(this.xm, this.ym, 4);
            }
            if (this.roadType[this.trackLoc + n8] == 2) {
                this.xm[0] = this.xvals[2];
                this.ym[0] = this.yvals[2];
                this.xm[1] = this.xvals[2];
                this.ym[1] = this.yvals[2] - (int)(this.zoom[n8 - 1] * 5.0);
                this.xm[2] = this.xvals[1];
                this.ym[2] = this.yvals[1] - (int)(this.zoom[n8] * 5.0);
                this.xm[3] = this.xvals[1];
                this.ym[3] = this.yvals[1];
                this.offscreenGraphics.setColor(this.tunnel[n8]);
                this.offscreenGraphics.fillPolygon(this.xm, this.ym, 4);
                this.xm[0] = this.xvals[0];
                this.xm[1] = this.xvals[1];
                this.xm[2] = this.xvals[2];
                this.xm[3] = this.xvals[3];
                this.ym[0] = this.yvals[0] - (int)(this.zoom[n8] * 5.0);
                this.ym[1] = this.ym[0];
                this.ym[2] = this.yvals[2] - (int)(this.zoom[n8 - 1] * 5.0);
                this.ym[3] = this.ym[2];
                this.offscreenGraphics.fillPolygon(this.xm, this.ym, 4);
                if (this.xvals[0] > 0) {
                    this.xm[0] = this.xvals[3];
                    this.ym[0] = this.yvals[3] - (int)(this.zoom[n8 - 1] * 10.0);
                    this.xm[1] = this.xm[0];
                    this.ym[1] = 300;
                    this.xm[2] = this.xm[0] - (300 - this.ym[0]);
                    this.ym[2] = 300;
                    this.offscreenGraphics.setColor(this.hill[n8]);
                    this.offscreenGraphics.fillPolygon(this.xm, this.ym, 3);
                    this.ym[1] = this.yvals[3] - (int)(this.zoom[n8 - 1] * 5.0);
                    this.ym[2] = this.ym[1];
                    this.xm[2] = 400;
                    this.xm[3] = 400;
                    this.ym[3] = this.ym[0] - (400 - this.xm[0]);
                    this.offscreenGraphics.fillPolygon(this.xm, this.ym, 4);
                    this.xm[0] = this.xvals[2];
                    this.xm[1] = this.xvals[2];
                    this.xm[2] = 400;
                    this.xm[3] = 400;
                    this.ym[0] = this.ym[1];
                    this.ym[1] = 300;
                    this.ym[2] = 300;
                    this.ym[3] = this.ym[0];
                    this.offscreenGraphics.fillPolygon(this.xm, this.ym, 4);
                }
            }
            if (this.upcomingCars[n8][0] != 999) {
                final int n15 = this.upcomingCars[n8][0];
                this.offscreenGraphics.drawImage(this.cars[this.driverCar[n15]][this.driverDir[n15]], n10 + n12 * (this.driverDir[n15] - 1) + n12 / 8, this.yvals[2] - n12 * 3 / 4, n12 * 3 / 4, n12 * 3 / 4, this);
            }
            if (this.upcomingCars[n8][1] != 999) {
                final int n16 = this.upcomingCars[n8][1];
                this.offscreenGraphics.drawImage(this.cars[this.driverCar[n16]][this.driverDir[n16]], n10 + n12 * (this.driverDir[n16] - 1) + n12 / 8, this.yvals[2] - n12 * 3 / 4, n12 * 3 / 4, n12 * 3 / 4, this);
            }
            if (this.roadType[this.trackLoc + n8] == 0) {
                if (this.roadSigns[this.trackLoc + n8][0] > 0) {
                    final int n17 = n10 - n12 * 2;
                    final int n18 = this.yvals[2] - (int)(this.zoom[n8 - 1] * 3.0);
                    this.offscreenGraphics.drawImage(this.signs[this.roadSigns[this.trackLoc + n8][0] - 1], n17, n18, n12, n12 / 3, this);
                    this.offscreenGraphics.drawImage(this.post, n17 + n12 * 19 / 40, n18 + n12 / 3, n12 / 20, n12, this);
                }
                if (this.roadSigns[this.trackLoc + n8][1] > 0) {
                    final int n19 = this.xvals[2];
                    final int n20 = this.yvals[2] - (int)(this.zoom[n8 - 1] * 6.0);
                    this.offscreenGraphics.drawImage(this.signs[this.roadSigns[this.trackLoc + n8][1] - 1], n19, n20, n12, n12 / 3, this);
                    this.offscreenGraphics.drawImage(this.post, n19 + n12 * 19 / 40, n20 + n12 / 3, n12 / 20, n12, this);
                }
                if (this.trees[this.trackLoc + n8] > 0) {
                    final int n21 = n10 + (int)(this.zoom[n8 - 1] * (this.trees[this.trackLoc + n8] + 1));
                    final int n22 = this.yvals[2] - (int)(this.zoom[n8 - 1] * (this.trees[this.trackLoc + n8] + 6));
                    if (n21 < 400) {
                        this.offscreenGraphics.drawImage(this.tree, n21, n22, (int)(this.zoom[n8 - 1] * 4.0), (int)(this.zoom[n8 - 1] * 7.0), this);
                    }
                }
                if (this.bushes[this.trackLoc + n8] > 0) {
                    final int n23 = n10 + (int)(this.zoom[n8 - 1] * (this.bushes[this.trackLoc + n8] + 1));
                    final int n24 = this.yvals[2] - (int)(this.zoom[n8 - 1] * (this.bushes[this.trackLoc + n8] + 6));
                    if (n23 < 400) {
                        this.offscreenGraphics.drawImage(this.bush, n23, n24, (int)(this.zoom[n8 - 1] * 4.0), (int)(this.zoom[n8 - 1] * 7.0), this);
                    }
                }
                if (this.bushestwo[this.trackLoc + n8] > 0) {
                    final int n25 = n10 + (int)(this.zoom[n8 - 1] * (this.bushestwo[this.trackLoc + n8] + 1));
                    final int n26 = this.yvals[2] - (int)(this.zoom[n8 - 1] * (this.bushestwo[this.trackLoc + n8] + 6));
                    if (n25 < 400) {
                        this.offscreenGraphics.drawImage(this.bush2, n25, n26, (int)(this.zoom[n8 - 1] * 4.0), (int)(this.zoom[n8 - 1] * 7.0), this);
                    }
                }
                if (this.trees[this.trackLoc + n8] == -1) {
                    final int n27 = n10 - (int)(this.zoom[n8 - 1] * 9.0);
                    final int n28 = this.yvals[2] - (int)(this.zoom[n8 - 1] * 4.0);
                    if (n27 > -(int)(this.zoom[n8 - 1] * 4.0)) {
                        this.offscreenGraphics.drawImage(this.tree, n27, n28, (int)(this.zoom[n8 - 1] * 4.0), (int)(this.zoom[n8 - 1] * 7.0), this);
                    }
                }
                if (this.bushes[this.trackLoc + n8] == -1) {
                    final int n29 = n10 - (int)(this.zoom[n8 - 1] * 9.0);
                    final int n30 = this.yvals[2] - (int)(this.zoom[n8 - 1] * 4.0);
                    if (n29 > -(int)(this.zoom[n8 - 1] * 4.0)) {
                        this.offscreenGraphics.drawImage(this.bush, n29, n30, (int)(this.zoom[n8 - 1] * 4.0), (int)(this.zoom[n8 - 1] * 7.0), this);
                    }
                }
                if (this.bushestwo[this.trackLoc + n8] == -1) {
                    final int n31 = n10 - (int)(this.zoom[n8 - 1] * 9.0);
                    final int n32 = this.yvals[2] - (int)(this.zoom[n8 - 1] * 4.0);
                    if (n31 > -(int)(this.zoom[n8 - 1] * 4.0)) {
                        this.offscreenGraphics.drawImage(this.bush2, n31, n32, (int)(this.zoom[n8 - 1] * 4.0), (int)(this.zoom[n8 - 1] * 7.0), this);
                    }
                }
            }
        }
        this.offscreenGraphics.setColor(Color.white);
        this.offscreenGraphics.setFont(this.updateFont);
        this.time = (int)((System.currentTimeMillis() - this.startTime) / 1000L);
        this.offscreenGraphics.drawString("Checkpoints: " + this.trackLoc / 100 + " (" + this.cpoint / 100 + ") Speed: " + this.speed / 2 + " Driving Time: " + this.time + " (" + this.levtime + ") Level: " + this.level + " Lives: " + this.lives, 5, 15);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.offscreenImage, 0, 0, this);
    }
    
    private void randomizeDrivers() {
        for (int i = 0; i < 50; ++i) {
            this.driverDir[i] = (int)(Math.random() * 2.0);
            this.driverY[i] = (int)(Math.random() * 4990.0) + 10;
            this.driverCar[i] = (int)(Math.random() * 3.0);
        }
    }
    
    public void randomizeTrack() {
        this.track = new int[5100];
        this.trackZ = new int[5100];
        this.roadType = new int[5100];
        this.roadSigns = new int[5100][2];
        this.trees = new int[5100];
        this.bushes = new int[5100];
        this.bushestwo = new int[5100];
        for (int i = 0; i < 25; ++i) {
            this.roadSigns[i * 200 + (int)(Math.random() * 200.0)][(int)(Math.random() * 2.0)] = (int)(Math.random() * 3.0) + 1;
        }
        for (int j = 0; j < 100; ++j) {
            final int n = j * 50 + (int)(Math.random() * 50.0);
            int n2 = 0;
            switch ((int)(Math.random() * 3.0)) {
                case 0: {
                    n2 = (int)(Math.random() * 5.0) + 1;
                    break;
                }
                case 1: {
                    n2 = 1;
                    break;
                }
                case 2: {
                    n2 = -1;
                    break;
                }
            }
            this.trees[n] = n2;
        }
        for (int k = 0; k < 50; ++k) {
            final int n3 = (int)(Math.random() * 5000.0);
            final int n4 = (int)(Math.random() * 2.0) + 1;
            int n5 = 1;
            if (n4 == 1) {
                n5 = (int)(Math.random() * 10.0) + 5;
            }
            if (n4 == 2) {
                n5 = (int)(Math.random() * 25.0) + 10;
            }
            for (int l = 0; l < n5; ++l) {
                this.roadType[n3 + l] = n4;
            }
        }
        for (int n6 = 0; n6 < 100; ++n6) {
            final int n7 = n6 * 50 + (int)(Math.random() * 50.0);
            int n8 = 0;
            int n9 = 0;
            switch ((int)(Math.random() * 3.0)) {
                case 0: {
                    n8 = (int)(Math.random() * 5.0) + 1;
                    n9 = (int)(Math.random() * 5.0) + 1;
                    break;
                }
                case 1: {
                    n8 = 1;
                    n9 = -1;
                    break;
                }
                case 2: {
                    n8 = -1;
                    n9 = 1;
                    break;
                }
            }
            this.bushes[n7] = n8;
            this.bushestwo[n7] = n9;
        }
        for (int n10 = 1; n10 < 6; ++n10) {
            int n11 = 0;
            int n12 = 0;
            int n13 = 0;
            int n14 = 0;
            switch (n10) {
                case 1: {
                    n11 = 0;
                    n12 = 500;
                    n13 = 1;
                    n14 = 1;
                    break;
                }
                case 2: {
                    n11 = 500;
                    n12 = 1500;
                    n13 = 2;
                    n14 = 1;
                    break;
                }
                case 3: {
                    n11 = 1500;
                    n12 = 2500;
                    n13 = 3;
                    n14 = 2;
                    break;
                }
                case 4: {
                    n11 = 2500;
                    n12 = 3500;
                    n13 = 4;
                    n14 = 2;
                    break;
                }
                default: {
                    n11 = 3500;
                    n12 = 5000;
                    n13 = 5;
                    n14 = 3;
                    break;
                }
            }
            for (int n15 = 0; n15 < n10 * 30; ++n15) {
                final int n16 = (int)(Math.random() * (n12 - n11) + n11);
                switch ((int)(Math.random() * 10.0)) {
                    case 0:
                    case 6:
                    case 7: {
                        this.track[n16] = (int)(Math.random() * (n13 * 2 + 1)) - n13;
                        break;
                    }
                    case 1: {
                        this.track[n16] = -n13;
                        this.track[n16 + 1] = -(n13 * 2);
                        this.track[n16 + 2] = -(n13 * 3);
                        this.track[n16 + 3] = -(n13 * 2);
                        this.track[n16 + 4] = -n13;
                        break;
                    }
                    case 2: {
                        this.track[n16] = n13;
                        this.track[n16 + 1] = n13 * 2;
                        this.track[n16 + 2] = n13 * 3;
                        this.track[n16 + 3] = n13 * 2;
                        this.track[n16 + 4] = n13;
                        break;
                    }
                    case 3:
                    case 8:
                    case 9: {
                        this.trackZ[n16] = (int)(Math.random() * (n14 * 2 + 1) - n14) / 2;
                        break;
                    }
                    case 4: {
                        this.trackZ[n16] = -n14;
                        this.trackZ[n16 + 1] = -(n14 * 2);
                        this.trackZ[n16 + 2] = -n14;
                        break;
                    }
                    case 5: {
                        this.trackZ[n16] = n14;
                        this.trackZ[n16 + 1] = n14 * 2;
                        this.trackZ[n16 + 2] = n14;
                        break;
                    }
                }
            }
        }
    }
    
    public final void run() {
        if (!this.m_fAllLoaded) {
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(this.cracked, 0);
            mediaTracker.addImage(this.post, 0);
            mediaTracker.addImage(this.tree, 0);
            mediaTracker.addImage(this.bush, 0);
            mediaTracker.addImage(this.bush2, 0);
            for (int i = 0; i < 3; ++i) {
                mediaTracker.addImage(this.signs[i], 0);
            }
            for (int j = 0; j < 3; ++j) {
                for (int k = 0; k < 2; ++k) {
                    mediaTracker.addImage(this.cars[j][k], 0);
                }
            }
            try {
                mediaTracker.waitForAll();
                this.m_fAllLoaded = !mediaTracker.isErrorAny();
            }
            catch (InterruptedException ex) {}
            if (!this.m_fAllLoaded) {
                this.stop();
                this.m_Graphics.drawString("Error loading images!", 10, 40);
                return;
            }
        }
        this.randomizeTrack();
        this.randomizeDrivers();
        this.startTime = System.currentTimeMillis();
        this.levtime = this.level * 14 + 40;
        this.time = (int)((System.currentTimeMillis() - this.startTime) / 1000L);
        this.cpoint = 2 * (this.level * 70);
        double n = 0.0;
    Label_0249_Outer:
        while (true) {
            while (true) {
                try {
                    while (true) {
                        this.sleeper = System.currentTimeMillis();
                        final int facing = this.facing;
                        if (this.running || this.autopilot) {
                            this.nextFrame(facing + this.roadFacing);
                            ++n;
                            if (!this.autopilot) {
                                if (this.lives < 1) {
                                    this.lose();
                                }
                                if (this.carPosition < -500 || this.carPosition > 500) {
                                    --this.lives;
                                    this.wrecked = true;
                                }
                                if (this.levtime > 0 && this.trackLoc > this.cpoint) {
                                    this.ending();
                                }
                                if (this.upcomingCars[1][0] != 999) {
                                    final int n2 = this.driverDir[this.upcomingCars[1][0]];
                                    final int n3 = -200 + n2 * 400;
                                    int n4 = 300;
                                    if (this.driverCar[this.upcomingCars[1][0]] == 2) {
                                        n4 = 150;
                                    }
                                    if (this.carPosition < n3 + n4 && n2 == 0) {
                                        --this.lives;
                                        this.wrecked = true;
                                    }
                                    if (this.carPosition > n3 - n4 && n2 == 1) {
                                        --this.lives;
                                        this.wrecked = true;
                                    }
                                }
                                if (this.upcomingCars[1][1] != 999) {
                                    final int n5 = this.driverDir[this.upcomingCars[1][1]];
                                    final int n6 = -200 + n5 * 400;
                                    int n7 = 300;
                                    if (this.driverCar[this.upcomingCars[1][1]] == 2) {
                                        n7 = 150;
                                    }
                                    if (this.carPosition < n6 + n7 && n5 == 0) {
                                        this.wrecked = true;
                                    }
                                    if (this.carPosition > n6 - n7 && n5 == 1) {
                                        this.wrecked = true;
                                        --this.lives;
                                    }
                                }
                                if (this.upcomingCars[0][0] != 999) {
                                    final int n8 = this.driverDir[this.upcomingCars[0][0]];
                                    final int n9 = -200 + n8 * 400;
                                    int n10 = 300;
                                    if (this.driverCar[this.upcomingCars[0][0]] == 2) {
                                        n10 = 150;
                                    }
                                    if (this.carPosition < n9 + n10 && n8 == 0) {
                                        this.wrecked = true;
                                    }
                                    if (this.carPosition > n9 - n10 && n8 == 1) {
                                        --this.lives;
                                        this.wrecked = true;
                                    }
                                }
                                if (this.upcomingCars[0][1] != 999) {
                                    final int n11 = this.driverDir[this.upcomingCars[0][1]];
                                    final int n12 = -200 + n11 * 400;
                                    int n13 = 300;
                                    if (this.driverCar[this.upcomingCars[0][1]] == 2) {
                                        n13 = 150;
                                    }
                                    if (this.carPosition < n12 + n13 && n11 == 0) {
                                        --this.lives;
                                        this.wrecked = true;
                                    }
                                    if (this.carPosition > n12 - n13 && n11 == 1) {
                                        --this.lives;
                                        this.wrecked = true;
                                    }
                                }
                            }
                            if (this.wrecked) {
                                this.speed = 0;
                                this.running = false;
                            }
                            if (200.0 / (this.speed + 1) < n && this.speed > 0) {
                                n -= 200.0 / (this.speed + 1);
                                ++this.trackLoc;
                                this.roadFacing -= this.track[this.trackLoc];
                                if (this.autopilot) {
                                    this.facing += this.track[this.trackLoc];
                                }
                                this.facingZ += this.trackZ[this.trackLoc];
                                if (this.trackLoc > 5000) {
                                    this.running = false;
                                    this.speed = 0;
                                }
                                this.carPosition += (this.facing + this.roadFacing) * this.speed / 20;
                            }
                            if (!this.wrecked) {
                                this.updateDrivers();
                            }
                            else {
                                this.offscreenGraphics.drawImage(this.cracked, 0, 0, 400, 300, this);
                                if (this.soundvar == 1) {
                                    this.sound2.play();
                                }
                            }
                        }
                        if (this.wrecked) {
                            this.dimImage();
                            ++this.dimmer;
                            if (this.dimmer > 50) {
                                this.offscreenGraphics.setFont(this.startMouse);
                                this.offscreenGraphics.setColor(Color.cyan);
                                this.offscreenGraphics.drawString("Click mouse to restart", 115, 150);
                                this.copyRight();
                            }
                        }
                        if (this.autopilot) {
                            this.offscreenGraphics.setColor(Color.yellow);
                            this.offscreenGraphics.setFont(this.title);
                            this.offscreenGraphics.drawString("Night Driver", 120, 60);
                            this.offscreenGraphics.setFont(this.updateFont);
                            this.offscreenGraphics.drawString("Version 3.0", 175, 75);
                            this.offscreenGraphics.setColor(Color.white);
                            this.offscreenGraphics.setFont(this.startMouse);
                            this.offscreenGraphics.drawString("Click with the mouse to start", 80, 100);
                            this.offscreenGraphics.setFont(this.updateFont);
                            this.offscreenGraphics.drawString("A - Gas", 180, 130);
                            this.offscreenGraphics.drawString("Z - Brake", 175, 150);
                            this.offscreenGraphics.drawString("LEFT/RIGHT ARROWS - Turn", 120, 170);
                            this.offscreenGraphics.drawString("S - Sound on/off", 175, 190);
                            this.copyRight();
                        }
                        long n14 = 80L - (System.currentTimeMillis() - this.sleeper);
                        this.repaint();
                        if (n14 < 10L) {
                            n14 = 10L;
                        }
                        Thread.sleep(n14);
                        if (this.left) {
                            this.facing -= 2;
                            if (this.facing - this.roadFacing == -1) {
                                this.facing = this.roadFacing;
                            }
                        }
                        if (this.right) {
                            this.facing += 5;
                            if (this.facing - this.roadFacing == 1) {
                                this.facing = this.roadFacing;
                            }
                        }
                        if (this.gas) {
                            this.speed += 300 / (this.speed + 20) + 1;
                            if (this.speed > 200) {
                                this.speed = 200;
                            }
                        }
                        if (this.brake) {
                            this.speed -= 700 / (this.speed + 5) + 2;
                            if (this.speed >= 0) {
                                continue Label_0249_Outer;
                            }
                            this.speed = 0;
                        }
                    }
                }
                catch (InterruptedException ex2) {
                    this.stop();
                    continue Label_0249_Outer;
                }
                continue;
            }
        }
    }
    
    public void start() {
        if (this.m_DUI == null) {
            this.sound.loop();
            this.soundvar = 1;
            (this.m_DUI = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.sound.stop();
        if (this.m_DUI != null) {
            this.m_DUI.stop();
            this.m_DUI = null;
        }
    }
    
    public void update(final Graphics graphics) {
        graphics.drawImage(this.offscreenImage, 0, 0, this);
    }
    
    private void updateDrivers() {
        ++this.driverCount;
        if (this.driverCount > 3) {
            for (int i = 0; i < 50; ++i) {
                this.driverCount = 0;
                if (this.driverDir[i] == 0) {
                    final int[] driverY = this.driverY;
                    final int n = i;
                    --driverY[n];
                    if (this.driverY[i] == -1) {
                        this.driverY[i] = 4999;
                    }
                }
                else {
                    final int[] driverY2 = this.driverY;
                    final int n2 = i;
                    ++driverY2[n2];
                    if (this.driverY[i] == 5000) {
                        this.driverY[i] = 0;
                    }
                }
            }
        }
    }
    
    public NightDriver() {
        this.str = "http://www.4webgames.com";
        this.b1 = new Button("check my homepage and search for more games");
        this.level = 1;
        this.lives = 3;
        this.speed = 180;
        this.wrecked = false;
        this.m_fAllLoaded = false;
        this.dn = 2;
        this.autopilot = true;
        this.cars = new Image[3][2];
        this.driverY = new int[50];
        this.signs = new Image[3];
        this.driverCar = new int[50];
        this.driverDir = new int[50];
        this.upcomingCars = new int[21][2];
        this.running = false;
        this.road = new Color[21];
        this.darkRoad = new Color[21];
        this.darkLines = new Color[21];
        this.hill = new Color[21];
        this.barricade = new Color[21];
        this.tunnel = new Color[21];
        this.wood = new Color[21];
        this.xvals = new int[4];
        this.yvals = new int[4];
        this.xm = new int[6];
        this.ym = new int[6];
    }
}
