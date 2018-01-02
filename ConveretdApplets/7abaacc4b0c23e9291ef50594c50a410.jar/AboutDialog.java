import java.awt.event.WindowEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Button;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

class AboutDialog extends Dialog implements ActionListener, WindowListener
{
    Button Close;
    
    AboutDialog(final Frame frame, final String s) {
        super(frame, s, false);
        this.setSize(300, 225);
        this.Close = new Button("Close");
        this.setLayout(new BorderLayout());
        this.add(this.Close, "South");
        this.Close.addActionListener(this);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.dispose();
        this.setVisible(false);
    }
    
    public Insets getInsets() {
        return new Insets(30, 50, 30, 50);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.blue);
        graphics.setFont(new Font("Dialog", 1, 18));
        graphics.drawString("Angle Finder", 75, 50);
        graphics.drawLine(0, 85, 300, 85);
        graphics.setFont(new Font("Dialog", 1, 12));
        graphics.setColor(Color.black);
        graphics.drawString("Programed by Matthew Paiz", 15, 125);
        graphics.setColor(Color.blue);
        graphics.drawString("matthewp93@comcast.net", 15, 145);
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
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
