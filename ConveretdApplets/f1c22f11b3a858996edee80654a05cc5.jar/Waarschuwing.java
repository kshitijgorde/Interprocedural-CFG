import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class Waarschuwing extends Frame implements WindowListener, ActionListener
{
    Button ok;
    Button visit;
    Iqapplet iq;
    
    public Waarschuwing(final Iqapplet iq, final String s) {
        super(s);
        this.iq = iq;
        this.setSize(400, 400);
        (this.ok = new Button("Cool...")).addActionListener(this);
        this.setLayout(null);
        this.ok.setBounds(40, 300, 140, 40);
        this.add(this.ok);
        (this.visit = new Button("Visit Homemadejava")).addActionListener(this);
        this.visit.setBounds(220, 300, 140, 40);
        this.add(this.visit);
        this.setLocation(250, 50);
        this.setBackground(new Color(0, 0, 122));
        this.addWindowListener(this);
        this.setVisible(true);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setFont(new Font("Garamond", 1, 20));
        graphics.setColor(Color.yellow);
        graphics.drawString("Credits :", 170, 50);
        graphics.setFont(new Font("Garamond", 0, 15));
        graphics.setColor(Color.white);
        graphics.drawString("Idea By DaHazard", 60, 100);
        graphics.drawString("Programming By DaHazard", 60, 130);
        graphics.drawString("Questions by DaHazard and Eysenck", 60, 160);
        graphics.drawString("Graphics By DaHazard and Spectrum", 60, 190);
        graphics.drawString("To use a registered version please", 60, 220);
        graphics.drawString("visit Http://www.homemadejava.com", 60, 250);
        graphics.drawString("Or mail to javadesign@pi.be", 60, 280);
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.dispose();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.ok) {
            this.dispose();
        }
        else {
            try {
                this.iq.getAppletContext().showDocument(new URL("Http://www.homemadejava.com"), "newWindow");
            }
            catch (MalformedURLException ex) {}
        }
    }
}
