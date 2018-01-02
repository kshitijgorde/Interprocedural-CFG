// 
// Decompiled by Procyon v0.5.30
// 

package graphTools;

import java.awt.FontMetrics;
import mathTools.MyMath;
import java.awt.Font;
import mathTools.Point;
import java.awt.event.MouseEvent;
import parser.Function;
import java.awt.Color;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Canvas;

public class GraphCanvas extends Canvas implements MouseListener, MouseMotionListener
{
    private int oldLinePos;
    private int hMin;
    private int hMax;
    private int vMin;
    private int vMax;
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;
    private double xWidth;
    private double yWidth;
    private int hWidth;
    private int vWidth;
    private double xPrevPrev;
    private double yPrevPrev;
    private double xPrev;
    private double yPrev;
    public boolean penDown;
    private double step;
    public Graphics g;
    private int hStart;
    private int vStart;
    private int hEnd;
    private int vEnd;
    protected double xClick;
    protected double yClick;
    protected double xDrag;
    protected double yDrag;
    protected boolean isClick;
    protected boolean isDrag;
    protected boolean monitorDrag;
    protected boolean ghostRectangles;
    protected boolean ghostVerticalLine;
    protected boolean plotDrag;
    protected boolean PLOT_DISCONTINUITIES;
    protected boolean MOUSEZOOM;
    protected Vector path;
    protected boolean sortPath;
    protected double xStep;
    protected double yStep;
    
    public GraphCanvas() {
        this.penDown = false;
        this.isClick = false;
        this.isDrag = false;
        this.monitorDrag = false;
        this.ghostRectangles = true;
        this.ghostVerticalLine = false;
        this.plotDrag = false;
        this.PLOT_DISCONTINUITIES = true;
        this.MOUSEZOOM = false;
        this.path = null;
        this.sortPath = false;
    }
    
    public GraphCanvas(final int hMax, final int vMax) {
        this.penDown = false;
        this.isClick = false;
        this.isDrag = false;
        this.monitorDrag = false;
        this.ghostRectangles = true;
        this.ghostVerticalLine = false;
        this.plotDrag = false;
        this.PLOT_DISCONTINUITIES = true;
        this.MOUSEZOOM = false;
        this.path = null;
        this.sortPath = false;
        this.hMin = 0;
        this.hMax = hMax;
        this.vMin = 0;
        this.vMax = vMax;
        this.hWidth = this.hMax - this.hMin;
        this.vWidth = this.vMax - this.vMin;
        this.setSize(hMax, vMax);
        this.setWindow(-10.0, 10.0, -10.0, 10.0);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public void paint(final Graphics graphics) {
        this.setGraphics(graphics);
        this.setBackground(Color.red);
        graphics.setColor(Color.black);
        this.drawAxes();
    }
    
    public void setGraphics(final Graphics g) {
        this.g = g;
    }
    
    public void draw() {
        this.repaint();
    }
    
    public void erasePath() {
        this.path = null;
    }
    
    public boolean pathIsEmpty() {
        return this.path == null || this.path.size() == 0;
    }
    
    public void setWindow(final double xMin, final double xMax, final double yMin, final double yMax) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        this.step = (this.xMax - this.xMin) / (this.hMax - this.hMin);
        this.xStep = this.hTOx(1) - this.hTOx(0);
        this.yStep = this.vTOy(0) - this.vTOy(1);
        this.xWidth = this.xMax - this.xMin;
        this.yWidth = this.yMax - this.yMin;
    }
    
    public void zoom(final double n, final double n2) {
        final double n3 = (this.xMin + this.xMax) / 2.0;
        final double n4 = (this.yMin + this.yMax) / 2.0;
        this.setWindow(n3 - n * this.xWidth / 2.0, n3 + n * this.xWidth / 2.0, n4 - n2 * this.yWidth / 2.0, n4 + n2 * this.yWidth / 2.0);
    }
    
    public void drawRelation(final Function function) {
        for (int i = this.hMin(); i <= this.hMax(); ++i) {
            for (int j = this.vMin(); j <= this.vMax(); ++j) {
                if (function.value(this.hTOx(i), this.vTOy(j)) == 1.0) {
                    this.g.drawLine(i, j, i, j);
                }
            }
        }
    }
    
