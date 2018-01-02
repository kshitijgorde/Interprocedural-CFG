// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.Color;
import java.awt.FontMetrics;
import java.util.StringTokenizer;
import java.awt.Point;
import java.awt.Dimension;
import com.daysofwonder.util.K;
import com.daysofwonder.b.a;

class d extends B
{
    public o a;
    public String b;
    public int c;
    
    public d(final String b, final o o) {
        try {
            this.a = (o)o.clone();
        }
        catch (CloneNotSupportedException ex) {
            System.out.println("Can't clone: " + ex.getMessage());
        }
        this.b = b;
    }
    
    public void a(final a a, final K k, final Dimension dimension, final Point point) {
        int x = point.x;
        boolean b = false;
        final int width = dimension.width;
        final FontMetrics d = a.d();
        final StringTokenizer stringTokenizer = new StringTokenizer(this.b, " ", true);
        final int countTokens = stringTokenizer.countTokens();
        int width2 = 0;
        this.a.a(a);
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < countTokens; ++i) {
            final String nextToken = stringTokenizer.nextToken();
            if (!nextToken.equals(" ")) {
                final String string = sb.toString() + nextToken;
                if (x + d.stringWidth(string) + 10 > width2) {
                    width2 = x + d.stringWidth(string) + 10;
                }
                if (x + d.stringWidth(string) > width - 15) {
                    k.c(new d(sb.toString(), this.a));
                    k.c(new ay(null));
                    x = 0;
                    b = true;
                    sb.setLength(0);
                }
                sb.append(nextToken);
            }
            else {
                sb.append(nextToken);
            }
        }
        final String string2 = sb.toString();
        if (!b) {
            dimension.width = width2;
        }
        if (x + d.stringWidth(string2) >= width - 15) {
            sb.setLength(0);
            for (int length = string2.length(), j = 0; j < length; ++j) {
                sb.append(string2.charAt(j));
                if (x + d.stringWidth(sb.toString()) >= width - 15) {
                    k.c(new d(sb.toString(), this.a));
                    k.c(new ay(null));
                    x = 0;
                    sb.setLength(0);
                }
            }
        }
        k.c(new d(sb.toString(), this.a));
        final int x2 = x + d.stringWidth(sb.toString());
        this.a.b(a);
        point.x = x2;
    }
    
    public int a() {
        return this.c;
    }
    
    public void a(final a a, final Point point, final Color color) {
        this.a.a(a);
        Color c = null;
        if (color != null) {
            c = a.c();
            a.a(color);
        }
        a.a(this.b, point.x, point.y);
        final FontMetrics d = a.d();
        this.e.x = point.x;
        this.c = point.y + 2;
        this.e.y = point.y - d.getAscent();
        this.e.width = a.d().stringWidth(this.b);
        this.e.height = d.getHeight();
        point.x += this.e.width;
        if (c != null) {
            a.a(c);
        }
        this.a.b(a);
    }
}
