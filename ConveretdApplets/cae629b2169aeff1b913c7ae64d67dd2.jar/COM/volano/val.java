// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.awt.MenuItem;
import java.util.StringTokenizer;
import java.awt.CheckboxMenuItem;
import java.awt.Menu;

public class val extends Menu
{
    public CheckboxMenuItem a;
    private CheckboxMenuItem b;
    
    public val(final vd vd) {
        super(vd.fp);
        final StringTokenizer stringTokenizer = new StringTokenizer(vd.fq, "|");
        while (stringTokenizer.hasMoreTokens()) {
            this.add(new CheckboxMenuItem(stringTokenizer.nextToken().trim()));
        }
        if (this.countItems() > 0) {
            this.addSeparator();
        }
        this.add(this.a = new CheckboxMenuItem(vd.fr));
        this.a(this.a);
    }
    
    public void a(final CheckboxMenuItem b) {
        if (this.b != null) {
            this.b.setState(false);
        }
        b.setState(true);
        this.b = b;
    }
    
    public void a(final int n) {
        if (n >= 0 && n <= this.countItems()) {
            this.a((n == 0) ? this.a : ((CheckboxMenuItem)this.getItem(n - 1)));
        }
    }
    
    public int a(final Object o) {
        if (o == this.a) {
            return 0;
        }
        int n = -1;
        for (int countItems = this.countItems(), n2 = 0; n == -1 && n2 < countItems; ++n2) {
            if (o == this.getItem(n2)) {
                n = n2;
            }
        }
        return n + 1;
    }
}
