// 
// Decompiled by Procyon v0.5.30
// 

package jclass.base;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Shape;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.Cursor;
import jclass.util.JCEnvironment;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.Component;
import java.awt.Event;
import java.awt.event.FocusEvent;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Color;

public abstract class TransientComponent extends BaseComponent
{
    public static long popdown_event_timestamp;
    public static long mouse_down_event_timestamp;
    protected int highlight;
    protected Color highlight_color;
    protected int pref_width;
    protected int pref_height;
    protected boolean traversable;
    protected transient Graphics draw_gc;
    private static final int[][] keyCodes;
    
    public TransientComponent() {
        this(null, null);
    }
    
    public TransientComponent(final Applet applet, final String s) {
        super(applet, s);
        this.highlight = 2;
        this.pref_width = -999;
        this.pref_height = -999;
        this.traversable = true;
    }
    
    protected void processFocusEvent(final FocusEvent focusEvent) {
        final Event event = new Event(this, 0L, focusEvent.getID(), 0, 0, 0, 0);
        switch (event.id) {
            case 1005: {
                this.lostFocus(event, this);
                break;
            }
            case 1004: {
                this.gotFocus(event, this);
                break;
            }
        }
        super.processFocusEvent(focusEvent);
    }
    
    public static final Event convertEvent(final Component component, final KeyEvent keyEvent) {
        int keyChar = keyEvent.getKeyChar();
        final int keyCode = keyEvent.getKeyCode();
        for (int i = 0; i < TransientComponent.keyCodes.length; ++i) {
            if (TransientComponent.keyCodes[i][0] == keyCode) {
                keyChar = TransientComponent.keyCodes[i][1];
                break;
            }
        }
        return new Event(component, keyEvent.getWhen(), 0, 0, 0, keyChar, keyEvent.getModifiers());
    }
    
    protected void processKeyEvent(final KeyEvent keyEvent) {
        int id = keyEvent.getID();
        switch (id) {
            case 401: {
                if (keyEvent.isActionKey()) {
                    id = 403;
                }
                final int keyCode = keyEvent.getKeyCode();
                if (keyCode == 16 || keyCode == 17 || keyCode == 18) {
                    return;
                }
                final Event convertEvent = convertEvent(this, keyEvent);
                convertEvent.id = id;
                this.keyDown(convertEvent, convertEvent.key);
                break;
            }
        }
        super.processKeyEvent(keyEvent);
    }
    
    protected void processMouseEvent(final MouseEvent mouseEvent) {
        final Event event = new Event(this, mouseEvent.getWhen(), mouseEvent.getID(), mouseEvent.getX(), mouseEvent.getY(), 0, mouseEvent.getModifiers());
        event.clickCount = mouseEvent.getClickCount();
        switch (event.id) {
            case 501: {
                TransientComponent.mouse_down_event_timestamp = event.when;
                if (event.when - TransientComponent.popdown_event_timestamp > 50L || JCEnvironment.getOS() == 1) {
                    this.mouseDown(event, event.x, event.y);
                    break;
                }
                break;
            }
            case 502: {
                this.mouseUp(event, event.x, event.y);
                break;
            }
            case 504: {
                this.mouseEnter(event, event.x, event.y);
                break;
            }
            case 505: {
                this.mouseExit(event, event.x, event.y);
                break;
            }
        }
        super.processMouseEvent(mouseEvent);
    }
    
    protected void processMouseMotionEvent(final MouseEvent mouseEvent) {
        final Event event = new Event(this, mouseEvent.getWhen(), mouseEvent.getID(), mouseEvent.getX(), mouseEvent.getY(), 0, mouseEvent.getModifiers());
        switch (event.id) {
            case 503: {
                this.mouseMove(event, event.x, event.y);
                break;
            }
            case 506: {
                this.mouseDrag(event, event.x, event.y);
                break;
            }
        }
        super.processMouseMotionEvent(mouseEvent);
    }
    
