// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import java.awt.event.ContainerEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.Event;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.applet.AppletContext;
import java.awt.Graphics;
import java.awt.Canvas;
import java.awt.Color;
import jclass.util.JCEnvironment;
import java.awt.event.KeyListener;
import java.awt.event.FocusListener;
import java.awt.event.ContainerListener;
import java.awt.Container;
import jclass.base.BaseComponent;
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
    public static final String about = "JClass BWT by KL Group (www.klg.com)";
    public static final String version;
    protected transient Applet applet;
    protected transient Image dblbuffer_image;
    protected JCButton action_button;
    protected JCButton current_action_button;
    static JCConverter conv;
    int pref_width;
    int pref_height;
    Object userdata;
    Insets insets;
    transient boolean in_pref_size;
    boolean double_buffer;
    transient Rectangle paint_rect;
    private Listener listener;
    private boolean listeners_installed;
    
    public JCContainer() {
        this(null, null);
    }
    
    public JCContainer(final Applet applet, final String name) {
        this.pref_width = -999;
        this.pref_height = -999;
        this.in_pref_size = false;
        this.double_buffer = false;
        this.listeners_installed = false;
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
    
    public static void useSystemColors(final boolean b) {
        BaseComponent.useSystemColors(b);
    }
    
    public String getAbout() {
        return "JClass BWT by KL Group (www.klg.com)";
    }
    
    public void setAbout(final String s) {
    }
    
    public String getVersion() {
        return JCContainer.version;
    }
    
    public void setVersion(final String s) {
    }
    
    public Object getUserData() {
        return this.userdata;
    }
    
    public void setUserData(final Object userdata) {
        this.userdata = userdata;
    }
    
    public JCButton getActionButton() {
        return this.action_button;
    }
    
    private void installListenerOnComponent(final Component component) {
        if (component instanceof Container) {
            ((Container)component).addContainerListener(this.listener);
            this.installListener((Container)component);
        }
        component.addFocusListener(this.listener);
        if (!(component instanceof JCButton)) {
            component.addKeyListener(this.listener);
        }
    }
    
    private void installListener(final Container container) {
        final Component[] components = container.getComponents();
        for (int i = 0; i < components.length; ++i) {
            this.installListenerOnComponent(components[i]);
        }
    }
    
    private void installListeners() {
        if (this.listener == null) {
            this.listener = new Listener();
        }
        this.installListener(this);
    }
    
    public void setActionButton(final JCButton action_button) {
        this.action_button = action_button;
        if (!this.listeners_installed) {
            this.installListeners();
        }
        if (this.current_action_button != this.action_button) {
            if (this.current_action_button != null) {
                this.current_action_button.setIsActionButton(false);
            }
            this.current_action_button = this.action_button;
            if (this.current_action_button != null) {
                this.current_action_button.setIsActionButton(true);
            }
        }
    }
    
    public Component add(final Component component) {
        if (component.getParent() != this) {
            return super.add(component);
        }
        return component;
    }
    
    protected void addImpl(final Component component, final Object o, final int n) {
        if (component.getParent() != this) {
            super.addImpl(component, o, n);
        }
    }
    
    public void enable() {
        if (this.isEnabled()) {
            return;
        }
        super.enable();
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            components[i].enable();
        }
    }
    
    public void disable() {
        if (!this.isEnabled()) {
            return;
        }
        super.disable();
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            components[i].disable();
        }
    }
    
    public boolean isShowing() {
        return JCEnvironment.isJavaOS() || super.isShowing();
    }
    
    public void setBackground(final Color background) {
        super.setBackground(background);
        this.repaint();
    }
    
    public void setForeground(final Color foreground) {
        super.setForeground(foreground);
        this.repaint();
    }
    
    public void repaint() {
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
    
    public void repaint(final Rectangle rectangle) {
        this.repaint(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public void repaint(final int n, final int n2, int n3, int n4) {
        Graphics graphics = null;
        synchronized (this) {
            if (this.getPeer() == null || !this.isShowing() || n3 <= 0 || n4 <= 0) {
                // monitorexit(this)
                return;
            }
            final int width = this.size().width;
            final int height = this.size().height;
            if (width <= n || height <= n2 || n + n3 < 0 || n2 + n4 < 0) {
                // monitorexit(this)
                return;
            }
            if (n + n3 > width) {
                n3 = width - n;
            }
            if (n2 + n4 > height) {
                n4 = height - n2;
            }
            graphics = this.getGraphics();
            graphics.clipRect(n, n2, n3, n4);
        }
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
        // monitorexit(this.getTreeLock())
    }
    
    public void addNotify() {
        if (this.getPeer() == null) {
            super.addNotify();
        }
        if (this.applet == null) {
            this.applet = BWTUtil.getApplet(this);
        }
        this.enableEvents(16L);
        if (!this.isEnabled()) {
            final Component[] components = this.getComponents();
            for (int i = 0; i < components.length; ++i) {
                components[i].disable();
            }
        }
    }
    
    public AppletContext getAppletContext() {
        return BWTUtil.getAppletContext(this.applet);
    }
    
    public void setInsets(final Insets insets) {
        this.insets = insets;
    }
    
    public Insets insets() {
        if (this.insets != null) {
            return this.insets;
        }
        return super.insets();
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
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        synchronized (this) {
            if (n == this.location().x && n2 == this.location().y && n3 == this.size().width && n4 == this.size().height) {
                // monitorexit(this)
                return;
            }
            if (n3 < 0 || n4 < 0) {
                // monitorexit(this)
                return;
            }
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
        if (n < components.length) {
            return components[n];
        }
        return null;
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
        if (this.dblbuffer_image != null) {
            return this.dblbuffer_image.getGraphics();
        }
        return null;
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
        if (graphics2 != null) {
            super.paint(graphics2);
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
        version = JCComponent.version;
        JCContainer.conv = new JCConverter();
    }
    
    class Listener implements FocusListener, KeyListener, ContainerListener
    {
        private void invokeActionListeners(final KeyEvent keyEvent) {
            if (JCContainer.this.current_action_button == null) {
                return;
            }
            final JCActionEvent jcActionEvent = new JCActionEvent(JCContainer.this.current_action_button, keyEvent.getID(), JCContainer.this.current_action_button.getActionCommand(), keyEvent.getModifiers());
            for (int i = 0; i < JCContainer.this.current_action_button.actionListeners.size(); ++i) {
                ((JCActionListener)JCContainer.this.current_action_button.actionListeners.elementAt(i)).actionPerformed(jcActionEvent);
            }
        }
        
        public void keyTyped(final KeyEvent keyEvent) {
        }
        
        public void keyPressed(final KeyEvent keyEvent) {
            if (((Component)keyEvent.getSource()) instanceof JCButton) {
                return;
            }
            if (keyEvent.getKeyCode() == 10) {
                this.invokeActionListeners(keyEvent);
            }
        }
        
        public void keyReleased(final KeyEvent keyEvent) {
        }
        
        public void focusGained(final FocusEvent focusEvent) {
            final Component component = (Component)focusEvent.getSource();
            if (component instanceof JCButton) {
                if (component != JCContainer.this.current_action_button) {
                    if (JCContainer.this.current_action_button != null) {
                        JCContainer.this.current_action_button.setIsActionButton(false);
                    }
                    (JCContainer.this.current_action_button = (JCButton)component).setIsActionButton(true);
                }
            }
            else if (JCContainer.this.current_action_button != JCContainer.this.action_button) {
                if (JCContainer.this.current_action_button != null) {
                    JCContainer.this.current_action_button.setIsActionButton(false);
                }
                JCContainer.this.current_action_button = JCContainer.this.action_button;
                if (JCContainer.this.current_action_button != null) {
                    JCContainer.this.current_action_button.setIsActionButton(true);
                }
            }
        }
        
        public void focusLost(final FocusEvent focusEvent) {
        }
        
        public void componentAdded(final ContainerEvent containerEvent) {
            JCContainer.this.installListenerOnComponent(containerEvent.getChild());
        }
        
        public void componentRemoved(final ContainerEvent containerEvent) {
        }
    }
}
