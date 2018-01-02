// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Vector;
import java.util.StringTokenizer;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Color;

public final class dl extends bk implements bf
{
    private static Color q;
    private static Color w;
    private static Color e;
    private int r;
    private A w;
    private M q;
    private int t;
    private int y;
    private int u;
    private int i;
    private int o;
    private FontMetrics q;
    private int p;
    
    private void q(final bp bp) {
        if (bp != null) {
            int o = bp.w();
            final int f = bp.f;
            if (bp instanceof cz && o == 0) {
                o = 128;
            }
            if (bp instanceof p && ((p)bp).w) {
                o = Color.red.getRGB();
            }
            this.w.p = f;
            this.w.o = o;
        }
    }
    
    private aO q(final int n) {
        return (aO)super.q.r().w(n);
    }
    
    protected final synchronized void q(final A w, int n, final Graphics graphics) {
        try {
            this.p = 0;
            this.w = w;
            if (w.q != null) {
                this.q = ((ap)w.q).a;
            }
            Rectangle clipRect;
            if ((clipRect = graphics.getClipRect()) == null) {
                final Dimension size = this.size();
                clipRect = new Rectangle(0, 0, size.width, size.height);
            }
            final int n3;
            final int n2 = (n3 = clipRect.y - 1) + clipRect.height + 2;
            final Dimension size2 = this.size();
            final int n4 = n + 3 + 1;
            final Font w2 = bC.w.w();
            final Font q = bC.w.q();
            if (w.s) {
                graphics.setColor(bC.w.g);
            }
            else if (w.d != 0) {
                graphics.setColor(new Color(w.d));
            }
            else if (w.r) {
                graphics.setColor(bC.w.d);
            }
            else if (w.t) {
                graphics.setColor(bC.w.f);
            }
            else if (w.w && super.q.u()) {
                graphics.setColor(bC.w.p);
            }
            else if (w.o) {
                graphics.setColor(bC.w.p);
            }
            else {
                graphics.setColor(bC.w.a);
            }
            if (this.q(w.y) != null && this.q(w.y).i != 0 && !w.r) {
                graphics.setColor(new Color(this.q(w.y).i));
            }
            if (graphics.getColor() != bC.w.a && super.w && super.q != null) {
                graphics.fillRect(0, n, size2.width, 2);
                graphics.fillRect(size2.width - 2 - this.q(), n, 2, w.w - 1);
                graphics.fillRect(0, n, 2, w.w - 1);
                graphics.fillRect(0, n + w.w - 1 - 2, size2.width, 2);
            }
            if (!super.w || super.q == null) {
                graphics.fillRect(0, n, size2.width, w.w - 1);
            }
            graphics.setColor(Color.white);
            graphics.drawLine(0, n + w.w - 1, size2.width, n + w.w - 1);
            if (w.q != null && n4 + 24 >= n3 && n4 <= n2) {
                graphics.drawImage(w.q, 6, n4, super.q);
            }
            this.q = this.getFontMetrics(w2);
            this.i = this.q.getHeight();
            n = n4 + this.q.getAscent() - 2;
            this.y = n + this.i;
            if (n >= n3 && n - this.i <= n2) {
                if (w.o == -1) {
                    if (w.r) {
                        this.q(this.q(w.y));
                    }
                    else if (w.u) {
                        this.q(this.q(w.y));
                    }
                    else if (w.w && !super.q.u()) {
                        this.q(this.q(w.y));
                    }
                    else if (w.e && !w.i) {
                        this.q(this.q(w.y));
                    }
                    else if (w.i) {
                        this.q(this.q(w.y));
                    }
                    else if (w.y != -999) {
                        this.q(this.q(w.y));
                    }
                }
                if (w.a > 999) {
                    final cx cx = (cx)super.q.q().w(w.a);
                    Image q2 = null;
                    if (cx != null) {
                        q2 = cx.q;
                    }
                    if (q2 != null) {
                        graphics.drawImage(q2, bk.q - 1, n - 11, null);
                    }
                }
                if (w.p) {
                    this.q((bp)super.q.o().w(w.y));
                }
                graphics.setColor(new Color(w.o));
                if (w.a) {
                    graphics.setFont(new Font(q.getFamily(), q.getStyle() + 2, q.getSize()));
                }
                else {
                    graphics.setFont(q);
                }
                String s = w.w;
                if (w.s) {
                    s = dN.e;
                    if (w.d) {
                        s = s + " - " + be.w("Warning");
                        graphics.setColor(dl.q);
                    }
                    else if (w.f) {
                        s = s + " - " + be.w("Notice");
                        graphics.setColor(dl.w);
                    }
                    else if (w.g) {
                        s = s + " - " + be.w("Info");
                        graphics.setColor(dl.e);
                    }
                }
                final int n5 = (w.a == 0) ? bk.q : (bk.q + 14);
                if (w.q instanceof aO && ((aO)w.q).q != null) {
                    graphics.drawImage(((aO)w.q).q, n5, n - 12, null);
                    final int height;
                    if ((height = ((aO)w.q).q.getHeight(null)) > 10) {
                        this.y += height - 10;
                    }
                }
                else {
                    graphics.drawString(s, n5, n);
                }
                graphics.setFont(q);
                if (w.r && super.w) {
                    aO ao;
                    if ((ao = (aO)super.q.r().w(w.y)) != null && ao.q(59) && ((cB)super.q.y().w(ao.r)).q(61)) {
                        final String w3 = be.w("(Private message from Guest Speaker)");
                        graphics.setColor(bC.w.o);
                        graphics.drawString(w3, bk.q + this.q.stringWidth(w.w + "######"), n);
                    }
                    else if (w.u == -1 && !w.s) {
                        if (ao == null) {
                            ao = (aO)w.q;
                        }
                        if (super.q.u().w(ao.s) == null) {
                            final String w4 = be.w("(Single click on this message to reply)");
                            graphics.setColor(bC.w.o);
                            graphics.drawString(w4, bk.q + this.q.stringWidth(w.w + "######"), n);
                        }
                    }
                }
                final bz bz;
                if (w.t && super.w && w.u == -1 && !w.s && (bz = (bz)super.q.i().w(w.g)) == null) {
                    final String w5 = be.w(ds.q("S#9>7<5K3<93;K?>KD89CK=5CC175KD?K:?9>K3?>65B5>35T"));
                    graphics.setColor(bC.w.o);
                    graphics.drawString(w5, bk.q + this.q.stringWidth(w.w + "######"), n);
                }
            }
            if (super.q.y() || w.q) {
                graphics.setFont(bC.w.e());
                graphics.setColor(new Color(16125039));
                graphics.drawString(w.e, bk.q, this.y);
                this.y += this.getFontMetrics(q).getHeight();
            }
            graphics.setColor(bC.w.o);
            graphics.setFont(w2);
            int i = 0;
            for (int j = 0; j < w.e; ++j) {
                this.t = bk.q;
                int n6 = w.q[j];
                final int n7 = w.q[j + 1];
                this.u = this.q.q.q(w.q.substring(n6, n7));
                this.o = 0;
                if (this.u > 0 && 16 > this.q.getHeight()) {
                    this.o = 16 - this.q.getHeight();
                }
                final boolean b = this.y + 1 >= n3 && this.y - this.i - this.o <= n2;
                while (i < w.i) {
                    n = w.e[i * 2];
                    int n8 = w.e[i * 2 + 1];
                    final String substring = w.q.substring(n, n8);
                    if (n8 > n7) {
                        n8 = n7;
                    }
                    else {
                        ++i;
                    }
                    if (n < n6) {
                        n = n6;
                    }
                    if (n6 >= n8 || n7 <= n) {
                        break;
                    }
                    if (b) {
                        if (n != n6) {
                            if (this.t == bk.q) {
                                while (n6 < n7 && w.q.charAt(n6) == ' ') {
                                    ++n6;
                                }
                            }
                            String s2;
                            try {
                                s2 = w.q.substring(n6, n);
                            }
                            catch (StringIndexOutOfBoundsException ex) {
                                s2 = w.q.substring(n6, n6);
                            }
                            this.q(b, graphics, s2, w.p, true, false);
                        }
                        int n9 = w.q(substring);
                        final String substring2 = w.q.substring(n, n8);
                        final int stringWidth = this.q.stringWidth(substring2);
                        if (n9 == -1) {
                            graphics.setColor(Color.blue);
                            graphics.drawLine(this.t, this.y + 1, this.t + stringWidth, this.y + 1);
                            n9 = Color.blue.getRGB();
                        }
                        if (w.r) {
                            graphics.setColor(bC.w.d);
                        }
                        else if (w.w && super.q.u()) {
                            graphics.setColor(bC.w.p);
                        }
                        else {
                            graphics.setColor(bC.w.a);
                        }
                        this.q(b, graphics, substring2, n9, true, true);
                        graphics.setColor(bC.w.o);
                    }
                    n6 = n8;
                }
                this.q(b, graphics, w.q.substring(n6, n7), w.p, w.p, false);
                this.y += this.i + this.o;
            }
        }
        catch (Exception ex2) {}
    }
    
