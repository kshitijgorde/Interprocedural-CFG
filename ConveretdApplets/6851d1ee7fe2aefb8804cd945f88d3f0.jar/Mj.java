import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Choice;
import java.awt.Button;
import java.awt.Label;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

class Mj extends a implements ActionListener
{
    String[] ua;
    Label[] qa;
    Button[] va;
    Choice[] wa;
    int sa;
    private final Jj ta;
    
    public Mj(final Jj ta, final int n) {
        super(1, new Insets(8, 8, 8, 8));
        this.ta = ta;
        this.ua = new String[n];
        this.qa = new Label[n];
        this.va = new Button[n];
        this.wa = new Choice[n];
        this.setLayout(new GridLayout(n + 1, 3, 2, 0));
        this.add(new Label(Jj.a(ta).b("strChart")));
        this.add(new Label(Jj.a(ta).b("strColor")));
        this.add(new Label(Jj.a(ta).b("strLineWidth")));
        this.sa = 0;
    }
    
    public Ij _() {
        final Ij ij = new Ij();
        for (int i = 0; i < this.ua.length; ++i) {
            final String s = this.ua[i];
            final Color background = this.va[i].getBackground();
            int selectedIndex = -1;
            if (this.wa[i] != null) {
                selectedIndex = this.wa[i].getSelectedIndex();
            }
            ij.b(s, background, selectedIndex);
        }
        return ij;
    }
    
    public void _(final String s, final Color background, final int n) {
        if (this.sa < this.qa.length) {
            this.ua[this.sa] = s;
            this.add(this.qa[this.sa] = new Label(Jj.a(this.ta).b("str" + s)));
            (this.va[this.sa] = new Button("")).setBackground(background);
            this.va[this.sa].addActionListener(this);
            this.add(this.va[this.sa]);
            this.wa[this.sa] = this.a(n);
            if (this.wa[this.sa] != null) {
                this.add(this.wa[this.sa]);
            }
            else {
                this.add(new Label("N/A"));
            }
            ++this.sa;
        }
    }
    
    private Choice a(final int n) {
        if (n < 0) {
            return null;
        }
        final Choice choice = new Choice();
        choice.add(Jj.a(this.ta).b("cbWidth0"));
        choice.add(Jj.a(this.ta).b("cbWidth1"));
        choice.add(Jj.a(this.ta).b("cbWidth2"));
        choice.add(Jj.a(this.ta).b("cbWidth3"));
        choice.add(Jj.a(this.ta).b("cbWidth4"));
        choice.add(Jj.a(this.ta).b("cbWidth5"));
        if (n < choice.getItemCount()) {
            choice.select(n);
        }
        else {
            choice.select(0);
        }
        return choice;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        for (int i = 0; i < this.sa; ++i) {
            if (actionEvent.getSource() == this.va[i]) {
                final Qh qh = new Qh(Jj.b(this.ta), Jj.a(this.ta).b("strSelectColor"), Jj.a(this.ta));
                qh.show();
                if (qh.getColor() != null) {
                    this.va[i].setBackground(qh.getColor());
                    this.va[i].repaint();
                }
            }
        }
    }
}
