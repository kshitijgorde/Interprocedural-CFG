// 
// Decompiled by Procyon v0.5.30
// 

package ji.awt;

import ji.util.d;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import ji.util.e;
import java.awt.LayoutManager;
import ji.document.ad;
import java.awt.event.ActionListener;
import ji.graphic.do;
import ji.v1base.jiPanel;

public class dn extends jiPanel
{
    private do a;
    private us b;
    private String c;
    private boolean d;
    
    public final void a(final ActionListener actionListener) {
        this.b.addActionListener(actionListener);
    }
    
    public dn(final ad ad, final String c) {
        super(c, null);
        this.d = false;
        this.c = c;
        this.a = new do(ad.a9(), e.aq(), c, false, true);
        this.b = new us(c, 1298, ad);
        this.setBorderStyle(0);
        this.setLayout(null);
        this.add(this.a);
        this.add(this.b);
    }
    
    public final void a(final int n) {
        this.a.a(n);
    }
    
    public final void a() {
        this.a.e();
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        super.setBounds(n, n2, n3, n4);
        this.a.setBounds(0, 0, n3 - 18, n4);
        this.b.setBounds(n3 - 18, 4, 16, n4 - 4);
    }
    
    public int b() {
        return this.a.b() + this.b.getPreferredSize().width;
    }
    
    public int c() {
        return this.a.c();
    }
    
    public Dimension getPreferredSize() {
        final Dimension dimension = new Dimension();
        dimension.width = this.b();
        dimension.height = this.c();
        return dimension;
    }
    
    class us extends dm
    {
        public us(final dn dn, final String s, final int n, final ad ad) {
            super("searchcancel", s, n, ad);
        }
        
        private Image b(final Color color) {
            final int width = this.l().width;
            final int height = this.l().height;
            final Image a = this.a(width, height);
            final Graphics graphics = a.getGraphics();
            graphics.setColor(color);
            graphics.fillRect(0, 0, width, height);
            int n2;
            final int n = n2 = 1;
            int n3 = width - n2 - 1 - n;
            int n4 = n;
            int n5 = height - n4 - 1 - n;
            graphics.setColor(Color.red);
            graphics.drawLine(n2, n4, n3, n4);
            graphics.drawLine(n3, n4, n3, n5);
            graphics.drawLine(n2, n5, n3, n5);
            graphics.drawLine(n2, n4, n2, n5);
            graphics.setColor(Color.white);
            ++n2;
            --n3;
            ++n4;
            --n5;
            graphics.drawLine(n2, n4, n3, n4);
            graphics.drawLine(n3, n4, n3, n5);
            graphics.drawLine(n2, n5, n3, n5);
            graphics.drawLine(n2, n4, n2, n5);
            graphics.setColor(Color.red);
            ++n2;
            ++n4;
            graphics.fillRect(n2, n4, n3 - n2, n5 - n4);
            return a;
        }
        
        public Image m() {
            return this.a(this.b(d.h), d.h);
        }
    }
}