    private void q(final boolean b, final Graphics graphics, String trim, final int n, final boolean b2, final boolean b3) {
        if (b && !"".equals(trim)) {
            if (this.t == bk.q) {
                trim = trim.trim();
            }
            if (this.u <= 0 || !dI.q(super.q.q(), 58)) {
                this.q(trim, graphics, this.t, this.y, n, b2, b3);
                this.t += this.q.stringWidth(trim + " ");
                return;
            }
            int n2 = 0;
            final int length = trim.length();
            int q = -1;
            final int n3 = this.y + (this.i + this.o - this.q.getHeight()) / 2;
            final int n4 = this.y - this.q.getAscent() + (this.i + this.o - 16) / 2;
            for (int n5 = 0; n5 < this.u && (q = this.q.q.q(trim, ++q)) != -1 && q < length; ++n5) {
                int index;
                if ((index = trim.indexOf(32, q + 1)) == -1) {
                    index = length;
                }
                final aj q2;
                if (((q2 = this.q.q.q(trim.substring(q, index))) != null && q2.q != null && this.w.q != null && ((dN.q() && this.p < ((dH)this.w.q).C) || (dN.w() && this.p < ((dH)this.w.q).ab))) || this.w.p) {
                    this.q(trim.substring(n2, q), graphics, this.t, n3, n, b2, b3);
                    this.t += this.q.stringWidth(trim.substring(n2, q) + " ");
                    graphics.drawImage(q2.q, this.t, n4, super.q);
                    this.t += 16 + this.q.charWidth(' ');
                    n2 = q + q2.a.length();
                    ++this.p;
                }
                else {
                    this.q(trim.substring(n2, index), graphics, this.t, n3, n, b2, b3);
                    this.t += this.q.stringWidth(trim.substring(n2, index));
                    n2 = index;
                }
            }
            if (n2 != length) {
                this.q(trim.substring(n2, length), graphics, this.t, n3, n, b2, b3);
                this.t += this.q.stringWidth(trim.substring(n2, length) + " ");
            }
        }
    }
    
