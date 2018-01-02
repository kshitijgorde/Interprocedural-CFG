import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Graphics;
import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class VolumeDisplayer implements MVCDisplayer
{
    public int count;
    public double[] vs;
    public int ystep;
    public float min;
    public float max;
    public boolean first;
    tipProducer tp;
    
    public void fromStockDisplayer(final StockDisplayer stockDisplayer) {
        this.count = stockDisplayer.getNbValue();
        this.vs = new double[this.count];
        int n = 0;
        if (this.count == 0) {
            return;
        }
        final Enumeration<StockDisplayer.StockValue> elements = stockDisplayer.values.elements();
        while (elements.hasMoreElements()) {
            final StockDisplayer.StockValue stockValue = elements.nextElement();
            this.vs[n++] = stockValue.volume;
            if (this.first || stockValue.volume < this.min) {
                this.min = stockValue.volume;
            }
            if (this.first || stockValue.volume > this.max) {
                this.max = stockValue.volume;
            }
            this.first = false;
        }
        this.ystep = 100000;
    }
    
    public double getValue(final int n) {
        return this.vs[n];
    }
    
    public Rectangle drawPoint(final Graphics graphics, final int n, final Point point, final double n2) {
        final int n3 = point.y + (int)(this.vs[n] * n2);
        graphics.drawLine(point.x - 1, n3, point.x + 1, n3);
        return new Rectangle(point.x, Math.min(n3, point.y), 10, (int)Math.abs(this.vs[n] * n2));
    }
    
    public void drawXStep(final Graphics graphics, final int n, final Point point) {
    }
    
    public double getMinValue() {
        return this.min;
    }
    
    public double getMaxValue() {
        return this.max;
    }
    
    public int getNbValue() {
        return this.count;
    }
    
    public void onMouseEnterValue(final MonoValueChart monoValueChart, final Graphics graphics, final int n, final Point point) {
        this.tp.setString("Volume = <i>" + this.vs[n] + "</i>");
        this.tp.draw(graphics, point.x, point.y - 16, monoValueChart.getSize().width - 4);
    }
    
    public void onMouseExitValue(final MonoValueChart monoValueChart, final Graphics graphics, final int n, final Point point) {
        monoValueChart.repaint();
    }
    
    public double getYValueStep() {
        return this.ystep;
    }
    
    public VolumeDisplayer() {
        this.first = true;
        this.tp = new tipProducer();
    }
}
