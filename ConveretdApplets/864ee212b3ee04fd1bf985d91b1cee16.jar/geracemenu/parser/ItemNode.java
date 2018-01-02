// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.parser;

import geracemenu.MenuItem;
import java.util.Hashtable;

public class ItemNode implements ItemNodeModel
{
    public static final int MENU_NODE = 1;
    public static final int MENU_FLAT_NODE = 2;
    public static final int MENU_DROPDOWN_NODE = 3;
    public static final int ITEM_NODE = 4;
    public static final int ITEM_SEPARATOR_NODE = 5;
    public static final int ITEM_LABELLED_NODE = 6;
    public static final int ITEM_LIST_NODE = 7;
    public static final int ITEM_EMBOSS_NODE = 8;
    public static final int VARIABLE_NODE = 9;
    private String data;
    protected ItemNode[] children;
    protected ItemNode sibling;
    
    public String getItemData() {
        return this.data;
    }
    
    public ItemNode[] getChildren() {
        return this.children;
    }
    
    public MenuItem cloneNode(final String s, final Hashtable hashtable) {
        return new MenuItem(s, hashtable);
    }
    
    public static int parseType(final String s) throws IllegalTypeException {
        if ("item".equals(s.toLowerCase())) {
            return 4;
        }
        if ("separator".equals(s.toLowerCase())) {
            return 5;
        }
        if ("labelleditem".equals(s.toLowerCase())) {
            return 6;
        }
        if ("listitem".equals(s.toLowerCase())) {
            return 7;
        }
        if ("embossitem".equals(s.toLowerCase())) {
            return 8;
        }
        if ("menu".equals(s.toLowerCase())) {
            return 1;
        }
        if ("flatmenu".equals(s.toLowerCase())) {
            return 2;
        }
        if ("dropdownmenu".equals(s.toLowerCase())) {
            return 3;
        }
        throw new IllegalTypeException(s);
    }
    
    public static boolean isMenuType(final int n) {
        return n == 1 || n == 2 || n == 3;
    }
    
    public ItemNode(final String data) {
        this.data = data;
        this.children = null;
        this.sibling = null;
    }
}
