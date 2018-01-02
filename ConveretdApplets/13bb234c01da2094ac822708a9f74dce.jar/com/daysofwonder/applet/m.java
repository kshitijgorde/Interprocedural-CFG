// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.GraphicsConfiguration;
import java.awt.Component;
import javax.swing.PopupFactory;
import java.awt.Toolkit;
import java.awt.Point;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.Popup;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComponent;

public class m extends JComponent
{
    protected String a;
    protected JComponent b;
    private boolean d;
    private Font e;
    private final int f = 30;
    private final int g = 10;
    private Color h;
    private Color i;
    private String[] j;
    int c;
    private Popup k;
    private Rectangle l;
    
    public m(final String a, final JComponent b, final Font e) {
        this.h = Color.yellow;
        this.i = Color.black;
        this.j = new String[] { "<b>", "<i>", "</b>", "</i>", "" };
        this.a = a;
        this.b = b;
        this.e = e;
        this.setBackground(new Color(255, 255, 220));
        this.setOpaque(false);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
        graphics.setColor(this.getForeground());
        graphics.drawRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
        final Font font = graphics.getFont();
        graphics.setFont(this.e);
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
        graphics.setColor(this.i);
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
        while (this.j[n].length() > 0) {
            final int index = s.indexOf(this.j[n]);
            if (index >= 0 && (index < n2 || n2 == -1)) {
                n2 = index;
            }
            ++n;
        }
        return n2;
    }
    
    public void a(final int n, final int n2, final Rectangle rectangle) {
        final FontMetrics fontMetrics = this.getFontMetrics(this.e);
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
        final Point locationOnScreen = this.b.getLocationOnScreen();
        final Point point = new Point();
        final GraphicsConfiguration graphicsConfiguration = this.b.getGraphicsConfiguration();
        final Rectangle bounds = graphicsConfiguration.getBounds();
        final Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(graphicsConfiguration);
        final Rectangle rectangle2 = bounds;
        rectangle2.x += screenInsets.left;
        final Rectangle rectangle3 = bounds;
        rectangle3.y += screenInsets.top;
        final Rectangle rectangle4 = bounds;
        rectangle4.width -= screenInsets.left + screenInsets.right;
        final Rectangle rectangle5 = bounds;
        rectangle5.height -= screenInsets.top + screenInsets.bottom;
        this.c();
        final Dimension preferredSize = this.getPreferredSize();
        point.x = locationOnScreen.x + n;
        point.y = locationOnScreen.y + n2 + 20;
        if (this.l == null) {
            this.l = new Rectangle();
        }
        this.l.setBounds(point.x, point.y, preferredSize.width, preferredSize.height);
        if (point.x < bounds.x) {
            point.x = bounds.x;
        }
        else if (point.x - bounds.x + preferredSize.width > bounds.width) {
            point.x = bounds.x + Math.max(0, bounds.width - preferredSize.width);
        }
        if (point.y < bounds.y) {
            point.y = bounds.y;
        }
        else if (point.y - bounds.y + preferredSize.height > bounds.height) {
            point.y = bounds.y + Math.max(0, bounds.height - preferredSize.height);
        }
        (this.k = PopupFactory.getSharedInstance().getPopup(this.b, this, point.x, point.y)).show();
        this.d = true;
    }
    
    public void b() {
        this.c();
    }
    
    public void c() {
        if (this.d) {
            this.k.hide();
        }
        this.d = false;
    }
}
