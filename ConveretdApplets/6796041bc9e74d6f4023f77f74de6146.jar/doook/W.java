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

public final class W extends ay implements aB
{
    protected final synchronized void a(final Z z, final int n, final Graphics graphics) {
        try {
            if (z.aj == -1) {
                if (z.v) {
                    z.aj = ((ab)super.i.c.b(z.Z)).aN;
                }
                else if (z.ab) {
                    z.aj = ((ab)super.i.c.b(z.Z)).aN;
                }
                else if (z.t && !super.i.H) {
                    z.aj = 2;
                }
                else if (z.u && !z.ac) {
                    z.aj = ((ab)super.i.c.b(z.Z)).aN;
                }
                else if (z.ac) {
                    z.aj = ((ab)super.i.c.b(z.Z)).aN;
                }
                else if (z.Z != -999) {
                    z.aj = ((ab)super.i.c.b(z.Z)).aN;
                }
                else {
                    z.aj = z.ak;
                }
                if (z.Z != -999 && super.i.c.b(z.Z) != null) {
                    z.b = ((ab)super.i.c.b(z.Z)).b;
                    if (((ab)super.i.c.b(z.Z)).d(18)) {
                        z.g = true;
                    }
                    if (((ab)super.i.c.b(z.Z)).d(36)) {
                        z.ae = true;
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
            final Font b = super.i.a.b();
            final Font a = super.i.a.a();
            if (z.d != -1) {
                graphics.setColor(new Color(z.d));
            }
            else if (!z.ah && (!z.v || !super.t)) {
                graphics.setColor(super.i.a.r);
            }
            else if (z.v) {
                graphics.setColor(super.i.a.p);
            }
            else if (z.t && super.i.H) {
                graphics.setColor(super.i.a.m);
            }
            else if (z.ad) {
                graphics.setColor(super.i.a.m);
            }
            else {
                graphics.setColor(super.i.a.n);
            }
            if ((graphics.getColor() != super.i.a.n || graphics.getColor() != super.i.a.r) && super.t && super.e != null) {
                graphics.fillRect(0, n, size2.width, 2);
                graphics.fillRect(size2.width - 2 - this.a(), n, 2, z.h - 1);
                graphics.fillRect(0, n, 2, z.h - 1);
                graphics.fillRect(0, n + z.h - 1 - 2, size2.width, 2);
            }
            if (!super.t || super.e == null) {
                graphics.fillRect(0, n, size2.width, z.h - 1);
            }
            graphics.setColor(Color.white);
            graphics.drawLine(0, n + z.h - 1, size2.width, n + z.h - 1);
            if (z.o != null && n4 + 24 >= n2 && n4 <= n3) {
                graphics.drawImage(z.o, 6, n4, super.a);
            }
            final FontMetrics fontMetrics = this.getFontMetrics(b);
            final int height = fontMetrics.getHeight();
            final int n5 = n4 + fontMetrics.getAscent() - 2;
            int n6 = n5 + height;
            if (n5 >= n2 && n5 - height <= n3) {
                if (z.b != null && z.b.length() != 0 && doook.y.b.containsKey(z.b)) {
                    graphics.drawImage(doook.y.b.get(z.b), ay.g - 1, n5 - 11, super.a);
                }
                graphics.setColor(new Color(z.aj));
                graphics.setFont(a);
                if (z.c != null && z.c.length() > 0 && doook.y.a.containsKey(z.c)) {
                    graphics.drawImage(doook.y.a.get(z.c), (z.b != null && z.b.length() != 0) ? (ay.g + 14) : ay.g, n5 - 11, super.a);
                    n6 += 14;
                }
                else {
                    graphics.drawString(z.m, (z.b != null && z.b.length() != 0) ? (ay.g + 14) : ay.g, n5);
                }
                int stringWidth = 0;
                if (super.i.G) {
                    graphics.setFont(super.i.a.c());
                    graphics.drawString(z.P, ay.g + this.getFontMetrics(a).stringWidth(z.m + "##"), n5);
                    stringWidth = this.getFontMetrics(super.i.a.c()).stringWidth(z.P);
                }
                if (z.v && super.t) {
                    final ab ab = (ab)super.i.c.b(z.Z);
                    if (ab != null && ab.d(59) && ((av)super.i.d.b(ab.h)).a()) {
                        final String e = ao.e("(Private message from Guest Speaker)");
                        graphics.setColor(super.i.a.l);
                        graphics.drawString(e, ay.g + stringWidth + fontMetrics.stringWidth(z.m + "######"), n5);
                    }
                    else if (z.ai == -1) {
                        final String e2 = ao.e("(Single click on this message to reply)");
                        graphics.setColor(super.i.a.l);
                        graphics.drawString(e2, ay.g + stringWidth + fontMetrics.stringWidth(z.m + "######"), n5);
                    }
                }
            }
            if (z.al != -1) {
                graphics.setColor(new Color(z.al));
            }
            else if (z.v) {
                graphics.setColor(super.i.a.o);
            }
            else if (!z.ah && (!z.v || !super.t)) {
                graphics.setColor(super.i.a.q);
            }
            else {
                graphics.setColor(super.i.a.l);
            }
            final Color color = graphics.getColor();
            z.am = 0;
            final Font font = b;
            final Font font2 = new Font(font.getFamily(), 1, font.getSize());
            final Font font3 = new Font(font.getFamily(), 2, font.getSize());
            final Font font4 = new Font(font.getFamily(), 3, font.getSize());
            if ((z.ah && doook.t.M) || (!z.ah && doook.t.N)) {
                if (z.ag && z.af) {
                    graphics.setFont(font4);
                }
                else if (z.ag) {
                    graphics.setFont(font2);
                }
                else if (z.af) {
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
            for (int j = 0; j < z.i; ++j) {
                int g = ay.g;
                int k = z.a[j];
                final int n7 = z.a[j + 1];
                final int a2 = bh.a(z.l.substring(k, n7));
                int n8 = 0;
                if (a2 > 0 && 16 > fontMetrics2.getHeight()) {
                    n8 = 16 - fontMetrics2.getHeight();
                }
                final boolean b2 = n6 + 1 >= n2 && n6 - height - n8 <= n3;
                while (i < z.a) {
                    int n9 = z.b[2 * i];
                    int n10 = z.b[2 * i + 1];
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
                            if (g == ay.g) {
                                while (k < n7) {
                                    if (z.l.charAt(k) != ' ') {
                                        break;
                                    }
                                    ++k;
                                }
                            }
                            String s;
                            try {
                                s = z.l.substring(k, n9);
                            }
                            catch (StringIndexOutOfBoundsException ex) {
                                s = z.l.substring(k, k);
                            }
                            graphics.drawString(s, g, n6);
                            g += fontMetrics2.stringWidth(s);
                        }
                        graphics.setColor(Color.blue);
                        final String substring = z.l.substring(n9, n10);
                        final int stringWidth2 = fontMetrics2.stringWidth(substring);
                        graphics.drawLine(g, n6 + 1, g + stringWidth2, n6 + 1);
                        if (z.v) {
                            graphics.setColor(super.i.a.p);
                        }
                        else if (z.t && super.i.H) {
                            graphics.setColor(super.i.a.m);
                        }
                        else if (!z.ah) {
                            graphics.setColor(super.i.a.r);
                        }
                        else {
                            graphics.setColor(super.i.a.n);
                        }
                        graphics.drawString(substring, g + 1, n6);
                        graphics.drawString(substring, g - 1, n6);
                        graphics.setColor(Color.blue);
                        graphics.drawString(substring, g, n6);
                        if (z.al != -1) {
                            graphics.setColor(new Color(z.al));
                        }
                        else if (!z.ah && (!z.v || !super.t)) {
                            graphics.setColor(super.i.a.q);
                        }
                        else {
                            graphics.setColor(super.i.a.l);
                        }
                        g += stringWidth2;
                    }
                    k = n10;
                }
                if (b2 && k != n7) {
                    String s2 = z.l.substring(k, n7);
                    if (g == ay.g) {
                        s2 = s2.trim();
                    }
                    if (a2 < 1 || !cD.a(super.i.c, 58)) {
                        this.a(z, s2, graphics, g, n6, color);
                    }
                    else {
                        int n11 = 0;
                        final int length = s2.length();
                        int b3 = -1;
                        final int n12 = n6 + (height + n8 - fontMetrics2.getHeight()) / 2;
                        final int n13 = n6 - fontMetrics2.getAscent() + (height + n8 - 16) / 2;
                        int n14 = 0;
                        for (int l = 0; l < a2; ++l) {
                            b3 = bh.b(s2, ++b3);
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
                            final bh a3 = bh.a(s2.substring(b3, index));
                            if (a3 != null && a3.e != null && (!a3.d(36) || z.ae) && n14 < cG.h[z.ah ? 2 : 3]) {
                                ++n14;
                                this.a(z, s2.substring(n11, b3), graphics, g, n12, color);
                                final int n15 = g + fontMetrics2.stringWidth(s2.substring(n11, b3) + " ");
                                graphics.drawImage(a3.e, n15, n13, super.a);
                                g = n15 + (16 + fontMetrics2.charWidth(' '));
                                n11 = b3 + a3.f().length();
                            }
                            else {
                                this.a(z, s2.substring(n11, index), graphics, g, n12, color);
                                g += fontMetrics2.stringWidth(s2.substring(n11, index));
                                n11 = index;
                            }
                        }
                        this.a(z, s2.substring(n11, length), graphics, g, n12, color);
                    }
                }
                n6 += height + n8;
            }
        }
        catch (Exception ex2) {}
    }
    
    public final synchronized void a(final Z z, final int n, FontMetrics fontMetrics) {
        try {
            b(z);
            final Font font = fontMetrics.getFont();
            final Font font2 = new Font(font.getFamily(), 1, font.getSize());
            final Font font3 = new Font(font.getFamily(), 2, font.getSize());
            final Font font4 = new Font(font.getFamily(), 3, font.getSize());
            if ((z.ah && doook.t.M) || (!z.ah && doook.t.N)) {
                if (z.ag && z.af) {
                    fontMetrics = this.getFontMetrics(font4);
                }
                else if (z.ag) {
                    fontMetrics = this.getFontMetrics(font2);
                }
                else if (z.af) {
                    fontMetrics = this.getFontMetrics(font3);
                }
            }
            final String s = new String(z.l);
            int n2 = 0;
            z.a[0] = 0;
            z.i = 0;
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
                            if (cD.a(super.i.c, 58)) {
                                n8 = (16 + 2 * fontMetrics.charWidth(' ')) * bh.a(array[i].substring(n5, n7)) - bh.a(array[i].substring(n5, n7), fontMetrics);
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
                for (int n10 = 1; n10 <= n3 && z.i < 30; ++n10) {
                    z.a[++z.i] = array2[n10] + n2;
                }
                n2 += array2[n3];
            }
            z.l = "";
            for (int k = 0; k < array.length; ++k) {
                z.l += array[k];
            }
            int n11 = 0;
            if (16 > fontMetrics.getHeight()) {
                for (int l = 0; l < z.i; ++l) {
                    if (bh.a(z.l.substring(z.a[l], z.a[l + 1])) > 0) {
                        n11 += 16 - fontMetrics.getHeight();
                    }
                }
            }
            z.h = 6 + fontMetrics.getHeight() * (z.i + 1) + n11;
            if (super.i.c.b(z.Z) != null) {
                z.c = ((ab)super.i.c.b(z.Z)).c;
            }
            if (z.c != null && z.c.length() > 0 && doook.y.a.containsKey(z.c)) {
                z.h += 14;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final String a(final Object o) {
        if (super.t) {
            return ao.e("Chat messages are displayed here.  Single-click on a private message to reply.");
        }
        return ao.e("Private messages are displayed here.");
    }
    
    private void a(final Z z, final String s, final Graphics graphics, int n, final int n2, final Color color) {
        try {
            String string = "";
            for (int i = 0; i < s.length(); ++i) {
                if (z.f.containsKey(String.valueOf(i + z.am))) {
                    if (string.length() > 0) {
                        n += a(graphics, string, n, n2);
                    }
                    string = "";
                    final Object value = z.f.get(String.valueOf(i + z.am));
                    graphics.setColor(((Color)value).getClass().getName().equals("java.awt.Color") ? ((Color)value) : color);
                }
                string += s.substring(i, i + 1);
            }
            z.am += s.length();
            if (string.length() > 0) {
                n += a(graphics, string, n, n2);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static final synchronized void b(final Z z) {
        if (z.f != null) {
            return;
        }
        z.f = new Hashtable();
        z.am = 0;
        String l = "";
        final String i = z.l;
        try {
            if (i.indexOf("[color=") > -1 && i.indexOf("[/color]") > -1) {
                final Hashtable hashtable = new Hashtable<String, String>();
                hashtable.put("0", "");
                int n = 0;
                String string = "";
                for (int j = 0; j < i.length(); ++j) {
                    if (j < i.length() - 7 && i.substring(j, j + 7).equalsIgnoreCase("[color=")) {
                        final int index = i.indexOf(93, j + 7);
                        if (index != -1) {
                            if (string.length() > 0) {
                                z.f.put(String.valueOf(l.length()), hashtable.get(String.valueOf(hashtable.size() - 1)));
                                l += string;
                                string = "";
                            }
                            hashtable.put(String.valueOf(hashtable.size()), (String)new Color(Integer.parseInt(i.substring(j + 7, index))));
                            j = index;
                            n = index + 1;
                        }
                    }
                    else if (j < i.length() - 7 && i.substring(j, j + 8).equalsIgnoreCase("[/color]")) {
                        z.f.put(String.valueOf(l.length()), hashtable.get(String.valueOf(hashtable.size() - 1)));
                        l += i.substring(n, j);
                        string = "";
                        hashtable.remove(String.valueOf(hashtable.size() - 1));
                        j += 7;
                        n = j + 1;
                    }
                    else {
                        string += i.substring(j, j + 1);
                    }
                }
                if (string.length() > 0) {
                    z.f.put(String.valueOf(l.length()), hashtable.get(String.valueOf(hashtable.size() - 1)));
                    l += string;
                }
                z.l = l;
                z.a();
            }
            else {
                z.f.put("0", "");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            z.l = ex.getMessage();
        }
    }
    
    private static int a(final Graphics graphics, final String s, final int n, final int n2) {
        graphics.drawString(s, n, n2);
        return graphics.getFontMetrics().stringWidth(s);
    }
    
    public W(final t t, final boolean b) {
        super(t, b);
        ay.g = 36;
    }
}
