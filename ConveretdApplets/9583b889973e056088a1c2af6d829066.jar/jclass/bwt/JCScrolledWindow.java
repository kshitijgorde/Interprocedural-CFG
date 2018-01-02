// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Container;
import java.awt.LayoutManager;
import java.applet.Applet;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.event.KeyListener;

public class JCScrolledWindow extends JCContainer implements KeyListener, JCAdjustmentListener
{
    public static final int DISPLAY_AS_NEEDED = 0;
    public static final int DISPLAY_ALWAYS = 1;
    public static final int DISPLAY_NONE = 2;
    public static final int DISPLAY_VERTICAL_ONLY = 3;
    public static final int DISPLAY_HORIZONTAL_ONLY = 4;
    int sb_display;
    int sb_offset;
    private JCScrollbar vert_sb;
    private JCScrollbar horiz_sb;
    int keystroke;
    private static final String base = "scrolledwindow";
    private static int nameCounter;
    protected Component viewport;
    Rectangle rect;
    
    public JCScrolledWindow() {
        this(null, null);
    }
    
    public JCScrolledWindow(final Applet applet, final String s) {
        super(applet, s);
        this.sb_display = 0;
        this.vert_sb = new JCScrollbar(1);
        this.horiz_sb = new JCScrollbar(0);
        this.rect = new Rectangle();
        if (s == null) {
            this.setName("scrolledwindow" + JCScrolledWindow.nameCounter++);
        }
        this.setLayout(null);
        this.viewport = new Viewport();
        if (this.getClass().getName().equals("jclass.bwt.JCScrolledWindow")) {
            this.getParameters(applet);
        }
        this.addInternal(this.vert_sb);
        this.vert_sb.addAdjustmentListener(this);
        this.addInternal(this.horiz_sb);
        this.horiz_sb.addAdjustmentListener(this);
    }
    
    protected void getParameters() {
        super.getParameters();
        ScrolledWindowConverter.getParams(this);
    }
    
    public int getScrollbarDisplay() {
        return this.sb_display;
    }
    
    public void setScrollbarDisplay(final int sb_display) {
        ScrolledWindowConverter.checkDisplay(sb_display);
        this.sb_display = sb_display;
        this.layout();
    }
    
    public int getScrollbarOffset() {
        return this.sb_offset;
    }
    
    public void setScrollbarOffset(final int sb_offset) {
        this.sb_offset = sb_offset;
        this.layout();
    }
    
    public Component getViewport() {
        return this.viewport;
    }
    
    int sb_size() {
        if (this.sb_display == 1) {
            return 16 + this.sb_offset;
        }
        return 0;
    }
    
    protected int preferredWidth() {
        final int n = (this.viewport != null) ? this.viewport.preferredSize().width : 0;
        if (n > 0) {
            return n + this.sb_size();
        }
        return 100;
    }
    
    protected int preferredHeight() {
        final int n = (this.viewport != null) ? this.viewport.preferredSize().height : 0;
        if (n > 0) {
            return n + this.sb_size();
        }
        return 100;
    }
    
    protected int getViewportWidth() {
        return this.viewport.preferredSize().width;
    }
    
    protected int getViewportHeight() {
        return this.viewport.preferredSize().height;
    }
    
    public Component add(final Component component) {
        if (this.viewport instanceof Viewport) {
            ((Viewport)this.viewport).add(component);
        }
        return component;
    }
    
    public Component add(final String s, final Component component) {
        if (this.viewport instanceof Viewport) {
            ((Viewport)this.viewport).add(s, component);
        }
        return component;
    }
    
    public Component add(final Component component, final int n) {
        if (this.viewport instanceof Viewport) {
            ((Viewport)this.viewport).add(component, n);
        }
        return component;
    }
    
    public void add(final Component component, final Object o) {
        if (this.viewport instanceof Viewport) {
            ((Viewport)this.viewport).add(component, o);
        }
    }
    
    public void add(final Component component, final Object o, final int n) {
        if (this.viewport instanceof Viewport) {
            ((Viewport)this.viewport).add(component, o, n);
        }
    }
    
    protected void addImpl(final Component component, final Object o, final int n) {
        if (this.viewport instanceof Viewport) {
            ((Viewport)this.viewport).add(component);
            if (!(component instanceof Container)) {
                component.addKeyListener(this);
            }
        }
    }
    
    protected void addInternal(final Component component) {
        if (component.getParent() != this) {
            super.addImpl(component, null, -1);
        }
    }
    
    public void addNotify() {
        this.addInternal(this.viewport);
        super.addNotify();
    }
    
    public JCScrollbar getVertScrollbar() {
        return this.vert_sb;
    }
    
    public JCScrollbar getHorizScrollbar() {
        return this.horiz_sb;
    }
    
    protected void setHorizScrollbarValues() {
    }
    
