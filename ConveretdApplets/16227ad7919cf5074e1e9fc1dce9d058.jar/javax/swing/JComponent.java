// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.io.InvalidObjectException;
import java.io.ObjectInputValidation;
import java.awt.event.ContainerEvent;
import java.util.Locale;
import java.awt.FontMetrics;
import java.awt.Cursor;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.accessibility.AccessibleStateSet;
import javax.accessibility.AccessibleRole;
import javax.accessibility.Accessible;
import java.awt.event.ContainerListener;
import javax.accessibility.AccessibleComponent;
import java.io.ObjectOutputStream;
import javax.accessibility.AccessibleState;
import java.awt.Font;
import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.peer.LightweightPeer;
import java.awt.event.MouseEvent;
import java.awt.Point;
import javax.swing.border.AbstractBorder;
import java.awt.Insets;
import java.util.Dictionary;
import java.beans.PropertyVetoException;
import java.awt.event.FocusListener;
import java.awt.event.ActionListener;
import java.beans.VetoableChangeListener;
import java.beans.PropertyChangeListener;
import java.util.Enumeration;
import javax.swing.event.AncestorListener;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.applet.Applet;
import java.awt.Window;
import java.util.Vector;
import javax.swing.event.SwingPropertyChangeSupport;
import javax.accessibility.AccessibleContext;
import java.awt.Component;
import javax.swing.border.Border;
import java.beans.VetoableChangeSupport;
import javax.swing.event.EventListenerList;
import javax.swing.plaf.ComponentUI;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.util.Hashtable;
import java.io.Serializable;
import java.awt.Container;

public abstract class JComponent extends Container implements Serializable
{
    private static final String uiClassID = "ComponentUI";
    private static final Hashtable readObjectCallbacks;
    private Dimension preferredSize;
    private Dimension minimumSize;
    private Dimension maximumSize;
    private Float alignmentX;
    private Float alignmentY;
    private AncestorNotifier ancestorNotifier;
    Rectangle _bounds;
    protected transient ComponentUI ui;
    protected EventListenerList listenerList;
    private Hashtable clientProperties;
    private VetoableChangeSupport vetoableChangeSupport;
    private Autoscroller autoscroller;
    private Border border;
    private int flags;
    private transient Rectangle tmpRect;
    transient Component paintingChild;
    public static final int WHEN_FOCUSED = 0;
    public static final int WHEN_ANCESTOR_OF_FOCUSED_COMPONENT = 1;
    public static final int WHEN_IN_FOCUSED_WINDOW = 2;
    public static final int UNDEFINED_CONDITION = -1;
    private static final String KEYBOARD_BINDINGS_KEY = "_KeyboardBindings";
    public static final String TOOL_TIP_TEXT_KEY = "ToolTipText";
    private static final String NEXT_FOCUS = "nextFocus";
    private static final int REQUEST_FOCUS_DISABLED = 0;
    private static final int IS_DOUBLE_BUFFERED = 1;
    private static final int ANCESTOR_USING_BUFFER = 2;
    private static final int IS_PAINTING_TILE = 3;
    private static final int HAS_FOCUS = 4;
    private static final int IS_OPAQUE = 5;
    protected AccessibleContext accessibleContext;
    private SwingPropertyChangeSupport changeSupport;
    private Rectangle paintImmediatelyClip;
    static /* synthetic */ Class class$javax$swing$JComponent$KeyboardState;
    
    static {
        readObjectCallbacks = new Hashtable(1);
    }
    
    public JComponent() {
        this._bounds = new Rectangle();
        this.listenerList = new EventListenerList();
        this.accessibleContext = null;
        this.paintImmediatelyClip = new Rectangle(0, 0, 0, 0);
        this.enableEvents(4L);
        this.enableSerialization();
    }
    
    void _paintImmediately(final int x, final int y, final int width, final int height) {
        int n = 0;
        int n2 = 0;
        boolean b = false;
        Component component = null;
        JComponent component2 = this;
        final RepaintManager currentManager = RepaintManager.currentManager(this);
        final Vector vector = new Vector<JComponent>(7);
        int n3 = -1;
        int n4 = 0;
        final int n5 = 0;
        this.paintImmediatelyClip.x = x;
        this.paintImmediatelyClip.y = y;
        this.paintImmediatelyClip.width = width;
        this.paintImmediatelyClip.height = height;
        final boolean b2 = this.alwaysOnTop() && this.isOpaque();
        Container parent;
        for (parent = this; parent != null && !(parent instanceof Window) && !(parent instanceof Applet); parent = parent.getParent()) {
            vector.addElement((JComponent)parent);
            if (!b2 && parent instanceof JComponent && !((JComponent)parent).isOptimizedDrawingEnabled()) {
                component2 = (JComponent)parent;
                n3 = n4;
                n2 = (n = 0);
                b = false;
            }
            ++n4;
            if (currentManager.isDoubleBufferingEnabled() && parent instanceof JComponent && ((JComponent)parent).isDoubleBuffered()) {
                b = true;
                component = parent;
            }
            if (!b2) {
                Rectangle rectangle;
                if (parent instanceof JComponent) {
                    rectangle = ((JComponent)parent)._bounds;
                }
                else {
                    rectangle = parent.getBounds();
                }
                SwingUtilities.computeIntersection(n5, n5, rectangle.width, rectangle.height, this.paintImmediatelyClip);
                final Rectangle paintImmediatelyClip = this.paintImmediatelyClip;
                paintImmediatelyClip.x += rectangle.x;
                final Rectangle paintImmediatelyClip2 = this.paintImmediatelyClip;
                paintImmediatelyClip2.y += rectangle.y;
                n += rectangle.x;
                n2 += rectangle.y;
            }
        }
        if (parent == null || parent.getPeer() == null) {
            return;
        }
        if (this.paintImmediatelyClip.width <= 0 || this.paintImmediatelyClip.height <= 0) {
            return;
        }
        final Rectangle paintImmediatelyClip3 = this.paintImmediatelyClip;
        paintImmediatelyClip3.x -= n;
        final Rectangle paintImmediatelyClip4 = this.paintImmediatelyClip;
        paintImmediatelyClip4.y -= n2;
        if (component2 != this) {
            for (int i = n3; i > 0; --i) {
                final JComponent component3 = vector.elementAt(i);
                if (component3 instanceof JComponent) {
                    component3.setPaintingChild(vector.elementAt(i - 1));
                }
            }
        }
        try {
            Graphics swingGraphics;
            try {
                swingGraphics = SwingGraphics.createSwingGraphics(component2.getGraphics());
            }
            catch (NullPointerException ex) {
                swingGraphics = null;
                ex.printStackTrace();
            }
            if (swingGraphics == null) {
                System.err.println("In paintImmediately null graphics");
                return;
            }
            final Image offscreenBuffer;
            if (b && (offscreenBuffer = currentManager.getOffscreenBuffer(component, this.paintImmediatelyClip.width, this.paintImmediatelyClip.height)) != null && offscreenBuffer.getWidth(null) > 0 && offscreenBuffer.getHeight(null) > 0) {
                this.paintWithBuffer(component2, swingGraphics, this.paintImmediatelyClip, offscreenBuffer);
                swingGraphics.dispose();
            }
            else {
                swingGraphics.setClip(this.paintImmediatelyClip.x, this.paintImmediatelyClip.y, this.paintImmediatelyClip.width, this.paintImmediatelyClip.height);
                try {
                    component2.paint(swingGraphics);
                }
                finally {
                    swingGraphics.dispose();
                }
            }
        }
        finally {
            if (component2 != this) {
                for (int j = n3; j > 0; --j) {
                    final JComponent component4 = vector.elementAt(j);
                    if (component4 instanceof JComponent) {
                        component4.setPaintingChild(null);
                    }
                }
            }
            vector.removeAllElements();
        }
    }
    
    public void addAncestorListener(final AncestorListener ancestorListener) {
        if (this.ancestorNotifier == null) {
            this.ancestorNotifier = new AncestorNotifier(this);
        }
        this.ancestorNotifier.addAncestorListener(ancestorListener);
    }
    
