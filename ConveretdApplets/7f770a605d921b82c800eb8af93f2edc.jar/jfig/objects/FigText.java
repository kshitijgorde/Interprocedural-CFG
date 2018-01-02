// 
// Decompiled by Procyon v0.5.30
// 

package jfig.objects;

import java.awt.event.KeyEvent;
import jfig.gui.ColorCache;
import java.awt.Color;
import java.awt.Point;
import jfig.canvas.FigTrafo2D;
import java.awt.Graphics;
import jfig.utils.LP2;
import jfig.utils.SetupManager;
import jfig.gui.FontCache;
import java.awt.Polygon;
import java.awt.FontMetrics;

public class FigText extends FigBaseobject
{
    private static char[] recode_table;
    int textCursorIndex;
    boolean showCursorFlag;
    String str;
    FontMetrics fm;
    FigRenderer renderer;
    Polygon border;
    
    public void createRenderer() {
        this.renderer = FigObjectFactory.getDefaultObjectFactory().createTextRenderer(this);
    }
    
    public boolean isShowCursor() {
        return this.showCursorFlag;
    }
    
    public int getTextCursorIndex() {
        return this.textCursorIndex;
    }
    
    public void rebuild() {
        this.update_bbox();
        if (this.renderer != null) {
            this.renderer.rebuild();
        }
    }
    
    public void update_bbox() {
        try {
            this.fm = FontCache.getFontCache().getFontMetrics(this.attribs.fig_font, (int)(this.attribs.fontSize * this.trafo.getZoom()));
            final int stringWidth = this.fm.stringWidth(this.str);
            final int ascent = this.fm.getAscent();
            final int descent = this.fm.getDescent();
            double n = 0.0;
            double n2 = 0.0;
            final double n3 = this.trafo.screen_to_wc(stringWidth);
            double n4 = -this.trafo.screen_to_wc(ascent);
            double n5 = this.trafo.screen_to_wc(descent);
            final double n6 = -this.attribs.fig_angle;
            if (this.attribs.textAlign == 1) {
                n = 0.0;
                n2 = n3;
            }
            else if (this.attribs.textAlign == 3) {
                n = -n3;
                n2 = 0.0;
            }
            else if (this.attribs.textAlign == 2) {
                n = -n3 / 2.0;
                n2 = n3 / 2.0;
            }
            if ((this.attribs.fig_font_flags & 0x2) != 0x0 && SetupManager.getBoolean("jfig.showTeXStrings", false)) {
                if (this.debug) {
                    System.out.println("-I- FigText: measuring TeX-mode text '" + this.str + "'");
                }
                try {
                    final double[] texModeBoundingBox = this.getTexModeBoundingBox();
                    n = texModeBoundingBox[0];
                    n2 = texModeBoundingBox[1];
                    final double n7 = texModeBoundingBox[2];
                    n4 = texModeBoundingBox[3];
                    n5 = texModeBoundingBox[4];
                    if (this.debug) {
                        System.out.println("   lrw= " + n + " " + n2 + " " + n7 + " ad= " + n4 + " " + n5);
                    }
                }
                catch (Throwable t) {
                    if (this.debug) {
                        System.out.println("-W- FigText: getTexModeBoundingBox() failed.");
                        t.printStackTrace();
                    }
                }
            }
            final double cos = Math.cos(n6);
            final double sin = Math.sin(n6);
            final double n8 = this.x + n * cos - n4 * sin;
            final double n9 = this.y + n * sin + n4 * cos;
            final double n10 = this.x + n * cos - n5 * sin;
            final double n11 = this.y + n * sin + n5 * cos;
            final double n12 = this.x + n2 * cos - n5 * sin;
            final double n13 = this.y + n2 * sin + n5 * cos;
            final double n14 = this.x + n2 * cos - n4 * sin;
            final double n15 = this.y + n2 * sin + n4 * cos;
            this.bbox = new FigBbox(this.min(n8, n10, n12, n14), this.min(n9, n11, n13, n15), this.max(n8, n10, n12, n14), this.max(n9, n11, n13, n15));
            (this.border = new Polygon()).addPoint((int)n8, (int)n9);
            this.border.addPoint((int)n10, (int)n11);
            this.border.addPoint((int)n12, (int)n13);
            this.border.addPoint((int)n14, (int)n15);
            super.build_sc_bbox();
            this.timestamp = System.currentTimeMillis();
        }
        catch (Throwable t2) {
            System.err.println("-E- FigText.update_bbox: internal error: " + t2);
            t2.printStackTrace();
        }
    }
    
