// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core;

import java.util.regex.Matcher;
import wordle.core.e.c;
import java.util.regex.Pattern;
import java.util.Comparator;

public final class C
{
    public final double a;
    public final String b;
    public final Object c;
    public static final Comparator d;
    private static final Pattern e;
    private static final Pattern f;
    
    static {
        d = new L();
        e = Pattern.compile("^\\d+(\\.\\d+)?$");
        f = Pattern.compile("^(.+):([^:]+)$");
    }
    
    public C(final double n, final String s) {
        this(n, s, null);
    }
    
    public C(final double a, final String s, final Object c) {
        this.b = s.replace(' ', ' ').replace('~', ' ');
        this.a = a;
        this.c = c;
    }
    
    public final C a(final double n) {
        return new C(this.a * n, this.b, this.c);
    }
    
    public final String toString() {
        return "<DrawableData " + this.b + ":" + this.a + ((this.c == null) ? "" : (":" + this.c)) + "]>";
    }
    
    public final String a() {
        return String.valueOf(wordle.core.e.c.a(this.a)) + ":" + this.b;
    }
    
    public static C a(final String s) {
        final String[] split = s.split(":", 2);
        if (C.e.matcher(split[0]).matches()) {
            return new C(c.a(split[0]), split[1]);
        }
        final Matcher matcher;
        if ((matcher = C.f.matcher(s)).find()) {
            return new C(c.a(matcher.group(2)), matcher.group(1));
        }
        return new C(c.a(split[1]), split[0]);
    }
}
