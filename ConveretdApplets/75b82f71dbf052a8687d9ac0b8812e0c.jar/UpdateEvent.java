// 
// Decompiled by Procyon v0.5.30
// 

class UpdateEvent
{
    protected float[] dvp;
    
    UpdateEvent(final float[] inDVP) {
        this.dvp = inDVP;
    }
    
    UpdateEvent() {
    }
    
    float[] getViewpointChange() {
        return this.dvp;
    }
    
    void setViewpointChange(final float[] inDVP) {
        this.dvp = inDVP;
    }
}
