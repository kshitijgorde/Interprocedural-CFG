// 
// Decompiled by Procyon v0.5.30
// 

package ia.classprotect;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.StringReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.Reader;
import java.util.Stack;
import java.util.TimeZone;

public class a
{
    final int a = 233280;
    final int b = 9301;
    final int d = 49297;
    int e;
    public static final TimeZone f;
    private static final String[] z;
    
    public a(final int e) {
        this.e = 1;
        this.e = e;
    }
    
    public float a() {
        this.e = (this.e * 9301 + 49297) % 233280;
        return Math.abs(this.e / 233280.0f);
    }
    
    public int a(final int n) {
        return Math.round(n * this.a());
    }
    
    private static int a(final Stack stack) {
        if (!stack.empty()) {
            return stack.pop();
        }
        return 15;
    }
    
    public static float a(final Reader p0) throws Exception {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       ia/classprotect/ClassProtect.q:I
        //     3: istore          19
        //     5: ldc             ""
        //     7: astore_3       
        //     8: new             Ljava/util/Stack;
        //    11: dup            
        //    12: invokespecial   java/util/Stack.<init>:()V
        //    15: astore          4
        //    17: iconst_0       
        //    18: istore          5
        //    20: bipush          15
        //    22: istore          6
        //    24: iconst_0       
        //    25: istore          7
        //    27: bipush          34
        //    29: istore          8
        //    31: iconst_0       
        //    32: istore          5
        //    34: new             Ljava/lang/StringBuffer;
        //    37: dup            
        //    38: invokespecial   java/lang/StringBuffer.<init>:()V
        //    41: astore          9
        //    43: new             Ljava/lang/StringBuffer;
        //    46: dup            
        //    47: invokespecial   java/lang/StringBuffer.<init>:()V
        //    50: astore          10
        //    52: aconst_null    
        //    53: astore          11
        //    55: aconst_null    
        //    56: astore          12
        //    58: aconst_null    
        //    59: astore          13
        //    61: aconst_null    
        //    62: astore          14
        //    64: new             Ljava/util/Stack;
        //    67: dup            
        //    68: invokespecial   java/util/Stack.<init>:()V
        //    71: astore          4
        //    73: iconst_1       
        //    74: istore          15
        //    76: iconst_0       
        //    77: istore          16
        //    79: iconst_0       
        //    80: istore          17
        //    82: aload_0        
        //    83: invokevirtual   java/io/Reader.read:()I
        //    86: dup            
        //    87: istore          7
        //    89: iconst_m1      
        //    90: if_icmpeq       2462
        //    93: iload           7
        //    95: iload           19
        //    97: ifne            152
        //   100: bipush          10
        //   102: if_icmpne       150
        //   105: goto            109
        //   108: athrow         
        //   109: iload           17
        //   111: iload           19
        //   113: ifne            152
        //   116: goto            120
        //   119: athrow         
        //   120: ifeq            150
        //   123: goto            127
        //   126: athrow         
        //   127: iconst_0       
        //   128: istore          17
        //   130: iload           19
        //   132: ifeq            82
        //   135: getstatic       ia/eb.c:Z
        //   138: ifeq            146
        //   141: iconst_0       
        //   142: goto            147
        //   145: athrow         
        //   146: iconst_1       
        //   147: putstatic       ia/eb.c:Z
        //   150: iload           17
        //   152: iload           19
        //   154: ifne            178
        //   157: ifeq            172
        //   160: goto            164
        //   163: athrow         
        //   164: iconst_0       
        //   165: istore          17
        //   167: iload           19
        //   169: ifeq            248
        //   172: iload           7
        //   174: goto            178
        //   177: athrow         
        //   178: bipush          10
        //   180: iload           19
        //   182: ifne            220
        //   185: if_icmpne       203
        //   188: goto            192
        //   191: athrow         
        //   192: iinc            15, 1
        //   195: iconst_0       
        //   196: istore          16
        //   198: iload           19
        //   200: ifeq            248
        //   203: iload           7
        //   205: iload           19
        //   207: ifne            234
        //   210: goto            214
        //   213: athrow         
        //   214: bipush          13
        //   216: goto            220
        //   219: athrow         
        //   220: if_icmpne       241
        //   223: iconst_1       
        //   224: istore          17
        //   226: bipush          10
        //   228: istore          7
        //   230: iinc            15, 1
        //   233: iconst_0       
        //   234: istore          16
        //   236: iload           19
        //   238: ifeq            248
        //   241: iinc            16, 1
        //   244: goto            248
        //   247: athrow         
        //   248: iload           6
        //   250: bipush          11
        //   252: iload           19
        //   254: ifne            271
        //   257: if_icmpne       268
        //   260: goto            264
        //   263: athrow         
        //   264: ldc             -1.0
        //   266: freturn        
        //   267: athrow         
        //   268: iload           6
        //   270: iconst_1       
        //   271: iload           19
        //   273: ifne            451
        //   276: if_icmpne       448
        //   279: goto            283
        //   282: athrow         
        //   283: iload           7
        //   285: bipush          60
        //   287: iload           19
        //   289: ifne            398
        //   292: goto            296
        //   295: athrow         
        //   296: if_icmpne       385
        //   299: goto            303
        //   302: athrow         
        //   303: aload           4
        //   305: new             Ljava/lang/Integer;
        //   308: dup            
        //   309: iload           6
        //   311: invokespecial   java/lang/Integer.<init>:(I)V
        //   314: invokevirtual   java/util/Stack.push:(Ljava/lang/Object;)Ljava/lang/Object;
        //   317: pop            
        //   318: iconst_5       
        //   319: istore          6
        //   321: aload           9
        //   323: iload           19
        //   325: ifne            340
        //   328: invokevirtual   java/lang/StringBuffer.length:()I
        //   331: ifle            82
        //   334: goto            338
        //   337: athrow         
        //   338: aload           9
        //   340: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   343: astore          18
        //   345: iload           19
        //   347: ifne            380
        //   350: aload_3        
        //   351: getstatic       ia/classprotect/a.z:[Ljava/lang/String;
        //   354: bipush          6
        //   356: aaload         
        //   357: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   360: ifeq            374
        //   363: goto            367
        //   366: athrow         
        //   367: aload           18
        //   369: invokestatic    java/lang/Float.parseFloat:(Ljava/lang/String;)F
        //   372: freturn        
        //   373: athrow         
        //   374: aload           9
        //   376: iconst_0       
        //   377: invokevirtual   java/lang/StringBuffer.setLength:(I)V
        //   380: iload           19
        //   382: ifeq            82
        //   385: iload           7
        //   387: iload           19
        //   389: ifne            421
        //   392: bipush          38
        //   394: goto            398
        //   397: athrow         
        //   398: if_icmpne       434
        //   401: aload           4
        //   403: new             Ljava/lang/Integer;
        //   406: dup            
        //   407: iload           6
        //   409: invokespecial   java/lang/Integer.<init>:(I)V
        //   412: invokevirtual   java/util/Stack.push:(Ljava/lang/Object;)Ljava/lang/Object;
        //   415: pop            
        //   416: iconst_2       
        //   417: goto            421
        //   420: athrow         
        //   421: istore          6
        //   423: aload           10
        //   425: iconst_0       
        //   426: invokevirtual   java/lang/StringBuffer.setLength:(I)V
        //   429: iload           19
        //   431: ifeq            82
        //   434: aload           9
        //   436: iload           7
        //   438: i2c            
        //   439: invokevirtual   java/lang/StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //   442: pop            
        //   443: iload           19
        //   445: ifeq            82
        //   448: iload           6
        //   450: iconst_4       
        //   451: iload           19
        //   453: ifne            550
        //   456: if_icmpne       546
        //   459: goto            463
        //   462: athrow         
        //   463: iload           7
        //   465: iload           19
        //   467: ifne            517
        //   470: goto            474
        //   473: athrow         
        //   474: bipush          62
        //   476: if_icmpne       532
        //   479: goto            483
        //   482: athrow         
        //   483: aload           4
        //   485: invokestatic    ia/classprotect/a.a:(Ljava/util/Stack;)I
        //   488: istore          6
        //   490: aload           9
        //   492: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   495: astore          11
        //   497: aload           9
        //   499: iconst_0       
        //   500: invokevirtual   java/lang/StringBuffer.setLength:(I)V
        //   503: iinc            5, -1
        //   506: iload           19
        //   508: ifne            527
        //   511: iload           5
        //   513: goto            517
        //   516: athrow         
        //   517: ifne            524
        //   520: bipush          11
        //   522: istore          6
        //   524: ldc             ""
        //   526: astore_3       
        //   527: iload           19
        //   529: ifeq            82
        //   532: aload           9
        //   534: iload           7
        //   536: i2c            
        //   537: invokevirtual   java/lang/StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //   540: pop            
        //   541: iload           19
        //   543: ifeq            82
        //   546: iload           6
        //   548: bipush          16
        //   550: iload           19
        //   552: ifne            660
        //   555: if_icmpne       656
        //   558: goto            562
        //   561: athrow         
        //   562: iload           7
        //   564: iload           19
        //   566: ifne            609
        //   569: goto            573
        //   572: athrow         
        //   573: bipush          62
        //   575: if_icmpne       642
        //   578: goto            582
        //   581: athrow         
        //   582: aload           9
        //   584: iload           19
        //   586: ifne            650
        //   589: goto            593
        //   592: athrow         
        //   593: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   596: getstatic       ia/classprotect/a.z:[Ljava/lang/String;
        //   599: bipush          11
        //   601: aaload         
        //   602: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   605: goto            609
        //   608: athrow         
        //   609: ifeq            642
        //   612: aload           9
        //   614: aload           9
        //   616: invokevirtual   java/lang/StringBuffer.length:()I
        //   619: iconst_2       
        //   620: isub           
        //   621: invokevirtual   java/lang/StringBuffer.setLength:(I)V
        //   624: aload           9
        //   626: iconst_0       
        //   627: invokevirtual   java/lang/StringBuffer.setLength:(I)V
        //   630: aload           4
        //   632: invokestatic    ia/classprotect/a.a:(Ljava/util/Stack;)I
        //   635: istore          6
        //   637: iload           19
        //   639: ifeq            82
        //   642: aload           9
        //   644: iload           7
        //   646: i2c            
        //   647: invokevirtual   java/lang/StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //   650: pop            
        //   651: iload           19
        //   653: ifeq            82
        //   656: iload           6
        //   658: bipush          13
        //   660: iload           19
        //   662: ifne            758
        //   665: if_icmpne       754
        //   668: goto            672
        //   671: athrow         
        //   672: iload           7
        //   674: iload           19
        //   676: ifne            719
        //   679: goto            683
        //   682: athrow         
        //   683: bipush          62
        //   685: if_icmpne       740
        //   688: goto            692
        //   691: athrow         
        //   692: aload           9
        //   694: iload           19
        //   696: ifne            748
        //   699: goto            703
        //   702: athrow         
        //   703: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   706: getstatic       ia/classprotect/a.z:[Ljava/lang/String;
        //   709: bipush          16
        //   711: aaload         
        //   712: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   715: goto            719
        //   718: athrow         
        //   719: ifeq            740
        //   722: aload           9
        //   724: iconst_0       
        //   725: invokevirtual   java/lang/StringBuffer.setLength:(I)V
        //   728: aload           4
        //   730: invokestatic    ia/classprotect/a.a:(Ljava/util/Stack;)I
        //   733: istore          6
        //   735: iload           19
        //   737: ifeq            82
        //   740: aload           9
        //   742: iload           7
        //   744: i2c            
        //   745: invokevirtual   java/lang/StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //   748: pop            
        //   749: iload           19
        //   751: ifeq            82
        //   754: iload           6
        //   756: bipush          15
        //   758: iload           19
        //   760: ifne            820
        //   763: if_icmpne       816
        //   766: goto            770
        //   769: athrow         
        //   770: iload           7
        //   772: iload           19
        //   774: ifne            809
        //   777: goto            781
        //   780: athrow         
        //   781: bipush          60
        //   783: if_icmpne       82
        //   786: goto            790
        //   789: athrow         
        //   790: iconst_1       
        //   791: istore          6
        //   793: aload           4
        //   795: new             Ljava/lang/Integer;
        //   798: dup            
        //   799: iload           6
        //   801: invokespecial   java/lang/Integer.<init>:(I)V
        //   804: invokevirtual   java/util/Stack.push:(Ljava/lang/Object;)Ljava/lang/Object;
        //   807: pop            
        //   808: iconst_5       
        //   809: istore          6
        //   811: iload           19
        //   813: ifeq            82
        //   816: iload           6
        //   818: bipush          14
        //   820: iload           19
        //   822: ifne            886
        //   825: if_icmpne       883
        //   828: goto            832
        //   831: athrow         
        //   832: iload           7
        //   834: bipush          62
        //   836: iload           19
        //   838: ifne            871
        //   841: goto            845
        //   844: athrow         
        //   845: if_icmpne       82
        //   848: goto            852
        //   851: athrow         
        //   852: aload           4
        //   854: invokestatic    ia/classprotect/a.a:(Ljava/util/Stack;)I
        //   857: istore          6
        //   859: iload           6
        //   861: iload           19
        //   863: ifne            876
        //   866: iconst_1       
        //   867: goto            871
        //   870: athrow         
        //   871: if_icmpne       82
        //   874: bipush          15
        //   876: istore          6
        //   878: iload           19
        //   880: ifeq            82
        //   883: iload           6
        //   885: iconst_5       
        //   886: iload           19
        //   888: ifne            1014
        //   891: if_icmpne       1011
        //   894: goto            898
        //   897: athrow         
        //   898: aload           4
        //   900: invokestatic    ia/classprotect/a.a:(Ljava/util/Stack;)I
        //   903: istore          6
        //   905: iload           7
        //   907: bipush          47
        //   909: iload           19
        //   911: ifne            957
        //   914: if_icmpne       944
        //   917: goto            921
        //   920: athrow         
        //   921: aload           4
        //   923: new             Ljava/lang/Integer;
        //   926: dup            
        //   927: iload           6
        //   929: invokespecial   java/lang/Integer.<init>:(I)V
        //   932: invokevirtual   java/util/Stack.push:(Ljava/lang/Object;)Ljava/lang/Object;
        //   935: pop            
        //   936: iconst_4       
        //   937: istore          6
        //   939: iload           19
        //   941: ifeq            82
        //   944: iload           7
        //   946: iload           19
        //   948: ifne            985
        //   951: bipush          63
        //   953: goto            957
        //   956: athrow         
        //   957: if_icmpne       969
        //   960: bipush          14
        //   962: istore          6
        //   964: iload           19
        //   966: ifeq            82
        //   969: aload           4
        //   971: new             Ljava/lang/Integer;
        //   974: dup            
        //   975: iload           6
        //   977: invokespecial   java/lang/Integer.<init>:(I)V
        //   980: invokevirtual   java/util/Stack.push:(Ljava/lang/Object;)Ljava/lang/Object;
        //   983: pop            
        //   984: iconst_3       
        //   985: istore          6
        //   987: aconst_null    
        //   988: astore          11
        //   990: new             Ljava/util/Hashtable;
        //   993: dup            
        //   994: invokespecial   java/util/Hashtable.<init>:()V
        //   997: astore          14
        //   999: aload           9
        //  1001: iload           7
        //  1003: i2c            
        //  1004: invokevirtual   java/lang/StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //  1007: pop            
        //  1008: goto            82
        //  1011: iload           6
        //  1013: iconst_2       
        //  1014: iload           19
        //  1016: ifne            1396
        //  1019: if_icmpne       1392
        //  1022: goto            1026
        //  1025: athrow         
        //  1026: iload           7
        //  1028: iload           19
        //  1030: ifne            1055
        //  1033: goto            1037
        //  1036: athrow         
        //  1037: bipush          59
        //  1039: if_icmpne       1378
        //  1042: goto            1046
        //  1045: athrow         
        //  1046: aload           4
        //  1048: invokestatic    ia/classprotect/a.a:(Ljava/util/Stack;)I
        //  1051: goto            1055
        //  1054: athrow         
        //  1055: istore          6
        //  1057: aload           10
        //  1059: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //  1062: astore          18
        //  1064: aload           10
        //  1066: iconst_0       
        //  1067: invokevirtual   java/lang/StringBuffer.setLength:(I)V
        //  1070: aload           18
        //  1072: getstatic       ia/classprotect/a.z:[Ljava/lang/String;
        //  1075: bipush          14
        //  1077: aaload         
        //  1078: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1081: iload           19
        //  1083: ifne            1125
        //  1086: ifeq            1110
        //  1089: goto            1093
        //  1092: athrow         
        //  1093: aload           9
        //  1095: bipush          60
        //  1097: invokevirtual   java/lang/StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //  1100: pop            
        //  1101: iload           19
        //  1103: ifeq            1373
        //  1106: goto            1110
        //  1109: athrow         
        //  1110: aload           18
        //  1112: getstatic       ia/classprotect/a.z:[Ljava/lang/String;
        //  1115: bipush          19
        //  1117: aaload         
        //  1118: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1121: goto            1125
        //  1124: athrow         
        //  1125: iload           19
        //  1127: ifne            1169
        //  1130: ifeq            1154
        //  1133: goto            1137
        //  1136: athrow         
        //  1137: aload           9
        //  1139: bipush          62
        //  1141: invokevirtual   java/lang/StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //  1144: pop            
        //  1145: iload           19
        //  1147: ifeq            1373
        //  1150: goto            1154
        //  1153: athrow         
        //  1154: aload           18
        //  1156: getstatic       ia/classprotect/a.z:[Ljava/lang/String;
        //  1159: bipush          21
        //  1161: aaload         
        //  1162: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1165: goto            1169
        //  1168: athrow         
        //  1169: iload           19
        //  1171: ifne            1212
        //  1174: ifeq            1198
        //  1177: goto            1181
        //  1180: athrow         
        //  1181: aload           9
        //  1183: bipush          38
        //  1185: invokevirtual   java/lang/StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //  1188: pop            
        //  1189: iload           19
        //  1191: ifeq            1373
        //  1194: goto            1198
        //  1197: athrow         
        //  1198: aload           18
        //  1200: getstatic       ia/classprotect/a.z:[Ljava/lang/String;
        //  1203: iconst_5       
        //  1204: aaload         
        //  1205: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1208: goto            1212
        //  1211: athrow         
        //  1212: iload           19
        //  1214: ifne            1256
        //  1217: ifeq            1241
        //  1220: goto            1224
        //  1223: athrow         
        //  1224: aload           9
        //  1226: bipush          34
        //  1228: invokevirtual   java/lang/StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //  1231: pop            
        //  1232: iload           19
        //  1234: ifeq            1373
        //  1237: goto            1241
        //  1240: athrow         
        //  1241: aload           18
        //  1243: getstatic       ia/classprotect/a.z:[Ljava/lang/String;
        //  1246: bipush          18
        //  1248: aaload         
        //  1249: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1252: goto            1256
        //  1255: athrow         
        //  1256: iload           19
        //  1258: ifne            1305
        //  1261: ifeq            1285
        //  1264: goto            1268
        //  1267: athrow         
        //  1268: aload           9
        //  1270: bipush          39
        //  1272: invokevirtual   java/lang/StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //  1275: pop            
        //  1276: iload           19
        //  1278: ifeq            1373
        //  1281: goto            1285
        //  1284: athrow         
        //  1285: aload           18
        //  1287: iload           19
        //  1289: ifne            1366
        //  1292: goto            1296
        //  1295: athrow         
        //  1296: ldc             "#"
        //  1298: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //  1301: goto            1305
        //  1304: athrow         
        //  1305: ifeq            1333
        //  1308: aload           9
        //  1310: aload           18
        //  1312: iconst_1       
        //  1313: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //  1316: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //  1319: i2c            
        //  1320: invokevirtual   java/lang/StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //  1323: pop            
        //  1324: iload           19
        //  1326: ifeq            1373
        //  1329: goto            1333
        //  1332: athrow         
        //  1333: new             Ljava/lang/StringBuffer;
        //  1336: dup            
        //  1337: invokespecial   java/lang/StringBuffer.<init>:()V
        //  1340: getstatic       ia/classprotect/a.z:[Ljava/lang/String;
        //  1343: bipush          22
        //  1345: aaload         
        //  1346: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //  1349: aload           18
        //  1351: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //  1354: ldc             ";"
        //  1356: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //  1359: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //  1362: goto            1366
        //  1365: athrow         
        //  1366: iload           15
        //  1368: iload           16
        //  1370: invokestatic    ia/classprotect/a.a:(Ljava/lang/String;II)V
        //  1373: iload           19
        //  1375: ifeq            82
        //  1378: aload           10
        //  1380: iload           7
        //  1382: i2c            
        //  1383: invokevirtual   java/lang/StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //  1386: pop            
        //  1387: iload           19
        //  1389: ifeq            82
        //  1392: iload           6
        //  1394: bipush          12
        //  1396: iload           19
        //  1398: ifne            1537
        //  1401: if_icmpne       1534
        //  1404: goto            1408
        //  1407: athrow         
        //  1408: aload           11
        //  1410: iload           19
        //  1412: ifne            1435
        //  1415: goto            1419
        //  1418: athrow         
        //  1419: ifnonnull       1437
        //  1422: goto            1426
        //  1425: athrow         
        //  1426: aload           9
        //  1428: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //  1431: goto            1435
        //  1434: athrow         
        //  1435: astore          11
        //  1437: iload           7
        //  1439: iload           19
        //  1441: ifne            1499
        //  1444: bipush          62
        //  1446: if_icmpeq       1497
        //  1449: goto            1453
        //  1452: athrow         
        //  1453: new             Ljava/lang/StringBuffer;
        //  1456: dup            
        //  1457: invokespecial   java/lang/StringBuffer.<init>:()V
        //  1460: getstatic       ia/classprotect/a.z:[Ljava/lang/String;
        //  1463: bipush          13
        //  1465: aaload         
        //  1466: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //  1469: aload           11
        //  1471: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //  1474: getstatic       ia/classprotect/a.z:[Ljava/lang/String;
        //  1477: bipush          7
        //  1479: aaload         
        //  1480: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //  1483: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //  1486: iload           15
        //  1488: iload           16
        //  1490: invokestatic    ia/classprotect/a.a:(Ljava/lang/String;II)V
        //  1493: goto            1497
        //  1496: athrow         
        //  1497: iload           5
        //  1499: ifne            1506
        //  1502: ldc             -1.0
        //  1504: freturn        
        //  1505: athrow         
        //  1506: aload           9
        //  1508: iconst_0       
        //  1509: invokevirtual   java/lang/StringBuffer.setLength:(I)V
        //  1512: new             Ljava/util/Hashtable;
        //  1515: dup            
        //  1516: invokespecial   java/util/Hashtable.<init>:()V
        //  1519: astore          14
        //  1521: aconst_null    
        //  1522: astore          11
        //  1524: aload           4
        //  1526: invokestatic    ia/classprotect/a.a:(Ljava/util/Stack;)I
        //  1529: istore          6
        //  1531: goto            82
        //  1534: iload           6
        //  1536: iconst_3       
        //  1537: iload           19
        //  1539: ifne            1888
        //  1542: if_icmpne       1884
        //  1545: goto            1549
        //  1548: athrow         
        //  1549: iload           7
        //  1551: bipush          62
        //  1553: iload           19
        //  1555: ifne            1632
        //  1558: goto            1562
        //  1561: athrow         
        //  1562: if_icmpne       1628
        //  1565: goto            1569
        //  1568: athrow         
        //  1569: aload           11
        //  1571: iload           19
        //  1573: ifne            1605
        //  1576: goto            1580
        //  1579: athrow         
        //  1580: ifnonnull       1594
        //  1583: goto            1587
        //  1586: athrow         
        //  1587: aload           9
        //  1589: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //  1592: astore          11
        //  1594: aload           9
        //  1596: iconst_0       
        //  1597: invokevirtual   java/lang/StringBuffer.setLength:(I)V
        //  1600: iinc            5, 1
        //  1603: aload           11
        //  1605: astore_3       
        //  1606: aconst_null    
        //  1607: astore          11
        //  1609: new             Ljava/util/Hashtable;
        //  1612: dup            
        //  1613: invokespecial   java/util/Hashtable.<init>:()V
        //  1616: astore          14
        //  1618: aload           4
        //  1620: invokestatic    ia/classprotect/a.a:(Ljava/util/Stack;)I
        //  1623: istore          6
        //  1625: goto            82
        //  1628: iload           7
        //  1630: bipush          47
        //  1632: iload           19
        //  1634: ifne            1657
        //  1637: if_icmpne       1653
        //  1640: goto            1644
        //  1643: athrow         
        //  1644: bipush          12
        //  1646: istore          6
        //  1648: iload           19
        //  1650: ifeq            82
        //  1653: iload           7
        //  1655: bipush          45
        //  1657: iload           19
        //  1659: ifne            1712
        //  1662: if_icmpne       1708
        //  1665: goto            1669
        //  1668: athrow         
        //  1669: aload           9
        //  1671: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //  1674: getstatic       ia/classprotect/a.z:[Ljava/lang/String;
        //  1677: bipush          10
        //  1679: aaload         
        //  1680: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1683: iload           19
        //  1685: ifne            1710
        //  1688: goto            1692
        //  1691: athrow         
        //  1692: ifeq            1708
        //  1695: goto            1699
        //  1698: athrow         
        //  1699: bipush          13
        //  1701: istore          6
        //  1703: iload           19
        //  1705: ifeq            82
        //  1708: iload           7
        //  1710: bipush          91
        //  1712: iload           19
        //  1714: ifne            1782
        //  1717: if_icmpne       1769
        //  1720: goto            1724
        //  1723: athrow         
        //  1724: aload           9
        //  1726: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //  1729: getstatic       ia/classprotect/a.z:[Ljava/lang/String;
        //  1732: bipush          9
        //  1734: aaload         
        //  1735: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1738: iload           19
        //  1740: ifne            1771
        //  1743: goto            1747
        //  1746: athrow         
        //  1747: ifeq            1769
        //  1750: goto            1754
        //  1753: athrow         
        //  1754: bipush          16
        //  1756: istore          6
        //  1758: aload           9
        //  1760: iconst_0       
        //  1761: invokevirtual   java/lang/StringBuffer.setLength:(I)V
        //  1764: iload           19
        //  1766: ifeq            82
        //  1769: iload           7
        //  1771: iload           19
        //  1773: ifne            1836
        //  1776: bipush          69
        //  1778: goto            1782
        //  1781: athrow         
        //  1782: if_icmpne       1830
        //  1785: aload           9
        //  1787: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //  1790: getstatic       ia/classprotect/a.z:[Ljava/lang/String;
        //  1793: bipush          8
        //  1795: aaload         
        //  1796: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1799: iload           19
        //  1801: ifne            1836
        //  1804: goto            1808
        //  1807: athrow         
        //  1808: ifeq            1830
        //  1811: goto            1815
        //  1814: athrow         
        //  1815: aload           9
        //  1817: iconst_0       
        //  1818: invokevirtual   java/lang/StringBuffer.setLength:(I)V
        //  1821: bipush          14
        //  1823: istore          6
        //  1825: iload           19
        //  1827: ifeq            82
        //  1830: iload           7
        //  1832: i2c            
        //  1833: invokestatic    java/lang/Character.isWhitespace:(C)Z
        //  1836: iload           19
        //  1838: ifne            1863
        //  1841: ifeq            1870
        //  1844: goto            1848
        //  1847: athrow         
        //  1848: aload           9
        //  1850: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //  1853: astore          11
        //  1855: aload           9
        //  1857: iconst_0       
        //  1858: invokevirtual   java/lang/StringBuffer.setLength:(I)V
        //  1861: bipush          8
        //  1863: istore          6
        //  1865: iload           19
        //  1867: ifeq            82
        //  1870: aload           9
        //  1872: iload           7
        //  1874: i2c            
        //  1875: invokevirtual   java/lang/StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //  1878: pop            
        //  1879: iload           19
        //  1881: ifeq            82
        //  1884: iload           6
        //  1886: bipush          7
        //  1888: iload           19
        //  1890: ifne            2059
        //  1893: if_icmpne       2055
        //  1896: goto            1900
        //  1899: athrow         
        //  1900: iload           7
        //  1902: iload           19
        //  1904: ifne            1963
        //  1907: goto            1911
        //  1910: athrow         
        //  1911: iload           8
        //  1913: if_icmpne       1952
        //  1916: goto            1920
        //  1919: athrow         
        //  1920: aload           9
        //  1922: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //  1925: astore          13
        //  1927: aload           9
        //  1929: iconst_0       
        //  1930: invokevirtual   java/lang/StringBuffer.setLength:(I)V
        //  1933: aload           14
        //  1935: aload           12
        //  1937: aload           13
        //  1939: invokevirtual   java/util/Hashtable.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1942: pop            
        //  1943: bipush          8
        //  1945: istore          6
        //  1947: iload           19
        //  1949: ifeq            82
        //  1952: getstatic       ia/classprotect/a.z:[Ljava/lang/String;
        //  1955: bipush          12
        //  1957: aaload         
        //  1958: iload           7
        //  1960: invokevirtual   java/lang/String.indexOf:(I)I
        //  1963: iload           19
        //  1965: ifne            1994
        //  1968: iflt            1992
        //  1971: goto            1975
        //  1974: athrow         
        //  1975: aload           9
        //  1977: bipush          32
        //  1979: invokevirtual   java/lang/StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //  1982: pop            
        //  1983: iload           19
        //  1985: ifeq            82
        //  1988: goto            1992
        //  1991: athrow         
        //  1992: iload           7
        //  1994: iload           19
        //  1996: ifne            2028
        //  1999: bipush          38
        //  2001: if_icmpne       2041
        //  2004: goto            2008
        //  2007: athrow         
        //  2008: aload           4
        //  2010: new             Ljava/lang/Integer;
        //  2013: dup            
        //  2014: iload           6
        //  2016: invokespecial   java/lang/Integer.<init>:(I)V
        //  2019: invokevirtual   java/util/Stack.push:(Ljava/lang/Object;)Ljava/lang/Object;
        //  2022: pop            
        //  2023: iconst_2       
        //  2024: goto            2028
        //  2027: athrow         
        //  2028: istore          6
        //  2030: aload           10
        //  2032: iconst_0       
        //  2033: invokevirtual   java/lang/StringBuffer.setLength:(I)V
        //  2036: iload           19
        //  2038: ifeq            82
        //  2041: aload           9
        //  2043: iload           7
        //  2045: i2c            
        //  2046: invokevirtual   java/lang/StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //  2049: pop            
        //  2050: iload           19
        //  2052: ifeq            82
        //  2055: iload           6
        //  2057: bipush          10
        //  2059: iload           19
        //  2061: ifne            2158
        //  2064: if_icmpne       2154
        //  2067: goto            2071
        //  2070: athrow         
        //  2071: iload           7
        //  2073: iload           19
        //  2075: ifne            2117
        //  2078: goto            2082
        //  2081: athrow         
        //  2082: bipush          34
        //  2084: if_icmpeq       2111
        //  2087: goto            2091
        //  2090: athrow         
        //  2091: iload           7
        //  2093: iload           19
        //  2095: ifne            2130
        //  2098: goto            2102
        //  2101: athrow         
        //  2102: bipush          39
        //  2104: if_icmpne       2124
        //  2107: goto            2111
        //  2110: athrow         
        //  2111: iload           7
        //  2113: istore          8
        //  2115: bipush          7
        //  2117: istore          6
        //  2119: iload           19
        //  2121: ifeq            82
        //  2124: iload           7
        //  2126: i2c            
        //  2127: invokestatic    java/lang/Character.isWhitespace:(C)Z
        //  2130: ifeq            2136
        //  2133: goto            82
        //  2136: getstatic       ia/classprotect/a.z:[Ljava/lang/String;
        //  2139: bipush          20
        //  2141: aaload         
        //  2142: iload           15
        //  2144: iload           16
        //  2146: invokestatic    ia/classprotect/a.a:(Ljava/lang/String;II)V
        //  2149: iload           19
        //  2151: ifeq            82
        //  2154: iload           6
        //  2156: bipush          6
        //  2158: iload           19
        //  2160: ifne            2270
        //  2163: if_icmpne       2266
        //  2166: goto            2170
        //  2169: athrow         
        //  2170: iload           7
        //  2172: i2c            
        //  2173: invokestatic    java/lang/Character.isWhitespace:(C)Z
        //  2176: iload           19
        //  2178: ifne            2216
        //  2181: goto            2185
        //  2184: athrow         
        //  2185: ifeq            2214
        //  2188: goto            2192
        //  2191: athrow         
        //  2192: aload           9
        //  2194: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //  2197: astore          12
        //  2199: aload           9
        //  2201: iconst_0       
        //  2202: invokevirtual   java/lang/StringBuffer.setLength:(I)V
        //  2205: bipush          9
        //  2207: istore          6
        //  2209: iload           19
        //  2211: ifeq            82
        //  2214: iload           7
        //  2216: iload           19
        //  2218: ifne            2245
        //  2221: bipush          61
        //  2223: if_icmpne       2252
        //  2226: goto            2230
        //  2229: athrow         
        //  2230: aload           9
        //  2232: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //  2235: astore          12
        //  2237: aload           9
        //  2239: iconst_0       
        //  2240: invokevirtual   java/lang/StringBuffer.setLength:(I)V
        //  2243: bipush          10
        //  2245: istore          6
        //  2247: iload           19
        //  2249: ifeq            82
        //  2252: aload           9
        //  2254: iload           7
        //  2256: i2c            
        //  2257: invokevirtual   java/lang/StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //  2260: pop            
        //  2261: iload           19
        //  2263: ifeq            82
        //  2266: iload           6
        //  2268: bipush          9
        //  2270: iload           19
        //  2272: ifne            2345
        //  2275: if_icmpne       2341
        //  2278: goto            2282
        //  2281: athrow         
        //  2282: iload           7
        //  2284: iload           19
        //  2286: ifne            2317
        //  2289: goto            2293
        //  2292: athrow         
        //  2293: bipush          61
        //  2295: if_icmpne       2311
        //  2298: goto            2302
        //  2301: athrow         
        //  2302: bipush          10
        //  2304: istore          6
        //  2306: iload           19
        //  2308: ifeq            82
        //  2311: iload           7
        //  2313: i2c            
        //  2314: invokestatic    java/lang/Character.isWhitespace:(C)Z
        //  2317: ifeq            2323
        //  2320: goto            82
        //  2323: getstatic       ia/classprotect/a.z:[Ljava/lang/String;
        //  2326: bipush          15
        //  2328: aaload         
        //  2329: iload           15
        //  2331: iload           16
        //  2333: invokestatic    ia/classprotect/a.a:(Ljava/lang/String;II)V
        //  2336: iload           19
        //  2338: ifeq            82
        //  2341: iload           6
        //  2343: bipush          8
        //  2345: if_icmpne       82
        //  2348: iload           7
        //  2350: bipush          62
        //  2352: iload           19
        //  2354: ifne            2466
        //  2357: iload           19
        //  2359: ifne            2411
        //  2362: goto            2366
        //  2365: athrow         
        //  2366: if_icmpne       2398
        //  2369: goto            2373
        //  2372: athrow         
        //  2373: aload           4
        //  2375: invokestatic    ia/classprotect/a.a:(Ljava/util/Stack;)I
        //  2378: istore          6
        //  2380: iinc            5, 1
        //  2383: aconst_null    
        //  2384: astore          11
        //  2386: new             Ljava/util/Hashtable;
        //  2389: dup            
        //  2390: invokespecial   java/util/Hashtable.<init>:()V
        //  2393: astore          14
        //  2395: goto            82
        //  2398: iload           7
        //  2400: iload           19
        //  2402: ifne            2429
        //  2405: bipush          47
        //  2407: goto            2411
        //  2410: athrow         
        //  2411: if_icmpne       2423
        //  2414: bipush          12
        //  2416: istore          6
        //  2418: iload           19
        //  2420: ifeq            82
        //  2423: iload           7
        //  2425: i2c            
        //  2426: invokestatic    java/lang/Character.isWhitespace:(C)Z
        //  2429: iload           19
        //  2431: ifne            2446
        //  2434: ifeq            2444
        //  2437: goto            2441
        //  2440: athrow         
        //  2441: goto            82
        //  2444: bipush          6
        //  2446: istore          6
        //  2448: aload           9
        //  2450: iload           7
        //  2452: i2c            
        //  2453: invokevirtual   java/lang/StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //  2456: pop            
        //  2457: iload           19
        //  2459: ifeq            82
        //  2462: iload           6
        //  2464: bipush          11
        //  2466: if_icmpne       2478
        //  2469: iload           19
        //  2471: ifeq            2495
        //  2474: goto            2478
        //  2477: athrow         
        //  2478: getstatic       ia/classprotect/a.z:[Ljava/lang/String;
        //  2481: bipush          17
        //  2483: aaload         
        //  2484: iload           15
        //  2486: iload           16
        //  2488: invokestatic    ia/classprotect/a.a:(Ljava/lang/String;II)V
        //  2491: goto            2495
        //  2494: athrow         
        //  2495: ldc             -1.0
        //  2497: freturn        
        //    Exceptions:
        //  throws java.lang.Exception
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  93     105    108    109    Ljava/lang/Exception;
        //  102    116    119    120    Ljava/lang/Exception;
        //  111    123    126    127    Ljava/lang/Exception;
        //  138    145    145    146    Ljava/lang/Exception;
        //  152    160    163    164    Ljava/lang/Exception;
        //  169    174    177    178    Ljava/lang/Exception;
        //  178    188    191    192    Ljava/lang/Exception;
        //  200    210    213    214    Ljava/lang/Exception;
        //  203    216    219    220    Ljava/lang/Exception;
        //  238    244    247    248    Ljava/lang/Exception;
        //  248    260    263    264    Ljava/lang/Exception;
        //  264    267    267    268    Ljava/lang/Exception;
        //  271    279    282    283    Ljava/lang/Exception;
        //  283    292    295    296    Ljava/lang/Exception;
        //  283    299    302    303    Ljava/lang/Exception;
        //  321    334    337    338    Ljava/lang/Exception;
        //  345    363    366    367    Ljava/lang/Exception;
        //  351    373    373    374    Ljava/lang/Exception;
        //  385    394    397    398    Ljava/lang/Exception;
        //  398    417    420    421    Ljava/lang/Exception;
        //  451    459    462    463    Ljava/lang/Exception;
        //  463    470    473    474    Ljava/lang/Exception;
        //  463    479    482    483    Ljava/lang/Exception;
        //  497    513    516    517    Ljava/lang/Exception;
        //  550    558    561    562    Ljava/lang/Exception;
        //  562    569    572    573    Ljava/lang/Exception;
        //  562    578    581    582    Ljava/lang/Exception;
        //  575    589    592    593    Ljava/lang/Exception;
        //  584    605    608    609    Ljava/lang/Exception;
        //  660    668    671    672    Ljava/lang/Exception;
        //  672    679    682    683    Ljava/lang/Exception;
        //  672    688    691    692    Ljava/lang/Exception;
        //  685    699    702    703    Ljava/lang/Exception;
        //  694    715    718    719    Ljava/lang/Exception;
        //  758    766    769    770    Ljava/lang/Exception;
        //  770    777    780    781    Ljava/lang/Exception;
        //  770    786    789    790    Ljava/lang/Exception;
        //  820    828    831    832    Ljava/lang/Exception;
        //  832    841    844    845    Ljava/lang/Exception;
        //  832    848    851    852    Ljava/lang/Exception;
        //  859    867    870    871    Ljava/lang/Exception;
        //  886    894    897    898    Ljava/lang/Exception;
        //  905    917    920    921    Ljava/lang/Exception;
        //  944    953    956    957    Ljava/lang/Exception;
        //  1014   1022   1025   1026   Ljava/lang/Exception;
        //  1026   1033   1036   1037   Ljava/lang/Exception;
        //  1026   1042   1045   1046   Ljava/lang/Exception;
        //  1039   1051   1054   1055   Ljava/lang/Exception;
        //  1064   1089   1092   1093   Ljava/lang/Exception;
        //  1093   1106   1109   1110   Ljava/lang/Exception;
        //  1093   1121   1124   1125   Ljava/lang/Exception;
        //  1125   1133   1136   1137   Ljava/lang/Exception;
        //  1137   1150   1153   1154   Ljava/lang/Exception;
        //  1137   1165   1168   1169   Ljava/lang/Exception;
        //  1169   1177   1180   1181   Ljava/lang/Exception;
        //  1181   1194   1197   1198   Ljava/lang/Exception;
        //  1181   1208   1211   1212   Ljava/lang/Exception;
        //  1212   1220   1223   1224   Ljava/lang/Exception;
        //  1224   1237   1240   1241   Ljava/lang/Exception;
        //  1224   1252   1255   1256   Ljava/lang/Exception;
        //  1256   1264   1267   1268   Ljava/lang/Exception;
        //  1268   1281   1284   1285   Ljava/lang/Exception;
        //  1268   1292   1295   1296   Ljava/lang/Exception;
        //  1285   1301   1304   1305   Ljava/lang/Exception;
        //  1305   1329   1332   1333   Ljava/lang/Exception;
        //  1308   1362   1365   1366   Ljava/lang/Exception;
        //  1396   1404   1407   1408   Ljava/lang/Exception;
        //  1408   1415   1418   1419   Ljava/lang/Exception;
        //  1408   1422   1425   1426   Ljava/lang/Exception;
        //  1426   1431   1434   1435   Ljava/lang/Exception;
        //  1437   1449   1452   1453   Ljava/lang/Exception;
        //  1446   1493   1496   1497   Ljava/lang/Exception;
        //  1499   1505   1505   1506   Ljava/lang/Exception;
        //  1537   1545   1548   1549   Ljava/lang/Exception;
        //  1549   1558   1561   1562   Ljava/lang/Exception;
        //  1549   1565   1568   1569   Ljava/lang/Exception;
        //  1569   1576   1579   1580   Ljava/lang/Exception;
        //  1569   1583   1586   1587   Ljava/lang/Exception;
        //  1632   1640   1643   1644   Ljava/lang/Exception;
        //  1657   1665   1668   1669   Ljava/lang/Exception;
        //  1669   1688   1691   1692   Ljava/lang/Exception;
        //  1671   1695   1698   1699   Ljava/lang/Exception;
        //  1712   1720   1723   1724   Ljava/lang/Exception;
        //  1724   1743   1746   1747   Ljava/lang/Exception;
        //  1726   1750   1753   1754   Ljava/lang/Exception;
        //  1771   1778   1781   1782   Ljava/lang/Exception;
        //  1782   1804   1807   1808   Ljava/lang/Exception;
        //  1787   1811   1814   1815   Ljava/lang/Exception;
        //  1836   1844   1847   1848   Ljava/lang/Exception;
        //  1888   1896   1899   1900   Ljava/lang/Exception;
        //  1900   1907   1910   1911   Ljava/lang/Exception;
        //  1900   1916   1919   1920   Ljava/lang/Exception;
        //  1963   1971   1974   1975   Ljava/lang/Exception;
        //  1975   1988   1991   1992   Ljava/lang/Exception;
        //  1994   2004   2007   2008   Ljava/lang/Exception;
        //  2001   2024   2027   2028   Ljava/lang/Exception;
        //  2059   2067   2070   2071   Ljava/lang/Exception;
        //  2071   2078   2081   2082   Ljava/lang/Exception;
        //  2071   2087   2090   2091   Ljava/lang/Exception;
        //  2084   2098   2101   2102   Ljava/lang/Exception;
        //  2093   2107   2110   2111   Ljava/lang/Exception;
        //  2158   2166   2169   2170   Ljava/lang/Exception;
        //  2170   2181   2184   2185   Ljava/lang/Exception;
        //  2170   2188   2191   2192   Ljava/lang/Exception;
        //  2216   2226   2229   2230   Ljava/lang/Exception;
        //  2270   2278   2281   2282   Ljava/lang/Exception;
        //  2282   2289   2292   2293   Ljava/lang/Exception;
        //  2282   2298   2301   2302   Ljava/lang/Exception;
        //  2348   2362   2365   2366   Ljava/lang/Exception;
        //  2359   2369   2372   2373   Ljava/lang/Exception;
        //  2398   2407   2410   2411   Ljava/lang/Exception;
        //  2429   2437   2440   2441   Ljava/lang/Exception;
        //  2466   2474   2477   2478   Ljava/lang/Exception;
        //  2469   2491   2494   2495   Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0203:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
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
    
    private static void a(final String s, final int n, final int n2) throws Exception {
        throw new Exception(s + a.z[0] + n + a.z[1] + n2);
    }
    
    private static float a(final String s) throws Exception {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(a.z[26] + s).openConnection().getInputStream()));
        final StringBuffer sb = new StringBuffer();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line.replaceAll(a.z[24], "<").replaceAll(a.z[23], ">"));
        }
        bufferedReader.close();
        float a;
        if (sb.indexOf(ia.classprotect.a.z[25]) > -1) {
            a = -1.0f;
        }
        else {
            a = a(new StringReader(sb.toString()));
        }
        return a;
    }
    
    public static long b() {
        long long1 = -1L;
        try {
            final float a = a(ia.classprotect.a.z[3]);
            final float a2 = a(ia.classprotect.a.z[2]);
            Label_0048: {
                try {
                    if (a <= -1.0f || a2 <= -1.0f) {
                        break Label_0048;
                    }
                }
                catch (Exception ex) {
                    throw ex;
                }
                long1 = (long)(a * a2 * 1000.0f);
            }
        }
        catch (Exception ex2) {}
        if (long1 == -1L) {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(a.z[4]);
            simpleDateFormat.setTimeZone(a.f);
            long1 = Long.parseLong(simpleDateFormat.format(new Date()));
        }
        return long1;
    }
    
    static {
        final String[] z2 = new String[27];
        final int n = 0;
        final char[] charArray = "}^\\\u0004\u0006}\\P\u000b\u0011}".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0098: {
                if (n2 > 1) {
                    break Label_0098;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = ']';
                            break;
                        }
                        case 1: {
                            c2 = '0';
                            break;
                        }
                        case 2: {
                            c2 = '9';
                            break;
                        }
                        case 3: {
                            c2 = 'e';
                            break;
                        }
                        default: {
                            c2 = 't';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        z2[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "q\u0010Z\n\u0018(]WE".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0214: {
                if (n6 > 1) {
                    break Label_0214;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = ']';
                            break;
                        }
                        case 1: {
                            c4 = '0';
                            break;
                        }
                        case 2: {
                            c4 = '9';
                            break;
                        }
                        case 3: {
                            c4 = 'e';
                            break;
                        }
                        default: {
                            c4 = 't';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        z2[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "\u0010c\u007f1".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0330: {
                if (n10 > 1) {
                    break Label_0330;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = ']';
                            break;
                        }
                        case 1: {
                            c6 = '0';
                            break;
                        }
                        case 2: {
                            c6 = '9';
                            break;
                        }
                        case 3: {
                            c6 = 'e';
                            break;
                        }
                        default: {
                            c6 = 't';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 > n12) {
                continue;
            }
            break;
        }
        z2[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "\u001a\u007fv\"".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0446: {
                if (n14 > 1) {
                    break Label_0446;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = ']';
                            break;
                        }
                        case 1: {
                            c8 = '0';
                            break;
                        }
                        case 2: {
                            c8 = '9';
                            break;
                        }
                        case 3: {
                            c8 = 'e';
                            break;
                        }
                        default: {
                            c8 = 't';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 > n16) {
                continue;
            }
            break;
        }
        z2[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "9Tt(\r$".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0562: {
                if (n18 > 1) {
                    break Label_0562;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = ']';
                            break;
                        }
                        case 1: {
                            c10 = '0';
                            break;
                        }
                        case 2: {
                            c10 = '9';
                            break;
                        }
                        case 3: {
                            c10 = 'e';
                            break;
                        }
                        default: {
                            c10 = 't';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 > n20) {
                continue;
            }
            break;
        }
        z2[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = ",EV\u0011".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0678: {
                if (n22 > 1) {
                    break Label_0678;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = ']';
                            break;
                        }
                        case 1: {
                            c12 = '0';
                            break;
                        }
                        case 2: {
                            c12 = '9';
                            break;
                        }
                        case 3: {
                            c12 = 'e';
                            break;
                        }
                        default: {
                            c12 = 't';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 > n24) {
                continue;
            }
            break;
        }
        z2[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "\rB\\\u0013\u001d2EJ&\u00182C\\".toCharArray();
        int length7;
        int n27;
        final int n26 = n27 = (length7 = charArray7.length);
        int n28 = 0;
        while (true) {
            Label_0798: {
                if (n26 > 1) {
                    break Label_0798;
                }
                length7 = (n27 = n28);
                do {
                    final char c13 = charArray7[n27];
                    char c14 = '\0';
                    switch (n28 % 5) {
                        case 0: {
                            c14 = ']';
                            break;
                        }
                        case 1: {
                            c14 = '0';
                            break;
                        }
                        case 2: {
                            c14 = '9';
                            break;
                        }
                        case 3: {
                            c14 = 'e';
                            break;
                        }
                        default: {
                            c14 = 't';
                            break;
                        }
                    }
                    charArray7[length7] = (char)(c13 ^ c14);
                    ++n28;
                } while (n26 == 0);
            }
            if (n26 > n28) {
                continue;
            }
            break;
        }
        z2[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "r\u000e".toCharArray();
        int length8;
        int n31;
        final int n30 = n31 = (length8 = charArray8.length);
        int n32 = 0;
        while (true) {
            Label_0918: {
                if (n30 > 1) {
                    break Label_0918;
                }
                length8 = (n31 = n32);
                do {
                    final char c15 = charArray8[n31];
                    char c16 = '\0';
                    switch (n32 % 5) {
                        case 0: {
                            c16 = ']';
                            break;
                        }
                        case 1: {
                            c16 = '0';
                            break;
                        }
                        case 2: {
                            c16 = '9';
                            break;
                        }
                        case 3: {
                            c16 = 'e';
                            break;
                        }
                        default: {
                            c16 = 't';
                            break;
                        }
                    }
                    charArray8[length8] = (char)(c15 ^ c16);
                    ++n32;
                } while (n30 == 0);
            }
            if (n30 > n32) {
                continue;
            }
            break;
        }
        z2[n29] = new String(charArray8).intern();
        final int n33 = 8;
        final char[] charArray9 = "|tv& \u0004`".toCharArray();
        int length9;
        int n35;
        final int n34 = n35 = (length9 = charArray9.length);
        int n36 = 0;
        while (true) {
            Label_1038: {
                if (n34 > 1) {
                    break Label_1038;
                }
                length9 = (n35 = n36);
                do {
                    final char c17 = charArray9[n35];
                    char c18 = '\0';
                    switch (n36 % 5) {
                        case 0: {
                            c18 = ']';
                            break;
                        }
                        case 1: {
                            c18 = '0';
                            break;
                        }
                        case 2: {
                            c18 = '9';
                            break;
                        }
                        case 3: {
                            c18 = 'e';
                            break;
                        }
                        default: {
                            c18 = 't';
                            break;
                        }
                    }
                    charArray9[length9] = (char)(c17 ^ c18);
                    ++n36;
                } while (n34 == 0);
            }
            if (n34 > n36) {
                continue;
            }
            break;
        }
        z2[n33] = new String(charArray9).intern();
        final int n37 = 9;
        final char[] charArray10 = "|kz!5\tq".toCharArray();
        int length10;
        int n39;
        final int n38 = n39 = (length10 = charArray10.length);
        int n40 = 0;
        while (true) {
            Label_1158: {
                if (n38 > 1) {
                    break Label_1158;
                }
                length10 = (n39 = n40);
                do {
                    final char c19 = charArray10[n39];
                    char c20 = '\0';
                    switch (n40 % 5) {
                        case 0: {
                            c20 = ']';
                            break;
                        }
                        case 1: {
                            c20 = '0';
                            break;
                        }
                        case 2: {
                            c20 = '9';
                            break;
                        }
                        case 3: {
                            c20 = 'e';
                            break;
                        }
                        default: {
                            c20 = 't';
                            break;
                        }
                    }
                    charArray10[length10] = (char)(c19 ^ c20);
                    ++n40;
                } while (n38 == 0);
            }
            if (n38 > n40) {
                continue;
            }
            break;
        }
        z2[n37] = new String(charArray10).intern();
        final int n41 = 10;
        final char[] charArray11 = "|\u001d".toCharArray();
        int length11;
        int n43;
        final int n42 = n43 = (length11 = charArray11.length);
        int n44 = 0;
        while (true) {
            Label_1278: {
                if (n42 > 1) {
                    break Label_1278;
                }
                length11 = (n43 = n44);
                do {
                    final char c21 = charArray11[n43];
                    char c22 = '\0';
                    switch (n44 % 5) {
                        case 0: {
                            c22 = ']';
                            break;
                        }
                        case 1: {
                            c22 = '0';
                            break;
                        }
                        case 2: {
                            c22 = '9';
                            break;
                        }
                        case 3: {
                            c22 = 'e';
                            break;
                        }
                        default: {
                            c22 = 't';
                            break;
                        }
                    }
                    charArray11[length11] = (char)(c21 ^ c22);
                    ++n44;
                } while (n42 == 0);
            }
            if (n42 > n44) {
                continue;
            }
            break;
        }
        z2[n41] = new String(charArray11).intern();
        final int n45 = 11;
        final char[] charArray12 = "\u0000m".toCharArray();
        int length12;
        int n47;
        final int n46 = n47 = (length12 = charArray12.length);
        int n48 = 0;
        while (true) {
            Label_1398: {
                if (n46 > 1) {
                    break Label_1398;
                }
                length12 = (n47 = n48);
                do {
                    final char c23 = charArray12[n47];
                    char c24 = '\0';
                    switch (n48 % 5) {
                        case 0: {
                            c24 = ']';
                            break;
                        }
                        case 1: {
                            c24 = '0';
                            break;
                        }
                        case 2: {
                            c24 = '9';
                            break;
                        }
                        case 3: {
                            c24 = 'e';
                            break;
                        }
                        default: {
                            c24 = 't';
                            break;
                        }
                    }
                    charArray12[length12] = (char)(c23 ^ c24);
                    ++n48;
                } while (n46 == 0);
            }
            if (n46 > n48) {
                continue;
            }
            break;
        }
        z2[n45] = new String(charArray12).intern();
        final int n49 = 12;
        final char[] charArray13 = "}=3l".toCharArray();
        int length13;
        int n51;
        final int n50 = n51 = (length13 = charArray13.length);
        int n52 = 0;
        while (true) {
            Label_1518: {
                if (n50 > 1) {
                    break Label_1518;
                }
                length13 = (n51 = n52);
                do {
                    final char c25 = charArray13[n51];
                    char c26 = '\0';
                    switch (n52 % 5) {
                        case 0: {
                            c26 = ']';
                            break;
                        }
                        case 1: {
                            c26 = '0';
                            break;
                        }
                        case 2: {
                            c26 = '9';
                            break;
                        }
                        case 3: {
                            c26 = 'e';
                            break;
                        }
                        default: {
                            c26 = 't';
                            break;
                        }
                    }
                    charArray13[length13] = (char)(c25 ^ c26);
                    ++n52;
                } while (n50 == 0);
            }
            if (n50 > n52) {
                continue;
            }
            break;
        }
        z2[n49] = new String(charArray13).intern();
        final int n53 = 13;
        final char[] charArray14 = "\u0018HI\u0000\u0017)U]EJ}VV\u0017T)Q^_Ta".toCharArray();
        int length14;
        int n55;
        final int n54 = n55 = (length14 = charArray14.length);
        int n56 = 0;
        while (true) {
            Label_1638: {
                if (n54 > 1) {
                    break Label_1638;
                }
                length14 = (n55 = n56);
                do {
                    final char c27 = charArray14[n55];
                    char c28 = '\0';
                    switch (n56 % 5) {
                        case 0: {
                            c28 = ']';
                            break;
                        }
                        case 1: {
                            c28 = '0';
                            break;
                        }
                        case 2: {
                            c28 = '9';
                            break;
                        }
                        case 3: {
                            c28 = 'e';
                            break;
                        }
                        default: {
                            c28 = 't';
                            break;
                        }
                    }
                    charArray14[length14] = (char)(c27 ^ c28);
                    ++n56;
                } while (n54 == 0);
            }
            if (n54 > n56) {
                continue;
            }
            break;
        }
        z2[n53] = new String(charArray14).intern();
        final int n57 = 14;
        final char[] charArray15 = "1D".toCharArray();
        int length15;
        int n59;
        final int n58 = n59 = (length15 = charArray15.length);
        int n60 = 0;
        while (true) {
            Label_1758: {
                if (n58 > 1) {
                    break Label_1758;
                }
                length15 = (n59 = n60);
                do {
                    final char c29 = charArray15[n59];
                    char c30 = '\0';
                    switch (n60 % 5) {
                        case 0: {
                            c30 = ']';
                            break;
                        }
                        case 1: {
                            c30 = '0';
                            break;
                        }
                        case 2: {
                            c30 = '9';
                            break;
                        }
                        case 3: {
                            c30 = 'e';
                            break;
                        }
                        default: {
                            c30 = 't';
                            break;
                        }
                    }
                    charArray15[length15] = (char)(c29 ^ c30);
                    ++n60;
                } while (n58 == 0);
            }
            if (n58 > n60) {
                continue;
            }
            break;
        }
        z2[n57] = new String(charArray15).intern();
        final int n61 = 15;
        final char[] charArray16 = "\u0018BK\n\u0006}YWE\u0015)DK\f\u0016(D\\E\u0004/_Z\u0000\u0007.YW\u0002Z".toCharArray();
        int length16;
        int n63;
        final int n62 = n63 = (length16 = charArray16.length);
        int n64 = 0;
        while (true) {
            Label_1878: {
                if (n62 > 1) {
                    break Label_1878;
                }
                length16 = (n63 = n64);
                do {
                    final char c31 = charArray16[n63];
                    char c32 = '\0';
                    switch (n64 % 5) {
                        case 0: {
                            c32 = ']';
                            break;
                        }
                        case 1: {
                            c32 = '0';
                            break;
                        }
                        case 2: {
                            c32 = '9';
                            break;
                        }
                        case 3: {
                            c32 = 'e';
                            break;
                        }
                        default: {
                            c32 = 't';
                            break;
                        }
                    }
                    charArray16[length16] = (char)(c31 ^ c32);
                    ++n64;
                } while (n62 == 0);
            }
            if (n62 > n64) {
                continue;
            }
            break;
        }
        z2[n61] = new String(charArray16).intern();
        final int n65 = 16;
        final char[] charArray17 = "p\u001d".toCharArray();
        int length17;
        int n67;
        final int n66 = n67 = (length17 = charArray17.length);
        int n68 = 0;
        while (true) {
            Label_1998: {
                if (n66 > 1) {
                    break Label_1998;
                }
                length17 = (n67 = n68);
                do {
                    final char c33 = charArray17[n67];
                    char c34 = '\0';
                    switch (n68 % 5) {
                        case 0: {
                            c34 = ']';
                            break;
                        }
                        case 1: {
                            c34 = '0';
                            break;
                        }
                        case 2: {
                            c34 = '9';
                            break;
                        }
                        case 3: {
                            c34 = 'e';
                            break;
                        }
                        default: {
                            c34 = 't';
                            break;
                        }
                    }
                    charArray17[length17] = (char)(c33 ^ c34);
                    ++n68;
                } while (n66 == 0);
            }
            if (n66 > n68) {
                continue;
            }
            break;
        }
        z2[n65] = new String(charArray17).intern();
        final int n69 = 17;
        final char[] charArray18 = "0YJ\u0016\u001d3W\u0019\u0000\u001a9\u0010M\u0004\u0013".toCharArray();
        int length18;
        int n71;
        final int n70 = n71 = (length18 = charArray18.length);
        int n72 = 0;
        while (true) {
            Label_2118: {
                if (n70 > 1) {
                    break Label_2118;
                }
                length18 = (n71 = n72);
                do {
                    final char c35 = charArray18[n71];
                    char c36 = '\0';
                    switch (n72 % 5) {
                        case 0: {
                            c36 = ']';
                            break;
                        }
                        case 1: {
                            c36 = '0';
                            break;
                        }
                        case 2: {
                            c36 = '9';
                            break;
                        }
                        case 3: {
                            c36 = 'e';
                            break;
                        }
                        default: {
                            c36 = 't';
                            break;
                        }
                    }
                    charArray18[length18] = (char)(c35 ^ c36);
                    ++n72;
                } while (n70 == 0);
            }
            if (n70 > n72) {
                continue;
            }
            break;
        }
        z2[n69] = new String(charArray18).intern();
        final int n73 = 18;
        final char[] charArray19 = "<@V\u0016".toCharArray();
        int length19;
        int n75;
        final int n74 = n75 = (length19 = charArray19.length);
        int n76 = 0;
        while (true) {
            Label_2238: {
                if (n74 > 1) {
                    break Label_2238;
                }
                length19 = (n75 = n76);
                do {
                    final char c37 = charArray19[n75];
                    char c38 = '\0';
                    switch (n76 % 5) {
                        case 0: {
                            c38 = ']';
                            break;
                        }
                        case 1: {
                            c38 = '0';
                            break;
                        }
                        case 2: {
                            c38 = '9';
                            break;
                        }
                        case 3: {
                            c38 = 'e';
                            break;
                        }
                        default: {
                            c38 = 't';
                            break;
                        }
                    }
                    charArray19[length19] = (char)(c37 ^ c38);
                    ++n76;
                } while (n74 == 0);
            }
            if (n74 > n76) {
                continue;
            }
            break;
        }
        z2[n73] = new String(charArray19).intern();
        final int n77 = 19;
        final char[] charArray20 = ":D".toCharArray();
        int length20;
        int n79;
        final int n78 = n79 = (length20 = charArray20.length);
        int n80 = 0;
        while (true) {
            Label_2358: {
                if (n78 > 1) {
                    break Label_2358;
                }
                length20 = (n79 = n80);
                do {
                    final char c39 = charArray20[n79];
                    char c40 = '\0';
                    switch (n80 % 5) {
                        case 0: {
                            c40 = ']';
                            break;
                        }
                        case 1: {
                            c40 = '0';
                            break;
                        }
                        case 2: {
                            c40 = '9';
                            break;
                        }
                        case 3: {
                            c40 = 'e';
                            break;
                        }
                        default: {
                            c40 = 't';
                            break;
                        }
                    }
                    charArray20[length20] = (char)(c39 ^ c40);
                    ++n80;
                } while (n78 == 0);
            }
            if (n78 > n80) {
                continue;
            }
            break;
        }
        z2[n77] = new String(charArray20).intern();
        final int n81 = 20;
        final char[] charArray21 = "\u0018BK\n\u0006}YWE\u0015)DK\f\u0016(D\\E\u0004/_Z\u0000\u0007.YW\u0002".toCharArray();
        int length21;
        int n83;
        final int n82 = n83 = (length21 = charArray21.length);
        int n84 = 0;
        while (true) {
            Label_2478: {
                if (n82 > 1) {
                    break Label_2478;
                }
                length21 = (n83 = n84);
                do {
                    final char c41 = charArray21[n83];
                    char c42 = '\0';
                    switch (n84 % 5) {
                        case 0: {
                            c42 = ']';
                            break;
                        }
                        case 1: {
                            c42 = '0';
                            break;
                        }
                        case 2: {
                            c42 = '9';
                            break;
                        }
                        case 3: {
                            c42 = 'e';
                            break;
                        }
                        default: {
                            c42 = 't';
                            break;
                        }
                    }
                    charArray21[length21] = (char)(c41 ^ c42);
                    ++n84;
                } while (n82 == 0);
            }
            if (n82 > n84) {
                continue;
            }
            break;
        }
        z2[n81] = new String(charArray21).intern();
        final int n85 = 21;
        final char[] charArray22 = "<]I".toCharArray();
        int length22;
        int n87;
        final int n86 = n87 = (length22 = charArray22.length);
        int n88 = 0;
        while (true) {
            Label_2598: {
                if (n86 > 1) {
                    break Label_2598;
                }
                length22 = (n87 = n88);
                do {
                    final char c43 = charArray22[n87];
                    char c44 = '\0';
                    switch (n88 % 5) {
                        case 0: {
                            c44 = ']';
                            break;
                        }
                        case 1: {
                            c44 = '0';
                            break;
                        }
                        case 2: {
                            c44 = '9';
                            break;
                        }
                        case 3: {
                            c44 = 'e';
                            break;
                        }
                        default: {
                            c44 = 't';
                            break;
                        }
                    }
                    charArray22[length22] = (char)(c43 ^ c44);
                    ++n88;
                } while (n86 == 0);
            }
            if (n86 > n88) {
                continue;
            }
            break;
        }
        z2[n85] = new String(charArray22).intern();
        final int n89 = 22;
        final char[] charArray23 = "\b^R\u000b\u001b*^\u0019\u0000\u001a)YM\u001cN}\u0016".toCharArray();
        int length23;
        int n91;
        final int n90 = n91 = (length23 = charArray23.length);
        int n92 = 0;
        while (true) {
            Label_2718: {
                if (n90 > 1) {
                    break Label_2718;
                }
                length23 = (n91 = n92);
                do {
                    final char c45 = charArray23[n91];
                    char c46 = '\0';
                    switch (n92 % 5) {
                        case 0: {
                            c46 = ']';
                            break;
                        }
                        case 1: {
                            c46 = '0';
                            break;
                        }
                        case 2: {
                            c46 = '9';
                            break;
                        }
                        case 3: {
                            c46 = 'e';
                            break;
                        }
                        default: {
                            c46 = 't';
                            break;
                        }
                    }
                    charArray23[length23] = (char)(c45 ^ c46);
                    ++n92;
                } while (n90 == 0);
            }
            if (n90 > n92) {
                continue;
            }
            break;
        }
        z2[n89] = new String(charArray23).intern();
        final int n93 = 23;
        final char[] charArray24 = "{WM^".toCharArray();
        int length24;
        int n95;
        final int n94 = n95 = (length24 = charArray24.length);
        int n96 = 0;
        while (true) {
            Label_2838: {
                if (n94 > 1) {
                    break Label_2838;
                }
                length24 = (n95 = n96);
                do {
                    final char c47 = charArray24[n95];
                    char c48 = '\0';
                    switch (n96 % 5) {
                        case 0: {
                            c48 = ']';
                            break;
                        }
                        case 1: {
                            c48 = '0';
                            break;
                        }
                        case 2: {
                            c48 = '9';
                            break;
                        }
                        case 3: {
                            c48 = 'e';
                            break;
                        }
                        default: {
                            c48 = 't';
                            break;
                        }
                    }
                    charArray24[length24] = (char)(c47 ^ c48);
                    ++n96;
                } while (n94 == 0);
            }
            if (n94 > n96) {
                continue;
            }
            break;
        }
        z2[n93] = new String(charArray24).intern();
        final int n97 = 24;
        final char[] charArray25 = "{\\M^".toCharArray();
        int length25;
        int n99;
        final int n98 = n99 = (length25 = charArray25.length);
        int n100 = 0;
        while (true) {
            Label_2958: {
                if (n98 > 1) {
                    break Label_2958;
                }
                length25 = (n99 = n100);
                do {
                    final char c49 = charArray25[n99];
                    char c50 = '\0';
                    switch (n100 % 5) {
                        case 0: {
                            c50 = ']';
                            break;
                        }
                        case 1: {
                            c50 = '0';
                            break;
                        }
                        case 2: {
                            c50 = '9';
                            break;
                        }
                        case 3: {
                            c50 = 'e';
                            break;
                        }
                        default: {
                            c50 = 't';
                            break;
                        }
                    }
                    charArray25[length25] = (char)(c49 ^ c50);
                    ++n100;
                } while (n98 == 0);
            }
            if (n98 > n100) {
                continue;
            }
            break;
        }
        z2[n97] = new String(charArray25).intern();
        final int n101 = 25;
        final char[] charArray26 = "a|X\u0016\u0000c\u0000\u0017UDa\u001fu\u0004\u0007)\u000e".toCharArray();
        int length26;
        int n103;
        final int n102 = n103 = (length26 = charArray26.length);
        int n104 = 0;
        while (true) {
            Label_3078: {
                if (n102 > 1) {
                    break Label_3078;
                }
                length26 = (n103 = n104);
                do {
                    final char c51 = charArray26[n103];
                    char c52 = '\0';
                    switch (n104 % 5) {
                        case 0: {
                            c52 = ']';
                            break;
                        }
                        case 1: {
                            c52 = '0';
                            break;
                        }
                        case 2: {
                            c52 = '9';
                            break;
                        }
                        case 3: {
                            c52 = 'e';
                            break;
                        }
                        default: {
                            c52 = 't';
                            break;
                        }
                    }
                    charArray26[length26] = (char)(c51 ^ c52);
                    ++n104;
                } while (n102 == 0);
            }
            if (n102 > n104) {
                continue;
            }
            break;
        }
        z2[n101] = new String(charArray26).intern();
        final int n105 = 26;
        final char[] charArray27 = "5DM\u0015Nr\u001fN\u0012\u0003sG\\\u0007\u00078BO\f\u00178H\u0017\u000b\u0011)\u001fJ\u0011\u001b>[H\u0010\u001b)U\u0017\u0004\u00070H\u0016\"\u0011)aL\n\u00008\u000fJ\u001c\u0019?_UX".toCharArray();
        int length27;
        int n107;
        final int n106 = n107 = (length27 = charArray27.length);
        int n108 = 0;
        while (true) {
            Label_3198: {
                if (n106 > 1) {
                    break Label_3198;
                }
                length27 = (n107 = n108);
                do {
                    final char c53 = charArray27[n107];
                    char c54 = '\0';
                    switch (n108 % 5) {
                        case 0: {
                            c54 = ']';
                            break;
                        }
                        case 1: {
                            c54 = '0';
                            break;
                        }
                        case 2: {
                            c54 = '9';
                            break;
                        }
                        case 3: {
                            c54 = 'e';
                            break;
                        }
                        default: {
                            c54 = 't';
                            break;
                        }
                    }
                    charArray27[length27] = (char)(c53 ^ c54);
                    ++n108;
                } while (n106 == 0);
            }
            if (n106 > n108) {
                continue;
            }
            break;
        }
        z2[n105] = new String(charArray27).intern();
        z = z2;
        final char[] charArray28 = "\u001c]\\\u0017\u001d>Q\u0016)\u001b.ox\u000b\u00138\\\\\u0016".toCharArray();
        int length28;
        int n110;
        final int n109 = n110 = (length28 = charArray28.length);
        int n111 = 0;
        while (true) {
            Label_3546: {
                if (n109 > 1) {
                    break Label_3546;
                }
                length28 = (n110 = n111);
                do {
                    final char c55 = charArray28[n110];
                    char c56 = '\0';
                    switch (n111 % 5) {
                        case 0: {
                            c56 = ']';
                            break;
                        }
                        case 1: {
                            c56 = '0';
                            break;
                        }
                        case 2: {
                            c56 = '9';
                            break;
                        }
                        case 3: {
                            c56 = 'e';
                            break;
                        }
                        default: {
                            c56 = 't';
                            break;
                        }
                    }
                    charArray28[length28] = (char)(c55 ^ c56);
                    ++n111;
                } while (n109 == 0);
            }
            if (n109 <= n111) {
                f = TimeZone.getTimeZone(new String(charArray28).intern());
                return;
            }
            continue;
        }
    }
}
