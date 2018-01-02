// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.util.Vector;
import java.awt.FontMetrics;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

class j
{
    void a(final Graph graph, final Graphics graphics) {
        final boolean g = GraphSerie.G;
        graphics.setFont(new Font(a("0$d]|\r*iWk"), 1, 10));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int n = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
        final Vector series = graph.getSeries();
        Label_0455: {
            if (series.size() == 1 && series.elementAt(0) instanceof PieSerie) {
                if (series.elementAt(0).getValues().length > 6) {
                    graphics.setColor(Color.white);
                    graphics.fillRect(0, 0, graph.getSize().width, graph.getSize().height);
                    graphics.setColor(Color.black);
                    graphics.drawString(a("4%*Fg\u0018k~@f\u001c'*Dj\u000f8c]aQkz[j]kyW}\u0014.y\u0012l\u001c%d]{"), 20, 20);
                    graphics.drawString(a("\u0015*dVc\u0018k*_`\u000f.*\u0012{\u0015*d\u0012/Kk*\u0012|\u0011\"iW|Sk*t`\u000fk*Sa"), 20, 20 + n + fontMetrics.getLeading());
                    graphics.drawString(a("\b%f[b\u0014?oV/\u000b.xAf\u0012%*]i]\u0001oFL\u0015*xF#];fSl\u0018k*Sa]"), 20, 20 + 2 * (n + fontMetrics.getLeading()));
                    graphics.drawString(a("\u00129nW}]*~\u0012g\t?z\b R<}E!\u0017\"dAf\u001a#~\u001cl\u0012&$"), 20, 20 + 3 * (n + fontMetrics.getLeading()));
                    graph.removeMouseListener(graph);
                    graph.removeMouseMotionListener(graph);
                    return;
                }
                if (!g) {
                    break Label_0455;
                }
            }
            int n2 = 0;
            while (true) {
                Label_0445: {
                    if (!g) {
                        break Label_0445;
                    }
                    boolean b;
                    if (series.elementAt(n2) instanceof OHLCSerie) {
                        b = (series.elementAt(n2).getMultipleValues().length > 10);
                    }
                    else {
                        b = (series.elementAt(n2).getValues().length > 10);
                    }
                    if (b) {
                        graphics.setColor(Color.white);
                        graphics.fillRect(0, 0, graph.getSize().width, graph.getSize().height);
                        graphics.setColor(Color.black);
                        graphics.drawString(a("4%*\u0012{\u0015.*F}\u0014*f\u0012/\u000b.xAf\u0012%&\u0012|\u00189cW|]kiSa\u0013$~"), 20, 20);
                        graphics.drawString(a("\u0015*dVc\u0018kg]}\u0018k~Zn\u0013k;\u0002/\u001e$e@k\u0014%kFj\u000eeL]}]*d"), 20, 20 + n + fontMetrics.getLeading());
                        graphics.drawString(a("\b%f[b\u0014?oV/\u000b.xAf\u0012%*\u0012`\u001bk*xj\t\bbS}\tg*Bc\u001c(o\u0012"), 20, 20 + 2 * (n + fontMetrics.getLeading()));
                        graphics.drawString(a("\u001c%*]}\u0019.x\u0012n\tkbF{\rq%\u001dx\n<$Xf\u00138cUg\tei]bS"), 20, 20 + 3 * (n + fontMetrics.getLeading()));
                        graph.removeMouseListener(graph);
                        graph.removeMouseMotionListener(graph);
                        return;
                    }
                    ++n2;
                }
                if (n2 < series.size()) {
                    continue;
                }
                break;
            }
        }
        final int n3 = (graph.getSize().width - graphics.getFontMetrics().stringWidth(a(")\u0003O\u0012E8\u001fIzN/\u001f*~F?\u0019K`V]f*f]4\nF\u0012Y8\u0019Y{@3"))) / 2;
        int n4 = (graph.getSize().height / 2 - n) / 2 + n + 20;
        int n5 = 0;
        while (true) {
            while (true) {
                Label_0582: {
                    if (!g) {
                        break Label_0582;
                    }
                    Label_0557: {
                        if (n5 == 0) {
                            graphics.setColor(Color.black);
                            if (!g) {
                                break Label_0557;
                            }
                        }
                        if (n5 == 1) {
                            graphics.setColor(Color.yellow);
                            if (!g) {
                                break Label_0557;
                            }
                        }
                        if (n5 == 2) {
                            graphics.setColor(Color.gray);
                        }
                    }
                    graphics.drawString(a(")\u0003O\u0012E8\u001fIzN/\u001f*~F?\u0019K`V]f*f]4\nF\u0012Y8\u0019Y{@3"), n3, n4);
                    n4 += n + 5;
                    ++n5;
                }
                if (n5 < 3) {
                    continue;
                }
                break;
            }
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
                            c2 = '}';
                            break;
                        }
                        case 1: {
                            c2 = 'K';
                            break;
                        }
                        case 2: {
                            c2 = '\n';
                            break;
                        }
                        case 3: {
                            c2 = '2';
                            break;
                        }
                        default: {
                            c2 = '\u000f';
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
