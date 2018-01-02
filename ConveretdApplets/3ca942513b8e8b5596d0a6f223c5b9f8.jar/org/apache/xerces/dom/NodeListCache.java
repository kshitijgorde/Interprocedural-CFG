// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import java.io.Serializable;

class NodeListCache implements Serializable
{
    private static final long serialVersionUID = -7927529254918631002L;
    int fLength;
    int fChildIndex;
    ChildNode fChild;
    ParentNode fOwner;
    NodeListCache next;
    
    NodeListCache(final ParentNode fOwner) {
        this.fLength = -1;
        this.fChildIndex = -1;
        this.fOwner = fOwner;
    }
}
