// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.util.Hashtable;

public abstract class bc
{
    public abstract String b(final Hashtable p0, final ak p1);
    
    public abstract bp a();
    
    public final bp a(final Hashtable hashtable, final di a) {
        final bp a2;
        final bp bp;
        (bp = (a2 = this.a())).a = a;
        bp.e = new boolean[4];
        bp.a(hashtable, a);
        return a2;
    }
    
    public final String a(final Hashtable hashtable, final ak ak) {
        String s = this.b(hashtable, ak);
        if (hashtable.get("dc") != null) {
            s = ((s != null) ? (s + " ") : "") + hashtable.get("dc");
        }
        return s;
    }
}
