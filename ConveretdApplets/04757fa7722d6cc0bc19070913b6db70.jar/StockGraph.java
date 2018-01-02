import java.text.DecimalFormat;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.applet.Applet;
import java.util.Vector;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class StockGraph
{
    public Graphics g;
    public Graphics bgGrfx;
    public Image scratchImage;
    public Image bgImage;
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
    private Color foreColor;
    private Color backColor;
    private Color lineColor;
    private Color subColor;
    private Color chartColor;
    private int margin3d;
    private int depth3d;
    private boolean origin2Zero;
    private String[] graphDataNames;
    private String colorStyle;
    private Vector graphData1;
    private double maxValue;
    private double minValue;
    private double interval;
    private double chartAdjust;
    private int maxSubElement;
    private int expectedSubElements;
    private Color lineGraph1Color;
    String alignment;
    int x1;
    int y1;
    int x2;
    int y2;
    int pX1;
    int pY1;
    int pX2;
    int pY2;
    private Applet appletComponent;
    private int width;
    private int height;
    private CoordinateSystem coordinateSystem;
    private LineGraph lineGraph;
    private int numericWid;
    private Dimension corner1;
    private Dimension corner2;
    private Dimension titleXY;
    private Dimension xAxisXY;
    private Dimension yAxisXY;
    
    StockGraph(final Applet appletComponent, final int width, final int height) {
        this.maxValue = 1000.0;
        this.minValue = 0.0;
        this.coordinateSystem = new CoordinateSystem();
        this.corner1 = new Dimension();
        this.corner2 = new Dimension();
        this.appletComponent = appletComponent;
        this.width = width;
        this.height = height;
        if (this.g == null) {
            this.scratchImage = appletComponent.createImage(width, height);
            this.g = this.scratchImage.getGraphics();
            this.bgImage = appletComponent.createImage(width, height);
            this.bgGrfx = this.bgImage.getGraphics();
        }
    }
    
    public void setOrigin(final String origin2Zero) {
        if (origin2Zero.equals("TRUE") || origin2Zero.equals("1")) {
            this.origin2Zero = true;
        }
        else {
            this.origin2Zero = false;
        }
    }
    
    public void setFonts(final String theFont, final String fontSize1) {
        final int fontSize2 = Integer.valueOf(fontSize1);
        this.font1 = new Font(theFont, 0, fontSize2);
        this.g.setFont(this.font1);
    }
    
    public void setImages(final String title, final String xCaption, final String yCaption, final String background, final String alignment) {
        this.background = background;
        this.title = title;
        this.xCaption = xCaption;
        this.yCaption = yCaption;
        this.alignment = alignment;
    }
    
    public void setMargins(final String topMargin, final String leftMargin) {
        this.topMargin = Integer.valueOf(topMargin);
        this.leftMargin = Integer.valueOf(leftMargin);
    }
    
    public void setchartAdjustment(final String chartAdjust) {
        this.chartAdjust = Double.valueOf(chartAdjust);
    }
    
    public void setDivisions(final String divisions, final String subDivision1, final String subDivision2) {
        this.divisions = Integer.valueOf(divisions);
        this.subDivision1 = Integer.valueOf(subDivision1);
        this.subDivision2 = Integer.valueOf(subDivision2);
    }
    
    public void set3dValues(final String margin3d, final String depth3d) {
        this.margin3d = Integer.valueOf(margin3d);
        this.depth3d = Integer.valueOf(depth3d);
    }
    
    public void setColors(final String foreColor, final String backColor, final String lineColor, final String subColor, final String chartColor, final String lineGraph1Color) {
        this.foreColor = Convert.string2Color(foreColor);
        this.backColor = Convert.string2Color(backColor);
        this.lineColor = Convert.string2Color(lineColor);
        this.subColor = Convert.string2Color(subColor);
        this.chartColor = Convert.string2Color(chartColor);
        this.lineGraph1Color = Convert.string2Color(lineGraph1Color);
    }
    
    public void setGraphVector(final Vector graphData1, final String[] graphDataNames, final double maxValue, final double minValue, final String expectedSubElements) {
        this.graphData1 = graphData1;
        this.graphDataNames = graphDataNames;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.adjustMaxMin();
        this.expectedSubElements = Integer.valueOf(expectedSubElements);
    }
    
    private void adjustMaxMin() {
        this.interval = (this.maxValue - this.minValue) / this.divisions;
        this.maxValue += this.interval * this.chartAdjust;
        this.minValue -= this.interval * this.chartAdjust;
    }
    
    public void drawBackground() {
        this.lineGraph = new LineGraph(this.coordinateSystem, this.g, this.expectedSubElements);
        this.g.setColor(this.backColor);
        this.g.fillRect(0, 0, this.width, this.height);
        this.g.setColor(this.foreColor);
        this.x1 = this.leftMargin + 1;
        this.y1 = this.topMargin + 1;
        this.x2 = this.width - this.x1;
        this.y2 = this.height - this.y1;
        Image img1 = null;
        final Image img2 = this.appletComponent.createImage(1, 1);
        final String[] params = { this.title, this.xCaption, this.yCaption, this.background };
        for (int i = 0; i < 4; ++i) {
            final String[] imageParams = Convert.string2Array(params[i], ";");
            int xOffset = 0;
            int yOffset = 0;
            if (imageParams.length >= 2) {
                xOffset = Integer.valueOf(imageParams[1]);
            }
            if (imageParams.length >= 3) {
                yOffset = Integer.valueOf(imageParams[2]);
            }
            if (params[i].trim().equals("")) {
                img1 = img2;
            }
            else {
                img1 = this.loadImage(imageParams[0]);
            }
            int x = this.x1 + xOffset;
            int y;
            if (i == 0) {
                x = this.computeAlignment(x, img1.getWidth(this.appletComponent), this.x2 - this.x1, this.alignment);
                y = this.y1 + yOffset;
                this.pY1 = y + img1.getHeight(this.appletComponent);
            }
            else if (i == 1) {
                x = this.computeAlignment(x, img1.getWidth(this.appletComponent), this.x2 - this.x1, this.alignment);
                y = this.y2 + yOffset - img1.getHeight(this.appletComponent);
                this.pY2 = y - 1;
            }
            else if (i == 2) {
                y = this.computeAlignment(this.y1 + yOffset, img1.getHeight(this.appletComponent), this.y2 - this.y1, this.alignment);
                this.pX1 = x + img1.getWidth(this.appletComponent);
                this.pX2 = this.x2;
            }
            else {
                this.computeViewport();
                this.g.setColor(this.chartColor);
                this.g.fillRect(this.pX1, this.pY1, this.pX2 - this.pX1, this.pY2 - this.pY1);
                this.g.setClip(this.pX1, this.pY1, this.pX2 - this.pX1, this.pY2 - this.pY1);
                x = this.computeAlignment(this.pX1, img1.getWidth(this.appletComponent), this.pX2 - this.pX1, this.alignment) + xOffset;
                y = this.computeAlignment(this.pY1, img1.getHeight(this.appletComponent), this.pY2 - this.pY1, this.alignment) + yOffset;
            }
            this.g.drawImage(img1, x, y, null);
        }
        this.g.setClip(0, 0, this.width, this.height);
        this.bgGrfx.drawImage(this.scratchImage, 0, 0, null);
    }
    
    private Image loadImage(final String imgName) {
        try {
            final Image img = this.appletComponent.getImage(this.appletComponent.getDocumentBase(), imgName);
            int flags = img.getHeight(this.appletComponent);
            do {
                flags = this.appletComponent.checkImage(img, this.appletComponent);
            } while ((flags & 0x20) == 0x0);
            return img;
        }
        catch (Exception e) {
            this.g.drawString(e.toString(), 10, 20);
            return this.appletComponent.createImage(1, 1);
        }
    }
    
    private int computeAlignment(final int orig, final int srcWidth, final int targetWidth, final String alignment) {
        if (alignment.equals("LEFT")) {
            return orig;
        }
        if (alignment.equals("RIGHT")) {
            return orig + (targetWidth - srcWidth);
        }
        return orig + (targetWidth - srcWidth) / 2;
    }
    
    private void computeViewport() {
        final String strnBuff = "000000000000000000000000000000";
        if (this.maxValue < 0.01) {
            this.numericWid = ((int)this.maxValue + ".0000").length();
        }
        else {
            this.numericWid = ((int)this.maxValue + ".00").length();
        }
        this.numericWid = this.appletComponent.getFontMetrics(this.font1).stringWidth(strnBuff.substring(0, this.numericWid));
        this.pX1 += this.numericWid;
        this.pY2 -= this.appletComponent.getFontMetrics(this.font1).getHeight();
        this.coordinateSystem.setOriginalRectangularPlane(this.pX1, this.pY1, this.pX2, this.pY2);
        this.coordinateSystem.setNewRectangularPlane(0.0, this.minValue, 1000.0, this.maxValue);
    }
    
    public void drawCartesianPlane() {
        this.g.drawImage(this.bgImage, 0, 0, null);
        this.drawTickMarks();
        final int x1 = this.coordinateSystem.xO1;
        final int y1 = this.coordinateSystem.yO1;
        final int x2 = this.coordinateSystem.xL1;
        final int y2 = this.coordinateSystem.yL1;
        final int subDivision1 = this.subDivision1 + 1;
        final int subDivision2 = this.subDivision2 + 1;
        final int verticalDiv = this.divisions;
        final int horizontalDiv = this.graphData1.size();
        final int width = Math.abs(x2 - x1);
        final int height = Math.abs(y1 - y2);
        this.g.setColor(this.subColor);
        double yLoc = y2;
        double gap = height / (verticalDiv * subDivision1);
        for (int i = 1; i < verticalDiv * subDivision1; ++i) {
            yLoc -= gap;
            this.g.drawLine(x1, (int)yLoc, x1 + width, (int)yLoc);
        }
        double xLoc = x1;
        gap = width / (horizontalDiv * subDivision2);
        for (int i = 1; i < horizontalDiv * subDivision2; ++i) {
            xLoc += gap;
            this.g.drawLine((int)xLoc, y2, (int)xLoc, y2 - height);
        }
        this.g.setColor(this.lineColor);
        yLoc = y2;
        gap = height / verticalDiv;
        for (int i = 1; i < verticalDiv; ++i) {
            yLoc -= gap;
            this.g.drawLine(x1, (int)yLoc, x1 + width, (int)yLoc);
        }
        xLoc = x1;
        gap = width / horizontalDiv;
        for (int i = 1; i < horizontalDiv; ++i) {
            xLoc += gap;
            this.g.drawLine((int)xLoc, y2, (int)xLoc, y2 - height);
        }
        this.g.setColor(this.lineColor);
        this.g.drawRect(x1 - 1, y2 - height - 1, width + 2, height + 2);
        this.g.drawRect(x1 - 2, y2 - height - 2, width + 4, height + 4);
        this.g.setClip(x1 - 1, y2 - height - 1, width + 2, height + 2);
    }
    
    private void drawTickMarks() {
        this.corner1.setSize(this.coordinateSystem.xO1, this.coordinateSystem.yO1);
        this.corner2.setSize(this.coordinateSystem.xL1, this.coordinateSystem.yL1);
        this.g.setColor(this.foreColor);
        final DecimalFormat form = new DecimalFormat("0.00");
        this.interval = (this.maxValue - this.minValue) / this.divisions;
        final double YGap = this.interval;
        final double YLocGap = (this.corner2.height - this.corner1.height) / this.divisions;
        double yLoc = this.corner2.height + this.appletComponent.getFontMetrics(this.font1).getHeight() / 3;
        double YTicks = this.minValue;
        final DecimalFormat form2 = new DecimalFormat("0.0000");
        if (!this.graphDataNames[0].equals("")) {
            for (int i = 0; i <= this.divisions; ++i) {
                String YTicksStr;
                if (YTicks < 0.25) {
                    YTicksStr = form2.format(YTicks);
                }
                else {
                    YTicksStr = form.format(YTicks);
                }
                final int xLoc = this.pX1 - this.appletComponent.getFontMetrics(this.font1).stringWidth(YTicksStr) - 5;
                this.g.drawString(YTicksStr, xLoc, (int)yLoc);
                YTicks += YGap;
                yLoc -= YLocGap;
            }
        }
        final int xLocGap = (this.corner2.width - this.corner1.width) / this.graphDataNames.length;
        int xLoc = this.corner1.width;
        for (int i = 0; i < this.graphDataNames.length; ++i) {
            this.g.drawString(this.graphDataNames[i], xLoc, this.pY2 + this.appletComponent.getFontMetrics(this.font1).getHeight());
            xLoc += xLocGap;
        }
        this.g.setColor(Color.black);
        this.coordinateSystem.drawLine(this.g, 0.0, 0.0, 1000.0, 0.0);
    }
    
    public void plotData() {
        this.lineGraph.drawGraph(this.graphData1, this.lineGraph1Color);
        this.g.setClip(0, 0, this.width, this.height);
    }
}
