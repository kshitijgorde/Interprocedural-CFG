// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.domx.events;

import org.w3c.dom.Node;

public interface MutationEvent extends Event
{
    String getAttrName();
    
    String getNewValue();
    
    String getPrevValue();
    
    Node getRelatedNode();
    
    void initMutationEvent(final String p0, final boolean p1, final boolean p2, final Node p3, final String p4, final String p5, final String p6);
}
