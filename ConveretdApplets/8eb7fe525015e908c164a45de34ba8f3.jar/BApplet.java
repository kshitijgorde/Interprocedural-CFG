import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ComponentListener;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.Frame;
import java.awt.Cursor;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.image.ColorModel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.io.PrintStream;
import java.util.Calendar;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class BApplet extends Applet implements BConstants, Runnable, MouseListener, MouseMotionListener, KeyListener
{
    static final int DEFAULT_WIDTH = 100;
    static final int DEFAULT_HEIGHT = 100;
    static final String LEECH_WAKEUP = "Error while running applet.";
    static final String EXTERNAL_FLAG = "--external=";
    static final String EXTERNAL_QUIT = "__QUIT__";
    static final String EXTERNAL_MOVE = "__MOVE__";
    static final int DEFAULT_LENGTH = 512;
    static final int PERLIN_YWRAPB = 4;
    static final int PERLIN_YWRAP = 16;
    static final int PERLIN_ZWRAPB = 8;
    static final int PERLIN_ZWRAP = 256;
    static final int PERLIN_SIZE = 4095;
    private static NumberFormat float_nf;
    private static int float_nf_left;
    private static int float_nf_right;
    private static NumberFormat int_nf;
    private static int int_nf_digits;
    public BGraphics g;
    public int[] pixels;
    public int mouseX;
    public int mouseY;
    public int pmouseX;
    public int pmouseY;
    boolean firstMouseEvent;
    public boolean mousePressed;
    public MouseEvent mouseEvent;
    public int key;
    public boolean keyPressed;
    public KeyEvent keyEvent;
    long millisOffset;
    Calendar calendar;
    protected float fps;
    protected long fpsLastMillis;
    protected long fpsLastDelayTime;
    protected float fpsTarget;
    boolean drawMethod;
    boolean loopMethod;
    boolean insideLoop;
    int qmouseX;
    int qmouseY;
    boolean qmouseDragged;
    boolean qmouseMoved;
    boolean firstFrame;
    public int frame;
    boolean finished;
    boolean drawn;
    Thread thread;
    Exception exception;
    int width;
    int height;
    PrintStream leechErr;
    boolean externalRuntime;
    boolean isMacintosh;
    BServer server;
    BClient client;
    String net;
    int net_mode;
    BSonic sonic;
    public int[] samples;
    public int length;
    public int frequency;
    public boolean sonicInit;
    int cursor_type;
    boolean cursor_visible;
    BImage invisible_cursor;
    int perlin_octaves;
    float perlin_amp_falloff;
    int perlin_TWOPI;
    int perlin_PI;
    float[] perlin_cosTable;
    float[] perlin;
    public Stopper stopper;
    
    public void init() {
        this.initg();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        this.millisOffset = System.currentTimeMillis();
        this.finished = false;
        this.drawn = false;
        this.firstFrame = true;
        this.drawMethod = true;
        this.loopMethod = true;
        this.firstMouseEvent = true;
        this.setup();
        this.pixels = this.g.pixels;
        this.width = this.g.width;
        this.height = this.g.height;
        this.g.applet = this;
        boolean isMacintosh = false;
        if (System.getProperty("os.name").toLowerCase().indexOf("mac") != -1) {
            isMacintosh = true;
        }
        this.isMacintosh = isMacintosh;
        if (!this.sonicInit) {
            this.length = 0;
        }
        else if (this.sonic == null) {
            this.beginSound();
        }
    }
    
    public void initg() {
        this.g = new BGraphics(100, 100);
    }
    
    public void start() {
        (this.thread = new Thread(this)).start();
        if (this.sonic != null) {
            this.sonic.start(this);
        }
    }
    
    public void stop() {
        if (this.thread != null) {
            this.thread = null;
        }
        this.endNet();
        this.endSound();
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    void setup() {
    }
    
    void draw() {
        this.drawMethod = false;
    }
    
    void loop() {
        this.loopMethod = false;
    }
    
    public void size(final int n, final int n2) {
        this.g.resize(n, n2);
        this.pixels = this.g.pixels;
        this.width = this.g.width;
        this.height = this.g.height;
        this.g.applet = this;
    }
    
    public void update() {
        final Graphics graphics = this.getGraphics();
        if (graphics != null) {
            this.paint(graphics);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.g.mis.newPixels(this.pixels, this.g.cm, 0, this.width);
        if (this.g != null) {
            graphics.drawImage(this.g.image, 0, 0, null);
        }
    }
    
    public void run() {
        try {
            while (Thread.currentThread() == this.thread && !this.finished) {
                this.nextFrame();
                if (this.firstFrame) {
                    this.firstFrame = false;
                }
                try {
                    final Thread thread = this.thread;
                    Thread.sleep(5);
                }
                catch (InterruptedException ex2) {}
            }
        }
        catch (Exception ex) {
            this.finished = true;
            if (this.leechErr != null) {
                this.leechErr.println("Error while running applet.");
                ex.printStackTrace(this.leechErr);
            }
            else {
                System.err.println("Error while running applet.");
                ex.printStackTrace();
            }
        }
    }
    
    public void nextFrame() {
        this.mouseX = this.qmouseX;
        this.mouseY = this.qmouseY;
        this.calendar = null;
        if (this.fpsTarget != 0.0f) {
            this.framerate_delay();
        }
        if (!this.drawn) {
            this.g.beginFrame();
            this.draw();
            if (this.drawMethod) {
                this.g.endFrame();
                this.update();
                if (this.isMacintosh) {
                    this.getToolkit().sync();
                }
                this.finished = true;
            }
            this.drawn = true;
        }
        if (!this.drawMethod) {
            this.g.beginFrame();
            this.insideLoop = true;
            this.loop();
            this.insideLoop = false;
            if (this.qmouseMoved) {
                this.mouseMoved();
                this.qmouseMoved = false;
            }
            if (this.qmouseDragged) {
                this.mouseDragged();
                this.qmouseDragged = false;
            }
            this.g.endFrame();
            this.update();
            if (this.isMacintosh) {
                this.getToolkit().sync();
            }
        }
        if (!this.loopMethod) {
            this.finished = true;
        }
        this.pmouseX = this.mouseX;
        this.pmouseY = this.mouseY;
        ++this.frame;
    }
    
    void mouseClicked() {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.mouseClicked();
    }
    
    void mousePressed() {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.mouseEvent = mouseEvent;
        this.mousePressed = true;
        this.mousePressed();
    }
    
    void mouseReleased() {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.mouseEvent = mouseEvent;
        this.mousePressed = false;
        this.mouseReleased();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    void mouseDragged() {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.mouseEvent = mouseEvent;
        this.qmouseX = mouseEvent.getX();
        this.qmouseY = mouseEvent.getY();
        if (this.firstMouseEvent) {
            this.pmouseX = this.qmouseX;
            this.pmouseY = this.qmouseY;
            this.firstMouseEvent = false;
        }
        this.mousePressed = true;
        this.qmouseDragged = true;
    }
    
    void mouseMoved() {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.mouseEvent = mouseEvent;
        this.qmouseX = mouseEvent.getX();
        this.qmouseY = mouseEvent.getY();
        if (this.firstMouseEvent) {
            this.pmouseX = this.qmouseX;
            this.pmouseY = this.qmouseY;
            this.firstMouseEvent = false;
        }
        this.mousePressed = false;
        this.qmouseMoved = true;
    }
    
    void keyTyped() {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        this.key = keyEvent.getKeyChar();
        if (this.key == (char)(-1)) {
            this.key = keyEvent.getKeyCode();
        }
        this.keyTyped();
    }
    
    void keyPressed() {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        this.keyPressed = true;
        this.key = keyEvent.getKeyChar();
        if (this.key == (char)(-1)) {
            this.key = keyEvent.getKeyCode();
        }
        this.keyPressed();
    }
    
    void keyReleased() {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        this.keyPressed = false;
        this.key = keyEvent.getKeyChar();
        if (this.key == (char)(-1)) {
            this.key = keyEvent.getKeyCode();
        }
        this.keyReleased();
    }
    
    public int millis() {
        return (int)(System.currentTimeMillis() - this.millisOffset);
    }
    
    public int second() {
        if (this.calendar == null) {
            this.calendar = Calendar.getInstance();
        }
        return this.calendar.get(13);
    }
    
    public int minute() {
        if (this.calendar == null) {
            this.calendar = Calendar.getInstance();
        }
        return this.calendar.get(12);
    }
    
    public int hour() {
        if (this.calendar == null) {
            this.calendar = Calendar.getInstance();
        }
        return this.calendar.get(11);
    }
    
    public int day() {
        if (this.calendar == null) {
            this.calendar = Calendar.getInstance();
        }
        return this.calendar.get(5);
    }
    
    public int month() {
        if (this.calendar == null) {
            this.calendar = Calendar.getInstance();
        }
        return this.calendar.get(2) + 1;
    }
    
    public int year() {
        if (this.calendar == null) {
            this.calendar = Calendar.getInstance();
        }
        return this.calendar.get(1);
    }
    
    public void delay(final int n) {
        if (this.firstFrame) {
            return;
        }
        if (n > 0) {
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public float framerate() {
        if (this.fpsLastMillis != 0L) {
            final float n = System.currentTimeMillis() - this.fpsLastMillis;
            if (n != 0.0f) {
                this.fps = this.fps * 0.9f + 1.0f / (n / 1000.0f) * 0.1f;
            }
        }
        this.fpsLastMillis = System.currentTimeMillis();
        return this.fps;
    }
    
    public void framerate(final float fpsTarget) {
        this.fpsTarget = fpsTarget;
    }
    
    protected void framerate_delay() {
        if (this.fpsLastDelayTime == 0L) {
            this.fpsLastDelayTime = System.currentTimeMillis();
            return;
        }
        final long fpsLastDelayTime = this.fpsLastDelayTime + (long)(1000.0f / this.fpsTarget);
        final int n = (int)(fpsLastDelayTime - System.currentTimeMillis());
        this.fpsLastDelayTime = fpsLastDelayTime;
        this.delay(n);
    }
    
    public boolean online() {
        try {
            this.getAppletContext();
        }
        catch (NullPointerException ex) {
            return false;
        }
        return true;
    }
    
    public String param(final String s) {
        if (this.online()) {
            return this.getParameter(s);
        }
        System.err.println("param() only works inside a web browser");
        return null;
    }
    
    public void status(final String s) {
        if (this.online()) {
            this.showStatus(s);
        }
        else {
            System.out.println(s);
        }
    }
    
    void link(final String s) {
        if (!this.online()) {
            System.err.println("Can't open " + s);
            System.err.println("link() only works inside a web browser");
            return;
        }
        try {
            this.getAppletContext().showDocument(new URL(s));
        }
        catch (Exception ex) {
            System.err.println("Could not open " + s);
            ex.printStackTrace();
        }
    }
    
    void link(final String s, final String s2) {
        if (!this.online()) {
            System.err.println("Can't open " + s);
            System.err.println("link() only works inside a web browser");
            return;
        }
        try {
            this.getAppletContext().showDocument(new URL(s), s2);
        }
        catch (Exception ex) {
            System.err.println("Could not open " + s);
            ex.printStackTrace();
        }
    }
    
    public void beginNet(final String s, final int n) {
        this.net_mode = 0;
        (this.client = new BClient(this, s, n)).start();
    }
    
    public void beginNet(final int n) {
        this.net_mode = 1;
        (this.server = new BServer(this, n)).start();
    }
    
    void netEvent() {
    }
    
    void netEvent(final int n) {
    }
    
    public void netWrite(final String s) {
        if (this.net_mode == 0) {
            this.client.writeData(s);
        }
        else if (this.net_mode == 1) {
            this.server.broadcast(s);
        }
    }
    
    public void endNet() {
        if (this.net_mode == 0) {
            if (this.client != null) {
                this.client.destroy();
            }
        }
        else if (this.net_mode == 1 && this.server != null) {
            this.server.destroy();
        }
    }
    
    public void beginSound() {
        if (this.sonicInit) {
            return;
        }
        this.sonicInit = true;
        if (this.length == -1) {
            this.length = 0;
        }
        this.sonic = new BSonic();
    }
    
    public void beginSound(int length) {
        if (this.sonicInit) {
            return;
        }
        this.sonicInit = true;
        if (length <= 0) {
            length = 512;
        }
        this.length = length;
        this.frequency = 8000;
        this.samples = new int[this.length];
        this.sonic = new BSonic(this.samples);
    }
    
    public void endSound() {
        if (this.sonic != null) {
            this.sonic.stop();
        }
    }
    
    public BSound loadSound(final String s) {
        if (this.sonic == null || !this.sonicInit) {
            this.beginSound();
        }
        BSound loadSound = this.sonic.loadSound(s);
        if (loadSound == null) {
            loadSound = new BSound(1);
        }
        return loadSound;
    }
    
    public BSound generate(final int n, final int n2) {
        if (this.sonic == null) {
            this.beginSound();
        }
        return this.sonic.generate(n, n2);
    }
    
    public BSound microphone() {
        if (this.sonic == null) {
            this.beginSound();
        }
        return this.sonic.microphone();
    }
    
    void soundEvent() {
    }
    
    public void volume(final float n) {
        BSonic.volume(n);
    }
    
    public void length(final int length) {
        if (this.sonic != null) {
            return;
        }
        this.length = length;
    }
    
    void frequency(final int n) {
        if (this.sonic != null) {
            return;
        }
    }
    
    public void volume(final BSound bSound, final float n) {
        bSound.volume(n);
    }
    
    public void speed(final BSound bSound, final float n) {
        bSound.speed(n);
    }
    
    public void jump(final BSound bSound, final int n) {
        bSound.jump(n);
    }
    
    public void play(final BSound bSound) {
        if (this.sonic == null) {
            this.beginSound();
        }
        this.sonic.play(bSound);
    }
    
    public void repeat(final BSound bSound) {
        if (this.sonic == null) {
            this.beginSound();
        }
        this.sonic.repeat(bSound);
    }
    
    public void play(final BSound bSound, final int n, final int n2) {
        if (this.sonic == null) {
            this.beginSound();
        }
        this.sonic.play(bSound, n, n2);
    }
    
    public void repeat(final BSound bSound, final int n, final int n2) {
        if (this.sonic == null) {
            this.beginSound();
        }
        this.sonic.repeat(bSound, n, n2);
    }
    
    public void pause(final BSound bSound) {
        if (this.sonic == null) {
            this.beginSound();
        }
        this.sonic.pause(bSound);
    }
    
    public void stop(final BSound bSound) {
        if (!this.sonicInit) {
            this.beginSound();
        }
        this.sonic.stop(bSound);
    }
    
    public void saveFrame() {
        if (this.online()) {
            System.err.println("Can't use saveFrame() when running in a browser.");
            return;
        }
        this.save("screen-" + nf(this.frame, 4) + ".tif");
    }
    
    public void saveFrame(final String s) {
        if (this.online()) {
            System.err.println("Can't use saveFrame() when running in a browser.");
            return;
        }
        final int index = s.indexOf(35);
        final int lastIndex = s.lastIndexOf(35);
        if (index == -1 || lastIndex == -1) {
            this.save(s);
        }
        else {
            this.save(s.substring(0, index) + nf(this.frame, lastIndex - index + 1) + s.substring(lastIndex + 1));
        }
    }
    
    void cursor(final int cursor_type) {
        this.setCursor(Cursor.getPredefinedCursor(cursor_type));
        this.cursor_visible = true;
        this.cursor_type = cursor_type;
    }
    
    void cursor(final BImage bImage, final int n, final int n2) {
        if (bImage == this.invisible_cursor) {
            System.err.println("Can't set the cursor invisible in an exported applet");
        }
        else {
            System.err.println("Can't set the cursor to an image in an exported applet");
        }
    }
    
    void cursor() {
        if (!this.cursor_visible) {
            this.cursor_visible = true;
            this.setCursor(Cursor.getPredefinedCursor(this.cursor_type));
        }
    }
    
    void noCursor() {
        if (!this.cursor_visible) {
            return;
        }
        if (this.invisible_cursor == null) {
            this.invisible_cursor = new BImage(new int[1024], 32, 32, 2);
        }
        this.cursor(this.invisible_cursor, 16, 16);
        this.cursor_visible = false;
    }
    
    public void print(final boolean b) {
        System.out.print(b);
        System.out.flush();
    }
    
    public void print(final char c) {
        System.out.print(c);
        System.out.flush();
    }
    
    public void print(final int n) {
        System.out.print(n);
        System.out.flush();
    }
    
    public void print(final float n) {
        System.out.print(n);
        System.out.flush();
    }
    
    public void print(final double n) {
        System.out.print(n);
        System.out.flush();
    }
    
    public void print(final String s) {
        System.out.print(s);
        System.out.flush();
    }
    
    public void print(final Object o) {
        System.out.print(o.toString());
        System.out.flush();
    }
    
    public void println(final boolean b) {
        this.print(b);
        System.out.println();
    }
    
    public void println(final char c) {
        this.print(c);
        System.out.println();
    }
    
    public void println(final int n) {
        this.print(n);
        System.out.println();
    }
    
    public void println(final float n) {
        this.print(n);
        System.out.println();
    }
    
    public void println(final double n) {
        this.print(n);
        System.out.println();
    }
    
    public void println(final String s) {
        this.print(s);
        System.out.println();
    }
    
    public void println(final Object o) {
        System.out.println(o.toString());
    }
    
    public void println() {
        System.out.println();
    }
    
    public final float abs(final float n) {
        return (n < 0.0f) ? (-n) : n;
    }
    
    public final int abs(final int n) {
        return (n < 0) ? (-n) : n;
    }
    
    public final float sq(final float n) {
        return n * n;
    }
    
    public final float sqrt(final float n) {
        return (float)Math.sqrt(n);
    }
    
    public final float pow(final float n, final float n2) {
        return (float)Math.pow(n, n2);
    }
    
    public final float max(final float n, final float n2) {
        return Math.max(n, n2);
    }
    
    public final float max(final float n, final float n2, final float n3) {
        return Math.max(n, Math.max(n2, n3));
    }
    
    public final float min(final float n, final float n2) {
        return Math.min(n, n2);
    }
    
    public final float min(final float n, final float n2, final float n3) {
        return Math.min(n, Math.min(n2, n3));
    }
    
    public final float constrain(final float n, final float n2, final float n3) {
        return (n < n2) ? n2 : ((n > n3) ? n3 : n);
    }
    
    public final int max(final int n, final int n2) {
        return (n > n2) ? n : n2;
    }
    
    public final int max(final int n, final int n2, final int n3) {
        return (n > n2) ? ((n > n3) ? n : n3) : n2;
    }
    
    public final int min(final int n, final int n2) {
        return (n < n2) ? n : n2;
    }
    
    public final int min(final int n, final int n2, final int n3) {
        return (n < n2) ? ((n < n3) ? n : n3) : n2;
    }
    
    public final int constrain(final int n, final int n2, final int n3) {
        return (n < n2) ? n2 : ((n > n3) ? n3 : n);
    }
    
    public final float sin(final float n) {
        return (float)Math.sin(n);
    }
    
    public final float cos(final float n) {
        return (float)Math.cos(n);
    }
    
    public final float tan(final float n) {
        return (float)Math.tan(n);
    }
    
    public final float atan2(final float n, final float n2) {
        return (float)Math.atan2(n, n2);
    }
    
    public final float degrees(final float n) {
        return n * 57.295776f;
    }
    
    public final float radians(final float n) {
        return n * 0.017453292f;
    }
    
    public final float ceil(final float n) {
        return (float)Math.ceil(n);
    }
    
    public final float floor(final float n) {
        return (float)Math.floor(n);
    }
    
    public final float round(final float n) {
        return Math.round(n);
    }
    
    public final float dist(final float n, final float n2, final float n3, final float n4) {
        return this.sqrt(this.sq(n3 - n) + this.sq(n4 - n2));
    }
    
    public final float dist(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        return this.sqrt(this.sq(n4 - n) + this.sq(n5 - n2) + this.sq(n6 - n3));
    }
    
    public final float random(final float n) {
        return (float)Math.random() * n;
    }
    
    public final float random(final float n, final float n2) {
        return n + (float)Math.random() * (n2 - n);
    }
    
    public float noise(final float n) {
        return this.noise(n, 0.0f, 0.0f);
    }
    
    public float noise(final float n, final float n2) {
        return this.noise(n, n2, 0.0f);
    }
    
    public float noise(float n, float n2, float n3) {
        if (this.perlin == null) {
            this.perlin = new float[4096];
            for (int i = 0; i < 4096; ++i) {
                this.perlin[i] = (float)Math.random();
            }
            final BGraphics g = this.g;
            this.perlin_cosTable = BGraphics.cosLUT;
            final BGraphics g2 = this.g;
            final int n4 = 720;
            this.perlin_PI = n4;
            this.perlin_TWOPI = n4;
            this.perlin_PI >>= 1;
        }
        if (n < 0.0f) {
            n = -n;
        }
        if (n2 < 0.0f) {
            n2 = -n2;
        }
        if (n3 < 0.0f) {
            n3 = -n3;
        }
        int n5 = (int)n;
        int n6 = (int)n2;
        int n7 = (int)n3;
        float n8 = n - n5;
        float n9 = n2 - n6;
        float n10 = n3 - n7;
        float n11 = 0.0f;
        float n12 = 0.5f;
        for (int j = 0; j < this.perlin_octaves; ++j) {
            int n13 = n5 + (n6 << 4) + (n7 << 8);
            final float noise_fsc = this.noise_fsc(n8);
            final float noise_fsc2 = this.noise_fsc(n9);
            final float n14 = this.perlin[n13 & 0xFFF];
            final float n15 = n14 + noise_fsc * (this.perlin[n13 + 1 & 0xFFF] - n14);
            final float n16 = this.perlin[n13 + 16 & 0xFFF];
            final float n17 = n15 + noise_fsc2 * (n16 + noise_fsc * (this.perlin[n13 + 16 + 1 & 0xFFF] - n16) - n15);
            n13 += 256;
            final float n18 = this.perlin[n13 & 0xFFF];
            final float n19 = n18 + noise_fsc * (this.perlin[n13 + 1 & 0xFFF] - n18);
            final float n20 = this.perlin[n13 + 16 & 0xFFF];
            n11 += (n17 + this.noise_fsc(n10) * (n19 + noise_fsc2 * (n20 + noise_fsc * (this.perlin[n13 + 16 + 1 & 0xFFF] - n20) - n19) - n17)) * n12;
            n12 *= this.perlin_amp_falloff;
            n5 <<= 1;
            n8 *= 2.0f;
            n6 <<= 1;
            n9 *= 2.0f;
            n7 <<= 1;
            n10 *= 2.0f;
            if (n8 >= 1.0f) {
                ++n5;
                --n8;
            }
            if (n9 >= 1.0f) {
                ++n6;
                --n9;
            }
            if (n10 >= 1.0f) {
                ++n7;
                --n10;
            }
        }
        return n11;
    }
    
    private final float noise_fsc(final float n) {
        return 0.5f * (1.0f - this.perlin_cosTable[(int)(n * this.perlin_PI) % this.perlin_TWOPI]);
    }
    
    public void noiseDetail(final int perlin_octaves) {
        if (perlin_octaves > 0) {
            this.perlin_octaves = perlin_octaves;
        }
    }
    
    public void noiseDetail(final int perlin_octaves, final float perlin_amp_falloff) {
        if (perlin_octaves > 0) {
            this.perlin_octaves = perlin_octaves;
        }
        if (perlin_amp_falloff > 0.0f) {
            this.perlin_amp_falloff = perlin_amp_falloff;
        }
    }
    
    public static int[] expand(final int[] array) {
        return expand(array, array.length << 1);
    }
    
    public static int[] expand(final int[] array, final int n) {
        final int[] array2 = new int[n];
        System.arraycopy(array, 0, array2, 0, Math.min(n, array.length));
        return array2;
    }
    
    public static float[] expand(final float[] array) {
        return expand(array, array.length << 1);
    }
    
    public static float[] expand(final float[] array, final int n) {
        final float[] array2 = new float[n];
        System.arraycopy(array, 0, array2, 0, Math.min(n, array.length));
        return array2;
    }
    
    public static String[] expand(final String[] array) {
        return expand(array, array.length << 1);
    }
    
    public static String[] expand(final String[] array, final int n) {
        final String[] array2 = new String[n];
        System.arraycopy(array, 0, array2, 0, Math.min(n, array.length));
        return array2;
    }
    
    public static Object[] expand(final Object[] array) {
        return expand(array, array.length << 1);
    }
    
    public static Object[] expand(final Object[] array, final int n) {
        final Object[] array2 = new Object[n];
        System.arraycopy(array, 0, array2, 0, Math.min(n, array.length));
        return array2;
    }
    
    public static int[] subset(final int[] array, final int n) {
        return subset(array, n, array.length - n);
    }
    
    public static int[] subset(final int[] array, final int n, final int n2) {
        final int[] array2 = new int[n2];
        System.arraycopy(array, n, array2, 0, n2);
        return array2;
    }
    
    public static float[] subset(final float[] array, final int n) {
        return subset(array, n, array.length - n);
    }
    
    public static float[] subset(final float[] array, final int n, final int n2) {
        final float[] array2 = new float[n2];
        System.arraycopy(array, n, array2, 0, n2);
        return array2;
    }
    
    public static String[] subset(final String[] array, final int n) {
        return subset(array, n, array.length - n);
    }
    
    public static String[] subset(final String[] array, final int n, final int n2) {
        final String[] array2 = new String[n2];
        System.arraycopy(array, n, array2, 0, n2);
        return array2;
    }
    
    public static Object[] subset(final Object[] array, final int n) {
        return subset(array, n, array.length - n);
    }
    
    public static Object[] subset(final Object[] array, final int n, final int n2) {
        final Object[] array2 = new Object[n2];
        System.arraycopy(array, n, array2, 0, n2);
        return array2;
    }
    
    public static String[] splitStrings(final String s) {
        return splitStrings(s, ' ');
    }
    
    public static String[] splitStrings(final String s, final char c) {
        final char[] charArray = s.toCharArray();
        int n = 1;
        for (int i = 0; i < charArray.length; ++i) {
            if (charArray[i] == c) {
                ++n;
            }
        }
        if (charArray[charArray.length - 1] == c) {
            --n;
        }
        if (n == 0) {
            return new String[] { new String(s) };
        }
        final String[] array = new String[n];
        int n2 = 0;
        int n3 = 0;
        for (int j = 0; j < charArray.length; ++j) {
            if (charArray[j] == c) {
                array[n2++] = new String(charArray, n3, j - n3);
                n3 = j + 1;
            }
        }
        if (n3 != charArray.length) {
            array[n2] = new String(charArray, n3, charArray.length - n3);
        }
        return array;
    }
    
    public static int[] splitInts(final String s) {
        return splitInts(s, ' ', 0);
    }
    
    public static int[] splitInts(final String s, final char c) {
        return splitInts(s, c, 0);
    }
    
    public static int[] splitInts(final String s, final char c, final int n) {
        final String[] splitStrings = splitStrings(s, c);
        if (splitStrings == null) {
            return null;
        }
        final int[] array = new int[splitStrings.length];
        for (int i = 0; i < splitStrings.length; ++i) {
            try {
                array[i] = Integer.parseInt(splitStrings[i]);
            }
            catch (NumberFormatException ex) {
                array[i] = n;
            }
        }
        return array;
    }
    
    public static long[] splitLongs(final String s) {
        return splitLongs(s, ' ', 0L);
    }
    
    public static long[] splitLongs(final String s, final char c) {
        return splitLongs(s, c, 0L);
    }
    
    public static long[] splitLongs(final String s, final char c, final long n) {
        final String[] splitStrings = splitStrings(s, c);
        if (splitStrings == null) {
            return null;
        }
        final long[] array = new long[splitStrings.length];
        for (int i = 0; i < splitStrings.length; ++i) {
            try {
                array[i] = Long.parseLong(splitStrings[i]);
            }
            catch (NumberFormatException ex) {
                array[i] = n;
            }
        }
        return array;
    }
    
    public static float[] splitFloats(final String s) {
        return splitFloats(s, ' ', Float.NaN);
    }
    
    public static float[] splitFloats(final String s, final char c) {
        return splitFloats(s, c, Float.NaN);
    }
    
    public static float[] splitFloats(final String s, final char c, final float n) {
        final String[] splitStrings = splitStrings(s, c);
        if (splitStrings == null) {
            return null;
        }
        final float[] array = new float[splitStrings.length];
        for (int i = 0; i < splitStrings.length; ++i) {
            try {
                array[i] = new Float(splitStrings[i]);
            }
            catch (NumberFormatException ex) {
                array[i] = n;
            }
        }
        return array;
    }
    
    public static double[] splitDoubles(final String s) {
        return splitDoubles(s, ' ', Double.NaN);
    }
    
    public static double[] splitDoubles(final String s, final char c) {
        return splitDoubles(s, c, Double.NaN);
    }
    
    public static double[] splitDoubles(final String s, final char c, final double n) {
        final String[] splitStrings = splitStrings(s, c);
        if (splitStrings == null) {
            return null;
        }
        final double[] array = new double[splitStrings.length];
        for (int i = 0; i < splitStrings.length; ++i) {
            try {
                array[i] = new Double(splitStrings[i]);
            }
            catch (NumberFormatException ex) {
                array[i] = n;
            }
        }
        return array;
    }
    
    public static String join(final String[] array, final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            if (i != 0) {
                sb.append(s);
            }
            sb.append(array[i]);
        }
        return sb.toString();
    }
    
    public static String join(final int[] array, final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            if (i != 0) {
                sb.append(s);
            }
            sb.append(array[i]);
        }
        return sb.toString();
    }
    
    public static String join(final int[] array, final String s, final int minimumIntegerDigits) {
        if (minimumIntegerDigits == 0) {
            return join(array, s);
        }
        final NumberFormat instance = NumberFormat.getInstance();
        instance.setGroupingUsed(false);
        instance.setMinimumIntegerDigits(minimumIntegerDigits);
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            if (i != 0) {
                sb.append(s);
            }
            sb.append(instance.format(array[i]));
        }
        return sb.toString();
    }
    
    public static String join(final long[] array, final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            if (i != 0) {
                sb.append(s);
            }
            sb.append(array[i]);
        }
        return sb.toString();
    }
    
    public static String join(final long[] array, final String s, final int minimumIntegerDigits) {
        if (minimumIntegerDigits == 0) {
            return join(array, s);
        }
        final NumberFormat instance = NumberFormat.getInstance();
        instance.setMinimumIntegerDigits(minimumIntegerDigits);
        instance.setGroupingUsed(false);
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            if (i != 0) {
                sb.append(s);
            }
            sb.append(instance.format(array[i]));
        }
        return sb.toString();
    }
    
    public static String join(final float[] array, final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            if (i != 0) {
                sb.append(s);
            }
            sb.append(array[i]);
        }
        return sb.toString();
    }
    
    public static String join(final float[] array, final String s, final int minimumIntegerDigits, final int n) {
        if (minimumIntegerDigits == 0 && n == 0) {
            return join(array, s);
        }
        final NumberFormat instance = NumberFormat.getInstance();
        instance.setGroupingUsed(false);
        if (minimumIntegerDigits != 0) {
            instance.setMinimumIntegerDigits(minimumIntegerDigits);
        }
        if (n != 0) {
            instance.setMinimumFractionDigits(n);
            instance.setMaximumFractionDigits(n);
        }
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            if (i != 0) {
                sb.append(s);
            }
            sb.append(instance.format(array[i]));
        }
        return sb.toString();
    }
    
    public static String join(final double[] array, final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            if (i != 0) {
                sb.append(s);
            }
            sb.append(array[i]);
        }
        return sb.toString();
    }
    
    public static String join(final double[] array, final String s, final int minimumIntegerDigits, final int n) {
        if (minimumIntegerDigits == 0 && n == 0) {
            return join(array, s);
        }
        final NumberFormat instance = NumberFormat.getInstance();
        instance.setGroupingUsed(false);
        if (minimumIntegerDigits != 0) {
            instance.setMinimumIntegerDigits(minimumIntegerDigits);
        }
        if (n != 0) {
            instance.setMinimumFractionDigits(n);
            instance.setMaximumFractionDigits(n);
        }
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            if (i != 0) {
                sb.append(s);
            }
            sb.append(instance.format(array[i]));
        }
        return sb.toString();
    }
    
    public static String nf(final float n, final int n2, final int float_nf_right) {
        if (BApplet.float_nf != null && BApplet.float_nf_left == n2 && BApplet.float_nf_right == float_nf_right) {
            return BApplet.float_nf.format(n);
        }
        (BApplet.float_nf = NumberFormat.getInstance()).setGroupingUsed(false);
        if (n2 != 0) {
            BApplet.float_nf.setMinimumIntegerDigits(n2);
        }
        if (float_nf_right != 0) {
            BApplet.float_nf.setMinimumFractionDigits(float_nf_right);
            BApplet.float_nf.setMaximumFractionDigits(float_nf_right);
        }
        BApplet.float_nf_left = n2;
        BApplet.float_nf_right = float_nf_right;
        return BApplet.float_nf.format(n);
    }
    
    public static String nf(final int n, final int n2) {
        if (BApplet.int_nf != null && BApplet.int_nf_digits == n2) {
            return BApplet.int_nf.format(n);
        }
        (BApplet.int_nf = NumberFormat.getInstance()).setGroupingUsed(false);
        BApplet.int_nf.setMinimumIntegerDigits(n2);
        BApplet.int_nf_digits = n2;
        return BApplet.int_nf.format(n);
    }
    
    public static String nfs(final float n, final int n2, final int n3) {
        return nfs(n, n2, n3, false);
    }
    
    public static String nfs(final float n, final int n2, final int n3, final boolean b) {
        if (n < 0.0f) {
            return nf(n, n2, n3);
        }
        return (b ? '+' : ' ') + nf(n, n2, n3);
    }
    
    public static String nfs(final int n, final int n2) {
        return nfs(n, n2, false);
    }
    
    public static String nfs(final int n, final int n2, final boolean b) {
        if (n < 0) {
            return nf(n, n2);
        }
        return (b ? '+' : ' ') + nf(n, n2);
    }
    
    public void setupExternal(final Frame frame) {
        this.externalRuntime = true;
        this.stopper = new Stopper();
        frame.addComponentListener(new ComponentAdapter() {
            public final void componentMoved(final ComponentEvent componentEvent) {
                final Point location = ((Frame)componentEvent.getSource()).getLocation();
                System.err.println("__MOVE__ " + location.x + ' ' + location.y);
                System.err.flush();
            }
        });
        frame.addWindowListener(new WindowAdapter() {
            public final void windowClosing(final WindowEvent windowEvent) {
                System.err.println("__QUIT__");
                System.err.flush();
                System.exit(0);
            }
        });
    }
    
    public static void main(final String[] array) {
        if (array.length < 1) {
            System.err.println("error: BApplet <appletname>");
            System.exit(1);
        }
        try {
            int n = 0;
            if (array[0].indexOf("--external=") == 0) {
                n = 1;
            }
            final Frame frame = new Frame();
            frame.pack();
            final BApplet bApplet = (BApplet)Class.forName(array[n]).newInstance();
            bApplet.init();
            bApplet.start();
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            if (n != 0) {
                String s = array[0].substring("--external=".length());
                boolean b = false;
                if (s.charAt(0) == 'e') {
                    b = true;
                }
                final boolean b2 = b;
                if (b2) {
                    s = s.substring(1);
                }
                final int[] splitInts = splitInts(s, ',');
                final int n2 = splitInts[0] - 20;
                final int n3 = splitInts[1];
                final Insets insets = frame.getInsets();
                final int n4 = 120;
                final int n5 = 120;
                final int n6 = Math.max(bApplet.width, n4) + insets.left + insets.right;
                final int n7 = Math.max(bApplet.height, n5) + insets.top + insets.bottom;
                if (n2 - n6 > 10) {
                    frame.setBounds(n2 - n6, n3, n6, n7);
                }
                else {
                    int n8 = splitInts[0] + 66;
                    int n9 = splitInts[1] + 66;
                    if (n8 + n6 > screenSize.width - 33 || n9 + n7 > screenSize.height - 33) {
                        n8 = (screenSize.width - n6) / 2;
                        n9 = (screenSize.height - n7) / 2;
                    }
                    frame.setBounds(n8, n9, n6, n7);
                }
                if (b2) {
                    frame.setLocation(splitInts[0], splitInts[1]);
                }
                frame.addComponentListener(new ComponentAdapter() {
                    public final void componentMoved(final ComponentEvent componentEvent) {
                        componentEvent.getComponent().getX();
                        componentEvent.getComponent().getY();
                    }
                });
                frame.setLayout(null);
                frame.add(bApplet);
                frame.setBackground(SystemColor.control);
                bApplet.setBounds((n6 - bApplet.width) / 2, insets.top + (n7 - insets.top - insets.bottom - bApplet.height) / 2, n6, n7);
                bApplet.setupExternal(frame);
            }
            else {
                frame.setLayout(new BorderLayout());
                frame.add(bApplet, "Center");
                frame.pack();
                frame.setLocation((screenSize.width - bApplet.g.width) / 2, (screenSize.height - bApplet.g.height) / 2);
                frame.addWindowListener(new WindowAdapter() {
                    public final void windowClosing(final WindowEvent windowEvent) {
                        System.exit(0);
                    }
                });
            }
            frame.show();
            bApplet.requestFocus();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }
    
    public void alpha(final int[] array) {
        this.g.alpha(array);
    }
    
    public void alpha(final BImage bImage) {
        this.g.alpha(bImage);
    }
    
    public int blendColor(final int n, final int n2, final int n3) {
        return this.g.blendColor(n, n2, n3);
    }
    
    public void toGrayscale() {
        this.g.toGrayscale();
    }
    
    public int get(final int n, final int n2) {
        return this.g.get(n, n2);
    }
    
    public BImage get(final int n, final int n2, final int n3, final int n4) {
        return this.g.get(n, n2, n3, n4);
    }
    
    public void set(final int n, final int n2, final int n3) {
        this.g.set(n, n2, n3);
    }
    
    public void set(final int n, final int n2, final BImage bImage) {
        this.g.set(n, n2, bImage);
    }
    
    public void replicate(final int n, final int n2, final int n3, final int n4) {
        this.g.replicate(n, n2, n3, n4);
    }
    
    public void replicate(final BImage bImage, final int n, final int n2, final int n3, final int n4) {
        this.g.replicate(bImage, n, n2, n3, n4);
    }
    
    public void replicate(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        this.g.replicate(n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    public void replicate(final BImage bImage, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        this.g.replicate(bImage, n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    public void blend(final BImage bImage, final int n, final int n2, final int n3, final int n4, final int n5) {
        this.g.blend(bImage, n, n2, n3, n4, n5);
    }
    
    public void blend(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.g.blend(n, n2, n3, n4, n5);
    }
    
    public void blend(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        this.g.blend(n, n2, n3, n4, n5, n6, n7, n8, n9);
    }
    
    public void blend(final BImage bImage, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        this.g.blend(bImage, n, n2, n3, n4, n5, n6, n7, n8, n9);
    }
    
    public BImage copy() {
        return this.g.copy();
    }
    
    public BImage copy(final int n, final int n2) {
        return this.g.copy(n, n2);
    }
    
    public void save(final String s) {
        this.g.save(s);
    }
    
    public void defaults() {
        this.g.defaults();
    }
    
    public void beginFrame() {
        this.g.beginFrame();
    }
    
    public void endFrame() {
        this.g.endFrame();
    }
    
    public final float[] nextVertex() {
        return this.g.nextVertex();
    }
    
    public final void addTexture(final BImage bImage) {
        this.g.addTexture(bImage);
    }
    
    public final void addLine(final int n, final int n2) {
        this.g.addLine(n, n2);
    }
    
    public final void addTriangle(final int n, final int n2, final int n3) {
        this.g.addTriangle(n, n2, n3);
    }
    
    public void beginShape() {
        this.g.beginShape();
    }
    
    public void beginShape(final int n) {
        this.g.beginShape(n);
    }
    
    public void texture(final BImage bImage) {
        this.g.texture(bImage);
    }
    
    public void textureMode(final int n) {
        this.g.textureMode(n);
    }
    
    public void normal(final float n, final float n2, final float n3) {
        this.g.normal(n, n2, n3);
    }
    
    public void vertex(final float n, final float n2) {
        this.g.vertex(n, n2);
    }
    
    public void vertex(final float n, final float n2, final float n3, final float n4) {
        this.g.vertex(n, n2, n3, n4);
    }
    
    public void vertex(final float n, final float n2, final float n3) {
        this.g.vertex(n, n2, n3);
    }
    
    public void vertex(final float n, final float n2, final float n3, final float n4, final float n5) {
        this.g.vertex(n, n2, n3, n4, n5);
    }
    
    public void bezierVertex(final float n, final float n2) {
        this.g.bezierVertex(n, n2);
    }
    
    public void bezierVertex(final float n, final float n2, final float n3) {
        this.g.bezierVertex(n, n2, n3);
    }
    
    public void curveVertex(final float n, final float n2) {
        this.g.curveVertex(n, n2);
    }
    
    public void curveVertex(final float n, final float n2, final float n3) {
        this.g.curveVertex(n, n2, n3);
    }
    
    public void endShape_newgraphics() {
        this.g.endShape_newgraphics();
    }
    
    public void endShape() {
        this.g.endShape();
    }
    
    public void flat_image(final BImage bImage, final int n, final int n2) {
        this.g.flat_image(bImage, n, n2);
    }
    
    public void point(final float n, final float n2) {
        this.g.point(n, n2);
    }
    
    public void point(final float n, final float n2, final float n3) {
        this.g.point(n, n2, n3);
    }
    
    public void line(final float n, final float n2, final float n3, final float n4) {
        this.g.line(n, n2, n3, n4);
    }
    
    public void line(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.g.line(n, n2, n3, n4, n5, n6);
    }
    
    public void triangle(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.g.triangle(n, n2, n3, n4, n5, n6);
    }
    
    public void quad(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        this.g.quad(n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    public void rectMode(final int n) {
        this.g.rectMode(n);
    }
    
    public void rect(final float n, final float n2, final float n3, final float n4) {
        this.g.rect(n, n2, n3, n4);
    }
    
    public void ellipseMode(final int n) {
        this.g.ellipseMode(n);
    }
    
    public void ellipse(final float n, final float n2, final float n3, final float n4) {
        this.g.ellipse(n, n2, n3, n4);
    }
    
    public void box(final float n) {
        this.g.box(n);
    }
    
    public void box(final float n, final float n2, final float n3) {
        this.g.box(n, n2, n3);
    }
    
    public void sphereDetail(final int n) {
        this.g.sphereDetail(n);
    }
    
    public void sphere(final float n) {
        this.g.sphere(n);
    }
    
    public void sphere(final float n, final float n2, final float n3, final float n4) {
        this.g.sphere(n, n2, n3, n4);
    }
    
    public float bezierPoint(final float n, final float n2, final float n3, final float n4, final float n5) {
        return this.g.bezierPoint(n, n2, n3, n4, n5);
    }
    
    public float bezierTangent(final float n, final float n2, final float n3, final float n4, final float n5) {
        return this.g.bezierTangent(n, n2, n3, n4, n5);
    }
    
    public void bezier(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        this.g.bezier(n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    public void bezier(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12) {
        this.g.bezier(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12);
    }
    
    public void bezierSegments(final int n) {
        this.g.bezierSegments(n);
    }
    
    public void curveSegments(final int n) {
        this.g.curveSegments(n);
    }
    
    public void curveTightness(final float n) {
        this.g.curveTightness(n);
    }
    
    public float curvePoint(final float n, final float n2, final float n3, final float n4, final float n5) {
        return this.g.curvePoint(n, n2, n3, n4, n5);
    }
    
    public float curveTangent(final float n, final float n2, final float n3, final float n4, final float n5) {
        return this.g.curveTangent(n, n2, n3, n4, n5);
    }
    
    public void curve(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        this.g.curve(n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    public void curve(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12) {
        this.g.curve(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12);
    }
    
    public BImage loadImage(final String s) {
        return this.g.loadImage(s);
    }
    
    public BImage loadImage(final String s, final boolean b) {
        return this.g.loadImage(s, b);
    }
    
    public void imageMode(final int n) {
        this.g.imageMode(n);
    }
    
    public void image(final BImage bImage, final float n, final float n2) {
        this.g.image(bImage, n, n2);
    }
    
    public void image(final BImage bImage, final float n, final float n2, final float n3, final float n4) {
        this.g.image(bImage, n, n2, n3, n4);
    }
    
    public void image(final BImage bImage, final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        this.g.image(bImage, n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    public void cache(final BImage bImage) {
        this.g.cache(bImage);
    }
    
    public void cache(final BImage[] array) {
        this.g.cache(array);
    }
    
    public BFont loadFont(final String s) {
        return this.g.loadFont(s);
    }
    
    public void textFont(final BFont bFont) {
        this.g.textFont(bFont);
    }
    
    public void textFont(final BFont bFont, final float n) {
        this.g.textFont(bFont, n);
    }
    
    public void textSize(final float n) {
        this.g.textSize(n);
    }
    
    public void textLeading(final float n) {
        this.g.textLeading(n);
    }
    
    public void textMode(final int n) {
        this.g.textMode(n);
    }
    
    public void textSpace(final int n) {
        this.g.textSpace(n);
    }
    
    public void text(final char c, final float n, final float n2) {
        this.g.text(c, n, n2);
    }
    
    public void text(final String s, final float n, final float n2) {
        this.g.text(s, n, n2);
    }
    
    public void text(final int n, final float n2, final float n3) {
        this.g.text(n, n2, n3);
    }
    
    public void text(final float n, final float n2, final float n3) {
        this.g.text(n, n2, n3);
    }
    
    public void push() {
        this.g.push();
    }
    
    public void pop() {
        this.g.pop();
    }
    
    public void resetMatrix() {
        this.g.resetMatrix();
    }
    
    public void applyMatrix(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12, final float n13, final float n14, final float n15, final float n16) {
        this.g.applyMatrix(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16);
    }
    
    public void beginCamera() {
        this.g.beginCamera();
    }
    
    public void cameraMode(final int n) {
        this.g.cameraMode(n);
    }
    
    public void endCamera() {
        this.g.endCamera();
    }
    
    public float screenX(final float n, final float n2, final float n3) {
        return this.g.screenX(n, n2, n3);
    }
    
    public float screenY(final float n, final float n2, final float n3) {
        return this.g.screenY(n, n2, n3);
    }
    
    public float screenZ(final float n, final float n2, final float n3) {
        return this.g.screenZ(n, n2, n3);
    }
    
    public float objectX(final float n, final float n2, final float n3) {
        return this.g.objectX(n, n2, n3);
    }
    
    public float objectY(final float n, final float n2, final float n3) {
        return this.g.objectY(n, n2, n3);
    }
    
    public float objectZ(final float n, final float n2, final float n3) {
        return this.g.objectZ(n, n2, n3);
    }
    
    public void ortho(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.g.ortho(n, n2, n3, n4, n5, n6);
    }
    
    public void perspective(final float n, final float n2, final float n3, final float n4) {
        this.g.perspective(n, n2, n3, n4);
    }
    
    public void frustum(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.g.frustum(n, n2, n3, n4, n5, n6);
    }
    
    public void lookat(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9) {
        this.g.lookat(n, n2, n3, n4, n5, n6, n7, n8, n9);
    }
    
    public void translate(final float n, final float n2) {
        this.g.translate(n, n2);
    }
    
    public void translate(final float n, final float n2, final float n3) {
        this.g.translate(n, n2, n3);
    }
    
    public void rotateX(final float n) {
        this.g.rotateX(n);
    }
    
    public void rotateY(final float n) {
        this.g.rotateY(n);
    }
    
    public void rotate(final float n) {
        this.g.rotate(n);
    }
    
    public void rotateZ(final float n) {
        this.g.rotateZ(n);
    }
    
    public void rotate(final float n, final float n2, final float n3, final float n4) {
        this.g.rotate(n, n2, n3, n4);
    }
    
    public void scale(final float n) {
        this.g.scale(n);
    }
    
    public void scale(final float n, final float n2) {
        this.g.scale(n, n2);
    }
    
    public void scale(final float n, final float n2, final float n3) {
        this.g.scale(n, n2, n3);
    }
    
    public void transform(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12, final float n13, final float n14, final float n15, final float n16) {
        this.g.transform(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16);
    }
    
    public void colorMode(final int n) {
        this.g.colorMode(n);
    }
    
    public void colorMode(final int n, final float n2) {
        this.g.colorMode(n, n2);
    }
    
    public void colorMode(final int n, final float n2, final float n3, final float n4) {
        this.g.colorMode(n, n2, n3, n4);
    }
    
    public void colorMode(final int n, final float n2, final float n3, final float n4, final float n5) {
        this.g.colorMode(n, n2, n3, n4, n5);
    }
    
    public void noTint() {
        this.g.noTint();
    }
    
    public void tint(final int n) {
        this.g.tint(n);
    }
    
    public void tint(final float n) {
        this.g.tint(n);
    }
    
    public void tint(final float n, final float n2) {
        this.g.tint(n, n2);
    }
    
    public void tint(final float n, final float n2, final float n3) {
        this.g.tint(n, n2, n3);
    }
    
    public void tint(final float n, final float n2, final float n3, final float n4) {
        this.g.tint(n, n2, n3, n4);
    }
    
    public void noFill() {
        this.g.noFill();
    }
    
    public void fill(final int n) {
        this.g.fill(n);
    }
    
    public void fill(final float n) {
        this.g.fill(n);
    }
    
    public void fill(final float n, final float n2) {
        this.g.fill(n, n2);
    }
    
    public void fill(final float n, final float n2, final float n3) {
        this.g.fill(n, n2, n3);
    }
    
    public void fill(final float n, final float n2, final float n3, final float n4) {
        this.g.fill(n, n2, n3, n4);
    }
    
    public void strokeWeight(final float n) {
        this.g.strokeWeight(n);
    }
    
    public void strokeJoin(final int n) {
        this.g.strokeJoin(n);
    }
    
    public void strokeMiter(final int n) {
        this.g.strokeMiter(n);
    }
    
    public void noStroke() {
        this.g.noStroke();
    }
    
    public void stroke(final int n) {
        this.g.stroke(n);
    }
    
    public void stroke(final float n) {
        this.g.stroke(n);
    }
    
    public void stroke(final float n, final float n2) {
        this.g.stroke(n, n2);
    }
    
    public void stroke(final float n, final float n2, final float n3) {
        this.g.stroke(n, n2, n3);
    }
    
    public void stroke(final float n, final float n2, final float n3, final float n4) {
        this.g.stroke(n, n2, n3, n4);
    }
    
    public void background(final int n) {
        this.g.background(n);
    }
    
    public void background(final float n) {
        this.g.background(n);
    }
    
    public void background(final float n, final float n2, final float n3) {
        this.g.background(n, n2, n3);
    }
    
    public void background(final BImage bImage) {
        this.g.background(bImage);
    }
    
    public void clear() {
        this.g.clear();
    }
    
    public void lights() {
        this.g.lights();
    }
    
    public void noLights() {
        this.g.noLights();
    }
    
    public void smooth() {
        this.g.smooth();
    }
    
    public void noSmooth() {
        this.g.noSmooth();
    }
    
    public void hint(final int n) {
        this.g.hint(n);
    }
    
    public void unhint(final int n) {
        this.g.unhint(n);
    }
    
    public void message(final int n, final String s) {
        this.g.message(n, s);
    }
    
    public void message(final int n, final String s, final Exception ex) {
        this.g.message(n, s, ex);
    }
    
    public InputStream loadStream(final String s) throws IOException {
        return this.g.loadStream(s);
    }
    
    public byte[] loadBytes(final String s) {
        return this.g.loadBytes(s);
    }
    
    public byte[] loadBytes(final InputStream inputStream) {
        return this.g.loadBytes(inputStream);
    }
    
    public String[] loadStrings(final String s) {
        return this.g.loadStrings(s);
    }
    
    public String[] loadStrings(final InputStream inputStream) {
        return this.g.loadStrings(inputStream);
    }
    
    public void saveBytes(final String s, final byte[] array) {
        this.g.saveBytes(s, array);
    }
    
    public void saveBytes(final OutputStream outputStream, final byte[] array) {
        this.g.saveBytes(outputStream, array);
    }
    
    public void saveStrings(final String s, final String[] array) {
        this.g.saveStrings(s, array);
    }
    
    public void saveStrings(final OutputStream outputStream, final String[] array) {
        this.g.saveStrings(outputStream, array);
    }
    
    public void sort(final String[] array) {
        this.g.sort(array);
    }
    
    public void sort(final String[] array, final Object[] array2) {
        this.g.sort(array, array2);
    }
    
    public void sort(final int[] array) {
        this.g.sort(array);
    }
    
    public void sort(final int[] array, final Object[] array2) {
        this.g.sort(array, array2);
    }
    
    public void sort(final float[] array) {
        this.g.sort(array);
    }
    
    public void sort(final float[] array, final Object[] array2) {
        this.g.sort(array, array2);
    }
    
    public void sort(final double[] array) {
        this.g.sort(array);
    }
    
    public void sort(final double[] array, final Object[] array2) {
        this.g.sort(array, array2);
    }
    
    public void sort(final String[] array, final int n, final Object[] array2) {
        this.g.sort(array, n, array2);
    }
    
    public void sort(final int[] array, final int n, final Object[] array2) {
        this.g.sort(array, n, array2);
    }
    
    public void sort(final float[] array, final int n, final Object[] array2) {
        this.g.sort(array, n, array2);
    }
    
    public void sort(final double[] array, final int n, final Object[] array2) {
        this.g.sort(array, n, array2);
    }
    
    public final int color(final int n, final int n2, final int n3) {
        return this.g.color(n, n2, n3);
    }
    
    public final int color(final float n, final float n2, final float n3) {
        return this.g.color(n, n2, n3);
    }
    
    public final int color(final int n, final int n2, final int n3, final int n4) {
        return this.g.color(n, n2, n3, n4);
    }
    
    public final int color(final float n, final float n2, final float n3, final float n4) {
        return this.g.color(n, n2, n3, n4);
    }
    
    public final float alpha(final int n) {
        return this.g.alpha(n);
    }
    
    public final float red(final int n) {
        return this.g.red(n);
    }
    
    public final float green(final int n) {
        return this.g.green(n);
    }
    
    public final float blue(final int n) {
        return this.g.blue(n);
    }
    
    public final float hue(final int n) {
        return this.g.hue(n);
    }
    
    public final float saturation(final int n) {
        return this.g.saturation(n);
    }
    
    public final float brightness(final int n) {
        return this.g.brightness(n);
    }
    
    private final /* synthetic */ void this() {
        this.fps = 10.0f;
        this.fpsLastMillis = 0L;
        this.fpsLastDelayTime = 0L;
        this.fpsTarget = 0.0f;
        this.net_mode = 0;
        this.length = -1;
        this.frequency = -1;
        this.sonicInit = false;
        this.cursor_type = 0;
        this.cursor_visible = true;
        this.perlin_octaves = 4;
        this.perlin_amp_falloff = 0.5f;
    }
    
    public BApplet() {
        this.this();
    }
    
    class Stopper implements Runnable
    {
        Thread thread;
        
        public void run() {
            while (this.thread != null) {
                try {
                    if (System.in.read() == 115) {
                        BApplet.this.finished = true;
                        this.thread = null;
                    }
                }
                catch (IOException ex) {
                    BApplet.this.finished = true;
                    this.thread = null;
                }
                try {
                    Thread.sleep(250L);
                }
                catch (InterruptedException ex2) {}
            }
        }
        
        public Stopper() {
            (this.thread = new Thread(this)).start();
        }
    }
}
