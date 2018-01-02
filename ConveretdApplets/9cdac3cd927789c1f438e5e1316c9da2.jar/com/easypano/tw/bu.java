// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import com.easypano.tw.c.p;
import com.easypano.tw.c.j;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Component;
import com.easypano.tw.b.c;
import com.easypano.tw.b.d;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.LayoutManager;
import com.easypano.tw.a.b;
import com.easypano.tw.b.a;
import java.util.Hashtable;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

public final class bu extends Panel implements db
{
    public static final int a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
    public static final String e = "yB\r\u0011\u0007Ob\t\u0015\fO@";
    public static final String f = "iK\t\u001d\u001bNW\u0017$\u0019KK\u0000\u0006";
    public static final String g = "l^\u0004\u0000%FS\u001c\u0011\u0007";
    private Image h;
    private Graphics i;
    private Image j;
    private Image k;
    private Hashtable l;
    private volatile a m;
    private int n;
    private cj o;
    private volatile int p;
    private volatile ce q;
    private volatile int r;
    private volatile int s;
    private double t;
    private double u;
    private double v;
    private double w;
    private double x;
    private double y;
    private double z;
    private double A;
    private double B;
    private double C;
    private double D;
    private int E;
    private int F;
    private volatile boolean G;
    private volatile boolean H;
    private TWViewer I;
    private ef J;
    private cr K;
    private com.easypano.tw.a.a L;
    private b M;
    private volatile boolean N;
    private volatile boolean O;
    private du P;
    protected e Q;
    