    public void drawGraph(final Function function) {
        for (double xMin = this.xMin; xMin <= this.xMax - this.step; xMin += this.step) {
            final double n = xMin - this.step;
            final double value = function.value(n);
            final double n2 = xMin;
            final double value2 = function.value(n2);
            final double n3 = xMin + this.step;
            final double value3 = function.value(n3);
            if (!this.dontPlotThisPoint(n, value, n2, value2, n3, value3) || this.PLOT_DISCONTINUITIES) {
                this.g.drawLine(this.xTOh(n2), this.yTOv(value2), this.xTOh(n3), this.yTOv(value3));
            }
        }
    }
    
    public void skipDiscontinuities(final boolean b) {
        if (b) {
            this.PLOT_DISCONTINUITIES = false;
        }
        else {
            this.PLOT_DISCONTINUITIES = true;
        }
    }
    
    public void mouseZoomOn(final boolean mousezoom) {
        this.MOUSEZOOM = mousezoom;
    }
    
    public void penDown() {
        this.penDown = true;
    }
    
    public void penUp() {
        this.penDown = false;
    }
    
    public void plot(final double xPrev, final double yPrev) {
        final boolean dontPlotThisPoint = this.dontPlotThisPoint(this.xPrevPrev, this.yPrevPrev, this.xPrev, this.yPrev, xPrev, yPrev);
        if (this.penDown && (!dontPlotThisPoint || this.PLOT_DISCONTINUITIES)) {
            this.g.drawLine(this.xTOh(this.xPrev), this.yTOv(this.yPrev), this.xTOh(xPrev), this.yTOv(yPrev));
        }
        this.xPrevPrev = this.xPrev;
        this.yPrevPrev = this.yPrev;
        this.xPrev = xPrev;
        this.yPrev = yPrev;
        this.penDown();
    }
    
    public void plt(final double n, final double n2) {
        if (!Double.isNaN(n2) && !Double.isInfinite(n2)) {
            this.g.drawLine(this.xTOh(n), this.yTOv(n2), this.xTOh(n), this.yTOv(n2));
        }
    }
    
    public void dot(final double n, final double n2, final int n3) {
        final int xtOh = this.xTOh(n);
        final int ytOv = this.yTOv(n2);
        this.g.fillOval(xtOh - n3 / 2, ytOv - n3 / 2, n3, n3);
        if (n3 == 2) {
            this.g.drawLine(xtOh - 1, ytOv - 1, xtOh - 1, ytOv);
            this.g.drawLine(xtOh, ytOv - 1, xtOh, ytOv);
        }
    }
    
    private boolean dontPlotThisPoint(final double n, final double n2, final double n3, final double n4, final double n5, final double n6) {
        final double n7 = (n4 - n2) / this.step;
        final double n8 = (n6 - n4) / this.step;
        final double abs = Math.abs(n7);
        final double abs2 = Math.abs(n8);
        final double max = Math.max(Math.abs(this.yMin), Math.abs(this.yMax));
        final double max2 = Math.max(Math.abs(this.xMin), Math.abs(this.xMax));
        final boolean b = (abs < 1.0E-10 && abs2 > 1.0) || (abs2 < 1.0E-10 && abs > 1.0) || n7 * n8 < 0.0;
        final boolean b2 = Math.abs(n4) > max || Math.abs(n6) > max || Math.abs(n3) > max2 || Math.abs(n5) > max2;
        final boolean b3 = Double.isNaN(n4) || Double.isNaN(n6) || Double.isInfinite(n4) || Double.isInfinite(n6) || Double.isNaN(n3) || Double.isNaN(n5) || Double.isInfinite(n3) || Double.isInfinite(n5);
        return b || b2 || b3;
    }
    
    public double st(final double n, final Function function, final Function function2) {
        function.value(n);
        final double abs = Math.abs(function2.value(n));
        double n2;
        if (this.badPoint(n, function, function2)) {
            n2 = this.xStep;
            if (!this.badPoint(n + n2, function, function2)) {
                while (n2 >= 2.0 * this.xStep / 1000.0) {
                    if (this.badPoint(n + n2 - this.xStep / 1000.0, function, function2)) {
                        break;
                    }
                    n2 -= this.xStep / 1000.0;
                }
            }
        }
        else if (abs <= this.yStep / this.xStep) {
            n2 = this.xStep;
        }
        else if (abs <= 1000.0 * this.yStep / this.xStep) {
            n2 = this.yStep / abs;
        }
        else {
            n2 = this.xStep / 1000.0;
        }
        return n2;
    }
    
