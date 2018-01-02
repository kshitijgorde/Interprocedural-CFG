// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.Dialog;

public class az extends Dialog implements ActionListener
{
    static String do;
    static String for;
    static final int byte = 3;
    float[] else;
    int try;
    String title;
    Label char;
    TextField[] goto;
    Button case;
    Button if;
    int new;
    String[] int;
    private Frame a;
    
    az(final Frame a, final String s, final String s2, final int new1) {
        super(a, s, true);
        this.else = new float[3];
        this.try = 0;
        this.goto = new TextField[3];
        this.int = new String[] { "38.2000", "50.0000", "61.8000" };
        this.title = s;
        this.new = new1;
        this.setTitle(s);
        this.setResizable(false);
        this.setModal(true);
        final Dimension size = a.getSize();
        this.setLocation(size.width / 2, size.height / 2);
        this.setLayout(new BorderLayout());
        this.add("North", this.char = new Label(s2));
        final Panel panel = new Panel();
        final Label label = new Label("%");
        panel.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        for (int i = 0; i < 3; ++i) {
            (this.goto[i] = new TextField(this.int[i])).addActionListener(this);
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = i;
            gridBagConstraints.gridheight = 1;
            gridBagConstraints.gridwidth = 10;
            gridBagConstraints.fill = 1;
            panel.add(this.goto[i], gridBagConstraints);
            gridBagConstraints.gridx = 11;
            gridBagConstraints.gridy = i;
            gridBagConstraints.gridheight = 1;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.fill = 1;
            panel.add(new Label("%"), gridBagConstraints);
        }
        this.add("Center", panel);
        final Panel panel2 = new Panel(new BorderLayout());
        this.add("South", panel2);
        panel2.add("West", this.case = new Button(az.do));
        this.case.addActionListener(this);
        panel2.add("East", this.if = new Button(az.for));
        this.if.addActionListener(this);
        this.a = a;
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                az.this.setVisible(false);
                az.this.try = 0;
            }
        });
    }
    
    private int a(final int n, final float n2) {
        for (int i = 0; i < n; ++i) {
            if (this.else[i] == n2) {
                return i;
            }
        }
        return 0;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        int i = 0;
        if (source != this.case) {
            if (source != this.goto) {
                if (source == this.if) {
                    this.try = 0;
                    this.setVisible(false);
                }
                return;
            }
        }
        try {
            int try1 = 0;
            for (i = 0; i < 3; ++i) {
                if (this.goto[i].getText().compareTo("") != 0) {
                    this.else[try1++] = new Float(this.goto[i].getText());
                    if (this.else[i] <= 0.0f || this.else[i] > 100.0f) {
                        throw new NumberFormatException();
                    }
                    if (this.a(try1 - 1, this.else[try1 - 1]) > 0) {
                        --try1;
                    }
                }
            }
            this.try = try1;
        }
        catch (NumberFormatException ex) {
            this.goto[i].selectAll();
            return;
        }
        this.setVisible(false);
        this.transferFocus();
    }
    
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (visible) {
            for (int i = 0; i < this.int.length; ++i) {
                this.goto[i].setText(this.int[i]);
            }
            this.goto[0].requestFocus();
            this.goto[0].selectAll();
        }
    }
    
    static {
        az.do = new String("Ok");
        az.for = new String("Cancel");
    }
}
