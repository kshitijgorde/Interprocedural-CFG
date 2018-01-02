// 
// Decompiled by Procyon v0.5.30
// 

package Prettyfe;

import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager;

public class PrettyLayout8 implements LayoutManager
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
            this.central.reshape(169, 199, 565, 360);
        }
        if (this.input != null) {
            this.input.reshape(175, 565, 550, 22);
        }
        if (this.ob != null) {
            this.ob.reshape(471, 124, 22, 22);
        }
        if (this.cb != null) {
            this.cb.reshape(308, 124, 22, 22);
        }
        if (this.lb != null) {
            this.lb.reshape(308, 149, 22, 22);
        }
        if (this.vb != null) {
            this.vb.reshape(471, 149, 22, 22);
        }
        if (this.sc != null) {
            this.sc.reshape(740, 250, 20, 280);
        }
        if (this.cbl != null) {
            this.cbl.reshape(330, 120, 109, 27);
        }
        if (this.lbl != null) {
            this.lbl.reshape(329, 149, 103, 25);
        }
        if (this.sh != null) {
            this.sh.reshape(2, 447, 145, 153);
        }
        if (this.co != null) {
            this.co.reshape(665, 0, 135, 135);
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
