// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.board;

import java.awt.Window;
import jagoclient.gui.ButtonAction;
import jagoclient.gui.CheckboxAction;
import jagoclient.gui.MyPanel;
import java.awt.Component;
import jagoclient.gui.SimplePanel;
import jagoclient.gui.DoActionListener;
import jagoclient.gui.TextFieldAction;
import jagoclient.gui.MyLabel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import jagoclient.Global;
import java.awt.Checkbox;
import java.awt.TextField;
import jagoclient.gui.CloseDialog;

class TextMarkQuestion extends CloseDialog
{
    GoFrame G;
    TextField T;
    Checkbox C;
    
    public TextMarkQuestion(final GoFrame g, final String text) {
        super(g, Global.resourceString("Text_Mark"), false);
        this.G = g;
        this.setLayout(new BorderLayout());
        this.add("Center", new SimplePanel(new MyLabel(Global.resourceString("String")), 1.0, this.T = new TextFieldAction(this, text), 2.0));
        this.T.setText(text);
        final MyPanel myPanel = new MyPanel();
        myPanel.add(this.C = new CheckboxAction(this, Global.resourceString("Auto_Advance")));
        this.C.setState(rene.gui.Global.getParameter("autoadvance", true));
        myPanel.add(new ButtonAction(this, Global.resourceString("Set")));
        myPanel.add(new ButtonAction(this, Global.resourceString("Close")));
        this.add("South", myPanel);
        Global.setpacked(this, "gettextmarkquestion", 300, 150);
        this.show();
    }
    
    public void doAction(final String s) {
        Global.notewindow(this, "gettextmarkquestion");
        rene.gui.Global.setParameter("autoadvance", this.C.getState());
        if (s.equals(Global.resourceString("Set"))) {
            this.G.setTextmark(this.T.getText());
            rene.gui.Global.setParameter("textmark", this.T.getText());
            return;
        }
        if (s.equals(Global.resourceString("Close"))) {
            this.close();
            this.setVisible(false);
            this.dispose();
        }
    }
    
    public boolean close() {
        this.G.TMQ = null;
        return true;
    }
    
    public void advance() {
        if (!this.C.getState()) {
            return;
        }
        final String text = this.T.getText();
        if (text.length() == 1) {
            this.T.setText(String.valueOf((char)(text.charAt(0) + '\u0001')));
            this.G.setTextmark(this.T.getText());
            return;
        }
        try {
            this.T.setText(String.valueOf(Integer.parseInt(text) + 1));
            this.G.setTextmark(this.T.getText());
        }
        catch (Exception ex) {}
    }
}
