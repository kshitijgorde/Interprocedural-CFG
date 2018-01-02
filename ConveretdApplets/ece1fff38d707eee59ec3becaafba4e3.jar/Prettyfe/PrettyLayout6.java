// 
// Decompiled by Procyon v0.5.30
// 

package Prettyfe;

import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager;

public class PrettyLayout6 implements LayoutManager
{
    Component central;
    Component input;
    Component ob;
    Component lb;
    Component vb;
    Component cb;
    Component sc;
    Component cbl;
    Component lbl;
    Component sh;
    Component co;
    
    public Dimension minimumLayoutSize(final Container target) {
        return new Dimension(800, 600);
    }
    
    public void removeLayoutComponent(final Component comp) {
        if (this.cbl == comp) {
            this.cbl = null;
        }
    }
    
    public Dimension preferredLayoutSize(final Container target) {
        return new Dimension(800, 600);
    }
    
    public void layoutContainer(final Container target) {
        if (this.central != null) {
            this.central.reshape(113, 138, 470, 280);
        }
        if (this.input != null) {
            this.input.reshape(113, 423, 447, 18);
        }
        if (this.ob != null) {
            this.ob.reshape(376, 88, 16, 17);
        }
        if (this.cb != null) {
            this.cb.reshape(247, 88, 16, 17);
        }
        if (this.lb != null) {
            this.lb.reshape(248, 108, 16, 17);
        }
        if (this.vb != null) {
            this.vb.reshape(376, 108, 16, 17);
        }
        if (this.sc != null) {
            this.sc.reshape(585, 190, 15, 200);
        }
        if (this.cbl != null) {
            this.cbl.reshape(265, 83, 79, 20);
        }
        if (this.lbl != null) {
            this.lbl.reshape(265, 105, 78, 20);
        }
        if (this.sh != null) {
            this.sh.reshape(0, 318, 102, 132);
        }
        if (this.co != null) {
            this.co.reshape(459, 1, 141, 143);
        }
    }
    
    public void addLayoutComponent(final String name, final Component comp) {
        if (name.equals("Central")) {
            this.central = comp;
        }
        else if (name.equals("Input")) {
            this.input = comp;
        }
        else if (name.equals("OB")) {
            this.ob = comp;
        }
        else if (name.equals("LB")) {
            this.lb = comp;
        }
        else if (name.equals("VB")) {
            this.vb = comp;
        }
        else if (name.equals("CB")) {
            this.cb = comp;
        }
        else if (name.equals("Sc")) {
            this.sc = comp;
        }
        else if (name.equals("CBL")) {
            this.cbl = comp;
        }
        else if (name.equals("LBL")) {
            this.lbl = comp;
        }
        else if (name.equals("SH")) {
            this.sh = comp;
        }
        else if (name.equals("CO")) {
            this.co = comp;
        }
    }
}