    private final int min(final double n, final double n2, final double n3, final double n4) {
        return (int)Math.min(Math.min(n, n2), Math.min(n3, n4));
    }
    
    private final int max(final double n, final double n2, final double n3, final double n4) {
        return (int)Math.max(Math.max(n, n2), Math.max(n3, n4));
    }
    
    private double[] getTexModeBoundingBox() {
        final LP2 lp2 = new LP2();
        lp2.setFontIndex(this.attribs.fig_font);
        lp2.setFontPtSize(this.attribs.fontSize);
        lp2.setColorIndex(this.attribs.fig_line_color);
        lp2.setEnableDisplayBoxes(false);
        lp2.setEnableDumpTokens(false);
        lp2.parse(this.str);
        final FigCompound figCompound = new FigCompound();
        figCompound.setTrafo(this.getTrafo());
        lp2.convertToFig(figCompound);
        figCompound.update_bbox();
        final FigBbox bbox = figCompound.getBbox();
        double n = 0.0;
        double n2 = 0.0;
        final double n3 = bbox.getXr() - bbox.getXl();
        final double n4 = bbox.getYt();
        final double n5 = bbox.getYb();
        if (this.attribs.textAlign == 1) {
            n = 0.0;
            n2 = n3;
        }
        else if (this.attribs.textAlign == 3) {
            n = -n3;
            n2 = 0.0;
        }
        else if (this.attribs.textAlign == 2) {
            n = -n3 / 2.0;
            n2 = n3 / 2.0;
        }
        return new double[] { n, n2, n3, n4, n5 };
    }
    
    public void paint(final Graphics graphics) {
        if (this.timestamp < this.trafo.getTimestamp()) {
            this.rebuild();
        }
        if (this.renderer != null) {
            this.renderer.paint(graphics);
        }
    }
    
    public void paint(final Graphics graphics, final FigTrafo2D figTrafo2D) {
        this.paint(graphics);
    }
    
    public void paintSave(final Graphics graphics, final FigTrafo2D trafo) {
        final Graphics create = graphics.create();
        this.setTrafo(trafo);
        this.rebuild();
        this.paint(create);
        create.dispose();
    }
    
    public void showCursor(final boolean showCursorFlag) {
        this.showCursorFlag = showCursorFlag;
    }
    
    public void setCursor(final int n, final int n2) {
        if (this.fm == null) {
            this.update_bbox();
        }
        final int wc_to_screen_x = this.trafo.wc_to_screen_x(this.x);
        final int wc_to_screen_y = this.trafo.wc_to_screen_y(this.y);
        final double n3 = -this.attribs.fig_angle;
        int stringWidth = this.fm.stringWidth(this.str);
        if (this.attribs.textAlign == 1) {
            stringWidth = 0;
        }
        else if (this.attribs.textAlign == 2) {
            stringWidth /= 2;
        }
        else if (this.attribs.textAlign == 3) {
            stringWidth *= 1;
        }
        final int n4 = wc_to_screen_x - (int)(stringWidth * Math.cos(n3) + 0.5);
        final int n5 = wc_to_screen_y - (int)(stringWidth * Math.sin(n3) + 0.5);
        int n6;
        int stringWidth2;
        int textCursorIndex;
        for (n6 = (int)Math.sqrt((n4 - n) * (n4 - n) + (n5 - n2) * (n5 - n2)), stringWidth2 = 0, textCursorIndex = 0; stringWidth2 <= n6 && textCursorIndex < this.str.length(); stringWidth2 = this.fm.stringWidth(this.str.substring(0, textCursorIndex++))) {}
        if (textCursorIndex >= 1) {
            --textCursorIndex;
        }
        this.textCursorIndex = textCursorIndex;
    }
    
    public int initializeCursor(final int n, final int n2) {
        this.setCursor(n, n2);
        return this.textCursorIndex;
    }
    
    public void moveCursorTo(final int textCursorIndex) {
        this.textCursorIndex = textCursorIndex;
    }
    
    public void move(final int n, final int n2) {
        this.x += n;
        this.y += n2;
        this.rebuild();
    }
    
    public FigObject copy() {
        return new FigText(new Point(this.x, this.y), this.str, this.attribs.getClone(), this.trafo);
    }
    
    public boolean canRotate(final double n) {
        return true;
    }
    
