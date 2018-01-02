// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import java.awt.event.MouseEvent;
import java.awt.Point;
import javax.swing.event.MenuDragMouseEvent;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import de.muntjak.tinylookandfeel.controlpanel.ColorRoutines;
import java.awt.Shape;
import java.awt.Component;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JCheckBoxMenuItem;
import java.awt.event.KeyEvent;
import javax.swing.plaf.ComponentUI;
import javax.swing.MenuSelectionManager;
import javax.swing.MenuElement;
import java.awt.FontMetrics;
import javax.swing.ButtonModel;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import java.awt.Graphics;
import javax.swing.text.View;
import java.awt.Dimension;
import javax.swing.KeyStroke;
import javax.swing.plaf.ComponentInputMapUIResource;
import javax.swing.Action;
import javax.swing.plaf.ActionMapUIResource;
import javax.swing.UIManager;
import javax.swing.ActionMap;
import java.awt.Insets;
import javax.swing.plaf.UIResource;
import javax.swing.LookAndFeel;
import java.awt.Container;
import javax.swing.JMenu;
import javax.swing.SwingUtilities;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.plaf.basic.BasicHTML;
import javax.swing.JComponent;
import java.awt.Rectangle;
import javax.swing.InputMap;
import javax.swing.Icon;
import java.beans.PropertyChangeListener;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuDragMouseListener;
import javax.swing.event.MouseInputListener;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JMenuItem;
import javax.swing.plaf.MenuItemUI;

public class TinyMenuItemUI extends MenuItemUI
{
    private static final boolean TRACE = false;
    private static final boolean VERBOSE = false;
    private static final boolean DEBUG = true;
    protected JMenuItem menuItem;
    protected Color selectionBackground;
    protected Color selectionForeground;
    protected Color disabledForeground;
    protected Color acceleratorForeground;
    protected Color acceleratorSelectionForeground;
    private String acceleratorDelimiter;
    protected static int defaultTextIconGap;
    protected static int defaultIconGap;
    protected Font acceleratorFont;
    protected MouseInputListener mouseInputListener;
    protected MenuDragMouseListener menuDragMouseListener;
    protected MenuKeyListener menuKeyListener;
    private PropertyChangeListener propertyChangeListener;
    protected Icon arrowIcon;
    protected Icon checkIcon;
    protected boolean oldBorderPainted;
    InputMap windowInputMap;
    static final String MAX_TEXT_WIDTH = "maxTextWidth";
    static final String MAX_ACC_WIDTH = "maxAccWidth";
    static final String MAX_ICON_WIDTH = "maxIconWidth";
    static Rectangle zeroRect;
    static Rectangle iconRect;
    static Rectangle textRect;
    static Rectangle acceleratorRect;
    static Rectangle checkIconRect;
    static Rectangle arrowIconRect;
    static Rectangle viewRect;
    static Rectangle r;
    
    public TinyMenuItemUI() {
        this.menuItem = null;
        this.arrowIcon = null;
        this.checkIcon = null;
    }
    
    public void installUI(final JComponent component) {
        this.menuItem = (JMenuItem)component;
        this.installDefaults();
        this.installComponents(this.menuItem);
        this.installListeners();
        this.installKeyboardActions();
    }
    
    protected void installComponents(final JMenuItem menuItem) {
        BasicHTML.updateRenderer(menuItem, menuItem.getText());
    }
    
    protected String getPropertyPrefix() {
        return "MenuItem";
    }
    
    protected void installListeners() {
        final MouseInputListener mouseInputListener = this.createMouseInputListener(this.menuItem);
        this.mouseInputListener = mouseInputListener;
        if (mouseInputListener != null) {
            this.menuItem.addMouseListener(this.mouseInputListener);
            this.menuItem.addMouseMotionListener(this.mouseInputListener);
        }
        if ((this.menuDragMouseListener = this.createMenuDragMouseListener(this.menuItem)) != null) {
            this.menuItem.addMenuDragMouseListener(this.menuDragMouseListener);
        }
        if ((this.propertyChangeListener = this.createPropertyChangeListener(this.menuItem)) != null) {
            this.menuItem.addPropertyChangeListener(this.propertyChangeListener);
        }
    }
    
    protected void installKeyboardActions() {
        SwingUtilities.replaceUIActionMap(this.menuItem, this.getActionMap());
        this.updateAcceleratorBinding();
    }
    
    public void uninstallUI(final JComponent component) {
        this.menuItem = (JMenuItem)component;
        this.uninstallDefaults();
        this.uninstallComponents(this.menuItem);
        this.uninstallListeners();
        this.uninstallKeyboardActions();
        final Container parent = this.menuItem.getParent();
        if (parent != null && parent instanceof JComponent && (!(this.menuItem instanceof JMenu) || !((JMenu)this.menuItem).isTopLevelMenu())) {
            final JComponent component2 = (JComponent)parent;
            component2.putClientProperty("maxAccWidth", null);
            component2.putClientProperty("maxTextWidth", null);
        }
        this.menuItem = null;
    }
    
    protected void uninstallDefaults() {
        LookAndFeel.uninstallBorder(this.menuItem);
        this.menuItem.setBorderPainted(this.oldBorderPainted);
        if (this.menuItem.getMargin() instanceof UIResource) {
            this.menuItem.setMargin(null);
        }
        if (this.arrowIcon instanceof UIResource) {
            this.arrowIcon = null;
        }
        if (this.checkIcon instanceof UIResource) {
            this.checkIcon = null;
        }
    }
    
