// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.Enumeration;
import java.util.Vector;

class af extends p
{
    static final double g;
    static final int h = 10;
    static int n;
    int b;
    int[] j;
    String[] i;
    private Vector d;
    private Vector k;
    private final long c = 10000L;
    private static final int f = 1;
    private static final int null = 2;
    private int e;
    boolean m;
    aa l;
    ChartBody void;
    
    af(final av av, final int n, int n2) {
        super(av, n, (n2 > 0) ? n2 : 1);
        this.d = new Vector(100);
        this.k = new Vector(100);
        this.e = 1;
        this.m = true;
        this.void = null;
        n2 = ((n2 > 0) ? n2 : 0);
        this.b = super.new / af.n + 1;
        this.j = new int[2 * this.b];
        this.i = new String[2 * this.b];
        super.for = av.case().left + av.b() + 1;
        super.a = av.case().top + 1;
    }
    
    void a(final int n, int n2) {
        super.a(n, (n2 > 0) ? n2 : 1);
        n2 = ((n2 > 0) ? n2 : 1);
        this.b = super.new / af.n + 1;
        this.j = new int[2 * this.b];
        this.i = new String[2 * this.b];
        super.for = super.else.case().left + super.else.b() + 1;
        super.a = super.else.case().top + 1;
    }
    
    private final String a(final float n) {
        String a;
        int length;
        for (a = this.l.a(n), length = a.length(); length > 0 && 5 + p.char.stringWidth(a.substring(0, length)) >= super.new; --length) {}
        return a.substring(0, length);
    }
    
    void for() {
        float n;
        float n2;
        if (this.void != null && !this.void.fl && !this.void.fj) {
            n = this.void.ab();
            n2 = this.void.ah();
        }
        else {
            n = super.else.bC;
            n2 = super.else.bF;
        }
        if (n > n2) {
            final float n3 = n;
            n = n2;
            n2 = n3;
        }
        if (super.else.bE) {
            n = (float)(Math.log(n) / af.g);
            n2 = (float)(Math.log(n2) / af.g);
        }
        System.currentTimeMillis();
        this.l = super.else.parent.eZ;
        final int do1 = super.else.parent.eZ.do();
        final int if1 = super.else.parent.eZ.if();
        if (super.else instanceof v || super.else instanceof e) {
            this.l.a(0);
        }
        super.int.setColor(super.try.gY);
        super.int.fillRect(0, 0, super.new, super.if);
        this.d.removeAllElements();
        this.k.removeAllElements();
        float n4;
        if (this.void != null) {
            n4 = this.void.ai();
        }
        else {
            n4 = super.else.void();
        }
        final float n5 = n4;
        if (n == n2) {
            if (n2 < 0.0) {
                n2 *= 0.05;
                n *= 1.05;
            }
            else {
                n2 *= 1.05;
                n *= 0.95;
            }
        }
        final float n6 = (super.else.char() - super.else.byte().bottom - super.else.byte().top) / (n2 - n);
        float n7;
        if (super.else.bE) {
            n7 = (float)(Math.pow(10.0, n2) - Math.pow(10.0, n)) * 10000.0f * af.n / super.if;
        }
        else {
            n7 = (n2 - n) * 10000.0f * af.n / super.if;
        }
        double n8 = 1.0;
        int n9 = 0;
        while (n7 > n8) {
            if (n9 % 2 == 0) {
                n8 *= 5.0;
            }
            else {
                n8 *= 2.0;
            }
            ++n9;
        }
        final double n10 = ((n9 % 2 == 0) ? (n8 * 5.0) : (n8 * 2.0)) / 10000.0;
        double n11 = n8 / 10000.0;
        final int round = Math.round(super.else.char() - super.else.byte().bottom);
        float n12 = (float)n11;
        if (super.else.bE) {
            n12 = (float)(Math.log(n12) / af.g);
        }
        if (Math.abs(Math.round(super.else.char() - super.else.byte().bottom - n12 * n6) - round) <= af.n / 5.0) {
            n11 = n10;
        }
        final String a = this.a(n4);
        float n13 = n4;
        if (super.else.bE) {
            n13 = (float)(Math.log(n13) / af.g);
        }
        int round2 = Math.round(super.else.char() - super.else.byte().bottom - (n13 - n) * n6);
        super.int.setColor(super.try.g1);
        super.int.drawString(a, 5, round2 + p.char.getHeight() / 3);
        super.int.setColor(super.try.gy);
        super.int.drawLine(0, round2, 3, round2);
        this.d.addElement(a);
        this.k.addElement(new Float(round2));
        float n14 = (float)(((int)(n5 / n11) + 1) * n11);
        while (true) {
            float n15 = n14;
            if (super.else.bE) {
                n15 = (float)(Math.log(n15) / af.g);
            }
            final int round3 = Math.round(super.else.char() - super.else.byte().bottom - (n15 - n) * n6);
            if (round3 < 6) {
                break;
            }
            if (round2 - round3 > af.n) {
                final String a2 = this.a(n14);
                super.int.setColor(super.try.g2);
                super.int.drawString(a2, 5, round3 + p.char.getHeight() / 3);
                super.int.setColor(super.try.gy);
                super.int.drawLine(0, round3, 3, round3);
                this.d.insertElementAt(a2, 0);
                this.k.insertElementAt(new Float(round3), 0);
                round2 = round3;
            }
            n14 += (float)n11;
        }
        float n16 = n5;
        if (super.else.bE) {
            n16 = (float)(Math.log(n16) / af.g);
        }
        int round4 = Math.round(super.else.char() - super.else.byte().bottom - (n16 - n) * n6);
        float n17 = (float)((int)(n5 / n11) * n11);
        while (true) {
            float n18 = n17;
            if (super.else.bE) {
                n18 = (float)(Math.log(n18) / af.g);
            }
            final int round5 = Math.round(super.else.char() - super.else.byte().bottom - (n18 - n) * n6);
            if (round5 > super.if - 6) {
                break;
            }
            if (round5 - round4 > af.n) {
                final String a3 = this.a(n17);
                super.int.setColor(super.try.g2);
                super.int.drawString(a3, 5, round5 + p.char.getHeight() / 3);
                super.int.setColor(super.try.gy);
                super.int.drawLine(0, round5, 3, round5);
                this.d.addElement(a3);
                this.k.addElement(new Float(round5));
                round4 = round5;
            }
            n17 -= (float)n11;
        }
        this.i = new String[this.d.size()];
        this.j = new int[this.k.size()];
        this.d.copyInto(this.i);
        final Enumeration<Float> elements = (Enumeration<Float>)this.k.elements();
        int b = 0;
        while (elements.hasMoreElements()) {
            this.j[b] = (int)(Object)elements.nextElement();
            ++b;
        }
        this.b = b;
        this.a(false);
        super.else.parent.eZ.a(do1, if1);
    }
    
