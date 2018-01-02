import java.awt.geom.Ellipse2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.Point;
import java.awt.font.FontRenderContext;
import java.awt.geom.Area;
import java.awt.font.TextLayout;
import java.awt.Font;
import java.awt.Shape;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Color;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class CirclePanel extends JPanel
{
    public static final int DIAGRAM_WIDTH = 400;
    public static final int DIAGRAM_HEIGHT = 400;
    public static final int DIAGRAM_PADDING = 80;
    public static final int DEFAULT = 1;
    public static final int DEFINE = 2;
    public static final int HIDE = 3;
    public static final int ADVANCED = 4;
    public static final double VARIANCE_MULTIPLIER = 1000.0;
    public static final String FONT_NAME = "Arial";
    public static final int FONT_STYLE = 1;
    public static final int FONT_SIZE = 12;
    protected static final Color monoBackgroundColor;
    protected static final Color colorBackgroundColor;
    protected static final Color textColor;
    public Color[] circleColors;
    public double[] pops;
    public String[] zoneLabels;
    public CircleLayout[] circles;
    public boolean useColor;
    public int labelsStatus;
    public boolean randomColors;
    protected static Random random;
    
    static {
        monoBackgroundColor = Color.white;
        colorBackgroundColor = Color.black;
        textColor = Color.black;
        CirclePanel.random = new Random();
    }
    
    public CirclePanel(final CircleLayout[] circles, final double[] pops) {
        this.circleColors = new Color[] { Color.gray, Color.red, Color.green, Color.yellow, Color.blue, Color.magenta, Color.cyan, Color.white };
        this.pops = new double[8];
        this.zoneLabels = new String[] { "Not Used", "", "", "", "", "", "", "" };
        this.useColor = true;
        this.labelsStatus = 1;
        this.randomColors = false;
        this.pops = pops;
        this.circles = circles;
        this.resetLabels();
    }
    
    public static Color getRandomColor() {
        return new Color(CirclePanel.random.nextInt(255), CirclePanel.random.nextInt(255), CirclePanel.random.nextInt(255));
    }
    
    public void paintComponent(final Graphics g) {
        final Graphics2D g2 = (Graphics2D)g;
        if (this.useColor) {
            this.setBackground(CirclePanel.colorBackgroundColor);
            if (this.randomColors) {
                final Color cA = getRandomColor();
                final Color cB = getRandomColor();
                final Color cC = getRandomColor();
                this.circleColors[1] = cA;
                this.circleColors[2] = cB;
                this.circleColors[4] = cC;
                this.circleColors[3] = this.mixColors(cA, cB);
                this.circleColors[5] = this.mixColors(cA, cC);
                this.circleColors[6] = this.mixColors(cB, cC);
                this.circleColors[7] = this.mixColors(cA, this.mixColors(cB, cC));
            }
        }
        else {
            this.setBackground(CirclePanel.monoBackgroundColor);
        }
        super.paintComponent(g2);
        final Area[] zoneArea = getZonesAsAreas(this.circles, g2);
        System.out.println("Singular Test");
        for (int i = 1; i < 8; ++i) {
            System.out.println(String.valueOf(i) + " " + zoneArea[i].isSingular());
        }
        if (this.useColor) {
            for (int i = 1; i < 8; ++i) {
                g2.setColor(this.circleColors[i]);
                g2.fill(zoneArea[i]);
            }
        }
        else {
            g2.setColor(Color.black);
            g2.draw(zoneArea[1]);
            g2.draw(zoneArea[2]);
            g2.draw(zoneArea[3]);
            g2.draw(zoneArea[4]);
            g2.draw(zoneArea[5]);
            g2.draw(zoneArea[6]);
        }
        if (this.labelsStatus != 3) {
            final double fitness = CircleAreaRunner.computeFitness(this.pops, this.circles);
            for (int j = 1; j < 8; ++j) {
                if (this.labelsStatus == 1) {
                    this.putZoneLabel(g2, j, zoneArea[j], this.getDefaultZoneLabel(j));
                }
                else if (this.labelsStatus == 2) {
                    this.putZoneLabel(g2, j, zoneArea[j], this.zoneLabels[j]);
                }
                else if (this.labelsStatus == 4) {
                    this.putZoneLabel(g2, j, zoneArea[j], this.getAdvancedZoneLabel(j));
                }
            }
            if (this.labelsStatus == 4) {
                final String label = "Fitness: " + fitness;
                final Font font = new Font("Arial", 1, 12);
                final FontRenderContext frc = g2.getFontRenderContext();
                final TextLayout labelLayout = new TextLayout(label, font, frc);
                if (Color.black.equals(this.getBackground())) {
                    g2.setColor(Color.white);
                }
                else {
                    g2.setColor(Color.black);
                }
                labelLayout.draw(g2, 30.0f, 30.0f);
            }
        }
    }
    
    public String getDefaultZoneLabel(final int i) {
        return String.valueOf(CircleAreaRunner.zoneIndex[i]) + " " + this.pops[i];
    }
    
    public String getAdvancedZoneLabel(final int i) {
        double[] areas = CircleAreaRunner.computeArea(this.circles);
        areas = CircleAreaRunner.convertArea1to1Ratio(areas, this.pops);
        return String.valueOf(CircleAreaRunner.zoneIndex[i]) + " " + (int)this.pops[i] + ";" + (int)areas[i];
    }
    
    public void resetLabels() {
        for (int i = 1; i < 8; ++i) {
            this.zoneLabels[i] = this.getDefaultZoneLabel(i);
        }
    }
    
    public static double round(final double inAmount, final int decimalPlaces) {
        long divider = 1L;
        for (int i = 1; i <= decimalPlaces; ++i) {
            divider *= 10L;
        }
        final double largeAmount = Math.rint(inAmount * divider);
        return largeAmount / divider;
    }
    
    public void putZoneLabel(final Graphics2D g2, final int zoneIndex, final Area zone, final String label) {
        if (label.length() == 0) {
            return;
        }
        int x = 0;
        int y = 0;
        final Point center = this.getLabelCenter(zone);
        if (center != null) {
            x = center.x;
            y = center.y;
        }
        else {
            x = 100 + zoneIndex * 50;
            y = 20;
        }
        final Font font = new Font("Arial", 1, 12);
        final FontRenderContext frc = g2.getFontRenderContext();
        final TextLayout labelLayout = new TextLayout(label, font, frc);
        final Rectangle2D textBBox = labelLayout.getBounds();
        x -= (int)textBBox.getWidth() / 2;
        y += (int)textBBox.getHeight() / 2;
        textBBox.setRect(textBBox.getX() + x - 2.0, textBBox.getY() + y - 2.0, textBBox.getWidth() + 4.0, textBBox.getHeight() + 4.0);
        if (this.useColor) {
            g2.setColor(this.circleColors[zoneIndex]);
        }
        else {
            g2.setColor(CirclePanel.monoBackgroundColor);
        }
        g2.fill(textBBox);
        g2.setColor(CirclePanel.textColor);
        labelLayout.draw(g2, x, y);
    }
    
    public Point getLabelCenter(final Area a) {
        final Rectangle r = this.findCompletelyContainedRectangle(a);
        if (r == null) {
            return null;
        }
        while (a.contains(r)) {
            final Rectangle rectangle = r;
            --rectangle.x;
            final Rectangle rectangle2 = r;
            ++rectangle2.width;
        }
        final Rectangle rectangle3 = r;
        ++rectangle3.x;
        final Rectangle rectangle4 = r;
        --rectangle4.width;
        while (a.contains(r)) {
            final Rectangle rectangle5 = r;
            --rectangle5.y;
            final Rectangle rectangle6 = r;
            ++rectangle6.height;
        }
        final Rectangle rectangle7 = r;
        ++rectangle7.y;
        final Rectangle rectangle8 = r;
        --rectangle8.height;
        while (a.contains(r)) {
            final Rectangle rectangle9 = r;
            ++rectangle9.width;
        }
        final Rectangle rectangle10 = r;
        --rectangle10.width;
        while (a.contains(r)) {
            final Rectangle rectangle11 = r;
            ++rectangle11.height;
        }
        final Rectangle rectangle12 = r;
        --rectangle12.height;
        final int x = r.x + r.width / 2;
        final int y = r.y + r.height / 2;
        return new Point(x, y);
    }
    
    public Rectangle findCompletelyContainedRectangle(final Area a) {
        final Rectangle bbox = a.getBounds();
        if (bbox.width < 3) {
            return null;
        }
        int divider = 3;
        final Rectangle containedRectangle = null;
        while (containedRectangle == null) {
            final int width = bbox.width / divider;
            final int height = bbox.height / divider;
            if (width == 0 || height == 0) {
                return null;
            }
            for (int xGrid = 1; xGrid < divider - 1; ++xGrid) {
                for (int yGrid = 1; yGrid < divider - 1; ++yGrid) {
                    final int x = bbox.x + xGrid * width;
                    final int y = bbox.y + yGrid * height;
                    final Rectangle r = new Rectangle(x, y, width, height);
                    if (a.contains(r)) {
                        return r;
                    }
                }
            }
            ++divider;
        }
        return null;
    }
    
    public Color mixColors(final Color c1, final Color c2) {
        final int r1 = c1.getRed();
        final int g1 = c1.getGreen();
        final int b1 = c1.getBlue();
        final int r2 = c2.getRed();
        final int g2 = c2.getGreen();
        final int b2 = c2.getBlue();
        final Color ret = new Color(r1 ^ r2, g1 ^ g2, b1 ^ b2);
        return ret;
    }
    
    public static Area[] getZonesAsAreas(final CircleLayout[] circles, final Graphics2D g2) {
        final Ellipse2D.Double ellipseA = CircleAreaRunner.ellipseFromCircle(circles[0]);
        final Ellipse2D.Double ellipseB = CircleAreaRunner.ellipseFromCircle(circles[1]);
        final Ellipse2D.Double ellipseC = CircleAreaRunner.ellipseFromCircle(circles[2]);
        final Area areaA = new Area(ellipseA);
        final Area areaB = new Area(ellipseB);
        final Area areaC = new Area(ellipseC);
        final Area[] zones = new Area[8];
        (zones[1] = new Area(areaA)).subtract(areaB);
        zones[1].subtract(areaC);
        (zones[2] = new Area(areaB)).subtract(areaA);
        zones[2].subtract(areaC);
        (zones[3] = new Area(areaA)).intersect(areaB);
        zones[3].subtract(areaC);
        (zones[4] = new Area(areaC)).subtract(areaA);
        zones[4].subtract(areaB);
        (zones[5] = new Area(areaA)).intersect(areaC);
        zones[5].subtract(areaB);
        (zones[6] = new Area(areaB)).intersect(areaC);
        zones[6].subtract(areaA);
        (zones[7] = new Area(areaA)).intersect(areaB);
        zones[7].intersect(areaC);
        return zones;
    }
    
    public void updatePanel(final int millisecondDelay) {
        sleep(millisecondDelay);
        this.update(this.getGraphics());
    }
    
    protected void runHillClimber(final boolean update) {
        CircleAreaRunner.circlePanel = this;
        CircleAreaRunner.hillClimber(this.pops, this.circles, update);
    }
    
    public static boolean sleep(final int time) {
        try {
            Thread.sleep(time);
        }
        catch (Exception e) {
            System.out.println("Exception occurred in Thread.sleep() in CirclePanel.sleep " + e);
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