    protected void uninstallComponents(final JMenuItem menuItem) {
        BasicHTML.updateRenderer(menuItem, "");
    }
    
    protected void uninstallListeners() {
        if (this.mouseInputListener != null) {
            this.menuItem.removeMouseListener(this.mouseInputListener);
            this.menuItem.removeMouseMotionListener(this.mouseInputListener);
        }
        if (this.menuDragMouseListener != null) {
            this.menuItem.removeMenuDragMouseListener(this.menuDragMouseListener);
        }
        if (this.propertyChangeListener != null) {
            this.menuItem.removePropertyChangeListener(this.propertyChangeListener);
        }
        this.mouseInputListener = null;
        this.menuDragMouseListener = null;
        this.menuKeyListener = null;
        this.propertyChangeListener = null;
    }
    
    protected void uninstallKeyboardActions() {
        SwingUtilities.replaceUIActionMap(this.menuItem, null);
        if (this.windowInputMap != null) {
            SwingUtilities.replaceUIInputMap(this.menuItem, 2, null);
            this.windowInputMap = null;
        }
    }
    
    protected MouseInputListener createMouseInputListener(final JComponent component) {
        return new MouseInputHandler();
    }
    
    protected MenuDragMouseListener createMenuDragMouseListener(final JComponent component) {
        return new MenuDragMouseHandler();
    }
    
    private PropertyChangeListener createPropertyChangeListener(final JComponent component) {
        return new PropertyChangeHandler();
    }
    
    ActionMap getActionMap() {
        final String string = this.getPropertyPrefix() + ".actionMap";
        ActionMap actionMap = (ActionMap)UIManager.get(string);
        if (actionMap == null) {
            actionMap = this.createActionMap();
            UIManager.getLookAndFeelDefaults().put(string, actionMap);
        }
        return actionMap;
    }
    
    ActionMap createActionMap() {
        final ActionMapUIResource actionMapUIResource = new ActionMapUIResource();
        actionMapUIResource.put("doClick", new ClickAction());
        return actionMapUIResource;
    }
    
    InputMap createInputMap(final int n) {
        if (n == 2) {
            return new ComponentInputMapUIResource(this.menuItem);
        }
        return null;
    }
    
    void updateAcceleratorBinding() {
        final KeyStroke accelerator = this.menuItem.getAccelerator();
        if (this.windowInputMap != null) {
            this.windowInputMap.clear();
        }
        if (accelerator != null) {
            if (this.windowInputMap == null) {
                this.windowInputMap = this.createInputMap(2);
                SwingUtilities.replaceUIInputMap(this.menuItem, 2, this.windowInputMap);
            }
            this.windowInputMap.put(accelerator, "doClick");
        }
    }
    
    public Dimension getMinimumSize(final JComponent component) {
        Dimension dimension = null;
        final View view = (View)component.getClientProperty("html");
        if (view != null) {
            final Dimension preferredSize;
            dimension = (preferredSize = this.getPreferredSize(component));
            preferredSize.width -= (int)(view.getPreferredSpan(0) - view.getMinimumSpan(0));
        }
        return dimension;
    }
    
    public Dimension getPreferredSize(final JComponent component) {
        return this.getPreferredMenuItemSize(component, this.checkIcon, this.arrowIcon, TinyMenuItemUI.defaultTextIconGap);
    }
    
    protected void paintText(final Graphics graphics, final JMenuItem menuItem, final Rectangle rectangle, final String s) {
        final ButtonModel model = menuItem.getModel();
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int displayedMnemonicIndex = menuItem.getDisplayedMnemonicIndex();
        if (!model.isEnabled()) {
            if (Theme.derivedStyle[Theme.style] == 1) {
                if (menuItem instanceof JMenu && ((JMenu)menuItem).isTopLevelMenu()) {
                    graphics.setColor(Theme.menuBarColor[Theme.style].getColor().darker());
                    BasicGraphicsUtils.drawStringUnderlineCharAt(graphics, s, displayedMnemonicIndex, rectangle.x, rectangle.y + fontMetrics.getAscent());
                }
                else {
                    graphics.setColor(Theme.menuIconShadowColor[Theme.style].getColor());
                    BasicGraphicsUtils.drawStringUnderlineCharAt(graphics, s, displayedMnemonicIndex, rectangle.x, rectangle.y + fontMetrics.getAscent());
                    graphics.setColor(Theme.menuIconDisabledColor[Theme.style].getColor());
                    BasicGraphicsUtils.drawStringUnderlineCharAt(graphics, s, displayedMnemonicIndex, rectangle.x - 1, rectangle.y + fontMetrics.getAscent() - 1);
                }
            }
            else {
                graphics.setColor(Theme.menuDisabledFgColor[Theme.style].getColor());
                BasicGraphicsUtils.drawStringUnderlineCharAt(graphics, s, displayedMnemonicIndex, rectangle.x, rectangle.y + fontMetrics.getAscent());
            }
        }
        else {
            if (this.isTopLevelMenu()) {
                if (menuItem.getClientProperty("rollover") == Boolean.TRUE && Theme.menuRollover[Theme.style] && !model.isSelected()) {
                    graphics.setColor(Theme.menuRolloverFgColor[Theme.style].getColor());
                }
                else if (!(menuItem.getForeground() instanceof ColorUIResource)) {
                    graphics.setColor(menuItem.getForeground());
                }
                else {
                    graphics.setColor(Theme.menuFontColor[Theme.style].getColor());
                }
            }
            else if (model.isArmed() || (menuItem instanceof JMenu && model.isSelected())) {
                graphics.setColor(Theme.menuSelectedTextColor[Theme.style].getColor());
            }
            else {
                graphics.setColor(menuItem.getForeground());
            }
            BasicGraphicsUtils.drawStringUnderlineCharAt(graphics, s, displayedMnemonicIndex, rectangle.x, rectangle.y + fontMetrics.getAscent());
        }
    }
    
