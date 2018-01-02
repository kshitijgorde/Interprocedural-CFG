// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.Button;
import java.awt.Component;
import java.awt.Label;
import java.util.StringTokenizer;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.event.WindowListener;
import java.awt.Frame;
import java.awt.Dialog;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public final class JAPAWTMsgBox extends WindowAdapter implements ActionListener
{
    private Dialog d;
    
    private JAPAWTMsgBox(final Frame frame, final String s, final String s2) {
        try {
            (this.d = new Dialog(frame, s2, true)).addWindowListener(this);
            final GridLayout layout = new GridLayout(0, 1, 0, 0);
            final Panel panel = new Panel();
            panel.setLayout(layout);
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n");
            while (stringTokenizer.hasMoreElements()) {
                panel.add(new Label(stringTokenizer.nextToken()));
            }
            panel.add(new Label(" "));
            this.d.add("Center", panel);
            final Button button = new Button("   Ok   ");
            button.addActionListener(this);
            final Panel panel2 = new Panel();
            panel2.add(button);
            this.d.add("South", panel2);
            final Panel panel3 = new Panel();
            panel3.setSize(7, 7);
            this.d.add("North", panel3);
            final Panel panel4 = new Panel();
            panel4.setSize(7, 7);
            this.d.add("West", panel4);
            final Panel panel5 = new Panel();
            panel5.setSize(7, 7);
            this.d.add("East", panel5);
            this.d.pack();
            this.d.setResizable(false);
            final Dimension screenSize = this.d.getToolkit().getScreenSize();
            try {
                final Dimension size = this.d.getSize();
                this.d.setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);
            }
            catch (Error error) {
                final Dimension size2 = this.d.size();
                this.d.locate((screenSize.width - size2.width) / 2, (screenSize.height - size2.height) / 2);
            }
            this.d.show();
        }
        catch (Exception ex) {}
    }
    
    public static final int MsgBox(final Frame frame, final String s, final String s2) {
        try {
            final JAPAWTMsgBox japawtMsgBox = new JAPAWTMsgBox(frame, s, s2);
        }
        catch (Exception ex) {
            return -1;
        }
        return 0;
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.d.dispose();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.d.dispose();
    }
}