    int a(final int n) {
        int i = 0;
        int n2 = this.b - 1;
        int n3 = (i + n2) / 2;
        if (this.j[i] > n || this.j[n2] < n) {
            return -1;
        }
        while (i <= n2) {
            if (this.j[n3] < n) {
                i = n3 + 1;
            }
            else {
                if (this.j[n3] <= n) {
                    return n3;
                }
                n2 = n3 - 1;
            }
            n3 = (i + n2) / 2;
        }
        if (Math.abs(n - this.j[i]) < Math.abs(n - this.j[n2])) {
            return i;
        }
        return n2;
    }
    
    void a(final ChartBody void1) {
        this.void = void1;
    }
    
    public void do() {
        this.a(true);
    }
    
    public void a(final Graphics graphics) {
        if (super.case == null) {
            super.case = super.else.createImage(super.new, super.if);
        }
        if (this.a()) {
            (super.int = super.case.getGraphics()).setFont(super.byte);
            p.char = super.int.getFontMetrics();
            this.for();
        }
        graphics.drawImage(super.case, super.for, super.a, super.else);
        if (super.int != null) {
            super.int.dispose();
        }
    }
    
    public void a(final Graphics graphics, int n) {
        n -= super.a;
        final int a = this.a(n);
        if (a < 0) {
            return;
        }
        final Font font = graphics.getFont();
        graphics.setFont(super.byte);
        graphics.setColor(super.do);
        graphics.drawString((String)this.d.elementAt(a), super.for + 5, super.a + this.j[a] + p.char.getHeight() / 3);
        graphics.setColor(super.else.bK.gW);
        graphics.setFont(font);
    }
    
    static {
        g = Math.log(10.0);
        af.n = 12;
    }
}