    public Dimension getMaximumSize(final JComponent component) {
        Dimension dimension = null;
        final View view = (View)component.getClientProperty("html");
        if (view != null) {
            final Dimension preferredSize;
            dimension = (preferredSize = this.getPreferredSize(component));
            preferredSize.width += (int)(view.getMaximumSpan(0) - view.getPreferredSpan(0));
        }
        return dimension;
    }
    
    private void resetRects() {
        TinyMenuItemUI.iconRect.setBounds(TinyMenuItemUI.zeroRect);
        TinyMenuItemUI.textRect.setBounds(TinyMenuItemUI.zeroRect);
        TinyMenuItemUI.acceleratorRect.setBounds(TinyMenuItemUI.zeroRect);
        TinyMenuItemUI.checkIconRect.setBounds(TinyMenuItemUI.zeroRect);
        TinyMenuItemUI.arrowIconRect.setBounds(TinyMenuItemUI.zeroRect);
        TinyMenuItemUI.viewRect.setBounds(0, 0, 32767, 32767);
        TinyMenuItemUI.r.setBounds(TinyMenuItemUI.zeroRect);
    }
    
    public void update(final Graphics graphics, final JComponent component) {
        this.paint(graphics, component);
    }
    
    public void paint(final Graphics graphics, final JComponent component) {
        this.paintMenuItem(graphics, component, this.checkIcon, this.arrowIcon, this.selectionBackground, this.selectionForeground, TinyMenuItemUI.defaultTextIconGap);
    }
    
    private boolean isTopLevelMenu() {
        return this.menuItem instanceof JMenu && ((JMenu)this.menuItem).isTopLevelMenu();
    }
    
    public MenuElement[] getPath() {
        final MenuElement[] selectedPath = MenuSelectionManager.defaultManager().getSelectedPath();
        final int length = selectedPath.length;
        if (length == 0) {
            return new MenuElement[0];
        }
        final Container parent = this.menuItem.getParent();
        MenuElement[] array;
        if (selectedPath[length - 1].getComponent() == parent) {
            array = new MenuElement[length + 1];
            System.arraycopy(selectedPath, 0, array, 0, length);
            array[length] = this.menuItem;
        }
        else {
            int n;
            for (n = selectedPath.length - 1; n >= 0 && selectedPath[n].getComponent() != parent; --n) {}
            array = new MenuElement[n + 2];
            System.arraycopy(selectedPath, 0, array, 0, n + 1);
            array[n + 1] = this.menuItem;
        }
        return array;
    }
    
    protected void doClick(MenuSelectionManager defaultManager) {
        if (defaultManager == null) {
            defaultManager = MenuSelectionManager.defaultManager();
        }
        defaultManager.clearSelectedPath();
        this.menuItem.doClick(0);
    }
    
    private boolean isInternalFrameSystemMenu() {
        final String actionCommand = this.menuItem.getActionCommand();
        return actionCommand == "Close" || actionCommand == "Minimize" || actionCommand == "Restore" || actionCommand == "Maximize";
    }
    
    public static ComponentUI createUI(final JComponent component) {
        return new TinyMenuItemUI();
    }
    
    protected void installDefaults() {
        final String propertyPrefix = this.getPropertyPrefix();
        this.acceleratorFont = UIManager.getFont("MenuItem.acceleratorFont");
        this.menuItem.setOpaque(true);
        if (this.menuItem.getMargin() == null || this.menuItem.getMargin() instanceof UIResource) {
            this.menuItem.setMargin(UIManager.getInsets(propertyPrefix + ".margin"));
        }
        LookAndFeel.installBorder(this.menuItem, propertyPrefix + ".border");
        this.oldBorderPainted = this.menuItem.isBorderPainted();
        this.menuItem.setBorderPainted((boolean)UIManager.get(propertyPrefix + ".borderPainted"));
        LookAndFeel.installColorsAndFont(this.menuItem, propertyPrefix + ".background", propertyPrefix + ".foreground", propertyPrefix + ".font");
        if (this.selectionBackground == null || this.selectionBackground instanceof UIResource) {
            this.selectionBackground = UIManager.getColor(propertyPrefix + ".selectionBackground");
        }
        if (this.selectionForeground == null || this.selectionForeground instanceof UIResource) {
            this.selectionForeground = UIManager.getColor(propertyPrefix + ".selectionForeground");
        }
        if (this.disabledForeground == null || this.disabledForeground instanceof UIResource) {
            this.disabledForeground = UIManager.getColor(propertyPrefix + ".disabledForeground");
        }
        if (this.acceleratorForeground == null || this.acceleratorForeground instanceof UIResource) {
            this.acceleratorForeground = UIManager.getColor(propertyPrefix + ".acceleratorForeground");
        }
        if (this.acceleratorSelectionForeground == null || this.acceleratorSelectionForeground instanceof UIResource) {
            this.acceleratorSelectionForeground = UIManager.getColor(propertyPrefix + ".acceleratorSelectionForeground");
        }
        this.acceleratorDelimiter = UIManager.getString("MenuItem.acceleratorDelimiter");
        if (this.acceleratorDelimiter == null) {
            this.acceleratorDelimiter = "+";
        }
        if (this.arrowIcon == null || this.arrowIcon instanceof UIResource) {
            this.arrowIcon = UIManager.getIcon(propertyPrefix + ".arrowIcon");
        }
        if (this.checkIcon == null || this.checkIcon instanceof UIResource) {
            this.checkIcon = UIManager.getIcon(propertyPrefix + ".checkIcon");
        }
        TinyMenuItemUI.defaultTextIconGap = 8;
        TinyMenuItemUI.defaultIconGap = 4;
    }
    
