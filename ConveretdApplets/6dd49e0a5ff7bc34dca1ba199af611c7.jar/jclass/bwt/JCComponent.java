// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import jclass.util.JCEnvironment;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.FocusEvent;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Shape;
import java.awt.Frame;
import java.awt.Font;
import jclass.util.JCUtilConverter;
import jclass.util.JCFile;
import java.awt.Component;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Rectangle;
import jclass.util.JCConverter;
import java.awt.Insets;
import java.awt.Color;
import java.applet.AppletContext;
import java.applet.Applet;
import java.awt.Canvas;

public abstract class JCComponent extends Canvas
{
    public static final String version;
    protected transient Applet applet;
    protected transient AppletContext applet_context;
    protected transient boolean in_repaint;
    protected transient boolean needs_layout;
    protected int highlight;
    protected int shadow;
    protected int shadow_type;
    static final Object LOCK;
    static long popdown_time;
    boolean double_buffer;
    Color highlight_color;
    Object userdata;
    boolean has_focus;
    boolean traversable;
    int pref_width;
    int pref_height;
    Insets insets;
    static JCConverter conv;
    boolean do_repaint;
    transient Rectangle paint_rect;
    protected transient Graphics draw_gc;
    protected transient Image dblbuffer_image;
    transient Graphics dblbuffer_image_gc;
    private Rectangle rect;
    private static final int[][] keyCodes;
    
    public JCComponent() {
        this(null, null);
    }
    
    public JCComponent(final Applet applet, final String name) {
        this.in_repaint = false;
        this.needs_layout = true;
        this.highlight = 2;
        this.shadow = 2;
        this.shadow_type = 3;
        this.double_buffer = true;
        this.has_focus = false;
        this.traversable = true;
        this.pref_width = -999;
        this.pref_height = -999;
        this.insets = new Insets(0, 0, 0, 0);
        this.do_repaint = true;
        this.rect = new Rectangle();
        this.applet = applet;
        this.setName(name);
        if (this.getClass().getName().equals("jclass.bwt.JCComponent")) {
            this.getParameters(applet);
        }
    }
    
    String getParam(final String s) {
        return JCComponent.conv.getParam(this.applet, this, this.getName(), s);
    }
    
    protected void getParameters() {
        ComponentConverter.getParams(this);
    }
    
    public void getParameters(final Applet applet) {
        this.getParameters(applet, null);
    }
    
    public void getParameters(final Applet applet, String param) {
        this.applet = applet;
        if (param == null) {
            param = this.getParam("paramFile");
        }
        if (param != null) {
            JCUtilConverter.setParamSource(this, JCFile.read(applet, param));
        }
        if (param != null || this.getAppletContext() != null) {
            this.getParameters();
            if (this.getPeer() != null) {
                this.addNotify();
                this.repaint();
            }
        }
    }
    
    public static void setConverter(final JCConverter conv) {
        JCComponent.conv = conv;
    }
    
    public static JCConverter getConverter() {
        return JCComponent.conv;
    }
    
    public synchronized void setFont(final Font font) {
        super.setFont(font);
        this.repaint();
    }
    
    public synchronized void setBackground(final Color background) {
        super.setBackground(background);
        this.repaint();
    }
    
    public synchronized void setForeground(final Color foreground) {
        super.setForeground(foreground);
        this.repaint();
    }
    
    public int getShadowThickness() {
        return this.shadow;
    }
    
    public void setShadowThickness(final int shadow) {
        this.shadow = shadow;
        this.layout();
        this.repaint();
    }
    
    public int getHighlightThickness() {
        return this.highlight;
    }
    
    public synchronized void setHighlightThickness(final int highlight) {
        this.highlight = highlight;
        this.layout();
        this.repaint();
    }
    
    public Color getHighlightColor() {
        return (this.highlight_color != null) ? this.highlight_color : Color.black;
    }
    
    public synchronized void setHighlightColor(final Color highlight_color) {
        this.highlight_color = highlight_color;
        this.repaint();
    }
    
    public synchronized void setInsets(final Insets insets) {
        this.insets = insets;
        this.layout();
        this.repaint();
    }
    
    public Rectangle getDrawingArea() {
        final Rectangle rectangle = new Rectangle();
        this.getDrawingArea(rectangle);
        return rectangle;
    }
    
