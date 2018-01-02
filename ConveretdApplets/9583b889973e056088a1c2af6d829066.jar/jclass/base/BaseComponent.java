// 
// Decompiled by Procyon v0.5.30
// 

package jclass.base;

import java.awt.Point;
import java.awt.Container;
import java.util.Enumeration;
import java.awt.image.ImageObserver;
import java.awt.Window;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.Event;
import java.awt.SystemColor;
import jclass.util.JCEnvironment;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Color;
import java.util.Hashtable;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Insets;
import java.applet.AppletContext;
import java.applet.Applet;
import java.awt.Canvas;

public abstract class BaseComponent extends Canvas
{
    public static final int NOVALUE = -999;
    public static final char BELL = '\u0007';
    public static final char BS = '\b';
    public static final char TAB = '\t';
    public static final char ENTER = '\n';
    public static final char DELETE = '\u007f';
    public static final char ESC = '\u001b';
    protected transient Applet applet;
    protected transient AppletContext applet_context;
    protected transient boolean in_repaint;
    protected transient boolean needs_layout;
    protected int border;
    protected int border_style;
    protected static final Object LOCK;
    protected boolean double_buffer;
    protected boolean has_focus;
    protected Object userdata;
    protected Insets insets;
    protected boolean do_repaint;
    protected static boolean use_system_colors;
    protected transient Rectangle paint_rect;
    protected transient Image dblbuffer_image;
    protected transient Graphics dblbuffer_image_gc;
    protected transient boolean realized;
    protected Rectangle rect;
    protected static Hashtable images;
    
    public BaseComponent() {
        this(null, null);
    }
    
    public BaseComponent(final Applet applet, final String name) {
        this.in_repaint = false;
        this.needs_layout = true;
        this.border = 2;
        this.border_style = 3;
        this.double_buffer = true;
        this.has_focus = false;
        this.insets = new Insets(0, 0, 0, 0);
        this.do_repaint = true;
        this.realized = false;
        this.rect = new Rectangle();
        this.applet = applet;
        this.setName(name);
    }
    
    public static void useSystemColors(final boolean use_system_colors) {
        BaseComponent.use_system_colors = use_system_colors;
    }
    
    public Object getAWTLock() {
        return this.getTreeLock();
    }
    
    public void setBackground(final Color background) {
        super.setBackground(background);
        this.repaint();
    }
    
    public void setForeground(final Color foreground) {
        super.setForeground(foreground);
        this.repaint();
    }
    
    public int getBorderThickness() {
        return this.border;
    }
    
    public void setBorderThickness(final int border) {
        this.border = border;
        this.doLayout();
        this.repaint();
    }
    
    public int getBorderStyle() {
        return this.border_style;
    }
    
    public void setBorderStyle(final int border_style) {
        this.border_style = border_style;
        this.doLayout();
        this.repaint();
    }
    
    public int getShadowThickness() {
        return this.getBorderThickness();
    }
    
    public void setShadowThickness(final int borderThickness) {
        this.setBorderThickness(borderThickness);
    }
    
    public void setInsets(final Insets insets) {
        synchronized (this) {
            this.insets = insets;
        }
        this.doLayout();
        this.repaint();
    }
    
    public Insets insets() {
        return this.insets;
    }
    
    public Insets getInsets() {
        return this.insets;
    }
    
    public Frame getFrame() {
        return getFrame(this);
    }
    
    public Object getUserData() {
        return this.userdata;
    }
    
    public int getUserDataInt() {
        if (this.userdata instanceof Integer) {
            return (int)this.userdata;
        }
        return 0;
    }
    
    public void setUserData(final Object userdata) {
        this.userdata = userdata;
    }
    
    public Image getDoubleBufferImage() {
        return this.dblbuffer_image;
    }
    
