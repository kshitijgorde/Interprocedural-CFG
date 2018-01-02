// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import java.awt.Image;
import java.awt.image.ImageObserver;
import javax.swing.JFrame;
import java.awt.Container;
import java.beans.PropertyChangeEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dialog;
import javax.swing.plaf.UIResource;
import java.awt.LayoutManager;
import de.muntjak.tinylookandfeel.controlpanel.ControlPanel;
import javax.swing.plaf.ButtonUI;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.AWTEvent;
import java.awt.event.WindowEvent;
import javax.swing.UIManager;
import java.awt.Frame;
import java.awt.Component;
import javax.swing.SwingUtilities;
import javax.swing.JRootPane;
import java.awt.Window;
import java.awt.event.ComponentListener;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.Action;
import javax.swing.JMenuBar;
import java.beans.PropertyChangeListener;
import javax.swing.JComponent;

public class TinyTitlePane extends JComponent
{
    private static final int IMAGE_HEIGHT = 16;
    private static final int IMAGE_WIDTH = 16;
    private static TinyWindowButtonUI iconButtonUI;
    private static TinyWindowButtonUI maxButtonUI;
    private static TinyWindowButtonUI closeButtonUI;
    private PropertyChangeListener propertyChangeListener;
    private JMenuBar menuBar;
    private Action closeAction;
    private Action iconifyAction;
    private Action restoreAction;
    private Action maximizeAction;
    private JButton toggleButton;
    private JButton iconifyButton;
    private JButton closeButton;
    private WindowListener windowListener;
    private ComponentListener windowMoveListener;
    private Window window;
    private JRootPane rootPane;
    private int buttonsWidth;
    private int state;
    private TinyRootPaneUI rootPaneUI;
    
    public TinyTitlePane(final JRootPane rootPane, final TinyRootPaneUI rootPaneUI) {
        this.rootPane = rootPane;
        this.rootPaneUI = rootPaneUI;
        this.state = -1;
        this.installSubcomponents();
        this.installDefaults();
        this.setLayout(this.createLayout());
    }
    
    private void uninstall() {
        this.uninstallListeners();
        this.window = null;
        this.removeAll();
    }
    
    private void installListeners() {
        if (this.window != null) {
            this.windowListener = this.createWindowListener();
            this.window.addWindowListener(this.windowListener);
            this.propertyChangeListener = this.createWindowPropertyChangeListener();
            this.window.addPropertyChangeListener(this.propertyChangeListener);
            this.windowMoveListener = new WindowMoveListener();
            this.window.addComponentListener(this.windowMoveListener);
        }
    }
    
    private void uninstallListeners() {
        if (this.window != null) {
            this.window.removeWindowListener(this.windowListener);
            this.window.removePropertyChangeListener(this.propertyChangeListener);
            this.window.removeComponentListener(this.windowMoveListener);
        }
    }
    
    private WindowListener createWindowListener() {
        return new WindowHandler();
    }
    
    private PropertyChangeListener createWindowPropertyChangeListener() {
        return new PropertyChangeHandler();
    }
    
    public JRootPane getRootPane() {
        return this.rootPane;
    }
    
    private int getWindowDecorationStyle() {
        return this.getRootPane().getWindowDecorationStyle();
    }
    
    public void addNotify() {
        super.addNotify();
        this.uninstallListeners();
        this.window = SwingUtilities.getWindowAncestor(this);
        if (this.window != null) {
            if (this.window instanceof Frame) {
                this.setState(((Frame)this.window).getExtendedState());
            }
            else {
                this.setState(0);
            }
            this.setActive(this.window.isActive());
            this.installListeners();
        }
    }
    
    public void removeNotify() {
        super.removeNotify();
        this.uninstallListeners();
        this.window = null;
    }
    
    private void installSubcomponents() {
        if (this.getWindowDecorationStyle() == 1) {
            this.createActions();
            this.add(this.menuBar = this.createMenuBar());
            this.createButtons();
            this.add(this.iconifyButton);
            this.add(this.toggleButton);
            this.add(this.closeButton);
            this.iconifyButton.putClientProperty("externalFrameButton", Boolean.TRUE);
            this.toggleButton.putClientProperty("externalFrameButton", Boolean.TRUE);
            this.closeButton.putClientProperty("externalFrameButton", Boolean.TRUE);
        }
        else if (this.getWindowDecorationStyle() != 0) {
            this.createActions();
            this.createButtons();
            this.add(this.closeButton);
            this.closeButton.putClientProperty("externalFrameButton", Boolean.FALSE);
        }
    }
    
