import java.text.DecimalFormat;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.applet.Applet;
import java.util.Vector;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class MarketGraph
{
    private Font font1;
    private String title;
    private String xCaption;
    private String yCaption;
    private String background;
    private int topMargin;
    private int leftMargin;
    private int divisions;
    private int subDivision1;
    private int subDivision2;
    private int margin3d;
    private int depth3d;
    private Color foreColor;
    private Color backColor;
    private Color lineColor;
    private Color subColor;
    private Color chartColor;
    private boolean origin2Zero;
    public Graphics g;
    public Graphics bgGrfx;
    public Image scratchImage;
    public Image bgImage;
    private String[] graphDataNames;
    private String colorStyle;
    private String closeTime;
    private double maxValue;
    private double minValue;
    private double interval;
    private double chartAdjust;
    private int maxSubElement;
    private int expectedSubElements;
    private Color barColor;
    private Color lineGraph1Color;
    private Vector graphData1;
    private Vector graphData2;
    String alignment;
    int x1;
    int y1;
    int x2;
    int y2;
    int pX1;
    int pY1;
    int pX2;
    int pY2;
    private int width;
    private int height;
    private int numericWid;
    private Applet appletComponent;
    private BarGraph barGraph;
    private LineGraph lineGraph;
    private CoordinateSystem coordinateSystem;
    private Dimension corner1;
    private Dimension corner2;
    private Dimension titleXY;
    private Dimension xAxisXY;
    private Dimension yAxisXY;
    
    public MarketGraph(final Applet appletComponent, final int width, final int height) {
        this.closeTime = "";
        this.maxValue = 1000.0;
        this.minValue = 0.0;
        this.coordinateSystem = new CoordinateSystem();
        this.corner1 = new Dimension();
        this.corner2 = new Dimension();
        this.width = width;
        this.height = height;
        this.appletComponent = appletComponent;
        if (this.g == null) {
            this.scratchImage = appletComponent.createImage(width, height);
            this.g = this.scratchImage.getGraphics();
            this.bgImage = appletComponent.createImage(width, height);
            this.bgGrfx = this.bgImage.getGraphics();
        }
    }
    
    private void adjustMaxMin() {
        this.interval = (this.maxValue - this.minValue) / this.divisions;
        this.maxValue += this.interval * this.chartAdjust;
        this.minValue -= this.interval * this.chartAdjust;
    }
    
    private int computeAlignment(final int n, final int n2, final int n3, final String s) {
        if (s.equals("LEFT")) {
            return n;
        }
        if (s.equals("RIGHT")) {
            return n + (n3 - n2);
        }
        return n + (n3 - n2) / 2;
    }
    
    private void computeViewport() {
        final String s = "000000000000000000000000000000";
        this.numericWid = (String.valueOf((int)this.maxValue) + ".00").length();
        this.numericWid = this.appletComponent.getFontMetrics(this.font1).stringWidth(s.substring(0, this.numericWid));
        this.pX1 += this.numericWid;
        this.pY2 -= this.appletComponent.getFontMetrics(this.font1).getHeight();
        this.coordinateSystem.setOriginalRectangularPlane(this.pX1, this.pY1, this.pX2, this.pY2);
        this.coordinateSystem.setNewRectangularPlane(0.0, this.minValue, 1000.0, this.maxValue);
    }
    
    public void drawBackground() {
        this.barGraph = new BarGraph(this.coordinateSystem, this.g, this.expectedSubElements);
        this.lineGraph = new LineGraph(this.coordinateSystem, this.g, this.expectedSubElements);
        this.g.setColor(this.backColor);
        this.g.fillRect(0, 0, this.width, this.height);
        this.g.setColor(this.foreColor);
        this.x1 = this.leftMargin + 1;
        this.y1 = this.topMargin + 1;
        this.x2 = this.width - this.x1;
        this.y2 = this.height - this.y1;
        final Image image = this.appletComponent.createImage(1, 1);
        final String[] array = { this.title, this.xCaption, this.yCaption, this.background };
        for (int i = 0; i < 4; ++i) {
            final String[] string2Array = Convert.string2Array(array[i], ";");
            int intValue = 0;
            int intValue2 = 0;
            if (string2Array.length >= 2) {
                intValue = Integer.valueOf(string2Array[1]);
            }
            if (string2Array.length >= 3) {
                intValue2 = Integer.valueOf(string2Array[2]);
            }
            Image loadImage;
            if (array[i].trim().equals("")) {
                loadImage = image;
            }
            else {
                loadImage = this.loadImage(string2Array[0]);
            }
            int n = this.x1 + intValue;
            int computeAlignment;
            if (i == 0) {
                n = this.computeAlignment(n, loadImage.getWidth(this.appletComponent), this.x2 - this.x1, this.alignment);
                computeAlignment = this.y1 + intValue2;
                this.pY1 = computeAlignment + loadImage.getHeight(this.appletComponent);
            }
            else if (i == 1) {
                n = this.computeAlignment(n, loadImage.getWidth(this.appletComponent), this.x2 - this.x1, this.alignment);
                computeAlignment = this.y2 + intValue2 - loadImage.getHeight(this.appletComponent);
                this.pY2 = computeAlignment - 1;
            }
            else if (i == 2) {
                computeAlignment = this.computeAlignment(this.y1 + intValue2, loadImage.getHeight(this.appletComponent), this.y2 - this.y1, this.alignment);
                this.pX1 = n + loadImage.getWidth(this.appletComponent);
                this.pX2 = this.x2;
            }
            else {
                this.computeViewport();
                this.g.setColor(this.chartColor);
                this.g.fillRect(this.pX1, this.pY1, this.pX2 - this.pX1, this.pY2 - this.pY1);
                this.g.setClip(this.pX1, this.pY1, this.pX2 - this.pX1, this.pY2 - this.pY1);
                n = this.computeAlignment(this.pX1, loadImage.getWidth(this.appletComponent), this.pX2 - this.pX1, this.alignment) + intValue;
                computeAlignment = this.computeAlignment(this.pY1, loadImage.getHeight(this.appletComponent), this.pY2 - this.pY1, this.alignment) + intValue2;
            }
            this.g.drawImage(loadImage, n, computeAlignment, null);
        }
        this.g.setClip(0, 0, this.width, this.height);
        this.bgGrfx.drawImage(this.scratchImage, 0, 0, null);
    }
    
    public void drawCartesianPlane() {
        this.g.drawImage(this.bgImage, 0, 0, null);
        this.drawTickMarks();
        final int xo1 = this.coordinateSystem.xO1;
        final int yo1 = this.coordinateSystem.yO1;
        final int xl1 = this.coordinateSystem.xL1;
        final int yl1 = this.coordinateSystem.yL1;
        final int n = this.subDivision1 + 1;
        final int n2 = this.subDivision2 + 1;
        final int divisions = this.divisions;
        final int size = this.graphData1.size();
        final int abs = Math.abs(xl1 - xo1);
        final int abs2 = Math.abs(yo1 - yl1);
        this.g.setColor(this.subColor);
        double n3 = yl1;
        final double n4 = abs2 / (divisions * n);
        for (int i = 1; i < divisions * n; ++i) {
            n3 -= n4;
            this.g.drawLine(xo1, (int)n3, xo1 + abs, (int)n3);
        }
        double n5 = xo1;
        final double n6 = abs / (size * n2);
        for (int j = 1; j < size * n2; ++j) {
            n5 += n6;
            this.g.drawLine((int)n5, yl1, (int)n5, yl1 - abs2);
        }
        this.g.setColor(this.lineColor);
        double n7 = yl1;
        final double n8 = abs2 / divisions;
        for (int k = 1; k < divisions; ++k) {
            n7 -= n8;
            this.g.drawLine(xo1, (int)n7, xo1 + abs, (int)n7);
        }
        double n9 = xo1;
        final double n10 = abs / size;
        for (int l = 1; l < size; ++l) {
            n9 += n10;
            this.g.drawLine((int)n9, yl1, (int)n9, yl1 - abs2);
        }
        this.g.setColor(this.lineColor);
        this.g.drawRect(xo1 - 1, yl1 - abs2 - 1, abs + 2, abs2 + 2);
        this.g.drawRect(xo1 - 2, yl1 - abs2 - 2, abs + 4, abs2 + 4);
        this.g.setClip(xo1 - 1, yl1 - abs2 - 1, abs + 2, abs2 + 2);
    }
    
    private void drawTickMarks() {
        this.corner1.setSize(this.coordinateSystem.xO1, this.coordinateSystem.yO1);
        this.corner2.setSize(this.coordinateSystem.xL1, this.coordinateSystem.yL1);
        this.g.setColor(this.foreColor);
        final DecimalFormat decimalFormat = new DecimalFormat("0.00");
        this.interval = (this.maxValue - this.minValue) / this.divisions;
        final double interval = this.interval;
        final double n = (this.corner2.height - this.corner1.height) / this.divisions;
        double n2 = this.corner2.height + this.appletComponent.getFontMetrics(this.font1).getHeight() / 3;
        double minValue = this.minValue;
        for (int i = 0; i <= this.divisions; ++i) {
            final String format = decimalFormat.format(minValue);
            this.g.drawString(format, this.pX1 - this.appletComponent.getFontMetrics(this.font1).stringWidth(format) - 5, (int)n2);
            minValue += interval;
            n2 -= n;
        }
        final double n3 = (this.corner2.width - this.corner1.width) / this.graphDataNames.length;
        double n4 = this.corner1.width - 5.0;
        final double n5 = this.pY2 + this.appletComponent.getFontMetrics(this.font1).getHeight();
        for (int j = 0; j < this.graphDataNames.length; ++j) {
            this.g.drawString(this.graphDataNames[j], (int)n4, (int)n5);
            n4 += n3;
        }
        this.g.drawString(this.closeTime, (int)n4, (int)n5);
        this.g.setColor(Color.black);
        this.coordinateSystem.drawLine(this.g, 0.0, 0.0, 1000.0, 0.0);
    }
    
    private Image loadImage(final String s) {
        try {
            final Image image = this.appletComponent.getImage(this.appletComponent.getDocumentBase(), s);
            image.getHeight(this.appletComponent);
            while ((this.appletComponent.checkImage(image, this.appletComponent) & 0x20) == 0x0) {}
            return image;
        }
        catch (Exception ex) {
            this.g.drawString(ex.toString(), 10, 20);
            return this.appletComponent.createImage(1, 1);
        }
    }
    
    public void plotData() {
        this.barGraph.drawGraph(this.graphData1, Color.black, this.barColor);
        this.lineGraph.drawGraph(this.graphData2, this.lineGraph1Color);
        this.g.setClip(0, 0, this.width, this.height);
    }
    
    public void set3dValues(final String s, final String s2) {
        this.margin3d = Integer.valueOf(s);
        this.depth3d = Integer.valueOf(s2);
    }
    
    public void setColors(final String clr, final String clr2, final String clr3, final String clr4, final String clr5, final String clr6, final String clr7) {
        this.foreColor = Convert.string2Color(clr);
        this.backColor = Convert.string2Color(clr2);
        this.lineColor = Convert.string2Color(clr3);
        this.subColor = Convert.string2Color(clr4);
        this.chartColor = Convert.string2Color(clr5);
        this.barColor = Convert.string2Color(clr6);
        this.lineGraph1Color = Convert.string2Color(clr7);
    }
    
    public void setDivisions(final String s, final String s2, final String s3) {
        this.divisions = Integer.valueOf(s);
        this.subDivision1 = Integer.valueOf(s2);
        this.subDivision2 = Integer.valueOf(s3);
    }
    
    public void setFonts(final String s, final String s2) {
        this.font1 = new Font(s, 0, Integer.valueOf(s2));
        this.g.setFont(this.font1);
    }
    
    public void setGraphVector(final Vector graphData1, final Vector graphData2, final String[] graphDataNames, final double maxValue, final double minValue, final String s, final String closeTime) {
        this.graphData1 = graphData1;
        this.graphData2 = graphData2;
        this.graphDataNames = graphDataNames;
        this.closeTime = closeTime;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.adjustMaxMin();
        this.expectedSubElements = Integer.valueOf(s);
    }
    
    public void setImages(final String title, final String xCaption, final String yCaption, final String background, final String alignment) {
        this.title = title;
        this.background = background;
        this.xCaption = xCaption;
        this.yCaption = yCaption;
        this.alignment = alignment;
    }
    
    public void setMargins(final String s, final String s2) {
        this.topMargin = Integer.valueOf(s);
        this.leftMargin = Integer.valueOf(s2);
    }
    
    public void setOrigin(final String s) {
        if (s.equals("TRUE") || s.equals("1")) {
            this.origin2Zero = true;
        }
        else {
            this.origin2Zero = false;
        }
    }
    
    public void setchartAdjustment(final String s) {
        this.chartAdjust = Double.valueOf(s);
    }
}