    public void rotate(final Point point, final double n) {
        if (this.debug) {
            this.message("-#- FigText.rotate: " + point + " " + n);
        }
        this.x -= point.x;
        this.y -= point.y;
        final double n2 = this.x * Math.cos(n) - this.y * Math.sin(n);
        final double n3 = this.x * Math.sin(n) + this.y * Math.cos(n);
        this.x = (int)n2 + point.x;
        this.y = (int)n3 + point.y;
        final FigAttribs attribs = this.attribs;
        attribs.fig_angle += -n;
        if (this.attribs.fig_angle >= 6.283185307179586) {
            final FigAttribs attribs2 = this.attribs;
            attribs2.fig_angle -= 6.283185307179586;
        }
        if (this.attribs.fig_angle <= 0.0) {
            final FigAttribs attribs3 = this.attribs;
            attribs3.fig_angle += 6.283185307179586;
        }
        this.rebuild();
    }
    
    public void scale(final Point point, final double n, final double n2) {
        final FigAttribs attribs = this.attribs;
        attribs.fontSize *= (int)Math.abs(n);
        super.scale(point, n, n2);
        this.rebuild();
    }
    
    public void mirrorX(final int n, final int n2) {
        final int n3 = 2 * n - this.x;
        final int y = this.y;
        if (this.attribs.textAlign == 1) {
            this.attribs.textAlign = 3;
        }
        else if (this.attribs.textAlign == 3) {
            this.attribs.textAlign = 1;
        }
        this.move(n3 - this.x, 0);
    }
    
    public void mirrorY(final int n, final int n2) {
        final int x = this.x;
        this.move(0, 2 * n2 - this.y - this.y);
    }
    
    public void update(final FigAttribs figAttribs) {
        this.attribs.update(figAttribs);
        this.rebuild();
    }
    
    public double minDistance_OLD(final Point point) {
        final double minDistance = this.bbox.minDistance(point);
        double snap = Double.MAX_VALUE;
        if (this.bbox.inside(point.x, point.y)) {
            snap = this.trafo.getSnap();
        }
        return Math.min(minDistance, snap);
    }
    
    public double minDistance(final Point point) {
        final double n = Math.abs(point.x - this.x) + Math.abs(point.y - this.y);
        double minDistance = this.bbox.minDistance(point);
        if (!this.bbox.isInside(point)) {
            return Math.min(n, minDistance);
        }
        if (this.border == null) {
            this.update_bbox();
        }
        if (this.border.contains(point.x, point.y)) {
            minDistance = 0.9 * this.trafo.getSnapRelative();
        }
        return Math.min(n, minDistance);
    }
    
    public double minDistanceEuclid(final Point point) {
        return this.minDistance(point);
    }
    
    public Point[] getPoints() {
        return new Point[] { new Point(this.x, this.y) };
    }
    
    public void setPoints(final Point[] array) {
        if (array == null || array.length < 1) {
            return;
        }
        this.x = array[0].x;
        this.y = array[0].y;
        this.rebuild();
    }
    
    public String getText() {
        return this.str;
    }
    
    public void setText(final String str) {
        if (str != null) {
            this.str = str;
        }
        else {
            this.str = " ";
        }
        this.timestamp = 0L;
    }
    
    public void setTrafo(final FigTrafo2D trafo) {
        this.trafo = trafo;
        this.timestamp = 0L;
    }
    
    public void setFont(final int fig_font) {
        this.attribs.fig_font = fig_font;
        this.timestamp = 0L;
    }
    
    public void setFontSize(final int fontSize) {
        this.attribs.fontSize = fontSize;
        this.timestamp = 0L;
    }
    
    public void setTextAlignment(final int textAlign) {
        this.attribs.textAlign = textAlign;
        this.timestamp = 0L;
    }
    
