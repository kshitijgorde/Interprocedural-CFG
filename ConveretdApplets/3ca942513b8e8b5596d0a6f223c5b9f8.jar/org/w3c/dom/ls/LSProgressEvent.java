// 
// Decompiled by Procyon v0.5.30
// 

package org.w3c.dom.ls;

import org.w3c.dom.events.Event;

public interface LSProgressEvent extends Event
{
    LSInput getInput();
    
    int getPosition();
    
    int getTotalSize();
}
