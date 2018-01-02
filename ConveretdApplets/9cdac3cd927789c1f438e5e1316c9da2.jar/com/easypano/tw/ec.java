// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.net.URL;

class ec extends Thread
{
    private volatile URL a;
    private int b;
    private Object c;
    private volatile boolean d;
    private volatile boolean e;
    private volatile boolean f;
    final /* synthetic */ ch g;
    
    public ec(final ch g) {
        super(b("-!\u007fl,\u0016>cd\u0007\u0018-"));
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
        dt.stopThread(this, b("=&zf\u0004\u0016(i(\u001c\u0011;hi\fY ~(\u0019\f y|\u0001\u0017.#&FWg#&F"), 100, 0);
    }
    
    public synchronized void a(final boolean d) {
        this.d = d;
    }
    
    public synchronized boolean b() {
        return this.e;
    }
    
    public synchronized void c() {
        final boolean q = com.easypano.tw.g.q;
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
        //     0: getstatic       com/easypano/tw/g.q:Z
        //     3: istore          22
        //     5: iconst_0       
        //     6: istore_1       
        //     7: iconst_0       
        //     8: istore_2       
        //     9: iload           22
        //    11: ifeq            1275
        //    14: aload_0        
        //    15: iload           22
        //    17: ifne            27
        //    20: getfield        com/easypano/tw/ec.a:Ljava/net/URL;
        //    23: ifnull          38
        //    26: aload_0        
        //    27: iload           22
        //    29: ifne            209
        //    32: getfield        com/easypano/tw/ec.d:Z
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
        //    53: iload           22
        //    55: ifne            198
        //    58: getfield        com/easypano/tw/ec.g:Lcom/easypano/tw/ch;
        //    61: invokestatic    com/easypano/tw/ch.a:(Lcom/easypano/tw/ch;)Z
        //    64: ifeq            197
        //    67: aload_0        
        //    68: iload           22
        //    70: ifne            198
        //    73: getfield        com/easypano/tw/ec.a:Ljava/net/URL;
        //    76: ifnonnull       197
        //    79: aload_0        
        //    80: iload           22
        //    82: ifne            198
        //    85: getfield        com/easypano/tw/ec.d:Z
        //    88: ifeq            197
        //    91: aload_0        
        //    92: getfield        com/easypano/tw/ec.g:Lcom/easypano/tw/ch;
        //    95: invokestatic    com/easypano/tw/ch.b:(Lcom/easypano/tw/ch;)I
        //    98: iconst_m1      
        //    99: iload           22
        //   101: ifne            151
        //   104: if_icmpne       124
        //   107: aload_0        
        //   108: getfield        com/easypano/tw/ec.g:Lcom/easypano/tw/ch;
        //   111: aload_0        
        //   112: getfield        com/easypano/tw/ec.g:Lcom/easypano/tw/ch;
        //   115: invokestatic    com/easypano/tw/ch.c:(Lcom/easypano/tw/ch;)Lcom/easypano/tw/TWViewer;
        //   118: invokevirtual   com/easypano/tw/TWViewer.l:()I
        //   121: invokestatic    com/easypano/tw/ch.a:(Lcom/easypano/tw/ch;I)V
        //   124: aload_0        
        //   125: getfield        com/easypano/tw/ec.g:Lcom/easypano/tw/ch;
        //   128: aload_0        
        //   129: getfield        com/easypano/tw/ec.g:Lcom/easypano/tw/ch;
        //   132: invokestatic    com/easypano/tw/ch.d:(Lcom/easypano/tw/ch;)I
        //   135: invokestatic    com/easypano/tw/ch.a:(Lcom/easypano/tw/ch;I)V
        //   138: aload_0        
        //   139: iload           22
        //   141: ifne            198
        //   144: getfield        com/easypano/tw/ec.g:Lcom/easypano/tw/ch;
        //   147: invokestatic    com/easypano/tw/ch.b:(Lcom/easypano/tw/ch;)I
        //   150: iconst_m1      
        //   151: if_icmple       197
        //   154: aload_0        
        //   155: getfield        com/easypano/tw/ec.g:Lcom/easypano/tw/ch;
        //   158: aload_0        
        //   159: getfield        com/easypano/tw/ec.g:Lcom/easypano/tw/ch;
        //   162: invokestatic    com/easypano/tw/ch.e:(Lcom/easypano/tw/ch;)Lcom/easypano/tw/ci;
        //   165: invokestatic    com/easypano/tw/ch.a:(Lcom/easypano/tw/ch;Lcom/easypano/tw/cg;)V
        //   168: aload_0        
        //   169: aload_0        
        //   170: getfield        com/easypano/tw/ec.g:Lcom/easypano/tw/ch;
        //   173: invokestatic    com/easypano/tw/ch.f:(Lcom/easypano/tw/ch;)Lcom/easypano/tw/ck;
        //   176: aload_0        
        //   177: getfield        com/easypano/tw/ec.g:Lcom/easypano/tw/ch;
        //   180: invokestatic    com/easypano/tw/ch.b:(Lcom/easypano/tw/ch;)I
        //   183: invokevirtual   com/easypano/tw/ck.a:(I)Lcom/easypano/tw/cj;
        //   186: getfield        com/easypano/tw/cj.k:Ljava/net/URL;
        //   189: putfield        com/easypano/tw/ec.a:Ljava/net/URL;
        //   192: aload_0        
        //   193: iconst_1       
        //   194: putfield        com/easypano/tw/ec.b:I
        //   197: aload_3        
        //   198: monitorexit    
        //   199: goto            1275
        //   202: aload_3        
        //   203: monitorexit    
        //   204: athrow         
        //   205: goto            1275
        //   208: aload_0        
        //   209: dup            
        //   210: astore_3       
        //   211: monitorenter   
        //   212: aload_0        
        //   213: iconst_1       
        //   214: putfield        com/easypano/tw/ec.e:Z
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
        //   248: getfield        com/easypano/tw/ec.a:Ljava/net/URL;
        //   251: invokevirtual   java/net/URL.getFile:()Ljava/lang/String;
        //   254: astore          16
        //   256: aload_0        
        //   257: aload           16
        //   259: invokespecial   com/easypano/tw/ec.a:(Ljava/lang/String;)Ljava/lang/String;
        //   262: astore          17
        //   264: aload           17
        //   266: ldc             "\t'<"
        //   268: invokestatic    com/easypano/tw/ec.b:(Ljava/lang/String;)Ljava/lang/String;
        //   271: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   274: iload           22
        //   276: ifne            319
        //   279: ifne            318
        //   282: aload           17
        //   284: ldc             "\u00139<"
        //   286: invokestatic    com/easypano/tw/ec.b:(Ljava/lang/String;)Ljava/lang/String;
        //   289: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   292: iload           22
        //   294: ifne            319
        //   297: ifne            318
        //   300: aload           17
        //   302: ldc             "\u001e/<"
        //   304: invokestatic    com/easypano/tw/ec.b:(Ljava/lang/String;)Ljava/lang/String;
        //   307: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   310: iload           22
        //   312: ifne            319
        //   315: ifeq            321
        //   318: iconst_1       
        //   319: istore          15
        //   321: aload_0        
        //   322: getfield        com/easypano/tw/ec.a:Ljava/net/URL;
        //   325: astore          18
        //   327: goto            536
        //   330: iload           4
        //   332: iload           22
        //   334: ifne            357
        //   337: iconst_1       
        //   338: if_icmpne       356
        //   341: iload_2        
        //   342: iload           22
        //   344: ifne            351
        //   347: ifeq            354
        //   350: iconst_0       
        //   351: goto            355
        //   354: iconst_1       
        //   355: istore_2       
        //   356: iload_2        
        //   357: ifeq            384
        //   360: aload_0        
        //   361: getfield        com/easypano/tw/ec.a:Ljava/net/URL;
        //   364: invokevirtual   java/net/URL.getFile:()Ljava/lang/String;
        //   367: invokestatic    com/easypano/tw/dt.l:(Ljava/lang/String;)Ljava/lang/String;
        //   370: astore          19
        //   372: aload           19
        //   374: invokestatic    com/easypano/tw/dt.j:(Ljava/lang/String;)Ljava/net/URL;
        //   377: astore          18
        //   379: iload           22
        //   381: ifeq            390
        //   384: aload_0        
        //   385: getfield        com/easypano/tw/ec.a:Ljava/net/URL;
        //   388: astore          18
        //   390: aload           18
        //   392: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //   395: astore          19
        //   397: aload           19
        //   399: iconst_1       
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
        //   425: goto            498
        //   428: astore          19
        //   430: iload           22
        //   432: ifne            460
        //   435: iload           4
        //   437: iconst_1       
        //   438: if_icmpne       498
        //   441: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   444: ldc             "\u0010&Hp\u0018"
        //   446: invokestatic    com/easypano/tw/ec.b:(Ljava/lang/String;)Ljava/lang/String;
        //   449: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   452: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   455: aload           18
        //   457: invokevirtual   java/io/PrintStream.println:(Ljava/lang/Object;)V
        //   460: aconst_null    
        //   461: astore          14
        //   463: goto            498
        //   466: astore          19
        //   468: iload           22
        //   470: ifne            490
        //   473: iload           4
        //   475: iconst_1       
        //   476: if_icmpne       498
        //   479: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   482: ldc             "\u0016=em\u001aY,ux"
        //   484: invokestatic    com/easypano/tw/ec.b:(Ljava/lang/String;)Ljava/lang/String;
        //   487: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   490: aconst_null    
        //   491: astore          14
        //   493: aload           19
        //   495: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //   498: aload           14
        //   500: iload           22
        //   502: ifne            520
        //   505: ifnonnull       533
        //   508: aload_0        
        //   509: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   512: aload           18
        //   514: invokevirtual   java/net/URL.toString:()Ljava/lang/String;
        //   517: invokevirtual   java/lang/Class.getResourceAsStream:(Ljava/lang/String;)Ljava/io/InputStream;
        //   520: astore          14
        //   522: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   525: aload           18
        //   527: invokevirtual   java/net/URL.getFile:()Ljava/lang/String;
        //   530: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   533: iinc            4, 1
        //   536: aload           14
        //   538: ifnonnull       557
        //   541: iload           4
        //   543: iconst_2       
        //   544: iload           22
        //   546: ifne            99
        //   549: iload           22
        //   551: ifne            338
        //   554: if_icmplt       330
        //   557: aload           14
        //   559: ifnull          1187
        //   562: aload_0        
        //   563: getfield        com/easypano/tw/ec.b:I
        //   566: tableswitch {
        //                8: 584
        //          default: 614
        //        }
        //   584: new             Ljava/util/zip/ZipInputStream;
        //   587: dup            
        //   588: aload           14
        //   590: invokespecial   java/util/zip/ZipInputStream.<init>:(Ljava/io/InputStream;)V
        //   593: astore          10
        //   595: aload_0        
        //   596: getfield        com/easypano/tw/ec.c:Ljava/lang/Object;
        //   599: invokevirtual   java/lang/Object.toString:()Ljava/lang/String;
        //   602: invokestatic    com/easypano/tw/dt.e:(Ljava/lang/String;)I
        //   605: istore          12
        //   607: aload           10
        //   609: astore          14
        //   611: goto            614
        //   614: aload_0        
        //   615: getfield        com/easypano/tw/ec.b:I
        //   618: iconst_4       
        //   619: if_icmpne       673
        //   622: aload           10
        //   624: invokevirtual   java/util/zip/ZipInputStream.getNextEntry:()Ljava/util/zip/ZipEntry;
        //   627: astore          11
        //   629: aload           11
        //   631: iload           22
        //   633: ifne            646
        //   636: ifnonnull       644
        //   639: iload           22
        //   641: ifeq            1122
        //   644: aload           11
        //   646: invokevirtual   java/util/zip/ZipEntry.isDirectory:()Z
        //   649: iload           22
        //   651: ifne            671
        //   654: ifeq            662
        //   657: iload           22
        //   659: ifeq            1090
        //   662: iinc            13, 1
        //   665: aload           11
        //   667: invokevirtual   java/util/zip/ZipEntry.getSize:()J
        //   670: l2i            
        //   671: istore          9
        //   673: iconst_0       
        //   674: istore          6
        //   676: iconst_0       
        //   677: istore          7
        //   679: iconst_0       
        //   680: istore          8
        //   682: iload           9
        //   684: iload           22
        //   686: ifne            694
        //   689: ifle            697
        //   692: iload           9
        //   694: goto            700
        //   697: sipush          10240
        //   700: newarray        B
        //   702: astore          5
        //   704: iload           9
        //   706: iload           22
        //   708: ifne            903
        //   711: ifle            899
        //   714: iload           22
        //   716: ifeq            786
        //   719: aload           14
        //   721: aload           5
        //   723: iload           8
        //   725: iload           9
        //   727: iload           8
        //   729: isub           
        //   730: invokevirtual   java/io/InputStream.read:([BII)I
        //   733: istore          6
        //   735: iload           6
        //   737: iconst_m1      
        //   738: iload           22
        //   740: ifne            763
        //   743: if_icmpne       751
        //   746: iload           22
        //   748: ifeq            976
        //   751: iload           8
        //   753: iload           6
        //   755: iadd           
        //   756: istore          8
        //   758: aload_0        
        //   759: getfield        com/easypano/tw/ec.b:I
        //   762: iconst_4       
        //   763: if_icmpeq       786
        //   766: aload_0        
        //   767: getfield        com/easypano/tw/ec.g:Lcom/easypano/tw/ch;
        //   770: aload_0        
        //   771: iload           8
        //   773: iload           9
        //   775: invokespecial   com/easypano/tw/ec.a:(II)I
        //   778: iload           22
        //   780: ifne            1246
        //   783: invokestatic    com/easypano/tw/ch.b:(Lcom/easypano/tw/ch;I)V
        //   786: aload_0        
        //   787: getfield        com/easypano/tw/ec.d:Z
        //   790: ifeq            976
        //   793: iload           9
        //   795: iload           8
        //   797: isub           
        //   798: iload           22
        //   800: ifne            737
        //   803: ifgt            719
        //   806: goto            976
        //   809: goto            899
        //   812: iload           8
        //   814: iload           6
        //   816: iadd           
        //   817: istore          8
        //   819: iload           7
        //   821: iload           6
        //   823: iadd           
        //   824: istore          7
        //   826: iload           7
        //   828: sipush          10240
        //   831: iadd           
        //   832: aload           5
        //   834: arraylength    
        //   835: iload           22
        //   837: ifne            873
        //   840: if_icmple       868
        //   843: iload           7
        //   845: sipush          10240
        //   848: iadd           
        //   849: newarray        B
        //   851: astore          19
        //   853: aload           5
        //   855: iconst_0       
        //   856: aload           19
        //   858: iconst_0       
        //   859: iload           7
        //   861: invokestatic    java/lang/System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //   864: aload           19
        //   866: astore          5
        //   868: aload_0        
        //   869: getfield        com/easypano/tw/ec.b:I
        //   872: iconst_4       
        //   873: if_icmpeq       899
        //   876: aload_0        
        //   877: getfield        com/easypano/tw/ec.g:Lcom/easypano/tw/ch;
        //   880: aload_0        
        //   881: iload           8
        //   883: iload           9
        //   885: sipush          10240
        //   888: invokespecial   com/easypano/tw/ec.a:(III)I
        //   891: iload           22
        //   893: ifne            1246
        //   896: invokestatic    com/easypano/tw/ch.b:(Lcom/easypano/tw/ch;I)V
        //   899: aload_0        
        //   900: getfield        com/easypano/tw/ec.d:Z
        //   903: ifeq            930
        //   906: aload           14
        //   908: aload           5
        //   910: iload           7
        //   912: sipush          10240
        //   915: invokevirtual   java/io/InputStream.read:([BII)I
        //   918: dup            
        //   919: istore          6
        //   921: iload           22
        //   923: ifne            934
        //   926: iconst_m1      
        //   927: if_icmpne       812
        //   930: aload_0        
        //   931: getfield        com/easypano/tw/ec.d:Z
        //   934: iload           22
        //   936: ifne            980
        //   939: ifeq            976
        //   942: iload           8
        //   944: iload           22
        //   946: ifne            980
        //   949: aload           5
        //   951: arraylength    
        //   952: if_icmpge       976
        //   955: iload           8
        //   957: newarray        B
        //   959: astore          19
        //   961: aload           5
        //   963: iconst_0       
        //   964: aload           19
        //   966: iconst_0       
        //   967: iload           8
        //   969: invokestatic    java/lang/System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //   972: aload           19
        //   974: astore          5
        //   976: aload_0        
        //   977: getfield        com/easypano/tw/ec.d:Z
        //   980: iload           22
        //   982: ifne            1094
        //   985: ifeq            1090
        //   988: iload           15
        //   990: iload           22
        //   992: ifne            1012
        //   995: ifeq            1003
        //   998: aload           5
        //  1000: invokestatic    com/easypano/tw/ch.a:([B)V
        //  1003: aload_0        
        //  1004: iload           22
        //  1006: ifne            1087
        //  1009: getfield        com/easypano/tw/ec.b:I
        //  1012: tableswitch {
        //                8: 1032
        //          default: 1071
        //        }
        //  1032: aload_0        
        //  1033: getfield        com/easypano/tw/ec.g:Lcom/easypano/tw/ch;
        //  1036: invokestatic    com/easypano/tw/ch.g:(Lcom/easypano/tw/ch;)Ljava/util/Hashtable;
        //  1039: aload           11
        //  1041: invokevirtual   java/util/zip/ZipEntry.getName:()Ljava/lang/String;
        //  1044: aload           5
        //  1046: invokevirtual   java/util/Hashtable.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1049: pop            
        //  1050: aload_0        
        //  1051: getfield        com/easypano/tw/ec.g:Lcom/easypano/tw/ch;
        //  1054: aload_0        
        //  1055: iload           13
        //  1057: iload           12
        //  1059: iconst_2       
        //  1060: invokespecial   com/easypano/tw/ec.a:(III)I
        //  1063: invokestatic    com/easypano/tw/ch.b:(Lcom/easypano/tw/ch;I)V
        //  1066: iload           22
        //  1068: ifeq            1088
        //  1071: aload_0        
        //  1072: getfield        com/easypano/tw/ec.g:Lcom/easypano/tw/ch;
        //  1075: invokestatic    com/easypano/tw/ch.g:(Lcom/easypano/tw/ch;)Ljava/util/Hashtable;
        //  1078: aload_0        
        //  1079: getfield        com/easypano/tw/ec.a:Ljava/net/URL;
        //  1082: aload           5
        //  1084: invokevirtual   java/util/Hashtable.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1087: pop            
        //  1088: iconst_1       
        //  1089: istore_1       
        //  1090: aload_0        
        //  1091: getfield        com/easypano/tw/ec.d:Z
        //  1094: iload           22
        //  1096: ifne            1106
        //  1099: ifeq            1122
        //  1102: aload_0        
        //  1103: getfield        com/easypano/tw/ec.b:I
        //  1106: iconst_4       
        //  1107: if_icmpeq       614
        //  1110: goto            1122
        //  1113: astore          19
        //  1115: aload           19
        //  1117: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //  1120: iconst_0       
        //  1121: istore_1       
        //  1122: aload           14
        //  1124: invokevirtual   java/io/InputStream.close:()V
        //  1127: goto            1132
        //  1130: astore          19
        //  1132: aload_0        
        //  1133: iload           22
        //  1135: ifne            1178
        //  1138: getfield        com/easypano/tw/ec.b:I
        //  1141: tableswitch {
        //                8: 1160
        //          default: 1184
        //        }
        //  1160: aload_0        
        //  1161: getfield        com/easypano/tw/ec.g:Lcom/easypano/tw/ch;
        //  1164: invokestatic    com/easypano/tw/ch.g:(Lcom/easypano/tw/ch;)Ljava/util/Hashtable;
        //  1167: aload_0        
        //  1168: getfield        com/easypano/tw/ec.a:Ljava/net/URL;
        //  1171: aload_0        
        //  1172: getfield        com/easypano/tw/ec.a:Ljava/net/URL;
        //  1175: invokevirtual   java/util/Hashtable.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1178: pop            
        //  1179: iload           22
        //  1181: ifeq            1201
        //  1184: goto            1201
        //  1187: iconst_0       
        //  1188: istore_1       
        //  1189: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //  1192: ldc_w           ":(c/\u001cY.h|H\u000b,~g\u001d\u000b*h(\u0001\u00179x|\u001b\r;hi\u0005"
        //  1195: invokestatic    com/easypano/tw/ec.b:(Ljava/lang/String;)Ljava/lang/String;
        //  1198: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //  1201: aload_0        
        //  1202: getfield        com/easypano/tw/ec.d:Z
        //  1205: iload           22
        //  1207: ifne            1224
        //  1210: ifeq            1223
        //  1213: aload_0        
        //  1214: aconst_null    
        //  1215: putfield        com/easypano/tw/ec.a:Ljava/net/URL;
        //  1218: aload_0        
        //  1219: iconst_0       
        //  1220: putfield        com/easypano/tw/ec.e:Z
        //  1223: iload_1        
        //  1224: ifeq            1241
        //  1227: aload_0        
        //  1228: getfield        com/easypano/tw/ec.g:Lcom/easypano/tw/ch;
        //  1231: bipush          100
        //  1233: invokestatic    com/easypano/tw/ch.b:(Lcom/easypano/tw/ch;I)V
        //  1236: iload           22
        //  1238: ifeq            1249
        //  1241: aload_0        
        //  1242: getfield        com/easypano/tw/ec.g:Lcom/easypano/tw/ch;
        //  1245: iconst_m1      
        //  1246: invokestatic    com/easypano/tw/ch.b:(Lcom/easypano/tw/ch;I)V
        //  1249: aload_3        
        //  1250: monitorexit    
        //  1251: goto            1270
        //  1254: aload_3        
        //  1255: monitorexit    
        //  1256: athrow         
        //  1257: goto            1270
        //  1260: astore          21
        //  1262: aload_0        
        //  1263: iconst_0       
        //  1264: putfield        com/easypano/tw/ec.e:Z
        //  1267: aload           21
        //  1269: athrow         
        //  1270: aload_0        
        //  1271: iconst_0       
        //  1272: putfield        com/easypano/tw/ec.e:Z
        //  1275: aload_0        
        //  1276: getfield        com/easypano/tw/ec.f:Z
        //  1279: ifne            14
        //  1282: iload           22
        //  1284: ifne            44
        //  1287: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  38     47     47     48     Ljava/lang/InterruptedException;
        //  52     202    202    205    Any
        //  403    413    413    418    Ljava/lang/Exception;
        //  390    428    428    466    Ljava/io/IOException;
        //  390    428    466    498    Ljava/lang/Exception;
        //  614    1113   1113   1122   Ljava/lang/Exception;
        //  1122   1130   1130   1132   Ljava/io/IOException;
        //  212    1254   1254   1257   Any
        //  208    1260   1260   1270   Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0099 (coming from #1269).
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
        if (!com.easypano.tw.g.q) {
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
        final boolean q = com.easypano.tw.g.q;
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
                            c2 = 'y';
                            break;
                        }
                        case 1: {
                            c2 = 'I';
                            break;
                        }
                        case 2: {
                            c2 = '\r';
                            break;
                        }
                        case 3: {
                            c2 = '\b';
                            break;
                        }
                        default: {
                            c2 = 'h';
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