    public void getDrawingArea(final Rectangle rectangle) {
        final int n = this.highlight + this.shadow;
        rectangle.reshape(n + this.insets.left, n + this.insets.top, Math.max(0, this.size().width - (2 * n + this.insets.left + this.insets.right)), Math.max(0, this.size().height - (2 * n + this.insets.top + this.insets.bottom)));
    }
    
    public int getDrawingAreaHeight() {
        return Math.max(0, this.size().height - (2 * this.shadow + 2 * this.highlight + this.insets.top + this.insets.bottom));
    }
    
    public int getDrawingAreaWidth() {
        return Math.max(0, this.size().width - (2 * this.shadow + 2 * this.highlight + this.insets.left + this.insets.right));
    }
    
    public Insets insets() {
        return this.insets;
    }
    
    public Insets getInsets() {
        return this.insets;
    }
    
    public Frame getFrame() {
        return BWTUtil.getFrame(this);
    }
    
    public void setCursor(final int n) {
        BWTUtil.setCursor(this, n);
    }
    
    public synchronized void enable() {
        if (!this.isEnabled()) {
            super.enable();
            this.repaint();
        }
    }
    
    public synchronized void disable() {
        if (this.isEnabled()) {
            this.drawHighlight(false);
            super.disable();
            this.repaint();
        }
    }
    
    public boolean isTraversable() {
        return this.traversable && this.isEnabled() && this.isShowing();
    }
    
    public boolean isFocusTraversable() {
        return this.traversable;
    }
    
    public void setTraversable(final boolean traversable) {
        this.traversable = traversable;
    }
    
    public Object getUserData() {
        return this.userdata;
    }
    
    public int getUserDataInt() {
        return (int)((this.userdata instanceof Integer) ? this.userdata : 0);
    }
    
    public void setUserData(final Object userdata) {
        this.userdata = userdata;
    }
    
    public synchronized void layout() {
        this.needs_layout = false;
    }
    