    public void setCursor(final int n) {
        final Window window = BaseComponent.getWindow(this);
        if (window != null) {
            window.setCursor(Cursor.getPredefinedCursor(n));
            this.getToolkit().sync();
        }
    }
    
    public int getPreferredWidthInternal() {
        return this.pref_width;
    }
    
    public int getPreferredHeightInternal() {
        return this.pref_height;
    }
    
    public Dimension preferredSize() {
        final int n = (this.pref_width != -999) ? this.pref_width : ((this.getPeer() != null) ? this.preferredWidth() : 0);
        final int n2 = (n < 0) ? 50 : (n + 2 * super.border + 2 * this.highlight + super.insets.left + super.insets.right);
        final int n3 = (this.pref_height != -999) ? this.pref_height : ((this.getPeer() != null) ? this.preferredHeight() : 0);
        return new Dimension(n2, (n3 < 0) ? 50 : (n3 + 2 * super.border + 2 * this.highlight + super.insets.top + super.insets.bottom));
    }
    
    public Dimension minimumSize() {
        return this.preferredSize();
    }
    
    protected int preferredWidth() {
        return -999;
    }
    
    protected int preferredHeight() {
        return -999;
    }
    
    public void setPreferredSize(final int pref_width, final int pref_height) {
        this.pref_width = pref_width;
        this.pref_height = pref_height;
    }
    
    public void enable() {
        if (!this.isEnabled()) {
            super.enable();
            this.repaint();
        }
    }
    
    public void disable() {
        if (this.isEnabled()) {
            this.drawHighlight(false);
            super.disable();
            this.repaint();
        }
    }
    
    protected void drawHighlight(final boolean b) {
        if (this.isShowing()) {
            final Graphics graphics = this.getGraphics();
            if (graphics != null) {
                this.drawHighlight(graphics, b);
                graphics.dispose();
            }
        }
    }
    
    protected void drawHighlight(final Graphics graphics, final boolean b) {
        if (this.highlight == 0) {
            return;
        }
        if (!this.intersects(super.paint_rect, this.highlight)) {
            return;
        }
        int n = 0;
        int n2 = 0;
        int width = this.getSize().width;
        int height = this.getSize().height;
        Color black = b ? this.highlight_color : this.getParent().getBackground();
        if (black == null) {
            black = Color.black;
        }
        graphics.setColor(black);
        for (int i = 0; i < this.highlight; ++i, ++n, ++n2, width -= 2, height -= 2) {
            graphics.drawRect(n, n2, width - 1, height - 1);
        }
    }
    
    protected void drawHighlightAndBorder(final Graphics graphics) {
        this.drawHighlight(graphics, super.has_focus);
        if (super.border > 0 && super.border_style != 0) {
            this.drawBorder(graphics);
        }
    }
    
    public boolean gotFocus102(final Event event, final Object o) {
        if (!this.isEnabled()) {
            this.drawHighlight(false);
            return true;
        }
        super.gotFocus102(event, o);
        super.has_focus = true;
        if (this.isShowing()) {
            this.drawHighlight(true);
        }
        return true;
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        synchronized (this) {
            if (this.size().width == n3 && this.size().height == n4 && this.location().x == n && this.location().y == n2) {
                // monitorexit(this)
                return;
            }
        }
        super.reshape(n, n2, n3, n4);
        if (JCEnvironment.getJavaVersion() >= 110 || JCEnvironment.isJavaOS()) {
            this.layout();
        }
        if (super.do_repaint) {
            this.repaint();
        }
    }
    