    private void installDefaults() {
        this.setFont(UIManager.getFont("InternalFrame.titleFont", this.getLocale()));
    }
    
    private void uninstallDefaults() {
    }
    
    protected JMenuBar createMenuBar() {
        (this.menuBar = new SystemMenuBar()).setFocusable(false);
        this.menuBar.setBorderPainted(true);
        this.menuBar.add(this.createMenu());
        return this.menuBar;
    }
    
    private void close() {
        final Window window = this.getWindow();
        if (window != null) {
            window.dispatchEvent(new WindowEvent(window, 201));
        }
    }
    
    private void iconify() {
        final Frame frame = this.getFrame();
        if (frame != null) {
            frame.setExtendedState(frame.getExtendedState() | 0x1);
        }
    }
    
    private void maximize() {
        final Frame frame = this.getFrame();
        if (frame != null) {
            this.setMaximizeBounds(frame);
            frame.setExtendedState(frame.getExtendedState() | 0x6);
        }
    }
    
    protected void setMaximizeBounds(final Frame frame) {
        if (frame.getMaximizedBounds() != null) {
            return;
        }
        final Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(frame.getGraphicsConfiguration());
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int top = screenInsets.top;
        final int left = screenInsets.left;
        frame.setMaximizedBounds(new Rectangle(top, left, screenSize.width - top - screenInsets.right, screenSize.height - left - screenInsets.bottom));
    }
    
    private void restore() {
        final Frame frame = this.getFrame();
        if (frame == null) {
            return;
        }
        if ((frame.getExtendedState() & 0x1) == 0x1) {
            frame.setExtendedState(this.state & 0xFFFFFFFE);
        }
        else {
            frame.setExtendedState(this.state & 0xFFFFFFF9);
        }
    }
    
    private void createActions() {
        this.closeAction = new CloseAction();
        this.iconifyAction = new IconifyAction();
        this.restoreAction = new RestoreAction();
        this.maximizeAction = new MaximizeAction();
    }
    
    private JMenu createMenu() {
        final JMenu menu = new JMenu("");
        if (this.getWindowDecorationStyle() == 1) {
            this.addMenuItems(menu);
            menu.putClientProperty("isSystemMenu", Boolean.TRUE);
        }
        return menu;
    }
    
    private void addMenuItems(final JMenu menu) {
        this.getRootPane().getLocale();
        final JMenuItem add = menu.add(this.restoreAction);
        final int int1 = this.getInt("MetalTitlePane.restoreMnemonic", -1);
        if (int1 != -1) {
            add.setMnemonic(int1);
        }
        final JMenuItem add2 = menu.add(this.iconifyAction);
        final int int2 = this.getInt("MetalTitlePane.iconifyMnemonic", -1);
        if (int2 != -1) {
            add2.setMnemonic(int2);
        }
        if (Toolkit.getDefaultToolkit().isFrameStateSupported(6)) {
            final JMenuItem add3 = menu.add(this.maximizeAction);
            final int int3 = this.getInt("MetalTitlePane.maximizeMnemonic", -1);
            if (int3 != -1) {
                add3.setMnemonic(int3);
            }
        }
        menu.addSeparator();
        final JMenuItem add4 = menu.add(this.closeAction);
        final int int4 = this.getInt("MetalTitlePane.closeMnemonic", -1);
        if (int4 != -1) {
            add4.setMnemonic(int4);
        }
    }
    
    protected void createButtons() {
        if (TinyTitlePane.iconButtonUI == null) {
            TinyTitlePane.iconButtonUI = TinyWindowButtonUI.createButtonUIForType(2);
            TinyTitlePane.maxButtonUI = TinyWindowButtonUI.createButtonUIForType(1);
            TinyTitlePane.closeButtonUI = TinyWindowButtonUI.createButtonUIForType(0);
        }
        (this.iconifyButton = new SpecialUIButton(TinyTitlePane.iconButtonUI)).setAction(this.iconifyAction);
        this.iconifyButton.setRolloverEnabled(true);
        (this.toggleButton = new SpecialUIButton(TinyTitlePane.maxButtonUI)).setAction(this.maximizeAction);
        this.toggleButton.setRolloverEnabled(true);
        (this.closeButton = new SpecialUIButton(TinyTitlePane.closeButtonUI)).setAction(this.closeAction);
        this.closeButton.setRolloverEnabled(true);
        this.closeButton.getAccessibleContext().setAccessibleName("Close");
        this.iconifyButton.getAccessibleContext().setAccessibleName("Iconify");
        this.toggleButton.getAccessibleContext().setAccessibleName("Maximize");
        if (TinyLookAndFeel.controlPanelInstantiated) {
            ControlPanel.setWindowButtons(new JButton[] { this.iconifyButton, this.toggleButton, this.closeButton });
        }
    }
    
