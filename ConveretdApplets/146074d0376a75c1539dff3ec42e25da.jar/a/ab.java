// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.Date;
import java.awt.Event;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.util.Locale;
import java.awt.Frame;
import java.util.Calendar;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Panel;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;

public final class ab extends ah implements ActionListener, ItemListener
{
    private static String[] q;
    private static String[] w;
    private static int[] q;
    private final String q;
    private final String w;
    private final String e;
    private Panel q;
    private Panel w;
    private Panel e;
    private g q;
    private g w;
    private f q;
    private Choice q;
    private Choice w;
    private Button[] q;
    private int q;
    private int w;
    private int e;
    private Calendar q;
    private aa q;
    
    public ab(final Frame frame, final aa q) {
        super(frame, true);
        this.q = eb.q("Select date");
        this.w = eb.q("Ok");
        this.e = eb.q("Cancel");
        this.q = Calendar.getInstance(Locale.getDefault());
        this.q = q;
        this.setLayout(new GridBagLayout());
        this.setSize(330, 320);
        this.setTitle(this.q);
        this.setBackground(cf.w.q);
        this.setLocation(this.q.q());
        final Panel panel;
        (panel = new Panel()).setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints;
        (gridBagConstraints = new GridBagConstraints()).gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.anchor = 11;
        gridBagConstraints.fill = 0;
        gridBagConstraints.insets = new Insets(5, 5, 3, 5);
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 0;
        final Panel panel2 = panel;
        if (this.q == null) {
            (this.q = new s()).setLayout(new GridBagLayout());
            this.q.setBackground(cf.w.i);
            this.q.setForeground(cf.w.u);
            final e e;
            (e = new e(Color.black, Color.white, 1)).setSize(250, 10);
            final Panel panel3;
            (panel3 = new Panel(new FlowLayout(0, 0, 0))).add(this.q());
            panel3.add(this.w());
            final Label label;
            (label = new Label(this.q)).setFont(m.t);
            final GridBagConstraints gridBagConstraints2;
            (gridBagConstraints2 = new GridBagConstraints()).gridx = 0;
            gridBagConstraints2.gridy = 0;
            gridBagConstraints2.gridwidth = 0;
            gridBagConstraints2.gridheight = 1;
            gridBagConstraints2.anchor = 17;
            gridBagConstraints2.fill = 0;
            gridBagConstraints2.insets = new Insets(5, 5, 1, 5);
            this.q.add(label, gridBagConstraints2);
            gridBagConstraints2.gridy = 1;
            gridBagConstraints2.fill = 2;
            gridBagConstraints2.insets = new Insets(1, 5, 0, 5);
            this.q.add(e, gridBagConstraints2);
            gridBagConstraints2.gridy = 2;
            gridBagConstraints2.fill = 0;
            gridBagConstraints2.insets = new Insets(0, 5, 0, 5);
            this.q.add(panel3, gridBagConstraints2);
            gridBagConstraints2.gridy = 3;
            gridBagConstraints2.insets = new Insets(1, 5, 0, 5);
            this.q.add(this.q(), gridBagConstraints2);
        }
        panel2.add(this.q, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = 15;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        final Panel panel4 = panel;
        if (this.e == null) {
            (this.e = new Panel()).setLayout(new FlowLayout(2, 3, 3));
            final Panel e2 = this.e;
            if (this.q == null) {
                this.q = new g(this.e);
            }
            e2.add(this.q);
            final Panel e3 = this.e;
            if (this.w == null) {
                this.w = new g(this.w);
                this.q = new f(this.w);
            }
            e3.add(this.q);
        }
        panel4.add(this.e, gridBagConstraints);
        final GridBagConstraints gridBagConstraints3;
        (gridBagConstraints3 = new GridBagConstraints()).insets = new Insets(3, 3, 3, 3);
        gridBagConstraints3.weighty = 1.0;
        gridBagConstraints3.weightx = 1.0;
        gridBagConstraints3.fill = 1;
        this.add(panel, gridBagConstraints3);
        this.e = this.q.get(5);
        this.r();
    }
    
    private Panel q() {
        if (this.w == null) {
            (this.w = new Panel()).setLayout(new GridLayout(7, 7, 0, 0));
            this.q = new Button[49];
            for (int i = 0; i < 7; ++i) {
                (this.q[i] = new Button()).setBackground(cf.w.i);
                this.q[i].setLabel(ab.q[i]);
                this.q[i].setFont(m.t);
                this.w.add(this.q[i]);
            }
            for (int j = 7; j < 49; ++j) {
                (this.q[j] = new Button()).setBackground(Color.white);
                this.q[j].addActionListener(this);
                this.w.add(this.q[j]);
            }
        }
        return this.w;
    }
    
    private Choice q() {
        if (this.q == null) {
            (this.q = new Choice()).addItemListener(this);
            this.e();
            this.w = this.q.get(2);
            this.q.select(this.w);
        }
        return this.q;
    }
    
    private Choice w() {
        if (this.w == null) {
            (this.w = new Choice()).addItemListener(this);
            this.q();
            this.q = this.q.get(1);
            this.w.select(this.q - 1582);
        }
        return this.w;
    }
    
    private void q() {
        for (int i = 1582; i < 2020; ++i) {
            this.w().addItem(String.valueOf(i));
        }
    }
    
    private void e() {
        for (int i = 0; i < ab.w.length; ++i) {
            this.q.addItem(ab.w[i]);
        }
    }
    
    private void r() {
        (this.q = Calendar.getInstance()).set(1, this.q);
        this.q.set(2, this.w);
        this.q.set(5, 1);
        final int n = this.q.get(7) + 6;
        final int n2 = ab.q[this.w] + ((this.w == 1 && this.q.get(1) % 4 == 0) ? 1 : 0);
        this.q.set(5, this.e);
        for (int i = 7; i < n; ++i) {
            this.q[i].setVisible(false);
        }
        byte b = 1;
        for (int j = n; j < n + n2; ++j) {
            this.q[j].setLabel(Byte.toString(b));
            this.q[j].setBackground(Color.white);
            this.q[j].setForeground(Color.black);
            this.q[j].setVisible(true);
            ++b;
        }
        for (int k = n + n2; k < 49; ++k) {
            this.q[k].setVisible(false);
        }
        final int n3 = n + this.e - 1;
        this.q[n3].setBackground(Color.gray);
        this.q[n3].setForeground(Color.red);
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.w) {
                    this.q.q(Long.toString(this.q.getTime().getTime()));
                    this.dispose();
                }
                if (event.target == this.q) {
                    this.dispose();
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public final void q(final long n) {
        if (n != 0L && n != -1L) {
            this.q.setTime(new Date(n));
        }
        this.e = this.q.get(5);
        this.w = this.q.get(2);
        ((Choice)(this.q = this.q.get(1))).select(this.w);
        this.w.select(this.q - 1582);
        this.r();
    }
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource() == this.q()) {
            this.w = this.q().getSelectedIndex();
            this.r();
        }
        if (itemEvent.getSource() == this.w()) {
            this.q = Integer.parseInt(this.w().getSelectedItem());
            this.r();
        }
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        this.e = Integer.parseInt(((Button)actionEvent.getSource()).getLabel().trim());
        this.r();
    }
    
    static {
        ab.q = new String[] { eb.q("Sun"), eb.q("Mon"), eb.q("Tue"), eb.q("Wed"), eb.q("Thu"), eb.q("Fri"), eb.q("Sat") };
        ab.w = new String[] { eb.q("January"), eb.q("February"), eb.q("March"), eb.q("April"), eb.q("May"), eb.q("June"), eb.q("July"), eb.q("August"), eb.q("September"), eb.q("October"), eb.q("November"), eb.q("December") };
        ab.q = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    }
}
