// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.awt.Dimension;
import javax.swing.plaf.DesktopIconUI;
import javax.accessibility.AccessibleRole;
import javax.accessibility.AccessibleValue;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.MenuComponent;
import javax.swing.plaf.ComponentUI;
import java.awt.Graphics;
import javax.swing.plaf.InternalFrameUI;
import java.awt.Container;
import javax.accessibility.AccessibleContext;
import javax.swing.event.InternalFrameEvent;
import java.beans.PropertyVetoException;
import java.util.EventListener;
import javax.swing.event.InternalFrameListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.accessibility.Accessible;

public class JInternalFrame extends JComponent implements Accessible, WindowConstants, RootPaneContainer
{
    private static final String uiClassID = "InternalFrameUI";
    protected JRootPane rootPane;
    protected boolean rootPaneCheckingEnabled;
    protected boolean closable;
    protected boolean isClosed;
    protected boolean maximizable;
    protected boolean isMaximum;
    protected boolean iconable;
    protected boolean isIcon;
    protected boolean resizable;
    protected boolean isSelected;
    protected Icon frameIcon;
    protected String title;
    protected JDesktopIcon desktopIcon;
    private boolean opened;
    private int defaultCloseOperation;
    public static final String CONTENT_PANE_PROPERTY = "contentPane";
    public static final String MENU_BAR_PROPERTY = "menuBar";
    public static final String TITLE_PROPERTY = "title";
    public static final String LAYERED_PANE_PROPERTY = "layeredPane";
    public static final String ROOT_PANE_PROPERTY = "rootPane";
    public static final String GLASS_PANE_PROPERTY = "glassPane";
    public static final String FRAME_ICON_PROPERTY = "frameIcon";
    public static final String IS_SELECTED_PROPERTY = "selected";
    public static final String IS_CLOSED_PROPERTY = "closed";
    public static final String IS_MAXIMUM_PROPERTY = "maximum";
    public static final String IS_ICON_PROPERTY = "icon";
    boolean isDragging;
    boolean danger;
    static /* synthetic */ Class class$javax$swing$event$InternalFrameListener;
    
    public JInternalFrame() {
        this("", false, false, false, false);
    }
    
    public JInternalFrame(final String s) {
        this(s, false, false, false, false);
    }
    
    public JInternalFrame(final String s, final boolean b) {
        this(s, b, false, false, false);
    }
    
    public JInternalFrame(final String s, final boolean b, final boolean b2) {
        this(s, b, b2, false, false);
    }
    
    public JInternalFrame(final String s, final boolean b, final boolean b2, final boolean b3) {
        this(s, b, b2, b3, false);
    }
    
    public JInternalFrame(final String title, final boolean resizable, final boolean closable, final boolean maximizable, final boolean iconable) {
        this.rootPaneCheckingEnabled = false;
        this.defaultCloseOperation = 1;
        this.isDragging = false;
        this.danger = false;
        this.setRootPane(this.createRootPane());
        this.setLayout(new BorderLayout());
        this.title = title;
        this.resizable = resizable;
        this.closable = closable;
        this.maximizable = maximizable;
        this.isMaximum = false;
        this.iconable = iconable;
        this.isIcon = false;
        this.setRootPaneCheckingEnabled(true);
        this.desktopIcon = new JDesktopIcon(this);
        this.updateUI();
    }
    
    protected void addImpl(final Component component, final Object o, final int n) {
        if (this.isRootPaneCheckingEnabled()) {
            throw this.createRootPaneException("add");
        }
        super.addImpl(component, o, n);
    }
    