    protected Dimension getPreferredMenuItemSize(final JComponent component, final Icon icon, final Icon icon2, final int n) {
        final JMenuItem menuItem = (JMenuItem)component;
        final Icon icon3 = menuItem.getIcon();
        final String text = menuItem.getText();
        final KeyStroke accelerator = menuItem.getAccelerator();
        String s = "";
        if (accelerator != null) {
            final int modifiers = accelerator.getModifiers();
            if (modifiers > 0) {
                s = KeyEvent.getKeyModifiersText(modifiers) + this.acceleratorDelimiter;
            }
            final int keyCode = accelerator.getKeyCode();
            if (keyCode != 0) {
                s += KeyEvent.getKeyText(keyCode);
            }
            else {
                s += accelerator.getKeyChar();
            }
        }
        final FontMetrics fontMetrics = menuItem.getFontMetrics(menuItem.getFont());
        final FontMetrics fontMetrics2 = menuItem.getFontMetrics(this.acceleratorFont);
        this.resetRects();
        this.layoutMenuItem(fontMetrics, text, fontMetrics2, s, icon3, icon, icon2, menuItem.getVerticalAlignment(), menuItem.getHorizontalAlignment(), menuItem.getVerticalTextPosition(), menuItem.getHorizontalTextPosition(), TinyMenuItemUI.viewRect, TinyMenuItemUI.iconRect, TinyMenuItemUI.textRect, TinyMenuItemUI.acceleratorRect, TinyMenuItemUI.checkIconRect, TinyMenuItemUI.arrowIconRect, (text == null) ? 0 : n, TinyMenuItemUI.defaultIconGap);
        TinyMenuItemUI.r.setBounds(TinyMenuItemUI.textRect);
        TinyMenuItemUI.r = SwingUtilities.computeUnion(TinyMenuItemUI.iconRect.x, TinyMenuItemUI.iconRect.y, TinyMenuItemUI.iconRect.width, TinyMenuItemUI.iconRect.height, TinyMenuItemUI.r);
        if (icon3 != null || icon != null) {
            final Rectangle r = TinyMenuItemUI.r;
            r.width -= 3 * TinyMenuItemUI.defaultTextIconGap;
        }
        final Container parent = this.menuItem.getParent();
        if (parent != null && parent instanceof JComponent && (!(this.menuItem instanceof JMenu) || !((JMenu)this.menuItem).isTopLevelMenu())) {
            final JComponent component2 = (JComponent)parent;
            final Integer n2 = (Integer)component2.getClientProperty("maxTextWidth");
            final Integer n3 = (Integer)component2.getClientProperty("maxAccWidth");
            final Integer n4 = (Integer)component2.getClientProperty("maxIconWidth");
            final int width = (n2 != null) ? n2 : 0;
            int width2 = (n3 != null) ? n3 : 0;
            final int n5 = (n4 != null) ? n4 : 0;
            if (TinyMenuItemUI.r.width < width) {
                TinyMenuItemUI.r.width = width;
            }
            else {
                component2.putClientProperty("maxTextWidth", new Integer(TinyMenuItemUI.r.width));
            }
            if (TinyMenuItemUI.acceleratorRect.width > width2) {
                width2 = TinyMenuItemUI.acceleratorRect.width;
                component2.putClientProperty("maxAccWidth", new Integer(TinyMenuItemUI.acceleratorRect.width));
            }
            if (icon3 != null && icon3.getIconWidth() > n5) {
                component2.putClientProperty("maxIconWidth", new Integer(icon3.getIconWidth()));
            }
            if (width2 > 0) {
                final Rectangle r2 = TinyMenuItemUI.r;
                r2.width += width2;
                final Rectangle r3 = TinyMenuItemUI.r;
                r3.width += n;
            }
        }
        if (!this.isTopLevelMenu()) {
            final Rectangle r4 = TinyMenuItemUI.r;
            r4.width += TinyMenuItemUI.checkIconRect.width;
            final Rectangle r5 = TinyMenuItemUI.r;
            r5.width += n;
            final Rectangle r6 = TinyMenuItemUI.r;
            r6.width += n;
            final Rectangle r7 = TinyMenuItemUI.r;
            r7.width += TinyMenuItemUI.arrowIconRect.width;
        }
        final Rectangle r8 = TinyMenuItemUI.r;
        r8.width += 2 * n;
        final Insets insets = menuItem.getInsets();
        if (insets != null) {
            final Rectangle r9 = TinyMenuItemUI.r;
            r9.width += insets.left + insets.right;
            final Rectangle r10 = TinyMenuItemUI.r;
            r10.height += insets.top + insets.bottom;
        }
        if (TinyMenuItemUI.r.width % 2 == 0) {
            final Rectangle r11 = TinyMenuItemUI.r;
            ++r11.width;
        }
        if (TinyMenuItemUI.r.height % 2 == 0) {
            final Rectangle r12 = TinyMenuItemUI.r;
            ++r12.height;
        }
        return TinyMenuItemUI.r.getSize();
    }
    
