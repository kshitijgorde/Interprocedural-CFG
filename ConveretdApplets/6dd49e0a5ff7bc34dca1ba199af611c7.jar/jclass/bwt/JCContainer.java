// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import java.awt.Event;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.applet.AppletContext;
import java.awt.Graphics;
import java.awt.Canvas;
import jclass.util.JCUtilConverter;
import jclass.util.JCFile;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Insets;
import jclass.util.JCConverter;
import java.awt.Image;
import java.applet.Applet;
import java.awt.Panel;

public class JCContainer extends Panel
{
    public static final String version;
    protected transient Applet applet;
    protected transient Image dblbuffer_image;
    static JCConverter conv;
    int pref_width;
    int pref_height;
    Object userdata;
    Insets insets;
    transient boolean in_pref_size;
    boolean double_buffer;
    transient Rectangle paint_rect;
    
    public JCContainer() {
        this(null, null);
    }
    
    public JCContainer(final Applet applet, final String name) {
        this.pref_width = -999;
        this.pref_height = -999;
        this.in_pref_size = false;
        this.double_buffer = false;
        this.applet = applet;
        this.setName(name);
        if (this.getClass().getName().equals("jclass.bwt.JCContainer")) {
            this.getParameters(applet);
        }
    }
    
    String getParam(final String s) {
        return JCContainer.conv.getParam(this.applet, this, this.getName(), s);
    }
    
    protected void getParameters() {
        ContainerConverter.getParams(this);
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
        JCContainer.conv = conv;
    }
    
    public static JCConverter getConverter() {
        return JCContainer.conv;
    }
    
    public Object getUserData() {
        return this.userdata;
    }
    
    public void setUserData(final Object userdata) {
        this.userdata = userdata;
    }
    
    public Component add(final Component component) {
        return (component.getParent() != this) ? super.add(component) : component;
    }
    
    public synchronized void enable() {
        if (this.isEnabled()) {
            return;
        }
        super.enable();
        for (int i = 0; i < this.countComponents(); ++i) {
            this.getComponents()[i].enable();
        }
    }
    
    public synchronized void disable() {
        if (!this.isEnabled()) {
            return;
        }
        super.disable();
        for (int i = 0; i < this.countComponents(); ++i) {
            this.getComponents()[i].disable();
        }
    }
    
