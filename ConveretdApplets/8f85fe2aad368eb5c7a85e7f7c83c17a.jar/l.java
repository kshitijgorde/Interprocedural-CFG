import java.io.FileInputStream;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.InputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.awt.event.FocusEvent;
import java.awt.Graphics;
import java.awt.Dimension;
import java.io.PrintStream;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class l extends Applet implements Runnable, MouseListener, MouseMotionListener, KeyListener, FocusListener
{
    public static final String a;
    public static final double a;
    private static NumberFormat b;
    private static int c;
    private static NumberFormat a;
    private static int o;
    private static int l;
    public j a;
    public String c;
    public int[] a;
    public int t;
    public int d;
    public boolean g;
    public MouseEvent a;
    public int u;
    public KeyEvent a;
    public boolean b;
    public boolean f;
    public float b;
    public long a;
    public long b;
    public float d;
    public boolean a;
    public boolean j;
    public int h;
    public boolean d;
    public Thread a;
    public int a;
    public int i;
    public b[] a;
    public boolean[][] a;
    public PrintStream a;
    public MouseEvent[] a;
    public int j;
    public KeyEvent[] a;
    public int g;
    public static Class a;
    
    public final void init() {
        this.d();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        this.addFocusListener(this);
        try {
            if (l.a >= 1.4) {
                Class a;
                if ((a = l.a) == null) {
                    a = (l.a = a("[Ljava.awt.Component;", false));
                }
                a.getMethod("setFocusTraversalKeysEnabled", Boolean.TYPE).invoke(this, Boolean.FALSE);
            }
            else {
                System.out.println(l.a);
            }
        }
        catch (Exception ex) {}
        System.currentTimeMillis();
        this.d = false;
        this.a = true;
        this.j = true;
        this.g = true;
        this.a = new b[10];
        this.a = new boolean[10][7];
        this.setup();
        this.a = this.a.e;
        this.a = this.a.b;
        this.i = this.a.k;
        try {
            this.getAppletContext();
            this.f = true;
        }
        catch (NullPointerException ex2) {
            this.f = false;
        }
    }
    
    public final void d() {
        this.a = new j(100, 100);
    }
    
    public final void start() {
        (this.a = new Thread(this)).start();
    }
    
    public final void stop() {
        if (this.a != null) {
            this.a = null;
        }
    }
    
    public final void destroy() {
        this.stop();
    }
    
    public final Dimension getPreferredSize() {
        return new Dimension(this.a, this.i);
    }
    
    public void setup() {
    }
    
    public void draw() {
    }
    
    public final void m() {
        if (!this.a) {
            this.j = true;
            if (this.a != null) {
                this.a.interrupt();
            }
        }
    }
    
    public final void j() {
        if (!this.a) {
            this.a = true;
            if (this.a != null) {
                this.a.interrupt();
            }
        }
    }
    
    public final void e() {
        if (this.a) {
            this.a = false;
            this.b = 0L;
            this.a = 0L;
            if (this.a != null) {
                this.a.interrupt();
            }
        }
    }
    
    public final void a(final int n, final int n2) {
        this.a.b(n, n2);
        this.a = this.a.e;
        this.a = this.a.b;
        this.i = this.a.k;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        l.h:I
        //     4: ifne            8
        //     7: return         
        //     8: goto            14
        //    11: aload_2        
        //    12: monitorexit    
        //    13: athrow         
        //    14: aload_0        
        //    15: getfield        l.a:Lj;
        //    18: dup            
        //    19: astore_2       
        //    20: monitorenter   
        //    21: aload_0        
        //    22: getfield        l.a:Lj;
        //    25: ifnull          43
        //    28: aload_1        
        //    29: aload_0        
        //    30: getfield        l.a:Lj;
        //    33: getfield        j.a:Ljava/awt/Image;
        //    36: iconst_0       
        //    37: iconst_0       
        //    38: aconst_null    
        //    39: invokevirtual   java/awt/Graphics.drawImage:(Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
        //    42: pop            
        //    43: aload_2        
        //    44: monitorexit    
        //    45: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  11     13     11     14     Any
        //  21     45     11     14     Any
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
    
    public final void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: goto            121
        //     3: aload_0        
        //     4: getfield        l.a:Z
        //     7: ifne            17
        //    10: aload_0        
        //    11: getfield        l.j:Z
        //    14: ifeq            94
        //    17: aload_0        
        //    18: getfield        l.d:F
        //    21: fconst_0       
        //    22: fcmpl          
        //    23: ifeq            30
        //    26: aload_0        
        //    27: invokevirtual   l.c:()V
        //    30: goto            36
        //    33: aload_1        
        //    34: monitorexit    
        //    35: athrow         
        //    36: aload_0        
        //    37: getfield        l.a:Lj;
        //    40: dup            
        //    41: astore_1       
        //    42: monitorenter   
        //    43: aload_0        
        //    44: getfield        l.a:Lj;
        //    47: invokevirtual   j.i:()V
        //    50: iconst_0       
        //    51: istore_3       
        //    52: aload_0        
        //    53: invokevirtual   l.draw:()V
        //    56: aload_0        
        //    57: invokevirtual   l.i:()V
        //    60: aload_0        
        //    61: invokevirtual   l.h:()V
        //    64: aload_0        
        //    65: getfield        l.a:Lj;
        //    68: invokevirtual   j.h:()V
        //    71: aload_0        
        //    72: dup            
        //    73: getfield        l.h:I
        //    76: iconst_1       
        //    77: iadd           
        //    78: putfield        l.h:I
        //    81: aload_0        
        //    82: invokevirtual   l.repaint:()V
        //    85: aload_0        
        //    86: invokevirtual   l.getToolkit:()Ljava/awt/Toolkit;
        //    89: invokevirtual   java/awt/Toolkit.sync:()V
        //    92: aload_1        
        //    93: monitorexit    
        //    94: aload_0        
        //    95: iconst_0       
        //    96: putfield        l.j:Z
        //    99: aload_0        
        //   100: getfield        l.a:Z
        //   103: ifeq            110
        //   106: iconst_1       
        //   107: goto            113
        //   110: sipush          10000
        //   113: i2l            
        //   114: invokestatic    java/lang/Thread.sleep:(J)V
        //   117: goto            121
        //   120: pop            
        //   121: invokestatic    java/lang/Thread.currentThread:()Ljava/lang/Thread;
        //   124: aload_0        
        //   125: getfield        l.a:Ljava/lang/Thread;
        //   128: if_acmpne       138
        //   131: aload_0        
        //   132: getfield        l.d:Z
        //   135: ifeq            3
        //   138: return         
        //   139: astore_1       
        //   140: aload_0        
        //   141: iconst_1       
        //   142: putfield        l.d:Z
        //   145: aload_0        
        //   146: getfield        l.a:Ljava/io/PrintStream;
        //   149: ifnull          170
        //   152: aload_0        
        //   153: getfield        l.a:Ljava/io/PrintStream;
        //   156: ldc             "Error while running applet."
        //   158: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   161: aload_1        
        //   162: aload_0        
        //   163: getfield        l.a:Ljava/io/PrintStream;
        //   166: invokevirtual   java/lang/Exception.printStackTrace:(Ljava/io/PrintStream;)V
        //   169: return         
        //   170: getstatic       java/lang/System.err:Ljava/io/PrintStream;
        //   173: ldc             "Error while running applet."
        //   175: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   178: aload_1        
        //   179: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   182: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  33     35     33     36     Any
        //  43     94     33     36     Any
        //  99     117    120    121    Ljava/lang/InterruptedException;
        //  0      138    139    183    Ljava/lang/Exception;
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
    
    public final void c(final MouseEvent p0) {
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
        //     7: getfield        l.a:[Ljava/awt/event/MouseEvent;
        //    10: dup            
        //    11: astore_2       
        //    12: monitorenter   
        //    13: aload_0        
        //    14: getfield        l.j:I
        //    17: aload_0        
        //    18: getfield        l.a:[Ljava/awt/event/MouseEvent;
        //    21: arraylength    
        //    22: if_icmpne       57
        //    25: aload_0        
        //    26: getfield        l.j:I
        //    29: iconst_1       
        //    30: ishl           
        //    31: anewarray       Ljava/awt/event/MouseEvent;
        //    34: astore          4
        //    36: aload_0        
        //    37: getfield        l.a:[Ljava/awt/event/MouseEvent;
        //    40: iconst_0       
        //    41: aload           4
        //    43: iconst_0       
        //    44: aload_0        
        //    45: getfield        l.j:I
        //    48: invokestatic    java/lang/System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //    51: aload_0        
        //    52: aload           4
        //    54: putfield        l.a:[Ljava/awt/event/MouseEvent;
        //    57: aload_0        
        //    58: getfield        l.a:[Ljava/awt/event/MouseEvent;
        //    61: aload_0        
        //    62: dup            
        //    63: getfield        l.j:I
        //    66: dup_x1         
        //    67: iconst_1       
        //    68: iadd           
        //    69: putfield        l.j:I
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
    
    public final void i() {
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
        //     7: getfield        l.a:[Ljava/awt/event/MouseEvent;
        //    10: dup            
        //    11: astore_1       
        //    12: monitorenter   
        //    13: iconst_0       
        //    14: istore_3       
        //    15: goto            39
        //    18: aload_0        
        //    19: aload_0        
        //    20: getfield        l.a:[Ljava/awt/event/MouseEvent;
        //    23: iload_3        
        //    24: aaload         
        //    25: putfield        l.a:Ljava/awt/event/MouseEvent;
        //    28: aload_0        
        //    29: aload_0        
        //    30: getfield        l.a:Ljava/awt/event/MouseEvent;
        //    33: invokevirtual   l.b:(Ljava/awt/event/MouseEvent;)V
        //    36: iinc            3, 1
        //    39: iload_3        
        //    40: aload_0        
        //    41: getfield        l.j:I
        //    44: if_icmplt       18
        //    47: aload_0        
        //    48: iconst_0       
        //    49: putfield        l.j:I
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
    
    public final void b(final MouseEvent a) {
        this.t = a.getX();
        this.d = a.getY();
        this.a = a;
        if (this.g) {
            this.g = false;
        }
        switch (a.getID()) {
            case 501: {
                this.mousePressed();
                break;
            }
        }
    }
    
    public final void a(final MouseEvent mouseEvent) {
        if (this.a) {
            this.c(mouseEvent);
            return;
        }
        this.b(mouseEvent);
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        this.a(mouseEvent);
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        this.a(mouseEvent);
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        this.a(mouseEvent);
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
        this.a(mouseEvent);
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
        this.a(mouseEvent);
    }
    
    public final void mouseDragged(final MouseEvent mouseEvent) {
        this.a(mouseEvent);
    }
    
    public final void mouseMoved(final MouseEvent mouseEvent) {
        this.a(mouseEvent);
    }
    
    public void mousePressed() {
    }
    
    public final void b(final KeyEvent p0) {
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
        //     7: getfield        l.a:[Ljava/awt/event/KeyEvent;
        //    10: dup            
        //    11: astore_2       
        //    12: monitorenter   
        //    13: aload_0        
        //    14: getfield        l.g:I
        //    17: aload_0        
        //    18: getfield        l.a:[Ljava/awt/event/KeyEvent;
        //    21: arraylength    
        //    22: if_icmpne       57
        //    25: aload_0        
        //    26: getfield        l.g:I
        //    29: iconst_1       
        //    30: ishl           
        //    31: anewarray       Ljava/awt/event/KeyEvent;
        //    34: astore          4
        //    36: aload_0        
        //    37: getfield        l.a:[Ljava/awt/event/KeyEvent;
        //    40: iconst_0       
        //    41: aload           4
        //    43: iconst_0       
        //    44: aload_0        
        //    45: getfield        l.g:I
        //    48: invokestatic    java/lang/System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //    51: aload_0        
        //    52: aload           4
        //    54: putfield        l.a:[Ljava/awt/event/KeyEvent;
        //    57: aload_0        
        //    58: getfield        l.a:[Ljava/awt/event/KeyEvent;
        //    61: aload_0        
        //    62: dup            
        //    63: getfield        l.g:I
        //    66: dup_x1         
        //    67: iconst_1       
        //    68: iadd           
        //    69: putfield        l.g:I
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
    
    public final void h() {
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
        //     7: getfield        l.a:[Ljava/awt/event/KeyEvent;
        //    10: dup            
        //    11: astore_1       
        //    12: monitorenter   
        //    13: iconst_0       
        //    14: istore_3       
        //    15: goto            39
        //    18: aload_0        
        //    19: aload_0        
        //    20: getfield        l.a:[Ljava/awt/event/KeyEvent;
        //    23: iload_3        
        //    24: aaload         
        //    25: putfield        l.a:Ljava/awt/event/KeyEvent;
        //    28: aload_0        
        //    29: aload_0        
        //    30: getfield        l.a:Ljava/awt/event/KeyEvent;
        //    33: invokevirtual   l.a:(Ljava/awt/event/KeyEvent;)V
        //    36: iinc            3, 1
        //    39: iload_3        
        //    40: aload_0        
        //    41: getfield        l.g:I
        //    44: if_icmplt       18
        //    47: aload_0        
        //    48: iconst_0       
        //    49: putfield        l.g:I
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
    
    public final void a(final KeyEvent a) {
        this.a = a;
        this.u = a.getKeyChar();
        a.getKeyCode();
        switch (a.getID()) {
            case 401: {
                this.keyPressed();
                break;
            }
        }
    }
    
    public final void c(final KeyEvent keyEvent) {
        if (this.a) {
            this.b(keyEvent);
            return;
        }
        this.a(keyEvent);
    }
    
    public final void keyPressed(final KeyEvent keyEvent) {
        this.c(keyEvent);
    }
    
    public final void keyReleased(final KeyEvent keyEvent) {
        this.c(keyEvent);
    }
    
    public final void keyTyped(final KeyEvent keyEvent) {
        this.c(keyEvent);
    }
    
    public void keyPressed() {
    }
    
    public final void focusGained(final FocusEvent focusEvent) {
        this.b = true;
    }
    
    public final void focusLost(final FocusEvent focusEvent) {
        this.b = false;
    }
    
    public final void h(final int n) {
        if (this.h == 0) {
            return;
        }
        if (n > 0) {
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public final float a() {
        final float n;
        if (this.a != 0L && (n = System.currentTimeMillis() - this.a) != 0.0f) {
            this.b = this.b * 0.9f + 1.0f / (n / 1000.0f) * 0.1f;
        }
        this.a = System.currentTimeMillis();
        return this.b;
    }
    
    public final void c() {
        if (this.b == 0L) {
            this.b = System.currentTimeMillis();
            return;
        }
        final long b;
        final int n = (int)((b = this.b + (long)(1000.0f / this.d)) - System.currentTimeMillis());
        this.b = b;
        this.h(n);
    }
    
    public final void a() {
        if (this.f) {
            System.err.println("Can't use saveFrame() when running in a browser.");
            return;
        }
        this.c(this.a("screen-" + a(this.h, 4) + ".tif"));
    }
    
    public static final void d(final String s) {
        System.out.print(s);
        System.out.flush();
    }
    
    public static final void b(final String s) {
        d(s);
        System.out.println();
    }
    
    public static final float d(final float n) {
        if (n < 0.0f) {
            return -n;
        }
        return n;
    }
    
    public final float c(float n) {
        if (this.a != null && this.a.c == 1) {
            n *= 0.017453292f;
        }
        return (float)Math.sin(n);
    }
    
    public final float a(float n) {
        if (this.a != null && this.a.c == 1) {
            n *= 0.017453292f;
        }
        return (float)Math.cos(n);
    }
    
    public static final float b(final float n) {
        return n * 0.017453292f;
    }
    
    public final d a(final String s) {
        try {
            final String lowerCase = s.toLowerCase();
            InputStream a = this.a(s);
            if (lowerCase.endsWith(".vlw.gz")) {
                a = new GZIPInputStream(a);
            }
            else if (!lowerCase.endsWith(".vlw")) {
                throw new IOException("I don't know how to load a font named " + s);
            }
            return new d(a);
        }
        catch (Exception ex2) {
            final Exception ex = ex2;
            ex2.printStackTrace();
            System.err.println("Could not load font " + s);
            System.err.println("Make sure that the font has been copied");
            System.err.println("to the data folder of your sketch.");
            System.err.println();
            ex.printStackTrace();
            return null;
        }
    }
    
    public final InputStream a(final String s) throws IOException {
        if (s.startsWith("http://")) {
            try {
                return new URL(s).openStream();
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
                return null;
            }
        }
        final InputStream resourceAsStream;
        if ((resourceAsStream = this.getClass().getResourceAsStream(s)) != null) {
            return resourceAsStream;
        }
        InputStream resourceAsStream2;
        if ((resourceAsStream2 = this.getClass().getResourceAsStream("data/" + s)) != null) {
            return resourceAsStream2;
        }
        try {
            try {
                return resourceAsStream2 = new FileInputStream(new File(this.c + File.separator + "data", s));
            }
            catch (Exception ex2) {
                try {
                    return resourceAsStream2 = new FileInputStream(new File("data", s));
                }
                catch (IOException ex3) {
                    try {
                        return resourceAsStream2 = new FileInputStream(s);
                    }
                    catch (IOException ex4) {}
                }
            }
        }
        catch (SecurityException ex5) {}
        if (resourceAsStream2 == null) {
            throw new IOException("openStream() could not open " + s);
        }
        return null;
    }
    
    public final String a(final String s) {
        final String string;
        a(string = this.c + File.separator + s);
        return string;
    }
    
    public static final void a(final String s) {
        final String parent;
        final File file;
        if ((parent = new File(s).getParent()) != null && !(file = new File(parent)).exists()) {
            file.mkdirs();
        }
    }
    
    public static final String a(final String[] array, final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            if (i != 0) {
                sb.append(s);
            }
            sb.append(array[i]);
        }
        return sb.toString();
    }
    
    public static final String[] a(final String s, final char c) {
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
    
    public static final int a(final String s) {
        try {
            return Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            return 0;
        }
    }
    
    public static final float a(final String s) {
        return a(s, Float.NaN);
    }
    
    public static final float a(final String s, final float n) {
        try {
            return new Float(s);
        }
        catch (NumberFormatException ex) {
            return n;
        }
    }
    
    public static final String a(final int n, final int n2) {
        if (l.b != null && l.c == n2) {
            return l.b.format(n);
        }
        (l.b = NumberFormat.getInstance()).setGroupingUsed(false);
        l.b.setMinimumIntegerDigits(n2);
        l.c = n2;
        return l.b.format(n);
    }
    
    public static final String a(final float n, final int n2, final int l) {
        if (l.a != null && l.o == n2 && l.l == l) {
            return l.a.format(n);
        }
        (l.a = NumberFormat.getInstance()).setGroupingUsed(false);
        if (n2 != 0) {
            l.a.setMinimumIntegerDigits(n2);
        }
        if (l != 0) {
            l.a.setMinimumFractionDigits(l);
            l.a.setMaximumFractionDigits(l);
        }
        l.o = n2;
        l.l = l;
        return l.a.format(n);
    }
    
    public final void c(final String s) {
        this.a.a(s);
    }
    
    public final void b(final float n, final float n2, final float n3, final float n4) {
        this.a.b(n, n2, n3, n4);
    }
    
    public final void a(final int h) {
        this.a.H = h;
    }
    
    public final void c(final float n, final float n2, final float n3, final float n4) {
        this.a.c(n, n2, n3, n4);
    }
    
    public final void b(final int f) {
        this.a.f = f;
    }
    
    public final void a(final float n, final float n2, final float n3, final float n4) {
        this.a.a(n, n2, n3, n4);
    }
    
    public final void a(final d d) {
        this.a.a(d);
    }
    
    public final void d(final int i) {
        this.a.I = i;
    }
    
    public final void c(final int n) {
        this.a.b(n);
    }
    
    public final void a(final String s, final float n, final float n2) {
        this.a.a(s, n, n2);
    }
    
    public final void a(final int n, final float n2, final float n3) {
        this.a.a(n, n2, n3);
    }
    
    public final void g(final int n) {
        this.a.g(n);
    }
    
    public final void g() {
        this.a.c();
    }
    
    public final void e(final int n) {
        this.a.d(n);
    }
    
    public final void f(final int n) {
        this.a.f(n);
    }
    
    public static final Class a(final String s, final boolean b) {
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
    
    private final void k() {
        this.b = false;
        this.f = false;
        this.b = 10.0f;
        this.a = 0L;
        this.b = 0L;
        this.d = 0.0f;
        this.a = new MouseEvent[10];
        this.a = new KeyEvent[10];
    }
    
    public l() {
        this.k();
    }
    
    static {
        a = System.getProperty("java.version").substring(0, 3);
        a = new Double(l.a);
    }
}