    protected void paintMenuItem(final Graphics graphics, final JComponent component, final Icon icon, final Icon icon2, final Color color, final Color color2, final int n) {
        final JMenuItem menuItem = (JMenuItem)component;
        final ButtonModel model = menuItem.getModel();
        final JComponent component2 = (JComponent)menuItem.getParent();
        final Integer n2 = (Integer)component2.getClientProperty("maxIconWidth");
        final int n3 = (n2 == null) ? 16 : n2;
        final int width = menuItem.getWidth();
        final int height = menuItem.getHeight();
        final Insets insets = component.getInsets();
        this.resetRects();
        TinyMenuItemUI.viewRect.setBounds(0, 0, width, height);
        final Rectangle viewRect = TinyMenuItemUI.viewRect;
        viewRect.x += 0;
        final Rectangle viewRect2 = TinyMenuItemUI.viewRect;
        viewRect2.y += insets.top;
        final Rectangle viewRect3 = TinyMenuItemUI.viewRect;
        viewRect3.width -= insets.right + TinyMenuItemUI.viewRect.x;
        final Rectangle viewRect4 = TinyMenuItemUI.viewRect;
        viewRect4.height -= insets.bottom + TinyMenuItemUI.viewRect.y;
        final Font font = graphics.getFont();
        final Font font2 = component.getFont();
        graphics.setFont(font2);
        final FontMetrics fontMetrics = graphics.getFontMetrics(font2);
        final FontMetrics fontMetrics2 = graphics.getFontMetrics(this.acceleratorFont);
        final KeyStroke accelerator = menuItem.getAccelerator();
        String s = "";
        if (accelerator != null) {
            final int modifiers = accelerator.getModifiers();
            if (modifiers > 0) {
                s = KeyEvent.getKeyModifiersText(modifiers) + this.acceleratorDelimiter;
            }
            final int keyCode = accelerator.getKeyCode();
            if (keyCode != 0) {
                s += KeyEvent.getKeyText(keyCode);
            }
            else {
                s += accelerator.getKeyChar();
            }
        }
        int n4 = 0;
        Icon icon4;
        Icon icon3 = icon4 = menuItem.getIcon();
        if (!this.isTopLevelMenu()) {
            if (component instanceof JCheckBoxMenuItem || component instanceof JRadioButtonMenuItem) {
                icon3 = icon;
                if (icon.getIconWidth() < n3) {
                    icon3 = new EmptyIcon(n3, icon.getIconHeight());
                    n4 = (n3 - icon.getIconWidth()) / 2;
                }
                icon4 = null;
            }
            else if (component instanceof JMenuItem && (icon3 == null || icon3.getIconWidth() < n3)) {
                final int n5 = (icon3 == null) ? 2 : menuItem.getIcon().getIconHeight();
                n4 = (n3 - ((icon3 == null) ? 2 : menuItem.getIcon().getIconWidth())) / 2;
                icon3 = new EmptyIcon(n3, n5);
            }
        }
        final String layoutMenuItem = this.layoutMenuItem(fontMetrics, menuItem.getText(), fontMetrics2, s, icon3, null, icon2, menuItem.getVerticalAlignment(), menuItem.getHorizontalAlignment(), menuItem.getVerticalTextPosition(), menuItem.getHorizontalTextPosition(), TinyMenuItemUI.viewRect, TinyMenuItemUI.iconRect, TinyMenuItemUI.textRect, TinyMenuItemUI.acceleratorRect, TinyMenuItemUI.checkIconRect, TinyMenuItemUI.arrowIconRect, (menuItem.getText() == null) ? 0 : n, TinyMenuItemUI.defaultIconGap);
        this.paintBackground(graphics, menuItem, color);
        final Color color3 = graphics.getColor();
        if (icon != null) {
            if (model.isArmed() || (component instanceof JMenu && model.isSelected())) {
                graphics.setColor(color2);
            }
            else {
                graphics.setColor(color3);
            }
            if (!this.isTopLevelMenu()) {
                icon.paintIcon(component, graphics, TinyMenuItemUI.iconRect.x + n4, TinyMenuItemUI.iconRect.y);
            }
            graphics.setColor(color3);
        }
        if (icon4 != null) {
            if (!model.isEnabled()) {
                final Icon disabledIcon = menuItem.getDisabledIcon();
                if (disabledIcon != null) {
                    disabledIcon.paintIcon(component, graphics, TinyMenuItemUI.iconRect.x + n4, TinyMenuItemUI.iconRect.y);
                }
            }
            else if (model.isPressed() && model.isArmed()) {
                Icon icon5 = menuItem.getPressedIcon();
                if (icon5 == null) {
                    icon5 = menuItem.getIcon();
                }
                if (icon5 != null) {
                    icon5.paintIcon(component, graphics, TinyMenuItemUI.iconRect.x + n4, TinyMenuItemUI.iconRect.y);
                }
            }
            else if (model.isArmed() || model.isSelected()) {
                final Icon icon6 = menuItem.getIcon();
                if (icon6 != null) {
                    icon6.paintIcon(component, graphics, TinyMenuItemUI.iconRect.x + n4, TinyMenuItemUI.iconRect.y);
                }
            }
            else {
                final Icon icon7 = menuItem.getIcon();
                if (icon7 != null) {
                    icon7.paintIcon(component, graphics, TinyMenuItemUI.iconRect.x + n4, TinyMenuItemUI.iconRect.y);
                }
            }
        }
        if (layoutMenuItem != null) {
            final View view = (View)component.getClientProperty("html");
            graphics.setColor(Theme.menuItemFontColor[Theme.style].getColor());
            if (view != null) {
                view.paint(graphics, TinyMenuItemUI.textRect);
            }
            else {
                this.paintText(graphics, menuItem, TinyMenuItemUI.textRect, layoutMenuItem);
            }
        }
        if (s != null && !s.equals("")) {
            int n6 = 0;
            final Container parent = this.menuItem.getParent();
            if (parent != null && parent instanceof JComponent) {
                final Integer n7 = (Integer)component2.getClientProperty("maxAccWidth");
                n6 = ((n7 != null) ? n7 : TinyMenuItemUI.acceleratorRect.width) - TinyMenuItemUI.acceleratorRect.width;
            }
            graphics.setFont(this.acceleratorFont);
            if (!model.isEnabled()) {
                graphics.setColor(Theme.menuIconShadowColor[Theme.style].getColor());
                BasicGraphicsUtils.drawString(graphics, s, 0, TinyMenuItemUI.acceleratorRect.x - n6, TinyMenuItemUI.acceleratorRect.y + fontMetrics2.getAscent());
                graphics.setColor(Theme.menuIconDisabledColor[Theme.style].getColor());
                BasicGraphicsUtils.drawString(graphics, s, 0, TinyMenuItemUI.acceleratorRect.x - n6 - 1, TinyMenuItemUI.acceleratorRect.y + fontMetrics2.getAscent() - 1);
            }
            else {
                if (model.isArmed() || (component instanceof JMenu && model.isSelected())) {
                    graphics.setColor(Theme.menuSelectedTextColor[Theme.style].getColor());
                }
                else {
                    graphics.setColor(Theme.menuItemFontColor[Theme.style].getColor());
                }
                BasicGraphicsUtils.drawString(graphics, s, 0, TinyMenuItemUI.acceleratorRect.x - n6, TinyMenuItemUI.acceleratorRect.y + fontMetrics2.getAscent());
            }
        }
        if (icon2 != null) {
            if (model.isArmed() || (component instanceof JMenu && model.isSelected())) {
                graphics.setColor(color2);
            }
            if (!this.isTopLevelMenu()) {
                icon2.paintIcon(component, graphics, TinyMenuItemUI.arrowIconRect.x, TinyMenuItemUI.arrowIconRect.y);
            }
        }
        graphics.setColor(color3);
        graphics.setFont(font);
    }
    
