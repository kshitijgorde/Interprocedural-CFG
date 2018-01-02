// 
// Decompiled by Procyon v0.5.30
// 

abstract class Translator
{
    static final int HARD_STOP = 0;
    static final int INTERRUPT = 1;
    static final int BOUNDARY = 2;
    protected Controller m_controller;
    
    abstract boolean updateViewpoint(final float[] p0);
    
    boolean interrupt(final int type) {
        return true;
    }
    
    void close() {
    }
    
    void handleEvent(final Event e) {
    }
    
    protected float getIntensity() {
        return 30.0f / this.m_controller.getFrameRate();
    }
    
    protected float accelerate() {
        return 0.00698f * this.getIntensity();
    }
    
    protected float newZoom(final float zoom, final boolean in) {
        final float dir = in ? 1.0f : -1.0f;
        return (float)Math.exp(Math.log(zoom) + dir * this.accelerate());
    }
}
