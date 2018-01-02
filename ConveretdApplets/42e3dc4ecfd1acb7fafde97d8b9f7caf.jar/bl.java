import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import au.com.rocketdog.project.awc.applet.Main;
import java.awt.Frame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class bl extends JDialog implements ActionListener
{
    public JButton a;
    
    public bl(final Frame frame, final String s) {
        super(frame, true);
        this.a = new JButton(Main.p.a("dialog.ok"));
        final JLabel label = new JLabel(s);
        label.setAlignmentX(0.0f);
        final JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        this.a.addActionListener(this);
        panel.add(this.a);
        this.getContentPane().add(panel, "South");
        this.getContentPane().add(label, "North");
        this.setLocation(100, 200);
        this.getContentPane().setBackground(dj.w);
        this.pack();
    }
    
    public bl(final Frame frame, final String s, final String s2) {
        super(frame, true);
        this.a = new JButton(Main.p.a("dialog.ok"));
        final JLabel label = new JLabel(s);
        label.setAlignmentX(0.0f);
        final JLabel label2 = new JLabel(s2);
        label2.setAlignmentX(0.0f);
        final JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        this.a.addActionListener(this);
        panel.add(this.a);
        this.getContentPane().add(panel, "South");
        this.getContentPane().add(label, "North");
        this.getContentPane().add(label2, "Center");
        this.setLocation(100, 200);
        this.pack();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.dispose();
    }
}
