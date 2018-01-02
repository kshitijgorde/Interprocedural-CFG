// 
// Decompiled by Procyon v0.5.30
// 

package duc.cal.gui;

import java.awt.Graphics;
import java.awt.Dimension;
import duc.cal.CalUtil;
import duc.cal.LunarDate;
import java.awt.Color;
import java.awt.Font;
import java.awt.Canvas;

public class DateCanvas extends Canvas
{
    static Font tinyFont;
    static Font smallFont;
    static Font bigFont;
    static Color solarColor;
    static Color lunarColor;
    static Color leapColor;
    static Color selectedColor;
    static Color tetColor;
    boolean empty;
    String solar;
    String lunar;
    String term;
    Color backColor;
    private LunarDate lunarDate;
    private LunarDate lunarDateAlt;
    private int solarDay;
    private int lunarFromFixed;
    int solarYear;
    int solarMonth;
    
    static {
        DateCanvas.tinyFont = new Font("Arial", 0, 9);
        DateCanvas.smallFont = new Font("Arial", 1, 12);
        DateCanvas.bigFont = new Font("Arial", 1, 18);
        DateCanvas.solarColor = Color.black;
        DateCanvas.lunarColor = new Color(0, 128, 48);
        DateCanvas.leapColor = Color.red;
        DateCanvas.selectedColor = new Color(224, 255, 0);
        DateCanvas.tetColor = new Color(255, 204, 153);
    }
    
    public DateCanvas() {
        this.empty = true;
        this.solar = "";
        this.lunar = "";
        this.term = "";
        this.backColor = Color.white;
    }
    
    public void setLunarDate(final LunarDate ld) {
        this.empty = false;
        this.lunarDate = ld;
        final int[] a = CalUtil.jdToDate(ld.getJd());
        this.solarDay = a[0];
        this.solarMonth = a[1];
        this.solarYear = a[2];
        this.initDisplayStrings();
    }
    
    public void setLunarDateAlt(final LunarDate ld) {
        this.lunarDateAlt = ld;
    }
    
    public void setTerm(final String solarTerm) {
        this.term = solarTerm;
    }
    
    LunarDate getLunarDate() {
        return this.lunarDate;
    }
    
    int getSolarDay() {
        return this.solarDay;
    }
    
    private void initDisplayStrings() {
        if (this.empty) {
            return;
        }
        this.solar = "" + this.solarDay;
        this.lunar = "" + this.lunarDate.getDay();
        if (this.lunarDate.getDay() == 1 || this.solarDay == 1) {
            this.lunar = String.valueOf(this.lunar) + "/" + this.lunarDate.getMonth();
            if (this.lunarDate.isLeap()) {
                this.lunar = String.valueOf(this.lunar) + " N";
            }
        }
    }
    
    public void clear() {
        this.empty = true;
        this.backColor = Color.white;
        this.lunarDate = null;
        this.lunarDateAlt = null;
        this.lunar = "";
        this.solar = "";
        this.term = "";
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(60, 45);
    }
    
    public void paint(final Graphics g) {
        final Color old = g.getColor();
        final int w = this.getSize().width;
        final int h = this.getSize().height;
        g.setColor(this.backColor);
        final LunarDate v = this.lunarDate;
        if (v != null && v.getDay() == 1 && v.getMonth() == 1 && !v.isLeap()) {
            g.setColor(DateCanvas.tetColor);
        }
        g.fillRect(0, 0, w, h);
        g.setColor(Color.darkGray);
        for (int off = 1; off < 2; ++off) {
            g.draw3DRect(off, off, w - off - 1, h - off - 1, true);
        }
        if (this.empty) {
            return;
        }
        g.setFont(DateCanvas.bigFont);
        g.setColor(DateCanvas.solarColor);
        final int y = g.getFontMetrics().getAscent() + 2;
        g.drawString(this.solar, 6, y);
        if (this.term.length() > 0) {
            g.setFont(DateCanvas.tinyFont);
            g.drawString(this.term, w / 2 - g.getFontMetrics().stringWidth(this.term) / 2, h / 2 + 7);
        }
        g.setFont(DateCanvas.smallFont);
        if (v != null && v.isLeap()) {
            g.setColor(DateCanvas.leapColor);
        }
        else {
            g.setColor(DateCanvas.lunarColor);
        }
        g.drawString(this.lunar, w - g.getFontMetrics().stringWidth(this.lunar) - 6, h - 4);
        if (v != null) {
            g.setColor(Color.darkGray);
            if (v.getDay() == 1) {
                g.fillOval(8, h / 2 + 2, h / 2 - 4, h / 2 - 4);
            }
            else if (v.getDay() == 15) {
                g.drawOval(8, h / 2 + 3, h / 2 - 6, h / 2 - 6);
            }
        }
        g.setColor(old);
        g.setFont(DateCanvas.tinyFont);
        final LunarDate ld1 = this.lunarDateAlt;
        final LunarDate ld2 = this.lunarDate;
        if (this.lunarDateAlt != null && (ld1.getDay() != ld2.getDay() || ld1.getMonth() != ld2.getMonth() || ld1.isLeap() != ld2.isLeap())) {
            if (this.lunarDateAlt.isLeap()) {
                g.setColor(DateCanvas.leapColor);
            }
            else {
                g.setColor(DateCanvas.lunarColor);
            }
            final String s = "[" + this.lunarDateAlt.getDay() + "/" + this.lunarDateAlt.getMonth() + (this.lunarDateAlt.isLeap() ? "N" : "") + "]";
            g.drawString(s, w - g.getFontMetrics().stringWidth(s) - 6, g.getFontMetrics().getAscent() + 4);
        }
    }
}
