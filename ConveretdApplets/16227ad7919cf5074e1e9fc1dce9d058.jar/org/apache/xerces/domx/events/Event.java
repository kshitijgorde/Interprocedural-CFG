// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.domx.events;

import org.w3c.dom.Node;

public interface Event
{
    public static final short BUBBLING_PHASE = 1;
    public static final short CAPTURING_PHASE = 2;
    public static final short AT_TARGET = 3;
    
    boolean getBubbles();
    
    boolean getCancelable();
    
    Node getCurrentNode();
    
    short getEventPhase();
    
    EventTarget getTarget();
    
    String getType();
    
    void initEvent(final String p0, final boolean p1, final boolean p2);
    
    void stopPropagation();
    
    void preventDefault();
}
