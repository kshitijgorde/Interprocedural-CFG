// 
// Decompiled by Procyon v0.5.30
// 

package mechanalyst;

import java.util.Vector;

public class KeyList extends Vector
{
    public void add(final String s, final int n, final DecompList list) {
        this.addElement(new Key(s, n, list));
    }
    
    public void print(final int n) {
        for (int i = 0; i < this.size(); ++i) {
            ((Key)this.elementAt(i)).print(n);
        }
    }
    
    Key getKey(final String s) {
        for (int i = 0; i < this.size(); ++i) {
            final Key key = this.elementAt(i);
            if (s.equals(key.key())) {
                return key;
            }
        }
        return null;
    }
    
    public void buildKeyStack(final KeyStack keyStack, String trim) {
        keyStack.reset();
        trim = EString.trim(trim);
        for (String[] array = new String[2]; EString.match(trim, "* *", array); trim = array[1]) {
            final Key key = this.getKey(array[0]);
            if (key != null) {
                keyStack.pushKey(key);
            }
        }
        final Key key2 = this.getKey(trim);
        if (key2 != null) {
            keyStack.pushKey(key2);
        }
    }
}
