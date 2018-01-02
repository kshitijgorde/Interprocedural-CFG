// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.net.URL;

class ea extends Thread
{
    private volatile URL a;
    private int b;
    private Object c;
    private volatile boolean d;
    private volatile boolean e;
    private volatile boolean f;
    final /* synthetic */ cg g;
    
    public ea(final cg g) {
        super(b("$|P3~\u001fcL;U\u0011p"));
        this.g = g;
        this.a = null;
        this.b = 1;
        this.c = null;
        this.d = true;
        this.e = false;
        this.f = true;
    }
    
    public void a() {
        this.f = false;
        dt.stopThread(this, b("4{U9V\u001fuFwN\u0018fG6^P}QwK\u0005}V#S\u001es\fy\u0014^:\fy\u0014"), 100, 0);
    }
    
    public synchronized void a(final boolean d) {
        this.d = d;
    }
    
    public synchronized boolean b() {
        return this.e;
    }
    
    public synchronized void c() {
        final boolean q = h.q;
        while (true) {
            while (true) {
                Label_0018: {
                    if (!q) {
                        break Label_0018;
                    }
                    try {
                        Thread.sleep(100L);
                    }
                    catch (InterruptedException ex) {}
                }
                if (this.e) {
                    continue;
                }
                break;
            }
            if (!q) {
                return;
            }
            continue;
        }
    }
    
    public synchronized void a(final URL a) {
        if (a != null) {
            this.a = a;
        }
    }
    
    public synchronized void a(final int b) {
        this.b = b;
    }
    
    public synchronized URL d() {
        return this.a;
    }
    
    public synchronized void a(final Object c) {
        this.c = c;
    }
    
