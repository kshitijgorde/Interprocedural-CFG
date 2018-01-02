import java.awt.event.ActionEvent;
import java.awt.AWTEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowListener;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import au.com.rocketdog.project.awc.applet.Main;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.Button;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class c1 extends JDialog implements ActionListener, KeyListener
{
    public Button a;
    public Choice b;
    public TextField c;
    
    public int a() {
        return this.b.getSelectedIndex();
    }
    
    public c1(final Frame frame, final String title, final String[] array) {
        super(frame, true);
        this.a = new Button(Main.p.a("dialog.ok"));
        this.b = new Choice();
        this.c = new TextField();
        for (int i = 0; i < array.length; ++i) {
            this.b.addItem(array[i]);
        }
        this.setTitle(title);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(this.b, "North");
        this.getContentPane().add(this.c, "Center");
        final JPanel panel = new JPanel();
        this.b.addKeyListener(this);
        this.c.addKeyListener(this);
        panel.setLayout(new FlowLayout());
        this.a.addActionListener(this);
        this.setBackground(dj.w);
        this.b.setBackground(dj.ac);
        panel.add(this.a);
        this.getContentPane().add("South", panel);
        this.setSize(600, 400);
        this.setLocation(frame.getLocationOnScreen());
        this.addWindowListener(new c2(this));
        this.pack();
    }
    
    public String b() {
        return this.c.getText();
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            this.a.dispatchEvent(keyEvent);
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.dispose();
    }
    
    public void c() {
        this.dispose();
    }
}
