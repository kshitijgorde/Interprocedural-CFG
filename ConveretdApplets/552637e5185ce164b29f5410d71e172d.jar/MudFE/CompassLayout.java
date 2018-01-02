// 
// Decompiled by Procyon v0.5.30
// 

package MudFE;

import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager;

public class CompassLayout implements LayoutManager
{
    Component n;
    Component s;
    Component w;
    Component e;
    Component nw;
    Component ne;
    Component sw;
    Component se;
    Component in;
    Component out;
    Component up;
    Component down;
    
    public Dimension minimumLayoutSize(final Container target) {
        return new Dimension(135, 116);
    }
    
    public void removeLayoutComponent(final Component comp) {
    }
    
    public Dimension preferredLayoutSize(final Container target) {
        return new Dimension(135, 116);
    }
    
    public void layoutContainer(final Container target) {
        if (this.n != null) {
            this.n.reshape(51, 1, 27, 26);
        }
        if (this.s != null) {
            this.s.reshape(48, 76, 36, 41);
        }
        if (this.w != null) {
            this.w.reshape(4, 41, 35, 31);
        }
        if (this.e != null) {
            this.e.reshape(92, 39, 33, 32);
        }
        if (this.nw != null) {
            this.nw.reshape(33, 26, 16, 14);
        }
        if (this.ne != null) {
            this.ne.reshape(81, 26, 16, 13);
        }
        if (this.sw != null) {
            this.sw.reshape(32, 73, 14, 15);
        }
        if (this.se != null) {
            this.se.reshape(84, 73, 17, 14);
        }
        if (this.in != null) {
            this.in.reshape(1, 27, 18, 15);
        }
        if (this.out != null) {
            this.out.reshape(1, 73, 24, 13);
        }
        if (this.up != null) {
            this.up.reshape(104, 25, 22, 17);
        }
        if (this.down != null) {
            this.down.reshape(105, 74, 30, 15);
        }
    }
    
    public void addLayoutComponent(final String name, final Component comp) {
        if (name.equals("N")) {
            this.n = comp;
        }
        else if (name.equals("S")) {
            this.s = comp;
        }
        else if (name.equals("W")) {
            this.w = comp;
        }
        else if (name.equals("E")) {
            this.e = comp;
        }
        else if (name.equals("SW")) {
            this.sw = comp;
        }
        else if (name.equals("SE")) {
            this.se = comp;
        }
        else if (name.equals("NW")) {
            this.nw = comp;
        }
        else if (name.equals("NE")) {
            this.ne = comp;
        }
        else if (name.equals("IN")) {
            this.in = comp;
        }
        else if (name.equals("OUT")) {
            this.out = comp;
        }
        else if (name.equals("UP")) {
            this.up = comp;
        }
        else if (name.equals("DOWN")) {
            this.down = comp;
        }
    }
}
