import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Choice;
import java.awt.Label;

// 
// Decompiled by Procyon v0.5.30
// 

class Nj extends a
{
    Label[] qa;
    Choice[] ra;
    int sa;
    private final Gj ta;
    
    public Nj(final Gj ta, final int n) {
        super(1, new Insets(8, 8, 8, 8));
        this.ta = ta;
        this.qa = new Label[n];
        this.ra = new Choice[n];
        this.setLayout(new GridLayout(n, 2, 2, 5));
        this.sa = 0;
    }
    
    public int b() {
        return this.ra[0].getSelectedIndex();
    }
    
    public int _() {
        return this.ra[1].getSelectedIndex();
    }
    
    public void _(final String s, final int n, final int n2) {
        if (this.sa < this.qa.length) {
            this.add(this.qa[this.sa] = new Label(Gj.a(this.ta).b(s)));
            this.add(this.ra[this.sa] = this.a(n, n2));
            ++this.sa;
        }
    }
    
    private Choice a(final int n, final int n2) {
        if (n < 0) {
            return null;
        }
        final Choice choice = new Choice();
        choice.add(Gj.a(this.ta).b("cbTimeframe0"));
        for (int i = 1; i <= n2; ++i) {
            choice.add(Gj.a(this.ta).b(String.valueOf(i)));
        }
        if (n < choice.getItemCount()) {
            choice.select(n);
        }
        else {
            choice.select(0);
        }
        return choice;
    }
}
