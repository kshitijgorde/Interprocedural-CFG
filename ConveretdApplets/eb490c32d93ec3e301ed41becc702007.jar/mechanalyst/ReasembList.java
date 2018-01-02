// 
// Decompiled by Procyon v0.5.30
// 

package mechanalyst;

import java.util.Vector;

public class ReasembList extends Vector
{
    public void add(final String s) {
        this.addElement(s);
    }
    
    public void print(final int n) {
        for (int i = 0; i < this.size(); ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.print(" ");
            }
            System.out.println("reasemb: " + (String)this.elementAt(i));
        }
    }
}
