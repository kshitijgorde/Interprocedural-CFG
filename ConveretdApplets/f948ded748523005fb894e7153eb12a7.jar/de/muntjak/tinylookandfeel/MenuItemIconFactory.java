// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import javax.swing.ButtonModel;
import javax.swing.JMenu;
import java.awt.Color;
import javax.swing.JMenuItem;
import java.awt.Graphics;
import java.awt.Component;
import javax.swing.plaf.UIResource;
import java.awt.Dimension;
import javax.swing.Icon;
import java.io.Serializable;

public class MenuItemIconFactory implements Serializable
{
    private static Icon checkBoxMenuItemIcon;
    private static Icon radioButtonMenuItemIcon;
    private static Icon menuArrowIcon;
    private static final Dimension menuCheckIconSize;
    private static final Dimension menuArrowIconSize;
    
    public static Icon getCheckBoxMenuItemIcon() {
        if (MenuItemIconFactory.checkBoxMenuItemIcon == null) {
            MenuItemIconFactory.checkBoxMenuItemIcon = new CheckBoxMenuItemIcon();
        }
        return MenuItemIconFactory.checkBoxMenuItemIcon;
    }
    
    public static Icon getRadioButtonMenuItemIcon() {
        if (MenuItemIconFactory.radioButtonMenuItemIcon == null) {
            MenuItemIconFactory.radioButtonMenuItemIcon = new RadioButtonMenuItemIcon();
        }
        return MenuItemIconFactory.radioButtonMenuItemIcon;
    }
    
    public static Icon getMenuArrowIcon() {
        if (MenuItemIconFactory.menuArrowIcon == null) {
            MenuItemIconFactory.menuArrowIcon = new MenuArrowIcon();
        }
        return MenuItemIconFactory.menuArrowIcon;
    }
    
    static {
        menuCheckIconSize = new Dimension(10, 10);
        menuArrowIconSize = new Dimension(4, 8);
    }
    
    private static class MenuArrowIcon implements Icon, UIResource, Serializable
    {
        public void paintIcon(final Component component, final Graphics graphics, final int n, final int n2) {
            final JMenuItem menuItem = (JMenuItem)component;
            final ButtonModel model = menuItem.getModel();
            graphics.translate(n, n2);
            if (!model.isEnabled()) {
                graphics.setColor(Theme.menuDisabledFgColor[Theme.style].getColor());
            }
            else if (model.isArmed() || (component instanceof JMenu && model.isSelected())) {
                graphics.setColor(Theme.menuSelectedTextColor[Theme.style].getColor());
            }
            else {
                graphics.setColor(menuItem.getForeground());
            }
            if (component.getComponentOrientation().isLeftToRight()) {
                graphics.drawLine(0, 0, 0, 7);
                graphics.drawLine(1, 1, 1, 6);
                graphics.drawLine(2, 2, 2, 5);
                graphics.drawLine(3, 3, 3, 4);
            }
            else {
                graphics.drawLine(4, 0, 4, 7);
                graphics.drawLine(3, 1, 3, 6);
                graphics.drawLine(2, 2, 2, 5);
                graphics.drawLine(1, 3, 1, 4);
            }
            graphics.translate(-n, -n2);
        }
        
        public int getIconWidth() {
            return MenuItemIconFactory.menuArrowIconSize.width;
        }
        
        public int getIconHeight() {
            return MenuItemIconFactory.menuArrowIconSize.height;
        }
    }
    
    private static class RadioButtonMenuItemIcon implements Icon, UIResource, Serializable
    {
        public void paintIcon(final Component component, final Graphics graphics, final int n, final int n2) {
            final ButtonModel model = ((JMenuItem)component).getModel();
            final boolean selected = model.isSelected();
            final boolean enabled = model.isEnabled();
            final boolean pressed = model.isPressed();
            final boolean armed = model.isArmed();
            graphics.translate(n, n2);
            if (enabled) {
                if (pressed || armed) {
                    graphics.setColor(Theme.menuIconRolloverColor[Theme.style].getColor());
                }
                else {
                    graphics.setColor(Theme.menuIconColor[Theme.style].getColor());
                }
            }
            else {
                graphics.setColor(Theme.menuIconDisabledColor[Theme.style].getColor());
            }
            graphics.drawLine(3, 0, 6, 0);
            graphics.drawLine(9, 3, 9, 6);
            graphics.drawLine(3, 9, 6, 9);
            graphics.drawLine(0, 3, 0, 6);
            graphics.drawLine(1, 1, 2, 1);
            graphics.drawLine(7, 1, 8, 1);
            graphics.drawLine(1, 8, 2, 8);
            graphics.drawLine(7, 8, 8, 8);
            graphics.drawLine(1, 2, 1, 2);
            graphics.drawLine(8, 2, 8, 2);
            graphics.drawLine(1, 7, 1, 7);
            graphics.drawLine(8, 7, 8, 7);
            if (selected) {
                graphics.drawLine(4, 3, 5, 3);
                graphics.drawLine(3, 4, 6, 4);
                graphics.drawLine(3, 5, 6, 5);
                graphics.drawLine(4, 6, 5, 6);
            }
            graphics.translate(-n, -n2);
        }
        
        public int getIconWidth() {
            return MenuItemIconFactory.menuCheckIconSize.width;
        }
        
        public int getIconHeight() {
            return MenuItemIconFactory.menuCheckIconSize.height;
        }
    }
    
    private static class CheckBoxMenuItemIcon implements Icon, UIResource, Serializable
    {
        public void paintIcon(final Component component, final Graphics graphics, final int n, final int n2) {
            final ButtonModel model = ((JMenuItem)component).getModel();
            if (!model.isSelected()) {
                return;
            }
            final boolean enabled = model.isEnabled();
            model.isPressed();
            model.isArmed();
            graphics.translate(n, n2);
            if (enabled) {
                if (model.isArmed() || (component instanceof JMenu && model.isSelected())) {
                    graphics.setColor(Theme.menuIconRolloverColor[Theme.style].getColor());
                }
                else {
                    graphics.setColor(Theme.menuIconColor[Theme.style].getColor());
                }
            }
            else {
                graphics.setColor(Theme.menuIconDisabledColor[Theme.style].getColor());
            }
            graphics.drawLine(2, 4, 2, 6);
            graphics.drawLine(3, 5, 3, 7);
            graphics.drawLine(4, 6, 4, 8);
            graphics.drawLine(5, 5, 5, 7);
            graphics.drawLine(6, 4, 6, 6);
            graphics.drawLine(7, 3, 7, 5);
            graphics.drawLine(8, 2, 8, 4);
            if (!enabled && Theme.derivedStyle[Theme.style] == 1) {
                graphics.setColor(Theme.menuIconShadowColor[Theme.style].getColor());
                graphics.drawLine(9, 3, 9, 5);
                graphics.drawLine(8, 5, 8, 6);
                graphics.drawLine(7, 6, 7, 7);
                graphics.drawLine(6, 7, 6, 8);
                graphics.drawLine(5, 8, 5, 9);
            }
            graphics.translate(-n, -n2);
        }
        
        public int getIconWidth() {
            return MenuItemIconFactory.menuCheckIconSize.width;
        }
        
        public int getIconHeight() {
            return MenuItemIconFactory.menuCheckIconSize.height;
        }
    }
}