    public synchronized void reshape(final Rectangle rectangle) {
        this.reshape(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public Image getDoubleBufferImage() {
        return this.dblbuffer_image;
    }
    
    public synchronized Graphics getDoubleBufferGraphics() {
        final Image dblbuffer_image = this.dblbuffer_image;
        this.dblbuffer_image = BWTUtil.createImage(this, this.size().width, this.size().height);
        if (this.dblbuffer_image == null) {
            this.dblbuffer_image_gc = null;
        }
        else if (this.dblbuffer_image != dblbuffer_image) {
            this.dblbuffer_image_gc = this.dblbuffer_image.getGraphics();
        }
        return this.dblbuffer_image_gc;
    }
    
    public synchronized void repaint() {
        this.repaint(0, 0, this.size().width, this.size().height);
    }
    
    public synchronized void repaint(final Rectangle rectangle) {
        this.repaint(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    void repaint(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (graphics == null) {
            return;
        }
        this.in_repaint = true;
        graphics.setClip(n, n2, n3, n4);
        this.paint(graphics);
        this.in_repaint = false;
    }
    
    public void repaint(final int n, final int n2, int n3, int n4) {
        if (this.getPeer() == null || !this.isShowing() || n3 <= 0 || n4 <= 0 || this.in_repaint) {
            return;
        }
        final int width = this.size().width;
        final int height = this.size().height;
        if (width <= n || height <= n2 || n + n3 < 0 || n2 + n4 < 0) {
            return;
        }
        if (n + n3 > width) {
            n3 = width - n;
        }
        if (n2 + n4 > height) {
            n4 = height - n2;
        }
        final Graphics graphics = this.getGraphics();
        this.repaint(graphics, n, n2, n3, n4);
        graphics.dispose();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void updateParent() {
        if (this.getParent() != null) {
            this.invalidate();
            this.getParent().invalidate();
            this.getParent().validate();
        }
    }
    
    protected synchronized void paintComponent(final Graphics graphics) {
    }
    
    protected void fillBackground(final Graphics graphics) {
        graphics.fillRect(0, 0, this.size().width, this.size().height);
    }
    
    final boolean intersects(final Rectangle rectangle, final int n) {
        if (rectangle == null) {
            return true;
        }
        final int n2 = n + this.size().height - 2 * n;
        final int n3 = n + this.size().width - 2 * n;
        return (n >= rectangle.x && n <= rectangle.x + rectangle.width) || (n3 >= rectangle.x && n3 <= rectangle.x + rectangle.width) || (n >= rectangle.y && n <= rectangle.y + rectangle.height) || (n2 >= rectangle.y && n2 <= rectangle.y + rectangle.height);
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
        if (!this.intersects(this.paint_rect, this.highlight)) {
            return;
        }
        int n = 0;
        int n2 = 0;
        int width = this.size().width;
        int height = this.size().height;
        Color black = b ? this.highlight_color : (BWTUtil.instanceOf(this.getParent(), "JCSpinBox") ? this.getBackground() : this.getParent().getBackground());
        if (black == null) {
            black = Color.black;
        }
        graphics.setColor(black);
        for (int i = 0; i < this.highlight; ++i, ++n, ++n2, width -= 2, height -= 2) {
            graphics.drawRect(n, n2, width - 1, height - 1);
        }
    }
    
    protected void drawShadow(final Graphics graphics) {
        if (!this.intersects(this.paint_rect, this.highlight + this.shadow)) {
            return;
        }
        Shadow.draw(graphics, this.shadow_type, this.shadow, this.highlight, this.highlight, this.size().width - 2 * this.highlight, this.size().height - 2 * this.highlight, this.getBackground(), this.getForeground());
    }
    
    void drawHighlightAndShadow(final Graphics graphics) {
        this.drawHighlight(graphics, this.has_focus);
        if (this.shadow > 0 && this.shadow_type != 0) {
            this.drawShadow(graphics);
        }
    }
    
    public Rectangle getPaintRect() {
        return this.paint_rect;
    }
    
    public synchronized void paint(Graphics draw_gc) {
        if (draw_gc == null || this.getBackground() == null) {
            return;
        }
        this.draw_gc = draw_gc;
        this.paint_rect = draw_gc.getClipRect();
        if (this.double_buffer) {
            draw_gc = this.getDoubleBufferGraphics();
            if (draw_gc == null) {
                draw_gc = this.draw_gc;
            }
            this.dblbuffer_image = this.getDoubleBufferImage();
            if (this.paint_rect != null) {
                draw_gc.setClip(this.paint_rect);
            }
        }
        if (this.paint_rect == null) {
            draw_gc.setClip(this.paint_rect = new Rectangle(this.size()));
        }
        this.rect.reshape(0, 0, this.size().width, this.size().height);
        draw_gc.setColor(this.getBackground());
        this.fillBackground(draw_gc);
        this.drawHighlightAndShadow(draw_gc);
        draw_gc.setFont(this.getFont());
        draw_gc.setColor(this.getForeground());
        this.getDrawingArea(this.rect);
        draw_gc.clipRect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
        final Image dblbuffer_image = this.dblbuffer_image;
        this.paintComponent(draw_gc);
        if (this.double_buffer && this.dblbuffer_image != null) {
            this.draw_gc.drawImage(this.dblbuffer_image, 0, 0, null);
            if (dblbuffer_image != this.dblbuffer_image) {
                this.drawHighlightAndShadow(this.draw_gc);
            }
        }
        this.dblbuffer_image = dblbuffer_image;
        this.draw_gc = null;
        this.paint_rect = null;
    }
    
    public void printAll(final Graphics graphics) {
        final boolean double_buffer = this.double_buffer;
        this.double_buffer = false;
        super.printAll(graphics);
        this.double_buffer = double_buffer;
    }
    
    protected void enable11Events(final long n) {
        this.enableEvents(n);
    }
    
    public void addNotify() {
        if (this.getPeer() == null) {
            super.addNotify();
        }
        if (this.applet == null) {
            this.applet = BWTUtil.getApplet(this);
        }
        this.applet_context = this.getAppletContext();
        this.enable11Events(28L);
    }
    
    public AppletContext getAppletContext() {
        return BWTUtil.getAppletContext(this.applet);
    }
    
    public Dimension minimumSize() {
        return this.preferredSize();
    }
    
    protected int preferredWidth() {
        return -1;
    }
    
    protected int preferredHeight() {
        return -1;
    }
    
    public void setPreferredSize(final int pref_width, final int pref_height) {
        this.pref_width = pref_width;
        this.pref_height = pref_height;
    }
    
    public Dimension preferredSize() {
        final int n = (this.pref_width != -999) ? this.pref_width : ((this.getPeer() != null) ? this.preferredWidth() : 0);
        final int n2 = (n < 0) ? 50 : (n + 2 * this.shadow + 2 * this.highlight + this.insets.left + this.insets.right);
        final int n3 = (this.pref_height != -999) ? this.pref_height : ((this.getPeer() != null) ? this.preferredHeight() : 0);
        return new Dimension(n2, (n3 < 0) ? 50 : (n3 + 2 * this.shadow + 2 * this.highlight + this.insets.top + this.insets.bottom));
    }
    
    public boolean getDoubleBuffer() {
        return this.double_buffer;
    }
    
    public void setDoubleBuffer(final boolean double_buffer) {
        this.double_buffer = double_buffer;
    }
    
    public boolean hasFocus() {
        return this.has_focus;
    }
    
    public boolean lostFocus(final Event event, final Object o) {
        super.lostFocus(event, o);
        this.has_focus = false;
        if (this.isShowing()) {
            this.drawHighlight(false);
        }
        return true;
    }
    
    public boolean gotFocus(final Event event, final Object o) {
        if (!this.isEnabled()) {
            this.drawHighlight(false);
            return true;
        }
        super.gotFocus(event, o);
        this.has_focus = true;
        if (this.isShowing()) {
            this.drawHighlight(true);
        }
        return true;
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
    
    static final Event convertEvent(final Component component, final KeyEvent keyEvent) {
        int keyChar = keyEvent.getKeyChar();
        final int keyCode = keyEvent.getKeyCode();
        for (int i = 0; i < JCComponent.keyCodes.length; ++i) {
            if (JCComponent.keyCodes[i][0] == keyCode) {
                keyChar = JCComponent.keyCodes[i][1];
                break;
            }
        }
        return new Event(component, keyEvent.getWhen(), keyEvent.getID(), 0, 0, keyChar, keyEvent.getModifiers());
    }
    
    protected void processKeyEvent(final KeyEvent keyEvent) {
        switch (keyEvent.getID()) {
            case 401: {
                if (keyEvent.isActionKey()) {}
                final int keyCode = keyEvent.getKeyCode();
                if (keyCode == 16 || keyCode == 17 || keyCode == 18) {
                    return;
                }
                final Event convertEvent = convertEvent(this, keyEvent);
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
                if (event.when - JCComponent.popdown_time > 50 || JCEnvironment.getOS() == 1) {
                    this.mouseDown(event, event.x, event.y);
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
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (event.when - JCComponent.popdown_time < 50 && JCEnvironment.getOS() != 1) {
            return true;
        }
        if (BWTUtil.getMouseButton(event) == 1 && this.isTraversable()) {
            this.requestFocus();
        }
        return false;
    }
    
    public void requestFocus() {
        super.requestFocus();
        if (this.isTraversable()) {
            this.handleEvent(new Event(this, 1004, null));
        }
    }
    
    public synchronized void reshape(final int n, final int n2, final int n3, final int n4) {
        if (this.size().width == n3 && this.size().height == n4 && this.location().x == n && this.location().y == n2) {
            return;
        }
        super.reshape(n, n2, n3, n4);
        if (JCEnvironment.getJavaVersion() == 110) {
            this.layout();
        }
        if (this.do_repaint) {
            this.repaint();
        }
    }
    
    public void validate() {
        if (this.isValid() || this.getPeer() == null) {
            return;
        }
        super.validate();
        if (JCEnvironment.getJavaVersion() == 110) {
            this.layout();
        }
    }
    
    public Image createImage(int max, int max2) {
        final Dimension screenSize = this.getToolkit().getScreenSize();
        max = Math.max(1, Math.min(max, screenSize.width));
        max2 = Math.max(1, Math.min(max2, screenSize.height));
        Image image = null;
        try {
            image = super.createImage(max, max2);
        }
        catch (Throwable t) {}
        return image;
    }
    
    void copyArea(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        graphics.copyArea(n, n2, n3, n4, n5, n6);
    }
    
    static {
        version = JCVersion.getVersionString();
        LOCK = new Object();
        JCComponent.conv = new JCConverter();
        keyCodes = new int[][] { { 127, 127 }, { 40, 1005 }, { 35, 1001 }, { 10, 10 }, { 36, 1000 }, { 37, 1006 }, { 34, 1003 }, { 33, 1002 }, { 39, 1007 }, { 38, 1004 } };
    }
}