    public bu(final TWViewer twViewer) {
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = new Hashtable();
        this.m = null;
        this.n = 2;
        this.o = null;
        this.p = -1;
        this.q = null;
        this.r = -1;
        this.s = 1;
        this.G = true;
        this.H = true;
        this.J = new ef(this);
        this.K = new cr();
        this.L = new com.easypano.tw.a.a();
        this.M = new b();
        this.N = true;
        this.O = false;
        this.P = null;
        this.Q = null;
        this.a(twViewer);
        try {
            this.a();
            this.b();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    void a() throws Exception {
        this.setLayout(null);
        final bc bc = new bc(this);
        this.addMouseListener(bc);
        this.addMouseMotionListener(bc);
        this.addMouseListener(new bb(this));
        this.addMouseMotionListener(new bl(this));
        this.addKeyListener(new t(this));
    }
    
    private void b() {
        this.l.put(b("yB\r\u0011\u0007Ob\t\u0015\fO@"), new d());
        this.l.put(b("iK\t\u001d\u001bNW\u0017$\u0019KK\u0000\u0006"), new com.easypano.tw.b.b());
        this.l.put(b("l^\u0004\u0000%FS\u001c\u0011\u0007"), new c());
        this.m = this.l.get(b("yB\r\u0011\u0007Ob\t\u0015\fO@"));
        this.K.a(this);
        this.J.setPriority(4);
        this.J.start();
    }
    
    public void destroyResource() {
        if (this.J != null) {
            this.J.a();
            this.J = null;
        }
        if (this.l != null) {
            this.l.get(b("yB\r\u0011\u0007Ob\t\u0015\fO@")).b();
            this.l.get(b("iK\t\u001d\u001bNW\u0017$\u0019KK\u0000\u0006")).b();
            this.l.get(b("l^\u0004\u0000%FS\u001c\u0011\u0007")).b();
            this.l.clear();
            this.l = null;
        }
        if (this.K != null) {
            this.K.f();
            this.K = null;
        }
        if (this.L != null) {
            this.L.b();
            this.L = null;
        }
        if (this.M != null) {
            this.M.b();
            this.M = null;
        }
        this.P = null;
        this.Q = null;
        this.j = null;
        this.k = null;
        this.I = null;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final boolean q = com.easypano.tw.g.q;
        final Image h = this.h;
        Label_0115: {
            bu bu = null;
            Label_0093: {
                if (!q) {
                    if (h == null) {
                        final Rectangle bounds = this.getBounds();
                        this.h = this.createImage(bounds.width, bounds.height);
                        this.i = this.h.getGraphics();
                    }
                    bu = this;
                    if (q) {
                        break Label_0093;
                    }
                    final Image j = this.j;
                }
                if (h != null) {
                    this.i.drawImage(this.j, 0, 0, this);
                    if (!q) {
                        break Label_0115;
                    }
                }
                this.i.setColor(this.getBackground());
                bu = this;
            }
            bu.i.fillRect(0, 0, this.getBounds().width, this.getBounds().height);
        }
        final dv[] l = this.m.l();
        int n = l.length - 1;
        while (true) {
            while (true) {
                Label_0150: {
                    if (!q) {
                        break Label_0150;
                    }
                    l[n].a(this.i);
                    --n;
                }
                if (n >= 0) {
                    continue;
                }
                break;
            }
            if (!q) {
                bu bu2 = this;
                if (!q) {
                    if (this.k != null) {
                        this.i.drawImage(this.k, 0, 0, this);
                    }
                    bu2 = this;
                }
                bu2.paint(this.i);
                graphics.drawImage(this.h, 0, 0, this);
                return;
            }
            continue;
        }
    }
    
    public void a(final du p) {
        this.P = p;
    }
    
    public void a(final String s) {
        this.a(s, Color.yellow);
    }
    
    public void a(final String s, final Color color) {
        this.a(s, color, null);
    }
    
    public void a(final String s, final Color background, final Color color) {
        final boolean q = com.easypano.tw.g.q;
        String s2 = s;
        Label_0034: {
            Label_0024: {
                if (!q) {
                    if (s == null) {
                        break Label_0024;
                    }
                    s2 = s;
                }
                if (!s2.equals("")) {
                    break Label_0034;
                }
            }
            this.Q = null;
            if (!q) {
                return;
            }
        }
        final g q2 = new g();
        q2.setBackground(background);
        final j j = new j(q2);
        j.d(color);
        q2.a(j);
        q2.e().a(s);
        this.Q = q2;
    }
    
    public void a(final e q) {
        final boolean q2 = com.easypano.tw.g.q;
        bu bu = null;
        Label_0052: {
            final e q3;
            Label_0048: {
                if (!q2) {
                    if (q != null) {
                        q3 = this.Q;
                        if (q2) {
                            break Label_0048;
                        }
                        if (q3 != null) {
                            final e q4 = this.Q;
                            if (q2) {
                                break Label_0048;
                            }
                            if (q4.equals(q)) {
                                return;
                            }
                        }
                    }
                    bu = this;
                    if (q2) {
                        break Label_0052;
                    }
                    this.Q = q;
                }
            }
            if (q3 != null) {
                return;
            }
            bu = this;
        }
        bu.P.a((Component)this);
    }
    
    public void a(final Image j) {
        (this.j = j).flush();
        this.repaint();
    }
    
    public void b(final Image k) {
        this.k = k;
    }
    
    public void a(final TWViewer i) {
        this.I = i;
        this.K.a(i);
    }
    
    private void a(final int n) {
        switch (n) {
            case 1:
            case 3: {
                this.n = n;
                if (com.easypano.tw.g.q) {
                    break;
                }
                return;
            }
        }
        this.n = 2;
    }
    
    public synchronized void a(final int n, final double n2, final double n3, final double n4) {
        this.a(n, n2, n3, n4, 0);
    }
    
    public synchronized void a(final int n, final double n2, final double n3, final double n4, final int n5) {
        try {
            this.a(n, n2, n3, n4, n5, false);
        }
        catch (Exception ex) {
            System.out.println(b("o@\u0017\u001b\u0007\n[\u000bT\u0006OF6\u0017\u0010DW"));
            ex.printStackTrace();
        }
        finally {
            this.J.b();
        }
        this.J.b();
    }
    
    public synchronized void a(final int n, final double n2, final double n3, final double n4, final int n5, final boolean b) throws Exception {
        try {
            this.a(n, n2, n3, n4, n5, this.I.h().g.a(n).w, false);
        }
        catch (Exception ex) {
            System.out.println(b("o@\u0017\u001b\u0007\n[\u000bT\u0006OF6\u0017\u0010DW"));
            ex.printStackTrace();
        }
    }
    
    public synchronized void a(final int p0, final double p1, final double p2, final double p3, final int p4, final int p5, final boolean p6) throws Exception {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       com/easypano/tw/g.q:Z
        //     3: istore          29
        //     5: aload_0        
        //     6: iconst_0       
        //     7: putfield        com/easypano/tw/bu.N:Z
        //    10: iload_1        
        //    11: iload           29
        //    13: ifne            1620
        //    16: iflt            1606
        //    19: iload_1        
        //    20: iload           29
        //    22: ifne            1620
        //    25: aload_0        
        //    26: getfield        com/easypano/tw/bu.I:Lcom/easypano/tw/TWViewer;
        //    29: invokevirtual   com/easypano/tw/TWViewer.h:()Lcom/easypano/tw/bz;
        //    32: getfield        com/easypano/tw/bz.g:Lcom/easypano/tw/ck;
        //    35: invokevirtual   com/easypano/tw/ck.a:()I
        //    38: if_icmpge       1606
        //    41: aload_0        
        //    42: getfield        com/easypano/tw/bu.G:Z
        //    45: iload           29
        //    47: ifne            1620
        //    50: ifeq            1606
        //    53: iload           10
        //    55: iload           29
        //    57: ifne            93
        //    60: ifne            84
        //    63: aload_0        
        //    64: getfield        com/easypano/tw/bu.J:Lcom/easypano/tw/ef;
        //    67: invokevirtual   com/easypano/tw/ef.d:()V
        //    70: aload_0        
        //    71: getfield        com/easypano/tw/bu.J:Lcom/easypano/tw/ef;
        //    74: invokevirtual   com/easypano/tw/ef.h:()V
        //    77: aload_0        
        //    78: getfield        com/easypano/tw/bu.J:Lcom/easypano/tw/ef;
        //    81: invokevirtual   com/easypano/tw/ef.c:()V
        //    84: aload_0        
        //    85: iload           29
        //    87: ifne            1534
        //    90: getfield        com/easypano/tw/bu.p:I
        //    93: iload_1        
        //    94: if_icmpeq       1516
        //    97: iconst_0       
        //    98: istore          13
        //   100: aload_0        
        //   101: getfield        com/easypano/tw/bu.m:Lcom/easypano/tw/b/a;
        //   104: astore          14
        //   106: aload_0        
        //   107: aload_0        
        //   108: getfield        com/easypano/tw/bu.I:Lcom/easypano/tw/TWViewer;
        //   111: invokevirtual   com/easypano/tw/TWViewer.h:()Lcom/easypano/tw/bz;
        //   114: getfield        com/easypano/tw/bz.g:Lcom/easypano/tw/ck;
        //   117: iload_1        
        //   118: invokevirtual   com/easypano/tw/ck.a:(I)Lcom/easypano/tw/cj;
        //   121: putfield        com/easypano/tw/bu.o:Lcom/easypano/tw/cj;
        //   124: aload_0        
        //   125: getfield        com/easypano/tw/bu.I:Lcom/easypano/tw/TWViewer;
        //   128: invokevirtual   com/easypano/tw/TWViewer.h:()Lcom/easypano/tw/bz;
        //   131: getfield        com/easypano/tw/bz.d:Lcom/easypano/tw/ch;
        //   134: aload_0        
        //   135: getfield        com/easypano/tw/bu.o:Lcom/easypano/tw/cj;
        //   138: getfield        com/easypano/tw/cj.k:Ljava/net/URL;
        //   141: iconst_1       
        //   142: invokevirtual   com/easypano/tw/ch.a:(Ljava/lang/Object;I)Ljava/lang/Object;
        //   145: checkcast       Ljava/awt/Image;
        //   148: astore          15
        //   150: aload           15
        //   152: iload           29
        //   154: ifne            162
        //   157: ifnull          177
        //   160: aload           15
        //   162: getstatic       com/easypano/tw/dt.d:Ljava/awt/Component;
        //   165: invokevirtual   java/awt/Image.getWidth:(Ljava/awt/image/ImageObserver;)I
        //   168: iconst_m1      
        //   169: iload           29
        //   171: ifne            251
        //   174: if_icmpne       241
        //   177: iload           10
        //   179: iload           29
        //   181: ifne            208
        //   184: ifne            194
        //   187: aload_0        
        //   188: getfield        com/easypano/tw/bu.J:Lcom/easypano/tw/ef;
        //   191: invokevirtual   com/easypano/tw/ef.b:()V
        //   194: aload_0        
        //   195: getfield        com/easypano/tw/bu.J:Lcom/easypano/tw/ef;
        //   198: invokevirtual   com/easypano/tw/ef.b:()V
        //   201: iload           29
        //   203: ifne            217
        //   206: iload           10
        //   208: ifne            240
        //   211: ldc2_w          50
        //   214: invokestatic    java/lang/Thread.sleep:(J)V
        //   217: goto            227
        //   220: astore          28
        //   222: aload           28
        //   224: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //   227: aload_0        
        //   228: iload           9
        //   230: iconst_0       
        //   231: iconst_0       
        //   232: invokevirtual   com/easypano/tw/bu.a:(III)V
        //   235: aload_0        
        //   236: iconst_1       
        //   237: invokevirtual   com/easypano/tw/bu.a:(Z)V
        //   240: return         
        //   241: aload_0        
        //   242: getfield        com/easypano/tw/bu.p:I
        //   245: iload           29
        //   247: ifne            303
        //   250: iconst_m1      
        //   251: if_icmpeq       286
        //   254: aload_0        
        //   255: getfield        com/easypano/tw/bu.I:Lcom/easypano/tw/TWViewer;
        //   258: aload_0        
        //   259: getfield        com/easypano/tw/bu.p:I
        //   262: aload_0        
        //   263: getfield        com/easypano/tw/bu.m:Lcom/easypano/tw/b/a;
        //   266: invokevirtual   com/easypano/tw/b/a.d:()D
        //   269: aload_0        
        //   270: getfield        com/easypano/tw/bu.m:Lcom/easypano/tw/b/a;
        //   273: invokevirtual   com/easypano/tw/b/a.f:()D
        //   276: aload_0        
        //   277: getfield        com/easypano/tw/bu.m:Lcom/easypano/tw/b/a;
        //   280: invokevirtual   com/easypano/tw/b/a.e:()D
        //   283: invokevirtual   com/easypano/tw/TWViewer.actionOnPanoSwitching:(IDDD)V
        //   286: aload_0        
        //   287: getfield        com/easypano/tw/bu.I:Lcom/easypano/tw/TWViewer;
        //   290: invokevirtual   com/easypano/tw/TWViewer.h:()Lcom/easypano/tw/bz;
        //   293: getfield        com/easypano/tw/bz.g:Lcom/easypano/tw/ck;
        //   296: iload_1        
        //   297: invokevirtual   com/easypano/tw/ck.a:(I)Lcom/easypano/tw/cj;
        //   300: getfield        com/easypano/tw/cj.g:I
        //   303: istore          16
        //   305: iload           8
        //   307: iload           29
        //   309: ifne            608
        //   312: ifle            588
        //   315: iload           16
        //   317: iload           29
        //   319: ifne            353
        //   322: tableswitch {
        //                2: 344
        //                3: 344
        //          default: 349
        //        }
        //   344: iload           29
        //   346: ifeq            588
        //   349: aload_0        
        //   350: getfield        com/easypano/tw/bu.p:I
        //   353: iload           29
        //   355: ifne            586
        //   358: iconst_m1      
        //   359: if_icmpeq       585
        //   362: aload_0        
        //   363: getfield        com/easypano/tw/bu.m:Lcom/easypano/tw/b/a;
        //   366: invokevirtual   com/easypano/tw/b/a.e:()D
        //   369: dstore          17
        //   371: aload_0        
        //   372: getfield        com/easypano/tw/bu.n:I
        //   375: iload           29
        //   377: ifne            423
        //   380: tableswitch {
        //                4: 404
        //                5: 404
        //          default: 414
        //        }
        //   404: ldc2_w          0.7853999733924866
        //   407: dstore          19
        //   409: iload           29
        //   411: ifeq            426
        //   414: aload_0        
        //   415: invokevirtual   java/awt/Component.getBounds:()Ljava/awt/Rectangle;
        //   418: getfield        java/awt/Rectangle.width:I
        //   421: iconst_2       
        //   422: idiv           
        //   423: i2d            
        //   424: dstore          19
        //   426: invokestatic    java/lang/System.currentTimeMillis:()J
        //   429: lstore          11
        //   431: aload_0        
        //   432: invokevirtual   java/awt/Component.getGraphics:()Ljava/awt/Graphics;
        //   435: astore          23
        //   437: aload_0        
        //   438: invokevirtual   java/awt/Component.getBounds:()Ljava/awt/Rectangle;
        //   441: astore          24
        //   443: iload           29
        //   445: ifeq            545
        //   448: invokestatic    java/lang/System.currentTimeMillis:()J
        //   451: lload           11
        //   453: lsub           
        //   454: ldc2_w          100
        //   457: lmul           
        //   458: iload           8
        //   460: i2l            
        //   461: ldiv           
        //   462: l2i            
        //   463: istore          13
        //   465: iload           13
        //   467: iload           29
        //   469: ifne            479
        //   472: bipush          100
        //   474: if_icmple       481
        //   477: bipush          100
        //   479: istore          13
        //   481: dload           17
        //   483: bipush          100
        //   485: iload           13
        //   487: isub           
        //   488: i2d            
        //   489: dmul           
        //   490: dload           19
        //   492: iload           13
        //   494: i2d            
        //   495: dmul           
        //   496: dadd           
        //   497: d2f            
        //   498: ldc_w           100.0
        //   501: fdiv           
        //   502: f2d            
        //   503: dstore          21
        //   505: aload_0        
        //   506: getfield        com/easypano/tw/bu.m:Lcom/easypano/tw/b/a;
        //   509: dload           21
        //   511: invokevirtual   com/easypano/tw/b/a.f:(D)V
        //   514: aload_0        
        //   515: getfield        com/easypano/tw/bu.m:Lcom/easypano/tw/b/a;
        //   518: iconst_1       
        //   519: invokevirtual   com/easypano/tw/b/a.a:(I)I
        //   522: pop            
        //   523: aload_0        
        //   524: aload           23
        //   526: invokevirtual   com/easypano/tw/bu.paint:(Ljava/awt/Graphics;)V
        //   529: ldc2_w          20
        //   532: invokestatic    java/lang/Thread.sleep:(J)V
        //   535: goto            545
        //   538: astore          25
        //   540: aload           25
        //   542: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //   545: iload           13
        //   547: bipush          100
        //   549: if_icmplt       448
        //   552: goto            575
        //   555: astore          25
        //   557: aload           25
        //   559: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //   562: goto            575
        //   565: astore          26
        //   567: aload           23
        //   569: invokevirtual   java/awt/Graphics.dispose:()V
        //   572: aload           26
        //   574: athrow         
        //   575: aload           23
        //   577: invokevirtual   java/awt/Graphics.dispose:()V
        //   580: iload           29
        //   582: ifeq            588
        //   585: iconst_1       
        //   586: istore          16
        //   588: aload_0        
        //   589: iload_1        
        //   590: putfield        com/easypano/tw/bu.p:I
        //   593: aload_0        
        //   594: aload_0        
        //   595: getfield        com/easypano/tw/bu.o:Lcom/easypano/tw/cj;
        //   598: getfield        com/easypano/tw/cj.d:I
        //   601: invokespecial   com/easypano/tw/bu.a:(I)V
        //   604: aload_0        
        //   605: getfield        com/easypano/tw/bu.n:I
        //   608: iload           29
        //   610: ifne            649
        //   613: tableswitch {
        //                2: 785
        //                3: 640
        //                4: 640
        //          default: 900
        //        }
        //   640: aload_0        
        //   641: iload           29
        //   643: ifne            678
        //   646: getfield        com/easypano/tw/bu.n:I
        //   649: iconst_3       
        //   650: if_icmpne       677
        //   653: aload_0        
        //   654: aload_0        
        //   655: getfield        com/easypano/tw/bu.l:Ljava/util/Hashtable;
        //   658: ldc             "iK\t\u001d\u001bNW\u0017$\u0019KK\u0000\u0006"
        //   660: invokestatic    com/easypano/tw/bu.b:(Ljava/lang/String;)Ljava/lang/String;
        //   663: invokevirtual   java/util/Hashtable.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   666: checkcast       Lcom/easypano/tw/b/a;
        //   669: putfield        com/easypano/tw/bu.m:Lcom/easypano/tw/b/a;
        //   672: iload           29
        //   674: ifeq            696
        //   677: aload_0        
        //   678: aload_0        
        //   679: getfield        com/easypano/tw/bu.l:Ljava/util/Hashtable;
        //   682: ldc             "yB\r\u0011\u0007Ob\t\u0015\fO@"
        //   684: invokestatic    com/easypano/tw/bu.b:(Ljava/lang/String;)Ljava/lang/String;
        //   687: invokevirtual   java/util/Hashtable.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   690: checkcast       Lcom/easypano/tw/b/a;
        //   693: putfield        com/easypano/tw/bu.m:Lcom/easypano/tw/b/a;
        //   696: aload_0        
        //   697: aload_0        
        //   698: aload_0        
        //   699: aload_0        
        //   700: getfield        com/easypano/tw/bu.m:Lcom/easypano/tw/b/a;
        //   703: invokevirtual   com/easypano/tw/b/a.g:()D
        //   706: ldc2_w          1000.0
        //   709: dmul           
        //   710: aload_0        
        //   711: getfield        com/easypano/tw/bu.J:Lcom/easypano/tw/ef;
        //   714: invokevirtual   com/easypano/tw/ef.j:()I
        //   717: i2d            
        //   718: ddiv           
        //   719: dup2_x1        
        //   720: putfield        com/easypano/tw/bu.v:D
        //   723: dup2_x1        
        //   724: putfield        com/easypano/tw/bu.u:D
        //   727: putfield        com/easypano/tw/bu.t:D
        //   730: aload_0        
        //   731: aload_0        
        //   732: aload_0        
        //   733: aload_0        
        //   734: getfield        com/easypano/tw/bu.m:Lcom/easypano/tw/b/a;
        //   737: invokevirtual   com/easypano/tw/b/a.g:()D
        //   740: dup2_x1        
        //   741: putfield        com/easypano/tw/bu.y:D
        //   744: dup2_x1        
        //   745: putfield        com/easypano/tw/bu.x:D
        //   748: putfield        com/easypano/tw/bu.w:D
        //   751: aload_0        
        //   752: aload_0        
        //   753: ldc2_w          3.141592653589793
        //   756: dup2_x1        
        //   757: putfield        com/easypano/tw/bu.A:D
        //   760: putfield        com/easypano/tw/bu.z:D
        //   763: aload_0        
        //   764: aload_0        
        //   765: aload_0        
        //   766: ldc2_w          0.017453292519943295
        //   769: dup2_x1        
        //   770: putfield        com/easypano/tw/bu.D:D
        //   773: dup2_x1        
        //   774: putfield        com/easypano/tw/bu.C:D
        //   777: putfield        com/easypano/tw/bu.B:D
        //   780: iload           29
        //   782: ifeq            900
        //   785: aload_0        
        //   786: aload_0        
        //   787: getfield        com/easypano/tw/bu.l:Ljava/util/Hashtable;
        //   790: ldc             "l^\u0004\u0000%FS\u001c\u0011\u0007"
        //   792: invokestatic    com/easypano/tw/bu.b:(Ljava/lang/String;)Ljava/lang/String;
        //   795: invokevirtual   java/util/Hashtable.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   798: checkcast       Lcom/easypano/tw/b/a;
        //   801: putfield        com/easypano/tw/bu.m:Lcom/easypano/tw/b/a;
        //   804: aload_0        
        //   805: aload_0        
        //   806: aload_0        
        //   807: aload_0        
        //   808: getfield        com/easypano/tw/bu.m:Lcom/easypano/tw/b/a;
        //   811: invokevirtual   com/easypano/tw/b/a.g:()D
        //   814: ldc2_w          1000.0
        //   817: dmul           
        //   818: aload_0        
        //   819: getfield        com/easypano/tw/bu.J:Lcom/easypano/tw/ef;
        //   822: invokevirtual   com/easypano/tw/ef.j:()I
        //   825: i2d            
        //   826: ddiv           
        //   827: dup2_x1        
        //   828: putfield        com/easypano/tw/bu.v:D
        //   831: dup2_x1        
        //   832: putfield        com/easypano/tw/bu.u:D
        //   835: putfield        com/easypano/tw/bu.t:D
        //   838: aload_0        
        //   839: aload_0        
        //   840: aload_0        
        //   841: aload_0        
        //   842: getfield        com/easypano/tw/bu.m:Lcom/easypano/tw/b/a;
        //   845: invokevirtual   com/easypano/tw/b/a.g:()D
        //   848: dup2_x1        
        //   849: putfield        com/easypano/tw/bu.y:D
        //   852: dup2_x1        
        //   853: putfield        com/easypano/tw/bu.x:D
        //   856: putfield        com/easypano/tw/bu.w:D
        //   859: aload_0        
        //   860: aload_0        
        //   861: aload_0        
        //   862: invokevirtual   java/awt/Component.getBounds:()Ljava/awt/Rectangle;
        //   865: getfield        java/awt/Rectangle.width:I
        //   868: i2d            
        //   869: dup2_x1        
        //   870: putfield        com/easypano/tw/bu.A:D
        //   873: putfield        com/easypano/tw/bu.z:D
        //   876: aload_0        
        //   877: aload_0        
        //   878: getfield        com/easypano/tw/bu.w:D
        //   881: putfield        com/easypano/tw/bu.B:D
        //   884: aload_0        
        //   885: aload_0        
        //   886: getfield        com/easypano/tw/bu.x:D
        //   889: putfield        com/easypano/tw/bu.C:D
        //   892: aload_0        
        //   893: aload_0        
        //   894: getfield        com/easypano/tw/bu.y:D
        //   897: putfield        com/easypano/tw/bu.D:D
        //   900: aload           14
        //   902: iload           29
        //   904: ifne            971
        //   907: ifnull          915
        //   910: aload           14
        //   912: invokevirtual   com/easypano/tw/b/a.c:()V
        //   915: aload_0        
        //   916: getfield        com/easypano/tw/bu.m:Lcom/easypano/tw/b/a;
        //   919: aload_0        
        //   920: getfield        com/easypano/tw/bu.o:Lcom/easypano/tw/cj;
        //   923: aload           15
        //   925: aload_0        
        //   926: invokevirtual   com/easypano/tw/b/a.a:(Lcom/easypano/tw/cj;Ljava/awt/Image;Ljava/awt/Container;)V
        //   929: aload_0        
        //   930: getfield        com/easypano/tw/bu.m:Lcom/easypano/tw/b/a;
        //   933: aload_0        
        //   934: invokevirtual   java/awt/Component.getBounds:()Ljava/awt/Rectangle;
        //   937: getfield        java/awt/Rectangle.width:I
        //   940: aload_0        
        //   941: invokevirtual   java/awt/Component.getBounds:()Ljava/awt/Rectangle;
        //   944: getfield        java/awt/Rectangle.height:I
        //   947: invokevirtual   com/easypano/tw/b/a.a:(II)V
        //   950: aload_0        
        //   951: getfield        com/easypano/tw/bu.m:Lcom/easypano/tw/b/a;
        //   954: dload_2        
        //   955: invokevirtual   com/easypano/tw/b/a.c:(D)V
        //   958: aload_0        
        //   959: getfield        com/easypano/tw/bu.m:Lcom/easypano/tw/b/a;
        //   962: dload           4
        //   964: invokevirtual   com/easypano/tw/b/a.i:(D)V
        //   967: aload_0        
        //   968: getfield        com/easypano/tw/bu.m:Lcom/easypano/tw/b/a;
        //   971: dload           6
        //   973: invokevirtual   com/easypano/tw/b/a.f:(D)V
        //   976: iload           8
        //   978: iload           29
        //   980: ifne            1482
        //   983: ifle            1451
        //   986: iload           16
        //   988: iload           29
        //   990: ifne            1482
        //   993: iconst_3       
        //   994: if_icmpeq       1451
        //   997: iload           16
        //   999: iload           29
        //  1001: ifne            1065
        //  1004: tableswitch {
        //                2: 1028
        //                3: 1044
        //          default: 1058
        //        }
        //  1028: aload_0        
        //  1029: getfield        com/easypano/tw/bu.K:Lcom/easypano/tw/cr;
        //  1032: aload_0        
        //  1033: getfield        com/easypano/tw/bu.L:Lcom/easypano/tw/a/a;
        //  1036: invokevirtual   com/easypano/tw/cr.a:(Lcom/easypano/tw/a/c;)V
        //  1039: iload           29
        //  1041: ifeq            1058
        //  1044: aload_0        
        //  1045: getfield        com/easypano/tw/bu.K:Lcom/easypano/tw/cr;
        //  1048: aload_0        
        //  1049: getfield        com/easypano/tw/bu.M:Lcom/easypano/tw/a/b;
        //  1052: invokevirtual   com/easypano/tw/cr.a:(Lcom/easypano/tw/a/c;)V
        //  1055: goto            1058
        //  1058: aload_0        
        //  1059: invokevirtual   java/awt/Component.getBounds:()Ljava/awt/Rectangle;
        //  1062: getfield        java/awt/Rectangle.width:I
        //  1065: istore          17
        //  1067: aload_0        
        //  1068: invokevirtual   java/awt/Component.getBounds:()Ljava/awt/Rectangle;
        //  1071: getfield        java/awt/Rectangle.height:I
        //  1074: istore          18
        //  1076: aconst_null    
        //  1077: astore          19
        //  1079: iload           17
        //  1081: iload           18
        //  1083: imul           
        //  1084: newarray        I
        //  1086: astore          20
        //  1088: aload_0        
        //  1089: iload           29
        //  1091: ifne            1101
        //  1094: getfield        com/easypano/tw/bu.j:Ljava/awt/Image;
        //  1097: ifnonnull       1144
        //  1100: aload_0        
        //  1101: invokevirtual   java/awt/Component.getBackground:()Ljava/awt/Color;
        //  1104: invokevirtual   java/awt/Color.getRGB:()I
        //  1107: istore          21
        //  1109: iload           17
        //  1111: iload           18
        //  1113: imul           
        //  1114: istore          22
        //  1116: iconst_0       
        //  1117: istore          23
        //  1119: iload           29
        //  1121: ifeq            1134
        //  1124: aload           20
        //  1126: iload           23
        //  1128: iload           21
        //  1130: iastore        
        //  1131: iinc            23, 1
        //  1134: iload           23
        //  1136: iload           22
        //  1138: if_icmplt       1124
        //  1141: goto            1184
        //  1144: new             Ljava/awt/image/PixelGrabber;
        //  1147: dup            
        //  1148: aload_0        
        //  1149: getfield        com/easypano/tw/bu.j:Ljava/awt/Image;
        //  1152: iconst_0       
        //  1153: iconst_0       
        //  1154: iload           17
        //  1156: iload           18
        //  1158: aload           20
        //  1160: iconst_0       
        //  1161: iload           17
        //  1163: invokespecial   java/awt/image/PixelGrabber.<init>:(Ljava/awt/Image;IIII[III)V
        //  1166: astore          21
        //  1168: aload           21
        //  1170: invokevirtual   java/awt/image/PixelGrabber.grabPixels:()Z
        //  1173: pop            
        //  1174: goto            1184
        //  1177: astore          22
        //  1179: aload           22
        //  1181: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //  1184: new             Ljava/awt/image/MemoryImageSource;
        //  1187: dup            
        //  1188: iload           17
        //  1190: iload           18
        //  1192: aload           20
        //  1194: iconst_0       
        //  1195: iload           17
        //  1197: invokespecial   java/awt/image/MemoryImageSource.<init>:(II[III)V
        //  1200: astore          21
        //  1202: aload_0        
        //  1203: aload           21
        //  1205: invokevirtual   java/awt/Component.createImage:(Ljava/awt/image/ImageProducer;)Ljava/awt/Image;
        //  1208: astore          19
        //  1210: aload           19
        //  1212: invokestatic    com/easypano/tw/dt.a:(Ljava/awt/Image;)V
        //  1215: aload_0        
        //  1216: getfield        com/easypano/tw/bu.m:Lcom/easypano/tw/b/a;
        //  1219: invokevirtual   com/easypano/tw/b/a.k:()V
        //  1222: aload_0        
        //  1223: getfield        com/easypano/tw/bu.K:Lcom/easypano/tw/cr;
        //  1226: aload           19
        //  1228: aload_0        
        //  1229: getfield        com/easypano/tw/bu.m:Lcom/easypano/tw/b/a;
        //  1232: invokevirtual   com/easypano/tw/b/a.a:()Ljava/awt/Image;
        //  1235: invokevirtual   com/easypano/tw/cr.a:(Ljava/awt/Image;Ljava/awt/Image;)V
        //  1238: aload_0        
        //  1239: getfield        com/easypano/tw/bu.K:Lcom/easypano/tw/cr;
        //  1242: aload_0        
        //  1243: invokevirtual   java/awt/Component.getBounds:()Ljava/awt/Rectangle;
        //  1246: invokevirtual   java/awt/Rectangle.getSize:()Ljava/awt/Dimension;
        //  1249: invokevirtual   com/easypano/tw/cr.a:(Ljava/awt/Dimension;)V
        //  1252: aload_0        
        //  1253: getfield        com/easypano/tw/bu.K:Lcom/easypano/tw/cr;
        //  1256: invokevirtual   com/easypano/tw/cr.a:()V
        //  1259: aload_0        
        //  1260: getfield        com/easypano/tw/bu.K:Lcom/easypano/tw/cr;
        //  1263: iconst_0       
        //  1264: invokevirtual   com/easypano/tw/cr.a:(I)V
        //  1267: aload_0        
        //  1268: aload_0        
        //  1269: getfield        com/easypano/tw/bu.K:Lcom/easypano/tw/cr;
        //  1272: invokevirtual   com/easypano/tw/cr.b:()Ljava/awt/Image;
        //  1275: invokevirtual   com/easypano/tw/bu.a:(Ljava/awt/Image;)V
        //  1278: aload_0        
        //  1279: invokevirtual   java/awt/Component.getGraphics:()Ljava/awt/Graphics;
        //  1282: astore          22
        //  1284: aload_0        
        //  1285: invokevirtual   java/awt/Component.getBounds:()Ljava/awt/Rectangle;
        //  1288: astore          23
        //  1290: invokestatic    java/lang/System.currentTimeMillis:()J
        //  1293: lstore          11
        //  1295: iload           29
        //  1297: ifeq            1409
        //  1300: invokestatic    java/lang/System.currentTimeMillis:()J
        //  1303: lload           11
        //  1305: lsub           
        //  1306: ldc2_w          100
        //  1309: lmul           
        //  1310: iload           8
        //  1312: i2l            
        //  1313: ldiv           
        //  1314: l2i            
        //  1315: istore          13
        //  1317: iload           13
        //  1319: iload           29
        //  1321: ifne            1354
        //  1324: bipush          100
        //  1326: if_icmple       1333
        //  1329: bipush          100
        //  1331: istore          13
        //  1333: aload_0        
        //  1334: getfield        com/easypano/tw/bu.K:Lcom/easypano/tw/cr;
        //  1337: iload           13
        //  1339: invokevirtual   com/easypano/tw/cr.a:(I)V
        //  1342: iload           29
        //  1344: ifne            1399
        //  1347: aload_0        
        //  1348: getfield        com/easypano/tw/bu.K:Lcom/easypano/tw/cr;
        //  1351: invokevirtual   com/easypano/tw/cr.d:()I
        //  1354: tableswitch {
        //                2: 1376
        //                3: 1387
        //          default: 1393
        //        }
        //  1376: aload_0        
        //  1377: aload           22
        //  1379: invokevirtual   com/easypano/tw/bu.paint:(Ljava/awt/Graphics;)V
        //  1382: iload           29
        //  1384: ifeq            1393
        //  1387: aload_0        
        //  1388: aload           22
        //  1390: invokevirtual   com/easypano/tw/bu.paint:(Ljava/awt/Graphics;)V
        //  1393: ldc2_w          20
        //  1396: invokestatic    java/lang/Thread.sleep:(J)V
        //  1399: goto            1409
        //  1402: astore          24
        //  1404: aload           24
        //  1406: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //  1409: iload           13
        //  1411: bipush          100
        //  1413: if_icmplt       1300
        //  1416: goto            1439
        //  1419: astore          24
        //  1421: aload           24
        //  1423: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //  1426: goto            1439
        //  1429: astore          25
        //  1431: aload           22
        //  1433: invokevirtual   java/awt/Graphics.dispose:()V
        //  1436: aload           25
        //  1438: athrow         
        //  1439: aload           22
        //  1441: invokevirtual   java/awt/Graphics.dispose:()V
        //  1444: aload_0        
        //  1445: getfield        com/easypano/tw/bu.K:Lcom/easypano/tw/cr;
        //  1448: invokevirtual   com/easypano/tw/cr.e:()V
        //  1451: aload_0        
        //  1452: getfield        com/easypano/tw/bu.I:Lcom/easypano/tw/TWViewer;
        //  1455: iload_1        
        //  1456: invokevirtual   com/easypano/tw/TWViewer.actionOnPanoSwitched:(I)V
        //  1459: aload_0        
        //  1460: aload_0        
        //  1461: getfield        com/easypano/tw/bu.m:Lcom/easypano/tw/b/a;
        //  1464: invokevirtual   com/easypano/tw/b/a.a:()Ljava/awt/Image;
        //  1467: putfield        com/easypano/tw/bu.j:Ljava/awt/Image;
        //  1470: aload_0        
        //  1471: invokevirtual   java/awt/Component.repaint:()V
        //  1474: aload_0        
        //  1475: getfield        com/easypano/tw/bu.m:Lcom/easypano/tw/b/a;
        //  1478: iconst_1       
        //  1479: invokevirtual   com/easypano/tw/b/a.a:(I)I
        //  1482: pop            
        //  1483: aload_0        
        //  1484: getfield        com/easypano/tw/bu.I:Lcom/easypano/tw/TWViewer;
        //  1487: aload_0        
        //  1488: getfield        com/easypano/tw/bu.m:Lcom/easypano/tw/b/a;
        //  1491: invokevirtual   com/easypano/tw/b/a.d:()D
        //  1494: aload_0        
        //  1495: getfield        com/easypano/tw/bu.m:Lcom/easypano/tw/b/a;
        //  1498: invokevirtual   com/easypano/tw/b/a.f:()D
        //  1501: aload_0        
        //  1502: getfield        com/easypano/tw/bu.m:Lcom/easypano/tw/b/a;
        //  1505: invokevirtual   com/easypano/tw/b/a.e:()D
        //  1508: invokevirtual   com/easypano/tw/TWViewer.actionOnViewPortChanged:(DDD)V
        //  1511: iload           29
        //  1513: ifeq            1606
        //  1516: aload_0        
        //  1517: getfield        com/easypano/tw/bu.m:Lcom/easypano/tw/b/a;
        //  1520: dload_2        
        //  1521: invokevirtual   com/easypano/tw/b/a.c:(D)V
        //  1524: aload_0        
        //  1525: getfield        com/easypano/tw/bu.m:Lcom/easypano/tw/b/a;
        //  1528: dload           4
        //  1530: invokevirtual   com/easypano/tw/b/a.i:(D)V
        //  1533: aload_0        
        //  1534: getfield        com/easypano/tw/bu.m:Lcom/easypano/tw/b/a;
        //  1537: dload           6
        //  1539: invokevirtual   com/easypano/tw/b/a.f:(D)V
        //  1542: goto            1606
        //  1545: astore          11
        //  1547: aload           11
        //  1549: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //  1552: goto            1606
        //  1555: astore          27
        //  1557: aload_0        
        //  1558: getfield        com/easypano/tw/bu.J:Lcom/easypano/tw/ef;
        //  1561: invokevirtual   com/easypano/tw/ef.b:()V
        //  1564: iload           29
        //  1566: ifne            1580
        //  1569: iload           10
        //  1571: ifne            1603
        //  1574: ldc2_w          50
        //  1577: invokestatic    java/lang/Thread.sleep:(J)V
        //  1580: goto            1590
        //  1583: astore          28
        //  1585: aload           28
        //  1587: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //  1590: aload_0        
        //  1591: iload           9
        //  1593: iconst_0       
        //  1594: iconst_0       
        //  1595: invokevirtual   com/easypano/tw/bu.a:(III)V
        //  1598: aload_0        
        //  1599: iconst_1       
        //  1600: invokevirtual   com/easypano/tw/bu.a:(Z)V
        //  1603: aload           27
        //  1605: athrow         
        //  1606: aload_0        
        //  1607: getfield        com/easypano/tw/bu.J:Lcom/easypano/tw/ef;
        //  1610: invokevirtual   com/easypano/tw/ef.b:()V
        //  1613: iload           29
        //  1615: ifne            1629
        //  1618: iload           10
        //  1620: ifne            1652
        //  1623: ldc2_w          50
        //  1626: invokestatic    java/lang/Thread.sleep:(J)V
        //  1629: goto            1639
        //  1632: astore          28
        //  1634: aload           28
        //  1636: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //  1639: aload_0        
        //  1640: iload           9
        //  1642: iconst_0       
        //  1643: iconst_0       
        //  1644: invokevirtual   com/easypano/tw/bu.a:(III)V
        //  1647: aload_0        
        //  1648: iconst_1       
        //  1649: invokevirtual   com/easypano/tw/bu.a:(Z)V
        //  1652: return         
        //    Exceptions:
        //  throws java.lang.Exception
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  211    220    220    227    Ljava/lang/Exception;
        //  529    538    538    545    Ljava/lang/Exception;
        //  443    555    555    565    Ljava/lang/Exception;
        //  443    562    565    575    Any
        //  1168   1177   1177   1184   Ljava/lang/Exception;
        //  1393   1402   1402   1409   Ljava/lang/Exception;
        //  1290   1419   1419   1429   Ljava/lang/Exception;
        //  1290   1426   1429   1439   Any
        //  10     1545   1545   1555   Ljava/lang/Exception;
        //  10     240    1555   1606   Any
        //  241    1552   1555   1606   Any
        //  1574   1583   1583   1590   Ljava/lang/Exception;
        //  1623   1632   1632   1639   Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException: -1
        //     at java.util.ArrayList.elementData(ArrayList.java:422)
        //     at java.util.ArrayList.remove(ArrayList.java:499)
        //     at com.strobel.assembler.ir.StackMappingVisitor.pop(StackMappingVisitor.java:267)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:577)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2030)
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
    
    public a c() {
        return this.m;
    }
    
    public int d() {
        return this.p;
    }
    
    public ce e() {
        return this.q;
    }
    
    public void b(int s) {
        final boolean q = com.easypano.tw.g.q;
        final int n = s;
        Label_0038: {
            if (!q) {
                switch (n) {
                    case 0:
                    case 1: {
                        if (q) {
                            break;
                        }
                        break Label_0038;
                    }
                }
            }
            s = n;
        }
        this.s = s;
    }
    
    public void f() {
        this.a(0.0, 0.0, -this.v);
    }
    
    public void g() {
        this.a(0.0, 0.0, this.v);
    }
    
    public void h() {
        this.a(-this.t, 0.0, 0.0);
    }
    
    public void i() {
        this.a(this.t, 0.0, 0.0);
    }
    
    public void j() {
        this.a(0.0, this.u, 0.0);
    }
    
    public void k() {
        this.a(0.0, -this.u, 0.0);
    }
    
    public void a(final double n, double n2, final double n3) {
        final boolean q = com.easypano.tw.g.q;
        int n4;
        final boolean b = (n4 = (this.G ? 1 : 0)) != 0;
        if (!q) {
            if (!b) {
                return;
            }
            final boolean v;
            n4 = ((v = this.o.v) ? 1 : 0);
        }
        bu bu = null;
        Label_0094: {
            if (!q) {
                if (!b) {
                    return;
                }
                this.J.h();
                bu = this;
                if (q) {
                    break Label_0094;
                }
                n4 = this.n;
            }
            switch (n4) {
                case 1: {
                    n2 = -n2;
                    break;
                }
            }
            this.J.m = 4;
            this.J.a(n, n2, n3);
            bu = this;
        }
        bu.J.e();
    }
    
    public void a(final int n, final int n2, final int n3) {
        this.a(this.t * n / 20.0, this.u * n2 / 20.0, this.v * n3 / 20.0);
    }
    
    public void stopAutoPan() {
        if (this.G && !this.N) {
            this.J.d();
            this.J.h();
        }
    }
    
    public void c(final int r) {
        final boolean q = com.easypano.tw.g.q;
        int g;
        final int n = g = (this.G ? 1 : 0);
        if (!q) {
            if (n == 0) {
                return;
            }
            g = r;
        }
        if (!q) {
            if (n < 0) {
                return;
            }
            g = r;
        }
        if (g == this.r) {
            this.l();
            if (!q) {
                return;
            }
        }
        this.r = r;
        final ce a = this.I.h().f.a(r);
        if (!q) {
            if (a == null) {
                return;
            }
            this.J.d();
            this.J.i();
            this.q = a;
        }
        this.J.g();
    }
    
    public void l() {
        final boolean q = com.easypano.tw.g.q;
        final boolean g = this.G;
        final ef j;
        Label_0051: {
            if (!q) {
                if (!g) {
                    return;
                }
                this.J.d();
                j = this.J;
                if (q) {
                    break Label_0051;
                }
                j.f();
            }
            if (g) {
                this.J.h();
                if (!q) {
                    return;
                }
            }
            final ef i = this.J;
        }
        j.g();
    }
    
    public void stopMovie() {
        if (this.G && !this.N && this.q != null) {
            this.J.d();
            this.J.i();
            this.q.f();
            this.I.i().stopAudioClip();
            final cd a = this.q.a(0);
            final cj a2 = this.I.h().g.a(a.d);
            try {
                this.a(a.d, a.f, a.g, a.h, a2.h, 0, false);
            }
            catch (Exception ex) {
                System.out.println(b("o@\u0017\u001b\u0007\n[\u000bT\u0006^]\u0015$\u0014^Z"));
                ex.printStackTrace();
            }
        }
    }
    
    public void b(final double n, final double n2, final double n3) {
        bu bu = this;
        if (!com.easypano.tw.g.q) {
            if (!this.G) {
                return;
            }
            this.stopAutoPan();
            bu = this;
        }
        bu.J.a(new dj(this, n, n2, n3));
    }
    
    public void a(final boolean g) {
        this.G = g;
        this.N = false;
    }
    
    private void a(final double n) {
        bu bu = this;
        if (!com.easypano.tw.g.q) {
            if (!this.G) {
                return;
            }
            bu = this;
        }
        bu.J.a(new dk(this, n));
    }
    
    private void b(final double n) {
        bu bu = this;
        if (!com.easypano.tw.g.q) {
            if (!this.G) {
                return;
            }
            bu = this;
        }
        bu.J.a(new dl(this, n));
    }
    
    private void c(final double n) {
        bu bu = this;
        if (!com.easypano.tw.g.q) {
            if (!this.G) {
                return;
            }
            bu = this;
        }
        bu.J.a(new dm(this, n));
    }
    
    public void m() {
        bu bu = this;
        if (!com.easypano.tw.g.q) {
            if (!this.G) {
                return;
            }
            this.m.h();
            bu = this;
        }
        bu.repaint();
    }
    
    public void n() {
        bu bu = this;
        if (!com.easypano.tw.g.q) {
            if (!this.G) {
                return;
            }
            this.m.i();
            bu = this;
        }
        bu.repaint();
    }
    
    public void o() {
        bu bu = this;
        if (!com.easypano.tw.g.q) {
            if (!this.G) {
                return;
            }
            this.m.j();
            bu = this;
        }
        bu.repaint();
    }
    
    static boolean a(final bu bu) {
        return bu.G;
    }
    
    static cj b(final bu bu) {
        return bu.o;
    }
    
    static a c(final bu bu) {
        return bu.m;
    }
    
    static double d(final bu bu) {
        return bu.D;
    }
    
    static void a(final bu bu, final double n) {
        bu.c(n);
    }
    
    static double e(final bu bu) {
        return bu.B;
    }
    
    static void b(final bu bu, final double n) {
        bu.a(n);
    }
    
    static int f(final bu bu) {
        return bu.n;
    }
    
    static double g(final bu bu) {
        return bu.C;
    }
    
    static void c(final bu bu, final double n) {
        bu.b(n);
    }
    
    static TWViewer h(final bu bu) {
        return bu.I;
    }
    
    static ef i(final bu bu) {
        return bu.J;
    }
    
    static void a(final bu bu, final int e) {
        bu.E = e;
    }
    
    static void b(final bu bu, final int f) {
        bu.F = f;
    }
    
    static int j(final bu bu) {
        return bu.E;
    }
    
    static int k(final bu bu) {
        return bu.F;
    }
    
    static double l(final bu bu) {
        return bu.z;
    }
    
    static double m(final bu bu) {
        return bu.A;
    }
    
    static Image n(final bu bu) {
        return bu.j;
    }
    
    static int o(final bu bu) {
        return bu.r;
    }
    
    static ce p(final bu bu) {
        return bu.q;
    }
    
    static int q(final bu bu) {
        return bu.p;
    }
    
    static void a(final bu bu, final cj o) {
        bu.o = o;
    }
    
    static void c(final bu bu, final int p2) {
        bu.p = p2;
    }
    
    static void d(final bu bu, final int n) {
        bu.a(n);
    }
    
    static Hashtable r(final bu bu) {
        return bu.l;
    }
    
    static void a(final bu bu, final a m) {
        bu.m = m;
    }
    
    static void d(final bu bu, final double v) {
        bu.v = v;
    }
    
    static void e(final bu bu, final double u) {
        bu.u = u;
    }
    
    static void f(final bu bu, final double t) {
        bu.t = t;
    }
    
    static void g(final bu bu, final double y) {
        bu.y = y;
    }
    
    static void h(final bu bu, final double x) {
        bu.x = x;
    }
    
    static void i(final bu bu, final double w) {
        bu.w = w;
    }
    
    static void j(final bu bu, final double a) {
        bu.A = a;
    }
    
    static void k(final bu bu, final double z) {
        bu.z = z;
    }
    
    static void l(final bu bu, final double d) {
        bu.D = d;
    }
    
    static void m(final bu bu, final double c) {
        bu.C = c;
    }
    
    static void n(final bu bu, final double b) {
        bu.B = b;
    }
    
    static double s(final bu bu) {
        return bu.w;
    }
    
    static double t(final bu bu) {
        return bu.x;
    }
    
    static double u(final bu bu) {
        return bu.y;
    }
    
    static cr v(final bu bu) {
        return bu.K;
    }
    
    static com.easypano.tw.a.a w(final bu bu) {
        return bu.L;
    }
    
    static b x(final bu bu) {
        return bu.M;
    }
    
    static void a(final bu bu, final Image j) {
        bu.j = j;
    }
    
    static int y(final bu bu) {
        return bu.s;
    }
    
    static void a(final bu bu, final boolean g) {
        bu.G = g;
    }
    
    static du z(final bu bu) {
        return bu.P;
    }
    
    private static String b(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
        while (true) {
            Label_0089: {
                if (length > 1) {
                    break Label_0089;
                }
                char[] array2;
                char[] array = array2 = charArray;
                int n3;
                int n2 = n3 = n;
                while (true) {
                    final char c = array2[n3];
                    char c2 = '\0';
                    switch (n % 5) {
                        case 0: {
                            c2 = '*';
                            break;
                        }
                        case 1: {
                            c2 = '2';
                            break;
                        }
                        case 2: {
                            c2 = 'e';
                            break;
                        }
                        case 3: {
                            c2 = 't';
                            break;
                        }
                        default: {
                            c2 = 'u';
                            break;
                        }
                    }
                    array[n2] = (char)(c ^ c2);
                    ++n;
                    if (length != 0) {
                        break;
                    }
                    array = (array2 = charArray);
                    n2 = (n3 = length);
                }
            }
            if (n >= length) {
                return new String(charArray);
            }
            continue;
        }
    }
}
