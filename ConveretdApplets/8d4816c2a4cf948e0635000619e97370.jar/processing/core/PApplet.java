// 
// Decompiled by Procyon v0.5.30
// 

package processing.core;

import java.awt.Insets;
import java.awt.GraphicsDevice;
import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.Label;
import java.awt.Window;
import java.awt.GraphicsEnvironment;
import java.awt.Color;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.util.StringTokenizer;
import java.lang.reflect.Array;
import java.io.ByteArrayOutputStream;
import java.io.BufferedInputStream;
import java.net.MalformedURLException;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.awt.FileDialog;
import java.awt.Container;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.awt.Font;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Point;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Cursor;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.awt.event.FocusEvent;
import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.io.PrintStream;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Frame;
import java.text.NumberFormat;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

public class PApplet extends Applet implements PConstants, Runnable, MouseListener, MouseMotionListener, KeyListener, FocusListener
{
    public static final String javaVersionName;
    public static final float javaVersion;
    public static int platform;
    public static String platformName;
    static final String NEW_RENDERER = "new renderer";
    static final boolean THREAD_DEBUG = false;
    public static final int DEFAULT_WIDTH = 100;
    public static final int DEFAULT_HEIGHT = 100;
    public static final String LEECH_WAKEUP = "Error while running applet.";
    public static final String ARGS_EDITOR_LOCATION = "--editor-location";
    public static final String ARGS_EXTERNAL = "--external";
    public static final String ARGS_LOCATION = "--location";
    public static final String ARGS_DISPLAY = "--display";
    public static final String ARGS_PRESENT = "--present";
    public static final String ARGS_PRESENT_BGCOLOR = "--present-color";
    public static final String ARGS_PRESENT_STOP_COLOR = "--present-stop-color";
    public static final String ARGS_SKETCH_FOLDER = "--sketch-folder";
    public static final char EXTERNAL_STOP = 's';
    public static final String EXTERNAL_QUIT = "__QUIT__";
    public static final String EXTERNAL_MOVE = "__MOVE__";
    static final int PERLIN_YWRAPB = 4;
    static final int PERLIN_YWRAP = 16;
    static final int PERLIN_ZWRAPB = 8;
    static final int PERLIN_ZWRAP = 256;
    static final int PERLIN_SIZE = 4095;
    static final int BYTES = 1;
    static final int CHARS = 2;
    static final int INTS = 3;
    static final int FLOATS = 4;
    static final int STRINGS = 5;
    private static NumberFormat int_nf;
    private static int int_nf_digits;
    private static boolean int_nf_commas;
    private static NumberFormat float_nf;
    private static int float_nf_left;
    private static int float_nf_right;
    private static boolean float_nf_commas;
    public PGraphics g;
    protected Object glock;
    public Frame frame;
    public Dimension screen;
    public PGraphics recorder;
    public String[] args;
    public String folder;
    public int[] pixels;
    public int width;
    public int height;
    public int mouseX;
    public int mouseY;
    public int pmouseX;
    public int pmouseY;
    protected int dmouseX;
    protected int dmouseY;
    protected int emouseX;
    protected int emouseY;
    public boolean firstMouse;
    public int mouseButton;
    public boolean mousePressed;
    public MouseEvent mouseEvent;
    public char key;
    public int keyCode;
    public boolean keyPressed;
    public KeyEvent keyEvent;
    public boolean focused;
    public boolean online;
    long millisOffset;
    public float framerate;
    protected long framerateLastMillis;
    protected long framerateLastDelayTime;
    protected float framerateTarget;
    protected boolean looping;
    protected boolean redraw;
    public int frameCount;
    public boolean finished;
    Thread thread;
    public Exception exception;
    protected RegisteredMethods sizeMethods;
    protected RegisteredMethods preMethods;
    protected RegisteredMethods drawMethods;
    protected RegisteredMethods postMethods;
    protected RegisteredMethods mouseEventMethods;
    protected RegisteredMethods keyEventMethods;
    protected RegisteredMethods disposeMethods;
    public PrintStream leechErr;
    protected boolean listenersAdded;
    MouseEvent[] mouseEventQueue;
    int mouseEventCount;
    KeyEvent[] keyEventQueue;
    int keyEventCount;
    int cursor_type;
    boolean cursor_visible;
    PImage invisible_cursor;
    Random internalRandom;
    int perlin_octaves;
    float perlin_amp_falloff;
    int perlin_TWOPI;
    int perlin_PI;
    float[] perlin_cosTable;
    float[] perlin;
    Random perlinRandom;
    int sort_mode;
    byte[] sort_bytes;
    char[] sort_chars;
    int[] sort_ints;
    float[] sort_floats;
    String[] sort_strings;
    static /* synthetic */ Class class$java$awt$Component;
    static /* synthetic */ Class class$java$awt$event$MouseEvent;
    static /* synthetic */ Class class$java$awt$event$KeyEvent;
    static /* synthetic */ Class class$processing$core$PApplet;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$java$awt$Image;
    static /* synthetic */ Class class$java$awt$Point;
    static /* synthetic */ Class class$java$awt$Toolkit;
    static /* synthetic */ Class class$java$awt$Font;
    static /* synthetic */ Class class$java$io$InputStream;
    
    public void init() {
        try {
            if (PApplet.javaVersion >= 1.4f) {
                Class class$java$awt$Component;
                if ((class$java$awt$Component = PApplet.class$java$awt$Component) == null) {
                    class$java$awt$Component = (PApplet.class$java$awt$Component = class("[Ljava.awt.Component;", false));
                }
                class$java$awt$Component.getMethod("setFocusTraversalKeysEnabled", Boolean.TYPE).invoke(this, Boolean.FALSE);
            }
        }
        catch (Exception ex) {}
        this.millisOffset = System.currentTimeMillis();
        this.finished = false;
        this.looping = true;
        this.redraw = true;
        this.firstMouse = true;
        this.sizeMethods = new RegisteredMethods();
        this.preMethods = new RegisteredMethods();
        this.drawMethods = new RegisteredMethods();
        this.postMethods = new RegisteredMethods();
        this.mouseEventMethods = new RegisteredMethods();
        this.keyEventMethods = new RegisteredMethods();
        this.disposeMethods = new RegisteredMethods();
        this.size(100, 100);
        this.width = 0;
        this.height = 0;
        try {
            this.getAppletContext();
            this.online = true;
        }
        catch (NullPointerException ex2) {
            this.online = false;
        }
        this.start();
    }
    
    public void start() {
        if (this.thread != null) {
            return;
        }
        (this.thread = new Thread(this)).start();
    }
    
    public void stop() {
        if (this.thread == null) {
            return;
        }
        this.thread = null;
        this.disposeMethods.handle();
    }
    
    public void destroy() {
        this.stop();
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public void registerSize(final Object o) {
        this.registerWithArgs(this.preMethods, "size", o, new Class[] { Integer.TYPE, Integer.TYPE });
    }
    
    public void registerPre(final Object o) {
        this.registerNoArgs(this.preMethods, "pre", o);
    }
    
    public void registerDraw(final Object o) {
        this.registerNoArgs(this.drawMethods, "draw", o);
    }
    
    public void registerPost(final Object o) {
        this.registerNoArgs(this.postMethods, "post", o);
    }
    
    public void registerMouseEvent(final Object o) {
        final Class[] array = { null };
        final int n = 0;
        Class class$java$awt$event$MouseEvent;
        if ((class$java$awt$event$MouseEvent = PApplet.class$java$awt$event$MouseEvent) == null) {
            class$java$awt$event$MouseEvent = (PApplet.class$java$awt$event$MouseEvent = class("[Ljava.awt.event.MouseEvent;", false));
        }
        array[n] = class$java$awt$event$MouseEvent;
        this.registerWithArgs(this.mouseEventMethods, "mouseEvent", o, array);
    }
    
    public void registerKeyEvent(final Object o) {
        final Class[] array = { null };
        final int n = 0;
        Class class$java$awt$event$KeyEvent;
        if ((class$java$awt$event$KeyEvent = PApplet.class$java$awt$event$KeyEvent) == null) {
            class$java$awt$event$KeyEvent = (PApplet.class$java$awt$event$KeyEvent = class("[Ljava.awt.event.KeyEvent;", false));
        }
        array[n] = class$java$awt$event$KeyEvent;
        this.registerWithArgs(this.keyEventMethods, "keyEvent", o, array);
    }
    
    public void registerDispose(final Object o) {
        this.registerNoArgs(this.disposeMethods, "dispose", o);
    }
    
    protected void registerNoArgs(final RegisteredMethods registeredMethods, final String s, final Object o) {
        final Class<?> class1 = o.getClass();
        try {
            registeredMethods.add(o, class1.getMethod(s, (Class[])new Class[0]));
        }
        catch (Exception ex) {
            this.die("Could not register " + s + " + () for " + o, ex);
        }
    }
    
    protected void registerWithArgs(final RegisteredMethods registeredMethods, final String s, final Object o, final Class[] array) {
        final Class<?> class1 = o.getClass();
        try {
            registeredMethods.add(o, class1.getMethod(s, (Class[])array));
        }
        catch (Exception ex) {
            this.die("Could not register " + s + " + () for " + o, ex);
        }
    }
    
    public void setup() {
    }
    
    public synchronized void draw() {
        this.finished = true;
    }
    
    public void redraw() {
        if (!this.looping) {
            this.redraw = true;
            if (this.thread != null) {
                this.thread.interrupt();
            }
        }
    }
    
    public void loop() {
        if (!this.looping) {
            this.looping = true;
            if (this.thread != null) {
                this.thread.interrupt();
            }
        }
    }
    
    public void noLoop() {
        if (this.looping) {
            this.looping = false;
            this.framerateLastDelayTime = 0L;
            this.framerateLastMillis = 0L;
            if (this.thread != null) {
                this.thread.interrupt();
            }
        }
    }
    
    public void size(final int n, final int n2) {
        if (this.g != null) {
            this.size(n, n2, this.g.getClass().getName());
        }
        else if (PApplet.javaVersion >= 1.3f) {
            try {
                Class.forName("processing.core.PGraphics2");
                this.size(n, n2, "processing.core.PGraphics2");
            }
            catch (ClassNotFoundException ex) {
                this.size(n, n2, "processing.core.PGraphics");
            }
        }
    }
    
    public void size(final int width, final int height, final String s) {
        final String s2 = (this.g == null) ? null : this.g.getClass().getName();
        if (s2 != null) {
            if (s2.equals(s)) {
                if (width == this.g.width && height == this.g.height) {
                    this.g.defaults();
                    return;
                }
            }
            else if (this.frameCount > 0) {
                throw new RuntimeException("size() cannot be called to change the renderer outside of setup()");
            }
        }
        final String s3 = "Before using OpenGL, you must first select Import Library > opengl from the Sketch menu.";
        try {
            final Class<?> forName = Class.forName(s);
            final Class[] array = { Integer.TYPE, Integer.TYPE, null };
            final int n = 2;
            Class class$processing$core$PApplet;
            if ((class$processing$core$PApplet = PApplet.class$processing$core$PApplet) == null) {
                class$processing$core$PApplet = (PApplet.class$processing$core$PApplet = class("[Lprocessing.core.PApplet;", false));
            }
            array[n] = class$processing$core$PApplet;
            this.g = (PGraphics)forName.getConstructor((Class<?>[])array).newInstance(new Integer(width), new Integer(height), this);
            this.width = width;
            this.height = height;
            this.setSize(this.width, this.height);
        }
        catch (InvocationTargetException ex) {
            final String message = ex.getTargetException().getMessage();
            if (message != null && message.indexOf("no jogl in java.library.path") != -1) {
                throw new RuntimeException(s3);
            }
            ex.getTargetException().printStackTrace();
        }
        catch (ClassNotFoundException ex2) {
            if (ex2.getMessage().indexOf("processing.opengl.PGraphicsGL") != -1) {
                throw new RuntimeException(s3);
            }
            throw new RuntimeException("You need to use \"Import Library\" to add " + s + " to your sketch.");
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
            this.die("Could not start because of a problem with size()", ex3);
        }
        if (s2 != null && !s2.equals(s)) {
            throw new RuntimeException("new renderer");
        }
        this.g.defaults();
        this.sizeMethods.handle(new Object[] { new Integer(this.width), new Integer(this.height) });
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       processing/core/PApplet.javaVersion:F
        //     3: ldc_w           1.3
        //     6: fcmpg          
        //     7: ifge            121
        //    10: aload_1        
        //    11: new             Ljava/awt/Color;
        //    14: dup            
        //    15: bipush          64
        //    17: bipush          64
        //    19: bipush          64
        //    21: invokespecial   java/awt/Color.<init>:(III)V
        //    24: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //    27: aload_0        
        //    28: invokevirtual   processing/core/PApplet.getSize:()Ljava/awt/Dimension;
        //    31: astore_2       
        //    32: aload_1        
        //    33: iconst_0       
        //    34: iconst_0       
        //    35: aload_2        
        //    36: getfield        java/awt/Dimension.width:I
        //    39: aload_2        
        //    40: getfield        java/awt/Dimension.height:I
        //    43: invokevirtual   java/awt/Graphics.fillRect:(IIII)V
        //    46: aload_1        
        //    47: getstatic       java/awt/Color.white:Ljava/awt/Color;
        //    50: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //    53: aload_1        
        //    54: new             Ljava/awt/Font;
        //    57: dup            
        //    58: ldc_w           "Dialog"
        //    61: iconst_0       
        //    62: bipush          9
        //    64: invokespecial   java/awt/Font.<init>:(Ljava/lang/String;II)V
        //    67: invokevirtual   java/awt/Graphics.setFont:(Ljava/awt/Font;)V
        //    70: aload_1        
        //    71: ldc_w           "You need to install"
        //    74: iconst_5       
        //    75: bipush          15
        //    77: invokevirtual   java/awt/Graphics.drawString:(Ljava/lang/String;II)V
        //    80: aload_1        
        //    81: ldc_w           "Java 1.3 or later"
        //    84: iconst_5       
        //    85: bipush          25
        //    87: invokevirtual   java/awt/Graphics.drawString:(Ljava/lang/String;II)V
        //    90: aload_1        
        //    91: ldc_w           "to view this content."
        //    94: iconst_5       
        //    95: bipush          35
        //    97: invokevirtual   java/awt/Graphics.drawString:(Ljava/lang/String;II)V
        //   100: aload_1        
        //   101: ldc_w           "Click here to visit"
        //   104: iconst_5       
        //   105: bipush          50
        //   107: invokevirtual   java/awt/Graphics.drawString:(Ljava/lang/String;II)V
        //   110: aload_1        
        //   111: ldc_w           "java.com and install."
        //   114: iconst_5       
        //   115: bipush          60
        //   117: invokevirtual   java/awt/Graphics.drawString:(Ljava/lang/String;II)V
        //   120: return         
        //   121: aload_0        
        //   122: getfield        processing/core/PApplet.frameCount:I
        //   125: ifne            129
        //   128: return         
        //   129: goto            135
        //   132: aload_2        
        //   133: monitorexit    
        //   134: athrow         
        //   135: aload_0        
        //   136: getfield        processing/core/PApplet.glock:Ljava/lang/Object;
        //   139: dup            
        //   140: astore_2       
        //   141: monitorenter   
        //   142: aload_0        
        //   143: getfield        processing/core/PApplet.g:Lprocessing/core/PGraphics;
        //   146: ifnull          174
        //   149: aload_0        
        //   150: getfield        processing/core/PApplet.g:Lprocessing/core/PGraphics;
        //   153: getfield        processing/core/PGraphics.image:Ljava/awt/Image;
        //   156: ifnull          174
        //   159: aload_1        
        //   160: aload_0        
        //   161: getfield        processing/core/PApplet.g:Lprocessing/core/PGraphics;
        //   164: getfield        processing/core/PGraphics.image:Ljava/awt/Image;
        //   167: iconst_0       
        //   168: iconst_0       
        //   169: aconst_null    
        //   170: invokevirtual   java/awt/Graphics.drawImage:(Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
        //   173: pop            
        //   174: aload_2        
        //   175: monitorexit    
        //   176: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  132    134    132    135    Any
        //  142    176    132    135    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void run() {
        try {
            while (Thread.currentThread() == this.thread && !this.finished) {
                this.g.requestDisplay(this);
                try {
                    int n = this.looping ? 1 : 10000;
                    if (this.frameCount == 1) {
                        n = 1;
                    }
                    Thread.sleep(n);
                }
                catch (InterruptedException ex) {}
            }
        }
        catch (Exception exception) {
            this.finished = true;
            this.exception = exception;
            if (this.leechErr != null) {
                this.leechErr.println("Error while running applet.");
                exception.printStackTrace(this.leechErr);
            }
            else {
                System.err.println("Error while running applet.");
                exception.printStackTrace();
            }
        }
        this.stop();
    }
    
    public synchronized void display() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        processing/core/PApplet.looping:Z
        //     4: ifne            14
        //     7: aload_0        
        //     8: getfield        processing/core/PApplet.redraw:Z
        //    11: ifeq            340
        //    14: goto            20
        //    17: aload_1        
        //    18: monitorexit    
        //    19: athrow         
        //    20: aload_0        
        //    21: getfield        processing/core/PApplet.glock:Ljava/lang/Object;
        //    24: dup            
        //    25: astore_1       
        //    26: monitorenter   
        //    27: aload_0        
        //    28: getfield        processing/core/PApplet.g:Lprocessing/core/PGraphics;
        //    31: invokevirtual   processing/core/PGraphics.beginFrame:()V
        //    34: aload_0        
        //    35: getfield        processing/core/PApplet.frameCount:I
        //    38: ifne            103
        //    41: aload_0        
        //    42: invokevirtual   processing/core/PApplet.setup:()V
        //    45: goto            78
        //    48: astore_3       
        //    49: aload_3        
        //    50: invokevirtual   java/lang/RuntimeException.getMessage:()Ljava/lang/String;
        //    53: astore          4
        //    55: aload           4
        //    57: ifnull          76
        //    60: aload_3        
        //    61: invokevirtual   java/lang/RuntimeException.getMessage:()Ljava/lang/String;
        //    64: ldc             "new renderer"
        //    66: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;)I
        //    69: iconst_m1      
        //    70: if_icmpeq       76
        //    73: aload_1        
        //    74: monitorexit    
        //    75: return         
        //    76: aload_3        
        //    77: athrow         
        //    78: aload_0        
        //    79: aload_0        
        //    80: getfield        processing/core/PApplet.g:Lprocessing/core/PGraphics;
        //    83: getfield        processing/core/PGraphics.width:I
        //    86: putfield        processing/core/PApplet.width:I
        //    89: aload_0        
        //    90: aload_0        
        //    91: getfield        processing/core/PApplet.g:Lprocessing/core/PGraphics;
        //    94: getfield        processing/core/PGraphics.height:I
        //    97: putfield        processing/core/PApplet.height:I
        //   100: goto            284
        //   103: aload_0        
        //   104: getfield        processing/core/PApplet.framerateLastMillis:J
        //   107: lconst_0       
        //   108: lcmp           
        //   109: ifeq            152
        //   112: invokestatic    java/lang/System.currentTimeMillis:()J
        //   115: aload_0        
        //   116: getfield        processing/core/PApplet.framerateLastMillis:J
        //   119: lsub           
        //   120: l2f            
        //   121: fstore_3       
        //   122: fload_3        
        //   123: fconst_0       
        //   124: fcmpl          
        //   125: ifeq            152
        //   128: aload_0        
        //   129: aload_0        
        //   130: getfield        processing/core/PApplet.framerate:F
        //   133: ldc_w           0.9
        //   136: fmul           
        //   137: fconst_1       
        //   138: fload_3        
        //   139: ldc_w           1000.0
        //   142: fdiv           
        //   143: fdiv           
        //   144: ldc_w           0.1
        //   147: fmul           
        //   148: fadd           
        //   149: putfield        processing/core/PApplet.framerate:F
        //   152: aload_0        
        //   153: invokestatic    java/lang/System.currentTimeMillis:()J
        //   156: putfield        processing/core/PApplet.framerateLastMillis:J
        //   159: aload_0        
        //   160: getfield        processing/core/PApplet.framerateTarget:F
        //   163: fconst_0       
        //   164: fcmpl          
        //   165: ifeq            221
        //   168: aload_0        
        //   169: getfield        processing/core/PApplet.framerateLastDelayTime:J
        //   172: lconst_0       
        //   173: lcmp           
        //   174: ifne            187
        //   177: aload_0        
        //   178: invokestatic    java/lang/System.currentTimeMillis:()J
        //   181: putfield        processing/core/PApplet.framerateLastDelayTime:J
        //   184: goto            221
        //   187: aload_0        
        //   188: getfield        processing/core/PApplet.framerateLastDelayTime:J
        //   191: ldc_w           1000.0
        //   194: aload_0        
        //   195: getfield        processing/core/PApplet.framerateTarget:F
        //   198: fdiv           
        //   199: f2l            
        //   200: ladd           
        //   201: lstore_3       
        //   202: lload_3        
        //   203: invokestatic    java/lang/System.currentTimeMillis:()J
        //   206: lsub           
        //   207: l2i            
        //   208: istore          5
        //   210: aload_0        
        //   211: lload_3        
        //   212: putfield        processing/core/PApplet.framerateLastDelayTime:J
        //   215: aload_0        
        //   216: iload           5
        //   218: invokevirtual   processing/core/PApplet.delay:(I)V
        //   221: aload_0        
        //   222: getfield        processing/core/PApplet.preMethods:Lprocessing/core/PApplet$RegisteredMethods;
        //   225: invokevirtual   processing/core/PApplet$RegisteredMethods.handle:()V
        //   228: aload_0        
        //   229: aload_0        
        //   230: getfield        processing/core/PApplet.dmouseX:I
        //   233: putfield        processing/core/PApplet.pmouseX:I
        //   236: aload_0        
        //   237: aload_0        
        //   238: getfield        processing/core/PApplet.dmouseY:I
        //   241: putfield        processing/core/PApplet.pmouseY:I
        //   244: aload_0        
        //   245: invokevirtual   processing/core/PApplet.draw:()V
        //   248: aload_0        
        //   249: aload_0        
        //   250: getfield        processing/core/PApplet.mouseX:I
        //   253: putfield        processing/core/PApplet.dmouseX:I
        //   256: aload_0        
        //   257: aload_0        
        //   258: getfield        processing/core/PApplet.mouseY:I
        //   261: putfield        processing/core/PApplet.dmouseY:I
        //   264: aload_0        
        //   265: invokevirtual   processing/core/PApplet.dequeueMouseEvents:()V
        //   268: aload_0        
        //   269: invokevirtual   processing/core/PApplet.dequeueKeyEvents:()V
        //   272: aload_0        
        //   273: getfield        processing/core/PApplet.drawMethods:Lprocessing/core/PApplet$RegisteredMethods;
        //   276: invokevirtual   processing/core/PApplet$RegisteredMethods.handle:()V
        //   279: aload_0        
        //   280: iconst_0       
        //   281: putfield        processing/core/PApplet.redraw:Z
        //   284: aload_0        
        //   285: getfield        processing/core/PApplet.g:Lprocessing/core/PGraphics;
        //   288: invokevirtual   processing/core/PGraphics.endFrame:()V
        //   291: aload_0        
        //   292: getfield        processing/core/PApplet.recorder:Lprocessing/core/PGraphics;
        //   295: ifnull          310
        //   298: aload_0        
        //   299: getfield        processing/core/PApplet.recorder:Lprocessing/core/PGraphics;
        //   302: invokevirtual   processing/core/PGraphics.endFrame:()V
        //   305: aload_0        
        //   306: aconst_null    
        //   307: putfield        processing/core/PApplet.recorder:Lprocessing/core/PGraphics;
        //   310: aload_0        
        //   311: dup            
        //   312: getfield        processing/core/PApplet.frameCount:I
        //   315: iconst_1       
        //   316: iadd           
        //   317: putfield        processing/core/PApplet.frameCount:I
        //   320: aload_0        
        //   321: invokevirtual   processing/core/PApplet.repaint:()V
        //   324: aload_0        
        //   325: invokevirtual   processing/core/PApplet.getToolkit:()Ljava/awt/Toolkit;
        //   328: invokevirtual   java/awt/Toolkit.sync:()V
        //   331: aload_0        
        //   332: getfield        processing/core/PApplet.postMethods:Lprocessing/core/PApplet$RegisteredMethods;
        //   335: invokevirtual   processing/core/PApplet$RegisteredMethods.handle:()V
        //   338: aload_1        
        //   339: monitorexit    
        //   340: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  17     19     17     20     Any
        //  41     45     48     78     Ljava/lang/RuntimeException;
        //  76     340    17     20     Any
        //  27     75     17     20     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void addListeners() {
        if (!this.listenersAdded) {
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
            this.addKeyListener(this);
            this.addFocusListener(this);
            this.listenersAdded = true;
        }
    }
    
