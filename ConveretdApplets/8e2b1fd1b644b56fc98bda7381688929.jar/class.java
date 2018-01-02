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

public class class extends Dialog implements WindowListener, ActionListener, KeyListener
{
    private Frame ya;
    private Button pa;
    private const Tqa;
    private boolean wa;
    private switch Uqa;
    
    public class(final Frame ya, final String s, final String s2, final String text, String s3, final String s4, final switch uqa) {
        super(ya, s, true);
        this.wa = false;
        this.Uqa = uqa;
        this.ya = ya;
        this.addWindowListener(this);
        this.setLayout(new BorderLayout());
        new try("\n").l(s2);
        final continue continue1 = new continue(new GridLayout(2, 1, 0, 0), 1, new Insets(5, 5, 5, 5));
        continue1.add(new Label(s2));
        this.Tqa = new const(50, false);
        if (text != null) {
            this.Tqa.setText(text);
        }
        this.Tqa.addKeyListener(this);
        continue1.add(this.Tqa);
        this.add(continue1, "Center");
        final continue continue2 = new continue(new FlowLayout(1), 1, new Insets(0, 0, 0, 0));
        if (s3 == null || s3.length() == 0) {
            s3 = "OK";
        }
        this.pa = new Button(s3);
        Button button = null;
        if (s4 != null && s4.length() > 0) {
            button = new Button(s4);
        }
        this.pa.addActionListener(this);
        continue2.add(this.pa);
        if (button != null) {
            button.addActionListener(this);
            continue2.add(button);
        }
        this.add(continue2, "South");
        this.pack();
        final Dimension size = this.getSize();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(screenSize.width / 2 - size.width / 2, screenSize.height / 2 - size.height / 2, size.width, size.height);
    }
    
    private void T() {
        if (this.Tqa.getText().trim().length() > 0) {
            this.wa = true;
            this.dispose();
        }
        else {
            this.Tqa.requestFocus();
            new default(this.ya, this.Uqa.a("strInputTextEmptyTitle"), this.Uqa.a("strInputTextEmptyMessage"), this.Uqa.a("btnOK"), null).show();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.pa) {
            this.T();
        }
        else {
            this.dispose();
        }
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
        this.Tqa.requestFocus();
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
    
    public boolean _() {
        return this.wa;
    }
    
    public String getText() {
        return this.Tqa.getText();
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            this.T();
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