    public Graphics getDoubleBufferGraphics() {
        synchronized (this.getAWTLock()) {
            final Image dblbuffer_image = this.dblbuffer_image;
            this.dblbuffer_image = createImage(this, this.getSize().width, this.getSize().height);
            if (this.dblbuffer_image == null) {
                this.dblbuffer_image_gc = null;
            }
            else if (this.dblbuffer_image != dblbuffer_image) {
                this.dblbuffer_image_gc = this.dblbuffer_image.getGraphics();
            }
            // monitorexit(this.getAWTLock())
            return this.dblbuffer_image_gc;
        }
    }
    
    public boolean isShowing() {
        return JCEnvironment.isJavaOS() || super.isShowing();
    }
    
    public void repaint() {
        this.repaint(0, 0, this.getSize().width, this.getSize().height);
    }
    
    public void repaint(final Rectangle rectangle) {
        this.repaint(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public void repaint(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (graphics == null) {
            return;
        }
        this.in_repaint = true;
        graphics.setClip(n, n2, n3, n4);
        try {
            this.paint(graphics);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        finally {
            this.in_repaint = false;
        }
    }
    
    public abstract void paint(final Graphics p0);
    
    public void repaint(final int n, final int n2, int n3, int n4) {
        if (!this.isRealized() || !this.isShowing() || n3 <= 0 || n4 <= 0 || this.in_repaint) {
            return;
        }
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        if (width <= n || height <= n2 || n + n3 < 0 || n2 + n4 < 0) {
            return;
        }
        if (n + n3 > width) {
            n3 = width - n;
        }
        if (n2 + n4 > height) {
            n4 = height - n2;
        }
        super.repaint(n, n2, n3, n4);
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
    
    protected void paintComponent(final Graphics graphics) {
    }
    
    protected void fillBackground(final Graphics graphics) {
        graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
    }
    
    protected final boolean intersects(final Rectangle rectangle, final int n) {
        if (rectangle == null) {
            return true;
        }
        final int n2 = n + this.getSize().height - 2 * n;
        final int n3 = n + this.getSize().width - 2 * n;
        return (n >= rectangle.x && n <= rectangle.x + rectangle.width) || (n3 >= rectangle.x && n3 <= rectangle.x + rectangle.width) || (n >= rectangle.y && n <= rectangle.y + rectangle.height) || (n2 >= rectangle.y && n2 <= rectangle.y + rectangle.height);
    }
    
    protected void drawBorder(final Graphics graphics) {
        if (!this.intersects(this.paint_rect, this.border)) {
            return;
        }
        Border.draw(graphics, this.border_style, this.border, 0, 0, this.getSize().width, this.getSize().height, this.getBackground(), this.getForeground());
    }
    
    public void printAll(final Graphics graphics) {
        final boolean double_buffer = this.double_buffer;
        this.double_buffer = false;
        super.printAll(graphics);
        this.double_buffer = double_buffer;
    }
    
    public void addNotify() {
        if (!this.realized) {
            if (BaseComponent.use_system_colors) {
                if (this.getBackground() == null) {
                    this.setBackground(SystemColor.control);
                }
                if (this.getForeground() == null) {
                    this.setForeground(SystemColor.controlText);
                }
            }
            super.addNotify();
            this.realized = true;
        }
        if (this.applet == null) {
            this.applet = getApplet(this);
        }
        this.applet_context = this.getAppletContext();
        this.enableEvents(28L);
    }
    
    public void removeNotify() {
        super.removeNotify();
        this.realized = false;
    }
    
    public AppletContext getAppletContext() {
        return getAppletContext(this.applet);
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
    
    public boolean lostFocus102(final Event event, final Object o) {
        this.has_focus = false;
        return true;
    }
    
    public boolean gotFocus102(final Event event, final Object o) {
        return true;
    }
    
    protected void processFocusEvent(final FocusEvent focusEvent) {
        final Event event = new Event(this, 0L, focusEvent.getID(), 0, 0, 0, 0);
        switch (event.id) {
            case 1005: {
                this.lostFocus102(event, this);
                break;
            }
            case 1004: {
                this.gotFocus102(event, this);
                break;
            }
        }
        super.processFocusEvent(focusEvent);
    }
    
    public void validate() {
        if (this.isValid() || !this.isRealized()) {
            return;
        }
        super.validate();
        if (JCEnvironment.getJavaVersion() >= 110 || JCEnvironment.isJavaOS()) {
            this.doLayout();
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
    
    public void copyArea(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        graphics.copyArea(n, n2, n3, n4, n5, n6);
    }
    
    public static Frame getFrame(final Component component) {
        Window window = getWindow(component);
        if (window instanceof Dialog && window.getParent() instanceof Window) {
            window = (Window)window.getParent();
        }
        if (window instanceof Frame) {
            return (Frame)window;
        }
        return null;
    }
    
    public static synchronized Image createImage(final Component component, Image image, int max, int max2) {
        final Dimension screenSize = component.getToolkit().getScreenSize();
        max = Math.max(1, Math.min(max, screenSize.width));
        max2 = Math.max(1, Math.min(max2, screenSize.height));
        try {
            if (image == null || image.getWidth(null) < max || image.getHeight(null) < max2) {
                image = component.createImage(max, max2);
            }
        }
        catch (Throwable t) {}
        return image;
    }
    
    public static synchronized Image createImage(final Component component, final int n, final int n2) {
        final Enumeration<Thread> keys = BaseComponent.images.keys();
        while (keys.hasMoreElements()) {
            final Thread thread = keys.nextElement();
            if (!thread.isAlive()) {
                BaseComponent.images.remove(thread);
            }
        }
        final Thread currentThread = Thread.currentThread();
        final Image image = BaseComponent.images.get(currentThread);
        final Image image2 = createImage(component, image, n, n2);
        if (image2 != image) {
            BaseComponent.images.remove(currentThread);
            if (image2 != null) {
                BaseComponent.images.put(currentThread, image2);
            }
        }
        return image2;
    }
    
    public static Point translateToParent(final Container container, Component parent, int n, int n2) {
        while (parent != null && parent != container) {
            n += parent.getLocation().x;
            n2 += parent.getLocation().y;
            parent = parent.getParent();
        }
        return new Point(n, n2);
    }
    
    public static Point translateFromParent(final Container container, Component parent, int n, int n2) {
        while (parent != null && parent != container) {
            n -= parent.getLocation().x;
            n2 -= parent.getLocation().y;
            parent = parent.getParent();
        }
        return new Point(n, n2);
    }
    
    public static Point getVisibleScreenLoc(final Component component, final int n, final int n2, final int n3, final int n4) {
        final Point translateToParent = translateToParent(null, component, n, n2);
        final Dimension screenSize = component.getToolkit().getScreenSize();
        translateToParent.x = Math.max(0, Math.min(translateToParent.x, screenSize.width - n3));
        translateToParent.y = Math.max(0, Math.min(translateToParent.y, screenSize.height - n4));
        return translateToParent;
    }
    
    public static Window getWindow(final Component component) {
        Component parent = component;
        Component component2 = component;
        while (component2 != null && !(component2 instanceof Window)) {
            if ((component2 = parent) == null) {
                return null;
            }
            parent = parent.getParent();
        }
        return (Window)component2;
    }
    
    public void setApplet(final Applet applet) {
        this.applet = applet;
    }
    
    public Applet getApplet() {
        return this.applet;
    }
    
    public boolean isRealized() {
        return this.realized;
    }
    
    public static Applet getApplet(final Component component) {
        if (component == null) {
            return null;
        }
        Container parent;
        Container parent2;
        for (parent = component.getParent(), parent2 = component.getParent(); parent2 != null && !(parent2 instanceof Applet); parent2 = parent, parent = ((parent == null) ? null : parent.getParent())) {}
        return (Applet)parent2;
    }
    
    public static AppletContext getAppletContext(final Applet applet) {
        if (applet != null) {
            try {
                return applet.getAppletContext();
            }
            catch (Exception ex) {}
        }
        return null;
    }
    
    static {
        LOCK = new Object();
        BaseComponent.images = new Hashtable(5);
    }
}