    private LayoutManager createLayout() {
        return new TitlePaneLayout();
    }
    
    private void setActive(final boolean enabled) {
        if (this.getWindowDecorationStyle() == 1) {
            final Boolean b = enabled ? Boolean.TRUE : Boolean.FALSE;
            this.iconifyButton.putClientProperty("paintActive", b);
            this.closeButton.putClientProperty("paintActive", b);
            this.toggleButton.putClientProperty("paintActive", b);
            this.iconifyButton.setEnabled(enabled);
            this.closeButton.setEnabled(enabled);
            this.toggleButton.setEnabled(enabled);
        }
        this.getRootPane().repaint();
    }
    
    private void setState(final int n) {
        this.setState(n, false);
    }
    
    private void setState(final int state, final boolean b) {
        if (this.getWindow() != null && this.getWindowDecorationStyle() == 1) {
            if (this.state == state && !b) {
                return;
            }
            final Frame frame = this.getFrame();
            if (frame != null) {
                final JRootPane rootPane = this.getRootPane();
                if ((state & 0x6) != 0x6 || (rootPane.getBorder() != null && !(rootPane.getBorder() instanceof UIResource)) || !frame.isShowing()) {
                    if ((state & 0x6) != 0x6) {}
                }
                if (frame.isResizable()) {
                    if ((state & 0x4) == 0x4 || (state & 0x2) == 0x2) {
                        this.updateToggleButton(this.restoreAction);
                        this.maximizeAction.setEnabled(false);
                        this.restoreAction.setEnabled(true);
                    }
                    else {
                        this.updateToggleButton(this.maximizeAction);
                        this.maximizeAction.setEnabled(true);
                        this.restoreAction.setEnabled(false);
                    }
                    if (this.toggleButton.getParent() == null || this.iconifyButton.getParent() == null) {
                        this.add(this.toggleButton);
                        this.add(this.iconifyButton);
                        this.revalidate();
                        this.repaint();
                    }
                    this.toggleButton.setText(null);
                }
                else {
                    this.maximizeAction.setEnabled(false);
                    this.restoreAction.setEnabled(false);
                    if (this.toggleButton.getParent() != null) {
                        this.remove(this.toggleButton);
                        this.revalidate();
                        this.repaint();
                    }
                }
            }
            else {
                this.maximizeAction.setEnabled(false);
                this.restoreAction.setEnabled(false);
                this.iconifyAction.setEnabled(false);
                this.remove(this.toggleButton);
                this.remove(this.iconifyButton);
                this.revalidate();
                this.repaint();
            }
            this.closeAction.setEnabled(true);
            this.state = state;
        }
    }
    
    private void updateToggleButton(final Action action) {
        this.toggleButton.setAction(action);
        this.toggleButton.setText(null);
    }
    
    private Frame getFrame() {
        final Window window = this.getWindow();
        if (window instanceof Frame) {
            return (Frame)window;
        }
        return null;
    }
    
    private Window getWindow() {
        return this.window;
    }
    
    private String getTitle() {
        final Window window = this.getWindow();
        if (window instanceof Frame) {
            return ((Frame)window).getTitle();
        }
        if (window instanceof Dialog) {
            return ((Dialog)window).getTitle();
        }
        return null;
    }
    
    public boolean isSelected() {
        final Window window = this.getWindow();
        return window == null || window.isActive();
    }
    
    public boolean isFrameMaximized() {
        final Frame frame = this.getFrame();
        return frame != null && (frame.getExtendedState() & 0x6) == 0x6;
    }
    
