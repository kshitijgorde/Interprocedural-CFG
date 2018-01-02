// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.gui;

import java.awt.Cursor;
import java.awt.Event;
import java.awt.Container;
import java.awt.Image;
import netcharts.graphics.NFRegion;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Component;
import java.awt.Canvas;

public class NFSash extends Canvas implements NFSashIntf
{
    protected Component left;
    protected int left_x;
    protected int left_y;
    protected int left_width;
    protected int left_height;
    protected Component right;
    protected int right_x;
    protected int right_y;
    protected int right_width;
    protected int right_height;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int mouse_down_x;
    protected int mouse_down_y;
    protected Frame frame;
    protected boolean mouse_down;
    protected int style;
    protected Color background;
    protected NFSashLine line;
    protected boolean enabled;
    protected NFGuiObserver observer;
    private int a;
    
    public NFSash(final Component component, final Component component2) {
        this(2, component, component2, 10, true);
    }
    
    public NFSash(final int n, final Component component, final Component component2) {
        this(n, component, component2, 10, true);
    }
    
    public NFSash(final int style, final Component left, final Component right, final int n, final boolean enabled) {
        this.mouse_down_x = 0;
        this.mouse_down_y = 0;
        this.mouse_down = false;
        this.style = 2;
        this.enabled = true;
        this.observer = null;
        this.left = left;
        this.right = right;
        this.style = style;
        (this.line = new NFSashLine(style)).disable();
        this.enabled = enabled;
        this.resize(n, n);
    }
    
    public void addObserver(final NFGuiObserver observer) {
        this.observer = observer;
    }
    
    public void addNotify() {
        super.addNotify();
        this.frame = this.getFrame();
        this.repaint();
        this.background = this.getBackground();
    }
    
    public void setColor(final Color background) {
        this.setBackground(background);
        this.background = this.getBackground();
    }
    
    public void left(final Component left) {
        this.left = left;
    }
    
    public void right(final Component right) {
        this.right = right;
    }
    
    public void paint(final Graphics graphics) {
        final Point location = this.location();
        this.x = location.x;
        this.y = location.y;
        final Dimension size = this.size();
        this.width = size.width;
        this.height = size.height;
        this.paint(graphics, size);
    }
    
    public void paint(final Graphics graphics, final Dimension dimension) {
        NFRegion.draw(graphics, 0, 0, dimension.width, dimension.height, this.background, 3, 2, Color.black, null, 0);
    }
    
    public void print(final Graphics graphics, final Dimension dimension) {
        this.paint(graphics, dimension);
    }
    
    protected Frame getFrame() {
        Container container;
        for (container = this.getParent(); !(container instanceof Frame); container = container.getParent()) {}
        return (Frame)container;
    }
    
    public Dimension minimumSize() {
        return new Dimension(this.width, this.height);
    }
    
    public boolean mouseDown(final Event event, final int mouse_down_x, final int mouse_down_y) {
        if (!this.enabled || this.mouse_down) {
            return false;
        }
        this.mouse_down_x = mouse_down_x;
        this.mouse_down_y = mouse_down_y;
        if (this.style == 2) {
            this.line.reshape(this.x + mouse_down_x, this.y, 2, this.height);
        }
        else {
            this.line.reshape(this.x, this.y + mouse_down_y, this.width, 2);
        }
        ((NFSashPanelIntf)this.getParent()).addLine(this.line);
        this.line.show();
        this.mouse_down = true;
        final Point location = this.location();
        this.x = location.x;
        this.y = location.y;
        final Dimension size = this.size();
        this.width = size.width;
        this.height = size.height;
        final Point location2 = this.left.location();
        this.left_x = location2.x;
        this.left_y = location2.y;
        final Dimension size2 = this.left.size();
        this.left_width = size2.width;
        this.left_height = size2.height;
        final Point location3 = this.right.location();
        this.right_x = location3.x;
        this.right_y = location3.y;
        final Dimension size3 = this.right.size();
        this.right_width = size3.width;
        this.right_height = size3.height;
        if (this.frame != null) {
            this.frame.setCursor(13);
        }
        return false;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (!this.enabled) {
            return false;
        }
        try {
            if (!this.mouse_down) {
                final Container parent = this.getParent();
                this.a = parent.getCursor().getType();
                parent.setCursor(new Cursor(13));
            }
        }
        catch (Exception ex) {
            if (this.frame != null && !this.mouse_down) {
                this.frame.setCursor(13);
            }
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (!this.enabled) {
            return false;
        }
        try {
            if (!this.mouse_down) {
                this.getParent().setCursor(new Cursor(this.a));
            }
        }
        catch (Exception ex) {
            if (this.frame != null && !this.mouse_down) {
                this.frame.setCursor(0);
            }
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (!this.enabled || !this.mouse_down) {
            return false;
        }
        final Dimension size = this.getParent().size();
        final Point location = this.location();
        location.translate(n, n2);
        if (this.style == 2 && location.x > 0 && location.x < size.width) {
            this.line.reshape(this.x + n, this.y, 2, this.height);
        }
        else if (this.style == 1 && location.y > 0 && location.y < size.height) {
            this.line.reshape(this.x, this.y + n2, this.width, 2);
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, int n, int n2) {
        if (!this.enabled || !this.mouse_down) {
            return false;
        }
        n -= this.mouse_down_x;
        n2 -= this.mouse_down_y;
        this.mouse_down_x = 0;
        this.mouse_down_y = 0;
        this.mouse_down = false;
        final NFSashPanelIntf nfSashPanelIntf = (NFSashPanelIntf)this.getParent();
        this.line.hide();
        nfSashPanelIntf.removeLine(this.line);
        if (this.style == 2) {
            if (this.left_width + n < 20) {
                n = this.left_x + 20 - this.x;
            }
            if (this.right_width - n < 20) {
                n = this.right_x + this.right_width - 20 - this.width - this.x;
            }
            this.left.reshape(this.left_x, this.left_y, this.left_width + n, this.left_height);
            this.reshape(this.x + n, this.y, this.width, this.height);
            this.right.reshape(this.right_x + n, this.right_y, this.right_width - n, this.right_height);
        }
        else {
            if (this.left_height + n2 < 20) {
                n2 = this.left_y + 20 - this.y;
            }
            if (this.right_height - n2 < 20) {
                n2 = this.right_y + this.right_height - 20 - this.height - this.y;
            }
            this.left.reshape(this.left_x, this.left_y, this.left_width, this.left_height + n2);
            this.reshape(this.x, this.y + n2, this.width, this.height);
            this.right.reshape(this.right_x, this.right_y + n2, this.right_width, this.right_height - n2);
        }
        nfSashPanelIntf.layout();
        this.a(this.left);
        if (this.frame != null) {
            this.frame.setCursor(0);
        }
        if (this.observer != null) {
            this.observer.buttonPressed(this, "SASHDRAG");
        }
        return true;
    }
    
    private void a(final Component component) {
        if (component instanceof Container) {
            for (int countComponents = ((Container)component).countComponents(), i = 0; i < countComponents; ++i) {
                this.a(((Container)component).getComponent(i));
            }
        }
        final Graphics graphics = component.getGraphics();
        if (graphics != null) {
            component.paint(graphics);
            graphics.dispose();
        }
    }
    
    public void destroy() {
        this.left = null;
        this.right = null;
        this.frame = null;
        this.line = null;
        this.observer = null;
    }
}
