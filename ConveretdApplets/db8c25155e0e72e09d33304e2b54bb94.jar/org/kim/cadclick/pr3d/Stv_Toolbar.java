// 
// Decompiled by Procyon v0.5.30
// 

package org.kim.cadclick.pr3d;

import java.awt.Rectangle;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.Color;
import java.awt.Panel;

public class Stv_Toolbar extends Panel
{
    public static final Color a;
    private String ID;
    protected int orientation;
    private String hidden;
    private Vector groups;
    
    static {
        a = new Color(192, 192, 192);
    }
    
    public Stv_Toolbar(final int n, final int n2, final String id, final int orientation, final String s) {
        this.ID = id;
        this.hidden = s.toLowerCase();
        this.setLocation(n, n2);
        this.setSize(0, 0);
        this.orientation = orientation;
        this.setBackground(null);
        this.groups = new Vector();
        this.setVisible(false);
    }
    
    public void if() {
        this.validate();
        this.setVisible(true);
    }
    
    public Stv_Button a(final Stv_Button stv_Button, final String s, final boolean b) {
        this.a(stv_Button);
        m group = null;
        final Enumeration<m> elements = this.groups.elements();
        while (elements.hasMoreElements()) {
            final m m = elements.nextElement();
            if (m.a.equalsIgnoreCase(s)) {
                group = m;
                break;
            }
        }
        if (group == null) {
            group = new m(s);
            this.groups.addElement(group);
        }
        group.a(stv_Button, b);
        stv_Button.group = group;
        return stv_Button;
    }
    
    public Stv_Button a(final Stv_Button stv_Button) {
        if (this.hidden.indexOf(String.valueOf(this.ID.toLowerCase()) + "." + stv_Button.a()) == -1) {
            final Rectangle bounds = this.getBounds();
            switch (this.orientation) {
                case 0: {
                    this.setSize(bounds.width + Stv_Button.if, Stv_Button.a + 1);
                    this.setLayout(new GridLayout(1, this.getComponentCount() + 1, 0, 0));
                    break;
                }
                case 1: {
                    this.setSize(Stv_Button.if + 1, bounds.height + Stv_Button.a);
                    this.setLayout(new GridLayout(this.getComponentCount() + 1, 1, 0, 0));
                    break;
                }
                default: {
                    System.err.println("Unknown toolbar orientation: " + this.orientation);
                    return null;
                }
            }
            return (Stv_Button)this.add(stv_Button);
        }
        return null;
    }
    
    public Stv_Button a(final int n) {
        final Component[] components = this.getComponents();
        for (int componentCount = this.getComponentCount(), i = 0; i < componentCount; ++i) {
            if (((Stv_Button)components[i]).functionCode == n) {
                return (Stv_Button)components[i];
            }
        }
        return null;
    }
}
