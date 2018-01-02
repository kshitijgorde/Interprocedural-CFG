import java.awt.Rectangle;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.io.StreamTokenizer;
import java.io.Reader;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class StockDisplayer implements MVCDisplayer
{
    public float min_der;
    public float max_der;
    public boolean first;
    public Vector values;
    public int id;
    public String name;
    public int ystep;
    tipProducer tp;
    
    public void fromString(final String s, final boolean b) {
        int i = 0;
        while (i != -1) {
            final StockValue stockValue = new StockValue();
            i = this.pointFromString(stockValue, s, i, b);
            this.updateBorne(stockValue);
            this.values.addElement(stockValue);
        }
    }
    
    public int pointFromString(final StockValue stockValue, final String s, int n, final boolean b) {
        final int index = s.indexOf(44, n);
        String s2;
        if (index == -1) {
            s2 = s.substring(n);
        }
        else {
            s2 = s.substring(n, index);
        }
        stockValue.open = Float.valueOf(s2);
        if (index == -1) {
            return -1;
        }
        n = index + 1;
        final int index2 = s.indexOf(44, n);
        String s3;
        if (index2 == -1) {
            s3 = s.substring(n);
        }
        else {
            s3 = s.substring(n, index2);
        }
        stockValue.last = Float.valueOf(s3);
        if (index2 == -1) {
            return -1;
        }
        n = index2 + 1;
        final int index3 = s.indexOf(44, n);
        String s4;
        if (index3 == -1) {
            s4 = s.substring(n);
        }
        else {
            s4 = s.substring(n, index3);
        }
        stockValue.min = Float.valueOf(s4);
        if (index3 == -1) {
            return -1;
        }
        n = index3 + 1;
        final int index4 = s.indexOf(44, n);
        String s5;
        if (index4 == -1) {
            s5 = s.substring(n);
        }
        else {
            s5 = s.substring(n, index4);
        }
        stockValue.max = Float.valueOf(s5);
        if (index4 == -1) {
            return -1;
        }
        n = index4 + 1;
        final int index5 = s.indexOf(44, n);
        String s6;
        if (index5 == -1) {
            s6 = s.substring(n);
        }
        else {
            s6 = s.substring(n, index5);
        }
        stockValue.volume = Float.valueOf(s6);
        if (index5 == -1) {
            return -1;
        }
        n = index5 + 1;
        final int index6 = s.indexOf(44, n);
        if (index6 == -1) {
            stockValue.xvalue = s.substring(n);
        }
        else {
            stockValue.xvalue = s.substring(n, index6);
        }
        if (index6 == -1) {
            return -1;
        }
        return index6 + 1;
    }
    
    public boolean fromParam(final String s) {
        final int index = s.indexOf(44, 0);
        String substring;
        if (index == -1) {
            substring = s;
        }
        else {
            substring = s.substring(0, index);
        }
        this.id = Integer.valueOf(substring);
        if (index != -1) {
            this.name = s.substring(index + 1);
        }
        else {
            this.name = "value" + this.id;
        }
        return true;
    }
    
    public boolean fromReader(final Reader reader, final boolean b) {
        String s = "";
        boolean b2 = true;
        final StreamTokenizer streamTokenizer = new StreamTokenizer(reader);
        streamTokenizer.commentChar(59);
        streamTokenizer.quoteChar(34);
        streamTokenizer.eolIsSignificant(true);
        try {
            while (b2) {
                streamTokenizer.nextToken();
                switch (streamTokenizer.ttype) {
                    default: {
                        continue;
                    }
                    case 10: {
                        this.fromString(s, b);
                        s = "";
                        continue;
                    }
                    case 44: {
                        s = String.valueOf(s) + ',';
                        continue;
                    }
                    case -1: {
                        b2 = false;
                        continue;
                    }
                    case -2: {
                        s = String.valueOf(s) + streamTokenizer.nval;
                        continue;
                    }
                    case 34: {
                        s = String.valueOf(s) + streamTokenizer.sval;
                        continue;
                    }
                }
            }
            if (s != "") {
                this.fromString(s, b);
            }
        }
        catch (Exception ex) {
            System.out.println("Error file ");
            return false;
        }
        return true;
    }
    
    public void updateBorne(final float n) {
        if (this.first || n < this.min_der) {
            this.min_der = n;
        }
        if (this.first || n > this.max_der) {
            this.max_der = n;
        }
        this.first = false;
    }
    
    public void updateBorne(final StockValue stockValue) {
        if (stockValue.max != -1.0f) {
            this.updateBorne(stockValue.max);
        }
        if (stockValue.min != -1.0f) {
            this.updateBorne(stockValue.min);
        }
        this.updateBorne(stockValue.last);
    }
    
    public double getValue(final int n) {
        try {
            final StockValue stockValue = this.values.elementAt(n);
            return (stockValue == null) ? 0.0f : stockValue.last;
        }
        catch (Exception ex) {
            return 0.0;
        }
    }
    
    public void drawXStep(final Graphics graphics, final int n, final Point point) {
        final StockValue stockValue = this.values.elementAt(n);
        if (stockValue.xvalue != null) {
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            graphics.setColor(Color.black);
            graphics.drawString(stockValue.xvalue, point.x - fontMetrics.stringWidth(stockValue.xvalue) / 2, point.y + fontMetrics.getHeight());
        }
    }
    
    public double getMinValue() {
        return this.min_der;
    }
    
    public double getMaxValue() {
        return this.max_der;
    }
    
    public int getNbValue() {
        return this.values.size();
    }
    
    public void onMouseEnterValue(final MonoValueChart monoValueChart, final Graphics graphics, final int n, final Point point) {
        final StockValue stockValue = this.values.elementAt(n);
        this.tp.setString("Open=<b>" + stockValue.open + "</b><br>Close=<b>" + stockValue.last + "</b><br>Max value=<i>" + stockValue.max + "</i><br>Min Value=<i>" + stockValue.min + "</i>");
        this.tp.draw(graphics, point.x + 4, point.y - (int)(stockValue.last * monoValueChart.getPixelValue()) + 20, monoValueChart.getSize().width - 4);
    }
    
    public void onMouseExitValue(final MonoValueChart monoValueChart, final Graphics graphics, final int n, final Point point) {
        monoValueChart.repaint();
    }
    
    public double getYValueStep() {
        return Math.pow(10.0, String.valueOf((long)this.max_der).length() - 1);
    }
    
    public StockDisplayer() {
        this.first = true;
        this.values = new Vector(100);
        this.name = "";
        this.ystep = 10;
        this.tp = new tipProducer();
    }
    
    public abstract Rectangle drawPoint(final Graphics p0, final int p1, final Point p2, final double p3);
    
    public class StockValue
    {
        float open;
        float last;
        float min;
        float max;
        float volume;
        String xvalue;
    }
}
