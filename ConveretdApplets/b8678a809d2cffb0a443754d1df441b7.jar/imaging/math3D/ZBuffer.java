// 
// Decompiled by Procyon v0.5.30
// 

package imaging.math3D;

public class ZBuffer
{
    private short[] depthBuffer;
    private int width;
    private int height;
    
    public ZBuffer(final int width, final int height) {
        this.depthBuffer = new short[width * height];
        this.width = width;
        this.height = height;
        this.clear();
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public void clear() {
        for (int i = 0; i < this.depthBuffer.length; ++i) {
            this.depthBuffer[i] = 0;
        }
    }
    
    public void setDepth(final int offset, final short depth) {
        this.depthBuffer[offset] = depth;
    }
    
    public boolean checkDepth(final int offset, final short depth) {
        if (depth >= this.depthBuffer[offset]) {
            this.depthBuffer[offset] = depth;
            return true;
        }
        return false;
    }
}
