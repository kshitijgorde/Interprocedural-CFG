import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.Color;
import java.awt.Graphics2D;
import MathFunction.MathFunction;
import java.awt.event.MouseMotionListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class GraphCanvas extends Canvas implements MouseMotionListener
{
    private double downX;
    private double upperX;
    double zoomFactor;
    private MathFunction func;
    
    public GraphCanvas() {
        final double n = 0.0;
        this.upperX = n;
        this.downX = n;
        this.zoomFactor = 1.0;
        this.func = new MathFunction("");
        this.addMouseMotionListener(this);
    }
    
    public void setFunction(final MathFunction func) {
        final double n = 0.0;
        this.upperX = n;
        this.downX = n;
        this.zoomFactor = 1.0;
        this.func = func;
    }
    
    public void setAreaLimits(final double downX, final double upperX) {
        this.downX = downX;
        this.upperX = upperX;
    }
    
    public boolean zoomOut() {
        this.zoomFactor *= 2.0;
        return this.zoomFactor < 256.0;
    }
    
    public boolean zoomIn() {
        this.zoomFactor /= 2.0;
        return this.zoomFactor > 0.0078125;
    }
    
    public boolean zoom(final double zoomFactor) {
        if (this.zoomFactor > 0.0078125 && this.zoomFactor < 256.0) {
            this.zoomFactor = zoomFactor;
            return true;
        }
        return false;
    }
    
    public void drawCoordinateSystem(final Graphics2D graphics2D) {
        graphics2D.setColor(Color.blue);
        graphics2D.draw(new Line2D.Double(new Point2D.Double(0.0, this.getSize().height / 2), new Point2D.Double(this.getSize().width, this.getSize().height / 2)));
        graphics2D.draw(new Line2D.Double(new Point2D.Double(this.getSize().width / 2, 0.0), new Point2D.Double(this.getSize().width / 2, this.getSize().height)));
        for (int i = 1; i < 20; ++i) {
            graphics2D.draw(new Line2D.Double(new Point2D.Double(i * this.getSize().width / 20, this.getSize().height / 2 + 3), new Point2D.Double(i * this.getSize().width / 20, this.getSize().height / 2 - 3)));
            graphics2D.draw(new Line2D.Double(new Point2D.Double(this.getSize().width / 2 + 3, i * this.getSize().height / 20), new Point2D.Double(this.getSize().width / 2 - 3, i * this.getSize().height / 20)));
        }
    }
    
    public void drawArea(final Graphics2D graphics2D, final double n, final double n2) {
        if (n >= n2) {
            return;
        }
        graphics2D.setColor(Color.green);
        try {
            for (double n3 = n; n3 < n2; n3 += 0.3) {
                graphics2D.draw(new Line2D.Double(new Point2D.Double(this.getSize().width / 2 + n3 * this.getSize().width / (20.0 * this.zoomFactor), this.getSize().height / 2), new Point2D.Double(this.getSize().width / 2 + n3 * this.getSize().width / (20.0 * this.zoomFactor), this.getSize().height / 2 - this.func.evaluate(n3) * this.getSize().height / (20.0 * this.zoomFactor))));
            }
            graphics2D.draw(new Line2D.Double(new Point2D.Double(this.getSize().width / 2 + n2 * this.getSize().width / (20.0 * this.zoomFactor), this.getSize().height / 2), new Point2D.Double(this.getSize().width / 2 + n2 * this.getSize().width / (20.0 * this.zoomFactor), this.getSize().height / 2 - this.func.evaluate(n2) * this.getSize().height / (20.0 * this.zoomFactor))));
        }
        catch (NullPointerException ex) {}
    }
    
    public void drawPath(final Graphics2D graphics2D, final double n, final double n2) {
        graphics2D.setColor(Color.green);
        double n4;
        for (double n3 = n; n3 < n2; n3 = n4 + 0.01 * this.zoomFactor) {
            final Point2D.Double double1 = new Point2D.Double(this.getSize().width / 2 + n3 * this.getSize().width / (20.0 * this.zoomFactor), this.getSize().height / 2 - this.func.evaluate(n3) * this.getSize().height / (20.0 * this.zoomFactor));
            n4 = n3 + 0.01 * this.zoomFactor;
            graphics2D.draw(new Line2D.Double(double1, new Point2D.Double(this.getSize().width / 2 + n4 * this.getSize().width / (20.0 * this.zoomFactor), this.getSize().height / 2 - this.func.evaluate(n4) * this.getSize().height / (20.0 * this.zoomFactor))));
        }
    }
    
    public void drawFunction(final Graphics2D graphics2D) throws NullPointerException {
        graphics2D.setColor(Color.red);
        for (double n = -10.0 * this.zoomFactor; n < 10.0 * this.zoomFactor; n += 0.01 * this.zoomFactor) {
            graphics2D.draw(new Line2D.Double(new Point2D.Double(this.getSize().width / 2 + n * this.getSize().width / (20.0 * this.zoomFactor), this.getSize().height / 2 - this.func.evaluate(n) * this.getSize().height / (20.0 * this.zoomFactor)), new Point2D.Double(this.getSize().width / 2 + n * this.getSize().width / (20.0 * this.zoomFactor), this.getSize().height / 2 - this.func.evaluate(n + 0.01 * this.zoomFactor) * this.getSize().height / (20.0 * this.zoomFactor))));
        }
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        final Graphics2D graphics2D = (Graphics2D)graphics;
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
        this.drawArea(graphics2D, this.downX, this.upperX);
        this.drawCoordinateSystem(graphics2D);
        try {
            this.drawFunction(graphics2D);
        }
        catch (Exception ex) {}
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        String s;
        try {
            s = String.valueOf((mouseEvent.getX() - this.getSize().width / 2) / (this.getSize().width / (20.0 * this.zoomFactor))).substring(0, 6);
        }
        catch (StringIndexOutOfBoundsException ex) {
            final String value = String.valueOf((mouseEvent.getX() - this.getSize().width / 2) / (this.getSize().width / (20.0 * this.zoomFactor)));
            s = value.substring(0, value.length());
        }
        String s2;
        try {
            s2 = String.valueOf(-(mouseEvent.getY() - this.getSize().height / 2) / (this.getSize().height / (20.0 * this.zoomFactor))).substring(0, 6);
        }
        catch (StringIndexOutOfBoundsException ex2) {
            final String value2 = String.valueOf(-(mouseEvent.getY() - this.getSize().height / 2) / (this.getSize().height / (20.0 * this.zoomFactor)));
            s2 = value2.substring(0, value2.length());
        }
        MathPlot.setCoordinatesTextField(s, s2);
    }
}
