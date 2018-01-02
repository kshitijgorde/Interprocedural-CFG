// 
// Decompiled by Procyon v0.5.30
// 

package mechanalyst;

import java.util.Vector;

public class DecompList extends Vector
{
    public void add(final String s, final boolean b, final ReasembList list) {
        this.addElement(new Decomp(s, b, list));
    }
    
    public void print(final int n) {
        for (int i = 0; i < this.size(); ++i) {
            ((Decomp)this.elementAt(i)).print(n);
        }
    }
}
