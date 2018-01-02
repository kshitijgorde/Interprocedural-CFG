import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.StringReader;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class13
{
    static IncomingOpcode aClass58_161;
    private Class207 aClass207_162;
    private Class79 aClass79_163;
    static OutgoingOpcode aClass171_164;
    
    static final Class48 method217(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        try {
            if (n != 5) {
                Class13.aClass171_164 = null;
            }
            final long n8 = n3 * 76724863L ^ (986053L * n2 ^ (67481L * n6 ^ 97549L * n7 ^ n5 * 475427L) ^ n4 * 32147369L);
            final Class48 class48 = (Class48)Class123.aClass79_1010.method802(-122, n8);
            if (class48 != null) {
                return class48;
            }
            final Class48 method1803 = Class98_Sub37.aHa4185.method1803(n6, n7, n5, n2, n4, n3);
            Class123.aClass79_1010.method805(n8, method1803, (byte)(-80));
            return method1803;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "at.A(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    final void method218(final boolean b) {
        try {
            synchronized (this.aClass79_163) {
                this.aClass79_163.method806((byte)96);
            }
            if (!b) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "at.H(" + b + ')');
        }
    }
    
    public static void method219(final boolean b) {
        try {
            if (b) {
                Class13.aClass171_164 = null;
                Class13.aClass58_161 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "at.D(" + b + ')');
        }
    }
    
    final Class220 method220(final int n, final int n2) {
        try {
            if (n2 != -11180) {
                this.aClass207_162 = null;
            }
            final Class220 class220;
            synchronized (this.aClass79_163) {
                class220 = (Class220)this.aClass79_163.method802(-127, n);
            }
            if (class220 != null) {
                return class220;
            }
            final byte[] method2745;
            synchronized (this.aClass207_162) {
                method2745 = this.aClass207_162.method2745(n, 35, false);
            }
            final Class220 class221 = new Class220();
            if (method2745 != null) {
                class221.method2816((byte)(-113), new Class98_Sub22(method2745));
            }
            class221.method2819(-9639);
            synchronized (this.aClass79_163) {
                this.aClass79_163.method805(n, class221, (byte)(-80));
            }
            return class221;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "at.E(" + n + ',' + n2 + ')');
        }
    }
    
    final void method221(final byte b) {
        try {
            if (b != 68) {
                method219(false);
            }
            synchronized (this.aClass79_163) {
                this.aClass79_163.method794(93);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "at.G(" + b + ')');
        }
    }
    
    static final String method222(Throwable aThrowable3199, final byte b) throws IOException {
        try {
            String s;
            if (!(aThrowable3199 instanceof RuntimeException_Sub1)) {
                s = "";
            }
            else {
                final RuntimeException_Sub1 runtimeException_Sub1 = (RuntimeException_Sub1)aThrowable3199;
                aThrowable3199 = runtimeException_Sub1.aThrowable3199;
                s = runtimeException_Sub1.aString3202 + " | ";
            }
            final StringWriter stringWriter = new StringWriter();
            final PrintWriter printWriter = new PrintWriter(stringWriter);
            if (b != -24) {
                Class13.aClass58_161 = null;
            }
            aThrowable3199.printStackTrace(printWriter);
            printWriter.close();
            final BufferedReader bufferedReader = new BufferedReader(new StringReader(stringWriter.toString()));
            final String line = bufferedReader.readLine();
            while (true) {
                final String line2 = bufferedReader.readLine();
                if (line2 == null) {
                    break;
                }
                final int index = line2.indexOf(40);
                final int index2 = line2.indexOf(41, index + 1);
                String substring;
                if (index != -1) {
                    substring = line2.substring(0, index);
                }
                else {
                    substring = line2;
                }
                final String trim = substring.trim();
                final String substring2 = trim.substring(1 + trim.lastIndexOf(32));
                String s2 = s + substring2.substring(1 + substring2.lastIndexOf(9));
                if (index != -1 && index2 != -1) {
                    final int index3 = line2.indexOf(".java:", index);
                    if (~index3 <= -1) {
                        s2 += line2.substring(5 + index3, index2);
                    }
                }
                s = s2 + ' ';
            }
            return s + "| " + line;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final void method223(final int n, final int n2) {
        try {
            synchronized (this.aClass79_163) {
                this.aClass79_163.method800((byte)62, n2);
            }
            if (n != 32) {
                Class13.aClass171_164 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "at.C(" + n + ',' + n2 + ')');
        }
    }
    
    static final boolean method224(final int n, final int n2, final int n3) {
        try {
            if (n != 8) {
                Class13.aClass171_164 = null;
            }
            return Class349.method3842(n3, n2, -18021) || Class98_Sub27.method1292(n3, (byte)117, n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "at.F(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    Class13(final Class279 class279, final int n, final Class207 aClass207_162) {
        this.aClass79_163 = new Class79(64);
        try {
            this.aClass207_162 = aClass207_162;
            if (this.aClass207_162 != null) {
                this.aClass207_162.method2761(0, 35);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "at.<init>(" + ((class279 != null) ? "{...}" : "null") + ',' + n + ',' + ((aClass207_162 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class13.aClass58_161 = new IncomingOpcode(15, -2);
        Class13.aClass171_164 = new OutgoingOpcode(58, 8);
    }
}
