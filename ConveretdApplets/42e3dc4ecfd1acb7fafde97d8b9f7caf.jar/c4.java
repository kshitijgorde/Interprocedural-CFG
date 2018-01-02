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
import java.awt.Choice;
import java.awt.Button;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class c4 extends JDialog implements ActionListener, KeyListener
{
    public Button a;
    private Choice b;
    private String[] c;
    
    public c4(final Frame frame, final String title, final String[] c) {
        super(frame, true);
        this.a = new Button(Main.p.a("dialog.ok"));
        this.b = new Choice();
        this.c = c;
        for (int i = 0; i < c.length; ++i) {
            this.b.addItem(c[i]);
        }
        this.setTitle(title);
        this.getContentPane().add("Center", this.b);
        final JPanel panel = new JPanel();
        this.b.addKeyListener(this);
        this.setBackground(dj.w);
        this.b.setBackground(dj.ac);
        panel.setLayout(new FlowLayout());
        this.a.addActionListener(this);
        panel.add(this.a);
        this.getContentPane().add("South", panel);
        this.setSize(600, 100);
        this.setLocation(frame.getLocationOnScreen());
        this.addWindowListener(new c5(this));
        this.pack();
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public String a() {
        return this.b.getSelectedItem();
    }
    
    public int b() {
        return this.b.getSelectedIndex();
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
