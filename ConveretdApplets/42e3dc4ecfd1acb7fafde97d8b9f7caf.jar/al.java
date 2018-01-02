import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import au.com.rocketdog.project.awc.applet.Main;
import java.awt.Frame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class al extends JDialog implements ActionListener
{
    public JButton a;
    public JButton b;
    
    public al(final Frame frame, final String s, final int n, final int n2) {
        super(frame, true);
        this.a = new JButton(Main.p.a("dialog.yes"));
        this.b = new JButton(Main.p.a("dialog.no"));
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(new JLabel(s), "Center");
        final JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        this.a.addActionListener(this);
        panel.add(this.a);
        this.b.addActionListener(this);
        panel.add(this.b);
        this.getContentPane().add(panel, "South");
        this.setSize(n, n2);
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.setLocation(screenSize.width / 2 - n / 2, screenSize.height / 2 - n2 / 2);
        this.setLocation(100, 200);
        this.setBackground(dj.w);
        try {
            this.pack();
        }
        catch (Exception ex) {}
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.dispose();
    }
}
