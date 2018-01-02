// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.dialogs;

import java.awt.Window;
import java.awt.Dialog;
import jagoclient.gui.DoActionListener;
import jagoclient.gui.ButtonAction;
import jagoclient.Global;
import java.awt.Component;
import jagoclient.gui.MyLabel;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Frame;
import java.awt.event.ActionListener;
import jagoclient.gui.CloseDialog;

public class Question extends CloseDialog implements ActionListener
{
    Object O;
    Frame F;
    public boolean Result;
    
    public Question(final Frame f, final String s, final String s2, final Object o, final boolean b) {
        super(f, s2, b);
        this.Result = false;
        this.F = f;
        final Panel panel = new Panel();
        final FlowLayout layout = new FlowLayout();
        panel.setLayout(layout);
        layout.setAlignment(1);
        panel.add(new MyLabel(" " + s + " "));
        this.add("Center", panel);
        final Panel panel2 = new Panel();
        panel2.add(new ButtonAction(this, Global.resourceString("Yes")));
        panel2.add(new ButtonAction(this, Global.resourceString("No")));
        this.add("South", panel2);
        this.O = o;
        if (b) {
            Global.setpacked(this, "question", 300, 150, f);
        }
        else {
            Global.setpacked(this, "question", 300, 150);
        }
        this.validate();
    }
    
    public void doAction(final String s) {
        Global.notewindow(this, "question");
        if (Global.resourceString("Yes").equals(s)) {
            this.tell(this, this.O, true);
            this.Result = true;
        }
        else if (Global.resourceString("No").equals(s)) {
            this.tell(this, this.O, false);
        }
        this.setVisible(false);
        this.dispose();
    }
    
    public void tell(final Question question, final Object o, final boolean b) {
    }
    
    public boolean result() {
        return this.Result;
    }
}
