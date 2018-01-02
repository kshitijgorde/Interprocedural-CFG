// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.dialogs;

import java.awt.Window;
import jagoclient.gui.Panel3D;
import jagoclient.gui.DoActionListener;
import jagoclient.gui.ButtonAction;
import jagoclient.gui.MyPanel;
import java.awt.Component;
import rene.viewer.SystemViewer;
import jagoclient.Global;
import java.awt.Frame;
import rene.viewer.Viewer;
import jagoclient.gui.CloseDialog;

public class Message extends CloseDialog
{
    Viewer T;
    
    public Message(final Frame frame, final String text) {
        super(frame, Global.resourceString("Message"), false);
        this.add("Center", this.T = (rene.gui.Global.getParameter("systemviewer", false) ? new SystemViewer() : new Viewer()));
        this.T.setFont(Global.Monospaced);
        final MyPanel myPanel = new MyPanel();
        myPanel.add(new ButtonAction(this, Global.resourceString("OK")));
        this.add("South", new Panel3D(myPanel));
        Global.setwindow(this, "message", 300, 150);
        this.validate();
        this.show();
        this.T.setText(text);
    }
    
    public void doAction(final String s) {
        Global.notewindow(this, "message");
        if (Global.resourceString("OK").equals(s)) {
            this.setVisible(false);
            this.dispose();
            return;
        }
        super.doAction(s);
    }
}
