// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.net.MalformedURLException;
import java.net.URL;
import com.daysofwonder.util.b;
import java.io.Reader;
import java.io.StringReader;
import com.daysofwonder.util.c;
import java.awt.Color;
import com.daysofwonder.util.y;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.Dimension;
import com.daysofwonder.b.a;
import com.daysofwonder.util.K;

public class T
{
    public static K a(final K k, final a a, final Dimension dimension) {
        final K i = new K();
        if (k != null) {
            final Point point = new Point(0, 0);
            final FontMetrics d = a.d();
            point.x = 0;
            point.y = 0 + d.getHeight() + 1;
            final int width = dimension.width;
            int width2 = 0;
            final y e = k.e();
            while (e.a()) {
                final B b = (B)e.b();
                dimension.width = width;
                b.a(a, i, dimension, point);
                if (dimension.width > width2) {
                    width2 = dimension.width;
                }
            }
            dimension.width = width2;
        }
        return i;
    }
    
    public static void b(final K k, final a a, final Dimension dimension) {
        if (k != null) {
            final Point point = new Point(0, 0);
            final FontMetrics d = a.d();
            point.x = 0;
            point.y = 0 + d.getHeight() + 1;
            final y e = k.e();
            while (e.a()) {
                ((B)e.b()).a(a, point, null);
            }
        }
    }
    
    public static K a(final String s) {
        final K k = new K();
        final o o = new o(null);
        final c c = new c();
        c.a(new StringReader(s));
        b a;
        while ((a = c.a()) != null) {
            final Object a2 = a(c, a, o);
            if (a2 != null) {
                k.c(a2);
            }
        }
        return k;
    }
    
    protected static Object a(final c c, final b b, final o o) {
        final Object o2 = null;
        if (b.e()) {
            if (b.a().equals("b")) {
                o.a(1);
            }
            if (b.a().equals("i")) {
                o.a(2);
            }
            if (b.a().equals("a")) {
                final K k = new K();
                b a;
                while ((a = c.a()) != null) {
                    final Object a2 = a(c, a, o);
                    if (a2 != null && !(a2 instanceof ai)) {
                        k.c(a2);
                    }
                    else {
                        if (a2 instanceof ai) {
                            break;
                        }
                        continue;
                    }
                }
                URL url = null;
                try {
                    url = new URL(b.a("href"));
                }
                catch (MalformedURLException ex) {
                    System.out.println("could not parse url: " + b.a("href"));
                }
                return new as(k, url);
            }
            if (b.a().equals("br")) {
                return new ay(null);
            }
        }
        else if (b.f()) {
            if (b.a().equals("b")) {
                o.b(1);
            }
            if (b.a().equals("i")) {
                o.b(2);
            }
            if (b.a().equals("a")) {
                return new ai(null);
            }
        }
        else if (b.b()) {
            return new d(b.c(), o);
        }
        return o2;
    }
}
