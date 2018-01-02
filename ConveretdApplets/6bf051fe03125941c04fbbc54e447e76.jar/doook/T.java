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

public final class T extends bt implements aO
{
    protected final synchronized void a(final bv bv, final int n, final Graphics graphics) {
        try {
            if (bv.aB == -1) {
                if (bv.z) {
                    bv.aB = ((a)super.k.e.b(bv.B)).u;
                }
                else if (bv.am) {
                    bv.aB = ((a)super.k.e.b(bv.B)).u;
                }
                else if (bv.w && !super.k.K) {
                    bv.aB = 2;
                }
                else if (bv.x && !bv.an) {
                    bv.aB = ((a)super.k.e.b(bv.B)).u;
                }
                else if (bv.an) {
                    bv.aB = ((a)super.k.e.b(bv.B)).u;
                }
                else if (bv.B != -999) {
                    bv.aB = ((a)super.k.e.b(bv.B)).u;
                }
                else {
                    bv.aB = bv.aC;
                }
                if (bv.B != -999 && super.k.e.b(bv.B) != null) {
                    bv.c = ((a)super.k.e.b(bv.B)).c;
                    if (((a)super.k.e.b(bv.B)).a(18)) {
                        bv.g = true;
                    }
                    if (((a)super.k.e.b(bv.B)).a(36)) {
                        bv.ao = true;
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
            final Font b = super.k.b.b();
            final Font a = super.k.b.a();
            if (bv.d != -1) {
                graphics.setColor(new Color(bv.d));
            }
            else if (!bv.ar && (!bv.z || !super.w)) {
                graphics.setColor(super.k.b.s);
            }
            else if (bv.z) {
                graphics.setColor(super.k.b.q);
            }
            else if (bv.w && super.k.K) {
                graphics.setColor(super.k.b.n);
            }
            else if (bv.Z) {
                graphics.setColor(super.k.b.n);
            }
            else {
                graphics.setColor(super.k.b.o);
            }
            if ((graphics.getColor() != super.k.b.o || graphics.getColor() != super.k.b.s) && super.w && super.b != null) {
                graphics.fillRect(0, n, size2.width, 2);
                graphics.fillRect(size2.width - 2 - this.a(), n, 2, bv.t - 1);
                graphics.fillRect(0, n, 2, bv.t - 1);
                graphics.fillRect(0, n + bv.t - 1 - 2, size2.width, 2);
            }
            if (!super.w || super.b == null) {
                graphics.fillRect(0, n, size2.width, bv.t - 1);
            }
            graphics.setColor(Color.white);
            graphics.drawLine(0, n + bv.t - 1, size2.width, n + bv.t - 1);
            if (bv.j != null && n4 + 24 >= n2 && n4 <= n3) {
                graphics.drawImage(bv.j, 6, n4, super.a);
            }
            final FontMetrics fontMetrics = this.getFontMetrics(b);
            final int height = fontMetrics.getHeight();
            final int n5 = n4 + fontMetrics.getAscent() - 2;
            int n6 = n5 + height;
            if (n5 >= n2 && n5 - height <= n3) {
                if (bv.c != null && bv.c.length() != 0 && aX.e.containsKey(bv.c)) {
                    graphics.drawImage(aX.e.get(bv.c), bt.c - 1, n5 - 11, super.a);
                }
                graphics.setColor(new Color(bv.aB));
                graphics.setFont(a);
                if (bv.a != null && bv.a.length() > 0 && aX.d.containsKey(bv.a)) {
                    graphics.drawImage(aX.d.get(bv.a), (bv.c != null && bv.c.length() != 0) ? (bt.c + 14) : bt.c, n5 - 11, super.a);
                    n6 += 14;
                }
                else {
                    graphics.drawString(bv.q, (bv.c != null && bv.c.length() != 0) ? (bt.c + 14) : bt.c, n5);
                }
                int stringWidth = 0;
                if (super.k.J) {
                    graphics.setFont(super.k.b.c());
                    graphics.drawString(bv.I, bt.c + this.getFontMetrics(a).stringWidth(bv.q + "##"), n5);
                    stringWidth = this.getFontMetrics(super.k.b.c()).stringWidth(bv.I);
                }
                if (bv.z && super.w) {
                    final a a2 = (a)super.k.e.b(bv.B);
                    if (a2 != null && a2.a(59) && ((aW)super.k.f.b(a2.t)).b()) {
                        final String b2 = ar.b("(Private message from Guest Speaker)");
                        graphics.setColor(super.k.b.m);
                        graphics.drawString(b2, bt.c + stringWidth + fontMetrics.stringWidth(bv.q + "######"), n5);
                    }
                    else if (bv.o == -1) {
                        final String b3 = ar.b("(Single click on this message to reply)");
                        graphics.setColor(super.k.b.m);
                        graphics.drawString(b3, bt.c + stringWidth + fontMetrics.stringWidth(bv.q + "######"), n5);
                    }
                }
            }
            if (bv.aD != -1) {
                graphics.setColor(new Color(bv.aD));
            }
            else if (bv.z) {
                graphics.setColor(super.k.b.p);
            }
            else if (!bv.ar && (!bv.z || !super.w)) {
                graphics.setColor(super.k.b.r);
            }
            else {
                graphics.setColor(super.k.b.m);
            }
            final Color color = graphics.getColor();
            bv.aE = 0;
            final Font font = b;
            final Font font2 = new Font(font.getFamily(), 1, font.getSize());
            final Font font3 = new Font(font.getFamily(), 2, font.getSize());
            final Font font4 = new Font(font.getFamily(), 3, font.getSize());
            if ((bv.ar && as.O) || (!bv.ar && as.P)) {
                if (bv.aq && bv.ap) {
                    graphics.setFont(font4);
                }
                else if (bv.aq) {
                    graphics.setFont(font2);
                }
                else if (bv.ap) {
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
            for (int j = 0; j < bv.g; ++j) {
                int c = bt.c;
                int k = bv.f[j];
                final int n7 = bv.f[j + 1];
                final int a3 = U.a(bv.p.substring(k, n7));
                int n8 = 0;
                if (a3 > 0 && 16 > fontMetrics2.getHeight()) {
                    n8 = 16 - fontMetrics2.getHeight();
                }
                final boolean b4 = n6 + 1 >= n2 && n6 - height - n8 <= n3;
                while (i < bv.h) {
                    int n9 = bv.g[2 * i];
                    int n10 = bv.g[2 * i + 1];
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
                    if (b4) {
                        if (n9 != k) {
                            if (c == bt.c) {
                                while (k < n7) {
                                    if (bv.p.charAt(k) != ' ') {
                                        break;
                                    }
                                    ++k;
                                }
                            }
                            String s;
                            try {
                                s = bv.p.substring(k, n9);
                            }
                            catch (StringIndexOutOfBoundsException ex) {
                                s = bv.p.substring(k, k);
                            }
                            graphics.drawString(s, c, n6);
                            c += fontMetrics2.stringWidth(s);
                        }
                        graphics.setColor(Color.blue);
                        final String substring = bv.p.substring(n9, n10);
                        final int stringWidth2 = fontMetrics2.stringWidth(substring);
                        graphics.drawLine(c, n6 + 1, c + stringWidth2, n6 + 1);
                        if (bv.z) {
                            graphics.setColor(super.k.b.q);
                        }
                        else if (bv.w && super.k.K) {
                            graphics.setColor(super.k.b.n);
                        }
                        else if (!bv.ar) {
                            graphics.setColor(super.k.b.s);
                        }
                        else {
                            graphics.setColor(super.k.b.o);
                        }
                        graphics.drawString(substring, c + 1, n6);
                        graphics.drawString(substring, c - 1, n6);
                        graphics.setColor(Color.blue);
                        graphics.drawString(substring, c, n6);
                        if (bv.aD != -1) {
                            graphics.setColor(new Color(bv.aD));
                        }
                        else if (!bv.ar && (!bv.z || !super.w)) {
                            graphics.setColor(super.k.b.r);
                        }
                        else {
                            graphics.setColor(super.k.b.m);
                        }
                        c += stringWidth2;
                    }
                    k = n10;
                }
                if (b4 && k != n7) {
                    String s2 = bv.p.substring(k, n7);
                    if (c == bt.c) {
                        s2 = s2.trim();
                    }
                    if (a3 < 1 || !aJ.a(super.k.d, 58)) {
                        this.a(bv, s2, graphics, c, n6, color);
                    }
                    else {
                        int n11 = 0;
                        final int length = s2.length();
                        int a4 = -1;
                        final int n12 = n6 + (height + n8 - fontMetrics2.getHeight()) / 2;
                        final int n13 = n6 - fontMetrics2.getAscent() + (height + n8 - 16) / 2;
                        int n14 = 0;
                        for (int l = 0; l < a3; ++l) {
                            a4 = U.a(s2, ++a4);
                            if (a4 == -1) {
                                break;
                            }
                            if (a4 >= length) {
                                break;
                            }
                            int index = s2.indexOf(32, a4 + 1);
                            if (index == -1) {
                                index = length;
                            }
                            final U a5 = U.a(s2.substring(a4, index));
                            if (a5 != null && a5.b != null && (!a5.a(36) || bv.ao) && n14 < F.b[bv.ar ? 2 : 3]) {
                                ++n14;
                                this.a(bv, s2.substring(n11, a4), graphics, c, n12, color);
                                final int n15 = c + fontMetrics2.stringWidth(s2.substring(n11, a4) + " ");
                                graphics.drawImage(a5.b, n15, n13, super.a);
                                c = n15 + (16 + fontMetrics2.charWidth(' '));
                                n11 = a4 + a5.d().length();
                            }
                            else {
                                this.a(bv, s2.substring(n11, index), graphics, c, n12, color);
                                c += fontMetrics2.stringWidth(s2.substring(n11, index));
                                n11 = index;
                            }
                        }
                        this.a(bv, s2.substring(n11, length), graphics, c, n12, color);
                    }
                }
                n6 += height + n8;
            }
        }
        catch (Exception ex2) {}
    }
    
    public final synchronized void a(final bv bv, final int n, FontMetrics fontMetrics) {
        try {
            a(bv);
            final Font font = fontMetrics.getFont();
            final Font font2 = new Font(font.getFamily(), 1, font.getSize());
            final Font font3 = new Font(font.getFamily(), 2, font.getSize());
            final Font font4 = new Font(font.getFamily(), 3, font.getSize());
            if ((bv.ar && as.O) || (!bv.ar && as.P)) {
                if (bv.aq && bv.ap) {
                    fontMetrics = this.getFontMetrics(font4);
                }
                else if (bv.aq) {
                    fontMetrics = this.getFontMetrics(font2);
                }
                else if (bv.ap) {
                    fontMetrics = this.getFontMetrics(font3);
                }
            }
            final String s = new String(bv.p);
            int n2 = 0;
            bv.f[0] = 0;
            bv.g = 0;
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
                            if (aJ.a(super.k.d, 58)) {
                                n8 = (16 + 2 * fontMetrics.charWidth(' ')) * U.a(array[i].substring(n5, n7)) - U.a(array[i].substring(n5, n7), fontMetrics);
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
                for (int n10 = 1; n10 <= n3 && bv.g < 30; ++n10) {
                    bv.f[++bv.g] = array2[n10] + n2;
                }
                n2 += array2[n3];
            }
            bv.p = "";
            for (int k = 0; k < array.length; ++k) {
                bv.p += array[k];
            }
            int n11 = 0;
            if (16 > fontMetrics.getHeight()) {
                for (int l = 0; l < bv.g; ++l) {
                    if (U.a(bv.p.substring(bv.f[l], bv.f[l + 1])) > 0) {
                        n11 += 16 - fontMetrics.getHeight();
                    }
                }
            }
            bv.t = 6 + fontMetrics.getHeight() * (bv.g + 1) + n11;
            if (super.k.e.b(bv.B) != null) {
                bv.a = ((a)super.k.e.b(bv.B)).a;
            }
            if (bv.a != null && bv.a.length() > 0 && aX.d.containsKey(bv.a)) {
                bv.t += 14;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final String a(final Object o) {
        if (super.w) {
            return ar.b("Chat messages are displayed here.  Single-click on a private message to reply.");
        }
        return ar.b("Private messages are displayed here.");
    }
    
    private void a(final bv bv, final String s, final Graphics graphics, int n, final int n2, final Color color) {
        try {
            String string = "";
            for (int i = 0; i < s.length(); ++i) {
                if (bv.j.containsKey(String.valueOf(i + bv.aE))) {
                    if (string.length() > 0) {
                        n += a(graphics, string, n, n2);
                    }
                    string = "";
                    final Object value = bv.j.get(String.valueOf(i + bv.aE));
                    graphics.setColor(((Color)value).getClass().getName().equals("java.awt.Color") ? ((Color)value) : color);
                }
                string += s.substring(i, i + 1);
            }
            bv.aE += s.length();
            if (string.length() > 0) {
                n += a(graphics, string, n, n2);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static final synchronized void a(final bv bv) {
        if (bv.j != null) {
            return;
        }
        bv.j = new Hashtable();
        bv.aE = 0;
        String p = "";
        final String p2 = bv.p;
        try {
            if (p2.indexOf("[color=") > -1 && p2.indexOf("[/color]") > -1) {
                final Hashtable hashtable = new Hashtable<String, String>();
                hashtable.put("0", "");
                int n = 0;
                String string = "";
                for (int i = 0; i < p2.length(); ++i) {
                    if (i < p2.length() - 7 && p2.substring(i, i + 7).equalsIgnoreCase("[color=")) {
                        final int index = p2.indexOf(93, i + 7);
                        if (index != -1) {
                            if (string.length() > 0) {
                                bv.j.put(String.valueOf(p.length()), hashtable.get(String.valueOf(hashtable.size() - 1)));
                                p += string;
                                string = "";
                            }
                            hashtable.put(String.valueOf(hashtable.size()), (String)new Color(Integer.parseInt(p2.substring(i + 7, index))));
                            i = index;
                            n = index + 1;
                        }
                    }
                    else if (i < p2.length() - 7 && p2.substring(i, i + 8).equalsIgnoreCase("[/color]")) {
                        bv.j.put(String.valueOf(p.length()), hashtable.get(String.valueOf(hashtable.size() - 1)));
                        p += p2.substring(n, i);
                        string = "";
                        hashtable.remove(String.valueOf(hashtable.size() - 1));
                        i += 7;
                        n = i + 1;
                    }
                    else {
                        string += p2.substring(i, i + 1);
                    }
                }
                if (string.length() > 0) {
                    bv.j.put(String.valueOf(p.length()), hashtable.get(String.valueOf(hashtable.size() - 1)));
                    p += string;
                }
                bv.p = p;
                bv.f();
            }
            else {
                bv.j.put("0", "");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            bv.p = ex.getMessage();
        }
    }
    
    private static int a(final Graphics graphics, final String s, final int n, final int n2) {
        graphics.drawString(s, n, n2);
        return graphics.getFontMetrics().stringWidth(s);
    }
    
    public T(final as as, final boolean b) {
        super(as, b);
        bt.c = 36;
    }
}
