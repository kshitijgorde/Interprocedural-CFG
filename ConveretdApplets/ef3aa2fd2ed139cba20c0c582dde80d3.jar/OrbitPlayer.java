import astro.ATime;

// 
// Decompiled by Procyon v0.5.30
// 

class OrbitPlayer implements Runnable
{
    OrbitViewer orbitViewer;
    
    public OrbitPlayer(final OrbitViewer orbitViewer) {
        this.orbitViewer = orbitViewer;
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex) {
                break;
            }
            final ATime atime = this.orbitViewer.getAtime();
            atime.changeDate(this.orbitViewer.timeStep, this.orbitViewer.playDirection);
            this.orbitViewer.setNewDate(atime);
        }
    }
}