    private boolean badPoint(final double n, final Function function, final Function function2) {
        final double value = function.value(n);
        final double abs = Math.abs(function2.value(n));
        return Double.isNaN(value) || Double.isInfinite(value) || value < this.yMin() || value > this.yMax() || Double.isNaN(abs) || Double.isInfinite(abs);
    }
    
    public double xMin() {
        return this.xMin;
    }
    
    public double xMax() {
        return this.xMax;
    }
    
    public double yMin() {
        return this.yMin;
    }
    
    public double yMax() {
        return this.yMax;
    }
    
    public int hMin() {
        return this.hMin;
    }
    
    public int hMax() {
        return this.hMax;
    }
    
    public int vMin() {
        return this.vMin;
    }
    
    public int vMax() {
        return this.vMax;
    }
    
    public double step() {
        return this.step;
    }
    
    private void wait(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (Exception ex) {}
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (!this.MOUSEZOOM) {
            return;
        }
        final Graphics graphics = this.getGraphics();
        this.hStart = mouseEvent.getX();
        this.vStart = mouseEvent.getY();
        this.xClick = this.hTOx(this.hStart);
        this.yClick = this.vTOy(this.vStart);
        if (this.plotDrag && this.path == null) {
            this.path = new Vector();
        }
        if (this.ghostVerticalLine) {
            graphics.setXORMode(Color.green);
            graphics.drawLine(this.oldLinePos = this.hStart, this.vMin, this.oldLinePos, this.vMax);
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (!this.MOUSEZOOM) {
            return;
        }
        final Graphics graphics = this.getGraphics();
        this.hEnd = mouseEvent.getX();
        this.vEnd = mouseEvent.getY();
        this.xDrag = this.hTOx(this.hEnd);
        this.yDrag = this.vTOy(this.vEnd);
        this.isDrag = true;
        if (this.plotDrag) {
            this.penUp();
        }
        if (this.plotDrag) {
            this.path.addElement("liftPen");
        }
        if (this.ghostVerticalLine) {
            graphics.setXORMode(Color.green);
            graphics.drawLine(this.oldLinePos, this.vMin, this.oldLinePos, this.vMax);
            graphics.setPaintMode();
        }
        if (!this.ghostVerticalLine) {
            this.repaint();
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (!this.MOUSEZOOM) {
            return;
        }
        this.xClick = this.hTOx(mouseEvent.getX());
        this.yClick = this.vTOy(mouseEvent.getY());
        this.isClick = true;
        if (!this.ghostVerticalLine) {
            this.repaint();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (!this.MOUSEZOOM) {
            return;
        }
        final Graphics graphics = this.getGraphics();
        this.hEnd = mouseEvent.getX();
        this.vEnd = mouseEvent.getY();
        this.xDrag = this.hTOx(this.hEnd);
        this.yDrag = this.vTOy(this.vEnd);
        if (this.plotDrag) {
            graphics.drawOval(this.hEnd - 1, this.vEnd - 1, 2, 2);
            this.path.addElement(new Point(this.xDrag, this.yDrag));
        }
        if (this.ghostRectangles) {
            graphics.setXORMode(Color.green);
            final int min = Math.min(this.hStart, this.hEnd);
            final int min2 = Math.min(this.vStart, this.vEnd);
            final int abs = Math.abs(this.hStart - this.hEnd);
            final int abs2 = Math.abs(this.vStart - this.vEnd);
            graphics.drawRect(min, min2, abs, abs2);
            graphics.drawRect(min + 1, min2 + 1, abs - 2, abs2 - 2);
            this.wait(100);
            graphics.drawRect(min, min2, abs, abs2);
            graphics.drawRect(min + 1, min2 + 1, abs - 2, abs2 - 2);
            graphics.setPaintMode();
        }
        if (this.ghostVerticalLine) {
            graphics.setXORMode(Color.green);
            final int hEnd = this.hEnd;
            graphics.drawLine(this.oldLinePos, this.vMin, this.oldLinePos, this.vMax);
            graphics.drawLine(hEnd, this.vMin, hEnd, this.vMax);
            this.oldLinePos = hEnd;
            graphics.setPaintMode();
        }
        if (this.monitorDrag) {
            this.repaint();
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void acknowledgeMouse() {
        this.isDrag = false;
        this.isClick = false;
    }
    
    public void trimPath() {
        if (this.path == null) {
            return;
        }
        if (this.path.size() > 0) {
            this.path.removeElementAt(this.path.size() - 1);
        }
        while (this.path.size() > 0 && this.path.elementAt(this.path.size() - 1) instanceof Point) {
            this.path.removeElementAt(this.path.size() - 1);
        }
    }
    
    public void drawPath() {
        this.skipDiscontinuities(false);
        if (this.path != null) {
            for (int i = 0; i < this.path.size(); ++i) {
                final Object element = this.path.elementAt(i);
                if (element instanceof Point) {
                    final Point point = (Point)element;
                    this.plot(point.getX(), point.getY());
                    this.dot(point.getX(), point.getY(), 2);
                }
                else {
                    this.penUp();
                }
            }
            this.penUp();
        }
    }
    
    private void sortPathByXvalues() {
        for (int i = 0; i < this.path.size() - 1; ++i) {
            int n = i;
            for (int j = i + 1; j < this.path.size(); ++j) {
                if (((Point)this.path.elementAt(j)).getX() < this.path.elementAt(n).getX()) {
                    n = j;
                }
            }
            final Object element = this.path.elementAt(i);
            this.path.setElementAt(this.path.elementAt(n), i);
            this.path.setElementAt(element, n);
        }
    }
    
    public int xTOh(final double n) {
        return (int)(this.hMin + (this.hMax - this.hMin) * (n - this.xMin) / (this.xMax - this.xMin));
    }
    
    public int yTOv(final double n) {
        return (int)(this.vMax - (this.vMax - this.vMin) * (n - this.yMin) / (this.yMax - this.yMin));
    }
    
    public double hTOx(final int n) {
        return this.xMin + (this.xMax - this.xMin) * ((n - this.hMin) / (this.hMax - this.hMin));
    }
    
    public double vTOy(final int n) {
        return this.yMin + (this.yMax - this.yMin) * ((this.vMax - n) / (this.vMax - this.vMin));
    }
    
    public void drawAxes() {
        this.MOUSEZOOM = true;
        int n = this.xTOh(0.0);
        int n2 = this.yTOv(0.0);
        if (n < this.hMin) {
            n = this.hMin;
        }
        if (n > this.hMax) {
            n = this.hMax;
        }
        if (n2 < this.vMin) {
            n2 = this.vMin;
        }
        if (n2 > this.vMax) {
            n2 = this.vMax;
        }
        Font font;
        if (this.hWidth > 560) {
            font = new Font("SansSerif", 1, 12);
        }
        else if (this.hWidth > 350) {
            font = new Font("Courier", 1, 9);
        }
        else {
            font = new Font("SansSerif", 0, 6);
        }
        this.g.setFont(font);
        this.axes(n, n2);
        this.ticks(n, n2);
    }
    
    private void axes(final int n, final int n2) {
        final int n3 = (this.hMax - this.hMin > 350) ? 2 : 1;
        this.g.fillRect(this.hMin, n2, this.hMax, n3);
        this.g.fillRect(n, this.vMin, n3, this.vMax);
        final int n4 = (this.hMax - this.hMin) / 70;
        if (this.vMin <= n2 && n2 <= this.vMax) {
            this.g.drawLine(this.hMax, n2, this.hMax - n4, n2 - n4);
            this.g.drawLine(this.hMax, n2, this.hMax - n4, n2 + n4);
            if (n3 == 2) {
                this.g.drawLine(this.hMax - 1, n2, this.hMax - 1 - n4, n2 - n4);
                this.g.drawLine(this.hMax - 1, n2, this.hMax - 1 - n4, n2 + n4);
            }
        }
        if (this.hMin <= n && n <= this.hMax) {
            this.g.drawLine(n, this.vMin, n - n4, this.vMin + n4);
            this.g.drawLine(n, this.vMin, n + n4, this.vMin + n4);
            if (n3 == 2) {
                this.g.drawLine(n, this.vMin + 1, n - n4, this.vMin + 1 + n4);
                this.g.drawLine(n, this.vMin + 1, n + n4, this.vMin + 1 + n4);
            }
        }
    }
    
    private void ticks(final int n, final int n2) {
        int n3 = this.vWidth / 150;
        if (n3 < 1) {
            n3 = 1;
        }
        final int n4 = 2 * n3;
        final double tickInterval = this.getTickInterval(this.xWidth);
        final double tickSubinterval = this.getTickSubinterval(tickInterval, true);
        final int n5 = (int)Math.ceil(this.xWidth / tickInterval);
        final int n6 = (int)Math.round(tickInterval / tickSubinterval);
        final double n7 = tickInterval * Math.floor(this.xMin / tickInterval) - tickInterval;
        for (int i = 0; i <= n5 + 1; ++i) {
            final double n8 = n7 + i * tickInterval;
            this.g.drawLine(this.xTOh(n8), n2 - n4, this.xTOh(n8), n2 + n4);
            if (Math.abs(n8) > tickInterval / 2.0) {
                this.labelxTick(n8, n, n2, n4);
            }
            for (int j = 1; j <= n6 - 1; ++j) {
                final double n9 = n8 + j * tickSubinterval;
                this.g.drawLine(this.xTOh(n9), n2 - n3, this.xTOh(n9), n2 + n3);
            }
        }
        final double tickInterval2 = this.getTickInterval(this.yWidth);
        final double tickSubinterval2 = this.getTickSubinterval(tickInterval2, false);
        final int n10 = (int)Math.ceil(this.yWidth / tickInterval2);
        final int n11 = (int)Math.round(tickInterval2 / tickSubinterval2);
        final double n12 = tickInterval2 * Math.floor(this.yMin / tickInterval2) - tickInterval2;
        for (int k = 0; k <= n10 + 1; ++k) {
            final double n13 = n12 + k * tickInterval2;
            this.g.drawLine(n - n4, this.yTOv(n13), n + n4, this.yTOv(n13));
            if (Math.abs(n13) > tickInterval2 / 2.0) {
                this.labelyTick(n13, n, n2, n4);
            }
            for (int l = 1; l <= n11 - 1; ++l) {
                final double n14 = n13 + l * tickSubinterval2;
                this.g.drawLine(n - n3, this.yTOv(n14), n + n3, this.yTOv(n14));
            }
        }
    }
    
    private double getTickInterval(final double n) {
        double power = MyMath.power(10.0, (int)Math.floor(Math.log(n) / Math.log(10.0)));
        if (n / power <= 1.0 && this.vWidth > 350) {
            power /= 10.0;
        }
        else if (n / power <= 1.0) {
            power /= 5.0;
        }
        else if (n / power <= 2.0 && this.vWidth > 350) {
            power /= 5.0;
        }
        else if (n / power <= 2.0) {
            power /= 2.0;
        }
        else if (n / power <= 4.0) {
            power /= 2.0;
        }
        return power;
    }
    
    private double pixSize(final double n, final boolean b) {
        if (b) {
            return n * this.hWidth / this.xWidth;
        }
        return n * this.vWidth / this.yWidth;
    }
    
    private double getTickSubinterval(final double n, final boolean b) {
        if (this.pixSize(n / 10.0, b) > 10.0) {
            return n / 10.0;
        }
        if (this.pixSize(n / 5.0, b) > 10.0) {
            return n / 5.0;
        }
        if (this.pixSize(n / 2.0, b) > 10.0) {
            return n / 2.0;
        }
        return n;
    }
    
    private void labelxTick(final double n, final int n2, final int n3, final int n4) {
        final FontMetrics fontMetrics = this.g.getFontMetrics();
        final int n5 = (Math.abs(n) > 1.0) ? 5 : 14;
        final String string = Double.toString(Math.round(MyMath.power(10.0, n5) * n) / MyMath.power(10.0, n5));
        final int stringWidth = fontMetrics.stringWidth(string);
        final int n6 = n4 + fontMetrics.getAscent() + 1;
        if (n3 + n6 < this.vMax) {
            this.g.drawString(string, this.xTOh(n) - stringWidth / 2, n3 + n6);
        }
        else {
            this.g.drawString(string, this.xTOh(n) - stringWidth / 2, n3 - n4 - fontMetrics.getDescent() - 1);
        }
    }
    
    private void labelyTick(final double n, final int n2, final int n3, final int n4) {
        final FontMetrics fontMetrics = this.g.getFontMetrics();
        final int n5 = (Math.abs(n) > 1.0) ? 5 : 14;
        final String string = Double.toString(Math.round(MyMath.power(10.0, n5) * n) / MyMath.power(10.0, n5));
        final int n6 = fontMetrics.stringWidth(string) + n4 + 2;
        if (n2 - n6 > this.hMin) {
            this.g.drawString(string, n2 - n6, this.yTOv(n) + fontMetrics.getAscent() / 2);
        }
        else {
            this.g.drawString(string, n2 + n4 + 2, this.yTOv(n) + fontMetrics.getAscent() / 2);
        }
    }
}
