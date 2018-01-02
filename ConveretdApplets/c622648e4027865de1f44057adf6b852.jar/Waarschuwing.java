import java.awt.event.WindowEvent;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
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
    int page;
    int k;
    
    public Waarschuwing(final String s) {
        super(s);
        this.page = 1;
        this.k = 0;
        this.setSize(400, 400);
        final Button button = new Button("Cool...");
        button.addActionListener(this);
        this.setLayout(null);
        button.setBounds(150, 330, 120, 40);
        this.add(button);
        this.setLocation(250, 50);
        this.setBackground(Color.black);
        this.addWindowListener(this);
        this.setVisible(true);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand() == "Cool...") {
            this.dispose();
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.page == 1) {
            graphics.setFont(new Font("Garamond", 1, 20));
            graphics.setColor(Color.yellow);
            graphics.drawString("Credits :", 170, 50);
            graphics.setFont(new Font("Garamond", 0, 15));
            graphics.setColor(Color.white);
            graphics.drawString("Original idea By Bavo", 60, 100);
            graphics.drawString("Programming By Bavo", 60, 130);
            graphics.drawString("Graphics By Bavo", 60, 160);
            graphics.drawString("Made with Kawa and Fireworks", 60, 190);
            graphics.drawString("Contact : Bavo Bruylandt", 60, 220);
        }
    }
    
    public void update() {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.dispose();
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
}
