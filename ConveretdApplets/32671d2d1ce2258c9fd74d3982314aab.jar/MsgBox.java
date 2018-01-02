import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

class MsgBox extends Dialog implements ActionListener
{
    boolean id;
    Button ok;
    Button can;
    
    MsgBox(final Frame frame, final String s, final boolean b) {
        super(frame, "Message", true);
        this.id = false;
        this.setLayout(new BorderLayout());
        this.add("Center", new Label(s));
        this.addOKCancelPanel(b);
        this.createFrame();
        this.pack();
        this.setVisible(true);
    }
    
    void addOKCancelPanel(final boolean b) {
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout());
        this.createOKButton(panel);
        if (b) {
            this.createCancelButton(panel);
        }
        this.add("South", panel);
    }
    
    void createOKButton(final Panel panel) {
        panel.add(this.ok = new Button("OK"));
        this.ok.addActionListener(this);
    }
    
    void createCancelButton(final Panel panel) {
        panel.add(this.can = new Button("Cancel"));
        this.can.addActionListener(this);
    }
    
    void createFrame() {
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.setLocation(screenSize.width / 3, screenSize.height / 3);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.ok) {
            this.id = true;
            this.setVisible(false);
            return;
        }
        if (actionEvent.getSource() == this.can) {
            this.setVisible(false);
        }
    }
}
