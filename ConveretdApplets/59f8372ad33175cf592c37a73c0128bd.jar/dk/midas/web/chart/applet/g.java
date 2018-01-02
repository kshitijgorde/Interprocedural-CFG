// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import dk.midas.web.chart.applet.d.c;
import java.awt.Font;
import java.awt.Color;
import dk.midas.web.chart.applet.d.d;

public class g implements d
{
    protected int a9;
    protected int a8;
    protected String bh;
    protected String ba;
    public boolean be;
    public String bo;
    public String bl;
    public String bp;
    public String bd;
    public int bk;
    public int bq;
    public int bf;
    public int bb;
    public boolean bg;
    public int bm;
    public int bc;
    public boolean bj;
    public Color bn;
    public Color a7;
    public Font bi;
    
    public static g a(final boolean b, final c c) {
        final g g = b ? new b() : new a();
        g.a(c);
        return g;
    }
    
    protected g(final int a9, final int a10, final String bh, final String ba) {
        this.a9 = a9;
        this.a8 = a10;
        this.bh = bh;
        this.ba = ba;
    }
    
    public int int() {
        return this.be ? this.bk : 0;
    }
    
    protected void a(final c c) {
        this.be = c.a("UseMenuBar", true);
        this.bl = c.getParameter("MenuBar");
        this.bp = c.getParameter("menu_icons");
        this.bd = c.getParameter("DisableMenuItems");
        this.bq = c.if("MenuLeftGap", 0);
        this.bm = c.if("MenuButtonsWidth", 60);
        this.bc = c.if("MenuButtonsHeight", 20);
        this.bg = c.a("MenuButtonsAutoSize", true);
        this.bj = c.a("MenuGrayScaleIcons", true);
        this.bo = c.a("MenuButtonLayout", this.bh);
        this.bn = c.for("MenuBackground", this.ba);
        this.a7 = c.for("MenuForeground", "000000");
        this.bf = c.if("MenuHorizontalSpacing", 2);
        this.bb = c.if("MenuVerticalSpacing", 2);
        this.bi = c.do("MenuFonts", "Dialog;11;PLAIN");
        this.bk = c.if("MenuHeight", this.a9);
    }
    
    private static class b extends g
    {
        protected b() {
            super(18, 14, "horizontal", "CCCCCC");
        }
    }
    
    private static class a extends g
    {
        protected a() {
            super(25, 20, "vertical", "C0C0C0");
        }
    }
}