    public void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       com/easypano/tw/h.q:Z
        //     3: istore          23
        //     5: iconst_0       
        //     6: istore_1       
        //     7: iconst_0       
        //     8: istore_2       
        //     9: iload           23
        //    11: ifeq            1292
        //    14: aload_0        
        //    15: iload           23
        //    17: ifne            27
        //    20: getfield        com/easypano/tw/ea.a:Ljava/net/URL;
        //    23: ifnull          38
        //    26: aload_0        
        //    27: iload           23
        //    29: ifne            209
        //    32: getfield        com/easypano/tw/ea.d:Z
        //    35: ifne            208
        //    38: ldc2_w          100
        //    41: invokestatic    java/lang/Thread.sleep:(J)V
        //    44: goto            48
        //    47: astore_3       
        //    48: aload_0        
        //    49: dup            
        //    50: astore_3       
        //    51: monitorenter   
        //    52: aload_0        
        //    53: iload           23
        //    55: ifne            198
        //    58: getfield        com/easypano/tw/ea.g:Lcom/easypano/tw/cg;
        //    61: invokestatic    com/easypano/tw/cg.a:(Lcom/easypano/tw/cg;)Z
        //    64: ifeq            197
        //    67: aload_0        
        //    68: iload           23
        //    70: ifne            198
        //    73: getfield        com/easypano/tw/ea.a:Ljava/net/URL;
        //    76: ifnonnull       197
        //    79: aload_0        
        //    80: iload           23
        //    82: ifne            198
        //    85: getfield        com/easypano/tw/ea.d:Z
        //    88: ifeq            197
        //    91: aload_0        
        //    92: getfield        com/easypano/tw/ea.g:Lcom/easypano/tw/cg;
        //    95: invokestatic    com/easypano/tw/cg.b:(Lcom/easypano/tw/cg;)I
        //    98: iconst_m1      
        //    99: iload           23
        //   101: ifne            151
        //   104: if_icmpne       124
        //   107: aload_0        
        //   108: getfield        com/easypano/tw/ea.g:Lcom/easypano/tw/cg;
        //   111: aload_0        
        //   112: getfield        com/easypano/tw/ea.g:Lcom/easypano/tw/cg;
        //   115: invokestatic    com/easypano/tw/cg.c:(Lcom/easypano/tw/cg;)Lcom/easypano/tw/TWViewer;
        //   118: invokevirtual   com/easypano/tw/TWViewer.l:()I
        //   121: invokestatic    com/easypano/tw/cg.a:(Lcom/easypano/tw/cg;I)V
        //   124: aload_0        
        //   125: getfield        com/easypano/tw/ea.g:Lcom/easypano/tw/cg;
        //   128: aload_0        
        //   129: getfield        com/easypano/tw/ea.g:Lcom/easypano/tw/cg;
        //   132: invokestatic    com/easypano/tw/cg.d:(Lcom/easypano/tw/cg;)I
        //   135: invokestatic    com/easypano/tw/cg.a:(Lcom/easypano/tw/cg;I)V
        //   138: aload_0        
        //   139: iload           23
        //   141: ifne            198
        //   144: getfield        com/easypano/tw/ea.g:Lcom/easypano/tw/cg;
        //   147: invokestatic    com/easypano/tw/cg.b:(Lcom/easypano/tw/cg;)I
        //   150: iconst_m1      
        //   151: if_icmple       197
        //   154: aload_0        
        //   155: getfield        com/easypano/tw/ea.g:Lcom/easypano/tw/cg;
        //   158: aload_0        
        //   159: getfield        com/easypano/tw/ea.g:Lcom/easypano/tw/cg;
        //   162: invokestatic    com/easypano/tw/cg.e:(Lcom/easypano/tw/cg;)Lcom/easypano/tw/ch;
        //   165: invokestatic    com/easypano/tw/cg.a:(Lcom/easypano/tw/cg;Lcom/easypano/tw/cf;)V
        //   168: aload_0        
        //   169: aload_0        
        //   170: getfield        com/easypano/tw/ea.g:Lcom/easypano/tw/cg;
        //   173: invokestatic    com/easypano/tw/cg.f:(Lcom/easypano/tw/cg;)Lcom/easypano/tw/cj;
        //   176: aload_0        
        //   177: getfield        com/easypano/tw/ea.g:Lcom/easypano/tw/cg;
        //   180: invokestatic    com/easypano/tw/cg.b:(Lcom/easypano/tw/cg;)I
        //   183: invokevirtual   com/easypano/tw/cj.a:(I)Lcom/easypano/tw/ci;
        //   186: getfield        com/easypano/tw/ci.k:Ljava/net/URL;
        //   189: putfield        com/easypano/tw/ea.a:Ljava/net/URL;
        //   192: aload_0        
        //   193: iconst_1       
        //   194: putfield        com/easypano/tw/ea.b:I
        //   197: aload_3        
        //   198: monitorexit    
        //   199: goto            1292
        //   202: aload_3        
        //   203: monitorexit    
        //   204: athrow         
        //   205: goto            1292
        //   208: aload_0        
        //   209: dup            
        //   210: astore_3       
        //   211: monitorenter   
        //   212: aload_0        
        //   213: iconst_1       
        //   214: putfield        com/easypano/tw/ea.e:Z
        //   217: iconst_0       
        //   218: istore          4
        //   220: aconst_null    
        //   221: checkcast       [B
        //   224: astore          5
        //   226: iconst_0       
        //   227: istore          9
        //   229: aconst_null    
        //   230: astore          10
        //   232: aconst_null    
        //   233: astore          11
        //   235: iconst_0       
        //   236: istore          12
        //   238: iconst_0       
        //   239: istore          13
        //   241: aconst_null    
        //   242: astore          14
        //   244: iconst_0       
        //   245: istore          15
        //   247: aload_0        
        //   248: getfield        com/easypano/tw/ea.a:Ljava/net/URL;
        //   251: invokevirtual   java/net/URL.getFile:()Ljava/lang/String;
        //   254: astore          16
        //   256: aload_0        
        //   257: aload           16
        //   259: invokespecial   com/easypano/tw/ea.a:(Ljava/lang/String;)Ljava/lang/String;
        //   262: astore          17
        //   264: aload           17
        //   266: ldc             "\u0000z\u0013"
        //   268: invokestatic    com/easypano/tw/ea.b:(Ljava/lang/String;)Ljava/lang/String;
        //   271: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   274: iload           23
        //   276: ifne            319
        //   279: ifne            318
        //   282: aload           17
        //   284: ldc             "\u001ad\u0013"
        //   286: invokestatic    com/easypano/tw/ea.b:(Ljava/lang/String;)Ljava/lang/String;
        //   289: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   292: iload           23
        //   294: ifne            319
        //   297: ifne            318
        //   300: aload           17
        //   302: ldc             "\u0017}\u0013"
        //   304: invokestatic    com/easypano/tw/ea.b:(Ljava/lang/String;)Ljava/lang/String;
        //   307: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   310: iload           23
        //   312: ifne            319
        //   315: ifeq            321
        //   318: iconst_1       
        //   319: istore          15
        //   321: aload_0        
        //   322: getfield        com/easypano/tw/ea.a:Ljava/net/URL;
        //   325: astore          18
        //   327: goto            506
        //   330: iload           4
        //   332: iload           23
        //   334: ifne            357
        //   337: iconst_1       
        //   338: if_icmpne       356
        //   341: iload_2        
        //   342: iload           23
        //   344: ifne            351
        //   347: ifeq            354
        //   350: iconst_0       
        //   351: goto            355
        //   354: iconst_1       
        //   355: istore_2       
        //   356: iload_2        
        //   357: ifeq            384
        //   360: aload_0        
        //   361: getfield        com/easypano/tw/ea.a:Ljava/net/URL;
        //   364: invokevirtual   java/net/URL.getFile:()Ljava/lang/String;
        //   367: invokestatic    com/easypano/tw/dt.l:(Ljava/lang/String;)Ljava/lang/String;
        //   370: astore          19
        //   372: aload           19
        //   374: invokestatic    com/easypano/tw/dt.j:(Ljava/lang/String;)Ljava/net/URL;
        //   377: astore          18
        //   379: iload           23
        //   381: ifeq            390
        //   384: aload_0        
        //   385: getfield        com/easypano/tw/ea.a:Ljava/net/URL;
        //   388: astore          18
        //   390: aload           18
        //   392: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //   395: astore          19
        //   397: aload           19
        //   399: iconst_0       
        //   400: invokevirtual   java/net/URLConnection.setUseCaches:(Z)V
        //   403: aload           19
        //   405: invokevirtual   java/net/URLConnection.getContentLength:()I
        //   408: istore          9
        //   410: goto            418
        //   413: astore          20
        //   415: iconst_0       
        //   416: istore          9
        //   418: aload           19
        //   420: invokevirtual   java/net/URLConnection.getInputStream:()Ljava/io/InputStream;
        //   423: astore          14
        //   425: goto            503
        //   428: astore          19
        //   430: iload           23
        //   432: ifne            465
        //   435: iload           4
        //   437: iconst_1       
        //   438: if_icmpne       503
        //   441: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   444: ldc             "\u0019{g/J"
        //   446: invokestatic    com/easypano/tw/ea.b:(Ljava/lang/String;)Ljava/lang/String;
        //   449: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   452: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   455: aload           18
        //   457: invokevirtual   java/io/PrintStream.println:(Ljava/lang/Object;)V
        //   460: aload           19
        //   462: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //   465: aconst_null    
        //   466: astore          14
        //   468: goto            503
        //   471: astore          19
        //   473: iload           23
        //   475: ifne            495
        //   478: iload           4
        //   480: iconst_1       
        //   481: if_icmpne       503
        //   484: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   487: ldc             "\u001f`J2HPqZ'"
        //   489: invokestatic    com/easypano/tw/ea.b:(Ljava/lang/String;)Ljava/lang/String;
        //   492: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   495: aconst_null    
        //   496: astore          14
        //   498: aload           19
        //   500: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //   503: iinc            4, 1
        //   506: aload           14
        //   508: ifnonnull       527
        //   511: iload           4
        //   513: iconst_2       
        //   514: iload           23
        //   516: ifne            99
        //   519: iload           23
        //   521: ifne            338
        //   524: if_icmplt       330
        //   527: aload           14
        //   529: iload           23
        //   531: ifne            564
        //   534: ifnonnull       562
        //   537: aload_0        
        //   538: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   541: aload           18
        //   543: invokevirtual   java/net/URL.toString:()Ljava/lang/String;
        //   546: invokevirtual   java/lang/Class.getResourceAsStream:(Ljava/lang/String;)Ljava/io/InputStream;
        //   549: astore          14
        //   551: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   554: aload           18
        //   556: invokevirtual   java/net/URL.getFile:()Ljava/lang/String;
        //   559: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   562: aload           14
        //   564: ifnull          1199
        //   567: aload_0        
        //   568: getfield        com/easypano/tw/ea.b:I
        //   571: tableswitch {
        //                8: 588
        //          default: 618
        //        }
        //   588: new             Ljava/util/zip/ZipInputStream;
        //   591: dup            
        //   592: aload           14
        //   594: invokespecial   java/util/zip/ZipInputStream.<init>:(Ljava/io/InputStream;)V
        //   597: astore          10
        //   599: aload_0        
        //   600: getfield        com/easypano/tw/ea.c:Ljava/lang/Object;
        //   603: invokevirtual   java/lang/Object.toString:()Ljava/lang/String;
        //   606: invokestatic    com/easypano/tw/dt.e:(Ljava/lang/String;)I
        //   609: istore          12
        //   611: aload           10
        //   613: astore          14
        //   615: goto            618
        //   618: aload_0        
        //   619: getfield        com/easypano/tw/ea.b:I
        //   622: iconst_4       
        //   623: if_icmpne       677
        //   626: aload           10
        //   628: invokevirtual   java/util/zip/ZipInputStream.getNextEntry:()Ljava/util/zip/ZipEntry;
        //   631: astore          11
        //   633: aload           11
        //   635: iload           23
        //   637: ifne            650
        //   640: ifnonnull       648
        //   643: iload           23
        //   645: ifeq            1133
        //   648: aload           11
        //   650: invokevirtual   java/util/zip/ZipEntry.isDirectory:()Z
        //   653: iload           23
        //   655: ifne            675
        //   658: ifeq            666
        //   661: iload           23
        //   663: ifeq            1101
        //   666: iinc            13, 1
        //   669: aload           11
        //   671: invokevirtual   java/util/zip/ZipEntry.getSize:()J
        //   674: l2i            
        //   675: istore          9
        //   677: iconst_0       
        //   678: istore          6
        //   680: iconst_0       
        //   681: istore          7
        //   683: iconst_0       
        //   684: istore          8
        //   686: iload           9
        //   688: iload           23
        //   690: ifne            698
        //   693: ifle            701
        //   696: iload           9
        //   698: goto            704
        //   701: sipush          10240
        //   704: newarray        B
        //   706: astore          5
        //   708: iload           9
        //   710: iload           23
        //   712: ifne            907
        //   715: ifle            903
        //   718: iload           23
        //   720: ifeq            790
        //   723: aload           14
        //   725: aload           5
        //   727: iload           8
        //   729: iload           9
        //   731: iload           8
        //   733: isub           
        //   734: invokevirtual   java/io/InputStream.read:([BII)I
        //   737: istore          6
        //   739: iload           6
        //   741: iconst_m1      
        //   742: iload           23
        //   744: ifne            767
        //   747: if_icmpne       755
        //   750: iload           23
        //   752: ifeq            980
        //   755: iload           8
        //   757: iload           6
        //   759: iadd           
        //   760: istore          8
        //   762: aload_0        
        //   763: getfield        com/easypano/tw/ea.b:I
        //   766: iconst_4       
        //   767: if_icmpeq       790
        //   770: aload_0        
        //   771: getfield        com/easypano/tw/ea.g:Lcom/easypano/tw/cg;
        //   774: aload_0        
        //   775: iload           8
        //   777: iload           9
        //   779: invokespecial   com/easypano/tw/ea.a:(II)I
        //   782: iload           23
        //   784: ifne            1258
        //   787: invokestatic    com/easypano/tw/cg.b:(Lcom/easypano/tw/cg;I)V
        //   790: aload_0        
        //   791: getfield        com/easypano/tw/ea.d:Z
        //   794: ifeq            980
        //   797: iload           9
        //   799: iload           8
        //   801: isub           
        //   802: iload           23
        //   804: ifne            741
        //   807: ifgt            723
        //   810: goto            980
        //   813: goto            903
        //   816: iload           8
        //   818: iload           6
        //   820: iadd           
        //   821: istore          8
        //   823: iload           7
        //   825: iload           6
        //   827: iadd           
        //   828: istore          7
        //   830: iload           7
        //   832: sipush          10240
        //   835: iadd           
        //   836: aload           5
        //   838: arraylength    
        //   839: iload           23
        //   841: ifne            877
        //   844: if_icmple       872
        //   847: iload           7
        //   849: sipush          10240
        //   852: iadd           
        //   853: newarray        B
        //   855: astore          19
        //   857: aload           5
        //   859: iconst_0       
        //   860: aload           19
        //   862: iconst_0       
        //   863: iload           7
        //   865: invokestatic    java/lang/System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //   868: aload           19
        //   870: astore          5
        //   872: aload_0        
        //   873: getfield        com/easypano/tw/ea.b:I
        //   876: iconst_4       
        //   877: if_icmpeq       903
        //   880: aload_0        
        //   881: getfield        com/easypano/tw/ea.g:Lcom/easypano/tw/cg;
        //   884: aload_0        
        //   885: iload           8
        //   887: iload           9
        //   889: sipush          10240
        //   892: invokespecial   com/easypano/tw/ea.a:(III)I
        //   895: iload           23
        //   897: ifne            1258
        //   900: invokestatic    com/easypano/tw/cg.b:(Lcom/easypano/tw/cg;I)V
        //   903: aload_0        
        //   904: getfield        com/easypano/tw/ea.d:Z
        //   907: ifeq            934
        //   910: aload           14
        //   912: aload           5
        //   914: iload           7
        //   916: sipush          10240
        //   919: invokevirtual   java/io/InputStream.read:([BII)I
        //   922: dup            
        //   923: istore          6
        //   925: iload           23
        //   927: ifne            938
        //   930: iconst_m1      
        //   931: if_icmpne       816
        //   934: aload_0        
        //   935: getfield        com/easypano/tw/ea.d:Z
        //   938: iload           23
        //   940: ifne            984
        //   943: ifeq            980
        //   946: iload           8
        //   948: iload           23
        //   950: ifne            984
        //   953: aload           5
        //   955: arraylength    
        //   956: if_icmpge       980
        //   959: iload           8
        //   961: newarray        B
        //   963: astore          19
        //   965: aload           5
        //   967: iconst_0       
        //   968: aload           19
        //   970: iconst_0       
        //   971: iload           8
        //   973: invokestatic    java/lang/System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //   976: aload           19
        //   978: astore          5
        //   980: aload_0        
        //   981: getfield        com/easypano/tw/ea.d:Z
        //   984: iload           23
        //   986: ifne            1105
        //   989: ifeq            1101
        //   992: iload           15
        //   994: iload           23
        //   996: ifne            1016
        //   999: ifeq            1007
        //  1002: aload           5
        //  1004: invokestatic    com/easypano/tw/cg.a:([B)V
        //  1007: aload_0        
        //  1008: iload           23
        //  1010: ifne            1098
        //  1013: getfield        com/easypano/tw/ea.b:I
        //  1016: tableswitch {
        //                8: 1036
        //          default: 1082
        //        }
        //  1036: aload_0        
        //  1037: getfield        com/easypano/tw/ea.g:Lcom/easypano/tw/cg;
        //  1040: invokestatic    com/easypano/tw/cg.g:(Lcom/easypano/tw/cg;)Ljava/util/Hashtable;
        //  1043: aload           11
        //  1045: invokevirtual   java/util/zip/ZipEntry.getName:()Ljava/lang/String;
        //  1048: bipush          92
        //  1050: bipush          47
        //  1052: invokevirtual   java/lang/String.replace:(CC)Ljava/lang/String;
        //  1055: aload           5
        //  1057: invokevirtual   java/util/Hashtable.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1060: pop            
        //  1061: aload_0        
        //  1062: getfield        com/easypano/tw/ea.g:Lcom/easypano/tw/cg;
        //  1065: aload_0        
        //  1066: iload           13
        //  1068: iload           12
        //  1070: iconst_2       
        //  1071: invokespecial   com/easypano/tw/ea.a:(III)I
        //  1074: invokestatic    com/easypano/tw/cg.b:(Lcom/easypano/tw/cg;I)V
        //  1077: iload           23
        //  1079: ifeq            1099
        //  1082: aload_0        
        //  1083: getfield        com/easypano/tw/ea.g:Lcom/easypano/tw/cg;
        //  1086: invokestatic    com/easypano/tw/cg.g:(Lcom/easypano/tw/cg;)Ljava/util/Hashtable;
        //  1089: aload_0        
        //  1090: getfield        com/easypano/tw/ea.a:Ljava/net/URL;
        //  1093: aload           5
        //  1095: invokevirtual   java/util/Hashtable.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1098: pop            
        //  1099: iconst_1       
        //  1100: istore_1       
        //  1101: aload_0        
        //  1102: getfield        com/easypano/tw/ea.d:Z
        //  1105: iload           23
        //  1107: ifne            1117
        //  1110: ifeq            1133
        //  1113: aload_0        
        //  1114: getfield        com/easypano/tw/ea.b:I
        //  1117: iconst_4       
        //  1118: if_icmpeq       618
        //  1121: goto            1133
        //  1124: astore          19
        //  1126: aload           19
        //  1128: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //  1131: iconst_0       
        //  1132: istore_1       
        //  1133: aload           14
        //  1135: invokevirtual   java/io/InputStream.close:()V
        //  1138: goto            1143
        //  1141: astore          19
        //  1143: aload_0        
        //  1144: iload           23
        //  1146: ifne            1190
        //  1149: getfield        com/easypano/tw/ea.b:I
        //  1152: tableswitch {
        //                8: 1172
        //          default: 1196
        //        }
        //  1172: aload_0        
        //  1173: getfield        com/easypano/tw/ea.g:Lcom/easypano/tw/cg;
        //  1176: invokestatic    com/easypano/tw/cg.g:(Lcom/easypano/tw/cg;)Ljava/util/Hashtable;
        //  1179: aload_0        
        //  1180: getfield        com/easypano/tw/ea.a:Ljava/net/URL;
        //  1183: aload_0        
        //  1184: getfield        com/easypano/tw/ea.a:Ljava/net/URL;
        //  1187: invokevirtual   java/util/Hashtable.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1190: pop            
        //  1191: iload           23
        //  1193: ifeq            1213
        //  1196: goto            1213
        //  1199: iconst_0       
        //  1200: istore_1       
        //  1201: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //  1204: ldc_w           "3uLpNPsG#\u001a\u0002qQ8O\u0002wGwS\u001edW#I\u0004fG6W"
        //  1207: invokestatic    com/easypano/tw/ea.b:(Ljava/lang/String;)Ljava/lang/String;
        //  1210: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //  1213: aload_0        
        //  1214: getfield        com/easypano/tw/ea.d:Z
        //  1217: iload           23
        //  1219: ifne            1236
        //  1222: ifeq            1235
        //  1225: aload_0        
        //  1226: aconst_null    
        //  1227: putfield        com/easypano/tw/ea.a:Ljava/net/URL;
        //  1230: aload_0        
        //  1231: iconst_0       
        //  1232: putfield        com/easypano/tw/ea.e:Z
        //  1235: iload_1        
        //  1236: ifeq            1253
        //  1239: aload_0        
        //  1240: getfield        com/easypano/tw/ea.g:Lcom/easypano/tw/cg;
        //  1243: bipush          100
        //  1245: invokestatic    com/easypano/tw/cg.b:(Lcom/easypano/tw/cg;I)V
        //  1248: iload           23
        //  1250: ifeq            1261
        //  1253: aload_0        
        //  1254: getfield        com/easypano/tw/ea.g:Lcom/easypano/tw/cg;
        //  1257: iconst_m1      
        //  1258: invokestatic    com/easypano/tw/cg.b:(Lcom/easypano/tw/cg;I)V
        //  1261: aload_3        
        //  1262: monitorexit    
        //  1263: goto            1289
        //  1266: aload_3        
        //  1267: monitorexit    
        //  1268: athrow         
        //  1269: goto            1289
        //  1272: astore          22
        //  1274: jsr             1280
        //  1277: aload           22
        //  1279: athrow         
        //  1280: astore          21
        //  1282: aload_0        
        //  1283: iconst_0       
        //  1284: putfield        com/easypano/tw/ea.e:Z
        //  1287: ret             21
        //  1289: jsr             1280
        //  1292: aload_0        
        //  1293: getfield        com/easypano/tw/ea.f:Z
        //  1296: ifne            14
        //  1299: iload           23
        //  1301: ifne            44
        //  1304: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  38     47     47     48     Ljava/lang/InterruptedException;
        //  52     202    202    205    Any
        //  403    413    413    418    Ljava/lang/Exception;
        //  390    428    428    471    Ljava/io/IOException;
        //  390    428    471    503    Ljava/lang/Exception;
        //  618    1124   1124   1133   Ljava/lang/Exception;
        //  1133   1141   1141   1143   Ljava/io/IOException;
        //  212    1266   1266   1269   Any
        //  208    1272   1272   1280   Any
        //  1289   1292   1272   1280   Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0099 (coming from #1281).
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2181)
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
    
    private String a(final String s) {
        int lastIndex;
        final int n2;
        final int n = n2 = (lastIndex = s.lastIndexOf("."));
        final int n3 = -1;
        if (!h.q) {
            if (n == n3) {
                return "";
            }
            lastIndex = n2;
            final int n4 = s.length() - 1;
        }
        if (lastIndex != n3) {
            return s.substring(n2 + 1);
        }
        return "";
    }
    
    private int a(final int n, final int n2, final int n3) {
        final boolean q = h.q;
        int n4 = n2;
        int n5 = 0;
        Label_0056: {
            if (!q) {
                if (n2 > 0) {
                    n5 = (int)(n * 100.0 / n2);
                    if (!q) {
                        break Label_0056;
                    }
                }
                n4 = n / n3;
            }
            n5 = 100 - (int)(Math.pow(0.9, n4) * 100.0);
        }
        final int n6 = n5;
        if (!q && n6 > 99) {
            n5 = 99;
            goto Label_0072;
        }
        return n6;
    }
    
    private int a(final int n, final int n2) {
        return this.a(n, n2, 10240);
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
                            c2 = 'p';
                            break;
                        }
                        case 1: {
                            c2 = '\u0014';
                            break;
                        }
                        case 2: {
                            c2 = '\"';
                            break;
                        }
                        case 3: {
                            c2 = 'W';
                            break;
                        }
                        default: {
                            c2 = ':';
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
