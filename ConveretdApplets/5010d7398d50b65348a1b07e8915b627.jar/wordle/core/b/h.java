// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.b;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.Iterator;
import java.awt.Font;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.Comparator;
import java.util.Map;

public final class h
{
    private static final Map a;
    private static final Comparator b;
    private Map c;
    private Pattern d;
    
    static {
        a = new HashMap();
        b = new a();
    }
    
    public h() {
        this.c = new TreeMap(h.b);
        this.d = null;
    }
    
    public static void a(final String s, final h h) {
        h.a.put(s, h);
    }
    
    public static String a(final String s, final Font font) {
        final h h;
        if ((h = wordle.core.b.h.a.get(font.getName())) == null) {
            return s;
        }
        final h h2;
        if ((h2 = h).c.size() == 0) {
            return s;
        }
        if (h2.d == null) {
            final h h3 = h2;
            final StringBuilder sb = new StringBuilder();
            for (final String s2 : h3.c.keySet()) {
                if (sb.length() > 0) {
                    sb.append('|');
                }
                sb.append(s2);
            }
            h3.d = Pattern.compile(sb.toString());
        }
        final Matcher matcher = h2.d.matcher(s);
        final StringBuffer sb2 = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb2, h2.c.get(matcher.group()));
        }
        matcher.appendTail(sb2);
        return sb2.toString();
    }
    
    public final void a(final String s, final String s2) {
        this.d = null;
        this.c.put(s, s2);
    }
}
