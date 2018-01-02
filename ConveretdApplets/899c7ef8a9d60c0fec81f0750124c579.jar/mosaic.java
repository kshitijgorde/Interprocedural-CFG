import java.awt.Event;
import java.awt.FontMetrics;
import java.io.InputStream;
import java.awt.Component;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.MediaTracker;
import java.net.URL;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class mosaic extends Applet implements Runnable, ImageObserver
{
    String[] fontType;
    int[] fontSize;
    int[] colors;
    int[] fontStyles;
    boolean[] border;
    int nFont;
    int nText;
    String[] texts;
    int[] originalxSave;
    int[] originalySave;
    int[] originalx;
    int[] originaly;
    int[] sizex;
    int[] sizey;
    PixelGrabber pg;
    Font credFont;
    Image imageL;
    Image dummyImage;
    Graphics dummyG;
    Graphics credGraphics;
    int[][] textBuffers;
    Dimension dim;
    int faseFade;
    int faseFadeInv;
    int ritorno;
    int incF;
    int maxF;
    int primaVolta;
    int[] animation;
    int nAnim;
    int[] pausa;
    String[] fadingStatus;
    int sonoQui;
    boolean ABORT;
    boolean ABORTtext;
    boolean ABORTanim;
    boolean ZEROfont;
    boolean[] flagZero;
    int[] destBuffer;
    boolean syncFade;
    boolean inizioSync;
    boolean parteI;
    int pausare;
    boolean nonAndare;
    anfy producer;
    MemoryImageSource producer11;
    boolean jvm11flag;
    Toolkit tk;
    int MinSYNC;
    long delaySYNC;
    Thread Tkicker;
    Lware winlink;
    int buggc;
    int memdelay;
    int priority;
    private Image overimg;
    int ovimgX;
    int ovimgY;
    String[] urli;
    String[] statusmsg;
    boolean FotogrCaricati;
    Frame AnFrame;
    int totale;
    int w;
    int h;
    int realw;
    int realh;
    int res;
    int[] destinationBuffer;
    int[] clearbuf;
    private Image image;
    private Image[] images;
    private Graphics graphics;
    String Miotesto;
    String Miotesto1;
    String Miotesto2;
    String Miotesto3;
    String Miotesto4;
    String Miotesto5;
    String Miotesto6;
    String Miotesto7;
    String Miotesto8;
    String Miotesto9;
    String Miotesto10;
    private Image offImage;
    private Graphics offGraphics;
    int backr;
    int backg;
    int backb;
    int backcolor;
    Color backC;
    int nimgs;
    String[] picnames;
    int pause;
    boolean okgif;
    int sizeImgX;
    int sizeImgY;
    int rows;
    int cols;
    int steps;
    boolean AniGIF;
    final String author = "Applet by Fabio Ciucci (www.anf";
    URL linkurl;
    boolean newlink;
    String Miotesto6b;
    int[][] valImgs;
    MediaTracker mTracker;
    boolean displaya;
    int[] sizew;
    int[] sizeh;
    boolean[] imloaded;
    int counte;
    boolean into;
    int oldcounte;
    protected int m_nRows;
    protected int m_nColumns;
    protected int count;
    protected int[] m_giro;
    protected int nGiros;
    protected double[] m_tilePosition;
    protected double[] m_tileAngle;
    protected int[] m_tileMapCoords;
    protected int m_ctexture;
    protected int m_tgm;
    protected double[] m_points;
    protected int[] m_planes;
    protected int[] m_map;
    protected double[] m_matrix;
    protected int[] m_projectPoints;
    protected int[] m_scan;
    protected int[] m_textureU;
    protected int[] m_textureV;
    protected int m_width;
    protected int m_height;
    protected int ymin;
    protected int ymax;
    protected double[] m_normals;
    protected boolean[] m_visiblePlanes;
    protected double[] m_position;
    protected final int m_nPoints = 4;
    protected final int m_nNormals = 2;
    protected final int m_nPolygons = 2;
    protected final int m_nMapCoordsByPlane = 4;
    protected final int m_nPointsByPlane = 4;
    protected double xy;
    protected double xz;
    protected double yz;
    protected int[] m_tilingDir;
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.overimg) {
            if (n == 16) {
                this.okgif = true;
            }
            return true;
        }
        return true;
    }
    
    public void destroy() {
        if (this.overimg != null) {
            this.overimg.flush();
        }
        this.overimg = null;
        if (this.offImage != null) {
            this.offImage.flush();
        }
        this.offImage = null;
        if (this.offGraphics != null) {
            this.offGraphics.dispose();
        }
        this.offGraphics = null;
        System.gc();
    }
    
    public synchronized void prepaniframe() {
        if (this.AniGIF) {
            this.notifyAll();
            while (!this.okgif) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.okgif = false;
        }
        this.offGraphics.drawImage(this.overimg, this.ovimgX, this.ovimgY, this);
    }
    
    public synchronized boolean CheckAniGIF() {
        this.prepareImage(this.overimg, this);
        if (this.jvm11flag) {
            for (int i = 0; i < 3; ++i) {
                this.notifyAll();
                Thread.yield();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            return this.okgif;
        }
        return false;
    }
    
    private final void bye() {
        while (true) {
            this.showStatus("Don't remove www.anfyteam.com credits line in HTML!");
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void init() {
        this.setLayout(null);
        this.addNotify();
        this.tk = this.getToolkit();
        if (this.getParameter("regnewframe").equalsIgnoreCase("YES")) {
            this.newlink = true;
        }
        this.Miotesto6b = this.getParameter("MinSYNC");
        if (this.Miotesto6b == null) {
            this.Miotesto6b = "10";
        }
        this.MinSYNC = Integer.valueOf(this.Miotesto6b);
        this.FotogrCaricati = false;
        Container container;
        for (container = this.getParent(); !(container instanceof Frame); container = ((Frame)container).getParent()) {}
        (this.AnFrame = (Frame)container).setCursor(3);
        final String parameter = this.getParameter("overimg");
        if (parameter != null && !parameter.equalsIgnoreCase("NO")) {
            this.overimg = this.fetchImageAndWait(parameter);
            if (this.overimg != null) {
                String parameter2 = this.getParameter("overimgX");
                if (parameter2 == null) {
                    parameter2 = "0";
                }
                this.ovimgX = Integer.valueOf(parameter2);
                String parameter3 = this.getParameter("overimgY");
                if (parameter3 == null) {
                    parameter3 = "0";
                }
                this.ovimgY = Integer.valueOf(parameter3);
            }
        }
        this.Miotesto1 = this.getParameter("res");
        if (this.Miotesto1 == null) {
            this.Miotesto1 = "1";
        }
        this.Miotesto3 = this.getParameter("pause");
        if (this.Miotesto3 == null) {
            this.Miotesto3 = "1500";
        }
        this.res = Integer.valueOf(this.Miotesto1);
        this.pause = Integer.valueOf(this.Miotesto3);
        this.Miotesto6 = this.getParameter("backr");
        if (this.Miotesto6 == null) {
            this.Miotesto6 = "64";
        }
        this.Miotesto7 = this.getParameter("backg");
        if (this.Miotesto7 == null) {
            this.Miotesto7 = "96";
        }
        this.Miotesto8 = this.getParameter("backb");
        if (this.Miotesto8 == null) {
            this.Miotesto8 = "160";
        }
        this.backr = Integer.valueOf(this.Miotesto6);
        this.backg = Integer.valueOf(this.Miotesto7);
        this.backb = Integer.valueOf(this.Miotesto8);
        this.backcolor = (this.backr << 16 | this.backg << 8 | this.backb);
        this.backC = new Color(this.backr, this.backg, this.backb);
        this.Miotesto9 = this.getParameter("memdelay");
        this.Miotesto10 = this.getParameter("priority");
        this.nimgs = 1;
        while (this.getParameter("image" + String.valueOf(this.nimgs)) != null) {
            ++this.nimgs;
        }
        --this.nimgs;
        if (this.nimgs > 1) {
            this.picnames = new String[this.nimgs];
            this.urli = new String[this.nimgs];
            this.statusmsg = new String[this.nimgs];
            for (int i = 0; i < this.nimgs; ++i) {
                this.picnames[i] = this.getParameter("image" + String.valueOf(i + 1));
            }
            for (int j = 0; j < this.nimgs; ++j) {
                this.urli[j] = this.getParameter("link" + String.valueOf(j + 1));
                this.statusmsg[j] = this.getParameter("statusmsg" + String.valueOf(j + 1));
            }
            this.memdelay = Integer.valueOf(this.Miotesto9);
            this.priority = Integer.valueOf(this.Miotesto10);
            if (this.memdelay < 0) {
                this.memdelay = 0;
            }
            if (this.priority > 10) {
                this.priority = 10;
            }
            else if (this.priority < 1) {
                this.priority = 1;
            }
            if (this.res > 8) {
                this.res = 8;
            }
            else if (this.res < 1) {
                this.res = 1;
            }
            if (this.pause < 1) {
                this.pause = 1;
            }
            this.w = this.size().width / this.res;
            this.h = this.size().height / this.res;
            this.realw = this.w * this.res;
            this.realh = this.h * this.res;
            this.totale = this.w * this.h;
            this.destinationBuffer = new int[this.totale];
            this.clearbuf = new int[this.totale];
            for (int k = 0; k < this.totale; ++k) {
                this.clearbuf[k] = this.backcolor;
            }
            this.Miotesto = null;
            this.Miotesto = this.getParameter("backimage");
            if (!this.Miotesto.equalsIgnoreCase("NO")) {
                final Image fetchImageAndWait = this.fetchImageAndWait(this.Miotesto);
                if (fetchImageAndWait == null) {
                    this.showStatus("Error loading background image");
                }
                else if (fetchImageAndWait.getWidth(this) == this.w && fetchImageAndWait.getHeight(this) == this.h) {
                    final PixelGrabber pixelGrabber = new PixelGrabber(fetchImageAndWait, 0, 0, this.w, this.h, this.clearbuf, 0, this.w);
                    try {
                        pixelGrabber.grabPixels();
                    }
                    catch (InterruptedException ex) {}
                }
            }
            try {
                this.initproducer();
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.initproducer();
            }
            this.offImage = this.createImage(this.realw, this.realh);
            this.offGraphics = this.offImage.getGraphics();
            this.Miotesto = this.getParameter("tileswidth");
            this.cols = ((this.Miotesto == null) ? 8 : Integer.valueOf(this.Miotesto));
            this.Miotesto = this.getParameter("tilesheight");
            this.rows = ((this.Miotesto == null) ? 8 : Integer.valueOf(this.Miotesto));
            this.Miotesto = this.getParameter("tilesteps");
            this.steps = ((this.Miotesto == null) ? 16 : Integer.valueOf(this.Miotesto));
            if (this.cols < 1) {
                this.cols = 1;
            }
            if (this.rows < 1) {
                this.rows = 1;
            }
            if (this.steps < 8) {
                this.steps = 8;
            }
            else if (this.steps > 32) {
                this.steps = 32;
            }
            (this.images = new Image[2])[0] = null;
            this.imloaded = new boolean[this.nimgs];
            this.sizew = new int[this.nimgs];
            this.sizeh = new int[this.nimgs];
            for (int l = 0; l < this.nimgs; ++l) {
                this.imloaded[l] = false;
            }
            this.counte = 1;
            return;
        }
        while (true) {
            this.showStatus("Almost 2 images required!");
        }
    }
    
    void initproducer() {
        this.producer11 = new MemoryImageSource(this.w, this.h, new DirectColorModel(24, 16711680, 65280, 255), this.destinationBuffer, 0, this.w);
        String property;
        try {
            property = System.getProperty("java.version");
        }
        catch (SecurityException ex) {
            property = "unk";
        }
        if (!property.startsWith("1.0")) {
            try {
                this.producer11.setAnimated(true);
                this.producer11.setFullBufferUpdates(true);
                this.image = this.createImage(this.producer11);
                this.producer11.newPixels();
                this.jvm11flag = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.jvm11flag = false;
            }
        }
        if (!this.jvm11flag) {
            this.producer11 = null;
            this.producer = new anfy(this.w, this.h, new DirectColorModel(24, 16711680, 65280, 255), this.destinationBuffer, 0, this.w);
            this.image = this.createImage(this.producer);
        }
    }
    
    Image fetchImageAndWait(final String s) {
        try {
            return this.fetchImageAndWait1(s);
        }
        catch (NoSuchMethodError noSuchMethodError) {
            return this.fetchImageAndWait1(s);
        }
    }
    
    synchronized Image fetchImageAndWait1(final String s) {
        URL url = null;
        Image image = null;
        try {
            url = new URL(this.getDocumentBase(), s);
        }
        catch (MalformedURLException ex) {}
        Label_0170: {
            try {
                try {
                    final InputStream resourceAsStream = this.getClass().getResourceAsStream(url.toString());
                    if (resourceAsStream == null) {
                        break Label_0170;
                    }
                    final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
                    final byte[] array = new byte[512];
                    boolean b = false;
                    byte[] byteArray;
                    try {
                        while (!b) {
                            final int read = resourceAsStream.read(array, 0, 512);
                            if (read != -1) {
                                byteArrayOutputStream.write(array, 0, read);
                                byteArrayOutputStream.flush();
                            }
                            else {
                                b = true;
                            }
                        }
                        byteArray = byteArrayOutputStream.toByteArray();
                        byteArrayOutputStream.close();
                        resourceAsStream.close();
                    }
                    catch (IOException ex2) {
                        byteArray = null;
                    }
                    System.gc();
                    if (byteArray != null) {
                        image = this.getToolkit().createImage(byteArray);
                        this.prepareImage(image, this);
                    }
                }
                catch (NoSuchMethodError noSuchMethodError) {}
            }
            catch (SecurityException ex3) {}
        }
        if (image == null) {
            int i = 0;
            while (i < 5) {
                try {
                    if (i % 2 == 0) {
                        image = Toolkit.getDefaultToolkit().getImage(url);
                    }
                    else {
                        image = this.getImage(url);
                    }
                    ++i;
                    final MediaTracker mediaTracker = new MediaTracker(this);
                    this.notifyAll();
                    Thread.currentThread();
                    Thread.yield();
                    try {
                        mediaTracker.addImage(image, 0);
                        mediaTracker.waitForID(0);
                    }
                    catch (InterruptedException ex4) {
                        image = null;
                    }
                    if (mediaTracker.isErrorID(0)) {
                        image = null;
                    }
                    else {
                        i = 6;
                    }
                }
                catch (NullPointerException ex5) {
                    System.gc();
                }
            }
        }
        if (image == null) {
            for (int j = 0; j < 25; ++j) {
                this.showStatus("Image " + s + " not found!");
                try {
                    Thread.currentThread();
                    Thread.sleep(250L);
                }
                catch (InterruptedException ex6) {}
            }
        }
        else {
            while (image.getWidth(this) < 0) {
                this.notifyAll();
                Thread.currentThread();
                Thread.yield();
                try {
                    Thread.currentThread();
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex7) {}
            }
        }
        return image;
    }
    
    private final synchronized boolean LoadImages() {
        this.mTracker = new MediaTracker(this);
        for (int i = 0; i < 2; ++i) {
            this.showStatus("Image " + String.valueOf(i + 1));
            this.images[i] = this.fetchImageAndWait(this.picnames[i]);
            if (this.images[i] == null) {
                this.showStatus("Error loading image " + String.valueOf(i + 1));
                return false;
            }
            this.imloaded[i] = true;
            this.sizew[i] = this.images[i].getWidth(this);
            this.sizeh[i] = this.images[i].getHeight(this);
            if (i == 0) {
                this.sizeImgX = this.sizew[0];
                this.sizeImgY = this.sizeh[0];
                this.displaya = true;
                this.repaint();
            }
            else if (this.sizew[i] != this.sizew[i - 1] || this.sizeh[i] != this.sizeh[i - 1]) {
                this.showStatus("Error! Images MUST be the same size!");
            }
            if (this.valImgs == null) {
                this.valImgs = new int[this.nimgs][this.sizew[i] * this.sizeh[i]];
            }
            if (!this.mypixelgrab(this.images[i], this.valImgs[i])) {
                return false;
            }
        }
        this.images[1].flush();
        this.images[1] = null;
        System.gc();
        return true;
    }
    
    private final synchronized boolean LoadNext(final int n) {
        new MediaTracker(this);
        final Image fetchImageAndWait = this.fetchImageAndWait(this.picnames[n]);
        if (fetchImageAndWait == null) {
            this.showStatus("Error loading image " + String.valueOf(n + 1));
            return false;
        }
        this.imloaded[n] = true;
        if (!this.mypixelgrab(fetchImageAndWait, this.valImgs[n])) {
            return false;
        }
        fetchImageAndWait.flush();
        System.gc();
        return true;
    }
    
    private boolean mypixelgrab(final Image image, final int[] array) {
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, image.getWidth(this), image.getHeight(this), array, 0, image.getWidth(this));
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        return true;
    }
    
    public void start() {
        if (this.Tkicker == null) {
            (this.Tkicker = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.Tkicker != null && this.Tkicker.isAlive()) {
            this.Tkicker.stop();
        }
        this.Tkicker = null;
    }
    
    public void run() {
        this.Tkicker.setPriority(this.priority);
        this.showStatus("");
        this.delaySYNC = System.currentTimeMillis();
        if (!this.imloaded[0]) {
            this.LoadImages();
        }
        this.showStatus("");
        this.buildMosaicModel(this.w, this.h, this.sizeImgX, this.sizeImgY, this.rows, this.cols);
        try {
            System.arraycopy(this.clearbuf, 0, this.destinationBuffer, 0, this.totale);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            this.stop();
        }
        catch (ArrayStoreException ex2) {
            this.stop();
        }
        this.render();
        try {
            this.producefixed();
        }
        catch (NoSuchMethodError noSuchMethodError) {}
        this.displaya = false;
        this.repaint();
        System.gc();
        final long n = this.pause - (System.currentTimeMillis() - this.delaySYNC);
        if (n > 0L) {
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex3) {}
        }
        final Graphics graphics = this.getGraphics();
        if (this.overimg != null && !this.AniGIF) {
            this.AniGIF = this.CheckAniGIF();
        }
        if (!this.urli[this.counte - 1].equalsIgnoreCase("NO")) {
            this.AnFrame.setCursor(12);
        }
        else {
            this.AnFrame.setCursor(0);
        }
        while (this.Tkicker != null) {
            try {
                System.arraycopy(this.clearbuf, 0, this.destinationBuffer, 0, this.totale);
            }
            catch (ArrayIndexOutOfBoundsException ex4) {
                this.stop();
            }
            catch (ArrayStoreException ex5) {
                this.stop();
            }
            this.render();
            if (this.syncFade) {
                this.ritorno = this.fade(this.sonoQui);
            }
            else {
                this.display();
            }
            if (++this.buggc == this.memdelay) {
                System.gc();
                this.buggc = 0;
            }
            try {
                this.producefixed();
            }
            catch (NoSuchMethodError noSuchMethodError2) {}
            if (this.res == 1) {
                this.offGraphics.drawImage(this.image, 0, 0, this);
            }
            else {
                this.prepscaled();
                this.offGraphics.drawImage(this.image, 0, 0, this.realw, this.realh, this);
            }
            if (this.overimg != null) {
                this.prepaniframe();
            }
            graphics.drawImage(this.offImage, 0, 0, this);
            this.waitsync();
        }
    }
    
    public final void producefixed() {
        try {
            if (this.jvm11flag) {
                this.producer11.newPixels();
                return;
            }
            this.producer.startProduction(this.producer.getConsumer());
        }
        catch (NoSuchMethodError noSuchMethodError) {}
    }
    
    public synchronized void prepscaled() {
        int checkImage = 0;
        this.prepareImage(this.image, this.realw, this.realh, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.image, this.realw, this.realh, this);
        }
    }
    
    public synchronized void prepscaled0() {
        int checkImage = 0;
        this.prepareImage(this.images[0], this.realw, this.realh, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.images[0], this.realw, this.realh, this);
        }
    }
    
    public synchronized void waitsync() {
        Thread.yield();
        this.tk.sync();
        final long n = 10L - (System.currentTimeMillis() - this.delaySYNC);
        if (n > 0L) {
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex) {}
        }
        else {
            try {
                Thread.sleep(1L);
            }
            catch (InterruptedException ex2) {}
        }
        this.delaySYNC = System.currentTimeMillis();
        try {
            Thread.sleep(this.MinSYNC);
        }
        catch (InterruptedException ex3) {}
    }
    
    public final void paint(final Graphics graphics) {
        if (this.FotogrCaricati) {
            if (this.image != null) {
                if (this.res == 1) {
                    this.offGraphics.drawImage(this.image, 0, 0, this);
                }
                else {
                    this.prepscaled();
                    this.offGraphics.drawImage(this.image, 0, 0, this.realw, this.realh, this);
                }
                if (this.overimg != null) {
                    this.prepaniframe();
                }
                graphics.drawImage(this.offImage, 0, 0, this);
            }
        }
        else {
            this.waitdisplay();
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void waitdisplay() {
        final Graphics graphics = this.getGraphics();
        if (this.images != null && this.offGraphics != null && this.displaya && this.images[0] != null) {
            final int n = (this.w - this.sizeImgX) / 2 + 1;
            final int n2 = (this.h - this.sizeImgY) / 2 + 1;
            this.offGraphics.setColor(this.backC);
            this.offGraphics.fillRect(0, 0, this.realw, this.realh);
            if (this.res == 1) {
                this.offGraphics.drawImage(this.images[0], n, n2, this);
            }
            else {
                this.prepscaled0();
                this.offGraphics.drawImage(this.images[0], n, n2, this.realw, this.realh, this);
            }
            if (this.overimg != null) {
                this.offGraphics.drawImage(this.overimg, this.ovimgX, this.ovimgY, this);
            }
            this.offGraphics.setColor(Color.black);
            this.offGraphics.drawString("Loading...", this.realw / 2 - 26 + 1, this.realh / 2 + 2 + 1);
            this.offGraphics.setColor(Color.white);
            this.offGraphics.drawString("Loading...", this.realw / 2 - 26, this.realh / 2 + 2);
            this.TextFade();
            this.destBuffer = this.destinationBuffer;
            if (this.syncFade) {
                this.ritorno = 0;
                this.primaVolta = 1;
                this.faseFade = 0;
                this.faseFadeInv = 256;
                this.incF = 8;
                this.maxF = 256;
                for (int i = 0; i < 256; ++i) {
                    this.ritorno = this.fade(this.sonoQui);
                    if (this.ritorno == 256) {
                        this.fadingStatus[this.sonoQui] = "BLOCKED";
                        this.primaVolta = 0;
                        this.ritorno = 0;
                    }
                }
                final Font font = new Font(this.fontType[0], this.fontStyles[0], this.fontSize[0]);
                this.offGraphics.setFont(font);
                final FontMetrics fontMetrics = this.dummyG.getFontMetrics(font);
                if (this.border[0]) {
                    this.offGraphics.setColor(Color.black);
                    this.offGraphics.drawString(this.texts[0], this.originalx[0] + 3, this.originaly[0] + fontMetrics.getHeight() - fontMetrics.getMaxDescent() + 2);
                }
                this.offGraphics.setColor(new Color(this.colors[0]));
                this.offGraphics.drawString(this.texts[0], this.originalx[0] + 1, this.originaly[0] + fontMetrics.getHeight() - fontMetrics.getMaxDescent());
            }
            graphics.drawImage(this.offImage, 0, 0, this);
        }
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        if (!this.urli[this.counte - 1].equalsIgnoreCase("NO")) {
            this.AnFrame.setCursor(12);
        }
        else {
            this.AnFrame.setCursor(0);
        }
        this.into = true;
        this.showStatus(this.statusmsg[this.counte - 1]);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.into = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.oldcounte != this.counte) {
            this.showStatus(this.statusmsg[this.counte - 1]);
        }
        this.oldcounte = this.counte;
        return true;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        this.linkurl = null;
        if (!this.urli[this.counte - 1].equalsIgnoreCase("NO")) {
            this.showStatus("Going to page " + String.valueOf(this.counte));
            try {
                this.linkurl = new URL(this.getDocumentBase(), this.urli[this.counte - 1]);
            }
            catch (MalformedURLException ex) {
                this.showStatus("Error linking");
                return true;
            }
            if (this.linkurl != null) {
                if (this.newlink) {
                    this.getAppletContext().showDocument(this.linkurl, this.getParameter("regframename"));
                }
                else {
                    this.getAppletContext().showDocument(this.linkurl);
                }
            }
        }
        return true;
    }
    
    void buildMosaicModel(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.nGiros = this.steps;
        this.setup(n, n2, n3, n4, n5, n6);
    }
    
    void giroMatrix() {
        if (this.m_tgm > 7) {
            this.m_tgm = 0;
        }
        for (int i = 0; i < this.m_nRows; ++i) {
            for (int j = 0; j < this.m_nColumns; ++j) {
                final int n = j + i * this.m_nColumns;
                switch (this.m_tgm) {
                    case 7: {
                        final double n2 = j - this.m_nColumns / 2;
                        final double n3 = i - this.m_nRows / 2;
                        this.m_giro[n] = (int)(4.0 * Math.sqrt(n2 * n2 + n3 * n3) + this.nGiros);
                        break;
                    }
                    case 1: {
                        this.m_giro[n] = j + i + this.nGiros;
                        break;
                    }
                    case 2: {
                        this.m_giro[n] = (j + i * this.m_nColumns) / 2 + this.nGiros;
                        break;
                    }
                    case 3: {
                        this.m_giro[n] = (j * this.m_nRows + i) / 2 + this.nGiros;
                        break;
                    }
                    case 4: {
                        this.m_giro[n] = i * 2 / 3 + this.nGiros;
                        break;
                    }
                    case 5: {
                        this.m_giro[n] = j * 2 / 3 + this.nGiros;
                        break;
                    }
                    case 6: {
                        this.m_giro[n] = (int)(Math.random() * 10.0) + this.nGiros;
                        break;
                    }
                    case 0: {
                        this.m_giro[this.m_nRows * this.m_nColumns - n - 1] = i + j + this.nGiros;
                        break;
                    }
                    default: {
                        this.m_giro[j + i * this.m_nColumns] = i + j * this.m_nRows + j + this.nGiros;
                        break;
                    }
                }
            }
        }
        ++this.m_tgm;
    }
    
    private void remapCoords() {
        final double n = this.sizeImgX / this.m_nColumns;
        final double n2 = this.sizeImgY / this.m_nRows;
        for (int i = 0; i < this.m_nRows; ++i) {
            for (int j = 0; j < this.m_nColumns; ++j) {
                final int n3 = j + i * this.m_nColumns;
                this.m_tileMapCoords[n3 * 8] = (int)((this.m_nColumns - 1 - j) * n);
                this.m_tileMapCoords[n3 * 8 + 1] = (int)((this.m_nRows - 1 - i) * n2);
                this.m_tileMapCoords[n3 * 8 + 2] = (int)(n + (this.m_nColumns - 1 - j) * n - 1.0);
                this.m_tileMapCoords[n3 * 8 + 3] = (int)((this.m_nRows - 1 - i) * n2 + n2 - 1.0);
                this.m_tileMapCoords[n3 * 8 + 4] = this.m_tileMapCoords[n3 * 8];
                this.m_tileMapCoords[n3 * 8 + 5] = this.m_tileMapCoords[n3 * 8 + 1];
                this.m_tileMapCoords[n3 * 8 + 6] = this.m_tileMapCoords[n3 * 8 + 2];
                this.m_tileMapCoords[n3 * 8 + 7] = this.m_tileMapCoords[n3 * 8 + 3];
            }
        }
    }
    
    private void setup(final int n, final int n2, final int n3, final int n4, final int nRows, final int nColumns) {
        this.m_giro = new int[nRows * nColumns];
        this.m_nRows = nRows;
        this.m_nColumns = nColumns;
        this.m_tilePosition = new double[nRows * nColumns * 3];
        this.m_tileAngle = new double[nRows * nColumns];
        this.m_tileMapCoords = new int[nRows * nColumns * 8];
        final double n5 = n3 / nColumns;
        final double n6 = n4 / nRows;
        this.cube(n5, n6, n, n2);
        for (int i = 0; i < this.m_nRows; ++i) {
            for (int j = 0; j < this.m_nColumns; ++j) {
                final int n7 = j + i * this.m_nColumns;
                this.m_tilePosition[n7 * 3] = (j - this.m_nColumns / 2.0) * 2.0 * n5 + n5;
                this.m_tilePosition[n7 * 3 + 1] = (this.m_nRows / 2.0 - i) * 2.0 * n6 - n6;
                this.m_tilePosition[n7 * 3 + 2] = -512.0;
                this.m_tileAngle[n7] = 3.141592653589793;
            }
        }
        this.giroMatrix();
        this.remapCoords();
    }
    
    void render() {
        this.count = 0;
        if (this.syncFade && this.inizioSync) {
            if (this.sonoQui != this.nText - 1) {
                ++this.sonoQui;
            }
            else {
                this.sonoQui = 0;
            }
            this.inizioSync = false;
        }
        if (!this.imloaded[this.counte % this.nimgs]) {
            this.LoadNext(this.counte % this.nimgs);
        }
        for (int i = 0; i < this.m_nRows; ++i) {
            for (int j = 0; j < this.m_nColumns; ++j) {
                final int n = j + i * this.m_nColumns;
                this.setPos(this.m_tilePosition[n * 3], this.m_tilePosition[n * 3 + 1], this.m_tilePosition[n * 3 + 2]);
                if (this.m_tilingDir[n] == 0) {
                    this.xz = this.m_tileAngle[n];
                    this.yz = 0.0;
                }
                else if (this.m_tilingDir[n] == 1) {
                    this.yz = this.m_tileAngle[n];
                    this.xz = 0.0;
                }
                else {
                    this.xz = 2.0 * this.m_tileAngle[n];
                    this.yz = this.m_tileAngle[n];
                }
                this.mapFace(this.m_tileMapCoords, n * 8, this.m_tilingDir[n]);
                this.draw();
                if (this.m_giro[n] <= this.nGiros && this.m_giro[n] > 0) {
                    this.m_tileAngle[n] = 3.141592653589793 / this.nGiros * (this.m_giro[n] - 1);
                }
                else if (this.m_giro[n] < -1) {
                    ++this.count;
                }
                final int[] giro = this.m_giro;
                final int n2 = n;
                --giro[n2];
            }
        }
        if (this.count == this.m_nRows * this.m_nColumns) {
            this.inizioSync = true;
            this.setDirs();
            this.giroMatrix();
            for (int k = 0; k < this.m_nRows * this.m_nColumns; ++k) {
                this.m_tileAngle[k] = 3.141592653589793;
            }
            ++this.m_ctexture;
            ++this.counte;
            if (this.counte > this.nimgs) {
                this.counte = 1;
            }
            if (this.into) {
                this.showStatus(this.statusmsg[this.counte - 1]);
            }
            if (!this.urli[this.counte - 1].equalsIgnoreCase("NO")) {
                this.AnFrame.setCursor(12);
            }
            else {
                this.AnFrame.setCursor(0);
            }
            if (this.syncFade) {
                try {
                    Thread.sleep(this.pause);
                    return;
                }
                catch (InterruptedException ex) {
                    return;
                }
            }
            final Graphics graphics = this.getGraphics();
            for (int l = 0; l < this.pause; l += this.MinSYNC * 4) {
                this.runPausa(graphics);
            }
        }
    }
    
    void runPausa(final Graphics graphics) {
        try {
            System.arraycopy(this.clearbuf, 0, this.destinationBuffer, 0, this.totale);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            this.stop();
        }
        catch (ArrayStoreException ex2) {
            this.stop();
        }
        this.render1();
        if (this.syncFade) {
            this.ritorno = this.fade(this.sonoQui);
        }
        else {
            this.display();
        }
        if (++this.buggc == this.memdelay) {
            System.gc();
            this.buggc = 0;
        }
        try {
            this.producefixed();
        }
        catch (NoSuchMethodError noSuchMethodError) {}
        if (this.res == 1) {
            this.offGraphics.drawImage(this.image, 0, 0, this);
        }
        else {
            this.prepscaled();
            this.offGraphics.drawImage(this.image, 0, 0, this.realw, this.realh, this);
        }
        if (this.overimg != null) {
            this.prepaniframe();
        }
        graphics.drawImage(this.offImage, 0, 0, this);
        this.waitsync();
    }
    
    void render1() {
        for (int i = 0; i < this.m_nRows; ++i) {
            for (int j = 0; j < this.m_nColumns; ++j) {
                final int n = j + i * this.m_nColumns;
                this.setPos(this.m_tilePosition[n * 3], this.m_tilePosition[n * 3 + 1], this.m_tilePosition[n * 3 + 2]);
                if (this.m_tilingDir[n] == 0) {
                    this.xz = this.m_tileAngle[n];
                    this.yz = 0.0;
                }
                else if (this.m_tilingDir[n] == 1) {
                    this.yz = this.m_tileAngle[n];
                    this.xz = 0.0;
                }
                else {
                    this.xz = 2.0 * this.m_tileAngle[n];
                    this.yz = this.m_tileAngle[n];
                }
                this.mapFace(this.m_tileMapCoords, n * 8, this.m_tilingDir[n]);
                this.draw();
            }
        }
        this.setDirs();
        this.giroMatrix();
        for (int k = 0; k < this.m_nRows * this.m_nColumns; ++k) {
            this.m_tileAngle[k] = 3.141592653589793;
        }
    }
    
    protected void cube(final double n, final double n2, final int n3, final int n4) {
        this.m_tilingDir = new int[this.m_nRows * this.m_nColumns];
        this.buildVertex(n, n2);
        this.buildPlanes();
        this.setupStuff(n3, n4);
    }
    
    protected void setDirs() {
        final double random = Math.random();
        int n;
        if (random < 0.25) {
            n = 0;
        }
        else if (random < 0.5) {
            n = 1;
        }
        else if (random < 0.75) {
            n = 2;
        }
        else {
            n = 3;
        }
        if (n == 2) {
            for (int i = 0; i < this.m_nRows * this.m_nColumns; ++i) {
                this.m_tilingDir[i] = ((Math.random() <= 0.5) ? 1 : 0);
            }
            return;
        }
        for (int j = 0; j < this.m_nRows * this.m_nColumns; ++j) {
            switch (n) {
                case 0: {
                    this.m_tilingDir[j] = 0;
                    break;
                }
                case 1: {
                    this.m_tilingDir[j] = 1;
                    break;
                }
                case 3: {
                    this.m_tilingDir[j] = 2;
                    break;
                }
            }
        }
    }
    
    protected void setupStuff(final int width, final int height) {
        this.m_map = new int[16];
        this.m_projectPoints = new int[8];
        this.m_visiblePlanes = new boolean[2];
        this.m_matrix = new double[16];
        this.m_scan = new int[height * 2];
        this.m_textureU = new int[height * 2];
        this.m_textureV = new int[height * 2];
        this.m_height = height;
        this.m_width = width;
        this.m_position = new double[6];
        for (int i = 0; i < 6; ++i) {
            this.m_position[i] = 0.0;
        }
    }
    
    protected void buildVertex(final double n, final double n2) {
        (this.m_points = new double[24])[0] = -1.0;
        this.m_points[1] = -1.0;
        this.m_points[2] = 0.0;
        this.m_points[3] = -1.0;
        this.m_points[4] = -1.0;
        this.m_points[5] = 0.0;
        this.m_points[6] = 1.0;
        this.m_points[7] = -1.0;
        this.m_points[8] = 0.0;
        this.m_points[9] = 1.0;
        this.m_points[10] = -1.0;
        this.m_points[11] = 0.0;
        this.m_points[12] = 1.0;
        this.m_points[13] = 1.0;
        this.m_points[14] = 0.0;
        this.m_points[15] = 1.0;
        this.m_points[16] = 1.0;
        this.m_points[17] = 0.0;
        this.m_points[18] = -1.0;
        this.m_points[19] = 1.0;
        this.m_points[20] = 0.0;
        this.m_points[21] = -1.0;
        this.m_points[22] = 1.0;
        this.m_points[23] = 0.0;
        for (int i = 0; i < 4; ++i) {
            final double[] points = this.m_points;
            final int n3 = i * 6;
            points[n3] *= n;
            final double[] points2 = this.m_points;
            final int n4 = i * 6 + 1;
            points2[n4] *= n2;
            final double[] points3 = this.m_points;
            final int n5 = i * 6 + 3;
            points3[n5] *= n;
            final double[] points4 = this.m_points;
            final int n6 = i * 6 + 4;
            points4[n6] *= n2;
        }
    }
    
    protected void buildPlanes() {
        (this.m_planes = new int[8])[0] = 0;
        this.m_planes[1] = 1;
        this.m_planes[2] = 2;
        this.m_planes[3] = 3;
        this.m_planes[4] = 3;
        this.m_planes[5] = 2;
        this.m_planes[6] = 1;
        this.m_planes[7] = 0;
        this.buildNormals();
    }
    
    protected void buildNormals() {
        this.m_normals = new double[12];
        for (int i = 0; i < 2; ++i) {
            final int n = this.m_planes[i * 4 + 2];
            final int n2 = this.m_planes[i * 4 + 1];
            final int n3 = this.m_planes[i * 4];
            final double n4 = this.m_points[n * 6] - this.m_points[n3 * 6];
            final double n5 = this.m_points[n * 6 + 1] - this.m_points[n3 * 6 + 1];
            final double n6 = this.m_points[n * 6 + 2] - this.m_points[n3 * 6 + 2];
            final double n7 = this.m_points[n2 * 6] - this.m_points[n3 * 6];
            final double n8 = this.m_points[n2 * 6 + 1] - this.m_points[n3 * 6 + 1];
            final double n9 = this.m_points[n2 * 6 + 2] - this.m_points[n3 * 6 + 2];
            final double n10 = n5 * n9 - n6 * n8;
            final double n11 = n6 * n7 - n4 * n9;
            final double n12 = n4 * n8 - n7 * n5;
            final double sqrt = Math.sqrt(n10 * n10 + n11 * n11 + n12 * n12);
            final double n13 = n10 / sqrt;
            final double n14 = n11 / sqrt;
            final double n15 = n12 / sqrt;
            this.m_normals[i * 6] = n13;
            this.m_normals[i * 6 + 1] = n14;
            this.m_normals[i * 6 + 2] = n15;
            this.m_normals[i * 6 + 3] = n13;
            this.m_normals[i * 6 + 4] = n14;
            this.m_normals[i * 6 + 5] = n15;
        }
    }
    
    void draw() {
        this.rotate();
        this.project();
        this.drawPlanes();
    }
    
    protected void drawPlanes() {
        this.ymin = Integer.MAX_VALUE;
        this.ymax = Integer.MIN_VALUE;
        for (int i = 0; i < 2; ++i) {
            if (this.m_visiblePlanes[i]) {
                for (int j = 0; j < 4; ++j) {
                    int height;
                    if (this.m_projectPoints[this.m_planes[i * 4 + j] * 2 + 1] <= this.m_projectPoints[this.m_planes[i * 4 + (j + 1) % 4] * 2 + 1]) {
                        height = 0;
                    }
                    else {
                        height = this.m_height;
                    }
                    this.linearInt(this.m_projectPoints[this.m_planes[i * 4 + j] * 2], this.m_projectPoints[this.m_planes[i * 4 + j] * 2 + 1], this.m_projectPoints[this.m_planes[i * 4 + (j + 1) % 4] * 2], this.m_projectPoints[this.m_planes[i * 4 + (j + 1) % 4] * 2 + 1], this.m_scan, height);
                    if (this.m_projectPoints[this.m_planes[i * 4 + j] * 2 + 1] < this.ymin) {
                        this.ymin = this.m_projectPoints[this.m_planes[i * 4 + j] * 2 + 1];
                    }
                    if (this.m_projectPoints[this.m_planes[i * 4 + (j + 1) % 4] * 2 + 1] > this.ymax) {
                        this.ymax = this.m_projectPoints[this.m_planes[i * 4 + (j + 1) % 4] * 2 + 1];
                    }
                    this.linearInt(this.m_map[(i * 4 + j) * 2], this.m_projectPoints[this.m_planes[i * 4 + j] * 2 + 1], this.m_map[(i * 4 + (j + 1) % 4) * 2], this.m_projectPoints[this.m_planes[i * 4 + (j + 1) % 4] * 2 + 1], this.m_textureU, height);
                    this.linearInt(this.m_map[(i * 4 + j) * 2 + 1], this.m_projectPoints[this.m_planes[i * 4 + j] * 2 + 1], this.m_map[(i * 4 + (j + 1) % 4) * 2 + 1], this.m_projectPoints[this.m_planes[i * 4 + (j + 1) % 4] * 2 + 1], this.m_textureV, height);
                }
                if (this.ymin >= this.m_height || this.ymax < 0) {
                    return;
                }
                if (this.ymin < 0) {
                    this.ymin = 0;
                }
                if (this.ymax >= this.m_height) {
                    this.ymax = this.m_height - 1;
                }
                this.render(this.m_ctexture + 1 - i);
            }
        }
    }
    
    void render(final int n) {
        final int[] array = this.valImgs[n % this.nimgs];
        final int[] destinationBuffer = this.destinationBuffer;
        int i = this.ymin;
        final int ymax = this.ymax;
        final int[] textureU = this.m_textureU;
        final int[] textureV = this.m_textureV;
        final int height = this.m_height;
        final int width = this.m_width;
        final int n2 = this.m_width - 1;
        final int sizeImgX = this.sizeImgX;
        while (i <= ymax) {
            final int n3 = i + height;
            int n4 = this.m_scan[i] >> 16;
            int n5 = this.m_scan[n3] >> 16;
            int n6 = textureU[i];
            final int n7 = textureU[n3];
            int n8 = textureV[i];
            final int n9 = textureV[n3];
            final int n10 = n5 - n4;
            int n11;
            int n12;
            if (n10 != 0) {
                n11 = (n7 - n6) / n10;
                n12 = (n9 - n8) / n10;
                if (n4 < 0) {
                    n6 -= n11 * n4;
                    n8 -= n12 * n4;
                    n4 = 0;
                }
            }
            else {
                n11 = 0;
                n12 = 0;
                if (n4 < 0) {
                    n4 = 0;
                }
            }
            if (n5 >= width) {
                n5 = n2;
            }
            for (int j = this.w * i + n4; j <= this.w * i + n5; ++j) {
                destinationBuffer[j] = array[sizeImgX * (n8 >> 16) + (n6 >> 16)];
                n6 += n11;
                n8 += n12;
            }
            ++i;
        }
    }
    
    protected void project() {
        for (int i = 0; i < 4; ++i) {
            this.m_projectPoints[i * 2] = (int)(this.w / 2.0 + this.m_points[i * 6 + 3] * 256.0 / this.m_points[i * 6 + 5]);
            this.m_projectPoints[i * 2 + 1] = (int)(this.h / 2.0 - this.m_points[i * 6 + 4] * 256.0 / this.m_points[i * 6 + 5]);
        }
    }
    
    protected void rotate() {
        final double sin = Math.sin(this.xy);
        final double sin2 = Math.sin(this.xz);
        final double sin3 = Math.sin(this.yz);
        final double cos = Math.cos(this.xy);
        final double cos2 = Math.cos(this.xz);
        final double cos3 = Math.cos(this.yz);
        this.m_matrix[0] = cos2 * cos;
        this.m_matrix[1] = -cos2 * sin;
        this.m_matrix[2] = sin2;
        this.m_matrix[3] = this.m_position[3];
        this.m_matrix[4] = sin3 * sin2 * cos + sin * cos3;
        this.m_matrix[5] = cos3 * cos - sin3 * sin2 * sin;
        this.m_matrix[6] = -sin3 * cos2;
        this.m_matrix[7] = this.m_position[4];
        this.m_matrix[8] = sin3 * sin - cos3 * sin2 * cos;
        this.m_matrix[9] = cos3 * sin2 * sin + sin3 * cos;
        this.m_matrix[10] = cos3 * cos2;
        this.m_matrix[11] = this.m_position[5];
        this.m_matrix[12] = 0.0;
        this.m_matrix[13] = 0.0;
        this.m_matrix[14] = 0.0;
        this.m_matrix[15] = 1.0;
        this.setupCoords();
        this.setupNormals();
    }
    
    void setPos(final double n, final double n2, final double n3) {
        this.m_position[0] = (this.m_position[3] = n);
        this.m_position[1] = (this.m_position[4] = n2);
        this.m_position[2] = (this.m_position[5] = n3);
    }
    
    protected void setupNormals() {
        for (int i = 0; i < 2; ++i) {
            final double n = this.m_normals[i * 6];
            final double n2 = this.m_normals[i * 6 + 1];
            final double n3 = this.m_normals[i * 6 + 2];
            final double[] normals = this.m_normals;
            final int n4 = i * 6 + 3;
            final double n5 = n * this.m_matrix[0] + n2 * this.m_matrix[1] + n3 * this.m_matrix[2];
            normals[n4] = n5;
            final double n6 = n5;
            final double[] normals2 = this.m_normals;
            final int n7 = i * 6 + 4;
            final double n8 = n * this.m_matrix[4] + n2 * this.m_matrix[5] + n3 * this.m_matrix[6];
            normals2[n7] = n8;
            final double n9 = n8;
            final double[] normals3 = this.m_normals;
            final int n10 = i * 6 + 5;
            final double n11 = n * this.m_matrix[8] + n2 * this.m_matrix[9] + n3 * this.m_matrix[10];
            normals3[n10] = n11;
            this.m_visiblePlanes[i] = (n6 * this.m_points[this.m_planes[i * 4] * 6 + 3] + n9 * this.m_points[this.m_planes[i * 4] * 6 + 4] + n11 * this.m_points[this.m_planes[i * 4] * 6 + 5] > 0.0);
        }
    }
    
    protected void setupCoords() {
        for (int i = 0; i < 4; ++i) {
            final double n = this.m_points[i * 6];
            final double n2 = this.m_points[i * 6 + 1];
            final double n3 = this.m_points[i * 6 + 2];
            this.m_points[i * 6 + 3] = n * this.m_matrix[0] + n2 * this.m_matrix[1] + n3 * this.m_matrix[2] + this.m_matrix[3];
            this.m_points[i * 6 + 4] = n * this.m_matrix[4] + n2 * this.m_matrix[5] + n3 * this.m_matrix[6] + this.m_matrix[7];
            this.m_points[i * 6 + 5] = n * this.m_matrix[8] + n2 * this.m_matrix[9] + n3 * this.m_matrix[10] + this.m_matrix[11];
        }
    }
    
    protected void linearInt(int n, int i, int n2, int n3, final int[] array, final int n4) {
        if (i == n3) {
            return;
        }
        if (n == n2) {
            this.vInt(n, i, n3, array, n4);
            return;
        }
        if (i > n3) {
            final int n5 = i;
            i = n3;
            n3 = n5;
            final int n6 = n;
            n = n2;
            n2 = n6;
        }
        final int n7 = (n2 - n << 16) / (n3 - i);
        n <<= 16;
        if (i < 0) {
            n -= n7 * i;
            i = 0;
        }
        if (n3 >= this.m_height) {
            n3 = this.m_height - 1;
        }
        while (i <= n3) {
            array[n4 + i] = n;
            n += n7;
            ++i;
        }
    }
    
    private void vInt(final int n, int i, int n2, final int[] array, final int n3) {
        if (i > n2) {
            final int n4 = i;
            i = n2;
            n2 = n4;
        }
        if (i < 0) {
            i = 0;
        }
        if (n2 >= this.m_height) {
            n2 = this.m_height - 1;
        }
        while (i <= n2) {
            array[n3 + i] = n << 16;
            ++i;
        }
    }
    
    void mapFace(final int[] array, int n, final int n2) {
        this.m_map[0] = array[n + 2];
        this.m_map[1] = array[n + 1];
        this.m_map[2] = array[n];
        this.m_map[3] = array[n + 1];
        this.m_map[4] = array[n];
        this.m_map[5] = array[n + 3];
        this.m_map[6] = array[n + 2];
        this.m_map[7] = array[n + 3];
        n += 4;
        if (n2 == 0) {
            this.m_map[8] = array[n];
            this.m_map[9] = array[n + 3];
            this.m_map[10] = array[n + 2];
            this.m_map[11] = array[n + 3];
            this.m_map[12] = array[n + 2];
            this.m_map[13] = array[n + 1];
            this.m_map[14] = array[n];
            this.m_map[15] = array[n + 1];
            return;
        }
        if (n2 == 1 || n2 == 2) {
            this.m_map[8] = array[n + 2];
            this.m_map[9] = array[n + 1];
            this.m_map[10] = array[n];
            this.m_map[11] = array[n + 1];
            this.m_map[12] = array[n];
            this.m_map[13] = array[n + 3];
            this.m_map[14] = array[n + 2];
            this.m_map[15] = array[n + 3];
        }
    }
    
    void TextFade() {
        this.dim = this.size();
        this.realw = this.dim.width;
        this.realh = this.dim.height;
        this.destBuffer = new int[this.realw * this.realh];
        this.nFont = 1;
        while (this.getParameter("font" + String.valueOf(this.nFont)) != null) {
            ++this.nFont;
            this.ZEROfont = false;
        }
        --this.nFont;
        if (this.ZEROfont) {
            this.nFont = 1;
            (this.fontType = new String[this.nFont])[0] = "Helvetica";
            (this.fontSize = new int[this.nFont])[0] = 24;
            (this.colors = new int[this.nFont])[0] = 16777215;
            (this.fontStyles = new int[this.nFont])[0] = 0;
            (this.border = new boolean[this.nFont])[0] = false;
        }
        else {
            this.fontType = new String[this.nFont];
            this.fontSize = new int[this.nFont];
            this.colors = new int[this.nFont];
            this.fontStyles = new int[this.nFont];
            this.border = new boolean[this.nFont];
            for (int i = 0; i < this.nFont; ++i) {
                this.fontType[i] = this.getParameter("font" + String.valueOf(i + 1));
                if (this.fontType[i] == null) {
                    this.fontType[i] = "Helvetica";
                }
                String parameter = this.getParameter("fontSize" + String.valueOf(i + 1));
                if (parameter == null) {
                    parameter = "24";
                }
                this.fontSize[i] = Integer.parseInt(parameter);
                String parameter2 = this.getParameter("fontColor" + String.valueOf(i + 1));
                if (parameter2 == null) {
                    parameter2 = "ffffff";
                }
                this.colors[i] = Integer.parseInt(parameter2, 16);
                final String parameter3 = this.getParameter("fontStyle" + String.valueOf(i + 1));
                if (parameter3 == null) {
                    this.fontStyles[i] = 0;
                }
                else {
                    final String upperCase = parameter3.toUpperCase();
                    if (upperCase.equals("B")) {
                        this.fontStyles[i] = 1;
                    }
                    else if (upperCase.equals("I")) {
                        this.fontStyles[i] = 2;
                    }
                    else if (upperCase.equals("BI") || upperCase.equals("IB")) {
                        this.fontStyles[i] = 3;
                    }
                    else {
                        this.fontStyles[i] = 0;
                    }
                }
                final String parameter4 = this.getParameter("fontBorder" + String.valueOf(i + 1));
                if (parameter4 == null) {
                    this.border[i] = false;
                }
                else if (parameter4.equals("true")) {
                    this.border[i] = true;
                }
            }
        }
        this.nText = 1;
        while (this.getParameter("text" + String.valueOf(this.nText)) != null) {
            ++this.nText;
            this.ABORTtext = false;
        }
        --this.nText;
        this.texts = new String[this.nText];
        this.originalxSave = new int[this.nText];
        this.originalySave = new int[this.nText];
        this.originalx = new int[this.nText];
        this.originaly = new int[this.nText];
        this.sizex = new int[this.nText];
        this.sizey = new int[this.nText];
        this.textBuffers = new int[this.nText][];
        this.fadingStatus = new String[this.nText];
        for (int j = 0; j < this.nText; ++j) {
            this.fadingStatus[j] = "OUT";
        }
        for (int k = 0; k < this.nText; ++k) {
            this.texts[k] = this.getParameter("text" + String.valueOf(k + 1));
            if (this.texts[k] == null) {
                this.texts[k] = "Error string n." + String.valueOf(k + 1);
            }
            final String parameter5 = this.getParameter("x" + String.valueOf(k + 1));
            if (parameter5 == null) {
                this.originalxSave[k] = (this.originalx[k] = 0);
            }
            else {
                this.originalxSave[k] = (this.originalx[k] = Integer.parseInt(parameter5));
            }
            final String parameter6 = this.getParameter("y" + String.valueOf(k + 1));
            if (parameter6 == null) {
                this.originalySave[k] = (this.originaly[k] = 0);
            }
            else {
                this.originalySave[k] = (this.originaly[k] = Integer.parseInt(parameter6));
            }
        }
        String s = this.fontType[0];
        int n = this.fontStyles[0];
        int int1 = this.fontSize[0];
        int n2 = this.colors[0] >> 16 & 0xFF;
        int n3 = this.colors[0] >> 8 & 0xFF;
        int n4 = this.colors[0] & 0xFF;
        boolean b = this.border[0];
        for (int l = 0; l < this.nText; ++l) {
            this.dummyImage = this.createImage(this.realw, this.realh);
            this.dummyG = this.dummyImage.getGraphics();
            if (this.getParameter("f" + String.valueOf(l + 1)) != null) {
                int n5 = -1;
                final String parameter7 = this.getParameter("f" + String.valueOf(l + 1));
                for (int n6 = 0; n6 < this.nFont; ++n6) {
                    if (parameter7.equals("font" + String.valueOf(n6 + 1))) {
                        n5 = n6;
                    }
                }
                if (n5 >= 0) {
                    s = this.fontType[n5];
                    int1 = this.fontSize[n5];
                    n2 = (this.colors[n5] >> 16 & 0xFF);
                    n3 = (this.colors[n5] >> 8 & 0xFF);
                    n4 = (this.colors[n5] & 0xFF);
                    n = this.fontStyles[n5];
                    b = this.border[n5];
                }
            }
            if (this.getParameter("sz" + String.valueOf(l + 1)) != null) {
                int1 = Integer.parseInt(this.getParameter("sz" + String.valueOf(l + 1)));
            }
            if (this.getParameter("st" + String.valueOf(l + 1)) != null) {
                final String upperCase2 = this.getParameter("st" + String.valueOf(l + 1)).toUpperCase();
                if (upperCase2.equals("B")) {
                    n = 1;
                }
                else if (upperCase2.equals("I")) {
                    n = 2;
                }
                else if (upperCase2.equals("BI") || upperCase2.equals("IB")) {
                    n = 3;
                }
                else {
                    n = 0;
                }
            }
            this.credFont = new Font(s, n, int1);
            final FontMetrics fontMetrics = this.dummyG.getFontMetrics(this.credFont);
            final int n7 = fontMetrics.stringWidth(this.texts[l]) + 6;
            final int n8 = fontMetrics.getHeight() + 6;
            this.imageL = this.createImage(n7, n8);
            (this.credGraphics = this.imageL.getGraphics()).setFont(this.credFont);
            this.credGraphics.setColor(Color.black);
            this.credGraphics.fillRect(0, 0, n7, n8);
            if (this.getParameter("c" + String.valueOf(l + 1)) != null) {
                final int int2 = Integer.parseInt(this.getParameter("c" + String.valueOf(l + 1)), 16);
                n2 = (int2 >> 16 & 0xFF);
                n3 = (int2 >> 8 & 0xFF);
                n4 = (int2 & 0xFF);
            }
            if (this.getParameter("b" + String.valueOf(l + 1)) != null) {
                final String upperCase3 = this.getParameter("b" + String.valueOf(l + 1)).toUpperCase();
                if (upperCase3.equals("TRUE")) {
                    b = true;
                }
                else if (upperCase3.equals("FALSE")) {
                    b = false;
                }
            }
            if (b) {
                this.credGraphics.setColor(new Color(0, 0, 15));
                this.credGraphics.drawString(this.texts[l], 2, fontMetrics.getHeight() - fontMetrics.getMaxDescent() + 2);
            }
            this.credGraphics.setColor(new Color(n2, n3, n4));
            this.credGraphics.drawString(this.texts[l], 0, fontMetrics.getHeight() - fontMetrics.getMaxDescent());
            this.textBuffers[l] = new int[n7 * n8];
            this.pg = new PixelGrabber(this.imageL, 0, 0, n7, n8, this.textBuffers[l], 0, n7);
            try {
                if (!this.pg.grabPixels()) {
                    this.showStatus("Errore grabPixels()!");
                }
            }
            catch (InterruptedException ex) {
                this.showStatus("grabPixels interrotto!");
            }
            this.imageL.flush();
            this.imageL = null;
            this.dummyImage.flush();
            this.dummyImage = null;
            this.sizex[l] = n7;
            this.sizey[l] = n8;
            s = this.fontType[0];
            int1 = this.fontSize[0];
            n = this.fontStyles[0];
            n2 = (this.colors[0] >> 16 & 0xFF);
            n3 = (this.colors[0] >> 8 & 0xFF);
            n4 = (this.colors[0] & 0xFF);
            b = this.border[0];
        }
        this.nAnim = 1;
        while (this.getParameter("parts" + String.valueOf(this.nAnim)) != null) {
            ++this.nAnim;
            this.ABORTanim = false;
        }
        --this.nAnim;
        this.animation = new int[this.nAnim];
        this.pausa = new int[this.nAnim];
        for (int n9 = 0; n9 < this.nAnim; ++n9) {
            this.pausa[n9] = 0;
        }
        this.flagZero = new boolean[this.nAnim];
        for (int n10 = 0; n10 < this.nAnim; ++n10) {
            this.flagZero[n10] = false;
        }
        for (int n11 = 0; n11 < this.nAnim; ++n11) {
            final String parameter8 = this.getParameter("parts" + String.valueOf(n11 + 1));
            if (parameter8 == null) {
                this.animation[n11] = 0;
            }
            else if (parameter8.startsWith("pausing") || parameter8.startsWith("PAUSING")) {
                this.pausa[n11] = Integer.parseInt(parameter8.substring(8));
            }
            else {
                this.animation[n11] = Integer.parseInt(parameter8);
                if (this.animation[n11] > 0) {
                    final int[] animation = this.animation;
                    final int n12 = n11;
                    --animation[n12];
                }
                if (this.animation[n11] < 0) {
                    final int[] animation2 = this.animation;
                    final int n13 = n11;
                    ++animation2[n13];
                    if (this.animation[n11] == 0) {
                        this.flagZero[n11] = true;
                    }
                }
            }
        }
        final String parameter9 = this.getParameter("syncMode");
        if (parameter9 == null) {
            this.syncFade = false;
        }
        else if (parameter9.equals("true")) {
            this.syncFade = true;
        }
        if (!this.syncFade && (this.ABORTtext || this.ABORTanim)) {
            this.ABORT = true;
        }
    }
    
    void display() {
        if (this.ABORT) {
            return;
        }
        if (this.pausa[this.sonoQui] != 0) {
            if (this.primaVolta == 0) {
                this.primaVolta = 1;
                this.faseFade = 0;
            }
            if (this.faseFade != this.pausa[this.sonoQui]) {
                ++this.faseFade;
            }
            else {
                if (this.sonoQui != this.nAnim - 1) {
                    ++this.sonoQui;
                }
                else {
                    this.sonoQui = 0;
                }
                this.primaVolta = 0;
            }
        }
        else if (this.animation[this.sonoQui] < 0 || (this.animation[this.sonoQui] == 0 && this.flagZero[this.sonoQui])) {
            final int n = this.animation[this.sonoQui] - this.animation[this.sonoQui] * 2;
            if (this.primaVolta == 0) {
                this.primaVolta = 1;
                this.faseFade = 0;
                this.faseFadeInv = 256;
                this.incF = 8;
                this.maxF = 256;
            }
            final int[] originalx = this.originalx;
            final int n2 = n;
            ++originalx[n2];
            if (this.primaVolta == 1) {
                this.ritorno = this.fade(n);
                if (this.ritorno == 256) {
                    this.primaVolta = 2;
                    this.ritorno = 0;
                }
            }
            if (this.primaVolta == 2) {
                this.primaVolta = 3;
                this.faseFade = 256;
                this.faseFadeInv = 0;
                this.incF = -8;
                this.maxF = 0;
            }
            if (this.primaVolta == 3) {
                this.ritorno = this.fade(n);
                if (this.ritorno == 0) {
                    if (this.sonoQui != this.nAnim - 1) {
                        ++this.sonoQui;
                    }
                    else {
                        this.sonoQui = 0;
                    }
                    this.primaVolta = 0;
                    this.ritorno = 256;
                    this.originalx[n] = this.originalxSave[n];
                }
            }
        }
        else if (this.fadingStatus[this.animation[this.sonoQui]].equals("OUT")) {
            if (this.primaVolta == 0) {
                this.primaVolta = 1;
                this.faseFade = 0;
                this.faseFadeInv = 256;
                this.incF = 8;
                this.maxF = 256;
            }
            this.ritorno = this.fade(this.animation[this.sonoQui]);
            if (this.ritorno == 256) {
                this.fadingStatus[this.animation[this.sonoQui]] = "BLOCKED";
                if (this.sonoQui != this.nAnim - 1) {
                    ++this.sonoQui;
                }
                else {
                    this.sonoQui = 0;
                }
                this.primaVolta = 0;
                this.ritorno = 0;
            }
        }
        else if (this.fadingStatus[this.animation[this.sonoQui]].equals("BLOCKED") || this.fadingStatus[this.animation[this.sonoQui]].equals("IN")) {
            if (this.primaVolta == 0) {
                this.fadingStatus[this.animation[this.sonoQui]] = "IN";
                this.primaVolta = 1;
                this.faseFade = 256;
                this.faseFadeInv = 0;
                this.incF = -8;
                this.maxF = 0;
            }
            this.ritorno = this.fade(this.animation[this.sonoQui]);
            if (this.ritorno == 0) {
                this.fadingStatus[this.animation[this.sonoQui]] = "OUT";
                if (this.sonoQui != this.nAnim - 1) {
                    ++this.sonoQui;
                }
                else {
                    this.sonoQui = 0;
                }
                this.primaVolta = 0;
                this.ritorno = 256;
            }
        }
        for (int i = 0; i < this.nText; ++i) {
            this.lockedOnScreen(i);
        }
    }
    
    private int fade(final int n) {
        int n2 = 0;
        int n3 = this.originalx[n] + this.originaly[n] * this.realw;
        int n4 = this.originalx[n];
        int n5 = this.originaly[n];
        int n6 = 0;
        for (int i = 0; i < this.sizey[n] * this.sizex[n]; ++i) {
            if (n2 != this.sizex[n] - 1) {
                ++n2;
                ++n3;
            }
            else {
                n2 = 0;
                n3 += this.realw - this.sizex[n] + 1;
                n4 = this.originalx[n];
                ++n5;
            }
            if (n4 >= this.realw || n4 < 0 || n5 >= this.realh || n5 < 0) {
                n6 = 1;
            }
            ++n4;
            if (this.textBuffers[n][i] != -16777216 && n6 == 0) {
                this.destBuffer[n3] = (((this.textBuffers[n][i] >> 16 & 0xFF) * this.faseFade >> 8) + ((this.destBuffer[n3] >> 16 & 0xFF) * this.faseFadeInv >> 8) << 16 | ((this.textBuffers[n][i] >> 8 & 0xFF) * this.faseFade >> 8) + ((this.destBuffer[n3] >> 8 & 0xFF) * this.faseFadeInv >> 8) << 8 | ((this.textBuffers[n][i] & 0xFF) * this.faseFade >> 8) + ((this.destBuffer[n3] & 0xFF) * this.faseFadeInv >> 8));
            }
            n6 = 0;
        }
        if (this.faseFade != this.maxF) {
            this.faseFade += this.incF;
            this.faseFadeInv -= this.incF;
        }
        return this.faseFade;
    }
    
    private void lockedOnScreen(final int n) {
        if (this.fadingStatus[n].equals("BLOCKED")) {
            int n2 = 0;
            int n3 = this.originalx[n] + this.originaly[n] * this.realw;
            int n4 = this.originalx[n];
            int n5 = this.originaly[n];
            int n6 = 0;
            for (int i = 0; i < this.sizey[n] * this.sizex[n]; ++i) {
                if (n2 != this.sizex[n] - 1) {
                    ++n2;
                    ++n3;
                }
                else {
                    n2 = 0;
                    n3 += this.realw - this.sizex[n] + 1;
                    n4 = this.originalx[n];
                    ++n5;
                }
                if (n4 >= this.realw || n4 < 0 || n5 >= this.realh || n5 < 0) {
                    n6 = 1;
                }
                ++n4;
                if (this.textBuffers[n][i] != -16777216 && n6 == 0) {
                    this.destBuffer[n3] = this.textBuffers[n][i];
                }
                n6 = 0;
            }
        }
    }
    
    public mosaic() {
        this.ABORT = false;
        this.ABORTtext = true;
        this.ABORTanim = true;
        this.ZEROfont = true;
        this.syncFade = false;
        this.inizioSync = true;
        this.parteI = true;
        this.nonAndare = true;
        this.jvm11flag = false;
        this.FotogrCaricati = false;
        this.res = 1;
        this.okgif = false;
        this.AniGIF = false;
        this.newlink = false;
        this.displaya = false;
        this.counte = 1;
        this.into = false;
        this.nGiros = 16;
    }
}
