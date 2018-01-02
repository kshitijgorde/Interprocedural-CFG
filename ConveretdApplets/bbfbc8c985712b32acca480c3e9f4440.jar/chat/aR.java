// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.util.Vector;
import java.util.StringTokenizer;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Graphics;

public final class aR extends ca implements aB
{
    public final synchronized void a(final aU au, int fontMetrics, final Graphics graphics) {
        try {
            Rectangle clipRect;
            if ((clipRect = graphics.getClipRect()) == null) {
                final Dimension size = this.size();
                clipRect = new Rectangle(0, 0, size.width, size.height);
            }
            final int n3;
            final int n2 = (n3 = clipRect.y - 1) + clipRect.height + 2;
            final Dimension size2 = this.size();
            final int n4 = n + 3 + 1;
            Font b;
            if (au.l > 0) {
                final int n5 = au.l - 1;
                b = new Font(super.a.a.a, ((n5 & 0xFFFFFFFC) != 0x0) ? 0 : n5, super.a.a.a);
            }
            else {
                b = super.a.a.b();
            }
            final Font a = super.a.a.a();
            if (au.b) {
                graphics.setColor(super.a.a.n);
            }
            else if (au.a && super.a.t) {
                graphics.setColor(super.a.a.k);
            }
            else if (au.e) {
                graphics.setColor(super.a.a.k);
            }
            else {
                graphics.setColor(super.a.a.l);
            }
            if (au.g && au.e == -999 && au.j != 0) {
                graphics.setColor(new Color(au.j));
            }
            else if (au.d) {
                graphics.setColor(new Color(au.j));
            }
            if (graphics.getColor() != super.a.a.l && super.b && super.a != null) {
                graphics.fillRect(0, n, size2.width, 2);
                graphics.fillRect(size2.width - 2 - this.a(), n, 2, au.a - 1);
                graphics.fillRect(0, n, 2, au.a - 1);
                graphics.fillRect(0, n + au.a - 1 - 2, size2.width, 2);
            }
            if (!super.b || super.a == null) {
                graphics.fillRect(0, n, size2.width, au.a - 1);
            }
            graphics.setColor(Color.white);
            graphics.drawLine(0, n + au.a - 1, size2.width, n + au.a - 1);
            if (au.a != null && n4 + 24 >= n3 && n4 <= n2) {
                graphics.drawImage(au.a, 6, n4, super.a);
            }
            final int height = ((FontMetrics)(fontMetrics = (int)this.getFontMetrics(b))).getHeight();
            final int n7;
            int n6 = (n7 = n4 + ((FontMetrics)fontMetrics).getAscent() - 2) + height;
            if (n7 >= n3 && n7 - height <= n2) {
                if (au.b) {
                    graphics.setColor(new Color(au.h));
                }
                else if (au.a && !super.a.t) {
                    graphics.setColor(super.a.a.k);
                }
                else {
                    graphics.setColor(new Color(au.h));
                }
                graphics.setFont(a);
                if (au.e == -999) {
                    graphics.setColor(new Color(au.h));
                }
                Image c = null;
                if (au.k != 0) {
                    try {
                        if (au.f && au.c != null && n4 + 24 >= n3 && n4 <= n2) {
                            c = au.c;
                        }
                        if (c != null) {
                            graphics.drawImage(c, (au.b != null) ? 50 : 36, n4, null);
                            final int n8 = n7 + c.getHeight(this) + 2;
                            if (n6 < n8) {
                                n6 = n8;
                            }
                        }
                        if (au.b != null) {
                            graphics.drawImage(au.b, 34, n7 - 11, null);
                        }
                    }
                    catch (Exception ex) {}
                }
                if (c == null) {
                    graphics.drawString(au.b, (au.b != null) ? 50 : 36, n7);
                }
                graphics.setFont(b);
                if (au.b && super.b) {
                    final aZ az;
                    if ((az = (aZ)super.a.c.b(au.e)) != null && az.a(59) && ((ak)super.a.d.b(az.b)).a(61)) {
                        final String a2 = aS.a(469);
                        graphics.setColor(super.a.a.i);
                        graphics.drawString(a2, 36 + ((FontMetrics)fontMetrics).stringWidth(au.b + "######"), n7);
                    }
                    else if (au.f == -1) {
                        final String a3 = aS.a(470);
                        graphics.setColor(super.a.a.i);
                        graphics.drawString(a3, 36 + ((FontMetrics)fontMetrics).stringWidth(au.b + "######"), n7);
                    }
                }
            }
            if (au.i != 0) {
                graphics.setColor(new Color(au.i));
            }
            else {
                graphics.setColor(super.a.a.i);
            }
            graphics.setFont(b);
            int i = 0;
            for (int j = 0; j < au.b; ++j) {
                int n9 = 36;
                int n10 = au.a[j];
                final int n11 = au.a[j + 1];
                int a4 = ae.a(au.a.substring(n10, n11));
                int n12 = 0;
                if (a4 > 0 && 16 > ((FontMetrics)fontMetrics).getHeight()) {
                    n12 = 16 - ((FontMetrics)fontMetrics).getHeight();
                }
                final boolean b2 = n6 + 1 >= n3 && n6 - height - n12 <= n2;
                int n13 = 0;
                int n14 = 0;
                int n15 = 0;
                if (a4 > 0) {
                    n14 = n6 + (height + n12 - ((FontMetrics)fontMetrics).getHeight()) / 2;
                    n15 = n6 - ((FontMetrics)fontMetrics).getAscent() + (height + n12 - 16) / 2;
                }
                boolean b3 = false;
                while (i < au.g) {
                    int n16 = au.c[i * 3];
                    int n17 = au.c[i * 3 + 1];
                    final int n18 = au.c[i * 3 + 2];
                    if (n17 > n11) {
                        n17 = n11;
                    }
                    else {
                        ++i;
                    }
                    if (n16 < n10) {
                        n16 = n10;
                    }
                    if (n10 >= n17 || n11 <= n16) {
                        break;
                    }
                    if (b2) {
                        if (n16 != n10) {
                            if (n9 == 36) {
                                while (n10 < n11 && au.a.charAt(n10) == ' ') {
                                    ++n10;
                                }
                            }
                            String s;
                            try {
                                s = au.a.substring(n10, n16);
                            }
                            catch (StringIndexOutOfBoundsException ex2) {
                                s = au.a.substring(n10, n10);
                            }
                            graphics.drawString(s, n9, n6);
                            n9 += ((FontMetrics)fontMetrics).stringWidth(s);
                        }
                        final String substring = au.a.substring(n16, n17);
                        final int stringWidth = ((FontMetrics)fontMetrics).stringWidth(substring);
                        Color blue;
                        if (n18 == 255) {
                            blue = Color.blue;
                            graphics.setColor(blue);
                            graphics.drawLine(n9, n6 + 1, n9 + stringWidth, n6 + 1);
                        }
                        else {
                            blue = new Color(n18);
                        }
                        if (au.b) {
                            graphics.setColor(super.a.a.n);
                        }
                        else if (au.a && super.a.t) {
                            graphics.setColor(super.a.a.k);
                        }
                        else {
                            graphics.setColor(super.a.a.l);
                        }
                        if (n18 == 255) {
                            graphics.drawString(substring, n9 + 1, n6);
                            graphics.drawString(substring, n9 - 1, n6);
                            graphics.setColor(Color.blue);
                            graphics.drawString(substring, n9, n6);
                        }
                        graphics.setColor(blue);
                        if (a4 > 0 && r.a(super.a.d, 58)) {
                            final int length = substring.length();
                            int a5 = -1;
                            int n19 = 0;
                            while ((a5 = ae.a(substring, ++a5)) != -1 && a5 < length) {
                                b3 = true;
                                int index;
                                if ((index = substring.indexOf(32, a5 + 1)) == -1) {
                                    index = length;
                                }
                                final ae a6;
                                if ((a6 = ae.a(substring.substring(a5, index))) != null && a6.a != null && (au.h || n13 < (au.d ? super.a.K : super.a.H))) {
                                    ++n13;
                                    --a4;
                                    graphics.drawString(substring.substring(n19, a5), n9, n14);
                                    final int n20 = n9 + ((FontMetrics)fontMetrics).stringWidth(substring.substring(n19, a5) + " ");
                                    graphics.drawImage(a6.a, n20, n15, super.a);
                                    n9 = n20 + (16 + ((FontMetrics)fontMetrics).charWidth(' '));
                                    n19 = a5 + a6.d.length();
                                }
                                else {
                                    graphics.drawString(substring.substring(n19, index), n9, n14);
                                    n9 += ((FontMetrics)fontMetrics).stringWidth(substring.substring(n19, index));
                                    n19 = index;
                                }
                                if (a5 == -1 || a5 >= length) {
                                    break;
                                }
                            }
                            if (b3) {
                                graphics.drawString(substring.substring(n19, length), n9, n14);
                                n9 += ((FontMetrics)fontMetrics).stringWidth(substring.substring(n19, length));
                            }
                        }
                        else {
                            graphics.drawString(substring, n9, n6);
                        }
                        if (au.i != 0) {
                            graphics.setColor(new Color(au.i));
                        }
                        else {
                            graphics.setColor(super.a.a.i);
                        }
                        if (!b3) {
                            n9 += stringWidth;
                        }
                    }
                    n10 = n17;
                }
                if (b2 && n10 != n11) {
                    String s2 = au.a.substring(n10, n11);
                    if (n9 == 36) {
                        s2 = s2.trim();
                    }
                    if (a4 < 1 || !r.a(super.a.d, 58)) {
                        graphics.drawString(s2, n9, n6);
                    }
                    else {
                        final int length2 = s2.length();
                        int a7 = -1;
                        int n21 = 0;
                        for (int n22 = 0; n22 < a4 && (a7 = ae.a(s2, ++a7)) != -1 && a7 < length2; ++n22) {
                            int index2;
                            if ((index2 = s2.indexOf(32, a7 + 1)) == -1) {
                                index2 = length2;
                            }
                            final ae a8;
                            if ((a8 = ae.a(s2.substring(a7, index2))) != null && a8.a != null && (au.h || n13 < (au.d ? super.a.K : super.a.H))) {
                                ++n13;
                                graphics.drawString(s2.substring(n21, a7), n9, n14);
                                final int n23 = n9 + ((FontMetrics)fontMetrics).stringWidth(s2.substring(n21, a7) + " ");
                                graphics.drawImage(a8.a, n23, n15, super.a);
                                n9 = n23 + (16 + ((FontMetrics)fontMetrics).charWidth(' '));
                                n21 = a7 + a8.d.length();
                            }
                            else {
                                graphics.drawString(s2.substring(n21, index2), n9, n14);
                                n9 += ((FontMetrics)fontMetrics).stringWidth(s2.substring(n21, index2));
                                n21 = index2;
                            }
                        }
                        graphics.drawString(s2.substring(n21, length2), n9, n14);
                    }
                }
                n6 += height + n12;
            }
        }
        catch (Exception ex3) {}
    }
    
