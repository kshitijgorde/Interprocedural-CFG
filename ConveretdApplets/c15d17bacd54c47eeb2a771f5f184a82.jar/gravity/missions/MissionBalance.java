// 
// Decompiled by Procyon v0.5.30
// 

package gravity.missions;

public class MissionBalance extends Mission
{
    public MissionBalance(final int minTicksNr) {
        super._minTicksNr = minTicksNr;
        super._escapeProbe = false;
    }
    
    public String getDescription() {
        return new String("Keep the balance for " + super._minTicksNr + " ticks");
    }
    
    public String getSuccessMessage(final int n, final int n2, final int n3) {
        return new String("You kept it going for " + n3 + " ticks. Nice job...");
    }
    
    public String getIncomingData(final int n, final int n2, final boolean b) {
        return new String(n2 + " ticks and going...");
    }
    
    public String getFailureAdditional(final int n, final int n2) {
        return new String(" after " + n2 + " ticks");
    }
}
