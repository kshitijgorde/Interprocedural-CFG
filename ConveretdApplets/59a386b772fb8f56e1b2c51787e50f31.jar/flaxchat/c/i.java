// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.c;

import java.util.Hashtable;

class i extends Hashtable
{
    private final c a;
    
    public synchronized Object a(final String s) {
        return super.get(s.toLowerCase());
    }
    
    public synchronized Object a(final String s, final Object o) {
        return super.put(s.toLowerCase(), o);
    }
    
    public synchronized Object b(final String s) {
        return super.remove(s.toLowerCase());
    }
    
    i(final c c) {
        this.a = c;
        this.a = c;
    }
}
