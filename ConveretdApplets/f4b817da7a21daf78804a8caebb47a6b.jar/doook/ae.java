// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.util.Hashtable;
import java.util.Vector;
import java.util.StringTokenizer;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;

public final class ae extends bl implements aj
{
    protected final synchronized void a(final aN an, final int n, final Graphics graphics) {
        try {
            if (an.z == -1) {
                if (an.p) {
                    an.z = ((aq)super.h.d.b(an.d)).ag;
                }
                else if (an.z) {
                    an.z = ((aq)super.h.d.b(an.d)).ag;
                }
                else if (an.l && !super.h.Q) {
                    an.z = 2;
                }
                else if (an.y && !an.A) {
                    an.z = ((aq)super.h.d.b(an.d)).ag;
                }
                else if (an.A) {
                    an.z = ((aq)super.h.d.b(an.d)).ag;
                }
                else if (an.d != -999) {
                    an.z = ((aq)super.h.d.b(an.d)).ag;
                }
                else {
                    an.z = an.A;
                }
                if (an.d != -999 && super.h.d.b(an.d) != null) {
                    an.y = ((aq)super.h.d.b(an.d)).Q;
                    if (((aq)super.h.d.b(an.d)).c(18)) {
                        an.B = true;
                    }
                    if (((aq)super.h.d.b(an.d)).c(36)) {
                        an.C = true;
                    }
                }
            }
            Rectangle clipRect = graphics.getClipRect();
            if (clipRect == null) {
                final Dimension size = this.size();
                clipRect = new Rectangle(0, 0, size.width, size.height);
            }
            final int n2 = clipRect.y - 1;
            final int n3 = n2 + clipRect.height + 2;
            final Dimension size2 = this.size();
            final int n4 = 3 + n + 1;
            final Font b = super.h.c.b();
            final Font a = super.h.c.a();
            if (an.x != -1) {
                graphics.setColor(new Color(an.x));
            }
            else if (!an.F && (!an.p || !super.k)) {
                graphics.setColor(super.h.c.q);
            }
            else if (an.p) {
                graphics.setColor(super.h.c.f);
            }
            else if (an.l && super.h.Q) {
                graphics.setColor(super.h.c.m);
            }
            else if (an.o) {
                graphics.setColor(super.h.c.m);
            }
            else {
                graphics.setColor(super.h.c.n);
            }
            if ((graphics.getColor() != super.h.c.n || graphics.getColor() != super.h.c.q) && super.k && super.a != null) {
                graphics.fillRect(0, n, size2.width, 2);
                graphics.fillRect(size2.width - 2 - this.i(), n, 2, an.f - 1);
                graphics.fillRect(0, n, 2, an.f - 1);
                graphics.fillRect(0, n + an.f - 1 - 2, size2.width, 2);
            }
            if (!super.k || super.a == null) {
                graphics.fillRect(0, n, size2.width, an.f - 1);
            }
            graphics.setColor(Color.white);
            graphics.drawLine(0, n + an.f - 1, size2.width, n + an.f - 1);
            if (an.d != null && n4 + 24 >= n2 && n4 <= n3) {
                graphics.drawImage(an.d, 6, n4, super.a);
            }
            final FontMetrics fontMetrics = this.getFontMetrics(b);
            final int height = fontMetrics.getHeight();
            final int n5 = n4 + fontMetrics.getAscent() - 2;
            int n6 = n5 + height;
            if (n5 >= n2 && n5 - height <= n3) {
                if (an.y != null && an.y.length() != 0 && bq.h.containsKey(an.y)) {
                    graphics.drawImage(bq.h.get(an.y), bl.e - 1, n5 - 11, super.a);
                }
                graphics.setColor(new Color(an.z));
                graphics.setFont(a);
                if (an.z != null && an.z.length() > 0 && bq.g.containsKey(an.z)) {
                    graphics.drawImage(bq.g.get(an.z), (an.y != null && an.y.length() != 0) ? (bl.e + 14) : bl.e, n5 - 11, super.a);
                    n6 += 14;
                }
                else {
                    graphics.drawString(an.e, (an.y != null && an.y.length() != 0) ? (bl.e + 14) : bl.e, n5);
                }
                int stringWidth = 0;
                if (super.h.P) {
                    graphics.setFont(super.h.c.c());
                    graphics.drawString(an.f, bl.e + this.getFontMetrics(a).stringWidth(an.e + "##"), n5);
                    stringWidth = this.getFontMetrics(super.h.c.c()).stringWidth(an.f);
                }
                if (an.p && super.k) {
                    final aq aq = (aq)super.h.d.b(an.d);
                    if (aq != null && aq.c(59) && ((a)super.h.e.b(aq.w)).a()) {
                        final String a2 = aG.a("(Private message from Guest Speaker)");
                        graphics.setColor(super.h.c.l);
                        graphics.drawString(a2, bl.e + stringWidth + fontMetrics.stringWidth(an.e + "######"), n5);
                    }
                    else if (an.g == -1) {
                        final String a3 = aG.a("(Single click on this message to reply)");
                        graphics.setColor(super.h.c.l);
                        graphics.drawString(a3, bl.e + stringWidth + fontMetrics.stringWidth(an.e + "######"), n5);
                    }
                }
            }
            if (an.B != -1) {
                graphics.setColor(new Color(an.B));
            }
            else if (an.p) {
                graphics.setColor(super.h.c.o);
            }
            else if (!an.F && (!an.p || !super.k)) {
                graphics.setColor(super.h.c.p);
            }
            else {
                graphics.setColor(super.h.c.l);
            }
            final Color color = graphics.getColor();
            an.C = 0;
            final Font font = b;
            final Font font2 = new Font(font.getFamily(), 1, font.getSize());
            final Font font3 = new Font(font.getFamily(), 2, font.getSize());
            final Font font4 = new Font(font.getFamily(), 3, font.getSize());
            if ((an.F && be.U) || (!an.F && be.V)) {
                if (an.E && an.D) {
                    graphics.setFont(font4);
                }
                else if (an.E) {
                    graphics.setFont(font2);
                }
                else if (an.D) {
                    graphics.setFont(font3);
                }
                else {
                    graphics.setFont(font);
                }
            }
            else {
                graphics.setFont(font);
            }
            final FontMetrics fontMetrics2 = this.getFontMetrics(graphics.getFont());
            int i = 0;
            for (int j = 0; j < an.a; ++j) {
                int e = bl.e;
                int k = an.e[j];
                final int n7 = an.e[j + 1];
                final int c = ba.c(an.d.substring(k, n7));
                int n8 = 0;
                if (c > 0 && 16 > fontMetrics2.getHeight()) {
                    n8 = 16 - fontMetrics2.getHeight();
                }
                final boolean b2 = n6 + 1 >= n2 && n6 - height - n8 <= n3;
                while (i < an.b) {
                    int n9 = an.f[2 * i];
                    int n10 = an.f[2 * i + 1];
                    if (n10 > n7) {
                        n10 = n7;
                    }
                    else {
                        ++i;
                    }
                    if (n9 < k) {
                        n9 = k;
                    }
                    if (k >= n10) {
                        break;
                    }
                    if (n7 <= n9) {
                        break;
                    }
                    if (b2) {
                        if (n9 != k) {
                            if (e == bl.e) {
                                while (k < n7) {
                                    if (an.d.charAt(k) != ' ') {
                                        break;
                                    }
                                    ++k;
                                }
                            }
                            String s;
                            try {
                                s = an.d.substring(k, n9);
                            }
                            catch (StringIndexOutOfBoundsException ex) {
                                s = an.d.substring(k, k);
                            }
                            graphics.drawString(s, e, n6);
                            e += fontMetrics2.stringWidth(s);
                        }
                        graphics.setColor(Color.blue);
                        final String substring = an.d.substring(n9, n10);
                        final int stringWidth2 = fontMetrics2.stringWidth(substring);
                        graphics.drawLine(e, n6 + 1, e + stringWidth2, n6 + 1);
                        if (an.p) {
                            graphics.setColor(super.h.c.f);
                        }
                        else if (an.l && super.h.Q) {
                            graphics.setColor(super.h.c.m);
                        }
                        else if (!an.F) {
                            graphics.setColor(super.h.c.q);
                        }
                        else {
                            graphics.setColor(super.h.c.n);
                        }
                        graphics.drawString(substring, e + 1, n6);
                        graphics.drawString(substring, e - 1, n6);
                        graphics.setColor(Color.blue);
                        graphics.drawString(substring, e, n6);
                        if (an.B != -1) {
                            graphics.setColor(new Color(an.B));
                        }
                        else if (!an.F && (!an.p || !super.k)) {
                            graphics.setColor(super.h.c.p);
                        }
                        else {
                            graphics.setColor(super.h.c.l);
                        }
                        e += stringWidth2;
                    }
                    k = n10;
                }
                if (b2 && k != n7) {
                    String s2 = an.d.substring(k, n7);
                    if (e == bl.e) {
                        s2 = s2.trim();
                    }
                    if (c < 1 || !V.a(super.h.h, 58)) {
                        this.a(an, s2, graphics, e, n6, color);
                    }
                    else {
                        int n11 = 0;
                        final int length = s2.length();
                        int b3 = -1;
                        final int n12 = n6 + (height + n8 - fontMetrics2.getHeight()) / 2;
                        final int n13 = n6 - fontMetrics2.getAscent() + (height + n8 - 16) / 2;
                        int n14 = 0;
                        for (int l = 0; l < c; ++l) {
                            b3 = ba.b(s2, ++b3);
                            if (b3 == -1) {
                                break;
                            }
                            if (b3 >= length) {
                                break;
                            }
                            int index = s2.indexOf(32, b3 + 1);
                            if (index == -1) {
                                index = length;
                            }
                            final ba a4 = ba.a(s2.substring(b3, index));
                            if (a4 != null && a4.b != null && (!a4.c(36) || an.C) && n14 < aI.d[an.F ? 2 : 3]) {
                                ++n14;
                                this.a(an, s2.substring(n11, b3), graphics, e, n12, color);
                                final int n15 = e + fontMetrics2.stringWidth(s2.substring(n11, b3) + " ");
                                graphics.drawImage(a4.b, n15, n13, super.a);
                                e = n15 + (16 + fontMetrics2.charWidth(' '));
                                n11 = b3 + a4.g().length();
                            }
                            else {
                                this.a(an, s2.substring(n11, index), graphics, e, n12, color);
                                e += fontMetrics2.stringWidth(s2.substring(n11, index));
                                n11 = index;
                            }
                        }
                        this.a(an, s2.substring(n11, length), graphics, e, n12, color);
                    }
                }
                n6 += height + n8;
            }
        }
        catch (Exception ex2) {}
    }
    
