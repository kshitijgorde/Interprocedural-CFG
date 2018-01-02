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
import java.awt.Frame;
import java.awt.TextField;
import java.awt.Button;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class Lh extends Dialog implements WindowListener, ActionListener, KeyListener
{
    private Button za;
    private boolean Ba;
    private TextField jka;
    private TextField kka;
    private TextField lka;
    private TextField mka;
    private int nka;
    private int oka;
    private int pka;
    private int qka;
    
    public Lh(final Frame frame, final o o, final int n, final int n2, final int n3, final int n4) {
        super(frame, o.b("strSetAxisMarginsTitle"), true);
        this.Ba = false;
        this.nka = 0;
        this.oka = 0;
        this.pka = 0;
        this.qka = 0;
        this.addWindowListener(this);
        this.setLayout(new BorderLayout());
        final a a = new a(new GridLayout(5, 3, 4, 4), 1, new Insets(5, 5, 5, 5));
        a.add(new Label(o.b("strInputAxisMargins")));
        a.add(new Label(""));
        a.add(new Label(""));
        a.add(new Label(o.b("strPriceAxisMaxMargin")));
        (this.kka = new c(3, true)).setText(Integer.toString(n4));
        this.kka.addKeyListener(this);
        a.add(this.kka);
        a.add(new Label(o.b("strAxisMarginFormat")));
        a.add(new Label(o.b("strPriceAxisMinMargin")));
        (this.jka = new c(3, true)).setText(Integer.toString(n3));
        this.jka.addKeyListener(this);
        a.add(this.jka);
        a.add(new Label(o.b("strAxisMarginFormat")));
        a.add(new Label(o.b("strTimeAxisMinMargin")));
        (this.lka = new c(3, true)).setText(Integer.toString(n));
        this.lka.addKeyListener(this);
        a.add(this.lka);
        a.add(new Label(o.b("strAxisMarginFormat")));
        a.add(new Label(o.b("strTimeAxisMaxMargin")));
        (this.mka = new c(3, true)).setText(Integer.toString(n2));
        this.mka.addKeyListener(this);
        a.add(this.mka);
        a.add(new Label(o.b("strAxisMarginFormat")));
        this.add(a, "Center");
        final a a2 = new a(new FlowLayout(1), 1, new Insets(0, 0, 0, 0));
        this.za = new Button(o.b("btnOK"));
        final Button button = new Button(o.b("btnCancel"));
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
    
    private static int b(final TextField textField) {
        int int1 = 0;
        try {
            int1 = Integer.parseInt(textField.getText());
        }
        catch (NumberFormatException ex) {}
        return int1;
    }
    
    private void Z() {
        this.pka = b(this.lka);
        this.qka = b(this.mka);
        this.nka = b(this.jka);
        this.oka = b(this.kka);
        this.Ba = true;
        this.dispose();
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
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            this.Z();
        }
        else if (keyEvent.getKeyCode() == 27) {
            this.dispose();
        }
    }
    
    public boolean a() {
        return this.Ba;
    }
    
    public int _a() {
        return this.nka;
    }
    
    public int aa() {
        return this.oka;
    }
    
    public int ba() {
        return this.pka;
    }
    
    public int ca() {
        return this.qka;
    }
}