    protected void setHorizScrollbarValues(final int n, int min, final int n2, final int n3) {
        min = Math.min(n3 - n2, min);
        this.horiz_sb.setValues(n, min, n2, n3);
        this.horiz_sb.setBlockIncrement(min);
        this.horiz_sb.setUnitIncrement(10);
        this.setHorizScrollbarValues();
    }
    
    protected void setVertScrollbarValues(final int n, int min, final int n2, final int n3) {
        min = Math.min(n3 - n2, min);
        this.vert_sb.setValues(n, min, n2, n3);
        this.vert_sb.setBlockIncrement(min);
        this.vert_sb.setUnitIncrement(10);
        this.setVertScrollbarValues();
    }
    
    protected void setVertScrollbarValues() {
    }
    
    protected int reshapeHeader(final int n, final int n2, final int n3) {
        return 0;
    }
    
    protected int headerHeight() {
        return 0;
    }
    
    protected void reshapeViewport(final int n, final int n2, final int n3, final int n4) {
        this.viewport.reshape(n, n2, n3, n4);
    }
    
    public void layout() {
        if (this.getPeer() == null) {
            return;
        }
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        final int headerHeight = this.headerHeight();
        final int viewportWidth = this.getViewportWidth();
        final int viewportHeight = this.getViewportHeight();
        boolean b2;
        boolean b;
        final int left;
        int top;
        synchronized (this) {
            final int n5 = this.size().width - this.insets().left - this.insets().right;
            final int n6 = this.size().height - this.insets().top - this.insets().bottom;
            n3 = n6 - headerHeight;
            n4 = n5;
            if (this.sb_display == 2) {
                b = (b2 = false);
            }
            else if (this.sb_display == 1) {
                b = (b2 = true);
            }
            else if (n3 >= viewportHeight && n4 >= viewportWidth && this.sb_display == 0) {
                n4 = n5;
                n3 = n6;
                b = (b2 = false);
            }
            else {
                b2 = (this.sb_display != 4);
                b = (this.sb_display != 3);
                boolean b3 = true;
                boolean b4 = true;
                if (viewportHeight <= n3 && this.sb_display != 3) {
                    b3 = false;
                }
                if (viewportWidth <= n4 && this.sb_display != 4) {
                    b4 = false;
                }
                if (b4) {
                    n3 -= 16 + this.sb_offset;
                }
                if (b3) {
                    n4 -= 16 + this.sb_offset;
                }
                if (viewportHeight <= n3 && this.sb_display != 3) {
                    n4 = n5;
                    b2 = false;
                }
                else {
                    n4 = n5 - (16 + this.sb_offset);
                }
                if (viewportWidth <= n4 && this.sb_display != 4) {
                    n3 = n6;
                    b = false;
                }
                else {
                    n3 = n6 - (16 + this.sb_offset);
                }
            }
            n3 = (b ? (n6 - (16 + this.sb_offset)) : n6);
            n4 = (b2 ? (n5 - (16 + this.sb_offset)) : n5);
            if (b) {
                if (!b2) {
                    n = n5;
                }
                else {
                    n = n4;
                }
            }
            if (b2) {
                if (!b) {
                    n2 = n6;
                }
                else {
                    n2 = n3;
                }
            }
            left = this.insets().left;
            top = this.insets().top;
            final int left2 = this.insets().left;
            final int n7 = b ? (this.size().height - this.insets().bottom - 16) : this.size().height;
            final int n8 = b2 ? (this.size().width - this.insets().right - 16) : n5;
            final int top2 = this.insets().top;
        }
        final int reshapeHeader = this.reshapeHeader(left, top, n4);
        int n9 = 0;
        int n10 = 0;
        int n11;
        synchronized (this) {
            top += reshapeHeader;
            n3 -= reshapeHeader;
            n2 -= reshapeHeader;
            final int value;
            n9 = (value = this.vert_sb.getValue());
            n9 = Math.max(0, Math.min(n9, viewportHeight - n3));
            if (n9 != value) {
                this.scrollVertical(null, n9);
            }
            n10 = viewportHeight;
            n11 = n3;
            if (this.viewport instanceof JCComponent) {
                final JCComponent jcComponent = (JCComponent)this.viewport;
                final int n12 = 2 * jcComponent.getBorderThickness() + jcComponent.getInsets().top + jcComponent.getInsets().bottom;
                n11 -= n12;
                n10 -= n12;
            }
        }
        this.setVertScrollbarValues(n9, n11, 0, n10);
        synchronized (this) {
            final int value2;
            n9 = (value2 = this.horiz_sb.getValue());
            n9 = Math.max(0, Math.min(n9, viewportWidth - n4));
            if (n9 != value2) {
                this.scrollHorizontal(null, n9);
            }
            n10 = viewportWidth;
            n11 = n4;
            if (this.viewport instanceof JCComponent) {
                final JCComponent jcComponent2 = (JCComponent)this.viewport;
                final int n13 = 2 * jcComponent2.getBorderThickness() + jcComponent2.getInsets().left + jcComponent2.getInsets().right;
                n11 -= n13;
                n10 -= n13;
            }
        }
        this.setHorizScrollbarValues(n9, n11, 0, n10);
        this.reshapeViewport(left, top, n4, n3);
        final Point location = this.viewport.location();
        final Dimension size = this.viewport.size();
        if (b2) {
            this.vert_sb.reshape(location.x + size.width + this.sb_offset, location.y, 16, n2);
        }
        this.vert_sb.show(b2);
        if (b) {
            this.horiz_sb.reshape(location.x, location.y + size.height + this.sb_offset, n, 16);
        }
        this.horiz_sb.show(b);
    }
    
