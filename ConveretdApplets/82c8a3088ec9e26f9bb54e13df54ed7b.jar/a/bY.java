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

public final class bY extends aF implements aA
{
    private static Color q;
    private static Color w;
    private static Color e;
    private int r;
    private r w;
    private A q;
    private int t;
    private int y;
    private int u;
    private int i;
    private int o;
    private FontMetrics q;
    private int p;
    
    private void q(final aJ aj) {
        if (aj != null) {
            int i = aj.w();
            final int f = aj.f;
            if (aj instanceof bv && i == 0) {
                i = 128;
            }
            if (aj instanceof l && ((l)aj).w) {
                i = Color.red.getRGB();
            }
            this.w.o = f;
            this.w.i = i;
        }
    }
    
    private ar q(final int n) {
        return (ar)super.q.t().w(n);
    }
    
    protected final synchronized void q(final r w, int n, final Graphics graphics) {
        try {
            this.p = 0;
            this.w = w;
            if (w.q != null) {
                this.q = ((W)w.q).e;
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
            final Font w2 = aS.w.w();
            final Font q = aS.w.q();
            if (w.p) {
                graphics.setColor(aS.w.g);
            }
            else if (w.s != 0) {
                graphics.setColor(new Color(w.s));
            }
            else if (w.e) {
                graphics.setColor(aS.w.d);
            }
            else if (w.r) {
                graphics.setColor(aS.w.f);
            }
            else if (w.q && super.q.y()) {
                graphics.setColor(aS.w.p);
            }
            else {
                graphics.setColor(aS.w.a);
            }
            if (this.q(w.t) != null && this.q(w.t).i != 0 && !w.e) {
                graphics.setColor(new Color(this.q(w.t).i));
            }
            if (graphics.getColor() != aS.w.a && super.w && super.q != null) {
                graphics.fillRect(0, n, size2.width, 2);
                graphics.fillRect(size2.width - 2 - this.q(), n, 2, w.q - 1);
                graphics.fillRect(0, n, 2, w.q - 1);
                graphics.fillRect(0, n + w.q - 1 - 2, size2.width, 2);
            }
            if (!super.w || super.q == null) {
                graphics.fillRect(0, n, size2.width, w.q - 1);
            }
            graphics.setColor(Color.white);
            graphics.drawLine(0, n + w.q - 1, size2.width, n + w.q - 1);
            if (w.q != null && n4 + 24 >= n3 && n4 <= n2) {
                graphics.drawImage(w.q, 6, n4, super.q);
            }
            this.q = this.getFontMetrics(w2);
            this.i = this.q.getHeight();
            n = n4 + this.q.getAscent() - 2;
            this.y = n + this.i;
            if (n >= n3 && n - this.i <= n2) {
                if (w.i == -1) {
                    if (w.e) {
                        this.q(this.q(w.t));
                    }
                    else if (w.y) {
                        this.q(this.q(w.t));
                    }
                    else if (w.q && !super.q.y()) {
                        this.q(this.q(w.t));
                    }
                    else if (w.w && !w.u) {
                        this.q(this.q(w.t));
                    }
                    else if (w.u) {
                        this.q(this.q(w.t));
                    }
                    else if (w.t != -999) {
                        this.q(this.q(w.t));
                    }
                }
                if (w.p > 999) {
                    final bt bt = (bt)super.q.q().w(w.p);
                    Image q2 = null;
                    if (bt != null) {
                        q2 = bt.q;
                    }
                    if (q2 != null) {
                        graphics.drawImage(q2, aF.q - 1, n - 11, null);
                    }
                }
                if (w.i) {
                    this.q((aJ)super.q.i().w(w.t));
                }
                graphics.setColor(new Color(w.i));
                if (w.o) {
                    graphics.setFont(new Font(q.getFamily(), q.getStyle() + 2, q.getSize()));
                }
                else {
                    graphics.setFont(q);
                }
                String s = w.w;
                if (w.p) {
                    s = cs.e;
                    if (w.a) {
                        s = s + " - " + ak.q("Warning");
                        graphics.setColor(bY.q);
                    }
                    else if (w.s) {
                        s = s + " - " + ak.q("Notice");
                        graphics.setColor(bY.w);
                    }
                    else if (w.d) {
                        s = s + " - " + ak.q("Info");
                        graphics.setColor(bY.e);
                    }
                }
                final int n5 = (w.p == 0) ? aF.q : (aF.q + 14);
                if (w.q instanceof ar && ((ar)w.q).q != null) {
                    graphics.drawImage(((ar)w.q).q, n5, n - 12, null);
                    final int height;
                    if ((height = ((ar)w.q).q.getHeight(null)) > 10) {
                        this.y += height - 10;
                    }
                }
                else {
                    graphics.drawString(s, n5, n);
                }
                graphics.setFont(q);
                if (w.e && super.w) {
                    ar ar;
                    if ((ar = (ar)super.q.t().w(w.t)) != null && ar.q(59) && ((bw)super.q.r().w(ar.r)).q(61)) {
                        final String q3 = ak.q("(Private message from Guest Speaker)");
                        graphics.setColor(aS.w.o);
                        graphics.drawString(q3, aF.q + this.q.stringWidth(w.w + "######"), n);
                    }
                    else if (w.y == -1 && !w.p) {
                        if (ar == null) {
                            ar = (ar)w.q;
                        }
                        if (super.q.y().w(ar.s) == null) {
                            final String q4 = ak.q("(Single click on this message to reply)");
                            graphics.setColor(aS.w.o);
                            graphics.drawString(q4, aF.q + this.q.stringWidth(w.w + "######"), n);
                        }
                    }
                }
                final aO ao;
                if (w.r && super.w && w.y == -1 && !w.p && (ao = (aO)super.q.u().w(w.d)) == null) {
                    final String q5 = ak.q(ce.q("S#9>7<5K3<93;K?>KD89CK=5CC175KD?K:?9>K3?>65B5>35T"));
                    graphics.setColor(aS.w.o);
                    graphics.drawString(q5, aF.q + this.q.stringWidth(w.w + "######"), n);
                }
            }
            if (super.q.t()) {
                graphics.setFont(aS.w.e());
                graphics.setColor(new Color(16125039));
                graphics.drawString(w.e, aF.q, this.y);
                this.y += this.getFontMetrics(q).getHeight();
            }
            graphics.setColor(aS.w.o);
            graphics.setFont(w2);
            int i = 0;
            for (int j = 0; j < w.w; ++j) {
                this.t = aF.q;
                int n6 = w.q[j];
                final int n7 = w.q[j + 1];
                this.u = this.q.q.q(w.q.substring(n6, n7));
                this.o = 0;
                if (this.u > 0 && 16 > this.q.getHeight()) {
                    this.o = 16 - this.q.getHeight();
                }
                final boolean b = this.y + 1 >= n3 && this.y - this.i - this.o <= n2;
                while (i < w.u) {
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
                            if (this.t == aF.q) {
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
                            this.q(b, graphics, s2, w.o, true, false);
                        }
                        int n9 = w.q(substring);
                        final String substring2 = w.q.substring(n, n8);
                        final int stringWidth = this.q.stringWidth(substring2);
                        if (n9 == -1) {
                            graphics.setColor(Color.blue);
                            graphics.drawLine(this.t, this.y + 1, this.t + stringWidth, this.y + 1);
                            n9 = Color.blue.getRGB();
                        }
                        if (w.e) {
                            graphics.setColor(aS.w.d);
                        }
                        else if (w.q && super.q.y()) {
                            graphics.setColor(aS.w.p);
                        }
                        else {
                            graphics.setColor(aS.w.a);
                        }
                        this.q(b, graphics, substring2, n9, true, true);
                        graphics.setColor(aS.w.o);
                    }
                    n6 = n8;
                }
                this.q(b, graphics, w.q.substring(n6, n7), w.o, w.i, false);
                this.y += this.i + this.o;
            }
        }
        catch (Exception ex2) {}
    }
    
