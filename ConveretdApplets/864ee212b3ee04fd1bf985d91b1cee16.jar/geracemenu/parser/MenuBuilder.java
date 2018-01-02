// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.parser;

import geracemenu.MenuPanel;
import geracemenu.MenuItem;
import geracemenu.LinkActionListener;
import geracemenu.SeparatorItem;
import java.awt.Component;
import java.util.Hashtable;
import geracemenu.Menu;
import geracemenu.GuiUtil;
import java.awt.LayoutManager;
import geracemenu.MenuLayout;
import java.awt.Insets;
import java.applet.AppletContext;
import java.awt.Panel;

public class MenuBuilder
{
    private static transient Panel panel;
    private static transient AppletContext browserControl;
    private static transient DefaultLinkAction defaultLinkListener;
    
    public static void setAppletContext(final AppletContext browserControl) {
        MenuBuilder.browserControl = browserControl;
    }
    
    public static AppletContext getAppletContext() {
        return MenuBuilder.browserControl;
    }
    
    public static Panel getMenuPanel(final ItemNode itemNode) {
        ItemNode sibling = itemNode;
        if (itemNode instanceof VarNode) {
            sibling = itemNode.sibling;
            final Hashtable space = ((VarNode)itemNode).getSpace();
            final Type type;
            if ((type = space.get("layout")) != null) {
                if ("dropdown".equals(((String)((StringValue)type).getValue()).toLowerCase())) {
                    MenuBuilder.panel.setLayout(new MenuLayout(2, new Insets(2, 2, 2, 2)));
                }
                else {
                    MenuBuilder.panel.setLayout(new MenuLayout(new Insets(2, 2, 2, 2)));
                }
            }
            final Type type2;
            if ((type2 = space.get("backgroundColor")) != null) {
                MenuBuilder.panel.setBackground(GuiUtil.findColor((String)((StringValue)type2).getValue(), MenuBuilder.panel.getBackground()));
            }
        }
        build(sibling, null);
        return MenuBuilder.panel;
    }
    
    private static void build(final ItemNode itemNode, final Menu menu) {
        if (itemNode == null) {
            System.err.println("MenuBuilder, build(): null node (!)");
            return;
        }
        if (itemNode instanceof VarNode) {
            return;
        }
        final Hashtable variableSpace = findVariableSpace(itemNode);
        MenuItem menuItem;
        if (menu == null) {
            MenuBuilder.panel.add("", menuItem = itemNode.cloneNode(itemNode.getItemData(), variableSpace));
        }
        else {
            menu.append(menuItem = itemNode.cloneNode(itemNode.getItemData(), variableSpace));
        }
        if (!(menuItem instanceof SeparatorItem) && menuItem.isClickable()) {
            menuItem.addActionListener(MenuBuilder.defaultLinkListener);
        }
        if (menuItem != null && menuItem instanceof Menu) {
            final ItemNode[] children = itemNode.getChildren();
            if (children != null) {
                for (int i = 0; i < children.length; ++i) {
                    build(children[i], (Menu)menuItem);
                }
            }
        }
        if (itemNode.sibling != null) {
            build(itemNode.sibling, menu);
        }
    }
    
    private static Hashtable findVariableSpace(final ItemNode itemNode) {
        final ItemNode[] children = itemNode.getChildren();
        if (children != null) {
            for (int i = 0; i < children.length; ++i) {
                if (children[i] instanceof VarNode) {
                    return ((VarNode)children[i]).getSpace();
                }
            }
        }
        return null;
    }
    
    static {
        MenuBuilder.panel = new MenuPanel(new MenuLayout());
        MenuBuilder.browserControl = null;
        MenuBuilder.defaultLinkListener = new DefaultLinkAction();
    }
}
