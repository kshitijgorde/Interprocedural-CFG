// 
// Decompiled by Procyon v0.5.30
// 

public abstract class ad implements i
{
    public aa a;
    public String b;
    public String c;
    public static /* synthetic */ Class d;
    
    public ad() {
        this.a = new z(2);
    }
    
    public abstract void append(final f p0, final h p1, final String p2, final Throwable p3);
    
    public abstract String toString();
    
    public boolean a(final aa a) {
        if (a != null) {
            this.a = a;
            return true;
        }
        if (f.e.g()) {
            f.e.d("cannot set null Formatter to " + this);
        }
        return false;
    }
    
    public aa a() {
        return this.a;
    }
    
    public boolean a(final String s) {
        if (s == null) {
            if (f.e.g()) {
                f.e.d("AppenderBase formatter setup is null");
            }
            return false;
        }
        final int index = s.indexOf(";");
        if (index > 0) {
            final String substring = s.substring(0, index);
            final String substring2 = s.substring(index + 1, s.length());
            if (f.e.i()) {
                f.e.g("AppenderBase: formatter=" + substring + " setup=" + substring2);
            }
            try {
                return this.a((aa)Class.forName(substring).getConstructor((ad.d == null) ? (ad.d = class$("java.lang.String")) : ad.d).newInstance(substring2));
            }
            catch (Exception ex) {
                if (f.e.g()) {
                    f.e.b("AppenderBase: could not create Formatter ", ex);
                    return false;
                }
                return false;
            }
        }
        if (f.e.g()) {
            f.e.d("AppenderBase no colon found in formatter setup string");
        }
        return false;
    }
    
    public void b(final String b) {
        if (b == null && f.e.g()) {
            f.e.d("AppenderBase setup is null");
        }
        final int index = b.indexOf(";");
        if (index > 0) {
            this.b = b.substring(0, index);
            if (index < b.length()) {
                this.c = b.substring(index + 1, b.length());
            }
            if (f.e.i()) {
                f.e.g("AppenderBase created appender-setup:" + this.b + " formatter-setup:" + this.c);
            }
        }
        else {
            this.b = b;
        }
    }
    
    public static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
