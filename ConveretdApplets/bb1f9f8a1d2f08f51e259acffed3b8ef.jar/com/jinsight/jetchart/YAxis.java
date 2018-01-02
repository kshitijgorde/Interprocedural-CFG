// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.util.Vector;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.util.Observable;
import java.text.DecimalFormat;

public class YAxis extends Axis
{
    private DecimalFormat t;
    
    public void update(final Observable observable, final Object o) {
        final p bi = super.k.bI;
        super.j = (Graphics)o;
        final Rectangle k = bi.k();
        super.e = k.x;
        super.f = k.y;
        super.h = k.height;
        this.a();
    }
    
    void a() {
        final boolean g = GraphSerie.G;
        final Color color = super.j.getColor();
        super.j.setColor(super.c);
        final Font font = super.j.getFont();
        super.j.setFont(super.n);
        final p bi = super.k.bI;
        final boolean bf = super.k.bf;
        super.j.drawLine(super.e, super.f, super.e, super.f + super.h);
        if (super.k.bc) {
            final int n = (super.k.bf ? super.k.p : super.k.o) * bi.q();
            final int[] array = { super.e, super.e + n, super.e + n, super.e };
            final int[] array2 = { super.f + super.h, super.f + super.h - n, super.f - n, super.f };
            super.j.setColor(super.d);
            super.j.fillPolygon(array, array2, 4);
            super.j.setColor(Color.black);
            super.j.drawPolygon(array, array2, 4);
            super.j.setColor(super.c);
            if (!bf && super.k.Y) {
                final int[] array3 = (int[])bi.n()[0];
                final Rectangle k = bi.k();
                super.j.setColor(super.k.getGrid().a());
                int n2 = 0;
                while (true) {
                    Label_0381: {
                        if (!g) {
                            break Label_0381;
                        }
                        super.j.drawLine(k.x, array3[n2], k.x + n, array3[n2] - n);
                        ++n2;
                    }
                    if (n2 < array3.length) {
                        continue;
                    }
                    break;
                }
            }
            else if (bf && super.k.bH.d) {
                final int[] e = bi.e();
                final Rectangle i = bi.k();
                super.j.setColor(super.k.getGrid().a());
                int n3 = 0;
                while (true) {
                    Label_0487: {
                        if (!g) {
                            break Label_0487;
                        }
                        super.j.drawLine(i.x, e[n3], i.x + n, e[n3] - n);
                        ++n3;
                    }
                    if (n3 < e.length) {
                        continue;
                    }
                    break;
                }
            }
        }
        this.a(super.j);
        super.j.setFont(font);
        super.j.setColor(color);
    }
    
    void a(final Graphics graphics) {
        if (super.o != null) {
            final Image a = this.a(1);
            final Vector i = super.k.i;
            if (!super.k.c()) {
                graphics.drawImage(a, super.k.k + super.k.bG.j(), (super.h - a.getHeight(null)) / 2 + super.f, null);
                if (!GraphSerie.G) {
                    return;
                }
            }
            final Rectangle o = i.elementAt(0).o;
            graphics.drawImage(a, super.k.k + super.k.bG.j(), (o.height - a.getHeight(null)) / 2 + o.y, null);
        }
    }
    
    YAxis(final Graph graph) {
        super(graph);
        super.d = Color.lightGray;
    }
}
