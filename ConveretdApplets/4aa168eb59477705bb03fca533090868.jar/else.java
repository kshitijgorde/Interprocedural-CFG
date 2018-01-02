import java.net.URL;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;

// 
// Decompiled by Procyon v0.5.30
// 

public class else extends extends
{
    protected class j;
    protected String k;
    protected String l;
    protected String m;
    private static String a = "\u8fbc\u8f85\u8f96\u8f81\u8f9f\u8f9c\u8f92\u8f97\u8fdd\u8fbd\u8f96\u8f84\u8f80\u8fa3\u8f81\u8f9c\u8f85\u8f9a\u8f97\u8f96\u8f81";
    private static String b = "\u8f90\u8f9b\u8f92\u8f81\u8f80\u8f96\u8f87";
    private static String c = "\u8fb6\u8f9d\u8f90\u8f9c\u8f97\u8f9a\u8f9d\u8f94\u8fd3";
    private static String d = "\u8fd3\u8f9a\u8f80\u8fd3\u8f9d\u8f9c\u8f87\u8fd3\u8f80\u8f86\u8f83\u8f83\u8f9c\u8f81\u8f87\u8f96\u8f97";
    private static String e = "\u8fba\u8f9d\u8f85\u8f92\u8f9f\u8f9a\u8f97\u8fd3\u8fa6\u8fa1\u8fbf\u8fd3\u8f95\u8f9c\u8f81\u8fd3\u8f87\u8f9b\u8f96\u8fd3\u8f9d\u8f96\u8f84\u8f80\u8fd3\u8f95\u8f96\u8f96\u8f97";
    
    public else(final class j, final Object o) {
        super(else.a, 100000, o);
        this.j = j;
        this.m = catch._(o, else.b, null);
    }
    
    public void run() {
        if (!this._(super._)) {
            super.sb = null;
        }
        final Thread currentThread = Thread.currentThread();
        BufferedReader bufferedReader = null;
        while (currentThread == super.sb) {
            final long currentTimeMillis = System.currentTimeMillis();
            final URL _ = catch._(super.rb, super._, false);
            if (_ != null) {
                try {
                    if (this.m == null) {
                        bufferedReader = new BufferedReader(new InputStreamReader(_.openStream()));
                    }
                    else {
                        bufferedReader = new BufferedReader(new InputStreamReader(_.openStream(), this.m));
                    }
                    for (int i = 0; i < Integer.MAX_VALUE; ++i) {
                        final String line = bufferedReader.readLine();
                        final String line2 = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        if (i == 0) {
                            this.j.b();
                        }
                        this.j.b(line, line2);
                    }
                }
                catch (UnsupportedEncodingException ex) {
                    System.out.println(else.c + this.m + else.d);
                }
                catch (IOException ex2) {
                    System.out.println(else.e);
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
            final long n = super.tb - (System.currentTimeMillis() - currentTimeMillis);
            if (n > 0L) {
                try {
                    Thread.sleep(n);
                }
                catch (InterruptedException ex5) {}
            }
        }
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF8FF3);
        }
        return new String(array);
    }
    
    static {
        else.a = _(else.a);
        else.b = _(else.b);
        else.c = _(else.c);
        else.d = _(else.d);
        else.e = _(else.e);
    }
}
