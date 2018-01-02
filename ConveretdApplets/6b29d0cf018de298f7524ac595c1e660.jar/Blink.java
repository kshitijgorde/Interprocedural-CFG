import java.awt.Graphics;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Blink extends Applet implements Runnable
{
    Thread blinker;
    String lbl;
    Font font;
    int speed;
    
    public void init() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0         /* this */
        //     1: new             Ljava/awt/Font;
        //     4: dup            
        //     5: ldc             "TimesRoman"
        //     7: iconst_0       
        //     8: bipush          24
        //    10: invokespecial   java/awt/Font.<init>:(Ljava/lang/String;II)V
        //    13: putfield        Blink.font:Ljava/awt/Font;
        //    16: aload_0         /* this */
        //    17: ldc             "speed"
        //    19: invokevirtual   java/applet/Applet.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //    22: astore_1        /* att */
        //    23: aload_0         /* this */
        //    24: aload_1         /* att */
        //    25: ifnonnull       34
        //    28: sipush          400
        //    31: goto            45
        //    34: sipush          1000
        //    37: aload_1        
        //    38: invokestatic    java/lang/Integer.valueOf:(Ljava/lang/String;)Ljava/lang/Integer;
        //    41: invokevirtual   java/lang/Integer.intValue:()I
        //    44: idiv           
        //    45: putfield        Blink.speed:I
        //    48: aload_0         /* this */
        //    49: ldc             "lbl"
        //    51: invokevirtual   java/applet/Applet.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //    54: astore_1        /* att */
        //    55: aload_0         /* this */
        //    56: aload_1         /* att */
        //    57: ifnonnull       65
        //    60: ldc             "Blink"
        //    62: goto            66
        //    65: aload_1         /* att */
        //    66: putfield        Blink.lbl:Ljava/lang/String;
        //    69: return         
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ------------------
        //  0      70      0     this  LBlink;
        //  23     11      1     att   Ljava/lang/String;
        //  55     10      1     att   Ljava/lang/String;
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
    
    public void paint(final Graphics g) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore_2        /* x */
        //     2: aload_0         /* this */
        //     3: getfield        Blink.font:Ljava/awt/Font;
        //     6: invokevirtual   java/awt/Font.getSize:()I
        //     9: istore_3        /* y */
        //    10: invokestatic    java/lang/Math.random:()D
        //    13: ldc2_w          50.0
        //    16: dmul           
        //    17: d2i            
        //    18: istore          red
        //    20: invokestatic    java/lang/Math.random:()D
        //    23: ldc2_w          50.0
        //    26: dmul           
        //    27: d2i            
        //    28: istore          green
        //    30: invokestatic    java/lang/Math.random:()D
        //    33: ldc2_w          256.0
        //    36: dmul           
        //    37: d2i            
        //    38: istore          blue
        //    40: aload_0         /* this */
        //    41: invokevirtual   java/awt/Component.size:()Ljava/awt/Dimension;
        //    44: astore          d
        //    46: aload_1         /* g */
        //    47: getstatic       java/awt/Color.black:Ljava/awt/Color;
        //    50: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //    53: aload_1         /* g */
        //    54: aload_0         /* this */
        //    55: getfield        Blink.font:Ljava/awt/Font;
        //    58: invokevirtual   java/awt/Graphics.setFont:(Ljava/awt/Font;)V
        //    61: aload_1         /* g */
        //    62: invokevirtual   java/awt/Graphics.getFontMetrics:()Ljava/awt/FontMetrics;
        //    65: astore          fm
        //    67: aload           fm
        //    69: ldc             " "
        //    71: invokevirtual   java/awt/FontMetrics.stringWidth:(Ljava/lang/String;)I
        //    74: istore          space
        //    76: new             Ljava/util/StringTokenizer;
        //    79: dup            
        //    80: aload_0         /* this */
        //    81: getfield        Blink.lbl:Ljava/lang/String;
        //    84: invokespecial   java/util/StringTokenizer.<init>:(Ljava/lang/String;)V
        //    87: astore          t
        //    89: goto            202
        //    92: aload           t
        //    94: invokevirtual   java/util/StringTokenizer.nextToken:()Ljava/lang/String;
        //    97: astore          word
        //    99: aload           9
        //   101: aload           word
        //   103: invokevirtual   java/awt/FontMetrics.stringWidth:(Ljava/lang/String;)I
        //   106: iload           4
        //   108: iadd           
        //   109: istore          w
        //   111: iload_2        
        //   112: iload           w
        //   114: iadd           
        //   115: aload           8
        //   117: getfield        java/awt/Dimension.width:I
        //   120: if_icmple       135
        //   123: iconst_0       
        //   124: istore_2        /* x */
        //   125: iload_3        
        //   126: aload_0         /* this */
        //   127: getfield        Blink.font:Ljava/awt/Font;
        //   130: invokevirtual   java/awt/Font.getSize:()I
        //   133: iadd           
        //   134: istore_3       
        //   135: invokestatic    java/lang/Math.random:()D
        //   138: ldc2_w          0.5
        //   141: dcmpg          
        //   142: ifge            182
        //   145: aload_1         /* g */
        //   146: new             Ljava/awt/Color;
        //   149: dup            
        //   150: iload           5
        //   152: iload_3        
        //   153: bipush          30
        //   155: imul           
        //   156: iadd           
        //   157: sipush          256
        //   160: irem           
        //   161: iload           6
        //   163: iload_2        
        //   164: iconst_3       
        //   165: idiv           
        //   166: iadd           
        //   167: sipush          256
        //   170: irem           
        //   171: iload           7
        //   173: invokespecial   java/awt/Color.<init>:(III)V
        //   176: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //   179: goto            189
        //   182: aload_1         /* g */
        //   183: getstatic       java/awt/Color.lightGray:Ljava/awt/Color;
        //   186: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //   189: aload_1         /* g */
        //   190: aload           11
        //   192: iload_2        
        //   193: iload_3        
        //   194: invokevirtual   java/awt/Graphics.drawString:(Ljava/lang/String;II)V
        //   197: iload_2        
        //   198: iload           12
        //   200: iadd           
        //   201: istore_2       
        //   202: aload           10
        //   204: invokevirtual   java/util/StringTokenizer.hasMoreTokens:()Z
        //   207: ifne            92
        //   210: return         
        //    LocalVariableTable:
        //  Start  Length  Slot  Name   Signature
        //  -----  ------  ----  -----  ---------------------------
        //  0      211     0     this   LBlink;
        //  0      211     1     g      Ljava/awt/Graphics;
        //  2      90      2     x      I
        //  10     82      3     y      I
        //  76     16      4     space  I
        //  20     72      5     red    I
        //  30     62      6     green  I
        //  40     52      7     blue   I
        //  46     46      8     d      Ljava/awt/Dimension;
        //  67     25      9     fm     Ljava/awt/FontMetrics;
        //  89     3       10    t      Ljava/util/StringTokenizer;
        //  125    10      2     x      I
        //  99     36      11    word   Ljava/lang/String;
        //  111    24      12    w      I
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
    
    public void start() {
        (this.blinker = new Thread(this)).start();
    }
    
    public void stop() {
        this.blinker.stop();
    }
    
    public void run() {
        while (true) {
            try {
                Thread.currentThread();
                Thread.sleep(this.speed);
            }
            catch (InterruptedException ex) {}
            this.repaint();
        }
    }
}
