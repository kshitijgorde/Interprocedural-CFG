// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.board;

import java.awt.Window;
import jagoclient.gui.DoActionListener;
import jagoclient.gui.ButtonAction;
import java.awt.BorderLayout;
import jagoclient.gui.GrayTextField;
import java.awt.Component;
import jagoclient.gui.MyLabel;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import jagoclient.gui.MyPanel;
import java.awt.Frame;
import jagoclient.Global;
import java.awt.TextField;
import java.awt.TextArea;
import jagoclient.gui.CloseDialog;

class EditCopyright extends CloseDialog
{
    TextArea Copyright;
    TextField User;
    Node N;
    
    public EditCopyright(final GoFrame goFrame, final Node n) {
        super(goFrame, Global.resourceString("Copyright_of_Game"), false);
        final MyPanel myPanel = new MyPanel();
        this.N = n;
        myPanel.setLayout(new GridLayout(0, 2));
        myPanel.add(new MyLabel(Global.resourceString("User")));
        myPanel.add(this.User = new GrayTextField(n.getaction("US")));
        this.add("North", myPanel);
        final MyPanel myPanel2 = new MyPanel();
        myPanel2.setLayout(new BorderLayout());
        myPanel2.add("North", new MyLabel(Global.resourceString("Copyright")));
        myPanel2.add("Center", this.Copyright = new TextArea("", 0, 0, 1));
        this.Copyright.setBackground(Global.gray);
        this.add("Center", myPanel2);
        final MyPanel myPanel3 = new MyPanel();
        myPanel3.add(new ButtonAction(this, Global.resourceString("OK")));
        myPanel3.add(new ButtonAction(this, Global.resourceString("Cancel")));
        this.add("South", myPanel3);
        Global.setwindow(this, "editcopyright", 350, 400);
        this.show();
        this.Copyright.setText(n.getaction("CP"));
    }
    
    public void doAction(final String s) {
        Global.notewindow(this, "editcopyright");
        if (Global.resourceString("OK").equals(s)) {
            this.N.setaction("US", this.User.getText());
            this.N.setaction("CP", this.Copyright.getText());
        }
        this.setVisible(false);
        this.dispose();
    }
}
