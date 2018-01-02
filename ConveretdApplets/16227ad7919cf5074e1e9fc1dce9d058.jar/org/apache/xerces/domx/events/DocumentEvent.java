// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.domx.events;

import org.w3c.dom.DOMException;

public interface DocumentEvent
{
    Event createEvent(final String p0) throws DOMException;
}
