import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import mindbright.ssh.SSHMenuHandlerFull;
import java.awt.TextField;
import java.awt.List;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class a2 extends Panel
{
    public List iq;
    public TextField ip;
    
    public a2(final SSHMenuHandlerFull sshMenuHandlerFull, final String s, final ActionListener actionListener, final ActionListener actionListener2) {
        super(new BorderLayout(5, 5));
        this.add(new Label(s), "North");
        this.add(this.iq = new List(5, false), "Center");
        final Panel panel = new Panel(new FlowLayout());
        panel.add(this.ip = new TextField("", 26));
        final Button button;
        panel.add(button = new Button("Add"));
        button.addActionListener(actionListener);
        final Button button2;
        panel.add(button2 = new Button("Delete"));
        button2.addActionListener(actionListener2);
        this.add(panel, "South");
        this.iq.addActionListener(new bk(this));
    }
    
    public final String ie(final int n) {
        return this.iq.getItem(n);
    }
    
    public final void id(final String s) {
        this.iq.add(s);
    }
    
    public final int ic() {
        return this.iq.getSelectedIndex();
    }
    
    public final void ib() {
        this.ip.selectAll();
    }
    
    public final String ia() {
        return this.ip.getText();
    }
    
    public final void removeAll() {
        this.iq.removeAll();
    }
}