    public void addNotify() {
        super.addNotify();
        this.firePropertyChange("ancestor", null, this.getParent());
        final Hashtable hashtable = (Hashtable)this.getClientProperty("_KeyboardBindings");
        if (hashtable == null) {
            return;
        }
        final Enumeration<KeyStroke> keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            final KeyStroke keyStroke = keys.nextElement();
            if (hashtable.get(keyStroke).condition == 2) {
                this.registerWithKeyboardManager(keyStroke);
            }
        }
    }
    
    public synchronized void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        if (this.changeSupport == null) {
            this.changeSupport = new SwingPropertyChangeSupport(this);
        }
        this.changeSupport.addPropertyChangeListener(propertyChangeListener);
    }
    
    public synchronized void addPropertyChangeListener(final String s, final PropertyChangeListener propertyChangeListener) {
        if (propertyChangeListener == null) {
            return;
        }
        if (this.changeSupport == null) {
            this.changeSupport = new SwingPropertyChangeSupport(this);
        }
        this.changeSupport.addPropertyChangeListener(s, propertyChangeListener);
    }
    
    public synchronized void addVetoableChangeListener(final VetoableChangeListener vetoableChangeListener) {
        if (this.vetoableChangeSupport == null) {
            this.vetoableChangeSupport = new VetoableChangeSupport(this);
        }
        this.vetoableChangeSupport.addVetoableChangeListener(vetoableChangeListener);
    }
    
    private void adjustPaintFlags() {
        Container container = this.getParent();
        while (container != null) {
            if (container instanceof JComponent) {
                final JComponent component = (JComponent)container;
                if (component.getFlag(2)) {
                    this.setFlag(2, true);
                }
                if (component.getFlag(3)) {
                    this.setFlag(3, true);
                    break;
                }
                break;
            }
            else {
                container = container.getParent();
            }
        }
    }
    
    boolean alwaysOnTop() {
        return false;
    }
    
    KeyboardBinding bindingForKeyStroke(final KeyStroke keyStroke, final int n) {
        KeyboardBinding keyboardBinding = null;
        final Hashtable hashtable = (Hashtable)this.getClientProperty("_KeyboardBindings");
        if (hashtable != null) {
            final KeyboardBinding keyboardBinding2 = hashtable.get(keyStroke);
            if (keyboardBinding2 != null) {
                ActionListener action = keyboardBinding2.getAction();
                if (action instanceof Action && !((Action)action).isEnabled()) {
                    action = null;
                }
                if (action != null) {
                    switch (keyboardBinding2.getCondition()) {
                        case 0: {
                            if (n == 0) {
                                keyboardBinding = keyboardBinding2;
                                break;
                            }
                            break;
                        }
                        case 1: {
                            if (n == 0 || n == 1) {
                                keyboardBinding = keyboardBinding2;
                                break;
                            }
                            break;
                        }
                        case 2: {
                            if (n == 0 || n == 2 || n == 1) {
                                keyboardBinding = keyboardBinding2;
                                break;
                            }
                            break;
                        }
                    }
                }
            }
        }
        return keyboardBinding;
    }
    
    boolean checkIfChildObscuredBySibling() {
        return true;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static final void computeVisibleRect(final Component component, final Rectangle rectangle) {
        final Container parent = component.getParent();
        final Rectangle bounds = component.getBounds();
        if (parent == null || parent instanceof Window || parent instanceof Applet) {
            rectangle.setBounds(0, 0, bounds.width, bounds.height);
        }
        else {
            computeVisibleRect(parent, rectangle);
            rectangle.x -= bounds.x;
            rectangle.y -= bounds.y;
            SwingUtilities.computeIntersection(0, 0, bounds.width, bounds.height, rectangle);
        }
    }
    
    public void computeVisibleRect(final Rectangle rectangle) {
        computeVisibleRect(this, rectangle);
    }
    
    public boolean contains(final int n, final int n2) {
        return (this.ui != null) ? this.ui.contains(this, n, n2) : super.contains(n, n2);
    }
    
    public JToolTip createToolTip() {
        final JToolTip toolTip = new JToolTip();
        toolTip.setComponent(this);
        return toolTip;
    }
    
    void enableSerialization() {
        this.addFocusListener(new EnableSerializationFocusListener());
    }
    
    public void firePropertyChange(final String s, final byte b, final byte b2) {
        if (this.changeSupport != null && b != b2) {
            this.changeSupport.firePropertyChange(s, new Byte(b), new Byte(b2));
        }
    }
    
    public void firePropertyChange(final String s, final char c, final char c2) {
        if (this.changeSupport != null && c != c2) {
            this.changeSupport.firePropertyChange(s, new Character(c), new Character(c2));
        }
    }
    
    public void firePropertyChange(final String s, final double n, final double n2) {
        if (this.changeSupport != null && n != n2) {
            this.changeSupport.firePropertyChange(s, new Double(n), new Double(n2));
        }
    }
    
    public void firePropertyChange(final String s, final float n, final float n2) {
        if (this.changeSupport != null && n != n2) {
            this.changeSupport.firePropertyChange(s, new Float(n), new Float(n2));
        }
    }
    
    public void firePropertyChange(final String s, final int n, final int n2) {
        if (this.changeSupport != null && n != n2) {
            this.changeSupport.firePropertyChange(s, new Integer(n), new Integer(n2));
        }
    }
    
    public void firePropertyChange(final String s, final long n, final long n2) {
        if (this.changeSupport != null && n != n2) {
            this.changeSupport.firePropertyChange(s, new Long(n), new Long(n2));
        }
    }
    
    protected void firePropertyChange(final String s, final Object o, final Object o2) {
        if (this.changeSupport != null) {
            this.changeSupport.firePropertyChange(s, o, o2);
        }
    }
    
    public void firePropertyChange(final String s, final short n, final short n2) {
        if (this.changeSupport != null && n != n2) {
            this.changeSupport.firePropertyChange(s, new Short(n), new Short(n2));
        }
    }
    
    public void firePropertyChange(final String s, final boolean b, final boolean b2) {
        if (this.changeSupport != null && b != b2) {
            this.changeSupport.firePropertyChange(s, new Boolean(b), new Boolean(b2));
        }
    }
    
    protected void fireVetoableChange(final String s, final Object o, final Object o2) throws PropertyVetoException {
        if (this.vetoableChangeSupport == null) {
            return;
        }
        this.vetoableChangeSupport.fireVetoableChange(s, o, o2);
    }
    
    public AccessibleContext getAccessibleContext() {
        return this.accessibleContext;
    }
    
    public ActionListener getActionForKeyStroke(final KeyStroke keyStroke) {
        final Hashtable keyboardBindings = this.keyboardBindings();
        if (keyboardBindings == null) {
            return null;
        }
        synchronized (keyboardBindings) {
            final KeyboardBinding keyboardBinding = keyboardBindings.get(keyStroke);
            if (keyboardBinding != null) {
                // monitorexit(keyboardBindings)
                return keyboardBinding.getAction();
            }
        }
        return null;
    }
    
    public float getAlignmentX() {
        return (this.alignmentX != null) ? this.alignmentX : super.getAlignmentX();
    }
    
    public float getAlignmentY() {
        return (this.alignmentY != null) ? this.alignmentY : super.getAlignmentY();
    }
    
    public boolean getAutoscrolls() {
        return this.autoscroller != null;
    }
    
    public Border getBorder() {
        return this.border;
    }
    
    public Rectangle getBounds(final Rectangle rectangle) {
        if (rectangle == null) {
            return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        }
        rectangle.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        return rectangle;
    }
    
    private Dictionary getClientProperties() {
        if (this.clientProperties == null) {
            this.clientProperties = new Hashtable(2);
        }
        return this.clientProperties;
    }
    
    public final Object getClientProperty(final Object o) {
        if (this.clientProperties == null) {
            return null;
        }
        return this.getClientProperties().get(o);
    }
    
    protected Graphics getComponentGraphics(final Graphics graphics) {
        Graphics swingGraphics = graphics;
        if (this.ui != null && DebugGraphics.debugComponentCount() != 0 && this.shouldDebugGraphics() != 0 && !(graphics instanceof DebugGraphics)) {
            if (graphics instanceof SwingGraphics) {
                if (!(((SwingGraphics)graphics).subGraphics() instanceof DebugGraphics)) {
                    swingGraphics = SwingGraphics.createSwingGraphics(new DebugGraphics(((SwingGraphics)graphics).subGraphics(), this));
                }
            }
            else {
                swingGraphics = new DebugGraphics(graphics, this);
            }
        }
        swingGraphics.setColor(this.getForeground());
        swingGraphics.setFont(this.getFont());
        return swingGraphics;
    }
    
    public int getConditionForKeyStroke(final KeyStroke keyStroke) {
        final Hashtable keyboardBindings = this.keyboardBindings();
        if (keyboardBindings == null) {
            return -1;
        }
        synchronized (keyboardBindings) {
            final KeyboardBinding keyboardBinding = keyboardBindings.get(keyStroke);
            if (keyboardBinding != null) {
                // monitorexit(keyboardBindings)
                return keyboardBinding.getCondition();
            }
        }
        return -1;
    }
    
    public int getDebugGraphicsOptions() {
        return DebugGraphics.getDebugOptions(this);
    }
    
    private boolean getFlag(final int n) {
        final int n2 = 1 << n;
        return (this.flags & n2) == n2;
    }
    
    public Graphics getGraphics() {
        if (this.shouldDebugGraphics() != 0) {
            return new DebugGraphics(super.getGraphics(), this);
        }
        return super.getGraphics();
    }
    
    public int getHeight() {
        return this._bounds.height;
    }
    
    public Insets getInsets() {
        if (this.border != null) {
            return this.border.getBorderInsets(this);
        }
        return super.getInsets();
    }
    
    public Insets getInsets(final Insets insets) {
        if (this.border == null) {
            final boolean b = false;
            insets.bottom = (b ? 1 : 0);
            insets.right = (b ? 1 : 0);
            insets.top = (b ? 1 : 0);
            insets.left = (b ? 1 : 0);
            return insets;
        }
        if (this.border instanceof AbstractBorder) {
            return ((AbstractBorder)this.border).getBorderInsets(this, insets);
        }
        return this.border.getBorderInsets(this);
    }
    
    public Point getLocation(final Point point) {
        if (point == null) {
            return new Point(this.getX(), this.getY());
        }
        point.setLocation(this.getX(), this.getY());
        return point;
    }
    
    public Dimension getMaximumSize() {
        if (this.maximumSize != null) {
            return this.maximumSize;
        }
        Dimension maximumSize = null;
        if (this.ui != null) {
            maximumSize = this.ui.getMaximumSize(this);
        }
        return (maximumSize != null) ? maximumSize : super.getMaximumSize();
    }
    
    public Dimension getMinimumSize() {
        if (this.minimumSize != null) {
            return this.minimumSize;
        }
        Dimension minimumSize = null;
        if (this.ui != null) {
            minimumSize = this.ui.getMinimumSize(this);
        }
        return (minimumSize != null) ? minimumSize : super.getMinimumSize();
    }
    
    public Component getNextFocusableComponent() {
        return (Component)this.getClientProperty("nextFocus");
    }
    
    public Dimension getPreferredSize() {
        if (this.preferredSize != null) {
            return this.preferredSize;
        }
        Dimension preferredSize = null;
        if (this.ui != null) {
            preferredSize = this.ui.getPreferredSize(this);
        }
        return (preferredSize != null) ? preferredSize : super.getPreferredSize();
    }
    
    public KeyStroke[] getRegisteredKeyStrokes() {
        final Hashtable keyboardBindings = this.keyboardBindings();
        if (keyboardBindings == null) {
            return new KeyStroke[0];
        }
        final KeyStroke[] array;
        synchronized (keyboardBindings) {
            array = new KeyStroke[keyboardBindings.size()];
            int n = 0;
            final Enumeration<KeyStroke> keys = keyboardBindings.keys();
            while (keys.hasMoreElements()) {
                array[n++] = keys.nextElement();
            }
        }
        return array;
    }
    
    public JRootPane getRootPane() {
        return SwingUtilities.getRootPane(this);
    }
    
    public Dimension getSize(final Dimension dimension) {
        if (dimension == null) {
            return new Dimension(this.getWidth(), this.getHeight());
        }
        dimension.setSize(this.getWidth(), this.getHeight());
        return dimension;
    }
    
    public Point getToolTipLocation(final MouseEvent mouseEvent) {
        return null;
    }
    
    public String getToolTipText() {
        return (String)this.getClientProperty("ToolTipText");
    }
    
    public String getToolTipText(final MouseEvent mouseEvent) {
        return this.getToolTipText();
    }
    
    public Container getTopLevelAncestor() {
        for (Container parent = this; parent != null; parent = parent.getParent()) {
            if (parent instanceof Window || parent instanceof Applet) {
                return parent;
            }
        }
        return null;
    }
    
    public String getUIClassID() {
        return "ComponentUI";
    }
    
    public Rectangle getVisibleRect() {
        final Rectangle rectangle = new Rectangle();
        this.computeVisibleRect(rectangle);
        return rectangle;
    }
    
    public int getWidth() {
        return this._bounds.width;
    }
    
    public int getX() {
        return this._bounds.x;
    }
    
    public int getY() {
        return this._bounds.y;
    }
    
    public void grabFocus() {
        super.requestFocus();
    }
    
    public boolean hasFocus() {
        return this.getFlag(4);
    }
    
    public boolean isDoubleBuffered() {
        return this.getFlag(1);
    }
    
    public boolean isFocusCycleRoot() {
        return false;
    }
    
    public boolean isFocusTraversable() {
        boolean b = false;
        final Hashtable hashtable;
        synchronized (this) {
            hashtable = (Hashtable)this.getClientProperty("_KeyboardBindings");
        }
        if (hashtable != null) {
            synchronized (hashtable) {
                final Enumeration<Object> keys = hashtable.keys();
                while (keys.hasMoreElements()) {
                    if (hashtable.get(keys.nextElement()).getCondition() == 0) {
                        b = true;
                        break;
                    }
                }
            }
            // monitorexit(hashtable)
        }
        return b;
    }
    
    public static boolean isLightweightComponent(final Component component) {
        return component.getPeer() instanceof LightweightPeer;
    }
    
    public boolean isManagingFocus() {
        return false;
    }
    
    public boolean isOpaque() {
        return this.getFlag(5);
    }
    
    public boolean isOptimizedDrawingEnabled() {
        return true;
    }
    
    public boolean isPaintingTile() {
        return this.getFlag(3);
    }
    
    public boolean isRequestFocusEnabled() {
        return !this.getFlag(0);
    }
    
    public boolean isValidateRoot() {
        return false;
    }
    
    private Hashtable keyboardBindings() {
        final Hashtable hashtable;
        synchronized (this) {
            hashtable = (Hashtable)this.getClientProperty("_KeyboardBindings");
        }
        return hashtable;
    }
    
    public void paint(final Graphics graphics) {
        boolean b = false;
        if (this.getWidth() <= 0 || this.getHeight() <= 0) {
            return;
        }
        final Graphics swingGraphics = SwingGraphics.createSwingGraphics(this.getComponentGraphics(graphics));
        try {
            final RepaintManager currentManager = RepaintManager.currentManager(this);
            final GraphicsWrapper graphicsWrapper = (GraphicsWrapper)swingGraphics;
            final int clipX = graphicsWrapper.getClipX();
            final int clipY = graphicsWrapper.getClipY();
            int n = graphicsWrapper.getClipWidth();
            int n2 = graphicsWrapper.getClipHeight();
            if (n > this.getWidth()) {
                n = this.getWidth();
            }
            if (n2 > this.getHeight()) {
                n2 = this.getHeight();
            }
            if (this.getParent() != null && !(this.getParent() instanceof JComponent)) {
                this.adjustPaintFlags();
                b = true;
            }
            final Image offscreenBuffer;
            final int width;
            final int height;
            if (currentManager.isDoubleBufferingEnabled() && !this.getFlag(2) && this.isDoubleBuffered() && (offscreenBuffer = currentManager.getOffscreenBuffer(this, n, n2)) != null && (width = offscreenBuffer.getWidth(null)) > 0 && (height = offscreenBuffer.getHeight(null)) > 0) {
                final Graphics swingGraphics2 = SwingGraphics.createSwingGraphics(offscreenBuffer.getGraphics());
                try {
                    swingGraphics2.translate(-clipX, -clipY);
                    int width2 = offscreenBuffer.getWidth(null);
                    int height2 = offscreenBuffer.getHeight(null);
                    if (width2 > n) {
                        width2 = n;
                    }
                    if (height2 > n2) {
                        height2 = n2;
                    }
                    this.setFlag(2, true);
                    this.setFlag(3, true);
                    for (int i = 0, n3 = n; i < n3; i += width2) {
                        for (int j = 0, n4 = n2; j < n4; j += height2) {
                            if (j + height2 >= n4 && i + width2 >= n3) {
                                this.setFlag(3, false);
                            }
                            swingGraphics2.translate(-i, -j);
                            swingGraphics2.setClip(clipX + i, clipY + j, width2, height2);
                            if (!this.rectangleIsObscured(clipX, clipY, width2, height2)) {
                                this.paintComponent(swingGraphics2);
                                this.paintBorder(swingGraphics2);
                            }
                            this.paintChildren(swingGraphics2);
                            swingGraphics.drawImage(offscreenBuffer, clipX + i, clipY + j, this);
                            swingGraphics2.translate(i, j);
                        }
                    }
                    return;
                }
                finally {
                    this.setFlag(2, false);
                    this.setFlag(3, false);
                    swingGraphics2.dispose();
                }
            }
            if (!this.rectangleIsObscured(clipX, clipY, n, n2)) {
                this.paintComponent(swingGraphics);
                this.paintBorder(swingGraphics);
            }
            this.paintChildren(swingGraphics);
        }
        finally {
            swingGraphics.dispose();
            if (b) {
                this.setFlag(2, false);
                this.setFlag(3, false);
            }
        }
    }
    
    protected void paintBorder(final Graphics graphics) {
        final Border border = this.getBorder();
        if (border != null) {
            border.paintBorder(this, graphics, 0, 0, this.getWidth(), this.getHeight());
        }
    }
    
    protected void paintChildren(final Graphics graphics) {
        Graphics swingGraphics = null;
        try {
            synchronized (this.getTreeLock()) {
                int i = this.getComponentCount() - 1;
                if (i < 0) {
                    // monitorexit(this.getTreeLock())
                    return;
                }
                swingGraphics = SwingGraphics.createSwingGraphics(graphics);
                if (this.paintingChild != null && this.paintingChild instanceof JComponent && ((JComponent)this.paintingChild).isOpaque()) {
                    while (i >= 0) {
                        if (this.getComponent(i) == this.paintingChild) {
                            break;
                        }
                        --i;
                    }
                }
                if (this.tmpRect == null) {
                    this.tmpRect = new Rectangle();
                }
                final boolean b = !this.isOptimizedDrawingEnabled() && this.checkIfChildObscuredBySibling();
                while (i >= 0) {
                    final Component component = this.getComponent(i);
                    Label_0593: {
                        if (component != null && isLightweightComponent(component) && component.isVisible()) {
                            final boolean b2 = component instanceof JComponent;
                            Rectangle rectangle;
                            if (b2) {
                                rectangle = this.tmpRect;
                                ((JComponent)component).getBounds(rectangle);
                            }
                            else {
                                rectangle = component.getBounds();
                            }
                            if (((GraphicsWrapper)swingGraphics).isClipIntersecting(rectangle)) {
                                if (b && i > 0) {
                                    final int x = rectangle.x;
                                    final int y = rectangle.y;
                                    final int width = rectangle.width;
                                    final int height = rectangle.height;
                                    final GraphicsWrapper graphicsWrapper = (GraphicsWrapper)swingGraphics;
                                    SwingUtilities.computeIntersection(graphicsWrapper.getClipX(), graphicsWrapper.getClipY(), graphicsWrapper.getClipWidth(), graphicsWrapper.getClipHeight(), rectangle);
                                    if (this.rectangleIsObscuredBySibling(i, rectangle.x, rectangle.y, rectangle.width, rectangle.height)) {
                                        break Label_0593;
                                    }
                                    rectangle.x = x;
                                    rectangle.y = y;
                                    rectangle.width = width;
                                    rectangle.height = height;
                                }
                                final Graphics swingGraphics2 = SwingGraphics.createSwingGraphics(swingGraphics, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                                swingGraphics2.setColor(component.getForeground());
                                swingGraphics2.setFont(component.getFont());
                                boolean b3 = false;
                                try {
                                    if (b2) {
                                        if (this.getFlag(2)) {
                                            ((JComponent)component).setFlag(2, true);
                                            b3 = true;
                                        }
                                        if (this.getFlag(3)) {
                                            ((JComponent)component).setFlag(3, true);
                                            b3 = true;
                                        }
                                        ((JComponent)component).paint(swingGraphics2);
                                    }
                                    else {
                                        component.paint(swingGraphics2);
                                    }
                                }
                                finally {
                                    swingGraphics2.dispose();
                                    if (b3) {
                                        ((JComponent)component).setFlag(2, false);
                                        ((JComponent)component).setFlag(3, false);
                                    }
                                }
                            }
                        }
                    }
                    --i;
                }
            }
            // monitorexit(this.getTreeLock())
        }
        finally {
            if (swingGraphics != null) {
                swingGraphics.dispose();
            }
        }
    }
    
    protected void paintComponent(final Graphics graphics) {
        if (this.ui != null) {
            final Graphics swingGraphics = SwingGraphics.createSwingGraphics(graphics);
            try {
                this.ui.update(swingGraphics, this);
            }
            finally {
                swingGraphics.dispose();
            }
        }
    }
    
    public void paintImmediately(int n, int n2, final int n3, final int n4) {
        JComponent component = this;
        if (!this.isShowing()) {
            return;
        }
        while (!component.isOpaque()) {
            final Container parent = component.getParent();
            if (parent == null) {
                break;
            }
            Rectangle rectangle;
            if (component instanceof JComponent) {
                rectangle = component._bounds;
            }
            else {
                rectangle = component.getBounds();
            }
            n += rectangle.x;
            n2 += rectangle.y;
            component = (JComponent)parent;
            if (!(component instanceof JComponent)) {
                break;
            }
        }
        if (component instanceof JComponent) {
            component._paintImmediately(n, n2, n3, n4);
        }
        else {
            component.repaint(n, n2, n3, n4);
        }
    }
    
    public void paintImmediately(final Rectangle rectangle) {
        this.paintImmediately(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    private void paintWithBuffer(final JComponent component, final Graphics graphics, final Rectangle rectangle, final Image image) {
        final Graphics swingGraphics = SwingGraphics.createSwingGraphics(image.getGraphics());
        int n = image.getWidth(null);
        int n2 = image.getHeight(null);
        if (n > rectangle.width) {
            n = rectangle.width;
        }
        if (n2 > rectangle.height) {
            n2 = rectangle.height;
        }
        try {
            component.setFlag(2, true);
            component.setFlag(3, true);
            for (int i = rectangle.x, n3 = rectangle.x + rectangle.width; i < n3; i += n) {
                for (int j = rectangle.y, n4 = rectangle.y + rectangle.height; j < n4; j += n2) {
                    if (j + n2 >= n4 && i + n >= n3) {
                        component.setFlag(3, false);
                    }
                    swingGraphics.translate(-i, -j);
                    swingGraphics.setClip(i, j, n, n2);
                    component.paint(swingGraphics);
                    graphics.setClip(i, j, n, n2);
                    graphics.drawImage(image, i, j, component);
                    swingGraphics.translate(i, j);
                }
            }
        }
        finally {
            component.setFlag(2, false);
            component.setFlag(3, false);
            swingGraphics.dispose();
        }
    }
    
    protected String paramString() {
        return String.valueOf(super.paramString()) + ",alignmentX=" + this.alignmentX + ",alignmentY=" + this.alignmentY + ",border=" + ((this.border != null) ? this.border.toString() : "") + ",flags=" + this.flags + ",maximumSize=" + ((this.maximumSize != null) ? this.maximumSize.toString() : "") + ",minimumSize=" + ((this.minimumSize != null) ? this.minimumSize.toString() : "") + ",preferredSize=" + ((this.preferredSize != null) ? this.preferredSize.toString() : "");
    }
    
    protected void processComponentKeyEvent(final KeyEvent keyEvent) {
    }
    
    protected void processFocusEvent(final FocusEvent focusEvent) {
        switch (focusEvent.getID()) {
            case 1004: {
                this.setFlag(4, true);
                break;
            }
            case 1005: {
                this.setFlag(4, false);
                break;
            }
        }
        super.processFocusEvent(focusEvent);
    }
    
    boolean processKeyBinding(final KeyEvent keyEvent, final int n, final boolean b) {
        final boolean b2 = !b;
        if (this.isEnabled()) {
            KeyboardBinding keyboardBinding;
            if (keyEvent.getID() == 400) {
                keyboardBinding = this.bindingForKeyStroke(KeyStroke.getKeyStroke(keyEvent.getKeyChar()), n);
            }
            else {
                keyboardBinding = this.bindingForKeyStroke(KeyStroke.getKeyStroke(keyEvent.getKeyCode(), keyEvent.getModifiers(), b2), n);
            }
            if (keyboardBinding != null) {
                final ActionListener action = keyboardBinding.getAction();
                if (action != null) {
                    action.actionPerformed(new ActionEvent(this, 1001, keyboardBinding.getCommand()));
                    return true;
                }
            }
        }
        return false;
    }
    
    boolean processKeyBindings(final KeyEvent keyEvent, final boolean b) {
        if (this.processKeyBinding(keyEvent, 0, b)) {
            return true;
        }
        Container container;
        for (container = this.getParent(); container != null && !(container instanceof Window) && !(container instanceof Applet) && !(container instanceof JInternalFrame); container = container.getParent()) {
            if (container instanceof JComponent && ((JComponent)container).processKeyBinding(keyEvent, 1, b)) {
                return true;
            }
        }
        return container != null && processKeyBindingsForAllComponents(keyEvent, container, b);
    }
    
    static boolean processKeyBindingsForAllComponents(final KeyEvent keyEvent, final Container container, final boolean b) {
        return KeyboardManager.getCurrentManager().fireKeyboardAction(keyEvent, b, container);
    }
    
    protected void processKeyEvent(final KeyEvent keyEvent) {
        boolean b = false;
        if (FocusManager.isFocusManagerEnabled()) {
            FocusManager.getCurrentManager().processKeyEvent(this, keyEvent);
            if (keyEvent.isConsumed()) {
                return;
            }
        }
        super.processKeyEvent(keyEvent);
        if (!keyEvent.isConsumed()) {
            this.processComponentKeyEvent(keyEvent);
        }
        if (keyEvent.getID() == 401) {
            b = true;
            if (!KeyboardState.keyIsPressed(keyEvent.getKeyCode())) {
                KeyboardState.registerKeyPressed(keyEvent.getKeyCode());
            }
        }
        else if (keyEvent.getID() == 402) {
            if (KeyboardState.keyIsPressed(keyEvent.getKeyCode())) {
                b = true;
                KeyboardState.registerKeyReleased(keyEvent.getKeyCode());
            }
        }
        else if (keyEvent.getID() == 400) {
            b = true;
        }
        if (keyEvent.isConsumed()) {
            return;
        }
        if (b && keyEvent.getID() == 401) {
            if (this.processKeyBindings(keyEvent, true)) {
                keyEvent.consume();
            }
        }
        else if (b && keyEvent.getID() == 402) {
            if (this.processKeyBindings(keyEvent, false)) {
                keyEvent.consume();
            }
        }
        else if (b && keyEvent.getID() == 400 && this.processKeyBindings(keyEvent, false)) {
            keyEvent.consume();
        }
    }
    
    protected void processMouseMotionEvent(final MouseEvent mouseEvent) {
        boolean b = true;
        if (this.autoscroller != null && mouseEvent.getID() == 506) {
            b = (this.autoscroller.timer.isRunning() ^ true);
            this.autoscroller.mouseDragged(mouseEvent);
        }
        if (b) {
            super.processMouseMotionEvent(mouseEvent);
        }
    }
    
    public final void putClientProperty(final Object o, final Object o2) {
        final Object value = this.getClientProperties().get(o);
        if (o2 != null) {
            this.getClientProperties().put(o, o2);
        }
        else {
            this.getClientProperties().remove(o);
        }
        this.firePropertyChange(o.toString(), value, o2);
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        ReadObjectCallback readObjectCallback = JComponent.readObjectCallbacks.get(objectInputStream);
        if (readObjectCallback == null) {
            try {
                JComponent.readObjectCallbacks.put(objectInputStream, readObjectCallback = new ReadObjectCallback(objectInputStream));
            }
            catch (Exception ex) {
                throw new IOException(ex.toString());
            }
        }
        readObjectCallback.registerComponent(this);
        if (this.getToolTipText() != null) {
            ToolTipManager.sharedInstance().registerComponent(this);
        }
    }
    
    boolean rectangleIsObscured(final int n, final int n2, final int n3, final int n4) {
        for (int componentCount = this.getComponentCount(), i = 0; i < componentCount; ++i) {
            final Component component = this.getComponent(i);
            Rectangle rectangle;
            if (component instanceof JComponent) {
                rectangle = ((JComponent)component)._bounds;
            }
            else {
                rectangle = component.getBounds();
            }
            if (n >= rectangle.x && n + n3 <= rectangle.x + rectangle.width && n2 >= rectangle.y && n2 + n4 <= rectangle.y + rectangle.height && component.isVisible()) {
                return component instanceof JComponent && ((JComponent)component).isOpaque();
            }
        }
        return false;
    }
    
    boolean rectangleIsObscuredBySibling(final int n, final int n2, final int n3, final int n4, final int n5) {
        for (int i = n - 1; i >= 0; --i) {
            final Component component = this.getComponent(i);
            if (component.isVisible()) {
                Rectangle rectangle;
                if (component instanceof JComponent) {
                    if (!((JComponent)component).isOpaque()) {
                        continue;
                    }
                    rectangle = ((JComponent)component).getBounds(this.tmpRect);
                }
                else {
                    rectangle = component.getBounds();
                }
                if (n2 >= rectangle.x && n2 + n4 <= rectangle.x + rectangle.width && n3 >= rectangle.y && n3 + n5 <= rectangle.y + rectangle.height) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void registerKeyboardAction(final ActionListener actionListener, final String s, final KeyStroke keyStroke, final int n) {
        boolean b = false;
        Hashtable<KeyStroke, KeyboardBinding> hashtable;
        synchronized (this) {
            hashtable = (Hashtable<KeyStroke, KeyboardBinding>)this.getClientProperty("_KeyboardBindings");
            if (hashtable == null) {
                hashtable = new Hashtable<KeyStroke, KeyboardBinding>();
                this.putClientProperty("_KeyboardBindings", hashtable);
                b = true;
            }
        }
        synchronized (hashtable) {
            hashtable.put(keyStroke, new KeyboardBinding(actionListener, s, keyStroke, n));
        }
        if (b) {
            this.enableEvents(8L);
        }
        if (this.getParent() != null && n == 2) {
            this.registerWithKeyboardManager(keyStroke);
        }
    }
    
    public void registerKeyboardAction(final ActionListener actionListener, final KeyStroke keyStroke, final int n) {
        this.registerKeyboardAction(actionListener, null, keyStroke, n);
    }
    
    void registerWithKeyboardManager(final KeyStroke keyStroke) {
        KeyboardManager.getCurrentManager().registerKeyStroke(keyStroke, this);
    }
    
    public void removeAncestorListener(final AncestorListener ancestorListener) {
        if (this.ancestorNotifier == null) {
            return;
        }
        this.ancestorNotifier.removeAncestorListener(ancestorListener);
        if (this.ancestorNotifier.listenerList.getListenerList().length == 0) {
            this.ancestorNotifier.removeAllListeners();
            this.ancestorNotifier = null;
        }
    }
    
    public void removeNotify() {
        super.removeNotify();
        this.firePropertyChange("ancestor", this.getParent(), null);
        final Hashtable hashtable = (Hashtable)this.getClientProperty("_KeyboardBindings");
        if (hashtable == null) {
            return;
        }
        final Enumeration<KeyStroke> keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            final KeyStroke keyStroke = keys.nextElement();
            if (hashtable.get(keyStroke).condition == 2) {
                this.unregisterWithKeyboardManager(keyStroke);
            }
        }
    }
    
    public synchronized void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        if (this.changeSupport != null) {
            this.changeSupport.removePropertyChangeListener(propertyChangeListener);
        }
    }
    
    public synchronized void removePropertyChangeListener(final String s, final PropertyChangeListener propertyChangeListener) {
        if (propertyChangeListener == null) {
            return;
        }
        if (this.changeSupport == null) {
            return;
        }
        this.changeSupport.removePropertyChangeListener(s, propertyChangeListener);
    }
    
    public synchronized void removeVetoableChangeListener(final VetoableChangeListener vetoableChangeListener) {
        if (this.vetoableChangeSupport == null) {
            return;
        }
        this.vetoableChangeSupport.removeVetoableChangeListener(vetoableChangeListener);
    }
    
    public void repaint(final long n, final int n2, final int n3, final int n4, final int n5) {
        RepaintManager.currentManager(this).addDirtyRegion(this, n2, n3, n4, n5);
    }
    
    public void repaint(final Rectangle rectangle) {
        this.repaint(0L, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public boolean requestDefaultFocus() {
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            if (components[i].isFocusTraversable()) {
                if (components[i] instanceof JComponent) {
                    ((JComponent)components[i]).grabFocus();
                }
                else {
                    components[i].requestFocus();
                }
                return true;
            }
            if (components[i] instanceof JComponent && !((JComponent)components[i]).isManagingFocus() && ((JComponent)components[i]).requestDefaultFocus()) {
                return true;
            }
        }
        return false;
    }
    
    public void requestFocus() {
        final FocusManager currentManager = FocusManager.getCurrentManager();
        if (currentManager instanceof DefaultFocusManager) {
            ((DefaultFocusManager)currentManager).clearHistory();
        }
        if (this.isRequestFocusEnabled()) {
            super.requestFocus();
        }
    }
    
    public void resetKeyboardActions() {
        synchronized (this) {
            final Hashtable hashtable = (Hashtable)this.getClientProperty("_KeyboardBindings");
            if (hashtable != null) {
                hashtable.clear();
            }
        }
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        if (this.isShowing()) {
            if (this._bounds.x + this._bounds.width > n && this._bounds.y + this._bounds.height > n2 && this._bounds.x < n + n3 && this._bounds.y < n2 + n4) {
                final Rectangle[] computeDifference = SwingUtilities.computeDifference(this.getBounds(), new Rectangle(n, n2, n3, n4));
                final Container parent = this.getParent();
                for (int i = 0; i < computeDifference.length; ++i) {
                    parent.repaint(computeDifference[i].x, computeDifference[i].y, computeDifference[i].width, computeDifference[i].height);
                }
            }
            else {
                this.getParent().repaint(this._bounds.x, this._bounds.y, this._bounds.width, this._bounds.height);
            }
        }
        this._bounds.setBounds(n, n2, n3, n4);
        super.reshape(n, n2, n3, n4);
    }
    
    public void revalidate() {
        if (this.getParent() == null) {
            this.invalidate();
        }
        else if (SwingUtilities.isEventDispatchThread()) {
            this.invalidate();
            RepaintManager.currentManager(this).addInvalidComponent(this);
        }
        else {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    JComponent.this.revalidate();
                }
            });
        }
    }
    
    public void scrollRectToVisible(final Rectangle rectangle) {
        int x = this.getX();
        int y = this.getY();
        Container container;
        for (container = this.getParent(); container != null && !(container instanceof JComponent) && !(container instanceof CellRendererPane); container = container.getParent()) {
            final Rectangle bounds = container.getBounds();
            x += bounds.x;
            y += bounds.y;
        }
        if (container != null && !(container instanceof CellRendererPane)) {
            rectangle.x += x;
            rectangle.y += y;
            ((JComponent)container).scrollRectToVisible(rectangle);
            rectangle.x -= x;
            rectangle.y -= y;
        }
    }
    
    public void setAlignmentX(final float n) {
        this.alignmentX = new Float((n > 1.0f) ? 1.0f : ((n < 0.0f) ? 0.0f : n));
    }
    
    public void setAlignmentY(final float n) {
        this.alignmentY = new Float((n > 1.0f) ? 1.0f : ((n < 0.0f) ? 0.0f : n));
    }
    
    public void setAutoscrolls(final boolean b) {
        if (b) {
            if (this.autoscroller == null) {
                this.autoscroller = new Autoscroller(this);
            }
        }
        else if (this.autoscroller != null) {
            this.autoscroller.dispose();
            this.autoscroller = null;
        }
    }
    
    public void setBackground(final Color background) {
        final Color background2 = this.getBackground();
        super.setBackground(background);
        Label_0043: {
            boolean b;
            if (background2 != null) {
                b = (background2.equals(background) ^ true);
            }
            else {
                if (background != null && !background.equals(background2)) {
                    break Label_0043;
                }
                b = false;
            }
            if (!b) {
                return;
            }
        }
        if (!SwingUtilities.is1dot2) {
            this.firePropertyChange("background", background2, background);
        }
        this.repaint();
    }
    
    public void setBorder(final Border border) {
        final Border border2 = this.border;
        this.firePropertyChange("border", border2, this.border = border);
        if (border != border2) {
            if (border == null || border2 == null || !border.getBorderInsets(this).equals(border2.getBorderInsets(this))) {
                this.revalidate();
            }
            this.repaint();
        }
    }
    
    public void setDebugGraphicsOptions(final int n) {
        DebugGraphics.setDebugOptions(this, n);
    }
    
    public void setDoubleBuffered(final boolean b) {
        this.setFlag(1, b);
    }
    
    public void setEnabled(final boolean enabled) {
        final boolean enabled2 = this.isEnabled();
        super.setEnabled(enabled);
        if (!enabled && this.hasFocus()) {
            FocusManager.getCurrentManager().focusPreviousComponent(this);
        }
        this.firePropertyChange("enabled", enabled2, enabled);
        if (enabled != enabled2) {
            this.repaint();
        }
    }
    
    private void setFlag(final int n, final boolean b) {
        if (b) {
            this.flags |= 1 << n;
        }
        else {
            this.flags &= ~(1 << n);
        }
    }
    
    public void setFont(final Font font) {
        final Font font2 = this.getFont();
        super.setFont(font);
        if (!SwingUtilities.is1dot2) {
            this.firePropertyChange("font", font2, font);
        }
        if (font != font2) {
            this.revalidate();
            this.repaint();
        }
    }
    
    public void setForeground(final Color foreground) {
        final Color foreground2 = this.getForeground();
        super.setForeground(foreground);
        Label_0043: {
            boolean b;
            if (foreground2 != null) {
                b = (foreground2.equals(foreground) ^ true);
            }
            else {
                if (foreground != null && !foreground.equals(foreground2)) {
                    break Label_0043;
                }
                b = false;
            }
            if (!b) {
                return;
            }
        }
        if (!SwingUtilities.is1dot2) {
            this.firePropertyChange("foreground", foreground2, foreground);
        }
        this.repaint();
    }
    
    public void setMaximumSize(final Dimension maximumSize) {
        this.firePropertyChange("maximumSize", this.maximumSize, this.maximumSize = maximumSize);
    }
    
    public void setMinimumSize(final Dimension minimumSize) {
        this.firePropertyChange("minimumSize", this.minimumSize, this.minimumSize = minimumSize);
    }
    
    public void setNextFocusableComponent(final Component component) {
        this.putClientProperty("nextFocus", component);
    }
    
    public void setOpaque(final boolean b) {
        final boolean flag = this.getFlag(5);
        this.setFlag(5, b);
        this.firePropertyChange("opaque", flag, b);
    }
    
    void setPaintingChild(final Component paintingChild) {
        this.paintingChild = paintingChild;
    }
    
    public void setPreferredSize(final Dimension preferredSize) {
        this.firePropertyChange("preferredSize", this.preferredSize, this.preferredSize = preferredSize);
    }
    
    public void setRequestFocusEnabled(final boolean b) {
        this.setFlag(0, !b);
    }
    
    public void setToolTipText(final String s) {
        this.putClientProperty("ToolTipText", s);
        final ToolTipManager sharedInstance = ToolTipManager.sharedInstance();
        if (s != null) {
            sharedInstance.registerComponent(this);
        }
        else {
            sharedInstance.unregisterComponent(this);
        }
    }
    
    protected void setUI(final ComponentUI ui) {
        if (this.ui != null) {
            this.ui.uninstallUI(this);
        }
        final ComponentUI ui2 = this.ui;
        this.ui = ui;
        if (this.ui != null) {
            this.ui.installUI(this);
        }
        this.firePropertyChange("UI", ui2, ui);
        this.revalidate();
        this.repaint();
    }
    
    public void setVisible(final boolean visible) {
        if (visible != this.isVisible()) {
            super.setVisible(visible);
            final Container parent = this.getParent();
            if (parent != null) {
                final Rectangle bounds = this.getBounds();
                parent.repaint(bounds.x, bounds.y, bounds.width, bounds.height);
            }
            this.revalidate();
            if (this.accessibleContext != null) {
                if (visible) {
                    this.accessibleContext.firePropertyChange("AccessibleState", null, AccessibleState.VISIBLE);
                }
                else {
                    this.accessibleContext.firePropertyChange("AccessibleState", AccessibleState.VISIBLE, null);
                }
            }
        }
    }
    
    int shouldDebugGraphics() {
        return DebugGraphics.shouldComponentDebug(this);
    }
    
    void superProcessMouseMotionEvent(final MouseEvent mouseEvent) {
        super.processMouseMotionEvent(mouseEvent);
    }
    
    public void unregisterKeyboardAction(final KeyStroke keyStroke) {
        final Hashtable keyboardBindings = this.keyboardBindings();
        if (keyboardBindings == null) {
            return;
        }
        final KeyboardBinding keyboardBinding;
        synchronized (keyboardBindings) {
            keyboardBinding = keyboardBindings.remove(keyStroke);
        }
        keyboardBindings.size();
        if (keyboardBinding != null && keyboardBinding.condition == 2) {
            this.unregisterWithKeyboardManager(keyStroke);
        }
    }
    
    void unregisterWithKeyboardManager(final KeyStroke keyStroke) {
        KeyboardManager.getCurrentManager().unregisterKeyStroke(keyStroke, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void updateUI() {
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        if (this.ui != null && this.getUIClassID().equals("ComponentUI")) {
            this.ui.installUI(this);
        }
    }
    
    static class KeyboardBinding implements Serializable
    {
        ActionListener action;
        String command;
        KeyStroke keyStroke;
        int condition;
        
        KeyboardBinding(final ActionListener action, final String command, final KeyStroke keyStroke, final int condition) {
            this.action = action;
            this.command = command;
            this.keyStroke = keyStroke;
            this.condition = condition;
        }
        
        ActionListener getAction() {
            return this.action;
        }
        
        String getCommand() {
            return this.command;
        }
        
        int getCondition() {
            return this.condition;
        }
        
        KeyStroke getKeyStroke() {
            return this.keyStroke;
        }
        
        public String toString() {
            return "KeyBinding (" + this.action + "," + this.keyStroke + "," + this.condition + ")";
        }
    }
    
    static final class IntVector
    {
        int[] array;
        int count;
        int capacity;
        
        IntVector() {
            this.array = null;
            this.count = 0;
            this.capacity = 0;
        }
        
        void addElement(final int n) {
            if (this.count == this.capacity) {
                this.capacity = (this.capacity + 2) * 2;
                final int[] array = new int[this.capacity];
                if (this.count > 0) {
                    System.arraycopy(this.array, 0, array, 0, this.count);
                }
                this.array = array;
            }
            this.array[this.count++] = n;
        }
        
        int elementAt(final int n) {
            return this.array[n];
        }
        
        void setElementAt(final int n, final int n2) {
            this.array[n2] = n;
        }
        
        int size() {
            return this.count;
        }
    }
    
    static class KeyboardState implements Serializable
    {
        private static final Object keyCodesKey;
        
        static {
            keyCodesKey = ((JComponent.class$javax$swing$JComponent$KeyboardState != null) ? JComponent.class$javax$swing$JComponent$KeyboardState : (JComponent.class$javax$swing$JComponent$KeyboardState = JComponent.class$("javax.swing.JComponent$KeyboardState")));
        }
        
        static IntVector getKeyCodeArray() {
            IntVector intVector = (IntVector)SwingUtilities.appContextGet(KeyboardState.keyCodesKey);
            if (intVector == null) {
                intVector = new IntVector();
                SwingUtilities.appContextPut(KeyboardState.keyCodesKey, intVector);
            }
            return intVector;
        }
        
        static boolean keyIsPressed(final int n) {
            final IntVector keyCodeArray = getKeyCodeArray();
            for (int size = keyCodeArray.size(), i = 0; i < size; ++i) {
                if (keyCodeArray.elementAt(i) == n) {
                    return true;
                }
            }
            return false;
        }
        
        static void registerKeyPressed(final int n) {
            final IntVector keyCodeArray = getKeyCodeArray();
            for (int size = keyCodeArray.size(), i = 0; i < size; ++i) {
                if (keyCodeArray.elementAt(i) == -1) {
                    keyCodeArray.setElementAt(n, i);
                    return;
                }
            }
            keyCodeArray.addElement(n);
        }
        
        static void registerKeyReleased(final int n) {
            final IntVector keyCodeArray = getKeyCodeArray();
            for (int size = keyCodeArray.size(), i = 0; i < size; ++i) {
                if (keyCodeArray.elementAt(i) == n) {
                    keyCodeArray.setElementAt(-1, i);
                    return;
                }
            }
        }
    }
    
    public abstract class AccessibleJComponent extends AccessibleContext implements Serializable, AccessibleComponent
    {
        protected ContainerListener accessibleContainerHandler;
        
        protected AccessibleJComponent() {
            this.accessibleContainerHandler = null;
        }
        
        public void addFocusListener(final FocusListener focusListener) {
            JComponent.this.addFocusListener(focusListener);
        }
        
        public void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
            if (this.accessibleContainerHandler == null) {
                this.accessibleContainerHandler = new AccessibleContainerHandler();
                JComponent.this.addContainerListener(this.accessibleContainerHandler);
            }
            super.addPropertyChangeListener(propertyChangeListener);
        }
        
        public boolean contains(final Point point) {
            return JComponent.this.contains(point);
        }
        
        public Accessible getAccessibleAt(final Point point) {
            return SwingUtilities.getAccessibleAt(JComponent.this, point);
        }
        
        public Accessible getAccessibleChild(final int n) {
            if (JComponent.this.ui != null) {
                return JComponent.this.ui.getAccessibleChild(JComponent.this, n);
            }
            return SwingUtilities.getAccessibleChild(JComponent.this, n);
        }
        
        public int getAccessibleChildrenCount() {
            if (JComponent.this.ui != null) {
                return JComponent.this.ui.getAccessibleChildrenCount(JComponent.this);
            }
            return SwingUtilities.getAccessibleChildrenCount(JComponent.this);
        }
        
        public AccessibleComponent getAccessibleComponent() {
            return this;
        }
        
        public String getAccessibleDescription() {
            String s = super.accessibleDescription;
            if (s == null) {
                try {
                    s = JComponent.this.getToolTipText(null);
                }
                catch (Exception ex) {}
            }
            if (s == null) {
                final Object clientProperty = JComponent.this.getClientProperty("labeledBy");
                if (clientProperty instanceof Accessible) {
                    final AccessibleContext accessibleContext = ((Accessible)clientProperty).getAccessibleContext();
                    if (accessibleContext != null) {
                        s = accessibleContext.getAccessibleDescription();
                    }
                }
            }
            return s;
        }
        
        public int getAccessibleIndexInParent() {
            return SwingUtilities.getAccessibleIndexInParent(JComponent.this);
        }
        
        public String getAccessibleName() {
            String s = super.accessibleName;
            if (s == null) {
                s = this.getBorderTitle(JComponent.this.getBorder());
            }
            if (s == null) {
                final Object clientProperty = JComponent.this.getClientProperty("labeledBy");
                if (clientProperty instanceof Accessible) {
                    final AccessibleContext accessibleContext = ((Accessible)clientProperty).getAccessibleContext();
                    if (accessibleContext != null) {
                        s = accessibleContext.getAccessibleName();
                    }
                }
            }
            return s;
        }
        
        public Accessible getAccessibleParent() {
            if (super.accessibleParent != null) {
                return super.accessibleParent;
            }
            final Container parent = JComponent.this.getParent();
            if (parent instanceof Accessible) {
                return (Accessible)parent;
            }
            return null;
        }
        
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.SWING_COMPONENT;
        }
        
        public AccessibleStateSet getAccessibleStateSet() {
            return SwingUtilities.getAccessibleStateSet(JComponent.this);
        }
        
        public Color getBackground() {
            return JComponent.this.getBackground();
        }
        
        protected String getBorderTitle(final Border border) {
            if (border instanceof TitledBorder) {
                return ((TitledBorder)border).getTitle();
            }
            if (border instanceof CompoundBorder) {
                String s = this.getBorderTitle(((CompoundBorder)border).getInsideBorder());
                if (s == null) {
                    s = this.getBorderTitle(((CompoundBorder)border).getOutsideBorder());
                }
                return s;
            }
            return null;
        }
        
        public Rectangle getBounds() {
            return JComponent.this.getBounds();
        }
        
        public Cursor getCursor() {
            return JComponent.this.getCursor();
        }
        
        public Font getFont() {
            return JComponent.this.getFont();
        }
        
        public FontMetrics getFontMetrics(final Font font) {
            return JComponent.this.getFontMetrics(font);
        }
        
        public Color getForeground() {
            return JComponent.this.getForeground();
        }
        
        public Locale getLocale() {
            return JComponent.this.getLocale();
        }
        
        public Point getLocation() {
            return JComponent.this.getLocation();
        }
        
        public Point getLocationOnScreen() {
            if (JComponent.this.isShowing()) {
                return JComponent.this.getLocationOnScreen();
            }
            return null;
        }
        
        public Dimension getSize() {
            return JComponent.this.getSize();
        }
        
        public boolean isEnabled() {
            return JComponent.this.isEnabled();
        }
        
        public boolean isFocusTraversable() {
            return JComponent.this.isFocusTraversable();
        }
        
        public boolean isShowing() {
            return JComponent.this.isShowing();
        }
        
        public boolean isVisible() {
            return JComponent.this.isVisible();
        }
        
        public void removeFocusListener(final FocusListener focusListener) {
            JComponent.this.removeFocusListener(focusListener);
        }
        
        public void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
            if (this.accessibleContainerHandler == null) {
                JComponent.this.removeContainerListener(this.accessibleContainerHandler);
            }
            super.removePropertyChangeListener(propertyChangeListener);
        }
        
        public void requestFocus() {
            JComponent.this.requestFocus();
        }
        
        public void setBackground(final Color background) {
            JComponent.this.setBackground(background);
        }
        
        public void setBounds(final Rectangle bounds) {
            JComponent.this.setBounds(bounds);
        }
        
        public void setCursor(final Cursor cursor) {
            JComponent.this.setCursor(cursor);
        }
        
        public void setEnabled(final boolean enabled) {
            final boolean enabled2 = JComponent.this.isEnabled();
            JComponent.this.setEnabled(enabled);
            if (enabled != enabled2 && JComponent.this.accessibleContext != null) {
                if (enabled) {
                    JComponent.this.accessibleContext.firePropertyChange("AccessibleState", null, AccessibleState.ENABLED);
                }
                else {
                    JComponent.this.accessibleContext.firePropertyChange("AccessibleState", AccessibleState.ENABLED, null);
                }
            }
        }
        
        public void setFont(final Font font) {
            JComponent.this.setFont(font);
        }
        
        public void setForeground(final Color foreground) {
            JComponent.this.setForeground(foreground);
        }
        
        public void setLocation(final Point location) {
            JComponent.this.setLocation(location);
        }
        
        public void setSize(final Dimension size) {
            JComponent.this.setSize(size);
        }
        
        public void setVisible(final boolean visible) {
            final boolean visible2 = JComponent.this.isVisible();
            JComponent.this.setVisible(visible);
            if (visible != visible2 && JComponent.this.accessibleContext != null) {
                if (visible) {
                    JComponent.this.accessibleContext.firePropertyChange("AccessibleState", null, AccessibleState.VISIBLE);
                }
                else {
                    JComponent.this.accessibleContext.firePropertyChange("AccessibleState", AccessibleState.VISIBLE, null);
                }
            }
        }
        
        protected class AccessibleContainerHandler implements ContainerListener
        {
            public void componentAdded(final ContainerEvent containerEvent) {
                final Component child = containerEvent.getChild();
                if (child != null && child instanceof Accessible) {
                    AccessibleJComponent.this.firePropertyChange("AccessibleChild", null, ((Accessible)child).getAccessibleContext());
                }
            }
            
            public void componentRemoved(final ContainerEvent containerEvent) {
                final Component child = containerEvent.getChild();
                if (child != null && child instanceof Accessible) {
                    AccessibleJComponent.this.firePropertyChange("AccessibleChild", ((Accessible)child).getAccessibleContext(), null);
                }
            }
        }
    }
    
    private class EnableSerializationFocusListener implements FocusListener, Serializable
    {
        public void focusGained(final FocusEvent focusEvent) {
        }
        
        public void focusLost(final FocusEvent focusEvent) {
        }
        
        private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
            boolean b = false;
            boolean rootPaneCheckingEnabled = false;
            JInternalFrame internalFrame = null;
            objectOutputStream.defaultWriteObject();
            if (JComponent.this.ui != null) {
                if (JComponent.this instanceof JInternalFrame) {
                    internalFrame = (JInternalFrame)JComponent.this;
                    b = true;
                    rootPaneCheckingEnabled = internalFrame.isRootPaneCheckingEnabled();
                    internalFrame.setRootPaneCheckingEnabled(false);
                }
                JComponent.this.ui.uninstallUI(JComponent.this);
                if (b) {
                    internalFrame.setRootPaneCheckingEnabled(rootPaneCheckingEnabled);
                }
            }
            if (JComponent.this.getToolTipText() != null) {
                ToolTipManager.sharedInstance().unregisterComponent(JComponent.this);
            }
        }
    }
    
    private class ReadObjectCallback implements ObjectInputValidation
    {
        private final Vector roots;
        private final ObjectInputStream inputStream;
        
        ReadObjectCallback(final ObjectInputStream inputStream) throws Exception {
            this.roots = new Vector(1);
            (this.inputStream = inputStream).registerValidation(this, 0);
        }
        
        private void registerComponent(final JComponent component) {
            for (int i = 0; i < this.roots.size(); ++i) {
                final JComponent component2 = this.roots.elementAt(i);
                for (Container parent = component; parent != null; parent = parent.getParent()) {
                    if (parent == component2) {
                        return;
                    }
                }
            }
            for (int j = 0; j < this.roots.size(); ++j) {
                for (Container container = this.roots.elementAt(j).getParent(); container != null; container = container.getParent()) {
                    if (container == component) {
                        this.roots.removeElementAt(j--);
                        break;
                    }
                }
            }
            this.roots.addElement(component);
        }
        
        public void validateObject() throws InvalidObjectException {
            try {
                for (int i = 0; i < this.roots.size(); ++i) {
                    SwingUtilities.updateComponentTreeUI((Component)this.roots.elementAt(i));
                }
            }
            finally {
                JComponent.readObjectCallbacks.remove(this.inputStream);
            }
        }
    }
}
