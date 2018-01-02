import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class Helpbox extends Frame implements WindowListener, ActionListener
{
    String solutionstring;
    String btlabel;
    boolean menuhelp;
    Button ok;
    Button visit;
    Iqapplet iq;
    
    public Helpbox(final String btlabel, final Iqapplet iq, final String s, final String solutionstring, final boolean menuhelp) {
        super(s);
        this.iq = iq;
        this.solutionstring = solutionstring;
        this.btlabel = btlabel;
        this.menuhelp = menuhelp;
        this.setSize(400, 400);
        this.setLocation(250, 50);
        this.setBackground(new Color(0, 0, 122));
        this.addWindowListener(this);
        (this.ok = new Button("Thanx for the help...")).addActionListener(this);
        this.setLayout(null);
        this.ok.setBounds(40, 300, 140, 40);
        this.add(this.ok);
        (this.visit = new Button("Visit Homemadejava")).addActionListener(this);
        this.visit.setBounds(220, 300, 140, 40);
        this.add(this.visit);
        this.setVisible(true);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setFont(new Font("Verdana", 1, 20));
        graphics.setColor(Color.white);
        graphics.drawString("Help", 180, 50);
        graphics.setFont(new Font("Verdana", 0, 15));
        graphics.setColor(Color.white);
        if (this.menuhelp) {
            graphics.drawString("Answer each question and press next.", 60, 100);
            graphics.drawString("If you dont know the answer you can", 60, 130);
            graphics.drawString("click next and later return to it by ", 60, 160);
            graphics.drawString("pressing previous. If you are done", 60, 190);
            graphics.drawString("Click stop to see the results.", 60, 220);
            graphics.drawString("Http://www.homemadejava.com", 60, 250);
        }
        else if (this.btlabel.equals("Solution")) {
            graphics.drawString("The answer to this question is:", 60, 100);
            graphics.drawString(this.solutionstring, 60, 130);
            graphics.drawString("This will declare the test invalid", 60, 160);
        }
        else {
            graphics.drawString("This is a creation of Javadesign", 60, 100);
            graphics.drawString("Our work can be downloaded at:", 60, 130);
            graphics.drawString("Http://www.homemadejava.com", 60, 160);
            graphics.drawString("Click the button to visit us.", 60, 190);
            graphics.drawString("Thanks for using our software!", 60, 220);
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
