import java.io.IOException;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.awt.Rectangle;
import java.util.Iterator;
import java.awt.geom.Rectangle2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Point2D;
import java.awt.font.TextLayout;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class PolygonPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    public static final int DIAGRAM_WIDTH = 500;
    public static final int DIAGRAM_HEIGHT = 500;
    public static final int DIAGRAM_PADDING = 40;
    public static final int DEFAULT = 1;
    public static final int DEFINE = 2;
    public static final int HIDE = 3;
    public static final int ADVANCED = 4;
    public static final int DIAGRAMS = 5;
    public static final String FONT_NAME = "Arial";
    public static final int FONT_STYLE = 1;
    public static final int FONT_SIZE = 20;
    public static final int LINE_THICKNESS = 3;
    public static final int DASH_SIZE_B = 3;
    public static final int DASH_SIZE_C = 12;
    protected static final Color monoBackgroundColor;
    protected static final Color colorBackgroundColor;
    protected static final Color textColor;
    public Color[] contourColors;
    public float colorAlpha;
    public double[] pops;
    public PolygonDiagram polygonDiagram;
    public double diagramChecksum;
    public ExperimentPanel experimentPanel;
    public boolean useColor;
    public boolean useShading;
    public boolean useDashes;
    public int labelsStatus;
    public boolean randomColors;
    protected static Random random;
    public static ArrayList<Area> areas;
    public static ArrayList<Polygon> polygons;
    public static ArrayList<String> strings;
    public static ArrayList<Point> stringPoints;
    
    static {
        monoBackgroundColor = Color.white;
        colorBackgroundColor = Color.white;
        textColor = Color.black;
        PolygonPanel.random = new Random();
        PolygonPanel.areas = new ArrayList<Area>();
        PolygonPanel.polygons = new ArrayList<Polygon>();
        PolygonPanel.strings = new ArrayList<String>();
        PolygonPanel.stringPoints = new ArrayList<Point>();
    }
    
    public PolygonPanel(final int diagramType, final boolean improveLayout, final boolean simpleVersion, final double[] pops) {
        this.contourColors = new Color[] { Color.gray, Color.red, Color.green, Color.blue };
        this.colorAlpha = 0.2f;
        this.pops = new double[8];
        this.useColor = true;
        this.useShading = false;
        this.useDashes = false;
        this.labelsStatus = 3;
        this.randomColors = false;
        this.pops = pops;
        this.updatePanel(0, diagramType, improveLayout, simpleVersion);
    }
    
    public PolygonDiagram getPolygonDiagram() {
        return this.polygonDiagram;
    }
    
    public void setExperimentPanel(final ExperimentPanel experimentPanel) {
        this.experimentPanel = experimentPanel;
    }
    
    public void paintComponent(final Graphics g) {
        final Graphics2D g2 = (Graphics2D)g;
        if (this.useColor) {
            this.setBackground(PolygonPanel.colorBackgroundColor);
        }
        else {
            this.setBackground(PolygonPanel.monoBackgroundColor);
        }
        super.paintComponent(g2);
        if (this.polygonDiagram == null) {
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            return;
        }
        final Font font = new Font("Arial", 1, 20);
        final FontRenderContext frc = g2.getFontRenderContext();
        final Curve[] ps = this.polygonDiagram.getCurves();
        for (int i = 1; i <= 3; ++i) {
            if (this.useColor) {
                final Color lineColor = this.contourColors[i];
                g2.setColor(lineColor);
            }
            else {
                g2.setColor(Color.black);
            }
            if (this.useDashes) {
                g2.setStroke(new BasicStroke(3.0f, 0, 1, 0.0f, null, 0.0f));
                if (i == 2) {
                    g2.setStroke(new BasicStroke(3.0f, 0, 1, 0.0f, new float[] { 3.0f }, 0.0f));
                }
                if (i == 3) {
                    g2.setStroke(new BasicStroke(3.0f, 0, 1, 0.0f, new float[] { 12.0f }, 0.0f));
                }
            }
            else {
                g2.setStroke(new BasicStroke(3.0f, 0, 1, 0.0f, null, 0.0f));
            }
            g2.draw(ps[i].getPolygon());
            if (this.useShading) {
                final Color shadingColor = new Color(this.contourColors[i].getRed() / 255, this.contourColors[i].getGreen() / 255, this.contourColors[i].getBlue() / 255, this.colorAlpha);
                g2.setColor(shadingColor);
                g2.fill(ps[i].getPolygon());
            }
        }
        if (this.labelsStatus != 3) {
            for (int i = 1; i <= 3; ++i) {
                final String label = Util.contourIndex[i];
                final TextLayout labelLayout = new TextLayout(label, font, frc);
                final Point2D.Double labelPoint = Util.betweenPoints(ps[i].getP4(), ps[i].getP5(), 0.2);
                int x = (int)labelPoint.x;
                int y = (int)labelPoint.y;
                final Rectangle2D textBBox = labelLayout.getBounds();
                x -= (int)textBBox.getWidth() / 2;
                y += (int)textBBox.getHeight() / 2;
                textBBox.setRect(textBBox.getX() + x - 2.0, textBBox.getY() + y - 2.0, textBBox.getWidth() + 4.0, textBBox.getHeight() + 4.0);
                if (this.useColor) {
                    g2.setColor(PolygonPanel.colorBackgroundColor);
                }
                else {
                    g2.setColor(PolygonPanel.monoBackgroundColor);
                }
                g2.fill(textBBox);
                if (this.useColor) {
                    g2.setColor(this.contourColors[i]);
                }
                else {
                    g2.setColor(Color.black);
                }
                labelLayout.draw(g2, x, y);
            }
            final Point2D.Double[][] polyZones = this.polygonDiagram.findZonePolygonArrays();
            final double areaOfABC = Util.computePolygonArea(polyZones[7]);
            final Double userInputAreaOfABC = this.pops[7];
            for (int j = 1; j < 8; ++j) {
                final Polygon realPolygon = new Polygon();
                for (int k = 0; k < polyZones[j].length; ++k) {
                    realPolygon.addPoint((int)polyZones[j][k].x, (int)polyZones[j][k].y);
                }
                final Area zoneArea = new Area(realPolygon);
                final Double areaOfZone = this.pops[j];
                final String zoneAreaString = Double.toString(areaOfZone);
                this.putZoneLabel(g2, j, zoneArea, zoneAreaString);
            }
        }
        int i = 0;
        for (final Area a : PolygonPanel.areas) {
            if (i >= this.contourColors.length) {
                i = 0;
            }
            final Color solidColor = this.contourColors[i];
            final Color c = new Color(solidColor.getRed() / 255, solidColor.getGreen() / 255, solidColor.getBlue() / 255, 0.2f);
            g2.setColor(c);
            if (!a.isEmpty()) {
                g2.fill(a);
            }
            ++i;
        }
        for (final Polygon p : PolygonPanel.polygons) {
            g2.setStroke(new BasicStroke(1.0f));
            g2.setColor(Color.blue);
            g2.draw(p);
        }
        for (int l = 0; l < PolygonPanel.strings.size(); ++l) {
            g2.setColor(Color.red);
            final String s = PolygonPanel.strings.get(l);
            final Point p2 = PolygonPanel.stringPoints.get(l);
            final TextLayout labelLayout2 = new TextLayout(s, font, frc);
            labelLayout2.draw(g2, p2.x, p2.y);
        }
        for (final ArrayList<Point2D.Double> p3 : PolygonDiagram.polygons) {
            final Polygon poly = new Polygon();
            for (final Point2D.Double point : p3) {
                poly.addPoint((int)point.x, (int)point.y);
            }
            g2.setStroke(new BasicStroke(1.0f));
            g2.setColor(Color.BLACK);
            g2.draw(poly);
        }
    }
    
    public void putZoneLabel(final Graphics2D g2, final int zoneIndex, final Area zone, final String label) {
        if (label.length() == 0) {
            return;
        }
        int x = 0;
        int y = 0;
        final Point center = this.getLabelCentre(zoneIndex, zone);
        if (center != null) {
            x = center.x;
            y = center.y;
        }
        else {
            x = 100 + zoneIndex * 50;
            y = 20;
        }
        final Font font = new Font("Arial", 1, 20);
        final FontRenderContext frc = g2.getFontRenderContext();
        final TextLayout labelLayout = new TextLayout(label, font, frc);
        final Rectangle2D textBBox = labelLayout.getBounds();
        x -= (int)textBBox.getWidth() / 2;
        y += (int)textBBox.getHeight() / 2;
        textBBox.setRect(textBBox.getX() + x - 2.0, textBBox.getY() + y - 2.0, textBBox.getWidth() + 4.0, textBBox.getHeight() + 4.0);
        if (this.useColor) {
            g2.setColor(PolygonPanel.colorBackgroundColor);
        }
        else {
            g2.setColor(PolygonPanel.monoBackgroundColor);
        }
        g2.fill(textBBox);
        g2.setColor(PolygonPanel.textColor);
        labelLayout.draw(g2, x, y);
    }
    
    public Point centreOfPolygon(final int zoneIndex) {
        final Point2D.Double[][] polyZones = this.polygonDiagram.findZonePolygonArrays();
        final Point2D.Double[] polygon = polyZones[zoneIndex];
        double xTotal = 0.0;
        double yTotal = 0.0;
        for (int i = 0; i < polygon.length; ++i) {
            xTotal += polygon[i].x;
            yTotal += polygon[i].y;
        }
        final int xCentre = Util.convertToInteger(xTotal / polygon.length);
        final int yCentre = Util.convertToInteger(yTotal / polygon.length);
        return new Point(xCentre, yCentre);
    }
    
    public Point getLabelCentre(final int zoneIndex, final Area a) {
        final Rectangle r = this.findCompletelyContainedRectangle(a);
        if (r == null) {
            return this.centreOfPolygon(zoneIndex);
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
            divider *= 2;
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
    
    public boolean updatePanel(final int millisecondDelay, final int diagramType, final boolean improveLayout, final boolean simpleVersion) {
        boolean result = true;
        this.polygonDiagram = null;
        try {
            this.polygonDiagram = new PolygonDiagram(this.pops[1], this.pops[2], this.pops[4], this.pops[3], this.pops[5], this.pops[6], this.pops[7], diagramType, improveLayout, simpleVersion);
            this.diagramChecksum = this.polygonDiagram.getChecksum();
            this.centreDiagram();
        }
        catch (Exception e) {
            result = false;
        }
        sleep(millisecondDelay);
        this.update(this.getGraphics());
        return result;
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
    
    public void centreDiagram() {
        final double panelWidth = 460.0;
        final double panelHeight = 460.0;
        final double diagramWidth = this.polygonDiagram.findWidth();
        final double diagramHeight = this.polygonDiagram.findHeight();
        double scale = panelWidth / diagramWidth;
        if (panelWidth / diagramWidth > panelHeight / diagramHeight) {
            scale = panelHeight / diagramHeight;
        }
        this.polygonDiagram.scaleDiagram(scale);
        final Point diagramCentre = this.polygonDiagram.findDiagramCentrePoint();
        final int xDiff = diagramCentre.x - 250;
        final int yDiff = diagramCentre.y - 250;
        this.polygonDiagram.moveDiagram(-xDiff, -yDiff);
    }
    
    public String generateSVG() {
        final StringBuffer ret = new StringBuffer();
        ret.append("<?xml version=\"1.0\" standalone=\"no\"?>\n");
        ret.append("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\"\n");
        ret.append("\"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n");
        ret.append("<svg width=\"100%\" height=\"100%\" version=\"1.1\"\n");
        ret.append("xmlns=\"http://www.w3.org/2000/svg\">\n\n");
        final Curve[] ps = this.polygonDiagram.getCurves();
        for (int i = 1; i <= 3; ++i) {
            Color solidColor = Color.black;
            String fillText = "fill:none; ";
            String dashText = "";
            if (this.useColor) {
                solidColor = this.contourColors[i];
            }
            if (this.useShading) {
                fillText = "fill:rgba(" + solidColor.getRed() + "," + solidColor.getGreen() + "," + solidColor.getBlue() + "," + Util.convertToInteger(this.colorAlpha * 100.0f) / 100.0 + "); ";
            }
            if (this.useDashes) {
                if (i == 1) {
                    dashText = "stroke-dasharray: 3, 3; ";
                }
                if (i == 2) {
                    dashText = "stroke-dasharray: 12, 12; ";
                }
            }
            final String polygonSVG = ps[i].generateSVGPolygon(String.valueOf(fillText) + dashText + "stroke:rgb(" + solidColor.getRed() + "," + solidColor.getGreen() + "," + solidColor.getBlue() + ");stroke-width:" + 3);
            ret.append(polygonSVG);
            ret.append("\n");
        }
        ret.append("\n");
        if (this.labelsStatus != 3) {
            for (int i = 1; i <= 3; ++i) {
                final String label = Util.contourIndex[i];
                final Point2D.Double labelPoint = Util.betweenPoints(ps[i].getP4(), ps[i].getP5(), 0.2);
                int x = (int)labelPoint.x;
                int y = (int)labelPoint.y;
                final int rectangleWidth = 30;
                final int rectangleHeight = 30;
                x -= 15;
                y += 15;
                final String backgroundColor = "white";
                final String rectangleText = "<rect x=\"" + x + "\" y=\"" + (y - 30) + "\" width=\"" + 30 + "\" height=\"" + 30 + "\" style=\"fill:" + backgroundColor + ";stroke:" + backgroundColor + "\"/>";
                ret.append(rectangleText);
                ret.append("\n");
                Color contourLabelColor = Color.black;
                if (this.useColor) {
                    contourLabelColor = this.contourColors[i];
                }
                final String contourText = "<text x=\"" + (int)(x + 8.0) + "\" y=\"" + (int)(y - 8.0) + "\" font-size=\"" + 20 + "\" style=\"stroke:rgb(" + contourLabelColor.getRed() + "," + contourLabelColor.getGreen() + "," + contourLabelColor.getBlue() + ")\">" + label + "</text>";
                ret.append(contourText);
                ret.append("\n");
            }
            ret.append("\n");
            final Point2D.Double[][] polyZones = this.polygonDiagram.findZonePolygonArrays();
            final double areaOfABC = Util.computePolygonArea(polyZones[7]);
            final Double userInputAreaOfABC = this.pops[7];
            for (int j = 1; j < 8; ++j) {
                Double areaOfZone = Util.computePolygonArea(polyZones[j]);
                final Polygon realPolygon = new Polygon();
                for (int k = 0; k < polyZones[j].length; ++k) {
                    realPolygon.addPoint((int)polyZones[j][k].x, (int)polyZones[j][k].y);
                }
                final Area zoneArea = new Area(realPolygon);
                areaOfZone /= areaOfABC;
                areaOfZone *= userInputAreaOfABC;
                areaOfZone = Util.round(areaOfZone, 2);
                final String zoneAreaString = Double.toString(areaOfZone);
                final String zoneText = this.getZoneLabelSVG(j, zoneArea, zoneAreaString);
                ret.append(zoneText);
                ret.append("\n");
            }
            ret.append("\n");
        }
        ret.append("</svg>\n");
        return ret.toString();
    }
    
    public String getZoneLabelSVG(final int zoneIndex, final Area zone, final String label) {
        int x = 0;
        int y = 0;
        final Point center = this.getLabelCentre(zoneIndex, zone);
        if (center != null) {
            x = center.x - 8;
            y = center.y;
        }
        else {
            x = 100 + zoneIndex * 50;
            y = 20;
        }
        final Color zoneLabelColor = Color.black;
        final String zoneText = "<text x=\"" + x + "\" y=\"" + y + "\" font-size=\"" + 20 + "\" style=\"stroke:rgb(" + zoneLabelColor.getRed() + "," + zoneLabelColor.getGreen() + "," + zoneLabelColor.getBlue() + ")\">" + label + "</text>";
        return zoneText;
    }
    
    public boolean saveSVGFile(final File file) {
        try {
            final BufferedWriter b = new BufferedWriter(new FileWriter(file));
            final String diagramSVG = this.generateSVG();
            b.write(diagramSVG);
            b.newLine();
            b.close();
        }
        catch (IOException e) {
            System.out.println("An IO exception occured when executing saveSVGFile(" + file.getName() + ") in Graph.java: " + e + "\n");
            return false;
        }
        return true;
    }
}
