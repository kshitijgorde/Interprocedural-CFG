// 
// Decompiled by Procyon v0.5.30
// 

package ji.graphic;

import java.awt.Dimension;
import java.awt.SystemColor;
import ji.util.e;
import ji.util.d;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Color;
import ji.v1base.jiPanel;

public class do extends jiPanel
{
    boolean a;
    Color b;
    String c;
    String[] d;
    int[] e;
    int f;
    int g;
    int h;
    int i;
    boolean j;
    boolean k;
    private String l;
    private int m;
    private boolean n;
    Object o;
    
    public void a() {
        this.j = false;
    }
    
    public void releaseResources() {
        super.releaseResources();
    }
    
    public do(final boolean b, final Color color, final String s) {
        this(b, color, s, true, false);
    }
    
    public do(final boolean a, final Color b, final String l, final boolean n, final boolean b2) {
        super(l);
        this.a = false;
        this.b = null;
        this.c = "";
        this.d = null;
        this.e = null;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = false;
        this.k = false;
        this.l = null;
        this.m = 0;
        this.n = true;
        this.o = new Object();
        this.n = n;
        this.l = l;
        this.b = b;
        this.a = a;
        this.setOpaque(true);
        this.setAcceptFocus(false);
        this.setBorderStyle(0);
        this.setDarkShade(Color.black);
        this.setBackground(this.b);
        this.setLayout(null);
        if (b2) {
            this.m = -1;
        }
    }
    
    public void setBackground(final Color b) {
        if (b != null) {
            super.setBackground(this.b = b);
        }
    }
    
    public int b() {
        return this.f + 20;
    }
    
    public int c() {
        return this.d() + 7 + this.h / 2;
    }
    
    public int d() {
        if (this.f() > this.m || this.j) {
            this.j = true;
            return this.g + 2 + 4;
        }
        return this.g - this.h / 2;
    }
    
    private int f() {
        synchronized (this) {
            return this.i;
        }
    }
    
    public void a(final int i) {
        synchronized (this) {
            this.i = i;
        }
    }
    
