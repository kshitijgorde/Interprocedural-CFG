import java.net.URL;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;

// 
// Decompiled by Procyon v0.5.30
// 

public class throw extends static
{
    protected interface R;
    protected String S;
    protected String T;
    protected String U;
    private static String V = "\u1d45\u1d7c\u1d6f\u1d78\u1d66\u1d65\u1d6b\u1d6e\u1d24\u1d44\u1d6f\u1d7d\u1d79\u1d5a\u1d78\u1d65\u1d7c\u1d63\u1d6e\u1d6f\u1d78";
    private static String W = "\u1d69\u1d62\u1d6b\u1d78\u1d79\u1d6f\u1d7e";
    private static String _a = "\u1d4f\u1d64\u1d69\u1d65\u1d6e\u1d63\u1d64\u1d6d\u1d2a";
    private static String aa = "\u1d2a\u1d63\u1d79\u1d2a\u1d64\u1d65\u1d7e\u1d2a\u1d79\u1d7f\u1d7a\u1d7a\u1d65\u1d78\u1d7e\u1d6f\u1d6e";
    private static String ba = "\u1d43\u1d64\u1d7c\u1d6b\u1d66\u1d63\u1d6e\u1d2a\u1d5f\u1d58\u1d46\u1d2a\u1d6c\u1d65\u1d78\u1d2a\u1d7e\u1d62\u1d6f\u1d2a\u1d64\u1d6f\u1d7d\u1d79\u1d2a\u1d6c\u1d6f\u1d6f\u1d6e";
    
    public throw(final interface r, final Object o) {
        super(throw.V, 100000, o);
        this.R = r;
        this.U = instanceof._(o, throw.W, null);
    }
    
    public void run() {
        if (!this.b(super.X)) {
            super.Y = null;
        }
        final Thread currentThread = Thread.currentThread();
        BufferedReader bufferedReader = null;
        while (currentThread == super.Y) {
            final long currentTimeMillis = System.currentTimeMillis();
            final URL _ = instanceof._(super.Z, super.X, false);
            if (_ != null) {
                try {
                    if (this.U == null) {
                        bufferedReader = new BufferedReader(new InputStreamReader(_.openStream()));
                    }
                    else {
                        bufferedReader = new BufferedReader(new InputStreamReader(_.openStream(), this.U));
                    }
                    for (int i = 0; i < Integer.MAX_VALUE; ++i) {
                        final String line = bufferedReader.readLine();
                        final String line2 = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        if (i == 0) {
                            this.R._();
                        }
                        this.R.a(line, line2);
                    }
                }
                catch (UnsupportedEncodingException ex) {
                    System.out.println(throw._a + this.U + throw.aa);
                }
                catch (IOException ex2) {
                    System.out.println(throw.ba);
                }
                finally {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        }
                        catch (IOException ex3) {}
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    }
                    catch (IOException ex4) {}
                }
            }
            final long n = super.ca - (System.currentTimeMillis() - currentTimeMillis);
            if (n > 0L) {
                try {
                    Thread.sleep(n);
                }
                catch (InterruptedException ex5) {}
            }
        }
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0x11D0A);
        }
        return new String(array);
    }
    
    static {
        throw.V = a(throw.V);
        throw.W = a(throw.W);
        throw._a = a(throw._a);
        throw.aa = a(throw.aa);
        throw.ba = a(throw.ba);
    }
}
