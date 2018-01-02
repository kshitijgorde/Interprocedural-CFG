import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class extends implements Runnable
{
    protected Object rb;
    protected volatile Thread sb;
    protected int tb;
    protected String name;
    protected String _;
    private static String a = "\u066f\u066c\u066c\u066d";
    private static String b = "\u0603\u064f\u066c\u066c\u066d\u0629\u0679\u0668\u067b\u0668\u0664\u066c\u067d\u066c\u067b\u0629\u0664\u0660\u067a\u067a\u0660\u0667\u066e\u0603";
    private static String c = "\u067b\u066c\u066f\u067b\u066c\u067a\u0661\u064d\u066c\u0665\u0668\u0670";
    private static String d = "\u066c\u066b\u0668\u067b\u067b\u066c\u0665\u067b\u0668\u066a\u0660\u0667\u066e\u0627\u066a\u0666\u0664";
    private static String e = "\u067a\u067d\u0666\u066a\u0662\u0668\u0679\u0679\u0665\u066c\u067d\u067a\u0627\u0667\u066c\u067d";
    private static String f = "\u067a\u067d\u0666\u066a\u0662\u0668\u0679\u0679\u0665\u066c\u067d\u067a\u0627\u066a\u0666\u0664";
    private static String g = "\u0663\u0668\u067f\u0668\u0667\u066c\u067e\u067a\u067d\u0660\u066a\u0662\u066c\u067b\u0627\u066a\u0666\u0664";
    private static String h = "\u0663\u0668\u067f\u0668\u0624\u067d\u0660\u066a\u0662\u066c\u067b\u0627\u0667\u066c\u067d";
    private static String i = "\u0665\u0666\u066a\u0668\u0665\u0661\u0666\u067a\u067d";
    
    protected extends(final String name, final int n, final Object rb) {
        this.tb = 25000;
        this.rb = rb;
        this.name = name;
        final URL b = catch.b(rb, extends.a, false);
        if (b != null) {
            this._ = for.a(b.toString());
        }
        else {
            System.out.println(extends.b);
        }
        this.tb = catch.a(rb, extends.c, n);
        if (this.tb < 0) {
            this.tb = 1000;
        }
    }
    
    public void start() {
        (this.sb = new Thread(this, this.name)).start();
    }
    
    public void stop() {
        final Thread sb = this.sb;
        this.sb = null;
        if (sb != null && sb.isAlive()) {
            sb.interrupt();
        }
    }
    
    protected boolean _(final String s) {
        if (System.currentTimeMillis() < -1L) {
            return true;
        }
        try {
            final URL _ = catch._(this.rb, s, false);
            final String[] array = { extends.d, extends.e, extends.f, extends.g, extends.h, extends.i };
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
    
    protected boolean a(final String s) {
        final String _ = catch._(this.rb, s, null);
        return _ != null && _.trim().length() != 0;
    }
    
    public abstract void run();
    
    private static String b(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u0609');
        }
        return new String(array);
    }
    
    static {
        extends.a = b(extends.a);
        extends.b = b(extends.b);
        extends.c = b(extends.c);
        extends.d = b(extends.d);
        extends.e = b(extends.e);
        extends.f = b(extends.f);
        extends.g = b(extends.g);
        extends.h = b(extends.h);
        extends.i = b(extends.i);
    }
}
