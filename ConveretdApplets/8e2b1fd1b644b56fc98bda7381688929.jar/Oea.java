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

class Oea extends continue implements ActionListener
{
    String[] sa;
    Label[] aa;
    Button[] ta;
    Choice[] ua;
    int ca;
    private final Lea da;
    
    public Oea(final Lea da, final int n) {
        super(1, new Insets(8, 8, 8, 8));
        this.da = da;
        this.sa = new String[n];
        this.aa = new Label[n];
        this.ta = new Button[n];
        this.ua = new Choice[n];
        this.setLayout(new GridLayout(n + 1, 3, 2, 1));
        this.add(new Label(Lea._(da).a("strChart")));
        this.add(new Label(Lea._(da).a("strColor")));
        this.add(new Label(Lea._(da).a("strLineWidth")));
        this.ca = 0;
    }
    
    public Kea _() {
        final Kea kea = new Kea();
        for (int i = 0; i < this.sa.length; ++i) {
            final String s = this.sa[i];
            final Color background = this.ta[i].getBackground();
            int selectedIndex = -1;
            if (this.ua[i] != null) {
                selectedIndex = this.ua[i].getSelectedIndex();
            }
            kea.b(s, background, selectedIndex);
        }
        return kea;
    }
    
    public void _(final String s, final Color background, final int n) {
        if (this.ca < this.aa.length) {
            this.sa[this.ca] = s;
            this.add(this.aa[this.ca] = new Label(Lea._(this.da).a("str" + s)));
            (this.ta[this.ca] = new Button("")).setBackground(background);
            this.ta[this.ca].addActionListener(this);
            this.add(this.ta[this.ca]);
            this.ua[this.ca] = this.b(n);
            if (this.ua[this.ca] != null) {
                this.add(this.ua[this.ca]);
            }
            else {
                this.add(new Label("N/A"));
            }
            ++this.ca;
        }
    }
    
    private Choice b(final int n) {
        if (n < 0) {
            return null;
        }
        final Choice choice = new Choice();
        choice.add(Lea._(this.da).a("cbWidth0"));
        choice.add(Lea._(this.da).a("cbWidth1"));
        choice.add(Lea._(this.da).a("cbWidth2"));
        choice.add(Lea._(this.da).a("cbWidth3"));
        choice.add(Lea._(this.da).a("cbWidth4"));
        choice.add(Lea._(this.da).a("cbWidth5"));
        if (n < choice.getItemCount()) {
            choice.select(n);
        }
        else {
            choice.select(0);
        }
        return choice;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        for (int i = 0; i < this.ca; ++i) {
            if (actionEvent.getSource() == this.ta[i]) {
                final Pea pea = new Pea(Lea._(this.da), Lea._(this.da).a("strSelectColor"), Lea._(this.da));
                pea.show();
                if (pea.getColor() != null) {
                    this.ta[i].setBackground(pea.getColor());
                    this.ta[i].repaint();
                }
            }
        }
    }
}
