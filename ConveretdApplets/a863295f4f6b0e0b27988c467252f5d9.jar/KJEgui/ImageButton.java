// 
// Decompiled by Procyon v0.5.30
// 

package KJEgui;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Canvas;

public class ImageButton extends Canvas
{
    private boolean bPressed;
    private boolean pCheckButton;
    private Dimension prefSize;
    private Image imgPressed;
    private Image imgUnpressed;
    private Color upColor;
    private Color downColor;
    private String sLabel;
    private Font fLabel;
    
    public ImageButton(final Image imgUnpressed, final Image imgPressed, final Color upColor, final Color downColor, final String sLabel, final Font fLabel, final boolean b) {
        this.bPressed = false;
        this.pCheckButton = false;
        this.prefSize = new Dimension(10, 10);
        this.imgPressed = null;
        this.imgUnpressed = null;
        this.upColor = Color.black;
        this.downColor = Color.white;
        this.sLabel = null;
        this.fLabel = null;
        this.imgPressed = imgPressed;
        this.imgUnpressed = imgUnpressed;
        this.upColor = upColor;
        this.downColor = downColor;
        this.sLabel = sLabel;
        this.fLabel = fLabel;
    }
    
    public ImageButton(final Image imgUnpressed, final Image imgPressed, final boolean b) {
        this.bPressed = false;
        this.pCheckButton = false;
        this.prefSize = new Dimension(10, 10);
        this.imgPressed = null;
        this.imgUnpressed = null;
        this.upColor = Color.black;
        this.downColor = Color.white;
        this.sLabel = null;
        this.fLabel = null;
        this.imgPressed = imgPressed;
        this.imgUnpressed = imgUnpressed;
    }
    
    public boolean getChecked() {
        return this.bPressed;
    }
    
    public Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public Dimension getPreferredSize() {
        if (this.imgUnpressed != null) {
            this.prefSize.width = this.imgUnpressed.getWidth(this);
            this.prefSize.height = this.imgUnpressed.getHeight(this);
        }
        else {
            this.prefSize.width = 20;
            this.prefSize.height = 20;
        }
        return this.prefSize;
    }
    
    public Dimension minimumSize() {
        return this.getMinimumSize();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.bPressed = true;
        this.repaint(0, 0, this.size().width, this.size().height + 1);
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (!this.pCheckButton) {
            this.bPressed = false;
        }
        this.deliverEvent(new Event(this, 1001, this));
        this.repaint(0, 0, this.size().width, this.size().height + 1);
        return true;
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        final Dimension preferredSize = this.getPreferredSize();
        final int n = (size.width - preferredSize.width) / 2;
        final int n2 = (size.height - preferredSize.height) / 2;
        if (this.bPressed) {
            if (this.imgPressed != null) {
                graphics.drawImage(this.imgPressed, n, n2, preferredSize.width, preferredSize.height, this);
            }
            if (this.sLabel != null) {
                graphics.setColor(this.upColor);
                graphics.setFont(this.fLabel);
                final FontMetrics fontMetrics = graphics.getFontMetrics();
                graphics.drawString(this.sLabel, (size.width - fontMetrics.stringWidth(this.sLabel)) / 2, (size.height - fontMetrics.getHeight()) / 2 + fontMetrics.getHeight() - 2);
            }
        }
        else {
            if (this.imgUnpressed != null) {
                graphics.drawImage(this.imgUnpressed, n, n2, preferredSize.width, preferredSize.height, this);
            }
            if (this.sLabel != null) {
                graphics.setColor(this.downColor);
                graphics.setFont(this.fLabel);
                final FontMetrics fontMetrics2 = graphics.getFontMetrics();
                graphics.drawString(this.sLabel, (size.width - fontMetrics2.stringWidth(this.sLabel)) / 2, (size.height - fontMetrics2.getHeight()) / 2 + fontMetrics2.getHeight());
            }
        }
    }
    
    public Dimension preferredSize() {
        return this.getPreferredSize();
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        super.reshape(n, n2, n3, n4);
    }
    
    public void resize(final int n, final int n2) {
        this.setBounds(this.getLocation().x, this.getLocation().y, n, n2);
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        this.reshape(n, n2, n3, n4);
    }
    
    public void setChecked(final boolean bPressed) {
        if (this.bPressed == bPressed) {
            return;
        }
        this.bPressed = bPressed;
        this.repaint();
    }
    
    public void setSize(final int n, final int n2) {
        this.resize(n, n2);
    }
}
