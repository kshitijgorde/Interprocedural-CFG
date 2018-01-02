import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.awt.Graphics;
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

class Helpbox extends Frame implements WindowListener, Runnable, ActionListener
{
    int page;
    Thread appletThread;
    int k;
    boolean running;
    
    public Helpbox(final String s) {
        super(s);
        this.page = 1;
        this.running = true;
        this.setLocation(250, 50);
        if (this.appletThread == null) {
            (this.appletThread = new Thread(this)).start();
        }
        this.setBackground(Color.black);
        this.addWindowListener(this);
        final Button button = new Button("Thanx for the help..");
        button.addActionListener(this);
        this.setLayout(null);
        button.setBounds(180, 380, 120, 40);
        this.add(button);
        this.setVisible(true);
    }
    
    public void paint(final Graphics graphics) {
        if (this.page == 1) {
            graphics.setFont(new Font("Garamond", 1, 20));
            graphics.setColor(Color.yellow);
            graphics.drawString("Help", 180, 50);
            graphics.setFont(new Font("Garamond", 0, 15));
            graphics.setColor(Color.white);
            graphics.drawString("Just find the 5 differences in each picture before", 60, 100);
            graphics.drawString("time runs out. Try to click in the middle if it is", 60, 130);
            graphics.drawString("a large area.", 60, 160);
            graphics.drawString("Send comments to Bavo.Bruylandt@rug.ac.be or go to", 60, 310);
            graphics.drawString("Http://javadesign.web.com", 60, 340);
        }
    }
    
    public void run() {
        while (this.running) {
            this.setSize(this.k, this.k);
            this.k += 5;
            if (this.k > 450) {
                this.appletThread.stop();
                this.running = false;
            }
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex) {
                System.exit(1);
            }
        }
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
        if (actionEvent.getActionCommand() == "Thanx for the help..") {
            this.dispose();
        }
    }
}
