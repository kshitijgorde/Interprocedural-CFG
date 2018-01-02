// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.bio;

import java.awt.FontMetrics;
import com.mindprod.common11.BigDate;
import com.mindprod.common11.FontFactory;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.JPanel;

final class DrawingPanel extends JPanel
{
    private static final int xmargin = 0;
    private static final int ymargin = 25;
    private Graphics g;
    private double scalex;
    private double scaley;
    private double yhigh;
    private double ylow;
    private int forOrdinal;
    private int fromOrdinal;
    private int phase;
    private int toOrdinal;
    private int xhigh;
    private int xlow;
    private int xPixelBase;
    private int yPixelBase;
    
    public void paintComponent(final Graphics g) {
        final Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.render(g2d);
    }
    
    public final void set(final int fromOrdinal, final int toOrdinal, final int birthOrdinal, final int forOrdinal) {
        this.fromOrdinal = fromOrdinal;
        this.toOrdinal = toOrdinal;
        this.phase = fromOrdinal - birthOrdinal;
        this.forOrdinal = forOrdinal;
    }
    
    private void plotSine(final int period, final int start, final Color colour) {
        this.g.setColor(colour);
        final int days = this.xhigh - this.xlow + 1;
        int oldx = 0;
        int oldy = 0;
        for (int i = 0; i < days; ++i) {
            final int newx = this.toPixelX(i + this.xlow);
            final int newy = this.toPixelY(CalcBiorhythms.level(start + i, period));
            if (i > 0) {
                this.g.drawLine(oldx, oldy, newx, newy);
            }
            oldx = newx;
            oldy = newy;
        }
    }
    
    final void render(final Graphics g) {
        int daysToPlot = this.toOrdinal - this.fromOrdinal;
        final Dimension d = this.getSize();
        final int width;
        if (d != null && (width = d.width) != 0) {
            daysToPlot = width / 5;
        }
        this.setScale(g, this.fromOrdinal, this.fromOrdinal + daysToPlot, 5.0, -1.0, 1.0, 50.0);
        this.xaxis(Color.black);
        this.yaxis(Color.black);
        this.plotSine(23, this.phase, CalcBiorhythms.FOREGROUND_FOR_PHYSICAL);
        this.plotSine(28, this.phase, CalcBiorhythms.FOREGROUND_FOR_EMOTIONAL);
        this.plotSine(33, this.phase, CalcBiorhythms.FOREGROUND_FOR_INTELLECTUAL);
        this.plotSine(21252, this.phase, CalcBiorhythms.FOREGROUND_FOR_COMBI);
    }
    
    private void setScale(final Graphics g, final int xlow, final int xhigh, final double scalex, final double ylow, final double yhigh, final double scaley) {
        this.g = g;
        this.xlow = xlow;
        this.xhigh = xhigh;
        this.scalex = scalex;
        this.ylow = ylow;
        this.yhigh = yhigh;
        this.scaley = scaley;
        this.xPixelBase = 0;
        this.yPixelBase = this.getBounds().height - 25;
    }
    
    private int toPixelX(final int x) {
        return (int)Math.round((x - this.xlow) * this.scalex) + this.xPixelBase;
    }
    
    private int toPixelY(final double y) {
        return this.yPixelBase - (int)Math.round((y - this.ylow) * this.scaley);
    }
    
    private void xaxis(final Color colour) {
        this.g.setColor(colour);
        this.g.drawLine(this.toPixelX(this.xlow), this.toPixelY(this.ylow), this.toPixelX(this.xhigh), this.toPixelY(this.ylow));
        String wording = "";
        this.g.setFont(FontFactory.build("Dialog", 1, 12));
        FontMetrics fm = this.getFontMetrics(this.g.getFont());
        final BigDate firstDate = new BigDate(this.xlow);
        final int firstYear = firstDate.getYYYY();
        final int firstMonth = firstDate.getMM();
        final int firstSunday = BigDate.nthXXXDay(1, 0, firstYear, firstMonth) + this.xlow - 1;
        int prevXtick = 0;
        int month = firstMonth;
        while (true) {
            final BigDate d = new BigDate(firstYear, month, 1, 2);
            final int x = d.getOrdinal();
            if (x > this.xhigh) {
                break;
            }
            final int xtick = this.toPixelX(d.getOrdinal());
            final int ytick = this.toPixelY(this.ylow);
            this.g.drawLine(xtick, ytick, xtick, ytick + 25);
            final int centerXtick = (xtick + prevXtick) / 2;
            final int xadj = fm.stringWidth(wording) / 2;
            this.g.drawString(wording, centerXtick - xadj, ytick + 21);
            wording = BigDate.monthAbbr(d.getMM());
            prevXtick = xtick;
            ++month;
        }
        this.g.setFont(FontFactory.build("Dialog", 0, 8));
        fm = this.getFontMetrics(this.g.getFont());
        for (int week = firstSunday; week <= this.xhigh; week += 7) {
            final int xtick2 = this.toPixelX(week);
            final int ytick2 = this.toPixelY(this.ylow);
            this.g.drawLine(xtick2, ytick2, xtick2, ytick2 + 4);
            wording = Integer.toString(new BigDate(week).getDD());
            final int xadj2 = fm.stringWidth(wording) / 2;
            this.g.drawString(wording, xtick2 - xadj2, ytick2 + 12);
        }
        for (int day = this.xlow; day <= this.xhigh; ++day) {
            final int xtick2 = this.toPixelX(day);
            final int ytick2 = this.toPixelY(this.ylow);
            this.g.drawLine(xtick2, ytick2, xtick2, ytick2 + 2);
        }
        this.g.drawLine(this.toPixelX(this.xlow), this.toPixelY(0.0), this.toPixelX(this.xhigh), this.toPixelY(0.0));
    }
    
    private void yaxis(final Color colour) {
        this.g.setColor(colour);
        this.g.drawLine(this.toPixelX(this.forOrdinal), this.toPixelY(-1.0), this.toPixelX(this.forOrdinal), this.toPixelY(1.0));
        if (this.forOrdinal == BigDate.localToday().getOrdinal()) {
            this.g.setFont(FontFactory.build("Dialog", 1, 10));
            final FontMetrics fm = this.getFontMetrics(this.g.getFont());
            final String wording = "today";
            final int xadj = fm.stringWidth("today") / 2;
            this.g.drawString("today", this.toPixelX(this.forOrdinal) - xadj, this.toPixelY(1.0) - 2);
        }
    }
}
