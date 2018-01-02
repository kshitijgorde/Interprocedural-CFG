// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.board;

import java.awt.event.KeyListener;
import java.awt.Window;
import jagoclient.gui.ButtonAction;
import jagoclient.gui.MyPanel;
import jagoclient.gui.DoActionListener;
import java.awt.Component;
import jagoclient.gui.MyLabel;
import java.awt.Frame;
import jagoclient.Global;
import jagoclient.gui.TextFieldAction;
import jagoclient.gui.CloseDialog;

class GetSearchString extends CloseDialog
{
    GoFrame GF;
    TextFieldAction T;
    static boolean Active;
    
    public GetSearchString(final GoFrame gf) {
        super(gf, Global.resourceString("Search"), false);
        if (GetSearchString.Active) {
            return;
        }
        this.add("North", new MyLabel(Global.resourceString("Search_String")));
        this.add("Center", this.T = new TextFieldAction(this, "Input", 25));
        final MyPanel myPanel = new MyPanel();
        myPanel.add(new ButtonAction(this, Global.resourceString("Search")));
        myPanel.add(new ButtonAction(this, Global.resourceString("Cancel")));
        this.add("South", myPanel);
        Global.setpacked(this, "getparameter", 300, 150);
        this.validate();
        this.T.addKeyListener(this);
        this.T.setText(rene.gui.Global.getParameter("searchstring", "++"));
        this.GF = gf;
        this.show();
        GetSearchString.Active = true;
    }
    
    public void doAction(final String s) {
        if (s.equals(Global.resourceString("Search")) || s.equals("Input")) {
            rene.gui.Global.setParameter("searchstring", this.T.getText());
            this.GF.search();
            System.out.println(this.T.getText());
            return;
        }
        if (s.equals(Global.resourceString("Cancel"))) {
            this.setVisible(false);
            this.dispose();
            GetSearchString.Active = false;
        }
    }
    
    public void dispose() {
        GetSearchString.Active = false;
        super.dispose();
    }
}
