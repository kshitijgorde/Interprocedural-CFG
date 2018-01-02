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

class Waarschuwing extends Frame implements WindowListener, Runnable, ActionListener
{
    int page;
    Thread appletThread;
    int k;
    boolean running;
    
    public Waarschuwing(final String s) {
        super(s);
        this.page = 1;
        this.running = true;
        final Button button = new Button("Cool...");
        button.addActionListener(this);
        this.setLayout(null);
        button.setBounds(150, 350, 120, 40);
        this.add(button);
        this.setLocation(250, 50);
        if (this.appletThread == null) {
            (this.appletThread = new Thread(this)).start();
        }
        this.setBackground(Color.black);
        this.addWindowListener(this);
        this.setVisible(true);
    }
    
    public void paint(final Graphics graphics) {
        if (this.page == 1) {
            graphics.setFont(new Font("Garamond", 1, 20));
            graphics.setColor(Color.yellow);
            graphics.drawString("Credits :", 170, 50);
            graphics.setFont(new Font("Garamond", 0, 15));
            graphics.setColor(Color.white);
            graphics.drawString("Original idea By DubbleEagle", 60, 100);
            graphics.drawString("Programming By DaHazard", 60, 130);
            graphics.drawString("Programming Assistance By Luna-Tic", 60, 160);
            graphics.drawString("Graphics By DaHazard", 60, 190);
            graphics.drawString("Photographs By DubbleEagle", 60, 220);
            graphics.drawString("Photo editing By DubbleEagle", 60, 250);
            graphics.drawString("Lay-out By DaHazard", 60, 280);
            graphics.drawString("Testing and tweaking by Antoon, An and LunaTic", 60, 310);
            graphics.drawString("Special Thanks to Luna-Tic", 60, 340);
        }
    }
    
    public void run() {
        while (this.running) {
            this.setSize(this.k, this.k);
            this.k += 5;
            if (this.k > 420) {
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
        new Waarschuwing("ikke");
        this.dispose();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand() == "Cool...") {
            this.dispose();
        }
    }
}