    public void paintComponent(final Graphics graphics) {
        if (this.getFrame() != null) {
            this.setState(this.getFrame().getExtendedState());
        }
        final Window window = this.getWindow();
        final boolean b = (window == null) ? this.getRootPane().getComponentOrientation().isLeftToRight() : window.getComponentOrientation().isLeftToRight();
        final boolean b2 = window == null || window.isActive();
        final int width = this.getWidth();
        final int height = this.getHeight();
        int n = b ? 5 : (width - 5);
        if (this.getWindowDecorationStyle() == 1) {
            n += (b ? 21 : -21);
        }
        final String title = this.getTitle();
        if (title != null) {
            UIManager.getFont("InternalFrame.normalTitleFont");
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            final int n2 = (height - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();
            Rectangle bounds = new Rectangle(0, 0, 0, 0);
            if (this.iconifyButton != null && this.iconifyButton.getParent() != null) {
                bounds = this.iconifyButton.getBounds();
            }
            String s;
            if (b) {
                if (bounds.x == 0) {
                    bounds.x = window.getWidth() - window.getInsets().right - 2;
                }
                s = this.clippedText(title, fontMetrics, bounds.x - n - 4);
            }
            else {
                s = this.clippedText(title, fontMetrics, n - bounds.x - bounds.width - 4);
                n -= SwingUtilities.computeStringWidth(fontMetrics, s);
            }
            final int computeStringWidth = SwingUtilities.computeStringWidth(fontMetrics, s);
            if (b2) {
                graphics.setColor(Theme.frameTitleColor[Theme.style].getColor());
                graphics.drawString(s, n, n2);
            }
            else {
                graphics.setColor(Theme.frameTitleDisabledColor[Theme.style].getColor());
                graphics.drawString(s, n, n2);
            }
            final int n3 = n + (b ? (computeStringWidth + 5) : -5);
        }
    }
    
    private String clippedText(String string, final FontMetrics fontMetrics, final int n) {
        if (string == null || string.equals("")) {
            return "";
        }
        final int computeStringWidth = SwingUtilities.computeStringWidth(fontMetrics, string);
        final String s = "...";
        if (computeStringWidth > n) {
            int computeStringWidth2 = SwingUtilities.computeStringWidth(fontMetrics, s);
            int i;
            for (i = 0; i < string.length(); ++i) {
                computeStringWidth2 += fontMetrics.charWidth(string.charAt(i));
                if (computeStringWidth2 > n) {
                    break;
                }
            }
            string = string.substring(0, i) + s;
        }
        return string;
    }
    
    private int getInt(final Object o, final int n) {
        final Object value = UIManager.get(o);
        if (value instanceof Integer) {
            return (int)value;
        }
        if (value instanceof String) {
            try {
                return Integer.parseInt((String)value);
            }
            catch (NumberFormatException ex) {}
        }
        return n;
    }
    
    class WindowMoveListener extends ComponentAdapter
    {
        public void componentMoved(final ComponentEvent componentEvent) {
            if (TinyTitlePane.this.getWindowDecorationStyle() == 0) {
                return;
            }
            final Window access$1800 = TinyTitlePane.this.getWindow();
            if (!access$1800.isShowing()) {
                return;
            }
            access$1800.repaint(0, 0, access$1800.getWidth(), 5);
        }
        
        public void componentResized(final ComponentEvent componentEvent) {
            if (TinyTitlePane.this.getWindowDecorationStyle() == 0) {
                return;
            }
            final Window access$1800 = TinyTitlePane.this.getWindow();
            if (!access$1800.isShowing()) {
                return;
            }
            access$1800.repaint(0, 0, access$1800.getWidth(), 5);
        }
    }
    
    private class WindowHandler extends WindowAdapter
    {
        public void windowActivated(final WindowEvent windowEvent) {
            TinyTitlePane.this.setActive(true);
        }
        
        public void windowDeactivated(final WindowEvent windowEvent) {
            TinyTitlePane.this.setActive(false);
        }
    }
    
    private class PropertyChangeHandler implements PropertyChangeListener
    {
        public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
            final String propertyName = propertyChangeEvent.getPropertyName();
            if ("resizable".equals(propertyName) || "state".equals(propertyName)) {
                final Frame access$800 = TinyTitlePane.this.getFrame();
                if (access$800 != null) {
                    TinyTitlePane.this.setState(access$800.getExtendedState(), true);
                }
                if ("resizable".equals(propertyName)) {
                    TinyTitlePane.this.getRootPane().repaint();
                }
            }
            else if ("title".equals(propertyName)) {
                TinyTitlePane.this.repaint();
            }
            else if ("componentOrientation".equals(propertyName)) {
                TinyTitlePane.this.revalidate();
                TinyTitlePane.this.repaint();
            }
        }
    }
    
    private class TitlePaneLayout implements LayoutManager
    {
        public void addLayoutComponent(final String s, final Component component) {
        }
        
        public void removeLayoutComponent(final Component component) {
        }
        
        public Dimension preferredLayoutSize(final Container container) {
            return new Dimension(104, this.computeHeight());
        }
        
        public Dimension minimumLayoutSize(final Container container) {
            return this.preferredLayoutSize(container);
        }
        
        private int computeHeight() {
            if (TinyTitlePane.this.getFrame() instanceof JFrame) {
                TinyTitlePane.this.setMaximizeBounds(TinyTitlePane.this.getFrame());
                return Theme.frameTitleHeight[Theme.derivedStyle[Theme.style]];
            }
            return Theme.frameInternalTitleHeight[Theme.derivedStyle[Theme.style]];
        }
        
