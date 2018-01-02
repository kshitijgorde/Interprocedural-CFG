import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class a8 extends JDialog implements ActionListener
{
    public JButton a;
    
    public a8(final Frame frame, final String s, final String s2, final int n, final int n2) {
        super(frame, true);
        this.a = new JButton("OK");
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(new JLabel(s, 0), "North");
        this.getContentPane().add(new JLabel(s2, 0), "Center");
        this.a(n, n2);
    }
    
    private void a(final int n, final int n2) {
        final JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        this.a.addActionListener(this);
        panel.add(this.a);
        this.getContentPane().add(panel, "South");
        this.setSize(n, n2);
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.setLocation(screenSize.width / 2 - n / 2, screenSize.height / 2 - n2 / 2);
        try {
            this.pack();
        }
        catch (Exception ex) {}
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.dispose();
    }
}
