import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Point;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class CartesianAxes extends Canvas
{
    protected Point m_UserOrg;
    protected Point m_Org;
    protected int m_nUpperY;
    protected int m_nLowerY;
    protected int m_nUpperX;
    protected int m_nLowerX;
    protected double m_dUnitX;
    protected double m_dUnitY;
    protected int m_nXLabelsAt;
    protected int m_nYLabelsAt;
    protected int m_nXLabelsStartFrom;
    protected int m_nYLabelsStartFrom;
    protected int m_nWidth;
    final int MAX_LEGENDS = 5;
    protected String[] m_strLegend;
    protected Color[] m_colorLegend;
    protected int m_nLegends;
    
    public CartesianAxes(final Point point, final int nLowerX, final int nUpperX, final int nLowerY, final int nUpperY) {
        this.m_nUpperY = nUpperY;
        this.m_nLowerY = nLowerY;
        this.m_nUpperX = nUpperX;
        this.m_nLowerX = nLowerX;
        this.m_UserOrg = new Point(point.x, point.y);
        this.m_Org = new Point(0, 0);
        this.m_nXLabelsAt = 0;
        this.m_nYLabelsAt = 0;
        this.setFont(new Font("TimesRoman", 0, 10));
        this.m_nLegends = 0;
        this.m_strLegend = new String[5];
        this.m_colorLegend = new Color[5];
        this.setBackground(Color.white);
    }
    
    public void paint(final Graphics graphics) {
        final Rectangle rectangle = new Rectangle(this.getBounds());
        this.m_nWidth = rectangle.width - 60;
        final int n = rectangle.height - 20;
        this.m_dUnitX = this.m_nWidth / (this.m_nUpperX - this.m_nLowerX);
        this.m_dUnitY = n / (this.m_nUpperY - this.m_nLowerY);
        this.m_Org.x = (int)(-this.m_nLowerX * this.m_dUnitX) + 20;
        this.m_Org.y = (int)(this.m_nUpperY * this.m_dUnitY) + 20;
        final Point translate = this.translate(new Point(this.m_nUpperX, this.m_UserOrg.y));
        final Point translate2 = this.translate(new Point(this.m_nLowerX, this.m_UserOrg.y));
        final Point translate3 = this.translate(new Point(this.m_UserOrg.x, this.m_nUpperY));
        final Point translate4 = this.translate(new Point(this.m_UserOrg.x, this.m_nLowerY));
        graphics.drawLine(translate2.x, translate2.y, translate.x, translate.y);
        graphics.drawLine(translate4.x, translate4.y, translate3.x, translate3.y);
        this.drawXLabels(graphics);
        this.drawYLabels(graphics);
        this.drawLegends(graphics);
    }
    
    public void setLabelsAt(final int nxLabelsAt, final int nxLabelsStartFrom, final int nyLabelsAt, final int nyLabelsStartFrom) {
        this.m_nXLabelsAt = nxLabelsAt;
        this.m_nYLabelsAt = nyLabelsAt;
        this.m_nXLabelsStartFrom = nxLabelsStartFrom;
        this.m_nYLabelsStartFrom = nyLabelsStartFrom;
        this.repaint();
    }
    
    public void addLegend(final String s, final Color color) {
        if (this.m_nLegends < 5) {
            this.m_strLegend[this.m_nLegends] = s;
            this.m_colorLegend[this.m_nLegends] = color;
            ++this.m_nLegends;
        }
    }
    
    protected Point translate(final Point point) {
        return new Point((int)(point.x * this.m_dUnitX) + this.m_Org.x, this.m_Org.y - (int)(point.y * this.m_dUnitY));
    }
    
    protected void drawXLabels(final Graphics graphics) {
        if (this.m_nXLabelsAt != 0) {
            for (int i = this.m_nLowerX; i <= this.m_nUpperX; i += this.m_nXLabelsAt) {
                final Point translate = this.translate(new Point(i, this.m_UserOrg.y));
                graphics.drawString(Integer.toString(i + this.m_nXLabelsStartFrom), translate.x, translate.y + 20);
                graphics.drawLine(translate.x, translate.y, translate.x, translate.y + 5);
            }
        }
    }
    
    protected void drawYLabels(final Graphics graphics) {
        if (this.m_nYLabelsAt != 0) {
            for (int i = this.m_nLowerY; i <= this.m_nUpperY; i += this.m_nYLabelsAt) {
                final Point translate = this.translate(new Point(this.m_UserOrg.x, i));
                graphics.drawString(Integer.toString(i + this.m_nYLabelsStartFrom), translate.x - 20, translate.y);
                graphics.drawLine(translate.x, translate.y, translate.x - 5, translate.y);
            }
        }
    }
    
    protected void drawLegends(final Graphics graphics) {
        for (int i = 0; i < this.m_nLegends; ++i) {
            graphics.setColor(this.m_colorLegend[i]);
            graphics.drawString(this.m_strLegend[i], this.m_nWidth + 1, 20 + i * 20);
        }
    }
}
