import java.awt.event.WindowEvent;
import java.awt.Font;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.WindowListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class Popup extends Frame implements WindowListener
{
    ReadMe ts;
    String file;
    
    public Popup(final String file) {
        super("Help");
        this.setLayout(null);
        this.setSize(300, 300);
        this.file = file;
        this.setBackground(new Color(0, 0, 128));
        this.addWindowListener(this);
    }
    
    public void paint(final Graphics graphics) {
    }
    
    public void setup() {
        (this.ts = new ReadMe(this, this.file, 250, 200)).setBounds(25, 50, 250, 200);
        this.ts.setScreenColor(new Color(0, 0, 122), 1);
        this.ts.setScreenColor(new Color(0, 122, 122), 2);
        this.ts.setScreenColor(Color.white, 3);
        this.ts.setFontColor(Color.cyan, 1);
        this.ts.setFontColor(Color.white, 2);
        this.ts.setFont(new Font("Arial", 0, 12), 1);
        this.ts.setFont(new Font("Arial", 1, 12), 2);
        this.add(this.ts);
        this.repaint();
        this.ts.repaint();
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        if (this.ts != null) {
            this.ts.delete();
        }
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
