// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.File;
import java.io.IOException;
import com.hw.client.util.a;
import java.io.OutputStream;
import java.util.Vector;

public final class cg implements Runnable
{
    private Vector a;
    private j b;
    private j c;
    private Process d;
    private OutputStream e;
    private da f;
    private aK g;
    private bS h;
    private com.hw.client.util.j i;
    private boolean j;
    private int k;
    private static boolean l;
    private Thread m;
    private long n;
    private int o;
    
    public cg(final da f) {
        this.a = new Vector();
        this.i = null;
        this.m = null;
        this.n = System.currentTimeMillis();
        this.o = 0;
        this.f = f;
        this.g = f.g();
        this.h = f.f();
        if (this.f.l()) {
            try {
                final File a = this.g.a();
                (this.i = new com.hw.client.util.j(a)).a();
                com.hw.client.util.a.c("DoorController(), successfully set up log file, lf => " + a);
            }
            catch (IOException ex) {
                com.hw.client.util.a.a("DoorController(), unable to setup log file", ex);
            }
        }
        this.j = true;
    }
    
    public final void a() {
        (this.m = new Thread(this, "HW-DoorController")).setPriority(10);
        this.m.start();
    }
    
    final void b() {
        com.hw.client.util.a.b("DoorController.stopDoor(), stopping door, keep_running => " + this.j);
        this.j = false;
        if (this.i != null) {
            this.i.b();
        }
    }
    
    public final boolean c() {
        return this.j;
    }
    
