import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class GraphArea extends JPanel
{
    private final int MAX_CURVES = 5;
    public static Color[] colors;
    private int pixelsPerUnit;
    private double increment;
    private int graphAreaWidth;
    private int graphAreaHeight;
    private Dimension center;
    private Calculator calc;
    private Curve[] curves;
    private boolean gridLineVisible;
    private Color gridColor;
    private Color backgroundColor;
    
    public GraphArea() {
        this.pixelsPerUnit = 20;
        this.increment = 1.0 / this.pixelsPerUnit;
        this.graphAreaWidth = 520;
        this.graphAreaHeight = 520;
        this.calc = new Calculator();
        this.center = new Dimension(0, 0);
        this.getClass();
        this.curves = new Curve[5];
        int n = 0;
        while (true) {
            final int n2 = n;
            this.getClass();
            if (n2 >= 5) {
                break;
            }
            this.curves[n] = new Curve(null, GraphArea.colors[n]);
            ++n;
        }
        this.gridLineVisible = true;
        this.gridColor = new Color(230, 230, 230);
        this.setBackground(this.backgroundColor = new Color(255, 255, 255));
    }
    
    public void setEquation(String tempInsert, final int n) {
        if (tempInsert != null) {
            tempInsert = this.tempInsert(tempInsert);
        }
        this.curves[n].setExpression(tempInsert);
    }
    
    public void setCenter(final int n, final int n2) {
        this.center.setSize(n, n2);
        this.repaint();
    }
    
    public void setPixelsPerUnit(final int pixelsPerUnit) {
        this.pixelsPerUnit = pixelsPerUnit;
        this.increment = 1.0 / this.pixelsPerUnit;
    }
    
    public void setGridVisible(final boolean gridLineVisible) {
        this.gridLineVisible = gridLineVisible;
    }
    
    public void setGridColor(final Color gridColor) {
        this.gridColor = gridColor;
    }
    
    public void setBackgroundColor(final Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    
    public int getPixelsPerUnit() {
        return this.pixelsPerUnit;
    }
    
    public double getIncrement() {
        return this.increment;
    }
    
    public int getGraphAreaWidth() {
        return this.graphAreaWidth;
    }
    
    public int getGraphAreaHeight() {
        return this.graphAreaHeight;
    }
    
    public Dimension getCenter() {
        return this.center;
    }
    
    public Curve[] getCurves() {
        return this.curves;
    }
    
    public Color[] getColors() {
        return GraphArea.colors;
    }
    
    public boolean getGridLineVisible() {
        return this.gridLineVisible;
    }
    
    public Color getGridColor() {
        return this.gridColor;
    }
    
    public Color getBackgroundColor() {
        return this.backgroundColor;
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        this.setBackground(this.backgroundColor);
        this.drawAxis(graphics);
        int n = 0;
        while (true) {
            final int n2 = n;
            this.getClass();
            if (n2 >= 5) {
                break;
            }
            if (this.curves[n].isVisible() && this.curves[n].getExpression() != null) {
                this.drawCurve(this.curves[n].getExpression(), this.curves[n].getColor(), graphics);
            }
            ++n;
        }
    }
    
    private void drawAxis(final Graphics graphics) {
        final int round = Math.round(this.graphAreaWidth / 2);
        final int round2 = Math.round(this.graphAreaHeight / 2);
        if (this.gridLineVisible) {
            for (int i = 0; i < round; i += this.pixelsPerUnit) {
                graphics.setColor(this.gridColor);
                graphics.drawLine(-i + round, 0, -i + round, 2 * round2);
                graphics.drawLine(i + round, 0, i + round, 2 * round2);
                graphics.setColor(Color.BLACK);
                graphics.drawLine(i + round, -5 + this.getXAxisY(), i + round, 5 + this.getXAxisY());
                graphics.drawLine(-i + round, -5 + this.getXAxisY(), -i + round, 5 + this.getXAxisY());
            }
            for (int j = 0; j < round2; j += this.pixelsPerUnit) {
                graphics.setColor(this.gridColor);
                graphics.drawLine(0, j + round, 2 * round, j + round2);
                graphics.drawLine(0, -j + round, 2 * round, -j + round2);
                graphics.setColor(Color.BLACK);
                graphics.drawLine(-5 + this.getYAxisX(), j + round2, 5 + this.getYAxisX(), j + round2);
                graphics.drawLine(-5 + this.getYAxisX(), -j + round2, 5 + this.getYAxisX(), -j + round2);
            }
        }
        graphics.setColor(Color.BLACK);
        graphics.drawLine(0, this.getXAxisY(), 2 * round, this.getXAxisY());
        graphics.drawLine(this.getYAxisX(), 0, this.getYAxisX(), 2 * round2);
    }
    
    public void drawCurve(String replaceAll, final Color color, final Graphics graphics) {
        replaceAll = Formatter.replaceAll(replaceAll, "X", "x");
        this.calc.calculate(Formatter.replaceAll(replaceAll, "x", new Double(this.center.getWidth() - this.getGraphAreaWidth() / 2 / this.pixelsPerUnit - 1.0).toString()));
        double n = this.calc.getAnswer();
        for (double n2 = this.center.getWidth() - this.getGraphAreaWidth() / 2 / this.pixelsPerUnit - 1.0 + this.increment; n2 < this.center.getWidth() + this.getGraphAreaWidth() / 2 / this.pixelsPerUnit + 1.0; n2 += this.increment) {
            final String replaceAll2 = Formatter.replaceAll(replaceAll, "x", new Double(n2).toString());
            graphics.setColor(color);
            this.calc.calculate(replaceAll2);
            if (!new Double(n).toString().equals("NaN")) {
                graphics.drawLine((int)Math.round((n2 - this.center.getWidth() - this.increment) * this.pixelsPerUnit) + Math.round(this.graphAreaWidth / 2), Math.round(this.graphAreaHeight / 2) - (int)Math.round((n - this.center.getHeight()) * this.pixelsPerUnit), (int)Math.round((n2 - this.center.getWidth()) * this.pixelsPerUnit) + Math.round(this.graphAreaWidth / 2), Math.round(this.graphAreaHeight / 2) - (int)Math.round((this.calc.getAnswer() - this.center.getHeight()) * this.pixelsPerUnit));
            }
            n = this.calc.getAnswer();
        }
    }
    
    private int getXAxisY() {
        return (int)Math.round(this.graphAreaHeight / 2 + this.center.getHeight() * this.pixelsPerUnit);
    }
    
    private int getYAxisX() {
        return (int)Math.round(this.graphAreaWidth / 2 - this.center.getWidth() * this.pixelsPerUnit);
    }
    
    private String tempInsert(String s) {
        s = Formatter.trim(s);
        for (int i = 0; i < s.length(); ++i) {
            if ((s.charAt(i) == 'X' || s.charAt(i) == 'x') && i != s.length() - 1 && (this.isDigit(s.charAt(i + 1)) || s.charAt(i + 1) == 'x' || s.charAt(i + 1) == 'X')) {
                s = s.substring(0, i + 1) + "*" + s.substring(i + 1, s.length());
            }
            if ((s.charAt(i) == 'X' || s.charAt(i) == 'x') && i > 0 && (this.isDigit(s.charAt(i - 1)) || s.charAt(i - 1) == ')')) {
                s = s.substring(0, i) + "*" + s.substring(i, s.length());
            }
        }
        return Formatter.replaceAll(s, "-", "-1*");
    }
    
    private boolean isDigit(final char c) {
        for (char c2 = '0'; c2 <= '9'; ++c2) {
            if (c == c2) {
                return true;
            }
        }
        return c == '.';
    }
    
    static {
        GraphArea.colors = new Color[] { new Color(0, 0, 102), new Color(0, 102, 0), new Color(102, 51, 153), new Color(255, 102, 20), new Color(51, 102, 255), new Color(0, 102, 102), new Color(0, 0, 51), new Color(0, 51, 0), new Color(51, 0, 51), new Color(0, 51, 51) };
    }
}
