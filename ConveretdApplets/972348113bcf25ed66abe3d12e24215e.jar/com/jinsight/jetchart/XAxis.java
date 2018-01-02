// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.util.Observable;

public class XAxis extends Axis
{
    public void update(final Observable observable, final Object o) {
        final p bi = super.k.bI;
        super.j = (Graphics)o;
        final Rectangle j = bi.j();
        super.e = j.x;
        super.g = j.width;
        super.f = j.y;
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
        super.j.drawLine(super.e, super.f, super.e + super.g, super.f);
        if (super.k.bc) {
            final int n = (bf ? super.k.p : super.k.o) * bi.q();
            final int[] array = { super.e, super.e + n, super.e + super.g + n, super.e + super.g };
            final int[] array2 = { super.f, super.f - n, super.f - n, super.f };
            super.j.setColor(super.d);
            super.j.fillPolygon(array, array2, 4);
            super.j.setColor(Color.black);
            super.j.drawPolygon(array, array2, 4);
            if (bf && super.k.Y) {
                final int[] array3 = (int[])bi.n()[0];
                final Rectangle j = bi.j();
                super.j.setColor(super.k.getGrid().a());
                int n2 = 0;
                while (true) {
                    Label_0365: {
                        if (!g) {
                            break Label_0365;
                        }
                        super.j.drawLine(array3[n2], j.y, array3[n2] + n, j.y - n);
                        ++n2;
                    }
                    if (n2 < array3.length) {
                        continue;
                    }
                    break;
                }
            }
            else if (!bf && super.k.bH.d) {
                final int[] e = bi.e();
                final Rectangle i = bi.j();
                super.j.setColor(super.k.getGrid().a());
                int n3 = 0;
                while (true) {
                    Label_0471: {
                        if (!g) {
                            break Label_0471;
                        }
                        super.j.drawLine(e[n3], i.y, e[n3] + n, i.y - n);
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
        super.j.setColor(color);
        super.j.setFont(font);
    }
    
    void a(final Graphics graphics) {
        if (super.o != null) {
            final Image a = this.a(0);
            boolean v = false;
            if (super.k.bI != null) {
                v = super.k.bI.v();
            }
            final int n = v ? 0 : super.k.bG.h();
            if (!super.k.c()) {
                graphics.drawImage(a, (super.g - a.getWidth(null)) / 2 + super.e, super.k.getSize().height - super.k.n - a.getHeight(null) - n, null);
                if (!GraphSerie.G) {
                    return;
                }
            }
            final Rectangle o = super.k.i.elementAt(0).o;
            graphics.drawImage(a, (o.width - a.getWidth(null)) / 2 + o.x, super.k.getSize().height - super.k.n - a.getHeight(null) - n, null);
        }
    }
    
    XAxis(final Graph graph) {
        super(graph);
        super.d = Color.lightGray;
    }
}
