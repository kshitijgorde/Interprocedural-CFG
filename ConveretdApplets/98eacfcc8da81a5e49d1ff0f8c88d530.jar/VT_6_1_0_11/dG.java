// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.WindowListener;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Dimension;
import java.awt.Frame;

final class dG extends Frame
{
    private Dimension a;
    private Label b;
    private Label c;
    private Label d;
    private TextField e;
    private TextField f;
    private int g;
    
    dG() {
        super("Authorization Request");
        this.a = this.getToolkit().getScreenSize();
        this.addNotify();
        this.addWindowListener(new aX(this));
        this.setLayout(new BorderLayout());
        final Panel panel;
        (panel = new Panel(new GridLayout(3, 1))).add(this.b = new Label());
        panel.add(this.c = new Label());
        panel.add(this.d = new Label());
        this.add("North", panel);
        final Panel panel2;
        (panel2 = new Panel(new GridLayout(2, 1))).add(new Label("Username:"));
        panel2.add(new Label("Password:"));
        this.add("West", panel2);
        final Panel panel3;
        (panel3 = new Panel(new GridLayout(2, 1))).add(this.e = new TextField(30));
        panel3.add(this.f = new TextField(30));
        this.f.addActionListener(new db(this));
        this.f.setEchoChar('*');
        this.add("East", panel3);
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final Panel panel4 = new Panel(gridBagLayout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final Panel panel5 = new Panel();
        panel4.add(panel5);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(panel5, gridBagConstraints);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        final Button button;
        panel4.add(button = new Button("  OK  "));
        button.addActionListener(new db(this));
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(button, gridBagConstraints);
        final Button button2;
        panel4.add(button2 = new Button("Clear"));
        button2.addActionListener(new bu(this));
        gridBagConstraints.weightx = 2.0;
        gridBagLayout.setConstraints(button2, gridBagConstraints);
        final Button button3;
        panel4.add(button3 = new Button("Cancel"));
        button3.addActionListener(new bn(this));
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(button3, gridBagConstraints);
        this.add("South", panel4);
        this.pack();
    }
    
    final synchronized cU a(String s, final String text2, final String text3, final String s2) {
        this.b.setText(text);
        this.c.setText(text2);
        this.d.setText(text3);
        this.b.invalidate();
        this.c.invalidate();
        this.d.invalidate();
        this.setResizable(true);
        this.pack();
        this.setResizable(false);
        this.setLocation((this.a.width - this.getPreferredSize().width) / 2, (int)((this.a.height - this.getPreferredSize().height) / 2 * 0.7));
        s = (String)1;
        if (s2.equalsIgnoreCase("NTLM")) {
            try {
                this.e.setText(System.getProperty("user.name", ""));
                s = (String)0;
            }
            catch (SecurityException ex) {}
        }
        this.setVisible(true);
        if (s != 0) {
            if (bj.e()) {
                this.e.requestFocus();
            }
            else if (bj.e()) {
                this.f.requestFocus();
            }
        }
        try {
            this.wait();
        }
        catch (InterruptedException ex2) {}
        this.setVisible(false);
        final cU cu = new cU(this.e.getText(), this.f.getText());
        this.e.setText("");
        this.f.setText("");
        if (this.g == 0) {
            return null;
        }
        return cu;
    }
    
    static int a(final dG dg, final int g) {
        return dg.g = g;
    }
    
    static TextField a(final dG dg) {
        return dg.e;
    }
    
    static TextField b(final dG dg) {
        return dg.f;
    }
}
