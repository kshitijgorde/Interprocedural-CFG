import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Label;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class Ph extends Dialog implements WindowListener, ActionListener, KeyListener
{
    private Frame Da;
    private Button za;
    private c Sja;
    private boolean Ba;
    private o m;
    
    public Ph(final Frame da, final String s, final String s2, final String text, String s3, final String s4, final o m) {
        super(da, s, true);
        this.Ba = false;
        this.m = m;
        this.Da = da;
        this.addWindowListener(this);
        this.setLayout(new BorderLayout());
        new u("\n").m(s2);
        final a a = new a(new GridLayout(2, 1, 0, 0), 1, new Insets(5, 5, 5, 5));
        a.add(new Label(s2));
        this.Sja = new c(50, false);
        if (text != null) {
            this.Sja.setText(text);
        }
        this.Sja.addKeyListener(this);
        a.add(this.Sja);
        this.add(a, "Center");
        final a a2 = new a(new FlowLayout(1), 1, new Insets(0, 0, 0, 0));
        if (s3 == null || s3.length() == 0) {
            s3 = "OK";
        }
        this.za = new Button(s3);
        Button button = null;
        if (s4 != null && s4.length() > 0) {
            button = new Button(s4);
        }
        this.za.addActionListener(this);
        a2.add(this.za);
        if (button != null) {
            button.addActionListener(this);
            a2.add(button);
        }
        this.add(a2, "South");
        this.pack();
        final Dimension size = this.getSize();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(screenSize.width / 2 - size.width / 2, screenSize.height / 2 - size.height / 2, size.width, size.height);
    }
    
    private void Z() {
        if (this.Sja.getText().trim().length() > 0) {
            this.Ba = true;
            this.dispose();
        }
        else {
            this.Sja.requestFocus();
            new b(this.Da, this.m.b("strInputTextEmptyTitle"), this.m.b("strInputTextEmptyMessage"), this.m.b("btnOK"), null).show();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.za) {
            this.Z();
        }
        else {
            this.dispose();
        }
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
        this.Sja.requestFocus();
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.dispose();
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public boolean a() {
        return this.Ba;
    }
    
    public String getText() {
        return this.Sja.getText();
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            this.Z();
        }
        else if (keyEvent.getKeyCode() == 27) {
            this.dispose();
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
}
