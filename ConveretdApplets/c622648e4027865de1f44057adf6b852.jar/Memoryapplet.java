import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Memoryapplet extends Applet implements ActionListener
{
    Button ok1;
    String companyname;
    String registered;
    boolean reg;
    int regnum;
    Dimension dim;
    
    public Memoryapplet() {
        this.ok1 = new Button("Start Memory");
        this.companyname = "";
        this.registered = "";
        this.reg = false;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.ok1.setLabel("Game Loading...");
        new Memory(this).setVisible(true);
    }
    
    public URL getDocumentBase() {
        return this.getCodeBase();
    }
    
    public void init() {
        this.reg = true;
        this.companyname = this.getParameter("Company_name");
        this.setLayout(null);
        this.dim = this.getSize();
        this.ok1.addActionListener(this);
        this.ok1.requestFocus();
        this.ok1.setBackground(new Color(0, 0, 255));
        this.ok1.setForeground(new Color(255, 255, 255));
        this.ok1.setBounds(1, 1, this.dim.width - 2, this.dim.height - 2);
        this.add(this.ok1);
        this.setBackground(Color.black);
    }
    
    public void paint(final Graphics graphics) {
    }
}