    public final synchronized void a(final aN an, final int n, FontMetrics fontMetrics) {
        try {
            b(an);
            final Font font = fontMetrics.getFont();
            final Font font2 = new Font(font.getFamily(), 1, font.getSize());
            final Font font3 = new Font(font.getFamily(), 2, font.getSize());
            final Font font4 = new Font(font.getFamily(), 3, font.getSize());
            if ((an.F && be.U) || (!an.F && be.V)) {
                if (an.E && an.D) {
                    fontMetrics = this.getFontMetrics(font4);
                }
                else if (an.E) {
                    fontMetrics = this.getFontMetrics(font2);
                }
                else if (an.D) {
                    fontMetrics = this.getFontMetrics(font3);
                }
            }
            final String s = new String(an.d);
            int n2 = 0;
            an.e[0] = 0;
            an.a = 0;
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n");
            final Vector vector = new Vector<String>();
            while (stringTokenizer.hasMoreTokens()) {
                vector.addElement(stringTokenizer.nextToken());
            }
            final String[] array = new String[vector.size()];
            vector.copyInto(array);
            for (int i = 0; i < array.length; ++i) {
                final int[] array2 = new int[31];
                array2[0] = 0;
                int n3 = 0;
                if (i != array.length - 1) {
                    final StringBuffer sb = new StringBuffer();
                    final String[] array3 = array;
                    final int n4 = i;
                    array3[n4] = sb.append(array3[n4]).append("\n").toString();
                }
                int n5 = 0;
                final int length = array[i].length();
                while (true) {
                    if (n5 >= length || array[i].charAt(n5) != ' ') {
                        int n6 = n5;
                        int n7;
                        int j = n7 = length;
                        while (j > n6) {
                            int n8 = 0;
                            if (V.a(super.h.h, 58)) {
                                n8 = (16 + 2 * fontMetrics.charWidth(' ')) * ba.c(array[i].substring(n5, n7)) - ba.a(array[i].substring(n5, n7), fontMetrics);
                            }
                            final int n9 = fontMetrics.stringWidth(array[i].substring(n5, n7)) + n8;
                            if (n9 <= n && n7 == length) {
                                break;
                            }
                            if (n9 < n) {
                                n6 = 1 + n7;
                            }
                            else {
                                j = n7;
                            }
                            n7 = (n6 + j) / 2;
                        }
                        final int lastIndex = array[i].lastIndexOf(32, n7);
                        if (lastIndex < n5) {
                            n5 = n7;
                        }
                        else {
                            n5 = lastIndex;
                        }
                        array2[++n3] = n5;
                        if (n3 >= 30 || j >= length) {
                            break;
                        }
                        continue;
                    }
                    else {
                        ++n5;
                    }
                }
                array2[n3] = length;
                for (int n10 = 1; n10 <= n3 && an.a < 30; ++n10) {
                    an.e[++an.a] = array2[n10] + n2;
                }
                n2 += array2[n3];
            }
            an.d = "";
            for (int k = 0; k < array.length; ++k) {
                an.d += array[k];
            }
            int n11 = 0;
            if (16 > fontMetrics.getHeight()) {
                for (int l = 0; l < an.a; ++l) {
                    if (ba.c(an.d.substring(an.e[l], an.e[l + 1])) > 0) {
                        n11 += 16 - fontMetrics.getHeight();
                    }
                }
            }
            an.f = 6 + fontMetrics.getHeight() * (an.a + 1) + n11;
            if (super.h.d.b(an.d) != null) {
                an.z = ((aq)super.h.d.b(an.d)).t;
            }
            if (an.z != null && an.z.length() > 0 && bq.g.containsKey(an.z)) {
                an.f += 14;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final String a(final Object o) {
        if (super.k) {
            return aG.a("Chat messages are displayed here.  Single-click on a private message to reply.");
        }
        return aG.a("Private messages are displayed here.");
    }
    
    private void a(final aN an, final String s, final Graphics graphics, int n, final int n2, final Color color) {
        try {
            String string = "";
            for (int i = 0; i < s.length(); ++i) {
                if (an.d.containsKey(String.valueOf(i + an.C))) {
                    if (string.length() > 0) {
                        n += a(graphics, string, n, n2);
                    }
                    string = "";
                    final Object value = an.d.get(String.valueOf(i + an.C));
                    graphics.setColor(((Color)value).getClass().getName().equals("java.awt.Color") ? ((Color)value) : color);
                }
                string += s.substring(i, i + 1);
            }
            an.C += s.length();
            if (string.length() > 0) {
                n += a(graphics, string, n, n2);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static final synchronized void b(final aN an) {
        if (an.d != null) {
            return;
        }
        an.d = new Hashtable();
        an.C = 0;
        String d = "";
        final String d2 = an.d;
        try {
            if (d2.indexOf("[color=") > -1 && d2.indexOf("[/color]") > -1) {
                final Hashtable hashtable = new Hashtable<String, String>();
                hashtable.put("0", "");
                int n = 0;
                String string = "";
                for (int i = 0; i < d2.length(); ++i) {
                    if (i < d2.length() - 7 && d2.substring(i, i + 7).equalsIgnoreCase("[color=")) {
                        final int index = d2.indexOf(93, i + 7);
                        if (index != -1) {
                            if (string.length() > 0) {
                                an.d.put(String.valueOf(d.length()), hashtable.get(String.valueOf(hashtable.size() - 1)));
                                d += string;
                                string = "";
                            }
                            hashtable.put(String.valueOf(hashtable.size()), (String)new Color(Integer.parseInt(d2.substring(i + 7, index))));
                            i = index;
                            n = index + 1;
                        }
                    }
                    else if (i < d2.length() - 7 && d2.substring(i, i + 8).equalsIgnoreCase("[/color]")) {
                        an.d.put(String.valueOf(d.length()), hashtable.get(String.valueOf(hashtable.size() - 1)));
                        d += d2.substring(n, i);
                        string = "";
                        hashtable.remove(String.valueOf(hashtable.size() - 1));
                        i += 7;
                        n = i + 1;
                    }
                    else {
                        string += d2.substring(i, i + 1);
                    }
                }
                if (string.length() > 0) {
                    an.d.put(String.valueOf(d.length()), hashtable.get(String.valueOf(hashtable.size() - 1)));
                    d += string;
                }
                an.d = d;
                an.c();
            }
            else {
                an.d.put("0", "");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            an.d = ex.getMessage();
        }
    }
    
    private static int a(final Graphics graphics, final String s, final int n, final int n2) {
        graphics.drawString(s, n, n2);
        return graphics.getFontMetrics().stringWidth(s);
    }
    
    public ae(final be be, final boolean b) {
        super(be, b);
        bl.e = 36;
    }
}
