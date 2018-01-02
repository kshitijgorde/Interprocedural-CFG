import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Button;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

class AboutDialog extends Dialog
{
    Button okButton;
    int myIndex;
    boolean demo;
    boolean online;
    Button moreButton;
    String theVersion;
    String[] str;
    
    public AboutDialog(final Frame frame, final String theVersion, final int myIndex, final boolean demo, final boolean online) {
        super(frame, true);
        this.str = new String[12];
        this.myIndex = myIndex;
        this.setBackground(new Color(50, 255, 255));
        this.setTitle("About");
        this.demo = demo;
        this.online = online;
        this.theVersion = theVersion;
        for (int i = 0; i < 10; ++i) {
            this.str[i] = "";
        }
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        if (this.myIndex == 1) {
            this.okButton = new Button("run demo");
        }
        else {
            this.okButton = new Button("close window");
        }
        gridBagConstraints.insets = new Insets(180, 0, 0, 0);
        layout.setConstraints(this.okButton, gridBagConstraints);
        this.add(this.okButton);
        this.moreButton = new Button("more");
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(5, 0, 0, 0);
        layout.setConstraints(this.moreButton, gridBagConstraints);
        if (this.demo && !this.online) {
            this.add(this.moreButton);
        }
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.setFont(new Font("Helvetiva", 0, 12));
        graphics.drawString("Sun & Earth Applet -- " + this.theVersion, 20, 20);
        graphics.drawString("© 1998-2001 Juergen Giesen", 20, 35);
        graphics.drawString("For private not commercial use only.", 20, 50);
        graphics.drawString("E-Mail: jgiesen@t-online.de", 20, 70);
        graphics.drawString("Home Page: http://www.jgiesen.de", 20, 85);
        for (int i = 0; i < 6; ++i) {
            graphics.drawString(this.str[i], 20, 100 + i * 15);
        }
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            if (event.target == this.moreButton) {
                this.str[0] = "__________________________________________________________________";
                this.str[1] = "";
                this.str[2] = "";
                this.str[3] = "Please read \u2018register'";
                this.str[4] = "";
                this.str[5] = "";
                this.moreButton.hide();
                this.repaint();
            }
            if (event.target == this.okButton) {
                this.hide();
            }
        }
        return true;
    }
}
