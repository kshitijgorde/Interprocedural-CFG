import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Calendar_applet extends Applet implements ActionListener
{
    Monthly ins;
    TextField text;
    
    public Calendar_applet() {
        this.ins = null;
        this.text = null;
    }
    
    public void init() {
        this.ins = new Monthly(this.getSize());
        this.text = new TextField(20);
        final Label label = new Label("Enter the month/year (MM/YYYY)");
        final Panel panel = new Panel();
        this.text.addActionListener(this);
        panel.setLayout(new FlowLayout());
        panel.add(this.text);
        panel.add(label);
        this.setLayout(new BorderLayout());
        this.add(this.ins, "Center");
        this.add(panel, "South");
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.ins.setEntry("01/" + this.text.getText().trim());
        this.text.setText("");
        this.ins.repaint();
        this.repaint();
    }
    
    public String getAppletInfo() {
        final StringBuffer sb = new StringBuffer();
        sb.append("Choose your Calendar\n");
        sb.append('©');
        sb.append("by Neri Llosa (nerill@aol.com), All Rights Reserved\n");
        return sb.toString();
    }
}
