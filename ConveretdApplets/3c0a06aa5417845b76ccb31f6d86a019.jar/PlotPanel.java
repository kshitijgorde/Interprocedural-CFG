import java.awt.Graphics;
import java.awt.Point;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class PlotPanel extends JPanel
{
    private static final Color COLOR_LABEL;
    private int _height;
    private int _width;
    private int _xMax;
    private int _yMax;
    
    public PlotPanel() {
        this.setLayout(null);
        this.setBackground(Main.WINDOW_COLOR);
        final boolean b = true;
        this._yMax = (b ? 1 : 0);
        this._xMax = (b ? 1 : 0);
        this._width = (b ? 1 : 0);
        this._height = (b ? 1 : 0);
    }
    
    public PlotPanel(final int x, final int y, final int width, final int height, final String yAxis, final String xAxis, final int xMax, final int yMax) {
        this._height = height;
        this._width = width;
        this._xMax = xMax;
        this._yMax = yMax;
        this.setLayout(null);
        this.setBackground(Main.WINDOW_COLOR);
        this.setBounds(x, y, this._width, this._height);
        final CLabel yLabel = new CLabel(yAxis, 10, this._height / 2 - yAxis.length() * 4, 12, PlotPanel.COLOR_LABEL);
        yLabel.setRotation(2);
        yLabel.setHorizontalAlignment(2);
        yLabel.setVerticalAlignment(1);
        this.add(yLabel);
        this.add(new CLabel(xAxis, this._width / 2 - xAxis.length() * 4, this._height - 30, 12, PlotPanel.COLOR_LABEL));
    }
    
    public Point getXY(final int x, final int y) {
        final int newX = (int)(x / this._xMax * (this._width - 50.0) + 30.0);
        final int newY = (int)((1.0 - y / this._yMax) * (this._height - 40.0) + 10.0);
        return new Point(newX, newY);
    }
    
    public void paint(final Graphics g) {
        super.paint(g);
        if (this._width == 1 && this._height == 1) {
            return;
        }
        final int x1 = 30;
        final int y1 = 10;
        final int x2 = this._width - 20;
        final int y2 = this._height - 30;
        g.drawLine(x1, y1, x1, y2);
        g.drawLine(x1, y2, x2, y2);
    }
    
    static {
        COLOR_LABEL = new Color(0, 0, 128);
    }
}
