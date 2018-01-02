// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

class k
{
    void a(final Graph graph, final Graphics graphics) {
        final boolean g = GraphSerie.G;
        final Color color = graphics.getColor();
        graphics.setFont(new Font(a("f%\u0018=e[+\u00157r"), 1, 10));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int n = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
        final int n2 = (graph.getSize().width - graphics.getFontMetrics().stringWidth(a("\u007f\u00023r\\n\u001e5\u001aWy\u001eV\u001e_i\u00187\u0000O\u000bgV\u0006Db\u000b:r@n\u0018%\u001bYe"))) / 2;
        int n3 = (graph.getSize().height / 2 - n) / 2 + n + 20;
        int n4 = 0;
        while (true) {
            while (true) {
                Label_0175: {
                    if (!g) {
                        break Label_0175;
                    }
                    Label_0150: {
                        if (n4 == 0) {
                            graphics.setColor(Color.black);
                            if (!g) {
                                break Label_0150;
                            }
                        }
                        if (n4 == 1) {
                            graphics.setColor(Color.yellow);
                            if (!g) {
                                break Label_0150;
                            }
                        }
                        if (n4 == 2) {
                            graphics.setColor(Color.gray);
                        }
                    }
                    graphics.drawString(a("\u007f\u00023r\\n\u001e5\u001aWy\u001eV\u001e_i\u00187\u0000O\u000bgV\u0006Db\u000b:r@n\u0018%\u001bYe"), n2, n3);
                    n3 += n + 5;
                    ++n4;
                }
                if (n4 < 3) {
                    continue;
                }
                break;
            }
            graphics.setColor(color);
            if (!g) {
                return;
            }
            continue;
        }
    }
    
    private static String a(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
        while (true) {
            Label_0089: {
                if (length > 1) {
                    break Label_0089;
                }
                char[] array2;
                char[] array = array2 = charArray;
                int n3;
                int n2 = n3 = n;
                while (true) {
                    final char c = array2[n3];
                    char c2 = '\0';
                    switch (n % 5) {
                        case 0: {
                            c2 = '+';
                            break;
                        }
                        case 1: {
                            c2 = 'J';
                            break;
                        }
                        case 2: {
                            c2 = 'v';
                            break;
                        }
                        case 3: {
                            c2 = 'R';
                            break;
                        }
                        default: {
                            c2 = '\u0016';
                            break;
                        }
                    }
                    array[n2] = (char)(c ^ c2);
                    ++n;
                    if (length != 0) {
                        break;
                    }
                    array = (array2 = charArray);
                    n2 = (n3 = length);
                }
            }
            if (n >= length) {
                return new String(charArray);
            }
            continue;
        }
    }
}
