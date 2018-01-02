// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import java.io.Serializable;

class NodeListCache implements Serializable
{
    int fLength;
    int fChildIndex;
    ChildNode fChild;
    ParentNode fOwner;
    NodeListCache next;
    
    NodeListCache(final ParentNode owner) {
        this.fLength = -1;
        this.fChildIndex = -1;
        this.fOwner = owner;
    }
}
