// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Dialog;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Component;
import java.awt.Window;

public class bq implements bU
{
    public dH q;
    
    public bq() {
    }
    
    public static final Window q(Window window, final Component component) {
        Frame frame = null;
        Window q = null;
        if (ap.t()) {
            q = q(component, false);
        }
        else {
            frame = (Frame)q(component, true);
        }
        if (window != null) {
            window.removeAll();
            try {
                window.dispose();
            }
            catch (Exception ex) {}
            window = null;
        }
        if (window == null || !window.equals((frame != null) ? new Window(frame) : new Window(q))) {
            if (frame != null) {
                window = new Window(frame);
            }
            else {
                window = new Window(q);
            }
            window.setLayout(new BorderLayout());
        }
        return window;
    }
    
    public static final Window q(Component parent, final boolean b) {
        Window window = null;
        while (!(parent instanceof Frame) && (parent = parent.getParent()) != null) {
            if (parent instanceof Window) {
                window = (Window)parent;
                if (!b && parent instanceof Dialog) {
                    break;
                }
                continue;
            }
        }
        if (b) {
            return window;
        }
        return (Window)parent;
    }
    
    public bq(final dH q) {
        this.q = q;
    }
    
    public void q(final dI p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   a/dI.q:()I
        //     4: ldc             66310
        //     6: if_icmpeq       10
        //     9: return         
        //    10: aload_1        
        //    11: iconst_0       
        //    12: iconst_0       
        //    13: invokevirtual   a/dI.q:(II)I
        //    16: istore_2       
        //    17: aload_1        
        //    18: iconst_0       
        //    19: iconst_1       
        //    20: invokevirtual   a/dI.q:(II)I
        //    23: istore_3       
        //    24: aload_1        
        //    25: iconst_0       
        //    26: iconst_2       
        //    27: invokevirtual   a/dI.q:(II)I
        //    30: istore          4
        //    32: aload_1        
        //    33: iconst_0       
        //    34: iconst_3       
        //    35: invokevirtual   a/dI.q:(II)I
        //    38: istore          5
        //    40: aload_1        
        //    41: iconst_0       
        //    42: iconst_0       
        //    43: invokevirtual   a/dI.q:(II)Ljava/lang/String;
        //    46: astore_1       
        //    47: aload_0        
        //    48: getfield        a/bq.q:La/dH;
        //    51: getfield        a/dH.c:La/M;
        //    54: dup            
        //    55: astore          7
        //    57: monitorenter   
        //    58: aload_0        
        //    59: getfield        a/bq.q:La/dH;
        //    62: getfield        a/dH.c:La/M;
        //    65: iload_2        
        //    66: invokevirtual   a/M.w:(I)Ljava/lang/Object;
        //    69: checkcast       La/bz;
        //    72: astore          6
        //    74: aload           7
        //    76: monitorexit    
        //    77: goto            86
        //    80: astore_3       
        //    81: aload           7
        //    83: monitorexit    
        //    84: aload_3        
        //    85: athrow         
        //    86: iload_3        
        //    87: tableswitch {
        //                0: 306
        //                1: 409
        //                2: 423
        //                3: 197
        //                4: 124
        //                5: 437
        //          default: 459
        //        }
        //   124: new             La/bz;
        //   127: dup            
        //   128: aload_0        
        //   129: getfield        a/bq.q:La/dH;
        //   132: checkcast       La/ap;
        //   135: aload_0        
        //   136: getfield        a/bq.q:La/dH;
        //   139: dup            
        //   140: astore_1       
        //   141: getfield        a/bp.s:I
        //   144: new             La/dl;
        //   147: dup            
        //   148: aload_0        
        //   149: getfield        a/bq.q:La/dH;
        //   152: iconst_0       
        //   153: invokespecial   a/dl.<init>:(La/dH;Z)V
        //   156: iload_2        
        //   157: invokespecial   a/bz.<init>:(La/ap;ILa/bk;I)V
        //   160: astore          7
        //   162: aload_0        
        //   163: getfield        a/bq.q:La/dH;
        //   166: getfield        a/dH.c:La/M;
        //   169: dup            
        //   170: astore_3       
        //   171: monitorenter   
        //   172: aload_0        
        //   173: getfield        a/bq.q:La/dH;
        //   176: getfield        a/dH.c:La/M;
        //   179: aload           7
        //   181: invokevirtual   a/M.q:(La/dh;)I
        //   184: pop            
        //   185: aload_3        
        //   186: monitorexit    
        //   187: goto            459
        //   190: astore          7
        //   192: aload_3        
        //   193: monitorexit    
        //   194: aload           7
        //   196: athrow         
        //   197: aload_0        
        //   198: getfield        a/bq.q:La/dH;
        //   201: getfield        a/dH.a:La/M;
        //   204: iload           4
        //   206: invokevirtual   a/M.w:(I)Ljava/lang/Object;
        //   209: checkcast       La/p;
        //   212: dup            
        //   213: astore_3       
        //   214: ifnonnull       233
        //   217: aload_0        
        //   218: getfield        a/bq.q:La/dH;
        //   221: getfield        a/dH.s:La/M;
        //   224: iload           4
        //   226: invokevirtual   a/M.w:(I)Ljava/lang/Object;
        //   229: checkcast       La/p;
        //   232: astore_3       
        //   233: aload_3        
        //   234: getfield        a/p.e:I
        //   237: istore          7
        //   239: aload_0        
        //   240: getfield        a/bq.q:La/dH;
        //   243: getfield        a/dH.p:La/M;
        //   246: iload           7
        //   248: invokevirtual   a/M.w:(I)Ljava/lang/Object;
        //   251: checkcast       La/aZ;
        //   254: astore          5
        //   256: new             La/A;
        //   259: dup            
        //   260: aload_0        
        //   261: getfield        a/bq.q:La/dH;
        //   264: aload_1        
        //   265: invokevirtual   a/dH.w:(Ljava/lang/String;)Ljava/lang/String;
        //   268: aload_3        
        //   269: iconst_0       
        //   270: iconst_1       
        //   271: aload           5
        //   273: aload_0        
        //   274: getfield        a/bq.q:La/dH;
        //   277: invokespecial   a/A.<init>:(Ljava/lang/String;La/p;ZZLa/aZ;La/cs;)V
        //   280: dup            
        //   281: astore_1       
        //   282: iload_2        
        //   283: putfield        a/A.g:I
        //   286: aload_1        
        //   287: iload           4
        //   289: putfield        a/A.h:I
        //   292: aload_0        
        //   293: getfield        a/bq.q:La/dH;
        //   296: getfield        a/dH.q:La/aH;
        //   299: aload_1        
        //   300: invokeinterface a/aH.q:(La/A;)V
        //   305: return         
        //   306: aload           6
        //   308: ifnonnull       312
        //   311: return         
        //   312: aload_0        
        //   313: getfield        a/bq.q:La/dH;
        //   316: getfield        a/dH.a:La/M;
        //   319: iload           4
        //   321: invokevirtual   a/M.w:(I)Ljava/lang/Object;
        //   324: checkcast       La/p;
        //   327: dup            
        //   328: astore_3       
        //   329: ifnonnull       348
        //   332: aload_0        
        //   333: getfield        a/bq.q:La/dH;
        //   336: getfield        a/dH.s:La/M;
        //   339: iload           4
        //   341: invokevirtual   a/M.w:(I)Ljava/lang/Object;
        //   344: checkcast       La/p;
        //   347: astore_3       
        //   348: aload_3        
        //   349: getfield        a/p.e:I
        //   352: istore          7
        //   354: aload_3        
        //   355: iload           5
        //   357: invokevirtual   a/p.i:(I)V
        //   360: aload_0        
        //   361: getfield        a/bq.q:La/dH;
        //   364: getfield        a/dH.p:La/M;
        //   367: iload           7
        //   369: invokevirtual   a/M.w:(I)Ljava/lang/Object;
        //   372: checkcast       La/aZ;
        //   375: astore          5
        //   377: new             La/A;
        //   380: dup            
        //   381: aload_0        
        //   382: getfield        a/bq.q:La/dH;
        //   385: aload_1        
        //   386: invokevirtual   a/dH.w:(Ljava/lang/String;)Ljava/lang/String;
        //   389: aload_3        
        //   390: iconst_0       
        //   391: iconst_1       
        //   392: aload           5
        //   394: aload_0        
        //   395: getfield        a/bq.q:La/dH;
        //   398: invokespecial   a/A.<init>:(Ljava/lang/String;La/p;ZZLa/aZ;La/cs;)V
        //   401: astore_1       
        //   402: aload           6
        //   404: aload_1        
        //   405: invokevirtual   a/bz.q:(La/A;)V
        //   408: return         
        //   409: aload           6
        //   411: ifnonnull       415
        //   414: return         
        //   415: aload           6
        //   417: iload           4
        //   419: invokevirtual   a/bz.q:(I)V
        //   422: return         
        //   423: aload           6
        //   425: ifnonnull       429
        //   428: return         
        //   429: aload           6
        //   431: iload           4
        //   433: invokevirtual   a/bz.w:(I)V
        //   436: return         
        //   437: aload           6
        //   439: ifnonnull       443
        //   442: return         
        //   443: aload           6
        //   445: dup            
        //   446: astore_1       
        //   447: getfield        a/bz.q:Z
        //   450: ifeq            454
        //   453: return         
        //   454: aload           6
        //   456: invokevirtual   a/bz.dispose:()V
        //   459: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  58     77     80     86     Any
        //  80     84     80     86     Any
        //  172    187    190    197    Any
        //  190    194    190    197    Any
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
}
