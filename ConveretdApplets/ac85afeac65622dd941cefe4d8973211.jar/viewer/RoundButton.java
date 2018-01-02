// 
// Decompiled by Procyon v0.5.30
// 

package viewer;

import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Canvas;

public class RoundButton extends Canvas
{
    private String label;
    private String actionCommand;
    private boolean pressed;
    private boolean flag;
    private int width;
    private int height;
    private int min_width;
    private int min_height;
    private Color fillColor;
    private Color topColor;
    private Color botColor;
    private Object frm;
    private int instanceCurrentNumber;
    private static int instanceNumber;
    private String className;
    
    public RoundButton() {
        this("Button");
    }
    
    public RoundButton(final String s) {
        this(s, -1, -1);
    }
    
    public RoundButton(final String s, final Dimension dimension) {
        this(s, dimension.width, dimension.height);
    }
    
    public RoundButton(final String label, final int n, final int n2) {
        this.className = "roundbutton";
        this.label = label;
        this.width = -1;
        this.height = -1;
        this.instanceCurrentNumber = RoundButton.instanceNumber;
        ++RoundButton.instanceNumber;
    }
    
    public void setActionCommand(final String actionCommand) {
        this.actionCommand = actionCommand;
    }
    
    public String getActionCommand() {
        if (this.actionCommand == null) {
            return this.label;
        }
        return this.actionCommand;
    }
    
    public void paint(final Graphics graphics) {
        if (this.fillColor == null) {
            this.fillColor = this.getBackground().darker();
        }
        if (this.frm == null) {
            this.frm = this.getParent();
            while (!(this.frm instanceof Frame)) {
                this.frm = ((Component)this.frm).getParent();
            }
        }
        this.width = this.size().width - 1;
        this.height = this.size().height - 1;
        if (!this.pressed) {
            this.topColor = this.fillColor.brighter();
            this.botColor = this.fillColor.darker();
        }
        else {
            this.topColor = this.fillColor.darker();
            this.botColor = this.fillColor.brighter();
        }
        graphics.setColor(this.fillColor);
        graphics.fillRect(1, 1, this.width - 1, this.height - 1);
        graphics.setColor(this.topColor);
        graphics.drawLine(2, 1, this.width - 2, 1);
        graphics.drawLine(2, 2, this.width - 3, 2);
        graphics.drawLine(1, 2, 1, this.height - 2);
        graphics.drawLine(2, 3, 2, this.height - 3);
        graphics.fillRect(3, 3, 1, 1);
        graphics.setColor(this.botColor);
        graphics.drawLine(3, this.height - 2, this.width - 3, this.height - 2);
        graphics.drawLine(2, this.height - 1, this.width - 2, this.height - 1);
        graphics.drawLine(this.width - 2, 3, this.width - 2, this.height - 1);
        graphics.drawLine(this.width - 1, 2, this.width - 1, this.height - 2);
        graphics.fillRect(this.width - 3, this.height - 3, 1, 1);
        graphics.fillRect(this.width - 3, 3, 1, 1);
        graphics.fillRect(3, this.height - 3, 1, 1);
        graphics.fillRect(this.width - 2, this.height - 2, 1, 1);
        graphics.setColor(Color.black);
        graphics.drawRoundRect(0, 0, this.width, this.height, 7, 7);
        if (this.getFont() != null) {
            final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
            graphics.setColor(this.getForeground());
            if (!this.pressed) {
                graphics.drawString(this.label, this.width / 2 - fontMetrics.stringWidth(this.label) / 2, this.height / 2 + fontMetrics.getHeight() / 2 - fontMetrics.getMaxDescent());
                return;
            }
            graphics.drawString(this.label, this.width / 2 - fontMetrics.stringWidth(this.label) / 2 + 1, this.height / 2 + fontMetrics.getHeight() / 2 - fontMetrics.getMaxDescent() + 1);
        }
    }
    
    public void setColor(final Color fillColor) {
        this.fillColor = fillColor;
        this.repaint();
    }
    
    public Color getColor() {
        return this.fillColor;
    }
    
    public void resize(final Dimension dimension) {
        this.resize(dimension.width, dimension.height);
    }
    
    public void resize(final int min_width, final int min_height) {
        this.flag = true;
        this.min_width = min_width;
        this.min_height = min_height;
    }
    
    public Dimension getPreferredSize() {
        return this.getMinimumSize();
    }
    
    public Dimension getMinimumSize() {
        if (!this.flag) {
            final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
            return new Dimension(fontMetrics.stringWidth(this.label) + fontMetrics.getMaxDescent() * 5, fontMetrics.getHeight() + fontMetrics.getMaxDescent() * 2 + 5);
        }
        return new Dimension(this.min_width, this.min_height);
    }
    
    public String getName() {
        if (this.label != null) {
            return this.className + ", label=" + this.label;
        }
        return this.className + this.instanceCurrentNumber;
    }
    
    public void setName(final String s) {
        this.className = this.className;
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 501: {
                this.pressed = true;
                this.repaint();
                break;
            }
            case 502: {
                this.postEvent(new Event(this, 1001, this.label));
                this.pressed = false;
                this.repaint();
                break;
            }
            case 504: {
                if (this.frm != null) {
                    ((Frame)this.frm).setCursor(12);
                    break;
                }
                break;
            }
            case 505: {
                if (this.frm != null) {
                    ((Frame)this.frm).setCursor(0);
                }
                if (this.pressed) {
                    this.pressed = false;
                    this.repaint();
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public void setLabel(final String label) {
        this.label = label;
        this.invalidate();
        this.repaint();
    }
    
    public String getLabel() {
        return this.label;
    }
}
