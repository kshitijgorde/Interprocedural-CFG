// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.domx.events;

import org.w3c.dom.DOMException;

public interface EventTarget
{
    void addEventListener(final String p0, final EventListener p1, final boolean p2);
    
    boolean dispatchEvent(final Event p0) throws DOMException;
    
    void removeEventListener(final String p0, final EventListener p1, final boolean p2);
}