    protected void paintBackground(final Graphics graphics, final JMenuItem menuItem, final Color color) {
        if (!menuItem.isOpaque()) {
            return;
        }
        final ButtonModel model = menuItem.getModel();
        final Color color2 = graphics.getColor();
        final int width = menuItem.getWidth();
        final int height = menuItem.getHeight();
        final boolean b = model.isArmed() || (menuItem instanceof JMenu && model.isSelected());
        if (menuItem instanceof JMenu && ((JMenu)menuItem).isTopLevelMenu()) {
            if (model.isSelected()) {
                graphics.setColor(Theme.menuBarColor[Theme.style].getColor());
                graphics.fillRect(0, 0, width, height);
                switch (Theme.derivedStyle[Theme.style]) {
                    case 0: {
                        this.drawTinyTopMenuBorder(graphics, 0, 0, width, height, true);
                        break;
                    }
                    case 1: {
                        this.drawWinTopMenuBorder(graphics, 0, 0, width, height, true);
                        break;
                    }
                    case 2: {
                        this.drawXpTopMenuBorder(graphics, 0, 0, width, height, true);
                        break;
                    }
                }
            }
            else if (menuItem.getClientProperty("rollover") == Boolean.TRUE && Theme.menuRollover[Theme.style]) {
                graphics.setColor(Theme.menuRolloverBgColor[Theme.style].getColor());
                graphics.fillRect(0, 0, width - 8, height);
                graphics.setColor(Theme.menuBarColor[Theme.style].getColor());
                graphics.fillRect(width - 8, 0, 8, height);
                switch (Theme.derivedStyle[Theme.style]) {
                    case 0: {
                        this.drawTinyTopMenuBorder(graphics, 0, 0, width, height, false);
                        break;
                    }
                    case 1: {
                        this.drawWinTopMenuBorder(graphics, 0, 0, width, height, false);
                        break;
                    }
                    case 2: {
                        this.drawXpTopMenuBorder(graphics, 0, 0, width, height, false);
                        break;
                    }
                }
            }
            else {
                if (menuItem.getBackground() instanceof ColorUIResource) {
                    graphics.setColor(Theme.menuBarColor[Theme.style].getColor());
                }
                else {
                    graphics.setColor(menuItem.getBackground());
                }
                graphics.fillRect(0, 0, width, height);
            }
        }
        else if (b) {
            graphics.setColor(Theme.menuItemRolloverColor[Theme.style].getColor());
            graphics.fillRect(0, 0, width, height);
        }
        else {
            if (menuItem.getBackground() instanceof ColorUIResource) {
                graphics.setColor(Theme.menuPopupColor[Theme.style].getColor());
            }
            else {
                graphics.setColor(menuItem.getBackground());
            }
            graphics.fillRect(0, 0, width, height);
        }
        graphics.setColor(color2);
    }
    