    public final void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             "DoorController.run(), entering door controller run method"
        //     2: invokestatic    com/hw/client/util/a.c:(Ljava/lang/String;)V
        //     5: aload_0        
        //     6: iconst_2       
        //     7: putfield        VT_6_1_0_11/cg.k:I
        //    10: getstatic       VT_6_1_0_11/cg.l:Z
        //    13: ifeq            40
        //    16: ldc             "DoorController.run(), removing door"
        //    18: invokestatic    com/hw/client/util/a.d:(Ljava/lang/String;)V
        //    21: aload_0        
        //    22: getfield        VT_6_1_0_11/cg.h:LVT_6_1_0_11/bS;
        //    25: invokevirtual   VT_6_1_0_11/bS.a:()Z
        //    28: ifne            36
        //    31: ldc             "DoorController.run(), unable to remove door"
        //    33: invokestatic    com/hw/client/util/a.d:(Ljava/lang/String;)V
        //    36: iconst_0       
        //    37: putstatic       VT_6_1_0_11/cg.l:Z
        //    40: aload_0        
        //    41: getfield        VT_6_1_0_11/cg.h:LVT_6_1_0_11/bS;
        //    44: invokevirtual   VT_6_1_0_11/bS.b:()Z
        //    47: ifne            83
        //    50: ldc             "DoorController.run(), unable to install door"
        //    52: invokestatic    com/hw/client/util/a.d:(Ljava/lang/String;)V
        //    55: sipush          130
        //    58: invokestatic    VT_6_1_0_11/cx.a:(I)V
        //    61: aload_0        
        //    62: dup            
        //    63: getfield        VT_6_1_0_11/cg.k:I
        //    66: bipush          64
        //    68: ior            
        //    69: putfield        VT_6_1_0_11/cg.k:I
        //    72: aload_0        
        //    73: getfield        VT_6_1_0_11/cg.f:LVT_6_1_0_11/da;
        //    76: getstatic       VT_6_1_0_11/cG.c:LVT_6_1_0_11/aB;
        //    79: invokevirtual   VT_6_1_0_11/da.a:(LVT_6_1_0_11/aB;)V
        //    82: return         
        //    83: ldc             "DoorController.run(), attempting to launch agent"
        //    85: invokestatic    com/hw/client/util/a.c:(Ljava/lang/String;)V
        //    88: aload_0        
        //    89: getfield        VT_6_1_0_11/cg.f:LVT_6_1_0_11/da;
        //    92: invokevirtual   VT_6_1_0_11/da.h:()Z
        //    95: ifeq            113
        //    98: aload_0        
        //    99: getfield        VT_6_1_0_11/cg.f:LVT_6_1_0_11/da;
        //   102: iconst_0       
        //   103: invokevirtual   VT_6_1_0_11/da.a:(Z)V
        //   106: aload_0        
        //   107: invokespecial   VT_6_1_0_11/cg.e:()V
        //   110: goto            163
        //   113: ldc             "skipping initial connect"
        //   115: invokestatic    com/hw/client/util/a.c:(Ljava/lang/String;)V
        //   118: aload_0        
        //   119: getfield        VT_6_1_0_11/cg.f:LVT_6_1_0_11/da;
        //   122: iconst_0       
        //   123: invokevirtual   VT_6_1_0_11/da.a:(Z)V
        //   126: return         
        //   127: astore_1       
        //   128: ldc             "DoorController.run(), unable to launch agent"
        //   130: aload_1        
        //   131: invokestatic    com/hw/client/util/a.a:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   134: sipush          180
        //   137: aload_1        
        //   138: invokestatic    VT_6_1_0_11/cx.a:(ILjava/lang/Exception;)V
        //   141: aload_0        
        //   142: dup            
        //   143: getfield        VT_6_1_0_11/cg.k:I
        //   146: bipush          64
        //   148: ior            
        //   149: putfield        VT_6_1_0_11/cg.k:I
        //   152: aload_0        
        //   153: getfield        VT_6_1_0_11/cg.f:LVT_6_1_0_11/da;
        //   156: getstatic       VT_6_1_0_11/cG.c:LVT_6_1_0_11/aB;
        //   159: invokevirtual   VT_6_1_0_11/da.a:(LVT_6_1_0_11/aB;)V
        //   162: return         
        //   163: ldc             "DoorController.run(), entering door controller's main while loop"
        //   165: invokestatic    com/hw/client/util/a.c:(Ljava/lang/String;)V
        //   168: aload_0        
        //   169: dup            
        //   170: astore_1       
        //   171: getfield        VT_6_1_0_11/cg.j:Z
        //   174: ifeq            737
        //   177: aload_0        
        //   178: dup            
        //   179: astore_1       
        //   180: dup            
        //   181: astore          10
        //   183: getfield        VT_6_1_0_11/cg.k:I
        //   186: bipush          64
        //   188: iand           
        //   189: ifeq            196
        //   192: iconst_1       
        //   193: goto            197
        //   196: iconst_0       
        //   197: iconst_1       
        //   198: if_icmpeq       231
        //   201: aload_1        
        //   202: getfield        VT_6_1_0_11/cg.b:LVT_6_1_0_11/j;
        //   205: invokevirtual   VT_6_1_0_11/j.a:()Z
        //   208: ifne            231
        //   211: aload_1        
        //   212: getfield        VT_6_1_0_11/cg.b:LVT_6_1_0_11/j;
        //   215: invokevirtual   VT_6_1_0_11/j.b:()Z
        //   218: ifne            231
        //   221: aload_1        
        //   222: getfield        VT_6_1_0_11/cg.c:LVT_6_1_0_11/j;
        //   225: invokevirtual   VT_6_1_0_11/j.a:()Z
        //   228: ifeq            246
        //   231: aload_1        
        //   232: dup            
        //   233: getfield        VT_6_1_0_11/cg.k:I
        //   236: bipush          -17
        //   238: iand           
        //   239: putfield        VT_6_1_0_11/cg.k:I
        //   242: iconst_0       
        //   243: goto            258
        //   246: aload_1        
        //   247: dup            
        //   248: getfield        VT_6_1_0_11/cg.k:I
        //   251: bipush          16
        //   253: ior            
        //   254: putfield        VT_6_1_0_11/cg.k:I
        //   257: iconst_1       
        //   258: ifne            496
        //   261: aload_0        
        //   262: getfield        VT_6_1_0_11/cg.f:LVT_6_1_0_11/da;
        //   265: invokevirtual   VT_6_1_0_11/da.k:()Z
        //   268: iconst_1       
        //   269: if_icmpne       493
        //   272: ldc             "DoorController.run(), attempting to restart agent"
        //   274: invokestatic    com/hw/client/util/a.c:(Ljava/lang/String;)V
        //   277: aload_0        
        //   278: dup            
        //   279: astore_1       
        //   280: dup            
        //   281: astore_2       
        //   282: getfield        VT_6_1_0_11/cg.k:I
        //   285: bipush          16
        //   287: iand           
        //   288: ifeq            295
        //   291: iconst_1       
        //   292: goto            296
        //   295: iconst_0       
        //   296: ifne            490
        //   299: ldc             "notifying door that agent has stopped"
        //   301: invokestatic    com/hw/client/util/a.b:(Ljava/lang/String;)V
        //   304: aload_1        
        //   305: getfield        VT_6_1_0_11/cg.f:LVT_6_1_0_11/da;
        //   308: getstatic       VT_6_1_0_11/cG.b:LVT_6_1_0_11/aB;
        //   311: invokevirtual   VT_6_1_0_11/da.a:(LVT_6_1_0_11/aB;)V
        //   314: invokestatic    java/lang/System.currentTimeMillis:()J
        //   317: aload_1        
        //   318: getfield        VT_6_1_0_11/cg.n:J
        //   321: lsub           
        //   322: dup2           
        //   323: lstore          10
        //   325: ldc2_w          1000
        //   328: lcmp           
        //   329: ifge            347
        //   332: ldc             "restart request came too quickly"
        //   334: invokestatic    com/hw/client/util/a.d:(Ljava/lang/String;)V
        //   337: new             LVT_6_1_0_11/at;
        //   340: dup            
        //   341: ldc             "restart request came too quickly"
        //   343: invokespecial   VT_6_1_0_11/at.<init>:(Ljava/lang/String;)V
        //   346: athrow         
        //   347: aload_1        
        //   348: dup            
        //   349: getfield        VT_6_1_0_11/cg.o:I
        //   352: iconst_1       
        //   353: iadd           
        //   354: dup_x1         
        //   355: putfield        VT_6_1_0_11/cg.o:I
        //   358: iconst_5       
        //   359: if_icmple       386
        //   362: lload           10
        //   364: ldc2_w          10000
        //   367: lcmp           
        //   368: ifge            386
        //   371: ldc             "too many requests to restart the agent"
        //   373: invokestatic    com/hw/client/util/a.d:(Ljava/lang/String;)V
        //   376: new             LVT_6_1_0_11/at;
        //   379: dup            
        //   380: ldc             "too many requests to restart the agent"
        //   382: invokespecial   VT_6_1_0_11/at.<init>:(Ljava/lang/String;)V
        //   385: athrow         
        //   386: new             Ljava/lang/StringBuffer;
        //   389: dup            
        //   390: invokespecial   java/lang/StringBuffer.<init>:()V
        //   393: ldc             "attempting to relaunch the agent, restart_count => "
        //   395: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   398: aload_1        
        //   399: getfield        VT_6_1_0_11/cg.o:I
        //   402: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //   405: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   408: invokestatic    com/hw/client/util/a.c:(Ljava/lang/String;)V
        //   411: aload_1        
        //   412: dup            
        //   413: getfield        VT_6_1_0_11/cg.k:I
        //   416: bipush          8
        //   418: ior            
        //   419: putfield        VT_6_1_0_11/cg.k:I
        //   422: ldc             "stopping agent"
        //   424: invokestatic    com/hw/client/util/a.c:(Ljava/lang/String;)V
        //   427: aload_1        
        //   428: invokespecial   VT_6_1_0_11/cg.f:()V
        //   431: ldc             "clearing command stack"
        //   433: invokestatic    com/hw/client/util/a.c:(Ljava/lang/String;)V
        //   436: aload_1        
        //   437: invokevirtual   VT_6_1_0_11/cg.d:()V
        //   440: ldc             "launching agent"
        //   442: invokestatic    com/hw/client/util/a.c:(Ljava/lang/String;)V
        //   445: aload_1        
        //   446: invokespecial   VT_6_1_0_11/cg.e:()V
        //   449: aload_1        
        //   450: invokestatic    java/lang/System.currentTimeMillis:()J
        //   453: putfield        VT_6_1_0_11/cg.n:J
        //   456: lload           10
        //   458: ldc2_w          10000
        //   461: lcmp           
        //   462: ifle            470
        //   465: aload_1        
        //   466: iconst_0       
        //   467: putfield        VT_6_1_0_11/cg.o:I
        //   470: goto            496
        //   473: astore_1       
        //   474: ldc             "unknown exception while relaunching agent"
        //   476: aload_1        
        //   477: invokestatic    com/hw/client/util/a.a:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   480: new             LVT_6_1_0_11/at;
        //   483: dup            
        //   484: ldc             "unknown exception while relaunching agent"
        //   486: invokespecial   VT_6_1_0_11/at.<init>:(Ljava/lang/String;)V
        //   489: athrow         
        //   490: goto            529
        //   493: goto            737
        //   496: goto            529
        //   499: astore_3       
        //   500: ldc             "DoorController.run(), unable to relaunch the agent"
        //   502: aload_3        
        //   503: invokestatic    com/hw/client/util/a.a:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   506: ldc             "DoorController.run(), sleeping before trying to restart agent"
        //   508: invokestatic    com/hw/client/util/a.c:(Ljava/lang/String;)V
        //   511: ldc2_w          2000
        //   514: invokestatic    java/lang/Thread.sleep:(J)V
        //   517: goto            168
        //   520: pop            
        //   521: ldc             "DoorController.run(), interrupted while waiting to restart agent"
        //   523: invokestatic    com/hw/client/util/a.c:(Ljava/lang/String;)V
        //   526: goto            168
        //   529: aload_0        
        //   530: getfield        VT_6_1_0_11/cg.a:Ljava/util/Vector;
        //   533: dup            
        //   534: astore_3       
        //   535: monitorenter   
        //   536: aload_0        
        //   537: getfield        VT_6_1_0_11/cg.a:Ljava/util/Vector;
        //   540: invokevirtual   java/util/Vector.isEmpty:()Z
        //   543: ifeq            565
        //   546: aload_0        
        //   547: getfield        VT_6_1_0_11/cg.a:Ljava/util/Vector;
        //   550: ldc2_w          500
        //   553: invokevirtual   java/lang/Object.wait:(J)V
        //   556: goto            565
        //   559: pop            
        //   560: ldc             "DoorController.run(), interrupted in stack.wait"
        //   562: invokestatic    com/hw/client/util/a.d:(Ljava/lang/String;)V
        //   565: aload_0        
        //   566: getfield        VT_6_1_0_11/cg.a:Ljava/util/Vector;
        //   569: invokevirtual   java/util/Vector.isEmpty:()Z
        //   572: ifne            603
        //   575: aload_0        
        //   576: getfield        VT_6_1_0_11/cg.a:Ljava/util/Vector;
        //   579: iconst_0       
        //   580: invokevirtual   java/util/Vector.elementAt:(I)Ljava/lang/Object;
        //   583: astore_1       
        //   584: aload_0        
        //   585: getfield        VT_6_1_0_11/cg.a:Ljava/util/Vector;
        //   588: iconst_0       
        //   589: invokevirtual   java/util/Vector.removeElementAt:(I)V
        //   592: aload_0        
        //   593: getfield        VT_6_1_0_11/cg.a:Ljava/util/Vector;
        //   596: invokevirtual   java/util/Vector.isEmpty:()Z
        //   599: istore_2       
        //   600: goto            613
        //   603: aload_0        
        //   604: getfield        VT_6_1_0_11/cg.f:LVT_6_1_0_11/da;
        //   607: invokevirtual   VT_6_1_0_11/da.e:()Ljava/lang/Object;
        //   610: astore_1       
        //   611: iconst_1       
        //   612: istore_2       
        //   613: aload_3        
        //   614: monitorexit    
        //   615: goto            623
        //   618: astore_1       
        //   619: aload_3        
        //   620: monitorexit    
        //   621: aload_1        
        //   622: athrow         
        //   623: aload_1        
        //   624: ifnull          640
        //   627: aload_0        
        //   628: getfield        VT_6_1_0_11/cg.f:LVT_6_1_0_11/da;
        //   631: aload_0        
        //   632: getfield        VT_6_1_0_11/cg.e:Ljava/io/OutputStream;
        //   635: aload_1        
        //   636: iload_2        
        //   637: invokevirtual   VT_6_1_0_11/da.a:(Ljava/io/OutputStream;Ljava/lang/Object;Z)V
        //   640: goto            168
        //   643: astore_3       
        //   644: new             Ljava/lang/StringBuffer;
        //   647: dup            
        //   648: invokespecial   java/lang/StringBuffer.<init>:()V
        //   651: ldc             "IOException in DoorController.write(), keep_running => "
        //   653: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   656: aload_0        
        //   657: getfield        VT_6_1_0_11/cg.j:Z
        //   660: invokevirtual   java/lang/StringBuffer.append:(Z)Ljava/lang/StringBuffer;
        //   663: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   666: aload_3        
        //   667: invokestatic    com/hw/client/util/a.a:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   670: sipush          184
        //   673: invokestatic    VT_6_1_0_11/cx.a:(I)V
        //   676: aload_0        
        //   677: dup            
        //   678: getfield        VT_6_1_0_11/cg.k:I
        //   681: bipush          64
        //   683: ior            
        //   684: putfield        VT_6_1_0_11/cg.k:I
        //   687: goto            168
        //   690: astore_3       
        //   691: new             Ljava/lang/StringBuffer;
        //   694: dup            
        //   695: invokespecial   java/lang/StringBuffer.<init>:()V
        //   698: ldc             "RuntimeException in DoorController.run(), keep_running => "
        //   700: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   703: aload_0        
        //   704: getfield        VT_6_1_0_11/cg.j:Z
        //   707: invokevirtual   java/lang/StringBuffer.append:(Z)Ljava/lang/StringBuffer;
        //   710: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   713: aload_3        
        //   714: invokestatic    com/hw/client/util/a.a:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   717: sipush          185
        //   720: invokestatic    VT_6_1_0_11/cx.a:(I)V
        //   723: aload_0        
        //   724: dup            
        //   725: getfield        VT_6_1_0_11/cg.k:I
        //   728: bipush          64
        //   730: ior            
        //   731: putfield        VT_6_1_0_11/cg.k:I
        //   734: goto            168
        //   737: ldc             "DoorController.run(), exiting door controller's main while loop"
        //   739: invokestatic    com/hw/client/util/a.c:(Ljava/lang/String;)V
        //   742: aload_0        
        //   743: getfield        VT_6_1_0_11/cg.a:Ljava/util/Vector;
        //   746: invokevirtual   java/util/Vector.isEmpty:()Z
        //   749: ifne            838
        //   752: new             Ljava/lang/StringBuffer;
        //   755: dup            
        //   756: invokespecial   java/lang/StringBuffer.<init>:()V
        //   759: ldc             "sending commands remaining in stack, size => "
        //   761: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   764: aload_0        
        //   765: getfield        VT_6_1_0_11/cg.a:Ljava/util/Vector;
        //   768: invokevirtual   java/util/Vector.size:()I
        //   771: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //   774: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   777: invokestatic    com/hw/client/util/a.b:(Ljava/lang/String;)V
        //   780: aload_0        
        //   781: getfield        VT_6_1_0_11/cg.a:Ljava/util/Vector;
        //   784: dup            
        //   785: astore_1       
        //   786: monitorenter   
        //   787: aload_0        
        //   788: getfield        VT_6_1_0_11/cg.a:Ljava/util/Vector;
        //   791: iconst_0       
        //   792: invokevirtual   java/util/Vector.elementAt:(I)Ljava/lang/Object;
        //   795: astore_3       
        //   796: aload_0        
        //   797: getfield        VT_6_1_0_11/cg.a:Ljava/util/Vector;
        //   800: iconst_0       
        //   801: invokevirtual   java/util/Vector.removeElementAt:(I)V
        //   804: aload_0        
        //   805: getfield        VT_6_1_0_11/cg.a:Ljava/util/Vector;
        //   808: invokevirtual   java/util/Vector.isEmpty:()Z
        //   811: istore_2       
        //   812: aload_1        
        //   813: monitorexit    
        //   814: goto            822
        //   817: astore_2       
        //   818: aload_1        
        //   819: monitorexit    
        //   820: aload_2        
        //   821: athrow         
        //   822: aload_0        
        //   823: getfield        VT_6_1_0_11/cg.f:LVT_6_1_0_11/da;
        //   826: aload_0        
        //   827: getfield        VT_6_1_0_11/cg.e:Ljava/io/OutputStream;
        //   830: aload_3        
        //   831: iload_2        
        //   832: invokevirtual   VT_6_1_0_11/da.a:(Ljava/io/OutputStream;Ljava/lang/Object;Z)V
        //   835: goto            742
        //   838: goto            848
        //   841: astore_3       
        //   842: ldc             "DoorController.run(), exception sending commands remaining in stack"
        //   844: aload_3        
        //   845: invokestatic    com/hw/client/util/a.a:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   848: ldc             "DoorController.run(), sending quit command"
        //   850: invokestatic    com/hw/client/util/a.a:(Ljava/lang/String;)V
        //   853: aload_0        
        //   854: getfield        VT_6_1_0_11/cg.f:LVT_6_1_0_11/da;
        //   857: aload_0        
        //   858: getfield        VT_6_1_0_11/cg.e:Ljava/io/OutputStream;
        //   861: aload_0        
        //   862: getfield        VT_6_1_0_11/cg.f:LVT_6_1_0_11/da;
        //   865: invokevirtual   VT_6_1_0_11/da.d:()Ljava/lang/Object;
        //   868: iconst_1       
        //   869: invokevirtual   VT_6_1_0_11/da.a:(Ljava/io/OutputStream;Ljava/lang/Object;Z)V
        //   872: goto            922
        //   875: astore_1       
        //   876: ldc             "DoorController.run(), exception cleaning up after run loop"
        //   878: aload_1        
        //   879: invokestatic    com/hw/client/util/a.a:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   882: goto            922
        //   885: astore_1       
        //   886: ldc             "DoorController.run(), sending quit command"
        //   888: invokestatic    com/hw/client/util/a.a:(Ljava/lang/String;)V
        //   891: aload_0        
        //   892: getfield        VT_6_1_0_11/cg.f:LVT_6_1_0_11/da;
        //   895: aload_0        
        //   896: getfield        VT_6_1_0_11/cg.e:Ljava/io/OutputStream;
        //   899: aload_0        
        //   900: getfield        VT_6_1_0_11/cg.f:LVT_6_1_0_11/da;
        //   903: invokevirtual   VT_6_1_0_11/da.d:()Ljava/lang/Object;
        //   906: iconst_1       
        //   907: invokevirtual   VT_6_1_0_11/da.a:(Ljava/io/OutputStream;Ljava/lang/Object;Z)V
        //   910: goto            920
        //   913: astore_2       
        //   914: ldc             "DoorController.run(), exception cleaning up after run loop"
        //   916: aload_2        
        //   917: invokestatic    com/hw/client/util/a.a:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   920: aload_1        
        //   921: athrow         
        //   922: ldc             "DoorController.run(), calling destroyAgent()"
        //   924: invokestatic    com/hw/client/util/a.a:(Ljava/lang/String;)V
        //   927: aload_0        
        //   928: invokespecial   VT_6_1_0_11/cg.f:()V
        //   931: ldc             "DoorController.run(), leaving door controller run method"
        //   933: invokestatic    com/hw/client/util/a.c:(Ljava/lang/String;)V
        //   936: aload_0        
        //   937: getfield        VT_6_1_0_11/cg.f:LVT_6_1_0_11/da;
        //   940: invokevirtual   VT_6_1_0_11/da.l:()Z
        //   943: ifeq            953
        //   946: aload_0        
        //   947: getfield        VT_6_1_0_11/cg.i:Lcom/hw/client/util/j;
        //   950: invokevirtual   com/hw/client/util/j.b:()V
        //   953: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  386    470    473    490    Ljava/lang/Exception;
        //  83     126    127    163    Ljava/io/IOException;
        //  177    493    499    529    LVT_6_1_0_11/at;
        //  506    517    520    529    Ljava/lang/InterruptedException;
        //  546    556    559    565    Ljava/lang/InterruptedException;
        //  536    615    618    623    Any
        //  618    621    618    623    Any
        //  623    640    643    690    Ljava/io/IOException;
        //  529    687    690    737    Ljava/lang/RuntimeException;
        //  787    814    817    822    Any
        //  817    820    817    822    Any
        //  742    838    841    848    Ljava/lang/Exception;
        //  848    872    875    885    Ljava/lang/Exception;
        //  163    848    885    922    Any
        //  886    910    913    920    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
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
    
