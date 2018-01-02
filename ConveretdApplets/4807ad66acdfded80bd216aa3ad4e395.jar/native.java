import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class native implements Runnable
{
    protected Object ma;
    protected volatile Thread la;
    protected int qa;
    protected String name;
    protected String ka;
    private static String ja = "\u7f5e\u7f5d\u7f5d\u7f5c";
    private static String na = "\u7f32\u7f7e\u7f5d\u7f5d\u7f5c\u7f18\u7f48\u7f59\u7f4a\u7f59\u7f55\u7f5d\u7f4c\u7f5d\u7f4a\u7f18\u7f55\u7f51\u7f4b\u7f4b\u7f51\u7f56\u7f5f\u7f32";
    private static String oa = "\u7f4a\u7f5d\u7f5e\u7f4a\u7f5d\u7f4b\u7f50\u7f7c\u7f5d\u7f54\u7f59\u7f41";
    
    protected native(final String name, final int n, final Object ma) {
        this.qa = 25000;
        this.ma = ma;
        this.name = name;
        final URL a = extends.a(ma, native.ja, false);
        if (a != null) {
            this.ka = new.b(a.toString());
        }
        else {
            System.out.println(native.na);
        }
        this.qa = extends.a(ma, native.oa, n);
        if (this.qa < 0) {
            this.qa = 1000;
        }
    }
    
    public void start() {
        (this.la = new Thread(this, this.name)).start();
    }
    
    public void stop() {
        final Thread la = this.la;
        this.la = null;
        if (la != null && la.isAlive()) {
            la.interrupt();
        }
    }
    
    protected boolean a(final String s) {
        return true;
    }
    
    protected boolean b(final String s) {
        final String _ = extends._(this.ma, s, null);
        return _ != null && _.trim().length() != 0;
    }
    
    public abstract void run();
    
    private static String g(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFE7F38);
        }
        return new String(array);
    }
    
    static {
        native.ja = g(native.ja);
        native.na = g(native.na);
        native.oa = g(native.oa);
    }
}