    public void addInternalFrameListener(final InternalFrameListener internalFrameListener) {
        super.listenerList.add((JInternalFrame.class$javax$swing$event$InternalFrameListener != null) ? JInternalFrame.class$javax$swing$event$InternalFrameListener : (JInternalFrame.class$javax$swing$event$InternalFrameListener = class$("javax.swing.event.InternalFrameListener")), internalFrameListener);
        this.enableEvents(0L);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    protected JRootPane createRootPane() {
        return new JRootPane();
    }
    
    private Error createRootPaneException(final String s) {
        final String name = this.getClass().getName();
        return new Error("Do not use " + name + "." + s + "() use " + name + ".getContentPane()." + s + "() instead");
    }
    
    public void dispose() {
        if (this.isVisible()) {
            this.setVisible(false);
        }
        if (this.isSelected()) {
            try {
                this.setSelected(false);
            }
            catch (PropertyVetoException ex) {}
        }
        if (!this.isClosed) {
            this.fireInternalFrameEvent(25551);
        }
    }
    
    private void doDefaultCloseAction() {
        switch (this.defaultCloseOperation) {
            case 1: {
                try {
                    this.setClosed(true);
                }
                catch (PropertyVetoException ex) {}
                break;
            }
            case 2: {
                try {
                    this.setClosed(true);
                    this.dispose();
                }
                catch (PropertyVetoException ex2) {}
                break;
            }
            case 3: {
                System.exit(0);
                break;
            }
        }
    }
    
    protected void fireInternalFrameEvent(final int n) {
        final Object[] listenerList = super.listenerList.getListenerList();
        InternalFrameEvent internalFrameEvent = null;
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((JInternalFrame.class$javax$swing$event$InternalFrameListener != null) ? JInternalFrame.class$javax$swing$event$InternalFrameListener : (JInternalFrame.class$javax$swing$event$InternalFrameListener = class$("javax.swing.event.InternalFrameListener")))) {
                if (internalFrameEvent == null) {
                    internalFrameEvent = new InternalFrameEvent(this, n);
                }
                switch (internalFrameEvent.getID()) {
                    case 25549: {
                        ((InternalFrameListener)listenerList[i + 1]).internalFrameOpened(internalFrameEvent);
                        break;
                    }
                    case 25550: {
                        ((InternalFrameListener)listenerList[i + 1]).internalFrameClosing(internalFrameEvent);
                        break;
                    }
                    case 25551: {
                        ((InternalFrameListener)listenerList[i + 1]).internalFrameClosed(internalFrameEvent);
                        break;
                    }
                    case 25552: {
                        ((InternalFrameListener)listenerList[i + 1]).internalFrameIconified(internalFrameEvent);
                        break;
                    }
                    case 25553: {
                        ((InternalFrameListener)listenerList[i + 1]).internalFrameDeiconified(internalFrameEvent);
                        break;
                    }
                    case 25554: {
                        ((InternalFrameListener)listenerList[i + 1]).internalFrameActivated(internalFrameEvent);
                        break;
                    }
                    case 25555: {
                        ((InternalFrameListener)listenerList[i + 1]).internalFrameDeactivated(internalFrameEvent);
                        break;
                    }
                }
            }
        }
        if (n == 25550) {
            this.doDefaultCloseAction();
        }
    }
    
    public AccessibleContext getAccessibleContext() {
        if (super.accessibleContext == null) {
            super.accessibleContext = new AccessibleJInternalFrame();
        }
        return super.accessibleContext;
    }
    
    public Container getContentPane() {
        return this.getRootPane().getContentPane();
    }
    
    public int getDefaultCloseOperation() {
        return this.defaultCloseOperation;
    }
    
    public JDesktopIcon getDesktopIcon() {
        return this.desktopIcon;
    }
    
    public JDesktopPane getDesktopPane() {
        Container container;
        for (container = this.getParent(); container != null && !(container instanceof JDesktopPane); container = container.getParent()) {}
        if (container == null) {
            for (container = this.getDesktopIcon().getParent(); container != null && !(container instanceof JDesktopPane); container = container.getParent()) {}
        }
        return (JDesktopPane)container;
    }
    
    public Icon getFrameIcon() {
        return this.frameIcon;
    }
    
    public Component getGlassPane() {
        return this.getRootPane().getGlassPane();
    }
    
    public JMenuBar getJMenuBar() {
        return this.getRootPane().getJMenuBar();
    }
    
    public int getLayer() {
        return JLayeredPane.getLayer(this);
    }
    
    public JLayeredPane getLayeredPane() {
        return this.getRootPane().getLayeredPane();
    }
    
    public JMenuBar getMenuBar() {
        return this.getRootPane().getMenuBar();
    }
    
    public JRootPane getRootPane() {
        return this.rootPane;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public InternalFrameUI getUI() {
        return (InternalFrameUI)super.ui;
    }
    
    public String getUIClassID() {
        return "InternalFrameUI";
    }
    
    public final String getWarningString() {
        return null;
    }
    
    public boolean isClosable() {
        return this.closable;
    }
    
    public boolean isClosed() {
        return this.isClosed;
    }
    
    public boolean isIcon() {
        return this.isIcon;
    }
    
    public boolean isIconifiable() {
        return this.iconable;
    }
    
    public boolean isMaximizable() {
        return this.maximizable;
    }
    
    public boolean isMaximum() {
        return this.isMaximum;
    }
    
    public boolean isResizable() {
        return !this.isMaximum && this.resizable;
    }
    
    protected boolean isRootPaneCheckingEnabled() {
        return this.rootPaneCheckingEnabled;
    }
    
    public boolean isSelected() {
        return this.isSelected;
    }
    
    public void moveToBack() {
        if (this.getParent() != null && this.getParent() instanceof JLayeredPane) {
            ((JLayeredPane)this.getParent()).moveToBack(this);
        }
    }
    
    public void moveToFront() {
        if (this.getParent() != null && this.getParent() instanceof JLayeredPane) {
            ((JLayeredPane)this.getParent()).moveToFront(this);
        }
    }
    
    public void pack() {
        final Container parent = this.getParent();
        if (parent != null && parent.getPeer() == null) {
            parent.addNotify();
            this.addNotify();
        }
        this.setSize(this.getPreferredSize());
        this.validate();
    }
    
    protected void paintComponent(final Graphics graphics) {
        if (this.isDragging) {
            this.danger = true;
        }
        super.paintComponent(graphics);
    }
    
    protected String paramString() {
        final String s = (this.rootPane != null) ? this.rootPane.toString() : "";
        final String s2 = this.rootPaneCheckingEnabled ? "true" : "false";
        final String s3 = this.closable ? "true" : "false";
        final String s4 = this.isClosed ? "true" : "false";
        final String s5 = this.maximizable ? "true" : "false";
        final String s6 = this.isMaximum ? "true" : "false";
        final String s7 = this.iconable ? "true" : "false";
        final String s8 = this.isIcon ? "true" : "false";
        final String s9 = this.resizable ? "true" : "false";
        final String s10 = this.isSelected ? "true" : "false";
        final String s11 = (this.frameIcon != null) ? this.frameIcon.toString() : "";
        final String s12 = (this.title != null) ? this.title : "";
        final String s13 = (this.desktopIcon != null) ? this.desktopIcon.toString() : "";
        final String s14 = this.opened ? "true" : "false";
        String s15;
        if (this.defaultCloseOperation == 1) {
            s15 = "HIDE_ON_CLOSE";
        }
        else if (this.defaultCloseOperation == 2) {
            s15 = "DISPOSE_ON_CLOSE";
        }
        else if (this.defaultCloseOperation == 0) {
            s15 = "DO_NOTHING_ON_CLOSE";
        }
        else if (this.defaultCloseOperation == 3) {
            s15 = "EXIT_ON_CLOSE";
        }
        else {
            s15 = "";
        }
        return String.valueOf(super.paramString()) + ",closable=" + s3 + ",defaultCloseOperation=" + s15 + ",desktopIcon=" + s13 + ",frameIcon=" + s11 + ",iconable=" + s7 + ",isClosed=" + s4 + ",isIcon=" + s8 + ",isMaximum=" + s6 + ",isSelected=" + s10 + ",maximizable=" + s5 + ",opened=" + s14 + ",resizable=" + s9 + ",rootPane=" + s + ",rootPaneCheckingEnabled=" + s2 + ",title=" + s12;
    }
    
    public void remove(final Component component) {
        final int componentCount = this.getComponentCount();
        super.remove(component);
        if (componentCount == this.getComponentCount()) {
            this.getContentPane().remove(component);
        }
    }
    
    public void removeInternalFrameListener(final InternalFrameListener internalFrameListener) {
        super.listenerList.remove((JInternalFrame.class$javax$swing$event$InternalFrameListener != null) ? JInternalFrame.class$javax$swing$event$InternalFrameListener : (JInternalFrame.class$javax$swing$event$InternalFrameListener = class$("javax.swing.event.InternalFrameListener")), internalFrameListener);
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        super.reshape(n, n2, n3, n4);
        this.validate();
        this.repaint();
    }
    
    public void setClosable(final boolean closable) {
        final Boolean b = this.closable ? Boolean.TRUE : Boolean.FALSE;
        final Boolean b2 = closable ? Boolean.TRUE : Boolean.FALSE;
        this.closable = closable;
        this.firePropertyChange("closable", b, b2);
    }
    
    public void setClosed(final boolean isClosed) throws PropertyVetoException {
        if (this.isClosed == isClosed) {
            return;
        }
        final Boolean b = this.isClosed ? Boolean.TRUE : Boolean.FALSE;
        final Boolean b2 = isClosed ? Boolean.TRUE : Boolean.FALSE;
        this.fireVetoableChange("closed", b, b2);
        this.isClosed = isClosed;
        if (this.isClosed) {
            this.fireInternalFrameEvent(25550);
            this.fireInternalFrameEvent(25551);
            this.opened = false;
        }
        else if (!this.opened) {
            this.fireInternalFrameEvent(25549);
            this.opened = true;
        }
        this.firePropertyChange("closed", b, b2);
    }
    
    public void setContentPane(final Container contentPane) {
        final Container contentPane2 = this.getContentPane();
        this.getRootPane().setContentPane(contentPane);
        this.firePropertyChange("contentPane", contentPane2, contentPane);
    }
    
    public void setDefaultCloseOperation(final int defaultCloseOperation) {
        this.defaultCloseOperation = defaultCloseOperation;
    }
    
    public void setDesktopIcon(final JDesktopIcon desktopIcon) {
        this.firePropertyChange("desktopIcon", this.getDesktopIcon(), this.desktopIcon = desktopIcon);
    }
    
    public void setFrameIcon(final Icon frameIcon) {
        this.firePropertyChange("frameIcon", this.frameIcon, this.frameIcon = frameIcon);
    }
    
    public void setGlassPane(final Component glassPane) {
        final Component glassPane2 = this.getGlassPane();
        this.getRootPane().setGlassPane(glassPane);
        this.firePropertyChange("glassPane", glassPane2, glassPane);
    }
    
    public void setIcon(final boolean isIcon) throws PropertyVetoException {
        if (this.isIcon == isIcon) {
            return;
        }
        final Boolean b = this.isIcon ? Boolean.TRUE : Boolean.FALSE;
        final Boolean b2 = isIcon ? Boolean.TRUE : Boolean.FALSE;
        this.fireVetoableChange("icon", b, b2);
        this.isIcon = isIcon;
        this.firePropertyChange("icon", b, b2);
        if (isIcon) {
            this.fireInternalFrameEvent(25552);
        }
        else {
            this.fireInternalFrameEvent(25553);
        }
    }
    
    public void setIconifiable(final boolean iconable) {
        this.iconable = iconable;
    }
    
    public void setJMenuBar(final JMenuBar jMenuBar) {
        final JMenuBar menuBar = this.getMenuBar();
        this.getRootPane().setJMenuBar(jMenuBar);
        this.firePropertyChange("menuBar", menuBar, jMenuBar);
    }
    
    public void setLayer(final Integer n) {
        if (this.getParent() != null && this.getParent() instanceof JLayeredPane) {
            final JLayeredPane layeredPane = (JLayeredPane)this.getParent();
            layeredPane.setLayer(this, n, layeredPane.getPosition(this));
        }
        else {
            JLayeredPane.putLayer(this, n);
            if (this.getParent() != null) {
                this.getParent().repaint(super._bounds.x, super._bounds.y, super._bounds.width, super._bounds.height);
            }
        }
    }
    
    public void setLayeredPane(final JLayeredPane layeredPane) {
        final JLayeredPane layeredPane2 = this.getLayeredPane();
        this.getRootPane().setLayeredPane(layeredPane);
        this.firePropertyChange("layeredPane", layeredPane2, layeredPane);
    }
    
    public void setLayout(final LayoutManager layout) {
        if (this.isRootPaneCheckingEnabled()) {
            throw this.createRootPaneException("setLayout");
        }
        super.setLayout(layout);
    }
    
    public void setMaximizable(final boolean maximizable) {
        final Boolean b = this.maximizable ? Boolean.TRUE : Boolean.FALSE;
        final Boolean b2 = maximizable ? Boolean.TRUE : Boolean.FALSE;
        this.maximizable = maximizable;
        this.firePropertyChange("maximizable", b, b2);
    }
    
    public void setMaximum(final boolean isMaximum) throws PropertyVetoException {
        if (this.isMaximum == isMaximum) {
            return;
        }
        final Boolean b = this.isMaximum ? Boolean.TRUE : Boolean.FALSE;
        final Boolean b2 = isMaximum ? Boolean.TRUE : Boolean.FALSE;
        this.fireVetoableChange("maximum", b, b2);
        this.isMaximum = isMaximum;
        this.firePropertyChange("maximum", b, b2);
    }
    
    public void setMenuBar(final JMenuBar jMenuBar) {
        final JMenuBar menuBar = this.getMenuBar();
        this.getRootPane().setJMenuBar(jMenuBar);
        this.firePropertyChange("menuBar", menuBar, jMenuBar);
    }
    
    public void setResizable(final boolean resizable) {
        final Boolean b = this.resizable ? Boolean.TRUE : Boolean.FALSE;
        final Boolean b2 = resizable ? Boolean.TRUE : Boolean.FALSE;
        this.resizable = resizable;
        this.firePropertyChange("resizable", b, b2);
    }
    
    protected void setRootPane(final JRootPane rootPane) {
        if (this.rootPane != null) {
            this.remove(this.rootPane);
        }
        final JRootPane rootPane2 = this.getRootPane();
        this.rootPane = rootPane;
        if (this.rootPane != null) {
            final boolean rootPaneCheckingEnabled = this.isRootPaneCheckingEnabled();
            try {
                this.setRootPaneCheckingEnabled(false);
                this.add(this.rootPane, "Center");
            }
            finally {
                this.setRootPaneCheckingEnabled(rootPaneCheckingEnabled);
            }
        }
        this.firePropertyChange("rootPane", rootPane2, rootPane);
    }
    
    protected void setRootPaneCheckingEnabled(final boolean rootPaneCheckingEnabled) {
        this.rootPaneCheckingEnabled = rootPaneCheckingEnabled;
    }
    
    public void setSelected(final boolean isSelected) throws PropertyVetoException {
        if (this.isSelected == isSelected) {
            return;
        }
        final Boolean b = this.isSelected ? Boolean.TRUE : Boolean.FALSE;
        final Boolean b2 = isSelected ? Boolean.TRUE : Boolean.FALSE;
        this.fireVetoableChange("selected", b, b2);
        this.isSelected = isSelected;
        this.firePropertyChange("selected", b, b2);
        if (this.isSelected) {
            this.fireInternalFrameEvent(25554);
        }
        else {
            this.fireInternalFrameEvent(25555);
        }
        this.repaint();
    }
    
    public void setTitle(final String title) {
        this.firePropertyChange("title", this.title, this.title = title);
    }
    
    public void setUI(final InternalFrameUI ui) {
        final boolean rootPaneCheckingEnabled = this.isRootPaneCheckingEnabled();
        try {
            this.setRootPaneCheckingEnabled(false);
            super.setUI(ui);
        }
        finally {
            this.setRootPaneCheckingEnabled(rootPaneCheckingEnabled);
        }
    }
    
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
    }
    
    public void show() {
        final Container parent = this.getParent();
        if (parent != null && parent.getPeer() == null) {
            parent.addNotify();
            this.addNotify();
        }
        this.validate();
        if (!this.opened) {
            this.fireInternalFrameEvent(25549);
            this.opened = true;
        }
        if (this.isVisible()) {
            this.toFront();
        }
        else {
            super.show();
        }
        if (!this.isSelected()) {
            try {
                this.setSelected(true);
            }
            catch (PropertyVetoException ex) {}
        }
    }
    
    synchronized void startModal() {
        if (this.isVisible() && !this.isShowing()) {
            for (Container container = this.getParent(); container != null; container = container.getParent()) {
                if (!container.isVisible()) {
                    container.setVisible(true);
                }
            }
        }
        try {
            if (SwingUtilities.isEventDispatchThread()) {
                final EventQueue systemEventQueue = this.getToolkit().getSystemEventQueue();
                while (this.isVisible()) {
                    final AWTEvent nextEvent = systemEventQueue.getNextEvent();
                    final Object source = nextEvent.getSource();
                    if (source instanceof Component) {
                        ((Component)source).dispatchEvent(nextEvent);
                    }
                    else if (source instanceof MenuComponent) {
                        ((MenuComponent)source).dispatchEvent(nextEvent);
                    }
                    else {
                        System.err.println("unable to dispatch event: " + nextEvent);
                    }
                }
            }
            else {
                while (this.isVisible()) {
                    this.wait();
                }
            }
        }
        catch (InterruptedException ex) {}
    }
    
    synchronized void stopModal() {
        this.notifyAll();
    }
    
    public void toBack() {
        this.moveToBack();
    }
    
    public void toFront() {
        this.moveToFront();
    }
    
    public void updateUI() {
        this.setUI((InternalFrameUI)UIManager.getUI(this));
        this.invalidate();
        if (this.desktopIcon != null) {
            this.desktopIcon.updateUIWhenHidden();
        }
    }
    
    void updateUIWhenHidden() {
        this.setUI((InternalFrameUI)UIManager.getUI(this));
        this.invalidate();
        final Component[] components = this.getComponents();
        if (components != null) {
            for (int i = 0; i < components.length; ++i) {
                SwingUtilities.updateComponentTreeUI(components[i]);
            }
        }
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        if (super.ui != null && this.getUIClassID().equals("InternalFrameUI")) {
            final boolean rootPaneCheckingEnabled = this.isRootPaneCheckingEnabled();
            try {
                this.setRootPaneCheckingEnabled(false);
                super.ui.installUI(this);
            }
            finally {
                this.setRootPaneCheckingEnabled(rootPaneCheckingEnabled);
            }
        }
    }
    
    protected class AccessibleJInternalFrame extends AccessibleJComponent implements AccessibleValue
    {
        public String getAccessibleName() {
            if (super.accessibleName != null) {
                return super.accessibleName;
            }
            return JInternalFrame.this.getTitle();
        }
        
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.INTERNAL_FRAME;
        }
        
        public AccessibleValue getAccessibleValue() {
            return this;
        }
        
        public Number getCurrentAccessibleValue() {
            return new Integer(JInternalFrame.this.getLayer());
        }
        
        public Number getMaximumAccessibleValue() {
            return new Integer(Integer.MAX_VALUE);
        }
        
        public Number getMinimumAccessibleValue() {
            return new Integer(Integer.MIN_VALUE);
        }
        
        public boolean setCurrentAccessibleValue(final Number n) {
            if (n instanceof Integer) {
                JInternalFrame.this.setLayer((Integer)n);
                return true;
            }
            return false;
        }
    }
    
    public static class JDesktopIcon extends JComponent implements Accessible
    {
        JInternalFrame internalFrame;
        
        public JDesktopIcon(final JInternalFrame internalFrame) {
            this.setInternalFrame(internalFrame);
            this.updateUI();
        }
        
        public AccessibleContext getAccessibleContext() {
            if (super.accessibleContext == null) {
                super.accessibleContext = new AccessibleJDesktopIcon();
            }
            return super.accessibleContext;
        }
        
        public JDesktopPane getDesktopPane() {
            if (this.getInternalFrame() != null) {
                return this.getInternalFrame().getDesktopPane();
            }
            return null;
        }
        
        public JInternalFrame getInternalFrame() {
            return this.internalFrame;
        }
        
        public DesktopIconUI getUI() {
            return (DesktopIconUI)super.ui;
        }
        
        public String getUIClassID() {
            return "DesktopIconUI";
        }
        
        public void setInternalFrame(final JInternalFrame internalFrame) {
            this.internalFrame = internalFrame;
        }
        
        public void setUI(final DesktopIconUI ui) {
            super.setUI(ui);
        }
        
        public void updateUI() {
            final boolean b = super.ui != null;
            this.setUI((DesktopIconUI)UIManager.getUI(this));
            this.invalidate();
            final Dimension preferredSize = this.getPreferredSize();
            this.setSize(preferredSize.width, preferredSize.height);
            if (this.internalFrame != null && this.internalFrame.getUI() != null) {
                SwingUtilities.updateComponentTreeUI(this.internalFrame);
            }
        }
        
        void updateUIWhenHidden() {
            this.setUI((DesktopIconUI)UIManager.getUI(this));
            this.invalidate();
            final Component[] components = this.getComponents();
            if (components != null) {
                for (int i = 0; i < components.length; ++i) {
                    SwingUtilities.updateComponentTreeUI(components[i]);
                }
            }
        }
        
        protected class AccessibleJDesktopIcon extends AccessibleJComponent implements AccessibleValue
        {
            public AccessibleRole getAccessibleRole() {
                return AccessibleRole.DESKTOP_ICON;
            }
            
            public AccessibleValue getAccessibleValue() {
                return this;
            }
            
            public Number getCurrentAccessibleValue() {
                final AccessibleValue accessibleValue = JDesktopIcon.this.getInternalFrame().getAccessibleContext().getAccessibleValue();
                if (accessibleValue != null) {
                    return accessibleValue.getCurrentAccessibleValue();
                }
                return null;
            }
            
            public Number getMaximumAccessibleValue() {
                final AccessibleContext accessibleContext = JDesktopIcon.this.getInternalFrame().getAccessibleContext();
                if (accessibleContext instanceof AccessibleValue) {
                    return ((AccessibleValue)accessibleContext).getMaximumAccessibleValue();
                }
                return null;
            }
            
            public Number getMinimumAccessibleValue() {
                final AccessibleContext accessibleContext = JDesktopIcon.this.getInternalFrame().getAccessibleContext();
                if (accessibleContext instanceof AccessibleValue) {
                    return ((AccessibleValue)accessibleContext).getMinimumAccessibleValue();
                }
                return null;
            }
            
            public boolean setCurrentAccessibleValue(final Number currentAccessibleValue) {
                final AccessibleValue accessibleValue = JDesktopIcon.this.getInternalFrame().getAccessibleContext().getAccessibleValue();
                return accessibleValue != null && accessibleValue.setCurrentAccessibleValue(currentAccessibleValue);
            }
        }
    }
}
