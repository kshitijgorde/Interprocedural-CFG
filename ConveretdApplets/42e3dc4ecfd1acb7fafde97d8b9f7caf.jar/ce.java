import java.awt.event.ActionEvent;
import java.awt.AWTEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowListener;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.awt.Component;
import au.com.rocketdog.project.awc.applet.Main;
import java.awt.Frame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class ce extends JDialog implements ActionListener, KeyListener
{
    public JButton a;
    private JTextField b;
    
    public ce(final Frame frame, final String title) {
        super(frame, true);
        this.a = new JButton(Main.p.a("dialog.ok"));
        this.b = new JTextField();
        this.setTitle(title);
        this.getContentPane().add("Center", this.b);
        final JPanel panel = new JPanel();
        this.b.addKeyListener(this);
        panel.setLayout(new FlowLayout());
        this.a.addActionListener(this);
        panel.add(this.a);
        this.getContentPane().add("South", panel);
        this.setSize(500, 100);
        this.setLocation(frame.getLocationOnScreen());
        this.setBackground(dj.w);
        this.addWindowListener(new cf(this));
        this.pack();
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public String a() {
        return this.b.getText();
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            this.a.dispatchEvent(keyEvent);
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.dispose();
    }
}