    public void a(final String c, final Graphics graphics) {
        synchronized (this.o) {
            Label_0311: {
                try {
                    this.c = c;
                    if (c != null && graphics != null) {
                        this.f = 0;
                        if (this.a) {
                            if (ji.util.d.em() || ji.util.d.aj(this.l)) {
                                this.h = graphics.getFontMetrics().getHeight() + 4;
                            }
                            else {
                                this.h = graphics.getFontMetrics().getHeight() + 2;
                            }
                        }
                        else {
                            this.h = graphics.getFontMetrics().getHeight();
                        }
                        final char[] charArray = c.toCharArray();
                        int n = 1;
                        for (int i = 0; i < charArray.length; ++i) {
                            if (charArray[i] == '\n') {
                                ++n;
                            }
                        }
                        this.d = new String[n];
                        this.e = new int[n];
                        int n2 = 1;
                        for (int j = 0; j < charArray.length; ++j) {
                            if (charArray[j] == '\n') {
                                ++n2;
                            }
                            else {
                                if (this.d[n2 - 1] == null) {
                                    this.d[n2 - 1] = "";
                                }
                                final String[] d = this.d;
                                final int n3 = n2 - 1;
                                d[n3] = String.valueOf(String.valueOf(d[n3])).concat(String.valueOf(String.valueOf(charArray[j])));
                                this.e[n2 - 1] = graphics.getFontMetrics().stringWidth(this.d[n2 - 1]);
                                this.f = Math.max(this.f, this.e[n2 - 1]);
                            }
                        }
                        this.g = this.h * n2 - 1;
                        this.f += 6;
                    }
                    break Label_0311;
                }
                catch (Exception ex) {
                }
                // monitorexit(this.o)
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.isSwing()) {
            super.paint(graphics);
        }
        else {
            this.paintComponent(graphics);
        }
    }
    
    private void g() {
        ji.util.e.b(new ve(this), this.l);
    }
    
    public void e() {
        try {
            this.g();
        }
        catch (Exception ex) {}
    }
    
    public void paintComponent(final Graphics graphics) {
        synchronized (this.o) {
            if (!this.k) {
                try {
                    this.k = true;
                    final Dimension size = this.getSize();
                    Color color = this.getDarkShade();
                    final Color lightShade = this.getLightShade();
                    if (color == null) {
                        if (ji.util.e.g != null) {
                            color = ji.util.e.g;
                        }
                        else {
                            color = SystemColor.controlShadow;
                        }
                    }
                    if (lightShade == null) {
                        if (ji.util.e.h != null) {
                            final Color h = ji.util.e.h;
                        }
                        else {
                            final SystemColor controlLtHighlight = SystemColor.controlLtHighlight;
                        }
                    }
                    if (this.n) {
                        graphics.setColor(this.b.darker());
                        graphics.drawLine(0, 0, size.width, 0);
                        graphics.drawLine(0, 0, 0, size.height);
                        graphics.setColor(this.b.brighter());
                        graphics.drawLine(1, 1, size.width, 1);
                        graphics.drawLine(1, 1, 1, size.height);
                        graphics.setColor(this.b.darker().darker().darker());
                        graphics.drawLine(0, size.height - 1, size.width, size.height - 1);
                        graphics.drawLine(size.width - 1, 0, size.width - 1, size.height);
                        graphics.setColor(this.b.darker().darker());
                        graphics.drawLine(1, size.height - 2, size.width - 2, size.height - 2);
                        graphics.drawLine(size.width - 2, 1, size.width - 2, size.height - 2);
                        graphics.setColor(this.b);
                        graphics.fillRect(3, 3, size.width - 6, size.height - 6);
                    }
                    if (graphics != null) {
                        super.paintComponent(graphics);
                    }
                    graphics.setColor(color);
                    final int d = this.d();
                    if (this.c != null) {
                        graphics.setColor(this.getForeground());
                        final int n = (size.width + 3 - this.f) / 2;
                        int n2 = (size.height - d) / 2;
                        if (this.f() > this.m || this.j) {
                            this.j = true;
                            n2 += 10;
                        }
                        else {
                            n2 += 8;
                        }
                        if (this.d != null) {
                            for (int i = 0; i < this.d.length; ++i) {
                                graphics.drawString(this.d[i], n + (this.f - this.e[i]) / 2, n2);
                                n2 += this.h;
                            }
                        }
                    }
                    graphics.setColor(color);
                    if (this.f() > this.m || this.j) {
                        graphics.setColor(this.b.darker());
                        final int n3 = 4;
                        final int n4 = size.width - 2 * n3;
                        final int n5 = size.height - n3 - 2 - 4;
                        final int n6 = size.width - n3 - 5;
                        final int n7 = n3 + 4;
                        graphics.drawLine(n7 - 1, n5 - 1, n6, n5 - 1);
                        graphics.drawLine(n7 - 1, n5 - 1, n7 - 1, n5 + 2 + 2);
                        graphics.setColor(this.b.brighter());
                        graphics.drawLine(n7, n5 + 2 + 2, n6, n5 + 2 + 2);
                        graphics.drawLine(n6, n5, n6, n5 + 2 + 2);
                        graphics.setColor(color);
                        final int n8 = (size.width - n3 - 6) * this.f() / 100;
                        if (this.f() > this.m && n5 > 0) {
                            int n9 = 3;
                            final int n10 = 1;
                            graphics.setColor(SystemColor.textHighlight);
                            for (int j = n3 + 5; j <= n8 + 2; j += n9 + n10) {
                                if (n9 + j > n6) {
                                    n9 = n6 - j;
                                }
                                if (n9 <= 0) {
                                    break;
                                }
                                graphics.fillRect(j, n5 + 1, n9, 3);
                            }
                        }
                    }
                }
                catch (Exception ex) {}
                finally {
                    this.k = false;
                }
            }
        }
        // monitorexit(this.o)
    }
    
    public final void update(final Graphics graphics) {
        if (this.isSwing()) {
            super.update(graphics);
        }
        else {
            this.paintComponent(graphics);
        }
    }
}