    public synchronized void repaint() {
        if (this.getPeer() == null || !this.isShowing()) {
            return;
        }
        this.paint(this.getGraphics());
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            if (components[i] instanceof JCContainer || components[i] instanceof Canvas) {
                components[i].repaint();
            }
        }
    }
    
    public synchronized void repaint(final Rectangle rectangle) {
        this.repaint(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public synchronized void repaint(final int n, final int n2, int n3, int n4) {
        if (this.getPeer() == null || !this.isShowing() || n3 <= 0 || n4 <= 0) {
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
        graphics.clipRect(n, n2, n3, n4);
        this.paint(graphics);
        graphics.dispose();
    }
    
    public void updateParent() {
        if (this.getParent() != null) {
            this.invalidate();
            this.getParent().invalidate();
            this.getParent().validate();
        }
    }
    
    public void validate() {
        if (this.isValid() || this.getPeer() == null) {
            return;
        }
        synchronized (this.getTreeLock()) {
            this.validateTree();
        }
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
        this.enable11Events(16L);
        if (!this.isEnabled()) {
            for (int i = 0; i < this.countComponents(); ++i) {
                this.getComponents()[i].disable();
            }
        }
    }
    
    public AppletContext getAppletContext() {
        return BWTUtil.getAppletContext(this.applet);
    }
    
    public Dimension minimumSize() {
        return this.preferredSize();
    }
    
    public void setInsets(final Insets insets) {
        this.insets = insets;
    }
    
    public Insets insets() {
        return (this.insets != null) ? this.insets : super.insets();
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
        if (this.in_pref_size) {
            return super.preferredSize();
        }
        this.in_pref_size = true;
        final int n = (this.pref_width != -999) ? this.pref_width : this.preferredWidth();
        final int n2 = (n < 0) ? Math.max(super.preferredSize().width, 20) : (n + this.insets().left + this.insets().right);
        final int n3 = (this.pref_height != -999) ? this.pref_height : this.preferredHeight();
        final int n4 = (n3 < 0) ? Math.max(super.preferredSize().height, 20) : (n3 + this.insets().top + this.insets().bottom);
        this.in_pref_size = false;
        return new Dimension(n2, n4);
    }
    
    public synchronized void reshape(final int n, final int n2, final int n3, final int n4) {
        if (n == this.location().x && n2 == this.location().y && n3 == this.size().width && n4 == this.size().height) {
            return;
        }
        if (n3 < 0 || n4 < 0) {
            return;
        }
        super.reshape(n, n2, n3, n4);
        this.invalidate();
        this.validate();
    }
    
    public void setCursor(final int n) {
        BWTUtil.setCursor(this, n);
    }
    
    public Component getComponent(final int n) {
        final Component[] components = this.getComponents();
        return (n < components.length) ? components[n] : null;
    }
    
    public int getComponent(final Component component) {
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            if (components[i] == component) {
                return i;
            }
        }
        return -999;
    }
    
    protected synchronized Graphics getDoubleBufferGraphics() {
        this.dblbuffer_image = BWTUtil.createImage(this, this.size().width, this.size().height);
        return (this.dblbuffer_image != null) ? this.dblbuffer_image.getGraphics() : null;
    }
    
    public synchronized void paintInterior(final Graphics graphics) {
    }
    
    protected void fillBackground(final Graphics graphics) {
        graphics.fillRect(0, 0, this.size().width, this.size().height);
    }
    
    public Rectangle getPaintRect() {
        return this.paint_rect;
    }
    
    public void paint(Graphics graphics) {
        if (graphics == null || this.getBackground() == null || this.getParent() == null) {
            return;
        }
        final Graphics graphics2 = graphics;
        Rectangle clipRect = graphics.getClipRect();
        if (clipRect == null) {
            clipRect = new Rectangle(this.size());
        }
        if (clipRect.width == 0 || clipRect.height == 0) {
            return;
        }
        this.paint_rect = clipRect;
        if (this.double_buffer) {
            final Graphics doubleBufferGraphics = this.getDoubleBufferGraphics();
            if (doubleBufferGraphics == null) {
                this.double_buffer = false;
            }
            else {
                graphics = doubleBufferGraphics;
                if (clipRect != null) {
                    graphics.clipRect(clipRect.x, clipRect.y, clipRect.width, clipRect.height);
                }
            }
        }
        graphics.setColor(this.getBackground());
        this.fillBackground(graphics);
        if (!graphics.getColor().equals(this.getBackground())) {
            graphics.setColor(this.getBackground());
        }
        this.paintInterior(graphics);
        if (this.double_buffer) {
            graphics.dispose();
            graphics2.drawImage(this.dblbuffer_image, 0, 0, null);
        }
    }
    
    public void printAll(final Graphics graphics) {
        final boolean double_buffer = this.double_buffer;
        this.double_buffer = false;
        super.printAll(graphics);
        this.double_buffer = double_buffer;
    }
    
    protected void processMouseEvent(final MouseEvent mouseEvent) {
        final Event event = new Event(this, mouseEvent.getWhen(), mouseEvent.getID(), mouseEvent.getX(), mouseEvent.getY(), 0, mouseEvent.getModifiers());
        event.clickCount = mouseEvent.getClickCount();
        switch (event.id) {
            case 501: {
                this.mouseDown(event, event.x, event.y);
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
    
    static {
        version = JCVersion.getVersionString();
        JCContainer.conv = new JCConverter();
    }
}