    protected void enqueueMouseEvent(final MouseEvent p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: goto            6
        //     3: aload_2        
        //     4: monitorexit    
        //     5: athrow         
        //     6: aload_0        
        //     7: getfield        processing/core/PApplet.mouseEventQueue:[Ljava/awt/event/MouseEvent;
        //    10: dup            
        //    11: astore_2       
        //    12: monitorenter   
        //    13: aload_0        
        //    14: getfield        processing/core/PApplet.mouseEventCount:I
        //    17: aload_0        
        //    18: getfield        processing/core/PApplet.mouseEventQueue:[Ljava/awt/event/MouseEvent;
        //    21: arraylength    
        //    22: if_icmpne       57
        //    25: aload_0        
        //    26: getfield        processing/core/PApplet.mouseEventCount:I
        //    29: iconst_1       
        //    30: ishl           
        //    31: anewarray       Ljava/awt/event/MouseEvent;
        //    34: astore          4
        //    36: aload_0        
        //    37: getfield        processing/core/PApplet.mouseEventQueue:[Ljava/awt/event/MouseEvent;
        //    40: iconst_0       
        //    41: aload           4
        //    43: iconst_0       
        //    44: aload_0        
        //    45: getfield        processing/core/PApplet.mouseEventCount:I
        //    48: invokestatic    java/lang/System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //    51: aload_0        
        //    52: aload           4
        //    54: putfield        processing/core/PApplet.mouseEventQueue:[Ljava/awt/event/MouseEvent;
        //    57: aload_0        
        //    58: getfield        processing/core/PApplet.mouseEventQueue:[Ljava/awt/event/MouseEvent;
        //    61: aload_0        
        //    62: dup            
        //    63: getfield        processing/core/PApplet.mouseEventCount:I
        //    66: dup_x1         
        //    67: iconst_1       
        //    68: iadd           
        //    69: putfield        processing/core/PApplet.mouseEventCount:I
        //    72: aload_1        
        //    73: aastore        
        //    74: aload_2        
        //    75: monitorexit    
        //    76: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  3      5      3      6      Any
        //  13     76     3      6      Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected void dequeueMouseEvents() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: goto            6
        //     3: aload_1        
        //     4: monitorexit    
        //     5: athrow         
        //     6: aload_0        
        //     7: getfield        processing/core/PApplet.mouseEventQueue:[Ljava/awt/event/MouseEvent;
        //    10: dup            
        //    11: astore_1       
        //    12: monitorenter   
        //    13: iconst_0       
        //    14: istore_3       
        //    15: goto            39
        //    18: aload_0        
        //    19: aload_0        
        //    20: getfield        processing/core/PApplet.mouseEventQueue:[Ljava/awt/event/MouseEvent;
        //    23: iload_3        
        //    24: aaload         
        //    25: putfield        processing/core/PApplet.mouseEvent:Ljava/awt/event/MouseEvent;
        //    28: aload_0        
        //    29: aload_0        
        //    30: getfield        processing/core/PApplet.mouseEvent:Ljava/awt/event/MouseEvent;
        //    33: invokevirtual   processing/core/PApplet.handleMouseEvent:(Ljava/awt/event/MouseEvent;)V
        //    36: iinc            3, 1
        //    39: iload_3        
        //    40: aload_0        
        //    41: getfield        processing/core/PApplet.mouseEventCount:I
        //    44: if_icmplt       18
        //    47: aload_0        
        //    48: iconst_0       
        //    49: putfield        processing/core/PApplet.mouseEventCount:I
        //    52: aload_1        
        //    53: monitorexit    
        //    54: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  3      5      3      6      Any
        //  13     54     3      6      Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected void handleMouseEvent(final MouseEvent mouseEvent) {
        this.pmouseX = this.emouseX;
        this.pmouseY = this.emouseY;
        this.mouseX = mouseEvent.getX();
        this.mouseY = mouseEvent.getY();
        this.mouseEvent = mouseEvent;
        final int modifiers = mouseEvent.getModifiers();
        if ((modifiers & 0x10) != 0x0) {
            this.mouseButton = 37;
        }
        else if ((modifiers & 0x8) != 0x0) {
            this.mouseButton = 3;
        }
        else if ((modifiers & 0x4) != 0x0) {
            this.mouseButton = 39;
        }
        if ((PApplet.platform == 3 || PApplet.platform == 2) && this.mouseEvent.isPopupTrigger()) {
            this.mouseButton = 39;
        }
        this.mouseEventMethods.handle(new Object[] { mouseEvent });
        if (this.firstMouse) {
            this.pmouseX = this.mouseX;
            this.pmouseY = this.mouseY;
            this.dmouseX = this.mouseX;
            this.dmouseY = this.mouseY;
            this.firstMouse = false;
        }
        switch (mouseEvent.getID()) {
            case 501: {
                this.mousePressed = true;
                this.mousePressed();
                break;
            }
            case 502: {
                this.mousePressed = false;
                this.mouseReleased();
                break;
            }
            case 500: {
                this.mouseClicked();
                break;
            }
            case 506: {
                this.mouseDragged();
                break;
            }
            case 503: {
                this.mouseMoved();
                break;
            }
        }
        this.emouseX = this.mouseX;
        this.emouseY = this.mouseY;
    }
    