    private void e() {
        this.k |= 0x4;
        File parentFile;
        try {
            final File e = this.g.e();
            com.hw.client.util.a.b("DoorController.launchAgent(), agent_file => " + e);
            parentFile = e.getParentFile();
            com.hw.client.util.a.b("DoorController.launchAgent(), agent_dir => " + parentFile);
        }
        catch (IOException ex) {
            cx.a(181);
            throw ex;
        }
        try {
            final String[] i = this.f.i();
            com.hw.client.util.a.b("DoorController.launchAgent(), about to execute agent, command => " + this.f.j());
            this.d = Runtime.getRuntime().exec(i, null, parentFile);
        }
        catch (at at) {
            com.hw.client.util.a.a("DoorController.launchAgent(), unable to get agent command string", at);
            cx.a(182, at);
            return;
        }
        if (this.d == null) {
            com.hw.client.util.a.d("DoorController.launchAgent(), Runtime.exec returned null");
            cx.a(183);
            throw new IOException("DoorController.launchAgent(), Could not launch agent");
        }
        this.b = new j(this.f, 1, this.f.c(this.d.getInputStream()));
        this.c = new j(this.f, 2, this.f.d(this.d.getErrorStream()));
        this.e = this.f.a(this.d.getOutputStream());
        this.f.a(cG.a);
        this.k = 16;
        com.hw.client.util.a.b("DoorController.launchAgent(), agent started");
    }
    
