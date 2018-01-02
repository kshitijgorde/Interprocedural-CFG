import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class bo extends Thread
{
    protected IpixViewer a;
    protected Vector b;
    protected bb c;
    protected Vector d;
    protected long e;
    private long f;
    private long g;
    private int h;
    private long i;
    private boolean j;
    protected boolean k;
    protected Object l;
    private bl m;
    
    boolean a(final bb bb) {
        if (bb == null) {
            return this.c != null;
        }
        return bb == this.c;
    }
    
    void a(final bm bm) {
        this.d.addElement(bm);
    }
    
    float a() {
        return 1000000.0f / this.f;
    }
    
    bo(final IpixViewer a) {
        this.b = new Vector();
        this.d = new Vector();
        this.e = 1L;
        this.f = 15000L;
        this.g = 0L;
        this.h = 1;
        this.i = 0L;
        this.j = false;
        this.k = false;
        this.l = new Object();
        this.m = null;
        this.a = a;
    }
    
    synchronized boolean b(final bb c) {
        if (this.c != null && !this.c.a((c != null) ? 1 : 0)) {
            return false;
        }
        this.c(this.c);
        if (c != null) {
            this.c = c;
            if (c instanceof bh || c instanceof bj || c instanceof bk) {
                this.e += 25L;
            }
        }
        return true;
    }
    
    synchronized void c(final bb bb) {
        if (bb == this.c) {
            this.c = null;
            if (bb instanceof bh || bb instanceof bj || bb instanceof bk) {
                this.e -= 25L;
            }
        }
    }
    
    IpixViewer b() {
        return this.a;
    }
    
    protected void a(final bl bl) {
        for (int i = 0; i < this.d.size(); ++i) {
            ((bm)this.d.elementAt(i)).a(bl);
        }
    }
    
    protected bb c() {
        return new bg(this);
    }
    
    void b(final bm bm) {
        this.d.removeElement(bm);
    }
    
    public void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        bo.j:Z
        //     4: iconst_1       
        //     5: if_icmpne       9
        //     8: return         
        //     9: aload_0        
        //    10: iconst_1       
        //    11: invokevirtual   java/lang/Thread.setPriority:(I)V
        //    14: ldc             "os.name"
        //    16: invokestatic    java/lang/System.getProperty:(Ljava/lang/String;)Ljava/lang/String;
        //    19: ldc             "Mac"
        //    21: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    24: istore_1       
        //    25: ldc             "java.vendor"
        //    27: invokestatic    java/lang/System.getProperty:(Ljava/lang/String;)Ljava/lang/String;
        //    30: ldc             "Netscape"
        //    32: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    35: istore_2       
        //    36: ldc             "java.version"
        //    38: invokestatic    java/lang/System.getProperty:(Ljava/lang/String;)Ljava/lang/String;
        //    41: ldc             "1.1"
        //    43: invokevirtual   java/lang/String.compareTo:(Ljava/lang/String;)I
        //    46: iflt            53
        //    49: iconst_0       
        //    50: goto            54
        //    53: iconst_1       
        //    54: istore_3       
        //    55: iload_1        
        //    56: iconst_1       
        //    57: if_icmpne       72
        //    60: iload_2        
        //    61: iconst_1       
        //    62: if_icmpne       72
        //    65: aload_0        
        //    66: ldc2_w          50
        //    69: putfield        bo.e:J
        //    72: invokestatic    IpixViewer.g:()F
        //    75: ldc             2.1
        //    77: fcmpl          
        //    78: ifle            96
        //    81: aload_0        
        //    82: getfield        bo.b:Ljava/util/Vector;
        //    85: new             Lbc;
        //    88: dup            
        //    89: aload_0        
        //    90: invokespecial   bc.<init>:(Lbo;)V
        //    93: invokevirtual   java/util/Vector.addElement:(Ljava/lang/Object;)V
        //    96: iload_1        
        //    97: iconst_1       
        //    98: if_icmpne       106
        //   101: iload_2        
        //   102: iconst_1       
        //   103: if_icmpeq       119
        //   106: iload_3        
        //   107: iconst_1       
        //   108: if_icmpeq       119
        //   111: invokestatic    IpixViewer.g:()F
        //   114: fconst_2       
        //   115: fcmpg          
        //   116: ifgt            137
        //   119: aload_0        
        //   120: getfield        bo.b:Ljava/util/Vector;
        //   123: new             Lbf;
        //   126: dup            
        //   127: aload_0        
        //   128: invokespecial   bf.<init>:(Lbo;)V
        //   131: invokevirtual   java/util/Vector.addElement:(Ljava/lang/Object;)V
        //   134: goto            167
        //   137: aload_0        
        //   138: getfield        bo.b:Ljava/util/Vector;
        //   141: aload_0        
        //   142: invokevirtual   bo.c:()Lbb;
        //   145: invokevirtual   java/util/Vector.addElement:(Ljava/lang/Object;)V
        //   148: goto            167
        //   151: pop            
        //   152: aload_0        
        //   153: getfield        bo.b:Ljava/util/Vector;
        //   156: new             Lbf;
        //   159: dup            
        //   160: aload_0        
        //   161: invokespecial   bf.<init>:(Lbo;)V
        //   164: invokevirtual   java/util/Vector.addElement:(Ljava/lang/Object;)V
        //   167: aload_0        
        //   168: new             Lbl;
        //   171: dup            
        //   172: invokespecial   bl.<init>:()V
        //   175: putfield        bo.m:Lbl;
        //   178: goto            577
        //   181: aload_0        
        //   182: getfield        bo.c:Lbb;
        //   185: astore          4
        //   187: goto            500
        //   190: aload_0        
        //   191: getfield        bo.j:Z
        //   194: iconst_1       
        //   195: if_icmpne       202
        //   198: jsr             611
        //   201: return         
        //   202: aload_0        
        //   203: getfield        bo.a:LIpixViewer;
        //   206: invokevirtual   IpixViewer.h:()[F
        //   209: astore          5
        //   211: aload           5
        //   213: invokestatic    bn.a:([F)[F
        //   216: astore          6
        //   218: aload           4
        //   220: aload           6
        //   222: invokevirtual   bb.a:([F)Z
        //   225: iconst_1       
        //   226: if_icmpne       487
        //   229: aload           5
        //   231: aload           6
        //   233: invokestatic    bn.b:([F[F)[F
        //   236: astore          5
        //   238: aload_0        
        //   239: getfield        bo.a:LIpixViewer;
        //   242: aload           6
        //   244: iconst_0       
        //   245: invokevirtual   IpixViewer.a:([FZ)Z
        //   248: istore          7
        //   250: iload           7
        //   252: ifne            271
        //   255: aload           4
        //   257: iconst_2       
        //   258: invokevirtual   bb.a:(I)Z
        //   261: iconst_1       
        //   262: if_icmpne       271
        //   265: aload_0        
        //   266: aload           4
        //   268: invokevirtual   bo.c:(Lbb;)V
        //   271: aload_0        
        //   272: getfield        bo.m:Lbl;
        //   275: aload           5
        //   277: invokevirtual   bl.a:([F)V
        //   280: aload_0        
        //   281: aload_0        
        //   282: getfield        bo.m:Lbl;
        //   285: invokevirtual   bo.a:(Lbl;)V
        //   288: iload           7
        //   290: iconst_1       
        //   291: if_icmpne       487
        //   294: invokestatic    java/lang/System.currentTimeMillis:()J
        //   297: lstore          8
        //   299: aload_0        
        //   300: getfield        bo.a:LIpixViewer;
        //   303: invokevirtual   IpixViewer.c:()V
        //   306: invokestatic    java/lang/System.currentTimeMillis:()J
        //   309: lstore          10
        //   311: lload           10
        //   313: lload           8
        //   315: lcmp           
        //   316: ifeq            331
        //   319: lload           10
        //   321: lload           8
        //   323: lsub           
        //   324: ldc2_w          33
        //   327: lcmp           
        //   328: ifle            338
        //   331: lconst_1       
        //   332: invokestatic    java/lang/Thread.sleep:(J)V
        //   335: goto            350
        //   338: ldc2_w          33
        //   341: lload           10
        //   343: lload           8
        //   345: lsub           
        //   346: lsub           
        //   347: invokestatic    java/lang/Thread.sleep:(J)V
        //   350: invokestatic    java/lang/System.currentTimeMillis:()J
        //   353: lstore          10
        //   355: lload           10
        //   357: lload           8
        //   359: lsub           
        //   360: aload_0        
        //   361: getfield        bo.e:J
        //   364: lconst_1       
        //   365: lsub           
        //   366: ladd           
        //   367: ldc2_w          1000
        //   370: lmul           
        //   371: lstore          12
        //   373: lload           12
        //   375: lconst_0       
        //   376: lcmp           
        //   377: ifne            417
        //   380: aload_0        
        //   381: getfield        bo.h:I
        //   384: iconst_1       
        //   385: if_icmple       401
        //   388: lload           10
        //   390: aload_0        
        //   391: getfield        bo.i:J
        //   394: lsub           
        //   395: ldc2_w          1000
        //   398: lmul           
        //   399: lstore          12
        //   401: aload_0        
        //   402: lload           10
        //   404: putfield        bo.i:J
        //   407: aload_0        
        //   408: dup            
        //   409: getfield        bo.h:I
        //   412: iconst_1       
        //   413: iadd           
        //   414: putfield        bo.h:I
        //   417: lload           12
        //   419: lconst_0       
        //   420: lcmp           
        //   421: ifeq            487
        //   424: lload           12
        //   426: aload_0        
        //   427: getfield        bo.f:J
        //   430: ldc2_w          2
        //   433: lmul           
        //   434: lcmp           
        //   435: iflt            452
        //   438: lload           12
        //   440: ldc2_w          2
        //   443: aload_0        
        //   444: getfield        bo.g:J
        //   447: lmul           
        //   448: lcmp           
        //   449: ifge            476
        //   452: aload_0        
        //   453: lload           12
        //   455: bipush          50
        //   457: aload_0        
        //   458: getfield        bo.h:I
        //   461: isub           
        //   462: i2l            
        //   463: aload_0        
        //   464: getfield        bo.f:J
        //   467: lmul           
        //   468: ladd           
        //   469: ldc2_w          50
        //   472: ldiv           
        //   473: putfield        bo.f:J
        //   476: aload_0        
        //   477: lload           12
        //   479: putfield        bo.g:J
        //   482: aload_0        
        //   483: iconst_1       
        //   484: putfield        bo.h:I
        //   487: aload_0        
        //   488: getfield        bo.e:J
        //   491: invokestatic    java/lang/Thread.sleep:(J)V
        //   494: aload_0        
        //   495: getfield        bo.c:Lbb;
        //   498: astore          4
        //   500: aload           4
        //   502: ifnonnull       190
        //   505: aload_0        
        //   506: getfield        bo.j:Z
        //   509: iconst_1       
        //   510: if_icmpne       517
        //   513: jsr             611
        //   516: return         
        //   517: aload_0        
        //   518: getfield        bo.a:LIpixViewer;
        //   521: aload_0        
        //   522: getfield        bo.a:LIpixViewer;
        //   525: invokevirtual   IpixViewer.h:()[F
        //   528: iconst_1       
        //   529: invokevirtual   IpixViewer.a:([FZ)Z
        //   532: pop            
        //   533: aload_0        
        //   534: new             Lbl;
        //   537: dup            
        //   538: iconst_5       
        //   539: newarray        F
        //   541: invokespecial   bl.<init>:([F)V
        //   544: invokevirtual   bo.a:(Lbl;)V
        //   547: aload_0        
        //   548: getfield        bo.a:LIpixViewer;
        //   551: invokevirtual   IpixViewer.c:()V
        //   554: goto            563
        //   557: ldc2_w          50
        //   560: invokestatic    java/lang/Thread.sleep:(J)V
        //   563: aload_0        
        //   564: getfield        bo.c:Lbb;
        //   567: ifnonnull       577
        //   570: aload_0        
        //   571: getfield        bo.j:Z
        //   574: ifeq            557
        //   577: aload_0        
        //   578: getfield        bo.j:Z
        //   581: ifeq            181
        //   584: goto            599
        //   587: astore          4
        //   589: aload           4
        //   591: athrow         
        //   592: astore          4
        //   594: aload           4
        //   596: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //   599: jsr             611
        //   602: return         
        //   603: astore          14
        //   605: jsr             611
        //   608: aload           14
        //   610: athrow         
        //   611: astore          15
        //   613: iconst_0       
        //   614: istore          4
        //   616: goto            637
        //   619: aload_0        
        //   620: getfield        bo.b:Ljava/util/Vector;
        //   623: iload           4
        //   625: invokevirtual   java/util/Vector.elementAt:(I)Ljava/lang/Object;
        //   628: checkcast       Lbb;
        //   631: invokevirtual   bb.a:()V
        //   634: iinc            4, 1
        //   637: iload           4
        //   639: aload_0        
        //   640: getfield        bo.b:Ljava/util/Vector;
        //   643: invokevirtual   java/util/Vector.size:()I
        //   646: if_icmplt       619
        //   649: aload_0        
        //   650: getfield        bo.b:Ljava/util/Vector;
        //   653: invokevirtual   java/util/Vector.removeAllElements:()V
        //   656: aload_0        
        //   657: aconst_null    
        //   658: putfield        bo.c:Lbb;
        //   661: aload_0        
        //   662: aconst_null    
        //   663: putfield        bo.m:Lbl;
        //   666: aload_0        
        //   667: getfield        bo.l:Ljava/lang/Object;
        //   670: dup            
        //   671: astore          16
        //   673: monitorenter   
        //   674: aload_0        
        //   675: iconst_1       
        //   676: putfield        bo.k:Z
        //   679: aload_0        
        //   680: iconst_0       
        //   681: putfield        bo.j:Z
        //   684: jsr             694
        //   687: goto            701
        //   690: aload           16
        //   692: monitorexit    
        //   693: athrow         
        //   694: astore          17
        //   696: aload           16
        //   698: monitorexit    
        //   699: ret             17
        //   701: ret             15
        //   703: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                   
        //  -----  -----  -----  -----  -----------------------
        //  96     148    151    167    Ljava/lang/Throwable;
        //  167    584    587    592    Ljava/lang/ThreadDeath;
        //  167    584    592    599    Ljava/lang/Throwable;
        //  167    599    603    611    Any
        //  674    684    690    694    Any
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
    
    bb d() {
        return this.c;
    }
    
    void a(final int n) throws InterruptedException, IllegalThreadStateException {
        if (Thread.currentThread() == this) {
            throw new IllegalThreadStateException();
        }
        synchronized (this.l) {
            if (this.k) {
                // monitorexit(this.l)
                return;
            }
            this.j = true;
        }
        // monitorexit(this.l)
        final long currentTimeMillis = System.currentTimeMillis();
        while (this.j && System.currentTimeMillis() - currentTimeMillis < n) {
            Thread.sleep(10L);
        }
    }
}