    protected void checkMouseEvent(final MouseEvent mouseEvent) {
        if (this.looping) {
            this.enqueueMouseEvent(mouseEvent);
        }
        else {
            this.handleMouseEvent(mouseEvent);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (PApplet.javaVersion < 1.3f) {
            this.link("http://java.com/");
        }
        this.checkMouseEvent(mouseEvent);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.checkMouseEvent(mouseEvent);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.checkMouseEvent(mouseEvent);
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.checkMouseEvent(mouseEvent);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.checkMouseEvent(mouseEvent);
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.checkMouseEvent(mouseEvent);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.checkMouseEvent(mouseEvent);
    }
    
    public void mousePressed() {
    }
    
    public void mouseReleased() {
    }
    
    public void mouseClicked() {
    }
    
    public void mouseDragged() {
    }
    
    public void mouseMoved() {
    }
    
    protected void enqueueKeyEvent(final KeyEvent p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: goto            6
        //     3: aload_2        
        //     4: monitorexit    
        //     5: athrow         
        //     6: aload_0        
        //     7: getfield        processing/core/PApplet.keyEventQueue:[Ljava/awt/event/KeyEvent;
        //    10: dup            
        //    11: astore_2       
        //    12: monitorenter   
        //    13: aload_0        
        //    14: getfield        processing/core/PApplet.keyEventCount:I
        //    17: aload_0        
        //    18: getfield        processing/core/PApplet.keyEventQueue:[Ljava/awt/event/KeyEvent;
        //    21: arraylength    
        //    22: if_icmpne       57
        //    25: aload_0        
        //    26: getfield        processing/core/PApplet.keyEventCount:I
        //    29: iconst_1       
        //    30: ishl           
        //    31: anewarray       Ljava/awt/event/KeyEvent;
        //    34: astore          4
        //    36: aload_0        
        //    37: getfield        processing/core/PApplet.keyEventQueue:[Ljava/awt/event/KeyEvent;
        //    40: iconst_0       
        //    41: aload           4
        //    43: iconst_0       
        //    44: aload_0        
        //    45: getfield        processing/core/PApplet.keyEventCount:I
        //    48: invokestatic    java/lang/System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //    51: aload_0        
        //    52: aload           4
        //    54: putfield        processing/core/PApplet.keyEventQueue:[Ljava/awt/event/KeyEvent;
        //    57: aload_0        
        //    58: getfield        processing/core/PApplet.keyEventQueue:[Ljava/awt/event/KeyEvent;
        //    61: aload_0        
        //    62: dup            
        //    63: getfield        processing/core/PApplet.keyEventCount:I
        //    66: dup_x1         
        //    67: iconst_1       
        //    68: iadd           
        //    69: putfield        processing/core/PApplet.keyEventCount:I
        //    72: aload_1        
        //    73: aastore        
        //    74: aload_2        
        //    75: monitorexit    
        //    76: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  3      5      3      6      Any
        //  13     76     3      6      Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected void dequeueKeyEvents() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: goto            6
        //     3: aload_1        
        //     4: monitorexit    
        //     5: athrow         
        //     6: aload_0        
        //     7: getfield        processing/core/PApplet.keyEventQueue:[Ljava/awt/event/KeyEvent;
        //    10: dup            
        //    11: astore_1       
        //    12: monitorenter   
        //    13: iconst_0       
        //    14: istore_3       
        //    15: goto            39
        //    18: aload_0        
        //    19: aload_0        
        //    20: getfield        processing/core/PApplet.keyEventQueue:[Ljava/awt/event/KeyEvent;
        //    23: iload_3        
        //    24: aaload         
        //    25: putfield        processing/core/PApplet.keyEvent:Ljava/awt/event/KeyEvent;
        //    28: aload_0        
        //    29: aload_0        
        //    30: getfield        processing/core/PApplet.keyEvent:Ljava/awt/event/KeyEvent;
        //    33: invokevirtual   processing/core/PApplet.handleKeyEvent:(Ljava/awt/event/KeyEvent;)V
        //    36: iinc            3, 1
        //    39: iload_3        
        //    40: aload_0        
        //    41: getfield        processing/core/PApplet.keyEventCount:I
        //    44: if_icmplt       18
        //    47: aload_0        
        //    48: iconst_0       
        //    49: putfield        processing/core/PApplet.keyEventCount:I
        //    52: aload_1        
        //    53: monitorexit    
        //    54: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  3      5      3      6      Any
        //  13     54     3      6      Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected void handleKeyEvent(final KeyEvent keyEvent) {
        this.keyEvent = keyEvent;
        this.key = keyEvent.getKeyChar();
        this.keyCode = keyEvent.getKeyCode();
        this.keyEventMethods.handle(new Object[] { keyEvent });
        switch (keyEvent.getID()) {
            case 401: {
                this.keyPressed = true;
                this.keyPressed();
                break;
            }
            case 402: {
                this.keyPressed = false;
                this.keyReleased();
                break;
            }
            case 400: {
                this.keyTyped();
                break;
            }
        }
    }
    
    protected void checkKeyEvent(final KeyEvent keyEvent) {
        if (this.looping) {
            this.enqueueKeyEvent(keyEvent);
        }
        else {
            this.handleKeyEvent(keyEvent);
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        this.checkKeyEvent(keyEvent);
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        this.checkKeyEvent(keyEvent);
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        this.checkKeyEvent(keyEvent);
    }
    
    public void keyPressed() {
    }
    
    public void keyReleased() {
    }
    
    public void keyTyped() {
    }
    
    public void focusGained() {
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        this.focused = true;
        this.focusGained();
    }
    
    public void focusLost() {
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        this.focused = false;
        this.focusLost();
    }
    
    public int millis() {
        return (int)(System.currentTimeMillis() - this.millisOffset);
    }
    
    public static int second() {
        return Calendar.getInstance().get(13);
    }
    
    public static int minute() {
        return Calendar.getInstance().get(12);
    }
    
    public static int hour() {
        return Calendar.getInstance().get(11);
    }
    
    public static int day() {
        return Calendar.getInstance().get(5);
    }
    
    public static int month() {
        return Calendar.getInstance().get(2) + 1;
    }
    
    public static int year() {
        return Calendar.getInstance().get(1);
    }
    
    public void delay(final int n) {
        if (this.frameCount == 0) {
            return;
        }
        if (n > 0) {
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void framerate(final float framerateTarget) {
        this.framerateTarget = framerateTarget;
    }
    
    public String param(final String s) {
        if (this.online) {
            return this.getParameter(s);
        }
        System.err.println("param() only works inside a web browser");
        return null;
    }
    
    public void status(final String s) {
        if (this.online) {
            this.showStatus(s);
        }
        else {
            System.out.println(s);
        }
    }
    
    public void link(final String s) {
        this.link(s, null);
    }
    
    public void link(final String s, final String s2) {
        if (this.online) {
            try {
                if (s2 == null) {
                    this.getAppletContext().showDocument(new URL(s));
                }
                else {
                    this.getAppletContext().showDocument(new URL(s), s2);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                throw new RuntimeException("Could not open " + s);
            }
        }
        else {
            try {
                if (PApplet.platform == 1) {
                    Runtime.getRuntime().exec("cmd /c start " + s);
                }
                else {
                    if (PApplet.platform != 3) {
                        if (PApplet.platform != 2) {
                            throw new RuntimeException("Can't open URLs for this platform");
                        }
                    }
                    try {
                        final Class<?> forName = Class.forName("com.apple.mrj.MRJFileUtils");
                        final String s3 = "openURL";
                        final Class[] array = { null };
                        final int n = 0;
                        Class class$java$lang$String;
                        if ((class$java$lang$String = PApplet.class$java$lang$String) == null) {
                            class$java$lang$String = (PApplet.class$java$lang$String = class("[Ljava.lang.String;", false));
                        }
                        array[n] = class$java$lang$String;
                        forName.getMethod(s3, (Class[])array).invoke(null, s);
                    }
                    catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                }
            }
            catch (IOException ex3) {
                ex3.printStackTrace();
                throw new RuntimeException("Could not open " + s);
            }
        }
    }
    
    public void open(final String s) {
        if (PApplet.platform == 1) {
            try {
                Runtime.getRuntime().exec("cmd /c \"" + s + '\"');
            }
            catch (IOException ex) {
                ex.printStackTrace();
                throw new RuntimeException("Could not open " + s);
            }
        }
        else if (PApplet.platform == 3 || PApplet.platform == 2) {
            String s2 = "file://" + s;
            if (s2.indexOf(32) != -1) {
                final StringBuffer sb = new StringBuffer();
                final char[] charArray = s2.toCharArray();
                for (int i = 0; i < charArray.length; ++i) {
                    if (charArray[i] == ' ') {
                        sb.append("%20");
                    }
                    else {
                        sb.append(charArray[i]);
                    }
                }
                s2 = sb.toString();
            }
            this.link(s2);
        }
        else {
            this.open(new String[] { s });
        }
    }
    
    public Process open(final String[] array) {
        try {
            return Runtime.getRuntime().exec(array);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Could not open " + join(array, ' '));
        }
    }
    
    public void die(final String s) {
        this.stop();
        throw new RuntimeException(s);
    }
    
    public void die(final String s, final Exception ex) {
        if (ex != null) {
            ex.printStackTrace();
        }
        this.die(s);
    }
    
    public void exit() {
        this.stop();
    }
    
    public void save(final String s) {
        this.g.save(this.savePath(s));
    }
    
    public void saveFrame() {
        if (this.online) {
            System.err.println("Can't use saveFrame() when running in a browser.");
            return;
        }
        this.save("screen-" + nf(this.frameCount, 4) + ".tif");
    }
    
    public void saveFrame(final String s) {
        if (this.online) {
            System.err.println("Can't use saveFrame() when running in a browser.");
            return;
        }
        final int index = s.indexOf(35);
        final int lastIndex = s.lastIndexOf(35);
        if (index == -1) {
            this.save(s);
        }
        else {
            this.save(s.substring(0, index) + nf(this.frameCount, lastIndex - index + 1) + s.substring(lastIndex + 1));
        }
    }
    
    public void cursor(final int cursor_type) {
        this.setCursor(Cursor.getPredefinedCursor(cursor_type));
        this.cursor_visible = true;
        this.cursor_type = cursor_type;
    }
    
    public void cursor(final PImage pImage, final int n, final int n2) {
        if (PApplet.javaVersion < 1.2f) {
            System.err.println("Java 1.2 or higher is required to use cursor()");
            System.err.println("(You're using version " + PApplet.javaVersionName + ')');
            return;
        }
        final Image image = this.createImage(new MemoryImageSource(pImage.width, pImage.height, pImage.pixels, 0, pImage.width));
        final Point point = new Point(n, n2);
        try {
            Class class$java$awt$Toolkit;
            if ((class$java$awt$Toolkit = PApplet.class$java$awt$Toolkit) == null) {
                class$java$awt$Toolkit = (PApplet.class$java$awt$Toolkit = class("[Ljava.awt.Toolkit;", false));
            }
            final String s = "createCustomCursor";
            final Class[] array = new Class[3];
            final int n3 = 0;
            Class class$java$awt$Image;
            if ((class$java$awt$Image = PApplet.class$java$awt$Image) == null) {
                class$java$awt$Image = (PApplet.class$java$awt$Image = class("[Ljava.awt.Image;", false));
            }
            array[n3] = class$java$awt$Image;
            final int n4 = 1;
            Class class$java$awt$Point;
            if ((class$java$awt$Point = PApplet.class$java$awt$Point) == null) {
                class$java$awt$Point = (PApplet.class$java$awt$Point = class("[Ljava.awt.Point;", false));
            }
            array[n4] = class$java$awt$Point;
            final int n5 = 2;
            Class class$java$lang$String;
            if ((class$java$lang$String = PApplet.class$java$lang$String) == null) {
                class$java$lang$String = (PApplet.class$java$lang$String = class("[Ljava.lang.String;", false));
            }
            array[n5] = class$java$lang$String;
            this.setCursor((Cursor)class$java$awt$Toolkit.getMethod(s, (Class[])array).invoke(Toolkit.getDefaultToolkit(), image, point, "no cursor"));
            this.cursor_visible = true;
        }
        catch (NoSuchMethodError noSuchMethodError) {
            System.err.println("cursor() is not available when using Java " + PApplet.javaVersionName);
        }
        catch (IndexOutOfBoundsException ex2) {
            System.err.println("cursor() error: the hotspot " + point + " is out of bounds for the given image.");
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
    }
    
    public void cursor() {
        if (!this.cursor_visible) {
            this.cursor_visible = true;
            this.setCursor(Cursor.getPredefinedCursor(this.cursor_type));
        }
    }
    
    public void noCursor() {
        if (!this.cursor_visible) {
            return;
        }
        if (this.invisible_cursor == null) {
            this.invisible_cursor = new PImage(new int[256], 16, 16, 2);
        }
        this.cursor(this.invisible_cursor, 0, 0);
        this.cursor_visible = false;
    }
    
    public static void print(final byte b) {
        System.out.print(b);
        System.out.flush();
    }
    
    public static void print(final boolean b) {
        System.out.print(b);
        System.out.flush();
    }
    
    public static void print(final char c) {
        System.out.print(c);
        System.out.flush();
    }
    
    public static void print(final int n) {
        System.out.print(n);
        System.out.flush();
    }
    
    public static void print(final float n) {
        System.out.print(n);
        System.out.flush();
    }
    
    public static void print(final double n) {
        System.out.print(n);
        System.out.flush();
    }
    
    public static void print(final String s) {
        System.out.print(s);
        System.out.flush();
    }
    
    public static void print(final Object o) {
        if (o == null) {
            System.out.print("null");
        }
        else {
            final String name = o.getClass().getName();
            if (name.charAt(0) == '[') {
                switch (name.charAt(1)) {
                    case '[': {
                        System.out.print(o);
                        System.out.print(' ');
                        break;
                    }
                    case 'L': {
                        final Object[] array = (Object[])o;
                        for (int i = 0; i < array.length; ++i) {
                            System.out.print(array[i]);
                            System.out.print(' ');
                        }
                        break;
                    }
                    case 'Z': {
                        final boolean[] array2 = (boolean[])o;
                        for (int j = 0; j < array2.length; ++j) {
                            System.out.print(array2[j]);
                            System.out.print(' ');
                        }
                        break;
                    }
                    case 'B': {
                        final byte[] array3 = (byte[])o;
                        for (int k = 0; k < array3.length; ++k) {
                            System.out.print(array3[k]);
                            System.out.print(' ');
                        }
                        break;
                    }
                    case 'C': {
                        final char[] array4 = (char[])o;
                        for (int l = 0; l < array4.length; ++l) {
                            System.out.print(array4[l]);
                            System.out.print(' ');
                        }
                        break;
                    }
                    case 'I': {
                        final int[] array5 = (int[])o;
                        for (int n = 0; n < array5.length; ++n) {
                            System.out.print(array5[n]);
                            System.out.print(' ');
                        }
                        break;
                    }
                    case 'F': {
                        final float[] array6 = (float[])o;
                        for (int n2 = 0; n2 < array6.length; ++n2) {
                            System.out.print(array6[n2]);
                            System.out.print(' ');
                        }
                        break;
                    }
                    case 'D': {
                        final double[] array7 = (double[])o;
                        for (int n3 = 0; n3 < array7.length; ++n3) {
                            System.out.print(array7[n3]);
                            System.out.print(' ');
                        }
                        break;
                    }
                    default: {
                        System.out.print(o);
                        break;
                    }
                }
            }
            else {
                System.out.print(o);
            }
        }
    }
    
    public static void println() {
        System.out.println();
    }
    
    public static void println(final byte b) {
        print(b);
        System.out.println();
    }
    
    public static void println(final boolean b) {
        print(b);
        System.out.println();
    }
    
    public static void println(final char c) {
        print(c);
        System.out.println();
    }
    
    public static void println(final int n) {
        print(n);
        System.out.println();
    }
    
    public static void println(final float n) {
        print(n);
        System.out.println();
    }
    
    public static void println(final double n) {
        print(n);
        System.out.println();
    }
    
    public static void println(final String s) {
        print(s);
        System.out.println();
    }
    
    public static void println(final Object o) {
        if (o == null) {
            System.out.println("null");
        }
        else {
            final String name = o.getClass().getName();
            if (name.charAt(0) == '[') {
                switch (name.charAt(1)) {
                    case '[': {
                        System.out.println(o);
                        break;
                    }
                    case 'L': {
                        final Object[] array = (Object[])o;
                        for (int i = 0; i < array.length; ++i) {
                            System.out.println(array[i]);
                        }
                        break;
                    }
                    case 'Z': {
                        final boolean[] array2 = (boolean[])o;
                        for (int j = 0; j < array2.length; ++j) {
                            System.out.println(array2[j]);
                        }
                        break;
                    }
                    case 'B': {
                        final byte[] array3 = (byte[])o;
                        for (int k = 0; k < array3.length; ++k) {
                            System.out.println(array3[k]);
                        }
                        break;
                    }
                    case 'C': {
                        final char[] array4 = (char[])o;
                        for (int l = 0; l < array4.length; ++l) {
                            System.out.println(array4[l]);
                        }
                        break;
                    }
                    case 'I': {
                        final int[] array5 = (int[])o;
                        for (int n = 0; n < array5.length; ++n) {
                            System.out.println(array5[n]);
                        }
                        break;
                    }
                    case 'F': {
                        final float[] array6 = (float[])o;
                        for (int n2 = 0; n2 < array6.length; ++n2) {
                            System.out.println(array6[n2]);
                        }
                        break;
                    }
                    case 'D': {
                        final double[] array7 = (double[])o;
                        for (int n3 = 0; n3 < array7.length; ++n3) {
                            System.out.println(array7[n3]);
                        }
                        break;
                    }
                    default: {
                        System.out.println(o);
                        break;
                    }
                }
            }
            else {
                System.out.println(o);
            }
        }
    }
    
    public static final float abs(final float n) {
        return (n < 0.0f) ? (-n) : n;
    }
    
    public static final int abs(final int n) {
        return (n < 0) ? (-n) : n;
    }
    
    public static final float sq(final float n) {
        return n * n;
    }
    
    public static final float sqrt(final float n) {
        return (float)Math.sqrt(n);
    }
    
    public static final float log(final float n) {
        return (float)Math.log(n);
    }
    
    public static final float exp(final float n) {
        return (float)Math.exp(n);
    }
    
    public static final float pow(final float n, final float n2) {
        return (float)Math.pow(n, n2);
    }
    
    public static final float max(final float n, final float n2) {
        return Math.max(n, n2);
    }
    
    public static final float max(final float n, final float n2, final float n3) {
        return Math.max(n, Math.max(n2, n3));
    }
    
    public static final float min(final float n, final float n2) {
        return Math.min(n, n2);
    }
    
    public static final float min(final float n, final float n2, final float n3) {
        return Math.min(n, Math.min(n2, n3));
    }
    
    public static final float lerp(final float n, final float n2, final float n3) {
        return n + (n2 - n) * n3;
    }
    
    public static final float constrain(final float n, final float n2, final float n3) {
        return (n < n2) ? n2 : ((n > n3) ? n3 : n);
    }
    
    public static final int max(final int n, final int n2) {
        return (n > n2) ? n : n2;
    }
    
    public static final int max(final int n, final int n2, final int n3) {
        return (n > n2) ? ((n > n3) ? n : n3) : ((n2 > n3) ? n2 : n3);
    }
    
    public static final int min(final int n, final int n2) {
        return (n < n2) ? n : n2;
    }
    
    public static final int min(final int n, final int n2, final int n3) {
        return (n < n2) ? ((n < n3) ? n : n3) : ((n2 < n3) ? n2 : n3);
    }
    
    public static final int constrain(final int n, final int n2, final int n3) {
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
    
    public final float asin(final float n) {
        return (float)Math.asin(n);
    }
    
    public final float acos(final float n) {
        return (float)Math.acos(n);
    }
    
    public final float atan(final float n) {
        return (float)Math.atan(n);
    }
    
    public final float atan2(final float n, final float n2) {
        return (float)Math.atan2(n, n2);
    }
    
    public static final float degrees(final float n) {
        return n * 57.295776f;
    }
    
    public static final float radians(final float n) {
        return n * 0.017453292f;
    }
    
    public static final float ceil(final float n) {
        return (float)Math.ceil(n);
    }
    
    public static final float floor(final float n) {
        return (float)Math.floor(n);
    }
    
    public static final float round(final float n) {
        return Math.round(n);
    }
    
    public static final float mag(final float n, final float n2) {
        return (float)Math.sqrt(n * n + n2 * n2);
    }
    
    public static final float mag(final float n, final float n2, final float n3) {
        return (float)Math.sqrt(n * n + n2 * n2 + n3 * n3);
    }
    
    public static final float dist(final float n, final float n2, final float n3, final float n4) {
        return sqrt(sq(n3 - n) + sq(n4 - n2));
    }
    
    public static final float dist(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        return sqrt(sq(n4 - n) + sq(n5 - n2) + sq(n6 - n3));
    }
    
    public final float random(final float n) {
        if (n == 0.0f) {
            return 0.0f;
        }
        if (this.internalRandom == null) {
            this.internalRandom = new Random();
        }
        float n2;
        do {
            n2 = this.internalRandom.nextFloat() * n;
        } while (n2 == n);
        return n2;
    }
    
    public final float random(final float n, final float n2) {
        if (n >= n2) {
            return n;
        }
        return this.random(n2 - n) + n;
    }
    
    public final void randomSeed(final long seed) {
        if (this.internalRandom == null) {
            this.internalRandom = new Random();
        }
        this.internalRandom.setSeed(seed);
    }
    
    public float noise(final float n) {
        return this.noise(n, 0.0f, 0.0f);
    }
    
    public float noise(final float n, final float n2) {
        return this.noise(n, n2, 0.0f);
    }
    
    public float noise(float n, float n2, float n3) {
        if (this.perlin == null) {
            if (this.perlinRandom == null) {
                this.perlinRandom = new Random();
            }
            this.perlin = new float[4096];
            for (int i = 0; i < 4096; ++i) {
                this.perlin[i] = this.perlinRandom.nextFloat();
            }
            this.perlin_cosTable = PGraphics.cosLUT;
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
    
    public void noiseSeed(final long seed) {
        if (this.perlinRandom == null) {
            this.perlinRandom = new Random();
        }
        this.perlinRandom.setSeed(seed);
    }
    
    public PSound loadSound(final String s) {
        if (PApplet.javaVersion >= 1.3f) {
            return new PSound2(this, this.openStream(s));
        }
        return new PSound(this, this.openStream(s));
    }
    
    public PImage loadImage(final String s) {
        if (s.toLowerCase().endsWith(".tga")) {
            return this.loadImageTGA(s);
        }
        return this.loadImage(s, true);
    }
    
    public PImage loadImage(final String s, final boolean b) {
        final Image image = Toolkit.getDefaultToolkit().createImage(this.loadBytes(s));
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        final PImage pImage = new PImage(image);
        if (s.toLowerCase().endsWith(".gif")) {
            for (int i = 0; i < pImage.pixels.length; ++i) {
                if ((pImage.pixels[i] & 0xFF000000) != 0xFF000000) {
                    pImage.format = 2;
                }
            }
        }
        return pImage;
    }
    
    protected PImage loadImageTGA(final String s) {
        final byte[] loadBytes = this.loadBytes(s);
        if (loadBytes[2] == 2 && loadBytes[17] == 8) {
            final int n = ((loadBytes[13] & 0xFF) << 8) + (loadBytes[12] & 0xFF);
            final int n2 = ((loadBytes[15] & 0xFF) << 8) + (loadBytes[14] & 0xFF);
            int n3 = 0;
            if (loadBytes[16] == 32) {
                n3 = 1;
            }
            final int n4 = n3;
            final PImage pImage = new PImage(n, n2);
            pImage.format = n4 + 1;
            int n5 = (n2 - 1) * n;
            int n6 = 18;
            for (int i = n2 - 1; i >= 0; --i) {
                for (int j = 0; j < n; ++j) {
                    pImage.pixels[n5 + j] = ((loadBytes[n6++] & 0xFF) | (loadBytes[n6++] & 0xFF) << 8 | (loadBytes[n6++] & 0xFF) << 16 | ((n4 != 0) ? ((loadBytes[n6++] & 0xFF) << 24) : -16777216));
                }
                n5 -= n;
            }
            return pImage;
        }
        this.die("loadImage(): bad targa image format");
        return null;
    }
    
    public PFont loadFont(final String s) {
        try {
            final String lowerCase = s.toLowerCase();
            InputStream openStream = this.openStream(s);
            if (lowerCase.endsWith(".vlw.gz")) {
                openStream = new GZIPInputStream(openStream);
            }
            else if (!lowerCase.endsWith(".vlw")) {
                throw new IOException("I don't know how to load a font named " + s);
            }
            return new PFont(openStream);
        }
        catch (Exception ex) {
            this.die("Could not load font " + s + "\nMake sure that the font has been copied\nto the data folder of your sketch.", ex);
            return null;
        }
    }
    
    public PFont createFont(final String s, final float n) {
        return this.createFont(s, n, PFont.DEFAULT_CHARSET, true);
    }
    
    public PFont createFont(final String s, final float n, final char[] array) {
        return this.createFont(s, n, array, true);
    }
    
    public PFont createFont(final String s, final float n, final boolean b) {
        return this.createFont(s, n, PFont.DEFAULT_CHARSET, b);
    }
    
    public PFont createFont(final String s, final float n, final char[] array, final boolean b) {
        if (PApplet.javaVersion < 1.3f) {
            throw new RuntimeException("Can only create fonts with Java 1.3 or higher");
        }
        final String lowerCase = s.toLowerCase();
        final Object o = null;
        Font font;
        try {
            Class class$java$awt$Font;
            if ((class$java$awt$Font = PApplet.class$java$awt$Font) == null) {
                class$java$awt$Font = (PApplet.class$java$awt$Font = class("[Ljava.awt.Font;", false));
            }
            final Method method = class$java$awt$Font.getMethod("deriveFont", Float.TYPE);
            final Float n2 = new Float(n);
            if (lowerCase.endsWith(".otf") || lowerCase.endsWith(".ttf")) {
                Class class$java$awt$Font2;
                if ((class$java$awt$Font2 = PApplet.class$java$awt$Font) == null) {
                    class$java$awt$Font2 = (PApplet.class$java$awt$Font = class("[Ljava.awt.Font;", false));
                }
                final String s2 = "createFont";
                final Class[] array2 = { Integer.TYPE, null };
                final int n3 = 1;
                Class class$java$io$InputStream;
                if ((class$java$io$InputStream = PApplet.class$java$io$InputStream) == null) {
                    class$java$io$InputStream = (PApplet.class$java$io$InputStream = class("[Ljava.io.InputStream;", false));
                }
                array2[n3] = class$java$io$InputStream;
                final Method method2 = class$java$awt$Font2.getMethod(s2, (Class[])array2);
                Class class$java$awt$Font3;
                if ((class$java$awt$Font3 = PApplet.class$java$awt$Font) == null) {
                    class$java$awt$Font3 = (PApplet.class$java$awt$Font = class("[Ljava.awt.Font;", false));
                }
                final Field field = class$java$awt$Font3.getField("TRUETYPE_FONT");
                font = (Font)method.invoke(method2.invoke(s, new Integer(field.getInt(field)), this.openStream(s)), n2);
            }
            else {
                final Font font2 = new Font(s, 0, 1);
                font = (Font)method.invoke(o, n2);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Problem using createFont() with the file " + s);
        }
        return new PFont(font, array, b);
    }
    
    public File inputFile() {
        return this.inputFile("Select a file...");
    }
    
    public File inputFile(final String s) {
        Frame frame = null;
        for (Container container = this.getParent(); container != null; container = container.getParent()) {
            if (container instanceof Frame) {
                frame = (Frame)container;
                break;
            }
        }
        return inputFile(s, frame);
    }
    
    public static File inputFile(final Frame frame) {
        return inputFile("Select a file...", frame);
    }
    
    public static File inputFile(final String s, Frame frame) {
        if (frame == null) {
            frame = new Frame();
        }
        final FileDialog fileDialog = new FileDialog(frame, s, 0);
        fileDialog.show();
        final String directory = fileDialog.getDirectory();
        final String file = fileDialog.getFile();
        if (file == null) {
            return null;
        }
        return new File(directory, file);
    }
    
    public File outputFile() {
        return this.outputFile("Save as...");
    }
    
    public File outputFile(final String s) {
        for (Container container = this.getParent(); container != null; container = container.getParent()) {
            if (container instanceof Frame) {
                final Frame frame = (Frame)container;
                break;
            }
        }
        return outputFile(s, this.frame);
    }
    
    public static File outputFile(final Frame frame) {
        return outputFile("Save as...", frame);
    }
    
    public static File outputFile(final String s, Frame frame) {
        if (frame == null) {
            frame = new Frame();
        }
        final FileDialog fileDialog = new FileDialog(frame, s, 1);
        fileDialog.show();
        final String directory = fileDialog.getDirectory();
        final String file = fileDialog.getFile();
        if (file == null) {
            return null;
        }
        return new File(directory, file);
    }
    
    public BufferedReader reader(final String s) {
        try {
            return reader(this.openStream(s));
        }
        catch (Exception ex) {
            if (s == null) {
                this.die("Filename passed to reader() was null", ex);
            }
            else {
                this.die("Couldn't create a reader for " + s, ex);
            }
            return null;
        }
    }
    
    public static BufferedReader reader(final File file) {
        try {
            return reader(new FileInputStream(file));
        }
        catch (Exception ex) {
            if (file == null) {
                throw new RuntimeException("File passed to reader() was null");
            }
            ex.printStackTrace();
            throw new RuntimeException("Couldn't create a reader for " + file.getAbsolutePath());
        }
    }
    
    public static BufferedReader reader(final InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream));
    }
    
    public static InputStream gzipInput(final InputStream inputStream) {
        try {
            return new GZIPInputStream(inputStream);
        }
        catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Problem with gzip input");
        }
    }
    
    public static OutputStream gzipOutput(final OutputStream outputStream) {
        try {
            return new GZIPOutputStream(outputStream);
        }
        catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Problem with gzip output");
        }
    }
    
    public PrintWriter writer(final String s) {
        try {
            return writer(new FileOutputStream(this.savePath(s)));
        }
        catch (Exception ex) {
            if (s == null) {
                this.die("Filename passed to writer() was null", ex);
            }
            else {
                this.die("Couldn't create a writer for " + s, ex);
            }
            return null;
        }
    }
    
    public static PrintWriter writer(final File file) {
        try {
            return writer(new FileOutputStream(file));
        }
        catch (Exception ex) {
            if (file == null) {
                throw new RuntimeException("File passed to writer() was null");
            }
            ex.printStackTrace();
            throw new RuntimeException("Couldn't create a writer for " + file.getAbsolutePath());
        }
    }
    
    public static PrintWriter writer(final OutputStream outputStream) {
        return new PrintWriter(new OutputStreamWriter(outputStream));
    }
    
    public static InputStream openStream(final File file) {
        try {
            return new FileInputStream(file);
        }
        catch (IOException ex) {
            if (file == null) {
                throw new RuntimeException("File passed to openStream() was null");
            }
            ex.printStackTrace();
            throw new RuntimeException("Couldn't openStream() for " + file.getAbsolutePath());
        }
    }
    
    public InputStream openStream(final String s) {
        try {
            return new URL(s).openStream();
        }
        catch (MalformedURLException ex2) {
            if (!this.online) {
                try {
                    final File file = new File(this.folder + File.separator + "data", s);
                    try {
                        final String name = new File(file.getCanonicalPath()).getName();
                        if (!name.equals(s)) {
                            throw new RuntimeException("This file is named " + name + " not " + s + '.');
                        }
                    }
                    catch (Exception ex3) {}
                    final FileInputStream fileInputStream = new FileInputStream(file);
                    if (fileInputStream != null) {
                        return fileInputStream;
                    }
                }
                catch (Exception ex4) {}
            }
            try {
                final InputStream resourceAsStream = this.getClass().getResourceAsStream(s);
                if (resourceAsStream != null) {
                    return resourceAsStream;
                }
                InputStream resourceAsStream2 = this.getClass().getResourceAsStream("data/" + s);
                if (resourceAsStream2 != null) {
                    return resourceAsStream2;
                }
                try {
                    try {
                        resourceAsStream2 = new FileInputStream(new File(this.folder, s));
                        if (resourceAsStream2 != null) {
                            return resourceAsStream2;
                        }
                    }
                    catch (Exception ex5) {}
                    try {
                        resourceAsStream2 = new FileInputStream(new File("data", s));
                        if (resourceAsStream2 != null) {
                            return resourceAsStream2;
                        }
                    }
                    catch (IOException ex6) {}
                    try {
                        resourceAsStream2 = new FileInputStream(s);
                        if (resourceAsStream2 != null) {
                            return resourceAsStream2;
                        }
                    }
                    catch (IOException ex7) {}
                }
                catch (SecurityException ex8) {}
                if (resourceAsStream2 == null) {
                    throw new IOException("openStream() could not open " + s);
                }
            }
            catch (Exception ex) {
                this.die(ex.getMessage(), ex);
            }
            return null;
        }
        catch (IOException ex9) {}
    }
    
    public byte[] loadBytes(final String s) {
        return loadBytes(this.openStream(s));
    }
    
    public static byte[] loadBytes(final InputStream inputStream) {
        try {
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            for (int i = bufferedInputStream.read(); i != -1; i = bufferedInputStream.read()) {
                byteArrayOutputStream.write(i);
            }
            return byteArrayOutputStream.toByteArray();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Couldn't load bytes from stream");
        }
    }
    
    public static String[] loadStrings(final File file) {
        final InputStream openStream = openStream(file);
        if (openStream != null) {
            return loadStrings(openStream);
        }
        return null;
    }
    
    public String[] loadStrings(final String s) {
        final InputStream openStream = this.openStream(s);
        if (openStream != null) {
            return loadStrings(openStream);
        }
        this.die("Couldn't open " + s);
        return null;
    }
    
    public static String[] loadStrings(final InputStream inputStream) {
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String[] array = new String[100];
            int n = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (n == array.length) {
                    final String[] array2 = new String[n << 1];
                    System.arraycopy(array, 0, array2, 0, n);
                    array = array2;
                }
                array[n++] = line;
            }
            bufferedReader.close();
            if (n == array.length) {
                return array;
            }
            final String[] array3 = new String[n];
            System.arraycopy(array, 0, array3, 0, n);
            return array3;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error inside loadStrings()");
        }
    }
    
    public void saveBytes(final String s, final byte[] array) {
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(this.savePath(s));
            saveBytes(fileOutputStream, array);
            fileOutputStream.close();
        }
        catch (IOException ex) {
            System.err.println("error saving bytes to " + s);
            ex.printStackTrace();
        }
    }
    
    public static void saveBytes(final File file, final byte[] array) {
        try {
            createPath(file.getAbsolutePath());
            final FileOutputStream fileOutputStream = new FileOutputStream(file);
            saveBytes(fileOutputStream, array);
            fileOutputStream.close();
        }
        catch (IOException ex) {
            System.err.println("error saving bytes to " + file);
            ex.printStackTrace();
        }
    }
    
    public static void saveBytes(final OutputStream outputStream, final byte[] array) {
        try {
            outputStream.write(array);
            outputStream.flush();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Couldn't save bytes");
        }
    }
    
    public void saveStrings(final String s, final String[] array) {
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(this.savePath(s));
            saveStrings(fileOutputStream, array);
            fileOutputStream.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("saveStrings() failed: " + ex.getMessage());
        }
    }
    
    public static void saveStrings(final File file, final String[] array) {
        try {
            final String absolutePath = file.getAbsolutePath();
            createPath(absolutePath);
            final FileOutputStream fileOutputStream = new FileOutputStream(absolutePath);
            saveStrings(fileOutputStream, array);
            fileOutputStream.close();
        }
        catch (IOException ex) {
            System.err.println("error while saving strings");
            ex.printStackTrace();
        }
    }
    
    public static void saveStrings(final OutputStream outputStream, final String[] array) {
        final PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream));
        for (int i = 0; i < array.length; ++i) {
            printWriter.println(array[i]);
        }
        printWriter.flush();
    }
    
