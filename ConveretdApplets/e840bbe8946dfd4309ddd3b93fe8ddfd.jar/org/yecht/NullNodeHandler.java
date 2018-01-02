// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht;

public class NullNodeHandler implements NodeHandler
{
    private long current;
    
    public NullNodeHandler() {
        this.current = 0L;
    }
    
    public Object handle(final Parser p, final Node n) {
        return this.current++;
    }
}
