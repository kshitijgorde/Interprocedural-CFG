import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Component;
import java.awt.Label;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Frame;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

class AlertDialog extends Dialog implements ActionListener
{
    private final Button btnOK;
    
    public AlertDialog(final Frame frame, final String s) {
        super(frame, "Information", true);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int n = 300;
        final int n2 = 100;
        this.setLocation(screenSize.width / 2 - n / 2, screenSize.height / 2 - n2 / 2);
        this.setSize(n, n2);
        this.setBackground(Color.lightGray);
        this.setLayout(new BorderLayout());
        this.setResizable(true);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                AlertDialog.this.dispose();
            }
        });
        this.add("Center", new Label(s, 1));
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout(1));
        (this.btnOK = new Button("  Ok  ")).addActionListener(this);
        panel.add(this.btnOK);
        this.add("South", panel);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.btnOK) {
            this.setVisible(false);
            this.dispose();
        }
    }
}
