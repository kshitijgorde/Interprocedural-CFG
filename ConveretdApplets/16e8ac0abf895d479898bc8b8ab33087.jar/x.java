import java.util.TimeZone;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.util.GregorianCalendar;
import java.awt.Graphics;
import java.net.URLConnection;
import java.util.TimerTask;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Image;
import java.util.Timer;
import java.awt.Point;
import java.applet.Applet;
import java.net.URL;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public final class x extends Canvas
{
    private int f;
    private byte[] a;
    private byte[] b;
    private byte[] c;
    private int[] a;
    private int[][] a;
    private int[] b;
    private String[] a;
    private int[] c;
    private int g;
    private char[] a;
    private int h;
    public int a;
    public int b;
    public int c;
    private int i;
    private int j;
    private int k;
    private h a;
    private URL a;
    private Applet a;
    private boolean c;
    public boolean a;
    private int l;
    private boolean d;
    private z a;
    private Thread a;
    private d a;
    public boolean b;
    private int m;
    private Point a;
    private int n;
    private int o;
    private boolean e;
    private boolean f;
    private Timer a;
    public String a;
    public int d;
    public int e;
    private int p;
    private Image a;
    private final byte[] d;
    private final byte[][] a;
    
    public x(final Applet a, final h a2, final String s, final String s2, final int f) {
        this.f = 0;
        this.a = new byte[1200];
        this.b = new byte[1200];
        this.c = new byte[1200];
        this.a = new int[42];
        this.a = new int[16][42];
        this.b = new int[1200];
        this.a = new String[50];
        this.c = new int[1000];
        this.a = new char[5];
        this.h = 0;
        this.c = false;
        this.a = false;
        this.l = 0;
        this.d = false;
        this.b = false;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.e = true;
        this.f = false;
        this.a = "Page not found! ";
        this.p = 0;
        this.a = null;
        this.d = new byte[] { -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 7, -1, -1, -1, -1, -1, -1, -1, -1, 6, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 3, -1, -1, -1, -1, -1, 4, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 5, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 11, -1, -1, -1, -1, -1, 12, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 13, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 9, -1, -1, -1, -1, -1, -1, -1, -1, 8, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 14, -1, -1 };
        this.a = new byte[][] { { 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 }, { 32, 33, 34, 95, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, -41, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, -50, -49, -47, -40, -46, -39, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, -45, -44, -42, -38, 127 }, { 32, 33, 34, 95, -52, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, -51, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, -50, -49, -48, -47, -46, -64, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, -45, -44, -43, -42, 127 }, { 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, -64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, -39, -53, 93, 94, 95, -59, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, -62, -32, -57, -31, 127 }, { 32, 33, 34, -64, -63, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, -62, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, -61, -60, -59, -58, 95, -57, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, -56, -55, -54, -53, 127 }, { 32, 33, 34, -53, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, -53, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, -34, -64, -33, -30, -29, -36, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, -42, -35, -57, -62, 127 }, { 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 }, { 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 } };
        this.a = new Runnable(this) {
            public ArrayList a;
            public boolean a;
            public final x a = true;
            
            {
                this.a = a;
                this.a = new ArrayList();
            }
            
            public final boolean a(final URL url) throws IOException {
                return a(this.a(url));
            }
            
            private s a(final URL url) {
                final Object object = new Object(this.a, url) {
                    public URL a;
                    public IOException a = null;
                    
                    public final boolean a() {
                        return this.a == null;
                    }
                    
                    {
                        this.a = a;
                    }
                };
                synchronized (this.a) {
                    this.a.add(object);
                    this.a.notify();
                    // monitorexit(this.a)
                    return object;
                }
            }
            
            private static boolean a(final s object) throws IOException {
                try {
                    synchronized (object) {
                        object.wait();
                    }
                    if (!object.a()) {
                        throw object.a;
                    }
                    return true;
                }
                catch (InterruptedException ex) {
                    return false;
                }
            }
            
            public final void run() {
                try {
                    while (this.a) {
                        synchronized (this.a) {
                            this.a.wait();
                        }
                        // monitorexit(this.a)
                        this.a();
                    }
                }
                catch (Exception ex) {
                    System.err.println("Run");
                    System.err.println(ex);
                }
            }
            
            private void a() {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_0        
                //     1: getfield        d.a:Ljava/util/ArrayList;
                //     4: dup            
                //     5: astore_2       
                //     6: monitorenter   
                //     7: aload_0        
                //     8: getfield        d.a:Ljava/util/ArrayList;
                //    11: invokevirtual   java/util/ArrayList.size:()I
                //    14: anewarray       Ls;
                //    17: astore_1       
                //    18: aload_0        
                //    19: getfield        d.a:Ljava/util/ArrayList;
                //    22: aload_1        
                //    23: invokevirtual   java/util/ArrayList.toArray:([Ljava/lang/Object;)[Ljava/lang/Object;
                //    26: pop            
                //    27: aload_0        
                //    28: getfield        d.a:Ljava/util/ArrayList;
                //    31: invokevirtual   java/util/ArrayList.clear:()V
                //    34: aload_2        
                //    35: monitorexit    
                //    36: goto            42
                //    39: aload_2        
                //    40: monitorexit    
                //    41: athrow         
                //    42: iconst_0       
                //    43: istore_2       
                //    44: goto            151
                //    47: aload_0        
                //    48: getfield        d.a:Lx;
                //    51: aload_1        
                //    52: iload_2        
                //    53: aaload         
                //    54: getfield        s.a:Ljava/net/URL;
                //    57: invokevirtual   x.a:(Ljava/net/URL;)V
                //    60: goto            102
                //    63: astore_3       
                //    64: aload_1        
                //    65: iload_2        
                //    66: aaload         
                //    67: aload_3        
                //    68: putfield        s.a:Ljava/io/IOException;
                //    71: goto            125
                //    74: astore          4
                //    76: aload_1        
                //    77: iload_2        
                //    78: aaload         
                //    79: dup            
                //    80: astore          5
                //    82: monitorenter   
                //    83: aload_1        
                //    84: iload_2        
                //    85: aaload         
                //    86: invokevirtual   java/lang/Object.notify:()V
                //    89: aload           5
                //    91: monitorexit    
                //    92: goto            99
                //    95: aload           5
                //    97: monitorexit    
                //    98: athrow         
                //    99: aload           4
                //   101: athrow         
                //   102: aload_1        
                //   103: iload_2        
                //   104: aaload         
                //   105: dup            
                //   106: astore          5
                //   108: monitorenter   
                //   109: aload_1        
                //   110: iload_2        
                //   111: aaload         
                //   112: invokevirtual   java/lang/Object.notify:()V
                //   115: aload           5
                //   117: monitorexit    
                //   118: goto            148
                //   121: aload           5
                //   123: monitorexit    
                //   124: athrow         
                //   125: aload_1        
                //   126: iload_2        
                //   127: aaload         
                //   128: dup            
                //   129: astore          5
                //   131: monitorenter   
                //   132: aload_1        
                //   133: iload_2        
                //   134: aaload         
                //   135: invokevirtual   java/lang/Object.notify:()V
                //   138: aload           5
                //   140: monitorexit    
                //   141: goto            148
                //   144: aload           5
                //   146: monitorexit    
                //   147: athrow         
                //   148: iinc            2, 1
                //   151: iload_2        
                //   152: aload_1        
                //   153: arraylength    
                //   154: if_icmplt       47
                //   157: return         
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                 
                //  -----  -----  -----  -----  ---------------------
                //  7      36     39     42     Any
                //  39     41     39     42     Any
                //  47     60     63     148    Ljava/io/IOException;
                //  47     74     74     102    Any
                //  83     92     95     99     Any
                //  95     98     95     99     Any
                //  109    118    121    125    Any
                //  121    124    121    125    Any
                //  132    141    144    148    Any
                //  144    147    144    148    Any
                // 
                // The error that occurred was:
                // 
                // java.lang.IndexOutOfBoundsException: Index: 99, Size: 99
                //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
                //     at java.util.ArrayList.get(ArrayList.java:433)
                //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
                //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3551)
                //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:692)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:529)
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
        };
        (this.a = new Thread(this.a, "LoadPage thread")).start();
        this.a = new Timer();
        this.f = f;
        this.a = a2;
        this.addMouseListener(new o(this));
        this.addMouseMotionListener(new a(this));
        this.addKeyListener(new ac(this));
        this.a = a.getCodeBase();
        this.a = a;
        this.a = new z(this);
        if (s == null || s2 == null) {
            this.a = 100;
            this.b = 0;
            return;
        }
        this.a = Integer.valueOf(s);
        this.b = Integer.valueOf(s2);
    }
    
    public final void a() {
        this.a(this.a, this.b);
    }
    
    private URL a(final int n, final int n2) {
        try {
            final byte b = (byte)(n2 / 10);
            final byte b2 = (byte)(n2 % 10);
            String string = "";
            if (this.b) {
                string = "?t=" + Calendar.getInstance().getTimeInMillis() / 60000L;
            }
            return new URL(this.a, String.valueOf(((TSSView)this.a).currentChannelDir()) + "/" + n + "s" + b + b2 + string);
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    private int a(final int n, final int n2) {
        return (this.d[n] >> 3) + (this.d[n2] << 1);
    }
    
    private void b(final URL url) throws IOException {
        System.err.println("Load: " + url);
        this.a.a(url);
    }
    
    public final void a(final URL url) throws IOException {
        byte b = 32;
        byte b2 = 7;
        byte b3 = 7;
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        final byte[] array = new byte[5];
        final InputStream openStream = url.openStream();
        this.j = openStream.read();
        this.j = openStream.read();
        openStream.read(array);
        this.j = openStream.read() + (openStream.read() << 8);
        this.i = this.d[openStream.read()];
        this.j = openStream.read();
        this.j = openStream.read();
        this.j += this.j;
        this.i >>= 1;
        int i = 0;
        if ((this.i & 0x1) != 0x0) {
            i += 4;
        }
        if ((this.i & 0x2) != 0x0) {
            i += 2;
        }
        if ((this.i & 0x4) != 0x0) {
            ++i;
        }
        this.i = i;
        System.err.println("Charset:" + this.i);
        this.j = openStream.read();
        this.c = 1;
        this.d = 0;
        this.e = 0;
        this.g = 0;
        for (int j = 0; j <= 41; ++j) {
            this.a[j] = 0;
        }
        for (int k = 0; k < 15; ++k) {
            for (int l = 0; l <= 41; ++l) {
                this.a[k][l] = 0;
            }
        }
        for (int n8 = 960; n8 < 1000; ++n8) {
            this.a[n8] = 32;
            this.c[n8] = 0;
            this.b[n8] = 0;
        }
        this.p = -1;
        int n9 = 11;
        int n10 = 11;
        int a = 1;
        String string = "";
        boolean b4 = false;
        int read;
        while ((read = openStream.read()) >= 0) {
            if (n9 >= 2040) {
                string = String.valueOf(string) + (char)read;
            }
            else {
                if (a >= 25 || a < 0) {
                    if (a == 27) {
                        this.a[n9 % 40 + 2] = read;
                    }
                    else if (a == 26) {
                        this.a[this.p][n9 % 40 + 2] = read;
                    }
                    ++n9;
                }
                else if (a >= 0 && a < 25) {
                    final int n11 = read & 0x7F;
                    this.c[n10] = b2;
                    this.b[n10] = 0;
                    if (n4 != 0) {
                        this.b[n10] |= 0x1;
                    }
                    if (n5 != 0) {
                        this.b[n10] |= 0x4;
                    }
                    if (n6 != 0) {
                        this.b[n10] |= 0x8;
                    }
                    if (n7 != 0) {
                        this.b[n10] |= 0x10;
                        this.c[this.g] = n9;
                        ++this.g;
                    }
                    if (n11 >= 32 && n11 <= 127) {
                        if (n == 0 || (n11 >= 64 && n11 <= 95)) {
                            this.a[n10] = this.a[this.i][n11 - 32];
                        }
                        else {
                            final int n12;
                            this.a[n10] = (byte)(n12 + (((n12 = n11) >= 96) ? 64 : 96));
                        }
                    }
                    else {
                        this.a[n10] = (byte)((n2 != 0 && n != 0) ? b : 32);
                        if (n11 <= 7) {
                            n = 0;
                            b3 = (byte)((b2 & 0xF0) | n11);
                            n6 = 0;
                        }
                        else if (n11 == 8) {
                            n7 = 1;
                        }
                        else if (n11 == 9) {
                            n7 = 0;
                        }
                        else if (n11 == 12) {
                            n4 = 0;
                        }
                        else if (n11 == 13) {
                            n4 = 1;
                            n3 = 1;
                        }
                        else if (n11 >= 16 && n11 <= 23) {
                            n = 1;
                            b3 = (byte)((b2 & 0xF0) | n11 - 16);
                            n6 = 0;
                        }
                        else if (n11 == 24) {
                            n6 = 1;
                        }
                        else if (n11 == 25) {
                            n5 = 0;
                        }
                        else if (n11 == 26) {
                            n5 = 1;
                            this.b[n10] |= 0x4;
                        }
                        else if (n11 == 28) {
                            b3 = (byte)(b2 & 0xF);
                            this.c[n10] = b3;
                        }
                        else if (n11 == 29) {
                            b3 = (byte)((b2 & 0xF) | (b2 & 0xF) << 4);
                            this.c[n10] = b3;
                        }
                        else if (n11 == 30) {
                            n2 = 1;
                            if (n != 0) {
                                this.a[n10] = b;
                            }
                        }
                        else if (n11 == 31) {
                            n2 = 0;
                        }
                    }
                    b = this.a[n10];
                    b2 = b3;
                    ++n10;
                    ++n9;
                }
                if (n9 % 40 != 0 || n9 >= 2038) {
                    continue;
                }
                n = 0;
                n2 = 0;
                n5 = 0;
                n6 = 0;
                n7 = 0;
                n4 = 0;
                b = 32;
                b2 = 7;
                b3 = 7;
                final int read2 = openStream.read();
                final int read3 = openStream.read();
                if (read2 >= 0 && read2 <= 255 && read3 >= 0 && read3 <= 255) {
                    if ((a = this.a(read2, read3)) == 27) {
                        b4 = true;
                    }
                    else if (a == 26) {
                        ++this.p;
                    }
                }
                if (n3 == 0) {
                    continue;
                }
                openStream.skip(42L);
                for (int n13 = 0; n13 < 40; ++n13) {
                    this.c[n10] = this.c[n10 - 40];
                    if ((this.b[n10 - 40] & 0x3) > 0) {
                        this.a[n10] = this.a[n10 - 40];
                        this.b[n10] = (byte)((this.b[n10 - 40] & 0xFC) | 0x2);
                    }
                    else {
                        this.a[n10] = 32;
                    }
                    ++n9;
                    ++n10;
                }
                n3 = 0;
            }
        }
        for (int n14 = 0; n14 < 39; ++n14) {
            this.c[n14] = 7;
        }
        this.a[4] = 45;
        this.a[5] = 45;
        this.a[6] = 45;
        this.a[8] = (byte)(this.a / 100 + 48);
        this.a[9] = (byte)(this.a / 10 % 10 + 48);
        this.a[10] = (byte)(this.a % 10 + 48);
        if (string.length() == 8) {
            final String substring = string.substring(0, 3);
            final String substring2 = string.substring(3, 6);
            this.c = Integer.valueOf(string.substring(6));
            this.d = Integer.valueOf(substring);
            this.e = Integer.valueOf(substring2);
        }
        System.err.println("subpages:" + this.c);
        ((TSSView)this.a).UpdatePrevNext(this.d, this.e);
        if (b4) {
            this.i();
        }
        this.h();
        this.k();
        this.j();
        this.l();
        this.m();
        this.c = false;
        if (!this.a.isAlive()) {
            this.a.start();
        }
    }
    
    private void h() {
        System.err.println("Got " + (this.p + 1) + " x26 packets ");
        for (int i = 0; i <= this.p; ++i) {
            int n = -1;
            int n2 = -1;
            for (int j = 0; j <= 12; ++j) {
                final int n3 = this.a[i][j * 3 + 3];
                final int n4 = this.a[i][j * 3 + 4];
                final int n5 = this.a[i][j * 3 + 5];
                final int n6 = ((n3 & 0x4) >> 2) + ((n3 & 0x70) >> 3);
                final int n7 = n4 & 0x7F;
                final int n8 = n5 & 0x7F;
                final int n9 = n6 + ((n7 & 0x3) << 4);
                final int n10 = n7 >> 2;
                int n11 = n8 & 0x7F;
                System.err.println("trip:" + n6 + "/" + n7 + "/" + n8 + "  adr:" + n9 + " mode:" + n10 + " data:" + n11);
                if (n9 >= 0 && n9 <= 39) {
                    n = n9;
                }
                else if (n9 >= 40 && n9 <= 63) {
                    n2 = n9 - 40;
                }
                if (n10 == 1 && n9 <= 39) {
                    System.err.println("block mosaic from g1 at :" + n + "," + n2);
                    this.a[n2 * 40 + n] = 63;
                }
                if (n10 == 16 && n9 <= 39) {
                    System.err.println("char from g0 at :" + n + "," + n2 + " char:" + (char)n11);
                    this.a[n2 * 40 + n] = (byte)n11;
                }
                if (n10 > 16 && n10 < 31 && n9 <= 39) {
                    System.err.println("char from g0 diacrit at :" + n + "," + n2 + " char:" + (char)n11 + " crit:" + (n10 - 16));
                    final int n12;
                    if ((n12 = n10 - 16) == 1) {
                        if ((char)n11 == 'a') {
                            n11 = 194;
                        }
                        else if ((char)n11 == 'u') {
                            n11 = 197;
                        }
                        else if ((char)n11 == 'e') {
                            n11 = 199;
                        }
                        else if ((char)n11 == 'o') {
                            n11 = 224;
                        }
                        else if ((char)n11 == 'i') {
                            n11 = 225;
                        }
                    }
                    else if (n12 == 2) {
                        if ((char)n11 == 'e') {
                            n11 = 192;
                        }
                        else if ((char)n11 == 'E') {
                            n11 = 205;
                        }
                        else if ((char)n11 == 'a') {
                            n11 = 222;
                        }
                        else if ((char)n11 == 'A') {
                            n11 = 232;
                        }
                        else if ((char)n11 == 'i') {
                            n11 = 223;
                        }
                        else if ((char)n11 == 'o') {
                            n11 = 226;
                        }
                        else if ((char)n11 == 'u') {
                            n11 = 227;
                        }
                    }
                    else if (n12 == 3) {
                        if ((char)n11 == 'e') {
                            n11 = 196;
                        }
                        else if ((char)n11 == 'i') {
                            n11 = 198;
                        }
                        else if ((char)n11 == 'a') {
                            n11 = 200;
                        }
                        else if ((char)n11 == 'o') {
                            n11 = 201;
                        }
                        else if ((char)n11 == 'u') {
                            n11 = 202;
                        }
                    }
                    else if (n12 == 3) {
                        if ((char)n11 == 'n') {
                            n11 = 221;
                        }
                    }
                    else if (n12 == 8) {
                        if ((char)n11 == 'e') {
                            n11 = 195;
                        }
                        else if ((char)n11 == 'a') {
                            n11 = 211;
                        }
                        else if ((char)n11 == 'A') {
                            n11 = 206;
                        }
                        else if ((char)n11 == 'o') {
                            n11 = 212;
                        }
                        else if ((char)n11 == 'O') {
                            n11 = 207;
                        }
                        else if ((char)n11 == 'u') {
                            n11 = 214;
                        }
                        else if ((char)n11 == 'U') {
                            n11 = 209;
                        }
                        else if ((char)n11 == 'i') {
                            n11 = 193;
                        }
                    }
                    else if (n12 == 10) {
                        if ((char)n11 == 'A') {
                            n11 = 208;
                        }
                        else if ((char)n11 == 'a') {
                            n11 = 213;
                        }
                    }
                    else if (n12 == 11) {
                        if ((char)n11 == 'c') {
                            n11 = 203;
                        }
                    }
                    else if (n12 == 13) {
                        if ((char)n11 == 'u') {
                            n11 = 228;
                        }
                        else if ((char)n11 == 'U') {
                            n11 = 230;
                        }
                        else if ((char)n11 == 'o') {
                            n11 = 229;
                        }
                        else if ((char)n11 == 'O') {
                            n11 = 231;
                        }
                    }
                    this.a[n2 * 40 + n] = (byte)n11;
                }
                if (n10 == 15 && n9 <= 39) {
                    if (n11 == 123) {
                        n11 = 218;
                    }
                    else if (n11 == 48) {
                        n11 = 217;
                    }
                    else if (n11 == 39) {
                        n11 = 215;
                    }
                    else if (n11 == 63) {
                        n11 = 220;
                    }
                    else if (n11 == 33) {
                        n11 = 219;
                    }
                    else if (n11 == 40) {
                        n11 = 204;
                    }
                    this.a[n2 * 40 + n] = (byte)n11;
                }
            }
        }
    }
    
    private void i() {
        int n;
        if ((n = this.a / 100) == 8) {
            n = 0;
        }
        final byte b = this.d[this.a[3]];
        final byte b2 = this.d[this.a[4]];
        final byte b3 = (byte)(this.d[this.a[6]] & 0x8);
        final byte b4 = (byte)(this.d[this.a[8]] & 0x4);
        final byte b5 = (byte)(this.d[this.a[8]] & 0x8);
        int n2 = n;
        if (b3 != 0) {
            if ((n & 0x1) != 0x0) {
                n2 &= 0xE;
            }
            else {
                n2 |= 0x1;
            }
        }
        if (b4 != 0) {
            if ((n & 0x2) != 0x0) {
                n2 &= 0xD;
            }
            else {
                n2 |= 0x2;
            }
        }
        if (b5 != 0) {
            if ((n & 0x4) != 0x0) {
                n2 &= 0xB;
            }
            else {
                n2 |= 0x4;
            }
        }
        if (n2 == 0) {
            n2 = 8;
        }
        final int n3 = n2 * 100 + b2 * 10 + b;
        final byte b6 = this.d[this.a[9]];
        final byte b7 = this.d[this.a[10]];
        final byte b8 = (byte)(this.d[this.a[12]] & 0x8);
        final byte b9 = (byte)(this.d[this.a[14]] & 0x4);
        final byte b10 = (byte)(this.d[this.a[14]] & 0x8);
        int n4 = n;
        if (b8 != 0) {
            if ((n & 0x1) != 0x0) {
                n4 &= 0xE;
            }
            else {
                n4 |= 0x1;
            }
        }
        if (b9 != 0) {
            if ((n & 0x2) != 0x0) {
                n4 &= 0xD;
            }
            else {
                n4 |= 0x2;
            }
        }
        if (b10 != 0) {
            if ((n & 0x4) != 0x0) {
                n4 &= 0xB;
            }
            else {
                n4 |= 0x4;
            }
        }
        if (n4 == 0) {
            n4 = 8;
        }
        final int n5 = n4 * 100 + b7 * 10 + b6;
        final byte b11 = this.d[this.a[15]];
        final byte b12 = this.d[this.a[16]];
        final byte b13 = (byte)(this.d[this.a[18]] & 0x8);
        final byte b14 = (byte)(this.d[this.a[20]] & 0x4);
        final byte b15 = (byte)(this.d[this.a[20]] & 0x8);
        int n6 = n;
        if (b13 != 0) {
            if ((n & 0x1) != 0x0) {
                n6 &= 0xE;
            }
            else {
                n6 |= 0x1;
            }
        }
        if (b14 != 0) {
            if ((n & 0x2) != 0x0) {
                n6 &= 0xD;
            }
            else {
                n6 |= 0x2;
            }
        }
        if (b15 != 0) {
            if ((n & 0x4) != 0x0) {
                n6 &= 0xB;
            }
            else {
                n6 |= 0x4;
            }
        }
        if (n6 == 0) {
            n6 = 8;
        }
        final int n7 = n6 * 100 + b12 * 10 + b11;
        final byte b16 = this.d[this.a[21]];
        final byte b17 = this.d[this.a[22]];
        final byte b18 = (byte)(this.d[this.a[24]] & 0x8);
        final byte b19 = (byte)(this.d[this.a[26]] & 0x4);
        final byte b20 = (byte)(this.d[this.a[26]] & 0x8);
        int n8 = n;
        if (b18 != 0) {
            if ((n & 0x1) != 0x0) {
                n8 &= 0xE;
            }
            else {
                n8 |= 0x1;
            }
        }
        if (b19 != 0) {
            if ((n & 0x2) != 0x0) {
                n8 &= 0xD;
            }
            else {
                n8 |= 0x2;
            }
        }
        if (b20 != 0) {
            if ((n & 0x4) != 0x0) {
                n8 &= 0xB;
            }
            else {
                n8 |= 0x4;
            }
        }
        if (n8 == 0) {
            n8 = 8;
        }
        final int n9 = n8 * 100 + b17 * 10 + b16;
        for (int i = 960; i < 1000; ++i) {
            final byte b21 = this.c[i];
            this.b[i] = 0;
            if (b21 == 1 && n3 >= 100 && n3 <= 899) {
                this.b[i] = n3;
            }
            if (b21 == 2 && n5 >= 100 && n5 <= 899) {
                this.b[i] = n5;
            }
            if (b21 == 3 && n7 >= 100 && n7 <= 899) {
                this.b[i] = n7;
            }
            if (b21 == 6 && n9 >= 100 && n9 <= 899) {
                this.b[i] = n9;
            }
        }
    }
    
    public final boolean a(final int n) {
        return this.a(n, 0);
    }
    
    public final boolean a(final int a, final int n) {
        try {
            final String notifyUrl;
            if ((notifyUrl = ((TSSView)this.a).NotifyUrl()) != "") {
                final URLConnection openConnection;
                (openConnection = new URL(String.valueOf(notifyUrl) + "?page=" + a + "&subpagenr=" + n + "&Channeldir=" + ((TSSView)this.a).currentChannelDir()).openConnection()).connect();
                openConnection.getContent();
            }
        }
        catch (Exception ex) {
            System.err.println("Error reporting back" + ex.getMessage());
        }
        boolean b = false;
        final int a2 = this.a;
        final int b2 = this.b;
        try {
            final int b3 = n + 0;
            this.a = a;
            this.b = b3;
            this.b(this.a(a, n));
            ((TSSView)this.a).UpdateCurrentSubLabel(a, n, this.c);
            b = true;
        }
        catch (IOException ex2) {}
        this.f = !b;
        if (!b) {
            this.a.showStatus("page " + a + " sub " + (n + 1) + " not found");
            this.a = a2;
            this.b = b2;
            this.a.schedule(new y(this), 3000L);
        }
        this.repaint();
        return b;
    }
    
    private void j() {
        String string = "";
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        for (int i = 0; i < 960; ++i) {
            final char c = (char)this.a[i];
            switch (n) {
                case 0: {
                    if (c == 'w' || c == 'W') {
                        n = 1;
                        n2 = i;
                        break;
                    }
                    break;
                }
                case 1: {
                    if (c == 'w' || c == 'W') {
                        n = 2;
                        break;
                    }
                    n = 0;
                    break;
                }
                case 2: {
                    if (c == 'w' || c == 'W') {
                        n = 3;
                        break;
                    }
                    n = 0;
                    break;
                }
                case 3: {
                    if (c == '.') {
                        n = 4;
                        break;
                    }
                    n = 0;
                    break;
                }
                case 4: {
                    if (c == '.') {
                        n = 5;
                        break;
                    }
                    if (c <= ' ') {
                        n = 0;
                        break;
                    }
                    break;
                }
                case 5: {
                    if (c < '0' && c != '/') {
                        for (int n4 = i - 1, j = n2; j <= n4; ++j) {
                            this.b[j] = 1000 + n3;
                            string = String.valueOf(string) + (char)this.a[j];
                        }
                        this.a[n3] = string;
                        ++n3;
                        n = 0;
                        string = "";
                        break;
                    }
                    break;
                }
            }
        }
    }
    
    private void k() {
        int n = 0;
        int n2 = 0;
        final char[] array = new char[3];
        for (int i = 0; i < 960; ++i) {
            this.b[i] = 0;
            if (i % 40 == 0) {
                if (n == 4) {
                    n2 = 1;
                }
                n = 1;
            }
            if (" .-/".indexOf(this.a[i]) >= 0) {
                if (n == 4) {
                    n2 = 1;
                }
                n = 1;
            }
            else if (this.a[i] >= 48 && this.a[i] <= 57) {
                if (n == 1 && this.a[i] != 48) {
                    array[0] = (char)this.a[i];
                    ++n;
                }
                else if (n == 2 || n == 3) {
                    array[n - 1] = (char)this.a[i];
                    ++n;
                }
                else {
                    n = 0;
                }
            }
            else {
                n = 0;
            }
            if (n2 != 0) {
                final char c;
                if ((c = (char)((array[0] - '0') * 'd' + (array[1] - '0') * '\n' + (array[2] - '0'))) >= 'd' && c <= '\u0383') {
                    this.b[i - 3] = c;
                    this.b[i - 2] = c;
                    this.b[i - 1] = c;
                }
                n2 = 0;
            }
        }
        if (n == 4) {
            final char c2 = (char)((array[0] - '0') * 'd' + (array[1] - '0') * '\n' + (array[2] - '0'));
            this.b[957] = c2;
            this.b[958] = c2;
            this.b[959] = c2;
        }
    }
    
    private void l() {
        for (int i = 0; i < 960; ++i) {
            final char c;
            final char c2;
            if ((c = (char)this.a[i]) == '>' && (c2 = (char)this.a[i + 1]) == '>' && this.e >= 100 && this.e <= 899) {
                this.b[i] = this.e;
            }
            final char c3;
            if (c == '<' && (c3 = (char)this.a[i + 1]) == '<' && this.d >= 100 && this.d <= 899) {
                this.b[i] = this.d;
            }
            if (c == '/') {
                int n = (char)this.a[i - 1];
                int n2 = (char)this.a[i - 2];
                if (n == 32) {
                    n = n2;
                    n2 = 32;
                }
                if (n >= 48 && n <= 57) {
                    boolean b = false;
                    if (this.b + 1 < 10) {
                        if ((char)(n - 48) == this.b + 1) {
                            b = true;
                        }
                    }
                    else {
                        final char c4 = (char)(n - 48);
                        final char c5 = (char)(n2 - 48);
                        if (c4 == (this.b + 1) % 10 && c5 == (this.b + 1) / 10) {
                            b = true;
                        }
                    }
                    if (b) {
                        final char c6 = (char)this.a[i + 1];
                        final char c7 = (char)this.a[i + 2];
                        if (c6 >= '0' && c6 <= '9') {
                            int n3;
                            final char c8 = (char)(n3 = (char)(c6 - 48));
                            if (c7 >= '0' && c8 <= '9') {
                                n3 = n3 * 10 + (char)(c7 - '0');
                                this.b[i + 2] = n3;
                            }
                            this.b[i + 1] = n3;
                        }
                    }
                }
            }
        }
    }
    
    public final void b() {
        this.c = !this.c;
        if (this.c) {
            this.a.showStatus("conceal hidden text");
        }
        else {
            this.a.showStatus("reveal hidden text");
        }
        final Graphics graphics;
        if ((graphics = this.getGraphics()) == null) {
            return;
        }
        for (int i = 0; i < 1000; ++i) {
            if ((this.b[i] & 0x8) == 0x8 || (i >= 983 && i < 990)) {
                this.a(graphics, i);
            }
        }
    }
    
    public final void c() {
        if (this.a == null) {
            this.a = this.createImage(this.getSize().width, this.getSize().height);
        }
        final Graphics graphics;
        (graphics = this.a.getGraphics()).setClip(0, 0, this.getSize().width, this.getSize().height);
        this.d = !this.d;
        if (new GregorianCalendar().get(13) != this.m) {
            if (this.a) {
                ++this.l;
                if (this.l >= 10) {
                    if (this.a(this.a, this.b)) {
                        this.repaint();
                    }
                    this.l = 0;
                }
                ((TSSView)this.a).OnUpdateAutoRefreshButton(10 - this.l);
            }
            else {
                this.l = 0;
            }
            this.m();
        }
        if (this.e) {
            this.a(graphics);
            for (int i = 40; i < 1000; ++i) {
                this.a(graphics, i);
            }
        }
        final Graphics graphics2;
        if ((graphics2 = this.getGraphics()) == null) {
            return;
        }
        graphics2.drawImage(this.a, 0, 0, null);
        graphics.dispose();
    }
    
    private void a(final Graphics graphics) {
        for (int i = 0; i < 40; ++i) {
            if (this.f) {
                this.a.a(graphics, 1, 0, i % 40, i / 40, (byte)((i < this.a.length()) ? this.a.charAt(i % 40) : ' '), (byte)0, true, 0, false);
            }
            else {
                this.a(graphics, i);
            }
        }
    }
    
    private void a(final Graphics graphics, final int n) {
        this.a.a(graphics, (byte)(this.c[n] & 0xF), (byte)((this.c[n] & 0xF0) >> 4), n % 40, n / 40, this.a[n], this.b[n], ((this.b[n] & 0x8) != 0x8 || this.c) && ((this.b[n] & 0x10) != 0x10 || this.d), this.b[n], false);
    }
    
    public final void paint(final Graphics graphics) {
    }
    
    public final boolean b(final int n, final int n2) {
        boolean b = false;
        final Point a;
        if ((a = this.a.a(new Point(n, n2))).x > 39 || a.x < 0 || a.y > 24 || a.y < 0) {
            return true;
        }
        final int n3;
        if ((n3 = this.b[a.y * 40 + a.x]) == 0) {
            return true;
        }
        if (n3 >= 100 && n3 < 900) {
            b = this.a(n3);
        }
        else if (n3 >= 1 && n3 < 100) {
            b = this.a(this.a, n3 - 1);
        }
        else {
            if (n3 < 1000) {
                return true;
            }
            final String s = this.a[n3 - 1000];
            try {
                this.a.getAppletContext().showDocument(new URL("http://" + s), "_blank");
            }
            catch (Exception ex) {
                System.err.println("Exception:" + ex.toString());
            }
        }
        if (b) {
            this.k = -1;
            this.repaint();
            this.a.showStatus("");
        }
        return true;
    }
    
    public final boolean c(final int n, final int n2) {
        final Point a;
        if ((a = this.a.a(new Point(n, n2))).x > 39 || a.x < 0 || a.y > 24 || a.y < 0) {
            return true;
        }
        final int n3 = a.y * 40 + a.x;
        int k;
        int n4;
        for (k = this.b[n3], n4 = n3; n4 > 0 && this.b[n4] == k; --n4) {}
        int n5;
        for (n5 = ++n4; n4 < 1000 & this.b[n4] == k; ++n4) {}
        final int o;
        if ((o = n4) == this.o && n5 == this.n) {
            return true;
        }
        this.n = n5;
        this.o = o;
        final Graphics graphics;
        (graphics = this.a.getGraphics()).setClip(0, 0, this.getSize().width, this.getSize().height);
        for (int i = 0; i < 1000; ++i) {
            this.a(graphics, i);
        }
        if (k >= 1) {
            if (this.k >= 1) {
                int n6 = this.a.y * 40 + this.a.x;
                final byte b = this.c[n6];
                final int n7 = (b >> 4) + ((b & 0xF) << 4);
                while (this.b[n6] == this.k) {
                    --n6;
                }
                ++n6;
                while (this.b[n6] == this.k) {
                    this.c[n6] = (byte)n7;
                    this.a(n6);
                    ++n6;
                }
            }
            this.k = k;
            int n8 = a.y * 40 + a.x;
            final byte b2 = this.c[n8];
            final int n9 = (b2 >> 4) + ((b2 & 0xF) << 4);
            while (this.b[n8] == this.k) {
                --n8;
            }
            ++n8;
            while (this.b[n8] == this.k) {
                this.c[n8] = (byte)n9;
                this.a(n8);
                ++n8;
            }
            this.a = a;
        }
        if (k == 0) {
            this.a.showStatus("");
            if (this.k >= 1) {
                int n10 = this.a.y * 40 + this.a.x;
                final byte b3 = this.c[n10];
                final int n11 = (b3 >> 4) + ((b3 & 0xF) << 4);
                while (this.b[n10] == this.k) {
                    --n10;
                }
                ++n10;
                while (this.b[n10] == this.k) {
                    this.c[n10] = (byte)n11;
                    this.a(n10);
                    ++n10;
                }
            }
            this.k = 0;
        }
        else if (k >= 1 && k < 100) {
            this.a.showStatus("go to subpage " + k);
        }
        else if (k >= 100 && k <= 999) {
            this.a.showStatus("go to page " + k);
        }
        else if (k >= 1000) {
            this.a.showStatus("go to " + this.a[k - 1000]);
        }
        final Graphics graphics2;
        if ((graphics2 = this.getGraphics()) == null) {
            return true;
        }
        graphics2.drawImage(this.a, 0, 0, null);
        graphics.dispose();
        return true;
    }
    
    private void a(final int n) {
        if ((this.b[n] & 0x1) != 0x0) {
            this.c[n + 40] = this.c[n];
            return;
        }
        if ((this.b[n] & 0x2) != 0x0) {
            this.c[n - 40] = this.c[n];
        }
    }
    
    private void a(final KeyEvent keyEvent) {
        this.b(keyEvent.getKeyChar());
    }
    
    public final boolean b(final int n) {
        final char c;
        if (((c = (char)n) == '0' || c == '9') && this.h == 0) {
            return true;
        }
        if (c >= '0' && c <= '9') {
            if (this.f) {
                final Graphics graphics = this.getGraphics();
                this.f = false;
                this.a(graphics);
            }
            if (this.h == 2) {
                final char c2 = (char)((this.a[0] - '0') * 'd' + (this.a[1] - '0') * '\n' + (c - '0'));
                this.a[this.h] = c;
                this.a[4 + this.h] = (byte)c;
                this.h = 0;
                final Graphics graphics2 = this.getGraphics();
                this.a(graphics2, this.a[4]);
                this.a(graphics2, this.a[5]);
                this.a(graphics2, this.a[6]);
                final boolean a = this.a((int)c2);
                this.a[4] = 45;
                this.a[5] = 45;
                this.a[6] = 45;
                if (!a) {
                    this.a.showStatus("page " + (int)c2 + " not found");
                    final Graphics graphics3;
                    if ((graphics3 = this.getGraphics()) != null) {
                        this.a(graphics3, 4);
                        this.a(graphics3, 5);
                    }
                }
                else {
                    this.repaint();
                    this.a.showStatus("");
                }
            }
            else {
                this.a[this.h] = c;
                this.a[4 + this.h] = (byte)c;
                final Graphics graphics4;
                if ((graphics4 = this.getGraphics()) != null) {
                    this.a(graphics4, 4 + this.h);
                }
                ++this.h;
            }
        }
        return true;
    }
    
    public final void d() {
        if (this.b > 0) {
            --this.b;
            if (this.a(this.a, this.b)) {
                this.repaint();
                this.a.showStatus("");
                return;
            }
            ++this.b;
        }
    }
    
    public final void e() {
        if (this.b + 1 < this.c) {
            ++this.b;
            if (this.a(this.a, this.b)) {
                this.repaint();
                this.a.showStatus("");
                return;
            }
            --this.b;
        }
    }
    
    public final void f() {
        final int a = this.a;
        final int b = this.b;
        if (this.a > 100) {
            if (this.d >= 100) {
                this.a = this.d;
            }
            else {
                --this.a;
            }
            this.b = 0;
            if (this.a(this.a)) {
                this.repaint();
                this.a.showStatus("");
                return;
            }
            this.a = a;
            this.b = b;
        }
    }
    
    public final void g() {
        final int a = this.a;
        final int b = this.b;
        if (this.a < 899) {
            if (this.e >= 100) {
                this.a = this.e;
            }
            else {
                ++this.a;
            }
            this.b = 0;
            if (this.a(this.a)) {
                this.repaint();
                this.a.showStatus("");
                return;
            }
            this.a = a;
            this.b = b;
        }
    }
    
    public final boolean a() {
        this.e = !this.e;
        this.a.a = this.e;
        this.repaint();
        return this.e;
    }
    
    private void m() {
        final GregorianCalendar gregorianCalendar;
        final int value = (gregorianCalendar = new GregorianCalendar(TimeZone.getDefault())).get(11);
        final int value2 = gregorianCalendar.get(12);
        final int value3 = gregorianCalendar.get(13);
        final int value4 = gregorianCalendar.get(7);
        final int value5 = gregorianCalendar.get(2);
        final int value6 = gregorianCalendar.get(5);
        final String currentChannelName = ((TSSView)this.a).currentChannelName();
        for (int i = 0; i < currentChannelName.length(); ++i) {
            this.a[12 + i] = (byte)currentChannelName.charAt(i);
        }
        final String string = String.valueOf(c.a(value4, this.f)) + " " + String.valueOf(value6) + " " + c.b(value5, this.f);
        for (int j = 0; j < string.length(); ++j) {
            this.a[31 - string.length() + j] = (byte)string.charAt(j);
        }
        this.m = value3;
        this.a[39] = (byte)(value3 % 10 + 48);
        this.a[38] = (byte)(value3 / 10 + 48);
        if (value3 % 2 == 0) {
            this.a[37] = 58;
        }
        else {
            this.a[37] = 32;
        }
        this.a[36] = (byte)(value2 % 10 + 48);
        this.a[35] = (byte)(value2 / 10 + 48);
        this.a[34] = 58;
        this.a[33] = (byte)(value % 10 + 48);
        this.a[32] = (byte)(value / 10 + 48);
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void invalidate() {
        super.invalidate();
    }
    
    public static void a(final x x, final KeyEvent keyEvent) {
        x.a(keyEvent);
    }
    
    public static void a(final x x, final boolean f) {
        x.f = f;
    }
}
