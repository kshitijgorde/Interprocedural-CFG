// 
// Decompiled by Procyon v0.5.30
// 

package KJEgui;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.Panel;

public class Slider extends Panel
{
    private String[] legend;
    private int iMin;
    private int iMax;
    private int iOffset;
    private int iPosition;
    private int iLineHeight;
    private boolean bWholeNumber;
    private boolean bDown;
    private static int iDraggerSize;
    private static Color colorLine;
    private static Color colorFill;
    private static Color colorFillClicked;
    private static Color colorText;
    private static Font font;
    private double dValue;
    private Dimension _dSize;
    private int iFontHeight;
    private int iFontDescent;
    private FontMetrics fontMetrics;
    public static int ROUND;
    public static int TRIANGLE;
    public int iType;
    int[] an1;
    int[] an2;
    
    static {
        Slider.iDraggerSize = 6;
        Slider.colorLine = Color.black;
        Slider.colorFill = Color.red;
        Slider.colorFillClicked = Color.blue;
        Slider.colorText = Color.black;
        Slider.font = new Font("Helvetica", 0, 9);
        Slider.ROUND = 1;
        Slider.TRIANGLE = 0;
    }
    
    public Slider(final int n, final int n2, final Font font, final String[] array, final boolean b) throws IllegalArgumentException {
        this(n, n2, font, array, b, 200);
    }
    
    public Slider(final int iMin, final int iMax, final Font font, final String[] legend, final boolean bWholeNumber, final int n) throws IllegalArgumentException {
        this.dValue = 0.0;
        this.iType = Slider.TRIANGLE;
        this.an1 = new int[4];
        this.an2 = new int[4];
        this.legend = legend;
        this.iPosition = Slider.iDraggerSize;
        this.bWholeNumber = bWholeNumber;
        this.bDown = false;
        if (iMax <= iMin) {
            throw new IllegalArgumentException("max must be bigger than min");
        }
        this.iMin = iMin;
        this.iMax = iMax;
        this.setDefaultFont(font, n);
    }
    
    private double computeValue() {
        return this.dValue = this.iMin + (this.iMax - this.iMin) * (this.iPosition - Slider.iDraggerSize) / (this.size().width - 2 * Slider.iDraggerSize);
    }
    
    public void forceWhole(final boolean bWholeNumber) {
        this.bWholeNumber = bWholeNumber;
    }
    
    public Dimension getMinimumSize() {
        return this._dSize;
    }
    
    public int getPosition() {
        return (int)(Slider.iDraggerSize + (this.dValue - this.iMin) / (this.iMax - this.iMin) * (this.size().width - 2 * Slider.iDraggerSize));
    }
    
    public Dimension getPreferredSize() {
        return this._dSize;
    }
    
