// 
// Decompiled by Procyon v0.5.30
// 

package neat.system;

import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

public class d extends PrintWriter
{
    protected static boolean a;
    static long b;
    boolean c;
    public PrintStream d;
    public static boolean e;
    private static String[] z;
    
    public static d a(String string) throws IOException {
        if (string == null) {
            throw new RuntimeException(d.z[1]);
        }
        d d = null;
        if (neat.system.d.a) {
            try {
                if (!string.endsWith(neat.system.d.z[0])) {
                    string += neat.system.d.z[0];
                }
                d = new d(new FileOutputStream(string));
                d.c = true;
            }
            catch (Throwable t) {
                t.printStackTrace(System.err);
            }
        }
        if (d == null) {
            d = new d(new b());
            d.c = false;
        }
        return d;
    }
    
    public void write(final int n) {
        if (neat.system.d.a && this.c) {
            super.write(n);
        }
    }
    
    public void write(final char[] array, final int n, final int n2) {
        if (neat.system.d.a && this.c) {
            super.write(array, n, n2);
        }
    }
    
    public void write(final String s, final int n, final int n2) {
        if (neat.system.d.a && this.c) {
            super.write(s, n, n2);
        }
    }
    
    public void println() {
        if (neat.system.d.a) {
            super.println();
        }
    }
    
    public void println(final boolean b) {
        if (neat.system.d.a) {
            super.println(b);
        }
    }
    
    public void println(final char c) {
        if (neat.system.d.a) {
            super.println(c);
        }
    }
    
    public void println(final int n) {
        if (neat.system.d.a) {
            super.println(n);
        }
    }
    
    public void println(final long n) {
        if (neat.system.d.a) {
            super.println(n);
        }
    }
    
    public void println(final float n) {
        if (neat.system.d.a) {
            super.println(n);
        }
    }
    
    public void println(final double n) {
        if (neat.system.d.a) {
            super.println(n);
        }
    }
    
    public void println(final char[] array) {
        if (neat.system.d.a) {
            super.println(array);
        }
    }
    
    public void println(final String s) {
        if (neat.system.d.a) {
            this.a();
            super.println(s);
        }
    }
    
    public void println(final Object o) {
        if (neat.system.d.a) {
            this.a();
            super.println(o);
        }
    }
    
    public void print(final long n) {
        if (neat.system.d.a) {
            super.print(n);
        }
    }
    
    public void print(final int n) {
        if (neat.system.d.a) {
            super.print(n);
        }
    }
    
    public void print(final Object o) {
        if (neat.system.d.a) {
            super.print(o);
        }
    }
    
    public void print(final String s) {
        if (neat.system.d.a) {
            super.print(s);
        }
    }
    
    private void a() {
        if (neat.system.d.a) {
            this.print(System.currentTimeMillis() - neat.system.d.b + neat.system.d.z[2]);
        }
    }
    
    d(final OutputStream outputStream) {
        super(outputStream, true);
        this.d = new PrintStream(outputStream, true);
    }
    
    static {
        final String[] z = new String[3];
        final int n = 0;
        final char[] charArray = "N@|\u0015".toCharArray();
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
                            c2 = '`';
                            break;
                        }
                        case 1: {
                            c2 = ',';
                            break;
                        }
                        case 2: {
                            c2 = '\u0013';
                            break;
                        }
                        case 3: {
                            c2 = 'r';
                            break;
                        }
                        default: {
                            c2 = '-';
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
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "\tBe\u0013A\tH3\u0014D\fI}\u0013@\u0005\fc\u0013_\u0001Av\u0006H\u0012".toCharArray();
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
                            c4 = '`';
                            break;
                        }
                        case 1: {
                            c4 = ',';
                            break;
                        }
                        case 2: {
                            c4 = '\u0013';
                            break;
                        }
                        case 3: {
                            c4 = 'r';
                            break;
                        }
                        default: {
                            c4 = '-';
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
        z[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "Z\f".toCharArray();
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
                            c6 = '`';
                            break;
                        }
                        case 1: {
                            c6 = ',';
                            break;
                        }
                        case 2: {
                            c6 = '\u0013';
                            break;
                        }
                        case 3: {
                            c6 = 'r';
                            break;
                        }
                        default: {
                            c6 = '-';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 <= n12) {
                z[n9] = new String(charArray3).intern();
                d.z = z;
                d.a = false;
                d.b = System.currentTimeMillis();
                return;
            }
            continue;
        }
    }
}
