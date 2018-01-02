// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.dialogs;

import java.awt.Window;
import jagoclient.gui.MyLabel;
import jagoclient.gui.DoActionListener;
import jagoclient.gui.ButtonAction;
import jagoclient.gui.MyPanel;
import jagoclient.gui.Panel3D;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import jagoclient.Global;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Color;
import java.awt.Label;
import jagoclient.gui.CloseDialog;

public class ColorEdit extends CloseDialog
{
    ColorScrollbar Red;
    ColorScrollbar Green;
    ColorScrollbar Blue;
    Label RedLabel;
    Label GreenLabel;
    Label BlueLabel;
    Color C;
    Panel CP;
    String Name;
    
    public ColorEdit(final Frame frame, final String name, final int n, final int n2, final int n3, final boolean b) {
        super(frame, Global.resourceString("Edit_Color"), b);
        this.Name = name;
        this.C = Global.getColor(name, n, n2, n3);
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(0, 1));
        panel.add(this.Red = new ColorScrollbar(this, Global.resourceString("Red"), this.C.getRed()));
        panel.add(this.Green = new ColorScrollbar(this, Global.resourceString("Green"), this.C.getGreen()));
        panel.add(this.Blue = new ColorScrollbar(this, Global.resourceString("Blue"), this.C.getBlue()));
        this.add("Center", new Panel3D(panel));
        final MyPanel myPanel = new MyPanel();
        myPanel.add(new ButtonAction(this, Global.resourceString("OK")));
        myPanel.add(new ButtonAction(this, Global.resourceString("Cancel")));
        this.addbutton(myPanel);
        this.add("South", new Panel3D(myPanel));
        (this.CP = new MyPanel()).add(new MyLabel(Global.resourceString("Your_Color")));
        this.CP.setBackground(this.C);
        this.add("North", new Panel3D(this.CP));
        Global.setpacked(this, "coloredit", 350, 150);
        this.validate();
    }
    
    public void addbutton(final Panel panel) {
    }
    
    public ColorEdit(final Frame frame, final String s, final Color color, final boolean b) {
        this(frame, s, color.getRed(), color.getGreen(), color.getBlue(), b);
    }
    
    public void doAction(final String s) {
        Global.notewindow(this, "coloredit");
        if (Global.resourceString("Cancel").equals(s)) {
            this.setVisible(false);
            this.dispose();
            return;
        }
        if (Global.resourceString("OK").equals(s)) {
            Global.setColor(this.Name, this.C);
            this.tell(this.C);
            this.setVisible(false);
            this.dispose();
        }
    }
    
    public void setcolor() {
        this.C = new Color(this.Red.value(), this.Green.value(), this.Blue.value());
        this.CP.setBackground(this.C);
        this.CP.repaint();
    }
    
    public void tell(final Color color) {
    }
    
    public Color color() {
        return this.C;
    }
}