    protected void scrollHorizontal(final JCScrollableInterface jcScrollableInterface, final JCAdjustmentEvent jcAdjustmentEvent, final int horizOrigin) {
        final int n = horizOrigin - jcScrollableInterface.getHorizOrigin();
        final int abs = Math.abs(n);
        final Component component = (Component)jcScrollableInterface;
        jcScrollableInterface.setHorizOrigin(horizOrigin);
        if (component instanceof JCComponent) {
            ((JCComponent)component).getDrawingArea(this.rect);
        }
        else {
            this.rect.resize(component.size().width, component.size().height);
        }
        if (abs >= this.rect.width) {
            component.repaint();
            return;
        }
        final int n2 = this.rect.width - abs;
        final JCComponent jcComponent = (component instanceof JCComponent) ? ((JCComponent)component) : null;
        final Graphics graphics = component.getGraphics();
        if (jcComponent != null) {
            jcComponent.copyArea(graphics, this.rect.x + Math.max(n, 0), this.rect.y, n2, this.rect.height, -n, 0);
            jcComponent.repaint(graphics, this.rect.x + ((n > 0) ? n2 : false), this.rect.y, abs, this.rect.height);
        }
        else {
            graphics.copyArea(this.rect.x + Math.max(n, 0), this.rect.y, n2, this.rect.height, -n, 0);
            component.repaint(this.rect.x + ((n > 0) ? n2 : false), this.rect.y, abs, this.rect.height);
        }
        graphics.dispose();
    }
    
    protected void scrollVertical(final JCScrollableInterface jcScrollableInterface, final JCAdjustmentEvent jcAdjustmentEvent, final int vertOrigin) {
        final int n = vertOrigin - jcScrollableInterface.getVertOrigin();
        final int abs = Math.abs(n);
        final Component component = (Component)jcScrollableInterface;
        jcScrollableInterface.setVertOrigin(vertOrigin);
        if (component instanceof JCComponent) {
            ((JCComponent)component).getDrawingArea(this.rect);
        }
        else {
            this.rect.resize(component.size().width, component.size().height);
        }
        if (abs >= this.rect.height) {
            component.repaint();
            return;
        }
        final int n2 = this.rect.height - abs;
        final JCComponent jcComponent = (component instanceof JCComponent) ? ((JCComponent)component) : null;
        final Graphics graphics = component.getGraphics();
        if (jcComponent != null) {
            jcComponent.copyArea(graphics, this.rect.x, this.rect.y + Math.max(n, 0), this.rect.width, n2, 0, -n);
            jcComponent.repaint(graphics, this.rect.x, this.rect.y + ((n > 0) ? n2 : false), this.rect.width, abs);
        }
        else {
            graphics.copyArea(this.rect.x, this.rect.y + Math.max(n, 0), this.rect.width, n2, 0, -n);
            component.repaint(this.rect.x, this.rect.y + ((n > 0) ? n2 : false), this.rect.width, abs);
        }
        graphics.dispose();
    }
    
