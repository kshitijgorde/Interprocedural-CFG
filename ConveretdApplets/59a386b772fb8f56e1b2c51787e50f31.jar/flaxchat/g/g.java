// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.g;

import java.util.Enumeration;

final class g implements Enumeration
{
    Enumeration a;
    
    public boolean hasMoreElements() {
        return this.a.hasMoreElements();
    }
    
    public Object nextElement() {
        return this.a.nextElement().c;
    }
    
    g(final f f) {
        this.a = f.a(f).c();
    }
}
