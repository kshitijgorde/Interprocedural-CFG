// 
// Decompiled by Procyon v0.5.30
// 

final class MouseDetection implements Runnable
{
    private client clientInstance;
    public final Object syncObject;
    public final int[] coordsY;
    public boolean running;
    public final int[] coordsX;
    public int coordsIndex;
    
    @Override
    public void run() {
        while (this.running) {
            synchronized (this.syncObject) {
                if (this.coordsIndex < 500) {
                    this.coordsX[this.coordsIndex] = this.clientInstance.mouseX;
                    this.coordsY[this.coordsIndex] = this.clientInstance.mouseY;
                    ++this.coordsIndex;
                }
            }
            try {
                Thread.sleep(50L);
            }
            catch (Exception ex) {}
        }
    }
    
    public MouseDetection(final client clientInstance) {
        this.syncObject = new Object();
        this.coordsY = new int[500];
        this.running = true;
        this.coordsX = new int[500];
        this.clientInstance = clientInstance;
    }
}
