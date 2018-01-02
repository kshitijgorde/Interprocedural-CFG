// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.combo;

import org.xmodel.Xlate;
import org.xmodel.IModelObject;

class Item
{
    public Object value;
    
    public Item(final Object value) {
        this.value = value;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof IModelObject) {
            return Xlate.get((IModelObject)this.value, "").equals(this.toString());
        }
        return o.toString().equals(this.toString());
    }
    
    @Override
    public String toString() {
        if (this.value instanceof IModelObject) {
            return Xlate.get((IModelObject)this.value, "");
        }
        return this.value.toString();
    }
}
