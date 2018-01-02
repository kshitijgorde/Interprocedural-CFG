// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

class ItemEditMaskStrData
{
    ItemEditMaskState state;
    
    ItemEditMaskStrData(final ItemEditMaskState state) {
        this.state = state;
        state.privateObject = this;
    }
}
