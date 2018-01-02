// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.util.Vector;
import dk.midas.web.chart.applet.d.c;
import java.awt.Color;
import dk.midas.web.chart.applet.d.d;

public class au implements d
{
    public boolean bA;
    public Color bs;
    public Color by;
    public int bB;
    public int bv;
    public String[] bz;
    public String[] bx;
    protected int br;
    protected String bu;
    protected String bt;
    protected int bw;
    
    public static au if(final boolean b, final c c) {
        final au au = b ? new b() : new a();
        au.if(c);
        return au;
    }
    
    protected au(final int br, final String bu, final String bt, final int bw) {
        this.br = br;
        this.bu = bu;
        this.bt = bt;
        this.bw = bw;
    }
    
    public int new() {
        return this.bA ? this.bB : 0;
    }
    
    protected void if(final c c) {
        this.bA = c.a("UseFooter", true);
        this.bs = c.for("FooterBackground", this.bu);
        this.by = c.for("FooterForeground", this.bt);
        this.bB = c.if("FooterHeight", this.br);
        this.bv = c.if("FooterLeftGap", this.bw);
        final Vector vector = new Vector<String>();
        final Vector<String> vector2 = new Vector<String>();
        int n = 1;
        while (true) {
            final String a = c.a("Button" + n, null);
            if (a == null) {
                break;
            }
            vector.addElement(a);
            vector2.addElement(c.a("ButtonTip" + n, null));
            ++n;
        }
        vector.copyInto(this.bz = new String[vector.size()]);
        vector2.copyInto(this.bx = new String[this.bz.length]);
    }
    
    private static class b extends au
    {
        public b() {
            super(17, "CCCCCC", "000000", 2);
        }
    }
    
    private static class a extends au
    {
        public a() {
            super(20, "C0C0C0", "000000", 0);
        }
    }
}
