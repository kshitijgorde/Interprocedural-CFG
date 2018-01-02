// 
// Decompiled by Procyon v0.5.30
// 

package ji.image;

import java.awt.Rectangle;
import ji.util.jiPrinti;
import java.awt.PrintJob;
import java.awt.Color;
import ji.util.e;
import java.awt.Graphics;
import ji.util.d;
import ji.document.cw;
import java.awt.print.PageFormat;
import java.awt.Dimension;
import java.awt.print.PrinterJob;
import java.awt.print.Printable;

public class ny implements Printable
{
    boolean a;
    int b;
    int c;
    int d;
    int e;
    int f;
    int g;
    int h;
    PrinterJob i;
    Dimension j;
    PageFormat k;
    cw l;
    double m;
    boolean n;
    boolean o;
    boolean p;
    String q;
    
    public ny(final cw l, final PrinterJob i, final boolean a, final int b, final Dimension j, final PageFormat k, final int g, final boolean n, final boolean o, final boolean p11, final String q) {
        this.a = false;
        this.b = 0;
        this.c = -1;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = 1.2;
        this.n = false;
        this.o = false;
        this.p = false;
        this.q = null;
        this.q = q;
        this.l = l;
        this.i = i;
        this.a = a;
        this.b = b;
        this.j = j;
        this.k = k;
        this.g = g;
        this.n = n;
        this.o = o;
        this.p = p11;
        this.m = ji.util.d.a(System.getProperty("java.specification.version"), 1.2);
    }
    
    public int print(final Graphics graphics, final PageFormat pageFormat, final int c) {
        try {
            if (this.l.r) {
                this.i.cancel();
                return 1;
            }
            if (c > this.b - 1) {
                return 1;
            }
            cy cy;
            if (this.p) {
                cy = this.l.p;
            }
            else {
                cy = this.l.o;
            }
            if (cy != null && cy.bd() && !this.l.ex()) {
                try {
                    this.i.cancel();
                }
                catch (Exception ex2) {}
                return 1;
            }
            if (this.m < 1.3) {
                cy.a7(false);
            }
            boolean b = true;
            if (ji.util.d.ay(this.q) && ji.util.e.av() && !ji.util.d.dp()) {
                b = false;
            }
            if (b) {
                graphics.translate((int)pageFormat.getImageableX(), (int)pageFormat.getImageableY());
            }
            this.j = new Dimension((int)pageFormat.getImageableWidth(), (int)pageFormat.getImageableHeight());
            graphics.setColor(Color.black);
            int n;
            if (this.k.getOrientation() == 1) {
                n = 0;
            }
            else {
                n = 90;
            }
            if (c != this.c || !ji.util.d.ae()) {
                this.d = 0;
                this.c = c;
                if (!this.n || this.p) {
                    cy.d(this.g, 2);
                }
                final Dimension em = cy.em();
                if (!this.n || this.p) {
                    cy.setBounds(0, 0, em.width, em.height);
                }
                ++ji.util.d.bs;
                cy.a(ji.util.d.ae(), this.j);
                ji.image.cy.az(true);
                cy.ce();
                cy.a(null, null, graphics, 72, this.j, true, this.a, n, null, this.o);
                this.l.a(3, String.valueOf(String.valueOf(new StringBuffer("printpage: page ").append(c + 1).append(" of ").append(this.b))), false);
            }
            else {
                final Rectangle clipBounds = graphics.getClipBounds();
                if (clipBounds.y < 50) {
                    cy.ce();
                }
                cy.a(graphics, clipBounds, this.j);
            }
            try {
                graphics.dispose();
            }
            catch (Exception ex3) {}
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
}
