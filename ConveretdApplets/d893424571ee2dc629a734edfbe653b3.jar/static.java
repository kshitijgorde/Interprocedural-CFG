import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class static implements Runnable
{
    protected Object Z;
    protected volatile Thread Y;
    protected int ca;
    protected String name;
    protected String X;
    private static String V = "\u8571\u8572\u8572\u8573";
    private static String W = "\u851d\u8551\u8572\u8572\u8573\u8537\u8567\u8576\u8565\u8576\u857a\u8572\u8563\u8572\u8565\u8537\u857a\u857e\u8564\u8564\u857e\u8579\u8570\u851d";
    private static String _a = "\u8565\u8572\u8571\u8565\u8572\u8564\u857f\u8553\u8572\u857b\u8576\u856e";
    private static String aa = "\u8563\u8565\u857a\u8574\u8578\u857a\u8539\u8574\u8578\u857a";
    private static String ba = "\u8564\u8563\u8578\u8574\u857c\u8576\u8567\u8567\u857b\u8572\u8563\u8564\u8539\u8574\u8578\u857a";
    private static String fa = "\u857d\u8576\u8561\u8576\u853a\u8563\u857e\u8574\u857c\u8572\u8565\u8539\u8579\u8572\u8563";
    private static String ga = "\u857b\u8578\u8574\u8576\u857b\u857f\u8578\u8564\u8563";
    
    protected static(final String name, final int n, final Object z) {
        this.ca = 25000;
        this.Z = z;
        this.name = name;
        final URL a = instanceof.a(z, static.V, false);
        if (a != null) {
            this.X = super._(a.toString());
        }
        else {
            System.out.println(static.W);
        }
        this.ca = instanceof._(z, static._a, n);
        if (this.ca < 0) {
            this.ca = 1000;
        }
    }
    
    public void start() {
        (this.Y = new Thread(this, this.name)).start();
    }
    
    public void stop() {
        final Thread y = this.Y;
        this.Y = null;
        if (y != null && y.isAlive()) {
            y.interrupt();
        }
    }
    
    protected boolean b(final String s) {
        if (System.currentTimeMillis() < -1L) {
            return true;
        }
        try {
            final URL _ = instanceof._(this.Z, s, false);
            final String[] array = { static.aa, static.ba, static.fa, static.ga };
            for (int i = 0; i < array.length; ++i) {
                try {
                    if (_.getHost().toLowerCase().endsWith(array[i].toLowerCase())) {
                        return true;
                    }
                }
                catch (Throwable t) {}
            }
            return false;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    protected boolean _(final String s) {
        final String _ = instanceof._(this.Z, s, null);
        return _ != null && _.trim().length() != 0;
    }
    
    public abstract void run();
    
    private static String l(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u8517');
        }
        return new String(array);
    }
    
    static {
        static.V = l(static.V);
        static.W = l(static.W);
        static._a = l(static._a);
        static.aa = l(static.aa);
        static.ba = l(static.ba);
        static.fa = l(static.fa);
        static.ga = l(static.ga);
    }
}
