// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.dialogs;

import java.awt.Panel;
import java.awt.event.KeyListener;
import java.awt.Window;
import java.awt.Dialog;
import jagoclient.gui.ButtonAction;
import jagoclient.Global;
import jagoclient.gui.Panel3D;
import jagoclient.gui.DoActionListener;
import jagoclient.gui.TextFieldAction;
import java.awt.Component;
import jagoclient.gui.MyLabel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import jagoclient.gui.MyPanel;
import java.awt.TextField;
import java.awt.Frame;
import jagoclient.gui.CloseDialog;

public class GetParameter extends CloseDialog
{
    public boolean Result;
    Object O;
    Frame F;
    TextField T;
    String Helpfile;
    
    public GetParameter(final Frame frame, final String s, final String s2, final Object o, final boolean b) {
        this(frame, s, s2, o, b, "");
    }
    
    public GetParameter(final Frame f, final String s, final String s2, final Object o, final boolean b, final String helpfile) {
        super(f, s2, b);
        this.F = f;
        this.Helpfile = helpfile;
        final MyPanel myPanel = new MyPanel();
        myPanel.setLayout(new BorderLayout());
        myPanel.add("North", new MyLabel(s));
        myPanel.add("Center", this.T = new TextFieldAction(this, "Input", 25));
        this.add("Center", new Panel3D(myPanel));
        final MyPanel myPanel2 = new MyPanel();
        myPanel2.add(new ButtonAction(this, Global.resourceString("OK")));
        myPanel2.add(new ButtonAction(this, Global.resourceString("Cancel")));
        if (!helpfile.equals("")) {
            myPanel2.add(new MyLabel(" "));
            myPanel2.add(new ButtonAction(this, Global.resourceString("Help")));
        }
        this.add("South", new Panel3D(myPanel2));
        this.O = o;
        if (b) {
            Global.setpacked(this, "getparameter", 300, 150, f);
        }
        else {
            Global.setpacked(this, "getparameter", 300, 150);
        }
        this.validate();
        this.T.addKeyListener(this);
    }
    
    public GetParameter(final Frame frame, final String s, final String s2, final Object o, final char c, final boolean b) {
        this(frame, s, s2, o, c, b, "");
    }
    
    public GetParameter(final Frame f, final String s, final String s2, final Object o, final char echoChar, final boolean b, final String helpfile) {
        super(f, s2, b);
        this.F = f;
        this.Helpfile = helpfile;
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add("North", new MyLabel(s));
        panel.add("Center", this.T = new TextFieldAction(this, "Input", 25));
        this.add("Center", new Panel3D(panel));
        this.add("North", new MyLabel(s));
        this.T.setEchoChar(echoChar);
        final Panel panel2 = new Panel();
        panel2.add(new ButtonAction(this, Global.resourceString("OK")));
        panel2.add(new ButtonAction(this, Global.resourceString("Cancel")));
        if (!helpfile.equals("")) {
            panel2.add(new MyLabel(" "));
            panel2.add(new ButtonAction(this, Global.resourceString("Help")));
        }
        this.add("South", new Panel3D(panel2));
        if (b) {
            Global.setpacked(this, "getparameter", 300, 150, f);
        }
        else {
            Global.setpacked(this, "getparameter", 300, 150);
        }
        this.validate();
        this.T.addKeyListener(this);
        this.O = o;
    }
    
    public void doAction(final String s) {
        Global.notewindow(this, "getparameter");
        if (Global.resourceString("Cancel").equals(s)) {
            this.close();
            this.setVisible(false);
            this.dispose();
            return;
        }
        if (s.equals("Input") || s.equals(Global.resourceString("OK"))) {
            if (this.tell(this.O, this.T.getText())) {
                this.setVisible(false);
                this.dispose();
            }
        }
        else {
            if (s.equals(Global.resourceString("Help"))) {
                new Help(this.Helpfile);
                return;
            }
            super.doAction(s);
        }
    }
    
    public boolean tell(final Object o, final String s) {
        return true;
    }
    
    public void set(final String text) {
        this.T.setText(text);
    }
    
    public boolean close() {
        return true;
    }
    
    public String getText() {
        return this.T.getText();
    }
}
