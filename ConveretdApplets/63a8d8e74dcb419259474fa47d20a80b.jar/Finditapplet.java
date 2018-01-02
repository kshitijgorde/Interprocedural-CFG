import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Finditapplet extends Applet implements ActionListener
{
    Button ok1;
    Findit fa;
    int autoload;
    
    public void init() {
        this.setLayout(null);
        this.autoload = ((this.getParameter("autoload") == null) ? 0 : Integer.parseInt(this.getParameter("autoload")));
        this.ok1.addActionListener(this);
        this.ok1.requestFocus();
        final Dimension size = this.getSize();
        this.ok1.setBounds(1, 1, size.width - 2, size.height - 2);
        this.add(this.ok1);
    }
    
    public void start() {
        if (this.autoload == 1) {
            (this.fa = new Findit(this)).setVisible(true);
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand() == "Start Findit" && this.fa == null) {
            (this.fa = new Findit(this)).setVisible(true);
        }
    }
    
    public void paint(final Graphics graphics) {
    }
    
    public Finditapplet() {
        this.ok1 = new Button("Start Findit");
    }
}
