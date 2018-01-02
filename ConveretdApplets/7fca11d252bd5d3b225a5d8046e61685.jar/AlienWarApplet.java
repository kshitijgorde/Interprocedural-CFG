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
import java.awt.Color;
import java.awt.event.KeyEvent;
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

public class AlienWarApplet extends Applet implements Runnable, MouseMotionListener, MouseListener, KeyListener
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
    private AudioClip err;
    private AudioClip fillRect;
    private AudioClip getAppletContext;
    private AudioClip getAudioClip;
    private AudioClip getCodeBase;
    private AudioClip getDocumentBase;
    private AudioClip getGraphics;
    private AudioClip getHeight;
    private AudioClip getImage;
    private AudioClip getKeyCode;
    private Graphics getMessage;
    private Graphics getSize;
    private int getWidth;
    private int getX;
    private Image getY;
    private Image grabPixels;
    private Image length;
    private Image[] loop;
    private Image[][] newGame;
    private Image nextInt;
    private Image[] out;
    private Image[] paint;
    private Image[] parseInt;
    private Image play;
    private Image printStackTrace;
    private Image println;
    private Image setColor;
    private Image setCursor;
    private Image setFont;
    private Image[] showDocument;
    private Image[] showStatus;
    private Image sleep;
    private Image startUp;
    private Image status;
    private Image stop;
    private Image substring;
    private Image toString;
    private Image trim;
    private Image valueOf;
    private Image waitForID;
    private Image I;
    private boolean Z;
    private int C;
    private int B;
    private int D;
    private int F;
    private boolean[] J;
    private int[] S;
    private int[] A;
    private boolean[] E;
    private int[] G;
    private int[] H;
    private int[] K;
    private boolean[] L;
    private int M;
    private int N;
    private int O;
    private int[] P;
    private int[] Q;
    private int R;
    private int T;
    private boolean[] U;
    private int[] V;
    private int[] W;
    private boolean[] X;
    private int[] Y;
    private int[] z;
    private int[] c;
    private boolean b;
    private int d;
    private int f;
    private int s;
    private int a;
    private long e;
    private long g;
    private int h;
    private int k;
    private int l;
    private boolean m;
    private int n;
    private boolean o;
    private int p;
    private int q;
    private boolean r;
    private boolean t;
    private boolean u;
    private String v;
    private String w;
    private String II;
    private int ZI;
    private int CI;
    private boolean BI;
    private int DI;
    private int i;
    private int j;
    private Random FI;
    private int JI;
    private int SI;
    private int AI;
    private int EI;
    private int GI;
    private boolean HI;
    private boolean KI;
    private int LI;
    private int MI;
    
    public AlienWarApplet() {
        this.addImage = 0;
        this.addKeyListener = false;
        this.addMouseListener = true;
        this.currentThread = false;
        this.currentTimeMillis = false;
        this.downloadImage = null;
        this.drawImage = null;
        this.drawRect = null;
        this.drawString = null;
        this.err = null;
        this.fillRect = null;
        this.getAppletContext = null;
        this.getAudioClip = null;
        this.getCodeBase = null;
        this.getDocumentBase = null;
        this.getGraphics = null;
        this.getHeight = null;
        this.getImage = null;
        this.getKeyCode = null;
        this.D = 0;
        this.F = 0;
        this.r = true;
        this.t = true;
        this.BI = true;
    }
    
    public final void init() {
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.addKeyListener(this);
    }
    
    public final void start() {
        this.addKeyListener = false;
        this.e = 25L;
        this.startUp();
        (this.append = new Thread(this)).start();
    }
    
    public final void stop() {
        this.drawString(true);
        this.append.stop();
        this.append = null;
    }
    
    public final void destroy() {
        this.stop();
    }
    
    public final void startUp() {
        this.getWidth = this.getSize().width;
        this.getX = this.getSize().height;
        this.FI = new Random();
        this.out = new Image[5];
        this.loop = new Image[8];
        this.paint = new Image[5];
        this.parseInt = new Image[5];
        this.newGame = new Image[7][4];
        this.showDocument = new Image[10];
        this.showStatus = new Image[10];
        try {
            this.nextInt = this.downloadImage("./images/starfield.gif");
            this.setCursor = this.downloadImage("./images/title.gif");
            this.setFont = this.downloadImage("./images/gameover.gif");
            this.trim = this.downloadImage("./images/paused.gif");
            this.setColor = this.downloadImage("./images/images.gif");
            this.play = this.downloadImage("./images/stats.gif");
            this.valueOf = this.downloadImage("./images/start.gif");
            this.waitForID = this.downloadImage("./images/pause.gif");
            this.I = this.downloadImage("./images/resume.gif");
            this.printStackTrace = this.createImage(3, 6);
            (this.getMessage = this.printStackTrace.getGraphics()).drawImage(this.setColor, -315, 0, this.addMouseMotionListener);
            this.println = this.createImage(3, 6);
            (this.getMessage = this.println.getGraphics()).drawImage(this.setColor, -318, 0, this.addMouseMotionListener);
            this.length = this.createImage(33, 42);
            (this.getMessage = this.length.getGraphics()).drawImage(this.setColor, 0, -54, this.addMouseMotionListener);
            this.i = 0;
            while (this.i < 5) {
                this.parseInt[this.i] = this.createImage(27, 27);
                (this.getMessage = this.parseInt[this.i].getGraphics()).drawImage(this.setColor, -this.i * 27, 0, this.addMouseMotionListener);
                ++this.i;
            }
            this.loop[0] = this.createImage(21, 24);
            (this.getMessage = this.loop[0].getGraphics()).drawImage(this.setColor, 0, -114, this.addMouseMotionListener);
            this.i = 1;
            while (this.i < 8) {
                this.loop[this.i] = this.createImage(this.i * 24, 24);
                (this.getMessage = this.loop[this.i].getGraphics()).drawImage(this.setColor, -21, -114, this.addMouseMotionListener);
                ++this.i;
            }
            this.i = 0;
            while (this.i < 7) {
                this.j = 0;
                while (this.j < 2) {
                    this.newGame[this.i][this.j] = this.createImage(27, 27);
                    ++this.j;
                }
                ++this.i;
            }
            this.newGame[2][2] = this.createImage(27, 27);
            this.newGame[2][3] = this.createImage(27, 27);
            (this.getMessage = this.newGame[0][0].getGraphics()).drawImage(this.setColor, -135, 0, this.addMouseMotionListener);
            (this.getMessage = this.newGame[0][1].getGraphics()).drawImage(this.setColor, -162, 0, this.addMouseMotionListener);
            (this.getMessage = this.newGame[1][0].getGraphics()).drawImage(this.setColor, -189, 0, this.addMouseMotionListener);
            (this.getMessage = this.newGame[1][1].getGraphics()).drawImage(this.setColor, -216, 0, this.addMouseMotionListener);
            (this.getMessage = this.newGame[2][0].getGraphics()).drawImage(this.setColor, 0, -27, this.addMouseMotionListener);
            (this.getMessage = this.newGame[2][1].getGraphics()).drawImage(this.setColor, -27, -27, this.addMouseMotionListener);
            (this.getMessage = this.newGame[2][2].getGraphics()).drawImage(this.setColor, -54, -27, this.addMouseMotionListener);
            (this.getMessage = this.newGame[2][3].getGraphics()).drawImage(this.setColor, -81, -27, this.addMouseMotionListener);
            (this.getMessage = this.newGame[3][0].getGraphics()).drawImage(this.setColor, -243, 0, this.addMouseMotionListener);
            (this.getMessage = this.newGame[3][1].getGraphics()).drawImage(this.setColor, -270, 0, this.addMouseMotionListener);
            (this.getMessage = this.newGame[4][0].getGraphics()).drawImage(this.setColor, -108, -27, this.addMouseMotionListener);
            (this.getMessage = this.newGame[4][1].getGraphics()).drawImage(this.setColor, -135, -27, this.addMouseMotionListener);
            (this.getMessage = this.newGame[5][0].getGraphics()).drawImage(this.setColor, -162, -27, this.addMouseMotionListener);
            (this.getMessage = this.newGame[5][1].getGraphics()).drawImage(this.setColor, -189, -27, this.addMouseMotionListener);
            (this.getMessage = this.newGame[6][0].getGraphics()).drawImage(this.setColor, -216, -27, this.addMouseMotionListener);
            (this.getMessage = this.newGame[6][1].getGraphics()).drawImage(this.setColor, -243, -27, this.addMouseMotionListener);
            this.i = 0;
            while (this.i < 3) {
                this.out[this.i] = this.createImage(33, 60);
                this.paint[this.i] = this.createImage(45, 60);
                ++this.i;
            }
            (this.getMessage = this.out[0].getGraphics()).drawImage(this.setColor, 0, -54, this.addMouseMotionListener);
            (this.getMessage = this.out[1].getGraphics()).drawImage(this.setColor, -33, -54, this.addMouseMotionListener);
            (this.getMessage = this.out[2].getGraphics()).drawImage(this.setColor, -66, -54, this.addMouseMotionListener);
            this.out[3] = this.out[1];
            this.out[4] = this.out[0];
            (this.getMessage = this.paint[0].getGraphics()).drawImage(this.setColor, -99, -54, this.addMouseMotionListener);
            (this.getMessage = this.paint[1].getGraphics()).drawImage(this.setColor, -144, -54, this.addMouseMotionListener);
            (this.getMessage = this.paint[2].getGraphics()).drawImage(this.setColor, -189, -54, this.addMouseMotionListener);
            this.paint[3] = this.paint[1];
            this.paint[4] = this.paint[0];
            this.i = 0;
            while (this.i < 10) {
                this.showDocument[this.i] = this.createImage(15, 21);
                (this.getMessage = this.showDocument[this.i].getGraphics()).drawImage(this.setColor, -this.i * 15, -138, this.addMouseMotionListener);
                this.showStatus[this.i] = this.createImage(18, 24);
                (this.getMessage = this.showStatus[this.i].getGraphics()).drawImage(this.setColor, -this.i * 18, -192, this.addMouseMotionListener);
                ++this.i;
            }
            this.sleep = this.createImage(90, 30);
            (this.getMessage = this.sleep.getGraphics()).drawImage(this.setColor, 0, -159, this.addMouseMotionListener);
            this.startUp = this.createImage(159, 30);
            (this.getMessage = this.startUp.getGraphics()).drawImage(this.setColor, -90, -159, this.addMouseMotionListener);
            this.status = this.createImage(162, 30);
            (this.getMessage = this.status.getGraphics()).drawImage(this.setColor, 0, -216, this.addMouseMotionListener);
            this.stop = this.createImage(201, 24);
            (this.getMessage = this.stop.getGraphics()).drawImage(this.setColor, 0, -246, this.addMouseMotionListener);
            this.substring = this.createImage(333, 30);
            (this.getMessage = this.substring.getGraphics()).drawImage(this.setColor, 0, -273, this.addMouseMotionListener);
            this.toString = this.createImage(333, 30);
            (this.getMessage = this.toString.getGraphics()).drawImage(this.setColor, 0, -306, this.addMouseMotionListener);
            this.setColor = null;
        }
        catch (Exception ex) {
            System.out.println("Error Getting Images:" + ex.getMessage());
        }
        this.grabPixels = this.createImage(this.getWidth, this.getX);
        this.getSize = this.grabPixels.getGraphics();
        this.getY = this.createImage(378, 270);
        this.getMessage = this.getY.getGraphics();
        this.J = new boolean[3];
        this.S = new int[3];
        this.A = new int[3];
        this.U = new boolean[10];
        this.V = new int[10];
        this.W = new int[10];
        this.E = new boolean[16];
        this.G = new int[16];
        this.H = new int[16];
        this.K = new int[16];
        this.L = new boolean[16];
        this.P = new int[112];
        this.Q = new int[8];
        this.X = new boolean[4];
        this.Y = new int[4];
        this.z = new int[4];
        this.c = new int[4];
        this.r = true;
        this.t = true;
        this.drawRect();
        this.addKeyListener = true;
    }
    
    public final void newGame() {
        this.drawString(true);
        this.JI = 0;
        this.SI = 3;
        this.k = 1;
        this.l = 1;
        this.m = false;
        this.t = true;
        this.g = 50 - 5 * this.k;
        if (this.g < 15L) {
            this.g = 15L;
        }
        this.f = 1;
        this.h = 0;
        this.Z = true;
        this.C = 60;
        this.B = 210;
        this.i = 0;
        while (this.i < 3) {
            this.J[this.i] = false;
            this.S[this.i] = 0;
            this.A[this.i] = 0;
            ++this.i;
        }
        this.downloadImage();
        this.i = 0;
        while (this.i < 10) {
            this.U[this.i] = false;
            this.V[this.i] = 0;
            this.W[this.i] = 0;
            ++this.i;
        }
        this.i = 0;
        while (this.i < 4) {
            this.X[this.i] = false;
            this.Y[this.i] = 0;
            this.z[this.i] = 0;
            this.c[this.i] = 0;
            ++this.i;
        }
        this.b = false;
        this.BI = true;
        this.d = -128;
        this.r = true;
        this.t = true;
    }
    
    public final void keyPressed(final KeyEvent keyEvent) {
        this.LGJC(keyEvent.getKeyCode());
    }
    
    public final void keyTyped(final KeyEvent keyEvent) {
    }
    
    public final void keyReleased(final KeyEvent keyEvent) {
    }
    
    private void LGJC(final int n) {
        switch (this.DI = this.addImage(n)) {
            case 4: {
                this.F = 0;
                break;
            }
            case 5: {
                if (this.Z) {
                    int n2 = 0;
                    for (int i = 0; i < 3; ++i) {
                        if (!this.J[i] && n2 == 0) {
                            n2 = 1;
                            this.J[i] = true;
                            this.S[i] = this.C + 15;
                            this.A[i] = this.B - 3;
                            if (this.downloadImage != null) {
                                this.downloadImage.play();
                            }
                        }
                    }
                    break;
                }
                break;
            }
            case 1: {
                if (!this.Z) {
                    break;
                }
                this.F = 1;
                if (this.C > 10) {
                    --this.C;
                    break;
                }
                break;
            }
            case 2: {
                if (!this.Z) {
                    break;
                }
                this.F = 2;
                if (this.C < 300) {
                    ++this.C;
                    break;
                }
                break;
            }
            case 3: {
                this.F = 0;
                break;
            }
            case 6: {
                this.b = false;
                this.newGame();
                break;
            }
            case 7: {
                this.drawString(true);
                this.b = true;
                this.paint(this.getGraphics());
                break;
            }
            case 8: {
                this.b = false;
                break;
            }
        }
    }
    
    private int addImage(final int n) {
        int n2 = -1;
        if (!this.b) {
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
            if (n == 83 && this.f == 0) {
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
            graphics.fillRect(0, 0, this.getWidth, this.getX);
            graphics.setColor(new Color(255, 255, 255));
            graphics.setFont(new Font("Arial", 0, 12));
            graphics.drawString("Alien War", 20, 20);
            graphics.drawString("Loading Sounds please wait.", 20, 60);
            graphics.drawString("Sound " + this.createImage, 20, 80);
        }
        else {
            if (this.r) {
                this.LI = 4;
                this.MI = 4;
                this.getSize.setColor(new Color(0, 0, 0));
                this.getSize.fillRect(0, 0, this.getWidth, this.getX);
                this.getSize.setColor(new Color(85, 119, 255));
                this.getSize.drawRect(this.LI - 1, this.MI - 1, 384, 330);
                this.r = false;
            }
            if (this.addKeyListener) {
                this.getMessage.setColor(new Color(0, 0, 0));
                this.getMessage.fillRect(0, 0, this.getWidth, this.getX);
                this.getMessage.drawImage(this.nextInt, 0, this.d, this.addMouseMotionListener);
                this.getMessage.drawImage(this.nextInt, 0, this.d - 768, this.addMouseMotionListener);
                if (this.Z) {
                    if (this.s == 2) {
                        this.getMessage.drawImage(this.length, this.C, this.B, this.addMouseMotionListener);
                        this.getMessage.drawImage(this.paint[this.D], this.C - 6, this.B + 39, this.addMouseMotionListener);
                    }
                    else {
                        this.getMessage.drawImage(this.out[this.D], this.C, this.B, this.addMouseMotionListener);
                    }
                }
                if (this.J[0]) {
                    this.getMessage.drawImage(this.println, this.S[0], this.A[0] - 1, this.addMouseMotionListener);
                }
                if (this.J[1]) {
                    this.getMessage.drawImage(this.println, this.S[1], this.A[1] - 1, this.addMouseMotionListener);
                }
                if (this.J[2]) {
                    this.getMessage.drawImage(this.println, this.S[2], this.A[2] - 1, this.addMouseMotionListener);
                }
                if (this.l == 5) {
                    for (int i = 0; i < this.T; ++i) {
                        if (this.E[i]) {
                            if (this.L[i]) {
                                this.getMessage.drawImage(this.newGame[this.l - 1][this.M + this.n], this.AI, this.EI, this.addMouseMotionListener);
                            }
                            else {
                                this.getMessage.drawImage(this.newGame[this.l - 1][this.M + this.n], this.G[i], this.H[i], this.addMouseMotionListener);
                            }
                        }
                    }
                }
                else {
                    for (int j = 0; j < this.T; ++j) {
                        if (this.E[j]) {
                            this.getMessage.drawImage(this.newGame[this.l - 1][this.M + this.n], this.G[j], this.H[j], this.addMouseMotionListener);
                        }
                    }
                }
                if (this.U[0]) {
                    this.getMessage.drawImage(this.printStackTrace, this.V[0], this.W[0] - 1, this.addMouseMotionListener);
                }
                if (this.U[1]) {
                    this.getMessage.drawImage(this.printStackTrace, this.V[1], this.W[1] - 1, this.addMouseMotionListener);
                }
                if (this.U[2]) {
                    this.getMessage.drawImage(this.printStackTrace, this.V[2], this.W[2] - 1, this.addMouseMotionListener);
                }
                if (this.U[3]) {
                    this.getMessage.drawImage(this.printStackTrace, this.V[3], this.W[3] - 1, this.addMouseMotionListener);
                }
                if (this.U[4]) {
                    this.getMessage.drawImage(this.printStackTrace, this.V[4], this.W[4] - 1, this.addMouseMotionListener);
                }
                if (this.U[5]) {
                    this.getMessage.drawImage(this.printStackTrace, this.V[5], this.W[5] - 1, this.addMouseMotionListener);
                }
                if (this.U[6]) {
                    this.getMessage.drawImage(this.printStackTrace, this.V[6], this.W[6] - 1, this.addMouseMotionListener);
                }
                if (this.U[7]) {
                    this.getMessage.drawImage(this.printStackTrace, this.V[7], this.W[7] - 1, this.addMouseMotionListener);
                }
                if (this.U[8]) {
                    this.getMessage.drawImage(this.printStackTrace, this.V[8], this.W[8] - 1, this.addMouseMotionListener);
                }
                if (this.U[9]) {
                    this.getMessage.drawImage(this.printStackTrace, this.V[9], this.W[9] - 1, this.addMouseMotionListener);
                }
                if (this.X[0]) {
                    this.getMessage.drawImage(this.parseInt[this.c[0]], this.Y[0], this.z[0], this.addMouseMotionListener);
                }
                if (this.X[1]) {
                    this.getMessage.drawImage(this.parseInt[this.c[1]], this.Y[1], this.z[1], this.addMouseMotionListener);
                }
                if (this.X[2]) {
                    this.getMessage.drawImage(this.parseInt[this.c[2]], this.Y[2], this.z[2], this.addMouseMotionListener);
                }
                if (this.X[3]) {
                    this.getMessage.drawImage(this.parseInt[this.c[3]], this.Y[3], this.z[3], this.addMouseMotionListener);
                }
                this.getSize.setColor(new Color(50, 50, 50));
                this.getSize.fillRect(0, 0, this.getWidth, this.getX);
                this.getSize.drawImage(this.getY, this.LI, this.MI, this.addMouseMotionListener);
                this.v = err(this.JI, 5);
                this.w = err(this.k, 2);
                this.II = err(this.l, 2);
                this.t = false;
                this.getSize.drawImage(this.play, this.LI, this.MI + 273, this.addMouseMotionListener);
                this.getSize.drawImage(this.showDocument[0], this.LI + 219, this.MI + 303, this.addMouseMotionListener);
                this.getSize.drawImage(this.showDocument[this.l], this.LI + 234, this.MI + 303, this.addMouseMotionListener);
                for (int k = 0; k < 2; ++k) {
                    this.getSize.drawImage(this.showDocument[getAppletContext(this.w.substring(k, k + 1))], this.LI + 84 + k * 15, this.MI + 303, this.addMouseMotionListener);
                }
                if (this.SI > 0) {
                    this.getSize.drawImage(this.loop[this.SI], this.LI + 3, this.MI + 273, this.addMouseMotionListener);
                }
                for (int l = 0; l < 5; ++l) {
                    this.getSize.drawImage(this.showDocument[getAppletContext(this.v.substring(l, l + 1))], this.LI + 285 + l * 15, this.MI + 303, this.addMouseMotionListener);
                }
                this.getSize.setColor(new Color(85, 119, 255));
                this.getSize.drawRect(this.LI - 3, this.MI - 3, 384, 330);
                if (this.f == 0) {
                    this.getSize.drawImage(this.setCursor, this.LI + 57, this.MI + 90, this.addMouseMotionListener);
                }
                if (this.f == 3) {
                    this.getSize.drawImage(this.setFont, this.LI + 63, this.MI + 102, this.addMouseMotionListener);
                }
                if (this.f == 4) {
                    this.getSize.drawImage(this.sleep, this.LI + 30, this.MI + 90, this.addMouseMotionListener);
                    for (int n = 0; n < 2; ++n) {
                        this.getSize.drawImage(this.showDocument[getAppletContext(this.w.substring(n, n + 1))], this.LI + 126 + n * 15, this.MI + 90, this.addMouseMotionListener);
                    }
                    this.getSize.drawImage(this.startUp, this.LI + 165, this.MI + 90, this.addMouseMotionListener);
                    if (this.m) {
                        this.getSize.drawImage(this.status, this.LI + 30, this.MI + 150, this.addMouseMotionListener);
                    }
                }
                if (this.f == 5) {
                    switch (this.s) {
                        case 0: {
                            this.getSize.drawImage(this.stop, this.LI + 30, this.MI + 90, this.addMouseMotionListener);
                            break;
                        }
                        case 1: {
                            this.getSize.drawImage(this.toString, this.LI + 30, this.MI + 90, this.addMouseMotionListener);
                        }
                        case 3: {
                            this.getSize.drawImage(this.substring, this.LI + 30, this.MI + 90, this.addMouseMotionListener);
                            break;
                        }
                    }
                }
                this.getSize.drawImage(this.valueOf, 15, 338, this.addMouseMotionListener);
                if (this.b) {
                    this.getSize.drawImage(this.trim, this.LI + 63, this.MI + 102, this.addMouseMotionListener);
                    this.getSize.drawImage(this.I, 75, 338, this.addMouseMotionListener);
                }
                else {
                    this.getSize.drawImage(this.waitForID, 75, 338, this.addMouseMotionListener);
                }
                if (this.currentTimeMillis) {
                    this.getSize.setColor(new Color(0, 255, 0));
                    this.getSize.drawRect(229, 334, 154, 47);
                }
                graphics.drawImage(this.grabPixels, 0, 0, this);
            }
        }
    }
    
    public final void run() {
        int n = 1;
        this.fillRect();
        try {
            while (this.BI) {
                if (!this.b) {
                    final long currentTimeMillis = System.currentTimeMillis();
                    ++this.D;
                    if (this.D > 4) {
                        this.D = 0;
                    }
                    if (this.J[0]) {
                        this.A[0] -= 3;
                        if (this.A[0] < 1) {
                            this.J[0] = false;
                        }
                    }
                    if (this.J[1]) {
                        this.A[1] -= 3;
                        if (this.A[1] < 1) {
                            this.J[1] = false;
                        }
                    }
                    if (this.J[2]) {
                        this.A[2] -= 3;
                        if (this.A[2] < 1) {
                            this.J[2] = false;
                        }
                    }
                    switch (this.F) {
                        case 1: {
                            if (this.C > 3) {
                                this.C -= 3;
                                break;
                            }
                            this.F = 0;
                            break;
                        }
                        case 2: {
                            if (this.C < 342) {
                                this.C += 3;
                                break;
                            }
                            this.F = 0;
                            break;
                        }
                    }
                    if (this.l < 7) {
                        this.addKeyListener();
                        this.addKeyListener();
                        for (int i = 0; i < 10; ++i) {
                            if (this.U[i]) {
                                this.W[i] = this.W[i] + 2 + this.k;
                                if (this.W[i] > 273) {
                                    this.U[i] = false;
                                }
                            }
                        }
                        if (this.f == 1 && !this.u) {
                            this.currentTimeMillis();
                        }
                    }
                    else if (this.p < 0 && this.f == 1) {
                        this.currentTimeMillis();
                    }
                    this.addMouseListener();
                    if (n != 0) {
                        this.d += 3;
                        if (this.d >= 384) {
                            this.d = -384;
                        }
                        this.createImage();
                        n = 0;
                    }
                    else {
                        for (int j = 0; j < 4; ++j) {
                            if (this.X[j]) {
                                final int[] c = this.c;
                                final int n2 = j;
                                ++c[n2];
                                if (this.c[j] > 4) {
                                    this.X[j] = false;
                                    this.Y[j] = 0;
                                    this.z[j] = 0;
                                    this.c[j] = 0;
                                }
                            }
                        }
                        n = 1;
                    }
                    if (this.f != 1) {
                        if (this.f == 0) {
                            --this.CI;
                            if (this.CI <= 0) {
                                ++this.l;
                                this.t = true;
                                this.CI = 400;
                                if (this.l >= 8) {
                                    this.l = 1;
                                }
                                this.downloadImage();
                            }
                        }
                        if (this.f == 2) {
                            --this.h;
                            if (this.h <= 0) {
                                this.Z = true;
                                this.C = 60;
                                this.B = 210;
                                this.F = 0;
                                this.f = this.a;
                            }
                        }
                        if (this.f == 3) {
                            --this.h;
                            if (this.h <= 0) {
                                this.drawString(true);
                                this.drawRect();
                            }
                        }
                        if (this.f == 4) {
                            --this.h;
                            if (this.h <= 0) {
                                ++this.k;
                                this.t = true;
                                this.g = 50 - 5 * this.k;
                                if (this.g < 15L) {
                                    this.g = 15L;
                                }
                                this.downloadImage();
                                this.f = 1;
                            }
                        }
                        if (this.f == 5) {
                            this.drawString(false);
                            --this.h;
                            if (this.h == 275) {
                                if (this.drawString != null) {
                                    this.drawString.play();
                                }
                                if (this.err != null) {
                                    this.err.loop();
                                }
                            }
                            if (this.h == 40) {
                                if (this.err != null) {
                                    this.err.stop();
                                }
                                if (this.fillRect != null) {
                                    this.fillRect.play();
                                }
                            }
                            if (this.h == 35 && this.err != null) {
                                this.err.stop();
                            }
                            if (this.h < 280) {
                                this.s = 1;
                            }
                            if (this.h < 240) {
                                this.s = 2;
                            }
                            if (this.h < 40) {
                                this.s = 3;
                            }
                            if (this.s == 1 && this.B > 150) {
                                this.B -= 3;
                            }
                            if (this.s == 2 && this.B < 210) {
                                this.d += 6;
                                if (this.d >= 384) {
                                    this.d = -384;
                                }
                            }
                            if (this.s == 3 && this.B < 210) {
                                this.B += 3;
                            }
                            if (this.h <= 0) {
                                this.err.stop();
                                ++this.l;
                                this.t = true;
                                this.downloadImage();
                                this.f = 1;
                                if (this.l >= 8) {
                                    this.l = 1;
                                    this.f = 4;
                                    if (this.SI < 7) {
                                        this.m = true;
                                        ++this.SI;
                                    }
                                    else {
                                        this.m = false;
                                    }
                                    this.h = 80;
                                }
                            }
                        }
                    }
                    this.paint(this.getGraphics());
                    final long n3 = System.currentTimeMillis() - currentTimeMillis;
                    this.e = 0L;
                    if (n3 >= this.g) {
                        continue;
                    }
                    this.e = this.g - n3;
                    try {
                        Thread.currentThread();
                        Thread.sleep(this.e);
                    }
                    catch (InterruptedException ex2) {}
                }
                else {
                    try {
                        Thread.currentThread();
                        Thread.sleep(100L);
                    }
                    catch (InterruptedException ex3) {}
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
        if (x > 15 && x < 66 && y > 338 && y < 378) {
            this.b = false;
            this.newGame();
        }
        if (x > 75 && x < 126 && y > 338 && y < 378) {
            if (this.b) {
                this.b = false;
                this.paint(this.getGraphics());
            }
            else {
                this.drawString(true);
                this.b = true;
                this.paint(this.getGraphics());
            }
        }
        if (x > 228 && x < 384 && y > 333 && y < 382) {
            this.drawString(true);
            this.b = true;
            this.paint(this.getGraphics());
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
        if (x > 228 && x < 384 && y > 333 && y < 382) {
            this.setCursor(new Cursor(12));
            this.currentTimeMillis = true;
        }
        else {
            this.setCursor(new Cursor(0));
            this.currentTimeMillis = false;
        }
    }
    
    private void addKeyListener() {
        int n = (this.FI.nextInt() % 300 - (300 - this.T * this.k)) / this.k;
        if (n >= 0 && n < this.T) {
            int n2 = 0;
            int n3 = 0;
            while (n2 == 0 && n3 < this.T) {
                if (this.E[n]) {
                    n3 = 0;
                    for (int n4 = 0; n4 == 0 && n3 < 10; ++n3) {
                        if (!this.U[n3]) {
                            n4 = 1;
                            if (this.L[n]) {
                                this.W[n3] = this.EI + 27;
                                this.V[n3] = this.AI + 12;
                                this.U[n3] = true;
                            }
                            else {
                                this.W[n3] = this.H[n] + 27;
                                this.V[n3] = this.G[n] + 12;
                                this.U[n3] = true;
                            }
                        }
                    }
                    n2 = 1;
                }
                ++n3;
                if (++n >= this.T) {
                    n = 0;
                }
            }
        }
    }
    
    private void addMouseListener() {
        int n = 0;
        int n2 = 0;
        switch (this.l) {
            case 1: {
                ++this.O;
                if (this.O <= 16) {
                    for (int i = 0; i < this.T; ++i) {
                        this.append(i, (this.O + 1) / 2);
                    }
                }
                if (this.O > 16) {
                    for (int j = 0; j < this.T; ++j) {
                        if (this.G[j] < 9 && this.E[j]) {
                            n = 4;
                        }
                        if (this.G[j] > 342 && this.E[j]) {
                            n = 8;
                        }
                    }
                    if (n > 0) {
                        for (int k = 0; k < this.T; ++k) {
                            this.K[k] = n;
                        }
                        n2 = 1;
                    }
                    for (int l = 0; l < this.T; ++l) {
                        if (this.H[l] > 165 && this.E[l]) {
                            n2 = 0;
                        }
                    }
                    if (n2 != 0) {
                        for (int n3 = 0; n3 < this.T; ++n3) {
                            this.H[n3] += 12;
                        }
                    }
                    for (int n4 = 0; n4 < this.T; ++n4) {
                        this.append(n4, this.K[n4]);
                        this.append(n4, this.K[n4]);
                    }
                }
                if (this.O > 18) {
                    this.O = 0;
                    break;
                }
                break;
            }
            case 2: {
                ++this.O;
                if (this.O <= 8) {
                    for (int n5 = 0; n5 < this.T; ++n5) {
                        this.append(n5, this.O);
                    }
                }
                if (this.O > 8) {
                    for (int n6 = 0; n6 < this.T; ++n6) {
                        if (this.G[n6] < 9 && this.E[n6]) {
                            n = 4;
                        }
                        if (this.G[n6] > 342 && this.E[n6]) {
                            n = 8;
                        }
                    }
                    if (n > 0) {
                        for (int n7 = 0; n7 < this.T; ++n7) {
                            this.K[n7] = n;
                        }
                        n2 = 1;
                    }
                    for (int n8 = 0; n8 < this.T; ++n8) {
                        if (this.H[n8] > 165 && this.E[n8]) {
                            n2 = 0;
                        }
                    }
                    if (n2 != 0) {
                        for (int n9 = 0; n9 < this.T; ++n9) {
                            this.H[n9] += 15;
                        }
                    }
                    for (int n10 = 0; n10 < this.T; ++n10) {
                        this.append(n10, this.K[n10]);
                        this.append(n10, this.K[n10]);
                    }
                }
                if (this.O > 10) {
                    this.O = 0;
                    break;
                }
                break;
            }
            case 3: {
                for (int n11 = 0; n11 < this.T; ++n11) {
                    this.append(n11, this.K[n11]);
                }
                boolean b = true;
                for (int n12 = 0; n12 < this.T; ++n12) {
                    if (this.H[n12] < 273 && this.E[n12]) {
                        b = false;
                    }
                }
                if (!b) {
                    break;
                }
                if (this.K[0] == 5) {
                    this.G[0] = 450;
                    this.H[0] = 45;
                    this.G[1] = 420;
                    this.H[1] = 75;
                    this.G[2] = 435;
                    this.H[2] = 0;
                    this.G[3] = 432;
                    this.H[3] = -45;
                    this.G[4] = 450;
                    this.H[4] = -90;
                    this.G[5] = 465;
                    this.H[5] = 0;
                    this.G[6] = 465;
                    this.H[6] = -45;
                    this.G[7] = 510;
                    this.H[7] = -60;
                    this.G[8] = 270;
                    this.H[8] = -15;
                    this.G[9] = 180;
                    this.H[9] = 0;
                    this.i = 0;
                    while (this.i < 10) {
                        this.K[this.i] = 7;
                        ++this.i;
                    }
                    this.n = 0;
                }
                else {
                    this.G[0] = -90;
                    this.H[0] = 45;
                    this.G[1] = -60;
                    this.H[1] = 75;
                    this.G[2] = -75;
                    this.H[2] = 0;
                    this.G[3] = -78;
                    this.H[3] = -45;
                    this.G[4] = -90;
                    this.H[4] = -90;
                    this.G[5] = -105;
                    this.H[5] = 0;
                    this.G[6] = -105;
                    this.H[6] = -45;
                    this.G[7] = -150;
                    this.H[7] = -60;
                    this.G[8] = 90;
                    this.H[8] = -15;
                    this.G[9] = 180;
                    this.H[9] = 0;
                    this.i = 0;
                    while (this.i < 10) {
                        this.K[this.i] = 5;
                        ++this.i;
                    }
                    this.n = 2;
                }
                if (this.f > 0 && this.getCodeBase != null) {
                    this.getCodeBase.play();
                    break;
                }
                break;
            }
            case 4: {
                for (int n13 = 0; n13 < this.T; ++n13) {
                    for (int n14 = 0; n14 < this.k; ++n14) {
                        this.append(n13, 8);
                    }
                }
                boolean b2 = true;
                for (int n15 = 0; n15 < this.T; ++n15) {
                    if (this.G[n15] > -30 && this.E[n15]) {
                        b2 = false;
                    }
                }
                if (b2) {
                    int n16 = this.H[0] + 20;
                    if (n16 > 150) {
                        n16 = 150;
                    }
                    this.G[0] = 420;
                    this.H[0] = n16;
                    this.G[1] = 480;
                    this.H[1] = n16;
                    this.G[2] = 540;
                    this.H[2] = n16;
                    this.G[3] = 600;
                    this.H[3] = n16;
                    this.G[4] = 660;
                    this.H[4] = n16;
                    this.G[5] = 720;
                    this.H[5] = n16;
                    this.G[6] = 780;
                    this.H[6] = n16;
                    this.G[7] = 840;
                    this.H[7] = n16;
                    this.G[8] = 450;
                    this.H[8] = 30 + n16;
                    this.G[9] = 510;
                    this.H[9] = 30 + n16;
                    this.G[10] = 570;
                    this.H[10] = 30 + n16;
                    this.G[11] = 630;
                    this.H[11] = 30 + n16;
                    this.G[12] = 690;
                    this.H[12] = 30 + n16;
                    this.G[13] = 750;
                    this.H[13] = 30 + n16;
                    this.G[14] = 810;
                    this.H[14] = 30 + n16;
                    this.G[15] = 870;
                    this.H[15] = 30 + n16;
                    break;
                }
                break;
            }
            case 5: {
                ++this.O;
                if (this.O <= 16) {
                    for (int n17 = 0; n17 < this.T; ++n17) {
                        this.append(n17, (this.O + 1) / 2);
                    }
                }
                if (this.O > 16) {
                    for (int n18 = 0; n18 < this.T; ++n18) {
                        if (this.G[n18] < 9 && this.E[n18]) {
                            n = 4;
                        }
                        if (this.G[n18] > 342 && this.E[n18]) {
                            n = 8;
                        }
                    }
                    if (n > 0) {
                        for (int n19 = 0; n19 < this.T; ++n19) {
                            this.K[n19] = n;
                        }
                        n2 = 1;
                    }
                    for (int n20 = 0; n20 < this.T; ++n20) {
                        if (this.H[n20] > 180 && this.E[n20]) {
                            n2 = 0;
                        }
                    }
                    if (n2 != 0) {
                        for (int n21 = 0; n21 < this.T; ++n21) {
                            this.H[n21] += 9;
                        }
                    }
                    for (int n22 = 0; n22 < this.T; ++n22) {
                        this.append(n22, this.K[n22]);
                        this.append(n22, this.K[n22]);
                    }
                }
                this.GI = -1;
                for (int gi = 0; gi < this.T; ++gi) {
                    if (this.L[gi] && this.E[gi]) {
                        this.GI = gi;
                    }
                }
                if (this.GI == -1) {
                    int gi2;
                    for (gi2 = 0; !this.E[gi2] && gi2 < this.T; ++gi2) {}
                    this.GI = gi2;
                    this.L[this.GI] = true;
                    this.AI = this.G[this.GI];
                    this.EI = this.H[this.GI];
                    this.KI = false;
                    if (this.f > 0 && this.getHeight != null) {
                        this.getHeight.play();
                    }
                }
                if (this.HI) {
                    this.addMouseMotionListener();
                    this.HI = false;
                }
                else {
                    this.HI = true;
                }
                if (this.EI > 273) {
                    this.KI = true;
                    this.AI = this.G[this.GI];
                    this.EI = 0;
                }
                if (this.O > 18) {
                    this.O = 0;
                    break;
                }
                break;
            }
            case 6: {
                if (this.o) {
                    for (int n23 = 0; n23 < this.T; ++n23) {
                        this.H[n23] += 3;
                        if (this.H[n23] >= 180) {
                            this.o = false;
                        }
                    }
                    break;
                }
                for (int n24 = 0; n24 < this.T; ++n24) {
                    this.append(n24, this.P[this.Q[n24]]);
                    final int[] q = this.Q;
                    final int n25 = n24;
                    ++q[n25];
                    if (this.Q[n24] > 111) {
                        this.Q[n24] = 0;
                    }
                    ++this.R;
                }
                for (int n26 = 0; n26 < this.T; ++n26) {
                    if (this.G[n26] < 9 && this.E[n26]) {
                        n = 2;
                    }
                    if (this.G[n26] > 342 && this.E[n26]) {
                        n = 1;
                    }
                }
                if (n > 0) {
                    for (int n27 = 0; n27 < this.T; ++n27) {
                        this.K[n27] = n;
                    }
                }
                for (int n28 = 0; n28 < this.T; ++n28) {
                    if (this.E[n28]) {
                        switch (this.K[n28]) {
                            case 1: {
                                this.G[n28] -= 3;
                                break;
                            }
                            case 2: {
                                this.G[n28] += 3;
                                break;
                            }
                        }
                    }
                }
                break;
            }
            case 7: {
                for (int n29 = 0; n29 < this.q; ++n29) {
                    if (this.E[n29]) {
                        this.H[n29] = this.H[n29] + 1 + 2 * this.k;
                        if (this.H[n29] > 273) {
                            this.E[n29] = false;
                        }
                    }
                }
                if (this.p > 130) {
                    for (int n30 = 0; n30 < this.q; ++n30) {
                        final int n31 = this.FI.nextInt() % 2400;
                        if (!this.E[n30] && n31 < 120 && n31 > 0) {
                            this.E[n30] = true;
                            this.G[n30] = n31 * 3;
                            this.H[n30] = -30;
                        }
                    }
                }
                --this.p;
                break;
            }
        }
        --this.N;
        if (this.N <= 0) {
            if (this.M == 0) {
                this.M = 1;
            }
            else {
                this.M = 0;
            }
            this.N = 10;
        }
    }
    
    private void addMouseMotionListener() {
        this.EI += 3;
        if (this.EI >= this.H[this.GI] && this.KI) {
            this.L[this.GI] = false;
        }
        else {
            this.AI = this.G[this.GI];
        }
    }
    
    private void append(final int n, final int n2) {
        switch (n2) {
            case 1: {
                this.G[n] -= 3;
                this.H[n] -= 3;
                break;
            }
            case 2: {
                this.H[n] -= 3;
                break;
            }
            case 3: {
                this.G[n] += 3;
                this.H[n] -= 3;
                break;
            }
            case 4: {
                this.G[n] += 3;
                break;
            }
            case 5: {
                this.G[n] += 3;
                this.H[n] += 3;
                break;
            }
            case 6: {
                this.H[n] += 3;
                break;
            }
            case 7: {
                this.G[n] -= 3;
                this.H[n] += 3;
                break;
            }
            case 8: {
                this.G[n] -= 3;
                break;
            }
        }
    }
    
    private void createImage() {
        if (this.l < 7) {
            if (this.l == 5) {
                for (int i = 0; i < 3; ++i) {
                    if (this.J[i]) {
                        final int n = this.A[i];
                        final int n2 = this.S[i];
                        for (int j = 0; j < this.T; ++j) {
                            if (this.L[j]) {
                                if (n2 >= this.AI && n2 <= this.AI + 27 && n >= this.EI && n <= this.EI + 27 && this.E[j]) {
                                    this.JI += 10 * this.k;
                                    this.t = true;
                                    this.E[j] = false;
                                    if (this.drawImage != null) {
                                        this.drawImage.play();
                                    }
                                    this.currentThread();
                                    this.J[i] = false;
                                    int n3 = 0;
                                    for (int k = 0; k < 4; ++k) {
                                        if (!this.X[k] && n3 == 0) {
                                            this.X[k] = true;
                                            this.Y[k] = this.AI;
                                            this.z[k] = this.EI;
                                            this.c[k] = 0;
                                            n3 = 1;
                                        }
                                    }
                                }
                            }
                            else if (n <= this.H[j] + 27 && n2 >= this.G[j] && this.E[j] && n >= this.H[j] && n2 <= this.G[j] + 27) {
                                this.JI += 10 * this.k;
                                this.t = true;
                                this.E[j] = false;
                                if (this.drawImage != null) {
                                    this.drawImage.play();
                                }
                                this.currentThread();
                                this.J[i] = false;
                                int n4 = 0;
                                for (int l = 0; l < 4; ++l) {
                                    if (!this.X[l] && n4 == 0) {
                                        this.X[l] = true;
                                        this.Y[l] = this.G[j];
                                        this.z[l] = this.H[j];
                                        this.c[l] = 0;
                                        n4 = 1;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else {
                for (int n5 = 0; n5 < 3; ++n5) {
                    if (this.J[n5]) {
                        final int n6 = this.A[n5];
                        final int n7 = this.S[n5];
                        for (int n8 = 0; n8 < this.T; ++n8) {
                            if (n6 <= this.H[n8] + 27 && n7 >= this.G[n8] && this.E[n8] && n6 >= this.H[n8] && n7 <= this.G[n8] + 27) {
                                this.JI += 10 * this.k;
                                this.t = true;
                                this.E[n8] = false;
                                if (this.drawImage != null) {
                                    this.drawImage.play();
                                }
                                this.currentThread();
                                this.J[n5] = false;
                                int n9 = 0;
                                for (int n10 = 0; n10 < 4; ++n10) {
                                    if (!this.X[n10] && n9 == 0) {
                                        this.X[n10] = true;
                                        this.Y[n10] = this.G[n8];
                                        this.z[n10] = this.H[n8];
                                        this.c[n10] = 0;
                                        n9 = 1;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            for (int n11 = 0; n11 < 10; ++n11) {
                if (this.U[n11] && this.W[n11] >= this.B && this.V[n11] >= this.C && this.V[n11] <= this.C + 33 && this.W[n11] <= this.B + 39 && this.Z) {
                    this.Z = false;
                    --this.SI;
                    if (this.drawRect != null) {
                        this.drawRect.play();
                    }
                    this.t = true;
                    if (this.SI > 0) {
                        this.a = this.f;
                        this.f = 2;
                        this.h = 40;
                    }
                    else {
                        this.f = 3;
                        this.h = 80;
                    }
                    this.U[n11] = false;
                    int n12 = 0;
                    for (int n13 = 0; n13 < 4; ++n13) {
                        if (!this.X[n13] && n12 == 0) {
                            this.X[n13] = true;
                            this.Y[n13] = this.C;
                            this.z[n13] = this.B;
                            this.c[n13] = 0;
                            n12 = 1;
                        }
                    }
                }
            }
        }
        for (int n14 = 0; n14 < this.T; ++n14) {
            int ai;
            int ei;
            if (this.L[n14]) {
                ai = this.AI;
                ei = this.EI;
            }
            else {
                ai = this.G[n14];
                ei = this.H[n14];
            }
            if ((ei + 27 > this.B & this.E[n14]) && this.Z && ai + 27 > this.C && ai < this.C + 33 && ei + 27 > this.B && ei < this.B + 39) {
                this.Z = false;
                --this.SI;
                if (this.drawRect != null) {
                    this.drawRect.play();
                }
                this.t = true;
                if (this.SI > 0) {
                    this.a = this.f;
                    this.f = 2;
                    this.h = 40;
                }
                else {
                    this.f = 3;
                    this.h = 80;
                }
                int n15 = 0;
                for (int n16 = 0; n16 < 4; ++n16) {
                    if (!this.X[n16] && n15 == 0) {
                        this.X[n16] = true;
                        this.Y[n16] = this.C;
                        this.z[n16] = this.B;
                        this.c[n16] = 0;
                        n15 = 1;
                    }
                }
                if (this.l < 7) {
                    this.E[n14] = false;
                    if (this.drawImage != null) {
                        this.drawImage.play();
                    }
                    this.currentThread();
                    int n17 = 0;
                    for (int n18 = 0; n18 < 4; ++n18) {
                        if (!this.X[n18] && n17 == 0) {
                            this.X[n18] = true;
                            this.Y[n18] = this.G[n18];
                            this.z[n18] = this.H[n18];
                            this.c[n18] = 0;
                            n17 = 1;
                        }
                    }
                }
            }
        }
    }
    
    private void currentThread() {
        this.u = false;
        for (int i = 0; i < this.T; ++i) {
            if (this.E[i]) {
                this.u = true;
            }
        }
    }
    
    private void currentTimeMillis() {
        this.f = 5;
        this.h = 360;
        this.s = 0;
    }
    
    private void downloadImage() {
        this.M = 0;
        this.N = 5;
        this.u = true;
        for (int i = 0; i < 10; ++i) {
            this.U[i] = false;
            this.V[i] = 0;
            this.W[i] = 0;
        }
        switch (this.l) {
            case 1: {
                for (int j = 0; j < 4; ++j) {
                    this.E[j] = true;
                    this.G[j] = 60 + j * 60;
                    this.H[j] = 0;
                    this.K[j] = 4;
                    this.L[j] = false;
                    this.E[j + 4] = true;
                    this.G[j + 4] = 90 + j * 60;
                    this.H[j + 4] = 30;
                    this.K[j + 4] = 4;
                    this.L[j + 4] = false;
                    this.E[j + 8] = true;
                    this.G[j + 8] = 60 + j * 60;
                    this.H[j + 8] = 60;
                    this.K[j + 8] = 4;
                    this.L[j + 8] = false;
                }
                for (int k = 12; k < 16; ++k) {
                    this.E[k] = false;
                    this.L[k] = false;
                }
                this.E[7] = false;
                this.O = 4;
                if (this.f == 0) {
                    for (int l = 0; l < 16; ++l) {
                        this.H[l] += 15;
                    }
                }
                this.n = 0;
                this.T = 12;
                if (this.f > 0 && this.getAppletContext != null) {
                    this.getAppletContext.loop();
                    break;
                }
                break;
            }
            case 2: {
                for (int n = 0; n < 13; ++n) {
                    this.E[n] = true;
                    this.K[n] = 4;
                    this.L[n] = false;
                }
                for (int n2 = 13; n2 < 16; ++n2) {
                    this.E[n2] = false;
                    this.L[n2] = false;
                }
                this.G[0] = 210;
                this.H[0] = 60;
                this.G[1] = 231;
                this.H[1] = 30;
                this.G[2] = 189;
                this.H[2] = 30;
                this.G[3] = 168;
                this.H[3] = 0;
                this.G[4] = 210;
                this.H[4] = 0;
                this.G[5] = 252;
                this.H[5] = 0;
                this.G[6] = 147;
                this.H[6] = -30;
                this.G[7] = 189;
                this.H[7] = -30;
                this.G[8] = 231;
                this.H[8] = -30;
                this.G[9] = 273;
                this.H[9] = -30;
                this.G[10] = 51;
                this.H[10] = 15;
                this.G[11] = 30;
                this.H[11] = -15;
                this.G[12] = 72;
                this.H[12] = -15;
                this.n = 0;
                this.T = 13;
                if (this.f == 0) {
                    for (int n3 = 0; n3 < 16; ++n3) {
                        this.H[n3] += 45;
                    }
                }
                if (this.f > 0 && this.getAudioClip != null) {
                    this.getAudioClip.loop();
                    break;
                }
                break;
            }
            case 3: {
                for (int n4 = 0; n4 < 10; ++n4) {
                    this.E[n4] = true;
                    this.K[n4] = 7;
                    this.L[n4] = false;
                }
                for (int n5 = 10; n5 < 16; ++n5) {
                    this.E[n5] = false;
                    this.L[n5] = false;
                }
                this.G[0] = 450;
                this.H[0] = 45;
                this.G[1] = 420;
                this.H[1] = 75;
                this.G[2] = 435;
                this.H[2] = 0;
                this.G[3] = 432;
                this.H[3] = -45;
                this.G[4] = 450;
                this.H[4] = -90;
                this.G[5] = 465;
                this.H[5] = 0;
                this.G[6] = 465;
                this.H[6] = -45;
                this.G[7] = 510;
                this.H[7] = -60;
                this.G[8] = 270;
                this.H[8] = -15;
                this.G[9] = 180;
                this.H[9] = 0;
                this.n = 0;
                this.T = 10;
                if (this.f > 0 && this.getCodeBase != null) {
                    this.getCodeBase.play();
                    break;
                }
                break;
            }
            case 4: {
                for (int n6 = 0; n6 < 16; ++n6) {
                    this.E[n6] = true;
                    this.K[n6] = 8;
                    this.L[n6] = false;
                }
                int n7 = (this.k - 1) * 60 + 15;
                if (n7 > 150) {
                    n7 = 150;
                }
                this.G[0] = 420;
                this.H[0] = n7;
                this.G[1] = 480;
                this.H[1] = n7;
                this.G[2] = 540;
                this.H[2] = n7;
                this.G[3] = 600;
                this.H[3] = n7;
                this.G[4] = 660;
                this.H[4] = n7;
                this.G[5] = 720;
                this.H[5] = n7;
                this.G[6] = 780;
                this.H[6] = n7;
                this.G[7] = 840;
                this.H[7] = n7;
                this.G[8] = 450;
                this.H[8] = 30 + n7;
                this.G[9] = 510;
                this.H[9] = 30 + n7;
                this.G[10] = 570;
                this.H[10] = 30 + n7;
                this.G[11] = 630;
                this.H[11] = 30 + n7;
                this.G[12] = 690;
                this.H[12] = 30 + n7;
                this.G[13] = 750;
                this.H[13] = 30 + n7;
                this.G[14] = 810;
                this.H[14] = 30 + n7;
                this.G[15] = 870;
                this.H[15] = 30 + n7;
                this.n = 0;
                this.T = 16;
                if (this.f > 0 && this.getDocumentBase != null) {
                    this.getDocumentBase.loop();
                    break;
                }
                break;
            }
            case 5: {
                for (int n8 = 0; n8 < 4; ++n8) {
                    this.E[n8] = true;
                    this.G[n8] = 60 + n8 * 60;
                    this.H[n8] = 30;
                    this.K[n8] = 4;
                    this.L[n8] = false;
                    this.E[n8 + 4] = true;
                    this.G[n8 + 4] = 60 + n8 * 60;
                    this.H[n8 + 4] = 60;
                    this.K[n8 + 4] = 4;
                    this.L[n8 + 4] = false;
                }
                for (int n9 = 8; n9 < 16; ++n9) {
                    this.E[n9] = false;
                    this.L[n9] = false;
                }
                this.O = 4;
                if (this.f == 0) {
                    for (int n10 = 0; n10 < 16; ++n10) {
                        this.H[n10] += 15;
                    }
                }
                this.n = 0;
                this.T = 8;
                if (this.f > 0 && this.getGraphics != null) {
                    this.getGraphics.loop();
                    break;
                }
                break;
            }
            case 6: {
                for (int n11 = 0; n11 < 8; ++n11) {
                    this.E[n11] = true;
                    this.K[n11] = 8;
                    this.L[n11] = false;
                }
                for (int n12 = 8; n12 < 16; ++n12) {
                    this.E[n12] = false;
                    this.K[n12] = 8;
                    this.L[n12] = false;
                }
                this.G[0] = 60;
                this.H[0] = -120;
                this.G[1] = 102;
                this.H[1] = -102;
                this.G[2] = 120;
                this.H[2] = -60;
                this.G[3] = 102;
                this.H[3] = -18;
                this.G[4] = 60;
                this.H[4] = 0;
                this.G[5] = 18;
                this.H[5] = -18;
                this.G[6] = 0;
                this.H[6] = -60;
                this.G[7] = 18;
                this.H[7] = -102;
                this.n = 0;
                this.T = 8;
                this.drawImage();
                this.R = 0;
                this.o = true;
                if (this.f > 0 && this.getImage != null) {
                    this.getImage.loop();
                    break;
                }
                break;
            }
            case 7: {
                for (int n13 = 0; n13 < 16; ++n13) {
                    this.E[n13] = false;
                    this.L[n13] = false;
                }
                this.p = 700 + this.k * 100;
                this.q = 5 + this.k;
                if (this.q > 16) {
                    this.q = 16;
                }
                this.n = 0;
                this.T = 16;
                if (this.f > 0 && this.getKeyCode != null) {
                    this.getKeyCode.loop();
                    break;
                }
                break;
            }
        }
    }
    
    private void drawImage() {
        this.P[0] = 6;
        this.P[1] = 6;
        this.P[2] = 7;
        this.P[3] = 6;
        this.P[4] = 6;
        this.P[5] = 6;
        this.P[6] = 6;
        this.P[7] = 7;
        this.P[8] = 6;
        this.P[9] = 7;
        this.P[10] = 7;
        this.P[11] = 6;
        this.P[12] = 7;
        this.P[13] = 7;
        this.P[14] = 7;
        this.P[15] = 7;
        this.P[16] = 8;
        this.P[17] = 7;
        this.P[18] = 7;
        this.P[19] = 8;
        this.P[20] = 7;
        this.P[21] = 8;
        this.P[22] = 8;
        this.P[23] = 8;
        this.P[24] = 8;
        this.P[25] = 7;
        this.P[26] = 8;
        this.P[27] = 8;
        this.P[28] = 8;
        this.P[29] = 8;
        this.P[30] = 1;
        this.P[31] = 8;
        this.P[32] = 8;
        this.P[33] = 8;
        this.P[34] = 8;
        this.P[35] = 1;
        this.P[36] = 8;
        this.P[37] = 1;
        this.P[38] = 1;
        this.P[39] = 8;
        this.P[40] = 1;
        this.P[41] = 1;
        this.P[42] = 1;
        this.P[43] = 1;
        this.P[44] = 2;
        this.P[45] = 1;
        this.P[46] = 1;
        this.P[47] = 2;
        this.P[48] = 1;
        this.P[49] = 2;
        this.P[50] = 2;
        this.P[51] = 2;
        this.P[52] = 2;
        this.P[53] = 1;
        this.P[54] = 2;
        this.P[55] = 2;
        this.P[56] = 2;
        this.P[57] = 2;
        this.P[58] = 3;
        this.P[59] = 2;
        this.P[60] = 2;
        this.P[61] = 2;
        this.P[62] = 2;
        this.P[63] = 3;
        this.P[64] = 2;
        this.P[65] = 3;
        this.P[66] = 3;
        this.P[67] = 2;
        this.P[68] = 3;
        this.P[69] = 3;
        this.P[70] = 3;
        this.P[71] = 3;
        this.P[72] = 4;
        this.P[73] = 3;
        this.P[74] = 3;
        this.P[75] = 4;
        this.P[76] = 3;
        this.P[77] = 4;
        this.P[78] = 4;
        this.P[79] = 4;
        this.P[80] = 4;
        this.P[81] = 3;
        this.P[82] = 4;
        this.P[83] = 4;
        this.P[84] = 4;
        this.P[85] = 4;
        this.P[86] = 5;
        this.P[87] = 4;
        this.P[88] = 4;
        this.P[89] = 4;
        this.P[90] = 4;
        this.P[91] = 5;
        this.P[92] = 4;
        this.P[93] = 5;
        this.P[94] = 5;
        this.P[95] = 4;
        this.P[96] = 5;
        this.P[97] = 5;
        this.P[98] = 5;
        this.P[99] = 5;
        this.P[100] = 6;
        this.P[101] = 5;
        this.P[102] = 5;
        this.P[103] = 6;
        this.P[104] = 5;
        this.P[105] = 6;
        this.P[106] = 6;
        this.P[107] = 6;
        this.P[108] = 6;
        this.P[109] = 5;
        this.P[110] = 6;
        this.P[111] = 6;
        this.Q[0] = 0;
        this.Q[1] = 14;
        this.Q[2] = 28;
        this.Q[3] = 42;
        this.Q[4] = 56;
        this.Q[5] = 70;
        this.Q[6] = 84;
        this.Q[7] = 98;
    }
    
    private void drawRect() {
        this.f = 0;
        this.ZI = 1;
        this.CI = 400;
        this.k = 1;
        this.l = 1;
        this.g = 30L;
        this.drawString(true);
        this.downloadImage();
    }
    
    private void drawString(final boolean b) {
        if (b) {
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
            if (this.err != null) {
                this.err.stop();
            }
            if (this.fillRect != null) {
                this.fillRect.stop();
            }
        }
        if (this.getAppletContext != null) {
            this.getAppletContext.stop();
        }
        if (this.getAudioClip != null) {
            this.getAudioClip.stop();
        }
        if (this.getCodeBase != null) {
            this.getCodeBase.stop();
        }
        if (this.getDocumentBase != null) {
            this.getDocumentBase.stop();
        }
        if (this.getGraphics != null) {
            this.getGraphics.stop();
        }
        if (this.getHeight != null) {
            this.getHeight.stop();
        }
        if (this.getImage != null) {
            this.getImage.stop();
        }
        if (this.getKeyCode != null) {
            this.getKeyCode.stop();
        }
    }
    
    private static String err(final int n, final int n2) {
        String s;
        for (s = String.valueOf(n); s.length() < n2; s = "0" + s) {}
        if (s.length() > n2) {
            for (s = "9"; s.length() < n2; s = "9" + s) {}
        }
        return s;
    }
    
    private void fillRect() {
        this.createImage = 14;
        this.paint(this.getGraphics());
        try {
            this.downloadImage = this.getAudioClip(this.getDocumentBase(), "./sound/sound_fire.au");
        }
        catch (Exception ex) {
            this.downloadImage = null;
            System.out.println("Error Getting sound sound_fire.au:" + ex.getMessage());
        }
        this.createImage = 13;
        this.paint(this.getGraphics());
        try {
            this.drawImage = this.getAudioClip(this.getDocumentBase(), "./sound/sound_alien_exp.au");
        }
        catch (Exception ex2) {
            this.drawImage = null;
            System.out.println("Error Getting sound sound_alien_exp.au:" + ex2.getMessage());
        }
        this.createImage = 12;
        this.paint(this.getGraphics());
        try {
            this.drawRect = this.getAudioClip(this.getDocumentBase(), "./sound/sound_ship_exp.au");
        }
        catch (Exception ex3) {
            this.drawRect = null;
            System.out.println("Error Getting sound sound_ship_exp.au:" + ex3.getMessage());
        }
        this.createImage = 11;
        this.paint(this.getGraphics());
        try {
            this.drawString = this.getAudioClip(this.getDocumentBase(), "./sound/sound_warp.au");
        }
        catch (Exception ex4) {
            this.drawString = null;
            System.out.println("Error Getting sound sound_warp.au:" + ex4.getMessage());
        }
        this.createImage = 10;
        this.paint(this.getGraphics());
        try {
            this.err = this.getAudioClip(this.getDocumentBase(), "./sound/sound_warp2.au");
        }
        catch (Exception ex5) {
            this.err = null;
            System.out.println("Error Getting sound sound_warp2.au:" + ex5.getMessage());
        }
        this.createImage = 9;
        this.paint(this.getGraphics());
        try {
            this.fillRect = this.getAudioClip(this.getDocumentBase(), "./sound/sound_warp3.au");
        }
        catch (Exception ex6) {
            this.fillRect = null;
            System.out.println("Error Getting sound sound_warp3.au:" + ex6.getMessage());
        }
        this.createImage = 8;
        this.paint(this.getGraphics());
        try {
            this.getAppletContext = this.getAudioClip(this.getDocumentBase(), "./sound/sound_alien_noise.au");
        }
        catch (Exception ex7) {
            this.getAppletContext = null;
            System.out.println("Error Getting sound sound_alien_noise.au:" + ex7.getMessage());
        }
        this.createImage = 7;
        this.paint(this.getGraphics());
        try {
            this.getAudioClip = this.getAudioClip(this.getDocumentBase(), "./sound/sound_alien_noise2.au");
        }
        catch (Exception ex8) {
            this.getAudioClip = null;
            System.out.println("Error Getting sound sound_alien_noise2.au:" + ex8.getMessage());
        }
        this.createImage = 6;
        this.paint(this.getGraphics());
        try {
            this.getCodeBase = this.getAudioClip(this.getDocumentBase(), "./sound/sound_alien_noise3.au");
        }
        catch (Exception ex9) {
            this.getCodeBase = null;
            System.out.println("Error Getting sound sound_alien_noise3.au:" + ex9.getMessage());
        }
        this.createImage = 5;
        this.paint(this.getGraphics());
        try {
            this.getDocumentBase = this.getAudioClip(this.getDocumentBase(), "./sound/sound_alien_noise4.au");
        }
        catch (Exception ex10) {
            this.getDocumentBase = null;
            System.out.println("Error Getting sound sound_alien_noise4.au:" + ex10.getMessage());
        }
        this.createImage = 4;
        this.paint(this.getGraphics());
        try {
            this.getGraphics = this.getAudioClip(this.getDocumentBase(), "./sound/sound_alien_noise51.au");
        }
        catch (Exception ex11) {
            this.getGraphics = null;
            System.out.println("Error Getting sound sound_alien_noise51.au:" + ex11.getMessage());
        }
        this.createImage = 3;
        this.paint(this.getGraphics());
        try {
            this.getHeight = this.getAudioClip(this.getDocumentBase(), "./sound/sound_alien_noise52.au");
        }
        catch (Exception ex12) {
            this.getHeight = null;
            System.out.println("Error Getting sound sound_alien_noise52.au:" + ex12.getMessage());
        }
        this.createImage = 2;
        this.paint(this.getGraphics());
        try {
            this.getImage = this.getAudioClip(this.getDocumentBase(), "./sound/sound_alien_noise6.au");
        }
        catch (Exception ex13) {
            this.getImage = null;
            System.out.println("Error Getting sound sound_alien_noise6.au:" + ex13.getMessage());
        }
        this.createImage = 1;
        this.paint(this.getGraphics());
        try {
            this.getKeyCode = this.getAudioClip(this.getDocumentBase(), "./sound/sound_alien_noise7.au");
        }
        catch (Exception ex14) {
            this.getKeyCode = null;
            System.out.println("Error Getting sound sound_alien_noise7.au:" + ex14.getMessage());
        }
        this.currentThread = true;
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
    
    private static int getAppletContext(final String s) {
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
}