    public double getValue() {
        return this.dValue;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (n > this.iPosition - Slider.iDraggerSize && n < this.iPosition + Slider.iDraggerSize) {
            this.iOffset = n - this.iPosition;
        }
        else {
            this.iOffset = 0;
        }
        int iDraggerSize = n - this.iOffset;
        if (iDraggerSize < Slider.iDraggerSize) {
            iDraggerSize = Slider.iDraggerSize;
        }
        else if (iDraggerSize > this.size().width - Slider.iDraggerSize) {
            iDraggerSize = this.size().width - Slider.iDraggerSize;
        }
        if (iDraggerSize != this.iPosition) {
            this.iPosition = iDraggerSize;
            this.deliverEvent(new Event(this, 1001, new Double(this.computeValue())));
        }
        this.bDown = true;
        this.repaint(0, this.iLineHeight, this.size().width, this.size().height + 1);
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        int iDraggerSize = n - this.iOffset;
        if (iDraggerSize < Slider.iDraggerSize) {
            iDraggerSize = Slider.iDraggerSize;
        }
        else if (iDraggerSize > this.size().width - Slider.iDraggerSize) {
            iDraggerSize = this.size().width - Slider.iDraggerSize;
        }
        final boolean b = iDraggerSize != this.iPosition;
        if (b) {
            this.iPosition = iDraggerSize;
            this.deliverEvent(new Event(this, 1001, new Double(this.computeValue())));
        }
        if (b || !this.bDown) {
            this.bDown = true;
            this.repaint(0, this.iLineHeight, this.size().width, this.size().height + 1);
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.bDown = false;
        if (this.bWholeNumber) {
            this.setValue(Math.round(this.getValue()));
            this.deliverEvent(new Event(this, 1001, new Double(this.computeValue())));
        }
        this.repaint(0, this.iLineHeight, this.size().width, this.size().height + 1);
        return true;
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.size().width;
        final int height = this.size().height;
        final int n = width - 2 * Slider.iDraggerSize;
        graphics.setFont(Slider.font);
        graphics.setColor(Slider.colorLine);
        if (this.bDown) {
            graphics.drawLine(Slider.iDraggerSize, this.iFontHeight - 1 + Slider.iDraggerSize / 2, width - Slider.iDraggerSize, this.iFontHeight - 1 + Slider.iDraggerSize / 2);
        }
        else {
            for (int i = Slider.iDraggerSize; i < width - Slider.iDraggerSize; i += 2) {
                graphics.drawLine(i, this.iFontHeight - 1 + Slider.iDraggerSize / 2, i, this.iFontHeight - 1 + Slider.iDraggerSize / 2);
            }
        }
        graphics.setColor(Slider.colorText);
        if (this.legend != null) {
            for (int j = 0; j < this.legend.length; ++j) {
                final int stringWidth = this.fontMetrics.stringWidth(this.legend[j]);
                int n2 = j * n / (this.legend.length - 1) - stringWidth / 2;
                if (n2 < 0) {
                    n2 = 0;
                }
                if (n2 + stringWidth > n) {
                    n2 = n - stringWidth;
                }
                graphics.drawString(this.legend[j], n2 + Slider.iDraggerSize, this.iFontHeight - 2);
            }
        }
        final int n3 = this.iFontHeight + Slider.iDraggerSize / 2 - 3;
        final int position = this.getPosition();
        if (!this.bDown) {
            this.an1[0] = position;
            this.an1[1] = position + Slider.iDraggerSize / 2 + 1;
            this.an1[2] = position - Slider.iDraggerSize / 2 - 1;
            this.an1[3] = position;
            this.an2[0] = n3 - Slider.iDraggerSize / 2;
            this.an2[1] = n3 + Slider.iDraggerSize / 2 + 2;
            this.an2[2] = n3 + Slider.iDraggerSize / 2 + 2;
            this.an2[3] = n3 - Slider.iDraggerSize / 2;
        }
        graphics.setColor(Slider.colorFill.darker());
        if (this.iType == Slider.ROUND) {
            graphics.fillOval(this.getPosition() - Slider.iDraggerSize / 2, this.iFontHeight - 1, Slider.iDraggerSize, Slider.iDraggerSize);
            graphics.setColor(Slider.colorFillClicked.brighter());
            graphics.drawOval(this.getPosition() - Slider.iDraggerSize / 2, this.iFontHeight - 1, Slider.iDraggerSize, Slider.iDraggerSize);
        }
        else {
            final Polygon polygon = new Polygon(this.an1, this.an2, 4);
            graphics.fillPolygon(polygon);
            graphics.setColor(Slider.colorFillClicked.brighter());
            graphics.drawPolygon(polygon);
        }
        if (this.bDown) {
            graphics.setColor(Slider.colorFill);
            if (this.iType == Slider.ROUND) {
                graphics.fillOval(this.getPosition() - Slider.iDraggerSize / 2, this.iFontHeight - 1, Slider.iDraggerSize, Slider.iDraggerSize);
                graphics.setColor(Slider.colorFillClicked.brighter());
                graphics.drawOval(this.getPosition() - Slider.iDraggerSize / 2, this.iFontHeight - 1, Slider.iDraggerSize, Slider.iDraggerSize);
            }
            else {
                final Polygon polygon2 = new Polygon(new int[] { position, position + Slider.iDraggerSize / 2 + 1, position - Slider.iDraggerSize / 2 - 1, position }, new int[] { n3 - Slider.iDraggerSize / 2, n3 + Slider.iDraggerSize / 2 + 2, n3 + Slider.iDraggerSize / 2 + 2, n3 - Slider.iDraggerSize / 2 }, 4);
                graphics.fillPolygon(polygon2);
                graphics.setColor(Slider.colorFillClicked);
                graphics.drawPolygon(polygon2);
            }
        }
    }
    
    public void printAll(final Graphics graphics) {
        final int width = graphics.getClipBounds().width;
        final int height = graphics.getClipBounds().height;
        final Image image = this.createImage(width, height);
        final Graphics graphics2 = image.getGraphics();
        graphics2.setColor(Color.white);
        graphics2.fillRect(0, 0, width, height);
        this.paint(graphics2);
        graphics.drawImage(image, 0, 0, this);
    }
    
    public static void setAttributes(final Color colorLine, final Color colorFill, final Color colorFillClicked, final Color colorText) {
        Slider.colorLine = colorLine;
        Slider.colorFill = colorFill;
        Slider.colorFillClicked = colorFillClicked;
        Slider.colorText = colorText;
    }
    
    public void setDefaultFont(final Font font, final int n) {
        this.iLineHeight = this.getFontMetrics(font).getHeight() - 3;
        Slider.font = font;
        this.fontMetrics = this.getFontMetrics(font);
        this.iFontHeight = this.fontMetrics.getHeight();
        this.iFontDescent = this.fontMetrics.getDescent();
        this._dSize = new Dimension(n, this.iFontHeight + Slider.iDraggerSize);
        this.invalidate();
    }
    
    public static void setDraggerSize(final int iDraggerSize) {
        Slider.iDraggerSize = iDraggerSize;
    }
    
    public void setLegend(final String[] array) {
        this.legend = this.legend;
    }
    
    public void setType(final int iType) {
        this.iType = iType;
    }
    
    public void setValue(double dValue) {
        if (dValue < this.iMin) {
            dValue = this.iMin;
        }
        if (dValue > this.iMax) {
            dValue = this.iMax;
        }
        this.dValue = dValue;
    }
    
    public void setValueRepaint(final double value) {
        this.setValue(value);
        this.repaint(0, this.iLineHeight, this.size().width, this.size().height + 1);
    }
}
