// 
// Decompiled by Procyon v0.5.30
// 

package mechanalyst;

import java.util.Vector;

public class WordList extends Vector
{
    public void add(final String s) {
        this.addElement(s);
    }
    
    public void print(final int n) {
        for (int i = 0; i < this.size(); ++i) {
            System.out.print(String.valueOf(this.elementAt(i)) + "  ");
        }
        System.out.println();
    }
    
    boolean find(final String s) {
        for (int i = 0; i < this.size(); ++i) {
            if (s.equals(this.elementAt(i))) {
                return true;
            }
        }
        return false;
    }
}
