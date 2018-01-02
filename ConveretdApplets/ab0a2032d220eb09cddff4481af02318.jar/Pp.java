import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Choice;
import java.awt.Label;

// 
// Decompiled by Procyon v0.5.30
// 

class Pp extends Vo
{
    Label[] GDb;
    Choice[] HDb;
    int IDb;
    private final Op n;
    
    public Pp(final Op n, final int n2) {
        super(1, new Insets(8, 8, 8, 8));
        this.n = n;
        this.GDb = new Label[n2];
        this.HDb = new Choice[n2];
        this.setLayout(new GridLayout(n2, 2, 2, 5));
        this.IDb = 0;
    }
    
    public int E() {
        return this.HDb[0].getSelectedIndex();
    }
    
    public int F() {
        return this.HDb[1].getSelectedIndex();
    }
    
    public void _(final String s, final int n, final int n2) {
        if (this.IDb < this.GDb.length) {
            this.add(this.GDb[this.IDb] = new Label(Op._(this.n)._(s)));
            this.add(this.HDb[this.IDb] = this.a(n, n2));
            ++this.IDb;
        }
    }
    
    private Choice a(final int n, final int n2) {
        if (n < 0) {
            return null;
        }
        final Choice choice = new Choice();
        choice.add(Op._(this.n)._("cbTimeframe0"));
        for (int i = 1; i <= n2; ++i) {
            choice.add(Op._(this.n)._(String.valueOf(i)));
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