    public String savePath(final String s) {
        final String string = this.folder + File.separator + s;
        createPath(string);
        return string;
    }
    
    public static void createPath(final String s) {
        final String parent = new File(s).getParent();
        if (parent != null) {
            final File file = new File(parent);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
    }
    
    public byte[] sort(final byte[] array) {
        return this.sort(array, array.length);
    }
    
    public char[] sort(final char[] array) {
        return this.sort(array, array.length);
    }
    
    public int[] sort(final int[] array) {
        return this.sort(array, array.length);
    }
    
    public float[] sort(final float[] array) {
        return this.sort(array, array.length);
    }
    
    public String[] sort(final String[] array) {
        return this.sort(array, array.length);
    }
    
    public byte[] sort(final byte[] array, final int n) {
        if (n == 0) {
            return null;
        }
        this.sort_mode = 1;
        System.arraycopy(array, 0, this.sort_bytes = new byte[n], 0, n);
        this.sort_internal(0, n - 1);
        return this.sort_bytes;
    }
    
    public char[] sort(final char[] array, final int n) {
        if (n == 0) {
            return null;
        }
        this.sort_mode = 2;
        System.arraycopy(array, 0, this.sort_chars = new char[n], 0, n);
        this.sort_internal(0, n - 1);
        return this.sort_chars;
    }
    
    public int[] sort(final int[] array, final int n) {
        if (n == 0) {
            return null;
        }
        this.sort_mode = 3;
        System.arraycopy(array, 0, this.sort_ints = new int[n], 0, n);
        this.sort_internal(0, n - 1);
        return this.sort_ints;
    }
    
    public float[] sort(final float[] array, final int n) {
        if (n == 0) {
            return null;
        }
        this.sort_mode = 4;
        System.arraycopy(array, 0, this.sort_floats = new float[n], 0, n);
        this.sort_internal(0, n - 1);
        return this.sort_floats;
    }
    
    public String[] sort(final String[] array, final int n) {
        if (n == 0) {
            return null;
        }
        this.sort_mode = 5;
        System.arraycopy(array, 0, this.sort_strings = new String[n], 0, n);
        this.sort_internal(0, n - 1);
        return this.sort_strings;
    }
    
    protected void sort_internal(final int n, final int n2) {
        this.sort_swap((n + n2) / 2, n2);
        final int sort_partition = this.sort_partition(n - 1, n2);
        this.sort_swap(sort_partition, n2);
        if (sort_partition - n > 1) {
            this.sort_internal(n, sort_partition - 1);
        }
        if (n2 - sort_partition > 1) {
            this.sort_internal(sort_partition + 1, n2);
        }
    }
    
    protected int sort_partition(int n, int n2) {
        while (true) {
            if (this.sort_compare(++n, n2) >= 0) {
                while (n2 != 0 && this.sort_compare(--n2, n2) > 0) {}
                this.sort_swap(n, n2);
                if (n >= n2) {
                    break;
                }
                continue;
            }
        }
        this.sort_swap(n, n2);
        return n;
    }
    
    protected void sort_swap(final int n, final int n2) {
        switch (this.sort_mode) {
            case 1: {
                final byte b = this.sort_bytes[n];
                this.sort_bytes[n] = this.sort_bytes[n2];
                this.sort_bytes[n2] = b;
                break;
            }
            case 2: {
                final char c = this.sort_chars[n];
                this.sort_chars[n] = this.sort_chars[n2];
                this.sort_chars[n2] = c;
                break;
            }
            case 3: {
                final int n3 = this.sort_ints[n];
                this.sort_ints[n] = this.sort_ints[n2];
                this.sort_ints[n2] = n3;
                break;
            }
            case 4: {
                final float n4 = this.sort_floats[n];
                this.sort_floats[n] = this.sort_floats[n2];
                this.sort_floats[n2] = n4;
                break;
            }
            case 5: {
                final String s = this.sort_strings[n];
                this.sort_strings[n] = this.sort_strings[n2];
                this.sort_strings[n2] = s;
                break;
            }
        }
    }
    
    protected int sort_compare(final int n, final int n2) {
        switch (this.sort_mode) {
            case 1: {
                return this.sort_bytes[n] - this.sort_bytes[n2];
            }
            case 2: {
                return this.sort_chars[n] - this.sort_chars[n2];
            }
            case 3: {
                return this.sort_ints[n] - this.sort_ints[n2];
            }
            case 4: {
                if (this.sort_floats[n] < this.sort_floats[n2]) {
                    return -1;
                }
                final int n3 = 1;
                int n4 = 0;
                if (this.sort_floats[n] == this.sort_floats[n2]) {
                    n4 = 1;
                }
                return n3 - n4;
            }
            case 5: {
                return this.sort_strings[n].compareTo(this.sort_strings[n2]);
            }
            default: {
                return 0;
            }
        }
    }
    
    public static boolean[] expand(final boolean[] array) {
        return expand(array, array.length << 1);
    }
    
    public static boolean[] expand(final boolean[] array, final int n) {
        final boolean[] array2 = new boolean[n];
        System.arraycopy(array, 0, array2, 0, Math.min(n, array.length));
        return array2;
    }
    
    public static byte[] expand(final byte[] array) {
        return expand(array, array.length << 1);
    }
    
    public static byte[] expand(final byte[] array, final int n) {
        final byte[] array2 = new byte[n];
        System.arraycopy(array, 0, array2, 0, Math.min(n, array.length));
        return array2;
    }
    
    public static char[] expand(final char[] array) {
        return expand(array, array.length << 1);
    }
    
    public static char[] expand(final char[] array, final int n) {
        final char[] array2 = new char[n];
        System.arraycopy(array, 0, array2, 0, Math.min(n, array.length));
        return array2;
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
    
    public static Object expand(final Object o) {
        return expand(o, Array.getLength(o) << 1);
    }
    
    public static Object expand(final Object o, final int n) {
        final Object instance = Array.newInstance(o.getClass().getComponentType(), n);
        System.arraycopy(o, 0, instance, 0, Math.min(Array.getLength(o), n));
        return instance;
    }
    
    public static boolean[] contract(final boolean[] array, final int n) {
        return expand(array, n);
    }
    
    public static byte[] contract(final byte[] array, final int n) {
        return expand(array, n);
    }
    
    public static char[] contract(final char[] array, final int n) {
        return expand(array, n);
    }
    
    public static int[] contract(final int[] array, final int n) {
        return expand(array, n);
    }
    
    public static float[] contract(final float[] array, final int n) {
        return expand(array, n);
    }
    
    public static String[] contract(final String[] array, final int n) {
        return expand(array, n);
    }
    
    public static Object contract(final Object o, final int n) {
        return expand(o, n);
    }
    
    public static byte[] append(byte[] expand, final byte b) {
        expand = expand(expand, expand.length + 1);
        expand[expand.length - 1] = b;
        return expand;
    }
    
    public static char[] append(char[] expand, final char c) {
        expand = expand(expand, expand.length + 1);
        expand[expand.length - 1] = c;
        return expand;
    }
    
    public static int[] append(int[] expand, final int n) {
        expand = expand(expand, expand.length + 1);
        expand[expand.length - 1] = n;
        return expand;
    }
    
    public static float[] append(float[] expand, final float n) {
        expand = expand(expand, expand.length + 1);
        expand[expand.length - 1] = n;
        return expand;
    }
    
    public static String[] append(String[] expand, final String s) {
        expand = expand(expand, expand.length + 1);
        expand[expand.length - 1] = s;
        return expand;
    }
    
    public static boolean[] shorten(final boolean[] array) {
        return contract(array, array.length - 1);
    }
    
    public static byte[] shorten(final byte[] array) {
        return contract(array, array.length - 1);
    }
    
    public static char[] shorten(final char[] array) {
        return contract(array, array.length - 1);
    }
    
    public static int[] shorten(final int[] array) {
        return contract(array, array.length - 1);
    }
    
    public static float[] shorten(final float[] array) {
        return contract(array, array.length - 1);
    }
    
    public static String[] shorten(final String[] array) {
        return contract(array, array.length - 1);
    }
    
    public static final boolean[] splice(final boolean[] array, final boolean b, final int n) {
        final boolean[] array2 = new boolean[array.length + 1];
        System.arraycopy(array, 0, array2, 0, n);
        array2[n] = b;
        System.arraycopy(array, n, array2, n + 1, array.length - n);
        return array2;
    }
    
    public static final boolean[] splice(final boolean[] array, final boolean[] array2, final int n) {
        final boolean[] array3 = new boolean[array.length + array2.length];
        System.arraycopy(array, 0, array3, 0, n);
        System.arraycopy(array2, 0, array3, n, array2.length);
        System.arraycopy(array, n, array3, n + array2.length, array.length - n);
        return array3;
    }
    
    public static final byte[] splice(final byte[] array, final byte b, final int n) {
        final byte[] array2 = new byte[array.length + 1];
        System.arraycopy(array, 0, array2, 0, n);
        array2[n] = b;
        System.arraycopy(array, n, array2, n + 1, array.length - n);
        return array2;
    }
    
    public static final byte[] splice(final byte[] array, final byte[] array2, final int n) {
        final byte[] array3 = new byte[array.length + array2.length];
        System.arraycopy(array, 0, array3, 0, n);
        System.arraycopy(array2, 0, array3, n, array2.length);
        System.arraycopy(array, n, array3, n + array2.length, array.length - n);
        return array3;
    }
    
    public static final char[] splice(final char[] array, final char c, final int n) {
        final char[] array2 = new char[array.length + 1];
        System.arraycopy(array, 0, array2, 0, n);
        array2[n] = c;
        System.arraycopy(array, n, array2, n + 1, array.length - n);
        return array2;
    }
    
    public static final char[] splice(final char[] array, final char[] array2, final int n) {
        final char[] array3 = new char[array.length + array2.length];
        System.arraycopy(array, 0, array3, 0, n);
        System.arraycopy(array2, 0, array3, n, array2.length);
        System.arraycopy(array, n, array3, n + array2.length, array.length - n);
        return array3;
    }
    
    public static final int[] splice(final int[] array, final int n, final int n2) {
        final int[] array2 = new int[array.length + 1];
        System.arraycopy(array, 0, array2, 0, n2);
        array2[n2] = n;
        System.arraycopy(array, n2, array2, n2 + 1, array.length - n2);
        return array2;
    }
    
    public static final int[] splice(final int[] array, final int[] array2, final int n) {
        final int[] array3 = new int[array.length + array2.length];
        System.arraycopy(array, 0, array3, 0, n);
        System.arraycopy(array2, 0, array3, n, array2.length);
        System.arraycopy(array, n, array3, n + array2.length, array.length - n);
        return array3;
    }
    
    public static final float[] splice(final float[] array, final float n, final int n2) {
        final float[] array2 = new float[array.length + 1];
        System.arraycopy(array, 0, array2, 0, n2);
        array2[n2] = n;
        System.arraycopy(array, n2, array2, n2 + 1, array.length - n2);
        return array2;
    }
    
    public static final float[] splice(final float[] array, final float[] array2, final int n) {
        final float[] array3 = new float[array.length + array2.length];
        System.arraycopy(array, 0, array3, 0, n);
        System.arraycopy(array2, 0, array3, n, array2.length);
        System.arraycopy(array, n, array3, n + array2.length, array.length - n);
        return array3;
    }
    
    public static final String[] splice(final String[] array, final String s, final int n) {
        final String[] array2 = new String[array.length + 1];
        System.arraycopy(array, 0, array2, 0, n);
        array2[n] = s;
        System.arraycopy(array, n, array2, n + 1, array.length - n);
        return array2;
    }
    
    public static final String[] splice(final String[] array, final String[] array2, final int n) {
        final String[] array3 = new String[array.length + array2.length];
        System.arraycopy(array, 0, array3, 0, n);
        System.arraycopy(array2, 0, array3, n, array2.length);
        System.arraycopy(array, n, array3, n + array2.length, array.length - n);
        return array3;
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
    
    public static boolean[] concat(final boolean[] array, final boolean[] array2) {
        final boolean[] array3 = new boolean[array.length + array2.length];
        System.arraycopy(array, 0, array3, 0, array.length);
        System.arraycopy(array2, 0, array3, array.length, array2.length);
        return array3;
    }
    
    public static byte[] concat(final byte[] array, final byte[] array2) {
        final byte[] array3 = new byte[array.length + array2.length];
        System.arraycopy(array, 0, array3, 0, array.length);
        System.arraycopy(array2, 0, array3, array.length, array2.length);
        return array3;
    }
    
    public static char[] concat(final char[] array, final char[] array2) {
        final char[] array3 = new char[array.length + array2.length];
        System.arraycopy(array, 0, array3, 0, array.length);
        System.arraycopy(array2, 0, array3, array.length, array2.length);
        return array3;
    }
    
    public static int[] concat(final int[] array, final int[] array2) {
        final int[] array3 = new int[array.length + array2.length];
        System.arraycopy(array, 0, array3, 0, array.length);
        System.arraycopy(array2, 0, array3, array.length, array2.length);
        return array3;
    }
    
    public static float[] concat(final float[] array, final float[] array2) {
        final float[] array3 = new float[array.length + array2.length];
        System.arraycopy(array, 0, array3, 0, array.length);
        System.arraycopy(array2, 0, array3, array.length, array2.length);
        return array3;
    }
    
    public static String[] concat(final String[] array, final String[] array2) {
        final String[] array3 = new String[array.length + array2.length];
        System.arraycopy(array, 0, array3, 0, array.length);
        System.arraycopy(array2, 0, array3, array.length, array2.length);
        return array3;
    }
    
    public static boolean[] reverse(final boolean[] array) {
        final boolean[] array2 = new boolean[array.length];
        final int n = array.length - 1;
        for (int i = 0; i < array.length; ++i) {
            array2[i] = array[n - i];
        }
        return array2;
    }
    
    public static byte[] reverse(final byte[] array) {
        final byte[] array2 = new byte[array.length];
        final int n = array.length - 1;
        for (int i = 0; i < array.length; ++i) {
            array2[i] = array[n - i];
        }
        return array2;
    }
    
    public static char[] reverse(final char[] array) {
        final char[] array2 = new char[array.length];
        final int n = array.length - 1;
        for (int i = 0; i < array.length; ++i) {
            array2[i] = array[n - i];
        }
        return array2;
    }
    
    public static int[] reverse(final int[] array) {
        final int[] array2 = new int[array.length];
        final int n = array.length - 1;
        for (int i = 0; i < array.length; ++i) {
            array2[i] = array[n - i];
        }
        return array2;
    }
    
    public static float[] reverse(final float[] array) {
        final float[] array2 = new float[array.length];
        final int n = array.length - 1;
        for (int i = 0; i < array.length; ++i) {
            array2[i] = array[n - i];
        }
        return array2;
    }
    
    public static String[] reverse(final String[] array) {
        final String[] array2 = new String[array.length];
        final int n = array.length - 1;
        for (int i = 0; i < array.length; ++i) {
            array2[i] = array[n - i];
        }
        return array2;
    }
    
    public static String trim(final String s) {
        return s.replace(' ', ' ').trim();
    }
    
    public static String join(final String[] array, final char c) {
        return join(array, String.valueOf(c));
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
    
    public static String[] split(final String s) {
        return split(s, " \t\n\r\f ");
    }
    
    public static String[] split(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final String[] array = new String[stringTokenizer.countTokens()];
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            array[n++] = stringTokenizer.nextToken();
        }
        return array;
    }
    
    public static String[] split(final String s, final char c) {
        if (s == null) {
            return null;
        }
        final char[] charArray = s.toCharArray();
        int n = 0;
        for (int i = 0; i < charArray.length; ++i) {
            if (charArray[i] == c) {
                ++n;
            }
        }
        if (n == 0) {
            return new String[] { new String(s) };
        }
        final String[] array = new String[n + 1];
        int n2 = 0;
        int n3 = 0;
        for (int j = 0; j < charArray.length; ++j) {
            if (charArray[j] == c) {
                array[n2++] = new String(charArray, n3, j - n3);
                n3 = j + 1;
            }
        }
        array[n2] = new String(charArray, n3, charArray.length - n3);
        return array;
    }
    
    public static final boolean toBoolean(final char c) {
        boolean b = false;
        if (c == 't' || c == 'T' || c == '1') {
            b = true;
        }
        return b;
    }
    
    public static final boolean toBoolean(final int n) {
        boolean b = false;
        if (n != 0) {
            b = true;
        }
        return b;
    }
    
    public static final boolean toBoolean(final float n) {
        boolean b = false;
        if (n != 0.0f) {
            b = true;
        }
        return b;
    }
    
    public static final boolean toBoolean(final String s) {
        return new Boolean(s);
    }
    
    public static final boolean[] toBoolean(final char[] array) {
        final boolean[] array2 = new boolean[array.length];
        for (int i = 0; i < array.length; ++i) {
            final boolean[] array3 = array2;
            final int n = i;
            boolean b = false;
            if (array[i] == 't' || array[i] == 'T' || array[i] == '1') {
                b = true;
            }
            array3[n] = b;
        }
        return array2;
    }
    
    public static final boolean[] toBoolean(final byte[] array) {
        final boolean[] array2 = new boolean[array.length];
        for (int i = 0; i < array.length; ++i) {
            final boolean[] array3 = array2;
            final int n = i;
            boolean b = false;
            if (array[i] != 0) {
                b = true;
            }
            array3[n] = b;
        }
        return array2;
    }
    
    public static final boolean[] toBoolean(final float[] array) {
        final boolean[] array2 = new boolean[array.length];
        for (int i = 0; i < array.length; ++i) {
            final boolean[] array3 = array2;
            final int n = i;
            boolean b = false;
            if (array[i] != 0.0f) {
                b = true;
            }
            array3[n] = b;
        }
        return array2;
    }
    
    public static final boolean[] toBoolean(final String[] array) {
        final boolean[] array2 = new boolean[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = new Boolean(array[i]);
        }
        return array2;
    }
    
    public static final byte toByte(final boolean b) {
        return (byte)(b ? 1 : 0);
    }
    
    public static final byte toByte(final char c) {
        return (byte)c;
    }
    
    public static final byte toByte(final int n) {
        return (byte)n;
    }
    
    public static final byte toByte(final float n) {
        return (byte)n;
    }
    
    public static final byte[] toByte(final String s) {
        return s.getBytes();
    }
    
    public static final byte[] toByte(final boolean[] array) {
        final byte[] array2 = new byte[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = (byte)(array[i] ? 1 : 0);
        }
        return array2;
    }
    
    public static final byte[] toByte(final char[] array) {
        final byte[] array2 = new byte[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = (byte)array[i];
        }
        return array2;
    }
    
    public static final byte[] toByte(final int[] array) {
        final byte[] array2 = new byte[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = (byte)array[i];
        }
        return array2;
    }
    
    public static final byte[] toByte(final float[] array) {
        final byte[] array2 = new byte[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = (byte)array[i];
        }
        return array2;
    }
    
    public static final byte[][] toByte(final String[] array) {
        final byte[][] array2 = new byte[array.length][];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = array[i].getBytes();
        }
        return array2;
    }
    
    public static final char toChar(final boolean b) {
        return b ? 't' : 'f';
    }
    
    public static final char toChar(final byte b) {
        return (char)(b & 0xFF);
    }
    
    public static final char toChar(final int n) {
        return (char)n;
    }
    
    public static final char toChar(final float n) {
        return (char)n;
    }
    
    public static final char[] toChar(final String s) {
        return s.toCharArray();
    }
    
    public static final char[] toChar(final boolean[] array) {
        final char[] array2 = new char[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = (char)(array[i] ? 116 : 102);
        }
        return array2;
    }
    
    public static final char[] toChar(final int[] array) {
        final char[] array2 = new char[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = (char)array[i];
        }
        return array2;
    }
    
    public static final char[] toChar(final byte[] array) {
        final char[] array2 = new char[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = (char)(array[i] & 0xFF);
        }
        return array2;
    }
    
    public static final char[] toChar(final float[] array) {
        final char[] array2 = new char[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = (char)array[i];
        }
        return array2;
    }
    
    public static final char[][] toChar(final String[] array) {
        final char[][] array2 = new char[array.length][];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = array[i].toCharArray();
        }
        return array2;
    }
    
    public static final int toInt(final boolean b) {
        return b ? 1 : 0;
    }
    
    public static final int toInt(final byte b) {
        return b & 0xFF;
    }
    
    public static final int toInt(final char c) {
        return c;
    }
    
    public static final int toInt(final float n) {
        return (int)n;
    }
    
    public static final int toInt(final String s) {
        try {
            return Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            return 0;
        }
    }
    
    public static final int toInt(final String s, final int n) {
        try {
            return Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            return n;
        }
    }
    
    public static final int[] toInt(final boolean[] array) {
        final int[] array2 = new int[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = (array[i] ? 1 : 0);
        }
        return array2;
    }
    
    public static final int[] toInt(final byte[] array) {
        final int[] array2 = new int[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = (array[i] & 0xFF);
        }
        return array2;
    }
    
    public static final int[] toInt(final char[] array) {
        final int[] array2 = new int[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = array[i];
        }
        return array2;
    }
    
    public static int[] toInt(final float[] array) {
        final int[] array2 = new int[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = (int)array[i];
        }
        return array2;
    }
    
    public static int[] toInt(final String[] array) {
        return toInt(array, 0);
    }
    
    public static int[] toInt(final String[] array, final int n) {
        final int[] array2 = new int[array.length];
        for (int i = 0; i < array.length; ++i) {
            try {
                array2[i] = Integer.parseInt(array[i]);
            }
            catch (NumberFormatException ex) {
                array2[i] = n;
            }
        }
        return array2;
    }
    
    public static final float toFloat(final boolean b) {
        return b ? 1 : 0;
    }
    
    public static final float toFloat(final int n) {
        return n;
    }
    
    public static final float toFloat(final String s) {
        return toFloat(s, Float.NaN);
    }
    
    public static final float toFloat(final String s, final float n) {
        try {
            return new Float(s);
        }
        catch (NumberFormatException ex) {
            return n;
        }
    }
    
    public static final float[] toFloat(final boolean[] array) {
        final float[] array2 = new float[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = (array[i] ? 1 : 0);
        }
        return array2;
    }
    
    public static final float[] toFloat(final char[] array) {
        final float[] array2 = new float[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = array[i];
        }
        return array2;
    }
    
    public static final float[] toFloat(final int[] array) {
        final float[] array2 = new float[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = array[i];
        }
        return array2;
    }
    
    public static final float[] toFloat(final String[] array) {
        return toFloat(array, 0.0f);
    }
    
    public static final float[] toFloat(final String[] array, final float n) {
        final float[] array2 = new float[array.length];
        for (int i = 0; i < array.length; ++i) {
            try {
                array2[i] = new Float(array[i]);
            }
            catch (NumberFormatException ex) {
                array2[i] = n;
            }
        }
        return array2;
    }
    
    public static final String str(final boolean b) {
        return String.valueOf(b);
    }
    
    public static final String str(final byte b) {
        return String.valueOf(b);
    }
    
    public static final String str(final char c) {
        return String.valueOf(c);
    }
    
    public static final String str(final short n) {
        return String.valueOf(n);
    }
    
    public static final String str(final int n) {
        return String.valueOf(n);
    }
    
    public static final String str(final float n) {
        return String.valueOf(n);
    }
    
    public static final String str(final long n) {
        return String.valueOf(n);
    }
    
    public static final String str(final double n) {
        return String.valueOf(n);
    }
    
    public static final String[] str(final boolean[] array) {
        final String[] array2 = new String[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = String.valueOf(array);
        }
        return array2;
    }
    
    public static final String[] str(final byte[] array) {
        final String[] array2 = new String[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = String.valueOf(array);
        }
        return array2;
    }
    
    public static final String[] str(final char[] array) {
        final String[] array2 = new String[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = String.valueOf(array);
        }
        return array2;
    }
    
    public static final String[] str(final short[] array) {
        final String[] array2 = new String[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = String.valueOf(array);
        }
        return array2;
    }
    
    public static final String[] str(final int[] array) {
        final String[] array2 = new String[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = String.valueOf(array);
        }
        return array2;
    }
    
    public static final String[] str(final float[] array) {
        final String[] array2 = new String[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = String.valueOf(array);
        }
        return array2;
    }
    
    public static final String[] str(final long[] array) {
        final String[] array2 = new String[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = String.valueOf(array);
        }
        return array2;
    }
    
    public static final String[] str(final double[] array) {
        final String[] array2 = new String[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = String.valueOf(array);
        }
        return array2;
    }
    
    public static String[] nf(final int[] array, final int n) {
        final String[] array2 = new String[array.length];
        for (int i = 0; i < array2.length; ++i) {
            array2[i] = nf(array[i], n);
        }
        return array2;
    }
    
    public static String nf(final int n, final int n2) {
        if (PApplet.int_nf != null && PApplet.int_nf_digits == n2 && !PApplet.int_nf_commas) {
            return PApplet.int_nf.format(n);
        }
        (PApplet.int_nf = NumberFormat.getInstance()).setGroupingUsed(false);
        PApplet.int_nf_commas = false;
        PApplet.int_nf.setMinimumIntegerDigits(n2);
        PApplet.int_nf_digits = n2;
        return PApplet.int_nf.format(n);
    }
    
    public static String[] nfc(final int[] array) {
        final String[] array2 = new String[array.length];
        for (int i = 0; i < array2.length; ++i) {
            array2[i] = nfc(array[i]);
        }
        return array2;
    }
    
    public static String nfc(final int n) {
        if (PApplet.int_nf != null && PApplet.int_nf_digits == 0 && PApplet.int_nf_commas) {
            return PApplet.int_nf.format(n);
        }
        (PApplet.int_nf = NumberFormat.getInstance()).setGroupingUsed(true);
        PApplet.int_nf_commas = true;
        PApplet.int_nf.setMinimumIntegerDigits(0);
        PApplet.int_nf_digits = 0;
        return PApplet.int_nf.format(n);
    }
    
    public static String nfs(final int n, final int n2) {
        return (n < 0) ? nf(n, n2) : (" " + nf(n, n2));
    }
    
    public static String[] nfs(final int[] array, final int n) {
        final String[] array2 = new String[array.length];
        for (int i = 0; i < array2.length; ++i) {
            array2[i] = nfs(array[i], n);
        }
        return array2;
    }
    
    public static String nfp(final int n, final int n2) {
        return (n < 0) ? nf(n, n2) : ("+" + nf(n, n2));
    }
    
    public static String[] nfp(final int[] array, final int n) {
        final String[] array2 = new String[array.length];
        for (int i = 0; i < array2.length; ++i) {
            array2[i] = nfp(array[i], n);
        }
        return array2;
    }
    
    public static String[] nf(final float[] array, final int n, final int n2) {
        final String[] array2 = new String[array.length];
        for (int i = 0; i < array2.length; ++i) {
            array2[i] = nf(array[i], n, n2);
        }
        return array2;
    }
    
    public static String nf(final float n, final int n2, final int float_nf_right) {
        if (PApplet.float_nf != null && PApplet.float_nf_left == n2 && PApplet.float_nf_right == float_nf_right && !PApplet.float_nf_commas) {
            return PApplet.float_nf.format(n);
        }
        (PApplet.float_nf = NumberFormat.getInstance()).setGroupingUsed(false);
        PApplet.float_nf_commas = false;
        if (n2 != 0) {
            PApplet.float_nf.setMinimumIntegerDigits(n2);
        }
        if (float_nf_right != 0) {
            PApplet.float_nf.setMinimumFractionDigits(float_nf_right);
            PApplet.float_nf.setMaximumFractionDigits(float_nf_right);
        }
        PApplet.float_nf_left = n2;
        PApplet.float_nf_right = float_nf_right;
        return PApplet.float_nf.format(n);
    }
    
    public static String[] nfc(final float[] array, final int n) {
        final String[] array2 = new String[array.length];
        for (int i = 0; i < array2.length; ++i) {
            array2[i] = nfc(array[i], n);
        }
        return array2;
    }
    
    public static String nfc(final float n, final int float_nf_right) {
        if (PApplet.float_nf != null && PApplet.float_nf_left == 0 && PApplet.float_nf_right == float_nf_right && PApplet.float_nf_commas) {
            return PApplet.float_nf.format(n);
        }
        (PApplet.float_nf = NumberFormat.getInstance()).setGroupingUsed(true);
        PApplet.float_nf_commas = true;
        if (float_nf_right != 0) {
            PApplet.float_nf.setMinimumFractionDigits(float_nf_right);
            PApplet.float_nf.setMaximumFractionDigits(float_nf_right);
        }
        PApplet.float_nf_left = 0;
        PApplet.float_nf_right = float_nf_right;
        return PApplet.float_nf.format(n);
    }
    
    public static String[] nfs(final float[] array, final int n, final int n2) {
        final String[] array2 = new String[array.length];
        for (int i = 0; i < array2.length; ++i) {
            array2[i] = nfs(array[i], n, n2);
        }
        return array2;
    }
    
    public static String nfs(final float n, final int n2, final int n3) {
        return (n < 0.0f) ? nf(n, n2, n3) : (" " + nf(n, n2, n3));
    }
    
    public static String[] nfp(final float[] array, final int n, final int n2) {
        final String[] array2 = new String[array.length];
        for (int i = 0; i < array2.length; ++i) {
            array2[i] = nfp(array[i], n, n2);
        }
        return array2;
    }
    
    public static String nfp(final float n, final int n2, final int n3) {
        return (n < 0.0f) ? nf(n, n2, n3) : ("+" + nf(n, n2, n3));
    }
    
    public static final String hex(final byte b) {
        return hex(b, 2);
    }
    
    public static final String hex(final char c) {
        return hex(c, 4);
    }
    
    public static final String hex(final int n) {
        return hex(n, 8);
    }
    
    public static final String hex(final int n, final int n2) {
        final String upperCase = Integer.toHexString(n).toUpperCase();
        final int length = upperCase.length();
        if (length > n2) {
            return upperCase.substring(length - n2);
        }
        if (length < n2) {
            return "00000000".substring(8 - (n2 - length)) + upperCase;
        }
        return upperCase;
    }
    
    public static final int unhex(final String s) {
        return (int)Long.parseLong(s, 16);
    }
    
    public static final String binary(final byte b) {
        return binary(b, 8);
    }
    
    public static final String binary(final char c) {
        return binary(c, 16);
    }
    
    public static final String binary(final int n) {
        return Integer.toBinaryString(n);
    }
    
    public static final String binary(final int n, final int n2) {
        final String binaryString = Integer.toBinaryString(n);
        final int length = binaryString.length();
        if (length > n2) {
            return binaryString.substring(length - n2);
        }
        if (length < n2) {
            return "00000000000000000000000000000000".substring(32 - (n2 - length)) + binaryString;
        }
        return binaryString;
    }
    
    public static final int unbinary(final String s) {
        return Integer.parseInt(s, 2);
    }
    
    public final int color(int n) {
        if (this.g == null) {
            if (n > 255) {
                n = 255;
            }
            else if (n < 0) {
                n = 0;
            }
            return 0xFF000000 | n << 16 | n << 8 | n;
        }
        return this.g.color(n);
    }
    
    public final int color(final float n) {
        if (this.g == null) {
            int n2 = (int)n;
            if (n2 > 255) {
                n2 = 255;
            }
            else if (n2 < 0) {
                n2 = 0;
            }
            return 0xFF000000 | n2 << 16 | n2 << 8 | n2;
        }
        return this.g.color(n);
    }
    
    public final int color(int n, int n2) {
        if (this.g == null) {
            if (n > 255) {
                n = 255;
            }
            else if (n < 0) {
                n = 0;
            }
            if (n2 > 255) {
                n2 = 255;
            }
            else if (n2 < 0) {
                n2 = 0;
            }
            return n2 << 24 | n << 16 | n << 8 | n;
        }
        return this.g.color(n, n2);
    }
    
    public final int color(final float n, final float n2) {
        if (this.g == null) {
            int n3 = (int)n;
            final int n4 = (int)n2;
            if (n3 > 255) {
                n3 = 255;
            }
            else if (n3 < 0) {
                n3 = 0;
            }
            if (n4 <= 255) {
                if (n4 < 0) {}
            }
            return 0xFF000000 | n3 << 16 | n3 << 8 | n3;
        }
        return this.g.color(n, n2);
    }
    
    public final int color(int n, int n2, int n3) {
        if (this.g == null) {
            if (n > 255) {
                n = 255;
            }
            else if (n < 0) {
                n = 0;
            }
            if (n2 > 255) {
                n2 = 255;
            }
            else if (n2 < 0) {
                n2 = 0;
            }
            if (n3 > 255) {
                n3 = 255;
            }
            else if (n3 < 0) {
                n3 = 0;
            }
            return 0xFF000000 | n << 16 | n2 << 8 | n3;
        }
        return this.g.color(n, n2, n3);
    }
    
    public final int color(float n, float n2, float n3) {
        if (this.g == null) {
            if (n > 255.0f) {
                n = 255.0f;
            }
            else if (n < 0.0f) {
                n = 0.0f;
            }
            if (n2 > 255.0f) {
                n2 = 255.0f;
            }
            else if (n2 < 0.0f) {
                n2 = 0.0f;
            }
            if (n3 > 255.0f) {
                n3 = 255.0f;
            }
            else if (n3 < 0.0f) {
                n3 = 0.0f;
            }
            return 0xFF000000 | (int)n << 16 | (int)n2 << 8 | (int)n3;
        }
        return this.g.color(n, n2, n3);
    }
    
    public final int color(int n, int n2, int n3, int n4) {
        if (this.g == null) {
            if (n4 > 255) {
                n4 = 255;
            }
            else if (n4 < 0) {
                n4 = 0;
            }
            if (n > 255) {
                n = 255;
            }
            else if (n < 0) {
                n = 0;
            }
            if (n2 > 255) {
                n2 = 255;
            }
            else if (n2 < 0) {
                n2 = 0;
            }
            if (n3 > 255) {
                n3 = 255;
            }
            else if (n3 < 0) {
                n3 = 0;
            }
            return n4 << 24 | n << 16 | n2 << 8 | n3;
        }
        return this.g.color(n, n2, n3, n4);
    }
    
    public final int color(float n, float n2, float n3, float n4) {
        if (this.g == null) {
            if (n4 > 255.0f) {
                n4 = 255.0f;
            }
            else if (n4 < 0.0f) {
                n4 = 0.0f;
            }
            if (n > 255.0f) {
                n = 255.0f;
            }
            else if (n < 0.0f) {
                n = 0.0f;
            }
            if (n2 > 255.0f) {
                n2 = 255.0f;
            }
            else if (n2 < 0.0f) {
                n2 = 0.0f;
            }
            if (n3 > 255.0f) {
                n3 = 255.0f;
            }
            else if (n3 < 0.0f) {
                n3 = 0.0f;
            }
            return (int)n4 << 24 | (int)n << 16 | (int)n2 << 8 | (int)n3;
        }
        return this.g.color(n, n2, n3, n4);
    }
    
    public void setupExternal(final Frame frame) {
        final Worker worker = new Worker();
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
            System.err.println("Usage: PApplet <appletname>");
            System.err.println("For additional options, see the javadoc for PApplet");
            System.exit(1);
        }
        try {
            boolean b = false;
            int[] int1 = null;
            int[] int2 = null;
            String property = System.getProperty("user.dir");
            String s = null;
            boolean b2 = false;
            Color black = Color.BLACK;
            Color gray = Color.GRAY;
            GraphicsDevice graphicsDevice = null;
            for (int i = 0; i < array.length; ++i) {
                final int index = array[i].indexOf(61);
                if (index != -1) {
                    final String substring = array[i].substring(0, index);
                    String s2 = array[i].substring(index + 1);
                    if (substring.equals("--editor-location")) {
                        b = true;
                        int2 = toInt(split(s2, ','));
                    }
                    else if (substring.equals("--display")) {
                        final int n = Integer.parseInt(s2) - 1;
                        final GraphicsDevice[] screenDevices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
                        if (n >= 0 && n < screenDevices.length) {
                            graphicsDevice = screenDevices[n];
                        }
                        else {
                            System.err.println("Display " + s2 + " does not exist, using the default display instead.");
                        }
                    }
                    else if (substring.equals("--present-color")) {
                        if (s2.charAt(0) == '#') {
                            s2 = s2.substring(1);
                        }
                        black = new Color(Integer.parseInt(s2, 16));
                    }
                    else if (substring.equals("--present-stop-color")) {
                        if (s2.charAt(0) == '#') {
                            s2 = s2.substring(1);
                        }
                        gray = new Color(Integer.parseInt(s2, 16));
                    }
                    else if (substring.equals("--sketch-folder")) {
                        property = s2;
                    }
                    else if (substring.equals("--location")) {
                        int1 = toInt(split(s2, ','));
                    }
                }
                else if (array[i].equals("--present")) {
                    b2 = true;
                }
                else {
                    if (!array[i].equals("--external")) {
                        s = array[i];
                        break;
                    }
                    b = true;
                }
            }
            Frame frame;
            if (graphicsDevice != null) {
                frame = new Frame(graphicsDevice.getDefaultConfiguration());
            }
            else {
                frame = new Frame();
            }
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setResizable(false);
            final PApplet pApplet = (PApplet)Class.forName(s).newInstance();
            pApplet.frame = frame;
            pApplet.folder = property;
            pApplet.args = subset(array, 1);
            pApplet.init();
            while (pApplet.width == 0 && !pApplet.finished) {
                try {
                    Thread.sleep(5);
                }
                catch (InterruptedException ex2) {}
            }
            if (b2) {
                frame.setUndecorated(true);
                frame.setBackground(black);
                graphicsDevice.setFullScreenWindow(frame);
                frame.add(pApplet);
                final Dimension size = frame.getSize();
                pApplet.setBounds((size.width - pApplet.width) / 2, (size.height - pApplet.height) / 2, pApplet.width, pApplet.height);
                if (b) {
                    final Label label = new Label("stop");
                    label.setForeground(gray);
                    label.addMouseListener(new MouseAdapter() {
                        public final void mousePressed(final MouseEvent mouseEvent) {
                            System.exit(0);
                        }
                    });
                    frame.add(label);
                    final Dimension preferredSize = label.getPreferredSize();
                    final Dimension size2 = new Dimension(preferredSize.width * 2, preferredSize.height);
                    label.setSize(size2);
                    label.setLocation(20, size.height - size2.height - 20);
                    pApplet.setupExternal(frame);
                }
            }
            else {
                frame.pack();
                if (b) {
                    final Insets insets = frame.getInsets();
                    final int n2 = Math.max(pApplet.width, 120) + insets.left + insets.right;
                    final int n3 = Math.max(pApplet.height, 120) + insets.top + insets.bottom;
                    frame.setSize(n2, n3);
                    if (int1 != null) {
                        frame.setLocation(int1[0], int1[1]);
                    }
                    else {
                        final int n4 = int2[0] - 20;
                        final int n5 = int2[1];
                        if (n4 - n2 > 10) {
                            frame.setLocation(n4 - n2, n5);
                        }
                        else {
                            int n6 = int2[0] + 66;
                            int n7 = int2[1] + 66;
                            if (n6 + n2 > screenSize.width - 33 || n7 + n3 > screenSize.height - 33) {
                                n6 = (screenSize.width - n2) / 2;
                                n7 = (screenSize.height - n3) / 2;
                            }
                            frame.setLocation(n6, n7);
                        }
                    }
                    frame.setLayout(null);
                    frame.add(pApplet);
                    frame.setBackground(SystemColor.control);
                    pApplet.setBounds((n2 - pApplet.width) / 2, insets.top + (n3 - insets.top - insets.bottom - pApplet.height) / 2, n2, n3);
                    pApplet.setupExternal(frame);
                }
                else {
                    frame.setLayout(new BorderLayout());
                    frame.add(pApplet, "Center");
                    frame.setLocation((screenSize.width - pApplet.width) / 2, (screenSize.height - pApplet.height) / 2);
                    frame.addWindowListener(new WindowAdapter() {
                        public final void windowClosing(final WindowEvent windowEvent) {
                            System.exit(0);
                        }
                    });
                }
                frame.show();
            }
            pApplet.requestFocus();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }
    
    public void recordFrame(final PGraphics recorder) {
        (this.recorder = recorder).beginFrame();
    }
    
    public void loadPixels() {
        this.g.loadPixels();
        this.pixels = this.g.pixels;
    }
    
    public void imageMode(final int n) {
        if (this.recorder != null) {
            this.recorder.imageMode(n);
        }
        this.g.imageMode(n);
    }
    
    public void smooth() {
        if (this.recorder != null) {
            this.recorder.smooth();
        }
        this.g.smooth();
    }
    
    public void noSmooth() {
        if (this.recorder != null) {
            this.recorder.noSmooth();
        }
        this.g.noSmooth();
    }
    
    public void updatePixels() {
        if (this.recorder != null) {
            this.recorder.updatePixels();
        }
        this.g.updatePixels();
    }
    
    public void updatePixels(final int n, final int n2, final int n3, final int n4) {
        if (this.recorder != null) {
            this.recorder.updatePixels(n, n2, n3, n4);
        }
        this.g.updatePixels(n, n2, n3, n4);
    }
    
    public int get(final int n, final int n2) {
        return this.g.get(n, n2);
    }
    
    public PImage get(final int n, final int n2, final int n3, final int n4) {
        return this.g.get(n, n2, n3, n4);
    }
    
    public PImage get() {
        return this.g.get();
    }
    
    public void set(final int n, final int n2, final int n3) {
        if (this.recorder != null) {
            this.recorder.set(n, n2, n3);
        }
        this.g.set(n, n2, n3);
    }
    
    public void set(final int n, final int n2, final PImage pImage) {
        if (this.recorder != null) {
            this.recorder.set(n, n2, pImage);
        }
        this.g.set(n, n2, pImage);
    }
    
    public void mask(final int[] array) {
        if (this.recorder != null) {
            this.recorder.mask(array);
        }
        this.g.mask(array);
    }
    
    public void mask(final PImage pImage) {
        if (this.recorder != null) {
            this.recorder.mask(pImage);
        }
        this.g.mask(pImage);
    }
    
    public void filter(final int n) {
        if (this.recorder != null) {
            this.recorder.filter(n);
        }
        this.g.filter(n);
    }
    
    public void filter(final int n, final float n2) {
        if (this.recorder != null) {
            this.recorder.filter(n, n2);
        }
        this.g.filter(n, n2);
    }
    
    public void copy(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        if (this.recorder != null) {
            this.recorder.copy(n, n2, n3, n4, n5, n6, n7, n8);
        }
        this.g.copy(n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    public void copy(final PImage pImage, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        if (this.recorder != null) {
            this.recorder.copy(pImage, n, n2, n3, n4, n5, n6, n7, n8);
        }
        this.g.copy(pImage, n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    public static int blend(final int n, final int n2, final int n3) {
        return PImage.blend(n, n2, n3);
    }
    
    public void blend(final int n, final int n2, final int n3, final int n4, final int n5) {
        if (this.recorder != null) {
            this.recorder.blend(n, n2, n3, n4, n5);
        }
        this.g.blend(n, n2, n3, n4, n5);
    }
    
    public void blend(final PImage pImage, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (this.recorder != null) {
            this.recorder.blend(pImage, n, n2, n3, n4, n5);
        }
        this.g.blend(pImage, n, n2, n3, n4, n5);
    }
    
    public void blend(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        if (this.recorder != null) {
            this.recorder.blend(n, n2, n3, n4, n5, n6, n7, n8, n9);
        }
        this.g.blend(n, n2, n3, n4, n5, n6, n7, n8, n9);
    }
    
    public void blend(final PImage pImage, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        if (this.recorder != null) {
            this.recorder.blend(pImage, n, n2, n3, n4, n5, n6, n7, n8, n9);
        }
        this.g.blend(pImage, n, n2, n3, n4, n5, n6, n7, n8, n9);
    }
    
    public static boolean saveHeaderTIFF(final OutputStream outputStream, final int n, final int n2) {
        return PImage.saveHeaderTIFF(outputStream, n, n2);
    }
    
    public static boolean saveTIFF(final OutputStream outputStream, final int[] array, final int n, final int n2) {
        return PImage.saveTIFF(outputStream, array, n, n2);
    }
    
    public static boolean saveHeaderTGA(final OutputStream outputStream, final int n, final int n2) {
        return PImage.saveHeaderTGA(outputStream, n, n2);
    }
    
    public static boolean saveTGA(final OutputStream outputStream, final int[] array, final int n, final int n2) {
        return PImage.saveTGA(outputStream, array, n, n2);
    }
    
    public void requestDisplay(final PApplet pApplet) {
        if (this.recorder != null) {
            this.recorder.requestDisplay(pApplet);
        }
        this.g.requestDisplay(pApplet);
    }
    
    public void hint(final int n) {
        if (this.recorder != null) {
            this.recorder.hint(n);
        }
        this.g.hint(n);
    }
    
    public void unhint(final int n) {
        if (this.recorder != null) {
            this.recorder.unhint(n);
        }
        this.g.unhint(n);
    }
    
    public void beginShape() {
        if (this.recorder != null) {
            this.recorder.beginShape();
        }
        this.g.beginShape();
    }
    
    public void beginShape(final int n) {
        if (this.recorder != null) {
            this.recorder.beginShape(n);
        }
        this.g.beginShape(n);
    }
    
    public void normal(final float n, final float n2, final float n3) {
        if (this.recorder != null) {
            this.recorder.normal(n, n2, n3);
        }
        this.g.normal(n, n2, n3);
    }
    
    public void textureMode(final int n) {
        if (this.recorder != null) {
            this.recorder.textureMode(n);
        }
        this.g.textureMode(n);
    }
    
    public void texture(final PImage pImage) {
        if (this.recorder != null) {
            this.recorder.texture(pImage);
        }
        this.g.texture(pImage);
    }
    
    public void vertex(final float n, final float n2) {
        if (this.recorder != null) {
            this.recorder.vertex(n, n2);
        }
        this.g.vertex(n, n2);
    }
    
    public void vertex(final float n, final float n2, final float n3) {
        if (this.recorder != null) {
            this.recorder.vertex(n, n2, n3);
        }
        this.g.vertex(n, n2, n3);
    }
    
    public void vertex(final float n, final float n2, final float n3, final float n4) {
        if (this.recorder != null) {
            this.recorder.vertex(n, n2, n3, n4);
        }
        this.g.vertex(n, n2, n3, n4);
    }
    
    public void vertex(final float n, final float n2, final float n3, final float n4, final float n5) {
        if (this.recorder != null) {
            this.recorder.vertex(n, n2, n3, n4, n5);
        }
        this.g.vertex(n, n2, n3, n4, n5);
    }
    
    public void bezierVertex(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        if (this.recorder != null) {
            this.recorder.bezierVertex(n, n2, n3, n4, n5, n6);
        }
        this.g.bezierVertex(n, n2, n3, n4, n5, n6);
    }
    
    public void bezierVertex(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9) {
        if (this.recorder != null) {
            this.recorder.bezierVertex(n, n2, n3, n4, n5, n6, n7, n8, n9);
        }
        this.g.bezierVertex(n, n2, n3, n4, n5, n6, n7, n8, n9);
    }
    
    public void curveVertex(final float n, final float n2) {
        if (this.recorder != null) {
            this.recorder.curveVertex(n, n2);
        }
        this.g.curveVertex(n, n2);
    }
    
    public void curveVertex(final float n, final float n2, final float n3) {
        if (this.recorder != null) {
            this.recorder.curveVertex(n, n2, n3);
        }
        this.g.curveVertex(n, n2, n3);
    }
    
    public void endShape() {
        if (this.recorder != null) {
            this.recorder.endShape();
        }
        this.g.endShape();
    }
    
    public void point(final float n, final float n2) {
        if (this.recorder != null) {
            this.recorder.point(n, n2);
        }
        this.g.point(n, n2);
    }
    
    public void point(final float n, final float n2, final float n3) {
        if (this.recorder != null) {
            this.recorder.point(n, n2, n3);
        }
        this.g.point(n, n2, n3);
    }
    
    public void line(final float n, final float n2, final float n3, final float n4) {
        if (this.recorder != null) {
            this.recorder.line(n, n2, n3, n4);
        }
        this.g.line(n, n2, n3, n4);
    }
    
    public void line(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        if (this.recorder != null) {
            this.recorder.line(n, n2, n3, n4, n5, n6);
        }
        this.g.line(n, n2, n3, n4, n5, n6);
    }
    
    public void triangle(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        if (this.recorder != null) {
            this.recorder.triangle(n, n2, n3, n4, n5, n6);
        }
        this.g.triangle(n, n2, n3, n4, n5, n6);
    }
    
    public void quad(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        if (this.recorder != null) {
            this.recorder.quad(n, n2, n3, n4, n5, n6, n7, n8);
        }
        this.g.quad(n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    public void rectMode(final int n) {
        if (this.recorder != null) {
            this.recorder.rectMode(n);
        }
        this.g.rectMode(n);
    }
    
    public void rect(final float n, final float n2, final float n3, final float n4) {
        if (this.recorder != null) {
            this.recorder.rect(n, n2, n3, n4);
        }
        this.g.rect(n, n2, n3, n4);
    }
    
    public void ellipseMode(final int n) {
        if (this.recorder != null) {
            this.recorder.ellipseMode(n);
        }
        this.g.ellipseMode(n);
    }
    
    public void ellipse(final float n, final float n2, final float n3, final float n4) {
        if (this.recorder != null) {
            this.recorder.ellipse(n, n2, n3, n4);
        }
        this.g.ellipse(n, n2, n3, n4);
    }
    
    public void arc(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        if (this.recorder != null) {
            this.recorder.arc(n, n2, n3, n4, n5, n6);
        }
        this.g.arc(n, n2, n3, n4, n5, n6);
    }
    
    public void box(final float n) {
        if (this.recorder != null) {
            this.recorder.box(n);
        }
        this.g.box(n);
    }
    
    public void box(final float n, final float n2, final float n3) {
        if (this.recorder != null) {
            this.recorder.box(n, n2, n3);
        }
        this.g.box(n, n2, n3);
    }
    
    public void sphereDetail(final int n) {
        if (this.recorder != null) {
            this.recorder.sphereDetail(n);
        }
        this.g.sphereDetail(n);
    }
    
    public void sphere(final float n) {
        if (this.recorder != null) {
            this.recorder.sphere(n);
        }
        this.g.sphere(n);
    }
    
    public float bezierPoint(final float n, final float n2, final float n3, final float n4, final float n5) {
        return this.g.bezierPoint(n, n2, n3, n4, n5);
    }
    
    public float bezierTangent(final float n, final float n2, final float n3, final float n4, final float n5) {
        return this.g.bezierTangent(n, n2, n3, n4, n5);
    }
    
    public void bezier(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        if (this.recorder != null) {
            this.recorder.bezier(n, n2, n3, n4, n5, n6, n7, n8);
        }
        this.g.bezier(n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    public void bezier(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12) {
        if (this.recorder != null) {
            this.recorder.bezier(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12);
        }
        this.g.bezier(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12);
    }
    
    public void bezierDetail(final int n) {
        if (this.recorder != null) {
            this.recorder.bezierDetail(n);
        }
        this.g.bezierDetail(n);
    }
    
    public void curveDetail(final int n) {
        if (this.recorder != null) {
            this.recorder.curveDetail(n);
        }
        this.g.curveDetail(n);
    }
    
    public void curveTightness(final float n) {
        if (this.recorder != null) {
            this.recorder.curveTightness(n);
        }
        this.g.curveTightness(n);
    }
    
    public float curvePoint(final float n, final float n2, final float n3, final float n4, final float n5) {
        return this.g.curvePoint(n, n2, n3, n4, n5);
    }
    
    public float curveTangent(final float n, final float n2, final float n3, final float n4, final float n5) {
        return this.g.curveTangent(n, n2, n3, n4, n5);
    }
    
    public void curve(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        if (this.recorder != null) {
            this.recorder.curve(n, n2, n3, n4, n5, n6, n7, n8);
        }
        this.g.curve(n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    public void curve(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12) {
        if (this.recorder != null) {
            this.recorder.curve(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12);
        }
        this.g.curve(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12);
    }
    
    public void image(final PImage pImage, final float n, final float n2) {
        if (this.recorder != null) {
            this.recorder.image(pImage, n, n2);
        }
        this.g.image(pImage, n, n2);
    }
    
    public void image(final PImage pImage, final float n, final float n2, final float n3, final float n4) {
        if (this.recorder != null) {
            this.recorder.image(pImage, n, n2, n3, n4);
        }
        this.g.image(pImage, n, n2, n3, n4);
    }
    
    public void image(final PImage pImage, final float n, final float n2, final float n3, final float n4, final int n5, final int n6, final int n7, final int n8) {
        if (this.recorder != null) {
            this.recorder.image(pImage, n, n2, n3, n4, n5, n6, n7, n8);
        }
        this.g.image(pImage, n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    public void textFont(final PFont pFont, final float n) {
        if (this.recorder != null) {
            this.recorder.textFont(pFont, n);
        }
        this.g.textFont(pFont, n);
    }
    
    public void textFont(final PFont pFont) {
        if (this.recorder != null) {
            this.recorder.textFont(pFont);
        }
        this.g.textFont(pFont);
    }
    
    public void textSize(final float n) {
        if (this.recorder != null) {
            this.recorder.textSize(n);
        }
        this.g.textSize(n);
    }
    
    public void textLeading(final float n) {
        if (this.recorder != null) {
            this.recorder.textLeading(n);
        }
        this.g.textLeading(n);
    }
    
    public void textAlign(final int n) {
        if (this.recorder != null) {
            this.recorder.textAlign(n);
        }
        this.g.textAlign(n);
    }
    
    public void textMode(final int n) {
        if (this.recorder != null) {
            this.recorder.textMode(n);
        }
        this.g.textMode(n);
    }
    
    public float textAscent() {
        return this.g.textAscent();
    }
    
    public float textDescent() {
        return this.g.textDescent();
    }
    
    public float textWidth(final char c) {
        return this.g.textWidth(c);
    }
    
    public float textWidth(final String s) {
        return this.g.textWidth(s);
    }
    
    public void text(final char c, final float n, final float n2) {
        if (this.recorder != null) {
            this.recorder.text(c, n, n2);
        }
        this.g.text(c, n, n2);
    }
    
    public void text(final char c, final float n, final float n2, final float n3) {
        if (this.recorder != null) {
            this.recorder.text(c, n, n2, n3);
        }
        this.g.text(c, n, n2, n3);
    }
    
    public void text(final String s, final float n, final float n2) {
        if (this.recorder != null) {
            this.recorder.text(s, n, n2);
        }
        this.g.text(s, n, n2);
    }
    
    public void text(final String s, final float n, final float n2, final float n3) {
        if (this.recorder != null) {
            this.recorder.text(s, n, n2, n3);
        }
        this.g.text(s, n, n2, n3);
    }
    
    public void text(final String s, final float n, final float n2, final float n3, final float n4) {
        if (this.recorder != null) {
            this.recorder.text(s, n, n2, n3, n4);
        }
        this.g.text(s, n, n2, n3, n4);
    }
    
    public void text(final String s, final float n, final float n2, final float n3, final float n4, final float n5) {
        if (this.recorder != null) {
            this.recorder.text(s, n, n2, n3, n4, n5);
        }
        this.g.text(s, n, n2, n3, n4, n5);
    }
    
    public void text(final int n, final float n2, final float n3) {
        if (this.recorder != null) {
            this.recorder.text(n, n2, n3);
        }
        this.g.text(n, n2, n3);
    }
    
    public void text(final int n, final float n2, final float n3, final float n4) {
        if (this.recorder != null) {
            this.recorder.text(n, n2, n3, n4);
        }
        this.g.text(n, n2, n3, n4);
    }
    
    public void text(final float n, final float n2, final float n3) {
        if (this.recorder != null) {
            this.recorder.text(n, n2, n3);
        }
        this.g.text(n, n2, n3);
    }
    
    public void text(final float n, final float n2, final float n3, final float n4) {
        if (this.recorder != null) {
            this.recorder.text(n, n2, n3, n4);
        }
        this.g.text(n, n2, n3, n4);
    }
    
    public void translate(final float n, final float n2) {
        if (this.recorder != null) {
            this.recorder.translate(n, n2);
        }
        this.g.translate(n, n2);
    }
    
    public void translate(final float n, final float n2, final float n3) {
        if (this.recorder != null) {
            this.recorder.translate(n, n2, n3);
        }
        this.g.translate(n, n2, n3);
    }
    
    public void rotate(final float n) {
        if (this.recorder != null) {
            this.recorder.rotate(n);
        }
        this.g.rotate(n);
    }
    
    public void rotateX(final float n) {
        if (this.recorder != null) {
            this.recorder.rotateX(n);
        }
        this.g.rotateX(n);
    }
    
    public void rotateY(final float n) {
        if (this.recorder != null) {
            this.recorder.rotateY(n);
        }
        this.g.rotateY(n);
    }
    
    public void rotateZ(final float n) {
        if (this.recorder != null) {
            this.recorder.rotateZ(n);
        }
        this.g.rotateZ(n);
    }
    
    public void rotate(final float n, final float n2, final float n3, final float n4) {
        if (this.recorder != null) {
            this.recorder.rotate(n, n2, n3, n4);
        }
        this.g.rotate(n, n2, n3, n4);
    }
    
    public void scale(final float n) {
        if (this.recorder != null) {
            this.recorder.scale(n);
        }
        this.g.scale(n);
    }
    
    public void scale(final float n, final float n2) {
        if (this.recorder != null) {
            this.recorder.scale(n, n2);
        }
        this.g.scale(n, n2);
    }
    
    public void scale(final float n, final float n2, final float n3) {
        if (this.recorder != null) {
            this.recorder.scale(n, n2, n3);
        }
        this.g.scale(n, n2, n3);
    }
    
    public void pushMatrix() {
        if (this.recorder != null) {
            this.recorder.pushMatrix();
        }
        this.g.pushMatrix();
    }
    
    public void popMatrix() {
        if (this.recorder != null) {
            this.recorder.popMatrix();
        }
        this.g.popMatrix();
    }
    
    public void resetMatrix() {
        if (this.recorder != null) {
            this.recorder.resetMatrix();
        }
        this.g.resetMatrix();
    }
    
    public void applyMatrix(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        if (this.recorder != null) {
            this.recorder.applyMatrix(n, n2, n3, n4, n5, n6);
        }
        this.g.applyMatrix(n, n2, n3, n4, n5, n6);
    }
    
    public void applyMatrix(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12, final float n13, final float n14, final float n15, final float n16) {
        if (this.recorder != null) {
            this.recorder.applyMatrix(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16);
        }
        this.g.applyMatrix(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16);
    }
    
    public void printMatrix() {
        if (this.recorder != null) {
            this.recorder.printMatrix();
        }
        this.g.printMatrix();
    }
    
    public void cameraMode(final int n) {
        if (this.recorder != null) {
            this.recorder.cameraMode(n);
        }
        this.g.cameraMode(n);
    }
    
    public void beginCamera() {
        if (this.recorder != null) {
            this.recorder.beginCamera();
        }
        this.g.beginCamera();
    }
    
    public void endCamera() {
        if (this.recorder != null) {
            this.recorder.endCamera();
        }
        this.g.endCamera();
    }
    
    public void camera() {
        if (this.recorder != null) {
            this.recorder.camera();
        }
        this.g.camera();
    }
    
    public void camera(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9) {
        if (this.recorder != null) {
            this.recorder.camera(n, n2, n3, n4, n5, n6, n7, n8, n9);
        }
        this.g.camera(n, n2, n3, n4, n5, n6, n7, n8, n9);
    }
    
    public void ortho() {
        if (this.recorder != null) {
            this.recorder.ortho();
        }
        this.g.ortho();
    }
    
    public void ortho(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        if (this.recorder != null) {
            this.recorder.ortho(n, n2, n3, n4, n5, n6);
        }
        this.g.ortho(n, n2, n3, n4, n5, n6);
    }
    
    public void perspective() {
        if (this.recorder != null) {
            this.recorder.perspective();
        }
        this.g.perspective();
    }
    
    public void perspective(final float n, final float n2, final float n3, final float n4) {
        if (this.recorder != null) {
            this.recorder.perspective(n, n2, n3, n4);
        }
        this.g.perspective(n, n2, n3, n4);
    }
    
    public void frustum(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        if (this.recorder != null) {
            this.recorder.frustum(n, n2, n3, n4, n5, n6);
        }
        this.g.frustum(n, n2, n3, n4, n5, n6);
    }
    
    public void printCamera() {
        if (this.recorder != null) {
            this.recorder.printCamera();
        }
        this.g.printCamera();
    }
    
    public void printProjection() {
        if (this.recorder != null) {
            this.recorder.printProjection();
        }
        this.g.printProjection();
    }
    
    public float screenX(final float n, final float n2) {
        return this.g.screenX(n, n2);
    }
    
    public float screenY(final float n, final float n2) {
        return this.g.screenY(n, n2);
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
    
    public float modelX(final float n, final float n2, final float n3) {
        return this.g.modelX(n, n2, n3);
    }
    
    public float modelY(final float n, final float n2, final float n3) {
        return this.g.modelY(n, n2, n3);
    }
    
    public float modelZ(final float n, final float n2, final float n3) {
        return this.g.modelZ(n, n2, n3);
    }
    
    public void colorMode(final int n) {
        if (this.recorder != null) {
            this.recorder.colorMode(n);
        }
        this.g.colorMode(n);
    }
    
    public void colorMode(final int n, final float n2) {
        if (this.recorder != null) {
            this.recorder.colorMode(n, n2);
        }
        this.g.colorMode(n, n2);
    }
    
    public void colorMode(final int n, final float n2, final float n3, final float n4) {
        if (this.recorder != null) {
            this.recorder.colorMode(n, n2, n3, n4);
        }
        this.g.colorMode(n, n2, n3, n4);
    }
    
    public void colorMode(final int n, final float n2, final float n3, final float n4, final float n5) {
        if (this.recorder != null) {
            this.recorder.colorMode(n, n2, n3, n4, n5);
        }
        this.g.colorMode(n, n2, n3, n4, n5);
    }
    
    public void noTint() {
        if (this.recorder != null) {
            this.recorder.noTint();
        }
        this.g.noTint();
    }
    
    public void tint(final int n) {
        if (this.recorder != null) {
            this.recorder.tint(n);
        }
        this.g.tint(n);
    }
    
    public void tint(final float n) {
        if (this.recorder != null) {
            this.recorder.tint(n);
        }
        this.g.tint(n);
    }
    
    public void tint(final float n, final float n2) {
        if (this.recorder != null) {
            this.recorder.tint(n, n2);
        }
        this.g.tint(n, n2);
    }
    
    public void tint(final float n, final float n2, final float n3) {
        if (this.recorder != null) {
            this.recorder.tint(n, n2, n3);
        }
        this.g.tint(n, n2, n3);
    }
    
    public void tint(final float n, final float n2, final float n3, final float n4) {
        if (this.recorder != null) {
            this.recorder.tint(n, n2, n3, n4);
        }
        this.g.tint(n, n2, n3, n4);
    }
    
    public void noFill() {
        if (this.recorder != null) {
            this.recorder.noFill();
        }
        this.g.noFill();
    }
    
    public void fill(final int n) {
        if (this.recorder != null) {
            this.recorder.fill(n);
        }
        this.g.fill(n);
    }
    
    public void fill(final float n) {
        if (this.recorder != null) {
            this.recorder.fill(n);
        }
        this.g.fill(n);
    }
    
    public void fill(final float n, final float n2) {
        if (this.recorder != null) {
            this.recorder.fill(n, n2);
        }
        this.g.fill(n, n2);
    }
    
    public void fill(final float n, final float n2, final float n3) {
        if (this.recorder != null) {
            this.recorder.fill(n, n2, n3);
        }
        this.g.fill(n, n2, n3);
    }
    
    public void fill(final float n, final float n2, final float n3, final float n4) {
        if (this.recorder != null) {
            this.recorder.fill(n, n2, n3, n4);
        }
        this.g.fill(n, n2, n3, n4);
    }
    
    public void ambient(final int n) {
        if (this.recorder != null) {
            this.recorder.ambient(n);
        }
        this.g.ambient(n);
    }
    
    public void ambient(final float n) {
        if (this.recorder != null) {
            this.recorder.ambient(n);
        }
        this.g.ambient(n);
    }
    
    public void ambient(final float n, final float n2, final float n3) {
        if (this.recorder != null) {
            this.recorder.ambient(n, n2, n3);
        }
        this.g.ambient(n, n2, n3);
    }
    
    public void specular(final int n) {
        if (this.recorder != null) {
            this.recorder.specular(n);
        }
        this.g.specular(n);
    }
    
    public void specular(final float n) {
        if (this.recorder != null) {
            this.recorder.specular(n);
        }
        this.g.specular(n);
    }
    
    public void specular(final float n, final float n2) {
        if (this.recorder != null) {
            this.recorder.specular(n, n2);
        }
        this.g.specular(n, n2);
    }
    
    public void specular(final float n, final float n2, final float n3) {
        if (this.recorder != null) {
            this.recorder.specular(n, n2, n3);
        }
        this.g.specular(n, n2, n3);
    }
    
    public void specular(final float n, final float n2, final float n3, final float n4) {
        if (this.recorder != null) {
            this.recorder.specular(n, n2, n3, n4);
        }
        this.g.specular(n, n2, n3, n4);
    }
    
    public void shininess(final float n) {
        if (this.recorder != null) {
            this.recorder.shininess(n);
        }
        this.g.shininess(n);
    }
    
    public void emissive(final int n) {
        if (this.recorder != null) {
            this.recorder.emissive(n);
        }
        this.g.emissive(n);
    }
    
    public void emissive(final float n) {
        if (this.recorder != null) {
            this.recorder.emissive(n);
        }
        this.g.emissive(n);
    }
    
    public void emissive(final float n, final float n2, final float n3) {
        if (this.recorder != null) {
            this.recorder.emissive(n, n2, n3);
        }
        this.g.emissive(n, n2, n3);
    }
    
    public void lights() {
        if (this.recorder != null) {
            this.recorder.lights();
        }
        this.g.lights();
    }
    
    public void ambientLight(final float n, final float n2, final float n3) {
        if (this.recorder != null) {
            this.recorder.ambientLight(n, n2, n3);
        }
        this.g.ambientLight(n, n2, n3);
    }
    
    public void ambientLight(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        if (this.recorder != null) {
            this.recorder.ambientLight(n, n2, n3, n4, n5, n6);
        }
        this.g.ambientLight(n, n2, n3, n4, n5, n6);
    }
    
    public void directionalLight(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        if (this.recorder != null) {
            this.recorder.directionalLight(n, n2, n3, n4, n5, n6);
        }
        this.g.directionalLight(n, n2, n3, n4, n5, n6);
    }
    
    public void pointLight(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        if (this.recorder != null) {
            this.recorder.pointLight(n, n2, n3, n4, n5, n6);
        }
        this.g.pointLight(n, n2, n3, n4, n5, n6);
    }
    
    public void spotLight(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11) {
        if (this.recorder != null) {
            this.recorder.spotLight(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11);
        }
        this.g.spotLight(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11);
    }
    
    public void lightFalloff(final float n, final float n2, final float n3) {
        if (this.recorder != null) {
            this.recorder.lightFalloff(n, n2, n3);
        }
        this.g.lightFalloff(n, n2, n3);
    }
    
    public void lightSpecular(final float n, final float n2, final float n3) {
        if (this.recorder != null) {
            this.recorder.lightSpecular(n, n2, n3);
        }
        this.g.lightSpecular(n, n2, n3);
    }
    
    public void strokeWeight(final float n) {
        if (this.recorder != null) {
            this.recorder.strokeWeight(n);
        }
        this.g.strokeWeight(n);
    }
    
    public void strokeJoin(final int n) {
        if (this.recorder != null) {
            this.recorder.strokeJoin(n);
        }
        this.g.strokeJoin(n);
    }
    
    public void strokeCap(final int n) {
        if (this.recorder != null) {
            this.recorder.strokeCap(n);
        }
        this.g.strokeCap(n);
    }
    
    public void noStroke() {
        if (this.recorder != null) {
            this.recorder.noStroke();
        }
        this.g.noStroke();
    }
    
    public void stroke(final int n) {
        if (this.recorder != null) {
            this.recorder.stroke(n);
        }
        this.g.stroke(n);
    }
    
    public void stroke(final float n) {
        if (this.recorder != null) {
            this.recorder.stroke(n);
        }
        this.g.stroke(n);
    }
    
    public void stroke(final float n, final float n2) {
        if (this.recorder != null) {
            this.recorder.stroke(n, n2);
        }
        this.g.stroke(n, n2);
    }
    
    public void stroke(final float n, final float n2, final float n3) {
        if (this.recorder != null) {
            this.recorder.stroke(n, n2, n3);
        }
        this.g.stroke(n, n2, n3);
    }
    
    public void stroke(final float n, final float n2, final float n3, final float n4) {
        if (this.recorder != null) {
            this.recorder.stroke(n, n2, n3, n4);
        }
        this.g.stroke(n, n2, n3, n4);
    }
    
    public void background(final int n) {
        if (this.recorder != null) {
            this.recorder.background(n);
        }
        this.g.background(n);
    }
    
    public void background(final float n) {
        if (this.recorder != null) {
            this.recorder.background(n);
        }
        this.g.background(n);
    }
    
    public void background(final float n, final float n2, final float n3) {
        if (this.recorder != null) {
            this.recorder.background(n, n2, n3);
        }
        this.g.background(n, n2, n3);
    }
    
    public void background(final PImage pImage) {
        if (this.recorder != null) {
            this.recorder.background(pImage);
        }
        this.g.background(pImage);
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
    
    static /* synthetic */ Class class(final String s, final boolean b) {
        try {
            final Class<?> forName = Class.forName(s);
            if (!b) {
                forName.getComponentType();
            }
            return forName;
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    private final /* synthetic */ void this() {
        this.glock = new Object();
        this.screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.focused = false;
        this.online = false;
        this.framerate = 10.0f;
        this.framerateLastMillis = 0L;
        this.framerateLastDelayTime = 0L;
        this.framerateTarget = 0.0f;
        this.mouseEventQueue = new MouseEvent[10];
        this.keyEventQueue = new KeyEvent[10];
        this.cursor_type = 0;
        this.cursor_visible = true;
        this.perlin_octaves = 4;
        this.perlin_amp_falloff = 0.5f;
    }
    
    public PApplet() {
        this.this();
    }
    
    static {
        javaVersionName = System.getProperty("java.version").substring(0, 3);
        javaVersion = new Float(PApplet.javaVersionName);
        PApplet.platformName = System.getProperty("os.name");
        if (PApplet.platformName.toLowerCase().indexOf("mac") != -1) {
            if (System.getProperty("mrj.version") != null) {
                PApplet.platform = (PApplet.platformName.equals("Mac OS X") ? 1 : 0) + 2;
            }
        }
        else {
            final String property = System.getProperty("os.name");
            if (property.indexOf("Windows") != -1) {
                PApplet.platform = 1;
            }
            else if (property.equals("Linux")) {
                PApplet.platform = 4;
            }
            else {
                PApplet.platform = 0;
            }
        }
    }
    
    public class RegisteredMethods
    {
        int count;
        Object[] objects;
        Method[] methods;
        
        public void handle() {
            this.handle(new Object[0]);
        }
        
        public void handle(final Object[] array) {
            for (int i = 0; i < this.count; ++i) {
                try {
                    this.methods[i].invoke(this.objects[i], array);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        
        public void add(final Object o, final Method method) {
            if (this.objects == null) {
                this.objects = new Object[5];
                this.methods = new Method[5];
            }
            if (this.count == this.objects.length) {
                final Object[] objects = new Object[this.count << 1];
                System.arraycopy(this.objects, 0, objects, 0, this.count);
                this.objects = objects;
                final Method[] methods = new Method[this.count << 1];
                System.arraycopy(this.methods, 0, methods, 0, this.count);
                this.methods = methods;
            }
            this.objects[this.count] = o;
            this.methods[this.count] = method;
            ++this.count;
        }
    }
    
    private static class WorkerVar
    {
        private Thread thread;
        
        synchronized Thread get() {
            return this.thread;
        }
        
        synchronized void clear() {
            this.thread = null;
        }
        
        WorkerVar(final Thread thread) {
            this.thread = thread;
        }
    }
    
    class Worker
    {
        private Object value;
        private WorkerVar workerVar;
        
        protected synchronized Object getValue() {
            return this.value;
        }
        
        private final synchronized void setValue(final Object value) {
            this.value = value;
        }
        
        public Object construct() {
            try {
                if (System.in.read() == 115) {
                    PApplet.this.stop();
                    PApplet.this.finished = true;
                }
            }
            catch (IOException ex) {
                PApplet.this.finished = true;
            }
            try {
                Thread.sleep(250L);
            }
            catch (InterruptedException ex2) {}
            return null;
        }
        
        public void interrupt() {
            final Thread value = this.workerVar.get();
            if (value != null) {
                value.interrupt();
            }
            this.workerVar.clear();
        }
        
        public Object get() {
            while (true) {
                final Thread value = this.workerVar.get();
                if (value == null) {
                    break;
                }
                try {
                    value.join();
                }
                catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    return null;
                }
            }
            return this.getValue();
        }
        
        public void start() {
            final Thread value = this.workerVar.get();
            if (value != null) {
                value.start();
            }
        }
        
        public Worker() {
            this.workerVar = new WorkerVar(new Thread(new Runnable() {
                public final void run() {
                    try {
                        Worker.this.setValue(Worker.this.construct());
                    }
                    finally {
                        Worker.this.workerVar.clear();
                    }
                    Worker.this.workerVar.clear();
                }
            }));
        }
    }
}
