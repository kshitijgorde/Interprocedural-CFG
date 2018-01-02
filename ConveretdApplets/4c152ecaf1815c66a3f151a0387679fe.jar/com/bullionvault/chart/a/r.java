// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.a;

import java.awt.event.ItemListener;
import java.awt.CheckboxMenuItem;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import com.bullionvault.chart.resources.Resources;
import javax.swing.JMenu;
import java.util.Date;

public class r
{
    private final Date a;
    private final String b;
    
    public r() {
    }
    
    public static JMenu a(final Object o, final Object[] array, final Object o2) {
        JMenu menu;
        if (o instanceof JMenu) {
            menu = (JMenu)o;
        }
        else {
            if (!(o instanceof String)) {
                return null;
            }
            String b;
            if ((b = (String)o).startsWith("menu.")) {
                b = Resources.b(b);
            }
            (menu = new JMenu(b)).setName(b);
            menu.setFont(menu.getFont().deriveFont(0));
        }
        for (int i = 0; i < array.length; ++i) {
            if (array[i] instanceof String) {
                String b2;
                if ((b2 = (String)o).startsWith("menu.")) {
                    b2 = Resources.b(b2);
                }
                final JMenuItem menuItem;
                (menuItem = new JMenuItem(b2)).setFont(menuItem.getFont().deriveFont(0));
                if (o2 instanceof ActionListener) {
                    menuItem.addActionListener((ActionListener)o2);
                }
                menu.add(menuItem);
            }
            else if (array[i] instanceof CheckboxMenuItem && o2 instanceof ItemListener) {
                final JMenuItem menuItem2;
                (menuItem2 = (JMenuItem)array[i]).addItemListener((ItemListener)o2);
                menuItem2.setFont(menuItem2.getFont().deriveFont(0));
                menu.add(menuItem2);
            }
            else if (array[i] instanceof JMenuItem) {
                final JMenuItem menuItem3 = (JMenuItem)array[i];
                if (o2 instanceof ActionListener) {
                    menuItem3.addActionListener((ActionListener)o2);
                }
                if (menuItem3.getText().startsWith("menu.")) {
                    menuItem3.setText(Resources.b(menuItem3.getText()));
                }
                menuItem3.setName(menu.getName());
                menuItem3.setFont(menuItem3.getFont().deriveFont(0));
                menu.add(menuItem3);
            }
            else if (array[i] == null) {
                menu.addSeparator();
            }
        }
        menu.setFont(menu.getFont().deriveFont(0));
        return menu;
    }
    
    public r(final Date a, final String b) {
        this.a = a;
        this.b = b;
    }
    
    public Date a() {
        return this.a;
    }
    
    public String b() {
        return this.b;
    }
}
