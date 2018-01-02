// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.util;

import java.util.Enumeration;

public class SequenceEnumeration implements Enumeration
{
    private Enumeration first;
    private Enumeration second;
    
    public SequenceEnumeration(final Enumeration first, final Enumeration second) {
        this.first = first;
        this.second = second;
    }
    
    public boolean hasMoreElements() {
        return this.first.hasMoreElements() || this.second.hasMoreElements();
    }
    
    public synchronized Object nextElement() {
        if (this.first.hasMoreElements()) {
            return this.first.nextElement();
        }
        return this.second.nextElement();
    }
}
