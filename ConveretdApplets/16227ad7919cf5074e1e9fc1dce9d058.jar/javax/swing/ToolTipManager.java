// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.awt.Window;
import java.awt.Panel;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionListener;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseAdapter;

public class ToolTipManager extends MouseAdapter implements MouseMotionListener
{
    Timer enterTimer;
    Timer exitTimer;
    Timer insideTimer;
    String toolTipText;
    Point preferredLocation;
    JComponent insideComponent;
    MouseEvent mouseEvent;
    boolean showImmediately;
    static final ToolTipManager sharedInstance;
    Popup tipWindow;
    JToolTip tip;
    private Rectangle popupRect;
    private Rectangle popupFrameRect;
    boolean enabled;
    boolean mouseAboveToolTip;
    private boolean tipShowing;
    private long timerEnter;
    private KeyStroke postTip;
    private KeyStroke hideTip;
    private ActionListener postTipAction;
    private ActionListener hideTipAction;
    private FocusListener focusChangeListener;
    protected boolean lightWeightPopupEnabled;
    protected boolean heavyWeightPopupEnabled;
    
    static {
        sharedInstance = new ToolTipManager();
    }
    
    ToolTipManager() {
        this.popupRect = null;
        this.popupFrameRect = null;
        this.enabled = true;
        this.mouseAboveToolTip = false;
        this.tipShowing = false;
        this.timerEnter = 0L;
        this.focusChangeListener = null;
        this.lightWeightPopupEnabled = true;
        this.heavyWeightPopupEnabled = false;
        (this.enterTimer = new Timer(750, new insideTimerAction())).setRepeats(false);
        (this.exitTimer = new Timer(500, new outsideTimerAction())).setRepeats(false);
        (this.insideTimer = new Timer(4000, new stillInsideTimerAction())).setRepeats(false);
        this.postTip = KeyStroke.getKeyStroke(112, 2);
        this.postTipAction = new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (ToolTipManager.this.tipWindow != null) {
                    ToolTipManager.this.hideTipWindow();
                }
                else {
                    ToolTipManager.this.hideTipWindow();
                    ToolTipManager.this.enterTimer.stop();
                    ToolTipManager.this.exitTimer.stop();
                    ToolTipManager.this.insideTimer.stop();
                    ToolTipManager.this.insideComponent = (JComponent)actionEvent.getSource();
                    if (ToolTipManager.this.insideComponent != null) {
                        ToolTipManager.this.toolTipText = ToolTipManager.this.insideComponent.getToolTipText();
                        ToolTipManager.this.preferredLocation = new Point(10, ToolTipManager.this.insideComponent.getHeight() + 10);
                        ToolTipManager.this.showTipWindow();
                        if (ToolTipManager.this.focusChangeListener == null) {
                            ToolTipManager.access$2(ToolTipManager.this, ToolTipManager.this.createFocusChangeListener());
                        }
                        ToolTipManager.this.insideComponent.addFocusListener(ToolTipManager.this.focusChangeListener);
                    }
                }
            }
        };
        this.hideTip = KeyStroke.getKeyStroke(27, 0);
        this.hideTipAction = new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                ToolTipManager.this.hideTipWindow();
                ((JComponent)actionEvent.getSource()).removeFocusListener(ToolTipManager.this.focusChangeListener);
                ToolTipManager.this.preferredLocation = null;
            }
        };
    }
    
    static /* synthetic */ void access$2(final ToolTipManager toolTipManager, final FocusListener focusChangeListener) {
        toolTipManager.focusChangeListener = focusChangeListener;
    }
    
    private FocusListener createFocusChangeListener() {
        return new FocusAdapter() {
            public void focusLost(final FocusEvent focusEvent) {
                ToolTipManager.this.hideTipWindow();
                ((JComponent)focusEvent.getSource()).removeFocusListener(ToolTipManager.this.focusChangeListener);
            }
        };
    }
    
    static Frame frameForComponent(Component parent) {
        while (!(parent instanceof Frame)) {
            parent = parent.getParent();
        }
        return (Frame)parent;
    }
    
    public int getDismissDelay() {
        return this.insideTimer.getInitialDelay();
    }
    
    private int getHeightAdjust(final Rectangle rectangle, final Rectangle rectangle2) {
        if (rectangle2.y >= rectangle.y && rectangle2.y + rectangle2.height <= rectangle.y + rectangle.height) {
            return 0;
        }
        return rectangle2.y + rectangle2.height - (rectangle.y + rectangle.height) + 5;
    }
    
    public int getInitialDelay() {
        return this.enterTimer.getInitialDelay();
    }
    
    private int getPopupFitHeight(final Rectangle rectangle, final Component component) {
        if (component != null) {
            for (Container container = component.getParent(); container != null; container = container.getParent()) {
                if (container instanceof JFrame || container instanceof JDialog || container instanceof JWindow) {
                    return this.getHeightAdjust(container.getBounds(), rectangle);
                }
                if (container instanceof JApplet || container instanceof JInternalFrame) {
                    if (this.popupFrameRect == null) {
                        this.popupFrameRect = new Rectangle();
                    }
                    final Point locationOnScreen = container.getLocationOnScreen();
                    this.popupFrameRect.setBounds(locationOnScreen.x, locationOnScreen.y, container.getBounds().width, container.getBounds().height);
                    return this.getHeightAdjust(this.popupFrameRect, rectangle);
                }
            }
        }
        return 0;
    }
    
    private int getPopupFitWidth(final Rectangle rectangle, final Component component) {
        if (component != null) {
            for (Container container = component.getParent(); container != null; container = container.getParent()) {
                if (container instanceof JFrame || container instanceof JDialog || container instanceof JWindow) {
                    return this.getWidthAdjust(container.getBounds(), rectangle);
                }
                if (container instanceof JApplet || container instanceof JInternalFrame) {
                    if (this.popupFrameRect == null) {
                        this.popupFrameRect = new Rectangle();
                    }
                    final Point locationOnScreen = container.getLocationOnScreen();
                    this.popupFrameRect.setBounds(locationOnScreen.x, locationOnScreen.y, container.getBounds().width, container.getBounds().height);
                    return this.getWidthAdjust(this.popupFrameRect, rectangle);
                }
            }
        }
        return 0;
    }
    
    public int getReshowDelay() {
        return this.exitTimer.getInitialDelay();
    }
    
    private int getWidthAdjust(final Rectangle rectangle, final Rectangle rectangle2) {
        if (rectangle2.x >= rectangle.x && rectangle2.x + rectangle2.width <= rectangle.x + rectangle.width) {
            return 0;
        }
        return rectangle2.x + rectangle2.width - (rectangle.x + rectangle.width) + 5;
    }
    
    void hideTipWindow() {
        if (this.tipWindow != null) {
            this.tipWindow.removeMouseListener(this);
            this.tipWindow.hide();
            this.tipWindow = null;
            this.tipShowing = false;
            this.timerEnter = 0L;
            this.tip.getUI().uninstallUI(this.tip);
            this.tip = null;
            this.insideTimer.stop();
        }
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public boolean isLightWeightPopupEnabled() {
        return this.lightWeightPopupEnabled;
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (this.tipShowing && !this.lightWeightPopupEnabled && System.currentTimeMillis() - this.timerEnter < 200L) {
            return;
        }
        if (mouseEvent.getSource() == this.tipWindow) {
            return;
        }
        final JComponent insideComponent = (JComponent)mouseEvent.getSource();
        this.toolTipText = insideComponent.getToolTipText(mouseEvent);
        this.preferredLocation = insideComponent.getToolTipLocation(mouseEvent);
        this.exitTimer.stop();
        final Point point = mouseEvent.getPoint();
        if (point.x < 0 || point.x >= insideComponent.getWidth() || point.y < 0 || point.y >= insideComponent.getHeight()) {
            return;
        }
        if (this.insideComponent != null) {
            this.enterTimer.stop();
            this.insideComponent = null;
        }
        insideComponent.addMouseMotionListener(this);
        this.insideComponent = insideComponent;
        if (this.tipWindow != null) {
            if (this.heavyWeightPopupEnabled) {
                return;
            }
            this.mouseEvent = mouseEvent;
            if (this.showImmediately) {
                this.showTipWindow();
            }
            else {
                this.enterTimer.start();
            }
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (this.tipShowing && !this.lightWeightPopupEnabled && System.currentTimeMillis() - this.timerEnter < 200L) {
            return;
        }
        boolean b = true;
        if (mouseEvent.getSource() == this.tipWindow) {
            final Container topLevelAncestor = this.insideComponent.getTopLevelAncestor();
            final Rectangle bounds = this.tipWindow.getBounds();
            final Point point2;
            final Point point = point2 = mouseEvent.getPoint();
            point2.x += bounds.x;
            final Point point3 = point;
            point3.y += bounds.y;
            final Rectangle bounds2 = topLevelAncestor.getBounds();
            final Point point4 = point;
            point4.x -= bounds2.x;
            final Point point5 = point;
            point5.y -= bounds2.y;
            final Point convertPoint = SwingUtilities.convertPoint(null, point, this.insideComponent);
            b = (convertPoint.x < 0 || convertPoint.x >= this.insideComponent.getWidth() || convertPoint.y < 0 || convertPoint.y >= this.insideComponent.getHeight());
        }
        else if (mouseEvent.getSource() == this.insideComponent && this.tipWindow != null) {
            final Point convertPoint2 = SwingUtilities.convertPoint(this.insideComponent, mouseEvent.getPoint(), null);
            final Rectangle bounds3 = this.insideComponent.getTopLevelAncestor().getBounds();
            final Point point6 = convertPoint2;
            point6.x += bounds3.x;
            final Point point7 = convertPoint2;
            point7.y += bounds3.y;
            final Rectangle bounds4 = this.tipWindow.getBounds();
            b = (convertPoint2.x < bounds4.x || convertPoint2.x >= bounds4.x + bounds4.width || convertPoint2.y < bounds4.y || convertPoint2.y >= bounds4.y + bounds4.height);
        }
        if (b) {
            this.enterTimer.stop();
            if (this.insideComponent != null) {
                this.insideComponent.removeMouseMotionListener(this);
            }
            this.insideComponent = null;
            this.toolTipText = null;
            this.mouseEvent = null;
            this.hideTipWindow();
            this.exitTimer.start();
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final JComponent component = (JComponent)mouseEvent.getSource();
        final String toolTipText = component.getToolTipText(mouseEvent);
        final Point toolTipLocation = component.getToolTipLocation(mouseEvent);
        if (toolTipText != null || toolTipLocation != null) {
            this.mouseEvent = mouseEvent;
            if (((toolTipText != null && toolTipText.equals(this.toolTipText)) || toolTipText == null) && ((toolTipLocation != null && toolTipLocation.equals(this.preferredLocation)) || toolTipLocation == null)) {
                if (this.tipWindow != null) {
                    this.insideTimer.restart();
                }
                else {
                    this.enterTimer.restart();
                }
            }
            else {
                this.toolTipText = toolTipText;
                this.preferredLocation = toolTipLocation;
                if (this.showImmediately) {
                    this.hideTipWindow();
                    this.showTipWindow();
                }
                else {
                    this.enterTimer.restart();
                }
            }
        }
        else {
            this.toolTipText = null;
            this.preferredLocation = null;
            this.mouseEvent = null;
            this.hideTipWindow();
            this.enterTimer.stop();
            this.exitTimer.start();
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.hideTipWindow();
        this.enterTimer.stop();
        this.showImmediately = false;
    }
    
    public void registerComponent(final JComponent component) {
        component.removeMouseListener(this);
        component.addMouseListener(this);
        component.registerKeyboardAction(this.postTipAction, this.postTip, 0);
        component.registerKeyboardAction(this.hideTipAction, this.hideTip, 0);
    }
    
    public void setDismissDelay(final int initialDelay) {
        this.insideTimer.setInitialDelay(initialDelay);
    }
    
    public void setEnabled(final boolean enabled) {
        if (!(this.enabled = enabled)) {
            this.hideTipWindow();
        }
    }
    
    public void setInitialDelay(final int initialDelay) {
        this.enterTimer.setInitialDelay(initialDelay);
    }
    
    public void setLightWeightPopupEnabled(final boolean lightWeightPopupEnabled) {
        this.lightWeightPopupEnabled = lightWeightPopupEnabled;
    }
    
    public void setReshowDelay(final int initialDelay) {
        this.exitTimer.setInitialDelay(initialDelay);
    }
    
    public static ToolTipManager sharedInstance() {
        return ToolTipManager.sharedInstance;
    }
    
    void showTipWindow() {
        if (this.insideComponent == null || !this.insideComponent.isShowing()) {
            return;
        }
        if (this.enabled) {
            final Point locationOnScreen = this.insideComponent.getLocationOnScreen();
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            final Point point = new Point();
            this.hideTipWindow();
            (this.tip = this.insideComponent.createToolTip()).setTipText(this.toolTipText);
            final Dimension preferredSize = this.tip.getPreferredSize();
            if (this.insideComponent.getRootPane() == null) {
                this.tipWindow = (Popup)new WindowPopup(frameForComponent(this.insideComponent), this.tip, preferredSize);
                this.heavyWeightPopupEnabled = true;
            }
            else if (this.lightWeightPopupEnabled) {
                this.heavyWeightPopupEnabled = false;
                this.tipWindow = (Popup)new JPanelPopup(this.tip, preferredSize);
            }
            else {
                this.heavyWeightPopupEnabled = false;
                this.tipWindow = (Popup)new PanelPopup(this.tip, preferredSize);
            }
            this.tipWindow.addMouseListener(this);
            if (this.preferredLocation != null) {
                point.x = locationOnScreen.x + this.preferredLocation.x;
                point.y = locationOnScreen.y + this.preferredLocation.y;
            }
            else {
                point.x = locationOnScreen.x + this.mouseEvent.getX();
                point.y = locationOnScreen.y + this.mouseEvent.getY() + 20;
                if (point.x + preferredSize.width > screenSize.width) {
                    final Point point2 = point;
                    point2.x -= preferredSize.width;
                }
                if (point.y + preferredSize.height > screenSize.height) {
                    final Point point3 = point;
                    point3.y -= preferredSize.height + 20;
                }
            }
            if (!this.heavyWeightPopupEnabled) {
                if (this.popupRect == null) {
                    this.popupRect = new Rectangle();
                }
                this.popupRect.setBounds(point.x, point.y, this.tipWindow.getBounds().width, this.tipWindow.getBounds().height);
                final int popupFitHeight = this.getPopupFitHeight(this.popupRect, this.insideComponent);
                final int popupFitWidth = this.getPopupFitWidth(this.popupRect, this.insideComponent);
                if (popupFitHeight > 0) {
                    final Point point4 = point;
                    point4.y -= popupFitHeight;
                }
                if (popupFitWidth > 0) {
                    final Point point5 = point;
                    point5.x -= popupFitWidth;
                }
            }
            this.tipWindow.show(this.insideComponent, point.x, point.y);
            this.insideTimer.start();
            this.timerEnter = System.currentTimeMillis();
            this.tipShowing = true;
        }
    }
    
    public void unregisterComponent(final JComponent component) {
        component.removeMouseListener(this);
        component.unregisterKeyboardAction(this.postTip);
        component.unregisterKeyboardAction(this.hideTip);
    }
    
    protected class insideTimerAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            if (ToolTipManager.this.insideComponent != null && ToolTipManager.this.insideComponent.isShowing()) {
                ToolTipManager.this.showImmediately = true;
                ToolTipManager.this.showTipWindow();
            }
        }
    }
    
    protected class outsideTimerAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            ToolTipManager.this.showImmediately = false;
        }
    }
    
    protected class stillInsideTimerAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            ToolTipManager.this.hideTipWindow();
            ToolTipManager.this.enterTimer.stop();
            ToolTipManager.this.showImmediately = false;
        }
    }
    
    class JPanelPopup extends JPanel implements Popup
    {
        public JPanelPopup(final JComponent component, final Dimension size) {
            this.setLayout(new BorderLayout());
            this.setDoubleBuffered(true);
            this.setOpaque(true);
            this.add(component, "Center");
            this.setSize(size);
        }
        
        public void addMouseListener(final ToolTipManager toolTipManager) {
            super.addMouseListener(toolTipManager);
        }
        
        public Rectangle getBounds() {
            return super.getBounds();
        }
        
        public void hide() {
            final Container parent = this.getParent();
            final Rectangle bounds = this.getBounds();
            if (parent != null) {
                parent.remove(this);
                parent.repaint(bounds.x, bounds.y, bounds.width, bounds.height);
            }
        }
        
        public void removeMouseListener(final ToolTipManager toolTipManager) {
            super.removeMouseListener(toolTipManager);
        }
        
        public void show(final JComponent component, final int n, final int n2) {
            final Point point = new Point(n, n2);
            SwingUtilities.convertPointFromScreen(point, component.getRootPane().getLayeredPane());
            this.setBounds(point.x, point.y, this.getSize().width, this.getSize().height);
            component.getRootPane().getLayeredPane().add(this, JLayeredPane.POPUP_LAYER, 0);
        }
        
        public void update(final Graphics graphics) {
            this.paint(graphics);
        }
    }
    
    class PanelPopup extends Panel implements Popup
    {
        public PanelPopup(final JComponent component, final Dimension size) {
            this.setLayout(new BorderLayout());
            this.add(component, "Center");
            this.setSize(size);
        }
        
        public void addMouseListener(final ToolTipManager toolTipManager) {
            super.addMouseListener(toolTipManager);
        }
        
        public Rectangle getBounds() {
            return super.getBounds();
        }
        
        public void hide() {
            final Container parent = this.getParent();
            final Rectangle bounds = this.getBounds();
            if (parent != null) {
                parent.remove(this);
                parent.repaint(bounds.x, bounds.y, bounds.width, bounds.height);
            }
        }
        
        public void removeMouseListener(final ToolTipManager toolTipManager) {
            super.removeMouseListener(toolTipManager);
        }
        
        public void show(final JComponent component, final int n, final int n2) {
            final Point point = new Point(n, n2);
            SwingUtilities.convertPointFromScreen(point, component.getRootPane().getLayeredPane());
            component.getRootPane().getLayeredPane().add(this, JLayeredPane.POPUP_LAYER, 0);
            this.setBounds(point.x, point.y, this.getSize().width, this.getSize().height);
        }
    }
    
    class WindowPopup extends Window implements Popup
    {
        boolean firstShow;
        JComponent tip;
        Frame frame;
        
        public WindowPopup(final Frame frame, final JComponent tip, final Dimension dimension) {
            super(frame);
            this.firstShow = true;
            this.tip = tip;
            this.frame = frame;
            this.add(tip, "Center");
            this.pack();
        }
        
        public void addMouseListener(final ToolTipManager toolTipManager) {
            super.addMouseListener(toolTipManager);
        }
        
        public Rectangle getBounds() {
            return super.getBounds();
        }
        
        public void hide() {
            super.hide();
            this.removeNotify();
        }
        
        public void removeMouseListener(final ToolTipManager toolTipManager) {
            super.removeMouseListener(toolTipManager);
        }
        
        public void show(final JComponent component, final int n, final int n2) {
            this.setLocation(n, n2);
            this.setVisible(true);
            if (this.firstShow) {
                this.hide();
                this.setVisible(true);
                this.firstShow = false;
            }
        }
    }
    
    private interface Popup
    {
        void addMouseListener(final ToolTipManager p0);
        
        Rectangle getBounds();
        
        void hide();
        
        void removeMouseListener(final ToolTipManager p0);
        
        void show(final JComponent p0, final int p1, final int p2);
    }
}
