import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Driver extends Applet implements Runnable
{
    private Thread m_DUI;
    private Graphics m_Graphics;
    private Image offscreenImage;
    private Image cracked;
    private Image backdrop;
    private Image post;
    private Image[] signs;
    private Image[][] cars;
    private Image tree;
    private Image bush;
    private Graphics finishedGraphics;
    private Graphics offscreenGraphics;
    private Color[] road;
    private Color[] hill;
    private Color[] barricade;
    private Color[] wood;
    private Font updateFont;
    private Font startMouse;
    private Font tooSlow;
    private int[] xvals;
    private int[] yvals;
    private int[] xm;
    private int[] ym;
    private int[] track;
    private int[] trackZ;
    private int carPosition;
    private int facing;
    private int roadFacing;
    private int trackLoc;
    private int speed;
    private boolean wrecked;
    private double[] zoom;
    private boolean m_fAllLoaded;
    private int[] driverY;
    private int[] driverCar;
    private int[] driverDir;
    private int driverCount;
    private int[][] upcomingCars;
    private int[][] roadSigns;
    private int[] roadType;
    private int[] trees;
    private int[] bushes;
    private int facingZ;
    private long sleeper;
    public boolean running;
    public boolean autopilot;
    public int dimmer;
    private long startTime;
    private boolean gas;
    private boolean brake;
    private boolean left;
    private boolean right;
    private Color[] tunnel;
    private Font title;
    private Color[] darkRoad;
    private Color[] darkLines;
    
    public void stop() {
        if (this.m_DUI != null) {
            this.m_DUI.stop();
            this.m_DUI = null;
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.offscreenImage, 0, 0, this);
    }
    
    public void destroy() {
    }
    
    public void newGame() {
        this.carPosition = 100;
        this.facing = 0;
        this.roadFacing = 0;
        this.trackLoc = 0;
        this.speed = 0;
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
        int n4 = 1;
        do {
            n2 += this.track[n4 + this.trackLoc];
            n3 += this.trackZ[n4 + this.trackLoc];
            for (int k = n4; k < 25; ++k) {
                final int[] array3 = array;
                final int n5 = k;
                array3[n5] += n2;
                final int[] array4 = array2;
                final int n6 = k;
                array4[n6] += n3;
            }
        } while (++n4 < 21);
        int n7 = 0;
        do {
            this.upcomingCars[n7][0] = 999;
            this.upcomingCars[n7][1] = 999;
        } while (++n7 < 21);
        int n8 = 0;
        do {
            if (this.driverY[n8] >= this.trackLoc && this.driverY[n8] <= this.trackLoc + 20) {
                if (this.upcomingCars[this.driverY[n8] - this.trackLoc][0] == 999) {
                    this.upcomingCars[this.driverY[n8] - this.trackLoc][0] = n8;
                }
                else {
                    this.upcomingCars[this.driverY[n8] - this.trackLoc][1] = n8;
                }
            }
        } while (++n8 < 50);
        int n9 = 20;
        do {
            final int n10 = array[n9] - this.carPosition / (n9 + 1) + 200 - n * n9;
            final int n11 = array[n9 - 1] - this.carPosition / n9 + 200 - n * n9 + n;
            this.xvals[0] = n10 - (int)(this.zoom[n9] * 4.0);
            this.xvals[1] = n10 + (int)(this.zoom[n9] * 4.0);
            this.xvals[3] = n11 - (int)(this.zoom[n9 - 1] * 4.0);
            this.xvals[2] = n11 + (int)(this.zoom[n9 - 1] * 4.0);
            this.yvals[0] = 200 + (int)(this.zoom[n9] + array2[n9]);
            this.yvals[1] = this.yvals[0];
            this.yvals[2] = 200 + (int)(this.zoom[n9 - 1] + array2[n9 - 1]);
            this.yvals[3] = this.yvals[2];
            final int n12 = (int)(this.zoom[n9] * 4.0);
            final int n13 = (int)(this.zoom[n9 - 1] * 4.0);
            if (this.roadType[this.trackLoc + n9] == 0) {
                this.xm[0] = this.xvals[0];
                this.ym[0] = this.yvals[0];
                this.xm[1] = this.xvals[3];
                this.ym[1] = this.yvals[3];
                this.xm[2] = this.xm[1];
                this.ym[2] = 300;
                this.xm[3] = this.xm[0] - (300 - this.yvals[0]);
                this.ym[3] = 300;
                this.offscreenGraphics.setColor(this.hill[n9]);
                this.offscreenGraphics.fillPolygon(this.xm, this.ym, 4);
            }
            if (this.roadType[this.trackLoc + n9] == 1) {
                this.xm[0] = this.xvals[3];
                this.ym[0] = this.yvals[3];
                this.xm[1] = this.xvals[3];
                this.ym[1] = this.yvals[3] - (int)this.zoom[n9 - 1];
                this.xm[2] = this.xvals[0];
                this.ym[2] = this.yvals[0] - (int)this.zoom[n9];
                this.xm[3] = this.xvals[0];
                this.ym[3] = this.yvals[0];
                this.offscreenGraphics.setColor(this.barricade[n9]);
                this.offscreenGraphics.fillPolygon(this.xm, this.ym, 4);
            }
            if (this.roadType[this.trackLoc + n9] == 2) {
                this.xm[0] = this.xvals[3];
                this.ym[0] = this.yvals[3];
                this.xm[1] = this.xvals[3];
                this.ym[1] = this.yvals[3] - (int)(this.zoom[n9 - 1] * 5.0);
                this.xm[2] = this.xvals[0];
                this.ym[2] = this.yvals[0] - (int)(this.zoom[n9] * 5.0);
                this.xm[3] = this.xvals[0];
                this.ym[3] = this.yvals[0];
                this.offscreenGraphics.setColor(this.tunnel[n9]);
                this.offscreenGraphics.fillPolygon(this.xm, this.ym, 4);
            }
            if (this.yvals[2] > this.yvals[0]) {
                this.xm[0] = n10 - n12;
                this.xm[1] = n10 + n12;
                this.xm[2] = n11 + n13;
                this.xm[3] = n11 - n13;
                final float n14 = (this.trackLoc + n9) / 100.0f;
                if (n14 == Math.round(n14)) {
                    this.offscreenGraphics.setColor(new Color(255 - n9 * 2, 0, 0));
                }
                else if (this.trackLoc + n9 > 5000) {
                    this.offscreenGraphics.setColor(new Color(0, 255 - n9 * 4, 0));
                }
                else {
                    if (this.roadType[this.trackLoc + n9] == 1) {
                        this.offscreenGraphics.setColor(this.wood[n9]);
                    }
                    if (this.roadType[this.trackLoc + n9] == 2) {
                        this.offscreenGraphics.setColor(this.darkRoad[n9]);
                    }
                    if (this.roadType[this.trackLoc + n9] == 0) {
                        this.offscreenGraphics.setColor(this.road[n9]);
                    }
                }
                this.offscreenGraphics.fillPolygon(this.xm, this.yvals, 4);
                final int n15 = this.trackLoc + n9;
                if (n15 / 2.0f == n15 / 2) {
                    this.xm[0] = n10 - (int)(this.zoom[n9] / 8.0) - 1;
                    this.xm[1] = n10 + (int)(this.zoom[n9] / 8.0) + 1;
                    this.xm[3] = n11 - (int)(this.zoom[n9 - 1] / 8.0) - 1;
                    this.xm[2] = n11 + (int)(this.zoom[n9 - 1] / 8.0) + 1;
                    this.ym[0] = this.yvals[0];
                    this.ym[1] = this.yvals[0];
                    this.ym[2] = this.yvals[2];
                    this.ym[3] = this.yvals[2];
                    if (this.roadType[this.trackLoc + n9] == 2) {
                        this.offscreenGraphics.setColor(this.darkLines[n9]);
                    }
                    else {
                        this.offscreenGraphics.setColor(Color.yellow);
                    }
                    this.offscreenGraphics.fillPolygon(this.xm, this.ym, 4);
                }
            }
            if (this.roadType[this.trackLoc + n9] == 0) {
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
                this.offscreenGraphics.setColor(this.hill[n9]);
                this.offscreenGraphics.fillPolygon(this.xm, this.ym, 6);
            }
            if (this.roadType[this.trackLoc + n9] == 1) {
                this.xm[0] = this.xvals[2];
                this.ym[0] = this.yvals[2];
                this.xm[1] = this.xvals[2];
                this.ym[1] = this.yvals[2] - (int)this.zoom[n9 - 1];
                this.xm[2] = this.xvals[1];
                this.ym[2] = this.yvals[1] - (int)this.zoom[n9];
                this.xm[3] = this.xvals[1];
                this.ym[3] = this.yvals[1];
                this.offscreenGraphics.setColor(this.barricade[n9]);
                this.offscreenGraphics.fillPolygon(this.xm, this.ym, 4);
            }
            if (this.roadType[this.trackLoc + n9] == 2) {
                this.xm[0] = this.xvals[2];
                this.ym[0] = this.yvals[2];
                this.xm[1] = this.xvals[2];
                this.ym[1] = this.yvals[2] - (int)(this.zoom[n9 - 1] * 5.0);
                this.xm[2] = this.xvals[1];
                this.ym[2] = this.yvals[1] - (int)(this.zoom[n9] * 5.0);
                this.xm[3] = this.xvals[1];
                this.ym[3] = this.yvals[1];
                this.offscreenGraphics.setColor(this.tunnel[n9]);
                this.offscreenGraphics.fillPolygon(this.xm, this.ym, 4);
                this.xm[0] = this.xvals[0];
                this.xm[1] = this.xvals[1];
                this.xm[2] = this.xvals[2];
                this.xm[3] = this.xvals[3];
                this.ym[0] = this.yvals[0] - (int)(this.zoom[n9] * 5.0);
                this.ym[1] = this.ym[0];
                this.ym[2] = this.yvals[2] - (int)(this.zoom[n9 - 1] * 5.0);
                this.ym[3] = this.ym[2];
                this.offscreenGraphics.fillPolygon(this.xm, this.ym, 4);
                if (this.xvals[0] > 0) {
                    this.xm[0] = this.xvals[3];
                    this.ym[0] = this.yvals[3] - (int)(this.zoom[n9 - 1] * 10.0);
                    this.xm[1] = this.xm[0];
                    this.ym[1] = 300;
                    this.xm[2] = this.xm[0] - (300 - this.ym[0]);
                    this.ym[2] = 300;
                    this.offscreenGraphics.setColor(this.hill[n9]);
                    this.offscreenGraphics.fillPolygon(this.xm, this.ym, 3);
                    this.ym[1] = this.yvals[3] - (int)(this.zoom[n9 - 1] * 5.0);
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
            if (this.upcomingCars[n9][0] != 999) {
                final int n16 = this.upcomingCars[n9][0];
                this.offscreenGraphics.drawImage(this.cars[this.driverCar[n16]][this.driverDir[n16]], n11 + n13 * (this.driverDir[n16] - 1) + n13 / 8, this.yvals[2] - n13 * 3 / 4, n13 * 3 / 4, n13 * 3 / 4, this);
            }
            if (this.upcomingCars[n9][1] != 999) {
                final int n17 = this.upcomingCars[n9][1];
                this.offscreenGraphics.drawImage(this.cars[this.driverCar[n17]][this.driverDir[n17]], n11 + n13 * (this.driverDir[n17] - 1) + n13 / 8, this.yvals[2] - n13 * 3 / 4, n13 * 3 / 4, n13 * 3 / 4, this);
            }
            if (this.roadType[this.trackLoc + n9] == 0) {
                if (this.roadSigns[this.trackLoc + n9][0] > 0) {
                    final int n18 = n11 - n13 * 2;
                    final int n19 = this.yvals[2] - (int)(this.zoom[n9 - 1] * 3.0);
                    this.offscreenGraphics.drawImage(this.signs[this.roadSigns[this.trackLoc + n9][0] - 1], n18, n19, n13, n13 / 3, this);
                    this.offscreenGraphics.drawImage(this.post, n18 + n13 * 19 / 40, n19 + n13 / 3, n13 / 20, n13, this);
                }
                if (this.roadSigns[this.trackLoc + n9][1] > 0) {
                    final int n20 = this.xvals[2];
                    final int n21 = this.yvals[2] - (int)(this.zoom[n9 - 1] * 6.0);
                    this.offscreenGraphics.drawImage(this.signs[this.roadSigns[this.trackLoc + n9][1] - 1], n20, n21, n13, n13 / 3, this);
                    this.offscreenGraphics.drawImage(this.post, n20 + n13 * 19 / 40, n21 + n13 / 3, n13 / 20, n13, this);
                }
                if (this.trees[this.trackLoc + n9] > 0) {
                    final int n22 = n11 + (int)(this.zoom[n9 - 1] * (this.trees[this.trackLoc + n9] + 1));
                    final int n23 = this.yvals[2] - (int)(this.zoom[n9 - 1] * (this.trees[this.trackLoc + n9] + 6));
                    if (n22 < 400) {
                        this.offscreenGraphics.drawImage(this.tree, n22, n23, (int)(this.zoom[n9 - 1] * 4.0), (int)(this.zoom[n9 - 1] * 7.0), this);
                    }
                }
                if (this.bushes[this.trackLoc + n9] > 0) {
                    final int n24 = n11 + (int)(this.zoom[n9 - 1] * (this.bushes[this.trackLoc + n9] + 1));
                    final int n25 = this.yvals[2] - (int)(this.zoom[n9 - 1] * (this.bushes[this.trackLoc + n9] + 6));
                    if (n24 < 400) {
                        this.offscreenGraphics.drawImage(this.bush, n24, n25, (int)(this.zoom[n9 - 1] * 4.0), (int)(this.zoom[n9 - 1] * 7.0), this);
                    }
                }
                if (this.trees[this.trackLoc + n9] == -1) {
                    final int n26 = n11 - (int)(this.zoom[n9 - 1] * 9.0);
                    final int n27 = this.yvals[2] - (int)(this.zoom[n9 - 1] * 4.0);
                    if (n26 > -(int)(this.zoom[n9 - 1] * 4.0)) {
                        this.offscreenGraphics.drawImage(this.tree, n26, n27, (int)(this.zoom[n9 - 1] * 4.0), (int)(this.zoom[n9 - 1] * 7.0), this);
                    }
                }
                if (this.bushes[this.trackLoc + n9] != -1) {
                    continue;
                }
                final int n28 = n11 - (int)(this.zoom[n9 - 1] * 9.0);
                final int n29 = this.yvals[2] - (int)(this.zoom[n9 - 1] * 4.0);
                if (n28 <= -(int)(this.zoom[n9 - 1] * 4.0)) {
                    continue;
                }
                this.offscreenGraphics.drawImage(this.bush, n28, n29, (int)(this.zoom[n9 - 1] * 4.0), (int)(this.zoom[n9 - 1] * 7.0), this);
            }
        } while (--n9 > 0);
        this.offscreenGraphics.setColor(Color.white);
        this.offscreenGraphics.setFont(this.updateFont);
        this.offscreenGraphics.drawString("Checkpoints: " + this.trackLoc / 100 + "  Speed: " + this.speed / 2 + " Driving Time: " + (int)((System.currentTimeMillis() - this.startTime) / 1000L), 5, 15);
    }
    
    private void copyRight() {
        this.offscreenGraphics.setFont(new Font("TimesNewRoman", 1, 12));
        this.offscreenGraphics.setColor(Color.white);
        this.offscreenGraphics.drawString("(C)1999 Derek L. Ramey", 125, 260);
        this.offscreenGraphics.drawString("All Rights Reserved.", 125, 270);
        this.offscreenGraphics.drawString("http://derekramey.virtualave.net", 125, 280);
        this.offscreenGraphics.drawString("Email: indybane@aol.com", 125, 290);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void updateDrivers() {
        ++this.driverCount;
        if (this.driverCount > 3) {
            int n = 0;
            do {
                this.driverCount = 0;
                if (this.driverDir[n] == 0) {
                    final int[] driverY = this.driverY;
                    final int n2 = n;
                    --driverY[n2];
                    if (this.driverY[n] != -1) {
                        continue;
                    }
                    this.driverY[n] = 4999;
                }
                else {
                    final int[] driverY2 = this.driverY;
                    final int n3 = n;
                    ++driverY2[n3];
                    if (this.driverY[n] != 5000) {
                        continue;
                    }
                    this.driverY[n] = 0;
                }
            } while (++n < 50);
        }
    }
    
    public boolean keyUp(final Event event, final int n) {
        if (n == 1006) {
            this.left = false;
            return true;
        }
        if (n == 1007) {
            this.right = false;
            return true;
        }
        if (n == 97 || n == 65) {
            this.gas = false;
            return true;
        }
        if (n == 122 || n == 90) {
            this.brake = false;
            return true;
        }
        return false;
    }
    
    public void start() {
        if (this.m_DUI == null) {
            (this.m_DUI = new Thread(this)).start();
        }
    }
    
    public void drawBG(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, 800, 600);
        graphics.setColor(Color.white);
        int n = 0;
        do {
            final int n2 = (int)Math.round(Math.random() * 800.0);
            final int n3 = (int)Math.round(Math.random() * 600.0);
            graphics.drawLine(n2, n3, n2, n3);
        } while (++n < 250);
        graphics.setColor(Color.gray);
        int n4 = 0;
        do {
            final int n5 = (int)Math.round(Math.random() * 800.0);
            final int n6 = (int)Math.round(Math.random() * 600.0);
            graphics.drawLine(n5, n6, n5, n6);
        } while (++n4 < 750);
    }
    
    public String getAppletInfo() {
        return "Name: DUI\r\n" + "Author: Derek L. Ramey, MCP\r\n" + "Created with Microsoft Visual J++ Version 1.1";
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.running) {
            this.newGame();
            this.running = true;
        }
        return true;
    }
    
    public void randomizeTrack() {
        this.track = new int[5100];
        this.trackZ = new int[5100];
        this.roadType = new int[5100];
        this.roadSigns = new int[5100][2];
        this.trees = new int[5100];
        this.bushes = new int[5100];
        int n = 0;
        do {
            this.roadSigns[n * 200 + (int)(Math.random() * 200.0)][(int)(Math.random() * 2.0)] = (int)(Math.random() * 3.0) + 1;
        } while (++n < 25);
        int n2 = 0;
        do {
            final int n3 = n2 * 50 + (int)(Math.random() * 50.0);
            int n4 = 0;
            switch ((int)(Math.random() * 3.0)) {
                case 0: {
                    n4 = (int)(Math.random() * 5.0) + 1;
                    break;
                }
                case 1: {
                    n4 = 1;
                    break;
                }
                case 2: {
                    n4 = -1;
                    break;
                }
            }
            this.trees[n3] = n4;
        } while (++n2 < 100);
        int n5 = 0;
        do {
            final int n6 = (int)(Math.random() * 5000.0);
            final int n7 = (int)(Math.random() * 2.0) + 1;
            int n8 = 1;
            if (n7 == 1) {
                n8 = (int)(Math.random() * 10.0) + 5;
            }
            if (n7 == 2) {
                n8 = (int)(Math.random() * 25.0) + 10;
            }
            for (int i = 0; i < n8; ++i) {
                this.roadType[n6 + i] = n7;
            }
        } while (++n5 < 50);
        int n9 = 0;
        do {
            final int n10 = n9 * 50 + (int)(Math.random() * 50.0);
            int n11 = 0;
            switch ((int)(Math.random() * 3.0)) {
                case 0: {
                    n11 = (int)(Math.random() * 5.0) + 1;
                    break;
                }
                case 1: {
                    n11 = 1;
                    break;
                }
                case 2: {
                    n11 = -1;
                    break;
                }
            }
            this.bushes[n10] = n11;
        } while (++n9 < 100);
        int n12 = 1;
        do {
            int n13 = 0;
            int n14 = 0;
            int n15 = 0;
            int n16 = 0;
            switch (n12) {
                case 1: {
                    n13 = 0;
                    n14 = 500;
                    n15 = 1;
                    n16 = 1;
                    break;
                }
                case 2: {
                    n13 = 500;
                    n14 = 1500;
                    n15 = 2;
                    n16 = 1;
                    break;
                }
                case 3: {
                    n13 = 1500;
                    n14 = 2500;
                    n15 = 3;
                    n16 = 2;
                    break;
                }
                case 4: {
                    n13 = 2500;
                    n14 = 3500;
                    n15 = 4;
                    n16 = 2;
                    break;
                }
                default: {
                    n13 = 3500;
                    n14 = 5000;
                    n15 = 5;
                    n16 = 3;
                    break;
                }
            }
            for (int j = 0; j < n12 * 30; ++j) {
                final int n17 = (int)(Math.random() * (n14 - n13) + n13);
                switch ((int)(Math.random() * 10.0)) {
                    case 0:
                    case 6:
                    case 7: {
                        this.track[n17] = (int)(Math.random() * (n15 * 2 + 1)) - n15;
                        break;
                    }
                    case 1: {
                        this.track[n17] = -n15;
                        this.track[n17 + 1] = -(n15 * 2);
                        this.track[n17 + 2] = -(n15 * 3);
                        this.track[n17 + 3] = -(n15 * 2);
                        this.track[n17 + 4] = -n15;
                        break;
                    }
                    case 2: {
                        this.track[n17] = n15;
                        this.track[n17 + 1] = n15 * 2;
                        this.track[n17 + 2] = n15 * 3;
                        this.track[n17 + 3] = n15 * 2;
                        this.track[n17 + 4] = n15 * 1;
                        break;
                    }
                    case 3:
                    case 8:
                    case 9: {
                        this.trackZ[n17] = (int)(Math.random() * (n16 * 2 + 1) - n16) / 2;
                        break;
                    }
                    case 4: {
                        this.trackZ[n17] = -n16;
                        this.trackZ[n17 + 1] = -(n16 * 2);
                        this.trackZ[n17 + 2] = -n16;
                        break;
                    }
                    case 5: {
                        this.trackZ[n17] = n16;
                        this.trackZ[n17 + 1] = n16 * 2;
                        this.trackZ[n17 + 2] = n16;
                        break;
                    }
                }
            }
        } while (++n12 < 6);
    }
    
    public Driver() {
        this.speed = 180;
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
    
    private void dimImage() {
        this.offscreenGraphics.setColor(Color.black);
        int n = 0;
        do {
            this.offscreenGraphics.fillRect((int)(Math.random() * 400.0), (int)(Math.random() * 300.0), 4, 4);
        } while (++n < 1000);
    }
    
    private void randomizeDrivers() {
        int n = 0;
        do {
            this.driverDir[n] = (int)(Math.random() * 2.0);
            this.driverY[n] = (int)(Math.random() * 4990.0) + 10;
            this.driverCar[n] = (int)(Math.random() * 3.0);
        } while (++n < 50);
    }
    
    public final void run() {
        if (!this.m_fAllLoaded) {
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(this.cracked, 0);
            mediaTracker.addImage(this.post, 0);
            mediaTracker.addImage(this.tree, 0);
            mediaTracker.addImage(this.bush, 0);
            int n = 0;
            do {
                mediaTracker.addImage(this.signs[n], 0);
            } while (++n < 3);
            int n2 = 0;
            do {
                int n3 = 0;
                do {
                    mediaTracker.addImage(this.cars[n2][n3], 0);
                } while (++n3 < 2);
            } while (++n2 < 3);
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
        double n4 = 0.0;
    Label_0173_Outer:
        while (true) {
            while (true) {
                try {
                    while (true) {
                        this.sleeper = System.currentTimeMillis();
                        final int facing = this.facing;
                        if (this.running || this.autopilot) {
                            this.nextFrame(facing + this.roadFacing);
                            ++n4;
                            if (!this.autopilot) {
                                if (this.carPosition < -500 || this.carPosition > 500) {
                                    this.wrecked = true;
                                }
                                if (this.upcomingCars[1][0] != 999) {
                                    final int n5 = this.driverDir[this.upcomingCars[1][0]];
                                    final int n6 = -200 + n5 * 400;
                                    int n7 = 300;
                                    if (this.driverCar[this.upcomingCars[1][0]] == 2) {
                                        n7 = 150;
                                    }
                                    if (this.carPosition < n6 + n7 && n5 == 0) {
                                        this.wrecked = true;
                                    }
                                    if (this.carPosition > n6 - n7 && n5 == 1) {
                                        this.wrecked = true;
                                    }
                                }
                                if (this.upcomingCars[1][1] != 999) {
                                    final int n8 = this.driverDir[this.upcomingCars[1][1]];
                                    final int n9 = -200 + n8 * 400;
                                    int n10 = 300;
                                    if (this.driverCar[this.upcomingCars[1][1]] == 2) {
                                        n10 = 150;
                                    }
                                    if (this.carPosition < n9 + n10 && n8 == 0) {
                                        this.wrecked = true;
                                    }
                                    if (this.carPosition > n9 - n10 && n8 == 1) {
                                        this.wrecked = true;
                                    }
                                }
                                if (this.upcomingCars[0][0] != 999) {
                                    final int n11 = this.driverDir[this.upcomingCars[0][0]];
                                    final int n12 = -200 + n11 * 400;
                                    int n13 = 300;
                                    if (this.driverCar[this.upcomingCars[0][0]] == 2) {
                                        n13 = 150;
                                    }
                                    if (this.carPosition < n12 + n13 && n11 == 0) {
                                        this.wrecked = true;
                                    }
                                    if (this.carPosition > n12 - n13 && n11 == 1) {
                                        this.wrecked = true;
                                    }
                                }
                                if (this.upcomingCars[0][1] != 999) {
                                    final int n14 = this.driverDir[this.upcomingCars[0][1]];
                                    final int n15 = -200 + n14 * 400;
                                    int n16 = 300;
                                    if (this.driverCar[this.upcomingCars[0][1]] == 2) {
                                        n16 = 150;
                                    }
                                    if (this.carPosition < n15 + n16 && n14 == 0) {
                                        this.wrecked = true;
                                    }
                                    if (this.carPosition > n15 - n16 && n14 == 1) {
                                        this.wrecked = true;
                                    }
                                }
                            }
                            if (this.wrecked) {
                                this.speed = 0;
                                this.running = false;
                            }
                            if (200.0 / (this.speed + 1) < n4 && this.speed > 0) {
                                n4 -= 200.0 / (this.speed + 1);
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
                            this.offscreenGraphics.setColor(Color.blue);
                            this.offscreenGraphics.setFont(this.title);
                            this.offscreenGraphics.drawString("3D Driver", 120, 60);
                            this.offscreenGraphics.setFont(this.updateFont);
                            this.offscreenGraphics.drawString("Version 1.2", 175, 75);
                            this.offscreenGraphics.setColor(Color.white);
                            this.offscreenGraphics.setFont(this.startMouse);
                            this.offscreenGraphics.drawString("Click with the mouse to start", 80, 100);
                            this.offscreenGraphics.setFont(this.updateFont);
                            this.offscreenGraphics.drawString("A - Gas", 180, 130);
                            this.offscreenGraphics.drawString("Z - Brake", 175, 150);
                            this.offscreenGraphics.drawString("LEFT/RIGHT ARROWS - Turn", 120, 170);
                            this.copyRight();
                        }
                        long n17 = 80L - (System.currentTimeMillis() - this.sleeper);
                        this.repaint();
                        if (n17 < 10L) {
                            n17 = 10L;
                        }
                        Thread.sleep(n17);
                        if (this.left) {
                            this.facing -= 2;
                            if (this.facing - this.roadFacing == -1) {
                                this.facing = this.roadFacing;
                            }
                        }
                        if (this.right) {
                            this.facing += 2;
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
                                continue Label_0173_Outer;
                            }
                            this.speed = 0;
                        }
                    }
                }
                catch (InterruptedException ex2) {
                    this.stop();
                    continue Label_0173_Outer;
                }
                continue;
            }
        }
    }
    
    public void init() {
        this.resize(400, 300);
        this.zoom = new double[26];
        int n = 0;
        do {
            this.zoom[n] = 200.0 / (n + 1) - 8.0;
        } while (++n < 25);
        this.offscreenImage = this.createImage(400, 300);
        this.offscreenGraphics = this.offscreenImage.getGraphics();
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
        this.tree = this.getImage(this.getCodeBase(), "tree1.gif");
        this.bush = this.getImage(this.getCodeBase(), "tree2.gif");
        this.post = this.getImage(this.getCodeBase(), "post.gif");
        this.backdrop = this.createImage(800, 600);
        this.drawBG(this.backdrop.getGraphics());
        this.updateFont = new Font("TimesNewRoman", 1, 12);
        this.startMouse = new Font("TimesNewRoman", 1, 18);
        this.tooSlow = new Font("TimesNewRoman", 1, 32);
        this.title = new Font("Serif", 3, 42);
        int n2 = 0;
        do {
            this.hill[n2] = new Color(0, 196 - n2 * 5, 0);
            this.road[n2] = new Color(150 - n2 * 4, 150 - n2 * 4, 150 - n2 * 4);
            this.barricade[n2] = new Color(255 - n2 * 6, 255 - n2 * 6, 255 - n2 * 6);
            this.wood[n2] = new Color(150 - n2 * 4, 100 - n2 * 3, 50 - n2 * 2);
            this.tunnel[n2] = new Color(75 - n2 * 3, 50 - n2 * 2, 50 - n2 * 2);
            this.darkRoad[n2] = new Color(75 - n2 * 3, 75 - n2 * 3, 75 - n2 * 3);
            this.darkLines[n2] = new Color(150 - n2 * 4, 150 - n2 * 4, 0);
        } while (++n2 < 21);
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
        return (n == 122 || n == 90) && (this.brake = true);
    }
}
