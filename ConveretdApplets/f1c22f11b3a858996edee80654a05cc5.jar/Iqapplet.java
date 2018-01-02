import java.net.URL;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.io.InputStream;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Iqapplet extends Applet implements ActionListener
{
    Button ok1;
    Iqquiz iq;
    int autoload;
    int regnum;
    String companyname;
    String registered;
    boolean reg;
    private InputStream input;
    
    public Iqapplet() {
        this.ok1 = new Button("Start Iqquiz");
        this.companyname = "";
        this.registered = "";
        this.reg = false;
    }
    
    public void init() {
        this.reg = this.registration();
        this.setLayout(null);
        this.autoload = ((this.getParameter("autoload") == null) ? 0 : Integer.parseInt(this.getParameter("autoload")));
        this.ok1.addActionListener(this);
        this.ok1.requestFocus();
        final Dimension size = this.getSize();
        this.ok1.setBounds(1, 1, size.width - 2, size.height - 2);
        this.add(this.ok1);
    }
    
    public void start() {
        if (this.autoload == 1) {
            (this.iq = new Iqquiz(this)).setVisible(true);
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand() == "Start Iqquiz" && this.iq == null) {
            (this.iq = new Iqquiz(this)).setVisible(true);
        }
    }
    
    public void paint(final Graphics graphics) {
    }
    
    public boolean registration() {
        try {
            this.input = new URL(this.getDocumentBase(), "License.txt").openStream();
        }
        catch (Exception ex3) {
            System.out.println("Corrupted License file or file is missing");
            System.out.println("or it runs in NS locally.");
            return false;
        }
        try {
            char c = 'a';
            while (c != '\n' && c > '\0') {
                c = (char)this.input.read();
                if (c != '\n' && c != '\r') {
                    this.companyname += c;
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
        try {
            char c2 = 'a';
            while (c2 != '\n' && c2 > '\0' && c2 != '\r') {
                c2 = (char)this.input.read();
                if (c2 != '\n' && c2 != '\r') {
                    this.registered += c2;
                }
            }
        }
        catch (Exception ex2) {
            System.out.println(ex2);
            return false;
        }
        for (int i = 0; i < this.companyname.length(); ++i) {
            this.regnum += this.companyname.charAt(i) + '\0';
        }
        try {
            if (Integer.parseInt(this.registered) == this.regnum) {
                System.out.println("Registered to " + this.companyname);
                this.reg = true;
            }
            else {
                System.out.println("Unregistered Version");
                this.reg = false;
            }
        }
        catch (Exception ex4) {
            System.out.println("Registration number is corrupted");
            System.out.println("or it runs in NS locally.");
            return false;
        }
        return this.reg;
    }
}
