import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.Color;
import java.awt.Image;
import com.pzzl.utils.Panel2;

// 
// Decompiled by Procyon v0.5.30
// 

public class CardImagePanel extends Panel2
{
    Image \u00c2;
    boolean showBorder;
    boolean hideString;
    boolean mousePressed;
    String \u00c3;
    int \u00c4;
    int \u00c5;
    int \u00c6;
    int \u00c7;
    SetPanel \u00c8;
    Color \u00c9;
    Color \u00ca;
    Color \u00cb;
    Color \u00cc;
    Color \u00cd;
    int \u00ce;
    
    public CardImagePanel(final SetPanel \u00e8, final int \u00ee) {
        this.showBorder = false;
        this.hideString = false;
        this.mousePressed = false;
        this.\u00c9 = Color.white;
        this.\u00ca = this.\u00c9;
        this.\u00cb = this.\u00c9;
        this.\u00cc = this.\u00c9;
        this.\u00cd = Color.black;
        this.\u00c8 = \u00e8;
        this.\u00ce = \u00ee;
    }
    
    public CardImagePanel(final int n, final int n2, final int n3, final int n4) {
        this.showBorder = false;
        this.hideString = false;
        this.mousePressed = false;
        this.\u00c9 = Color.white;
        this.\u00ca = this.\u00c9;
        this.\u00cb = this.\u00c9;
        this.\u00cc = this.\u00c9;
        this.\u00cd = Color.black;
        this.reshape(n, n2, n3, n4);
    }
    
    public CardImagePanel(final Image \u00e2, final int n, final int n2) {
        this.showBorder = false;
        this.hideString = false;
        this.mousePressed = false;
        this.\u00c9 = Color.white;
        this.\u00ca = this.\u00c9;
        this.\u00cb = this.\u00c9;
        this.\u00cc = this.\u00c9;
        this.\u00cd = Color.black;
        this.setSize(n, n2);
        this.\u00c2 = \u00e2;
    }
    
    public CardImagePanel(final Image \u00e2) {
        this.showBorder = false;
        this.hideString = false;
        this.mousePressed = false;
        this.\u00c9 = Color.white;
        this.\u00ca = this.\u00c9;
        this.\u00cb = this.\u00c9;
        this.\u00cc = this.\u00c9;
        this.\u00cd = Color.black;
        this.\u00c2 = \u00e2;
    }
    
    public void setParent(final SetPanel \u00e8) {
        this.\u00c8 = \u00e8;
    }
    
    public boolean getMousePressed() {
        return this.mousePressed;
    }
    
    public void setMouseDefaultBorderColor(final Color \u00ea) {
        this.\u00ca = \u00ea;
    }
    
    public void setMouseEnterBorderColor(final Color \u00eb) {
        this.\u00cb = \u00eb;
    }
    
    public void setMousePressedBorderColor(final Color \u00ec) {
        this.\u00cc = \u00ec;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (!this.mousePressed) {
            if (!this.\u00c8.limitReached()) {
                this.setCursor(new Cursor(12));
                this.\u00c8.\u01ae[this.\u00ce].setBorderColor(this.\u00cb);
            }
            return true;
        }
        this.setCursor(new Cursor(12));
        this.repaint();
        return true;
    }
    
    public void setMousePressed(final boolean mousePressed) {
        this.mousePressed = mousePressed;
        if (mousePressed) {
            this.\u00c8.\u01ae[this.\u00ce].setBorderColor(this.\u00cc);
            return;
        }
        this.\u00c8.\u01ae[this.\u00ce].setBorderColor(this.\u00ca);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.mousePressed) {
            if (!this.\u00c8.limitReached()) {
                this.\u00c8.\u01ae[this.\u00ce].setBorderColor(this.\u00cc);
                this.mousePressed = true;
                if (this.\u00c8 != null) {
                    this.\u00c8.checkSolution();
                }
            }
        }
        else {
            this.\u00c8.resetCardPanel();
            this.\u00c8.\u01ae[this.\u00ce].setBorderColor(this.\u00ca);
            this.mousePressed = false;
        }
        this.repaint();
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.setCursor(new Cursor(0));
        if (!this.mousePressed) {
            this.\u00c8.\u01ae[this.\u00ce].setBorderColor(this.\u00ca);
        }
        this.repaint();
        return true;
    }
    
    public void setString(final String \u00e3) {
        this.\u00c3 = \u00e3;
        this.repaint();
    }
    
    public void setBorderColor(final Color \u00ed) {
        this.\u00cd = \u00ed;
        this.repaint();
    }
    
    public void setImage(final Image \u00e2) {
        this.\u00c2 = \u00e2;
        this.repaint();
    }
    
    public void setImage(final Image \u00e2, final int \u00e6, final int \u00e7) {
        this.\u00c6 = \u00e6;
        this.\u00c7 = \u00e7;
        this.\u00c2 = \u00e2;
        this.repaint();
    }
    
    public void setStringPosition(final int \u00e4, final int \u00e5) {
        this.\u00c4 = \u00e4;
        this.\u00c5 = \u00e5;
    }
    
    public void hideString() {
        this.hideString = true;
    }
    
    public void showBorder(final boolean showBorder) {
        this.showBorder = showBorder;
    }
    
    public void paint(final Graphics graphics) {
        if (this.\u00c2 != null) {
            if (this.\u00c6 == 0 || this.\u00c7 == 0) {
                graphics.drawImage(this.\u00c2, 0, 0, this);
            }
            else {
                graphics.drawImage(this.\u00c2, 0, 0, this.\u00c6, this.\u00c7, this);
            }
        }
        else {
            graphics.clearRect(0, 0, this.size().width, this.size().height);
        }
        if (this.\u00c3 != null && !this.hideString) {
            final int stringWidth = this.getFontMetrics(this.getFont()).stringWidth(this.\u00c3);
            final int size = this.getFont().getSize();
            if (this.\u00c4 == 0 && this.\u00c5 == 0) {
                this.\u00c4 = this.size().width / 2 - stringWidth / 2;
                this.\u00c5 = this.size().height / 2 + size / 2;
            }
            graphics.drawString(this.\u00c3, this.\u00c4, this.\u00c5);
        }
        if (this.showBorder) {
            graphics.setColor(this.\u00cd);
            graphics.drawRect(0, 0, this.size().width - 1, this.size().height - 1);
        }
    }
}
