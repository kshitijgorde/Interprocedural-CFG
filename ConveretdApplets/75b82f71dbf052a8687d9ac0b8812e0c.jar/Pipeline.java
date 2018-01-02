// 
// Decompiled by Procyon v0.5.30
// 

class Pipeline extends Filter
{
    protected Filter filter;
    
    void addFilter(final Filter filter) {
        this.filter = filter;
    }
    
    void setProperty(final String key, final Object datum) {
        try {
            if (key.equals("ptrz")) {
                ((Dewarp)this.filter).limitViewpoint((float[])datum);
            }
        }
        catch (NullPointerException ex) {}
        this.filter.setProperty(key, datum);
    }
    
    Object getProperty(final String key) {
        return this.filter.getProperty(key);
    }
    
    synchronized void setInput(final Frame src) {
        super.setInput(src);
        this.filter.setInput(src);
    }
    
    void setOutput(final Frame dst) {
        super.setOutput(dst);
        this.filter.setOutput(dst);
    }
    
    Frame getSource() {
        return this.src;
    }
    
    Frame getOutput() {
        return this.dst;
    }
    
    synchronized void render() {
        synchronized (this.src) {
            this.filter.render();
        }
        // monitorexit(this.src)
    }
    
    float getvFOVMin(final float zoom) {
        return ((Dewarp)this.filter).getvFOVMin(zoom);
    }
    
    float getvFOVMax(final float zoom) {
        return ((Dewarp)this.filter).getvFOVMax(zoom);
    }
    
    float gethFOVMin(final float zoom) {
        return ((Dewarp)this.filter).gethFOVMin(zoom);
    }
    
    float gethFOVMax(final float zoom) {
        return ((Dewarp)this.filter).gethFOVMax(zoom);
    }
    
    float getZoomMax() {
        final float[] vp = { 0.0f, 0.0f, 0.0f, 10000.0f };
        ((Dewarp)this.filter).limitViewpoint(vp);
        return vp[3];
    }
    
    float getZoomMin() {
        final float[] vp = { 0.0f, 0.0f, 0.0f, 0.0f };
        ((Dewarp)this.filter).limitViewpoint(vp);
        return vp[3];
    }
}