    public void setColor(final Color lineColor) {
        this.attribs.fig_line_color = ColorCache.getColorCache().registerUserColor(lineColor);
        this.attribs.lineColor = lineColor;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        final char keyChar = keyEvent.getKeyChar();
        final int keyCode = keyEvent.getKeyCode();
        if (this.debug) {
            this.message(this.toString() + ".keyPressed: " + keyEvent);
        }
        if (keyEvent.isActionKey()) {
            if (keyCode == 37) {
                --this.textCursorIndex;
                if (this.textCursorIndex < 0) {
                    this.textCursorIndex = 0;
                }
            }
            else if (keyCode == 39) {
                ++this.textCursorIndex;
                if (this.textCursorIndex >= this.str.length()) {
                    this.textCursorIndex = this.str.length();
                }
            }
            else if (keyCode == 38 || keyCode == 40) {}
        }
        if (keyChar == '\b' || keyChar == '\u007f' || keyChar == '\b' || keyChar == '\uffff' || keyChar == '\uff08') {
            if (this.str.length() > 0) {
                if (this.str.length() > this.textCursorIndex) {
                    this.str = this.str.substring(0, this.textCursorIndex) + this.str.substring(this.textCursorIndex + 1, this.str.length());
                }
                else {
                    this.str = this.str.substring(0, this.textCursorIndex - 1);
                    --this.textCursorIndex;
                }
            }
        }
        else {
            if (keyChar < ' ') {
                if (this.debug) {
                    this.message("Ignoring control char: '" + (int)keyChar);
                }
                return;
            }
            if (keyChar > '\u00ff') {
                final char c = keyChar;
            }
            else {
                char c2;
                if (keyEvent.isAltDown()) {
                    if (keyChar == 'a') {
                        c2 = '\u00e4';
                    }
                    else if (keyChar == 'A') {
                        c2 = '\u00c4';
                    }
                    else if (keyChar == 'o') {
                        c2 = '\u00f6';
                    }
                    else if (keyChar == 'O') {
                        c2 = '\u00d6';
                    }
                    else if (keyChar == 'u') {
                        c2 = '\u00fc';
                    }
                    else if (keyChar == 'U') {
                        c2 = '\u00dc';
                    }
                    else if (keyChar == 's') {
                        c2 = '\u00df';
                    }
                    else {
                        this.message("FigText: ignored <ALT> modifier for key:" + (int)keyChar);
                        c2 = keyChar;
                    }
                }
                else {
                    c2 = keyChar;
                }
                this.str = this.str.substring(0, this.textCursorIndex) + c2 + this.str.substring(this.textCursorIndex, this.str.length());
                ++this.textCursorIndex;
            }
        }
        this.update_bbox();
        super.build_sc_bbox();
    }
    
