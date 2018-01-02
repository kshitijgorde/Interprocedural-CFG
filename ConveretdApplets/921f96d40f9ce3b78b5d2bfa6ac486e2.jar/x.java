import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class x extends db implements dq, KeyListener, ActionListener
{
    private static int p;
    private String d;
    private TextField a;
    private dl n;
    private dl v;
    private dl i;
    private dl l;
    private dh b;
    private String c;
    private long e;
    private int f;
    private int g;
    
    public x(final y y, final String s) {
        super(y, "Private message with " + s);
        this.c = "";
        this.d = String.valueOf(s);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.f = x.p % 9 / 3 * screenSize.width / 3 + du.p(10);
        this.g = x.p % 3 * screenSize.height / 3 + du.p(5);
        ++x.p;
        (this.a = new TextField()).setBackground(Color.white);
        this.a.addKeyListener(this);
        this.a.setFont(dw.l);
        this.a.addActionListener(this);
        (this.b = new dh(this, y.p(), 150, 1)).setBackground(Color.white);
        this.setLayout(new BorderLayout());
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add("Center", this.a);
        panel.add("East", this.v = new dl("Send"));
        this.v.p(this);
        final Panel panel2 = new Panel();
        panel2.setLayout(new BorderLayout());
        panel2.add("West", this.n = new dl("NoPopup"));
        panel2.add("East", this.i = new dl("Close"));
        final Panel panel3 = new Panel();
        panel3.setLayout(new BorderLayout());
        panel3.add("West", this.l = new dl("Mute"));
        panel3.add("East", panel2);
        final dl[] array = { this.l, this.n, this.i };
        for (int i = 0; i < array.length; ++i) {
            array[i].setFont(dw.n);
            array[i].p(this);
        }
        this.add("North", panel3);
        this.add("Center", this.b);
        this.add("South", panel);
        this.l();
        this.pack();
        this.setBounds(this.f, this.g, this.getSize().width, this.getSize().height);
        this.show();
        try {
            this.toFront();
        }
        catch (Exception ex) {}
    }
    
    public final void d() {
        if (this.n.p().equals(" Popup ")) {
            try {
                this.toFront();
            }
            catch (Exception ex) {}
        }
    }
    
    public final String p() {
        return this.d;
    }
    
    public final void v(final String text) {
        this.a.setText(text);
        this.a.select(0, text.length());
        this.e = System.currentTimeMillis();
    }
    
    public final Dimension getPreferredSize() {
        return new Dimension(350, 220);
    }
    
    public final void p(final String s) {
        this.b.i(s);
    }
    
    public final void keyReleased(final KeyEvent keyEvent) {
    }
    
    public final void keyTyped(final KeyEvent keyEvent) {
    }
    
    public final void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.isActionKey() && keyEvent.getModifiers() == 0) {
            final int keyCode = keyEvent.getKeyCode();
            if (keyCode == 114) {
                this.v(this.c);
            }
            else if (keyCode == 115) {
                this.a.setText("");
            }
            this.a.requestFocus();
        }
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final i p = super.p.p();
        final String d = p.d();
        if (actionEvent.getSource() == this.a || actionEvent.getSource() == this.v) {
            final String p2 = super.p.p().p(this.a);
            this.a.setText("");
            if (p2.length() == 0) {
                return;
            }
            this.c = p2;
            if (p2.startsWith("/")) {
                this.p("<4>command not allowed here");
                return;
            }
            if (this.e + 4000L > System.currentTimeMillis()) {
                du.p((int)(this.e + 4000L - System.currentTimeMillis()));
            }
            if (this.d.equalsIgnoreCase("*Helpers*")) {
                if (!super.p.p().d && p.p("WALLHELPER " + p2)) {
                    this.p("<3>{" + d + "} <12>" + p2);
                    super.p.p().l("<3>+HH+" + d + ": <12>" + p2);
                }
            }
            else if (this.d.equalsIgnoreCase("*RoomHelpers*")) {
                if (!super.p.p().d && p.p("WALLRH " + p2)) {
                    this.p("<3>{" + d + "} <12>" + p2);
                    super.p.p().l("<3>+RR+" + d + ": <12>" + p2);
                }
            }
            else {
                if (!p.p("M " + this.d + " " + p2)) {
                    return;
                }
                this.p("<12>{" + d + "} <5>" + p2);
                super.p.p().l("<6>{to " + this.d + "} <5>" + p2);
            }
        }
        else {
            if (actionEvent.getSource() == this.i) {
                super.p.n(this.d);
                this.dispose();
                return;
            }
            if (actionEvent.getActionCommand() == "NoPopup") {
                this.n.p(" Popup ");
                try {
                    this.toFront();
                    return;
                }
                catch (Exception ex) {
                    return;
                }
            }
            if (actionEvent.getActionCommand() == " Popup ") {
                this.n.p("NoPopup");
                try {
                    this.toBack();
                    return;
                }
                catch (Exception ex2) {
                    return;
                }
            }
            if (actionEvent.getSource() == this.l) {
                super.p.p(this.d);
                super.p.n(this.d);
                this.dispose();
            }
        }
    }
    
    public final void windowClosing(final WindowEvent windowEvent) {
        super.p.n(this.d);
        this.dispose();
    }
}
