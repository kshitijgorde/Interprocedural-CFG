// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import dk.midas.web.chart.applet.d.c;
import java.awt.Color;
import dk.midas.web.chart.applet.d.d;

public class bi implements d
{
    protected String g7;
    protected String g6;
    public Color gY;
    public Color gW;
    public Color gN;
    public Color gM;
    public boolean gz;
    public int gV;
    public Color gG;
    public Color gE;
    public int gw;
    public Color gB;
    public Color gT;
    public Color gI;
    public Color gO;
    public Color ha;
    public Color gX;
    public Color gP;
    public Color g2;
    public Color gy;
    public Color g1;
    public Color gS;
    public Color g9;
    public Color g3;
    public Color gC;
    public Color[] g8;
    private static final String gA = "000000";
    private static final String gF = "000000";
    private static final String g5 = "FF0000";
    private static final String gx = "FF0000";
    private static final String g0 = "FF0000";
    private static final String gL = "000000";
    private static final String gU = "000000";
    private static final String gH = "000000";
    private static final String gJ;
    private static final String gR;
    private static final String gZ = "FF0000";
    private static final String gQ = "00FF00";
    private static final boolean gK = true;
    private static final int gD = 10;
    private static final int g4 = 10;
    
    public static bi do(final boolean b, final c c) {
        final bi bi = b ? new b() : new a();
        bi.do(c);
        return bi;
    }
    
    protected bi(final String g7, final String g8) {
        this.gI = null;
        this.gO = null;
        this.gC = null;
        this.g8 = null;
        this.g7 = g7;
        this.g6 = g8;
    }
    
    protected void do(final c c) {
        this.gY = c.for("background", this.g7);
        this.gW = c.for("foreground", this.g6);
        this.gN = c.for("chart_area_background1", this.g7);
        this.gM = c.for("chart_area_background2", this.g7);
        this.gz = c.a("chart_area_static_rows", true);
        this.gV = c.if("chart_area_rows", 10);
        this.gG = c.for("studies_area_background1", this.g7);
        this.gE = c.for("studies_area_background2", this.g7);
        this.gw = c.if("studies_area_rows", 10);
        this.gB = c.for("UpCandleColor", null);
        this.gT = c.for("DownCandleColor", null);
        this.gI = c.for("UpCandleFrameColor", null);
        this.gO = c.for("DownCandleFrameColor", null);
        this.ha = c.for("FillColor", this.g7);
        this.gX = c.for("TrendlineColor", "000000");
        this.g2 = c.for("axis_font_color", "000000");
        this.gP = c.for("axis_line_color", "000000");
        this.gy = c.for("axis_markers_color", "FF0000");
        this.g1 = c.for("price_line_color", "FF0000");
        this.gS = c.for("neutral_line_color", "FF0000");
        this.g9 = c.for("chart_line_color", "000000");
        this.g3 = c.for("studies_line_color", "000000");
    }
    
    static {
        gJ = null;
        gR = null;
    }
    
    private static class b extends bi
    {
        public b() {
            super("EEEEEE", "000000");
        }
    }
    
    private static class a extends bi
    {
        public a() {
            super("C0C0C0", "000000");
        }
    }
}