    public final synchronized void q(final A a, final int n, final FontMetrics fontMetrics) {
        try {
            final int index;
            if (a.s && (index = a.q.indexOf(" > ")) >= 0) {
                a.q = a.q.substring(0, index) + "\n" + a.q.substring(index + " > ".length());
                a.q();
                a.w();
            }
            final int stringWidth = fontMetrics.stringWidth(a.q);
            final int n2 = this.getSize().width - 65;
            if (a.q != null && a.q != null && n2 > 0 && stringWidth > n2 * a.s && !a.s) {
                String string = "";
                for (int i = 0; i < a.q.length(); ++i) {
                    string += a.q.charAt(i);
                    if (fontMetrics.stringWidth(string) > n2 * a.s) {
                        a.q = string.substring(0, string.length() - 1);
                        a.q();
                        a.w();
                        break;
                    }
                }
            }
            if (a.p) {
                a.q();
                a.w();
            }
            final String s = new String(a.q);
            int n3 = 0;
            a.q[0] = 0;
            a.e = 0;
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n");
            final Vector vector = new Vector<String>();
            int n4 = 0;
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (a.q == null || (a.q != null && n4++ < a.s) || a.s) {
                    vector.addElement(nextToken);
                }
            }
            final String[] array = new String[vector.size()];
            vector.copyInto(array);
            for (int j = 0; j < array.length; ++j) {
                final int[] array2;
                (array2 = new int[31])[0] = 0;
                int n5 = 0;
                if (j != array.length - 1) {
                    final StringBuffer sb = new StringBuffer();
                    final String[] array3 = array;
                    final int n6 = j;
                    array3[n6] = sb.append(array3[n6]).append("\n").toString();
                }
                int n7 = 0;
                final int length = array[j].length();
                while (true) {
                    if (n7 < length && array[j].charAt(n7) == ' ') {
                        ++n7;
                    }
                    else {
                        int n8 = n7;
                        int n9;
                        int k = n9 = length;
                        while (k > n8) {
                            int n10 = 0;
                            if (dI.q(super.q.q(), 58)) {
                                n10 = (16 + 2 * fontMetrics.charWidth(' ')) * this.q.q.q(array[j].substring(n7, n9)) - this.q.q.q(array[j].substring(n7, n9), fontMetrics);
                            }
                            final int n11;
                            if ((n11 = fontMetrics.stringWidth(array[j].substring(n7, n9)) + n10) <= n && n9 == length) {
                                break;
                            }
                            if (n11 < n) {
                                n8 = n9 + 1;
                            }
                            else {
                                k = n9;
                            }
                            n9 = (n8 + k) / 2;
                        }
                        final int lastIndex;
                        if ((lastIndex = array[j].lastIndexOf(32, n9)) < n7) {
                            n7 = n9;
                        }
                        else {
                            n7 = lastIndex;
                        }
                        array2[++n5] = n7;
                        if (n5 >= 30 || k >= length) {
                            break;
                        }
                        continue;
                    }
                }
                array2[n5] = length;
                for (int n12 = 1; n12 <= n5 && a.e < 30; ++n12) {
                    a.q[++a.e] = array2[n12] + n3;
                }
                n3 += array2[n5];
            }
            a.q = "";
            for (int l = 0; l < array.length; ++l) {
                a.q += array[l];
            }
            int n13 = 0;
            if (16 > fontMetrics.getHeight()) {
                for (int n14 = 0; n14 < a.e; ++n14) {
                    if (this.q.q.q(a.q.substring(a.q[n14], a.q[n14 + 1])) > 0) {
                        n13 += 16 - fontMetrics.getHeight();
                    }
                }
            }
            final FontMetrics fontMetrics2 = this.getFontMetrics(bC.w.e());
            final int height;
            if (a.q != null && a.q instanceof aO && ((aO)a.q).q != null && (height = ((aO)a.q).q.getHeight(null)) > 12) {
                n13 += height - 12;
            }
            a.w = 6 + fontMetrics.getHeight() * (a.e + 1) + n13 + (a.q ? fontMetrics2.getHeight() : 0);
        }
        catch (Exception ex) {}
    }
    
    private void q(final String s, final Graphics graphics, final int n, final int n2, final int n3, final boolean b, final boolean b2) {
        if (b || (dN.t && ((this.w.q != null && !this.w.q.q(61)) || this.w.q == null)) || (dN.y && ((this.w.q != null && this.w.q.q(61)) || this.w.q == null))) {
            graphics.setColor(new Color((n3 != 0) ? n3 : this.w.p));
        }
        if ((dN.q && this.w.q != null && !this.w.q.q(61)) || (dN.w && this.w.q != null && this.w.q.q(61)) || b2) {
            if (dN.q == 1) {
                this.q(this.q(s, graphics, n, n2), graphics, n + this.r, n2);
                return;
            }
            this.q(s, graphics, n, n2);
        }
        else {
            if (dN.q == 1) {
                graphics.drawString(this.q(s, graphics, n, n2), n + this.r, n2);
                return;
            }
            graphics.drawString(s, n, n2);
        }
    }
    
    private String q(String substring, final Graphics graphics, final int n, final int n2) {
        this.r = 0;
        final Font font = graphics.getFont();
        final Font font2 = new Font(font.getFamily(), 1, font.getSize());
        final FontMetrics fontMetrics = this.getFontMetrics(font2);
        if (this.q != null && this.w.q != null) {
            for (int i = 0; i < this.q.q; ++i) {
                final aO ao;
                if ((ao = (aO)this.q.q(i)) != null && ao.a != null && substring.length() > ao.a.length() && substring.substring(0, ao.a.length()).equals(ao.a) && ao.s == this.w.q.q()) {
                    this.r = q(graphics, ao.a, font2, fontMetrics, n, n2);
                    substring = substring.substring(ao.a.length());
                    break;
                }
            }
        }
        graphics.setFont(font);
        return substring;
    }
    
    private void q(String s, final Graphics graphics, int n, final int n2) {
        final bu bu = new bu(graphics.getFont());
        final FontMetrics fontMetrics = this.getFontMetrics(bu.r());
        final FontMetrics fontMetrics2 = this.getFontMetrics(bu.q());
        final FontMetrics fontMetrics3 = this.getFontMetrics(bu.w());
        final FontMetrics fontMetrics4 = this.getFontMetrics(bu.e());
        s = s;
        int n3 = 0;
        int n4 = 0;
        for (int n5 = s.indexOf(91), n6 = 0; n6 < s.length() && n5 >= 0 && n5 + 2 < s.length(); n5 = s.substring(++n5).indexOf(91), ++n6) {
            if (Character.isSpaceChar(s.charAt(n5 + 1)) && s.charAt(n5 + 2) == '/') {
                s = s.substring(0, n5 + 1) + s.substring(n5 + 2);
            }
        }
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '[' && i + 2 < s.length()) {
                if (s.charAt(i + 1) == 'i' && s.charAt(i + 2) == ']') {
                    if (n3 != 0) {
                        s = s.substring(0, i) + s.substring(i + 3);
                        i = 0;
                    }
                    n3 = 1;
                }
                else if (s.charAt(i + 1) == '/' && s.charAt(i + 2) == 'i' && s.charAt(i + 3) == ']') {
                    if (n3 == 0 && i + 3 < s.length()) {
                        s = s.substring(0, i) + s.substring(i + 4);
                        i = 0;
                    }
                    n3 = 0;
                }
                else if (s.charAt(i + 1) == 'b' && s.charAt(i + 2) == ']') {
                    if (n4 != 0) {
                        s = s.substring(0, i) + s.substring(i + 3);
                        i = 0;
                    }
                    n4 = 1;
                }
                else if (s.charAt(i + 1) == '/' && s.charAt(i + 2) == 'b' && s.charAt(i + 3) == ']') {
                    if (n4 == 0 && i + 3 < s.length()) {
                        s = s.substring(0, i) + s.substring(i + 4);
                        i = 0;
                    }
                    n4 = 0;
                }
            }
        }
        s = s;
        String substring = "";
        int n7 = -1;
        int n8 = -1;
        int n9 = -1;
        int n10 = -1;
        int n11 = 0;
        int n12 = 0;
        if (s.indexOf("i]") >= 0 || s.indexOf("b]") >= 0) {
            for (int j = 0; j < s.length(); ++j) {
                if (s.charAt(j) == '[' && j + 2 < s.length()) {
                    if (s.charAt(j + 1) == 'i' && s.charAt(j + 2) == ']') {
                        if (n12 == 0) {
                            n += q(graphics, s.substring(q(n9, n10), j), bu.r(), fontMetrics, n, n2);
                        }
                        else if (n11 != 0 && j + 3 < s.length()) {
                            s = s.substring(0, j) + s.substring(j + 3);
                        }
                        else {
                            n += q(graphics, s.substring(n8 + 3, j), bu.w(), fontMetrics3, n, n2);
                        }
                        n7 = j;
                        n11 = 1;
                        j += 2;
                    }
                    else if (j + 3 < s.length() && s.charAt(j + 1) == '/' && s.charAt(j + 2) == 'i' && s.charAt(j + 3) == ']') {
                        if (n11 != 0 && n12 == 0) {
                            n += q(graphics, s.substring(q(n7 + 3, n10), j), bu.q(), fontMetrics2, n, n2);
                            n11 = 0;
                        }
                        else if (n11 != 0 && n12 != 0) {
                            n += q(graphics, s.substring(q(n7, n8) + 3, j), bu.e(), fontMetrics4, n, n2);
                            n11 = 0;
                        }
                        else if (j + 4 < s.length()) {
                            s = s.substring(0, j) + s.substring(j + 4);
                        }
                        j += 3;
                        n9 = j + 1;
                    }
                    else if (s.charAt(j + 1) == 'b' && s.charAt(j + 2) == ']') {
                        if (n11 == 0) {
                            n += q(graphics, s.substring(q(n9, n10), j), bu.r(), fontMetrics, n, n2);
                        }
                        else if (n12 != 0 && j + 3 < s.length()) {
                            s = s.substring(0, j) + s.substring(j + 3);
                        }
                        else {
                            n += q(graphics, s.substring(n7 + 3, j), bu.q(), fontMetrics2, n, n2);
                        }
                        n8 = j;
                        n12 = 1;
                        j += 2;
                    }
                    else if (j + 3 < s.length() && s.charAt(j + 1) == '/' && s.charAt(j + 2) == 'b' && s.charAt(j + 3) == ']') {
                        if (n12 != 0 && n11 == 0) {
                            n += q(graphics, s.substring(q(n8 + 3, n9), j), bu.w(), fontMetrics3, n, n2);
                            n12 = 0;
                        }
                        else if (n11 != 0 && n12 != 0) {
                            n += q(graphics, s.substring(q(n7, n8) + 3, j), bu.e(), fontMetrics4, n, n2);
                            n12 = 0;
                        }
                        else if (j + 4 < s.length()) {
                            s = s.substring(0, j) + s.substring(j + 4);
                        }
                        j += 3;
                        n10 = j + 1;
                    }
                    if (j + 1 > s.length()) {
                        break;
                    }
                    substring = s.substring(j + 1);
                }
            }
            if (substring.length() > 0) {
                q(graphics, substring, bu.r(), fontMetrics, n, n2);
            }
        }
        else {
            q(graphics, s, bu.r(), fontMetrics, n, n2);
        }
        graphics.setFont(bu.r());
    }
    
    private static int q(final int n, final int n2) {
        if (n > n2) {
            return n;
        }
        if (n2 > n) {
            return n2;
        }
        if (n == -1) {
            return 0;
        }
        return n;
    }
    
    private static int q(final Graphics graphics, final String s, final Font font, final FontMetrics fontMetrics, final int n, final int n2) {
        graphics.setFont(font);
        graphics.drawString(s, n, n2);
        return fontMetrics.stringWidth(s);
    }
    
    public final String q(final Object o) {
        if (super.w) {
            return be.w("Chat messages are displayed here.  Single-click on a private message to reply.");
        }
        return be.w("Private messages are displayed here.");
    }
    
    public dl(final dH dh, final boolean b) {
        super(dh, b);
        this.r = 0;
        this.w = null;
        this.q = null;
        this.p = 0;
        bk.q = 36;
    }
    
    static {
        dl.q = new Color(130, 15, 15);
        dl.w = new Color(240, 130, 40);
        dl.e = new Color(0, 140, 0);
    }
}
