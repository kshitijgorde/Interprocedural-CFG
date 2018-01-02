// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Color;
import nanoxml.XMLElement;

public class bg
{
    private XMLElement a;
    private static /* synthetic */ boolean b;
    
    public bg(final XMLElement a) {
        if (!bg.b && a == null) {
            throw new AssertionError();
        }
        this.a = a;
    }
    
    public final boolean a(final String s) {
        return this.a.getAttribute(s) != null;
    }
    
    private String l(final String s) {
        return this.a.getName() + "." + s;
    }
    
    public final int b(final String s) {
        if (!bg.b && s.length() <= 0) {
            throw new AssertionError();
        }
        try {
            return Integer.parseInt(this.c(s));
        }
        catch (NumberFormatException ex) {
            throw new RuntimeException("Invalid attribute value: " + this.l(s));
        }
    }
    
    public final String c(final String s) {
        final String s2;
        if ((s2 = (String)this.a.getAttribute(s)) == null) {
            throw new RuntimeException("Configuration is missing attribute: " + this.l(s));
        }
        return s2;
    }
    
    public final Color d(final String s) {
        if (!bg.b && s.length() <= 0) {
            throw new AssertionError();
        }
        final String string = "Invalid attribute value: " + this.l(s) + "; format should be hex RRGGBB";
        final String c;
        if ((c = this.c(s)).length() != 6) {
            throw new RuntimeException(string);
        }
        try {
            return new Color(E.a(c.substring(0, 2)), E.a(c.substring(2, 4)), E.a(c.substring(4, 6)));
        }
        catch (IllegalArgumentException ex) {
            throw new RuntimeException(string + "; " + ex.getMessage());
        }
    }
    
    private Point m(final String s) {
        if (!bg.b && (s == null || s.length() <= 0)) {
            throw new AssertionError();
        }
        final String c;
        if ((c = this.c(s)) == null) {
            return new Point(0, 0);
        }
        final String[] split;
        if ((split = c.split(",", 2)).length != 2) {
            throw new RuntimeException("Invalid attribute value: " + this.l(s));
        }
        try {
            return new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        }
        catch (NumberFormatException ex) {
            throw new RuntimeException("Invalid attribute value: " + this.l(s));
        }
    }
    
    public final Dimension e(String s) {
        if (!bg.b && s.length() <= 0) {
            throw new AssertionError();
        }
        final bg bg = this;
        s = s;
        this = bg;
        if (!z.bg.b && (s == null || s.length() <= 0)) {
            throw new AssertionError();
        }
        final Point m;
        if ((m = this.m(s)).x < 0 || m.y < 0) {
            throw new RuntimeException("Invalid attribute value: " + this.l(s) + "; (x,y) must both be >=0");
        }
        final Point point = m;
        return new Dimension(point.x, point.y);
    }
    
    public final bg f(final String s) {
        final XMLElement d;
        if ((d = aa.d(this.a, s)) == null) {
            throw new RuntimeException("Missing configuration element: " + this.a.getName() + "/" + s);
        }
        return new bg(d);
    }
    
    public final boolean g(final String s) {
        return aa.d(this.a, s) != null;
    }
    
    public final List a() {
        final ArrayList<bg> list = new ArrayList<bg>();
        final Enumeration<XMLElement> elements = this.a.getChildren().elements();
        while (elements.hasMoreElements()) {
            list.add(new bg(elements.nextElement()));
        }
        return list;
    }
    
    public final String b() {
        return this.a.getName();
    }
    
    public final float h(final String s) {
        if (!bg.b && s.length() <= 0) {
            throw new AssertionError();
        }
        final String c = this.c(s);
        try {
            return Float.parseFloat(c);
        }
        catch (NumberFormatException ex) {
            throw new RuntimeException("Invalid attribute value: " + this.l(s));
        }
    }
    
    public final URL i(final String s) {
        if (!bg.b && s.length() <= 0) {
            throw new AssertionError();
        }
        final String c;
        if ((c = this.c(s)) == null) {
            return null;
        }
        final String replaceFirst = c.replaceFirst("./", G.b().toString());
        try {
            return new URL(replaceFirst);
        }
        catch (MalformedURLException ex) {
            throw new RuntimeException("Invalid attribute value: " + this.l(s));
        }
    }
    
    public final URL j(final String s) {
        System.out.print("name: " + s);
        if (!bg.b && s.length() <= 0) {
            throw new AssertionError();
        }
        final String c = this.c(s);
        System.out.println("getAttribute(name)= " + this.c(s));
        if (c == null) {
            return null;
        }
        System.out.println("Configuration.getRootPage().toString()= " + G.b().toString());
        final String format = String.format(c.replaceFirst("./", G.b().toString()), G.c());
        try {
            return new URL(format);
        }
        catch (MalformedURLException ex) {
            throw new RuntimeException("Invalid attribute value: " + this.l(s));
        }
    }
    
    public final boolean k(final String s) {
        if (!bg.b && s.length() <= 0) {
            throw new AssertionError();
        }
        return Boolean.parseBoolean(this.c(s));
    }
    
    static {
        bg.b = !bg.class.desiredAssertionStatus();
    }
}
