// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.components;

import java.util.Hashtable;
import java.io.Serializable;

public abstract class ComponentStatus implements Serializable
{
    private int ivComponentType;
    private Hashtable ivSubComponentStates;
    
    public int getComponentType() {
        return this.ivComponentType;
    }
    
    public Hashtable getSubComponentStates() {
        return this.ivSubComponentStates;
    }
    
    public ComponentStatus getSubComponentStatus(final int pSubComponentID) {
        if (this.ivSubComponentStates == null) {
            return null;
        }
        return this.ivSubComponentStates.get(new StringBuilder().append(pSubComponentID).toString());
    }
    
    public void setComponentType(final int pType) {
        this.ivComponentType = pType;
    }
    
    public void setSubComponentStates(final Hashtable pComponentStates) {
        this.ivSubComponentStates = pComponentStates;
    }
    
    public void setSubComponentStatus(final int pSubComponentID, final ComponentStatus pSubComponentStatus) {
        if (this.ivSubComponentStates == null) {
            this.ivSubComponentStates = new Hashtable();
        }
        this.ivSubComponentStates.put(new StringBuilder().append(pSubComponentID).toString(), pSubComponentStatus);
    }
}
