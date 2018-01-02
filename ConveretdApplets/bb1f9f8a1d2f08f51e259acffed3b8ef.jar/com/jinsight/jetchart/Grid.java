// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.awt.Rectangle;
import java.util.Observable;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Observer;

public class Grid implements Observer
{
    private Graph a;
    private Graphics b;
    private Color c;
    boolean d;
    
    public void setColor(final Color c) {
        this.c = c;
    }
    
    public void setCrossedLinesEnabled(final boolean d) {
        this.d = d;
    }
    
    public void update(final Observable observable, final Object o) {
        this.b = (Graphics)o;
        this.b();
    }
    
    Color a() {
        return this.c;
    }
    
    void b() {
        final boolean g = GraphSerie.G;
        final Color color = this.b.getColor();
        this.b.setColor(this.c);
        final p bi = this.a.bI;
        final boolean bf = this.a.bf;
        final String[] u = bi.u();
        final int[] array = (int[])bi.n()[0];
        int[] e = null;
        if (this.d) {
            e = bi.e();
        }
        int n = 0;
        if (this.a.bc) {
            n = (bf ? this.a.p : this.a.o) * bi.q();
        }
        Label_0466: {
            if (!bf) {
                final Rectangle j = bi.j();
                int n2 = 0;
                while (true) {
                    while (true) {
                        Label_0193: {
                            if (!g) {
                                break Label_0193;
                            }
                            final Grid grid = this;
                            grid.b.drawLine(j.x + n, array[n2] - n, j.x + j.width + n, array[n2] - n);
                            ++n2;
                        }
                        if (n2 < array.length) {
                            continue;
                        }
                        break;
                    }
                    final Grid grid = this;
                    if (g) {
                        continue;
                    }
                    break;
                }
                if (this.d) {
                    final Rectangle k = bi.k();
                    int n3 = 0;
                    while (true) {
                        Label_0288: {
                            if (!g) {
                                break Label_0288;
                            }
                            if (u[n3] != null || g) {
                                this.b.drawLine(e[n3] + n, k.y - n, e[n3] + n, k.y - n + k.height);
                            }
                            ++n3;
                        }
                        if (n3 < e.length) {
                            continue;
                        }
                        break;
                    }
                }
                if (!g) {
                    break Label_0466;
                }
            }
            final Rectangle i = bi.k();
            int n4 = 0;
            while (true) {
                while (true) {
                    Label_0363: {
                        if (!g) {
                            break Label_0363;
                        }
                        final Grid grid2 = this;
                        grid2.b.drawLine(array[n4] + n, i.y + i.height - n, array[n4] + n, i.y - n);
                        ++n4;
                    }
                    if (n4 < array.length) {
                        continue;
                    }
                    break;
                }
                final Grid grid2 = this;
                if (g) {
                    continue;
                }
                break;
            }
            if (this.d) {
                final Rectangle l = bi.j();
                int n5 = 0;
                while (true) {
                    Label_0458: {
                        if (!g) {
                            break Label_0458;
                        }
                        if (u[n5] != null || g) {
                            this.b.drawLine(l.x + n, e[n5] - n, l.x + n + l.width, e[n5] - n);
                        }
                        ++n5;
                    }
                    if (n5 < e.length) {
                        continue;
                    }
                    break;
                }
            }
        }
        this.b.setColor(color);
    }
    
    Grid(final Graph a) {
        this.c = Color.black;
        this.a = a;
    }
}