    private void drawTinyTopMenuBorder(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final boolean b) {
    }
    
    private void drawWinTopMenuBorder(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final boolean b) {
        if (!b) {
            graphics.setColor(Theme.menuDarkColor[Theme.style].getColor());
            graphics.drawLine(n, n2 + n4 - 2, n + n3 - 8, n2 + n4 - 2);
            graphics.drawLine(n + n3 - 8, n2, n + n3 - 8, n2 + n4 - 3);
            graphics.setColor(Theme.menuLightColor[Theme.style].getColor());
            graphics.drawLine(n, n2, n + n3 - 8, n2);
            graphics.drawLine(n, n2 + 1, n, n2 + n4 - 2);
        }
        else {
            graphics.setColor(Theme.menuLightColor[Theme.style].getColor());
            graphics.drawLine(n, n2 + n4 - 2, n + n3 - 8, n2 + n4 - 2);
            graphics.drawLine(n + n3 - 8, n2, n + n3 - 8, n2 + n4 - 3);
            graphics.setColor(Theme.menuDarkColor[Theme.style].getColor());
            graphics.drawLine(n, n2, n + n3 - 8, n2);
            graphics.drawLine(n, n2 + 1, n, n2 + n4 - 2);
            graphics.translate(1, 1);
        }
    }
    
    private void drawXpTopMenuBorder(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final boolean b) {
        graphics.setColor(Theme.menuBorderColor[Theme.style].getColor());
        if (b) {
            graphics.drawLine(n, n2, n + n3 - 8, n2);
            graphics.drawLine(n, n2, n, n2 + n4 - 1);
            graphics.drawLine(n + n3 - 8, n2, n + n3 - 8, n2 + n4 - 1);
            this.drawXpShadow(graphics, ColorRoutines.darken(Theme.menuBorderColor[Theme.style].getColor(), 15), Theme.menuBarColor[Theme.style].getColor(), n + n3 - 7, n2 + 6, 6, n4 - 6);
        }
        else {
            graphics.drawRect(n, n2, n3 - 8, n4 - 1);
        }
    }
    
    private void drawXpShadow(final Graphics graphics, final Color color, final Color color2, final int n, final int n2, final int n3, final int n4) {
        for (int i = 0; i < n3; ++i) {
            final Color gradient = ColorRoutines.getGradient(color, color2, n3, i);
            graphics.setColor(gradient);
            graphics.drawLine(n + i, n2, n + i, n2 + n4);
            int n5 = 0;
            for (int j = n2 - 1; j >= n2 - 6; --j) {
                graphics.setColor(ColorRoutines.getGradient(gradient, color2, 5, n5++));
                graphics.drawLine(n + i, j, n + i, j);
            }
        }
    }
    
    private String layoutMenuItem(final FontMetrics fontMetrics, final String s, final FontMetrics fontMetrics2, String s2, final Icon icon, final Icon icon2, final Icon icon3, final int n, final int n2, final int n3, final int n4, final Rectangle rectangle, final Rectangle rectangle2, final Rectangle rectangle3, final Rectangle rectangle4, final Rectangle rectangle5, final Rectangle rectangle6, final int n5, final int n6) {
        SwingUtilities.layoutCompoundLabel(this.menuItem, fontMetrics, s, icon, n, n2, n3, n4, rectangle, rectangle2, rectangle3, n5);
        if (s2 == null || s2.equals("")) {
            final boolean b = false;
            rectangle4.height = (b ? 1 : 0);
            rectangle4.width = (b ? 1 : 0);
            s2 = "";
        }
        else {
            rectangle4.width = SwingUtilities.computeStringWidth(fontMetrics2, s2);
            rectangle4.height = fontMetrics2.getHeight();
        }
        if (!this.isTopLevelMenu()) {
            if (icon2 != null) {
                rectangle5.height = icon2.getIconHeight();
                rectangle5.width = icon2.getIconWidth();
            }
            else {
                final boolean b2 = false;
                rectangle5.height = (b2 ? 1 : 0);
                rectangle5.width = (b2 ? 1 : 0);
            }
            if (icon3 != null) {
                rectangle6.width = icon3.getIconWidth();
                rectangle6.height = icon3.getIconHeight();
            }
            else {
                final boolean b3 = false;
                rectangle6.height = (b3 ? 1 : 0);
                rectangle6.width = (b3 ? 1 : 0);
            }
        }
        else {
            final boolean b4 = false;
            rectangle5.height = (b4 ? 1 : 0);
            rectangle5.width = (b4 ? 1 : 0);
            final boolean b5 = false;
            rectangle6.height = (b5 ? 1 : 0);
            rectangle6.width = (b5 ? 1 : 0);
        }
        final Rectangle union = rectangle2.union(rectangle3);
        if (icon2 != null) {
            rectangle5.x += n6;
        }
        else {
            rectangle3.x += n6;
            rectangle2.x += n6;
        }
        rectangle4.x = rectangle.x + rectangle.width - rectangle6.width - n6 - rectangle4.width;
        rectangle4.y = union.y + union.height / 2 - rectangle4.height / 2;
        if (!this.isTopLevelMenu()) {
            rectangle6.y = union.y + union.height / 2 - rectangle6.height / 2;
            rectangle5.y = union.y + union.height / 2 - rectangle5.height / 2;
            rectangle6.x = rectangle.x + rectangle.width - n6 - rectangle6.width;
        }
        return s;
    }
    
