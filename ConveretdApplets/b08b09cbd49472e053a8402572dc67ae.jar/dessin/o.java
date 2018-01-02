// 
// Decompiled by Procyon v0.5.30
// 

package dessin;

import java.awt.Font;
import java.awt.Label;
import java.awt.Point;
import java.awt.Graphics;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.Rectangle;
import java.awt.Color;

public class o extends m
{
    private Color r;
    String w;
    private String q;
    int u;
    int s;
    Rectangle v;
    Vector t;
    
    public o(final String w, final Color r, final String q) {
        this.r = Color.black;
        this.w = w;
        this.r = r;
        this.q = q;
        this.a(w);
        this.v = new Rectangle();
        this.v = this.try();
        this.u = this.v.x + this.v.width / 2;
        this.s = this.v.y + this.v.height / 2;
    }
    
    m do(final int n, final int n2) {
        final double n3 = this.u - this.v.width / 2;
        final double n4 = this.s - this.v.height / 2;
        if (n >= n3 && n <= n3 + this.v.width && n2 >= n4 && n2 <= n4 + this.v.height) {
            return this;
        }
        return null;
    }
    
    void a(final Vector vector) {
        final double n = this.u - this.v.width / 2;
        final double n2 = this.s - this.v.height / 2;
        final int intValue = vector.elementAt(0);
        final int intValue2 = vector.elementAt(1);
        final int intValue3 = vector.elementAt(2);
        final int intValue4 = vector.elementAt(3);
        if (intValue < n && intValue2 < n2 && intValue3 > n + this.v.width && intValue4 > n2 + this.v.height) {
            super.a = true;
        }
    }
    