        public void layoutContainer(final Container container) {
            if (TinyTitlePane.this.getWindowDecorationStyle() == 0) {
                TinyTitlePane.this.buttonsWidth = 0;
                return;
            }
            final boolean b = (TinyTitlePane.this.window == null) ? TinyTitlePane.this.getRootPane().getComponentOrientation().isLeftToRight() : TinyTitlePane.this.window.getComponentOrientation().isLeftToRight();
            final int width = TinyTitlePane.this.getWidth();
            int height;
            int width2;
            if (TinyTitlePane.this.closeButton != null) {
                height = TinyTitlePane.this.closeButton.getPreferredSize().height;
                width2 = TinyTitlePane.this.closeButton.getPreferredSize().width;
            }
            else {
                height = 16;
                width2 = 16;
            }
            int n = (TinyTitlePane.this.getHeight() - height) / 2 + 1;
            if (Theme.derivedStyle[Theme.style] == 1) {
                ++n;
            }
            final boolean b2 = (b ? width : false) != 0;
            final int n2 = 5;
            final int n3 = b ? n2 : (width - width2 - n2);
            if (TinyTitlePane.this.menuBar != null) {
                TinyTitlePane.this.menuBar.setBounds(n3, n, width2, height);
            }
            final int n4 = b ? width : 0;
            final int n5 = 2;
            int n6 = n4 + (b ? (-n5 - width2) : n5);
            if (TinyTitlePane.this.closeButton != null) {
                TinyTitlePane.this.closeButton.setBounds(n6, n, width2, height);
            }
            if (!b) {
                n6 += width2;
            }
            if (Toolkit.getDefaultToolkit().isFrameStateSupported(6) && TinyTitlePane.this.toggleButton.getParent() != null) {
                n6 += (b ? (-n5 - width2) : n5);
                TinyTitlePane.this.toggleButton.setBounds(n6, n, width2, height);
                if (!b) {
                    n6 += width2;
                }
            }
            if (TinyTitlePane.this.iconifyButton != null && TinyTitlePane.this.iconifyButton.getParent() != null) {
                n6 += (b ? (-n5 - width2) : n5);
                TinyTitlePane.this.iconifyButton.setBounds(n6, n, width2, height);
                if (!b) {
                    n6 += width2;
                }
            }
            TinyTitlePane.this.buttonsWidth = (b ? (width - n6) : n6);
        }
    }
    
    private class SystemMenuBar extends JMenuBar
    {
        public void paint(final Graphics graphics) {
            final Frame access$800 = TinyTitlePane.this.getFrame();
            final Image image = (access$800 != null) ? access$800.getIconImage() : null;
            if (image != null) {
                graphics.drawImage(image, 0, 0, 16, 16, null);
            }
            else {
                final Icon icon = UIManager.getIcon("InternalFrame.icon");
                if (icon != null) {
                    icon.paintIcon(this, graphics, 0, 0);
                }
            }
        }
        
        public Dimension getMinimumSize() {
            return this.getPreferredSize();
        }
        
        public Dimension getPreferredSize() {
            final Icon icon = UIManager.getIcon("InternalFrame.icon");
            if (icon != null) {
                return new Dimension(icon.getIconWidth(), icon.getIconHeight());
            }
            final Dimension preferredSize = super.getPreferredSize();
            return new Dimension(Math.max(16, preferredSize.width), Math.max(preferredSize.height, 16));
        }
    }
    
    private class MaximizeAction extends AbstractAction
    {
        public MaximizeAction() {
            super(UIManager.getString("MetalTitlePane.maximizeTitle", TinyTitlePane.this.getLocale()));
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            TinyTitlePane.this.maximize();
        }
    }
    
    private class RestoreAction extends AbstractAction
    {
        public RestoreAction() {
            super(UIManager.getString("MetalTitlePane.restoreTitle", TinyTitlePane.this.getLocale()));
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            TinyTitlePane.this.restore();
        }
    }
    
    private class IconifyAction extends AbstractAction
    {
        public IconifyAction() {
            super(UIManager.getString("MetalTitlePane.iconifyTitle", TinyTitlePane.this.getLocale()));
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            TinyTitlePane.this.iconify();
        }
    }
    
    private class CloseAction extends AbstractAction
    {
        public CloseAction() {
            super(UIManager.getString("MetalTitlePane.closeTitle", TinyTitlePane.this.getLocale()));
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            TinyTitlePane.this.close();
        }
    }
}
