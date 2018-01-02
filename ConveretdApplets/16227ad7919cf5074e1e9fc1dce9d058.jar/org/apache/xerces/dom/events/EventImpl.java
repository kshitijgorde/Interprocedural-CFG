// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom.events;

import org.w3c.dom.Node;
import org.apache.xerces.domx.events.EventTarget;
import org.apache.xerces.domx.events.Event;

public class EventImpl implements Event
{
    public String type;
    public EventTarget target;
    public Node currentNode;
    public short eventPhase;
    public boolean initialized;
    public boolean bubbles;
    public boolean cancelable;
    public boolean stopPropagation;
    public boolean preventDefault;
    
    public void initEvent(final String type, final boolean bubbles, final boolean cancelable) {
        this.type = type;
        this.bubbles = bubbles;
        this.cancelable = cancelable;
        this.initialized = true;
    }
    
    public boolean getBubbles() {
        return this.bubbles;
    }
    
    public boolean getCancelable() {
        return this.cancelable;
    }
    
    public Node getCurrentNode() {
        return this.currentNode;
    }
    
    public short getEventPhase() {
        return this.eventPhase;
    }
    
    public EventTarget getTarget() {
        return this.target;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void stopPropagation() {
        this.stopPropagation = true;
    }
    
    public void preventDefault() {
        this.preventDefault = true;
    }
    
    public EventImpl() {
        this.initialized = false;
        this.bubbles = true;
        this.cancelable = false;
        this.stopPropagation = false;
        this.preventDefault = false;
    }
}