    public final synchronized void a(final aU au, final int n, final FontMetrics fontMetrics) {
        try {
            final String s = new String(au.a);
            int n2 = 0;
            au.a[0] = 0;
            au.b = 0;
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n");
            final Vector vector = new Vector<String>();
            while (stringTokenizer.hasMoreTokens()) {
                vector.addElement(stringTokenizer.nextToken());
            }
            final String[] array = new String[vector.size()];
            vector.copyInto(array);
            for (int i = 0; i < array.length; ++i) {
                final int[] array2;
                (array2 = new int[31])[0] = 0;
                int n3 = 0;
                if (i != array.length - 1) {
                    final StringBuffer sb = new StringBuffer();
                    final String[] array3 = array;
                    final int n4 = i;
                    array3[n4] = sb.append(array3[n4]).append("\n").toString();
                }
                int n5 = 0;
                int j;
                int length;
                do {
                    for (length = array[i].length(); n5 < length && array[i].charAt(n5) == ' '; ++n5) {}
                    int n6 = n5;
                    int n7;
                    j = (n7 = length);
                    while (j > n6) {
                        int n8 = 0;
                        if (r.a(super.a.d, 58)) {
                            n8 = (16 + 2 * fontMetrics.charWidth(' ')) * ae.a(array[i].substring(n5, n7)) - ae.a(array[i].substring(n5, n7), fontMetrics);
                        }
                        final int n9;
                        if ((n9 = fontMetrics.stringWidth(array[i].substring(n5, n7)) + n8) <= n && n7 == length) {
                            break;
                        }
                        if (n9 < n) {
                            n6 = n7 + 1;
                        }
                        else {
                            j = n7;
                        }
                        n7 = (n6 + j) / 2;
                    }
                    final int lastIndex;
                    if ((lastIndex = array[i].lastIndexOf(32, n7)) < n5) {
                        n5 = n7;
                    }
                    else {
                        n5 = lastIndex;
                    }
                    array2[++n3] = n5;
                } while (n3 < 30 && j < length);
                array2[n3] = length;
                for (int n10 = 1; n10 <= n3 && au.b < 30; ++n10) {
                    au.a[++au.b] = array2[n10] + n2;
                }
                n2 += array2[n3];
            }
            au.a = "";
            for (int k = 0; k < array.length; ++k) {
                au.a += array[k];
            }
            int n11 = 0;
            if (16 > fontMetrics.getHeight()) {
                for (int l = 0; l < au.b; ++l) {
                    if (ae.a(au.a.substring(au.a[l], au.a[l + 1])) > 0) {
                        n11 += 16 - fontMetrics.getHeight();
                    }
                }
            }
            if (au.f && au.k != 0 && au.c != null) {
                n11 += 25 - fontMetrics.getHeight();
            }
            au.a = 6 + fontMetrics.getHeight() * (au.b + 1) + n11;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final String a(final Object o) {
        if (super.b) {
            return aS.a(471);
        }
        return aS.a(472);
    }
    
    public aR(final cs cs, final boolean b) {
        super(cs, b);
    }
}