    static {
        TinyMenuItemUI.zeroRect = new Rectangle(0, 0, 0, 0);
        TinyMenuItemUI.iconRect = new Rectangle();
        TinyMenuItemUI.textRect = new Rectangle();
        TinyMenuItemUI.acceleratorRect = new Rectangle();
        TinyMenuItemUI.checkIconRect = new Rectangle();
        TinyMenuItemUI.arrowIconRect = new Rectangle();
        TinyMenuItemUI.viewRect = new Rectangle(32767, 32767);
        TinyMenuItemUI.r = new Rectangle();
    }
    
    public static class EmptyIcon implements Icon
    {
        int width;
        int height;
        
        public EmptyIcon(final int width, final int height) {
            this.height = height;
            this.width = width;
        }
        
        public void paintIcon(final Component component, final Graphics graphics, final int n, final int n2) {
        }
        
        public int getIconWidth() {
            return this.width;
        }
        
        public int getIconHeight() {
            return this.height;
        }
    }
    
    private static class ClickAction extends AbstractAction
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final JMenuItem menuItem = (JMenuItem)actionEvent.getSource();
            MenuSelectionManager.defaultManager().clearSelectedPath();
            menuItem.doClick();
        }
    }
    
    private class PropertyChangeHandler implements PropertyChangeListener
    {
        public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
            final String propertyName = propertyChangeEvent.getPropertyName();
            if (propertyName.equals("labelFor") || propertyName.equals("displayedMnemonic") || propertyName.equals("accelerator")) {
                TinyMenuItemUI.this.updateAcceleratorBinding();
            }
            else if (propertyName.equals("text") || "font".equals(propertyName) || "foreground".equals(propertyName)) {
                final JMenuItem menuItem = (JMenuItem)propertyChangeEvent.getSource();
                BasicHTML.updateRenderer(menuItem, menuItem.getText());
            }
        }
    }
    
    private class MenuDragMouseHandler implements MenuDragMouseListener
    {
        public void menuDragMouseEntered(final MenuDragMouseEvent menuDragMouseEvent) {
        }
        
        public void menuDragMouseDragged(final MenuDragMouseEvent menuDragMouseEvent) {
            menuDragMouseEvent.getMenuSelectionManager().setSelectedPath(menuDragMouseEvent.getPath());
        }
        
        public void menuDragMouseExited(final MenuDragMouseEvent menuDragMouseEvent) {
        }
        
        public void menuDragMouseReleased(final MenuDragMouseEvent menuDragMouseEvent) {
            final MenuSelectionManager menuSelectionManager = menuDragMouseEvent.getMenuSelectionManager();
            menuDragMouseEvent.getPath();
            final Point point = menuDragMouseEvent.getPoint();
            if (point.x >= 0 && point.x < TinyMenuItemUI.this.menuItem.getWidth() && point.y >= 0 && point.y < TinyMenuItemUI.this.menuItem.getHeight()) {
                TinyMenuItemUI.this.doClick(menuSelectionManager);
            }
            else {
                menuSelectionManager.clearSelectedPath();
            }
        }
    }
    
    protected class MouseInputHandler implements MouseInputListener
    {
        public void mouseClicked(final MouseEvent mouseEvent) {
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            final MenuSelectionManager defaultManager = MenuSelectionManager.defaultManager();
            final Point point = mouseEvent.getPoint();
            if (point.x >= 0 && point.x < TinyMenuItemUI.this.menuItem.getWidth() && point.y >= 0 && point.y < TinyMenuItemUI.this.menuItem.getHeight()) {
                TinyMenuItemUI.this.doClick(defaultManager);
            }
            else {
                defaultManager.processMouseEvent(mouseEvent);
            }
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
            final MenuSelectionManager defaultManager = MenuSelectionManager.defaultManager();
            if ((mouseEvent.getModifiers() & 0x1C) != 0x0) {
                MenuSelectionManager.defaultManager().processMouseEvent(mouseEvent);
            }
            else {
                defaultManager.setSelectedPath(TinyMenuItemUI.this.getPath());
            }
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            final MenuSelectionManager defaultManager = MenuSelectionManager.defaultManager();
            if ((mouseEvent.getModifiers() & 0x1C) != 0x0) {
                MenuSelectionManager.defaultManager().processMouseEvent(mouseEvent);
            }
            else {
                final MenuElement[] selectedPath = defaultManager.getSelectedPath();
                if (selectedPath.length > 1) {
                    final MenuElement[] selectedPath2 = new MenuElement[selectedPath.length - 1];
                    for (int i = 0; i < selectedPath.length - 1; ++i) {
                        selectedPath2[i] = selectedPath[i];
                    }
                    defaultManager.setSelectedPath(selectedPath2);
                }
            }
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            MenuSelectionManager.defaultManager().processMouseEvent(mouseEvent);
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
        }
    }
}
