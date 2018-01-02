import java.net.URL;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;

// 
// Decompiled by Procyon v0.5.30
// 

public class const extends abstract
{
    protected String h;
    protected n i;
    protected String j;
    protected String k;
    private static String l = "\ufacb\ufaf2\ufae1\ufaf6\ufae8\ufaeb\ufae5\ufae0\ufaaa\ufaca\ufae1\ufaf3\ufaf7\ufad4\ufaf6\ufaeb\ufaf2\ufaed\ufae0\ufae1\ufaf6";
    private static String m = "\ufaf6\ufae1\ufae2\ufaf6\ufae1\ufaf7\ufaec\ufac0\ufae1\ufae8\ufae5\ufafd";
    private static String o = "\ufae2\ufae1\ufae1\ufae0";
    private static String p = "\ufaf1\ufaea\ufaef\ufaea\ufaeb\ufaf3\ufaea\ufaca\ufae1\ufaf3\ufaf7\ufac2\ufae1\ufae1\ufae0";
    private static String s = "\ufacd\ufaea\ufaf2\ufae5\ufae8\ufaed\ufae0\ufaa4\ufad1\ufad6\ufac8\ufaa4\ufae2\ufaeb\ufaf6\ufaa4\ufaf0\ufaec\ufae1\ufaa4\ufaea\ufae1\ufaf3\ufaf7\ufaa4\ufae2\ufae1\ufae1\ufae0";
    
    public const(final n i, final Object o) {
        super(const.l, o);
        this.i = i;
        super.n = m.b(o, const.m, 100000);
        this.h = break.a(m.a(o, const.o, const.p));
    }
    
    public void run() {
        if (!this.b(this.h)) {
            super.q = null;
        }
        final Thread currentThread = Thread.currentThread();
        BufferedReader bufferedReader = null;
        while (currentThread == super.q) {
            final long currentTimeMillis = System.currentTimeMillis();
            final URL b = m.b(super.r, this.h, false);
            if (b != null) {
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(b.openStream()));
                    this.i._();
                    for (int i = 0; i < Integer.MAX_VALUE; ++i) {
                        final String line = bufferedReader.readLine();
                        final String line2 = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        this.i._(line, line2);
                    }
                }
                catch (IOException ex) {
                    System.out.println(const.s);
                }
                finally {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        }
                        catch (IOException ex2) {}
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    }
                    catch (IOException ex3) {}
                }
            }
            final long n = super.n - (System.currentTimeMillis() - currentTimeMillis);
            if (n > 0L) {
                try {
                    Thread.sleep(n);
                }
                catch (InterruptedException ex4) {}
            }
        }
    }
    
    private static String b(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFEFA84);
        }
        return new String(array);
    }
    
    static {
        const.l = b(const.l);
        const.m = b(const.m);
        const.o = b(const.o);
        const.p = b(const.p);
        const.s = b(const.s);
    }
}
