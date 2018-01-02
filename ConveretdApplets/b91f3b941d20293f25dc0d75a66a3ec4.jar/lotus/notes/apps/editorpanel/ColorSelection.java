// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editorpanel;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Container;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Panel;

public class ColorSelection extends Panel
{
    private MultiImgButton selector;
    private ColorCanvas clrCanvas;
    private ColorList clrList;
    private Color cColor;
    private Insets insets;
    
    public Insets insets() {
        return this.insets;
    }
    
    ColorSelection(final MultiImgButton selector, final boolean b) {
        this.insets = new Insets(0, 0, 0, 0);
        this.selector = selector;
        if (b) {
            this.insets = new Insets(3, 3, 1, 1);
            this.setLayout(new BorderLayout());
            this.add("East", this.selector);
            this.add("West", this.clrCanvas = new ColorCanvas(Color.black));
            this.clrCanvas.resize(55, selector.size().height);
        }
        else {
            this.clrCanvas = null;
            this.add(this.selector);
            this.resize(selector.size().width, selector.size().height);
        }
    }
    
    public void setColor(final Color color) {
        if (this.clrCanvas != null) {
            this.clrCanvas.setColor(color);
        }
        this.cColor = color;
    }
    
    public Color getColor() {
        Color color = this.cColor;
        if (this.clrCanvas != null) {
            color = this.clrCanvas.getColor();
        }
        return color;
    }
    
    public void setToolTipText(final String toolTipText) {
        this.selector.setToolTipText(toolTipText);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1001) {
            if (event.target == this.selector) {
                Container container;
                for (container = this.getParent(); container != null && !(container instanceof ToolHost); container = container.getParent()) {}
                if (container != null && container instanceof ToolHost) {
                    this.clrList = (ColorList)((ToolHost)container).getTool("ColorMap");
                }
                if (this.clrList != null) {
                    final Rectangle bounds = this.bounds();
                    this.clrList.setListener(this);
                    this.clrList.showList(bounds.x, bounds.y + bounds.height);
                }
            }
            else if (event.target == this.clrList) {
                this.setColor(this.clrList.getColor());
                this.getParent().postEvent(new Event(this, 1001, null));
            }
            return true;
        }
        if ((event.id == 502 || event.id == 505) && event.target == this.clrList) {
            this.getParent().postEvent(new Event(this, 502, null));
            return true;
        }
        return super.handleEvent(event);
    }
    
    public void paint(final Graphics graphics) {
        if (this.clrCanvas != null) {
            graphics.setColor(Color.lightGray);
            graphics.drawLine(0, 0, 0, this.size().height - 1);
            graphics.drawLine(0, 0, this.size().width - 1, 0);
            graphics.drawLine(0, this.size().height - 1, this.size().width - 1, this.size().height - 1);
            graphics.drawLine(this.size().width - 1, 0, this.size().width - 1, this.size().height - 1);
            graphics.drawLine(2, this.size().height - 3, this.size().width - 3, this.size().height - 3);
            graphics.drawLine(this.size().width - 3, 2, this.size().width - 3, this.size().height - 3);
            graphics.setColor(Color.darkGray);
            graphics.drawLine(1, 1, 1, this.size().height - 3);
            graphics.drawLine(1, 1, this.size().width - 3, 1);
            graphics.setColor(Color.black);
            graphics.drawLine(2, 2, this.size().width - 4, 2);
            graphics.drawLine(2, 2, 2, this.size().height - 4);
            graphics.dispose();
        }
    }
}
