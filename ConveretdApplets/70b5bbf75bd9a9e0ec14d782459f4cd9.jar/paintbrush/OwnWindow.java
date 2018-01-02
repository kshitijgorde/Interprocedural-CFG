// 
// Decompiled by Procyon v0.5.30
// 

package paintbrush;

import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Label;
import java.awt.Button;
import java.awt.Panel;
import java.awt.Frame;

public class OwnWindow extends Frame
{
    Panel panel1;
    Panel panel2;
    Button closebutton;
    Label label;
    
    public OwnWindow(final String s) {
        this.panel1 = new Panel();
        this.panel2 = new Panel();
        this.closebutton = new Button("Close");
        this.label = new Label();
        try {
            this.setBackground(Color.white);
            this.setTitle("Applet window");
            this.setLayout(new BorderLayout());
            this.label.setText(s);
            this.closebutton.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    OwnWindow.this.dispose();
                }
            });
            this.panel1.add(this.label);
            this.add(this.panel1, "Center");
            this.panel2.add(this.closebutton);
            this.add(this.panel2, "South");
            this.move(this.size().width / 2, this.size().height / 2);
            this.pack();
            this.addWindowListener(new WindowAdapter() {
                public void windowClosing(final WindowEvent e) {
                    OwnWindow.this.dispose();
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
