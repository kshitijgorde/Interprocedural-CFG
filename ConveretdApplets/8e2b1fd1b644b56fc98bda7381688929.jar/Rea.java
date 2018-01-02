import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Choice;
import java.awt.Label;

// 
// Decompiled by Procyon v0.5.30
// 

class Rea extends continue
{
    Label[] aa;
    Choice[] ba;
    int ca;
    private final Iea da;
    
    public Rea(final Iea da, final int n) {
        super(1, new Insets(8, 8, 8, 8));
        this.da = da;
        this.aa = new Label[n];
        this.ba = new Choice[n];
        this.setLayout(new GridLayout(n, 2, 2, 5));
        this.ca = 0;
    }
    
    public int e() {
        return this.ba[0].getSelectedIndex();
    }
    
    public int f() {
        return this.ba[1].getSelectedIndex();
    }
    
    public void _(final String s, final int n, final int n2) {
        if (this.ca < this.aa.length) {
            this.add(this.aa[this.ca] = new Label(Iea.b(this.da).a(s)));
            this.add(this.ba[this.ca] = this.b(n, n2));
            ++this.ca;
        }
    }
    
    private Choice b(final int n, final int n2) {
        if (n < 0) {
            return null;
        }
        final Choice choice = new Choice();
        choice.add(Iea.b(this.da).a("cbTimeframe0"));
        for (int i = 1; i <= n2; ++i) {
            choice.add(Iea.b(this.da).a(String.valueOf(i)));
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