    private void q(final boolean b, final Graphics graphics, String trim, final int n, final boolean b2, final boolean b3) {
        if (b && !"".equals(trim)) {
            if (this.t == aF.q) {
                trim = trim.trim();
            }
            if (this.u <= 0 || !cp.q(super.q.w(), 58)) {
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
                final Q q2;
                if (((q2 = this.q.q.q(trim.substring(q, index))) != null && q2.q != null && this.w.q != null && ((cs.q() && this.p < ((co)this.w.q).T) || (cs.w() && this.p < ((co)this.w.q).A))) || this.w.i) {
                    this.q(trim.substring(n2, q), graphics, this.t, n3, n, b2, b3);
                    this.t += this.q.stringWidth(trim.substring(n2, q) + " ");
                    graphics.drawImage(q2.q, this.t, n4, super.q);
                    this.t += 16 + this.q.charWidth(' ');
                    n2 = q + q2.o.length();
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
    
    public final synchronized void q(final r r, final int n, final FontMetrics fontMetrics) {
        try {
            final int index;
            if (r.p && (index = r.q.indexOf(" > ")) >= 0) {
                r.q = r.q.substring(0, index) + "\n" + r.q.substring(index + " > ".length());
                r.q();
                r.w();
            }
            final int stringWidth = fontMetrics.stringWidth(r.q);
            final int n2 = this.getSize().width - 65;
            if (r.q != null && r.q != null && n2 > 0 && stringWidth > n2 * r.a && !r.p) {
                String string = "";
                for (int i = 0; i < r.q.length(); ++i) {
                    string += r.q.charAt(i);
                    if (fontMetrics.stringWidth(string) > n2 * r.a) {
                        r.q = string.substring(0, string.length() - 1);
                        r.q();
                        r.w();
                        break;
                    }
                }
            }
            if (r.i) {
                r.q();
                r.w();
            }
            final String s = new String(r.q);
            int n3 = 0;
            r.q[0] = 0;
            r.w = 0;
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n");
            final Vector vector = new Vector<String>();
            int n4 = 0;
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (r.q == null || (r.q != null && n4++ < r.a) || r.p) {
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
                            if (cp.q(super.q.w(), 58)) {
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
                for (int n12 = 1; n12 <= n5 && r.w < 30; ++n12) {
                    r.q[++r.w] = array2[n12] + n3;
                }
                n3 += array2[n5];
            }
            r.q = "";
            for (int l = 0; l < array.length; ++l) {
                r.q += array[l];
            }
            int n13 = 0;
            if (16 > fontMetrics.getHeight()) {
                for (int n14 = 0; n14 < r.w; ++n14) {
                    if (this.q.q.q(r.q.substring(r.q[n14], r.q[n14 + 1])) > 0) {
                        n13 += 16 - fontMetrics.getHeight();
                    }
                }
            }
            this.getFontMetrics(aS.w.e());
            final int height;
            if (r.q != null && r.q instanceof ar && ((ar)r.q).q != null && (height = ((ar)r.q).q.getHeight(null)) > 12) {
                n13 += height - 12;
            }
            r.q = 6 + fontMetrics.getHeight() * (r.w + 1) + n13;
        }
        catch (Exception ex) {}
    }
    
    private void q(final String s, final Graphics graphics, final int n, final int n2, final int n3, final boolean b, final boolean b2) {
        if (b || (cs.t && ((this.w.q != null && !this.w.q.q(61)) || this.w.q == null)) || (cs.y && ((this.w.q != null && this.w.q.q(61)) || this.w.q == null))) {
            graphics.setColor(new Color((n3 != 0) ? n3 : this.w.o));
        }
        if ((cs.q && this.w.q != null && !this.w.q.q(61)) || (cs.w && this.w.q != null && this.w.q.q(61)) || b2) {
            if (cs.q == 1) {
                this.q(this.q(s, graphics, n, n2), graphics, n + this.r, n2);
                return;
            }
            this.q(s, graphics, n, n2);
        }
        else {
            if (cs.q == 1) {
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
                final ar ar;
                if ((ar = (ar)this.q.q(i)) != null && ar.o != null && substring.length() > ar.o.length() && substring.substring(0, ar.o.length()).equals(ar.o) && ar.s == this.w.q.q()) {
                    this.r = q(graphics, ar.o, font2, fontMetrics, n, n2);
                    substring = substring.substring(ar.o.length());
                    break;
                }
            }
        }
        graphics.setFont(font);
        return substring;
    }
    
    private void q(String s, final Graphics graphics, int n, final int n2) {
        final aK ak = new aK(graphics.getFont());
        final FontMetrics fontMetrics = this.getFontMetrics(ak.r());
        final FontMetrics fontMetrics2 = this.getFontMetrics(ak.q());
        final FontMetrics fontMetrics3 = this.getFontMetrics(ak.w());
        final FontMetrics fontMetrics4 = this.getFontMetrics(ak.e());
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
                            n += q(graphics, s.substring(q(n9, n10), j), ak.r(), fontMetrics, n, n2);
                        }
                        else if (n11 != 0 && j + 3 < s.length()) {
                            s = s.substring(0, j) + s.substring(j + 3);
                        }
                        else {
                            n += q(graphics, s.substring(n8 + 3, j), ak.w(), fontMetrics3, n, n2);
                        }
                        n7 = j;
                        n11 = 1;
                        j += 2;
                    }
                    else if (j + 3 < s.length() && s.charAt(j + 1) == '/' && s.charAt(j + 2) == 'i' && s.charAt(j + 3) == ']') {
                        if (n11 != 0 && n12 == 0) {
                            n += q(graphics, s.substring(q(n7 + 3, n10), j), ak.q(), fontMetrics2, n, n2);
                            n11 = 0;
                        }
                        else if (n11 != 0 && n12 != 0) {
                            n += q(graphics, s.substring(q(n7, n8) + 3, j), ak.e(), fontMetrics4, n, n2);
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
                            n += q(graphics, s.substring(q(n9, n10), j), ak.r(), fontMetrics, n, n2);
                        }
                        else if (n12 != 0 && j + 3 < s.length()) {
                            s = s.substring(0, j) + s.substring(j + 3);
                        }
                        else {
                            n += q(graphics, s.substring(n7 + 3, j), ak.q(), fontMetrics2, n, n2);
                        }
                        n8 = j;
                        n12 = 1;
                        j += 2;
                    }
                    else if (j + 3 < s.length() && s.charAt(j + 1) == '/' && s.charAt(j + 2) == 'b' && s.charAt(j + 3) == ']') {
                        if (n12 != 0 && n11 == 0) {
                            n += q(graphics, s.substring(q(n8 + 3, n9), j), ak.w(), fontMetrics3, n, n2);
                            n12 = 0;
                        }
                        else if (n11 != 0 && n12 != 0) {
                            n += q(graphics, s.substring(q(n7, n8) + 3, j), ak.e(), fontMetrics4, n, n2);
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
                q(graphics, substring, ak.r(), fontMetrics, n, n2);
            }
        }
        else {
            q(graphics, s, ak.r(), fontMetrics, n, n2);
        }
        graphics.setFont(ak.r());
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
            return ak.q("Chat messages are displayed here.  Single-click on a private message to reply.");
        }
        return ak.q("Private messages are displayed here.");
    }
    
    public bY(final co co, final boolean b) {
        super(co, b);
        this.r = 0;
        this.w = null;
        this.q = null;
        this.p = 0;
        aF.q = 36;
    }
    
    static {
        bY.q = new Color(130, 15, 15);
        bY.w = new Color(240, 130, 40);
        bY.e = new Color(0, 140, 0);
    }
}
