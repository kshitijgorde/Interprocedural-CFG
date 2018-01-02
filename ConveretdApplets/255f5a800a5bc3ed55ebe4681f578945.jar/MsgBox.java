import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Label;
import java.awt.Panel;
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
    
    MsgBox(final Frame frame, final String s, final String s2, final String s3, final String s4, final int n) {
        super(frame, s, true);
        this.id = false;
        this.setLayout(new BorderLayout());
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add("North", new Label(s2));
        if (s3 != "") {
            panel.add("Center", new Label(s3));
        }
        if (s4 != "") {
            panel.add("South", new Label(s4));
        }
        this.add("Center", panel);
        this.addButtonsPanel(n);
        this.createFrame();
        this.pack();
        this.setVisible(true);
    }
    
    void addButtonsPanel(final int n) {
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout());
        this.createFirstButton(panel, n);
        if (n > 1) {
            this.createSecondButton(panel, n);
        }
        this.add("South", panel);
    }
    
    void createFirstButton(final Panel panel, final int n) {
        if (n < 3) {
            panel.add(this.ok = new Button("OK"));
        }
        else {
            panel.add(this.ok = new Button("Yes"));
        }
        this.ok.addActionListener(this);
    }
    
    void createSecondButton(final Panel panel, final int n) {
        if (n == 2) {
            panel.add(this.can = new Button("Cancel"));
        }
        if (n == 3) {
            panel.add(this.can = new Button("No"));
        }
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
        }
        else if (actionEvent.getSource() == this.can) {
            this.setVisible(false);
        }
    }
}
