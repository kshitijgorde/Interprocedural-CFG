// 
// Decompiled by Procyon v0.5.30
// 

package gravity.missions;

public class MissionScanPlanets extends Mission
{
    public MissionScanPlanets(final int minPlanetsScannedNr) {
        super._drawAtmospheres = true;
        super._minPlanetsScannedNr = minPlanetsScannedNr;
    }
    
    public String getDescription() {
        if (super._minPlanetsScannedNr == 1) {
            return new String("Scan 1 planet");
        }
        return new String("Scan " + super._minPlanetsScannedNr + " planets");
    }
    
    public String getSuccessMessage(final int n, final int n2, final int n3) {
        if (n2 == 1) {
            return new String("You scanned 1 planet. Well done...");
        }
        return new String("You scanned " + n2 + " planets. Well done...");
    }
    
    public String getIncomingData(final int n, final int n2, final boolean b) {
        if (n <= 0) {
            return new String();
        }
        if (n == 1) {
            return new String("Incoming data: 1 planet scanned...");
        }
        return new String("Incoming data: " + n + " planets scanned...");
    }
    
    public String getFailureAdditional(final int n, final int n2) {
        if (n <= 0) {
            return new String();
        }
        if (n == 1) {
            return new String(" - 1 planet scanned");
        }
        return new String(" - " + n + " planets scanned");
    }
}
