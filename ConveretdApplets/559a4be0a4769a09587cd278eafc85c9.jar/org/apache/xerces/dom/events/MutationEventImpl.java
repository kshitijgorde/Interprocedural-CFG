// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom.events;

import org.w3c.dom.Node;
import org.w3c.dom.events.MutationEvent;

public class MutationEventImpl extends EventImpl implements MutationEvent
{
    Node relatedNode;
    String prevValue;
    String newValue;
    String attrName;
    public short attrChange;
    public static final String DOM_SUBTREE_MODIFIED = "DOMSubtreeModified";
    public static final String DOM_NODE_INSERTED = "DOMNodeInserted";
    public static final String DOM_NODE_REMOVED = "DOMNodeRemoved";
    public static final String DOM_NODE_REMOVED_FROM_DOCUMENT = "DOMNodeRemovedFromDocument";
    public static final String DOM_NODE_INSERTED_INTO_DOCUMENT = "DOMNodeInsertedIntoDocument";
    public static final String DOM_ATTR_MODIFIED = "DOMAttrModified";
    public static final String DOM_CHARACTER_DATA_MODIFIED = "DOMCharacterDataModified";
    
    public MutationEventImpl() {
        this.relatedNode = null;
        this.prevValue = null;
        this.newValue = null;
        this.attrName = null;
    }
    
    public String getAttrName() {
        return this.attrName;
    }
    
    public short getAttrChange() {
        return this.attrChange;
    }
    
    public String getNewValue() {
        return this.newValue;
    }
    
    public String getPrevValue() {
        return this.prevValue;
    }
    
    public Node getRelatedNode() {
        return this.relatedNode;
    }
    
    public void initMutationEvent(final String typeArg, final boolean canBubbleArg, final boolean cancelableArg, final Node relatedNodeArg, final String prevValueArg, final String newValueArg, final String attrNameArg, final short attrChangeArg) {
        this.relatedNode = relatedNodeArg;
        this.prevValue = prevValueArg;
        this.newValue = newValueArg;
        this.attrName = attrNameArg;
        this.attrChange = attrChangeArg;
        super.initEvent(typeArg, canBubbleArg, cancelableArg);
    }
}