    void a(final int n, final int n2, final int n3, final int n4) {
        final Enumeration<m> elements = this.t.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().a(n, n2, n3, n4);
        }
        this.u += n3 - n;
        this.s += n4 - n2;
    }
    
    void a(final Graphics graphics, final boolean b) {
        if (!b) {
            graphics.setColor(Color.black);
        }
        else if (!super.a) {
            return;
        }
        final Enumeration<m> elements = this.t.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().a(graphics, false);
        }
    }
    
    void if(final Graphics graphics, final int u, final int s) {
        final Enumeration<m> elements = this.t.elements();
        while (elements.hasMoreElements()) {
            final m m = elements.nextElement();
            m.a(this.u, this.s, u, s);
            m.a(graphics, false);
        }
        this.u = u;
        this.s = s;
    }
    
    void a(final Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.drawRect(this.u - this.v.width / 2, this.s - this.v.height / 2, this.v.width, this.v.height);
    }
    
    void a(final Graphics graphics, final int n, final int n2) {
    }
    
    public Rectangle for() {
        double n = 500.0;
        double n2 = 0.0;
        double n3 = 500.0;
        double n4 = 0.0;
        for (int i = 0; i < this.t.size(); ++i) {
            final m m = this.t.elementAt(i);
            n = ((m.for().x <= n) ? m.for().x : n);
            n3 = ((m.for().y <= n3) ? m.for().y : n3);
            n2 = ((m.for().x + m.for().width >= n2) ? (m.for().x + m.for().width) : n2);
            n4 = ((m.for().y + m.for().height >= n4) ? (m.for().y + m.for().height) : n4);
        }
        return new Rectangle((int)n, (int)n3, (int)(n2 - n), (int)(n4 - n3));
    }
    
    void a() {
    }
    
    Color do() {
        return Color.white;
    }
    
    public String if() {
        final String property = System.getProperty("line.separator");
        String s = "";
        int n = 1;
        final Enumeration<m> elements = (Enumeration<m>)this.t.elements();
        while (elements.hasMoreElements()) {
            final m m = elements.nextElement();
            if (this.t.size() != n) {
                s = s + m.if() + property;
            }
            else {
                s += m.if();
            }
            ++n;
        }
        return s;
    }
    
    private Rectangle try() {
        double n = 500.0;
        double n2 = 0.0;
        double n3 = 500.0;
        double n4 = 0.0;
        for (int i = 0; i < this.t.size(); ++i) {
            final m m = this.t.elementAt(i);
            n = ((m.for().x <= n) ? m.for().x : n);
            n3 = ((m.for().y <= n3) ? m.for().y : n3);
            n2 = ((m.for().x + m.for().width >= n2) ? (m.for().x + m.for().width) : n2);
            n4 = ((m.for().y + m.for().height >= n4) ? (m.for().y + m.for().height) : n4);
        }
        return new Rectangle((int)n, (int)n3, (int)(n2 - n), (int)(n4 - n3));
    }
    
    void if(final int n, final int n2) {
    }
    
    void if(final Point point) {
    }
    
    void a(final Point point) {
    }
    
    void a(final int n, final int n2, final int n3) {
    }
    
    int a(final int n, final int n2) {
        if (this.do(n, n2) != null) {
            return 1;
        }
        return -1;
    }
    
    private void a(final String s) {
        if (this.q.equals("3")) {
            if (s.equals("resistance")) {
                final b b = new b(14, 4, 33, 12);
                final r r = new r(3, 8, 14, 8, this.r, 1.0f);
                final r r2 = new r(33, 8, 44, 8, this.r, 1.0f);
                (this.t = new Vector()).addElement(b);
                this.t.addElement(r);
                this.t.addElement(r2);
            }
            else if (s.equals("resistance_i")) {
                final b b2 = new b(4, 10, 12, 29);
                final r r3 = new r(8, 1, 8, 10);
                final r r4 = new r(8, 29, 8, 38);
                (this.t = new Vector()).addElement(b2);
                this.t.addElement(r3);
                this.t.addElement(r4);
            }
            else if (s.equals("condo")) {
                final r r5 = new r(1, 11, 15, 11, this.r, 1.0f);
                final r r6 = new r(15, 3, 15, 19, this.r, 1.0f);
                final r r7 = new r(23, 3, 23, 19, this.r, 1.0f);
                final r r8 = new r(23, 11, 38, 11, this.r, 1.0f);
                (this.t = new Vector()).addElement(r5);
                this.t.addElement(r6);
                this.t.addElement(r7);
                this.t.addElement(r8);
            }
            else if (s.equals("condo_i")) {
                final r r9 = new r(10, 2, 10, 15, this.r, 1.0f);
                final r r10 = new r(2, 15, 18, 15, this.r, 1.0f);
                final r r11 = new r(2, 23, 17, 23, this.r, 1.0f);
                final r r12 = new r(10, 23, 10, 36, this.r, 1.0f);
                (this.t = new Vector()).addElement(r9);
                this.t.addElement(r10);
                this.t.addElement(r11);
                this.t.addElement(r12);
            }
            else if (s.equals("trans")) {
                final r r13 = new r(13, 10, 13, 28, this.r, 1.0f);
                final r r14 = new r(13, 19, 22, 19, this.r, 1.0f);
                final r r15 = new r(13, 19, 5, 11, this.r, 1.0f);
                final r r16 = new r(5, 11, 5, 3, this.r, 1.0f);
                final r r17 = new r(13, 19, 6, 26, this.r, 1.0f);
                final r r18 = new r(6, 26, 6, 37, this.r, 1.0f);
                (this.t = new Vector()).addElement(r13);
                this.t.addElement(r14);
                this.t.addElement(r15);
                this.t.addElement(r16);
                this.t.addElement(r17);
                this.t.addElement(r18);
            }
            else if (s.equals("trans_i")) {
                final r r19 = new r(4, 22, 14, 22, this.r, 1.0f);
                final r r20 = new r(15, 13, 15, 32, this.r, 1.0f);
                final r r21 = new r(15, 22, 22, 14, this.r, 1.0f);
                final r r22 = new r(22, 14, 22, 4, this.r, 1.0f);
                final r r23 = new r(14, 22, 22, 28, this.r, 1.0f);
                final r r24 = new r(22, 28, 22, 40, this.r, 1.0f);
                (this.t = new Vector()).addElement(r19);
                this.t.addElement(r20);
                this.t.addElement(r21);
                this.t.addElement(r22);
                this.t.addElement(r23);
                this.t.addElement(r24);
            }
            else if (s.equals("self")) {
                final r r25 = new r(40, 11, 46, 11, this.r, 1.0f);
                final r r26 = new r(9, 11, 3, 11, this.r, 1.0f);
                final int[] array = { 0, 180 };
                final Point point = new Point(10, 6);
                final n n = new n(point, new Point(point.x + 10, point.y + 10), this.r, 1.0f, array);
                final Point point2 = new Point(20, 6);
                final n n2 = new n(point2, new Point(point2.x + 10, point2.y + 10), this.r, 1.0f, array);
                final Point point3 = new Point(30, 6);
                final n n3 = new n(point3, new Point(point3.x + 10, point3.y + 10), this.r, 1.0f, array);
                (this.t = new Vector()).addElement(r25);
                this.t.addElement(r26);
                this.t.addElement(n);
                this.t.addElement(n2);
                this.t.addElement(n3);
            }
            else if (s.equals("self_i")) {
                final r r27 = new r(8, 8, 8, 2, this.r, 1.0f);
                final r r28 = new r(8, 39, 8, 44, this.r, 1.0f);
                final int[] array2 = { 90, 180 };
                final Point point4 = new Point(4, 9);
                final n n4 = new n(point4, new Point(point4.x + 10, point4.y + 10), this.r, 1.0f, array2);
                final Point point5 = new Point(4, 19);
                final n n5 = new n(point5, new Point(point5.x + 10, point5.y + 10), this.r, 1.0f, array2);
                final Point point6 = new Point(4, 29);
                final n n6 = new n(point6, new Point(point6.x + 10, point6.y + 10), this.r, 1.0f, array2);
                (this.t = new Vector()).addElement(r27);
                this.t.addElement(r28);
                this.t.addElement(n4);
                this.t.addElement(n5);
                this.t.addElement(n6);
            }
            else if (s.equals("diode")) {
                final r r29 = new r(15, 3, 15, 24, this.r, 1.0f);
                final r r30 = new r(16, 4, 28, 14, this.r, 1.0f);
                final r r31 = new r(27, 14, 16, 24, this.r, 1.0f);
                final r r32 = new r(28, 5, 28, 23, this.r, 1.0f);
                final r r33 = new r(28, 14, 38, 14, this.r, 1.0f);
                final r r34 = new r(15, 14, 3, 14, this.r, 1.0f);
                (this.t = new Vector()).addElement(r29);
                this.t.addElement(r30);
                this.t.addElement(r31);
                this.t.addElement(r32);
                this.t.addElement(r33);
                this.t.addElement(r34);
            }
            else if (s.equals("diode_i")) {
                final r r35 = new r(3, 13, 17, 13, this.r, 1.0f);
                final r r36 = new r(17, 13, 10, 27, this.r, 1.0f);
                final r r37 = new r(10, 27, 3, 13, this.r, 1.0f);
                final r r38 = new r(10, 13, 10, 3, this.r, 1.0f);
                final r r39 = new r(3, 27, 16, 27, this.r, 1.0f);
                final r r40 = new r(10, 27, 10, 35, this.r, 1.0f);
                (this.t = new Vector()).addElement(r35);
                this.t.addElement(r36);
                this.t.addElement(r37);
                this.t.addElement(r38);
                this.t.addElement(r39);
                this.t.addElement(r40);
            }
            else if (s.equals("mos")) {
                final r r41 = new r(3, 19, 12, 19, this.r, 1.0f);
                final r r42 = new r(13, 10, 13, 31, this.r, 1.0f);
                final r r43 = new r(17, 10, 17, 31, this.r, 1.0f);
                final r r44 = new r(17, 14, 25, 14, this.r, 1.0f);
                final r r45 = new r(25, 14, 25, 3, this.r, 1.0f);
                final r r46 = new r(17, 26, 25, 26, this.r, 1.0f);
                final r r47 = new r(25, 26, 25, 37, this.r, 1.0f);
                (this.t = new Vector()).addElement(r41);
                this.t.addElement(r42);
                this.t.addElement(r43);
                this.t.addElement(r44);
                this.t.addElement(r45);
                this.t.addElement(r46);
                this.t.addElement(r47);
            }
            else if (s.equals("mos_i")) {
                final r r48 = new r(16, 11, 16, 30, this.r, 1.0f);
                final r r49 = new r(12, 11, 12, 30, this.r, 1.0f);
                final r r50 = new r(12, 14, 5, 14, this.r, 1.0f);
                final r r51 = new r(4, 14, 4, 4, this.r, 1.0f);
                final r r52 = new r(12, 26, 4, 26, this.r, 1.0f);
                final r r53 = new r(4, 26, 4, 37, this.r, 1.0f);
                final r r54 = new r(16, 20, 27, 20, this.r, 1.0f);
                (this.t = new Vector()).addElement(r48);
                this.t.addElement(r49);
                this.t.addElement(r50);
                this.t.addElement(r51);
                this.t.addElement(r52);
                this.t.addElement(r53);
                this.t.addElement(r54);
            }
            else if (s.equals("alim")) {
                final r r55 = new r(1, 15, 24, 15, this.r, 1.0f);
                final r r56 = new r(8, 20, 16, 20, this.r, 1.0f);
                final r r57 = new r(12, 21, 12, 30, this.r, 1.0f);
                final r r58 = new r(12, 15, 12, 3, this.r, 1.0f);
                (this.t = new Vector()).addElement(r55);
                this.t.addElement(r56);
                this.t.addElement(r57);
                this.t.addElement(r58);
            }
            else if (s.equals("accu")) {
                final r r59 = new r(3, 13, 25, 13, this.r, 1.0f);
                final r r60 = new r(3, 24, 26, 24, this.r, 1.0f);
                final b b3 = new b(new Point(10, 17), new Point(19, 20), this.r, 1.0f, true);
                final b b4 = new b(new Point(10, 29), new Point(19, 32), this.r, 1.0f, true);
                final r r61 = new r(14, 31, 14, 39, this.r, 1.0f);
                final r r62 = new r(14, 13, 14, 3, this.r, 1.0f);
                (this.t = new Vector()).addElement(r59);
                this.t.addElement(r60);
                this.t.addElement(b3);
                this.t.addElement(b4);
                this.t.addElement(r61);
                this.t.addElement(r62);
            }
            else if (s.equals("masse")) {
                final r r63 = new r(9, 13, 27, 13, this.r, 1.0f);
                final r r64 = new r(10, 13, 5, 22, this.r, 1.0f);
                final r r65 = new r(16, 13, 11, 22, this.r, 1.0f);
                final r r66 = new r(21, 14, 17, 22, this.r, 1.0f);
                final r r67 = new r(27, 13, 22, 22, this.r, 1.0f);
                final r r68 = new r(18, 13, 18, 3, this.r, 1.0f);
                (this.t = new Vector()).addElement(r63);
                this.t.addElement(r64);
                this.t.addElement(r65);
                this.t.addElement(r66);
                this.t.addElement(r67);
                this.t.addElement(r68);
            }
            else if (s.equals("terre")) {
                final r r69 = new r(2, 15, 13, 15, this.r, 1.0f);
                final r r70 = new r(13, 15, 8, 27, this.r, 1.0f);
                final r r71 = new r(8, 27, 3, 15, this.r, 1.0f);
                final r r72 = new r(8, 15, 8, 3, this.r, 1.0f);
                (this.t = new Vector()).addElement(r69);
                this.t.addElement(r70);
                this.t.addElement(r71);
                this.t.addElement(r72);
            }
        }
        else if (this.q.equals("2")) {
            if (s.equals("resistance")) {
                final r r73 = new r(8, 3, 27, 3, this.r, 1.0f);
                final r r74 = new r(8, 3, 19, 15, this.r, 1.0f);
                final r r75 = new r(19, 15, 8, 29, this.r, 1.0f);
                final r r76 = new r(8, 29, 29, 29, this.r, 1.0f);
                (this.t = new Vector()).addElement(r73);
                this.t.addElement(r74);
                this.t.addElement(r75);
                this.t.addElement(r76);
            }
            else if (s.equals("resistance_i")) {
                final r r77 = new r(10, 6, 10, 18, this.r, 1.0f);
                final r r78 = new r(14, 6, 14, 18, this.r, 1.0f);
                final r r79 = new r(6, 7, 9, 5, this.r, 1.0f);
                final r r80 = new r(9, 5, 16, 6, this.r, 1.0f);
                final r r81 = new r(16, 6, 18, 5, this.r, 1.0f);
                (this.t = new Vector()).addElement(r77);
                this.t.addElement(r78);
                this.t.addElement(r79);
                this.t.addElement(r80);
                this.t.addElement(r81);
            }
            else if (s.equals("condo")) {
                final r r82 = new r(12, 9, 10, 24, this.r, 1.0f);
                final r r83 = new r(12, 10, 13, 5, this.r, 1.0f);
                final r r84 = new r(13, 5, 16, 3, this.r, 1.0f);
                final r r85 = new r(10, 24, 8, 31, this.r, 1.0f);
                final r r86 = new r(8, 31, 4, 33, this.r, 1.0f);
                (this.t = new Vector()).addElement(r82);
                this.t.addElement(r83);
                this.t.addElement(r84);
                this.t.addElement(r85);
                this.t.addElement(r86);
            }
            else if (s.equals("condo_i")) {
                final g g = new g(new Point(7, 15), new Point(12, 25), this.r, 1.0f, false);
                final r r87 = new r(12, 18, 11, 12, this.r, 1.0f);
                final r r88 = new r(11, 12, 6, 6, this.r, 1.0f);
                (this.t = new Vector()).addElement(g);
                this.t.addElement(r87);
                this.t.addElement(r88);
            }
            else if (s.equals("trans")) {
                final g g2 = new g(new Point(7, 7), new Point(16, 14), this.r, 1.0f, false);
                final r r89 = new r(13, 13, 23, 7, this.r, 1.0f);
                final r r90 = new r(14, 8, 22, 14, this.r, 1.0f);
                (this.t = new Vector()).addElement(g2);
                this.t.addElement(r89);
                this.t.addElement(r90);
            }
            else if (s.equals("trans_i")) {
                final Label label = new Label();
                label.setFont(new Font("SansSerif", 2, 12));
                label.setText("B");
                final k k = new k(label, false);
                k.a(0, 0, 7, 13);
                final r r91 = new r(7, 12, 6, 15, this.r, 1.0f);
                (this.t = new Vector()).addElement(k);
                this.t.addElement(r91);
            }
            else if (s.equals("self")) {
                final g g3 = new g(new Point(5, 5), new Point(14, 15), this.r, 1.0f, false);
                final r r92 = new r(6, 6, 7, 9, this.r, 1.0f);
                final r r93 = new r(8, 9, 11, 11, this.r, 1.0f);
                final r r94 = new r(11, 11, 18, 8, this.r, 1.0f);
                (this.t = new Vector()).addElement(g3);
                this.t.addElement(r92);
                this.t.addElement(r93);
                this.t.addElement(r94);
            }
            else if (s.equals("self_i")) {
                final g g4 = new g(new Point(6, 7), new Point(17, 14), this.r, 1.0f, false);
                final g g5 = new g(new Point(16, 7), new Point(27, 14), this.r, 1.0f, false);
                (this.t = new Vector()).addElement(g4);
                this.t.addElement(g5);
            }
        }
    }
}
