// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.Frame;
import java.applet.Applet;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Container;
import java.awt.Component;
import java.awt.Canvas;

public class a extends Canvas
{
    protected String a;
    protected Component b;
    private Container d;
    private LayoutManager e;
    private boolean f;
    private Font g;
    private final int h = 30;
    private final int i = 10;
    private Color j;
    private Color k;
    private String[] l;
    int c;
    
    public a(final String a, final Component b, final Font g) {
        this.j = Color.yellow;
        this.k = Color.black;
        this.l = new String[] { "<b>", "<i>", "</b>", "</i>", "" };
        this.a = a;
        this.b = b;
        this.g = g;
        this.setBackground(new Color(255, 255, 220));
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
        final Font font = graphics.getFont();
        graphics.setFont(this.g);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        int n = 0;
        this.c = 0;
        int n2 = 0;
        String a;
        while ((a = this.a()) != null) {
            ++n;
            final int a2;
            if ((a2 = this.a(graphics, a, null)) > n2) {
                n2 = a2;
            }
        }
        int n3 = 0 + (fontMetrics.getHeight() - 2);
        graphics.setColor(this.k);
        this.c = 0;
        int n4 = (this.getSize().width - n2) / 2;
        if (n4 <= 0) {
            n4 = 1;
        }
        String a3;
        while ((a3 = this.a()) != null) {
            this.a(graphics, a3, n4, n3);
            n3 += fontMetrics.getHeight();
        }
        graphics.setFont(font);
    }
    
    protected String a() {
        if (this.a == null) {
            return null;
        }
        if (this.c >= this.a.length()) {
            return null;
        }
        final int index = this.a.indexOf("<br>", this.c);
        String s;
        if (index == -1) {
            s = this.a.substring(this.c);
            this.c = this.a.length();
        }
        else {
            s = this.a.substring(this.c, index);
            this.c = index + 4;
        }
        return s;
    }
    
    int a(final Graphics graphics, String s, final FontMetrics fontMetrics) {
        int n = 0;
        for (int i = this.a(s); i >= 0; i = this.a(s)) {
            final String substring = s.substring(0, i);
            FontMetrics fontMetrics2;
            if (fontMetrics == null) {
                fontMetrics2 = graphics.getFontMetrics();
            }
            else {
                fontMetrics2 = fontMetrics;
            }
            n += fontMetrics2.stringWidth(substring);
            s = s.substring(i);
            if (s.startsWith("<b>")) {
                final Font font = (fontMetrics == null) ? graphics.getFont() : fontMetrics.getFont();
                graphics.setFont(new Font(font.getName(), 1, font.getSize()));
                s = s.substring(3);
            }
            if (s.startsWith("<i>")) {
                final Font font2 = (fontMetrics == null) ? graphics.getFont() : fontMetrics.getFont();
                graphics.setFont(new Font(font2.getName(), 2, font2.getSize()));
                s = s.substring(3);
            }
            if (s.startsWith("</b>") || s.startsWith("</i>")) {
                final Font font3 = (fontMetrics == null) ? graphics.getFont() : fontMetrics.getFont();
                graphics.setFont(new Font(font3.getName(), 0, font3.getSize()));
                s = s.substring(4);
            }
        }
        return n + ((fontMetrics == null) ? graphics.getFontMetrics() : fontMetrics).stringWidth(s);
    }
    
    void a(final Graphics graphics, String s, int n, final int n2) {
        for (int i = this.a(s); i >= 0; i = this.a(s)) {
            final String substring = s.substring(0, i);
            graphics.drawString(substring, n, n2);
            n += graphics.getFontMetrics().stringWidth(substring);
            s = s.substring(i);
            if (s.startsWith("<b>")) {
                final Font font = graphics.getFont();
                graphics.setFont(new Font(font.getName(), 1, font.getSize()));
                s = s.substring(3);
            }
            if (s.startsWith("<i>")) {
                final Font font2 = graphics.getFont();
                graphics.setFont(new Font(font2.getName(), 2, font2.getSize()));
                s = s.substring(3);
            }
            if (s.startsWith("</b>") || s.startsWith("</i>")) {
                final Font font3 = graphics.getFont();
                graphics.setFont(new Font(font3.getName(), 0, font3.getSize()));
                s = s.substring(4);
            }
        }
        if (s.length() > 0) {
            graphics.drawString(s, n, n2);
        }
    }
    
    int a(final String s) {
        int n = 0;
        int n2 = -1;
        while (this.l[n].length() > 0) {
            final int index = s.indexOf(this.l[n]);
            if (index >= 0 && (index < n2 || n2 == -1)) {
                n2 = index;
            }
            ++n;
        }
        return n2;
    }
    
    public void a(final int n, final int n2, final Rectangle rectangle) {
        if (this.d == null) {
            this.d();
        }
        this.d.setLayout(null);
        final FontMetrics fontMetrics = this.getFontMetrics(this.g);
        int n3 = 0;
        this.c = 0;
        int n4 = 0;
        String a;
        while ((a = this.a()) != null) {
            ++n3;
            final int a2;
            if ((a2 = this.a(this.b.getGraphics(), a, fontMetrics)) > n4) {
                n4 = a2;
            }
        }
        this.setSize(n4 + 4 + 2, n3 * fontMetrics.getHeight() + 4 + 2);
        final Point point = new Point();
        point.x = n + 0;
        point.y = n2 + 20;
        this.getSize();
        this.setLocation(point.x, point.y);
        if (this.getLocation().x + this.getSize().width > this.d.getSize().width) {
            this.setLocation(this.d.getSize().width - this.getSize().width - 5, this.getLocation().y);
        }
        if (this.d.getSize().height < this.getLocation().y + this.getSize().height) {
            this.setLocation(this.getLocation().x, this.getLocation().y - (this.getSize().height + 20));
        }
        this.d.add(this, 0);
        this.d.validate();
        this.repaint();
        this.f = true;
    }
    
    public void b() {
        this.c();
    }
    
    public void c() {
        if (this.f) {
            this.d.remove(0);
            this.d.setLayout(this.e);
            this.d.validate();
        }
        this.f = false;
    }
    
    public void d() {
        Container d;
        for (d = this.b.getParent(); !(d instanceof Applet) && !(d instanceof Frame); d = d.getParent()) {}
        this.d = d;
        this.e = this.d.getLayout();
    }
}
