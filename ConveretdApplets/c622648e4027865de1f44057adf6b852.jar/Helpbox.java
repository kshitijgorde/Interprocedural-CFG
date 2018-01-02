import java.awt.event.WindowEvent;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class Helpbox extends Frame implements WindowListener, ActionListener
{
    int page;
    int k;
    
    public Helpbox(final String s) {
        super(s);
        this.page = 1;
        this.k = 0;
        this.setLocation(250, 50);
        this.setSize(450, 450);
        this.setBackground(Color.black);
        this.addWindowListener(this);
        final Button button = new Button("Thanx for the help..");
        button.addActionListener(this);
        this.setLayout(null);
        button.setBounds(180, 380, 120, 40);
        this.add(button);
        this.setVisible(true);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand() == "Thanx for the help..") {
            this.dispose();
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.page == 1) {
            graphics.setFont(new Font("Garamond", 1, 20));
            graphics.setColor(Color.yellow);
            graphics.drawString("Help", 180, 50);
            graphics.setFont(new Font("Garamond", 0, 15));
            graphics.setColor(Color.white);
            graphics.drawString("Choose your cardset first (Images are the easiest)", 60, 100);
            graphics.drawString("Then when the cards are shown remember their positions", 60, 130);
            graphics.drawString("When they dissappear, click the cards and", 60, 160);
            graphics.drawString("find the matching ones before time runs out.", 60, 190);
            graphics.drawString("The later the level the faster the time.", 60, 220);
            graphics.drawString("You can also set it to difficult, this setting", 60, 250);
            graphics.drawString("will activate when you restart the game.", 60, 280);
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
