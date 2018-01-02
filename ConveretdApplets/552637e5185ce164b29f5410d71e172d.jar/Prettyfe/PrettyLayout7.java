// 
// Decompiled by Procyon v0.5.30
// 

package Prettyfe;

import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager;

public class PrettyLayout7 implements LayoutManager
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
            this.central.reshape(135, 173, 565, 346);
        }
        if (this.input != null) {
            this.input.reshape(142, 521, 547, 25);
        }
        if (this.ob != null) {
            this.ob.reshape(450, 106, 22, 22);
        }
        if (this.cb != null) {
            this.cb.reshape(297, 106, 22, 22);
        }
        if (this.lb != null) {
            this.lb.reshape(297, 132, 22, 22);
        }
        if (this.vb != null) {
            this.vb.reshape(450, 132, 22, 22);
        }
        if (this.sc != null) {
            this.sc.reshape(704, 228, 15, 249);
        }
        if (this.cbl != null) {
            this.cbl.reshape(318, 106, 94, 23);
        }
        if (this.lbl != null) {
            this.lbl.reshape(318, 130, 96, 25);
        }
        if (this.sh != null) {
            this.sh.reshape(2, 403, 133, 152);
        }
        if (this.co != null) {
            this.co.reshape(575, 1, 163, 151);
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
