// 
// Decompiled by Procyon v0.5.30
// 

package rene.viewer;

import java.awt.PopupMenu;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.List;

public class SystemLister extends Lister
{
    List L;
    static Font F;
    
    public SystemLister() {
        super("dummy");
        this.setLayout(new BorderLayout());
        this.add("Center", this.L = new List());
        if (SystemLister.F != null) {
            this.L.setFont(SystemLister.F);
        }
    }
    
    public String getSelectedItem() {
        return this.L.getSelectedItem();
    }
    
    public void appendLine(final String s) {
        this.L.addItem(s);
    }
    
    public void setText(final String s) {
        if (s.equals("")) {
            this.L.removeAll();
            return;
        }
        this.L.addItem(s);
    }
    
    public void add(final String s) {
        this.L.addItem(s);
    }
    
    public void add(final String s, final Color color) {
        this.add(s);
    }
    
    public void setPopupMenu(final PopupMenu popupMenu) {
    }
    
    static {
        SystemLister.F = null;
    }
}
