// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import java.awt.Container;
import java.awt.AWTEventMulticaster;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;

public class i extends f
{
    protected String v;
    protected Font w;
    protected FontMetrics x;
    private boolean y;
    private boolean z;
    private boolean A;
    static Color B;
    static Color C;
    static Color D;
    static Color E;
    static Color F;
    Insets G;
    private String H;
    Dimension I;
    ActionListener J;
    private static String K;
    
    public i() {
        this("", false);
    }
    
    public i(final String s) {
        this(s, false);
    }
    
    public i(String v, final boolean y) {
        this.v = null;
        this.y = false;
        this.z = false;
        this.A = false;
        this.G = new Insets(1, 1, 1, 1);
        this.H = null;
        this.I = new Dimension(1, 1);
        if (!f.u) {
            if (v == null) {
                v = "";
            }
            this.y = y;
            this.v = v;
            this.G.right = 20;
            this.G.left = 15;
            this.G.top = 5;
            this.G.bottom = 5;
            this.setBackground(i.C);
            this.w = new Font(i.K, 0, 12);
            this.x = this.getFontMetrics(this.w);
            this.l();
            this.enableEvents(16L);
        }
    }
    
    public String h() {
        return this.v;
    }
    
    private void l() {
        this.I.setSize(this.G.left + this.G.right + this.x.stringWidth(this.v), this.G.top + this.G.bottom + this.x.getHeight());
    }
    
    public boolean m() {
        return this.A;
    }
    
    public void a(final boolean a) {
        this.A = a;
    }
    
    public void b(final boolean z) {
        this.z = z;
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        super.processMouseEvent(mouseEvent);
        this.a(mouseEvent);
    }
    
    public String n() {
        return this.H;
    }
    
    protected void a(final MouseEvent mouseEvent) {
        final boolean u = f.u;
        int n3;
        int id;
        int n2;
        final int n = n2 = (id = (n3 = (this.isEnabled() ? 1 : 0)));
        if (!u) {
            if (n == 0) {
                return;
            }
            id = (n2 = (n3 = mouseEvent.getID()));
        }
        int n5;
        final int n4 = n5 = 504;
        Label_0144: {
            if (!u) {
                if (n2 == n4) {
                    this.setBackground(i.B);
                    if (!u) {
                        break Label_0144;
                    }
                }
                n3 = (id = mouseEvent.getID());
                final int n6;
                n5 = (n6 = 502);
            }
            if (!u) {
                if (id == n4) {
                    i i = this;
                    Label_0105: {
                        if (!u) {
                            if (!this.y) {
                                i = this;
                                if (u) {
                                    break Label_0105;
                                }
                                if (this.A) {
                                    final boolean z = this.z;
                                    if (!u && z) {}
                                    this.z = z;
                                }
                            }
                            this.b(this.H);
                            i = this;
                        }
                    }
                    i.a(new ActionEvent(this, 1001, this.H));
                    if (!u) {
                        break Label_0144;
                    }
                }
                n3 = mouseEvent.getID();
                n5 = 505;
            }
            if (n3 == n5) {
                this.setBackground(i.C);
            }
        }
        this.repaint();
    }
    
    public void c(final String h) {
        this.H = h;
    }
    
    public boolean o() {
        return this.y;
    }
    
    public void paint(final Graphics graphics) {
        this.b(graphics);
        this.c(graphics);
        super.paint(graphics);
    }
    
    public void b(final Graphics graphics) {
    }
    
    public void c(final Graphics graphics) {
        final boolean u = f.u;
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, this.I.width, this.I.height);
        graphics.setColor(this.getForeground());
        graphics.setFont(this.w);
        final int n = this.I.height - this.x.getDescent() - this.G.bottom;
        graphics.drawString(this.v, this.G.left, n);
        int z;
        final int n2 = z = (this.z ? 1 : 0);
        if (!u) {
            if (n2 != 0) {
                final int n3 = this.G.left - 10;
                graphics.setColor(Color.black);
                graphics.drawLine(n3 - 3, n - 4, n3, n - 1);
                graphics.drawLine(n3 - 3, n - 5, n3, n - 2);
                graphics.drawLine(n3, n - 1, n3 + 3, n - 8);
                graphics.drawLine(n3, n - 2, n3 + 3, n - 9);
            }
            final boolean y;
            z = ((y = this.y) ? 1 : 0);
        }
        if (!u) {
            if (n2 == 0) {
                return;
            }
            z = this.I.width - this.G.right;
        }
        final int n4 = z;
        final int[] array = { n4 + 10, n4 + 10, n4 + 15 };
        final int[] array2 = { n, n - 10, n - 5 };
        graphics.setColor(Color.black);
        graphics.fillPolygon(array, array2, 3);
    }
    
    public Dimension getPreferredSize() {
        return this.I;
    }
    
    public synchronized void a(final ActionListener actionListener) {
        this.J = AWTEventMulticaster.add(this.J, actionListener);
    }
    
    public synchronized void b(final ActionListener actionListener) {
        this.J = AWTEventMulticaster.remove(this.J, actionListener);
    }
    
    public synchronized void a(final ActionEvent actionEvent) {
        final ActionListener j = this.J;
        if (!f.u) {
            if (j == null) {
                return;
            }
            final ActionListener i = this.J;
        }
        j.actionPerformed(actionEvent);
    }
    
    public void d(final String v) {
        final boolean u = f.u;
        this.v = v;
        this.l();
        final Container parent = this.getParent();
        if (!u) {
            Label_0051: {
                if (parent != null) {
                    Container container;
                    final k k = (k)(container = parent);
                    if (!u) {
                        if (!(k instanceof k)) {
                            break Label_0051;
                        }
                        container = parent;
                    }
                    ((k)container).b(this.I.width);
                }
            }
            this.repaint();
        }
    }
    
    static {
        final char[] charArray = "\u0010p-'`3".toCharArray();
        int length;
        int n2;
        final int n = n2 = (length = charArray.length);
        int n3 = 0;
        while (true) {
            Label_0094: {
                if (n > 1) {
                    break Label_0094;
                }
                length = (n2 = n3);
                do {
                    final char c = charArray[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = 'T';
                            break;
                        }
                        case 1: {
                            c2 = '\u0019';
                            break;
                        }
                        case 2: {
                            c2 = 'L';
                            break;
                        }
                        case 3: {
                            c2 = 'K';
                            break;
                        }
                        default: {
                            c2 = '\u000f';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                i.K = new String(charArray).intern();
                i.B = new Color(187, 174, 244);
                i.C = new Color(205, 205, 205);
                i.D = new Color(100, 100, 100);
                i.E = new Color(235, 235, 235);
                i.F = new Color(150, 150, 150);
                return;
            }
            continue;
        }
    }
}
