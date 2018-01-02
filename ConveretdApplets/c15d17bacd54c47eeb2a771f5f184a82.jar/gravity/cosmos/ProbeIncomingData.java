// 
// Decompiled by Procyon v0.5.30
// 

package gravity.cosmos;

public class ProbeIncomingData
{
    public int nrOfScannedPlanets;
    public int ticks;
    public boolean targetScanned;
    
    public ProbeIncomingData(final int nrOfScannedPlanets, final int ticks, final boolean targetScanned) {
        this.nrOfScannedPlanets = 0;
        this.ticks = 0;
        this.targetScanned = false;
        this.nrOfScannedPlanets = nrOfScannedPlanets;
        this.ticks = ticks;
        this.targetScanned = targetScanned;
    }
}
