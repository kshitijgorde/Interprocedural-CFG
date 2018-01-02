// 
// Decompiled by Procyon v0.5.30
// 

package mechanalyst;

import java.util.Vector;

public class PrePostList extends Vector
{
    public void add(final String s, final String s2) {
        this.addElement(new PrePost(s, s2));
    }
    
    public void print(final int n) {
        for (int i = 0; i < this.size(); ++i) {
            ((PrePost)this.elementAt(i)).print(n);
        }
    }
    
    String xlate(final String s) {
        for (int i = 0; i < this.size(); ++i) {
            final PrePost prePost = this.elementAt(i);
            if (s.equals(prePost.src())) {
                return prePost.dest();
            }
        }
        return s;
    }
    
    public String translate(String s) {
        final String[] array = new String[2];
        String s2 = EString.trim(s);
        s = "";
        while (EString.match(s2, "* *", array)) {
            s = String.valueOf(s) + this.xlate(array[0]) + " ";
            s2 = EString.trim(array[1]);
        }
        s = String.valueOf(s) + this.xlate(s2);
        return s;
    }
}
