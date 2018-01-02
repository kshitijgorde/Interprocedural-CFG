import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Cursor;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.util.Random;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.AudioClip;
import java.awt.image.ImageObserver;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class A220Mission1Applet extends Applet implements Runnable, MouseMotionListener, MouseListener, KeyListener
{
    int[] LGJC;
    int addImage;
    boolean addKeyListener;
    boolean addMouseListener;
    ImageObserver addMouseMotionListener;
    Thread append;
    int createImage;
    boolean currentThread;
    boolean currentTimeMillis;
    private AudioClip downloadImage;
    private AudioClip drawImage;
    private AudioClip drawRect;
    private AudioClip drawString;
    private AudioClip equals;
    private AudioClip err;
    private Graphics fillRect;
    private Graphics getAppletContext;
    private Graphics getAudioClip;
    private Graphics getCodeBase;
    private Graphics getDocumentBase;
    private int getGraphics;
    private int getHeight;
    private Image getImage;
    private Image getKeyCode;
    private Image[][] getMessage;
    private Image[][] getSize;
    private Image[] getWidth;
    private Image[][] getX;
    private Image[][] getY;
    private Image[][] grabPixels;
    private Image length;
    private Image loop;
    private Image newGame;
    private Image nextInt;
    private Image[] out;
    private Image[] paint;
    private Image parseInt;
    private Image[] play;
    private Image printStackTrace;
    private Image println;
    private Image setColor;
    private Image setCursor;
    private Image setFont;
    private int showDocument;
    private int showStatus;
    private Image[] sleep;
    private Image[] start;
    private Image[] startUp;
    private Image status;
    private Image stop;
    private Image substring;
    private Image toString;
    private Image trim;
    private Image valueOf;
    private boolean waitForID;
    private boolean I;
    private int Z;
    private int C;
    private int B;
    private int D;
    private int F;
    private int J;
    private int S;
    private int A;
    private int E;
    private int G;
    private boolean H;
    private Image[] K;
    private int L;
    private int M;
    private int N;
    private int[] O;
    private int[] P;
    private int[] Q;
    private Image[][] R;
    private int T;
    private int U;
    private boolean[] V;
    private int[] W;
    private int[] X;
    private Image Y;
    private Image z;
    private Image c;
    private Image[][] b;
    private int d;
    private String[] f;
    private int[][] s;
    private int[] a;
    private int[] e;
    private int[] g;
    private int[] h;
    private int[] k;
    private int[] l;
    private boolean[] m;
    private boolean[] n;
    private int[] o;
    private int[] p;
    private boolean[] q;
    private int[] r;
    private int[] t;
    private boolean[] u;
    private Image[] v;
    private int[] w;
    private int[] II;
    private int[] ZI;
    private boolean CI;
    private Image[] BI;
    private int DI;
    private int FI;
    private int JI;
    private boolean SI;
    private int AI;
    private long EI;
    private long GI;
    private int HI;
    private int KI;
    private boolean LI;
    private boolean MI;
    private boolean NI;
    private String OI;
    private boolean PI;
    private int QI;
    private int i;
    private int j;
    private Random RI;
    private int TI;
    private int UI;
    private int VI;
    private int WI;
    private int XI;
    private int YI;
    private boolean iI;
    private boolean zI;
    private boolean cI;
    private int bI;
    
    public A220Mission1Applet() {
        this.addImage = 0;
        this.addKeyListener = false;
        this.addMouseListener = true;
        this.currentThread = false;
        this.currentTimeMillis = false;
        this.downloadImage = null;
        this.drawImage = null;
        this.drawRect = null;
        this.drawString = null;
        this.equals = null;
        this.err = null;
        this.B = 0;
        this.G = 0;
        this.LI = true;
        this.MI = true;
        this.PI = true;
        this.XI = 0;
        this.zI = true;
        this.cI = false;
        this.bI = 0;
    }
    
    public final void init() {
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.addKeyListener(this);
    }
    
    public final void start() {
        this.addKeyListener = false;
        this.EI = 25L;
        this.startUp();
        (this.append = new Thread(this)).start();
    }
    
    public final void stop() {
        this.addKeyListener(true);
        if (this.append != null) {
            this.append.stop();
            this.append = null;
        }
    }
    
    public final void destroy() {
        this.stop();
    }
    
    public final void startUp() {
        this.getGraphics = this.getSize().width;
        this.getHeight = this.getSize().height;
        this.RI = new Random();
        try {
            this.parseInt = this.downloadImage("./images/images.gif");
            this.printStackTrace = this.downloadImage("./images/canvas.gif");
            this.setColor = this.downloadImage("./images/pausebutton.gif");
            this.setCursor = this.downloadImage("./images/resumebutton.gif");
            this.setFont = this.downloadImage("./images/startbutton.gif");
            this.out();
        }
        catch (Exception ex) {
            System.out.println("Error Getting Images:" + ex.getMessage());
        }
        this.getKeyCode = this.createImage(176, 195);
        this.println = this.createImage(200, 250);
        this.getDocumentBase = this.getKeyCode.getGraphics();
        this.getImage = this.createImage(174, 135);
        this.fillRect = this.getImage.getGraphics();
        this.getCodeBase = this.println.getGraphics();
        this.Y = this.createImage(900, 135);
        this.getAppletContext = this.Y.getGraphics();
        this.d = 135;
        this.f = new String[14];
        this.s = new int[14][90];
        this.z = this.createImage(180, 135);
        (this.getAudioClip = this.z.getGraphics()).setColor(new Color(0, 0, 0));
        this.getAudioClip.fillRect(0, 0, 180, 135);
        this.V = new boolean[6];
        this.W = new int[6];
        this.X = new int[6];
        this.u = new boolean[6];
        this.w = new int[6];
        this.II = new int[6];
        this.ZI = new int[6];
        this.H = false;
        this.CI = false;
        this.O = new int[10];
        this.P = new int[10];
        this.Q = new int[10];
        this.U = 0;
        this.T = 0;
        this.a = new int[15];
        this.e = new int[15];
        this.g = new int[15];
        this.h = new int[15];
        this.k = new int[15];
        this.l = new int[15];
        this.m = new boolean[15];
        this.n = new boolean[15];
        this.o = new int[15];
        this.p = new int[15];
        this.q = new boolean[10];
        this.r = new int[10];
        this.t = new int[10];
        this.LI = true;
        this.MI = true;
        this.NI = false;
        this.append();
        this.addKeyListener = true;
    }
    
    public final void newGame() {
        this.TI = 0;
        this.UI = 3;
        this.KI = 1;
        this.MI = true;
        this.D = 0;
        this.A = 0;
        this.E = 2;
        this.F = 0;
        this.J = 5000;
        this.I = false;
        this.G = 0;
        this.S = 0;
        this.YI = 1;
        this.iI = false;
        this.GI = 50 - 15 * (this.KI - 1);
        if (this.GI < 15L) {
            this.GI = 15L;
        }
        this.HI = 0;
        this.NI = false;
        this.waitForID = true;
        this.Z = 10;
        this.C = 60;
        this.H = false;
        this.getKeyCode();
        this.SI = false;
        this.PI = true;
        this.LI = true;
        this.MI = true;
        this.AI = 1;
        this.cI = false;
        this.bI = 0;
        this.CI = false;
        if (this.err != null) {
            this.err.loop();
        }
    }
    
    public final void keyPressed(final KeyEvent keyEvent) {
        this.LGJC(keyEvent.getKeyCode());
    }
    
    public final void keyTyped(final KeyEvent keyEvent) {
    }
    
    public final void keyReleased(final KeyEvent keyEvent) {
    }
    
    private void LGJC(final int n) {
        switch (this.QI = this.addImage(n)) {
            case 5: {
                if (!this.waitForID) {
                    break;
                }
                if (this.D == 0 || this.D == 2) {
                    int n2 = 0;
                    for (int i = 0; i < 6; ++i) {
                        if (!this.V[i] && n2 == 0) {
                            n2 = 1;
                            this.V[i] = true;
                            this.W[i] = this.Z + 24;
                            this.X[i] = this.C + 5;
                            if (this.D == 0 && this.downloadImage != null) {
                                this.downloadImage.play();
                            }
                            if (this.D == 2 && this.equals != null) {
                                this.equals.play();
                            }
                        }
                    }
                    break;
                }
                int n3 = 3;
                if (!this.V[0]) {
                    this.V[0] = true;
                    this.W[0] = this.Z + 24;
                    this.X[0] = this.C + 5;
                    --n3;
                }
                if (!this.V[1]) {
                    this.V[1] = true;
                    this.W[1] = this.Z + 9;
                    this.X[1] = this.C;
                    --n3;
                }
                if (!this.V[2]) {
                    this.V[2] = true;
                    this.W[2] = this.Z + 9;
                    this.X[2] = this.C + 11;
                    --n3;
                }
                if (!this.V[3] && n3 > 0) {
                    this.V[3] = true;
                    this.W[3] = this.Z + 24;
                    this.X[3] = this.C + 5;
                    --n3;
                }
                if (!this.V[4] && n3 > 0) {
                    this.V[4] = true;
                    this.W[4] = this.Z + 9;
                    this.X[4] = this.C;
                    --n3;
                }
                if (!this.V[5] && n3 > 0) {
                    this.V[5] = true;
                    this.W[5] = this.Z + 9;
                    this.X[5] = this.C + 11;
                    --n3;
                }
                if (n3 >= 3) {
                    break;
                }
                if (this.D == 1 && this.downloadImage != null) {
                    this.downloadImage.play();
                }
                if (this.D == 3 && this.equals != null) {
                    this.equals.play();
                    break;
                }
                break;
            }
            case 1: {
                this.B = 1;
                break;
            }
            case 2: {
                this.B = 2;
                break;
            }
            case 3: {
                this.B = 3;
                break;
            }
            case 4: {
                this.B = 4;
                break;
            }
            case 6: {
                this.SI = false;
                this.newGame();
                break;
            }
            case 7: {
                this.addKeyListener(true);
                this.SI = true;
                this.paint(this.getGraphics());
                break;
            }
            case 8: {
                this.SI = false;
                break;
            }
        }
    }
    
    private int addImage(final int n) {
        int n2 = -1;
        if (!this.SI) {
            if (n == 37) {
                n2 = 1;
            }
            if (n == 52) {
                n2 = 1;
            }
            if (n == 226) {
                n2 = 1;
            }
            if (n == 100) {
                n2 = 1;
            }
            if (n == 39) {
                n2 = 2;
            }
            if (n == 54) {
                n2 = 2;
            }
            if (n == 227) {
                n2 = 2;
            }
            if (n == 102) {
                n2 = 2;
            }
            if (n == 38) {
                n2 = 3;
            }
            if (n == 56) {
                n2 = 3;
            }
            if (n == 224) {
                n2 = 3;
            }
            if (n == 104) {
                n2 = 3;
            }
            if (n == 40) {
                n2 = 4;
            }
            if (n == 50) {
                n2 = 4;
            }
            if (n == 225) {
                n2 = 4;
            }
            if (n == 98) {
                n2 = 4;
            }
            if (n == 32) {
                n2 = 5;
            }
            if (n == 101) {
                n2 = 5;
            }
            if (n == 53) {
                n2 = 5;
            }
            if (n == 83 && this.AI == 0) {
                n2 = 6;
            }
        }
        if (n == 80) {
            n2 = 7;
        }
        if (n == 82) {
            n2 = 8;
        }
        return n2;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        if (!this.currentThread) {
            graphics.setColor(new Color(0, 0, 0));
            graphics.fillRect(0, 0, this.getGraphics, this.getHeight);
            graphics.setColor(new Color(255, 255, 255));
            graphics.setFont(new Font("Arial", 0, 12));
            graphics.drawString("A220 Mission 1", 20, 20);
            graphics.drawString("Loading Sounds please wait.", 20, 60);
            graphics.drawString("Sound " + this.createImage, 20, 80);
        }
        else if (this.addKeyListener) {
            if (this.LI) {
                this.VI = 1;
                this.WI = 48;
                this.getDocumentBase.setColor(new Color(0, 0, 0));
                this.getDocumentBase.fillRect(0, 0, 176, 195);
                this.getDocumentBase.setColor(new Color(85, 119, 255));
                this.getDocumentBase.drawRect(0, 0, 175, 194);
                this.getCodeBase.drawImage(this.printStackTrace, 0, 0, this.addMouseMotionListener);
                graphics.setColor(new Color(255, 255, 255));
                graphics.fillRect(0, 0, this.getGraphics, this.getHeight);
                graphics.drawImage(this.printStackTrace, 0, 0, this.addMouseMotionListener);
                this.LI = false;
            }
            this.fillRect.drawImage(this.z, 0, 0, this.addMouseMotionListener);
            this.fillRect.drawImage(this.Y, this.d, 0, this.addMouseMotionListener);
            for (int i = 0; i < this.T; ++i) {
                if (this.O[i] > 0) {
                    this.fillRect.drawImage(this.R[this.U][this.O[i]], this.P[i] + this.d, this.Q[i], this.addMouseMotionListener);
                }
            }
            for (int j = 0; j < 15; ++j) {
                if (this.n[j]) {
                    if (this.o[j] > 0) {
                        if (this.a[j] == 24) {
                            this.fillRect.drawImage(this.grabPixels[this.o[j] / 5][this.e[j]], this.g[j] - 6, this.h[j] - 2, this.addMouseMotionListener);
                        }
                        else {
                            this.fillRect.drawImage(this.getY[this.o[j] - 1][this.e[j]], this.g[j] - 3, this.h[j] - 3, this.addMouseMotionListener);
                        }
                    }
                    this.fillRect.drawImage(this.getX[this.a[j]][this.e[j]], this.g[j], this.h[j], this.addMouseMotionListener);
                }
            }
            for (int k = 0; k < 10; ++k) {
                if (this.q[k]) {
                    this.fillRect.drawImage(this.length, this.r[k], this.t[k], this.addMouseMotionListener);
                }
            }
            if (this.waitForID) {
                final int n = (this.S - 1000) / 1000;
                if (n > -1) {
                    this.fillRect.drawImage(this.getSize[n][this.F], this.Z - 3, this.C - 3, this.addMouseMotionListener);
                }
                this.fillRect.drawImage(this.getMessage[this.D][this.F], this.Z, this.C, this.addMouseMotionListener);
            }
            if (this.H && this.N < 5) {
                this.fillRect.drawImage(this.K[this.N], this.L, this.M, this.addMouseMotionListener);
            }
            if (this.CI && this.JI < 5) {
                this.fillRect.drawImage(this.BI[this.JI], this.DI, this.FI, this.addMouseMotionListener);
            }
            if (this.V[0]) {
                this.fillRect.drawImage(this.out[this.A], this.W[0] - 1, this.X[0] - 1, this.addMouseMotionListener);
            }
            if (this.V[1]) {
                this.fillRect.drawImage(this.out[this.A], this.W[1] - 1, this.X[1] - 1, this.addMouseMotionListener);
            }
            if (this.V[2]) {
                this.fillRect.drawImage(this.out[this.A], this.W[2] - 1, this.X[2] - 1, this.addMouseMotionListener);
            }
            if (this.V[3]) {
                this.fillRect.drawImage(this.out[this.A], this.W[3] - 1, this.X[3] - 1, this.addMouseMotionListener);
            }
            if (this.V[4]) {
                this.fillRect.drawImage(this.out[this.A], this.W[4] - 1, this.X[4] - 1, this.addMouseMotionListener);
            }
            if (this.V[5]) {
                this.fillRect.drawImage(this.out[this.A], this.W[5] - 1, this.X[5] - 1, this.addMouseMotionListener);
            }
            for (int l = 0; l < 6; ++l) {
                if (this.u[l]) {
                    this.fillRect.drawImage(this.v[this.ZI[l]], this.w[l], this.II[l], this.addMouseMotionListener);
                }
            }
            if (this.cI) {
                this.fillRect.drawImage(this.valueOf, 35, 15, this.addMouseMotionListener);
            }
            this.getDocumentBase.drawImage(this.getImage, this.VI, this.WI, this.addMouseMotionListener);
            this.OI = parseInt(this.TI, 5);
            this.MI = false;
            this.getDocumentBase.drawImage(this.loop, 1, 1, this.addMouseMotionListener);
            for (int n2 = 0; n2 < 5; ++n2) {
                this.getDocumentBase.drawImage(this.sleep[play(this.OI.substring(n2, n2 + 1))], 4 + n2 * 7, 13, this.addMouseMotionListener);
            }
            int n3;
            if (this.iI) {
                n3 = 7 + (this.YI - 1) * 27;
            }
            else {
                n3 = 7 + (this.YI - 1) * 27 - this.d * 27 / 900;
            }
            if (n3 < 7 + (this.YI - 1) * 27) {
                n3 = 7 + (this.YI - 1) * 27;
            }
            this.getDocumentBase.drawImage(this.c, n3, 28, this.addMouseMotionListener);
            this.getDocumentBase.drawImage(this.getWidth[this.UI], 45, 4, this.addMouseMotionListener);
            this.getDocumentBase.drawImage(this.newGame, 1, 183, this.addMouseMotionListener);
            if (this.waitForID) {
                if (this.J > 50) {
                    this.getDocumentBase.setColor(new Color(255, 119, 119));
                    this.getDocumentBase.fillRect(31, 186, this.J / 100, 5);
                }
                if (this.S > 50) {
                    this.getDocumentBase.setColor(new Color(119, 255, 119));
                    this.getDocumentBase.fillRect(131, 186, this.S / 100, 5);
                }
            }
            if (this.I) {
                if (this.G > 5) {
                    this.getDocumentBase.drawImage(this.paint[1], 45, 16, this.addMouseMotionListener);
                }
                else {
                    this.getDocumentBase.drawImage(this.paint[0], 45, 16, this.addMouseMotionListener);
                }
            }
            if (this.AI == 0) {
                this.getDocumentBase.drawImage(this.nextInt, 121, 58, this.addMouseMotionListener);
                this.getDocumentBase.drawImage(this.play[this.showDocument], 6, 58, this.addMouseMotionListener);
            }
            switch (this.XI) {
                case 0: {
                    this.getCodeBase.drawImage(this.setFont, 11, 212, this.addMouseMotionListener);
                    break;
                }
                case 1: {
                    this.getCodeBase.drawImage(this.setColor, 11, 212, this.addMouseMotionListener);
                    break;
                }
                case 2: {
                    this.getCodeBase.drawImage(this.setCursor, 11, 212, this.addMouseMotionListener);
                    break;
                }
            }
            this.getCodeBase.drawImage(this.getKeyCode, 11, 14, this);
            if (this.currentTimeMillis && this.AI == 0) {
                this.getCodeBase.setColor(new Color(0, 255, 0));
                this.getCodeBase.drawRect(132, 72, 49, 99);
            }
            graphics.drawImage(this.println, 0, 0, this.addMouseMotionListener);
        }
        else {
            graphics.setColor(new Color(255, 255, 255));
            graphics.fillRect(0, 0, this.getGraphics, this.getHeight);
        }
    }
    
    public final void run() {
        this.addMouseListener();
        int n = 0;
        try {
            while (this.PI) {
                if (!this.SI) {
                    final long currentTimeMillis = System.currentTimeMillis();
                    if (this.AI == 0) {
                        if (this.showStatus > 0) {
                            --this.showStatus;
                        }
                        else {
                            ++this.showDocument;
                            if (this.showDocument > 5) {
                                this.showDocument = 0;
                            }
                            this.showStatus = 200;
                        }
                    }
                    if (this.cI) {
                        --this.bI;
                        if (this.bI < 0) {
                            this.cI = false;
                        }
                    }
                    if (this.iI) {
                        this.getKeyCode();
                    }
                    if (this.AI > 0) {
                        ++this.F;
                        if (this.F > 4) {
                            this.F = 0;
                        }
                        ++this.U;
                        if (this.U > 4) {
                            this.U = 0;
                        }
                        this.loop();
                        this.downloadImage();
                        this.nextInt();
                        switch (this.B) {
                            case 1: {
                                if (this.Z > 5) {
                                    --this.Z;
                                    break;
                                }
                                this.B = 0;
                                break;
                            }
                            case 2: {
                                if (this.Z < 140) {
                                    ++this.Z;
                                    break;
                                }
                                this.B = 0;
                                break;
                            }
                            case 3: {
                                if (this.C > 3) {
                                    --this.C;
                                    break;
                                }
                                this.B = 0;
                                break;
                            }
                            case 4: {
                                if (this.C < 119) {
                                    ++this.C;
                                    break;
                                }
                                this.B = 0;
                                break;
                            }
                        }
                        this.length();
                    }
                    this.grabPixels();
                    if (++n > 10) {
                        n = 1;
                    }
                    switch (n) {
                        case 1: {
                            this.addMouseMotionListener();
                            break;
                        }
                        case 2: {
                            this.height();
                            this.getImage();
                            break;
                        }
                        case 3: {
                            this.addMouseMotionListener();
                            break;
                        }
                        case 4: {
                            this.height();
                            this.createImage();
                            break;
                        }
                        case 5: {
                            this.addMouseMotionListener();
                            break;
                        }
                        case 6: {
                            this.height();
                            this.drawImage();
                            break;
                        }
                        case 7: {
                            this.addMouseMotionListener();
                            break;
                        }
                        case 8: {
                            this.height();
                            this.createImage();
                            break;
                        }
                        case 9: {
                            this.addMouseMotionListener();
                            break;
                        }
                        case 10: {
                            this.height();
                            this.createImage();
                            break;
                        }
                    }
                    this.paint(this.getGraphics());
                    final long n2 = System.currentTimeMillis() - currentTimeMillis;
                    this.EI = 0L;
                    if (n2 >= this.GI) {
                        continue;
                    }
                    this.EI = this.GI - n2;
                    try {
                        Thread.currentThread();
                        Thread.sleep(this.EI);
                    }
                    catch (InterruptedException ex2) {}
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (x > 11 && x < 52 && y > 210 && y < 245) {
            switch (this.XI) {
                case 0: {
                    this.SI = false;
                    this.newGame();
                    this.XI = 1;
                    this.paint(this.getGraphics());
                    break;
                }
                case 1: {
                    this.addKeyListener(true);
                    this.SI = true;
                    this.XI = 2;
                    this.paint(this.getGraphics());
                    break;
                }
                case 2: {
                    if (this.err != null) {
                        this.err.loop();
                    }
                    this.SI = false;
                    this.XI = 1;
                    this.paint(this.getGraphics());
                    break;
                }
            }
        }
        if (x > 132 && x < 182 && y > 72 && y < 172 && this.AI == 0) {
            this.addKeyListener(true);
            this.SI = true;
            try {
                this.getAppletContext().showDocument(new URL("http://www.jpowered.com/promotion-software/index.htm"), "_top");
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
        this.setCursor(new Cursor(0));
        this.currentTimeMillis = false;
    }
    
    public final void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public final void mouseMoved(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (x > 132 && x < 182 && y > 72 && y < 172 && this.AI == 0) {
            this.setCursor(new Cursor(12));
            this.currentTimeMillis = true;
        }
        else {
            this.setCursor(new Cursor(0));
            this.currentTimeMillis = false;
        }
    }
    
    private void addKeyListener(final boolean b) {
        if (this.downloadImage != null) {
            this.downloadImage.stop();
        }
        if (this.drawImage != null) {
            this.drawImage.stop();
        }
        if (this.drawRect != null) {
            this.drawRect.stop();
        }
        if (this.drawString != null) {
            this.drawString.stop();
        }
        if (this.equals != null) {
            this.equals.stop();
        }
        if (this.err != null) {
            this.err.stop();
        }
    }
    
    private void addMouseListener() {
        this.createImage = 6;
        this.paint(this.getGraphics());
        try {
            this.downloadImage = this.getAudioClip(this.getDocumentBase(), "./sound/sound_fire.au");
        }
        catch (Exception ex) {
            this.downloadImage = null;
            System.out.println("Error Getting sound sound_fire.au:" + ex.getMessage());
        }
        this.createImage = 5;
        this.paint(this.getGraphics());
        try {
            this.drawImage = this.getAudioClip(this.getDocumentBase(), "./sound/sound_alien_exp.au");
        }
        catch (Exception ex2) {
            this.drawImage = null;
            System.out.println("Error Getting sound sound_alien_exp.au:" + ex2.getMessage());
        }
        this.createImage = 4;
        this.paint(this.getGraphics());
        try {
            this.drawRect = this.getAudioClip(this.getDocumentBase(), "./sound/sound_powerup.au");
        }
        catch (Exception ex3) {
            this.drawRect = null;
            System.out.println("Error Getting sound sound_powerup.au:" + ex3.getMessage());
        }
        this.createImage = 3;
        this.paint(this.getGraphics());
        try {
            this.drawString = this.getAudioClip(this.getDocumentBase(), "./sound/sound_bigexplode.au");
        }
        catch (Exception ex4) {
            this.drawString = null;
            System.out.println("Error Getting sound sound_powerup.au:" + ex4.getMessage());
        }
        this.createImage = 2;
        this.paint(this.getGraphics());
        try {
            this.equals = this.getAudioClip(this.getDocumentBase(), "./sound/sound_laser.au");
        }
        catch (Exception ex5) {
            this.drawString = null;
            System.out.println("Error Getting sound sound_laser.au:" + ex5.getMessage());
        }
        this.createImage = 1;
        this.paint(this.getGraphics());
        try {
            this.err = this.getAudioClip(this.getDocumentBase(), "./sound/sound_engine.au");
        }
        catch (Exception ex6) {
            this.drawString = null;
            System.out.println("Error Getting sound sound_engine.au:" + ex6.getMessage());
        }
        this.currentThread = true;
    }
    
    private void addMouseMotionListener() {
        for (int i = 0; i < this.T; ++i) {
            if (this.O[i] > 0 && this.Z < this.P[i] + this.d + 7 && this.Z + 25 > this.P[i] + this.d && this.C + 10 > this.Q[i] && this.C < this.Q[i] + 7) {
                switch (this.O[i]) {
                    case 1: {
                        ++this.D;
                        if (this.D > 3) {
                            this.D = 3;
                        }
                        if (this.D > 1) {
                            this.A = 1;
                            this.E = 5;
                        }
                        else {
                            this.A = 0;
                            this.E = 2;
                        }
                        if (this.drawRect != null) {
                            this.drawRect.play();
                            break;
                        }
                        break;
                    }
                    case 2: {
                        this.J += 1000;
                        if (this.J > 5000) {
                            this.J = 5000;
                        }
                        if (this.drawRect != null) {
                            this.drawRect.play();
                            break;
                        }
                        break;
                    }
                    case 3: {
                        this.S += 1000;
                        if (this.S > 4000) {
                            this.S = 4000;
                        }
                        if (this.drawRect != null) {
                            this.drawRect.play();
                            break;
                        }
                        break;
                    }
                }
                this.O[i] = 0;
            }
        }
        if (this.waitForID) {
            int n = (this.C + 6) / 10;
            if (n > 13) {
                n = 13;
            }
            final int n2 = (this.Z + 7 - this.d) / 10;
            if (n2 < 88 && n2 > -1 && (this.s[n][n2] > 0 || this.s[n][n2 + 1] > 0)) {
                this.S -= 500;
                if (this.S <= 0) {
                    this.S = 0;
                    this.equals();
                }
            }
        }
        for (int j = 0; j < 6; ++j) {
            if (this.V[j]) {
                final int n3 = (this.X[j] + 1) / 10;
                final int n4 = (this.W[j] - this.d) / 10;
                if (n4 < 89 && n4 > -1 && this.s[n3][n4] > 0) {
                    this.fillRect(j);
                    if (this.drawImage != null) {
                        this.drawImage.play();
                    }
                }
            }
        }
        for (int k = 0; k < 15; ++k) {
            if (this.n[k] && this.g[k] + this.k[k] > this.Z && this.g[k] < this.Z + 23 && this.h[k] + this.l[k] > this.C && this.h[k] < this.C + 10) {
                if (this.a[k] == 24) {
                    this.S -= 5000;
                }
                else {
                    this.S -= 700;
                }
                if (this.S <= 0) {
                    this.S = 0;
                    this.equals();
                }
                this.o[k] -= 5;
                if (this.o[k] <= 0) {
                    this.err(k);
                    if (this.drawImage != null) {
                        this.drawImage.play();
                    }
                }
            }
        }
        for (int l = 0; l < 10; ++l) {
            if (this.q[l] && this.r[l] + 2 > this.Z && this.r[l] < this.Z + 23 && this.t[l] + 2 > this.C && this.t[l] < this.C + 10) {
                this.q[l] = false;
                this.currentTimeMillis(this.r[l], this.t[l]);
                if (this.drawImage != null) {
                    this.drawImage.play();
                }
                this.S -= 300;
                if (this.S <= 0) {
                    this.S = 0;
                    this.equals();
                }
            }
        }
        for (int n5 = 0; n5 < 6; ++n5) {
            if (this.V[n5]) {
                final int n6 = this.W[n5];
                final int n7 = this.X[n5] + 1;
                int n8;
                if (this.D < 2) {
                    n8 = n6 + 2;
                }
                else {
                    n8 = n6 + 8;
                }
                for (int n9 = 0; n9 < 15; ++n9) {
                    if (this.n[n9] && this.g[n9] + this.k[n9] >= n6 && this.g[n9] <= n8 && this.h[n9] + this.l[n9] >= n7 && this.h[n9] <= n7) {
                        if (this.D < 2) {
                            --this.o[n9];
                        }
                        else {
                            this.o[n9] -= 2;
                        }
                        if (this.o[n9] < 0) {
                            this.err(n9);
                        }
                        this.V[n5] = false;
                        if (this.drawImage != null) {
                            this.drawImage.play();
                        }
                    }
                }
            }
        }
        for (int n10 = 0; n10 < 15; ++n10) {
            if (this.n[n10] && (this.a[n10] == 4 || this.a[n10] == 5)) {
                int n11 = (this.h[n10] + 6) / 10;
                if (n11 > 13) {
                    n11 = 13;
                }
                final int n12 = (this.g[n10] + 7 - this.d) / 10;
                if (n10 < 90 && n10 > -1 && this.s[n11][n12] > 0) {
                    this.err(n10);
                    if (this.drawImage != null) {
                        this.drawImage.play();
                    }
                }
            }
        }
    }
    
    private void append() {
        this.AI = 0;
        this.waitForID = false;
        this.KI = 1;
        this.MI = false;
        this.D = 0;
        this.A = 0;
        this.E = 2;
        this.F = 0;
        this.S = 0;
        this.YI = 1;
        this.iI = false;
        this.UI = 0;
        this.GI = 50 - 5 * this.KI;
        if (this.GI < 15L) {
            this.GI = 15L;
        }
        this.HI = 0;
        this.getKeyCode();
        this.SI = false;
        this.PI = true;
        this.LI = true;
        this.XI = 0;
        this.NI = false;
        this.CI = false;
        this.showDocument = 0;
        this.showStatus = 100;
    }
    
    private void createImage() {
        final int n = 10 * (this.KI - 1);
        for (int i = 0; i < 15; ++i) {
            if (this.n[i]) {
                switch (this.a[i]) {
                    case 1: {
                        if (this.RI.nextInt() % 100 > 90 - n) {
                            this.currentThread(this.g[i] - 2, this.h[i] + 5);
                            break;
                        }
                        break;
                    }
                    case 2: {
                        if (this.RI.nextInt() % 100 > 70 - n) {
                            this.currentThread(this.g[i] - 1, this.h[i] + 6);
                            break;
                        }
                        break;
                    }
                    case 3: {
                        if (this.RI.nextInt() % 100 > 85 - n) {
                            this.currentThread(this.g[i] - 2, this.h[i] + 5);
                            break;
                        }
                        break;
                    }
                    case 7: {
                        if (this.RI.nextInt() % 100 > 80 - n) {
                            this.currentThread(this.g[i] - 1, this.h[i] + 3);
                            break;
                        }
                        break;
                    }
                    case 24: {
                        if (this.RI.nextInt() % 100 > 60 - n) {
                            this.currentThread(this.g[i] - 1, this.h[i] + 10);
                            break;
                        }
                        break;
                    }
                }
            }
        }
    }
    
    private void currentThread(final int n, final int n2) {
        for (int n3 = 0, n4 = 0; n3 == 0 && n4 < 10; ++n4) {
            if (!this.q[n4]) {
                n3 = 1;
                this.q[n4] = true;
                this.r[n4] = n;
                this.t[n4] = n2;
            }
        }
    }
    
    private void currentTimeMillis(final int n, final int n2) {
        for (int n3 = 0, n4 = 0; n3 == 0 && n4 < 6; ++n4) {
            if (!this.u[n4]) {
                n3 = 1;
                this.u[n4] = true;
                this.w[n4] = n;
                this.II[n4] = n2;
                this.ZI[n4] = 0;
            }
        }
    }
    
    private void downloadImage() {
        for (int i = 0; i < 15; ++i) {
            if (this.n[i]) {
                switch (this.a[i]) {
                    case 1: {
                        final int[] e = this.e;
                        final int n = i;
                        ++e[n];
                        if (this.e[i] > 4) {
                            this.e[i] = 0;
                        }
                        this.g[i] -= 2;
                        if (this.g[i] < -15) {
                            this.n[i] = false;
                            break;
                        }
                        break;
                    }
                    case 2: {
                        final int[] e2 = this.e;
                        final int n2 = i;
                        ++e2[n2];
                        if (this.e[i] > 4) {
                            this.e[i] = 0;
                        }
                        if (this.zI) {
                            final int[] g = this.g;
                            final int n3 = i;
                            --g[n3];
                        }
                        if (this.g[i] < -21) {
                            this.n[i] = false;
                            break;
                        }
                        break;
                    }
                    case 3: {
                        final int[] e3 = this.e;
                        final int n4 = i;
                        ++e3[n4];
                        if (this.e[i] > 4) {
                            this.e[i] = 0;
                        }
                        if (this.zI) {
                            final int[] g2 = this.g;
                            final int n5 = i;
                            --g2[n5];
                        }
                        if (this.g[i] < -15) {
                            this.n[i] = false;
                        }
                        if (this.p[i] == 8) {
                            this.h[i] -= 2;
                        }
                        else {
                            this.h[i] += 2;
                        }
                        if (this.h[i] < 0) {
                            this.p[i] = 2;
                            this.h[i] += 2;
                        }
                        if (this.h[i] > 145) {
                            this.p[i] = 8;
                            this.h[i] -= 2;
                        }
                        int n6 = (this.h[i] + 5) / 10;
                        if (n6 > 13) {
                            n6 = 13;
                        }
                        final int n7 = (this.g[i] + 7 - this.d) / 10;
                        if (n7 >= 90 || n7 <= -1 || this.s[n6][n7] <= 0) {
                            break;
                        }
                        if (this.p[i] == 8) {
                            this.p[i] = 2;
                            this.h[i] += 2;
                            break;
                        }
                        this.p[i] = 8;
                        this.h[i] -= 2;
                        break;
                    }
                    case 4: {
                        final int[] e4 = this.e;
                        final int n8 = i;
                        ++e4[n8];
                        if (this.e[i] > 4) {
                            this.e[i] = 0;
                        }
                        if (this.zI) {
                            final int[] g3 = this.g;
                            final int n9 = i;
                            --g3[n9];
                        }
                        if (this.m[i]) {
                            this.h[i] += 2;
                        }
                        else if (this.RI.nextInt() % 100 > 97 || this.g[i] < 30) {
                            this.m[i] = true;
                        }
                        if (this.g[i] < -9 || this.h[i] > 145) {
                            this.n[i] = false;
                            break;
                        }
                        break;
                    }
                    case 5: {
                        final int[] e5 = this.e;
                        final int n10 = i;
                        ++e5[n10];
                        if (this.e[i] > 4) {
                            this.e[i] = 0;
                        }
                        if (this.zI) {
                            final int[] g4 = this.g;
                            final int n11 = i;
                            --g4[n11];
                        }
                        if (this.m[i]) {
                            this.h[i] -= 2;
                        }
                        else if (this.RI.nextInt() % 100 > 98 || this.g[i] < 30) {
                            this.m[i] = true;
                        }
                        if (this.g[i] < -9 || this.h[i] < -15) {
                            this.n[i] = false;
                            break;
                        }
                        break;
                    }
                    case 6: {
                        final int[] e6 = this.e;
                        final int n12 = i;
                        ++e6[n12];
                        if (this.e[i] > 4) {
                            this.e[i] = 0;
                        }
                        this.g[i] -= 3;
                        if (this.g[i] < -21) {
                            this.n[i] = false;
                            break;
                        }
                        break;
                    }
                    case 7: {
                        final int[] e7 = this.e;
                        final int n13 = i;
                        ++e7[n13];
                        if (this.e[i] > 4) {
                            this.e[i] = 0;
                        }
                        if (this.zI) {
                            final int[] g5 = this.g;
                            final int n14 = i;
                            --g5[n14];
                        }
                        if (this.g[i] < -11) {
                            this.n[i] = false;
                            break;
                        }
                        break;
                    }
                    case 24: {
                        final int[] e8 = this.e;
                        final int n15 = i;
                        ++e8[n15];
                        if (this.e[i] > 4) {
                            this.e[i] = 0;
                        }
                        if (this.g[i] > 150) {
                            final int[] g6 = this.g;
                            final int n16 = i;
                            --g6[n16];
                        }
                        else {
                            this.zI = false;
                        }
                        if (this.p[i] == 8) {
                            this.h[i] -= 2;
                        }
                        else {
                            this.h[i] += 2;
                        }
                        if (this.h[i] < 15) {
                            this.p[i] = 2;
                            this.h[i] += 2;
                        }
                        if (this.h[i] > 110) {
                            this.p[i] = 8;
                            this.h[i] -= 2;
                            break;
                        }
                        break;
                    }
                }
            }
        }
    }
    
    private void drawImage() {
        if (this.AI > 0) {
            final int n = (180 - this.d) / 10;
            if (n < 90 && n > -1) {
                for (int i = 0; i < 14; ++i) {
                    switch (this.s[i][n]) {
                        case -1: {
                            final int drawRect = this.drawRect();
                            if (drawRect > -1) {
                                this.a[drawRect] = 1;
                                this.e[drawRect] = 0;
                                this.g[drawRect] = n * 10 + this.d;
                                this.h[drawRect] = i * 10 - 4;
                                this.k[drawRect] = 15;
                                this.l[drawRect] = 9;
                                this.m[drawRect] = false;
                                this.n[drawRect] = true;
                                this.o[drawRect] = 0;
                                this.p[drawRect] = 4;
                                break;
                            }
                            break;
                        }
                        case -2: {
                            final int drawRect2 = this.drawRect();
                            if (drawRect2 > -1) {
                                this.a[drawRect2] = 2;
                                this.e[drawRect2] = 0;
                                this.g[drawRect2] = n * 10 + this.d;
                                this.h[drawRect2] = i * 10 - 4;
                                this.k[drawRect2] = 21;
                                this.l[drawRect2] = 11;
                                this.m[drawRect2] = false;
                                this.n[drawRect2] = true;
                                this.o[drawRect2] = 2;
                                this.p[drawRect2] = 5;
                                break;
                            }
                            break;
                        }
                        case -3: {
                            final int drawRect3 = this.drawRect();
                            if (drawRect3 > -1) {
                                this.a[drawRect3] = 3;
                                this.e[drawRect3] = 0;
                                this.g[drawRect3] = n * 10 + this.d;
                                this.h[drawRect3] = i * 10 - 4;
                                this.k[drawRect3] = 15;
                                this.l[drawRect3] = 9;
                                this.m[drawRect3] = false;
                                this.n[drawRect3] = true;
                                this.o[drawRect3] = 0;
                                this.p[drawRect3] = 8;
                                break;
                            }
                            break;
                        }
                        case -4: {
                            final int drawRect4 = this.drawRect();
                            if (drawRect4 > -1) {
                                this.a[drawRect4] = 4;
                                this.e[drawRect4] = 0;
                                this.g[drawRect4] = n * 10 + this.d;
                                this.h[drawRect4] = i * 10 - 4;
                                this.k[drawRect4] = 7;
                                this.l[drawRect4] = 10;
                                this.m[drawRect4] = false;
                                this.n[drawRect4] = true;
                                this.o[drawRect4] = 0;
                                this.p[drawRect4] = 0;
                                break;
                            }
                            break;
                        }
                        case -5: {
                            final int drawRect5 = this.drawRect();
                            if (drawRect5 > -1) {
                                this.a[drawRect5] = 5;
                                this.e[drawRect5] = 0;
                                this.g[drawRect5] = n * 10 + this.d;
                                this.h[drawRect5] = i * 10 - 4;
                                this.k[drawRect5] = 9;
                                this.l[drawRect5] = 15;
                                this.m[drawRect5] = false;
                                this.n[drawRect5] = true;
                                this.o[drawRect5] = 0;
                                this.p[drawRect5] = 0;
                                break;
                            }
                            break;
                        }
                        case -6: {
                            final int drawRect6 = this.drawRect();
                            if (drawRect6 > -1) {
                                this.a[drawRect6] = 6;
                                this.e[drawRect6] = 0;
                                this.g[drawRect6] = n * 10 + this.d;
                                this.h[drawRect6] = i * 10 - 4;
                                this.k[drawRect6] = 21;
                                this.l[drawRect6] = 7;
                                this.m[drawRect6] = false;
                                this.n[drawRect6] = true;
                                this.o[drawRect6] = 0;
                                this.p[drawRect6] = 0;
                                break;
                            }
                            break;
                        }
                        case -7: {
                            final int drawRect7 = this.drawRect();
                            if (drawRect7 > -1) {
                                this.a[drawRect7] = 7;
                                this.e[drawRect7] = 0;
                                this.g[drawRect7] = n * 10 + this.d;
                                this.h[drawRect7] = i * 10;
                                this.k[drawRect7] = 10;
                                this.l[drawRect7] = 10;
                                this.m[drawRect7] = false;
                                this.n[drawRect7] = true;
                                this.o[drawRect7] = 0;
                                this.p[drawRect7] = 0;
                                break;
                            }
                            break;
                        }
                        case -24: {
                            final int drawRect8 = this.drawRect();
                            if (drawRect8 > -1) {
                                this.a[drawRect8] = 24;
                                this.e[drawRect8] = 0;
                                this.g[drawRect8] = n * 10 + this.d;
                                this.h[drawRect8] = i * 10;
                                this.k[drawRect8] = 20;
                                this.l[drawRect8] = 20;
                                this.m[drawRect8] = false;
                                this.n[drawRect8] = true;
                                this.o[drawRect8] = 20;
                                this.p[drawRect8] = 0;
                                this.NI = true;
                                break;
                            }
                            break;
                        }
                    }
                }
            }
        }
    }
    
    private int drawRect() {
        int n = -1;
        for (int n2 = 0; n == -1 && n2 < 15; ++n2) {
            if (!this.n[n2]) {
                n = n2;
            }
        }
        return n;
    }
    
    private void drawString() {
        if (this.AI > 0 && this.UI <= 0) {
            this.append();
        }
    }
    
    private void equals() {
        this.H = true;
        this.L = this.Z + 8;
        this.M = this.C;
        this.N = 0;
        if (this.err != null) {
            this.err.stop();
        }
        if (this.drawString != null) {
            this.drawString.play();
        }
        this.waitForID = false;
    }
    
    private void err(final int n) {
        this.n[n] = false;
        int n2 = -1;
        for (int i = 0; i < 6; ++i) {
            if (!this.u[i] && n2 < 0) {
                n2 = i;
            }
        }
        if (n2 > -1 && this.a[n] < 24) {
            this.u[n2] = true;
            this.w[n2] = this.g[n];
            this.II[n2] = this.h[n];
            this.ZI[n2] = 0;
        }
        switch (this.a[n]) {
            case 1: {
                this.TI += 150;
                break;
            }
            case 2: {
                this.TI += 500;
                break;
            }
            case 3: {
                this.TI += 200;
                break;
            }
            case 4: {
                this.TI += 50;
                break;
            }
            case 5: {
                this.TI += 100;
                break;
            }
            case 6: {
                this.TI += 100;
                break;
            }
            case 7: {
                this.TI += 100;
                break;
            }
            case 24: {
                this.TI += 1500;
                this.NI = false;
                this.zI = true;
                this.CI = true;
                this.DI = this.g[n] - 5;
                this.FI = this.h[n] - 5;
                this.JI = 0;
                break;
            }
        }
    }
    
    private void fillRect(final int n) {
        int n2 = -1;
        for (int i = 0; i < 6; ++i) {
            if (!this.u[i] && n2 < 0) {
                n2 = i;
            }
        }
        if (n2 > -1) {
            this.u[n2] = true;
            this.w[n2] = this.W[n];
            this.II[n2] = this.X[n];
            this.ZI[n2] = 0;
        }
        this.V[n] = false;
    }
    
    private void getAudioClip() {
        --this.UI;
        if (this.UI <= 0) {
            this.UI = 0;
        }
        else if (this.err != null) {
            this.err.loop();
        }
        this.D = 0;
        this.A = 0;
        this.E = 2;
        this.F = 0;
        this.J = 5000;
        this.S = 0;
        this.waitForID = true;
        this.Z = 10;
        this.C = 60;
        this.getKeyCode();
        this.H = false;
        this.drawString();
    }
    
    private void getImage() {
        if (this.d < -900) {
            this.iI = true;
            ++this.YI;
        }
        if (this.YI > 6) {
            this.YI = 1;
            ++this.KI;
            this.GI = 50 - 15 * (this.KI - 1);
            if (this.GI < 15L) {
                this.GI = 15L;
            }
            this.cI = true;
            this.bI = 80;
        }
    }
    
    private void getKeyCode() {
        this.paint();
        if (this.KI > 2) {
            this.KI = 2;
        }
        if (this.KI < 1) {
            this.KI = 1;
        }
        switch (this.YI + 6 * (this.KI - 1)) {
            case 1: {
                this.f[0] = "..........................................................................................";
                this.f[1] = "..........................................................................................";
                this.f[2] = "....................................W.............S...........................a...........";
                this.f[3] = "..........................................................................................";
                this.f[4] = "...............................................................F...........a........S.....";
                this.f[5] = "..........................................................................................";
                this.f[6] = "..............................S..............................W................a...........";
                this.f[7] = "................................................................................e.........";
                this.f[8] = "......................................................................e........314........";
                this.f[9] = "........................e...........................................32124..ee.31114.......";
                this.f[10] = "..........e..........322124...F.......e........3212224...eee....32221111122112111114e.....";
                this.f[11] = ".........214.......321111114........3212224.e.31111111222111222211111111111111111111224e..";
                this.f[12] = "........31114..e..31111111114......31111111212111111111111111111111111111111111111111116..";
                this.f[13] = "222222221111122122111111111112222221111111111111111111111111111111111111111111111111111122";
                break;
            }
            case 2: {
                this.f[0] = "..........................................................................................";
                this.f[1] = "..............W.....................a......................a....................a..a......";
                this.f[2] = ".......S..................................................a...............b....a..a.......";
                this.f[3] = "....................................3222...................a....................a..a......";
                this.f[4] = ".........................a....324ee311114...F..................3224....34.................";
                this.f[5] = ".............................5111111111114...................g31116...3114................";
                this.f[6] = "..................e.....F...g5111111111111222...............311111122211114...............";
                this.f[7] = "................3214........111111111111111114.............31111111111111114.....34.....S.";
                this.f[8] = "...............311114..e.e.31111111111111111114.....S......51111111111111116....3114......";
                this.f[9] = "..............311111122121211111111111111111116............511111111111111114e.311114e....";
                this.f[10] = "........322222111111111111111111111111111111116............511111111111111111121111116....";
                this.f[11] = ".......31111111111111111111111111111111111111114.e.e.e.e..31111111111111111111111111114...";
                this.f[12] = "......3111111111111111111111111111111111111111112121212122111111111111111111111111111114..";
                this.f[13] = "222222111111111111111111111111111111111111111111111111111111111111111111111111111111111122";
                break;
            }
            case 3: {
                this.f[0] = "877777777711111117777777777777711111111111111111111111777777777777777777777777777777777779";
                this.f[1] = "..........8111119.....F........81111111111111111111119.......d....d....d.....d............";
                this.f[2] = "...........81119................877777777711111111119...............................a.....";
                this.f[3] = "...........d879d...............a.dddd.....8777777779........W...................b.........";
                this.f[4] = "....................................................................................a.....";
                this.f[5] = "..............................b..........S............a...................................";
                this.f[6] = "...S.........................................................g32222222222224..............";
                this.f[7] = "...............................a.............................5111111111111114....34.......";
                this.f[8] = "......................g34...................................g5111111111111116.e.3114......";
                this.f[9] = "......................1114....................3434..........1111111111111111141211114.....";
                this.f[10] = "....................g311114..........3224....311114......32211111111111111111111111114e...";
                this.f[11] = ".......324...S.....311111114........311114..3111111222222111111111111111111111111111116...";
                this.f[12] = "......31114.......31111111114......31111112211111111111111111111111111111111111111111114..";
                this.f[13] = "222222111112222222111111111112222221111111111111111111111111111111111111111111111111111122";
                break;
            }
            case 4: {
                this.f[0] = "811111111111111111111111111111111111111111111111111111111111111111111111111111111111777779";
                this.f[1] = ".81111111111111111111111111111111111117777777771111111111111111111111111111111111119......";
                this.f[2] = "..877771111111111117777711111111111119.ddd.....811111117777777117777777771111111119.......";
                this.f[3] = ".......811111111119.....8111111111116......S....5111119d......89........d811111119........";
                this.f[4] = "........8111111119.......811111111116...........511119....................8111119.....F...";
                this.f[5] = ".........87777779....c....81111111119...........87779.......c......c.......87779..........";
                this.f[6] = "...........................811111119.........c.............g...........................b..";
                this.f[7] = "............................8111119.......................312222222224.....c...........f..";
                this.f[8] = ".......S.....c......34.......87779.......................g511111111116...3224.............";
                this.f[9] = "...................3114....................3434...W.....g111111111111143211114........W...";
                this.f[10] = "..................311114...............F..311114......32111111111111111111111122224.......";
                this.f[11] = ".......324.......311111114....c...32222243111111222222111111111111111111111111111114e.....";
                this.f[12] = "......31114.....31111111114......31111111111111111111111111111111111111111111111111114....";
                this.f[13] = "222222111112222211111111111222222111111111111111111111111111111111111111111111111111112222";
                break;
            }
            case 5: {
                this.f[0] = "877111111111777777777777777777777777777777777777111111111111111111111111111111111111111119";
                this.f[1] = "...811111119..........d....d....................51111111111111111111111111111111111111119.";
                this.f[2] = "....8111119......F................f.......3222221111111111111987117777777711777777771179..";
                this.f[3] = ".....87779.....................322222222221777777711111111119...56........56...S....56....";
                this.f[4] = "..............g32224...........877777777779.......8111111119....56........56........56....";
                this.f[5] = "............321111114........................S.....87777779.....89...34...89...34...89....";
                this.f[6] = "............871111119............f.............f.......d.............56........56......c..";
                this.f[7] = "..............877779...............f...c.......f.....................56...F....56...S.....";
                this.f[8] = ".................................f..32222224......F.......c.....3222211222222221122224....";
                this.f[9] = "...........W..........3224..........87777779.............3434..311111111111111111111114...";
                this.f[10] = "....................g311114.....F...............3224....31111431111111111111111111111114..";
                this.f[11] = ".......324.........311111114...........g.......311112222111111111111111111111111111111114.";
                this.f[12] = "......31114e......31111111114e.....3222122222221111111111111111111111111111111111111111114";
                this.f[13] = "222222111111222222111111111111222221111111111111111111111111111111111111111111111111111111";
                break;
            }
            case 6: {
                this.f[0] = "811111111111111111111111177777777777777777777777777777777777111111177777777777777777111119";
                this.f[1] = ".811111111111111111111119...d.d.d.d.d.......................8111119d.d.d............81119.";
                this.f[2] = "..8111111177771111111116...................g....c............81116.............b.....519..";
                this.f[3] = "...8777779..d.8111111116..................312222224....c......8116..........c........56...";
                this.f[4] = ".......d.......811111116.................g5111111114...........816...................89...";
                this.f[5] = "....WFS.........87777779.................111111111114....c......89................z.......";
                this.f[6] = "..........c......d................f..f.f3111111111116............f........................";
                this.f[7] = "...32222224.....................f...f..f8111111111116......c.....f...................34...";
                this.f[8] = "...511111114.......3224..................877711111119...........34...................56...";
                this.f[9] = "...51111111124e..e311114..................ddd8111119.........c.316.............b.....56...";
                this.f[10] = "...511111111111221111116......................87779...........3116...................514..";
                this.f[11] = "..3111111111111111111116.............................F.......31116........c..........5114.";
                this.f[12] = ".311111111111111111111114..e.e.e.e.e............c...........3111114.e.e.e...........311114";
                this.f[13] = "211111111111111111111111122222222222222222222222222222222222111111122222222222222222111111";
                break;
            }
            case 7: {
                this.f[0] = "..........................................................................................";
                this.f[1] = ".............................................a.....................f......................";
                this.f[2] = "....................................W.............S...........................a...........";
                this.f[3] = ".............................................a............................................";
                this.f[4] = "...............................................................F...........a........S.....";
                this.f[5] = "...........................................f..............................................";
                this.f[6] = "..............................S..f........f..................W................a...........";
                this.f[7] = "...........................................f.......................f............e.........";
                this.f[8] = "......................................................................e........314........";
                this.f[9] = "........................e........................e..e...............32124..ee.31114.......";
                this.f[10] = "..........e.........e322124...F.......e........3212224...eee...e32221111122112111114e.....";
                this.f[11] = ".........214.......321111114........3212224.e.31111111222111222211111111111111111111224e..";
                this.f[12] = "........31114.eee.31111111114e..e.e31111111212111111111111111111111111111111111111111116..";
                this.f[13] = "222222221111122122111111111112222221111111111111111111111111111111111111111111111111111122";
                break;
            }
            case 8: {
                this.f[0] = "..........................................................................................";
                this.f[1] = "..............W.....................a......................a....................a..a......";
                this.f[2] = ".......S.............................g....................a...............b....a..a.......";
                this.f[3] = "...........................f...g....3122...................a....ee..............a..a......";
                this.f[4] = ".........................a....314ee311114...F..................3224....34.................";
                this.f[5] = ".............................5111111111114...................g31116.e.3114................";
                this.f[6] = "..................e.....F...g5111111111111222...............311111122211114...............";
                this.f[7] = "................3214........111111111111111114.............31111111111111114.....34.....S.";
                this.f[8] = "...............311114e.e.e.31111111111111111114.....S......51111111111111116....3114......";
                this.f[9] = ".........e.e.e311111122121211111111111111111116............511111111111111114e.311114e....";
                this.f[10] = "........322222111111111111111111111111111111116............511111111111111111121111116....";
                this.f[11] = ".......31111111111111111111111111111111111111114.e.e.e.e..31111111111111111111111111114...";
                this.f[12] = "......3111111111111111111111111111111111111111112121212122111111111111111111111111111114..";
                this.f[13] = "222222111111111111111111111111111111111111111111111111111111111111111111111111111111111122";
                break;
            }
            case 9: {
                this.f[0] = "877777777711111117777777777777711111111111111111111111777777777777777777777777777777777779";
                this.f[1] = ".......d..8111119...d.F.....d..81111111111111111111119.......d....d....d.....d............";
                this.f[2] = "...........81119................877777777711111111119...............................a.....";
                this.f[3] = "...........d879d...............a.dddd.....8777777779........W...................b.........";
                this.f[4] = "............................f.................d......................F......f.......a.....";
                this.f[5] = "..............................b..........S............a...................................";
                this.f[6] = "...S........................f................................g32222222222224..............";
                this.f[7] = "...............c...............a.................c...........5111111111111114....34.......";
                this.f[8] = "......................g34...................................g5111111111111116.e.3114......";
                this.f[9] = "......................1114....................3434..........1111111111111111141211114.....";
                this.f[10] = "....................g311114..........3224....311114.eeee.32211111111111111111111111114e...";
                this.f[11] = ".......324...S.....311111114........311114.e3111111222222111111111111111111111111111116...";
                this.f[12] = "......31114.......31111111114.e..e.31111112211111111111111111111111111111111111111111114..";
                this.f[13] = "222222111112222222111111111112222221111111111111111111111111111111111111111111111111111122";
                break;
            }
            case 10: {
                this.f[0] = "811111111111111111111111111111111111111111111111111111111111111111111111111111111111777779";
                this.f[1] = ".81111111111111111111111111111111111117777777771111111111111111111111111111111111119......";
                this.f[2] = "..877771111111111117777711111111111119.ddd.....811111117777777117777777771111111119.......";
                this.f[3] = ".......811111111119.....8111111111116......S....5111119d......89........d811111119........";
                this.f[4] = "........8111111119.......811111111116...........511119....................8111119.....F...";
                this.f[5] = ".........87777779....c....81111111119...........87779.........c....c.......87779..........";
                this.f[6] = "...........................811111119.........c.............g...........................f..";
                this.f[7] = "...........c................8111119..............c...c....312222222224.....c...........b..";
                this.f[8] = ".......S...........g34.......87779.......................g511111111116...3224.............";
                this.f[9] = ".............c....g1114...c........c.......3434...W.....g111111111111143211114........W...";
                this.f[10] = ".................g111114........c......F..311114......32111111111111111111111122224.......";
                this.f[11] = ".......324.......111111114....c...32222243111111222222111111111111111111111111111114e.....";
                this.f[12] = "......31114.....31111111114......31111111111111111111111111111111111111111111111111114....";
                this.f[13] = "222222111112222211111111111222222111111111111111111111111111111111111111111111111111112222";
                break;
            }
            case 11: {
                this.f[0] = "877111111111777777777777777777777777777777777777111111111111111111111111111111111111111119";
                this.f[1] = "...811111119..........d....d....................51111111111111111111111111111111111111119.";
                this.f[2] = "....8111119......F...........f............3222221111111111111987117777777711777777771179..";
                this.f[3] = ".....87779.....................322222222221777777711111111119...56........56........56....";
                this.f[4] = "..............g32224...........877777777779.......8111111119....56........56........56....";
                this.f[5] = "............321111114........................S.....87777779.....89...34...89...34...89....";
                this.f[6] = "............871111119............f........c....f.......d.............56........56......c..";
                this.f[7] = "..............877779...............f...c.......f..................g..56........56.........";
                this.f[8] = ".................................f..32222224......F.......c.....3212211222222221122224....";
                this.f[9] = "...........W..........3224..........87777779.............3434ee311111111111111111111114...";
                this.f[10] = "..............c.....g311114.....F.........c.....3224....31111111111111111111111111111114..";
                this.f[11] = ".......324.........311111114...........g.......311112222111111111111111111111111111111114.";
                this.f[12] = "......31114e......31111111114e.....3222122222221111111111111111111111111111111111111111114";
                this.f[13] = "222222111111222222111111111111222221111111111111111111111111111111111111111111111111111111";
                break;
            }
            case 12: {
                this.f[0] = "811111111111111111111111177777777777777777777777777777777777111111177777777777777777111119";
                this.f[1] = ".811111111111111111111119...d.d.d.d.d.......................8111119dd...............81119.";
                this.f[2] = "..8111111177771111111116...................g....c............81116.............b.....519..";
                this.f[3] = "...8777779..d.8111111116..................312222224....c......8116......c............56...";
                this.f[4] = ".......d.......811111116.................g5111111114...........816...................89...";
                this.f[5] = "....WFS.........87777779.................111111111114....c......89............b...z.......";
                this.f[6] = "..........c......d................f..f.f3111111111116..................f..................";
                this.f[7] = "...32222224..c..................f...f..f8111111111116......c...........f.....b.......34...";
                this.f[8] = "...511111114.......3224..................877711111119...........34...................56...";
                this.f[9] = "...51111111124e..e311114..................ddd8111119.........c.316.............b.....56...";
                this.f[10] = "...511111111111221111116......................87779...........3116........c..........514..";
                this.f[11] = "..3111111111111111111116............................fF.......31116...................5114.";
                this.f[12] = ".311111111111111111111114..e.e.e.e.e.........g..c...........3111114.ee..............311114";
                this.f[13] = "211111111111111111111111122222222222222222222122222222222222111111122222222222222222111111";
                break;
            }
        }
        for (int i = 0; i < 14; ++i) {
            for (int j = 0; j < 90; ++j) {
                this.s[i][j] = 0;
            }
        }
        for (int k = 0; k < 14; ++k) {
            for (int l = 0; l < 90; ++l) {
                final String substring = this.f[k].substring(l, l + 1);
                this.s[k][l] = 0;
                if (substring.equals("W") || substring.equals("F") || substring.equals("S")) {
                    this.s[k][l] = 0;
                    this.getMessage(substring, l * 10, k * 10);
                }
                if (substring.equals(".")) {
                    this.s[k][l] = 0;
                }
                if (substring.equals("1")) {
                    this.s[k][l] = 1;
                }
                if (substring.equals("2")) {
                    this.s[k][l] = 2;
                }
                if (substring.equals("3")) {
                    this.s[k][l] = 3;
                }
                if (substring.equals("4")) {
                    this.s[k][l] = 4;
                }
                if (substring.equals("5")) {
                    this.s[k][l] = 5;
                }
                if (substring.equals("6")) {
                    this.s[k][l] = 6;
                }
                if (substring.equals("7")) {
                    this.s[k][l] = 7;
                }
                if (substring.equals("8")) {
                    this.s[k][l] = 8;
                }
                if (substring.equals("9")) {
                    this.s[k][l] = 9;
                }
                if (substring.equals("a")) {
                    this.s[k][l] = -1;
                }
                if (substring.equals("b")) {
                    this.s[k][l] = -2;
                }
                if (substring.equals("c")) {
                    this.s[k][l] = -3;
                }
                if (substring.equals("d")) {
                    this.s[k][l] = -4;
                }
                if (substring.equals("e")) {
                    this.s[k][l] = -5;
                }
                if (substring.equals("f")) {
                    this.s[k][l] = -6;
                }
                if (substring.equals("g")) {
                    this.s[k][l] = -7;
                }
                if (substring.equals("z")) {
                    this.s[k][l] = -24;
                }
                int n;
                if (this.s[k][l] < 0) {
                    n = 0;
                }
                else {
                    n = this.s[k][l];
                }
                this.getAppletContext.drawImage(this.b[this.YI - 1][n], l * 10, k * 10, this.addMouseMotionListener);
            }
        }
        for (int n2 = 0; n2 < 18; ++n2) {
            this.getAudioClip.drawImage(this.b[this.YI - 1][2], n2 * 10, 130, this.addMouseMotionListener);
        }
        this.iI = false;
        this.zI = true;
        this.d = 135;
    }
    
    private void getMessage(final String s, final int n, final int n2) {
        if (s.equals("W")) {
            this.O[this.T] = 1;
        }
        if (s.equals("F")) {
            this.O[this.T] = 2;
        }
        if (s.equals("S")) {
            this.O[this.T] = 3;
        }
        this.P[this.T] = n;
        this.Q[this.T] = n2;
        ++this.T;
    }
    
    private void grabPixels() {
        if (!this.NI || this.d > -670) {
            --this.d;
            for (int i = 0; i < 6; ++i) {
                if (this.u[i]) {
                    final int[] w = this.w;
                    final int n = i;
                    --w[n];
                }
            }
        }
    }
    
    private void height() {
        for (int i = 0; i < 6; ++i) {
            if (this.u[i]) {
                final int[] zi = this.ZI;
                final int n = i;
                ++zi[n];
                if (this.ZI[i] > 4) {
                    this.u[i] = false;
                    this.w[i] = 0;
                    this.II[i] = 0;
                    this.ZI[i] = 0;
                }
            }
        }
        if (this.H) {
            ++this.N;
            if (this.N > 5) {
                this.getAudioClip();
            }
        }
        if (this.CI) {
            ++this.JI;
            if (this.JI > 5) {
                this.CI = false;
            }
        }
    }
    
    private void length() {
        if (this.J > 0) {
            this.J -= 2;
        }
        if (this.J < 1000 && this.waitForID) {
            this.I = true;
        }
        else {
            this.I = false;
        }
        if (this.I) {
            ++this.G;
            if (this.G > 10) {
                this.G = 0;
            }
        }
        if (this.J <= 0 && this.waitForID) {
            this.equals();
        }
    }
    
    private void loop() {
        if (this.V[0]) {
            this.W[0] += this.E;
            if (this.W[0] > 185) {
                this.V[0] = false;
            }
        }
        if (this.V[1]) {
            this.W[1] += this.E;
            if (this.W[1] > 185) {
                this.V[1] = false;
            }
        }
        if (this.V[2]) {
            this.W[2] += this.E;
            if (this.W[2] > 185) {
                this.V[2] = false;
            }
        }
        if (this.V[3]) {
            this.W[3] += this.E;
            if (this.W[3] > 185) {
                this.V[3] = false;
            }
        }
        if (this.V[4]) {
            this.W[4] += this.E;
            if (this.W[4] > 185) {
                this.V[4] = false;
            }
        }
        if (this.V[5]) {
            this.W[5] += this.E;
            if (this.W[5] > 185) {
                this.V[5] = false;
            }
        }
    }
    
    private void nextInt() {
        for (int i = 0; i < 10; ++i) {
            if (this.q[i]) {
                this.r[i] -= 3;
                if (this.r[i] < -2) {
                    this.q[i] = false;
                }
            }
        }
    }
    
    private void out() {
        this.getMessage = new Image[4][5];
        this.getSize = new Image[4][5];
        this.out = new Image[2];
        this.sleep = new Image[10];
        this.K = new Image[5];
        this.BI = new Image[5];
        this.v = new Image[5];
        this.getWidth = new Image[4];
        this.getX = new Image[25][5];
        this.getY = new Image[2][5];
        this.grabPixels = new Image[5][5];
        this.paint = new Image[2];
        this.play = new Image[6];
        this.start = new Image[10];
        this.startUp = new Image[30];
        this.i = 0;
        while (this.i < 4) {
            this.getWidth[this.i] = this.createImage(45, 11);
            (this.fillRect = this.getWidth[this.i].getGraphics()).drawImage(this.parseInt, -150, -33 - this.i * 12, this.addMouseMotionListener);
            ++this.i;
        }
        this.i = 0;
        while (this.i < 4) {
            this.j = 0;
            while (this.j < 3) {
                this.getSize[this.i][this.j] = this.createImage(32, 17);
                (this.fillRect = this.getSize[this.i][this.j].getGraphics()).drawImage(this.parseInt, -110 - this.i * 33, -160 - this.j * 18, this.addMouseMotionListener);
                ++this.j;
            }
            ++this.i;
        }
        this.i = 0;
        while (this.i < 4) {
            this.getSize[this.i][3] = this.createImage(32, 17);
            this.getSize[this.i][4] = this.createImage(32, 17);
            this.getSize[this.i][3] = this.getSize[this.i][1];
            this.getSize[this.i][4] = this.getSize[this.i][0];
            ++this.i;
        }
        this.i = 0;
        while (this.i < 4) {
            this.j = 0;
            while (this.j < 3) {
                this.getMessage[this.i][this.j] = this.createImage(26, 11);
                (this.fillRect = this.getMessage[this.i][this.j].getGraphics()).drawImage(this.parseInt, -this.i * 27, -this.j * 12, this.addMouseMotionListener);
                ++this.j;
            }
            ++this.i;
        }
        this.i = 0;
        while (this.i < 4) {
            this.getMessage[this.i][3] = this.createImage(26, 11);
            this.getMessage[this.i][4] = this.createImage(26, 11);
            this.getMessage[this.i][3] = this.getMessage[this.i][1];
            this.getMessage[this.i][4] = this.getMessage[this.i][0];
            ++this.i;
        }
        this.i = 0;
        while (this.i < 5) {
            this.K[this.i] = this.createImage(16, 11);
            (this.fillRect = this.K[this.i].getGraphics()).drawImage(this.parseInt, -this.i * 17, -237, this.addMouseMotionListener);
            ++this.i;
        }
        this.i = 0;
        while (this.i < 5) {
            this.BI[this.i] = this.createImage(30, 30);
            (this.fillRect = this.BI[this.i].getGraphics()).drawImage(this.parseInt, -this.i * 31, -491, this.addMouseMotionListener);
            ++this.i;
        }
        this.length = this.createImage(3, 3);
        (this.fillRect = this.length.getGraphics()).drawImage(this.parseInt, -112, -4, this.addMouseMotionListener);
        this.out[0] = this.createImage(3, 3);
        this.out[1] = this.createImage(9, 3);
        (this.fillRect = this.out[0].getGraphics()).drawImage(this.parseInt, -114, 0, this.addMouseMotionListener);
        (this.fillRect = this.out[1].getGraphics()).drawImage(this.parseInt, -118, 0, this.addMouseMotionListener);
        this.i = 0;
        while (this.i < 5) {
            this.v[this.i] = this.createImage(9, 9);
            (this.fillRect = this.v[this.i].getGraphics()).drawImage(this.parseInt, -97 - this.i * 10, -240, this.addMouseMotionListener);
            ++this.i;
        }
        this.i = 0;
        while (this.i < 5) {
            this.getX[1][this.i] = this.createImage(15, 9);
            (this.fillRect = this.getX[1][this.i].getGraphics()).drawImage(this.parseInt, -179 - this.i * 16, -101, this.addMouseMotionListener);
            ++this.i;
        }
        this.i = 0;
        while (this.i < 5) {
            this.getX[2][this.i] = this.createImage(21, 11);
            (this.fillRect = this.getX[2][this.i].getGraphics()).drawImage(this.parseInt, -179 - this.i * 22, -111, this.addMouseMotionListener);
            ++this.i;
        }
        this.i = 0;
        while (this.i < 5) {
            this.getX[3][this.i] = this.createImage(15, 9);
            (this.fillRect = this.getX[3][this.i].getGraphics()).drawImage(this.parseInt, -245, -131 - this.i * 10, this.addMouseMotionListener);
            ++this.i;
        }
        this.i = 0;
        while (this.i < 5) {
            this.getX[4][this.i] = this.createImage(7, 10);
            (this.fillRect = this.getX[4][this.i].getGraphics()).drawImage(this.parseInt, -179 - this.i * 8, -131, this.addMouseMotionListener);
            ++this.i;
        }
        this.i = 0;
        while (this.i < 5) {
            this.getX[5][this.i] = this.createImage(9, 15);
            (this.fillRect = this.getX[5][this.i].getGraphics()).drawImage(this.parseInt, -179 - this.i * 10, -85, this.addMouseMotionListener);
            ++this.i;
        }
        this.i = 0;
        while (this.i < 5) {
            this.getX[6][this.i] = this.createImage(21, 7);
            (this.fillRect = this.getX[6][this.i].getGraphics()).drawImage(this.parseInt, -179 - this.i * 22, -123, this.addMouseMotionListener);
            ++this.i;
        }
        this.i = 0;
        while (this.i < 5) {
            this.getX[7][this.i] = this.createImage(10, 10);
            (this.fillRect = this.getX[7][this.i].getGraphics()).drawImage(this.parseInt, -179 - this.i * 11, -142, this.addMouseMotionListener);
            ++this.i;
        }
        this.i = 0;
        while (this.i < 5) {
            this.getX[24][this.i] = this.createImage(20, 20);
            (this.fillRect = this.getX[24][this.i].getGraphics()).drawImage(this.parseInt, -168 - this.i * 21, -214, this.addMouseMotionListener);
            ++this.i;
        }
        this.j = 0;
        while (this.j < 2) {
            this.i = 0;
            while (this.i < 5) {
                this.getY[this.j][this.i] = this.createImage(27, 17);
                (this.fillRect = this.getY[this.j][this.i].getGraphics()).drawImage(this.parseInt, -238 - this.j * 28, -this.i * 18, this.addMouseMotionListener);
                ++this.i;
            }
            ++this.j;
        }
        this.j = 0;
        while (this.j < 5) {
            this.i = 0;
            while (this.i < 5) {
                this.grabPixels[this.j][this.i] = this.createImage(5, 24);
                (this.fillRect = this.grabPixels[this.j][this.i].getGraphics()).drawImage(this.parseInt, -294 - this.j * 6, -this.i * 25, this.addMouseMotionListener);
                ++this.i;
            }
            ++this.j;
        }
        this.status = this.createImage(34, 12);
        this.stop = this.createImage(44, 12);
        this.substring = this.createImage(57, 12);
        this.toString = this.createImage(174, 14);
        this.trim = this.createImage(59, 26);
        this.valueOf = this.createImage(104, 44);
        (this.fillRect = this.status.getGraphics()).drawImage(this.parseInt, 0, -59, this.addMouseMotionListener);
        (this.fillRect = this.stop.getGraphics()).drawImage(this.parseInt, -35, -59, this.addMouseMotionListener);
        (this.fillRect = this.substring.getGraphics()).drawImage(this.parseInt, 0, -72, this.addMouseMotionListener);
        (this.fillRect = this.toString.getGraphics()).drawImage(this.parseInt, 0, -85, this.addMouseMotionListener);
        (this.fillRect = this.trim.getGraphics()).drawImage(this.parseInt, -136, -5, this.addMouseMotionListener);
        (this.fillRect = this.valueOf.getGraphics()).drawImage(this.parseInt, -168, -236, this.addMouseMotionListener);
        this.paint[0] = this.createImage(57, 11);
        this.paint[1] = this.createImage(57, 11);
        (this.fillRect = this.paint[0].getGraphics()).drawImage(this.parseInt, -110, -214, this.addMouseMotionListener);
        (this.fillRect = this.paint[1].getGraphics()).drawImage(this.parseInt, -110, -226, this.addMouseMotionListener);
        this.i = 0;
        while (this.i < 10) {
            this.sleep[this.i] = this.createImage(7, 10);
            (this.fillRect = this.sleep[this.i].getGraphics()).drawImage(this.parseInt, -7 * this.i, -48, this.addMouseMotionListener);
            this.start[this.i] = this.createImage(5, 6);
            (this.fillRect = this.start[this.i].getGraphics()).drawImage(this.parseInt, -70 - 5 * this.i, -48, this.addMouseMotionListener);
            ++this.i;
        }
        this.i = 0;
        while (this.i < 30) {
            this.startUp[this.i] = this.createImage(6, 6);
            (this.fillRect = this.startUp[this.i].getGraphics()).drawImage(this.parseInt, -38 - 7 * this.i, -282, this.addMouseMotionListener);
            ++this.i;
        }
        this.loop = this.createImage(174, 47);
        (this.fillRect = this.loop.getGraphics()).drawImage(this.parseInt, 0, -100, this.addMouseMotionListener);
        this.newGame = this.createImage(174, 11);
        (this.fillRect = this.newGame.getGraphics()).drawImage(this.parseInt, 0, -148, this.addMouseMotionListener);
        this.R = new Image[5][4];
        this.i = 0;
        while (this.i < 4) {
            this.j = 0;
            while (this.j < 5) {
                this.R[this.j][this.i] = this.createImage(7, 7);
                (this.fillRect = this.R[this.j][this.i].getGraphics()).drawImage(this.parseInt, -198 - 8 * this.j, -1 - 8 * this.i, this.addMouseMotionListener);
                ++this.j;
            }
            ++this.i;
        }
        this.b = new Image[6][10];
        this.i = 0;
        while (this.i < 6) {
            this.j = 0;
            while (this.j < 10) {
                this.b[this.i][this.j] = this.createImage(10, 10);
                ++this.j;
            }
            ++this.i;
        }
        this.j = 0;
        while (this.j < 6) {
            this.i = 0;
            while (this.i < 10) {
                (this.fillRect = this.b[this.j][this.i].getGraphics()).drawImage(this.parseInt, -11 * this.i, -160 - this.j * 11, this.addMouseMotionListener);
                ++this.i;
            }
            ++this.j;
        }
        this.c = this.createImage(3, 15);
        (this.fillRect = this.c.getGraphics()).drawImage(this.parseInt, -136, -32, this.addMouseMotionListener);
        this.nextInt = this.createImage(50, 100);
        (this.fillRect = this.nextInt.getGraphics()).drawImage(this.parseInt, -289, -131, this.addMouseMotionListener);
        this.i = 0;
        while (this.i < 3) {
            this.play[this.i] = this.createImage(110, 100);
            this.play[this.i + 3] = this.createImage(110, 100);
            (this.fillRect = this.play[this.i].getGraphics()).drawImage(this.parseInt, -111 * this.i, -289, this.addMouseMotionListener);
            (this.fillRect = this.play[this.i + 3].getGraphics()).drawImage(this.parseInt, -111 * this.i, -390, this.addMouseMotionListener);
            ++this.i;
        }
        this.parseInt = null;
    }
    
    private void paint() {
        for (int i = 0; i < 10; ++i) {
            this.O[i] = 0;
            this.P[i] = 0;
            this.Q[i] = 0;
        }
        this.T = 0;
        for (int j = 0; j < 15; ++j) {
            this.a[j] = 0;
            this.e[j] = 0;
            this.g[j] = 0;
            this.h[j] = 0;
            this.k[j] = 0;
            this.l[j] = 0;
            this.m[j] = false;
            this.n[j] = false;
            this.o[j] = 0;
            this.p[j] = 0;
        }
        this.NI = false;
        for (int k = 0; k < 10; ++k) {
            this.q[k] = false;
            this.r[k] = 0;
            this.t[k] = 0;
        }
        for (int l = 0; l < 6; ++l) {
            this.V[l] = false;
            this.W[l] = 0;
            this.X[l] = 0;
        }
        for (int n = 0; n < 6; ++n) {
            this.u[n] = false;
            this.w[n] = 0;
            this.II[n] = 0;
            this.ZI[n] = 0;
        }
    }
    
    private static String parseInt(final int n, final int n2) {
        String s;
        for (s = String.valueOf(n); s.length() < n2; s = "0" + s) {}
        if (s.length() > n2) {
            for (s = "9"; s.length() < n2; s = "9" + s) {}
        }
        return s;
    }
    
    private static int play(final String s) {
        String trim;
        try {
            trim = s.trim();
        }
        catch (Exception ex) {
            trim = "0";
        }
        int int1;
        try {
            int1 = Integer.parseInt(trim);
        }
        catch (Exception ex2) {
            int1 = 0;
        }
        return int1;
    }
    
    public final Image downloadImage(final String s) {
        Image image2;
        try {
            final MediaTracker mediaTracker = new MediaTracker(this);
            final Image image = this.getImage(this.getCodeBase(), s);
            mediaTracker.addImage(image, 0);
            try {
                this.showStatus("Loading Image..." + s);
                mediaTracker.waitForID(0);
                this.showStatus("");
            }
            catch (InterruptedException ex2) {
                return null;
            }
            final int width = image.getWidth(this);
            final int height = image.getHeight(this);
            this.LGJC = new int[width * height];
            final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, this.LGJC, 0, width);
            try {
                pixelGrabber.grabPixels();
            }
            catch (InterruptedException ex3) {
                System.err.println("interrupted waiting for pixels!:" + s);
                this.showStatus("Image loading error:" + s);
                return null;
            }
            if ((pixelGrabber.status() & 0x80) != 0x0) {
                System.err.println("image fetch aborted or errored:" + s);
                this.showStatus("Image loading error:" + s);
                return null;
            }
            image2 = this.createImage(new MemoryImageSource(width, height, this.LGJC, 0, width));
            this.addImage = 0;
        }
        catch (Exception ex) {
            System.out.println("Error Getting Image:" + s + ":" + ex.getMessage());
            image2 = null;
        }
        return image2;
    }
}
