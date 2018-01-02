import sun.audio.AudioPlayer;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Toolkit;
import java.io.IOException;
import sun.audio.AudioDataStream;
import sun.audio.AudioData;
import java.awt.Component;
import java.io.InputStream;
import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.image.ImageProducer;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class cluster extends Applet implements Runnable, MouseListener, MouseMotionListener
{
    int i;
    int j;
    int k;
    int l;
    int m;
    int n;
    int x;
    int y;
    int lastX;
    int lastY;
    int gameMode;
    int ballnum;
    int tempball;
    int ballMidX;
    int ballMidY;
    int ballR;
    int dragOffsX;
    int dragOffsY;
    int busycount;
    int playing;
    int ip;
    int jp;
    int kp;
    int lp;
    int is;
    int js;
    int ks;
    int fronts;
    int backs;
    int id;
    int jd;
    int kd;
    int ipt;
    int idc;
    int mi;
    int mj;
    int mk;
    int wi;
    int dragging;
    boolean busy;
    boolean drawing;
    double ap;
    double bp;
    double cp;
    double as;
    double an;
    double amd;
    double adc;
    double bdc;
    double cdc;
    double apt;
    double bpt;
    double cpt;
    double dpt;
    double dragScale;
    double[] cornX;
    double[] cornY;
    double[] cornZ;
    double[] projX;
    double[] projY;
    double[] projZ;
    double[] pntX;
    double[] pntY;
    double[] pntZ;
    double[] scrX;
    double[] scrY;
    double[] scrZ;
    double[] frontZ;
    double[] backZ;
    final double[] xa;
    final double[] ya;
    final double[] za;
    double[] xaxis;
    double[] yaxis;
    double[] zaxis;
    double[] ut;
    double[] vt;
    double[] wt;
    double[] frontDir;
    double[] boxpos;
    double[] ballX;
    double[] ballY;
    double[] ballZ;
    final double scale = 11.0;
    final double ballsize = 0.341;
    long lastClick;
    long nextTime;
    int[] faces;
    int[] bbx;
    int[] bby;
    int[] linefaces;
    int[] cubedata;
    int[] balltype;
    int[] backOrder;
    int[] frontOrder;
    int[] ballOrder;
    int[] lFrom;
    int[] lTo;
    Image b1;
    Image b2;
    Image background;
    Image[] bballs;
    Graphics bG;
    Graphics bsG;
    ImageProducer improd;
    MediaTracker mt;
    Color[] gCol;
    InputStream[] sounds;
    byte[][] sndData;
    right rightApplet;
    bottom bottomApplet;
    Thread runner;
    
    public void init() {
        this.mt = new MediaTracker(this);
        final byte[] array = new byte[28];
        this.i = 0;
        while (this.i < 2) {
            final InputStream resourceAsStream = this.getClass().getResourceAsStream("sound" + this.i + ".au");
            try {
                this.j = resourceAsStream.available() - 28;
                this.sndData[this.i] = new byte[this.j];
                this.k = 0;
                while (this.k < 28) {
                    this.k += resourceAsStream.read(array, this.k, 28 - this.k);
                }
                resourceAsStream.read(this.sndData[this.i]);
                (this.sounds[this.i] = new AudioDataStream(new AudioData(this.sndData[this.i]))).mark(this.j);
            }
            catch (IOException ex) {}
            ++this.i;
        }
        final InputStream resourceAsStream2 = this.getClass().getResourceAsStream("balls1.gif");
        try {
            final byte[] array2 = new byte[resourceAsStream2.available()];
            resourceAsStream2.read(array2);
            this.b1 = Toolkit.getDefaultToolkit().createImage(array2);
        }
        catch (IOException ex2) {
            this.b1 = this.createImage(1, 1);
        }
        this.mt.addImage(this.b1, 0);
        final InputStream resourceAsStream3 = this.getClass().getResourceAsStream("balls2.gif");
        try {
            final byte[] array3 = new byte[resourceAsStream3.available()];
            resourceAsStream3.read(array3);
            this.b2 = Toolkit.getDefaultToolkit().createImage(array3);
        }
        catch (IOException ex3) {
            this.b2 = this.createImage(1, 1);
        }
        this.mt.addImage(this.b2, 0);
        try {
            this.mt.waitForID(0);
        }
        catch (InterruptedException ex4) {}
        this.improd = this.b1.getSource();
        this.j = 0;
        this.k = 30;
        this.i = 0;
        while (this.i < 10) {
            this.bballs[this.i] = this.createImage(new FilteredImageSource(this.improd, new CropImageFilter(this.j, 0, this.k, this.k)));
            this.mt.addImage(this.bballs[this.i], 1);
            this.j += this.k;
            this.k += 2;
            ++this.i;
        }
        this.improd = this.b2.getSource();
        this.j = 0;
        this.k = 30;
        this.i = 0;
        while (this.i < 10) {
            this.bballs[this.i + 10] = this.createImage(new FilteredImageSource(this.improd, new CropImageFilter(this.j, 0, this.k, this.k)));
            this.mt.addImage(this.bballs[this.i + 10], 1);
            this.j += this.k;
            this.k += 2;
            ++this.i;
        }
        try {
            this.mt.waitForID(1);
        }
        catch (InterruptedException ex5) {}
        this.frontDir[6] = -1.0;
        this.background = this.createImage(190, 190);
        this.bG = this.background.getGraphics();
        this.buildCoordinates();
        this.buildGrays();
        this.reset();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public void reset() {
        for (int i = 0; i < 3; ++i) {
            this.xaxis[i] = this.xa[i];
            this.yaxis[i] = this.ya[i];
            this.zaxis[i] = this.za[i];
        }
        this.busy = false;
        for (int j = 0; j < 27; ++j) {
            this.cubedata[j] = 0;
        }
        while (this.rightApplet == null || this.bottomApplet == null) {
            this.rightApplet = (right)this.getAppletContext().getApplet("right");
            this.bottomApplet = (bottom)this.getAppletContext().getApplet("bottom");
        }
        this.tempball = -1;
        this.rightApplet.clearBoard();
        this.bottomApplet.setNewMessage(0);
        this.drawing = false;
        this.performDrawing();
    }
    
    public void buildCoordinates() {
        this.i = 0;
        while (this.i < 2) {
            this.j = 0;
            while (this.j < 2) {
                this.k = 0;
                while (this.k < 2) {
                    this.cornX[this.i * 4 + this.j * 2 + this.k] = 1.0 - 2.0 * (this.k & 0x1);
                    this.cornY[this.i * 4 + this.j * 2 + this.k] = 1.0 - 2.0 * (this.j & 0x1);
                    this.cornZ[this.i * 4 + this.j * 2 + this.k] = 1.0 - 2.0 * (this.i & 0x1);
                    ++this.k;
                }
                ++this.j;
            }
            ++this.i;
        }
        this.m = 0;
        this.i = 0;
        while (this.i < 8) {
            this.pntX[this.i * 4] = this.cornX[this.i];
            this.pntY[this.i * 4] = this.cornY[this.i];
            this.pntZ[this.i * 4] = this.cornZ[this.i];
            this.j = 0;
            while (this.j < 3) {
                if ((this.i & 1 << this.j) == 0x0) {
                    this.lFrom[this.m] = this.i * 4;
                    this.lTo[this.m++] = (this.i + (1 << this.j)) * 4;
                }
                ++this.j;
            }
            this.j = 0;
            while (this.j < 3) {
                this.k = (this.i & 7 - (1 << this.j)) + ((1 << this.j) - (this.i & 1 << this.j));
                this.pntX[this.i * 4 + 1 + this.j] = (this.cornX[this.i] * 2.0 + this.cornX[this.k]) / 3.0;
                this.pntY[this.i * 4 + 1 + this.j] = (this.cornY[this.i] * 2.0 + this.cornY[this.k]) / 3.0;
                this.pntZ[this.i * 4 + 1 + this.j] = (this.cornZ[this.i] * 2.0 + this.cornZ[this.k]) / 3.0;
                this.k = 0;
                while (this.k < 3) {
                    if (this.k != this.j && (this.i & 1 << this.k) == 0x0) {
                        this.lFrom[this.m] = this.i * 4 + 1 + this.j;
                        this.lTo[this.m++] = (this.i + (1 << this.k)) * 4 + 1 + this.j;
                    }
                    ++this.k;
                }
                ++this.j;
            }
            ++this.i;
        }
        this.i = 0;
        while (this.i < 36) {
            this.linefaces[this.i * 2 + 1] = 6;
            this.l = this.i * 2;
            this.j = this.lFrom[this.i];
            this.k = this.lTo[this.i];
            if (Math.abs(this.pntX[this.j] - this.pntX[this.k]) < 0.01) {
                if (this.pntX[this.j] > 0.99) {
                    this.linefaces[this.l++] = 0;
                }
                else if (this.pntX[this.j] < -0.99) {
                    this.linefaces[this.l++] = 1;
                }
            }
            if (Math.abs(this.pntY[this.j] - this.pntY[this.k]) < 0.01) {
                if (this.pntY[this.j] > 0.99) {
                    this.linefaces[this.l++] = 2;
                }
                else if (this.pntY[this.j] < -0.99) {
                    this.linefaces[this.l++] = 3;
                }
            }
            if (Math.abs(this.pntZ[this.j] - this.pntZ[this.k]) < 0.01) {
                if (this.pntZ[this.j] > 0.99) {
                    this.linefaces[this.l++] = 4;
                }
                else if (this.pntZ[this.j] < -0.99) {
                    this.linefaces[this.l++] = 5;
                }
            }
            ++this.i;
        }
        this.i = 0;
        while (this.i < 3) {
            this.j = 0;
            while (this.j < 3) {
                this.k = 0;
                while (this.k < 3) {
                    this.boxpos[this.i * 27 + this.j * 9 + this.k * 3] = 0.66667 * (1 - this.k);
                    this.boxpos[this.i * 27 + this.j * 9 + this.k * 3 + 1] = 0.66667 * (1 - this.j);
                    this.boxpos[this.i * 27 + this.j * 9 + this.k * 3 + 2] = 0.66667 * (1 - this.i);
                    ++this.k;
                }
                ++this.j;
            }
            ++this.i;
        }
    }
    
    public void project() {
        this.ip = 0;
        while (this.ip < 32) {
            this.scrZ[this.ip] = this.scalProd(this.pntX[this.ip], this.pntY[this.ip], this.pntZ[this.ip], this.zaxis[0], this.zaxis[1], this.zaxis[2]);
            this.ap = (5.0 + this.scrZ[this.ip]) * 11.0;
            this.bp = this.scalProd(this.pntX[this.ip], this.pntY[this.ip], this.pntZ[this.ip], this.xaxis[0], this.xaxis[1], this.xaxis[2]);
            this.cp = this.scalProd(this.pntX[this.ip], this.pntY[this.ip], this.pntZ[this.ip], this.yaxis[0], this.yaxis[1], this.yaxis[2]);
            this.scrX[this.ip] = 95.0 + this.ap * this.bp;
            this.scrY[this.ip] = 95.0 + this.ap * this.cp;
            if ((this.ip & 0x3) == 0x0) {
                this.jp = this.ip >> 2;
                this.projX[this.jp] = this.bp * this.ap / 50.0;
                this.projY[this.jp] = this.cp * this.ap / 50.0;
                this.projZ[this.jp] = this.scrZ[this.ip];
            }
            ++this.ip;
        }
        this.ip = 0;
        while (this.ip < 6) {
            this.jp = this.faces[this.ip * 3];
            this.kp = this.faces[this.ip * 3 + 1];
            this.lp = this.faces[this.ip * 3 + 2];
            this.ut[0] = this.projX[this.kp] - this.projX[this.jp];
            this.ut[1] = this.projY[this.kp] - this.projY[this.jp];
            this.ut[2] = this.projZ[this.kp] - this.projZ[this.jp];
            this.vt[0] = this.projX[this.lp] - this.projX[this.jp];
            this.vt[1] = this.projY[this.lp] - this.projY[this.jp];
            this.vt[2] = this.projZ[this.lp] - this.projZ[this.jp];
            this.crossProd(this.ut, this.vt, this.wt);
            this.frontDir[this.ip] = this.wt[2];
            ++this.ip;
        }
        this.ballnum = 0;
        this.ip = 0;
        while (this.ip < 27) {
            if (this.cubedata[this.ip] > 0) {
                this.balltype[this.ballnum] = this.cubedata[this.ip];
                this.ballOrder[this.ballnum] = this.ballnum;
                this.ballZ[this.ballnum] = 11.0 * (5.0 + this.scalProd(this.boxpos[this.ip * 3], this.boxpos[this.ip * 3 + 1], this.boxpos[this.ip * 3 + 2], this.zaxis[0], this.zaxis[1], this.zaxis[2]));
                this.ballX[this.ballnum] = 95.0 + this.ballZ[this.ballnum] * this.scalProd(this.boxpos[this.ip * 3], this.boxpos[this.ip * 3 + 1], this.boxpos[this.ip * 3 + 2], this.xaxis[0], this.xaxis[1], this.xaxis[2]);
                this.ballY[this.ballnum] = 95.0 + this.ballZ[this.ballnum++] * this.scalProd(this.boxpos[this.ip * 3], this.boxpos[this.ip * 3 + 1], this.boxpos[this.ip * 3 + 2], this.yaxis[0], this.yaxis[1], this.yaxis[2]);
            }
            ++this.ip;
        }
    }
    
    public void sortStuff() {
        this.fronts = 0;
        this.backs = 0;
        this.is = 0;
        while (this.is < 36) {
            if (this.frontDir[this.linefaces[this.is * 2]] > 0.0 || this.frontDir[this.linefaces[this.is * 2 + 1]] > 0.0) {
                this.frontZ[this.fronts] = this.scrZ[this.lFrom[this.is]] + this.scrZ[this.lTo[this.is]];
                this.frontOrder[this.fronts++] = this.is;
            }
            else {
                this.backZ[this.backs] = this.scrZ[this.lFrom[this.is]] + this.scrZ[this.lTo[this.is]];
                this.backOrder[this.backs++] = this.is;
            }
            ++this.is;
        }
        this.is = 0;
        while (this.is < this.fronts - 1) {
            this.js = this.is + 1;
            while (this.js < this.fronts) {
                if (this.frontZ[this.is] > this.frontZ[this.js]) {
                    this.ks = this.frontOrder[this.is];
                    this.frontOrder[this.is] = this.frontOrder[this.js];
                    this.frontOrder[this.js] = this.ks;
                    this.as = this.frontZ[this.is];
                    this.frontZ[this.is] = this.frontZ[this.js];
                    this.frontZ[this.js] = this.as;
                }
                ++this.js;
            }
            ++this.is;
        }
        this.is = 0;
        while (this.is < this.backs - 1) {
            this.js = this.is + 1;
            while (this.js < this.backs) {
                if (this.backZ[this.is] > this.backZ[this.js]) {
                    this.ks = this.backOrder[this.is];
                    this.backOrder[this.is] = this.backOrder[this.js];
                    this.backOrder[this.js] = this.ks;
                    this.as = this.backZ[this.is];
                    this.backZ[this.is] = this.backZ[this.js];
                    this.backZ[this.js] = this.as;
                }
                ++this.js;
            }
            ++this.is;
        }
        this.is = 0;
        while (this.is < this.ballnum - 1) {
            this.js = this.is + 1;
            while (this.js < this.ballnum) {
                if (this.ballZ[this.is] > this.ballZ[this.js]) {
                    this.ks = this.ballOrder[this.is];
                    this.ballOrder[this.is] = this.ballOrder[this.js];
                    this.ballOrder[this.js] = this.ks;
                    this.as = this.ballZ[this.is];
                    this.ballZ[this.is] = this.ballZ[this.js];
                    this.ballZ[this.js] = this.as;
                }
                ++this.js;
            }
            ++this.is;
        }
    }
    
    public void drawCube() {
        int n = 0;
        this.bG.setColor(Color.black);
        this.bG.fillRect(0, 0, 190, 190);
        for (int i = 0; i < this.backs; ++i) {
            this.bG.setColor(this.gCol[n++]);
            this.bG.drawLine((int)this.scrX[this.lFrom[this.backOrder[i]]], (int)this.scrY[this.lFrom[this.backOrder[i]]], (int)this.scrX[this.lTo[this.backOrder[i]]], (int)this.scrY[this.lTo[this.backOrder[i]]]);
        }
        for (int j = 0; j < this.ballnum; ++j) {
            this.kd = this.ballOrder[j];
            this.adc = 0.341 * this.ballZ[j];
            final int ballR = (int)this.adc;
            switch (this.balltype[this.kd]) {
                case 2:
                case 10: {
                    this.bG.drawImage(this.bballs[ballR - 14], (int)this.ballX[this.kd] - ballR, (int)this.ballY[this.kd] - ballR, this);
                    break;
                }
                case 4:
                case 12: {
                    this.bG.drawImage(this.bballs[ballR - 4], (int)this.ballX[this.kd] - ballR, (int)this.ballY[this.kd] - ballR, this);
                    break;
                }
                default: {
                    this.ballMidX = (int)this.ballX[this.kd];
                    this.ballMidY = (int)this.ballY[this.kd];
                    this.ballR = ballR;
                    this.bG.setColor(this.gCol[(int)(this.adc * 3.0 - 39.0)]);
                    this.bG.fillOval(this.ballMidX - ballR, this.ballMidY - ballR, ballR * 2, ballR * 2);
                    break;
                }
            }
        }
        for (int k = 0; k < this.fronts; ++k) {
            this.bG.setColor(this.gCol[n++]);
            this.bG.drawLine((int)this.scrX[this.lFrom[this.frontOrder[k]]], (int)this.scrY[this.lFrom[this.frontOrder[k]]], (int)this.scrX[this.lTo[this.frontOrder[k]]], (int)this.scrY[this.lTo[this.frontOrder[k]]]);
        }
    }
    
    public void placeTemp(final int n, final int n2) {
        if (this.tempball >= 0) {
            this.cubedata[this.tempball] = 0;
        }
        this.tempball = -1;
        this.dpt = 10000.0;
        this.ipt = 0;
        while (this.ipt < 27) {
            if (this.cubedata[this.ipt] == 0) {
                this.apt = 11.0 * (5.0 + this.scalProd(this.boxpos[this.ipt * 3], this.boxpos[this.ipt * 3 + 1], this.boxpos[this.ipt * 3 + 2], this.zaxis[0], this.zaxis[1], this.zaxis[2]));
                this.bpt = 95.0 + this.apt * this.scalProd(this.boxpos[this.ipt * 3], this.boxpos[this.ipt * 3 + 1], this.boxpos[this.ipt * 3 + 2], this.xaxis[0], this.xaxis[1], this.xaxis[2]);
                this.cpt = 95.0 + this.apt * this.scalProd(this.boxpos[this.ipt * 3], this.boxpos[this.ipt * 3 + 1], this.boxpos[this.ipt * 3 + 2], this.yaxis[0], this.yaxis[1], this.yaxis[2]);
                this.apt *= 0.341;
                this.bpt = (this.bpt - n) * (this.bpt - n);
                this.cpt = (this.cpt - n2) * (this.cpt - n2);
                if (this.bpt + this.cpt < this.apt * this.apt && this.bpt + this.cpt < this.dpt) {
                    this.tempball = this.ipt;
                    this.dpt = this.bpt + this.cpt;
                }
            }
            ++this.ipt;
        }
        if (this.tempball >= 0) {
            this.cubedata[this.tempball] = 3;
        }
    }
    
    public void performDrawing() {
        if (!this.drawing) {
            this.drawing = true;
            this.project();
            this.sortStuff();
            this.drawCube();
            this.drawing = false;
        }
    }
    
    public void placeComputerBall(final int n) {
        this.cubedata[n] = 12;
        this.performDrawing();
        this.repaint();
        this.busycount = 10;
    }
    
    public void computerWins(final long n) {
        long n2 = n;
        this.wi = 0;
        while (this.wi < 27) {
            if ((n2 & 0x3L) > 0L) {
                this.cubedata[this.wi] = 12;
            }
            n2 >>>= 2;
            ++this.wi;
        }
        this.bottomApplet.setNewMessage(3);
    }
    
    public void playerWins(final long n) {
        long n2 = n;
        this.wi = 0;
        while (this.wi < 27) {
            if ((n2 & 0x3L) > 0L) {
                this.cubedata[this.wi] = 10;
            }
            n2 >>>= 2;
            ++this.wi;
        }
        this.bottomApplet.setNewMessage(2);
    }
    
    public void buildGrays() {
        this.i = 0;
        while (this.i < 36) {
            this.gCol[this.i] = new Color(45 + 6 * this.i, 45 + 6 * this.i, 45 + 6 * this.i);
            ++this.i;
        }
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.runner != null && this.runner.isAlive()) {
            this.runner.stop();
        }
        this.runner = null;
    }
    
    public void run() {
        while (this.runner != null) {
            try {
                Thread.sleep(Math.max(25L, this.nextTime - System.currentTimeMillis()));
            }
            catch (InterruptedException ex) {}
            this.nextTime = System.currentTimeMillis() + 120L;
            if (this.busy) {
                int n = 0;
                for (int i = 0; i < 27; ++i) {
                    if ((this.cubedata[i] & 0x8) > 0) {
                        final int[] cubedata = this.cubedata;
                        final int n2 = i;
                        cubedata[n2] ^= 0x1;
                        n = this.cubedata[i];
                    }
                }
                if (this.busycount > 0) {
                    --this.busycount;
                    if (this.busycount == 0) {
                        for (int j = 0; j < 27; ++j) {
                            final int[] cubedata2 = this.cubedata;
                            final int n3 = j;
                            cubedata2[n3] &= 0x6;
                        }
                        this.busy = false;
                        this.bottomApplet.setNewMessage(0);
                    }
                    else if (this.busycount == 5) {
                        this.playSound(1);
                    }
                }
                if (n <= 0) {
                    continue;
                }
                this.performDrawing();
                this.repaint();
            }
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.x = mouseEvent.getX();
        this.y = mouseEvent.getY();
        if (this.dragging == 1) {
            this.amd = (this.lastX - this.x) * this.dragScale;
            final double[] zaxis = this.zaxis;
            final int n = 0;
            zaxis[n] += this.amd * this.xaxis[0];
            final double[] zaxis2 = this.zaxis;
            final int n2 = 1;
            zaxis2[n2] += this.amd * this.xaxis[1];
            final double[] zaxis3 = this.zaxis;
            final int n3 = 2;
            zaxis3[n3] += this.amd * this.xaxis[2];
            this.normalize(this.zaxis);
            this.crossProd(this.yaxis, this.zaxis, this.xaxis);
            this.amd = (this.lastY - this.y) * this.dragScale;
            final double[] zaxis4 = this.zaxis;
            final int n4 = 0;
            zaxis4[n4] += this.amd * this.yaxis[0];
            final double[] zaxis5 = this.zaxis;
            final int n5 = 1;
            zaxis5[n5] += this.amd * this.yaxis[1];
            final double[] zaxis6 = this.zaxis;
            final int n6 = 2;
            zaxis6[n6] += this.amd * this.yaxis[2];
            this.normalize(this.zaxis);
            this.crossProd(this.zaxis, this.xaxis, this.yaxis);
            this.lastX = this.x;
            this.lastY = this.y;
            this.performDrawing();
            this.repaint();
            return;
        }
        if (this.dragging == 2) {
            this.placeTemp(this.x - this.dragOffsX, this.y - this.dragOffsY);
            this.performDrawing();
            this.repaint();
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.dragging = 0;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (System.currentTimeMillis() - this.lastClick >= 250L || this.tempball < 0) {
            this.lastClick = System.currentTimeMillis();
            return;
        }
        this.cubedata[this.tempball] = 2;
        this.mi = this.tempball;
        this.tempball = -1;
        this.performDrawing();
        this.repaint();
        this.busycount = -1;
        this.busy = true;
        this.mk = 0;
        this.mj = 0;
        while (this.mj < 27) {
            if (this.cubedata[this.mj] > 0) {
                ++this.mk;
            }
            ++this.mj;
        }
        if (this.mk < 27) {
            this.bottomApplet.setNewMessage(1);
            this.playSound(0);
            this.rightApplet.registerMove(this.mi);
            return;
        }
        this.bottomApplet.setNewMessage(4);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.x = mouseEvent.getX();
        this.y = mouseEvent.getY();
        this.lastX = this.x;
        this.lastY = this.y;
        if (this.busy) {
            this.dragging = 1;
            return;
        }
        if (this.tempball < 0) {
            this.placeTemp(this.x, this.y);
            if (this.tempball < 0) {
                this.dragging = 1;
                return;
            }
            this.dragOffsX = 0;
            this.dragOffsY = 0;
            this.dragging = 2;
            this.performDrawing();
            this.repaint();
        }
        else {
            if ((this.x - this.ballMidX) * (this.x - this.ballMidX) + (this.y - this.ballMidY) * (this.y - this.ballMidY) < this.ballR * this.ballR) {
                this.dragOffsX = this.x - this.ballMidX;
                this.dragOffsY = this.y - this.ballMidY;
                this.dragging = 2;
                return;
            }
            this.dragging = 1;
        }
    }
    
    public double scalProd(final double n, final double n2, final double n3, final double n4, final double n5, final double n6) {
        return n * n4 + n2 * n5 + n3 * n6;
    }
    
    public void normalize(final double[] array) {
        this.an = 1.0 / Math.sqrt(array[0] * array[0] + array[1] * array[1] + array[2] * array[2]);
        final int n = 0;
        array[n] *= this.an;
        final int n2 = 1;
        array[n2] *= this.an;
        final int n3 = 2;
        array[n3] *= this.an;
    }
    
    public void crossProd(final double[] array, final double[] array2, final double[] array3) {
        array3[0] = array[1] * array2[2] - array[2] * array2[1];
        array3[1] = array[2] * array2[0] - array[0] * array2[2];
        array3[2] = array[0] * array2[1] - array[1] * array2[0];
    }
    
    public void playSound(final int playing) {
        if (this.playing >= 0) {
            AudioPlayer.player.stop(this.sounds[this.playing]);
        }
        try {
            this.sounds[playing].reset();
        }
        catch (IOException ex) {}
        AudioPlayer.player.start(this.sounds[playing]);
        this.playing = playing;
    }
    
    public void update(final Graphics graphics) {
        graphics.drawImage(this.background, 0, 0, this);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.background, 0, 0, this);
    }
    
    public cluster() {
        this.playing = -1;
        this.busy = false;
        this.dragScale = 0.012;
        this.cornX = new double[8];
        this.cornY = new double[8];
        this.cornZ = new double[8];
        this.projX = new double[8];
        this.projY = new double[8];
        this.projZ = new double[8];
        this.pntX = new double[32];
        this.pntY = new double[32];
        this.pntZ = new double[32];
        this.scrX = new double[32];
        this.scrY = new double[32];
        this.scrZ = new double[32];
        this.frontZ = new double[36];
        this.backZ = new double[36];
        this.xa = new double[] { 1.0, 0.0, 0.0 };
        this.ya = new double[] { 0.0, 1.0, 0.0 };
        this.za = new double[] { 0.0, 0.0, 1.0 };
        this.xaxis = new double[3];
        this.yaxis = new double[3];
        this.zaxis = new double[3];
        this.ut = new double[3];
        this.vt = new double[3];
        this.wt = new double[3];
        this.frontDir = new double[7];
        this.boxpos = new double[81];
        this.ballX = new double[27];
        this.ballY = new double[27];
        this.ballZ = new double[27];
        this.faces = new int[] { 6, 4, 2, 7, 3, 5, 0, 4, 1, 6, 2, 7, 2, 0, 3, 6, 7, 4 };
        this.bbx = new int[] { 0, 28, 58, 90, 124, 160, 198, 90, 46, 0, 210, 180, 148, 114, 78, 40, 0, 132, 174, 218 };
        this.bby = new int[] { 34, 34, 34, 34, 34, 34, 34, 70, 67, 64, 120, 118, 118, 114, 113, 111, 110, 72, 74, 74 };
        this.linefaces = new int[72];
        this.cubedata = new int[27];
        this.balltype = new int[27];
        this.backOrder = new int[36];
        this.frontOrder = new int[36];
        this.ballOrder = new int[27];
        this.lFrom = new int[36];
        this.lTo = new int[36];
        this.bballs = new Image[20];
        this.gCol = new Color[36];
        this.sounds = new AudioDataStream[2];
        this.sndData = new byte[2][];
    }
}
