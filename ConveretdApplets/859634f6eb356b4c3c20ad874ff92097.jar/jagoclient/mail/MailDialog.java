// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.mail;

import jagoclient.dialogs.Message;
import java.awt.Window;
import jagoclient.gui.DoActionListener;
import jagoclient.gui.ButtonAction;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import jagoclient.Global;
import java.awt.Frame;
import java.awt.TextArea;
import jagoclient.gui.CloseDialog;

public class MailDialog extends CloseDialog
{
    String Message;
    TextArea T;
    Frame F;
    
    public MailDialog(final Frame f, final String s) {
        super(f, Global.resourceString("Mail_Game"), false);
        this.Message = s;
        this.F = f;
        this.setLayout(new BorderLayout());
        new Panel().setLayout(new GridLayout(0, 2));
        this.add("Center", this.T = new TextArea());
        this.T.setBackground(Global.gray);
        this.T.setFont(Global.Monospaced);
        this.T.setText(s);
        final Panel panel = new Panel();
        panel.add(new ButtonAction(this, Global.resourceString("Close")));
        this.add("South", panel);
        Global.setwindow(this, "mail", 400, 400);
        this.validate();
        this.show();
    }
    
    public void doAction(final String s) {
        Global.notewindow(this, "mail");
        if (Global.resourceString("Close").equals(s)) {
            this.setVisible(false);
            this.dispose();
            return;
        }
        super.doAction(s);
    }
    
    public void result(final boolean b, final String s) {
        new Message(Global.frame(), s);
    }
}
