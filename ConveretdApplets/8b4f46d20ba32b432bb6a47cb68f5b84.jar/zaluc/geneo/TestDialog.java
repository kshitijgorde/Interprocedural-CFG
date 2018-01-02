// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.geneo;

import zaluc.utils.Callback;
import java.awt.Event;
import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Button;
import zaluc.utils.EntryDlg;
import java.awt.Frame;
import java.applet.Applet;

public class TestDialog extends Applet
{
    Frame a_window;
    EntryDlg dialog;
    Button show_dialog;
    
    public void init() {
        System.out.println("Geneo Test Dialog Applet");
        this.setLayout(new BorderLayout(0, 0));
        Container container;
        for (container = this.getParent(); container != null && !(container instanceof Frame); container = container.getParent()) {}
        this.a_window = (Frame)container;
        this.add("South", this.show_dialog = new Button("Show Dialog"));
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            (this.dialog = new EntryDlg(this.a_window, "Entry Dialog", "Enter Yo Password", true, null, null, 1)).show();
        }
        return true;
    }
}
