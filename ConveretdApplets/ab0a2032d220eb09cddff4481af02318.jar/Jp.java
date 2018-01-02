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

class Jp extends Vo implements ActionListener
{
    String[] YDb;
    Label[] GDb;
    Button[] ZDb;
    Choice[] _Eb;
    int IDb;
    private final Gp n;
    
    public Jp(final Gp n, final int n2) {
        super(1, new Insets(8, 8, 8, 8));
        this.n = n;
        this.YDb = new String[n2];
        this.GDb = new Label[n2];
        this.ZDb = new Button[n2];
        this._Eb = new Choice[n2];
        this.setLayout(new GridLayout(n2 + 1, 3, 2, 2));
        this.add(new Label(Gp.b(n)._("strChart")));
        this.add(new Label(Gp.b(n)._("strColor")));
        this.add(new Label(Gp.b(n)._("strLineWidth")));
        this.IDb = 0;
    }
    
    public Fp a() {
        final Fp fp = new Fp();
        for (int i = 0; i < this.YDb.length; ++i) {
            final String s = this.YDb[i];
            final Color background = this.ZDb[i].getBackground();
            int selectedIndex = -1;
            if (this._Eb[i] != null) {
                selectedIndex = this._Eb[i].getSelectedIndex();
            }
            fp.b(s, background, selectedIndex);
        }
        return fp;
    }
    
    public void _(final String s, final Color background, final int n) {
        if (this.IDb < this.GDb.length) {
            this.YDb[this.IDb] = s;
            this.add(this.GDb[this.IDb] = new Label(Gp.b(this.n)._("str" + s)));
            (this.ZDb[this.IDb] = new Button("")).setBackground(background);
            this.ZDb[this.IDb].addActionListener(this);
            this.add(this.ZDb[this.IDb]);
            this._Eb[this.IDb] = this.b(n);
            if (this._Eb[this.IDb] != null) {
                this.add(this._Eb[this.IDb]);
            }
            else {
                this.add(new Label("N/A"));
            }
            ++this.IDb;
        }
    }
    
    private Choice b(final int n) {
        if (n < 0) {
            return null;
        }
        final Choice choice = new Choice();
        choice.add(Gp.b(this.n)._("cbWidth0"));
        choice.add(Gp.b(this.n)._("cbWidth1"));
        choice.add(Gp.b(this.n)._("cbWidth2"));
        choice.add(Gp.b(this.n)._("cbWidth3"));
        choice.add(Gp.b(this.n)._("cbWidth4"));
        choice.add(Gp.b(this.n)._("cbWidth5"));
        if (n < choice.getItemCount()) {
            choice.select(n);
        }
        else {
            choice.select(0);
        }
        return choice;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        for (int i = 0; i < this.IDb; ++i) {
            if (actionEvent.getSource() == this.ZDb[i]) {
                final Kp kp = new Kp(Gp.b(this.n), Gp.b(this.n)._("strSelectColor"), Gp.b(this.n));
                kp.show();
                if (kp.getColor() != null) {
                    this.ZDb[i].setBackground(kp.getColor());
                    this.ZDb[i].repaint();
                }
            }
        }
    }
}
