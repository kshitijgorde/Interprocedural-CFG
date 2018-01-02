// 
// Decompiled by Procyon v0.5.30
// 

package mechanalyst;

import java.util.Vector;

public class SynList extends Vector
{
    public void add(final WordList list) {
        this.addElement(list);
    }
    
    public void print(final int n) {
        for (int i = 0; i < this.size(); ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.print(" ");
            }
            System.out.print("synon: ");
            ((WordList)this.elementAt(i)).print(n);
        }
    }
    
    public WordList find(final String s) {
        for (int i = 0; i < this.size(); ++i) {
            final WordList list = this.elementAt(i);
            if (list.find(s)) {
                return list;
            }
        }
        return null;
    }
    
    boolean matchDecomp(final String s, String string, final String[] array) {
        if (!EString.match(string, "*@* *", array)) {
            return EString.match(s, string, array);
        }
        final String s2 = array[0];
        final String s3 = array[1];
        final String string2 = " " + array[2];
        final WordList find = this.find(s3);
        if (find == null) {
            System.out.println("Could not fnd syn list for " + s3);
            return false;
        }
        for (int i = 0; i < find.size(); ++i) {
            string = String.valueOf(s2) + find.elementAt(i) + string2;
            if (EString.match(s, string, array)) {
                final int count = EString.count(s2, '*');
                for (int j = array.length - 2; j >= count; --j) {
                    array[j + 1] = array[j];
                }
                array[count] = find.elementAt(i);
                return true;
            }
        }
        return false;
    }
}