    public void reshape(final Rectangle rectangle) {
        this.reshape(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public synchronized void layout() {
        super.needs_layout = false;
    }
    
    public boolean isTraversable() {
        return this.traversable;
    }
    
    public boolean isFocusTraversable() {
        return this.traversable && this.isEnabled() && this.isShowing();
    }
    
    public void setTraversable(final boolean traversable) {
        this.traversable = traversable;
    }
    
    public int getHighlightThickness() {
        return this.highlight;
    }
    
    public void setHighlightThickness(final int highlight) {
        synchronized (this) {
            this.highlight = highlight;
        }
        this.doLayout();
        this.repaint();
    }
    
    public Color getHighlightColor() {
        if (this.highlight_color != null) {
            return this.highlight_color;
        }
        return Color.black;
    }
    
    public void setHighlightColor(final Color highlight_color) {
        synchronized (this) {
            this.highlight_color = highlight_color;
        }
        this.repaint();
    }
    
    public Rectangle getPaintRect() {
        return super.paint_rect;
    }
    
    public void paint(Graphics draw_gc) {
        synchronized (this.getAWTLock()) {
            if (draw_gc == null || this.getBackground() == null) {
                // monitorexit(this.getAWTLock())
                return;
            }
            this.draw_gc = draw_gc;
            super.paint_rect = draw_gc.getClipRect();
            if (super.double_buffer) {
                draw_gc = this.getDoubleBufferGraphics();
                if (draw_gc == null) {
                    draw_gc = this.draw_gc;
                }
                super.dblbuffer_image = this.getDoubleBufferImage();
                if (super.paint_rect != null) {
                    draw_gc.setClip(super.paint_rect);
                }
            }
            if (super.paint_rect == null) {
                draw_gc.setClip(super.paint_rect = new Rectangle(this.getSize()));
            }
            super.rect.setBounds(0, 0, this.getSize().width, this.getSize().height);
            draw_gc.setColor(this.getBackground());
            this.fillBackground(draw_gc);
            this.drawHighlightAndBorder(draw_gc);
            draw_gc.setFont(this.getFont());
            draw_gc.setColor(this.getForeground());
            this.getDrawingArea(super.rect);
            draw_gc.clipRect(super.rect.x, super.rect.y, super.rect.width, super.rect.height);
            final Image dblbuffer_image = super.dblbuffer_image;
            this.paintComponent(draw_gc);
            if (super.double_buffer && super.dblbuffer_image != null) {
                this.draw_gc.drawImage(super.dblbuffer_image, 0, 0, null);
                if (dblbuffer_image != super.dblbuffer_image) {
                    this.drawHighlightAndBorder(this.draw_gc);
                }
            }
            super.dblbuffer_image = dblbuffer_image;
            this.draw_gc = null;
            super.paint_rect = null;
        }
        // monitorexit(this.getAWTLock())
    }
    
    public Rectangle getDrawingArea() {
        final Rectangle rectangle = new Rectangle();
        this.getDrawingArea(rectangle);
        return rectangle;
    }
    
    public void getDrawingArea(final Rectangle rectangle) {
        final int n = this.highlight + super.border;
        rectangle.setBounds(n + super.insets.left, n + super.insets.top, Math.max(0, this.getSize().width - (2 * n + super.insets.left + super.insets.right)), Math.max(0, this.getSize().height - (2 * n + super.insets.top + super.insets.bottom)));
    }
    
    public int getDrawingAreaHeight() {
        return Math.max(0, this.getSize().height - (2 * super.border + 2 * this.highlight + super.insets.top + super.insets.bottom));
    }
    
    public int getDrawingAreaWidth() {
        return Math.max(0, this.getSize().width - (2 * super.border + 2 * this.highlight + super.insets.left + super.insets.right));
    }
    
    protected void drawBorder(final Graphics graphics) {
        if (!this.intersects(super.paint_rect, super.border)) {
            return;
        }
        Border.draw(graphics, super.border_style, super.border, this.highlight, this.highlight, this.getSize().width - 2 * this.highlight, this.getSize().height - 2 * this.highlight, this.getBackground(), this.getForeground());
    }
    
    public boolean lostFocus102(final Event event, final Object o) {
        super.lostFocus102(event, o);
        if (this.isShowing()) {
            this.drawHighlight(false);
        }
        return true;
    }
    
    static {
        keyCodes = new int[][] { { 127, 127 }, { 40, 1005 }, { 35, 1001 }, { 10, 10 }, { 36, 1000 }, { 37, 1006 }, { 34, 1003 }, { 33, 1002 }, { 39, 1007 }, { 38, 1004 } };
    }
}