    protected void setScrollPosition(final JCScrollableInterface jcScrollableInterface, final int horizOrigin, final int vertOrigin) {
        final int n = horizOrigin - jcScrollableInterface.getHorizOrigin();
        final int abs = Math.abs(n);
        final int n2 = vertOrigin - jcScrollableInterface.getVertOrigin();
        final int abs2 = Math.abs(n2);
        jcScrollableInterface.setHorizOrigin(horizOrigin);
        jcScrollableInterface.setVertOrigin(vertOrigin);
        final Component component = (Component)jcScrollableInterface;
        if (component instanceof JCComponent) {
            ((JCComponent)component).getDrawingArea(this.rect);
        }
        else {
            this.rect.resize(component.size().width, component.size().height);
        }
        if (abs >= this.rect.width || abs2 > this.rect.height) {
            component.repaint();
            return;
        }
        final int n3 = this.rect.width - abs;
        final int n4 = this.rect.height - abs2;
        final JCComponent jcComponent = (component instanceof JCComponent) ? ((JCComponent)component) : null;
        final Graphics graphics = component.getGraphics();
        if (jcComponent != null) {
            jcComponent.copyArea(graphics, this.rect.x + Math.max(n, 0), this.rect.y + Math.max(n2, 0), n3, n4, -n, -n2);
            jcComponent.repaint(graphics, (n >= 0 && n2 >= 0) ? (this.rect.x + n3) : this.rect.x, this.rect.y, (n2 < 0) ? this.rect.width : abs, (n2 < 0) ? abs2 : n4);
            jcComponent.repaint(graphics, (n >= 0 && n2 < 0) ? (this.rect.x + n3) : this.rect.x, (n2 < 0) ? (this.rect.y + abs2) : (this.rect.y + n4), (n2 < 0) ? abs : this.rect.width, (n2 < 0) ? n4 : abs2);
        }
        else {
            graphics.copyArea(this.rect.x + Math.max(n, 0), this.rect.y + Math.max(n2, 0), n3, n4, -n, -n2);
            component.repaint((n >= 0 && n2 >= 0) ? (this.rect.x + n3) : this.rect.x, this.rect.y, (n2 < 0) ? this.rect.width : abs, (n2 < 0) ? abs2 : n4);
            component.repaint((n >= 0 && n2 < 0) ? (this.rect.x + n3) : this.rect.x, (n2 < 0) ? (this.rect.y + abs2) : (this.rect.y + n4), (n2 < 0) ? abs : this.rect.width, (n2 < 0) ? n4 : abs2);
        }
        graphics.dispose();
    }
    
    protected void scrollVertical(final JCAdjustmentEvent jcAdjustmentEvent, final int n) {
        if (this.viewport instanceof Viewport) {
            ((Viewport)this.viewport).scrollVertical(n);
            return;
        }
        if (this.viewport instanceof JCScrollableInterface) {
            this.scrollVertical((JCScrollableInterface)this.viewport, jcAdjustmentEvent, n);
        }
    }
    
    protected void scrollHorizontal(final JCAdjustmentEvent jcAdjustmentEvent, final int n) {
        if (this.viewport instanceof Viewport) {
            ((Viewport)this.viewport).scrollHorizontal(n);
            return;
        }
        if (this.viewport instanceof JCScrollableInterface) {
            this.scrollHorizontal((JCScrollableInterface)this.viewport, jcAdjustmentEvent, n);
        }
    }
    
    public void scrollVertical(final int value) {
        this.vert_sb.setValue(value);
        this.scrollVertical(null, this.vert_sb.getValue());
    }
    
    public void scrollHorizontal(final int value) {
        this.horiz_sb.setValue(value);
        this.scrollHorizontal(null, this.horiz_sb.getValue());
    }
    
    public void setScrollPosition(final int value, final int value2) {
        this.horiz_sb.setValue(value);
        this.vert_sb.setValue(value2);
        if (this.viewport instanceof Viewport) {
            ((Viewport)this.viewport).scrollHorizontal(value);
            ((Viewport)this.viewport).scrollVertical(value2);
            return;
        }
        if (this.viewport instanceof JCScrollableInterface) {
            this.setScrollPosition((JCScrollableInterface)this.viewport, this.horiz_sb.getValue(), this.vert_sb.getValue());
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 33: {
                this.keystroke = 1002;
                this.scrollVertical(this.vert_sb.getValue() - this.vert_sb.getBlockIncrement());
                break;
            }
            case 34: {
                this.keystroke = 1003;
                this.scrollVertical(this.vert_sb.getValue() + this.vert_sb.getBlockIncrement());
                break;
            }
            case 36: {
                this.keystroke = 1000;
                this.scrollVertical(0);
                break;
            }
            case 35: {
                this.keystroke = 1001;
                this.scrollVertical(this.vert_sb.getMaximum());
                break;
            }
            case 37: {
                this.keystroke = 1006;
                this.scrollHorizontal(this.horiz_sb.getValue() - this.horiz_sb.getUnitIncrement());
                break;
            }
            case 39: {
                this.keystroke = 1007;
                this.scrollHorizontal(this.horiz_sb.getValue() + this.horiz_sb.getUnitIncrement());
                break;
            }
        }
        this.keystroke = 0;
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void adjustmentValueChanged(final JCAdjustmentEvent jcAdjustmentEvent) {
        if (jcAdjustmentEvent.getSource() == this.vert_sb) {
            this.scrollVertical(jcAdjustmentEvent, this.vert_sb.getValue());
            return;
        }
        if (jcAdjustmentEvent.getSource() == this.horiz_sb) {
            this.scrollHorizontal(jcAdjustmentEvent, this.horiz_sb.getValue());
        }
    }
    
    public final void setLayout(final LayoutManager layoutManager) {
    }
}
