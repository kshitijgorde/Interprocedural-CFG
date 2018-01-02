// 
// Decompiled by Procyon v0.5.30
// 

package KJEgui;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Font;
import java.awt.Color;
import java.awt.Panel;

public class YesNo extends Panel
{
    private String[] legend;
    private int iPosition;
    private int iOffset;
    private int iLineHeight;
    private boolean bDown;
    private static int iDraggerSize;
    private static Color colorLine;
    private static Color colorFill;
    private static Color colorFillClicked;
    private static Color colorText;
    private static Font font;
    
    static {
        YesNo.iDraggerSize = 6;
        YesNo.colorLine = Color.black;
        YesNo.colorFill = Color.red;
        YesNo.colorFillClicked = Color.blue;
        YesNo.colorText = Color.black;
        YesNo.font = new Font("Helvetica", 0, 9);
    }
    
    public YesNo() {
        (this.legend = new String[2])[0] = "Low";
        this.legend[1] = "High";
        this.iPosition = YesNo.iDraggerSize;
        this.bDown = false;
        this.setDefaultFont(YesNo.font);
    }
    
    public YesNo(final Font defaultFont, final String[] legend) throws IllegalArgumentException {
        this.legend = legend;
        this.iPosition = YesNo.iDraggerSize;
        this.bDown = false;
        this.setDefaultFont(defaultFont);
    }
    
    public double getValue() {
        return this.iPosition;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (n < this.size().width / 2) {
            this.iPosition = 0;
        }
        else {
            this.iPosition = 1;
        }
        this.deliverEvent(new Event(this, 1001, new Double(this.getValue())));
        this.repaint(0, 0, this.size().width, this.size().height);
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        return super.mouseDrag(event, n, n2);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        return super.mouseUp(event, n, n2);
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.size().width;
        final int height = this.size().height;
        final int n = width - 2 * YesNo.iDraggerSize;
        final FontMetrics fontMetrics = this.getFontMetrics(YesNo.font);
        graphics.setFont(YesNo.font);
        final int height2 = fontMetrics.getHeight();
        fontMetrics.getDescent();
        graphics.setColor(YesNo.colorFill);
        if (this.getValue() > 0.0) {
            graphics.drawRect(4, 4, width / 2 - 8, height - 9);
            graphics.fillRect(width / 2 + 4, 4, width / 2 - 8, height - 9);
            graphics.setColor(YesNo.colorText);
            graphics.drawRect(width / 2 + 4, 4, width / 2 - 8, height - 9);
        }
        else {
            graphics.fillRect(4, 4, width / 2 - 8, height - 8);
            graphics.drawRect(width / 2 + 4, 4, width / 2 - 8, height - 8);
            graphics.setColor(YesNo.colorText);
            graphics.drawRect(4, 4, width / 2 - 8, height - 8);
        }
        graphics.setColor(YesNo.colorText);
        graphics.drawString(this.legend[0], width / 4 - fontMetrics.stringWidth(this.legend[0]) / 2, height - height2 / 2);
        graphics.drawString(this.legend[1], width / 4 * 3 - fontMetrics.stringWidth(this.legend[1]) / 2, height - height2 / 2);
    }
    
    public static void setAttributes(final Color colorLine, final Color colorFill, final Color colorFillClicked, final Color colorText) {
        YesNo.colorLine = colorLine;
        YesNo.colorFill = colorFill;
        YesNo.colorFillClicked = colorFillClicked;
        YesNo.colorText = colorText;
    }
    
    public void setDefaultFont(final Font font) {
        this.iLineHeight = this.getFontMetrics(font).getHeight();
        YesNo.font = font;
    }
    
    public void setLegend(final String[] array) {
        this.legend = this.legend;
    }
    
    public void setValue(final double n) {
        if (n > 0.0) {
            this.iPosition = 1;
        }
        else {
            this.iPosition = 0;
        }
    }
    
    public void setValueRepaint(final double value) {
        this.setValue(value);
        this.repaint(0, this.iLineHeight + 1, this.size().width, this.size().height);
    }
}