    public static String symbol_recode(final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            sb.append(FigText.recode_table[s.charAt(i)]);
        }
        return sb.toString();
    }
    
    public String toString() {
        return "FigText at " + this.x + "," + this.y + " layer " + this.attribs.currentLayer + " timestamp " + this.timestamp + " text: '" + this.str + "' ";
    }
    
    public FigText() {
        this.textCursorIndex = 0;
        this.showCursorFlag = false;
        this.fm = null;
        this.attribs.currentLayer = 5;
        this.debug = false;
        this.selected = false;
        this.timestamp = 0L;
        this.str = "";
        this.update_bbox();
        this.createRenderer();
    }
    
    public FigText(final Point point, final String s, final FigAttribs figAttribs, final FigTrafo2D trafo) {
        this();
        this.x = point.x;
        this.y = point.y;
        this.str = new String(s);
        this.attribs = figAttribs.getClone();
        this.trafo = trafo;
        this.update_bbox();
        super.build_sc_bbox();
    }
    
    static {
        FigText.recode_table = new char[65536];
        for (char c = '\0'; c < '\uffff'; ++c) {
            FigText.recode_table[c] = c;
        }
        FigText.recode_table[97] = '\u03b1';
        FigText.recode_table[98] = '\u03b2';
        FigText.recode_table[99] = '\u03c7';
        FigText.recode_table[100] = '\u03b4';
        FigText.recode_table[101] = '\u03b5';
        FigText.recode_table[102] = '\u03c6';
        FigText.recode_table[103] = '\u03b3';
        FigText.recode_table[104] = '\u03b7';
        FigText.recode_table[105] = '\u03b9';
        FigText.recode_table[106] = '\u03d5';
        FigText.recode_table[107] = '\u03ba';
        FigText.recode_table[108] = '\u03bb';
        FigText.recode_table[109] = '\u03bc';
        FigText.recode_table[110] = '\u03bd';
        FigText.recode_table[111] = '\u03bf';
        FigText.recode_table[112] = '\u03c0';
        FigText.recode_table[113] = '\u03b8';
        FigText.recode_table[114] = '\u03c1';
        FigText.recode_table[115] = '\u03c3';
        FigText.recode_table[116] = '\u03c4';
        FigText.recode_table[117] = '\u03c5';
        FigText.recode_table[118] = '\u03d6';
        FigText.recode_table[119] = '\u03c9';
        FigText.recode_table[120] = '\u03be';
        FigText.recode_table[121] = '\u03c8';
        FigText.recode_table[122] = '\u03b6';
        FigText.recode_table[65] = '\u0391';
        FigText.recode_table[66] = '\u0392';
        FigText.recode_table[67] = '\u03a7';
        FigText.recode_table[68] = '\u0394';
        FigText.recode_table[69] = '\u0395';
        FigText.recode_table[70] = '\u03a6';
        FigText.recode_table[71] = '\u0393';
        FigText.recode_table[72] = '\u0397';
        FigText.recode_table[73] = '\u0399';
        FigText.recode_table[74] = '\u03d1';
        FigText.recode_table[75] = '\u039a';
        FigText.recode_table[76] = '\u039b';
        FigText.recode_table[77] = '\u039c';
        FigText.recode_table[78] = '\u039d';
        FigText.recode_table[79] = '\u039f';
        FigText.recode_table[80] = '\u03a0';
        FigText.recode_table[81] = '\u0398';
        FigText.recode_table[82] = '\u03a1';
        FigText.recode_table[83] = '\u03a3';
        FigText.recode_table[84] = '\u03a4';
        FigText.recode_table[85] = '\u03a5';
        FigText.recode_table[86] = '\u03c2';
        FigText.recode_table[87] = '\u03a9';
        FigText.recode_table[88] = '\u039e';
        FigText.recode_table[89] = '\u03a8';
        FigText.recode_table[90] = '\u0396';
        FigText.recode_table[161] = '\u03d2';
        FigText.recode_table[162] = '\'';
        FigText.recode_table[163] = '\u2264';
        FigText.recode_table[164] = '\u2215';
        FigText.recode_table[165] = '\u221e';
        FigText.recode_table[166] = 'f';
        FigText.recode_table[167] = '\u2768';
        FigText.recode_table[168] = '\u2755';
        FigText.recode_table[169] = '\u2764';
        FigText.recode_table[170] = '\u2765';
        FigText.recode_table[171] = '?';
        FigText.recode_table[172] = '?';
        FigText.recode_table[173] = '?';
        FigText.recode_table[174] = '?';
        FigText.recode_table[175] = '?';
        FigText.recode_table[176] = '\u2218';
        FigText.recode_table[177] = '±';
        FigText.recode_table[178] = '\"';
        FigText.recode_table[179] = '\u2265';
        FigText.recode_table[180] = '\u2715';
        FigText.recode_table[181] = '\u221d';
        FigText.recode_table[182] = 'd';
        FigText.recode_table[183] = '\u2219';
        FigText.recode_table[184] = '%';
        FigText.recode_table[185] = '\u2260';
        FigText.recode_table[186] = '\u2265';
        FigText.recode_table[187] = '\u2265';
        FigText.recode_table[188] = '\u2265';
        FigText.recode_table[193] = 'I';
        FigText.recode_table[194] = 'R';
        FigText.recode_table[195] = '?';
        FigText.recode_table[196] = '\u2297';
        FigText.recode_table[197] = '\u2295';
        FigText.recode_table[198] = '\u2205';
        FigText.recode_table[199] = '\u2229';
        FigText.recode_table[200] = '\u222a';
        FigText.recode_table[201] = '\u2283';
        FigText.recode_table[202] = '\u2287';
        FigText.recode_table[203] = '\u2284';
        FigText.recode_table[204] = '\u2282';
        FigText.recode_table[205] = '\u2286';
        FigText.recode_table[206] = '\u2208';
        FigText.recode_table[207] = '\u2209';
        FigText.recode_table[208] = '?';
        FigText.recode_table[209] = '\u2207';
        FigText.recode_table[210] = '®';
        FigText.recode_table[211] = '©';
        FigText.recode_table[212] = '?';
        FigText.recode_table[213] = '\u220f';
        FigText.recode_table[214] = '\u221a';
        FigText.recode_table[215] = '\u22c5';
        FigText.recode_table[216] = '\u00ad';
        FigText.recode_table[217] = '\u2227';
        FigText.recode_table[218] = '\u2228';
        FigText.recode_table[219] = '?';
        FigText.recode_table[220] = '?';
        FigText.recode_table[221] = '?';
        FigText.recode_table[222] = '?';
        FigText.recode_table[223] = '?';
        FigText.recode_table[224] = '\u22c4';
        FigText.recode_table[229] = '\u2211';
        FigText.recode_table[242] = '\u222b';
        FigText.recode_table[92] = '\u2234';
        FigText.recode_table[64] = '\u2245';
        FigText.recode_table[36] = '\u2203';
        FigText.recode_table[34] = '\u2200';
        FigText.recode_table[39] = '\u220d';
        FigText.recode_table[94] = '\u22a5';
        FigText.recode_table[96] = '\u2212';
    }
}
