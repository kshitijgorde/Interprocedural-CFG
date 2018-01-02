// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a.b;

import a.a.a.a.c.a.b;
import a.a.a.a.e.a;
import a.a.a.a.c.a.c;
import java.awt.CheckboxMenuItem;

public class g extends CheckboxMenuItem implements c
{
    private a j;
    private a.a.a.a.b.a k;
    
    public g(final String s, final boolean b, final a j, final a.a.a.a.b.a k) {
        super(s, b);
        this.j = j;
        this.k = k;
    }
    
    public a.a.a.a.b.a byte() {
        return this.k;
    }
    
    public void a(final b b) {
        final a.a.a.a.c.a.a a = (a.a.a.a.c.a.a)b.getSource();
        final String label = this.getLabel();
        if (label.equals(this.j.a("resource/menuitem.checkbox.zoomin"))) {
            final int if1 = a.if();
            this.setEnabled(if1 != -1);
            if (if1 != -1) {
                this.setState(if1 == 2);
            }
        }
        else if (label.equals(this.j.a("resource/menuitem.checkbox.zoomout"))) {
            final int if2 = a.if();
            this.setEnabled(if2 != -1);
            if (if2 != -1) {
                this.setState(if2 == 3);
            }
        }
        else if (label.equals(this.j.a("resource/menuitem.checkbox.pan"))) {
            final int if3 = a.if();
            this.setEnabled(if3 != -1);
            if (if3 != -1) {
                this.setState(if3 == 4);
            }
        }
        else if (label.equals(this.j.a("resource/menuitem.checkbox.rotate"))) {
            final int if4 = a.if();
            this.setEnabled(if4 != -1);
            if (if4 != -1) {
                this.setState(if4 == 5);
            }
        }
        else if (label.equals(this.j.a("resource/menuitem.checkbox.hotspots"))) {
            final int byte1 = a.byte();
            this.setEnabled(byte1 != -1);
            if (byte1 != -1) {
                this.setState(byte1 == 1);
            }
        }
        else if (label.equals(this.j.a("resource/menuitem.checkbox.magnifier"))) {
            final int do1 = a.do();
            this.setEnabled(do1 != -1);
            if (do1 != -1) {
                this.setState(do1 == 1);
            }
        }
        else if (label.equals(this.j.a("resource/menuitem.checkbox.toolbar"))) {
            final int char1 = a.char();
            this.setEnabled(char1 != -1);
            if (char1 != -1) {
                this.setState(char1 == 1);
            }
        }
    }
}
