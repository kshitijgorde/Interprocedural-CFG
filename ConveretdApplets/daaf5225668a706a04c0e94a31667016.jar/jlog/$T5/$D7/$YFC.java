// 
// Decompiled by Procyon v0.5.30
// 

package jlog.$T5.$D7;

import java.util.ResourceBundle;
import java.awt.MenuShortcut;
import java.awt.MenuItem;
import java.awt.event.ActionListener;
import java.awt.Menu;

public class $YFC
{
    public static void addActionListener(final Menu menu, final ActionListener actionListener) {
        setActionListener(menu, actionListener, true);
    }
    
    public static MenuItem addItem(final Menu menu, final String s, final int n) {
        final MenuItem menuItem = new MenuItem(s);
        if (n != 0) {
            menuItem.setShortcut(new MenuShortcut(n));
        }
        if (menu != null) {
            menu.add(menuItem);
        }
        return menuItem;
    }
    
    public static MenuItem getMenuItem(final Menu menu, final String s) {
        int itemCount = menu.getItemCount();
        while (itemCount-- != 0) {
            final MenuItem item = menu.getItem(itemCount);
            if (s.equals(item.getActionCommand())) {
                return item;
            }
            if (!(item instanceof Menu)) {
                continue;
            }
            getMenuItem((Menu)item, s);
        }
        return null;
    }
    
    public static void removeActionListener(final Menu menu, final ActionListener actionListener) {
        setActionListener(menu, actionListener, false);
    }
    
    private static void setActionListener(final Menu menu, final ActionListener actionListener, final boolean b) {
        if (b) {
            menu.addActionListener(actionListener);
        }
        else {
            menu.removeActionListener(actionListener);
        }
        int itemCount = menu.getItemCount();
        while (itemCount-- != 0) {
            final MenuItem item = menu.getItem(itemCount);
            if (item instanceof Menu) {
                setActionListener((Menu)item, actionListener, b);
            }
        }
    }
    
    public static void setLabel(final MenuItem menuItem, final ResourceBundle resourceBundle, final String s) {
        if (resourceBundle != null && !s.startsWith("-") && !s.equals("")) {
            String string = s;
            try {
                string = resourceBundle.getString(s);
            }
            catch (Exception ex) {}
            menuItem.setLabel(string);
        }
        else {
            menuItem.setLabel(s);
        }
        menuItem.setActionCommand(s);
    }
    
    public static void setLanguage(final Menu menu, final ResourceBundle resourceBundle) {
        setLabel(menu, resourceBundle, menu.getActionCommand());
        int itemCount = menu.getItemCount();
        while (itemCount-- != 0) {
            final MenuItem item = menu.getItem(itemCount);
            if (item instanceof Menu) {
                setLanguage((Menu)item, resourceBundle);
            }
            else {
                final String actionCommand = item.getActionCommand();
                if (actionCommand.startsWith("-")) {
                    continue;
                }
                setLabel(item, resourceBundle, actionCommand);
            }
        }
    }
}