    private void f() {
        this.k = 32;
        this.b.c();
        this.c.c();
        try {
            int n = 0;
            while (!this.g()) {
                com.hw.client.util.a.c("unable to kill agent process, attempt => " + n);
                if (++n >= 5) {
                    break;
                }
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex2) {}
            }
            this.f.a(cG.b);
        }
        catch (Exception ex) {
            com.hw.client.util.a.a("unknown exception while attempting to kill agent process", ex);
            this.k |= 0x40;
        }
    }
    
    private boolean g() {
        if (this.d == null) {
            return true;
        }
        com.hw.client.util.a.c("attempting to kill the agent process");
        this.d.destroy();
        try {
            com.hw.client.util.a.c("waiting for agent process to terminate");
            this.d.waitFor();
        }
        catch (InterruptedException ex) {
            com.hw.client.util.a.a("thread interrupted waiting for agent process to exit", ex);
            return false;
        }
        try {
            com.hw.client.util.a.c("retrieving the agent process exit value");
            this.d.exitValue();
        }
        catch (IllegalThreadStateException ex2) {
            com.hw.client.util.a.a("unable to stop agent, process may still be running", ex2);
            return false;
        }
        com.hw.client.util.a.c("successfully killed agent process");
        this.d = null;
        return true;
    }
    
    final void a(final Object o) {
        synchronized (this.a) {
            this.a.addElement(o);
            this.a.notifyAll();
        }
    }
    
    public final void d() {
        synchronized (this.a) {
            this.a.removeAllElements();
        }
    }
    
    static {
        cg.l = true;
    }
}
