import java.net.URL;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;

// 
// Decompiled by Procyon v0.5.30
// 

public class protected extends native
{
    protected final ga;
    protected String ha;
    protected String ia;
    private static String ja = "\u6c44\u6c7d\u6c6e\u6c79\u6c67\u6c64\u6c6a\u6c6f\u6c25\u6c45\u6c6e\u6c7c\u6c78\u6c5b\u6c79\u6c64\u6c7d\u6c62\u6c6f\u6c6e\u6c79";
    private static String na = "\u6c42\u6c65\u6c7d\u6c6a\u6c67\u6c62\u6c6f\u6c2b\u6c5e\u6c59\u6c47\u6c2b\u6c6d\u6c64\u6c79\u6c2b\u6c7f\u6c63\u6c6e\u6c2b\u6c65\u6c6e\u6c7c\u6c78\u6c2b\u6c6d\u6c6e\u6c6e\u6c6f";
    private static String oa = "\u6c41\u6c4a\u6c5d\u6c4a\u6c26\u6c5f\u6c42\u6c48\u6c40\u6c4e\u6c59\u6c25\u6c45\u6c4e\u6c5f\u6c2b\u6c4f\u6c4e\u6c46\u6c44";
    private static String pa = "\u6c63\u6c7f\u6c7f\u6c7b\u6c31\u6c24\u6c24\u6c7c\u6c7c\u6c7c\u6c25\u6c61\u6c6a\u6c7d\u6c6a\u6c26\u6c7f\u6c62\u6c68\u6c60\u6c6e\u6c79\u6c25\u6c65\u6c6e\u6c7f";
    
    public protected(final final ga, final Object o) {
        super(protected.ja, 100000, o);
        this.ga = ga;
    }
    
    public void run() {
        if (!this.a(super.ka)) {
            super.la = null;
        }
        final Thread currentThread = Thread.currentThread();
        BufferedReader bufferedReader = null;
        while (currentThread == super.la) {
            final long currentTimeMillis = System.currentTimeMillis();
            final URL _ = extends._(super.ma, super.ka, false);
            if (_ != null) {
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(_.openStream()));
                    this.ga.a();
                    for (int i = 0; i < 3; ++i) {
                        final String line = bufferedReader.readLine();
                        final String line2 = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        this.ga.b(line, line2);
                    }
                }
                catch (IOException ex) {
                    System.out.println(protected.na);
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
                this.ga.b(protected.oa, protected.pa);
            }
            final long n = super.qa - (System.currentTimeMillis() - currentTimeMillis);
            if (n > 0L) {
                try {
                    Thread.sleep(n);
                }
                catch (InterruptedException ex4) {}
            }
        }
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u6c0b');
        }
        return new String(array);
    }
    
    static {
        protected.ja = _(protected.ja);
        protected.na = _(protected.na);
        protected.oa = _(protected.oa);
        protected.pa = _(protected.pa);
    }
}
